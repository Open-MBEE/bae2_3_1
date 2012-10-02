#For LADWP CyberSecurity Project 8/2/12.
#Authors: Maddalena Jackson, (add yourselves)
#		  Mark McKelvin
# Last modified: 08/12/2012, McKelvin
# Last modified: 08/20/2012, Jackson

#TODO:
#figure out how to have each type of action properly handle it's inputs - opaques process pins, etc.
#for that matter, how to associate the pins with the signals with the inputs? (pins = name of signal?)
#will need special parameters for read self (?), variable... maybe not (variable just puts out a ...variable, but will need to 
# the default
#action has members for each input pin ... signals set to those variables or passed in by invoker!
#aaaand, how to do the port/structure bit.
#set/get structural featurs - effect based on parameters...
#ALH.getsignal - change to parameter node and send to call behavior.
#concurrent activity is one that has a queue, and others don't. 
#translator will make dependency/queue adding ability depending on structure (don't export structure)
#type of classifier behavior reference should be what is now the value...
#invoke time == start time => dependency
#or pass the start time directly into the startTime
#get value is for time varying things
#usage.setValue(startTime,integer (no.getvalue))
#fix sends
# = setValue(add a time)
# .class on to the signal type definitions
#Objects (BOOL for controls)
#time parameter in constructors (load, cap, meterblah)... --->.getvalue().setValue(0,x) (THINK i fixed)
#constructors set value is a parameter... (THINK i fixed)

#.java file for objectflow and structuralsignal
#magicdraw's code generation
#need = exceution context or something sot hat we actually instantiate Power SYstem? CONSTRUCTORS
#opaque function - this.duration = 20 for sleeps... 
#no condition, no exists var if it's always true to elaborate.


#c,l,v ---> classes



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
from java.io import File
from javax.swing import JFileChooser

global gl

generatedXmlFileName = ''

class ClassifierClass(object):
	def __init__(self,system):
		classType = str(system.getClassType()).split(".")[-1].strip("'>")
		self.identifier = "%s_%s_%s" % (system.name,classType,system.owner.name)
		self.name = system.name
		self.id = system.name #SO THAT WE GET READABLE CLASS NAMES
		if isinstance(system,Signal): self.id = "Signal"+system.name
		#self.id = system.getID()
		self.events=[]
		self.members={} #"name":("type","value")
		self.structuralSignals = {}
		self.queues = {}
		self.MDsignalClasses = []
		self.methods = []
		self.constructors=[]
		self.cArgs={}
		self.inspectComposition(system)
		
	def inspectComposition(self,system):
		signalsToBuild = []
		#fill members
		simpleProperties = [prop for prop in system.ownedAttribute if not isinstance(prop,Port) and isinstance(prop.type,DataType)]
		gl.log("Simple Properties: " + str([str(pt.name) for pt in simpleProperties]))
		if isinstance(system,Signal):
			self.constructors.append("super();")
			self.constructors.append("init%sMembers();" % self.id)
			self.constructors.append("init%sCollections();" % self.id)
			self.constructors.append("init%sElaborations();" % self.id)
		for p in simpleProperties:
			propName = p.name + "_" + p.getID()
			self.members[propName] = ("TimeVaryingMap&lt;%s&gt;" % p.type.name,'new TimeVaryingMap("%s")' % p.name,"simple property (name " + p.name + ")")
			if isinstance(system,Signal): 
				self.cArgs["x"]=p.type.name
				self.cArgs["t"]="Timepoint"
				self.constructors.append("%s.getValue().setValue(t,x);" % propName)
		if len(simpleProperties)==0 and isinstance(system,Signal): 
			propName="control"
			self.members[propName] = ("TimeVaryingMap&lt;Boolean&gt;",'new TimeVaryingMap("%s")' % propName,"DEFAULT NO STRUCTURE OBJECT FLOW (name Control)")
			self.cArgs["x"]= "Boolean"
			self.cArgs["t"]="Timepoint"
			self.constructors.append("%s.getValue().setValue(t,x);" % propName)
		
		partProperties = [prop for prop in system.ownedAttribute if not isinstance(prop,Port) and isinstance(prop.type,Class)]
		gl.log("Part Properties: " + str([str(pt.name) for pt in partProperties]))
		for p in partProperties:
			self.members[p.name] = (p.type.name,"new " + p.type.name + "(this)","part property (name " + p.name + ")")
			classifiersToInspect.append(p.type) #this will probably have to change when we have lots of customers... also will need multiplicity
			constructorArgs[p.type]=system
			
		if system in constructorArgs.keys():
			self.constructors.append("super();")
			arg = constructorArgs[system]
			self.cArgs["x"]=arg.name
			self.constructors.append('this.x = new Parameter<%s>("%s",null,x,null);' % (arg.name,arg.name))
			self.members["x"]=(arg.name,None,"initialize reference to power system (or containing class)") #remove?
			self.constructors.append("init%sMembers();" % self.id)
			self.constructors.append("init%sCollections();" % self.id)
			self.constructors.append("init%sElaborations();" % self.id)
		
		#structural signals
		if not isinstance(system,Signal):
			if "super();" not in self.constructors:
				self.constructors.append("super();")
				self.constructors.append("init%sMembers();" % self.id)
				self.constructors.append("init%sCollections();" % self.id)
				self.constructors.append("init%sElaborations();" % self.id)
			for c in system.ownedConnector:
				infoFlows = c.get_informationFlowOfRealizingConnector() 
				gl.log("CONNECTOR: ")
				gl.log("	" + str(isinstance(infoFlows[0].conveyed[0],Signal)) + " ... " + infoFlows[0].conveyed[0].humanType)
				gl.log("	" + str(isinstance(infoFlows[0].source,Port)) + " ... " + infoFlows[0].source[0].humanType)
				gl.log("	" + str(isinstance(infoFlows[0].target,Port)) + " ... " + infoFlows[0].target[0].humanType)
				for i in [x for x in infoFlows if isinstance(x.conveyed[0],Signal) and isinstance(x.source[0],Port) and isinstance(x.target[0],Port)]: 
					gl.log("	+info flow: " + str(i) + " conveyed: " + str(i.conveyed[0].name))
					signal = i.conveyed[0] #generic signal type...
					if signal not in signalsToBuild: signalsToBuild.append(signal)
					port = i.source[0]
					targetQueueOwner = i.target[0].owner #inherited??
					name = "ss" + port.getID() + "_" + signal.name
					ss_invocation = 'new ObjectFlow("%s",%s.Signal%s.class)' % (name,system.name,signal.name) #portID_sigID, sigID.class
					self.members[name] = ("ObjectFlow&lt;Signal%s&gt;" % signal.name,ss_invocation,"Create New Structural SIgnal objectflow for signal" + signal.name +" between " + port.owner.name + " and " + targetQueueOwner.name)
					#setListeners(List<ObjectFlow<Obj>>["q_"+targetQueueOwner.name+"_"+signal.name]
					targname = "q_"+targetQueueOwner.name+"_"+signal.name
					constructortext = "%s.addListener(%s);" % (name,targname)
					pwp = None
					for e in c.end:
						if e.partWithPort.type is targetQueueOwner: pwp = e.partWithPort
					if pwp:
						constructortext = "((ObjectFlow) %s.getValue()).addListener(((ObjectFlow) ((%s) %s.getValue()).%s.getValue()));" % (name,targetQueueOwner.name,pwp.name,targname)
					self.constructors.append(constructortext)
					#((ObjectFlow) ss_17_0_5_edc0357_1345510113562_98635_13780_changeLoadValue.getValue()).addListener(((ObjectFlow) ((Power) p.getValue()).q_Power_changeLoadValue.getValue()));
			
			
			
			for p in system.ownedPort:
				for e in p.end:
					c = e.owner
					infoFlows = c.get_informationFlowOfRealizingConnector() 
					gl.log("CONNECTOR: ")
					gl.log("	" + str(isinstance(infoFlows[0].conveyed[0],Signal)) + " ... " + infoFlows[0].conveyed[0].humanType)
					gl.log("	" + str(isinstance(infoFlows[0].source,Port)) + " ... " + infoFlows[0].source[0].humanType)
					gl.log("	" + str(isinstance(infoFlows[0].target,Port)) + " ... " + infoFlows[0].target[0].humanType)
					for i in [x for x in infoFlows if isinstance(x.conveyed[0],Signal) and isinstance(x.source[0],Port) and isinstance(x.target[0],Port)]: 
						signal = i.conveyed[0] #generic signal type...
						#if signal not in signalsToBuild: signalsToBuild.append(signal)
						ofName = "q_"+system.name+"_"+signal.name
						q_invocation = 'new ObjectFlow("%s",%s.Signal%s.class)' % (ofName,constructorArgs[system].name,signal.name) #portID_sigID, sigID.class
						self.members[ofName] = ("ObjectFlow&lt;Signal%s&gt;" % signal.name,q_invocation,"Create New objectflow for signal to " + system.name)
						
			
			#build signals
			for s in signalsToBuild:
				self.MDsignalClasses.append(ClassifierClass(s))
			gl.log("Signal Classes: " + str(self.MDsignalClasses))
			#fill events
			for act in system.ownedBehavior: self.events.append(activityEventClass(act)) #will assume that you can't reference outside activities yet...
			
			#queues
			#self.members[system.getID() + "_queue"] = ("Queue","new LinkedList&lt;Object&gt;()","Queue for " + system.name)
			
			#run classifierbehavior
			if system.classifierBehavior:
				self.members["classifierBehavior"] = ("String", '"%s"' % system.classifierBehavior.getID(),"Classifier Behavior for " + system.name + " is " + system.classifierBehavior.name)
				#self.methods.append("this.start() {return new this.classifierBehavior};")
		#queues
		#sigports
		
		
#TODO - if you're a classifier behavior, have a queue. when you pop something off the queue, set it to has stuff (?)
class activityEventClass(object):
	def __init__(self,activity):
		gl.log("HI, my name is " + activity.name)
		classType = str(activity.getClassType()).split(".")[-1].strip("'>")
		self.identifier = "%s_%s_%s" % (activity,classType,activity.owner.name)
		self.id = str(activity.getID())
		self.name = activity.name
		self.members = {} #{name: (type,value)}
		self.objectFlows = {} #flow,item type
		self.elaborations = {}
		self.constraints = {}
		self.dependencies = {} #are there any?
		self.classes = []
		self.initial = None
		self.members["invoke_time"] = ("Integer",None,"invoke time for whole activity")
		self.invokeDict = {}
		self.enclosingClass = self.id + ".this"
		
		#new dictionaries for easier action assessment
		self.invokedInvoker = {} #might not need
		self.invokedFlow = {}
		self.flowObject={}
		self.invokerInvoked = {} #might not need, could construct for each action
		self.invokerFlow = {}
		
		self.inspectComposition(activity)
	
	def getObject(self,flow):
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
			finalSourceFlow = self.findEventualSourceFlow(flow)
			if finalSourceFlow: return self.getObject(finalSourceFlow) #could still be none...
		return None

	def addToDictionaries(self,invokingThing,invokedThing,flow):
		flowType = None
		if self.isObjectFlow(flow): 
			flowType = self.getObject(flow)
			#if isinstance(flowType,Property) and flowType.type and isinstance(flowType.type,Class): 
			#	flowType = flowType.type
			#	gl.log("FLOW TYPE WAS PROPERTY, TYPE IS %s" % flowType.name)
		else:
			gl.log("I'm control") 
			flowType = "Control"
		#if it's still none, that means source and target aren't typed.
		if invokedThing in self.invokedInvoker.keys() : self.invokedInvoker[invokedThing].append(invokingThing)
		else: self.invokedInvoker[invokedThing] = [invokingThing]
		if invokedThing in self.invokedFlow.keys(): self.invokedFlow[invokedThing].append(flow)
		else: self.invokedFlow[invokedThing] = [flow]
		self.flowObject[flow] = flowType
		if invokingThing in self.invokerInvoked.keys(): self.invokerInvoked[invokingThing].append(invokedThing)
		else: self.invokerInvoked[invokingThing] = [invokedThing]
		if invokingThing in self.invokerFlow.keys(): self.invokerFlow[invokingThing].append(flow)
		else: self.invokerFlow[invokingThing] = [flow]
		
	def inspectComposition(self,activity):
		#inspect joins
		for join in [j for j in activity.node if isinstance(j,JoinNode)]:
			if all([isinstance(i,ControlFlow) for i in join.incoming]):
				invokingThingSource = join.incoming[0].source
				if isinstance(invokingThingSource,Pin): invokingThing = invokingThingSource.owner
				else: invokingThing = invokingThingSource
				
				self.invokeDict[join] = invokingThing
				
				self.addToDictionaries(invokingThing, join, join.incoming[0])
				
				for inc in [z for z in join.incoming if z is not join.incoming[0]]:
					signame = "sig" + str(inc.getID())
					self.members[signame] = ("ObjectFlow&lt;Boolean&gt;",'new ObjectFlow("'+signame+'")',"member for object flow")
		
		#inspect edges...
		for e in activity.edge:
			next = self.getNext(e)
			gl.log("NEXT: " + next.name)
			if self.iShouldInvokeNext(e):
				gl.log("I (%s) SHOULD INVOKE %s" % (self.getPrev(e).name,next.name))
				if next not in self.invokedInvoker.keys() or isinstance(next,MergeNode): #join node will already be there
					gl.log("I'm gonna add to dictionary...")
					self.addToDictionaries(self.getPrev(e), next, e) 
					self.invokeDict[next] = self.getPrev(e)
			else: 
				if self.isObjectFlow(e):
					gl.log("	object flow for " + self.getPrev(e).name + " to " + next.name + ": " + str(self.getObject(e)) + " (" + e.getID() + ")")
					obj = self.getObject(e)
					#if isinstance(obj,Property) and obj.type and isinstance(obj.type,Class): 
				#		obj = obj.type
				#		gl.log("PROPERTY - OBJECT TYPE %s" % obj.name)
					self.flowObject[e] = obj 
					self.objectFlows[e] = obj
		
		#make object edges into members (we've already done that for control joins)
		self.translateEdgesToMembers()
		
		#now parse all the activities
		for node in activity.node:
			if isinstance(node,InitialNode):
				self.initial = node
				self.elaborations[node] = {
										"args":[("startTime","invoke_time","Integer")],
										"enclosingClass" : self.enclosingClass}
			if isinstance(node,ActivityParameterNode):
				p_id = node.parameter.getID()
				if node.parameter.type: tname = node.parameter.type.name
				else: tname = "Object"
				self.members[p_id]= (tname, None,"Activity Parameter Node")
				gl.log("PARAM DIRECTION: " + str(node.parameter.direction))
				if str(node.parameter.direction)=="out": self.members["sig"+p_id]=("ObjectFlow&lt;%s&gt;" % node.parameter.type.name,None,"Initialize passed-in object flow!")
				if len(node.outgoing)>0: self.elaborations[node] = {
																"args": [("startTime","invoke_time","Integer"),(p_id,p_id,tname)],
																"enclosingClass" : self.enclosingClass}
				elif len(node.outgoing)>0: pass #HOW DO I GET THE VALUE OUT OF IT>>>>
			if isinstance(node,AcceptEventAction):
				event = node.trigger[0].event
				if not isinstance(event,TimeEvent):
					sig = event.signal #TOTALLY could be not a "trigger event", should make this more robust.
					signame = "sig" + str(sig.getID())
					self.members[signame] = ("ObjectFlow&lt;Signal%s&gt;" % sig.name,'new ObjectFlow("'+signame+'")',"object flow for signal") #do we need this??
			if isinstance(node,CallBehaviorAction):
				for op in node.output:
					param = op.parameter
					pt = "Object"
					if param == None:
					  gl.log("BADNESS 0")
					else:
					  if param.type: pt = param.type.name
					  signame = "sig" + param.getID()
					  self.members[signame] = ("ObjectFlow&lt;%s&gt;" % pt,'new ObjectFlow("'+signame+'")', "object flow for return type activity parameter nodes")
			
			dicts = (self.invokeDict,self.invokedInvoker,self.invokedFlow,self.flowObject,self.invokerInvoked,self.invokerFlow,self.objectFlows)
			self.classes.append(actionEventClass(node,dicts,self.id))
	
	def findEventualTarget(self,edge):
		for o in edge.target.outgoing:
			if isinstance(o.target,Pin) or isinstance(o.target,ActivityParameterNode): return o.target
			elif isinstance(o.target,ControlNode): return self.findEventualTarget(o)
			else: return None
	
		
	def findEventualSourceFlow(self,flow): #only works on object flows?
		if isinstance(flow.source,Pin) or isinstance(flow.source,ActivityParameterNode): return flow
		elif isinstance(flow.source,ControlNode): return self.findEventualSourceFlow(flow.source.incoming[0])
		else: return None
			
	
	def translateEdgesToMembers(self):
		for edge,pinType in self.objectFlows.items():
			pt = "Object"
			if pinType: #need to make classes for these if they're more complicated than "Integer..."
				#if not isinstance(pinType,DataType): #TODO: we need to inspect the object... new inspect method for structures...doesn't recursively analyze behavior..
				if isinstance(pinType,Property) and pinType.type: pt = pinType.type.name
				else: pt = pinType.name
			else: pass #wasn't typed!!!
			k = str("sig" + edge.getID())
			t = "ObjectFlow&lt;%s&gt;" % pt
			self.members[k] = (t,'new ObjectFlow("'+k+'")',"member for object flow signal")
			
	def iShouldInvokeNext(self,flow):
		#object flows from anywhere to a pin
		if isinstance(flow.target,Pin):
			if len(flow.target.owner.incoming) == 0: 
				if flow.target.owner in self.invokedInvoker.keys(): return False
				else: return True
			else: return False
		#flows into a control node of any kind
		elif isinstance(flow.target,ControlNode):
			if len(flow.target.incoming) == 1: return True
			elif isinstance(flow.target,DecisionNode) and flow.target.getDecisionInputFlow() is flow: return False
			elif isinstance(flow.target,DecisionNode) and flow.target.getDecisionInputFlow() is not flow: return True
			elif isinstance(flow.target,JoinNode) and self.invokedInvoker[flow.target] in [flow.source, flow.source.owner]: return True
			elif isinstance(flow.target,MergeNode): return True
			else: return False
		#flows to actions
		else: return True #model checking earlier on should detect if more than one incoming to an action...
	
	def isObjectFlow(self,o):
		if isinstance(o.target,Pin) or isinstance(o.target,ActivityParameterNode) or isinstance(o.source,Pin) or isinstance(o.source,ActivityParameterNode): return True
		elif isinstance(o.target,ControlNode):
			if isinstance(o.target,DecisionNode) and o.target.decisionInputFlow is o: return True
			if not isinstance(o.target,FinalNode): return self.isObjectFlow(o.target.outgoing[0])
		else: return False
	
	def getNext(self,flow):
		if isinstance(flow.target,Pin): return flow.target.owner
		else: return flow.target
		
		
	def getPrev(self,flow):
		if isinstance(flow.source,Pin): return flow.source.owner
		else: return flow.source
		
		
#not addressed yet:
#pins/types/variables (should just be a parameter...)
#signals
#specifics of add structural feature, etc.
#durations...
#should allow signals to be passed in as parameters?
#conditions? how actually set true? after elaboration/invoke event, condition is that i am active?		
class actionEventClass(object):
	def __init__(self,actionNode,dicts,encloserID):
		
		classType = str(actionNode.getClassType()).split(".")[-1].strip("'>")
		self.identifier = "%s_%s_%s" % (actionNode.name,classType,actionNode.owner.name)
		(self.invokeDict,self.invokedInvoker,self.invokedFlow,self.flowObject,self.invokerInvoked,self.invokerFlow,self.flowTypes) = dicts
		self.name = actionNode.name
		gl.log("MY NAME IS: %s : %s [%s]" % (actionNode.name, actionNode.humanType,actionNode.getID()))
		self.actionType = actionNode.humanType
		#self.name = actionNode.getID()
		self.id = actionNode.getID()
		self.members = {} #name : (type,value)
		self.constraints = {}
		self.dependencies = {}
		self.elaborations = {}
		self.effects = [] #expression
		#self.invokeDict = joinDict
		self.enclosingClass = encloserID + ".this"
		
		self.inspectMyself(actionNode)
		self.inspectByType(actionNode)

	def safeConvertProperty(self,prop):
		if prop.type and isinstance(prop.type,Class): return prop.type
		else: return prop
		
	def inspectMyself(self,actionNode):
		gl.log(self.enclosingClass)
		iFollow = []
		iPrecede = {}
		iSend = []
		iReceive = []
		
		
		previousList = None
		if actionNode in self.invokedInvoker.keys(): previousList = self.invokedInvoker[actionNode]
		nextList = None
		if actionNode in self.invokerInvoked.keys(): nextList = self.invokerInvoked[actionNode]
		
		'''try:
			inpins = actionNode.input
			for pin in inpins:
				if pin.type: t = pin.type.name
				else: t = "Object"
				self.members[pin.getID()] = (t,None,"Initializing Input Pins")
		except: gl.log("exception trying to acquire input pins")'''
		try:
			outpins = actionNode.output
			for pin in outpins:
				t = "Object"
				if pin.type: t = pin.type.name
				elif pin.outgoing[0] in self.flowTypes.keys(): 
					t = self.flowTypes[pin.outgoing[0]].name #TODO - maybe need to check if it's a property?
				elif isinstance(actionNode,ReadStructuralFeatureAction):
					t = actionNode.structuralFeature.type.name
				elif isinstance(actionNode,AcceptEventAction):
					event = actionNode.trigger[0].event
					if not isinstance(event,TimeEvent):
						sig = event.signal
						t = "Signal" + sig.name
				elif isinstance(actionNode,ValueSpecificationAction):
					t =  actionNode.value.type.name
				self.members[pin.getID()] = (t,None,"Initializing Output Pins")
		except: gl.log("exception trying to acquire output pins")
		
		
		#----Role = INVOKED BY SOMETHING ELSE
		self.constraints["startTime"]=[]
		if previousList: #weed out initial nodes, parameter nodes...
			if len(previousList) == 1: #merge node not working here?
				previous = previousList[0]
				invokingFlow = self.invokedFlow[actionNode][0]
				object = self.flowObject[invokingFlow]
				if isinstance(object,Property): object = self.safeConvertProperty(object)
				
				self.members[previous.getID() + "_endTime"] = ("Integer",None,"previous event end time: " + getPrettyIdent(previous))
				self.constraints["startTime"].append("startTime &gt;" + str(previous.getID())+"_endTime")
				
				#members for object input receptions
				if object != "Control": #control type
					if object: 
						oname = object.name
						if isinstance(object,Property): oname = object.type.name
						if isinstance(object,Signal): 
							context = actionNode.context
							try: prep = constructorArgs[context].name+"."
							except: prep = ""
							oname = prep+"Signal"+object.name
					else: oname = "Object"
					if isinstance(invokingFlow.target,Pin): #should be a pin I own...
						ip = invokingFlow.target
						if isinstance(actionNode,CallBehaviorAction): ip = invokingFlow.target.parameter
						if ip:
							self.members[ip.getID()] = (oname,None,"Parameter passed in by Object-Invoker (pin id or parameter id) %s" % ip.name)
					elif isinstance(invokingFlow.target,ActivityParameterNode):
						try: d = str(invokingFlow.target.parameter.getDefault())
						except: d = None
						self.members[invokingFlow.target.parameter.getID()] = (oname,d,"Parameter passed in by Object-Invoker to ParamNode %s" % invokingFlow.target.parameter.name)
					else: self.members["objectToPass"] = (oname,None,"Object that this control node passes along")
				
				#MEMBERS & EFFECTS - Non-Invoking Objects/Signals
				if not isinstance(actionNode,ControlNode):
					try: inpins = actionNode.input
					except: inpins = []
					for inpin in inpins:
						flow = inpin.incoming[0]
						if flow is not invokingFlow:
							t = self.flowTypes[flow]
							if t: 
								tname = t.name
								if isinstance(t,Property) and t.type and isinstance(t.type,Class): 
									gl.log("	_-----_-_-property! %s" % t.name)
									tname = t.type.name
								gl.log("TNAME: " + tname)
							else: tname = "Object"
							ip = inpin
							if isinstance(actionNode,CallBehaviorAction): ip = inpin.parameter
							if ip == None:
							  gl.log("BADNESS 1")
							else:
							  self.members[ip.getID()] = (tname,None,"Variable to store signal receipt from this pin (or parameter if it's a call behavior)")
							  #self.effects.append(ip.getID() + " = sig" + flow.getID() + ".receive(startTime - 1)") #or should it be .setValue(flow.getID().receive(startTime))
							  self.dependencies[ip.getID()] = (tname,"sig" + flow.getID() + ".receive(startTime - 1)")
				#DECISION NODE - NEED VARIABLE FOR DECISION INPUT
				elif isinstance(actionNode,DecisionNode): pass #SEPARATE
					
				#TODO - need otehr join node?
					
			else: #MERGE NODE - can be invoked by either one...
				self.members["invoker_endTime"] = ("Integer",None,"Invoke Time")
				self.constraints["startTime"].append("startTime &gt; invoker_endTime")
				prev0 = previousList[0]
				invokingFlow0 = self.invokedFlow[actionNode][0]
				#gl.log(str(previousList) + str(self.invokedFlow[previousList[0]]) + str(invokingFlow0))
				object = self.flowObject[invokingFlow0]
				gl.log("MERGE OBJECTTT" + str(object))
				if isinstance(object,Property): object = self.safeConvertProperty(object)
				gl.log("Found a merge node!! " + str(object))
				if object != "Control": #control type
					if object: oname = object.name
					else: oname = "Object"
					self.members["objectToPass"] = (oname,None,"Object that this control node passes along")	
		else: #PARAM NODE OR INITIAL NODE
			#self.members["invoke_time"]=("time",None,"Initial Node or Parameter Node's invoke time")
			#self.constraints["startTime"]=["startTime==invoke_time"]
			#start time for these is passed in directly from invoker
			if isinstance(actionNode,ActivityParameterNode):
				param = actionNode.parameter
				default = None
				if param.getDefault() and param.getDefault()!="": default = param.getDefault()
				ptype = param.type
				if ptype: tname = ptype.name
				else: tname = "Object"
				self.members[actionNode.parameter.getID()] = (tname,default,"Parameter Node's parameter ID")

		#----Role = INVOKER OF SOMETHING ELSE
		myOutgoingSignalFlows=[]
		if nextList: #weed out final nodes and activity parameter nodes...
			#call behavior: signal send = sigout parameter.receive()
			myOutgoingSignalFlows.extend([x for x in actionNode.outgoing if x not in self.invokerFlow[actionNode]]) #any outgoing flows that don't invoke stuff
			try: myOutputPins = actionNode.output
			except: myOutputPins = []
			myOutgoingSignalFlows.extend([x.outgoing[0] for x in myOutputPins if x.outgoing[0] not in self.invokerFlow[actionNode]])
			i = 0
			for next in nextList:
				gl.log("next: " + next.name)
				invokingFlow = self.invokerFlow[actionNode][i]
				object = self.flowObject[invokingFlow]
				if isinstance(object,Property): object = self.safeConvertProperty(object)
				
				self.members[next.getID() + "_exists"] = ("Boolean","false","Initialize existence of " + getPrettyIdent(next) + " as false")
				
				
				#basic elaboration:
				elabVar = actionNode.getID() + "_endTime"
				if isinstance(next,MergeNode): elabVar = "invoker_endTime"
				#gl.log(self.enclosingClass)
				self.elaborations[next]={
										"args": [(elabVar,"endTime","Integer")],
										"conditions": {next:"endTime.getValue()"},
										"enclosingClass" : self.enclosingClass}
				
				#dependencies on "_exists"
				dependencyString = False
				if invokingFlow.guard:
					b = str(invokingFlow.guard.body[0]) #could be more than one
					b = b.replace("<","&lt;")
					b = b.replace(">","&gt;")
					dependencyString = str(b)
				
				#find all other signals we depend on
				if not isinstance(next,MergeNode): #because merge node is invoked as an OR not an AND
					nextsInvokingFlows=self.invokedFlow[next]
					if len(nextsInvokingFlows)>1: gl.log("*****ARGHH non-merge node has many invokers!!")
					nextsInvokingFlow=nextsInvokingFlows[0]
					try: nextsInPins = next.input
					except: nextsInPins = []
					signalFlows = []
					if len(next.incoming)>1: #join nodes and decision nodes only, hopefully...
						signalFlows.extend([x for x in next.incoming if x is not nextsInvokingFlow])
					if len(nextsInPins)>0:
						signalFlows.extend([x.incoming[0] for x in nextsInPins if x.incoming[0] is not invokingFlow])
					for inc in signalFlows:	
						s = "sig" + str(inc.getID()) + ".hasStuff(endTime.getValue())"
						if dependencyString: dependencyString = " &amp;&amp; ".join([dependencyString,s])
						else: dependencyString = s
				if isinstance(next,AcceptEventAction):
					event = next.trigger[0].event
					if not isinstance(event,TimeEvent):
						sig = event.signal
						context = next.context
						s = "q_" + context.name + "_" + sig.name + ".hasStuff(endTime.getValue())"
						if dependencyString: dependencyString = " &amp;&amp; ".join([dependencyString,s])
						else: dependencyString = s
				if not dependencyString: dependencyString = "true"
				self.dependencies[next.getID() + "_exists"] = ("Boolean",str(dependencyString))
				
				#send invoke params to next if it's an object-invoker
				src = "objectToPass"
				if isinstance(invokingFlow.source,Pin): 
					src = invokingFlow.source.getID()
				if isinstance(invokingFlow.source,ActivityParameterNode): src = invokingFlow.source.parameter.getID()
				#gl.log(str(object))
				if object != "Control":
					if isinstance(invokingFlow.target,Pin): #pin-pin - easy object-invoker
						it = invokingFlow.target.getID()
						if isinstance(invokingFlow.target.owner,CallBehaviorAction): it = invokingFlow.target.parameter.getID()
						self.elaborations[next]["args"].append((it,src,"Object"))
						self.elaborations[next]["enclosingClass"] = self.enclosingClass
					elif isinstance(invokingFlow.target,ControlNode):
						self.elaborations[next]["args"].append(("objectToPass",src,"Object"))
						self.elaborations[next]["enclosingClass"] = self.enclosingClass
					elif isinstance(invokingFlow.target,ActivityParameterNode):
						self.elaborations[next]["args"].append((invokingFlow.target.parameter.getID(),src,"Object"))
						self.elaborations[next]["enclosingClass"] = self.enclosingClass
				i+=1
		else:
			myOutgoingSignalFlows.extend([x for x in actionNode.outgoing])
			try: myOutputPins = actionNode.output
			except: myOutputPins = []
			myOutgoingSignalFlows.extend([x.outgoing[0] for x in myOutputPins])

		for outFlow in myOutgoingSignalFlows:
			if isinstance(outFlow.source,Pin): #should handle pin --> decision input flow
				self.effects.append("sig" + outFlow.getID() + ".send(" + outFlow.source.getID()+",startTime)")
				tname = "Object"
				if outFlow in self.flowTypes.keys():
					t = self.flowTypes[outFlow] #apparently there can be a key errror. wtf!?
					if t: tname = t.name
					if isinstance(t,Property): tname = t.type.name
				self.members[outFlow.source.getID()] = (tname,None,"Outgoing Signal Flow " + outFlow.source.name)
			elif isinstance(actionNode,ActivityParameterNode):
				self.effects.append("sig" + outFlow.getID() + ".send(" + actionNode.parameter.getID()+ ",startTime)")
			elif outFlow in self.flowTypes.keys(): #should handle fork --> decision input flow
				self.effects.append("sig" + outFlow.getID() + ".send(objectToPass,startTime)")
			else:
				self.effects.append("sig" + outFlow.getID() + ".send(true,startTime)") #for control signals...
		

	def inspectByType(self,node):
		myType = node.humanType
		if myType == "Read Self Action":
			#add: member for out-pin?
			objectFlowOut = node.result.outgoing[0]
			try: itemname = node.result.type.name
			except: itemname = node.owner.owner.name
			self.members[node.result.getID()] = (itemname,itemname+".this","Instance of whoever is operating this activity")
		elif myType =="Add Structural Feature Value Action" :
			sf = node.structuralFeature
			objectFlowIn = node.object.incoming[0]
			valueFlowIn = node.value.incoming[0]
			self.effects.append(sf.name + "_" + sf.getID() + ".setValue(startTime,"+ node.value.getID() + ")")
		elif myType =="Read Structural Feature Action" :
			sf = node.structuralFeature
			objectIn = node.object
			objectOut = node.result
			prepend=""
			try: tname = sf.type.name
			except:tname = "Object"
			if isinstance(sf.type,DataType): self.dependencies[objectOut.getID()]=(tname, node.object.getID() + "." + sf.name + "_" + sf.getID() + ".getValue(startTime)") #call the "field" of the structural feature on the incoming object, which should be correct type for that...
			else: self.dependencies[objectOut.getID()] = (tname,node.object.getID() + "." + sf.name)
		elif myType =="Send Signal Action" :
			sig = node.signal
			port = node.onPort
			structSig = "x.ss" + port.getID() + "_" + sig.name
			argPhrase = ""
			for arg in node.argument:
				if argPhrase is not "": argPhrase = " , ".join(argPhrase,arg.getID())
				else: argPhrase = arg.getID()+".getValue()"
			if argPhrase =="": #means there are no arguments
				argPhrase = "true"
			try: 
				ct = constructorArgs[node.context]
				prepend=ct.name+"."
				invokePhrase = "x.getValue().new Signal%s(endTime.getValue(),%s)" % (sig.getName(),argPhrase)
			except: 
				prepend = ""
				invokePhrase = "new %sSignal%s(endTime.getValue(),%s)" % (prepend,sig.getName(),argPhrase)
			self.effects.append(structSig + ".send(%s,endTime.getValue())" % invokePhrase)
		elif myType =="Accept Event Action" :
			event = node.trigger[0].event
			if not isinstance(event,TimeEvent):
				sig = event.signal
				context = node.context
				try: 
					ct = constructorArgs[node.context]
					prepend=ct.name+"."
				except: prepend = ""
				#queue stuff here?? this isn't right...
				#self.effects.append("sig" + sig.getID()+" = sig"+sig.getID() + ".receive(startTime - 1)")
				for res in node.result:
					self.dependencies[res.getID()] = ("%sSignal%s" % (prepend,sig.name),"q_"+context.name+"_"+sig.name + ".receive(startTime - 1)")
				#self.members["sigVar_" + sig.name]=(sig.name,None,getPrettyIdent(node) + " accepts the " + sig.name + " signal")
				for pin in node.output:
					if pin.type == sig:
						flow = pin.outgoing[0]
						next = flow.target
						if isinstance(next,Pin): next = next.owner
						#if self.invokeDict[next] is node: #or could make this generic... use the signal variable even if it's invoking...
							#self.elaborations[next]["args"].extend([(flow.target.getID(),"sigVar" + sig.name,"Object")])
							#self.elaborations[next]["enclosingClass"] = self.enclosingClass
						#else: self.effects.append("sig%s.send(sigVar_%s,endTime)" % (flow.getID(),sig.name))
			else: 
				when = event.when
				w = str(when.expr.value).strip("s")
				self.dependencies["duration"] = ("Integer", w)
		elif myType =="Value Specification Action" :
			val = node.value
			tname = None
			if isinstance(val,LiteralBoolean):
				(v,tname) = (str(val.isValue()).lower(),"Boolean")
			elif isinstance(val,LiteralInteger):
				(v,tname) = (str(val.getValue()),"Integer")
			else: v = str(val)
			objectFlowOut = node.result.outgoing[0]
			if not tname: 
				tname = "Object"
				t = node.value.type
				if t: tname = node.value.type.name
			self.members[node.result.getID()] = (tname,str(v),"value specification VALUE")
		elif myType =="Opaque Action" :
			self.effects.append("result = " + str(node.body))
		elif myType =="Call Behavior Action" :
			#gl.log(node.behavior.humanType)
			if node.behavior.humanType == "Opaque Behavior":
				gl.log("*****OPAAAAQUE")
				args = node.argument
				res = node.result
				body = node.behavior.body
				for p in args: 
					if p.type: tname = p.type.name #parameter
					else: tname = "Object"
					if p.parameter: self.members[p.parameter.name] = (str(tname),None,"Argument Param: " + p.name)
					if p.parameter: self.dependencies[p.parameter.name] = (str(tname),p.parameter.getID())
				for p in res:
					if p.type: tname = p.type.name
					else: tname = "Object"
					if p.parameter: self.members[p.parameter.name] = (str(tname),None,"Result Pin: " + p.name)
					if p.parameter: self.dependencies[p.parameter.name] = (str(tname),node.behavior.body[0].split("=")[1])
					if p.parameter: self.dependencies[p.getID()] = (str(tname),p.parameter.name)
			else:
				self.elaborations[node.behavior]={
												"args": [("endTime","cba_endTime","Integer")],
												"enclosingClass" : node.behavior.owner.name+".this"}
				for pin in node.input:
					if pin.type: tname = pin.type.name
					else: tname = "Object"
					if pin.parameter:
						self.elaborations[node.behavior]["args"].append((pin.parameter.getID(),pin.parameter.getID(),tname))
				for pin in node.output:
					if pin.type: tname = pin.type.name
					else: tname = "Object"
					#self.effects.append(pin.getID()+ " = sig" + pin.parameter.getID() + ".receive(endTime - 1)")
					self.dependencies[pin.getID()] = (tname,"sig" + pin.parameter.getID() + ".receive(endTime.getValue() - 1)")
					self.elaborations[node.behavior]["args"].append(("sig"+pin.parameter.getID(),"sig"+pin.parameter.getID(),"ObjectFlow"))
				self.members["cba_endTime"] = ("Integer",None,"Placeholder for CBA's end time")
				
				
			#self.dependencies["myBehavior"]=("Integer",node.behavior.getID()+".endTime")
		elif myType =="Start Object Behavior Action":
			#self.effects.append(node.object.getID() + ".start()")
			inc = node.object.incoming[0]
			#gl.log(inc.getID())
			flowThing = self.flowObject[node.object.incoming[0]]
			if flowThing: 
				if isinstance(flowThing,Element):
					gl.log("ELABORATION= " + str(flowThing) + flowThing.name + node.name)
					flowClass=flowThing
					if isinstance(flowThing,Property): flowClass = flowThing.type
					self.elaborations[flowClass.classifierBehavior] = {
					#self.elaborations[flowThing.classifierBehavior] = {
																	"args" : [("invoke_time","startTime","Integer")],
																	"enclosingClass" : flowThing.name}
				else: gl.log("*****ERROR -- CONTROL FLOW INTO START OBJECT BEHAVIOR OBJECT PIN!!")
			else: gl.log("*****ERROR -- CANNOT ELABORATE TO NONE FROM START OBJECT BEHAVIOR!!")
			#else: it's none, that's an error! maybe need to make structural features add this to flow dict...
		elif myType =="Activity Parameter Node":
			if len(node.incoming) > 0: 
				self.effects.append("sig" + node.parameter.getID()+".send("+ node.parameter.getID() + ",endTime.getValue())")
		elif myType =="Decision Node":
			gl.log("Special Decision Node Guard Handling...")
			dif = node.decisionInputFlow
			if dif:
				try: tname = self.flowTypes[dif].name
				except:tname = "Object"
				self.members[dif.getID()] = (tname,None,"Variable for DecisionInputFlow")
				#self.effects.append(dif.getID() + " = sig" + dif.getID() + ".receive(startTime - 1")
				self.dependencies[dif.getID()] = (tname,"sig" + dif.getID() + ".receive(startTime - 1)")
				for dName,(dType,dVal) in self.dependencies.items():
					if dName.endswith("_exists"):
						self.dependencies[dName] = (dType,dVal.replace("ALH.getTokenValue()",dif.getID()))

				
	
	
	
	def getPrettyIdent(self,node):
		return node.name + " (" + node.getClassType().split(".")[-1].strip("'>") + ")"		
	
	def isObjectFlow(self,o):
			if isinstance(o.target,Pin): return True
			elif isinstance(o.target,ControlNode) and not isinstance(o.target,FinalNode): return self.isObjectFlow(o.target.outgoing[0])
			else: return False
	
	def iShouldInvokeNext(self,flow):
		#object flows from anywhere to a pin
		if isinstance(flow.target,Pin):
			if len(flow.target.owner.incoming) == 0: 
				if flow.target.owner in self.invokeDict.keys(): return False
				else: return True
			else: return False
		#flows into a control node of any kind
		elif isinstance(flow.target,ControlNode):
			if len(flow.target.incoming) == 1: return True
			elif isinstance(flow.target,DecisionNode) and flow.target.getDecisionInputFlow() is flow: return False
			elif isinstance(flow.target,JoinNode) and self.invokeDict[flow.target] in [flow.source, flow.source.owner]: return True
			elif isinstance(flow.target,MergeNode): return True
			else: return False
		#flows to actions
		else: return True #model checking earlier on should detect if more than one incoming to an action...
	
	def getNext(self,flow):
		if isinstance(flow.target,Pin): return flow.target.owner
		else: return flow.target		
			
	def getPrev(self,flow):
		if isinstance(flow.source,Pin): return flow.source.owner
		else: return flow.source

def setup():
	#initialize the guiLog
	gl = Application.getInstance().getGUILog()
	gl.clearLog()
	debug = True
	logDebug = False
	global debug
	global logDebug
	global gl
	
	#get a copy of the project - useful for getting the elements factory, various other things like that.
	project = Application.getInstance().getProjectsManager().getActiveProject()
	global project

	#declare any stereotype elements here, for convenience.	
	return True

def getPrettyIdent(node):
		return node.name + " (" + str(node.getClassType()).split(".")[-1].strip("'>") + ")"		

def inspectClassifier(c):
	return ClassifierClass(c)

#TODO - make a "to inspect" category for classes on this level?
def inspectTactic(act,data): 
	gl.log(str(time.time()) + "\n*****hi, i'm the " + act.name + " activity!")
	#opaque behaviors are just chunks of math or functions that understand the input parameters.
	if act.humanType == "Opaque Behavior": 
		gl.log("I'm an opaque behavior! Look at my math or whatever!")
		return data #get rid of references to data...
	return activityEventClass(act)
	
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
		

def run(s):
	if not setup(): return
	gl.log("Date: October 2")
	#get the user's selection - the element that should be top level and contain (recursively) all other systems/behaviors you wish to reason about.
	firstSelected = Application.getInstance().getMainFrame().getBrowser().getActiveTree().getSelectedNodes()[0].getUserObject()
	gl.log(str(time.time()) + "You have selected " + firstSelected.name + " as the highest level element in your system.")
	
	#make sure the user picked a class... 
	if not isinstance(firstSelected,Class):
		gl.log("***ERROR: you must select some kind of class as your top level element.")
		return
	
	systemBehavior = firstSelected.classifierBehavior
	if systemBehavior: gl.log(systemBehavior.name)
	else: 
		gl.log("***ERROR: your top level system doesn't have a classifier behavior!")
		return
	

	inspected = []
	classifiersToInspect = [firstSelected]
	toInspect = [firstSelected]
	partsDict = {firstSelected:None}
	classesToTranslate = []
	constructorArgs = {}
	global data
	global inspected
	global toInspect
	global partsDict
	global classifiersToInspect
	global constructorArgs
	
	count = 0
	while len(classifiersToInspect)>0:
		if count > 50:
			gl.log("***LOOP LIMIT REACHED!")
			break
		thing = classifiersToInspect.pop(0)
		classesToTranslate.append(inspect(thing))
		count += 1

	homeDir = os.getenv('HOME')
	if homeDir == None:
		homeDir = os.curdir
	log_dir = os.path.join(homeDir,'LADWP_LOGS')
	if not os.path.exists(log_dir):
		try: os.mkdir(log_dir)
		except: #this won't do much if you're running in batch mode...
		    print "Error creating log directory!"
	os.chdir(log_dir)
	log_file_name = "Scenario_XML_" + str(formatCurrentTime()) + ".xml"
	log_file = open(log_file_name,"w")
	global log_file
	try: writeScenario(firstSelected,classesToTranslate)
	except: 
		exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
		gl.log("*** EXCEPTION:")
		messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
		for message in messages:
			gl.log(message)
		log_file.close
	#data=inspectClassifier(firstSelected,None,data)
	log_file.close()
	latestDir = "latest"
	if not os.path.exists(latestDir):
		try: os.mkdir(latestDir)
		except: #this won't do much if you're running in batch mode...
			print "Error creating latest directory!"
	generatedXmlFileName = latestDir + os.sep + "Scenario_latest.xml"
	shutil.copyfile(log_file_name, generatedXmlFileName)
	
	gl.log("INSPECTED:")
	for thing in inspected:
		gl.log("	+" + thing.name + " (" + thing.humanType + ")")
		
	gl.log("\nPARTS DICT:")
	for k,v in partsDict.items():
		gl.log(k.name + " -- " + str(v))
	
	gl.log("The log file for this execution is located at: " + str(log_dir))
	
	return

'''<events>
		<event>
			<class>
				<name>CustomerCreator</name>
				<members>
					<parameter>
						<name>cust</name>
						<type>Customer</type>
						<value>new Customer()</value>
					</parameter>
				</members>
			</class>
			<elaborations>
				<elaboration>
					<eventInvocation>
						<enclosingInstance>cust</enclosingInstance>
						<eventType>Customer._17_0_5_edc0357_1346893970375_935485_14291</eventType>
						<eventName>Activity</eventName>
					</eventInvocation>
				</elaboration>
			</elaborations>
		</event>
	</events>
	
	<eventToBeExecuted>
		<eventType>CustomerCreator</eventType>
		<arguments>
			<parameter>
				<name>startTime</name>
				<value>0</value>
			</parameter>
		</arguments>
	</eventToBeExecuted>'''



def logAndExport(tabNum,tag,text):
	line = "	"*tabNum
	if tag: line = line+"<%s>%s</%s>" % (tag,text,tag)
	else: line = line+text
	log_file.write(line+"\n")
	if logDebug: gl.log(line)

def writeScenario(top,classesToTranslate):
	logAndExport(0,None,"<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>")
	logAndExport(0,None,"<scenario xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"/Users/mjackson/Documents/workspace-Helios/CS/src/gov/nasa/jpl/ae/xml/eventSchema.xsd\">")
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
	logAndExport(6,"eventName","Activity")
	logAndExport(6,None,"<arguments/>")
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
	if isinstance(classThingy,activityEventClass):translateActivity(classThingy,l)
	elif isinstance(classThingy,ClassifierClass): translateClass(classThingy,l)

def writeConstructor(classThingy,l):
	if len(classThingy.constructors)>0:
		x=""
		if len(classThingy.cArgs.keys())>0:
			if "t" in classThingy.cArgs.keys(): x = classThingy.cArgs["t"] + " t,"
			else: x = ""
			x+= str(classThingy.cArgs["x"] + " x")
		logAndExport(l,None,"<constructors>")
		logAndExport(l+1,None,"<function>")
		logAndExport(l+1,None,"<![CDATA[")
		logAndExport(l+1,None," %s(%s) {" % (classThingy.id,x))
		for c in classThingy.constructors:
			logAndExport(l+2,None,"%s" % c)
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
			logAndExport(l+2,"type",type)
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
				#logAndExport(l+5,"type",type)
				logAndExport(l+5,"value",value)
				logAndExport(l+4,None,"</parameter>")
			logAndExport(l+3,None,"</arguments>")
			logAndExport(l+2,None,"</eventInvocation>")
			if "conditions" in stuff.keys() and stuff["conditions"]:
				for next,time in stuff["conditions"].items():
					exp = str(next.getID()) + "_exists"
					logAndExport(l+2,None,"<!--%s_exists-->" % next.name)
					logAndExport(l+2,None,"<condition>")
					logAndExport(l+3,"expression",exp)
					#logAndExport(l+3,None,"<timeApplicable><start><expression>%s</expression></start><end><expression>%s<expression></end></timeApplicable>" % (time,time))
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
	logAndExport(l+2,None,"<dependencies/>")
	
	#---EVENTS---#
	logAndExport(l+2,None,"<events>")
	for actionE in classThingy.classes:	
		logAndExport(l+3,None,"<event>")
		
		#---CLASS---#
		logAndExport(l+4,None,"<class>")
		logAndExport(l+5,"name",actionE.id)
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
	
def formatCurrentTime():
    tsec=time.localtime(time.time()) #as opposed to gmtime
    then ="%s-%03iT%02i.%02i.%02i" % (tsec.tm_year, tsec.tm_yday, tsec.tm_hour,tsec.tm_min,tsec.tm_sec)
    return then		
