/**
 * 
 */
package gov.nasa.jpl.ae.event;

/**
 * @author bclement
 *
 */
public class Simulator {

	protected Event event = null;
	/**
	 * 
	 */
	public Simulator() {
	}

	public Simulator( Event event ) {
		this.event = event;
	}
	
//	public Execution simulate() {
//		if ( event == null ) {
//			return null;
//		}
//		Execution ex = new Execution(event);
//		// TODO -- ground the timing and TimeVarying objects in ex
//		ex = event.clone();
//		return ex;
//	}

	public Event simulate() {
		if ( event == null ) {
			return null;
		}
		// TODO -- ground the timing and TimeVarying objects in event
		return event;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
