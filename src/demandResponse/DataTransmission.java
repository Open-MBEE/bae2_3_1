/**
 * 
 */
package demandResponse;

import gov.nasa.jpl.ae.event.DurativeEvent;
import gov.nasa.jpl.ae.event.Parameter;

/**
 * @author bclement
 *
 */
public class DataTransmission extends DurativeEvent {

	private Service source;
	private Service target;
	private Data dataSent;

	/**
	 * @param source
	 * @param target
	 * @param dataSent
	 */
	public DataTransmission(Service src, Service tgt, Data ds) {
		super();
		this.source = src;
		this.target = tgt;
		this.dataSent = ds;
		DataReceivedState drs = target.getDataReceivedState();
		DataSentState dss = source.getDataSentState();
    Parameter< DataReceivedState > drsParam =
        new Parameter< DataReceivedState >( "dataReceivedState", null, drs, this );
    Parameter< DataSentState > dssParam =
        new Parameter< DataSentState >( "dataSentState", null, dss, this );
		try {
			// TODO -- should not be null object -- execution should supply this
			addEffect(drsParam, null, DataSet.class.getMethod("add", Data.class), (Object)dataSent);
			addEffect(dssParam, null, DataSet.class.getMethod("changeTo", String.class), (Object)new String("sending"));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

@Override
	public void execute() {
		// TODO -- need to apply effect at some Timepoint on some TimeVarying
  	}

}
