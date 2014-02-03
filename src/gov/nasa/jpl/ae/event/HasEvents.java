/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.mbee.util.HasId;

import java.util.Set;

/**
 *
 */
public interface HasEvents extends HasId, Deconstructable {
	/**
	 * Get any event members; if deep, get members' event members.
	 * @param deep
	 * @param seen
	 * @return
	 */
	public Set<Event> getEvents( boolean deep, Set< HasEvents > seen );
}
