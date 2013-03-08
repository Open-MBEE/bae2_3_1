import re
import InterpolatedMap
#import SortedDict
import os

liveUpdating = False
debug = False
applicationsDir = "C:\Program Files" 
homeDir = os.getenv('HOME')
if homeDir == None:
    homeDir = "C:\Users\bclement"
workspacePath = homeDir + os.sep + 'proj' + os.sep + 'ae' + os.sep + 'workspace'
if not os.path.isdir(workspacePath):
    workspacePath = homeDir + os.sep + 'workspace'
    #workspacePath = homeDir + os.sep + 'proj/ae/workspace'
mdPath = homeDir + os.sep + 'apps' + os.sep + 'MagicDraw' + os.sep + 'OpsRevMD1702-20120912'#"C:\Program Files\MagicDraw\OpsRevMD1702-20120912\bin\mduml.exe"
if not os.path.isdir(mdPath):
    mdPath = applicationsDir + 'MagicDraw' + os.sep + 'OpsRevMD1702-20120912'

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

def debugPrint(s, outputFile=sys.stdout):
    if debug:
        outputFile.write(s + '\n')


class PlotDataReader(object):
    '''
    reads plottable values from AE output
    '''

    def __init__(self, fileName, liveUpdatingI):
        self.fileName = fileName
        self.data = {}
        global liveUpdating
        liveUpdating = liveUpdatingI
        self.read(fileName)
        
#    def __init__(self):
#        pass
        
    def read(self, fileName):
        self.fileName = fileName
        debugPrint("reading events from " + fileName)
        self.events = []
        try: f = open(fileName,"r")
        except:
            print("can't find file @ %s" % fileName)
            return
        readingExecution = False
        debugPrint( "PlotDataReader: before simulation, loading events from " + str(fileName) )
        for line in f.readlines():
            debugPrint("read line = " + line)
            if not readingExecution and str(line).startswith("execution:"):
                readingExecution = True;
                continue
            if not readingExecution:
                continue
            
            (category,name,m) = PlotDataReader.parseMapLine(line)
            if m:
                if category not in self.data.keys(): self.data[category] = {}
                else:
                    originalName = str(name)
                    ctr = 0
                    while name in self.data[category].keys():
                        name = originalName + str(ctr)
                        ctr = ctr + 1
#                self.data[category].append(tvm)
                self.data[category][name] = m
                debugPrint( "PlotDataReader: found " + name + " " + category + " " + str(m) )
                for p in m:
                    debugPrint(str(p))
            elif readingExecution and line.startswith("^--- simulation start"):
                readingExecution = False
        print( "PlotDataReader: finished loading plottables from " + str(fileName) )

    @staticmethod
   # def parseMapWith(s, prefix0, delimiter0, suffix0, prefix1, delimiter1, suffix1, prefix2, delimiter2, suffix2):
    def parseMapWith(s, prefix1, delimiter1, suffix1, prefix2, delimiter2, suffix2):
        zmap = InterpolatedMap.InterpolatedMap()
        #zmap = SortedDict.SortedDict()
        #outerOuterPatternStrings = [prefix0, delimiter0, suffix0]
        outerPatternStrings = [prefix1, delimiter1, suffix1]
        innerPatternStrings = [prefix2, delimiter2, suffix2]
        #outerOuterPatterns = [re.compile(ps) for ps in outerOuterPatternStrings]
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

            debugPrint("\nkvp match = " + m.group() );
            debugPrint("matcher.start() = " + str(m.start()));
            debugPrint("matcher.end() = " + str(m.end()));
            debugPrint("\npos = " + str(pos));

            #find key-value delimiter
            m = innerPatterns[1].search(s, pos)
            if m == None:
                break
            #get the key before the delimiter
            st = m.start()
            key = s[pos:st]
            pos = m.end()
            
            debugPrint("\nkvd match = " + m.group() );
            debugPrint("matcher.start() = " + str(m.start()));
            debugPrint("matcher.end() = " + str(m.end()));
            debugPrint("key = " + key)
            debugPrint("\npos = " + str(pos));

            #get the value between the key-value delimiter and the key-value suffix
            m = innerPatterns[2].search(s, pos)
            if m == None:
                break
            #get the key before the delimiter
            value = s[pos:m.start()]
            pos = m.end()
            
            debugPrint("\nkvs match = " + m.group() );
            debugPrint("matcher.start() = " + str(m.start()));
            debugPrint("matcher.end() = " + str(m.end()));
            debugPrint("value = " + value)
            debugPrint("\npos = " + str(pos));

            #add the key to the map
            try:
                zmap[int(key)] = float(value)
            except:
                try:
                    (name,category,innerMap) = PlotDataReader.parseMapLine(value,True)
                    if liveUpdating: zmap[int(key)] = innerMap
                    else: zmap = innerMap
                except: PlotDataReader.printStackTrace("Error! Failed to add key-value to plot: (" + str(key) + "=" + str(value) + ")")
            
            #skip over the delimiter
            m = outerPatterns[1].search(s, pos)
            gotDelimiter = (m != None)
            if gotDelimiter:
                pos = m.end()

                debugPrint("\nd match = " + m.group() );
                debugPrint("matcher.start() = " + str(m.start()));
                debugPrint("matcher.end() = " + str(m.end()));
                debugPrint("\npos = " + str(pos));

        #zmap.sorted()
        debugPrint("parsed map = " + str(zmap))
        return zmap
    
    @staticmethod
    def parseMapLine(line,plotOptional=False):
    #   x = re.search("^([A-Za-z0-9_-]*=)?plottable ([^ {]*) ",line)
        x = re.search("^(plottable )?(projected )?([^ {]*)( STEP|RAMP|LINEAR)?( [^ {]*)?(.*)",line)
        #else: x = re.search("^plottable( projected)? ([^ {]*)( STEP|RAMP|LINEAR)?( [^ {]*)?(.*)",line)
        category = name = m = None
        if x:
            debugPrint("x.groups()=" + str(x.groups()))
            plottable = x.groups()[0]
            if plottable != None: plottable = plottable.strip()
            else: 
                if not plotOptional: return (None,None,None)
            projected = x.groups()[1]
            if projected != None: projected = projected.strip()
            category = x.groups()[2]
            if "__" in category: category = category.split("__")[-1]
            if re.search("\d{5}",category): category = category[0:len(category)-6]
            interpolationType = x.groups()[3]
            name = x.groups()[4]
            if name != None:
                name = name.strip()
            if (name == None or len(name) == 0) and interpolationType not in ["STEP", "RAMP", "LINEAR"]:
                name = interpolationType
                interpolationType = "STEP"
            mapString = x.groups()[5]
            m = PlotDataReader.parseMap(mapString)
            m.interpolationType = interpolationType
            m.attributes["projected"] = (str(projected).lower() == "projected")
        return (category,name,m)
    
     
    @staticmethod
    def parseMap(s):
        #p = True if s.split("{")[0].contains("projected") else False
        #p = re.search("{[^{}]*{",s) #if p, it's a projection
        #numRegEx = "\d+(?:\.\d*)?"
        identifierRegEx = "[A-Za-z_]\\w*"
        valuePrefix = "(?:" + identifierRegEx + "(?::(?:" + identifierRegEx + ")?)?\\s*=)?"
        prefix1, delimiter1, suffix1 = "[\\[{(]\\s*", ",\\s*", "\\s*[\\]})]"
        prefix2, delimiter2, suffix2 = "[\\[{(]\\s*" + valuePrefix, "\\s*=\\s*" + valuePrefix, "\\s*[\\]})]"
        prefix0, delimiter0, suffix0 = "[\\[{(]\\s*", ",\\s*", "\\s*[\\]})]"
        #key, value = numRegEx, numRegEx
        #return PlotDataReader.parseMapWith(s, prefix0, delimiter0, suffix0, prefix1, delimiter1, suffix1, prefix2, delimiter2, suffix2)
        return PlotDataReader.parseMapWith(s, prefix1, delimiter1, suffix1, prefix2, delimiter2, suffix2)
    
    @staticmethod
    def printStackTrace(msg=None):
        import traceback
        exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
        messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
        for message in messages:
            print message
        if msg: print msg
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


