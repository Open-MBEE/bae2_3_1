import re

class PlotDataReader(object):
    '''
    reads plottable values from AE output
    '''


    def __init__(self, fileName):
        self.fileName = fileName
        self.read(fileName)
        self.data = {}
        
#    def __init__(self):
#        pass
        
    def read(self, fileName):
        self.fileName = fileName
        print("reading events from " + fileName)
        self.events = []
        try: f = open(fileName,"r")
        except:
            print("can't find file @ %s" % fileName)
            return
        lastTime = float(0.0)
        latestTime = float(-1.0e20)
        earliestTime = float(1.0e20)
        print( "MagicDrawAnimator2: before simulation, loading events from " + str(fileName) )
        print( "MagicDrawAnimator2: before simulation, loading events from " + str(fileName) )
        for line in f.readlines():
            print("read line = " + line)
            x = re.search("(\d*)[^0-9: \t\n\r\f\v]*\s*:\s*\S*\s*(\S*) -> (\S*)\s*(\S*) ==>",line)
            y = re.search("(\S*) -> (\S*)\s*(\S*) ==>",line)
            if x: 
                eventTime=float(x.groups()[0])
                lastTime = eventTime
                action=x.groups()[2]
                cid = x.groups()[3]
                ctype = x.groups()[1]
                #print("%s %s (%s)" % (action.upper(), cid, ctype))
            elif y:
                eventTime=lastTime
                action=y.groups()[1]
                cid = y.groups()[2]
                ctype = y.groups()[0]
                #print("%s %s (%s)" % (action.upper(), cid, ctype))
            elif line.startswith("---"): 
                print(line)
                continue
            else: continue
            #print("%s %s (%s)" % (action.upper(), cid, ctype))
            if any([x in cid for x in ["Main"]]): 
                #print("    ---> Skipping - can't animate Main")
                continue
            if re.search("(_)?Activity(_.*)?(?!\S)",ctype): 
                #print("    ---> Skipping - can't animate the Activity object!")
                continue
            try:
                if eventTime < earliestTime:
                    earliestTime = eventTime
                if eventTime > latestTime:
                    latestTime = eventTime
                evt = event(eventTime,action,cid,ctype)
                try: self.events.append(evt)
                except: print("NESTED ARGHHHHH")
            except: print("ARRGHHH")
        
        print( "MagicDrawAnimator2: finished loading events from " + str(fileName) )
        print( "MagicDrawAnimator2: finished loading events from " + str(fileName) )
        
