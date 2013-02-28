#handy python things
import time,sys,traceback,re

#some MD utils. 
from com.nomagic.magicdraw.core import * #application, project...
from com.nomagic.magicdraw.core import Application #this seems to want its own import.

from javax.swing import JOptionPane

import MPUtils
reload (MPUtils)

global gl
gl = Application.getInstance().getGUILog()
        

def run(s):
	#get the user's selection - the element that should be top level and contain (recursively) all other systems/behaviors you wish to reason about.
	selectedNode1 = Application.getInstance().getMainFrame().getBrowser().getActiveTree().getSelectedNodes()[0]
	#gl.log(str(dir(selectedNode1)))
	firstSelected = selectedNode1.getUserObject()
	#gl.log(str(dir(firstSelected)))
	gl.log( "selected name = " + firstSelected.name )
	gl.log( "selected id = " + str(firstSelected.getID()) )


