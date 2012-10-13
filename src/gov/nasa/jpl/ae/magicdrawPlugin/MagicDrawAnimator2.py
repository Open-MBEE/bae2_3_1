'''
Created on Sep 18, 2012

'''
#handy python things
import time,sys,traceback,re

#some MD utils. 
from com.nomagic.magicdraw.core import * #application, project...
from com.nomagic.magicdraw.core import Application #this seems to want its own import.

#threading, etc.
from java.lang import *

#this is where the actual animator class lives
import MagicDrawAnimatorUtils
reload (MagicDrawAnimatorUtils)

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
        self.timeScale = 8640.0 #animation time is sped up by this factor

    def run(self):
        gl.log("running MagicDrawAnimator")
        mode = 0
        if mode == 0:
            #filepath = "c:\\Users\\bclement\\Desktop\\foo.txt"
            filepath = "/Users/mjackson/Desktop/MedSim.txt"
            #self.animateFromFile(filepath)
            
            #self.loadEventsFromFile(filepath)
            
            self.events = []
            f = open(filepath,"r")
            for line in f.readlines():
                #gl.log(line)
                x = re.search("(\d*)[^0-9: \t\n\r\f\v]*\s*:\s*\S*\s*(\S*) -> (\S*)\s*(\S*) ==>",line)
                if x: 
                    eventTime=x.groups()[0]
                    action=x.groups()[2]
                    cid = x.groups()[3]
                    ctype = x.groups()[1]
                    gl.log("%s %s (%s)" % (action.upper(), cid, ctype))
                elif line.startswith("---"): 
                    gl.log(line)
                    continue
                else: continue
                #gl.log("%s %s (%s)" % (action.upper(), cid, ctype))
                if any([x in cid for x in ["Main","TimeVaryingMap","ObjectFlow"]]): 
                    gl.log("    ---> Skipping - can't animate Main or TimeVaryingMap or ObjectFlow")
                    continue
                if re.search("(_)?Activity(_.*)?(?!\S)",ctype): 
                    gl.log("    ---> Skipping - can't animate the Activity object!")
                    continue
                try:
                    evt = event(eventTime,action,cid,ctype)
                    try: self.events.append(evt)
                    except: gl.log("NESTED ARGHHHHH")
                except: gl.log("ARRGHHH")
            
            mda = MagicDrawAnimatorUtils.MagicDrawAnimator()
            
            #self.playEvents(mda)
            zeroTime = time.time()
            elapsedTime = 0
            gl.log(str(self.timeScale))
            for evt in self.events:
                gl.log("EVENT at " + str(evt.eventTime))
                try: timeToNextEvent = (float(evt.eventTime) + 0.0) / self.timeScale
                except: 
                    exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
                    gl.log("*** EXCEPTION:")
                    messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
                    for message in messages:
                        gl.log(message)
                gl.log("1")
                if timeToNextEvent > elapsedTime:
                    time.sleep( timeToNextEvent - elapsedTime )
                if "start" in evt.action: 
                    gl.log("    ---> STARTING")
                    mda.start(evt.componentId)
                elif "end" in evt.action: 
                    gl.log("    ---> ENDING")
                    mda.end(evt.componentId)
                elapsedTime = time.time() - zeroTime
            gl.log("DONE")
        
        elif mode == 1:
            e = "_17_0_5_edc0357_1346893970422_843838_14398"
            mda = MagicDrawAnimatorUtils.MagicDrawAnimator()
            gl.log("Starting e (%s)" % e)
            mda.start(e)
        
            i = 3
            while i > 0:
                i-=1
                gl.log(".")
                time.sleep(1)
            
            gl.log("ending e")
            mda.end(e)

    def animateFromFile(self, filepath):
        #gl.log(filepath)
        self.loadEventsFromFile(filepath)
        mda = MagicDrawAnimatorUtils.MagicDrawAnimator()
        self.playEvents(mda)

    def loadEventsFromFile(self, filepath):
        self.events = []
        f = open(filepath,"r")
        for line in f.readlines():
            #gl.log(line)
            x = re.search("(\d*)[^0-9: \t\n\r\f\v]*\s*:\s*\S*\s*(\S*) -> (\S*)\s*(\S*) ==>",line)
            if x: 
                eventTime=x.groups()[0]
                action=x.groups()[2]
                cid = x.groups()[3]
                ctype = x.groups()[1]
            else: continue
            gl.log("%s %s (%s)" % (action.upper(), cid, ctype))
            if any([x in cid for x in ["Main","TimeVaryingMap","ObjectFlow"]]): 
                gl.log("    ---> Skipping - can't animate Main or TimeVaryingMap or ObjectFlow")
                continue
            if re.search("(_)?Activity(_.*)?(?!\S)",ctype): 
                gl.log("    ---> Skipping - can't animate the Activity object!")
                continue
            evt = event(eventTime,action,cid,ctype)
            self.events += evt
            
    def playEvents(self,mda):
        zeroTime = time.time()
        elapsedTime = 0
        for evt in self.events:
            timeToNextEvent = (evt.eventTime + 0.0) / self.timeScale
            if timeToNextEvent > elapsedTime:
                time.sleep( timeToNextEvent - elapsedTime )
            if "start" in evt.action: 
                gl.log("    ---> STARTING")
                mda.start(evt.componentId)
            elif "end" in evt.action: 
                gl.log("    ---> ENDING")
                mda.end(evt.componentId)
            elapsedTime = time.time() - zeroTime
            

        
