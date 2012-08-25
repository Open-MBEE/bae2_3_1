/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import java.util.Collection;

/**
 * @author bclement
 *
 */
public interface HasConstraints {
  public Collection< Constraint > getConstraints();
}
