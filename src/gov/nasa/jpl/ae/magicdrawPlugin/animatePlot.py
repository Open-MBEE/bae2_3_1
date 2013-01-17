import os
import sys
print "PYTHONPATH = " + str(os.getenv("PYTHONPATH"))
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation
from InterpolatedMap import InterpolatedMap as InterpolatedMap
from OneWaySocket import OneWaySocket
import string

debugMode = True
useSocket = True
useTable = False
useTestData = False

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

numLines = 4 # the default number of lines to plot

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
    global linenames
    global subplotForLine
    global subplotIds

    sock = OneWaySocket(host, port, False, debugMode)
    sock.endianGet()
    numLines = sock.unpack("i", sock.receiveInPieces(4))
    numLines = int(numLines[0])
    
    linenames = []
    subplotForLine = []
    subplotIds = set()

    for _ in xrange(numLines):
        name = receiveString(sock)
        subplotId = receiveString(sock)
        #linename = sock.unpack(str(nameLength*2)+"c",msg)[0]
        #debugPrint("    unpacked name: %s" % linename)
        #linenames.append(str(linename))
        linenames.append(name)
        subplotForLine.append(subplotId)
        subplotIds.add(subplotId)
    debugPrint( "numLines = " + str(numLines) )
    debugPrint( "linenames = " + str(linenames))


def socketDataGen():
    global sock
    global lines
    global lineIdToIndex
    global numLines
    global linenames
    global subplotForLine
    global subplotIds
    global timeNow
    #global nowLines
    global donePlotting
    global subplotIds
    
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
                    debugPrint("ADDING EXTRA LINE to lines with names " + str(linenames))
                    linenames.append(lineId)
                    subplotForLine.append(subplotId)
                    if subplotId not in subplotIds:
                        ax = addAx(subplotId)
                    else:
                        ax = axs[subplotId]
                    addLine(ax, idx)
                    debugPrint("adding line " + linenames[idx] + " for subplot " + subplotId + ", ax=" + str(ax))
                    ax.legend(loc="upper right")

                staticLines[lineId][0] = [arr[i] for i in range(0,len(arr)) if np.mod(i,plotDimension) == 0]
                staticLines[lineId][1] = [arr[i] for i in range(0,len(arr)) if np.mod(i,plotDimension) == 1]
                lines[lineIdToIndex[lineId]].set_data(staticLines[lineId][0], staticLines[lineId][1])
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
    linenames.append('_nolegend_')
    nowLine = addLine(ax, None)
    nowLines.append(nowLine)
    #staticLines[lineId][0] = [timeNow-0.00001, timeNow+0.00001]
    #staticLines[lineId][1] = [arr[i] for i in range(1,len(arr)) if np.mod(i,plotDimension) == 0]
    moveNowLine(nowLine)
    
def updateData(data):
    global numLines
    global xdata
    global ydata
    global replaceInitValues
    global lines
    global nowLines

    debugPrint("try to update data for plot")
    t,y = data
    # update the plot data
    for i in range(numLines):
        if replaceInitValues:
            # overwrite any existing data (like initial values for plot)
            debugPrint( "xdata[" + str(i) + "][" + str(t) + " = " + str(t) + "]" )
            xdata[i][0] = t
            debugPrint( "ydata[" + str(i) + "][" + str(t) + " = " + str(y[i]) + "]" )
            ydata[i][0] = y[i]
        else:
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
        newLine.set_label(str(linenames[index]))
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
    global linenames
    
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
            debugPrint("adding line " + linenames[ii] + " for subplot " + subplotId + ", ax=" + str(ax))
            addLine(ax, ii)
        ii+=1
    addNowLine(ax)
    ax.set_ylim(-1.1, 1.1)
    ax.set_xlim(-0.005, 5)
    ax.grid()
    ax.set_xlabel("time")
    ax.set_ylabel("kWh")
    ax.set_title(subplotId)
    ax.legend(loc="upper right")
    return ax

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

    if argv is None:
        argv = sys.argv
    debugPrint( "argv = " + str(argv) )

    if useSocket:
        # get port from args
        if len(argv) > 1 and str(argv[1]).isdigit():
            port = int(argv[1])
            debugPrint("got arg for port = " + str(port) )
        else:
            port = defaultPort
            debugPrint("using default port = " + str(port) )
        if numLines < 1:
            return
    
        # connect with data source for plot
        initSocket( host, port )
    
    # create plot figure
    fig = plt.figure(figsize=(25.0,6.0))
    fig.subplots_adjust(hspace=2.0)
    for subplotId in subplotIds:
        addAx(subplotId)

    xdata = [[0] for _ in xrange(numLines)] #can't be empty
    ydata = [[0] for _ in xrange(numLines)]
    replaceInitValues = True

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

    def run(data):
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

        replaceInitValues = False
        
        # zoom the axes to fit all data according to the zoom options
        
        xmin = myMin([ax.get_xlim()[0] for ax in axs.values()])
        xmax = myMax([ax.get_xlim()[1] for ax in axs.values()])
        ymin = myMin([ax.get_ylim()[0] for ax in axs.values()])
        ymax = myMax([ax.get_ylim()[1] for ax in axs.values()])
        #xmin, xmax = ax.get_xlim()
        #ymin, ymax = ax.get_ylim()

        # find min/max for x-axis
        if centerAtNow:
            # the limits on the x-axis should include the horizon duration
            xmin = timeNow - horizonDurationHours / 2 
            xmax = timeNow + horizonDurationHours / 2
        if zoomToFitX:
            xDataMax = myMax(xdata)
            xDataMin = myMin(xdata)
            if ( staticLines != None and \
                 len(staticLines) > 0 and \
                 len(staticLines.values()) > 0 and \
                 len(staticLines.values()[0]) > 0 ):
                xDataMaxStatic = myMax([staticLines[dat][0] for dat in staticLines])
                xDataMinStatic = myMin([staticLines[dat][0] for dat in staticLines])
                xDataMax = myMax([xDataMax, xDataMaxStatic])
                xDataMin = myMax([xDataMin, xDataMinStatic])
            if xDataMax != xDataMin:
                #xmin = xDataMin - xGrow*(xDataMax - xDataMin)
                xmax = xDataMax + xGrow*(xDataMax - xDataMin)
            else:
                if xDataMax >= xmax:
                    xmax = myMax((xDataMax, xmax + xGrow*(xmax - xmin)))
                if xDataMin < xmin:
                    xmin = myMin((xDataMin, xmin - xGrow*(xmax - xmin)))

        # find min/max for y-axis
        if zoomToFitY:
            yvals = [[ydata[i][j] for j in range(len(ydata[i])) \
                      if ((not zoomToFitOnlyVisibleY) or \
                          (xdata[i][j] >= xmin and xdata[i][j] <= xmax)) ] \
                     for i in range(len(ydata))]
            yDataMax = myMax(yvals)
            yDataMin = myMin(yvals)
            if ( staticLines != None and \
                 len(staticLines) > 0 and \
                 len(staticLines.values()) > 0 and \
                 len(staticLines.values()[0]) > 1 ):
                yDataMaxStatic = myMax([staticLines[dat][1] for dat in staticLines])
                yDataMinStatic = myMin([staticLines[dat][1] for dat in staticLines])
                yDataMax = myMax([yDataMax, yDataMaxStatic])
                yDataMin = myMin([yDataMin, yDataMinStatic])
            if yDataMax != yDataMin:
                ymin = yDataMin - yGrow*(yDataMax - yDataMin)
                ymax = yDataMax + yGrow*(yDataMax - yDataMin)
            else:
                if yDataMax >= ymax:
                    ymax = myMax((yDataMax, ymax + yGrow*(ymax - ymin)))
                if yDataMin < ymin:
                    ymin = myMin((yDataMin, ymin - yGrow*(ymax - ymin)))

        if zoomToFitX or centerAtNow:
            for ax in axs.values():
                ax.set_xlim(xmin, xmax)
        if zoomToFitY:
            for ax in axs.values():
                ax.set_ylim(ymin, ymax)
            
        debugPrint("xdata=" + str(xdata))
        debugPrint("ydata=" + str(ydata))
        debugPrint("lines=" + str([y.get_data() for y in lines.values()]))
        debugPrint("staticLines=" + str(staticLines))

        for ax in axs.values():
            ax.figure.canvas.draw()

        debugPrint("updated lines = " + str([line.get_data() for line in lines.values()]))
        return lines.values()

        # end of run()

    gen = testDataGen
    if useSocket:
        gen = socketDataGen
    else:
        if useTable:
            gen = dataFromTable

    # must store return value, even if unused, or else it will stop plotting after the first point
    ani = animation.FuncAnimation(fig, run, gen, blit=True, interval=1, repeat=False)
    plt.show()

    #end of main()

if __name__ == "__main__":
    #print "I am running."
    main()
