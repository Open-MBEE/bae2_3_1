# debugMode can be passed in at the command line to turn it on
debugMode = True
# A modes for a data sources below can be passed at the command line and
# override these assignments 
useSocket = True
useFile = False
liveFromFile = False
useTable = False
useTestData = False

# separate out lines into separate plots or not
doSubplots = True

# save animation to mp4?
saveMovie = False
movieFilePrefix = "plotAnimation"
movieFileExtension = "mp4"

# default file to read if useFile
#fileName = "/home/bclement/proj/ae/workspace/CS/simulationSnapshot.example.txt"
fileName = 'C:\Users\bclement\workspace\CS\simulationSnapshot.example.txt'

plotLines = True
allLinesSameStyle = True # linestyle="-" for all lines
makeProjectionsDashed = True
plotMarkers = False
allMarkersSameStyle = False # linestyle="-" for all lines

# webpage for colors: http://matplotlib.org/api/colors_api.html
colors =  ['r', 'g', 'b', 'm', 'c', 'k','orange','purple','pink','grey','lime','aqua','maroon','navy']
    
# http://www.loria.fr/~rougier/teaching/matplotlib/#line-styles
# from interpreter: matplotlib.lines.Line2D.lineStyles
lineStyles = ['-','--','-.',':']
    
# http://www.loria.fr/~rougier/teaching/matplotlib/#markers
# from interpreter: matplotlib.lines.Line2D.markers
markers = ['^', 'd', '*', '+', 'x','o','v','s','p']

msizes =  [ 12, 12, 12, 12, 12,  12,  10,  4, 16, 18,  8, 18,  20] 
    
timeout = 20 # seconds of no data over socket before closing socket

dataModes = None

usingTk = True
if saveMovie: usingTk = False

# To keep time=now in center and scroll with time, set zoomToFitX=False and centerAtNow=True
zoomToFitX = True
zoomToFitY = True
zoomToFitOnlyVisibleY = True
centerAtNow = False
horizonDurationHours = 24
xAxisUnits = None
redrawEveryNthTime = 1
timeNow = horizonDurationHours / 1.6


if usingTk:
    # imports for Tk backend
    import matplotlib
    matplotlib.use('TkAgg')
    
    from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg, NavigationToolbar2TkAgg
    
    from Tkinter import *
    import threading

    # vars for Tk backend
    root = Tk()

import os
import sys
import Queue
import threading
print "PYTHONPATH = " + str(os.getenv("PYTHONPATH"))
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation
from PlotDataReader import PlotDataReader
from InterpolatedMap import InterpolatedMap
from OneWaySocket import OneWaySocket
from collections import Iterable
import string
import time

genXValues = True

queue = None

nowLines = []

doneReceiving = False

showLabels = False

numLines = 4 # the default number of lines to plot corresponding to test modes

replaceInitValues = False #to try and fix exception do we really need this?

host = "127.0.0.1"
defaultPort = 60002 # the actual port can be passed as an argument
sock = None
xGrow = 0.2 # how much to grow the x axis for points outside
yGrow = 0.2 # how much to grow the y axis for points outside

sock = None

fig = None
#ax = None
axs = {}
lines = {}
xdata = None
ydata = None
xmin = None
xmax = None
ymin = None
ymax = None

staticLines = {}
lineIdToIndex = {}
subplotIds = set()
lineNames = []
subplotForLine = []
defaultSubplotTitle = ""


def debugPrint( s ):
    if debugMode: print "[animatePlot]" + s

def receiveString( sock ):
    ''' We don't have Java code for packaging data like Python's struct.pack(),
        so we need to receive it another way.
    '''
    nameLength = sock.unpack("i", sock.receiveInPieces(4))[0]
    debugPrint("    unpacked receivedString length of %s" % str(nameLength))
    msg = str(sock.receiveInPieces(nameLength*2))
    debugPrint("    received %s of length %s" % (msg,str(len(msg))))
    receivedString = ""
    if len(msg) > nameLength: 
        for char in msg:
            debugPrint("char: " + char)
            if char not in string.printable: continue
            else: receivedString += char
        debugPrint("fixed receivedString: %s (length %s)" % (receivedString, str(len(receivedString))))
    else: receivedString=msg
    return receivedString

# create the socket, and get numLines, the number of lines to plot!
def initSocket( host, port ):
    global sock
    global numLines
    global lineNames
    global subplotForLine
    global subplotIds
    import re

    sock = OneWaySocket(host, port, False, debugMode)
    sock.endianGet()
    numLines = sock.unpack("i", sock.receiveInPieces(4))
    numLines = int(numLines[0])
    
    for _ in xrange(numLines):
        name = receiveString(sock)
        subplotId = receiveString(sock)
        if "__" in subplotId: subplotId = subplotId.split("__")[-1]
        if re.search("\d{6}",subplotId): subplotId = subplotId[0:len(subplotId)-6]
        lineNames.append(name)
        if not doSubplots and len(subplotIds) > 0: subplotId = [x for x in subplotIds][0]
        subplotForLine.append(subplotId)
        subplotIds.add(subplotId)
    debugPrint( "numLines = " + str(numLines) )
    debugPrint( "lineNames = " + str(lineNames) )
    spawnQueue()
    return

def interpolateForAllX():
    global xdata
    global ydata
    global xmin, xmax, ymin, ymax
    
    x = myMin([xmin[subId] for subId in xmin.keys()]) 
    lastx = myMax([xmax[subId] for subId in xmax.keys()])
    indices = [0 for _ in xrange(len(xdata))]
    newXdata = [[] for _ in xrange(len(xdata))]
    newYdata = [[] for _ in xrange(len(xdata))]
    done = False
    while not done and x != None:
        done = True
        for i in range(len(xdata)):
            value = None
            if indices[i] < len(xdata[i]):
                done = False
                curX = xdata[i][indices[i]]
                if x < curX:
                    if indices[i] > 0: value = ydata[i][indices[i]-1]
                else:
                    value = ydata[i][indices[i]]
                    indices[i] = indices[i] + 1
            elif len(xdata[i]) > 0 and len(newYdata[i]) > 0: value = newYdata[i][-1]
            if value != None:
                newXdata[i].append(x)
                newYdata[i].append(value)
        if x == lastx: break
        x = myMin([xdata[i][indices[i]] for i in range(len(xdata)) if indices[i] < len(xdata[i])])
    xdata = newXdata
    ydata = newYdata

def updateBounds(subId, datx, daty, updateLimits=True):
    global xmin, xmax, ymin, ymax
    global zoomToFitX
    global zoomToFitY
    global centerAtNow
    global timeNow
    global horizonDurationHours
    global xAxisUnits
    global xGrow
    global yGrow
    global axs

    # find min/max for x-axis
    if centerAtNow:
        # the limits on the x-axis should include the horizon duration
        halfHorizon = convertHoursTo(horizonDurationHours, xAxisUnits) / 2
        xmin[subId] = timeNow - halfHorizon
        xmax[subId] = timeNow + halfHorizon
    if zoomToFitX and datx != None and len(datx) > 0:
#        mn = myMin([xmin[subId], datx])
#        mx = myMax([xmax[subId], datx])
        mn = myMin(datx)
        mx = myMax(datx)
        tmx = mx
        tmn = mn
        if mx != mn:
            tmx = mx + xGrow*(mx - mn)
            tmn = mn - xGrow*(mx - mn)
#        else:
        if mx > xmax[subId]: xmax[subId] = myMax((tmx, xmax[subId] + xGrow*(xmax[subId] - xmin[subId])))
        if mn < xmin[subId]: xmin[subId] = myMin((tmn, xmin[subId] - xGrow*(xmax[subId] - xmin[subId])))
    if zoomToFitY and daty != None and len(daty) > 0:
#        mn = myMin([ymin[subId], daty])
#        mx = myMax([ymax[subId], daty])
        mn = myMin(daty)
        mx = myMax(daty)
        tmx = mx
        tmn = mn
        if mx != mn:
            tmx = mx + yGrow*(mx - mn)
            tmn = mn - yGrow*(mx - mn)
#        else:
        if mx >= ymax[subId]: ymax[subId] = myMax((tmx, ymax[subId] + yGrow*(ymax[subId] - ymin[subId])))
        if mn < ymin[subId]: ymin[subId] = myMin((tmn, ymin[subId] - yGrow*(ymax[subId] - ymin[subId])))
    if not updateLimits: return
    # Need to keep x-axis (time) the same for everyone
    if zoomToFitX or centerAtNow:
        curmin, curmax = axs[subId].get_xlim()
        if curmin != xmin[subId] or curmax != xmax[subId]:
            mn = myMin([xmin[s] for s in xmin.keys()]) 
            mx = myMax([xmax[s] for s in xmax.keys()])
            if mn != curmin or mx != curmax:
                for sId, ax in axs.items():
                    xmin[sId] = mn
                    xmax[sId] = mx
                    ax.set_xlim(xmin[sId], xmax[sId])
#    if zoomToFitX or centerAtNow:
#        axs[subId].set_xlim(xmin[subId], xmax[subId])
    if zoomToFitY: axs[subId].set_ylim(ymin[subId], ymax[subId])
    return

def queueSocketData(sock, queue):

    #queue = args[0]
    doneRecv = False

    while 1:
        if doneRecv: break
        try:
            # receive a string telling whether receiving data at timepoint or 
            # update a series of data
            dataType = receiveString(sock) #sock.receive()
            dat = [dataType]
            if dataType == 'quit' :
                debugPrint("got quit")
                doneRecv = True
                break
            if dataType == 'timepointData' :
                debugPrint("try to receive data for plot")
                arr = sock.receive()
                debugPrint("received data for plot: " + str(arr))
                if arr == None: break
                dat.append(arr)
            else:
                #if len(arr) != expectedLength: # HACK -- what if the size were coincidentally the same?
                debugPrint("static line")
                # We are receiving pairs of x,y values to plot.
                # The first number in the array is an id for plot line, so
                # we can replace it if it's an update.
                lineId = receiveString(sock) #sock.receive()
                dat.append(lineId)
                debugPrint("received lineId=" + str(lineId))
                subplotId = receiveString(sock) #sock.receive()
                if not doSubplots and len(subplotIds) > 0: subplotId = [x for x in subplotIds][0]
                dat.append(subplotId)
                debugPrint("received subplotId=" + str(subplotId))
                debugPrint("try to receive data for plot")
                arr = sock.receive()
                debugPrint("received data for plot: " + str(arr))
                dat.append(arr)
            queue.put(dat)
        except IOError as e:
            print "I/O error({0}): {1}".format(e.errno, e.strerror)
            print
            print sys.exc_info()[0]
            debugPrint("error receiving data")
            time.sleep(1.0) # seconds
            doneReceiving = True
        except:
            print "Unexpected error:", sys.exc_info()[0]
            debugPrint("error receiving data")
            raise
    sock.close()
    debugPrint("done queueing data")

def spawnQueue():
    global queue
    global qthread
    global sock
    queue = Queue.Queue()
    qthread = threading.Thread(target=queueSocketData, args=(sock, queue))
    qthread.daemon = True
    qthread.start()
    return qthread


def socketDataGen():
    global staticLines
    global sock
    global lines
    global lineIdToIndex
    global numLines
    global lineNames
    global subplotForLine
    global subplotIds
    global timeNow
    #global nowLines
    #global doneReceiving
    global queue
    global timeout

    doneReceiving = False
    cnt = 0
    if genXValues: yRange = range(1,numLines+1)
    else: yRange = range(numLines)
    #yield 0, [0.0 for n in yRange]
    while 1:
        if doneReceiving: break
        try:
            if queue == None: break
            dat = None
            try: dat = queue.get(True, timeout)
            except: pass
            if dat == None: break
            # receive a string telling whether receiving data at timepoint or 
            # update a series of data
            dataType = dat[0]
            if dataType == 'quit' :
                debugPrint("got quit from queue")
                doneReceiving = True
                break
            if dataType == 'timepointData' :
                debugPrint("try to receive data for plot")
                arr = dat[1]
                debugPrint("received data for plot: " + str(arr))
                if arr == None: break
                if genXValues and len(arr) > 0: xVal = arr[0]
                else: xVal = cnt
                timeNow = xVal
                yield xVal, [arr[i] for i in yRange]
                cnt+=1
            else:
                debugPrint("static line")
                # We are receiving pairs of x,y values to plot.
                # The first number in the array is an id for plot line, so
                # we can replace it if it's an update.
                lineId = dat[1]
                debugPrint("received lineId=" + str(lineId))
                subplotId = dat[2]
                debugPrint("received subplotId=" + str(subplotId))
                #lineId = arr[0]
                debugPrint("try to receive data for plot")
                arr = dat[3]
                debugPrint("received data for plot: " + str(arr))
                # REVIEW -- we could also put in a dimension for 3D plots.
                plotDimension = 2
                xvals = [arr[i] for i in range(0,len(arr)) if np.mod(i,plotDimension) == 0]
                yvals = [arr[i] for i in range(0,len(arr)) if np.mod(i,plotDimension) == 1]
                addStaticLine(lineId, subplotId, xvals, yvals)
        except RuntimeError: break
    debugPrint("finished yielding data")


def addStaticLine(lineId, subplotId, xvals, yvals):
    global staticLines
    global lines
    global lineIdToIndex
    global lineNames
    global subplotForLine
    global subplotIds
    import re
    
    if re.search("\d{6}",subplotId): subplotId = subplotId[0:len(subplotId)-6]
    # get x-axis values
    plotLineId = str(subplotId) + "_" + str(lineId)
    if plotLineId not in staticLines:
        staticLines[plotLineId] = {}
        idx = len(lines)
        lineIdToIndex[plotLineId] = idx 
        debugPrint("ADDING EXTRA LINE to lines with names " + str(lineNames))
        lineNames.append(lineId)
        subplotForLine.append(subplotId)
        if subplotId not in subplotIds: ax = addAx(subplotId)
        else: ax = axs[subplotId]
        addLine(ax, idx)
        debugPrint("adding line " + lineNames[idx] + " for subplot " + subplotId + ", ax=" + str(ax))
        ax.legend(loc="upper right")

    staticLines[plotLineId][0] = xvals
    staticLines[plotLineId][1] = yvals
    lines[lineIdToIndex[plotLineId]].set_data(staticLines[plotLineId][0], staticLines[plotLineId][1])
    debugPrint("lineNames = " + str(lineNames))
    debugPrint("subplotForLine = " + str(subplotForLine))
    debugPrint("subplotId = " + str(subplotId))
    debugPrint("lineId = " + str(lineId))
    debugPrint("lineIdToIndex = " + str(lineIdToIndex))
    debugPrint("lineIdToIndex[plotLineId] = " + str(lineIdToIndex[plotLineId]))
    debugPrint("subplotForLine = " + str(subplotForLine))
    debugPrint("lineId = " + str(lineId))
    debugPrint("plotLineId = " + str(plotLineId))
    debugPrint("staticLines[plotLineId][0] = " + str(staticLines[plotLineId][0]))
    debugPrint("staticLines[plotLineId][1] = " + str(staticLines[plotLineId][1]))
    debugPrint("lines.keys() = " + str(lines.keys()))
    initializePlotBounds(subplotId)

def testDataGen():
    global timeNow
    cnt = 0
    while cnt < 100:
        timeNow = cnt
        debugPrint("try to yield data for plot")
        yield cnt, [(cnt-n-1.0)/(cnt+n+1.0) for n in range(numLines)]
        cnt+=1

def dataFromTable():
    global timeNow 
    table = InterpolatedMap()
    table[0]=[3,4,1,0]
    table[10]=[7,4,8,4]
    table[15]=[5,10,1,10]
    debugPrint("dataFromTable()")
    cnt = 0
    #subplotIds.add("tableData")
    while cnt <= 30:
        timeNow = cnt
        debugPrint("try to yield data for plot")
        yield cnt, [table[cnt][i] for i in range(numLines)]
        debugPrint("tried to yield data for plot")
        cnt+=1
        debugPrint("incremented counter")

# Need this to get numLines
def initFileData():
    global fileName
    global fileData
    global numLines
    global lineNames
    global subplotForLine
    global subplotIds
    global xdata
    global ydata
    global projectedMap

    fileData = PlotDataReader(fileName,False)
    if fileData == None or fileData.data == None or len(fileData.data) == 0:
        print("No data found for " + fileName)
        return
    #numLines = sum([ len([lineData for lineData in subplotData.values()]) for subplotData in fileData.data.values() ])

    xdata = []
    ydata = []
    projectedMap = {}
    for subplotItem in fileData.data.items():
        subplotId = subplotItem[0]
        if not doSubplots and len(subplotIds) > 0:
            subplotId = [x for x in subplotIds][0]
        subplotIds.add(subplotId)
        for lineItems in subplotItem[1].items():
            #print lineItems.interpolationType
            lineNames.append(lineItems[0])
            subplotForLine.append(subplotId)
            #i = len(lineNames) - 1
            lineData = lineItems[1]
            print lineData.interpolationType
            print "(%s) projected: %s" % (lineItems[0],str(lineData.attributes["projected"]))
            print str(lineData.attributes)
            projectedMap[lineItems[0]] = lineData.attributes["projected"]
            xdata.append(lineData.keys())
            ydata.append(lineData.values())
    numLines = len(lineNames)
    debugPrint( "numLines = " + str(numLines) )
    debugPrint( "lineNames = " + str(lineNames))
    debugPrint( "subplotIds = " + str(subplotIds))
    return


def flatten(lst, useValuesInsteadOfKeys=False):
    print("flatten(" + str(lst) + ")")
    newLst = []
    for e in lst:
        if useValuesInsteadOfKeys and isinstance(e, dict): newLst.extend(flatten(e.values()))
        if isinstance(e, Iterable) and not isinstance(e, str): newLst.extend(flatten(e))
        else: newLst.append(e)
    return newLst

# TODO - needs testing
# TODO - here's where simulation code with timescaling and sleeps would go.
def dataFromFile():
    global timeNow
    global fileName
    global fileData
    global numLines
    debugPrint("dataFromFile()")

    if fileData == None or fileData.data == None or len(fileData.data) == 0:
        print("No data found for " + fileName)
        return

    mapLists = [ namedMaps.values() for namedMaps in file.data.values() if namedMaps != None ]
    keys = flatten([[ m.keys() for m in mList if len(m.keys()) > 0] for mList in mapLists])
    s = set()
    for k in keys(): s.add(k)
    keys = [k for k in s]
    keys.sort()
    for k in keys:
        timeNow = k
        dat = []
        for subplotItem in fileData.data.items():
            subplotId = subplotItem[0]
            for lineItems in subplotItem[1]:
                lineName = lineItems[0]
                lineData = lineItems[1]
                if "projected" in lineData.attributes.keys() and lineData.attributes["projected"] == True:
                    addStaticLine(lineName, subplotId, lineData.keys(), lineData.values())
                else: dat.append(lineData[timeNow])
        yield timeNow, dat
        debugPrint("tried to yield data for plot: " + str(dat))

def moveNowLine(nowLine):
    global timeNow

    xcoords = [timeNow, timeNow]
    ycoords = [-1e20, 1e20]
    debugPrint("moveNowLine: " + str(xcoords) + ", " + str(ycoords))
    nowLine.set_data(xcoords, ycoords)

def addNowLine(subplotId):
    global staticLines
    global lines
    global nowLines
    global timeNow
    global axs
    
    ax = axs[subplotId]
    lineNames.append('_nolegend_')
    subplotForLine.append(subplotId)
    nowLine = addLine(ax, None)
    nowLines.append(nowLine)
    moveNowLine(nowLine)

def convertHoursTo(numHours, units):
    if units == None or units == 'seconds': return numHours * 3600
    if units == 'minutes': return numHours * 60
    if units == 'hours': return numHours
    if units == 'days': return numHours / 24

def initializePlotBounds(subplotId = None):
    global numLines
    global xdata
    global ydata
    global xmin, xmax, ymin, ymax
    global replaceInitValues
    global lines
    global staticLines
    global axs
    global fig
    global zoomToFitX
    global zoomToFitY
    global zoomToFitOnlyVisibleY
    global centerAtNow
    global horizonDurationHours
    global xAxisUnits
    global timeNow
    global lineIdToIndex
    global subplotForLine

    if xmin == None:
        xmin = {}
        xmax = {}
        ymin = {}
        ymax = {}    
    if subplotId == None: subs = axs.keys()
    else: subs = [subplotId]
    for subId in subs:
        ax = axs[subId]
        xmin[subId], xmax[subId] = ax.get_xlim()
        ymin[subId], ymax[subId] = ax.get_ylim()
        
        # find min/max for x-axis
        if centerAtNow:
            # the limits on the x-axis should include the horizon duration
            halfHorizon = convertHoursTo(horizonDurationHours, xAxisUnits) / 2
            xmin[subId] = timeNow - halfHorizon
            xmax[subId] = timeNow + halfHorizon
        if zoomToFitX:
            xdat = [xdata[i] for i in range(len(xdata)) if subplotForLine[i] == subId]
            if ( staticLines != None and \
                 len(staticLines) > 0 and \
                 len(staticLines.values()) > 0 and \
                 len(staticLines.values()[0]) > 0 ):
                xdat.append([staticLines[dat][0] for dat in staticLines if subplotForLine[lineIdToIndex[dat]] == subId])
            updateBounds(subId, xdat, None, False)
    
        # find min/max for y-axis
        if zoomToFitY:
            yvals = [[ydata[i][j] for j in range(len(ydata[i])) \
                      if ((not zoomToFitOnlyVisibleY) or \
                          (xdata[i][j] >= xmin[subId] and xdata[i][j] <= xmax[subId])) ] \
                     for i in range(len(ydata)) if subplotForLine[i] == subId]
            yDataMax = myMax(yvals)
            yDataMin = myMin(yvals)
            if ( staticLines != None and \
                 len(staticLines) > 0 and \
                 len(staticLines.values()) > 0 and \
                 len(staticLines.values()[0]) > 1 ):
                yvals.append([staticLines[dat][1] for dat in staticLines if subplotForLine[lineIdToIndex[dat]] == subId])
            updateBounds(subId, None, yvals, False)
    if axs.keys() != None and len(axs.keys()) > 0:
        for subId in axs.keys(): updateBounds(subId, None, None, True)
    interpolateForAllX()
    for subId in axs.keys():
        debugPrint("plot bounds for " + subId + ":")
        debugPrint("xmin = " + str(xmin[subId]))
        debugPrint("xmax = " + str(xmax[subId]))
        debugPrint("ymin = " + str(ymin[subId]))
        debugPrint("ymax = " + str(ymax[subId]))
        if xAxisUnits == None and xmax[subId] - xmin[subId] > 5000: xAxisUnits = 'seconds'
    if xAxisUnits == None: xAxisUnits = 'hours'
    return

def updateData(data = (None, None)):
    global numLines
    global xdata
    global ydata
    global replaceInitValues
    global lines
    global nowLines
    global subplotForLine

    if data == None:
        t = None
        y = None
    else: t,y = data
    if xmin == None: initializePlotBounds()
    elif t != None and y != None:
        if len(y) > 0:
            updateBounds(subplotForLine[0], [t], None)
            for i in range(len(y)): updateBounds(subplotForLine[i], None, [y[i]])

    debugPrint("try to update data for plot")
    # update the plot data
    for i in range(numLines):
        if t != None and y != None:
            if replaceInitValues:
                # overwrite any existing data (like initial values for plot)
                debugPrint( "xdata[" + str(i) + "][" + str(t) + " = " + str(t) + "]" )
                xdata[i][0] = t
                if y != None:
                    debugPrint( "ydata[" + str(i) + "][" + str(t) + " = " + str(y[i]) + "]" )
                    ydata[i][0] = y[i]
                else:
                    debugPrint( "ydata[" + str(i) + "][" + str(t) + " = 0]" )
                    ydata[i][0] = 0
            elif t != None and y != None:
                # add the data to the end of the list
                debugPrint( "xdata[" + str(i) + "].append(" + str(t) + ")" )
                xdata[i].append(t)
                debugPrint( "ydata[" + str(i) + "].append(" + str(y[i]) + ")" )
                ydata[i].append(y[i])
        # apply the data to the plot lines
        debugPrint(str(lines))
        lines[i].set_data(xdata[i], ydata[i])
        if showLabels: plt.annotate(('%0.3f' % (y[i])), (t,y[i]))
    replaceInitValues = False

    # update nowLines
    for nowLine in nowLines:
        moveNowLine(nowLine)


def addLine(ax = None, index = None):
    global axs
    global fig
    global lines
    
    # specify options for plot data display
    if ax == None and axs != None and len(axs) > 0: return None
        #ax = axs.values()[0]
    if ax == None: return None
    n = len(lines)
    col = colors[index % len(colors)] if index is not None else 'y'
    if plotLines:
        try: havePmap = True if len(projectedMap.keys())>0 and index is not None else False
        except: havePmap = False
        if makeProjectionsDashed and havePmap and projectedMap[lineNames[index]]: lineStyle = lineStyles[1]  
        elif allLinesSameStyle: lineStyle = lineStyles[0]
        else: lineStyle = lineStyles[index % len(lineStyles)] if index else lineStyles[0]
    else: lineStyle = None
    if plotMarkers:
        if allMarkersSameStyle: marker = markers[0]
        else: marker = markers[index % len(markers)] if index else markers[0]
    else: marker = None
    newLine = ax.plot([0], [0], "-", lw=4, \
                      markeredgecolor=colors[n % len(colors)], \
                      markeredgewidth=1, markersize=7, \
                      markerfacecolor='y', color=col, linestyle=lineStyle,
                      marker=marker)[0]
    if index != None: newLine.set_label(str(lineNames[index]))
    else: index = n
    lines[index] = newLine
    debugPrint("new line = " + str(newLine) + " on ax=" + str(ax) )
    return newLine

def addAx(subplotId):
    global subplotForLine
    global subplotIds
    global axs
    global fig
    global lineNames
    
    subplotIds.add(subplotId)
    idx = [s for s in subplotIds].index(subplotId)
    ax = fig.add_subplot(len(subplotIds), 1, idx)
    if len(subplotIds) >= len(fig.axes):
        for i in range(len(fig.axes)):
            fig.axes[i].change_geometry(len(fig.axes), 1, i+1)
            debugPrint("fig.axes[" + str(i) + "].get_geometry() = " + str(fig.axes[i].get_geometry()))
    axs[subplotId] = ax
    debugPrint("ax.get_geometry() = " + str(ax.get_geometry()))
    ii = 0
    if len(subplotForLine) == 0:
        for _ in xrange(numLines): subplotForLine.append(subplotId)
    if len(lineNames) == 0:
        for _ in xrange(numLines): lineNames.append('_nolegend_')        
    for _ in xrange(numLines):
        if subplotForLine[ii] == subplotId:
            debugPrint("adding line " + lineNames[ii] + ", index " + str(ii) + ", for subplot " + subplotId + ", ax=" + str(ax))
            addLine(ax, ii)
        ii+=1
    ax.set_ylim(-1.1, 1.1)
    ax.set_xlim(-0.005, 5)
    ax.grid()
    ax.set_xlabel("time")
    ax.set_ylabel("kWh")
    ax.set_title(subplotId)
    ax.legend(loc="upper right")
    return ax

def pickDataMode(dataModes):
    #global dataModes
    selectedMode = None
    for mode in dataModes: exec("global " + mode)
    numDataModesChosen = sum([(1 if eval(x) else 0) for x in dataModes])
    if numDataModesChosen == 0:
        exec dataModes[0] + " = True" #in globals() # may not be needed bc of global cmds exec'd above 
        return dataModes[0]
    if numDataModesChosen > 1: print "Warning! Multiple data source modes chosen!"
    for mode in dataModes:
        if eval(mode):
            if selectedMode == None:
                if numDataModesChosen == 1: return mode
                selectedMode = mode
            else: exec(mode + " = False")
    return selectedMode

def handleCommandLineArgs(argv=None):
    global dataModes
    global debugMode
    global useSocket
    global useFile
    global useTable
    global useTestData
    global fileName
    global port
    
    dataModes = ["useSocket", "useFile", "useTable", "useTestData"]
    modes = dataModes + ["debugMode"]
    selectedMode = pickDataMode(dataModes)
    for mode in dataModes: exec mode + ' = False' in globals()
    if argv != None and len(argv) >= 2:
        for arg in argv[1:]:
            if arg in modes: exec arg + ' = True' in globals()
            elif arg.isdigit(): port = int(arg)
            else: # fileName must not be just digits
                fileName = arg
                debugPrint("setting file to read to " + fileName)
    numDataModesChosen = sum([(1 if eval(x) else 0) for x in dataModes])
    if numDataModesChosen == 0: exec selectedMode + " = True" in globals()
    else: selectedMode = pickDataMode(dataModes)
    for mode in modes: debugPrint(mode + " = " + str(eval(mode)))

    if useSocket:
        if port == None:
            for arg in argv: #.reverse():
                if str(arg).isdigit():
                    port = int(arg)
                    break
        if port == None:
            port = defaultPort
            debugPrint("using default port = " + str(port) )
        else: debugPrint("got arg for port = " + str(port) )
    return

def myMin(a):
    if type(a) not in [list, tuple, set]: return a
    else:
        minnest = None
        for x in a:
            z = myMin(x)
            if minnest == None or z < minnest: minnest = z
        return minnest

def myMax(a):
    if type(a) not in [list, tuple, set]: return a
    else:
        maxxest = None
        for x in a:
            z = myMax(x)
            if maxxest == None or z > maxxest: maxxest = z
        return maxxest

count = 0
def run(data = (None, None)):
    debugPrint("run()")
    global numLines
    global xdata
    global ydata
    global replaceInitValues
    global lines
    global axs
    global fig
    global zoomToFitX
    global zoomToFitY
    global zoomToFitOnlyVisibleY
    global centerAtNow
    global horizonDurationHours
    global timeNow
    global showLabels
    global count
    global redrawEveryNthTime
    global queue
    
    debugPrint( "data = " + str(data) )
    updateData(data)
    debugPrint("xdata=" + str(xdata))
    debugPrint("ydata=" + str(ydata))
    debugPrint("lines=" + str([y.get_data() for y in lines.values()]))
    debugPrint("staticLines=" + str(staticLines))

    if count % redrawEveryNthTime == 0:
        for ax in axs.values():
            try: ax.figure.canvas.draw()
            except: print("error")
    count = count + 1
    if queue != None and not queue.empty(): redrawEveryNthTime = int(1 + queue.qsize() / 2)
    debugPrint("updated lines = " + str([str(line.get_data()) + "\n" for line in lines.values()]))
    return lines.values()
    # end of run()

def initTk():
    global root
    #root = Tk()
    root.option_add('*Font', 'Verdana 10')
    root.title(defaultSubplotTitle)

def quit():
    global root
    if usingTk: root.quit()
        #root.destroy()

def makeTkCanvas():
    global fig
    global root

    canvas = FigureCanvasTkAgg(fig, master=root)
    canvas.show()
    canvas.get_tk_widget().pack(side=TOP, fill=BOTH, expand=1)
    toolbar = NavigationToolbar2TkAgg(canvas, root)
    toolbar.update()
    canvas._tkcanvas.pack(side=TOP, fill=BOTH, expand=1)
    button = Button(root, text="Quit", command=lambda root=root:quit()).pack()

def update_task(gen):
    """
    Update plots from socket here...
    """
    global root

    debugPrint("updateTask()")
    time_period = 20

    # read line without blocking
    try:
        data = gen.next()
        if data:
            debugPrint("generated data = " + str(data))
            run(data)
        root.update_idletasks()
        root.after(time_period, update_task, gen)
    except: debugPrint("no more data")
    return

#
# Main
#
def main(argv=None):
    global numLines
    global xdata
    global ydata
    global replaceInitValues
    global lines
    global axs
    global fig
    global fileName
    global fileData
    global port
    global subplotIds
    global dataModes
    global debugMode
    global useSocket
    global useFile
    global useTable
    global useTestData
    
    global usingTk
    global root

    if argv is None: argv = sys.argv
    debugPrint( "argv = " + str(argv) )
    port = None
    handleCommandLineArgs(argv);
    fileData = None

    if useSocket:
        # connect with data source for plot
        initSocket( host, port )
    
    elif useFile: initFileData()
    
    if numLines < 1:
        print("no lines!")
        return

    # create plot figure
    fig = plt.figure(figsize=(20.0,10.0))
    fig.subplots_adjust(hspace=0.05)
    fig.subplots_adjust(left=0.05)
    fig.subplots_adjust(right=0.95)
    if len(subplotIds) == 0: subplotIds.add(defaultSubplotTitle)
    for subplotId in subplotIds: addAx(subplotId)
    for subplotId in subplotIds: addNowLine(subplotId)

    if xdata == None:
        xdata = [[0] for _ in xrange(numLines)] #can't be empty
        ydata = [[0] for _ in xrange(numLines)]
        replaceInitValues = True

    initializePlotBounds()

    if usingTk:
        initTk()
        makeTkCanvas()

    gen = testDataGen
    if useSocket: gen = socketDataGen
    elif useTable: gen = dataFromTable
    elif useFile: gen = dataFromFile
        
    if useFile: run()
    else:
        if usingTk: root.after(1, update_task, gen())
        else:
            # must store return value, even if unused, or else it will stop plotting after the first point
            ani = animation.FuncAnimation(fig, run, gen, blit=True, interval=0, repeat=False, save_count=1000)
            if saveMovie: ani.save(movieFilePrefix + '.' + movieFileExtension, fps=10)
    if usingTk: root.mainloop()
    else: plt.show()
    print("done with main()")
    #end of main()

if __name__ == "__main__":
    #print "I am running."
    main(sys.argv)
