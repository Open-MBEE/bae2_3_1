'''
Created on Sep 28, 2012

@author: bclement
'''
import sys
import getopt

class Usage(Exception):
    def __init__(self, msg):
        self.msg = msg

def processArgs( argv ):
    try:
        try:
            opts, args = getopt.getopt( argv[1:], "h", 
                                        [ "help" ] )
            print "opts=" + str(opts)
            print "args=" + str(args)
        except getopt.error, msg:
            raise Usage(msg)
        # process options
        for o, a in opts:
            if o in ("-h", "--help"):
                print __doc__
                sys.exit(0)
        # process arguments
        for arg in args:
            print "got arg = " + arg
    except Usage, err:
        print >>sys.stderr, err.msg
        print >>sys.stderr, "for help use --help"
        return 2
    return 0

def doStuff():
    print 'Hello Python'

def main(argv=None):
    if argv is None:
        argv = sys.argv
    retVal = processArgs(argv)
    if retVal == 0:
        doStuff()
    return retVal

def main2():
    # parse command line options
    try:
        opts, args = getopt.getopt(sys.argv[1:], "h", ["help"])
    except getopt.error, msg:
        print msg
        print "for help use --help"
        sys.exit(2)
    print "opts=" + str(opts)
    print "args=" + str(args)
    # process options
    for o, a in opts:
        if o in ("-h", "--help"):
            print __doc__
            sys.exit(0)
    # process arguments
    for arg in args:
        print "got arg = " + arg
    doStuff()



if __name__ == "__main__":
    sys.exit(main2())
