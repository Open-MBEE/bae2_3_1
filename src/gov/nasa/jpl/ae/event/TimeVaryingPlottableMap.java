/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gov.nasa.jpl.ae.event.TimeVaryingMap.TimeValue;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;

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
  public static final TimeVaryingPlottableMap<Double> one = new TimeVaryingPlottableMap< Double >( "one", null, 1.0, Double.class ) {
    private static final long serialVersionUID = 1L;
    {
      // adding endpoint so that integrate() will work on it
      setValue( Timepoint.getHorizonTimepoint(), 1.0 );
    }
  };

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
   * Construct an empty map.
   */
  public TimeVaryingPlottableMap() {
    super();
    init();
  }
  
  /**
   * @param name
   * @param initialValueFunction
   * @param o
   * @param samplePeriod
   * @param horizonDuration
   */
  public TimeVaryingPlottableMap( String name, Method initialValueFunction,
                                  Object o, long samplePeriod,
                                  long horizonDuration ) {
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
                                  Object o, long samplePeriod,
                                  long horizonDuration, boolean projected ) {
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
  public TimeVaryingPlottableMap( String name, String fileName, String backupFileName, V defaultValue,
                                  Class<V> cls,
                                  boolean projected ) {
    super( name, fileName, backupFileName, defaultValue, cls );
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
    super( name, fileName, defaultValue, cls, interpolation );
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

  public TimeVaryingPlottableMap( String name, TimeVaryingMap< V > timeVaryingMap ) {
    super( name, timeVaryingMap );
    init();
  }

  public TimeVaryingPlottableMap( TimeVaryingPlottableMap< V > timeVaryingPlottableMap ) {
    this( timeVaryingPlottableMap.getName(), timeVaryingPlottableMap );
    this.category = timeVaryingPlottableMap.category;
    this.dataProjected = timeVaryingPlottableMap.dataProjected;
    this.parameters = new LinkedHashSet< Parameter< ? > >(timeVaryingPlottableMap.parameters);
  }

  public TimeVaryingPlottableMap( TimeVaryingMap< V > timeVaryingMap ) {
    this( timeVaryingMap.getName(), timeVaryingMap );
  }

  public TimeVaryingPlottableMap( String string, Class< V > type ) {
    super( string, type );
    init();
  }
  
  public <VV>TimeVaryingPlottableMap( String name, TimeVaryingPlottableMap<VV> tvm, Class<V> cls ) {
    super(name, tvm, cls);
    init();
  }

  public <VV> TimeVaryingPlottableMap( TimeVaryingPlottableMap<VV> tvm, Class<V> cls ) {
    this( tvm.getName(), tvm, cls );
  }

  @Override
  public TimeVaryingPlottableMap<V> clone() {
    TimeVaryingPlottableMap<V> tvm = new TimeVaryingPlottableMap<V>(this);
    return tvm;
  }
  @Override
  public <VV> TimeVaryingPlottableMap<VV> clone(Class<VV> cls) {
    TimeVaryingPlottableMap<VV> tvm = new TimeVaryingPlottableMap<VV>(this, cls);
    return tvm;
  }

  @Override
  public TimeVaryingPlottableMap<V> emptyClone() {
    TimeVaryingPlottableMap<V> tvm = new TimeVaryingPlottableMap<V>(this.getName(), (String)null, this.getType(), this.isProjection());
    return tvm;
  }
  @Override
  public <T> TimeVaryingPlottableMap< T > emptyClone(Class<T> cls) {
    TimeVaryingPlottableMap<T> tvm = new TimeVaryingPlottableMap<T>(this.name, cls);
    return tvm;
  }

  @Override
  public TimeVaryingMap< V > integrate(Parameter< Long > fromKey,
                                       Parameter< Long > toKey) {

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
  
  protected static boolean isNumber( Object o ) {
    if ( o instanceof Number ) return true;
    if ( o instanceof Wraps ) {
      if ( Number.class.isAssignableFrom( ((Wraps<?>)o).getType() ) ) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  protected TimeVaryingMap<Object> getEmptyMap( Object thenObj, Object elseObj ) {
    TimeVaryingMap<?> mapToClone = mapToClone(thenObj, elseObj);
    TimeVaryingMap<Object> newTvm;
    if ( mapToClone == null && isNumber(thenObj) && isNumber(elseObj) ) {
      newTvm = new TimeVaryingPlottableMap<Object>();
    } else {
      newTvm = (TimeVaryingMap< Object >)( mapToClone == null ? new TimeVaryingMap<Object>() : mapToClone.emptyClone() );
    }
    return newTvm;
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
