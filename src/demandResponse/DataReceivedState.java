/**
 * 
 */
package demandResponse;

import gov.nasa.jpl.ae.event.TimeVaryingMap;

/**
 * @author bclement
 *
 * TODO
 */
public class DataReceivedState extends TimeVaryingMap<DataSet> {

  /**
   * 
   */
  private static final long serialVersionUID = 5562241015028035094L;

  /**
   * 
   */
  public DataReceivedState() {
    super( DataReceivedState.class.getSimpleName() );
  }
  public DataReceivedState( String name ) {
    super( name );
  }
  
  
}
