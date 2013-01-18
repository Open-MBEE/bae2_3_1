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

import sys

def addToPath(s):
    if not(s in sys.path):
        sys.path.append(s)

# access to AE Java 
addToPath(projectPath + os.sep + 'bin');

import gov.nasa.jpl.ae.event.TimeVaryingPlottableMap as TimeVaryingPlottableMap

class PlotDataReader(object):
    '''
    reads plottable values from AE output
    '''

    def __init__(self, fileName):
        self.fileName = fileName
        self.data = {}
        self.read(fileName)
        
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
#            x = re.search("^([A-Za-z0-9_-]*=)?plottable ([^ {]*) ",line)
            x = re.search("^plottable ([^ {]*) ",line)
            if x:
                category = x.groups()[0]
                tvm = TimeVaryingPlottableMap("")
                tvm.fromString(line, None)
                if category not in self.data.keys():
                    self.data[category] = []
                self.data[category].append(tvm)
                print( "PlotDataReader: found " + line )
            elif readingExecution and line.startswith("^--- simulation start"):
                readingExecution = False
        print( "PlotDataReader: finished loading plottables from " + str(fileName) )

    @staticmethod
    def parseMapWith(s, prefix1, delimiter1, suffix1, prefix2, delimiter2, suffix2):
        zmap = {}
        outerPatternStrings = [prefix1, delimiter1, suffix1]
        innerPatternStrings = [prefix2, delimiter2, suffix2]
        outerPatterns = [re.compile(ps) for ps in outerPatternStrings]
        innerPatterns = [re.compile(ps) for ps in innerPatternStrings]
        
        m = outerPatterns[0].search(s)
        if m == None:
            return
        pos = m.end()

        gotDelimiter = True
        while gotDelimiter:
            #find key-value prefix
            m = innerPatterns[0].search(s, pos)
            if m == None:
                break
            pos = m.end()

            print("\nkvp match = " + m.group() );
            print("matcher.start() = " + str(m.start()));
            print("matcher.end() = " + str(m.end()));
            print("\npos = " + str(pos));

            #find key-value delimiter
            m = innerPatterns[1].search(s, pos)
            if m == None:
                break
            #get the key before the delimiter
            key = s[pos, m.start()]
            pos = m.end()
            
            print("\nkvd match = " + m.group() );
            print("matcher.start() = " + str(m.start()));
            print("matcher.end() = " + str(m.end()));
            print("key = " + key)
            print("\npos = " + str(pos));

            #get the value between the key-value delimiter and the key-value suffix
            m = innerPatterns[2].search(s, pos)
            if m == None:
                break
            #get the key before the delimiter
            value = s[pos, m.start()]
            pos = m.end()
            
            print("\nkvs match = " + m.group() );
            print("matcher.start() = " + str(m.start()));
            print("matcher.end() = " + str(m.end()));
            print("value = " + value)
            print("\npos = " + str(pos));

            #add the key to the map
            zmap[key] = value
            
            #skip over the delimiter
            m = outerPatterns[1].search(s, pos)
            gotDelimiter = (m == None)
            if gotDelimiter:
                pos = m.end()

                print("\nd match = " + m.group() );
                print("matcher.start() = " + str(m.start()));
                print("matcher.end() = " + str(m.end()));
                print("\npos = " + str(pos));

        print("parsed map = " + str(zmap))
        return zmap
        
    @staticmethod
    def parseMap(s):
        prefix1, delimiter1, suffix1 = "[\\[{(]\\s*", ",\\s*", "\\s*[\\]})]"
        prefix2, delimiter2, suffix2 = "[\\[{(]\\s*", "\\s*=\\s*", "\\s*[\\]})]"
        return PlotDataReader.parseMapWith(s, prefix1, delimiter1, suffix1, prefix2, delimiter2, suffix2)
    
    def reread(self, fileName):
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
#            x = re.search("^([A-Za-z0-9_-]*=)?plottable ([^ {]*) ",line)
            x = re.search("^plottable ([^ {]*) ",line)
            if x:
                category = x.groups()[0]
                tvm = TimeVaryingPlottableMap("")
                tvm.fromString(line, None)
                if category not in self.data.keys():
                    self.data[category] = []
                self.data[category].append(tvm)
                print( "PlotDataReader: found " + line )
            elif readingExecution and line.startswith("^--- simulation start"):
                readingExecution = False
        print( "PlotDataReader: finished loading plottables from " + str(fileName) )

#
# Main test
#
def main( argv ):
    testFileName = projectPath + os.sep + "simulationSnapshot.example.txt"
    pr = PlotDataReader(testFileName)
    print("found " + str(pr.data))
    return

if __name__ == "__main__":
    main(sys.argv)


