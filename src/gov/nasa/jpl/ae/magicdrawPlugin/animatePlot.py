#import os
import sys
#print "PYTHONPATH = " + str(os.getenv("PYTHONPATH"))
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation
from InterpolatedMap import InterpolatedMap as InterpolatedMap
from OneWaySocket import OneWaySocket

debugMode = True
useSocket = True
useTable = False
useTestData = False

genXValues = True

zoomToFitX = False
zoomToFitY = True
zoomToFitOnlyVisibleY = True
centerAtNow = True
horizonDurationHours = 24
timeNow = horizonDurationHours / 2
nowLine = None

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
ax = None
lines = []
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
        print s

# create the socket, and get numLines, the number of lines to plot!
def initSocket( host, port ):
    if useSocket:
        global sock
        global numLines
        sock = OneWaySocket(host, port, False, debugMode)
        sock.endianGet()
        numLines = sock.unpack("i", sock.receiveInPieces(4))
        numLines = numLines[0]
        debugPrint( "numLines = " + str(numLines) )


def socketDataGen():
    global sock
    global lines
    global lineIdToIndex
    global numLines
    global timeNow
    global nowLine
    
    cnt = 0
    if genXValues:
        yRange = range(1,numLines+1)
    else:
        yRange = range(numLines)
    #yield 0, [0.0 for n in yRange]
    while 1:
        try:
            debugPrint("try to receive data for plot")
            arr = sock.receive()
            debugPrint("received data for plot: " + str(arr))
            if arr == None:
                break
            expectedLength = numLines + 1
            if genXValues and len(arr) > 0:
                xVal = arr[0]
            else:
                expectedLength += 1
                xVal = cnt
            if arr == None:
                break
            else:
                if len(arr) != expectedLength: # HACK -- what if the size were coincidentally the same?
                    debugPrint("static lines")
                    # We are receiving pairs of x,y values to plot.
                    # The first number in the array is an id for plot line, so
                    # we can replace it if it's an update.
                    lineId = arr[0]
                    # REVIEW -- we could also put in a dimension for 3D plots.
                    plotDimension = 2
                    # get x-axis values
                    if lineId not in staticLines:
                        staticLines[lineId] = {}
                        lineIdToIndex[lineId] = len(lines)
                        addLine()
                    staticLines[lineId][0] = [arr[i] for i in range(1,len(arr)) if np.mod(i,plotDimension) == 1]
                    staticLines[lineId][1] = [arr[i] for i in range(1,len(arr)) if np.mod(i,plotDimension) == 0]
                    lines[lineIdToIndex[lineId]].set_data(staticLines[lineId][0], staticLines[lineId][1])
                    #yield 
                else:
                    timeNow = xVal
                    yield xVal, [arr[i] for i in yRange] #[ arr[13], arr[14], arr[15] ]
                    cnt+=1
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

def moveNowLine():
    global timeNow
    global nowLine
    xcoords = [timeNow, timeNow] #[timeNow-0.00001, timeNow+0.00001]
    ycoords = [-1e20, 1e20]
    debugPrint("moveNowLine: " + str(xcoords) + ", " + str(ycoords))
    nowLine.set_data(xcoords, ycoords)

def addNowLine():
    global staticLines
    global lines
    global nowLine
    global timeNow
    
    #lineId = -1.010101 # something random that we hope is not used elsewhere - HACK
    #staticLines[lineId] = {}
    #lineIdToIndex[lineId] = len(lines)
    nowLine = addLine()
    #staticLines[lineId][0] = [timeNow-0.00001, timeNow+0.00001]
    #staticLines[lineId][1] = [arr[i] for i in range(1,len(arr)) if np.mod(i,plotDimension) == 0]
    moveNowLine()
    
def updateData(data):
    global numLines
    global xdata
    global ydata
    global replaceInitValues
    global lines
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

def addLine():
    global ax
    global fig
    global lines

    # specify options for plot data display
    colors =  ['r', 'g', 'b', 'm', 'c', 'y', 'k', 'w']
    #symbols = ['-','--','-.',':','.',',','_','o', 'v', '^', 's', 'p', '*', '+', 'x']
    symbols = ['-','-','-','-','-','--','-.',':','.',',','_','o', 'v', '^', 's', 'p', '*', '+', 'x']
    msizes =  [ 12, 12, 12, 12, 12,  12,  10,  4, 16, 18,  8, 18,  20] 
    
    n = len(lines)
    newLine = ax.plot([0], [0], symbols[n % len(symbols)], lw=4, \
                      markeredgecolor=colors[n % len(colors)], \
                      markeredgewidth=2, markersize=msizes[n % len(msizes)], \
                      markerfacecolor='None')[0]
    lines.append( newLine )
    debugPrint("new line = " + str(newLine))
    return newLine

#
# Main
#
def main(argv=None):
    global numLines
    global xdata
    global ydata
    global replaceInitValues
    global lines
    global ax
    global fig

    # get port from args
    if argv is None:
        argv = sys.argv
    debugPrint( "argv = " + str(argv) )
    if len(argv) > 1 and str(argv[1]).isdigit():
        port = int(argv[1])
        debugPrint("got arg for port = " + str(port) )
    else:
        port = defaultPort
        debugPrint("using default port = " + str(port) )
    if numLines < 1:
        return
    
    # connect with plotter
    initSocket( host, port )
    
    # create plot figure
    fig = plt.figure()
    ax = fig.add_subplot(111)
    debugPrint( "xrange(numLines) = " + str(xrange(numLines)) )
    for _ in xrange(numLines):
        addLine()
    addNowLine()
    ax.set_ylim(-1.1, 1.1)
    ax.set_xlim(0, 5)
    ax.grid()
    xdata = [[0] for n in xrange(numLines)] #can't be empty
    ydata = [[0] for n in xrange(numLines)]
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
        global ax
        global fig
        global zoomToFitX
        global zoomToFitY
        global zoomToFitOnlyVisibleY
        global centerAtNow
        global horizonDurationHours
        global timeNow
        global lineNow

        global showLabels
        
        
        debugPrint( "data = " + str(data) )
        
        updateData(data)
        moveNowLine()

        replaceInitValues = False
        
        # zoom the axes to fit all data according to the zoom options
        
        xmin, xmax = ax.get_xlim()
        ymin, ymax = ax.get_ylim()

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
            ax.set_xlim(xmin, xmax)
        if zoomToFitY:
            ax.set_ylim(ymin, ymax)
            
        ax.figure.canvas.draw()

        debugPrint("updated lines = " + str([line.get_data() for line in lines]))
        return lines

    gen = testDataGen
    if useSocket:
        gen = socketDataGen
    else:
        if useTable:
            gen = dataFromTable

    # must store return value, even if unused, or else it will stop plotting after the first point
    ani = animation.FuncAnimation(fig, run, gen, blit=True, interval=1, repeat=False)
    plt.show()


if __name__ == "__main__":
    #print "I am running."
    main()
