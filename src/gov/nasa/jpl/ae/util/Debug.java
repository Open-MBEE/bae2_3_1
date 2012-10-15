/**
 * 
 */
package gov.nasa.jpl.ae.util;

import java.lang.reflect.InvocationTargetException;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.GUILog;
/**
 *
 */
public class Debug {
  protected static boolean on = true;
  public static GUILog gl = null;//getGuiLog();
  public static StringBuffer glBuf = new StringBuffer(); 
  public static StringBuffer glErrBuf = new StringBuffer();
  
  public static GUILog getGuiLog() {
    Application app = Application.getInstance();
    if ( app == null ) return null;
    if ( app.getMainFrame() == null ) return null;
    GUILog glt = app.getGUILog();
    if ( glt == null ) return null;
    try {
      glt.log("initializing log");
    } catch (NullPointerException e) {
      glt = null;
      System.out.println("Failed to get MagicDraw GUI log; continuing without.");
    }
    return glt;
  }
  
  public static synchronized void turnOn() {
    on = true;
  }
  public static synchronized void turnOff() {
    on = false;
  }
  public static void out( String s ) {
    if (on) {
      if ( gl == null ) {
        System.out.print( s );
      } else {
        glBuf.append( s );
      }
    }
  }
  public static void outln( String s ) {
    if (on) {
      if ( gl == null ) {
        System.out.println( s );
      } else {
        gl.log( glBuf.toString() + s );
      }
      glBuf = new StringBuffer();
    }
  }
  public static void err( String s ) {
    if (on) {
      if ( gl == null ) {
        System.err.print( s );
      } else {
        glBuf.append( s );
      }
    }
    //if (on) System.err.print( s );
  }
  public static void errln( String s ) {
    if (on) {
      if ( gl == null ) {
        System.err.println( s );
      } else {
        gl.log( "ERR: " + glErrBuf.toString() + s );
      }
      glErrBuf = new StringBuffer();
    }
    //if (on) System.err.println( s );
  }
  public static boolean isOn() {
    return on;
  }
}
