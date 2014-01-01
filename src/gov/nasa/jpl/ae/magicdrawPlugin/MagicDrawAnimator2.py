'''
Created on Sep 18, 2012

'''
#handy python things
import time,sys,traceback,re

#some MD utils. 
from com.nomagic.magicdraw.core import * #application, project...
from com.nomagic.magicdraw.core import Application #this seems to want its own import.
from com.nomagic.magicdraw.openapi.uml import PresentationElementsManager
from com.nomagic.magicdraw.openapi.uml import SessionManager
from com.nomagic.magicdraw.properties import ColorProperty
from com.nomagic.magicdraw.properties import PropertyManager
from com.nomagic.magicdraw.properties import PropertyID 

#threading, etc.
from java.awt import Rectangle
from java.awt import Color
from java.lang import *
from javax.swing import JOptionPane

import os
from os.path import expanduser
homeDir = expanduser("~")
#homeDir = '/home/bclement'
gitDir = homeDir + os.sep + 'git'
projectPath = gitDir + os.sep + 'bae'
filepath = projectPath + os.sep + 'simulationSnapshot.example.txt'

usingDialogs = True
if usingDialogs:
    from javax.swing import JOptionPane
    from javax.swing import JDialog

#this is where the actual animator class lives
import MagicDrawAnimatorUtils2
reload (MagicDrawAnimatorUtils2)

global gl
gl = Application.getInstance().getGUILog()
        
def setup():
    '''
    I use this to store anything handy I want to use. 
    This script is pretty simple so far so it's very small...
    '''
    global gl
    gl = Application.getInstance().getGUILog()
    gl.clearLog()

    return True

def run(x):
    '''
    Do whatever can be done in normal MD thread here. 
    Probably good to run the translator here at least.
    '''
    proceed = setup()
    if not proceed: return
    x = highlighterThread()
    gl.log("Timescale: " + str(x.timeScale))
    x.start()

class event(object):
    '''
    Just a data struct to hold info for a single event
    '''
    def __init__(self,t,a,cid,ctype):
        self.eventTime = t
        self.action = a
        self.componentId = cid
        self.componentType = ctype

        
    def str(self):
        s = str(self.eventTime) + ":" + str(self.action) + ":" \
        + str(self.componentId) + ":" + str(self.componentType)
        return s

class highlighterThread(Thread):
    '''
    By running animation activities in a separate thread, we can see them as they occur,
    rather than at the end of the script all piled together.
    1. Not sure if we actually need an init method for this... guess we could use it to 
    pass in config for the AE
    2. Currently set up to run a test with one element ID.
    3. Currently launched from the main toolbar - we'll need it to be launched from the element we
    want to simulate.
    '''
    def __init__(self):
        self.events = []
        self.timeScale = float(3.0) #animation sped up by this factor
        ts = JOptionPane.showInputDialog(
                                            None,
                                            "Increase speed by factor of...",
                                            "Set Timescale",
                                            JOptionPane.PLAIN_MESSAGE,
                                            None,
                                            None,
                                            "3.0")
        try: 
            if ts and float(ts): self.timeScale = float(ts)
        except: pass
        gl.log("using timescale: %s" % str(self.timeScale))
        self.frame = None
        if usingDialogs:
            self.frame = Application.getInstance().getMainFrame();

    def run(self):
        global filepath
        gl.log("running MagicDrawAnimator2")
        mode = 0
        if mode == 0:
            #filepath = "c:\\Users\\bclement\\git\\bae\\simulationSnapshot.Scenario_medium_2012.txt"
            #filepath = "c:\\Users\\bclement\\Desktop\\large6.txt"
            #filepath = "c:\\Users\\bclement\\Desktop\\foo12.txt"
            #filepath = "/Users/mjackson/Desktop/testSim.txt"
            gl.log("default filepath = %s" % filepath)
            filepath = JOptionPane.showInputDialog(
                                            None,
                                            "Select File to Animate",
                                            "Select File",
                                            JOptionPane.PLAIN_MESSAGE,
                                            None,
                                            None,
                                            filepath)
            gl.log("reading events from " + filepath)
            self.events = []
            try: f = open(filepath,"r")
            except:
                gl.log("can't find file @ %s" % filepath)
                return
            lastTime = float(0.0)
            latestTime = float(-1.0e20)
            earliestTime = float(1.0e20)
            gl.log( "MagicDrawAnimator2: before simulation, loading events from " + str(filepath) )
            print( "MagicDrawAnimator2: before simulation, loading events from " + str(filepath) )
            for line in f.readlines():
                gl.log("read line = " + line)
                x = re.search("(\d*)[^0-9: \t\n\r\f\v]*\s*:\s*\S*\s*(\S*) -> (\S*)\s*(\S*) ==>",line)
                y = re.search("(\S*) -> (\S*)\s*(\S*) ==>",line)
                if x: 
                    eventTime=float(x.groups()[0])
                    lastTime = eventTime
                    action=x.groups()[2]
                    cid = x.groups()[3]
                    ctype = x.groups()[1]
                    #gl.log("%s %s (%s)" % (action.upper(), cid, ctype))
                elif y:
                    eventTime=lastTime
                    action=y.groups()[1]
                    cid = y.groups()[2]
                    ctype = y.groups()[0]
                    #gl.log("%s %s (%s)" % (action.upper(), cid, ctype))
                elif line.startswith("---"): 
                    gl.log(line)
                    continue
                else: continue
                #gl.log("%s %s (%s)" % (action.upper(), cid, ctype))
                if any([x in cid for x in ["Main"]]): 
                    #gl.log("    ---> Skipping - can't animate Main")
                    continue
                if re.search("(_)?Activity(_.*)?(?!\S)",ctype): 
                    #gl.log("    ---> Skipping - can't animate the Activity object!")
                    continue
                try:
                    if eventTime < earliestTime:
                        earliestTime = eventTime
                    if eventTime > latestTime:
                        latestTime = eventTime
                    evt = event(eventTime,action,cid,ctype)
                    try: self.events.append(evt)
                    except: gl.log("NESTED ARGHHHHH")
                except: gl.log("ARRGHHH")
            
            print( "MagicDrawAnimator2: finished loading events from " + str(filepath) )
            gl.log( "MagicDrawAnimator2: finished loading events from " + str(filepath) )
            
            elementsNotEnded = []
            try:
                mda = MagicDrawAnimatorUtils2.MagicDrawAnimator2()
                #self.playEvents(mda)
                if self.timeScale == 0.0:
                    gl.log("*** ZERO TIMESCALE: setting timeScale to 1.0")
                    self.timeScale = 1.0
                if usingDialogs:
                    JOptionPane.showMessageDialog(self.frame,"Click ok to start simulation.")
                #simulatedDuration = (float(latestTime) - float(earliestTime))/ float(self.timeScale)
                simulatedDuration = (latestTime - earliestTime)/ self.timeScale
                lastTime = earliestTime
                elapsedTime = earliestTime
                simStartTime = earliestTime / self.timeScale  #start at time of first event
                elapsedSimTime = simStartTime #start at time of first event
                zeroTime = time.time()
            except:
                exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
                gl.log("*** EXCEPTION:")
                messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
                for message in messages:
                    gl.log(message)
            print("MagicDrawAnimator2: starting simulation with timeScale = " \
                   + str(self.timeScale) + "; last event at " + str(latestTime) \
                   + " scaling to " + str(simulatedDuration) + " seconds")
            gl.log("MagicDrawAnimator2: starting simulation with timeScale = " \
                   + str(self.timeScale) + "; last event at " + str(latestTime) \
                   + " scaling to " + str(simulatedDuration) + " seconds")
            try:
                for evt in self.events:
                    #gl.log("EVENT at " + str(evt.eventTime))
                    #Sprint("EVENT at " + str(evt.eventTime))
                    try: timeOfNextEvent = (float(evt.eventTime) + 0.0) / self.timeScale
                    except: 
                        exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
                        gl.log("*** EXCEPTION:")
                        messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
                        for message in messages:
                            gl.log(message)
                    #print("1")
                    if evt.eventTime != lastTime:
                        mda.doThePaint() #paint all events occurring at the previous event time
                        lastTime = float(evt.eventTime)
                    #print("2")
                    if timeOfNextEvent > elapsedSimTime:
                        print("sleeping sim time of next event(" + str(timeOfNextEvent) \
                               + ") - elapsed sim time(" + str(elapsedSimTime) + ") = " \
                               + str(timeOfNextEvent - elapsedSimTime) + " seconds")
                        gl.log("sleeping sim time of next event(" + str(timeOfNextEvent) \
                               + ") - elapsed sim time(" + str(elapsedSimTime) + ") = " \
                               + str(timeOfNextEvent - elapsedSimTime) + " seconds")
                        if (timeOfNextEvent - elapsedSimTime) > 300: 
                            gl.log("found time jump > 300")
                            break
                        time.sleep( timeOfNextEvent - elapsedSimTime )
                        
                    if "TimeVaryingMap" in evt.componentId:
                        try: val = evt.action.split("=")[1]
                        except: val = evt.action
                        gl.log("    ---> (%s) SETTING VALUE of %s: %s" % (evt.eventTime,evt.componentType,val))
                        
                    elif evt.componentType.startswith("sig") and "ObjectFlow" in evt.componentId:
                        sid = evt.componentType.strip("sig")
                        if "null" in evt.action and sid in elementsNotEnded:
                            gl.log("    ---> (%s) ENDING SIGNAL %s" % (evt.eventTime,sid))
                            mda.end(sid)
                            elementsNotEnded.remove(sid)
                        elif "null" not in evt.action: 
                            gl.log("    ---> (%s) STARTING SIGNAL %s ==> %s" % (evt.eventTime,sid,evt.action))
                            txt = ""
                            try: txt = evt.action.split("=")[1]
                            except: txt = evt.action
                            mda.start(sid,txt)
                            if sid not in elementsNotEnded: elementsNotEnded.append(sid)
                    
                    elif "start" in evt.action: 
                        gl.log("    ---> (%s) STARTING" % evt.eventTime)
                        mda.start(evt.componentId)
                        if evt.componentId not in elementsNotEnded: elementsNotEnded.append(evt.componentId)
                    elif "end" in evt.action:
                        gl.log("    ---> (%s) ENDING" % evt.eventTime)
                        mda.end(evt.componentId)
                        if evt.componentId in elementsNotEnded: elementsNotEnded.remove(evt.componentId)
                    t = time.time()
                    elapsedSimTime = t - zeroTime + simStartTime
                    elapsedTime = self.timeScale * elapsedSimTime
                    gl.log("Debug: elapsed time = " + str(elapsedSimTime))
            except: self.handleException()
            if len(elementsNotEnded)>0:
                gl.log("Ending Still-Active Events:")
                for e in elementsNotEnded:
                    gl.log("    ending %s " % e)
                    try: mda.end(e)
                    except: self.handleException()
                mda.doThePaint()
                
            else: gl.log("All Events Completed")
                
                
            gl.log("DONE")
        
        elif mode == 1:
            e = "_17_0_2_edc0357_1352328158277_34448_20209"
            mda = MagicDrawAnimatorUtils2.MagicDrawAnimator2()
            gl.log("Starting e (%s)" % e)
            mda.start(e,"Porpoise Hork")
            mda.doThePaint()
        
            i = 3
            while i > 0:
                i-=1
                gl.log(".")
                time.sleep(1)
            
            gl.log("ending e")
            mda.end(e)
            mda.doThePaint()
            
        elif mode == 2:
            try:
                mda = MagicDrawAnimatorUtils2.MagicDrawAnimator2()
                e = "_17_0_2_edc0357_1352328158277_34448_20209" # in the usePower activity
                gl.log("Starting e (%s)" % e)
                mda.start(e)
                mda.doThePaint()
                tb = None
                pem = PresentationElementsManager.getInstance()
                sm = SessionManager.getInstance()
                try:
                    sym = mda.gimmeTheSymbol(e)
                    midPoint = sym.getMiddlePoint()
                    sm.createSession("make box!")
                    try:
                        parentPM = sym.getPropertyManager()
                        c = Color.GREEN
                        tb = pem.createTextBox(sym,midPoint)
                        newBounds = Rectangle(15,15)
                        newBounds.setLocation(midPoint)
                        pem.reshapeShapeElement(tb,newBounds)
                        pem.setText(tb,"     gobblelkajdlfkjakj")
                        pem.setPresentationElementProperties(tb, parentPM)
                        sm.closeSession()
                    except: 
                        sm.cancelSession()
                        self.handleException() 
                except: self.handleException()
                
                
                i = 3
                while i > 0:
                    i-=1
                    gl.log(".")
                    time.sleep(1)
                
                gl.log("ending e")
                mda.end(e)
                if tb: 
                    sm.createSession("deleteBox!")
                    try:
                        pem.deletePresentationElement(tb)
                        sm.closeSession()
                    except:
                        sm.cancelSession()
                        self.handleException()
                mda.doThePaint()
            except: self.handleException()
            
    def handleException(self):
        exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
        gl.log("*** EXCEPTION:")
        messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
        for message in messages:
            gl.log(message)
    
    def animateFromFile(self, filepath):
        #gl.log(filepath)
        self.loadEventsFromFile(filepath)
        mda = MagicDrawAnimatorUtils2.MagicDrawAnimator2()
        self.playEvents(mda)

    def loadEventsFromFile(self, filepath):
        pass
    
# This would need updating to match code above
#        self.events = []
#        f = open(filepath,"r")
#        lastTime = 0
#        for line in f.readlines():
#            #gl.log(line)
#            x = re.search("(\d*)[^0-9: \t\n\r\f\v]*\s*:\s*\S*\s*(\S*) -> (\S*)\s*(\S*) ==>",line)
#            y = re.search("\s*\S*\s*(\S*) -> (\S*)\s*(\S*) ==>",line)
#            if x: 
#                eventTime=x.groups()[0]
#                lastTime = eventTime
#                action=x.groups()[2]
#                cid = x.groups()[3]
#                ctype = x.groups()[1]
#            elif y:
#                eventTime=x.groups()[0]
#                eventTime=lastTime
#                action=y.groups()[1]
#                cid = y.groups()[2]
#                ctype = y.groups()[0]
#            else: continue
#            gl.log("%s %s (%s)" % (action.upper(), cid, ctype))
#            if any([x in cid for x in ["Main","TimeVaryingMap","ObjectFlow"]]): 
#                gl.log("    ---> Skipping - can't animate Main or TimeVaryingMap or ObjectFlow")
#                continue
#            if re.search("(_)?Activity(_.*)?(?!\S)",ctype): 
#                gl.log("    ---> Skipping - can't animate the Activity object!")
#                continue
#            evt = event(eventTime,action,cid,ctype)
#            self.events += evt
            
    def playEvents(self,mda):
        pass
# This would need updating to match code above
#        zeroTime = time.time()
#        elapsedTime = 0
#        for evt in self.events:
#            timeToNextEvent = (evt.eventTime + 0.0) / self.timeScale
#            if timeToNextEvent > elapsedTime:
#                time.sleep( timeToNextEvent - elapsedTime )
#            if "start" in evt.action: 
#                gl.log("    ---> STARTING")
#                mda.start(evt.componentId)
#            elif "end" in evt.action: 
#                gl.log("    ---> ENDING")
#                mda.end(evt.componentId)
#            elapsedTime = time.time() - zeroTime
            

        
