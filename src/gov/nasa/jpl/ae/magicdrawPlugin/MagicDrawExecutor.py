'''
Created on Sep 28, 2012

@author: bclement
'''

from gov.nasa.jpl.ae.event import Executor
from MagicDrawAnimatorUtils import MagicDrawAnimator
import re
import time

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
        pass
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
        id = self.getComponentIdFromList([shortClassName,objectName,longClassName])
        print time.asctime() + " <--> " + eventTime + " : id=" + str(id) + " - " + objectName + " --> " + value 
        if id != None:
            if value == 'start':
                self.start(id)
            elif value == 'end':
                self.end(id)
            