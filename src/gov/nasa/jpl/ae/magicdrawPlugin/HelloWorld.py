'''
Created on Sep 27, 2012

'''

from java.util import TreeSet as TreeSet
from gov.nasa.jpl.ae.tests import Adder
from gov.nasa.jpl.ae.tests import TestJythonAdder
from java.io import File

class HelloWorld(Adder):
    '''
    classdocs
    '''

    sum = int(0)

    def __init__(self):
        '''
        Constructor
        '''
        s = TreeSet()
        s.add( 'hello' )
        s.add( 'world' )
        print s
       
    def add(self, x):
        self.sum += x
        print "sum = " + str(self.sum)
        return self.sum

class Pset(TreeSet):
    foo = 3

import sys
import getopt

class Usage(Exception):
    def __init__(self, msg):
        self.msg = msg

def processArgs( argv ):
    try:
        try:
            opts, args = getopt.getopt( argv[1:], "h", 
                                        [ "help", "foo=","bar=" ] )
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
    h = HelloWorld()
    TestJythonAdder.printInt(7)
    f = File('c:\\console.txt')
    tja = TestJythonAdder(f)
    tja = TestJythonAdder(HelloWorld())
    tja = TestJythonAdder(93)
    tja = TestJythonAdder(HelloWorld(), 39)
    tja1 = TestJythonAdder(h)    
    TestJythonAdder.doAddP(h)
    TestJythonAdder.doAddP(h)
    

def main(argv=None):
    print "Hello"
    if argv is None:
        argv = sys.argv
    retVal = processArgs(argv)
    if retVal == 0:
        doStuff()
    return retVal

if __name__ == "__main__":
    sys.exit(main())
