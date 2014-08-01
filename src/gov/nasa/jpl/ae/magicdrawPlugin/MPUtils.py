from com.nomagic.magicdraw.core import Application #this seems to want its own import.

import traceback,sys

gl = Application.getInstance().getGUILog()

def handleException(msg = None):
    exceptionType, exceptionValue, exceptionTraceback = sys.exc_info()
    messages=traceback.format_exception(exceptionType, exceptionValue, exceptionTraceback)
    for message in messages:
        gl.log(message)
    if msg: gl.log(msg)