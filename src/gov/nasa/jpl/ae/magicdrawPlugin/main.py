from com.nomagic.magicdraw.actions import MDActionsCategory
from com.nomagic.magicdraw.actions import DiagramContextAMConfigurator
from com.nomagic.magicdraw.ui.actions import DefaultDiagramAction
from com.nomagic.magicdraw.uml.symbols import DiagramPresentationElement
from com.nomagic.magicdraw.uml.symbols import PresentationElement
from com.nomagic.magicdraw.actions import *
from javax.swing import JOptionPane
from com.nomagic.actions import *
from com.nomagic.magicdraw.core import Application

from com.nomagic.magicdraw.uml import *
from java.lang import String
import os
import sys
import traceback

class ExampleAction( MDAction ):
	def __init__(self, name, s):
		gl = Application.getInstance().getGUILog()
		self.s = s
		MDAction.__init__( self,"", name, None, None )
		#gl.log(str(s))

	def actionPerformed(self, event):
		gl = Application.getInstance().getGUILog()
		try:
			#gl.log("blah")
			mod = __import__(self.getName().replace(" ", "_"))
			reload(mod)
			mod.run(self.s)
		except:
			exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
			gl.log("*** EXCEPTION:")
			messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
			for message in messages:
				gl.log(message)
		
	
# Configurator adds new menu item to First menu in main menu
class MainMenuConfigurator( AMConfigurator):
	def configure( self,manager ):
		active = True
		try:
			if active:
				category=MDActionsCategory("LADWP","LADWP")
				category.setNested(True)
				category.addAction(ExampleAction("MagicDrawAnimator",None))
				manager.addCategory(category)
			else: pass
		except:
			gl.log("exception")
			exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
			gl.log("*** EXCEPTION:")
			messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
			for message in messages:
				gl.log(message)
	def getPriority(self):
		return AMConfigurator.LOW_PRIORITY

class DiagramConfiguratorIBD(DiagramContextAMConfigurator):
    def configure(self,manager,diagram,selected,requestor):
    	active = False
    	try:
			if active:
				category=MDActionsCategory("LADWP_Utils","LADWP_Utils")
				category.setNested(True)
				#category.addAction(ExampleAction("SpacePorts",selected))
				#category.addAction(ExampleAction("Align",selected))
				#category.addAction(ExampleAction("MakeAssociationClassPattern",selected))
				manager.addCategory(category)
				#gl.log(str(diagram) + " " + str(selected) + " " + str(requestor))
			else: pass
        except:
			gl.log("exception")
			exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
			gl.log("*** EXCEPTION:")
			messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
			for message in messages:
				gl.log(message)
    def getPriority(self):
        return AMConfigurator.LOW_PRIORITY
       
       	
class DiagramConfigurator(DiagramContextAMConfigurator):
    def configure(self,manager,diagram,selected,requestor):
        active = False
    	try:
			if active:
				category=MDActionsCategory("LADWP_Utils","LADWP_Utils")
				category.setNested(True)
				#category.addAction(ExampleAction("SpacePorts",selected))
				#category.addAction(ExampleAction("Align",selected))
				#category.addAction(ExampleAction("MakeTreeNonNavigable",selected))
				manager.addCategory(category)
				#gl.log(str(diagram) + " " + str(selected) + " " + str(requestor))
			else: pass
        except:
			gl.log("exception")
			exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
			gl.log("*** EXCEPTION:")
			messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
			for message in messages:
				gl.log(message)
    def getPriority(self):
        return AMConfigurator.LOW_PRIORITY

class ActDiagramConfigurator(DiagramContextAMConfigurator): 
    def configure(self,manager,diagram,selected,requestor):
        try:
        	pass
			#gl.log("Configuring ActDiagramConfigurator!")
			#category=MDActionsCategory("", "")
			#category.addAction(ExampleAction("setQueryTargets",selected))
			#Smanager.addCategory(category)
        except:
			print "exception"
			exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
			gl.log("*** EXCEPTION:")
			messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
			for message in messages:
				gl.log(message)
    def getPriority(self):
        return AMConfigurator.LOW_PRIORITY

class BrowserContextAMConfigurator(BrowserContextAMConfigurator):
    def configure (self,manager,tree):
        try:
			category=MDActionsCategory("LADWP_Utils","LADWP_Utils")
			category.setNested(True)
			#category.addAction(ExampleAction("MassRefactor",None))
			#category.addAction(ExampleAction("ExportForAnalysisEngine",None))
			category.addAction(ExampleAction("ExportForAnalysisEngine_v2",None))
			#category.addAction(ExampleAction("RegisterAcronyms",None))
			#category.addAction(ExampleAction("UpdateDocumentNumbering"))
			#category.addAction(ExampleAction("DocumentViewer"))
			#category.addAction(ExampleAction("CreateDefaultDocument"))
			manager.addCategory(category)
        except:
			exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
			gl.log("*** EXCEPTION:")
			messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
			for message in messages:
				gl.log(message)

    def getPriority(self):
        return AMConfigurator.LOW_PRIORITY
       
# Script starts here
gl = Application.getInstance().getGUILog()
#print "Starting script, descriptor", pluginDescriptor
#JOptionPane.showMessageDialog(None,"HIIII")
ActionsConfiguratorsManager.getInstance().addMainMenuConfigurator( MainMenuConfigurator() )
ActionsConfiguratorsManager.getInstance().addContainmentBrowserContextConfigurator(BrowserContextAMConfigurator())
#ActionsConfiguratorsManager.getInstance().addBaseDiagramContextConfigurator("Class Diagram", DiagramConfigurator())
#ActionsConfiguratorsManager.getInstance().addBaseDiagramContextConfigurator("Activity Diagram", ActDiagramConfigurator())
#ActionsConfiguratorsManager.getInstance().addBaseDiagramContextConfigurator("UML Class Diagram", DiagramConfigurator())
#ActionsConfiguratorsManager.getInstance().addBaseDiagramContextConfigurator("Activity Diagram", DiagramConfigurator())
ActionsConfiguratorsManager.getInstance().addBaseDiagramContextConfigurator("SysML Internal Block Diagram", DiagramConfiguratorIBD())
ActionsConfiguratorsManager.getInstance().addBaseDiagramContextConfigurator("SysML Block Definition Diagram", DiagramConfigurator())


