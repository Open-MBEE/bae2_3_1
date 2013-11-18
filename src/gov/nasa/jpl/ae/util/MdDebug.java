package gov.nasa.jpl.ae.util;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.GUILog;

public class MdDebug extends Debug {
    public static GUILog gl = getGuiLog();
    public static StringBuffer glBuf = new StringBuffer(); 
    public static StringBuffer glErrBuf = new StringBuffer();

    public static GUILog getGuiLog() {
      GUILog glt = null;
      try {
        Application app = Application.getInstance();
        if ( app == null ) return null;
        if ( app.getMainFrame() == null ) return null;
        glt = app.getGUILog();
        if ( glt == null ) return null;
        glt.log("initializing log");
      } catch (NoClassDefFoundError e) {
        glt = null;
        System.out.println("Failed to get MagicDraw GUI log; continuing without.");
      } catch (NullPointerException e) {
        glt = null;
        System.out.println("Failed to get MagicDraw GUI log; continuing without.");
      }
      return glt;
    }

    public static void out( String s ) {
      if (on) {
        if ( gl != null ) {
          glBuf.append( s );
        }
        System.out.print( s );
      }
    }
    public static void outln( String s ) {
      if (on) {
        if ( gl != null ) {
          gl.log( glBuf.toString() + s );
        }
        System.out.println( s );
        glBuf = new StringBuffer();
      }
    }
    public static void err( String s ) {
      if (on) {
        if ( gl != null ) {
          glBuf.append( s );
        }
        System.err.print( s );
      }
    }
    public static void errln( String s ) {
      if (on) {
        if ( gl != null ) {
          gl.log( "ERR: " + glErrBuf.toString() + s );
        }
        System.err.println( s );
        glErrBuf = new StringBuffer();
      }
    }

}
