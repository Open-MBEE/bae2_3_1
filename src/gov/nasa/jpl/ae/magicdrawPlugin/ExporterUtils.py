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

gl = Application.getInstance().getGUILog()
global gl
debug = False
logDebug = False
global debug
global logDebug

def convertTime(fromType,toType,t):
	#conversion use "newT = t * conversion[from][to]
	conversion = {
				"s": {"s":1,"m":1/60,"h":1/3600},
				"m": {"s":60,"m":1,"h":1/60},
				"h": {"s":3600,"m":60,"h":1}
				}
	if fromType in conversion.keys() and toType in conversion.keys():
		newTime = t * conversion[fromType][toType]
	else: newTime = None
	return newTime

def getNext(flow):
	if isinstance(flow.target,Pin): return flow.target.owner
	else: return flow.target
	
def getPrev(flow):
	if isinstance(flow.source,Pin): return flow.source.owner
	else: return flow.source

def getPrettyIdent(node):
	return node.name + " (" + str(node.getClassType()).split(".")[-1].strip("'>") + ")"	

#walks along flows until it finds a pin or an activity parameter node
#preconditions:
#	-you already know that edge.target is not a pin or parameter node
#postcondition: a none response indicates that the flow doesn't end in an object exchange
#this is intended to help you figure out if your flow passes objects, and is really intended to help
#you figure out what kind of object is flowing when you look at the next node and it's a fork or decision node or something. 
def findEventualTarget(edge):
	for o in edge.target.outgoing:
		if isinstance(o.target,Pin) or isinstance(o.target,ActivityParameterNode): return o.target
		elif isinstance(o.target,ControlNode): return findEventualTarget(o)
		else: return None

#walks "backwards" along flows until it finds a pin or a parameter node. (or finds non-control node)
#possible problem: what if there's nothing incoming (model incomplete or something?)
def findEventualSourceFlow(flow): #only works on object flows?
	if isinstance(flow.source,Pin) or isinstance(flow.source,ActivityParameterNode): return flow
	elif isinstance(flow.source,ControlNode): return findEventualSourceFlow(flow.source.incoming[0])
	else: return None
		
#checks all ends of current flow for object type recipients, walks if it finds a control node
def isObjectFlow(o):
	if isinstance(o.target,Pin) or isinstance(o.target,ActivityParameterNode) or isinstance(o.source,Pin) or isinstance(o.source,ActivityParameterNode): return True
	elif isinstance(o.target,ControlNode):
		if isinstance(o.target,DecisionNode) and o.target.decisionInputFlow is o: return True
		if not isinstance(o.target,FinalNode): return isObjectFlow(o.target.outgoing[0])
		else: return False #just added, might be weird?
	else: return False

#"convert" from property to property type (useful for structural feature reads/sets)
def safeConvertProperty(prop):
	if prop.type and isinstance(prop.type,Class): return prop.type
	else: return prop

def getObject(flow):
	if isinstance(flow.source,Pin):
		t = flow.source.type
		if t: return t
		owner = flow.source.owner
		if isinstance(owner,ReadSelfAction):
			if owner.context: return owner.context
		if isinstance(owner,ReadStructuralFeatureAction): return owner.structuralFeature
		if isinstance(owner,AcceptEventAction):
			event = owner.trigger[0].event
			if not isinstance(event,TimeEvent):
				sig = event.signal
				return sig
		if isinstance(owner,ValueSpecificationAction):
			return owner.value.type
	if isinstance(flow.target,Pin):
		t = flow.target.type
		if t: return t
	if isinstance(flow.source,ActivityParameterNode): 
		if flow.source.parameter.type: return flow.source.parameter.type
	if isinstance(flow.source,ControlNode): 
		finalSourceFlow = findEventualSourceFlow(flow)
		if finalSourceFlow: return getObject(finalSourceFlow) #could still be none...
	return None

#just so i know what this is....
class ObjectFlow(object):
	def __init__(self):
		self.transporting = None
		self.history = {}
	def receive(self,time):
		o = self.transporting
		self.transporting = None
		self.history[time]=None
		return o
	def send(self,time,transportedItem): #need transported item?
		self.transporting = transportedItem
		self.history[time]=self.transporting
		return
	def hasStuff(self,time):
		#do stuff
		return True #(or false!)

class StructuralSignal(object):
	def __init__(self,targetQueue,isValid,signalType):
		self.isValid = isValid #boolean
		self.targetQueue = targetQueue #name of queue not queue object
		self.signalType = signalType #name of signal (?)
		self.currentSignal = None #timeVaryingMapThingy?
	def send(self,sigInstance,time):
		if self.isValid and isinstance(sigInstance,self.signalType):
			self.targetQueue.getInstance().add(sigInstance,time)
			self.currentSignal = sigInstance
	def receive(self,time):
		if self.currentSignal:
			cs = self.currentSignal
			self.currentSignal = None
			return cs
		else: return None

def formatCurrentTime():
    tsec=time.localtime(time.time()) #as opposed to gmtime
    then ="%s-%03iT%02i.%02i.%02i" % (tsec.tm_year, tsec.tm_yday, tsec.tm_hour,tsec.tm_min,tsec.tm_sec)
    return then	
   
def setDebug(status):
	debug = status
	
def setLogDebug(status):
	logDebug = status