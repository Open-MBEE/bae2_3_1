import UserDict
import re
import os

class InterpolatedMap(UserDict.IterableUserDict):
    
    @staticmethod
    def getNumPattern():
        #numPattern = r"[-+]?(\d+(\.\d*)?|\.\d+)([eE][-+]?\d+)?"
        numPattern = r"\d+"
        return numPattern
    
    """A map from time to value"""
    # def __init__(self):
    #     self={}
    def __getitem__(self, i=None):
        if i in self.keys():
            return UserDict.IterableUserDict.__getitem__(self, i)
        if len(self.keys()) == 0:
            return None
        sortedKeys = self.keys()
        sortedKeys.sort()
        last = sortedKeys[0]
        if i < 0: return UserDict.IterableUserDict.__getitem__(self, last)
        for k in sortedKeys:
            if last <= i < k:
                return UserDict.IterableUserDict.__getitem__(self, last)
            else:
                last = k
        return UserDict.IterableUserDict.__getitem__(self, sortedKeys[len(sortedKeys)-1])
    
    # TODO:  This needs to be cleaned up
    def getNumParameters(self, command):
        k = self.getKeyOfValue([command,], True)
        assert(k)
        print command + " key = " + str(k)
        commandFound = self[k]
        print "commandFound = " + str(commandFound)
        return len(commandFound)-1
        
    # WARNING: Assumes different format than commands!
    # (HOW TO DEAL WITH THIS?)
    # eg. 'position_, 0, 0, 0.5, 1.5, 3, 4.5, 6, 7.5, 9.5, 12,'
    def getTimeOfFirstOccurrence(self, key, value, getBoundIfNoMatch=False):
        retval = self[key].getKeyOfValue(value, True);
        
        # For now assume it exists
        assert(retval)
        return retval
        
    
    # TODO (TBS):  Document what this does.  I'm still not sure! :) 
    def getKeyOfValue( self, value, getBoundIfNoMatch=False ):
        #sortedKeys
        #lastKey = self
        #for k,v in self
        if len(self.keys()) == 0:
            return None
        sortedKeys = self.keys()
        sortedKeys.sort()
        lastKey = sortedKeys[0]
        isNum = re.match(self.getNumPattern(), str(value))
        isKeyNum = re.match(self.getNumPattern(), str(lastKey))
        floatValue = 0.0
        if isNum:
            floatValue = float(value)
        for k in sortedKeys:
            lastVal = self[lastKey]
            thisVal = self[k]
            if (thisVal == value):
                print "found value=" + str(value) + "; key=" + str(k)
                return k;
            if lastVal <= value < thisVal:
                print "value " + str(value) + " between " + str(lastVal) + " and " + str(thisVal) + "; key between " + str(lastKey) + " and " + str(k);
                if getBoundIfNoMatch:
                    print "returning upper bound = " + str(k)
                    return k
                if isNum:
                    midKey = float(lastKey) + (float(k) - float(lastKey)) * (floatValue - float(lastVal)) / (float(thisVal) - float(lastVal))
                    print "returning key = " + str(midKey)
                    return midKey
                print "found value=" + str(value) + " not foudn between " + str(lastKey) + " and " + str(k)
                return None
            else:
                lastKey = k
        if len(sortedKeys) and getBoundIfNoMatch:
            lastKey = sortedKeys[len(sortedKeys)-1]
            print "return lower bound key = " + lastKey + " for value = " + str(value)
            return lastKey
        print "default return None for value = " + str(value)
        return None
