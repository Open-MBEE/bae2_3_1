/**
 * 
 */
package gov.nasa.jpl.ae.solver;

/**
 *
 */
public final class Random {
  private static final long seed = 3;  // the magic number
  public static final java.util.Random global = new java.util.Random( seed );
}
