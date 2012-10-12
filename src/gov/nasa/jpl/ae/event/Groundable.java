/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.Set;

/**
 * A Groundable object can have an unspecified value.
 *
 */
public interface Groundable {
	      /**
   * @param deep
   *          whether the check is made recursively if the object is composed of
   *          other Groundable objects.
   * @param seen
   *          a set of Groundable objects that have already been checked to
   *          avoid resursing deeper into an infinite loop.
   * @return whether this object is assigned a specific value and, if deep,
   *         whether all contained objects are also grounded.
   */
	boolean isGrounded(boolean deep, Set< Groundable > seen);
	
  /**
   * @param deep
   * @param seen
   * @return whether the object and, if deep, its contained Grounadable objects
   *         were successfully grounded. A subsequent call to isGrounded()
   *         should return the same value.
   */
	boolean ground(boolean deep, Set< Groundable > seen);
}
