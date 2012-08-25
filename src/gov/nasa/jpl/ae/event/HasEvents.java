/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.Set;

/**
 * @author bclement
 *
 */
public interface HasEvents {
	// Get any event members; if deep get members' event members
	public Set<Event> getEvents( boolean deep );
}
