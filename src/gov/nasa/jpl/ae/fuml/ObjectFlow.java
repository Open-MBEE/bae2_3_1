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

  public class foo {
    public class bar {
      Class<?> c = ObjectFlow.foo.bar.class;
      
    }
    Class<?> c = bar.class;
  }
  Class<?> c1 = ObjectFlow.foo.bar.class;
  Class<?> c2 = foo.bar.class;
  //Class<?> c3 = bar.class; doesn't compile
  
  
  /**
   * @param name
   * @param initialValueFunction
   * @param o
   * @param samplePeriod
   * @param horizonDuration
   */
  public ObjectFlow( String name, Method initialValueFunction, Object o,
                     int samplePeriod, int horizonDuration ) {
    super( name );
    Assert.fail( "Error! Constructor not supported! ObjectFlow( String name, Method initialValueFunction, Object o, int samplePeriod, int horizonDuration )" );
  }

  /**
   * @param name
   * @param defaultValue
   */
  public ObjectFlow( String name ) {
    super( name, null );
  }

  public void send( Timepoint t, Obj o ) {
    this.setValue( t, o );
  }
  
  public Obj receive( Timepoint t ) {
    Obj o = this.getValue( t );
    this.setValue( t, null );
    return o;
  }
  
  public boolean hasStuff( Timepoint t ) {
    return getValue( t ) != null;
  }

}
