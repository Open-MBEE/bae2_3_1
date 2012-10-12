from com.nomagic.magicdraw.core import Application

import os, csv, sys, traceback, re, time, shutil

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


