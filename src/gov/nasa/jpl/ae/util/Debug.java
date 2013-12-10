/**
 * 
 */
package gov.nasa.jpl.ae.util;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 */
public class Debug {
  protected boolean on = false;
  protected static Debug instance;

  /**
   * Specify whether debug logging is turned on or off
   * @param on the new value for on
   */
  public void setOn( boolean on ) { this.on = on; }
  
  /**
   * @return true if on, else false
   */
  public boolean getOn() { return on; }  

  public static synchronized void turnOn() {
    getInstance().setOn( true );
  }
  public static synchronized void turnOff() {
    getInstance().setOn( false );
  }
  
  /**
   * @return the instance
   */
  public static Debug getInstance() {
    if ( instance == null ) instance = new Debug();
    return instance;
  }
  /**
   * @param instance the instance to set
   */
  public static void setInstance( Debug instance ) {
    Debug.instance = instance;
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
    getInstance().log( s, false, false );
  }
  public static void outln( String s ) {
    getInstance().log( s, true, false );
  }
  public static void err( String s ) {
    getInstance().log( s, false, true );
  }
  public static void errln( String s ) {
    getInstance().log( s, true, true );
  }
  public static boolean isOn() {
    return getInstance().on;
  }

  public void log( final String s ) {
    log( s, true, false );
  }
  public void logForce( final String s ) {
    logForce( s, true, false );
  }


  public void log( final String s, final boolean addNewLine,
                   final boolean isErr ) {
      log( s, addNewLine, isErr, false );
  }

  public void log( final String s, final boolean addNewLine,
                   final boolean isErr, boolean force ) {
      if ( !force && !on ) return;
      logForce( s, addNewLine, isErr );
  }
  public void logForce( final String s, final boolean addNewLine,
                        final boolean isErr ) {
    String ps = s;
    PrintStream stream = ( isErr ? System.err : System.out );
    if ( addNewLine ) ps = ps + "\n";
    stream.print( ps );
    stream.flush();
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

  public static String stackTrace() {
      Exception e = new Exception();
      return stackTrace( e );
  }
  public static String stackTrace( Throwable e ) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      return sw.toString();
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
      return errorOnNull( stackTrace, stackTrace, msg, maybeNullObjects );
  }
  public static boolean errorOnNull( boolean forceOutput, boolean stackTrace, String msg,
                                     Object... maybeNullObjects ) {
    try {
      if ( maybeNullObjects == null ) throw new Exception();
      for ( Object o : maybeNullObjects ) {
        if ( o == null ) {
          throw new Exception();
        }
      }
    } catch ( Exception e ) {
      boolean wasOn = isOn();
      if ( forceOutput ) turnOn();
      Debug.errln( msg );
      if ( stackTrace ) {
        String stackTraceString = stackTrace(e);
        Debug.errln( stackTraceString );
        Debug.err( "" ); // good place for a breakpoint
        //breakpoint();
      }
      if ( forceOutput && !wasOn ) turnOff(); 
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
  public static void error( boolean forceOutput, boolean stackTrace, String msg ) {
    errorOnNull( forceOutput, stackTrace, msg, (Object[])null );
  }
}
