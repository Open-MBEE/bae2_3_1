'''
Created on Sep 5, 2012

'''

from gov.nasa.jpl.ae.tests import TestEventXmlToJava
from gov.nasa.jpl.ae.xml import EventXmlToJava
##TestEventXmlToJava as TestEventXmlToJava
#from generated import TestEventXmlToJava as TestEventXmlToJava
from gov.nasa.jpl.ae.util import Utils
from java.util import TreeSet as TreeSet
import ExportForAnalysisEngine_v2
import os
import sys

xmlFileName = 'exampleDRScenario.xml'
#projectDir = 'c:\\Users\\bclement\\workspace\\CS'
workspaceDir = os.path.join('c:\\Users','bclement','workspace')
projectDir = os.path.join(workspaceDir,'CS')
srcDir = os.path.join(projectDir, 'src')
binDir = os.path.join(projectDir, 'bin')

def runSysMlToAeXml():
    ExportForAnalysisEngine_v2.run(None)
    xmlFileName = ExportForAnalysisEngine_v2.generatedXmlFileName

def run():
    print "AE run()"
    #runSysMlToAeXml()
    #print TestEventXmlToJava.main([xmlFileName])
    textj = TestEventXmlToJava( xmlFileName )
    textj.writeFiles()
    os.chdir(projectDir)
    packageName = textj.translator.getPackageName()
    packagePath = packageName.replace(".", os.sep)
    textj.translator.compileLoadAndRun( projectDir )
#    textj.translator.compileJavaFiles(os.path.join(srcDir, packagePath))
#    textj.translator.loadClasses(os.path.join(binDir, packagePath), packageName)
#    mainModule = __import__(packageName + ".Main")
#    scenario = mainModule.Main()
#    scenario.simulate()

def testJava():
    print "Hello world!"
    foo = TreeSet()
    foo.add("hello")
    foo.add("world")
    print foo
    print Utils.intCompare(3, 4)
    print "Goodbye world!"

if __name__ == '__main__':
    testJava()
    run()