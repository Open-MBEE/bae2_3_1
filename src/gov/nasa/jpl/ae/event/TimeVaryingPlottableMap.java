/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
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

//  /**
//   * @param name
//   * @param defaultValue
//   */
//  public TimeVaryingPlottableMap( String name, V defaultValue ) {
//    super( name, defaultValue );
//  }

  /**
   * @param name
   * @param fileName
   * @param defaultValue
   * @param cls
   */
  public TimeVaryingPlottableMap( String name, String fileName, V defaultValue, Class<V> cls ) {
    super( name, fileName, defaultValue, cls );
  }

  /**
   * @param name
   * @param fileName
   * @param cls
   * @param projected
   */
  public TimeVaryingPlottableMap( String name, String fileName, Class<V> cls, boolean projected ) {
    super( name, fileName, cls );
    dataProjected = projected;
  }

  /**
   * @param name
   * @param fileName
   * @param defaultValue
   * @param projected
   */
  public TimeVaryingPlottableMap( String name, String fileName, V defaultValue, boolean projected ) {
    super( name, fileName, defaultValue );
    dataProjected = projected;
  }

  /**
   * @param name
   * @param fileName
   * @param defaultValue
   * @param cls
   * @param projected
   */
  public TimeVaryingPlottableMap( String name, String fileName, V defaultValue,
                                  Class<V> cls,
                                  boolean projected ) {
    super( name, fileName, defaultValue, cls );
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

  public TimeVaryingPlottableMap( String name, TimeVaryingPlottableMap< V > timeVaryingPlottableMap ) {
    super( name, timeVaryingPlottableMap );
    dataProjected = timeVaryingPlottableMap.dataProjected;
  }

  public TimeVaryingPlottableMap( TimeVaryingPlottableMap< V > timeVaryingPlottableMap ) {
    this( timeVaryingPlottableMap.getName(), timeVaryingPlottableMap );
  }

  public TimeVaryingPlottableMap<V> clone() {
    TimeVaryingPlottableMap<V> tvm = new TimeVaryingPlottableMap<V>(this);
    return tvm;
  }
  
  @Override
  public boolean okToSample() {
    return true;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Plottable#isProjection()
   */
  @Override
  public boolean isProjection() {
    return dataProjected;
  }

  public void setProjection( boolean b ) {
    dataProjected = b;
  }
  
  @Override
  public void fromString( String s, Class< V > cls ) {
    // skip over "plottable", "projected", and owner name
    // REVIEW -- shouldn't owner be handled by super class?
    Pattern p = Pattern.compile( "\\s*plottable\\s*(projected)?\\s*[^{]*\\s*" );
    Matcher matcher = p.matcher( s );
    int end = 0;
    setProjection( false );
    if ( matcher.find() ) {
      end = matcher.end();
      for ( int i=1; i<=matcher.groupCount(); ++i ) {
        String g = matcher.group( i ); 
        if ( g.equals( "projected" ) ) {
          setProjection( true );
        }
      }
    }
    super.fromString( s.substring( end ), cls );
  }

  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen,
                          Map< String, Object > otherOptions ) {
    StringBuffer sb = new StringBuffer();
    sb.append( "plottable " );
    if ( isProjection() ) {
      sb.append( "projected " );
    }
    if ( getOwner() != null && getOwner() instanceof ParameterListener ) {
      sb.append( ( (ParameterListener)getOwner() ).getName() + " " );
    } else {
      sb.append( "unowned " );
    }
    sb.append( super.toString( withHash, deep, seen, otherOptions ) );
    return sb.toString();
  }

  
}
