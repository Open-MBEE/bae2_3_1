'''
Created on Sep 28, 2012

@author: bclement
'''

from gov.nasa.jpl.ae.event import Executor
import MagicDrawAnimatorUtils
reload(MagicDrawAnimatorUtils)
from MagicDrawAnimatorUtils import MagicDrawAnimator
import re
import time

from com.nomagic.magicdraw.core import Application

global gl
#if gl == None:
gl = Application.getInstance().getGUILog()


class MagicDrawExecutor(MagicDrawAnimator,Executor):
    '''
    classdocs
    '''
    #animator = None #MagicDrawAnimator
    #simulator = None
    idPattern = '_17_\\w*'

    def __init__(self):#, anim):#, sim):
        '''
        Constructor
        '''
        super(MagicDrawExecutor,self).__init__()
        #pass
        #self.animator = anim
        #simulator = sim
        
    def getComponentId(self, string):
        m = re.search(self.idPattern, string)
        if m == None:
            return None
        return m.group(0)

    def getComponentIdFromList(self, strings):
        for string in strings:
            id = self.getComponentId(string)
            if id != None and len(str(id)) > 0:
                return id
        return None
        
    def execute(self, eventTime, objectName, shortClassName, longClassName, value):
        cid = self.getComponentIdFromList([shortClassName,objectName,longClassName])
        gl.log( str(time.asctime()) + " <--> " + str(eventTime) + " : id=" + str(cid) + " - " + str(objectName) + " --> " + str(value) )
        if cid != None:
            if value == 'start':
                self.start(cid)
            elif value == 'end':
                self.end(cid)
            