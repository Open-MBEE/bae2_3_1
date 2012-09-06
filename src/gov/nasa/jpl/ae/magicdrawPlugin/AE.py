'''
Created on Sep 5, 2012

@author: bclement
'''

from gov.nasa.jpl.ae.tests import TestEventXmlToJava
##TestEventXmlToJava as TestEventXmlToJava
#from generated import TestEventXmlToJava as TestEventXmlToJava
from gov.nasa.jpl.ae.util import Utils
from java.util import TreeSet as TreeSet
import os
import sys


print "Hello world!"

#xml2java = TestEventXmlToJava()
#xml2java.main(None)

#Main.main(None)
os.chdir('c:\\Users\\bclement\\workspace\\CS')
print TestEventXmlToJava.main([])
print Utils.intCompare(3, 4)
#utils = 
#Utils.toInteger("3")


foo = TreeSet()
foo.add("hello")
foo.add("world")

print foo

print "Goodbye world!"

if __name__ == '__main__':
    pass