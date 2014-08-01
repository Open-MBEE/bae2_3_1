#For LADWP CyberSecurity Project 1/10/13.
#Author: Maddalena Jackson

from com.nomagic.magicdraw.core import Application
from com.nomagic.magicdraw.core import Project
from com.nomagic.magicdraw.core.project import *
from com.nomagic.magicdraw.ui import MainFrame
from com.nomagic.magicdraw.ui.browser import *
from com.nomagic.magicdraw.openapi.uml import *
from com.nomagic.magicdraw.ui.dialogs import MDDialogParentProvider
from com.nomagic.uml2.ext.magicdraw.auxiliaryconstructs.mdinformationflows import *
from com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures import *
from com.nomagic.uml2.ext.magicdraw.compositestructures.mdports import *
from com.nomagic.uml2.ext.magicdraw.mdprofiles import *
from com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities import *
from com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines import *
from com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors import *
from com.nomagic.uml2.ext.magicdraw.actions.mdcompleteactions import *
from com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions import * #Action...
from com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities import * #ControlNode 
from com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities import * #JoinNode
from com.nomagic.uml2.ext.magicdraw.actions.mdintermediateactions import * #ReadSelf, etcx.
from com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications  import * #signals
from com.nomagic.uml2.ext.magicdraw.classes.mdkernel import *
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper
from com.nomagic.uml2.ext.jmi.helpers import *
from com.nomagic.magicdraw.ui.dialogs.specifications import SpecificationDialogManager
from com.nomagic.magicdraw.openapi.uml import ModelElementsManager
from com.nomagic.magicdraw.uml import ClassTypes
from com.nomagic.magicdraw.uml import ElementFinder
from com.nomagic.magicdraw.actions import *
from com.nomagic.magicdraw.actions import MDActionsCategory

import os, csv, sys, traceback, re, time, shutil

from com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions import OpaqueAction
from com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions import Pin
from javax.swing import JOptionPane

import ExporterUtils as EU
reload(EU)
import ExporterClasses as EC
reload(EC)
import ExporterWriter as EW
reload(EW)

generatedXmlFileName = ''

def setup():
	#initialize the guiLog
	gl = Application.getInstance().getGUILog()
	#gl.clearLog()
	gl.log("===============================================================================")
	debug = True
	logDebug = False
	
	EU.setDebug(debug)
	EU.setLogDebug(logDebug)
	
	global debug
	global logDebug
	global gl
	
	#get a copy of the project - useful for getting the elements factory, various other things like that.
	project = Application.getInstance().getProjectsManager().getActiveProject()
	global project
	TVM = StereotypesHelper.getStereotype(project,"TimeVaryingMap")
	global TVM

	#declare any stereotype elements here, for convenience.	
	return True


def run(s):
	starttime = time.time()
	if not setup(): return
	gl.log("Last Exporter Modification Date: March 4, 2013")
	#get the user's selection - the element that should be top level and contain (recursively) all other systems/behaviors you wish to reason about.
	firstSelected = Application.getInstance().getMainFrame().getBrowser().getActiveTree().getSelectedNodes()[0].getUserObject()
	gl.log(str(time.time()) + "You have selected " + firstSelected.name + " as the highest level element in your system.")
	global firstSelected
	
	#make sure the user picked a class... 
	if not isinstance(firstSelected,Class):
		gl.log("***ERROR: you must select some kind of class as your top level element.")
		return
	
	systemBehavior = firstSelected.classifierBehavior
	try: gl.log(systemBehavior.name)
	except: 
		gl.log("***ERROR: your top level system doesn't have a classifier behavior!")
		return

	inspected = []
	classifiersToInspect = [firstSelected]
	toInspect = [firstSelected]
	classesToTranslate = []
	constructorArgs = {}
	global data
	global inspected
	global toInspect
	global classifiersToInspect
	global constructorArgs
	
	#collect and inspect everybody based on top classifier!
	count = 0
	while len(classifiersToInspect)>0:
		if count > 50:
			gl.log("***LOOP LIMIT REACHED!")
			return
		thing = classifiersToInspect.pop(0)
		classesToTranslate.append(inspect(thing))
		count += 1
	inspecttime = time.time()
	gl.log("finished inspecting - time: %s" % str(inspecttime-starttime))
	
	#write everything out
	log_dir = getLogDirectory()
	os.chdir(log_dir)
	log_file_name = "Scenario_XML_" + str(EU.formatCurrentTime()) + ".xml"
	log_file = EW.makeLog(firstSelected,classesToTranslate,log_file_name)
	latestDir = getLatestDirectory()
	ffile = "Demo_Scenario_Latest.xml"
	gl.log("time to write scenario: %s" % str(time.time()-inspecttime))
	generatedXmlFileName = getFileName(latestDir,ffile)
	if generatedXmlFileName:
		shutil.copyfile(log_file_name, generatedXmlFileName)
		gl.log("copied file from " + os.getcwd() + " copied " + log_file_name + " to " + generatedXmlFileName )
	
	#gl.log("\nERRORS:")
	#for thing in classesToTranslate:
	#	if len(thing.errors.keys())>0:
	#		gl.log("errors in %s" % thing.name)
	#		gl.log("	%s" % str(thing.errors))
			
	gl.log("\nThe log file for this execution is located at: " + str(os.getcwd()) )
	gl.log("The log file: %s" % str(generatedXmlFileName) )
	gl.log("time to do everything: %s" % str(time.time()-starttime))
	return generatedXmlFileName

def getFileName(latestDir,ff = ""):
	ff = JOptionPane.showInputDialog(
                                    None,
                                    "Output Filename",
                                    "What are we naming this scenario?",
                                    JOptionPane.PLAIN_MESSAGE,
                                    None,
                                    None,
                                    ff)
	if ff and len(ff) > 0:
		if not ff.endswith(".xml"): ff+=".xml"
		return latestDir + os.sep + ff
	return None

def getLatestDirectory():
	latestDir = str(os.getcwd()) + os.sep + "latest"
	if not os.path.exists(latestDir):
		try: os.mkdir(latestDir)
		except: #this won't do much if you're running in batch mode...
			print "Error creating latest directory!"
	return latestDir

def getLogDirectory():
	homeDir = os.getenv('HOME')
	if homeDir == None:
		homeDir = os.getcwd()
	log_dir = homeDir
	if 'LADWP_LOGS' not in homeDir:
		log_dir = os.path.join(homeDir,'LADWP_LOGS')
	if not os.path.exists(log_dir):
		try: os.mkdir(log_dir)
		except: #this won't do much if you're running in batch mode...
		    print "Error creating log directory!"
	return log_dir

def getPrettyIdent(node):
		return node.name + " (" + str(node.getClassType()).split(".")[-1].strip("'>") + ")"		

def inspectClassifier(c):
	thing = EC.ClassifierClass(c,constructorArgs,firstSelected)
	classifiersToInspect.extend(thing.toInspect)
	constructorArgs.update(thing.constructorArgs)
	return thing

#TODO - make a "to inspect" category for classes on this level?
def inspectTactic(act,data): 
	gl.log(str(time.time()) + "\n*****hi, i'm the " + act.name + " activity!")
	#opaque behaviors are just chunks of math or functions that understand the input parameters.
	if act.humanType == "Opaque Behavior": 
		gl.log("I'm an opaque behavior! Look at my math or whatever!")
		return data #get rid of references to data...
	return EC.activityEventClass(act,constructorArgs,firstSelected)
	
#this finds the regions in a state machine (there's apparently at least one in every state machine). 
#creates a structure of events (trigger events) and regions, adds an entry for depth into the state machine.	
def inspectStateMachine(sm,data):
	gl.log(str(time.time()) + "hi, i'm a state machine!")
	thisStateMachine = {}
	for x in [sm.event,sm.region]:
		for thing in x:
			if str(thing.getHumanType()) in thisStateMachine.keys(): thisStateMachine[str(thing.getHumanType())].append(thing)
			else: thisStateMachine[str(thing.getHumanType())]=[thing]
	if "Region" in thisStateMachine.keys():
		(thisStateMachine["RegionDictionaries"],data)=inspectRegions(sm,data)
	if sm not in data["statemachines"].keys():
		data["statemachines"][sm]=thisStateMachine
	return None

#recursively used to create a data object of nested/orthogonal/whatever regions...
def inspectRegions(stateThing,data):
	gl.log(str(time.time()) + "hi, I'm a region in " + stateThing.name + "!")
	stateDict = {}
	for reg in stateThing.region:
		stateDict[reg]={}
		for thing in reg.ownedMember:
			if str(thing.getHumanType()) in stateDict[reg].keys(): stateDict[reg][str(thing.getHumanType())].append(thing)
			else: stateDict[reg][str(thing.getHumanType())]=[thing]
		for k,v in stateDict[reg].items():
			gl.log(str(k) + " " + str([str(vn.name) for vn in v]))
		for t in stateDict[reg]["Transition"]:
			#look at the guards here
			#look at the triggers here
			if t.getEffect(): toInspect.append(t.getEffect())
				#data = inspectTactic(t.getEffect(),data)
		for s in stateDict[reg]["State"]:
			gl.log("looking for orthogonal / entry / exit in " + s.name)
			if s.getEntry(): toInspect.append(s.getEntry())
				#data = inspectTactic(s.getEntry(),data)
			if s.getExit(): toInspect.append(s.getExit())
				#data = inspectTactic(s.getExit(),data)
			if s.isOrthogonal():
				(stateDict[reg]["OrthogonalRegions"],data)=inspectRegions(s,data)	
	return stateDict, data

def inspect(item):
	thingy = None
	if isinstance(item, Behavior):
		if isinstance(item,Activity): thingy = inspectTactic(item,data)
		elif isinstance(item,StateMachine): thingy = inspectStateMachine(item,data)
	elif isinstance(item,Class): 
		thingy = inspectClassifier(item)
	else: gl.log("item isn't behavior or class... " + str(item))
	return thingy
