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


#if next is accept event-
#dependecy: endTime = Math.min(objectflow.nextTimeNotNull(starttime+1),activityfinalnodeendtime)



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
#from java.io import File
#from javax.swing import JFileChooser
from javax.swing import JOptionPane

global gl

generatedXmlFileName = ''

class ClassifierClass(object):
	def __init__(self,system):
		stime = time.time()
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
		self.errors = {}
		gl.log("time to make classifier: %s" % str(time.time()-stime))
		
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
			pl = "Plottable" if not isinstance(system,Signal) else ""
			self.members[propName] = ("TimeVarying%sMap&lt;%s&gt;" % (pl,p.type.name),'new TimeVarying%sMap("%s")' % (pl,p.name),"simple property (name " + p.name + ")")
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
						constructortext = "((ObjectFlow) %s.getValue()).addListener(((ObjectFlow) (%s.getValue()).%s.getValue()));" % (name,pwp.name,targname)
					self.constructors.append(constructortext)
					#((ObjectFlow) ss_17_0_5_edc0357_1345510113562_98635_13780_changeLoadValue.getValue()).addListener(((ObjectFlow) ((Power) p.getValue()).q_Power_changeLoadValue.getValue()));
						
			#build signals
			for s in signalsToBuild:
				self.MDsignalClasses.append(ClassifierClass(s))
			gl.log("Signal Classes: " + str(self.MDsignalClasses))
			
			#fill events
			for act in system.ownedBehavior: 
				aeC = activityEventClass(act)
				self.events.append(aeC) #will assume that you can't reference outside activities yet...
				gl.log(str(aeC.errors.keys()))
				if len(aeC.errors.keys())>0: self.errors[act] = aeC.errors
					
				#self.members[act.getID()+"_endTime"] = ("Integer",None,"endTime var for %s " % act.name)
			
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
		stime = time.time()
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
		self.final = None
		self.invokeDict = {}
		self.enclosingClass = self.id + ".this"
		
		#new dictionaries for easier action assessment
		self.invokedInvoker = {} #might not need
		self.invokedFlow = {}
		self.flowObject={}
		self.invokerInvoked = {} #might not need, could construct for each action
		self.invokerFlow = {}
		
		#NEW
		self.flowTypes = {}
		self.allSignals = {}
		self.nexts = {}
		self.prevs = {}
		self.ranks = {}
		self.errors = {}

		owner = activity.owner
		if activity is not owner.classifierBehavior:
			self.dependencies["caller.endTime"] = ("Integer","finalNode_endTime")
			self.members["caller"] = ("DurativeEvent",None,"Initialize variable for cba that called this")
			#self.members["duration"] = ("Integer","60","fake duration?")
			#self.members["endTime"] = ("Integer","300","fake end time")
		
		self.inspectComposition(activity)
		gl.log("time to inspect activity: %s" % str(time.time() - stime))
		
	def getPrettyIdent(self,node):
		#gl.log("DEBUG: node name %s (%s) (%s)" %(node.name,str(node),str(node.getClassType())))
		return node.name + " (" + str(node.getClassType()).split(".")[-1].strip("'>") + ")"	
	
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
		#Initialize Activity Parameter Nodes
		for p in activity.ownedParameter:
			try: d = str(p.getDefault())
			except: d = None
			self.members[p.getID()] = (p.type.name,d,"Initialize Activity Parameter Values (for transition through")
		
		#Set up final nodes
		for final in [f for f in activity.node if isinstance(f,ActivityFinalNode)]:
			self.members[final.getID() + "_exists"] = ("Boolean","false","Initialize existence of " + getPrettyIdent(final) + " as false")
			for inc in final.incoming:
				signame = "sig" + str(inc.getID())
				self.members[signame] = ("ObjectFlow&lt;Boolean&gt;",'new ObjectFlow("'+signame+'")',"member for FINAL NODE object flow")
			s = "finalNode_startTime != null &amp;&amp; sig" + str(inc.getID()) + ".hasStuff(finalNode_startTime - 1)" #WAS +1
			self.dependencies[final.getID() + "_exists"] = ("Boolean",s)
		
		#inspect edges...
		for e in activity.edge:		
			#NEW
			if self.isObjectFlow(e): 
				flowType = self.getObject(e)
				gl.log("FLOW TYPE %s" % str(flowType))
			else:
				gl.log("FLOW TYPE: Control") 
				flowType = "Control"
			self.flowTypes[e] = flowType
		
		#make object edges into members (we've already done that for control joins)
		self.translateEdgesToMembers()
		
		#now parse all the activities
		for node in activity.node:
			self.allSignals[node]={"out":[],"in":[]}
			self.nexts[node]={}
			self.prevs[node]={}
			gl.log("BUILDING DICTIONARIESSSSSS")
			try: 
				outpins = node.output
				for pin in outpins: self.allSignals[node]["out"].append(pin.outgoing[0])
			except: gl.log(self.getPrettyIdent(node) + " has no output pins")
			for f in node.outgoing: self.allSignals[node]["out"].append(f)
			try:
				inpins = node.input
				for pin in inpins: self.allSignals[node]["in"].append(pin.incoming[0])
			except: gl.log(self.getPrettyIdent(node) + "has no input pins")
			for f in node.incoming: self.allSignals[node]["in"].append(f)
			
			gl.log("nexts for %s" % self.getPrettyIdent(node))
			for outFlow in self.allSignals[node]["out"]:
				self.nexts[node][self.getNext(outFlow)]=outFlow
				gl.log("	next: %s" % self.getPrettyIdent(self.getNext(outFlow)))
				
			gl.log("prevs for %s" % self.getPrettyIdent(node))
			thisRank = 1
			l = len(self.allSignals[node]["in"])
			for inFlow in self.allSignals[node]["in"]:
				prev = self.getPrev(inFlow)
				self.prevs[node][prev]=inFlow
				gl.log("	prev: %s" % self.getPrettyIdent(prev))
				if l>1 and not isinstance(node,MergeNode):
					gl.log("	thisRank: " + str(thisRank))
					decider = "myDeciderID_decider%s" % node.getID()
					if prev in self.ranks.keys(): 
						pd = self.ranks[prev]
						pd[decider] = thisRank
					else: self.ranks[prev] = {decider : thisRank}
				thisRank+=1
				
			
			l = len(self.allSignals[node]["in"])
			if l>1 and not isinstance(node,MergeNode):
				self.members["decider%s" % node.getID()] = ("TimeVaryingList&lt;Integer&gt;",'new TimeVaryingList("decider%s",%s)' % (node.getID(),str(l)),"INTIALIZE DECIDER FOR %s with %s elements" % (self.getPrettyIdent(node),str(l)))
			
			if isinstance(node,InitialNode):
				self.initial = node
				self.elaborations[node] = {
										"args":[("startTime","startTime","Integer")],
										"enclosingClass" : self.enclosingClass}
			if isinstance (node,ActivityFinalNode):
				if self.final: 
					if "Multiple Final Nodes" in self.errors.keys(): self.errors["Multiple Final Nodes!"].append(node)
					else: self.errors["Multiple Final Nodes!"] = [node]
				self.final = node
				self.members["finalNode_endTime"] = ("Integer",None,"variable for final node's end time!")
				self.dependencies["endTime"] = ("Integer","finalNode_endTime")	
				self.members["finalNode_startTime"]=("Integer",None,"variable for final node's start time!")
				self.elaborations[node] = {
										#"args":[("endTime","endTime","Integer"),("startTime",node.getID()+"_startTime","Integer")],
										"args":[("startTime","finalNode_startTime","Integer")], #try not passing in the end time as a ref
										"conditions": {"exists":node.getID()+"_exists"},
										"enclosingClass" : self.enclosingClass}
			
			if isinstance(node,ActivityParameterNode):
				p_id = node.parameter.getID()
				if node.parameter.type: tname = node.parameter.type.name
				else: tname = "Object"
				#self.members[p_id]= (tname, None,"Activity Parameter Node")
				#gl.log("PARAM DIRECTION: " + str(node.parameter.direction))
				#if str(node.parameter.direction)=="out": 
				#	self.members["sig"+p_id]=("ObjectFlow&lt;%s&gt;" % node.parameter.type.name,None,"Initialize passed-in object flow!")
				self.members[node.getID()+"_startTime"]=("Integer",None,"variable for act param node's start time!")
				if len(node.outgoing)>0: self.elaborations[node] = {
																"args": [("startTime","startTime","Integer"),(p_id,p_id,tname)],
																"enclosingClass" : self.enclosingClass}
				#elif len(node.outgoing)>0: pass #HOW DO I GET THE VALUE OUT OF IT>>>>
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
			
		for node in activity.node:	
			dicts = (self.invokeDict,self.invokedInvoker,self.invokedFlow,self.flowObject,self.invokerInvoked,self.invokerFlow,self.objectFlows,self.flowTypes,self.allSignals, self.nexts,self.prevs)
			r = None
			if node in self.ranks.keys(): r = self.ranks[node]
			aeClass = actionEventClass(node,dicts,self.id,r,self.final.getID())
			self.classes.append(aeClass)
			if len(aeClass.errors.keys()) > 0:
				self.errors[node] = aeClass.errors
			
	
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
		for flow,type in self.flowTypes.items():
			typeName = "Object"
			if type is not "Control": 
				if isinstance(type,Property) and type.type: typeName = type.type.name
				elif isinstance(type,Signal): typeName = "Signal"+type.name
				else: typeName = type.name
			else: typeName = "Boolean"
			k = str("sig" + flow.getID())
			t = "ObjectFlow&lt;%s&gt;" % typeName
			self.members[k] = (t,'new ObjectFlow("'+k+'")',"NEWWWWW member for object flow signal")

			
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
		

class actionEventClass(object):
	def __init__(self,actionNode,dicts,encloserID,rankDict,finalID):
		stime = time.time()
		
		classType = str(actionNode.getClassType()).split(".")[-1].strip("'>")
		self.identifier = "%s_%s_%s" % (actionNode.name,classType,actionNode.owner.name)
		(self.invokeDict,self.invokedInvoker,self.invokedFlow,self.flowObject,self.invokerInvoked,self.invokerFlow,self.flowTypes,self.feet,self.allSignals,self.nexts,self.prevs) = dicts
		#gl.log("FEET: " + str(self.feet))
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
		self.decisionDict = rankDict #{myDeciderID_deciderX : rank}
		self.errors = {}
		self.finalID = finalID
		self.inspectMyself(actionNode)
		self.inspectByType(actionNode)
		gl.log("time to inspect action: %s" % str(time.time() - stime))

	def safeConvertProperty(self,prop):
		if prop.type and isinstance(prop.type,Class): return prop.type
		else: return prop
		
	def inspectMyself(self,actionNode):
		gl.log(self.enclosingClass)		
		
		previousList = None
		if actionNode in self.invokedInvoker.keys(): previousList = self.invokedInvoker[actionNode]
		nextList = None
		if actionNode in self.invokerInvoked.keys(): nextList = self.invokerInvoked[actionNode]
		
		#NEW
		self.setUpSignalEffectsAndMembers(actionNode)
		self.setUpBasicElaborationsAndDependencies(actionNode)
		
		#----Role = INVOKED BY SOMETHING ELSE
		
		self.constraints["startTime"]=[]
		if previousList: #weed out initial nodes, parameter nodes...
			if len(previousList) == 1: #merge node not working here?
				previous = previousList[0]
				invokingFlow = self.invokedFlow[actionNode][0]
				object = self.flowObject[invokingFlow]
				if isinstance(object,Property): object = self.safeConvertProperty(object)
				
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
					elif isinstance(invokingFlow.target,ActivityParameterNode):
						try: d = str(invokingFlow.target.parameter.getDefault())
						except: d = None

				
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
							else: pass
					
			else: #MERGE NODE
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
		else: #PARAM NODE OR INITIAL NODE
			if isinstance(actionNode,ActivityParameterNode):
				param = actionNode.parameter
				default = None
				if param.getDefault() and param.getDefault()!="": default = param.getDefault()
				ptype = param.type
				if ptype: tname = ptype.name
				else: tname = "Object"

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
				if isinstance(next,ActivityFinalNode):
					signame = "sig" + invokingFlow.getID()
					#self.effects.append("sig" + invokingFlow.getID() + ".send(true,endTime)")
					continue
				object = self.flowObject[invokingFlow]
				if isinstance(object,Property): object = self.safeConvertProperty(object)
				
				#basic elaboration:
				elabVar = actionNode.getID() + "_endTime"
				if isinstance(next,MergeNode): elabVar = "invoker_endTime"

				
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
						s = "sig" + str(inc.getID()) + ".hasStuff(endTime)"
						if dependencyString: dependencyString = " &amp;&amp; ".join([dependencyString,s])
						else: dependencyString = s
				if isinstance(next,AcceptEventAction):
					event = next.trigger[0].event
					if not isinstance(event,TimeEvent):
						sig = event.signal
						context = next.context
						s = "q_" + context.name + "_" + sig.name + ".hasStuff(endTime)"
						if dependencyString: dependencyString = " &amp;&amp; ".join([dependencyString,s])
						else: dependencyString = s
				if not dependencyString: dependencyString = "true"
				
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
				i+=1
		else:
			myOutgoingSignalFlows.extend([x for x in actionNode.outgoing])
			try: myOutputPins = actionNode.output
			except: myOutputPins = []
			myOutgoingSignalFlows.extend([x.outgoing[0] for x in myOutputPins])

		for outFlow in myOutgoingSignalFlows:
			if isinstance(outFlow.source,Pin): #should handle pin --> decision input flow
				tname = "Object"
				if outFlow in self.flowTypes.keys():
					t = self.flowTypes[outFlow] #apparently there can be a key errror. wtf!?
					if t: tname = t.name
					if isinstance(t,Property): tname = t.type.name

	def inspectByType(self,node):
		myType = node.humanType
		canHaveDuration = True
		nextAccepts = [x for x in self.nexts[node] if isinstance(x,AcceptEventAction)]
		if len(nextAccepts)>0:
			if any([not isinstance(x.trigger[0].event,TimeEvent) for x in nextAccepts]): canHaveDuration = False
		if isinstance(node,AcceptEventAction) and isinstance(node.trigger[0].event,TimeEvent): canHaveDuration = False
		if isinstance(node,CallBehaviorAction) and not node.behavior.humanType == "Opaque Behavior": canHaveDuration = False
		if canHaveDuration: self.dependencies["duration"] = ("Integer","1")
		
		if myType == "Read Self Action":
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
			prepend = ""
			if node.context in constructorArgs.keys(): prepend = constructorArgs[node.context].name + "."
			structSig = "x.ss" + port.getID() + "_" + sig.name
			self.members["signalObject"] = (prepend + "Signal" + sig.name,None,"initialize place holder for constructed signal")
			self.dependencies["signalObject"] = (prepend + "Signal" + sig.name,"x.new %sSignal%s()" % (prepend,sig.name))
			self.effects.append(structSig + ".send(signalObject,endTime)")
			for signalAttribute in sig.attribute:
				matched = False
				for arg in node.argument:
					if arg.name == signalAttribute.name: 
						self.effects.append("signalObject.%s_%s.setValue(endTime,%s)" % (signalAttribute.name,signalAttribute.getID(),arg.getID()))
						matched = True
				if not matched:
					if len(node.argument)==1 and len(sig.attribute)==1:
						self.effects.append("signalObject.%s_%s.setValue(endTime,%s)" % (signalAttribute.name,signalAttribute.getID(),node.argument[0].getID()))
						matched = True
				if not matched:
					self.errors[node] = "can't match node arguments to signal attributes!!"		
			if len(sig.attribute) == 0: self.effects.append("signalObject.control.setValue(endTime,true)")
	
		elif myType =="Accept Event Action" :
			event = node.trigger[0].event
			if not isinstance(event,TimeEvent):
				sig = event.signal
				context = node.context
				try: 
					ct = constructorArgs[node.context]
					prepend=ct.name+"."
				except: prepend = ""
				for res in node.result:
					self.dependencies[res.getID()] = ("%sSignal%s" % (prepend,sig.name),"q_"+context.name+"_"+sig.name + ".receive(startTime)")
				for pin in node.output:
					if pin.type == sig:
						flow = pin.outgoing[0]
						next = flow.target
						if isinstance(next,Pin): next = next.owner
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
			self.dependencies[node.result.getID()] = (tname,str(v))
		
		elif myType =="Opaque Action" :
			self.effects.append("result = " + str(node.body))
		
		elif myType =="Call Behavior Action" :
			if node.behavior.humanType == "Opaque Behavior":
				gl.log("*****OPAAAAQUE")
				args = node.argument
				res = node.result
				body = node.behavior.body
				for p in args: 
					if p.type: tname = p.type.name #parameter
					else: tname = "Object"
					if p.parameter: self.members[p.parameter.name] = (str(tname),None,"Argument Param: " + p.name)
					if p.parameter: self.dependencies[p.parameter.name] = (str(tname),p.getID())
				for p in res:
					if p.type: tname = p.type.name
					else: tname = "Object"
					if p.parameter: self.members[p.parameter.name] = (str(tname),None,"Result Pin: " + p.name)
					if p.parameter: self.dependencies[p.parameter.name] = (str(tname),node.behavior.body[0].split("=")[1])
					if p.parameter: self.dependencies[p.getID()] = (str(tname),p.parameter.name)
			else:
				behav = node.behavior
				owner = behav.owner
				#self.members["duration"] = ("Integer","45","fake duration")
				#self.dependencies["endTime"] = ("Integer","finalNode_endTime")
				self.elaborations[node.behavior]={
												#"args": [("endTime","endTime","Integer"),("startTime","startTime","Integer")],
												"args": [("startTime","startTime","Integer"),("caller","this","DurativeEvent")],
												"enclosingClass" : node.behavior.owner.name+".this"}
				for pin in node.input:
					if pin.type: tname = pin.type.name
					else: tname = "Object"
					if pin.parameter:
						self.elaborations[node.behavior]["args"].extend([(pin.parameter.getID(),pin.parameter.getID(),tname)])
						self.dependencies[pin.parameter.getID()] = (pin.parameter.type.name,pin.getID())
						self.members[pin.parameter.getID()] = (pin.parameter.type.name,None,"NEW - initialize parameter pass-through variable for call behavior")
				for pin in node.output:
					if pin.type: tname = pin.type.name
					else: tname = "Object"
					self.elaborations[node.behavior]["args"].extend([(pin.parameter.getID(),pin.parameter.getID(),pin.parameter.type.name)])
					self.dependencies[pin.getID()] = (pin.parameter.type.name,pin.getID())
					self.members[pin.parameter.getID()] = (pin.parameter.type.name,None,"NEW - initialize output parameter storage value")

		elif myType =="Start Object Behavior Action":
			inc = node.object.incoming[0]
			flowThing = self.feet[inc]
			if flowThing: 
				if isinstance(flowThing,Element):
					gl.log("ELABORATION= " + str(flowThing) + flowThing.name + node.name)
					flowClass=flowThing
					if isinstance(flowThing,Property): flowClass = flowThing.type
					self.elaborations[flowClass.classifierBehavior] = {
					#self.elaborations[flowThing.classifierBehavior] = {
																	"args" : [("startTime","startTime","Integer")],
																	"enclosingClass" : flowThing.name}
				else: gl.log("*****ERROR -- CONTROL FLOW INTO START OBJECT BEHAVIOR OBJECT PIN!!")
			else: gl.log("*****ERROR -- CANNOT ELABORATE TO NONE FROM START OBJECT BEHAVIOR!!")

		elif myType =="Activity Parameter Node":
			if str(node.parameter.direction)=="in": 
				self.dependencies["objectToPass"] = (node.parameter.type.name,node.parameter.getID())
				self.members[node.parameter.getID()] = (node.parameter.type.name, None, "Initialize Activity Parameter Node receptacle for incoming value!")
			else: self.dependencies[node.parameter.getID()]=(node.parameter.type.name,"objectToPass")
		
		elif myType =="Decision Node":
			gl.log("Special Decision Node Guard Handling...")
			dif = node.decisionInputFlow
			if dif:
				try: tname = self.flowTypes[dif].name
				except:tname = "Object"
				for dName,(dType,dVal) in self.dependencies.items():
					if dName.endswith("_exists"):
						self.dependencies[dName] = (dType,dVal.replace("ALH.getTokenValue()","decisionInput"))
		elif myType == "Activity Final Node":
			self.dependencies["endTime"] = ("Integer","startTime+duration")
			self.dependencies["finalNode_endTime"] = ("Integer","endTime")
		
		elif myType == "Merge Node": 
			tname = "Object"
			try:
				nextkey = self.nexts[node].keys()[0]
				nextFlow = self.nexts[node][nextkey]
				(t,tname) = self.getObtypeName(nextFlow, node)
			except: gl.log('ARRGHAHGHAHGHAHG')
			self.members["receiveThis"] = ("ObjectFlow&lt;%s&gt;" % tname,None,"Anonymous receive for invoker!")
			
				
	def setUpBasicElaborationsAndDependencies(self,node):
		for n in self.nexts[node].keys():
			if isinstance(n,ActivityFinalNode): #TODO - activity parameter nodes?
				self.dependencies["finalNode_startTime"] = ("Integer","endTime+2")
				continue #TODO - put dependency stuff on the elaborator (the ACTIVITY)
			nFlow = self.nexts[node][n]
			dependencyString = False
			self.members[n.getID()+"_exists"] = ("Boolean","false","NEW - initialize nexts to false")
			if nFlow.guard:
				b = str(nFlow.guard.body[0]) #could be more than one
				b = b.replace("<","&lt;")
				b = b.replace(">","&gt;")
				dependencyString = str(b)
				
			for otherPrev in self.prevs[n].keys(): #take out "my" flow??
				if isinstance(n,AcceptEventAction):
					event = n.trigger[0].event
					if not isinstance(event,TimeEvent):
						sig = event.signal
						context = n.context
						qname = "q_" + context.name + "_" + sig.name
						self.dependencies["endTime"] = ("Integer","Math.min(%s.nextTimeHasStuff(startTime),finalNode_endTime)" % qname)
						s = qname + ".hasStuff(endTime)"
						if dependencyString: dependencyString = " &amp;&amp; ".join([dependencyString,s])
						else: dependencyString = s
			if len(self.prevs[n].keys())>1 and not isinstance(n,MergeNode):
				rank = "None"
				deciderVarName = "myDeciderID_decider%s" % n.getID()
				try: rank = self.decisionDict[deciderVarName]
				except: gl.log("CAN'T FIND MY RANK FOR DECIDING %s" % self.getPrettyIdent(n))
				self.members[deciderVarName] = ("Integer",str(rank),"SETTING RANK FOR DECIDER")
				self.dependencies[deciderVarName] = ("Integer",str(rank))
				self.effects.append("decider" + n.getID()+".addIfNotContained(endTime,%s)" % deciderVarName)
				deciderString = "(decider%s.size(endTime) == decider%s.maxSize() &amp;&amp; decider%s.lastElement(endTime)==%s)" % (n.getID(),n.getID(),n.getID(),deciderVarName)
				if dependencyString: dependencyString = " &amp;&amp; ".join([dependencyString,deciderString])
				else: dependencyString = deciderString
			if not isinstance(n,ActivityFinalNode) and not isinstance(n,ActivityParameterNode):
				if dependencyString: dependencyString = " &amp;&amp; ".join([dependencyString,"((%s_exists == null || !%s_exists) || endTime + 2 &lt; finalNode_startTime)" % (self.finalID,self.finalID)])
				else: dependencyString = "((%s_exists == null || !%s_exists) || endTime + 2 &lt; finalNode_startTime)" % (self.finalID,self.finalID)
			if not dependencyString: dependencyString = "true"
			self.dependencies[n.getID() + "_exists"] = ("Boolean",str(dependencyString))
			existscon = n.getID()+"_exists"
			self.elaborations[n]={
						"args": [("startTime","endTime+2","Integer")],
						"conditions": {"exists":existscon},
						"enclosingClass" : self.enclosingClass}
			if isinstance(n,MergeNode):
				self.elaborations[n]["args"].append(("receiveThis","sig%s" % nFlow.getID(),"ObjectFlow"))
			
	def getObtypeName(self,f,node):					
		obtype = self.feet[f]
		obtypename = ""
		if isinstance(obtype,Signal):
			context = node.context
			try: 
				ct = constructorArgs[context]
				prepend=ct.name+"."
			except: prepend = ""
			obtypename = "%sSignal%s" % (prepend,obtype.name)
		elif obtype is "Control": obtypename = "Boolean"
		elif isinstance(obtype,Property): obtypename = obtype.type.name
		else: obtypename = obtype.name
		return(obtype,obtypename)
	
	def setUpSignalEffectsAndMembers(self,node):
		gl.log("SETTING UP SIGNALS FOR %s" % self.getPrettyIdent(node))
		for f in self.allSignals[node]["out"]:
			(obtype,obtypename) = self.getObtypeName(f,node)
			next = self.getNext(f)
			if isinstance(f.source,Pin):
				self.effects.append("sig" + f.getID() + ".send(%s,endTime)" % f.source.getID())
				self.members[f.source.getID()] = (obtypename,None,"NEW initialize output pin members")
			else:
				if isinstance(node,DecisionNode): self.effects.append("sig" + f.getID() + ".sendIf(%s,endTime,%s_exists)" % ("objectToPass",next.getID()))
				else: self.effects.append("sig" + f.getID() + ".send(%s,endTime)" % "objectToPass")
				if obtype is "Control":	self.dependencies["objectToPass"]=("Boolean","true")
				self.members["objectToPass"] = (obtypename,None,"NEW initialize objectToPass")
				if not obtype: gl.log("===> HAS NO FLOW TYPE")
		
		targetVar = "objectToPass"
		oTargetVar = "objectToPass"
		c=1
		for f in self.allSignals[node]["in"]:
			gl.log("	INPUT SIGNAL - need a receive")
			(obtype,obtypename) = self.getObtypeName(f,node)
			if isinstance(node,MergeNode): continue
			if isinstance(f.target,Pin):
				self.dependencies[f.target.getID()] = (obtypename,"sig" + f.getID() + ".receive(startTime)")
				self.members[f.target.getID()] = (obtypename,None,"NEW initialize input pin members")
			else:
				if isinstance(node,DecisionNode) and node.getDecisionInputFlow() is f: 
					targetVar = "decisionInput"
					self.members["decisionInput"] = (obtypename,None,"NEW Var for DecisionInputValue")
					gl.log("		-set up decision input member")
				else: 
					oTargetVar = oTargetVar + str(c)
					c+=1
				self.dependencies[targetVar] = (obtypename,"sig" + f.getID() + ".receive(startTime)")
				gl.log("		-set up receive dependency for %s" % targetVar)
				if not targetVar in self.members.keys(): self.members[targetVar] = (obtypename,None,"NEW - INTIALIZE TARGETVAR (if not there already)")
				targetVar = oTargetVar
		if isinstance(node,MergeNode):
			self.members["objectToPass"] = (obtypename,None,"ObjectToPass-mergenode")
			self.dependencies["objectToPass"] = (obtypename,"receiveThis.receive(startTime)")
			self.members["receiveThis"] = ("ObjectFlow",None,"anonymous object flow for merge nodes")
				
				
	
	def getPrettyIdent(self,node):
		return node.name + " (" + str(node.getClassType()).split(".")[-1].strip("'>") + ")"		
	
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
	#gl.clearLog()
	gl.log("===============================================================================")
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
	gl.log("Date: December 5")
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
		homeDir = os.getcwd()
	log_dir = homeDir
	if 'LADWP_LOGS' not in homeDir:
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
	latestDir = str(os.getcwd()) + os.sep + "latest"
	if not os.path.exists(latestDir):
		try: os.mkdir(latestDir)
		except: #this won't do much if you're running in batch mode...
			print "Error creating latest directory!"
	ffile = "Scenario_latest.xml"
	ff = JOptionPane.showInputDialog(
                                    None,
                                    "Output Filename",
                                    "What are we naming this scenario?",
                                    JOptionPane.PLAIN_MESSAGE,
                                    None,
                                    None,
                                    ffile)
	if ff and len(ff) > 0:
		if not ff.endswith(".xml"): ff+=".xml"
	
	generatedXmlFileName = latestDir + os.sep + ff
	shutil.copyfile(log_file_name, generatedXmlFileName)
	gl.log("\nfrom " + os.getcwd() + " copied " + log_file_name + " to " + generatedXmlFileName )
	
	
	#gl.log("\nERRORS:")
	#for thing in classesToTranslate:
	#	if len(thing.errors.keys())>0:
	#		gl.log("errors in %s" % thing.name)
	#		gl.log("	%s" % str(thing.errors))
			
	
	gl.log("\nThe log file for this execution is located at: " + str(os.getcwd()) )
	gl.log("The log file: " + generatedXmlFileName )
	return generatedXmlFileName

def logAndExport(tabNum,tag,text):
	line = "	"*tabNum
	if tag: line = line+"<%s>%s</%s>" % (tag,text,tag)
	else: line = line+text
	log_file.write(line+"\n")
	if logDebug: gl.log(line)

def writeScenario(top,classesToTranslate):
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
	#logAndExport(7,None,"<parameter>")
	#logAndExport(8,"name","caller")
	#logAndExport(8,"value","this")
	#logAndExport(7,None,"</parameter>")
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
	logAndExport(l+1,"import","gov.nasa.jpl.ae.event.TimeVaryingList")
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
				for name,exp in stuff["conditions"].items():
					#exp = str(next.getID()) + "_exists"
					logAndExport(l+2,None,"<!--%s condition %s-->" % (next.name,name))
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
	#logAndExport(l+2,None,"<dependencies/>")
	writeDependencies(classThingy,l+2)
	
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
