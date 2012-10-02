'''
Created on Sep 5, 2012

'''
import os
workspacePath = 'C:\\Users\\bclement\\workspace'
mdPath = 'C:\\Program Files\\MagicDraw\\IMCE-GENPROF-17.0sp5-build99-20120615'
projectPath = workspacePath + os.sep + 'CS'
pluginSrcPath = projectPath + os.sep + 'src' + os.sep + 'gov' + os.sep + \
                'nasa' + os.sep + 'jpl' + os.sep + 'ae' + os.sep + 'magicdrawPlugin'
aePluginDir = mdPath + os.sep + 'plugins' + os.sep + \
             'com.nomagic.magicdraw.jpython' + os.sep + 'scripts' + os.sep + 'LADWP'
import sys
sys.path.append(aePluginDir)
sys.path.append(pluginSrcPath);
sys.path.append(projectPath + os.sep + 'bin');
sys.path.append(projectPath + os.sep + 'AE.jar');

from gov.nasa.jpl.ae.tests import TestEventXmlToJava
from gov.nasa.jpl.ae.xml import EventXmlToJava
from gov.nasa.jpl.ae.event import DurativeEvent
from gov.nasa.jpl.ae.event import EventSimulation
##TestEventXmlToJava as TestEventXmlToJava
#from generated import TestEventXmlToJava as TestEventXmlToJava
from gov.nasa.jpl.ae.util import Utils

#for testing jython link to java
from java.util import TreeSet as TreeSet

from threading import Thread

#global gl
#gl = Application.getInstance().getGUILog()
    
class AE:
    
    xmlFileName = 'Scenario_latest.xml'
    timeScale = 1e12
    
    # Options for what to run
#    sysMlToAeXml = True
#    xmlToJava = True
#    compileAndLoad = True
#    execute = True
#    simulate = True
#    animate = True
    options = { 'sysMlToAeXml':False, 'xmlToJava':True, 'writeJavaFiles':False,
                'compileAndLoad':True, 'execute':True, 'simulate':True,
                'animate':False }
    
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
        self.xmlFileName = ExportForAnalysisEngine_v2.generatedXmlFileName
    
    def simulate( self ):
        if self.simulator != None:
            self.simulator.simulate( self.timeScale )
        
    def run(self):
        print "AE run() start"
        if self.options['sysMlToAeXml']:
            self.runSysMlToAeXml()
        if self.options['xmlToJava']:
            self.translatorTester = TestEventXmlToJava( self.xmlFileName )
            if self.options['writeJavaFiles']:
                self.translatorTester.writeFiles()
            self.translator = self.translatorTester.translator
        if self.options['compileAndLoad']:
            if self.translator == None:
                print >>sys.stderr, "Trying to compile and load Java, but there's no translator to provide it!"
            else:
                os.chdir(projectPath)
                self.translator.compileAndLoad( projectPath )
        if self.options['execute']:
            if self.translator == None:
                print >>sys.stderr, "Trying to execute event, but there's no translator to provide it!"
            else:
                self.mainEvent = self.translator.generateExecution()
        if self.options['simulate']:
            if self.mainEvent == None:
                print >>sys.stderr, "Trying to simulate with no event to simulate!"
            else:
                self.simulator = self.mainEvent.createEventSimulation()
                if self.simulator == None:
                    #gl.log("")
                    print >>sys.stderr, "Trying to simulate with no simulator!"
                else:
                    if self.options['animate']:
                        from MagicDrawExecutor import MagicDrawExecutor
                        reload(MagicDrawExecutor)
                        self.magicDrawExecutor = MagicDrawExecutor()
                        self.simulator.add( self.magicDrawExecutor )
                    t = Thread(target=self.simulate, args=None)
                    t.start()
        print "PYTHONPATH = " + str(os.getenv("PYTHONPATH"))
        print "AE run() finished!"

def run(system):
    ae = AE(system)
    #ae.options['sysMlToAeXml'] = True;
    #ae.options['writeJavaFiles'] = True;
    ae.options['animate'] = True;
    ae.run()

def testJava():
    print "Hello world!"
    foo = TreeSet()
    foo.add("hello")
    foo.add("world")
    print foo
    print Utils.intCompare(3, 4)
    print "Goodbye world!"

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
