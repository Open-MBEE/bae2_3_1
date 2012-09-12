/**
 * 
 */
package gov.nasa.jpl.ae.fuml;

import java.lang.reflect.Method;

import junit.framework.Assert;

import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.Timepoint;

/**
 * @author bclement
 *
 */
public class ObjectFlow< Obj > extends TimeVaryingMap< Obj > {

  /**
   * @param name
   * @param defaultValue
   */
  public ObjectFlow( String name ) {
    super( name, null );
  }

  public void send( Obj o, Timepoint t ) {
    this.setValue( t, o );
  }
  
  public void send( Obj o, Integer t ) {
    this.setValue( t, o );
  }

  public Obj receive( Timepoint t ) {
    Obj o = this.getValue( t );
    this.setValue( t, null );
    return o;
  }
  
  public Obj receive( Integer t ) {
    Obj o = this.getValue( t );
    this.setValue( t, null );
    return o;
  }
  
  public boolean hasStuff( Timepoint t ) {
    return getValue( t ) != null;
  }

  public boolean hasStuff( Integer t ) {
    return getValue( t ) != null;
  }
}
