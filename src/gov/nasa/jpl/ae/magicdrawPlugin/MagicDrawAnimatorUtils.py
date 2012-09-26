'''
Created on Sep 18, 2012

'''

import time,sys,traceback

from com.nomagic.magicdraw.core import * #application, project...
from com.nomagic.magicdraw.core import Application
from com.nomagic.magicdraw.uml.symbols.shapes import *
from com.nomagic.magicdraw.ui import DiagramSurfacePainter
from com.nomagic.magicdraw.openapi.uml import * #?
from com.nomagic.magicdraw.properties import *
from com.nomagic.actions import NMAction

from java.awt import *
from java.awt import Dimension
from java.lang import *
from javax.swing import *
from java.awt.event import *

global gl
gl = Application.getInstance().getGUILog()
global PEM
PEM = PresentationElementsManager.getInstance()


class MagicDrawAnimator(object):
    '''
    Changes visualization of SysML components
    '''
    def __init__(self):
        #project is used to find element by ID
        #SEM is key to getting symbols (usages on diagram) from elements
        self.project = Application.getInstance().getProjectsManager().getActiveProject()
        self.SEM = self.project.getSymbolElementMap()

        self.knownComponents = {} #{componentID,symbol}
        self.defaults = {} #{sym: (fill color, line color)}
        self.activeDiagrams = [] #probably don't need the active diagram tracker either but maybe later.
        self.paintEvents={} #keeps track of ActionListener thing for each symbol
    
    def start(self, componentId):
        '''
        1. Find symbol we're going to highlight
        2. If we haven't already highlighted this before, find it's default coloring.
        3. find the diagram and open it
        4. create a highlight listener ("Awesome Paint Action") for the symbol
        5. toggle it on (it's set to off initially)
        '''
        sym = self.findSymbolToHighlight(componentId)
        if componentId not in self.defaults.keys(): self.setDefaults(componentId)
        diagram = sym.getDiagramPresentationElement()
        diagram.open()
        self.activeDiagrams.append(diagram)
        t = AwesomePaintAction(sym,diagram,self.defaults[sym])
        self.paintEvents[sym]=t
        t.actionPerformed(None)
    
    def end(self, componentId):
        '''
        1. Find the symbol (we had better have seen it before...)
        2. Get the AwesomePaintAction for this symbol (we'd better have already made it...)
        3. Highlight it!
        '''
        sym = self.findSymbolToHighlight(componentId)
        p = self.paintEvents[sym]
        p.actionPerformed(None)

        
    def findSymbolToHighlight(self,componentId):
        '''
        1. If we already have an entry for this ID, return the symbol
        2. Otherwise, get the element by ID from the project
        3. Get the symbols from the symbol element map
        4. If there's more than one, we want to know about it! 
        5. Just pick the first symbol (always an array so...)
        6. NOTE: technically an activity can be valid without any symbols (i.e., no diagrams). We should check for this.
        '''
        if componentId in self.knownComponents.keys(): return self.knownComponents[componentId]
        else:
            element = self.project.getElementByID(componentId)
            symbols = self.SEM.getAllPresentationElements(element)
            if len(symbols)>1:
                for s in symbols: gl.log("MULTIPLE SYMBOLS!! " + str(s) + " in diagram " + str(s.getDiagramPresentationElement()))
            self.knownComponents[componentId]=symbols[0]
            return symbols[0]
             
    def setDefaults(self,cid):
        '''
        1. Get the symbol
        2. If we haven't already done this, set the default line and pen color
        '''
        sym = self.knownComponents[cid]
        if sym not in self.defaults.keys():
            pm = sym.getPropertyManager()
            fcolor = pm.getPropertyByName("Fill Color").getColor()
            lcolor = pm.getPropertyByName("Pen Color").getColor()
            self.defaults[sym]=(fcolor,lcolor)
            #gl.log("Debug: defaults: " + str(fcolor) + " & " + str(lcolor))


class AwesomePaintAction(NMAction):
    '''
    Based on a MagicDraw Action, this event handler thing toggles activities on & off
    Note: it actually changes the symbol properties rather than drawing a box around it like the Sim Toolkit does...
    '''
    def __init__(self,element,diagram,defaults):
        NMAction.__init__( self,"", "paintAction", None, None ) #super? I used this because it seems recommended by MD
        self.element = element 
        self.defaultFillColor = defaults[0]
        self.defaultLineColor = defaults[1]
        self.on = False #Initialize to off. Only start calls init so should be OK.
        self.diagram = diagram
    
    def actionPerformed(self,event):
        '''
        1. Wrap this in MD Session - (so we can undo it, among other useful things :D)
        2. create a property manager instance
        3. figure out which colors to use based on whether we're turning it on or off
        4. Use the Presentation Elements Manager to set the properties (MD API says this overwrites just those properties)
        5. Toggle state
        6. Close session --> Push out changes (as long as this was called from a thread other than event dispatcher thread)
        7. Cancel and print errors if there are any exceptions
        8. Repaint diagram surface. Not sure if that's necessary but ... whatever
        '''
        try:
            SessionManager.getInstance().createSession("Paint!")
            newPM = PropertyManager()
            fc = self.defaultFillColor
            lc = self.defaultLineColor
            if not self.on: 
                fc = Color.GREEN
                lc = Color.BLACK
            newPM.addProperty(ColorProperty(PropertyID.FILL_COLOR,fc))
            newPM.addProperty(ColorProperty(PropertyID.PEN_COLOR,lc))
            PresentationElementsManager.getInstance().setPresentationElementProperties(self.element, newPM)
            self.on = not self.on
            SessionManager.getInstance().closeSession()
        except:
            SessionManager.getInstance().cancelSession()
            exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
            messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
            for message in messages:
                gl.log(message)
            gl.log("***ERROR PAINTING!!")
            return
        self.diagram.getDiagramSurface().repaint()
