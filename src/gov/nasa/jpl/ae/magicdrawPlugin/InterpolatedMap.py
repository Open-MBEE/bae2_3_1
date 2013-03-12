from SortedDict import SortedDict
from bisect import bisect_left, bisect_right
import re
import os

class InterpolatedMap(SortedDict):
    

    
    """A map from time to value"""
    def __init__(self):
        super(InterpolatedMap,self).__init__()
        #self={}
        self.interpolationType = "STEP" # legal values are "STEP", "RAMP", and "LINEAR"
        self.attributes = {} # for storing attributes of the map with external meaning
    
    @staticmethod
    def getNumPattern():
        #numPattern = r"[-+]?(\d+(\.\d*)?|\.\d+)([eE][-+]?\d+)?"
        numPattern = r"\d+"
        return numPattern
    
    def __getitem__(self, k=None):
        size = len(self._sorted_keys)
        if size == 0:
            return None
        if k == None:
            return SortedDict.__getitem__(self, self._sorted_keys[0])

        indexOfFirstGreaterOrEqual = bisect_left(self._sorted_keys, k)
        
        if size > indexOfFirstGreaterOrEqual:
            if self._sorted_keys[indexOfFirstGreaterOrEqual] == k:
                return SortedDict.__getitem__(self, k)
        if indexOfFirstGreaterOrEqual == 0:
            return None
        if self.interpolationType == "STEP":
            return SortedDict.__getitem__(self, self._sorted_keys[indexOfFirstGreaterOrEqual-1])
        else:
            # linear interpolation (for both "RAMP" and "LINEAR")
            try:
                t1 = self._sorted_keys[indexOfFirstGreaterOrEqual-1]
                t2 = self._sorted_keys[indexOfFirstGreaterOrEqual]
                v1 = SortedDict.__getitem__(self, t1)
                v2 = SortedDict.__getitem__(self, t2)
                return v1+((v2-v1)*(k-t1))/(t2-t1)
            except:
                # if linear interpolation fails, do step
                return SortedDict.__getitem__(self, self._sorted_keys[indexOfFirstGreaterOrEqual-1])
    
    def getIndexOfNextKey(self, k):
        bisect_right(self._sorted_keys, k)

    def getNextKey(self, k):
        nki = self.getIndexOfNextKey(k)
        if nki >= len(self._sorted_keys):
            return None
        if nki < 0:
            return None
        return self._sorted_keys[nki]

    def getIndexOfFirstEqualOrGreaterKey(self, k):
        bisect_left(self._sorted_keys, k)

    def getPreviousKey(self, k):
        egki = self.getIndexOfFirstEqualOrGreaterKey(k)
        if egki <= 0:
            return None
        if egki > len(self._sorted_keys):
            return None
        return self._sorted_keys[egki-1]
    
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
                print "found value=" + str(value) + " not found between " + str(lastKey) + " and " + str(k)
                return None
            else:
                lastKey = k
        if len(sortedKeys) and getBoundIfNoMatch:
            lastKey = sortedKeys[len(sortedKeys)-1]
            print "return lower bound key = " + lastKey + " for value = " + str(value)
            return lastKey
        print "default return None for value = " + str(value)
        return None
