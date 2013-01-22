import os
import sys
print "PYTHONPATH = " + str(os.getenv("PYTHONPATH"))
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation
from PlotDataReader import PlotDataReader
from InterpolatedMap import InterpolatedMap
from OneWaySocket import OneWaySocket
import string

# debugMode can be passed in at the command line to turn it on
debugMode = True
# A modes for a data sources below can be passed at the command line and
# override these assignments 
useSocket = True
useFile = False
useTable = False
useTestData = False

dataModes = None
fileName = "/home/bclement/proj/ae/workspace/CS/simulationSnapshot.example.txt" # default file to read if useFile

genXValues = True

zoomToFitX = True
zoomToFitY = True
zoomToFitOnlyVisibleY = True
centerAtNow = False
horizonDurationHours = 2
timeNow = horizonDurationHours / 1.6
nowLines = []

donePlotting = False

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

table = InterpolatedMap()
table[0]=[3,4,1,0]
table[10]=[7,4,8,4]
table[15]=[5,10,1,10]


def debugPrint( s ):
    if debugMode:
        print "[animatePlot]" + s

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

    sock = OneWaySocket(host, port, False, debugMode)
    sock.endianGet()
    numLines = sock.unpack("i", sock.receiveInPieces(4))
    numLines = int(numLines[0])
    
    lineNames = []
    subplotForLine = []
    subplotIds = set()

    for _ in xrange(numLines):
        name = receiveString(sock)
        subplotId = receiveString(sock)
        #linename = sock.unpack(str(nameLength*2)+"c",msg)[0]
        #debugPrint("    unpacked name: %s" % linename)
        #lineNames.append(str(linename))
        lineNames.append(name)
        subplotForLine.append(subplotId)
        subplotIds.add(subplotId)
    debugPrint( "numLines = " + str(numLines) )
    debugPrint( "lineNames = " + str(lineNames))

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
    while not done:
        done = True
        for i in range(len(xdata)):
            value = None
            if indices[i] < len(xdata[i]):
                done = False
                curX = xdata[i][indices[i]]
                if x < curX:
                    if indices[i] > 0:
                        value = ydata[i][indices[i]-1]
                else:
                    value = ydata[i][indices[i]]
                    indices[i] = indices[i] + 1
            elif len(xdata[i]) > 0 and len(newYdata[i]) > 0:
                value = newYdata[i][-1]
            if value != None:
                newXdata[i].append(x)
                newYdata[i].append(value)
        if x == lastx:
            break
        x = myMin([xdata[i][indices[i]] for i in range(len(xdata)) if indices[i] < len(xdata[i])])
    xdata = newXdata
    ydata = newYdata

def updateBounds(subId, datx, daty, updateLimits=True):
    global xmin, xmax, ymin, ymax
    global zoomToFitX
    global zoomToFitY
    global centerAtNow
    global xGrow
    global yGrow
    global axs

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
        if mx > xmax[subId]:
            xmax[subId] = myMax((tmx, xmax[subId] + xGrow*(xmax[subId] - xmin[subId])))
        if mn < xmin[subId]:
            xmin[subId] = myMin((tmn, xmin[subId] - xGrow*(xmax[subId] - xmin[subId])))
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
        if mx >= ymax[subId]:
            ymax[subId] = myMax((tmx, ymax[subId] + yGrow*(ymax[subId] - ymin[subId])))
        if mn < ymin[subId]:
            ymin[subId] = myMin((tmn, ymin[subId] - yGrow*(ymax[subId] - ymin[subId])))
    if not updateLimits:
        return
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
    if zoomToFitY:
        axs[subId].set_ylim(ymin[subId], ymax[subId])
    return

def socketDataGen():
    global sock
    global lines
    global lineIdToIndex
    global numLines
    global lineNames
    global subplotForLine
    global subplotIds
    global timeNow
    #global nowLines
    global donePlotting

    cnt = 0
    if genXValues:
        yRange = range(1,numLines+1)
    else:
        yRange = range(numLines)
    #yield 0, [0.0 for n in yRange]
    while 1:
        if donePlotting:
            break
        try:
            # receive a string telling whether receiving data at timepoint or 
            # update a series of data
            dataType = receiveString(sock) #sock.receive()
            if dataType == 'quit' :
                donePlotting = True
                break
            if dataType == 'timepointData' :
                debugPrint("try to receive data for plot")
                arr = sock.receive()
                debugPrint("received data for plot: " + str(arr))
                if arr == None:
                    break
                #expectedLength = numLines + 1
                if genXValues and len(arr) > 0:
                    xVal = arr[0]
                else:
                    #expectedLength += 1
                    xVal = cnt
                timeNow = xVal
                yield xVal, [arr[i] for i in yRange] #[ arr[13], arr[14], arr[15] ]
                cnt+=1
            else:
                #if len(arr) != expectedLength: # HACK -- what if the size were coincidentally the same?
                debugPrint("static line")
                # We are receiving pairs of x,y values to plot.
                # The first number in the array is an id for plot line, so
                # we can replace it if it's an update.
                lineId = receiveString(sock) #sock.receive()
                debugPrint("received lineId=" + str(lineId))
                subplotId = receiveString(sock) #sock.receive()
                debugPrint("received subplotId=" + str(subplotId))
                #lineId = arr[0]
                # REVIEW -- we could also put in a dimension for 3D plots.
                plotDimension = 2
                debugPrint("try to receive data for plot")
                arr = sock.receive()
                debugPrint("received data for plot: " + str(arr))
                # get x-axis values
                if lineId not in staticLines:
                    staticLines[lineId] = {}
                    idx = len(lines)
                    lineIdToIndex[lineId] = idx 
                    debugPrint("ADDING EXTRA LINE to lines with names " + str(lineNames))
                    lineNames.append(lineId)
                    subplotForLine.append(subplotId)
                    if subplotId not in subplotIds:
                        ax = addAx(subplotId)
                    else:
                        ax = axs[subplotId]
                    addLine(ax, idx)
                    debugPrint("adding line " + lineNames[idx] + " for subplot " + subplotId + ", ax=" + str(ax))
                    ax.legend(loc="upper right")

                staticLines[lineId][0] = [arr[i] for i in range(0,len(arr)) if np.mod(i,plotDimension) == 0]
                staticLines[lineId][1] = [arr[i] for i in range(0,len(arr)) if np.mod(i,plotDimension) == 1]
                lines[lineIdToIndex[lineId]].set_data(staticLines[lineId][0], staticLines[lineId][1])
                updateBounds(subplotId, staticLines[lineId][0], staticLines[lineId][1])

                #yield 
        except RuntimeError:
            #print "Socket connection terminated"
            break
    debugPrint("finished yielding data")

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
    debugPrint("dataFromTable()")
    cnt = 0
    while cnt < 100:
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

    fileData = PlotDataReader(fileName)
    if fileData == None or fileData.data == None or len(fileData.data) == 0:
        print("No data found for " + fileName)
        return
    #numLines = sum([ len([lineData for lineData in subplotData.values()]) for subplotData in fileData.data.values() ])

    lineNames = []
    subplotForLine = []
    subplotIds = set()
    xdata = []
    ydata = []
    for subplotItem in fileData.data.items():
        subplotIds.add(subplotItem[0])
        for lineItems in subplotItem[1].items():
            lineNames.append(lineItems[0])
            subplotForLine.append(subplotItem[0])
            #i = len(lineNames) - 1
            lineData = lineItems[1]
            xdata.append(lineData.keys())
            ydata.append(lineData.values())
    numLines = len(lineNames)
    debugPrint( "numLines = " + str(numLines) )
    debugPrint( "lineNames = " + str(lineNames))
    debugPrint( "subplotIds = " + str(subplotIds))
    return

# TODO - here's where simulation code with timescaling and sleeps would go.
def dataFromFile():
    return # TODO
    global timeNow
    global fileName
    global fileData
    global numLines
    debugPrint("dataFromFile()")

    if fileData == None or fileData.data == None or len(fileData.data) == 0:
        print("No data found for " + fileName)
        return

    timeNow = fileData.data.keys()[0]
    done = False
    while not done:
        for subplotItem in fileData.data.items():
            for lineItems in subplotItem[1]:
                lineData = lineItems[1]
        yield timeNow, [table[timeNow][i] for i in range(numLines)]
        debugPrint("tried to yield data for plot")

def moveNowLine(nowLine):
    global timeNow
    #global nowLines

    xcoords = [timeNow, timeNow] #[timeNow-0.00001, timeNow+0.00001]
    ycoords = [-1e20, 1e20]
    debugPrint("moveNowLine: " + str(xcoords) + ", " + str(ycoords))
    nowLine.set_data(xcoords, ycoords)

def addNowLine(ax):
    global staticLines
    global lines
    global nowLines
    global timeNow
    
    #lineId = -1.010101 # something random that we hope is not used elsewhere - HACK
    #staticLines[lineId] = {}
    #lineIdToIndex[lineId] = len(lines)
    lineNames.append('_nolegend_')
    nowLine = addLine(ax, None)
    nowLines.append(nowLine)
    #staticLines[lineId][0] = [timeNow-0.00001, timeNow+0.00001]
    #staticLines[lineId][1] = [arr[i] for i in range(1,len(arr)) if np.mod(i,plotDimension) == 0]
    moveNowLine(nowLine)

def initializePlotBounds():
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
    global timeNow
    global lineIdToIndex
    global subplotForLine

    xmin = {}
    xmax = {}
    ymin = {}
    ymax = {}
    
    for subId in axs.keys():
        ax = axs[subId]
    
        xmin[subId], xmax[subId] = ax.get_xlim()
        ymin[subId], ymax[subId] = ax.get_ylim()
        
        # find min/max for x-axis
        if centerAtNow:
            # the limits on the x-axis should include the horizon duration
            xmin[subId] = timeNow - horizonDurationHours / 2 
            xmax[subId] = timeNow + horizonDurationHours / 2
        if zoomToFitX:
            xdat = [xdata[i] for i in range(len(xdata)) if subplotForLine[i] == subId]
            if ( staticLines != None and \
                 len(staticLines) > 0 and \
                 len(staticLines.values()) > 0 and \
                 len(staticLines.values()[0]) > 0 ):
                xdat.append([staticLines[dat][0] for dat in staticLines if subplotForLine[lineIdToIndex[dat]] == subId])
            updateBounds(subId, xdat, None, False)
#                xDataMaxStatic = myMax(statDat)
#                xDataMinStatic = myMin(statDat)
#                xDataMax = myMax([xDataMax, xDataMaxStatic])
#                xDataMin = myMax([xDataMin, xDataMinStatic])
#            xDataMax = myMax(xdat)
#            xDataMin = myMin(xdat)
#            if xDataMax != xDataMin:
#                #xmin = xDataMin - xGrow*(xDataMax - xDataMin)
#                xmax[subId] = xDataMax + xGrow*(xDataMax - xDataMin)
#            else:
#                if xDataMax >= xmax[subId]:
#                    xmax[subId] = myMax((xDataMax, xmax[subId] + xGrow*(xmax[subId] - xmin[subId])))
#                if xDataMin < xmin[subId]:
#                    xmin[subId] = myMin((xDataMin, xmin[subId] - xGrow*(xmax[subId] - xmin[subId])))
    
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
#                yDataMaxStatic = myMax([staticLines[dat][1] for dat in staticLines])
#                yDataMinStatic = myMin([staticLines[dat][1] for dat in staticLines])
#                yDataMax = myMax([yDataMax, yDataMaxStatic])
#                yDataMin = myMin([yDataMin, yDataMinStatic])
                yvals.append([staticLines[dat][1] for dat in staticLines if subplotForLine[lineIdToIndex[dat]] == subId])
            updateBounds(subId, None, yvals, False)
#            if yDataMax != yDataMin:
#                ymin[subId] = yDataMin - yGrow*(yDataMax - yDataMin)
#                ymax[subId] = yDataMax + yGrow*(yDataMax - yDataMin)
#            else:
#                if yDataMax >= ymax[subId]:
#                    ymax[subId] = myMax((yDataMax, ymax[subId] + yGrow*(ymax[subId] - ymin[subId])))
#                if yDataMin < ymin:
#                    ymin[subId] = myMin((yDataMin, ymin[subId] - yGrow*(ymax[subId] - ymin[subId])))
    if axs.keys() != None and len(axs.keys()) > 0:
        for subId in axs.keys():
            updateBounds(subId, None, None, True)
    interpolateForAllX()
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
    else:
        t,y = data

    if xmin == None:
        initializePlotBounds()
    elif y != None:
        if len(y) > 0:
            updateBounds(subplotForLine[0], [t], None)
        for i in xrange(len(y)):
            updateBounds(subplotForLine[i], None, [y[i]])

    if data == None:
        return
    debugPrint("try to update data for plot")
    # update the plot data
    for i in range(numLines):
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
    
        if showLabels:
            plt.annotate(('%0.3f' % (y[i])), (t,y[i]))

    replaceInitValues = False

    # update nowLines
    for nowLine in nowLines:
        moveNowLine(nowLine)


def addLine(ax = None, index = None):
    global axs
    global fig
    global lines
    
    # specify options for plot data display
    colors =  ['r', 'g', 'b', 'm', 'c', 'k','orange','purple','pink','grey','lime','aqua','maroon','navy']
    #symbols = ['-','--','-.',':','.',',','_','o', 'v', '^', 's', 'p', '*', '+', 'x']
    symbols = ['^', 'd', '*', '+', 'x','o']
    msizes =  [ 12, 12, 12, 12, 12,  12,  10,  4, 16, 18,  8, 18,  20] 
    
    if ax == None and axs != None and len(axs) > 0:
        return None
        #ax = axs.values()[0]
    if ax == None:
        return None
    n = len(lines)
    col = colors[index % len(colors)] if index else 'y'
    mrk = symbols[index % len(symbols)] if index else "*"
    newLine = ax.plot([0], [0], "-", lw=4, \
                      markeredgecolor=colors[n % len(colors)], \
                      markeredgewidth=1, markersize=7, \
                      markerfacecolor='y',color = col,marker=mrk)[0]
    if index != None:
        newLine.set_label(str(lineNames[index]))
    else:
        index = n
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
    for _ in xrange(numLines):
        if subplotForLine[ii] == subplotId:
            debugPrint("adding line " + lineNames[ii] + " for subplot " + subplotId + ", ax=" + str(ax))
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
    
    for mode in dataModes:
        exec("global " + mode)

    numDataModesChosen = sum([(1 if eval(x) else 0) for x in dataModes])

    if numDataModesChosen == 0:
        exec(dataModes[0] + " = True")
        return dataModes[0]

    if numDataModesChosen > 1:
        print "Warning! Multiple data source modes chosen!"
    for mode in dataModes:
        if eval(mode):
            if selectedMode == None:
                if numDataModesChosen == 1:
                    return mode
                selectedMode = mode
            else:
                exec(mode + " = False")

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

    for mode in dataModes:
        exec mode + ' = False' in globals()

    if argv != None and len(argv) >= 2:
        for arg in argv[1:]:
            if arg in modes:
                exec arg + ' = True' in globals()
            elif arg.isdigit():
                port = arg
            else: # fileName must not be just digits
                fileName = arg
                debugPrint("setting file to read to " + fileName)

    numDataModesChosen = sum([(1 if eval(x) else 0) for x in dataModes])
    
    if numDataModesChosen == 0:
        exec selectedMode + " = True" in globals()
    else:
        selectedMode = pickDataMode(dataModes)

    for mode in modes:
        print(mode + " = " + str(eval(mode)))

    if useSocket:
        if port == None:
            for arg in argv: #.reverse():
                if str(arg).isdigit():
                    port = int(arg)
                    break
        if port == None:
            port = defaultPort
            debugPrint("using default port = " + str(port) )
        else:
            debugPrint("got arg for port = " + str(port) )

    return

def myMin(a):
    if type(a) not in [list, tuple, set]:
        return a
    else:
        minnest = None
        for x in a:
            z = myMin(x)
            if minnest == None or z < minnest:
                minnest = z
        return minnest

def myMax(a):
    if type(a) not in [list, tuple, set]:
        return a
    else:
        maxxest = None
        for x in a:
            z = myMax(x)
            if maxxest == None or z > maxxest:
                maxxest = z
        return maxxest

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

    if argv is None:
        argv = sys.argv
    debugPrint( "argv = " + str(argv) )
    port = None
    handleCommandLineArgs(argv);
    fileData = None

    if useSocket:
        # connect with data source for plot
        initSocket( host, port )
    
    elif useFile:
        initFileData()
    
    if numLines < 1:
        print("no lines!")
        return

    # create plot figure
    fig = plt.figure(figsize=(25.0,6.0))
    fig.subplots_adjust(hspace=0.5)
    for subplotId in subplotIds:
        addAx(subplotId)
    for ax in axs.values():
        addNowLine(ax)

    if xdata == None:
        xdata = [[0] for _ in xrange(numLines)] #can't be empty
        ydata = [[0] for _ in xrange(numLines)]
        replaceInitValues = True

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
        
        
        debugPrint( "data = " + str(data) )
        
        updateData(data)

#        # zoom the axes to fit all data according to the zoom options
#        
#        xmin = myMin([ax.get_xlim()[0] for ax in axs.values()])
#        xmax = myMax([ax.get_xlim()[1] for ax in axs.values()])
#        ymin = myMin([ax.get_ylim()[0] for ax in axs.values()])
#        ymax = myMax([ax.get_ylim()[1] for ax in axs.values()])
#        #xmin, xmax = ax.get_xlim()
#        #ymin, ymax = ax.get_ylim()
#
#        # find min/max for x-axis
#        if centerAtNow:
#            # the limits on the x-axis should include the horizon duration
#            xmin = timeNow - horizonDurationHours / 2 
#            xmax = timeNow + horizonDurationHours / 2
#        if zoomToFitX:
#            xDataMax = myMax(xdata)
#            xDataMin = myMin(xdata)
#            if ( staticLines != None and \
#                 len(staticLines) > 0 and \
#                 len(staticLines.values()) > 0 and \
#                 len(staticLines.values()[0]) > 0 ):
#                xDataMaxStatic = myMax([staticLines[dat][0] for dat in staticLines])
#                xDataMinStatic = myMin([staticLines[dat][0] for dat in staticLines])
#                xDataMax = myMax([xDataMax, xDataMaxStatic])
#                xDataMin = myMax([xDataMin, xDataMinStatic])
#            if xDataMax != xDataMin:
#                #xmin = xDataMin - xGrow*(xDataMax - xDataMin)
#                xmax = xDataMax + xGrow*(xDataMax - xDataMin)
#            else:
#                if xDataMax >= xmax:
#                    xmax = myMax((xDataMax, xmax + xGrow*(xmax - xmin)))
#                if xDataMin < xmin:
#                    xmin = myMin((xDataMin, xmin - xGrow*(xmax - xmin)))
#
#        # find min/max for y-axis
#        if zoomToFitY:
#            yvals = [[ydata[i][j] for j in range(len(ydata[i])) \
#                      if ((not zoomToFitOnlyVisibleY) or \
#                          (xdata[i][j] >= xmin and xdata[i][j] <= xmax)) ] \
#                     for i in range(len(ydata))]
#            yDataMax = myMax(yvals)
#            yDataMin = myMin(yvals)
#            if ( staticLines != None and \
#                 len(staticLines) > 0 and \
#                 len(staticLines.values()) > 0 and \
#                 len(staticLines.values()[0]) > 1 ):
#                yDataMaxStatic = myMax([staticLines[dat][1] for dat in staticLines])
#                yDataMinStatic = myMin([staticLines[dat][1] for dat in staticLines])
#                yDataMax = myMax([yDataMax, yDataMaxStatic])
#                yDataMin = myMin([yDataMin, yDataMinStatic])
#            if yDataMax != yDataMin:
#                ymin = yDataMin - yGrow*(yDataMax - yDataMin)
#                ymax = yDataMax + yGrow*(yDataMax - yDataMin)
#            else:
#                if yDataMax >= ymax:
#                    ymax = myMax((yDataMax, ymax + yGrow*(ymax - ymin)))
#                if yDataMin < ymin:
#                    ymin = myMin((yDataMin, ymin - yGrow*(ymax - ymin)))
#
#        if zoomToFitX or centerAtNow:
#            for subId, ax in axs.items():
#                ax.set_xlim(xmin[subId], xmax[subId])
#        if zoomToFitY:
#            for subId, ax in axs.items():
#                ax.set_ylim(ymin[subId], ymax[subId])
            
        debugPrint("xdata=" + str(xdata))
        debugPrint("ydata=" + str(ydata))
        debugPrint("lines=" + str([y.get_data() for y in lines.values()]))
        debugPrint("staticLines=" + str(staticLines))

        for ax in axs.values():
            for line in ax.get_lines():
                print("x:" + str(line.get_xdata()))
                print("y:" + str(line.get_ydata()))
            try:
                ax.figure.canvas.draw()
            except:
                print("error")

        debugPrint("updated lines = " + str([line.get_data() for line in lines.values()]))
        return lines.values()

        # end of run()

    gen = testDataGen
    if useSocket:
        gen = socketDataGen
    elif useTable:
        gen = dataFromTable
    elif useFile:
        gen = dataFromFile
        
    if useFile:
        run()
    else:
        # must store return value, even if unused, or else it will stop plotting after the first point
        ani = animation.FuncAnimation(fig, run, gen, blit=True, interval=1, repeat=False)
    plt.show()
    print("done with main()")
    #end of main()

if __name__ == "__main__":
    #print "I am running."
    main(sys.argv)
