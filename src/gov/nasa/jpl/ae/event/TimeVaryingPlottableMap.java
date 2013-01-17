/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is the same as TimeVaryingMap< V > but implements Plottable.
 *
 */
public class TimeVaryingPlottableMap< V > extends TimeVaryingMap< V > implements
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
  public TimeVaryingPlottableMap( String name, V defaultValue ) {
    super( name, defaultValue );
  }

  /**
   * @param name
   * @param defaultValue
   */
  public TimeVaryingPlottableMap( String name, String fileName, V defaultValue ) {
    super( name, fileName, defaultValue );
  }

  /**
   * @param name
   * @param defaultValue
   * @param projected
   */
  public TimeVaryingPlottableMap( String name, V defaultValue, boolean projected ) {
    this( name, defaultValue );
    dataProjected = projected;
  }

  public TimeVaryingPlottableMap( String name, String fileName, Class<V> cls, boolean projected ) {
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
  
  @Override
  public void fromString( String s, Class< V > cls ) {
    // skip over "plottable" and owner name
    Pattern p = Pattern.compile( "\\s*plottable [^{]*\\s*" );
    Matcher matcher = p.matcher( s );
    int end = 0;
    if ( matcher.find() ) {
      end = matcher.end();
    }
    super.fromString( s.substring( end ), cls );
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append( "plottable " );
    if ( getOwner() != null && getOwner() instanceof ParameterListener ) {
      sb.append( ( (ParameterListener)getOwner() ).getName() );
    }
    sb.append( super.toString() );
    return sb.toString();
  }

}
