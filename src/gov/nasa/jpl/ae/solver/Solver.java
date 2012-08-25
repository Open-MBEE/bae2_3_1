/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import java.util.Collection;
import java.util.Set;

/**
 * @author bclement
 * 
 */
public interface Solver {
  public boolean solve( Collection< Constraint > constraints );
  public Collection< Constraint > getUnsatisfiedConstraints();
//  public static Collection< Constraint >
//      getUnsatisfiedConstraints( Collection< Constraint > constraints );
}
