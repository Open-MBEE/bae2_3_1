/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import java.util.Set;

/**
 * @author bclement
 *
 */
public interface Satisfiable extends HasId {
	public boolean isSatisfied(boolean deep, Set< Satisfiable > seen);
	public boolean satisfy(boolean deep, Set< Satisfiable > seen);
}
