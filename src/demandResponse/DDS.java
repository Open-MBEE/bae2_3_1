/**
 * 
 */
package demandResponse;

/**
 * @author bclement
 *
 */
public class DDS implements Service {

	public DataExchange askForCustomerList;

	public DDS() {
		// TODO Auto-generated constructor stub
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
