import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation
from InterpolatedMap import InterpolatedMap as InterpolatedMap
from OneWaySocket import OneWaySocket

debugMode = False
useSocket = True
useTable = False
useTestData = False

showLabels = False

numLines = 2
#host = "192.168.1.100"
host = "127.0.0.1"
port = 60002
xGrow = 0.2 # how much to grow the x axis for points outside
yGrow = 0.2 # how much to grow the y axis for points outside

sock = None

fig = None
ax = None
lines = None
xdata = None
ydata = None

table = InterpolatedMap()
table[0]=[3,4,1]
table[10]=[7,4,8]
table[15]=[5,10,1]

if useSocket:
    sock = OneWaySocket(host, port, False, debugMode)
    sock.endianGet()

def debugPrint( s ):
    if debugMode:
        print s

def socketDataGen():
    cnt = 0
    yield 0, [0.0 for n in range(numLines)]
    while 1:
        try:
            x = sock.receive()
            if x != None and len(x) >= numLines:
                yield cnt, [x[i] for i in range(numLines)] #[ x[13], x[14], x[15] ]
            cnt+=1
        except RuntimeError:
            print "Socket connection terminated"
            break

def testDataGen():
    cnt = 0
    while 1:
        yield cnt, [(cnt-n-1.0)/(cnt+n+1.0) for n in range(numLines)]
        cnt+=1

def dataFromTable():
    cnt = 0
    while 1:
        yield cnt, table[cnt]
        cnt+=1

#
# Main loop
#
def main():
    colors =  ['r', 'g', 'b', 'c', 'm', 'y', 'k', 'w']
    symbols = ['o', 'v', '^', 's', 'p', '*', '+', 'x']
    msizes =  [ 12,  10,  14,  16,  18,   8,  18,  20] 
    fig = plt.figure()
    ax = fig.add_subplot(111)
    lines = [ ax.plot([0], [0], symbols[n % len(symbols)], markeredgecolor=colors[n % len(colors)], markeredgewidth=2, markersize=msizes[n % len(msizes)], markerfacecolor='None')[0] for n in xrange(numLines) ]

    ax.set_ylim(-1.1, 1.1)
    ax.set_xlim(0, 5)
    ax.grid()
    xdata = [[0] for n in xrange(numLines)] #can't be empty
    ydata = [[0] for n in xrange(numLines)]

    def run(data):
        debugPrint( "data = " + str(data) )
        t,y = data
        # update the data
        for i in range(numLines):
            if len(xdata[i]) == t+1:
                # overwrite any existing data (like initial values for plot)
                xdata[i][t] = t
                ydata[i][t] = y[i]
            else:
                # add the data to the end of the list
                xdata[i].append(t)
                ydata[i].append(y[i])
            # apply the data to the plot lines
            lines[i].set_data(xdata[i], ydata[i])

            if showLabels:
                plt.annotate(('%0.3f' % (y[i])), (t,y[i]))

        # stretch the axes for points outside the current bounds
        xmin, xmax = ax.get_xlim()
        ymin, ymax = ax.get_ylim()

        xDataMax = np.max([np.max(dat) for dat in xdata])
        xDataMin = np.min([np.min(dat) for dat in xdata])
        yDataMax = np.max([np.max(dat) for dat in ydata])
        yDataMin = np.min([np.min(dat) for dat in ydata])
        if xDataMax != xDataMin:
            #xmin = xDataMin - xGrow*(xDataMax - xDataMin)
            xmax = xDataMax + xGrow*(xDataMax - xDataMin)
        else:
            if xDataMax >= xmax:
                xmax = np.max((xDataMax, xmax + xGrow*(xmax - xmin)))
            if xDataMin < xmin:
                xmin = np.min((xDataMin, xmin - xGrow*(xmax - xmin)))
        if yDataMax != yDataMin:
            ymin = yDataMin - yGrow*(yDataMax - yDataMin)
            ymax = yDataMax + yGrow*(yDataMax - yDataMin)
        else:
            if yDataMax >= ymax:
                ymax = np.max((yDataMax, ymax + yGrow*(ymax - ymin)))
            if y[i] < ymin:
                ymin = np.min((yDataMin, ymin - yGrow*(ymax - ymin)))
        ax.set_xlim(xmin, xmax)
        ax.set_ylim(ymin, ymax)
        ax.figure.canvas.draw()

        #debugPrint "updated lines = " + str([line.get_data() for line in lines])
        return lines

    gen = testDataGen
    if useSocket:
        gen = socketDataGen
    else:
        if useTable:
            gen = dataFromTable

    ani = animation.FuncAnimation(fig, run, gen, blit=True, interval=1, repeat=False)
    plt.show()


if __name__ == "__main__":
    main()
