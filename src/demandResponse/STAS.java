/**
 * 
 */
package demandResponse;

/**
 * @author bclement
 *
 */
public class STAS extends Computer implements Service {
	DataExchange sendCustomerList;
	DRCustomerList customerList;
	
	public STAS( DDS dds, GES ges, MDM mdm, DataRepository dataRepository,
			     PiHistorian ph, ECCDispatcher eccDispatcher, DRAS dras) {
		super("STAS");
		sendCustomerList = new DataExchange(this, dds, customerList,
				                            dds.askForCustomerList);
	}
	
	/* (non-Javadoc)
	 * @see demandResponse.Service#getDataReceivedState()
	 */
	@Override
	public DataReceivedState getDataReceivedState() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see demandResponse.Service#getDataSentState()
	 */
	@Override
	public DataSentState getDataSentState() {
		// TODO Auto-generated method stub
		return null;
	}

}
