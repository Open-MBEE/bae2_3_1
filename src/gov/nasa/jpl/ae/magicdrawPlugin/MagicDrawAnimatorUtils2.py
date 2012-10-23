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


class MagicDrawAnimator2(object):
    '''
    Changes visualization of SysML components
    '''
    def __init__(self):
        gl.log("Initializing the Magic Draw Animator!")
        #project is used to find element by ID
        #SEM is key to getting symbols (usages on diagram) from elements
        self.project = Application.getInstance().getProjectsManager().getActiveProject()
        self.SEM = self.project.getSymbolElementMap()

        self.knownComponents = {} #{componentID,symbol}
        self.defaults = {} #{sym: (fill color, line color)}
        self.activeDiagrams = [] #probably don't need the active diagram tracker either but maybe later.
        self.paintEvents={} #keeps track of the PaintEvent thing for each symbol
        self.currentPaintEvents = [] #the collection of events to be painted together
        self.currentDiagram = None #to avoid potential overhead of opening an already open diagram.
    
    def start(self, componentId):
        '''
        1. Find symbol we're going to highlight
        2. If we haven't already highlighted this before, find it's default coloring.
        3. find the diagram and open it
        4. create a highlight listener ("Awesome Paint Action") for the symbol
        5. toggle it on (it's set to off initially)
        '''
        sym = self.findSymbolToHighlight(componentId)
        #gl.log("Debug: symbol: " + str(sym))
        #gl.log("Debug: found symbol...")
        if componentId not in self.defaults.keys(): self.setDefaults(componentId)
        #gl.log("Debug: symbol: " + str(sym))
        if sym != None:
            diagram = sym.getDiagramPresentationElement()
            #gl.log("Debug: found diagram")
            diagram.open()
            #gl.log("Debug: opened diagram")
            self.activeDiagrams.append(diagram)
            p = PaintEvent(sym,diagram,self.defaults[sym])
            self.paintEvents[sym]=p
            self.currentPaintEvents.append(p)
    
    def end(self, componentId):
        '''
        1. Find the symbol (we had better have seen it before...)
        2. Get the AwesomePaintAction for this symbol (we'd better have already made it...)
        3. Highlight it!
        '''
        sym = self.findSymbolToHighlight(componentId)
        if sym and sym in self.paintEvents:
            p = self.paintEvents[sym]
            if p:
                self.currentPaintEvents.append(p)
    
    def doThePaint(self):
        t = AwesomePaintAction2(self.currentPaintEvents)
        t.actionPerformed(None)
        self.currentPaintEvents = []
        
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
            #gl.log("Debug: symbols found for id=" + componentId + ": " + str(symbols))
            if symbols:
                if len(symbols)>1:
                    for s in symbols:
                        if s:
                            gl.log("MULTIPLE SYMBOLS!! " + str(s) + " in diagram " + str(s.getDiagramPresentationElement()))
                        else:
                            gl.log("MULTIPLE SYMBOLS!! " + str(s) )
                if len(symbols)>0:
                    self.knownComponents[componentId]=symbols[0]
                    #gl.log("Debug: symbols found for id... "  + str(symbols))
                    return symbols[0]
            gl.log("No symbol found for componentId=" + str(componentId))
            return None
             
    def setDefaults(self,cid):
        '''
        1. Get the symbol
        2. If we haven't already done this, set the default line and pen color
        '''
        #gl.log("setDefaults()" )
        #print "checking for component id: " + str(cid)
        #gl.log("checking for component id: " + str(cid) )
        #gl.log("in knownComponents: " + str(self.knownComponents) )
        isKnown = False
        if self.knownComponents != None:
            isKnown = (cid in self.knownComponents)
        if isKnown:
            sym = self.knownComponents[cid]
        else:
            sym = None
            #gl.log("component id (" + str(cid) + ") is not in knownComponents: " + str(self.knownComponents) )
        if sym != None and sym not in self.defaults.keys():
            pm = sym.getPropertyManager()
            if pm:
                try: fcolor = pm.getPropertyByName("Fill Color").getColor()
                except:
                    gl.log("Eek, this is probably a node")
                    fcolor = None
                lcolor = pm.getPropertyByName("Pen Color").getColor()
                self.defaults[sym]=(fcolor,lcolor)
                #gl.log("Debug: defaults: " + str(fcolor) + " & " + str(lcolor))

class PaintEvent():
    def __init__(self,element,diagram,defaults):
        self.element = element 
        self.defaultFillColor = defaults[0]
        self.defaultLineColor = defaults[1]
        self.on = False #Initialize to off. Only start calls init so should be OK.
        self.diagram = diagram
    
class AwesomePaintAction2(NMAction):
    '''
    Based on a MagicDraw Action, this event handler thing toggles activities on & off
    Note: it actually changes the symbol properties rather than drawing a box around it like the Sim Toolkit does...
    '''
    def __init__(self,paintEvents):
        NMAction.__init__( self,"", "paintAction", None, None ) #super? I used this because it seems recommended by MD
        self.paintEvents = paintEvents
    
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
            for paintEvent in self.paintEvents:
                newPM = PropertyManager()
                fc = paintEvent.defaultFillColor
                lc = paintEvent.defaultLineColor
                if not paintEvent.on: 
                    lc = Color.GREEN
                    fc = Color.GREEN
                    if paintEvent.defaultFillColor: lc = Color.BLACK
                if paintEvent.defaultFillColor: newPM.addProperty(ColorProperty(PropertyID.FILL_COLOR,fc))
                newPM.addProperty(ColorProperty(PropertyID.PEN_COLOR,lc))
                PresentationElementsManager.getInstance().setPresentationElementProperties(paintEvent.element, newPM)
                paintEvent.on = not paintEvent.on
            SessionManager.getInstance().closeSession()
            #gl.log("Debug: actionPerformed")
        except:
            SessionManager.getInstance().cancelSession()
            exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
            messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
            for message in messages:
                gl.log(message)
            gl.log("***ERROR PAINTING!!")
            return
        #self.diagram.getDiagramSurface().repaint()
