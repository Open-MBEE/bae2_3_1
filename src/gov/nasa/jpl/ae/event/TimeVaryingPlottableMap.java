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
   * @param defaultValue
   */
  public TimeVaryingPlottableMap( String name, T defaultValue ) {
    super( name, defaultValue );
  }

  /**
   * @param name
   */
  public TimeVaryingPlottableMap( String name ) {
    super( name );
  }

  @Override
  public boolean okToSample() {
    return true;
  }

}
