/**
 * 
 */
package demandResponse;

import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.TimeVaryingPlottableMap;

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
  public DataReceivedState( DataReceivedState s ) {
    super( s );
  }
  
  @Override
  public DataReceivedState clone() {
    DataReceivedState tvm = new DataReceivedState( this );
    return tvm;
  }

  @Override
  public DataReceivedState emptyClone() {
    DataReceivedState tvm = new DataReceivedState( this.getName() );
    return tvm;
  }


  
}
