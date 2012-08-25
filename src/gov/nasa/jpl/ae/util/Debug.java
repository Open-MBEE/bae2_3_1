/**
 * 
 */
package gov.nasa.jpl.ae.util;

/**
 * @author bclement
 *
 */
public class Debug {
  protected static boolean on = false;
  public static synchronized void turnOn() {
    on = true;
  }
  public static synchronized void turnOff() {
    on = false;
  }
  public static void out( String s ) {
    if (on) System.out.print( s );
  }
  public static void outln( String s ) {
    if (on) System.out.println( s );
  }
  public static void err( String s ) {
    if (on) System.err.print( s );
  }
  public static void errln( String s ) {
    if (on) System.err.println( s );
  }
}
