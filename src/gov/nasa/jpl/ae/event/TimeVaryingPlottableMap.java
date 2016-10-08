/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
  
  public static final TimeVaryingPlottableMap<Double> zero = new TimeVaryingPlottableMap< Double >( "zero", null, 0.0, Double.class ); 
  public static final TimeVaryingPlottableMap<Double> one = new TimeVaryingPlottableMap< Double >( "one", null, 1.0, Double.class ); 

  protected boolean dataProjected = false;

  public StringParameter category = new StringParameter( "category", "", this );
  
  public Set<Parameter<?>> parameters = new HashSet<Parameter<?>>() {
    private static final long serialVersionUID = 3251438984877026858L;
    {
    add(category);
    }
  };
  
  protected void init(){
//    category.owner = this;
//    getParameters().add(category);
  }
  
  @Override
  public Set< Parameter< ? > > getParameters() {
    // TODO Auto-generated method stub
    return super.getParameters();
  }

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
    init();
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
    init();
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
    init();
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
    init();
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
    init();
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
    init();
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
                                  Interpolation interpolation,
                                  boolean projected ) {
    super( name, fileName, defaultValue, cls );
    this.interpolation = interpolation;
    this.dataProjected = projected;
    init();
  }
  
  /**
   * @param name
   */
  public TimeVaryingPlottableMap( String name ) {
    super( name );
    init();
  }

  /**
   * @param name
   */
  public TimeVaryingPlottableMap( String name, String fileName ) {
    super( name, fileName );
    init();
  }

  /**
   * @param name
   */
  public TimeVaryingPlottableMap( String name, boolean projected ) {
    this( name );
    dataProjected = projected;
    init();
  }

  /**
   * @param name
   */
  public TimeVaryingPlottableMap( String name, String fileName, boolean projected ) {
    this( name, fileName );
    dataProjected = projected;
    init();
  }

  public TimeVaryingPlottableMap( String name, TimeVaryingPlottableMap< V > timeVaryingPlottableMap ) {
    super( name, timeVaryingPlottableMap );
    dataProjected = timeVaryingPlottableMap.dataProjected;
    init();
  }

  public TimeVaryingPlottableMap( TimeVaryingPlottableMap< V > timeVaryingPlottableMap ) {
    this( timeVaryingPlottableMap.getName(), timeVaryingPlottableMap );
    this.category = timeVaryingPlottableMap.category;
    this.dataProjected = timeVaryingPlottableMap.dataProjected;
    this.parameters = new LinkedHashSet< Parameter< ? > >(timeVaryingPlottableMap.parameters);
    init();
  }

  public TimeVaryingPlottableMap( String string, Class< V > type ) {
    super( string, type );
    init();
  }

  @Override
  public TimeVaryingPlottableMap<V> clone() {
    TimeVaryingPlottableMap<V> tvm = new TimeVaryingPlottableMap<V>(this);
    return tvm;
  }

  @Override
  public TimeVaryingPlottableMap<V> emptyClone() {
    TimeVaryingPlottableMap<V> tvm = new TimeVaryingPlottableMap<V>(this.getName(), (String)null, this.getType(), this.isProjection());
    return tvm;
  }

  @Override
  public TimeVaryingMap< V > integrate(Parameter< Integer > fromKey,
                                       Parameter< Integer > toKey) {

    TimeVaryingPlottableMap<V> tvm = new TimeVaryingPlottableMap< V >( this.name + "Integral", this.type );
    return integrate( fromKey, toKey, tvm );
  }

  
  @Override
  public boolean okToSample() {
    init();
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
