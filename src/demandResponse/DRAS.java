/**
 * 
 */
package demandResponse;

/**
 * @author bclement
 *
 */
public class DRAS extends Computer implements Service {

	/**
	 * @param name
	 */
	public DRAS(String name) {
		super(name);
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
