/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import java.util.Collection;

/**
 * 
 */
public interface Solver {
  public static final boolean allowPickValue = false;
  public boolean solve( Collection< Constraint > constraints );
  public Collection< Constraint > getUnsatisfiedConstraints();
  public Collection< Constraint > getConstraints();
  public int getNumberOfResolvedConstraints();
  }
