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

gl = Application.getInstance().getGUILog()
global gl
project = Application.getInstance().getProjectsManager().getActiveProject()
global project
TVM = StereotypesHelper.getStereotype(project,"TimeVaryingMap")
global TVM

class ClassifierClass(object):
	def __init__(self,system,cs,firstSelected):
		stime = time.time()
		self.constructorArgs = cs
		self.firstSelected = firstSelected
		self.toInspect = []
		if isinstance(system,Signal): self.id = "Signal"+system.name
		self.errors = 		{}
		classType = str(system.getClassType()).split(".")[-1].strip("'>")
		self.identifier = "%s_%s_%s" % (system.name,classType,system.owner.name)
		self.name = 			system.name
		self.id = 				system.name #SO THAT WE GET READABLE CLASS NAMES
		self.events=			[]
		self.members=			{} #"name":("type","value")
		self.structuralSignals = {}
		self.queues = 			{}
		self.MDsignalClasses = 	[]
		self.methods = 			[]
		self.constructors=		[]
		self.cArgs=				{}
		
		self.inspectComposition(system)
				
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
			tvmargs = '"%s"' % p.name
			if StereotypesHelper.hasStereotype(p,TVM): tvmargs += ', "%s"' % StereotypesHelper.getStereotypePropertyValue(p,TVM,"filename")[0]
			self.members[propName] = ("TimeVarying%sMap&lt;%s&gt;" % (pl,p.type.name),'new TimeVarying%sMap(%s)' % (pl,tvmargs),"simple property (name " + p.name + ")")
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
			self.toInspect.append(p.type) #this will probably have to change when we have lots of customers... also will need multiplicity
			self.constructorArgs[p.type]=system
			
		if system in self.constructorArgs.keys():
			self.constructors.append("super();")
			arg = self.constructorArgs[system]
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
						ofName = "q_"+system.name+"_"+signal.name
						q_invocation = 'new ObjectFlow("%s",%s.Signal%s.class)' % (ofName,self.constructorArgs[system].name,signal.name) #portID_sigID, sigID.class
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
					targname = "q_"+targetQueueOwner.name+"_"+signal.name
					constructortext = "%s.addListener(%s);" % (name,targname)
					pwp = None
					for e in c.end:
						if e.partWithPort.type is targetQueueOwner: pwp = e.partWithPort
					if pwp: constructortext = "((ObjectFlow) %s.getValue()).addListener(((ObjectFlow) (%s.getValue()).%s.getValue()));" % (name,pwp.name,targname)
					self.constructors.append(constructortext)
						
			#build signals
			for s in signalsToBuild:
				self.MDsignalClasses.append(ClassifierClass(s,self.constructorArgs,self.firstSelected))
			gl.log("Signal Classes: " + str(self.MDsignalClasses))
			
			#fill events
			for act in system.ownedBehavior: 
				aeC = activityEventClass(act,self.constructorArgs,self.firstSelected)
				self.events.append(aeC) #will assume that you can't reference outside activities yet...
				if len(act.ownedParameter)>0:
					aIC = activityInterfaceClass(act)
					self.events.append(aIC)
				gl.log(str(aeC.errors.keys()))
				if len(aeC.errors.keys())>0: self.errors[act] = aeC.errors
			
			#run classifierbehavior
			if system.classifierBehavior:
				self.members["classifierBehavior"] = ("String", '"%s"' % system.classifierBehavior.getID(),"Classifier Behavior for " + system.name + " is " + system.classifierBehavior.name)
	
		
class activityInterfaceClass(object):
	def __init__(self,activity):
		self.id = 				str(activity.getID()) + "_interface"
		self.enclosingClass = 	self.id + ".this"
		self.name = 			activity.name + "_interface"
		self.members = 			{}
		self.elaborations = 	{}
		self.constraints = 		{}
		self.dependencies = 	{}
		self.classes = 			[]
		
		for p in activity.ownedParameter:
			if p.type: tname = p.type.name
			else: tname = "Object"
			self.members[p.getID()] = (tname,None,"Initialize Parameter Nodes")
		
#TODO - if you're a classifier behavior, have a queue. when you pop something off the queue, set it to has stuff (?)
class activityEventClass(object):
	def __init__(self,activity,conArgs,firstSelected):
		stime = time.time()
		gl.log("=====================================")
		gl.log("Activity: " + activity.name)
		
		self.constructorArgs = conArgs
		self.firstSelected = firstSelected
		classType = 		str(activity.getClassType()).split(".")[-1].strip("'>")
		self.identifier = 	"%s_%s_%s" % (activity,classType,activity.owner.name)
		self.id = 			str(activity.getID())
		self.name =			activity.name
		self.members = 		{} #{name: (type,value)}
		self.elaborations = {}
		self.constraints = 	{} #need to have, i think?
		self.dependencies = {} #are there any?
		self.classes = 		[]
		self.initial = 		None
		self.final = 		None
		self.enclosingClass = self.id + ".this"
		self.flowTypes = 	{}
		self.allSignals = 	{}
		self.nexts = 		{}
		self.prevs = 		{}
		self.ranks = 		{}
		self.errors = 		{}

		owner = activity.owner
		if activity is not owner.classifierBehavior or (activity is owner.classifierBehavior and owner is self.firstSelected):
			self.dependencies["caller.endTime"] = ("Integer","finalNode_endTime")
			interfaceText = "DurativeEvent"
			if len(activity.ownedParameter) > 0: interfaceText = activity.getID() + "_interface"
			self.members["caller"] = (interfaceText,None,"Initialize variable for cba that called this")
		
		self.inspectComposition(activity)
		gl.log("time to inspect activity: %s" % str(time.time() - stime))

	def translateEdgesToMembers(self,activity):
		for flow in activity.edge:		
			if EU.isObjectFlow(flow): type = EU.getObject(flow)
			else: type = "Control"
			self.flowTypes[flow] = type
			typeName = "Object"
			if type is not "Control": 
				if isinstance(type,Property) and type.type: typeName = type.type.name
				elif isinstance(type,Signal): typeName = "Signal"+type.name
				else: typeName = type.name
			else: typeName = "Boolean"
			k = str("sig" + flow.getID())
			t = "ObjectFlow&lt;%s&gt;" % typeName
			self.members[k] = (t,'new ObjectFlow("'+k+'")',"NEWWWWW member for object flow signal")
		
	def inspectComposition(self,activity):
		self.translateEdgesToMembers(activity)
		for node in activity.node:
			self.allSignals[node]={"out":[],"in":[]}
			self.nexts[node]={}
			self.prevs[node]={}
			try: 
				outpins = node.output
				for pin in outpins: self.allSignals[node]["out"].append(pin.outgoing[0])
			except: gl.log(EU.getPrettyIdent(node) + " has no output pins")
			#for f in node.outgoing: self.allSignals[node]["out"].append(f)
			self.allSignals[node]["out"].extend(node.outgoing)
			try:
				inpins = node.input
				for pin in inpins: self.allSignals[node]["in"].append(pin.incoming[0])
			except: gl.log(EU.getPrettyIdent(node) + "has no input pins")
			#for f in node.incoming: self.allSignals[node]["in"].append(f)
			self.allSignals[node]["in"].extend(node.incoming)
			
			for outFlow in self.allSignals[node]["out"]: self.nexts[node][EU.getNext(outFlow)]=outFlow
				
			thisRank = 1
			l = len(self.allSignals[node]["in"])
			for inFlow in self.allSignals[node]["in"]:
				prev = EU.getPrev(inFlow)
				self.prevs[node][prev]=inFlow
				if l>1 and not isinstance(node,MergeNode):
					decider = "myDeciderID_decider%s" % node.getID()
					if prev in self.ranks.keys(): 
						pd = self.ranks[prev]
						pd[decider] = thisRank
					else: self.ranks[prev] = {decider : thisRank}
				thisRank+=1
			
			l = len(self.allSignals[node]["in"])
			if l>1 and not isinstance(node,MergeNode):
				self.members["decider%s" % node.getID()] = ("TimeVaryingList&lt;Integer&gt;",'new TimeVaryingList("decider%s",%s)' % (node.getID(),str(l)),"INTIALIZE DECIDER FOR %s with %s elements" % (EU.getPrettyIdent(node),str(l)))
			
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
				self.members[node.getID() + "_exists"] = ("Boolean","false","Initialize existence of " + EU.getPrettyIdent(node) + " as false")
				self.members["finalNode_endTime"] = ("Integer",None,"variable for final node's end time!")
				self.members["finalNode_startTime"]=("Integer",None,"variable for final node's start time!")
				for inc in node.incoming:
					signame = "sig" + str(inc.getID())
					self.members[signame] = ("ObjectFlow&lt;Boolean&gt;",'new ObjectFlow("'+signame+'")',"member for FINAL NODE object flow")
				s = "finalNode_startTime != null &amp;&amp; sig" + str(inc.getID()) + ".hasStuff(finalNode_startTime - 1)" #WAS +1
				self.dependencies[node.getID() + "_exists"] = ("Boolean",s)
				self.dependencies["endTime"] = ("Integer","finalNode_endTime")
				self.elaborations[node] = {
										"args":[("startTime","finalNode_startTime","Integer")], #try not passing in the end time as a ref
										"conditions": {"exists":node.getID()+"_exists"},
										"enclosingClass" : self.enclosingClass}
			
			if isinstance(node,ActivityParameterNode):
				p_id = node.parameter.getID()
				if node.parameter.type: tname = node.parameter.type.name
				else: tname = "Object"
				try: d = str(node.getDefault())
				except: d = None
				self.members[node.getID()] = (node.type.name,d,"Initialize Activity Parameter Values (for transition through")
				if str(node.parameter.direction)=="out" or len(node.incoming)>0: 
					self.members[node.parameter.getID()+"_default"]=(tname,node.parameter.default,"Outgoing node's default value")
					self.members[node.parameter.getID()+"_changed"]=("Boolean","false","initialize new value as false")
					self.dependencies["caller.%s" % node.parameter.getID()] = (tname,node.parameter.getID())
					self.elaborations[node] = {
											"args":[("startTime","finalNode_startTime","Integer")],
											"enclosingClass" : self.enclosingClass}
				if len(node.outgoing)>0: self.elaborations[node] = {
																"args": [("startTime","startTime","Integer"),(p_id,p_id,tname)],
																"enclosingClass" : self.enclosingClass}
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
					  self.members[signame] = ("ObjectFlow&lt;%s&gt;" % pt,'new ObjectFlow("'+signame+'")', "object flow for return type activity parameter nodes") #used??
			
		for node in activity.node:	
			dicts = (self.flowTypes, self.allSignals, self.nexts, self.prevs)
			r = None
			if node in self.ranks.keys(): r = self.ranks[node]
			aeClass = actionEventClass(node,dicts,self.id,r,self.final.getID(),self.constructorArgs)
			self.classes.append(aeClass)
			if len(aeClass.errors.keys()) > 0:
				self.errors[node] = aeClass.errors
		
class actionEventClass(object):
	def __init__(self,actionNode,dicts,encloserID,rankDict,finalID,conArgs):
		stime = time.time()
		(self.feet, self.allSignals, self.nexts, self.prevs) = dicts
		self.enclosingClass = encloserID + ".this"
		self.decisionDict = rankDict #{myDeciderID_deciderX : rank}
		self.finalID = finalID
		self.constructorArgs = conArgs
		classType = str(actionNode.getClassType()).split(".")[-1].strip("'>")
		self.identifier = "%s_%s_%s" % (actionNode.name,classType,actionNode.owner.name)
		self.name = actionNode.name
		self.actionType = actionNode.humanType
		self.id = actionNode.getID()
		self.inheritsFrom = None
		self.interfaceClass = None
		self.members = {} #name : (type,value)
		self.constraints = {}
		self.dependencies = {}
		self.elaborations = {}
		self.effects = [] #expression
		self.errors = {}
		gl.log("-----------------------------------")
		gl.log("Action: %s : %s [%s]" % (actionNode.name, actionNode.humanType,actionNode.getID()))
		
		self.inspectMyself(actionNode)
		self.inspectByType(actionNode)
		
		gl.log("time to inspect action: %s" % str(time.time() - stime))
		
	def inspectMyself(self,actionNode):	
		self.setUpSignalEffectsAndMembers(actionNode)
		self.setUpBasicElaborationsAndDependencies(actionNode)

	def setUpSignalEffectsAndMembers(self,node):
		gl.log(". . . . . . .Signals. . . . . .")
		for f in self.allSignals[node]["out"]:
			(obtype,obtypename) = self.getObtypeName(f,node)
			next = EU.getNext(f)
			if isinstance(f.source,Pin):
				self.effects.append("sig" + f.getID() + ".send(%s,endTime)" % f.source.getID())
				self.members[f.source.getID()] = (obtypename,None,"NEW initialize output pin members")
			else:
				if isinstance(node,DecisionNode): self.effects.append("sig" + f.getID() + ".sendIf(%s,endTime,%s_exists)" % ("objectToPass",next.getID()))
				else: self.effects.append("sig" + f.getID() + ".send(%s,endTime)" % "objectToPass")
				if obtype is "Control":	self.dependencies["objectToPass"]=("Boolean","true")
				self.members["objectToPass"] = (obtypename,None,"NEW initialize objectToPass")
				if not obtype: gl.log("+ cannot find flow type for flow %s" % f.getID())
		targetVar = "objectToPass"
		oTargetVar = "objectToPass"
		c=1
		for f in self.allSignals[node]["in"]:
			(obtype,obtypename) = self.getObtypeName(f,node)
			if isinstance(node,MergeNode): continue
			if isinstance(f.target,Pin):
				self.dependencies[f.target.getID()] = (obtypename,"sig" + f.getID() + ".receive(startTime)")
				self.members[f.target.getID()] = (obtypename,None,"NEW initialize input pin members")
			else:
				if isinstance(node,DecisionNode) and node.getDecisionInputFlow() is f: 
					targetVar = "decisionInput"
					self.members["decisionInput"] = (obtypename,None,"NEW Var for DecisionInputValue")
				else: 
					oTargetVar = oTargetVar + str(c)
					c+=1
				self.dependencies[targetVar] = (obtypename,"sig" + f.getID() + ".receive(startTime)")
				if not targetVar in self.members.keys(): self.members[targetVar] = (obtypename,None,"NEW - INTIALIZE TARGETVAR (if not there already)")
				targetVar = oTargetVar
		if isinstance(node,MergeNode):
			self.members["objectToPass"] = (obtypename,None,"ObjectToPass-mergenode")
			self.dependencies["objectToPass"] = (obtypename,"receiveThis.receive(startTime)")
			self.members["receiveThis"] = ("ObjectFlow",None,"anonymous object flow for merge nodes")
		gl.log("finished setting up signal effects & members")
	
	def setUpBasicElaborationsAndDependencies(self,node):
		gl.log(". . . . . . .Elaborations & Dependencies. . . . . .")
		for n in self.nexts[node].keys():
			if isinstance(n,ActivityFinalNode): #TODO - activity parameter nodes?
				self.dependencies["finalNode_startTime"] = ("Integer","endTime+2")
				continue #TODO - put dependency stuff on the elaborator (the ACTIVITY)
			if isinstance(n,ActivityParameterNode):
				self.dependencies["%s_changed" % n.parameter.getID()] = ("Boolean","true")
				continue
			nFlow = self.nexts[node][n]
			dependencyString = False
			self.members[n.getID()+"_exists"] = ("Boolean","false","NEW - initialize nexts to false")
			if nFlow.guard:
				b = str(nFlow.guard.body[0]) #could be more than one
				b = b.replace("<","&lt;")
				b = b.replace(">","&gt;")
				if isinstance(node,DecisionNode): b = b.replace("ALH.getTokenValue()","decisionInput")
				dependencyString = str(b)
			for otherPrev in self.prevs[n].keys(): #take out "my" flow?? #uh, don't even use otherprev....
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
			if not isinstance(n,ActivityFinalNode) and not isinstance(n,ActivityParameterNode):
				if dependencyString: dependencyString = " &amp;&amp; ".join([dependencyString,"((%s_exists == null || !%s_exists) || endTime + 2 &lt; finalNode_startTime)" % (self.finalID,self.finalID)])
				else: dependencyString = "((%s_exists == null || !%s_exists) || endTime + 2 &lt; finalNode_startTime)" % (self.finalID,self.finalID)
			if len(self.prevs[n].keys())>1 and not isinstance(n,MergeNode):
				rank = "None"
				deciderVarName = "myDeciderID_decider%s" % n.getID()
				try: rank = self.decisionDict[deciderVarName]
				except: gl.log("+ can't find my rank for decider! %s" % EU.getPrettyIdent(n))
				self.members[deciderVarName] = ("Integer",str(rank),"SETTING RANK FOR DECIDER")
				self.dependencies[deciderVarName] = ("Integer",str(rank))
				deciderCondition = dependencyString if dependencyString else "true"
				self.members["addToDecider_%s" % n.getID()] = ("Boolean","false","initializing decider add")
				self.dependencies["addToDecider_%s" % n.getID()] = ("Boolean",deciderCondition)
				gl.log("decider condition: %s" % str(deciderCondition))
				self.effects.append("decider" + n.getID()+".addIfNotContained(endTime,%s,addToDecider_%s)" % (deciderVarName,n.getID()))
				deciderString = "(decider%s.size(endTime) == decider%s.maxSize() &amp;&amp; decider%s.lastElement(endTime)==%s)" % (n.getID(),n.getID(),n.getID(),deciderVarName)
				if dependencyString: dependencyString = " &amp;&amp; ".join([dependencyString,deciderString])
				else: dependencyString = deciderString
			if not dependencyString: dependencyString = "true"
			self.dependencies[n.getID() + "_exists"] = ("Boolean",str(dependencyString))
			existscon = n.getID()+"_exists"
			self.elaborations[n]={
						"args": [("startTime","endTime+2","Integer")],
						"conditions": {"exists":existscon},
						"enclosingClass" : self.enclosingClass}
			if isinstance(n,MergeNode):
				self.elaborations[n]["args"].append(("receiveThis","sig%s" % nFlow.getID(),"ObjectFlow"))	
			gl.log("finished setting up elaborations & dependencies")
			
	def inspectByType(self,node):
		gl.log(". . . . . . .Inspecting by Type. . . . . .")
		myType = node.humanType
		gl.log("type is %s" % myType)
		canHaveDuration = True
		nextAccepts = [x for x in self.nexts[node] if isinstance(x,AcceptEventAction)]
		if len(nextAccepts)>0:
			if any([not isinstance(x.trigger[0].event,TimeEvent) for x in nextAccepts]): canHaveDuration = False
		if isinstance(node,AcceptEventAction) and isinstance(node.trigger[0].event,TimeEvent): canHaveDuration = False
		if isinstance(node,CallBehaviorAction) and (not node.behavior or not node.behavior.humanType == "Opaque Behavior"): canHaveDuration = False
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
			if node.context in self.constructorArgs.keys(): prepend = self.constructorArgs[node.context].name + "."
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
					ct = self.constructorArgs[node.context]
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
				w = str(when.expr.value)
				if not w[-1] in ["s","m","h"]: raise
				tm = int(w[0:-1]) #should throw a type error if it's not an int...
				if w[-1] is not "s" : tm = EU.convertTime(w[-1],"s",tm)
				self.dependencies["duration"] = ("Integer", tm)
		
		elif myType =="Value Specification Action" :
			val = node.value
			tname = None
			if isinstance(val,LiteralBoolean): (v,tname) = (str(val.isValue()).lower(),"Boolean")
			elif isinstance(val,LiteralInteger): (v,tname) = (str(val.getValue()),"Integer")
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
			if not node.behavior: #blank action...
				pass
			elif node.behavior.humanType == "Opaque Behavior":
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
				interfaceText = ""
				if len(behav.ownedParameter)>0:
					self.inheritsFrom = str(node.behavior.owner.name + "." + node.behavior.getID()+"_interface")
					interfaceText = node.behavior.getID() + "_interface"
				gl.log("INHERITSFROM : %s" % str(self.inheritsFrom))
				self.elaborations[node.behavior]={
												"args": [("startTime","startTime","Integer"),("caller","this",interfaceText)],
												"enclosingClass" : node.behavior.owner.name+".this"}
				for pin in node.input:
					if pin.type: tname = pin.type.name
					else: tname = "Object"
					if pin.parameter:
						self.elaborations[node.behavior]["args"].extend([(pin.parameter.getID(),pin.parameter.getID(),tname)])
						self.dependencies[pin.parameter.getID()] = (pin.parameter.type.name,pin.getID())
				for pin in node.output:
					if pin.type: tname = pin.type.name
					else: tname = "Object"
					self.elaborations[node.behavior]["args"].extend([(pin.parameter.getID(),pin.parameter.getID(),pin.parameter.type.name)])
					self.dependencies[pin.getID()] = (pin.parameter.type.name,pin.parameter.getID())

		elif myType =="Start Object Behavior Action":
			inc = node.object.incoming[0]
			flowThing = self.feet[inc]
			if flowThing: 
				if isinstance(flowThing,Element):
					gl.log("ELABORATION= " + str(flowThing) + flowThing.name + node.name)
					flowClass=flowThing
					if isinstance(flowThing,Property): flowClass = flowThing.type
					self.elaborations[flowClass.classifierBehavior] = {
																	"args" : [("startTime","startTime","Integer")],
																	"enclosingClass" : flowThing.name}
				else: gl.log("*****ERROR -- CONTROL FLOW INTO START OBJECT BEHAVIOR OBJECT PIN!!")
			else: gl.log("*****ERROR -- CANNOT ELABORATE TO NONE FROM START OBJECT BEHAVIOR!!")

		elif myType =="Activity Parameter Node":
			if str(node.parameter.direction)=="in": 
				self.dependencies["objectToPass"] = (node.parameter.type.name,node.parameter.getID())
				self.members[node.parameter.getID()] = (node.parameter.type.name, None, "Initialize Activity Parameter Node receptacle for incoming value!")
			else: 
				self.members["startTimeMinusOne"] = ("Integer",None,"placeholder for a start time minus one function")
				self.dependencies["startTimeMinusOne"] = ("Integer","startTime-1")
				self.dependencies[node.parameter.getID()]=(node.parameter.type.name,"objectToPass")
				self.members["sendDefault"] = ("Boolean","false","initialize whether to send default return value on object flow as false")
				self.dependencies["sendDefault"] = ("Boolean","%s_changed == null || %s_changed == false" % (node.parameter.getID(),node.parameter.getID()))
				self.effects.append("sig%s.sendIf(%s_default,startTimeMinusOne,sendDefault)" % (node.incoming[0].getID(),node.parameter.getID()))
		
		elif myType =="Decision Node":
			dif = node.decisionInputFlow
			if dif:
				try: tname = self.feet[dif].name
				except:tname = "Object"
				for dName,(dType,dVal) in self.dependencies.items():
					if dName.endswith("_exists"): self.dependencies[dName] = (dType,dVal.replace("ALH.getTokenValue()","decisionInput"))
					
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
		gl.log("finished inspecting by type.")
	
	def getObtypeName(self,f,node):					
		obtype = self.feet[f]
		obtypename = ""
		if isinstance(obtype,Signal):
			context = node.context
			try: 
				ct = self.constructorArgs[context]
				prepend=ct.name+"."
			except: prepend = ""
			obtypename = "%sSignal%s" % (prepend,obtype.name)
		elif obtype is "Control": obtypename = "Boolean"
		elif isinstance(obtype,Property): obtypename = obtype.type.name
		else: obtypename = obtype.name
		return(obtype,obtypename)