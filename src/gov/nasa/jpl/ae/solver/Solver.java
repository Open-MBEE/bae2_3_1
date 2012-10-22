/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import java.util.Collection;

/**
 * @author bclement
 * 
 */
public interface Solver {
  public boolean solve( Collection< Constraint > constraints );
  public Collection< Constraint > getUnsatisfiedConstraints();
  public Collection< Constraint > getConstraints();
}
