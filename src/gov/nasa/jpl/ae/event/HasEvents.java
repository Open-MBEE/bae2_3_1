/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.Set;

/**
 *
 */
public interface HasEvents {
	/**
	 * Get any event members; if deep, get members' event members.
	 * @param deep
	 * @param seen
	 * @return
	 */
	public Set<Event> getEvents( boolean deep, Set< HasEvents > seen );
}
