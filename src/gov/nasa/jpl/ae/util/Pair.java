/**
 * 
 */
package gov.nasa.jpl.ae.util;

/**
 * @author bclement
 *
 */
public class Pair< T1, T2 > {
  T1 first = null;
  T2 second = null;
  public Pair( T1 f, T2 s ) {
    first = f;
    second = s;
  }
}
