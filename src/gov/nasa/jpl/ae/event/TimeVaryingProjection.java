/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Utils;

import java.util.Collections;

/**
 * A time-varying map of time-varying maps, which are plottable and projected.
 * 
 *
 */
public class TimeVaryingProjection< V > extends
                                        TimeVaryingPlottableMap< TimeVaryingMap< V > > {

  /**
   * 
   */
  private static final long serialVersionUID = -7124939704592838080L;

//  /**
//   * @param name
//   * @param fileName
//   * @param defaultValue
//   * @param cls
//   */
//  public TimeVaryingProjection( String name, String fileName,
//                                TimeVarying< T > defaultValue,
//                                Class< TimeVarying< T > > cls ) {
//    super( name, fileName, defaultValue, cls, true );
//  }
//
  /**
   * @param name
   * @param fileName
   * @param defaultValue
   * @param cls
   */
  public TimeVaryingProjection( String name, String fileName,
                                V defaultValue,
                                Class< V > cls ) {
    //super( name, fileName, null, null, true );
    super( name, (String)null,
           (TimeVaryingMap<V>)(new TimeVaryingMap< V >( name, fileName, defaultValue, cls )),
                                             (Class<TimeVaryingMap<V>>)(Class<?>)TimeVaryingMap.class,//ClassUtils.getClassForName( "Class<TimeVarying<V>>", "java.lang", false ), //null,//(Class<TimeVaryingMap<V>>)TimeVaryingMap.class,
           true );
  }

  /**
   * @param name
   */
  public TimeVaryingProjection( String name ) {
    super( name );
    this.dataProjected = true;
  }

  /**
   * @param name
   * @param fileName
   */
  public TimeVaryingProjection( String name, String fileName ) {
    super( name, fileName );
    this.dataProjected = true;
  }

  public TimeVaryingProjection( TimeVaryingProjection< V > timeVaryingProjection ) {
    super( timeVaryingProjection );
    for ( java.util.Map.Entry< Parameter< Integer >, TimeVaryingMap< V > > e : entrySet() ) {
      e.setValue( e.getValue().clone() );
    }
  }

  public TimeVaryingProjection<V> clone() {
    TimeVaryingProjection<V> tvm = new TimeVaryingProjection<V>(this);
    return tvm;
  }
  
  public void setValue( Timepoint t1, Timepoint t2, V value ) {
    
    TimeVaryingMap< V > newMap = getValue( t1 );
    if ( newMap == null ) {
      Class<?> cls = ( value == null ? null : value.getClass() );
      newMap = new TimeVaryingPlottableMap< V >( name, null, null, (Class<V>)cls, true );
    }
    newMap.setValue( t2, value );
    super.setValue( t1, newMap );
  }
  
  public V getValue( Timepoint t1, Timepoint t2 ) {
    
    TimeVaryingMap< V > newMap = getValue( t1 );
    if ( newMap == null ) {
      return null;
    }
    return newMap.getValue( t2 );
  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.TimeVaryingMap#getValue(java.lang.Integer)
   */
  @Override
  public TimeVaryingMap< V > getValue( Integer t ) {
    return super.getValue( t );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Wraps#getTypeNameForClassName(java.lang.String)
   */
  @Override
  public String getTypeNameForClassName( String className ) {
    // There should only be one generic parameter of type V
    String parameters = ClassUtils.parameterPartOfName( className, true );
    // must have angle brackets with something in them, "<V>"
    if ( Utils.isNullOrEmpty( parameters ) || parameters.length() < 3 ) parameters = "";
    return "TimeVaryingMap" + parameters;
  }


  
  /**
   * @param args
   */
  public static void main( String[] args ) {
    TimeVaryingProjection< Double > tvp =
        new TimeVaryingProjection< Double >( "tvm", null, 2.0, Double.class );

    Timepoint t0 = new Timepoint(0);
    Timepoint t2 = new Timepoint(2);
    Timepoint t3 = new Timepoint(3);
    Timepoint t4 = new Timepoint(4);
    Timepoint t6 = new Timepoint(6);
    tvp.setValue( t0, t0, 0.0 );
    tvp.setValue( t0, t2, 4.0 );
    tvp.setValue( t0, t4, 16.0 );
    tvp.setValue( t2, t4, 8.0 );
    tvp.setValue( t2, t6, 12.0 );
    tvp.setValue( t3, t4, 6.0 );
    tvp.setValue( t3, t6, 8.0 );

    EventSimulation sim = new EventSimulation( Collections.< Event >emptyList(), 1.0 );
    
    
    //TimeVaryingPlottableMap< Double > m = new 
    // TODO -- reproduce one of the animated plots with updating projections
  }

}
