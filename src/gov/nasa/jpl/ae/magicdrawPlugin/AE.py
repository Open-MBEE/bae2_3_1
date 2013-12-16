'''
Created on Sep 5, 2012

'''
import os
workspacePath = 'C:\\Users\\bclement\\workspaceFresh'
#mdPath = 'C:\\Program Files\\MagicDraw\\IMCE-GENPROF-17.0sp5-build99-20120615'
mdPath = 'C:\\Program Files\\MagicDraw\\OpsRevMD1702-20120912'
projectPath = workspacePath + os.sep + 'bae'
pluginSrcPath = projectPath + os.sep + 'src' + os.sep + 'gov' + os.sep + \
                'nasa' + os.sep + 'jpl' + os.sep + 'ae' + os.sep + 'magicdrawPlugin'
workspaceXmlPath = projectPath + os.sep + 'src' + os.sep + 'gov' + os.sep + \
                'nasa' + os.sep + 'jpl' + os.sep + 'ae' + os.sep + 'xml'
jpythonPath = mdPath + os.sep + 'plugins' + os.sep + \
             'com.nomagic.magicdraw.jpython'
jythonPath = jpythonPath + os.sep + "jython2.5.1"
aePluginDir = jpythonPath + os.sep + 'scripts' + os.sep + 'LADWP'
aeJar = projectPath + os.sep + 'AE.jar'
jythonJar = jythonPath + os.sep + 'jython.jar'
jpythonApiJar = jpythonPath + os.sep + 'jpython_api.jar'

from com.nomagic.magicdraw.core import Application

global gl
#if gl == None:
gl = Application.getInstance().getGUILog()
    

import sys


def addToPath(s):
    if not(s in sys.path):
        sys.path.append(s)

addToPath(aePluginDir)
addToPath(pluginSrcPath);
addToPath(projectPath + os.sep + 'bin');
addToPath(aeJar);

#from java.net import ClassLoader
from java.lang import ClassLoader
from java.net import URLClassLoader
from java.net import URL
from java.io import File
from gov.nasa.jpl.ae.xml import EventXmlToJava
reload(EventXmlToJava)
from gov.nasa.jpl.ae.tests import TestEventXmlToJava
reload(TestEventXmlToJava)
#aeJarFile = File(aeJar)
#jythonJarFile = File(jythonJar)
#jpythonJarFile = File(jpythonApiJar)
#gl.log("aeJarFile: " + aeJarFile.toString() + " exists = " + str(aeJarFile.exists()))
#myJars = [ aeJarFile.toURL(), jythonJarFile.toURL(), jpythonJarFile.toURL() ] 
#myclassloader = URLClassLoader(myJars, ClassLoader.getSystemClassLoader())
#classNames1 = EventXmlToJava.getClassNamesFromJARFile(aeJar, 'gov')
#classNames = classNames1
#try:
#    classNames2 = EventXmlToJava.getClassNamesFromJARFile(jythonJar, 'org')
#    if classNames2 != None:
#        classNames.addAll(classNames2)
#    classNames3 = EventXmlToJava.getClassNamesFromJARFile(jpythonApiJar, 'org')
#    if classNames3 != None:
#        classNames.addAll(classNames3)
#except:
#    gl.log("couldn't get other classes from jars")
#gl.log(str(classNames))
#for n in classNames:
#    gl.log("loading " + str(n))
#    try:
#        myclassloader.loadClass(n)
#    except:
#        gl.log(str(n) + " failed to load")
#reload(EventXmlToJava)
#reload(TestEventXmlToJava)
#myclassloader = gov.nasa.jpl.ae.tests.TestEventXmlToJava.getClassLoader()
from gov.nasa.jpl.ae.xml import EventXmlToJava
reload(EventXmlToJava)
from gov.nasa.jpl.ae.event import DurativeEvent
reload(DurativeEvent)
from gov.nasa.jpl.ae.event import EventSimulation
reload(EventSimulation)
##TestEventXmlToJava as TestEventXmlToJava
#from generated import TestEventXmlToJava as TestEventXmlToJava
from gov.nasa.jpl.ae.util import CompareUtils
reload(CompareUtils)

#for testing jython link to java
from java.util import TreeSet as TreeSet

from threading import Thread

class AE:#(Thread):
    
    #xmlFileName = workspaceXmlPath + os.sep + 'Scenario_XML_2012-291T17.49.09.xml'
    #packageName = 'Scenario_medium_2012'
    xmlFileName = workspaceXmlPath + os.sep + 'Scenario_XML_2012-291T17.48.51.xml'
    packageName = 'Scenario_large_2012'
    generatedXmlFileName = 'unspecified'
    timeScale = 1.0e15
    
    # Options for what to run
#    sysMlToAeXml = True
#    xmlToJava = True
#    compileAndLoad = True
#    execute = True
#    simulate = True
#    animate = True
    options = { 'sysMlToAeXml':True, 'xmlToJava':True, 'writeJavaFiles':True,
                'compile':True, 'load':True, 'execute':True, 'simulate':True,
                'animate':True }
#    options = { 'sysMlToAeXml':False, 'xmlToJava':False, 'writeJavaFiles':False,
#                'compile':False, 'load':True, 'execute':True, 'simulate':True,
#                'animate':True }
    
    # other members
    translator = None
    translatorTester = None
    mainEvent = None
    simulator = None
    magicDrawExecutor = None
    system = None # the selected SysML system/component to be processed
    
    def __init__(self, system):#, anim):#, sim):
        '''
        Constructor
        '''
        self.system = system

    def runSysMlToAeXml(self):
        import ExportForAnalysisEngine_v2
        reload(ExportForAnalysisEngine_v2)
        ExportForAnalysisEngine_v2.run(self.system)
        self.generatedXmlFileName = ExportForAnalysisEngine_v2.generatedXmlFileName
    
    def simulate( self ):
        if self.simulator != None:
            self.simulator.simulate( self.timeScale )
        
    def run(self):
        print "AE run() start"
        testJava()
        print "AE run() start2"
        gl.log( "running AE.py with options: " + str(self.options) )
        if self.options['sysMlToAeXml']:
            gl.log("translating sysML to XML")
            self.runSysMlToAeXml()
            gl.log("translated sysML to " + self.generatedXmlFileName)
        print "AE about to build tester"
        if self.options['xmlToJava']:
            self.xmlFileName = self.generatedXmlFileName
            gl.log("translating " + str(self.xmlFileName) + " to Java in package " + str(self.packageName) )
        self.translatorTester = TestEventXmlToJava( self.xmlFileName, self.packageName, self.options['xmlToJava'] )
        print "AE built tester"
        #self.translatorTester = TestEventXmlToJava( self.xmlFileName, self.packageName )
        if self.options['writeJavaFiles']:
            gl.log("writing Java")
            self.translatorTester.writeFiles()
        self.translator = self.translatorTester.translator
        if self.options['compile']:
            if self.translator == None:
                print >>sys.stderr, "Trying to compile, but there's no translator to provide it!"
            else:
                gl.log("compiling Java")
                os.chdir(projectPath)
                self.translator.compile( projectPath )
        if self.options['load']:
            if self.translator == None:
                print >>sys.stderr, "Trying to load Java, but there's no translator to provide it!"
            else:
                gl.log("loading Java")
                os.chdir(projectPath)
                self.translator.load( projectPath )
        if self.options['execute']:
            if self.translator == None:
                print >>sys.stderr, "Trying to execute event, but there's no translator to provide it!"
            else:
                gl.log("executing Java")
                self.mainEvent = self.translator.generateExecution()
        if self.options['simulate']:
            if self.mainEvent == None:
                print >>sys.stderr, "Trying to simulate with no event to simulate!"
            else:
                gl.log("simulating")
                self.simulator = self.mainEvent.createEventSimulation()
                if self.simulator == None:
                    #gl.log("")
                    print >>sys.stderr, "Trying to simulate with no simulator!"
                else:
                    if self.options['animate']:
                        gl.log("and animating")
                        import MagicDrawExecutor
                        reload(MagicDrawExecutor)
                        from MagicDrawExecutor import MagicDrawExecutor
                        self.magicDrawExecutor = MagicDrawExecutor()
                        self.simulator.add( self.magicDrawExecutor )
                    t = Thread(target=self.simulate, args=None)
                    t.start()
        print "PYTHONPATH = " + str(os.getenv("PYTHONPATH"))
        print "AE run() finished!"

def run(system):
    ae = AE(system)
    gl.log("constructed AE")
    #ae.options['sysMlToAeXml'] = True;
    #ae.options['writeJavaFiles'] = True;
    ae.options['animate'] = True;
    t = Thread(target=ae.run, args=None)
    t.start()
    ##ae.run()
    #ae.start()

def testJava():
    print "Hello world!"
    foo = TreeSet()
    hello = 'hello'
    foo.add(hello)
    foo.add("world")
    print foo
    print CompareUtils.compare(3, 4)
    print "Goodbye world!"
    print "sys.path = " + str(sys.path)

import sys
import getopt

class Usage(Exception):
    def __init__(self, msg):
        self.msg = msg

def processArg(arg, ae):
    argval = arg.split('=')
    val = argval[1].lower in ('1','t','true','y','yes')
    if argval[0] in ae.options:
        ae.options[argval[0]] = val
#    if arg == 'sysMlToAeXml':
#        sysMlToAeXml = val

def processArgs( argv, ae ):
    try:
        if ae == None:
            raise Usage('AE was not constructed!')
        try:
            opts, args = getopt.getopt( argv[1:], "h", 
                                        [ "help", "sysMlToAeXml=","xmlToJava=",
                                          "compileAndLoad=", "execute=",
                                          "simulate=", "animate=" ] )
            print "opts=" + str(opts) # WHY IS THIS ALWAYS EMPTY?!
            print "args=" + str(args)
        except getopt.error, msg:
            raise Usage(msg)
        # process options
        for o, a in opts:
            if o in ("-h", "--help"):
                print __doc__
                sys.exit(0)
        # process arguments
        for arg in args:
            processArg(arg, ae) # process() is defined elsewhere
    except Usage, err:
        print >>sys.stderr, err.msg
        print >>sys.stderr, "for help use --help"
        return 2
    return 0
    

def main2():
    testJava()
    ae = AE()
    processArgs(ae)
    ae.run()
    # parse command line options
    try:
        opts, args = getopt.getopt(sys.argv[1:], "h", ["help"])
    except getopt.error, msg:
        print msg
        print "for help use --help"
        sys.exit(2)
    # process options
    for o, a in opts:
        if o in ("-h", "--help"):
            print __doc__
            sys.exit(0)
    # process arguments
    for arg in args:
        processArg(arg, ae) # process() is defined elsewhere


def main(argv=None):
    testJava()
    ae = AE()
    if argv is None:
        argv = sys.argv
    retVal = processArgs(argv, ae)
    if retVal == 0:
        ae.run()
    return retVal

if __name__ == "__main__":
    sys.exit(main())
