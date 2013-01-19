#!/usr/bin/python
'''
This commands translates meter data from a file for a particular day into
seconds starting at zero for the first entry.
'''

import os
import sys
import re
from datetime import datetime, timedelta, date, time

# options that can be overridden at the command line with <key>=<value>
pathToMeterData = r'/home/bclement/proj/ae/meterData'
pathToHourlyData = pathToMeterData + os.sep + r'dataout_preops data_2012-09-26_2012-11-13_hourly'
day='9/26/12'
customerId = '000091373833'
fileName = pathToHourlyData + os.sep + r'dataout_preops data_' + customerId + r'_20120926-20121113.csv' 
dateFormat = "%m/%d/%y %H:%M" #used for parsing dates from file
startDateTime = None # the epoch of the scenario; defaults to the first datetime from file
startDateTimeFormat = dateFormat
debug = False
generateAllFiles = False
fromDay = datetime.strptime("9/26/12", "%m/%d/%y")
toDay = datetime.strptime("11/14/12", "%m/%d/%y")

outputDirectory = pathToMeterData + os.sep + 'output'
outputFile = sys.stdout

fileNameSpecified = False


def debugPrint(s, outputFile=sys.stdout):
    if debug:
        outputFile.write(s + '\n')

def getFileName():
    global fileName

    if not fileNameSpecified:
        pathToHourlyData = pathToMeterData + os.sep + r'dataout_preops data_2012-09-26_2012-11-13_hourly'
        fileName = pathToHourlyData + os.sep + r'dataout_preops data_' + customerId + '_20120926-20121113.csv'
    debugPrint("getFileName() = " + fileName)
    return fileName

def handleArgs(argv):
    # don't need to specify globals since exec() is run in global space
    debugPrint("argv = " + str(argv))

    if argv == None or len(argv) < 2:
        return
  
    for arg in argv[1:]:
        if '=' in arg:
            #try:
                kv = arg.split('=',1)
                if kv[0] in ['debug', 'generateAllFiles', 'outputFile'] or (len(kv[1])>0 and kv[1][0] in ["'",'"']): # the non-strings
                    cmd = kv[0] + ' = ' + kv[1]
                else:
                    cmd = kv[0] + ' = "' + kv[1] + '"'
                debugPrint(cmd)
                exec cmd in globals()
                if kv[0] == 'fileName':
                    fileNameSpecified = True 
                debugPrint(kv[0] + ' = ' + str(eval(kv[0])))
#            except BaseException:# as e:
#                print("couldn't make assignment " + arg )#+ '\n' + e.value)
##                print type(e)     # the exception instance
##                print e           # __str__ allows args to printed directly

    return

def secondsSinceEpoch(dt):
    epoch = datetime.utcfromtimestamp(0)
    delta = dt - epoch
    return (delta.seconds + delta.days * 24 * 3600)

def printData(outputFile):
    global day

    inputFileName = getFileName()
    try: inputFile = open(inputFileName,"r")
    except:
        print("can't find file @ %s" % fileName)
        return
    debugPrint(str(inputFile))

    start = None
    if startDateTime != None:
        dt = datetime.strptime(startDateTime, startDateTimeFormat)
        start = float(secondsSinceEpoch(dt))

    day = re.sub('0([1-9])/',r'\1/', day)
    debugPrint("day = " + day)

    #found = False # used to include the first entry for the next day
    for line in inputFile.readlines():
        m = re.search(day, line)
        if m:# or found:
            #if not m and found:
            #    found = False
            #else:
            #    found = True
            debugPrint(line)
            fields = re.split(',', line)
            dateString = fields[1].strip()
            kWH = fields[2].strip()
            dt = datetime.strptime(dateString, dateFormat)
            secs = float(secondsSinceEpoch(dt))
            if start == None: # assumes data is already ordered by time!
                start = secs
            secs = secs - start

            outputFile.write(str(secs) + ", " + kWH + '\n')
    inputFile.close()
    #outputFile.close()
    return

def generateFiles():
    import os
    if not os.path.exists(outputDirectory):
        os.makedirs(outputDirectory)
    for r,d,f in os.walk(pathToHourlyData):
        for fil in f:
            if fil.endswith(".csv"):
                fpath = os.path.join(r,fil)
                
                delta = toDay - fromDay
                for numDay in range(delta.days):
                    newDay = fromDay + timedelta(days=numDay)

                    dayString = newDay.strftime('%m-%d-%y')
                    outputFileName = os.path.join(outputDirectory,dayString + '_' + fil);
                    
                    # redirect stdout to output file
                    #so = se = open(outputFileName, 'w', 0)
                    ## re-open stdout without buffering
                    #sys.stdout = os.fdopen(sys.stdout.fileno(), 'w', 0)
                    ## redirect stdout and stderr to the log file opened above
                    #os.dup2(so.fileno(), sys.stdout.fileno())
                    ##os.dup2(se.fileno(), sys.stderr.fileno())
                
                    myArgv = ['fileName="' + fpath + '"', 'day=' \
                    + newDay.strftime('%m/%d/%y'), 'generateAllFiles=False', \
                    'outputFile=open("' + outputFileName + '",\'w\')']
                    main(myArgv)
                    outputFile.close()

def main(argv):

    handleArgs(argv)
    if generateAllFiles:
        generateFiles()
    else:
        printData(outputFile)

if __name__ == '__main__':
    main(sys.argv)
