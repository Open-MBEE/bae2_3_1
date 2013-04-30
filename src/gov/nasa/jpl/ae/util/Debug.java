/**
 * 
 */
package gov.nasa.jpl.ae.util;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.GUILog;
/**
 *
 */
public class Debug {
  protected static boolean on = false;
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
  
  public static synchronized void turnOn() {
    on = true;
  }
  public static synchronized void turnOff() {
    on = false;
  }
  
  /**
   * Place a breakpoint here and call breakpoint() wherever you need to add a
   * command to break on. For example:<br>
   * {@code if ( input.equals("x") ) Debug.breakpoint();}<br>
   * This makes it easy to clean up after debugging since you can show the Call
   * Hierarchy (Ctrl-Alt-h while breakpoint() is selected) to see where it's
   * being called.
   */
  public static void breakpoint() {
    if ( Debug.isOn() ) out( "" );
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
    //if (on) System.err.print( s );
  }
  public static void errln( String s ) {
    if (on) {
      if ( gl != null ) {
        gl.log( "ERR: " + glErrBuf.toString() + s );
      }
      System.err.println( s );
      glErrBuf = new StringBuffer();
    }
    //if (on) System.err.println( s );
  }
  public static boolean isOn() {
    return on;
  }

  /**
   * Throws and catches an exception and prints a supplied message and stack
   * trace to stderr if any of the input objects are null.
   * 
   * @param msg
   * @param maybeNullObjects
   *          variable number of Objects to check if null
   * @return
   */
  public static boolean errorOnNull( String msg, Object... maybeNullObjects ) {
    return errorOnNull( true, msg, maybeNullObjects );
  }

  /**
   * Throws and catches an exception if any of the input objects are null. It
   * prints a supplied message and, optionally, a stack trace to stderr.
   * 
   * @param msg
   * @param maybeNullObjects
   *          variable number of Objects to check if null
   * @return
   */
  public static boolean errorOnNull( boolean stackTrace, String msg,
                                     Object... maybeNullObjects ) {
    try {
      if ( maybeNullObjects == null ) throw new Exception();
      for ( Object o : maybeNullObjects ) {
        if ( o == null ) {
          throw new Exception();
        } 
      }
    } catch ( Exception e ) {
      System.err.println( msg );
      if ( stackTrace ) {
        e.printStackTrace();
        if ( Debug.isOn() ) Debug.err( "" ); // good place for a breakpoint
      }
      return true;
    }
    return false;
  }

  /**
   * Writes to stderr and throws and catches an exception printing a stack trace.
   * 
   * @param msg
   */
  public static void error( String msg ) {
    error( true, msg );
  }

  /**
   * Writes to stderr and throws and catches an exception, optionally printing a
   * stack trace.
   * 
   * @param msg
   */
  public static void error( boolean stackTrace, String msg ) {
    errorOnNull( stackTrace, msg, (Object[])null );
  }
}
