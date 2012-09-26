'''
Created on Sep 18, 2012

'''
#handy python things
import time,sys,traceback

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
        pass
    def run(self):
        e = "_17_0_5_edc0357_1345510114621_963079_14807"
        mda = MagicDrawAnimatorUtils.MagicDrawAnimator()
        gl.log("Starting e")
        mda.start(e)
    
        i = 3
        while i > 0:
            i-=1
            gl.log(".")
            time.sleep(1)
        
        gl.log("ending e")
        mda.end(e)
