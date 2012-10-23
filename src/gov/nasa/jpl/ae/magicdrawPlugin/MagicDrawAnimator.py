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
    x.start()

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
        self.timeStep = 1.0
        
    def run(self):
        mode = 0
        if mode == 0:
            mda = MagicDrawAnimatorUtils2.MagicDrawAnimator2()
            filepath = "c:\\Users\\bclement\\Desktop\\foo12.txt"
            gl.log("reading events from " + filepath)
            f = open(filepath,"r")
            lastTime = 0
            for line in f.readlines():
                #gl.log("read line = " + line)
                #x = re.search(" (\S*) -> (\S*)\s*(\S*) ==>",line)
                x = re.search("(\d*)[^0-9: \t\n\r\f\v]*\s*:\s*\S*\s*(\S*) -> (\S*)\s*(\S*) ==>",line)
                y = re.search("(\S*) -> (\S*)\s*(\S*) ==>",line)
                if x:
                    eventTime=x.groups()[0]
                    action=x.groups()[2]
                    cid = x.groups()[3]
                    ctype = x.groups()[1]
                elif y:
                    eventTime=lastTime
                    action=y.groups()[1]
                    cid = y.groups()[2]
                    ctype = y.groups()[0]
                elif line.startswith("---"): 
                    gl.log(line)
                    continue
                else: continue
                gl.log("%s %s (%s)" % (action.upper(), cid, ctype))
                if any([x in cid for x in ["Main","TimeVaryingMap","ObjectFlow"]]): 
                    gl.log("    ---> Skipping - can't animate Main or TimeVaryingMap or ObjectFlow")
                    continue
                if re.search("(_)?Activity(_.*)?(?!\S)",ctype): 
                    gl.log("    ---> Skipping - can't animate the Activity object!")
                    continue
                if "start" in action: 
                    gl.log("    ---> STARTING")
                    mda.start(cid)
                elif "end" in action: 
                    gl.log("    ---> ENDING")
                    mda.end(cid)
                if eventTime != lastTime:
                    mda.doThePaint()
                time.sleep(self.timeStep)
        
        elif mode == 1:
            e = "_17_0_5_edc0357_1346893970422_843838_14398"
            mda = MagicDrawAnimatorUtils2.MagicDrawAnimator2()
            gl.log("Starting e (%s)" % e)
            mda.start(e)
        
            i = 3
            while i > 0:
                i-=1
                gl.log(".")
                time.sleep(self.timeStep)
            
            gl.log("ending e")
            mda.end(e)
            mda.doThePaint()
