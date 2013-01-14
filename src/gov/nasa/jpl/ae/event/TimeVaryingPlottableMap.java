/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.lang.reflect.Method;

/**
 * This class is the same as TimeVaryingMap< T > but implements Plottable.
 *
 */
public class TimeVaryingPlottableMap< T > extends TimeVaryingMap< T > implements
                                                                     Plottable {

  /**
   * 
   */
  private static final long serialVersionUID = -897416349437818390L;
  
  protected boolean dataProjected = false;

  /**
   * @param name
   * @param initialValueFunction
   * @param o
   * @param samplePeriod
   * @param horizonDuration
   */
  public TimeVaryingPlottableMap( String name, Method initialValueFunction,
                                  Object o, int samplePeriod,
                                  int horizonDuration ) {
    super( name, initialValueFunction, o, samplePeriod, horizonDuration );
  }

  /**
   * @param name
   * @param initialValueFunction
   * @param o
   * @param samplePeriod
   * @param horizonDuration
   * @param projected
   */
  public TimeVaryingPlottableMap( String name, Method initialValueFunction,
                                  Object o, int samplePeriod,
                                  int horizonDuration, boolean projected ) {
    this( name, initialValueFunction, o, samplePeriod, horizonDuration );
    dataProjected = projected;
  }

  /**
   * @param name
   * @param defaultValue
   */
  public TimeVaryingPlottableMap( String name, T defaultValue ) {
    super( name, defaultValue );
  }

  /**
   * @param name
   * @param defaultValue
   */
  public TimeVaryingPlottableMap( String name, String fileName, T defaultValue ) {
    super( name, fileName, defaultValue );
  }

  /**
   * @param name
   * @param defaultValue
   * @param projected
   */
  public TimeVaryingPlottableMap( String name, T defaultValue, boolean projected ) {
    this( name, defaultValue );
    dataProjected = projected;
  }

  public TimeVaryingPlottableMap( String name, String fileName, Class<T> cls, boolean projected ) {
    super( name, fileName, cls );
    dataProjected = projected;
  }
  
  /**
   * @param name
   */
  public TimeVaryingPlottableMap( String name ) {
    super( name );
  }

  /**
   * @param name
   */
  public TimeVaryingPlottableMap( String name, String fileName ) {
    super( name, fileName );
  }

  /**
   * @param name
   */
  public TimeVaryingPlottableMap( String name, boolean projected ) {
    this( name );
    dataProjected = projected;
  }

  /**
   * @param name
   */
  public TimeVaryingPlottableMap( String name, String fileName, boolean projected ) {
    this( name, fileName );
    dataProjected = projected;
  }

  @Override
  public boolean okToSample() {
    return true;
  }

  @Override
  public boolean isProjection() {
    return dataProjected;
  }

  public void setProjection( boolean b ) {
    dataProjected = b;
  }

}
