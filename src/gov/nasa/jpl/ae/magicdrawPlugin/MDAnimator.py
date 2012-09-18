'''
Created on Sep 18, 2012

'''

class MagicDrawAnimator(object):
    '''
    Changes visualization of SysML components
    '''
    components = {}

    def __init__(self, params):
        '''
        Constructor
        '''
    
    def start(self, componentId ):
        self.changeFillColor( self, componentId, "green" );
        self.changeLineColor( self, componentId, "green" );
    
    def end(self, componentId ):
        self.changeFillColor( self, componentId, "white" );
        self.changeLineColor( self, componentId, "black" );

    def changeValue( self, componentId ):
        comp = self.components[componentId]