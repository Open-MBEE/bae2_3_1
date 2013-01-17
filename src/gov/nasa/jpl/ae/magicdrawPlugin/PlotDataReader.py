import re

import os
homeDir = os.getenv('HOME')
workspacePath = homeDir + os.sep + 'proj/ae/workspace'
mdPath = '/home/bclement/apps/MagicDraw/OpsRevMD1702-20120912'
pluginDirName = 'LADWP'
if 'mjackson' in homeDir: 
    workspacePath = homeDir + os.sep + 'Documents/workspace-Helios/'
    mdpath = '/Applications/OpsRevMD1702-20120818/'
    pluginDirName = 'magicdrawPlugin'


projectPath = workspacePath + os.sep + 'CS'
pluginSrcPath = projectPath + os.sep + 'src' + os.sep + 'gov' + os.sep + \
                'nasa' + os.sep + 'jpl' + os.sep + 'ae' + os.sep + 'magicdrawPlugin'
workspaceEventPath = projectPath + os.sep + 'src' + os.sep + 'gov' + os.sep + \
                'nasa' + os.sep + 'jpl' + os.sep + 'ae' + os.sep + 'event'

import sys

def addToPath(s):
    if not(s in sys.path):
        sys.path.append(s)

addToPath(workspaceEventPath)
addToPath(pluginSrcPath);
addToPath(projectPath + os.sep + 'bin');

import gov.nasa.jpl.ae.event.TimeVaryingPlottableMap as TimeVaryingPlottableMap

class PlotDataReader(object):
    '''
    reads plottable values from AE output
    '''


    def __init__(self, fileName):
        self.fileName = fileName
        self.read(fileName)
        self.data = {}
        
#    def __init__(self):
#        pass
        
    def read(self, fileName):
        self.fileName = fileName
        print("reading events from " + fileName)
        self.events = []
        try: f = open(fileName,"r")
        except:
            print("can't find file @ %s" % fileName)
            return
        readingExecution = False
        print( "PlotDataReader: before simulation, loading events from " + str(fileName) )
        for line in f.readlines():
            print("read line = " + line)
            if not readingExecution and str(line).startswith("execution:"):
                readingExecution = True;
                continue
            if not readingExecution:
                continue
            x = re.search("^plottable ([^ {]*) ",line)
            if x:
                category = x.groups()[0]
                tvm = TimeVaryingPlottableMap("")
                tvm.fromString()
                if category not in self.data.keys():
                    self.data[category] = []
                self.data[category].append(tvm)
                print( "PlotDataReader: found " + line )
            elif readingExecution and line.startsWith("^--- simulation start"):
                readingExecution = False
        print( "PlotDataReader: finished loading plottables from " + str(fileName) )

