/**
 * 
 */
package gov.nasa.jpl.ae.util;

/**
 * @author bclement
 *
 */
public class Utils {
  public static boolean isNullOrEmpty( String s ) {
    return ( s == null || s.isEmpty() ||
             s.trim().toLowerCase().equals( "null" ) );
  }
}
