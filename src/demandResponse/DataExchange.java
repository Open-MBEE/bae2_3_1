/**
 * 
 */
package demandResponse;

import gov.nasa.jpl.ae.event.Event;

/**
 * @author bclement
 *
 */
public class DataExchange extends DataTransmission {

	  protected Event invocation; // what causes/caused this exchanged
	  protected DataExchange reply; // reply data sent from the target to the source

	/**
	 * @param source
	 * @param target
	 * @param dataSent
	 */
	public DataExchange(Service source, Service target, Data dataSent, DataExchange reply) {
		super(source, target, dataSent);
		this.reply = reply;
		// TODO Auto-generated constructor stub
	}

}
