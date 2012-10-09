/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.Set;

/**
 * @author bclement
 *
 */
public interface Groundable {
	boolean isGrounded(boolean deep, Set< Groundable > seen);
	boolean ground(boolean deep, Set< Groundable > seen);
}
