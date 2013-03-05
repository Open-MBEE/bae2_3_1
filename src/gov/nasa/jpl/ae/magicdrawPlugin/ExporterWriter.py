#For LADWP CyberSecurity Project 1/10/13.
#Authors: Maddalena Jackson

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

import ExporterClasses as EC
reload(EC)

gl = Application.getInstance().getGUILog()
global gl
debug = False
logDebug = False
global debug
global logDebug

def formatCurrentTime():
    tsec=time.localtime(time.time()) #as opposed to gmtime
    then ="%s-%03iT%02i.%02i.%02i" % (tsec.tm_year, tsec.tm_yday, tsec.tm_hour,tsec.tm_min,tsec.tm_sec)
    return then	
   
def setDebug(status):
	debug = status
	
def setLogDebug(status):
	logDebug = status

def makeLog(firstSelected,classesToTranslate,log_file_name):
	lerrg = open(log_file_name,"w")
	global lerrg
	try: writeScenario(firstSelected,classesToTranslate)
	except: 
		exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
		gl.log("*** EXCEPTION:")
		messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
		for message in messages:
			gl.log(message)
		lerrg.close
	#data=inspectClassifier(firstSelected,None,data)
	lerrg.close()
	return lerrg

def logAndExport(tabNum,tag,text):
	line = "	"*tabNum
	if tag: line = line+"<%s>%s</%s>" % (tag,text,tag)
	else: line = line+text
	lerrg.write(line+"\n")
	if logDebug: gl.log(line)

def writeScenario(top,classesToTranslate):
	gl.log("writing scenario: %s" % str(classesToTranslate))
	logAndExport(0,None,"<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>")
	logAndExport(0,None,"<scenario xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"eventSchema.xsd\">")
	logAndExport(0,None,"<!--RUNNING ON: %s (owner: %s) at %s -->" % (top.name,top.owner.name,time.strftime("%B %d %Y %I:%M:%S %p", time.localtime())))
	logAndExport(1,"epoch","2012-08-05T23:30:00-07:00")
	logAndExport(1,"horizon","P1D")
	logAndExport(1,"timeUnits","seconds")
	writeTopEvent(top)
	writeScenarioRunner(top)
	logAndExport(1,None,"<classes>")
	for c in classesToTranslate: translate(c,2)
	logAndExport(1,None,"</classes>")
	logAndExport(0,None,"</scenario>")
	return

def writeScenarioRunner(e):
	cb = e.classifierBehavior
	logAndExport(1,None,"<events>")
	logAndExport(2,None,"<event>")
	logAndExport(3,None,"<class>")
	logAndExport(4,"name","BobCreator")
	logAndExport(4,None,"<members>")
	logAndExport(5,None,"<parameter>")
	logAndExport(6,"name","Bob")
	logAndExport(6,"type",e.name)
	logAndExport(6,"value","new " + e.name + "()")
	logAndExport(5,None,"</parameter>")
	logAndExport(4,None,"</members>")	
	logAndExport(3,None,"</class>")
	logAndExport(3,None,"<elaborations>")
	logAndExport(4,None,"<elaboration>")
	logAndExport(5,None,"<eventInvocation>")
	logAndExport(6,"enclosingInstance","Bob")
	logAndExport(6,"eventType",e.name + "." + cb.getID())
	logAndExport(6,"eventName","%s_%s_%s" % (cb.name,"Activity",cb.owner.name))
	logAndExport(6,None,"<arguments>")
	logAndExport(7,None,"<parameter>")
	logAndExport(8,"name","caller")
	logAndExport(8,"value","this")
	logAndExport(7,None,"</parameter>")
	logAndExport(7,None,"<parameter>")
	logAndExport(8,"name","startTime")
	logAndExport(8,"value","startTime")
	logAndExport(7,None,"</parameter>")
	logAndExport(6,None,"</arguments>")
	logAndExport(5,None,"</eventInvocation>")
	logAndExport(4,None,"</elaboration>")
	logAndExport(3,None,"</elaborations>")
	logAndExport(2,None,"</event>")
	logAndExport(1,None,"</events>")
#make this do stuff

def writeTopEvent(e):
	logAndExport(1,None,"<eventToBeExecuted>")
	logAndExport(2,"eventType","BobCreator")
	logAndExport(2,None,"<arguments>")
	logAndExport(3,None,"<parameter>")
	logAndExport(4,"name","startTime")
	logAndExport(4,"value","0")
	logAndExport(3,None,"</parameter>")
	logAndExport(2,None,"</arguments>")
	logAndExport(1,None,"</eventToBeExecuted>")
	return

def translate(classThingy,l):
	if isinstance(classThingy,EC.activityEventClass): translateActivity(classThingy,l)
	elif isinstance(classThingy,EC.ClassifierClass): translateClass(classThingy,l)

def writeConstructor(classThingy,l):
    if len(classThingy.constructors)>0:
        args = classThingy.cArgs["t"] + " t, " if "t" in classThingy.cArgs.keys() else ""
        for arg in [x for x in sorted(classThingy.cArgs.keys()) if x != "t"]: args += str("%s %s, " % (classThingy.cArgs[arg], arg))
        try: args = args.rstrip(", ")
        except: pass
    	logAndExport(l,None,"<constructors>")
    	logAndExport(l+1,None,"<function>")
    	logAndExport(l+1,None,"<![CDATA[")
    	logAndExport(l+1,None," %s(%s) {" % (classThingy.id,args))
    	for c in classThingy.constructors: logAndExport(l+2,None,"%s" % c)
    	logAndExport(l+1,None," }")
    	logAndExport(l+2,None,"]]>")
    	logAndExport(l+1,None,"</function>")
    	logAndExport(l,None,"</constructors>")
    else: logAndExport(l,None,"<constructors/>")
	
	
def translateClass(classThingy,l):
    logAndExport(l,None,"<class>")
    logAndExport(l+1,"name",classThingy.id)
    logAndExport(l+1,"import","java.util.Queue")
    logAndExport(l+1,"import","java.util.LinkedList")
    logAndExport(l+1,"import","gov.nasa.jpl.ae.event.Timepoint")
    logAndExport(l+1,"import","gov.nasa.jpl.ae.fuml.ObjectFlow")
    logAndExport(l+1,"import","gov.nasa.jpl.ae.event.TimeVaryingList")
    logAndExport(l+1,"import","gov.nasa.jpl.ae.event.TimeVaryingProjection")
    logAndExport(l+1,"import","java.lang.Math")
    writeMembers(classThingy,l+1)
    writeConstructor(classThingy,l+1)
    
    if len(classThingy.methods)>0:
    	logAndExport(l+1,None,"<methods>")
    	for m in classThingy.methods:
    		logAndExport(l+2,"function",m)	
    	logAndExport(l+1,None,"</methods>")
    else: logAndExport(l+1,None,"<methods/>")
    
    if len(classThingy.events)>0:
    	logAndExport(l+1,None,"<events>")
    	for activity in classThingy.events: translateActivity(activity,l+2)
    	logAndExport(l+1,None,"</events>")
    else: logAndExport(l+1,None,"<events/>")
    
    if len(classThingy.MDsignalClasses)>0:
    	logAndExport(l+1,None,"<classes>")
    	for c in classThingy.MDsignalClasses:
    		translateClass(c,l+2)
    	logAndExport(l+1,None,"</classes>")
    else: logAndExport(l+1,None,"<classes/>")
    logAndExport(l,None,"</class>")

def writeMembers(memberOwner,l):
	if len(memberOwner.members.keys())>0:
		logAndExport(l,None,"<members>")
		for name,(type,val,comment) in memberOwner.members.items():
			logAndExport(l+1,None,"<parameter>")
			logAndExport(l+2,None,"<!--%s-->" % comment)
			logAndExport(l+2,"name",name)
			logAndExport(l+2,"type",type)
			logAndExport(l+2,None,makeValueString(val,"value"))
			logAndExport(l+1,None,"</parameter>")
		logAndExport(l,None,"</members>")
	else: logAndExport(l,None,"<members/>")
	
def writeDependencies(dependencyOwner,l):
	if len(dependencyOwner.dependencies.keys())>0:
		logAndExport(l,None,"<dependencies>")
		for parameter, (type,expression) in dependencyOwner.dependencies.items():
			logAndExport(l+1,None,"<dependency>")
			logAndExport(l+2,"name",parameter)
			#logAndExport(l+2,"type",type)
			logAndExport(l+2,"value",expression)
			logAndExport(l+1,None,"</dependency>")
		logAndExport(l,None,"</dependencies>")
	else: logAndExport(l,None,"<dependencies/>")

def writeElaborations(elabOwner,l):
	if len(elabOwner.elaborations.keys())>0:
		logAndExport(l,None,"<elaborations>")
		for next,stuff in elabOwner.elaborations.items():
			logAndExport(l+1,None,"<elaboration>")
			logAndExport(l+2,None,"<eventInvocation>")
			logAndExport(l+3,"enclosingInstance",stuff["enclosingClass"])
			logAndExport(l+3,"eventType",next.getID())
			classType = str(next.getClassType()).split(".")[-1].strip("'>")
			identifier = "%s_%s_%s" % (next.name,classType,next.owner.name)
			logAndExport(l+3,"eventName",identifier)
			logAndExport(l+3,None,"<arguments>")
			for (name,value,type) in stuff["args"]:
				logAndExport(l+4,None,"<parameter>")
				logAndExport(l+5,"name",name)
				logAndExport(l+5,"value",value)
				logAndExport(l+4,None,"</parameter>")
			logAndExport(l+3,None,"</arguments>")
			logAndExport(l+2,None,"</eventInvocation>")
			if "conditions" in stuff.keys() and stuff["conditions"]:
				for name,exp in stuff["conditions"].items():
					logAndExport(l+2,None,"<!--%s condition %s-->" % (next.name,name))
					logAndExport(l+2,None,"<condition>")
					logAndExport(l+3,"expression",exp)
					logAndExport(l+2,None,"</condition>")
			logAndExport(l+1,None,"</elaboration>")
		logAndExport(l,None,"</elaborations>")
	else: logAndExport(l,None,"<elaborations/>")

def makeValueString(val,tag):
	if val is None: vString = "<%s/>" % tag
	else: vString = "<%s>%s</%s>" % (tag,val,tag)
	return vString

def writeEffects(effectOwner,l):
	if len(effectOwner.effects)>0:
		logAndExport(l,None,"<effects>")
		for effect in effectOwner.effects:
			logAndExport(l+1,"effect",effect)
		logAndExport(l,None,"</effects>")
	else: logAndExport(l,None,"<effects/>")

def translateActivity(classThingy,l):
	logAndExport(l,None,"<event>")
	logAndExport(l+1,None,"<!--%s-->" % classThingy.name)
	
	#---CLASS---#
	logAndExport(l+1,None,"<class>")
	logAndExport(l+2,"name",str(classThingy.id))
	writeMembers(classThingy,l+2)
	logAndExport(l+2,None,"<constraints/>")
	writeDependencies(classThingy,l+2)
	
	#---EVENTS---#
	logAndExport(l+2,None,"<events>")
	for actionE in classThingy.classes:	
		logAndExport(l+3,None,"<event>")
		
		#---CLASS---#
		logAndExport(l+4,None,"<class>")
		logAndExport(l+5,"name",actionE.id)
		if actionE.inheritsFrom is not None: logAndExport(l+5,"inheritsFrom",actionE.inheritsFrom)
		logAndExport(l+5,None,"<!--<actionName>%s</actionName><actionType>%s</actionType>-->" % (actionE.name,actionE.actionType))
		writeMembers(actionE,l+5)
		if len(actionE.constraints.keys())>0:
			logAndExport(l+5,None,"<constraints>")
			for constraintType,cList in actionE.constraints.items():
				for c in cList: logAndExport(l+6,None,"<constraint><expression>%s</expression></constraint>" % c)
			logAndExport(l+5,None,"</constraints>")
		else: logAndExport(l+5,None,"<constraints/>")
		writeDependencies(actionE,l+5)
		logAndExport(l+4,None,"</class>")
		
		writeEffects(actionE,l+4)
		writeElaborations(actionE,l+4)
		logAndExport(l+3,None,"</event>")
	logAndExport(l+2,None,"</events>")
	
	logAndExport(l+1,None,"</class>")
	
	#---ELABORATIONS--#
	writeElaborations(classThingy,l+1)
	
	logAndExport(l,None,"</event>")
	
