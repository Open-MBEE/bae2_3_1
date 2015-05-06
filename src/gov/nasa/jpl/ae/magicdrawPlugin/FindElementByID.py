'''
Created on Sep 18, 2012

'''
#handy python things
import time,sys,traceback,re

#some MD utils. 
from com.nomagic.magicdraw.core import * #application, project...
from com.nomagic.magicdraw.core import Application #this seems to want its own import.

from javax.swing import JOptionPane

import MPUtils
reload (MPUtils)

global gl
gl = Application.getInstance().getGUILog()
        

def run(x):
    gl.clearLog()
    project = Application.getInstance().getProjectsManager().getActiveProject()
    SEM = project.getSymbolElementMap()
    
    possibilities = None
    id = JOptionPane.showInputDialog(
                                    None,
                                    "Element ID",
                                    "Find Element",
                                    JOptionPane.PLAIN_MESSAGE,
                                    None,
                                    possibilities,
                                    None)

    if id and len(id)>0:
        id = id.strip()
        id = id.lstrip("sig")
        gl.log("looking for %s" % id)
        try: element = project.getElementByID(id)
        except:
            MPUtils.handleException("Can't find an element matching this ID.")
            return
        if not element: 
            gl.log("Can't find element matching this ID.")
            return
        gl.log("Found Element with ID = " + str(id) + ": ")
        try: gl.log("    Name: %s" % element.name)
        except: 
            try: gl.log("    HumanName: %s" % element.humanName)
            except: pass
        gl.log("    Type: %s" % element.humanType)
        try: symbols = SEM.getAllPresentationElements(element)
        except:
            MPUtils.handleException("Can't find symbols matching this ID.")
            return
        if symbols:
                if len(symbols)>1:
                    for s in symbols:
                        if s: gl.log("MULTIPLE SYMBOLS!! " + str(s) + " in diagram " + str(s.getDiagramPresentationElement()))
                        else: gl.log("MULTIPLE SYMBOLS!! " + str(s) )
                    gl.log("choosing first symbol")
                if len(symbols)>0:
                    sym = symbols[0]
                    if sym:
                        diagram = sym.getDiagramPresentationElement()
                        diagram.open()
                        sym.setSelected(True)  
                    else: gl.log("wtf?") 
        else: gl.log("No symbols found for componentId=" + str(id))
    else: gl.log("barrrf")