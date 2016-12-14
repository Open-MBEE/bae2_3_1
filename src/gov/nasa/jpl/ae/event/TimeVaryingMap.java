/**
 *
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.LongDomain;
import gov.nasa.jpl.ae.solver.StringDomain;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.FileUtils;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.TimeUtils;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

//import com.panayotis.gnuplot.JavaPlot;

/**
 *
 * TimeVaryingMap is a {@link TreeMap} for implementing {@link TimeVarying},
 * mapping a {@link Parameter< Long>} to a generic class, V. It is also
 * implements {@link ParameterListener} in order to maintain {@link TreeMap}
 * consistency when the key changes. It "floats" entries before its
 * {@link Parameter< Long>} changes to protect the data structure and
 * reinserts the entry after the {@link Parameter< Long>} has changed.
 *
 */
public class TimeVaryingMap< V > extends TreeMap< Parameter< Long >, V >
                                 implements Cloneable,
                                            TimeVarying< Long, V >,
                                            Affectable,
                                            ParameterListener,
                                            HasOwner,
                                            AspenTimelineWritable {

  protected static boolean checkConsistency = false;

  private static final long serialVersionUID = -2428504938515591538L;

  public static Interpolation STEP = new TimeVaryingMap.Interpolation(TimeVaryingMap.Interpolation.STEP);
  public static Interpolation LINEAR = new TimeVaryingMap.Interpolation(TimeVaryingMap.Interpolation.LINEAR);
  public static Interpolation RAMP = new TimeVaryingMap.Interpolation(TimeVaryingMap.Interpolation.RAMP);
  public static Interpolation NONE = new TimeVaryingMap.Interpolation(TimeVaryingMap.Interpolation.NONE);
  
  public static final TimeVaryingMap<Double> zero = new TimeVaryingMap< Double >( "zero", 0.0, Double.class ); 
  public static final TimeVaryingMap<Double> one = new TimeVaryingMap< Double >( "one", 1.0, Double.class ); 
  
  public static class Interpolation  {
    public static final byte STEP = 0; // value for key = get(floorKey( key ))
    public static final byte LINEAR = 1; // floorVal+(ceilVal-floorVal)*(key-floorKey)/(ceilKey-floorKey)
    public static final byte RAMP = 2; // linear
    public static final byte NONE = Byte.MAX_VALUE; // value for key = get(key)
    public byte type = STEP;
    public Interpolation() {}
    public Interpolation( byte type ) {
      this.type = type;
    }
    @Override
    public String toString() {
      switch ( type ) {
        case NONE:
          return "NONE";
        case STEP:
          return "STEP";
        case LINEAR:
          return "LINEAR";
        case RAMP:
          return "RAMP";
        default:
          return null;
      }
    }
    public void fromString( String s ) {
      try{
        type = Byte.parseByte( s );
      } catch ( NumberFormatException e ) {
        // ignore
      }
      if ( s.toLowerCase().equals( "none" ) ) {
        type = NONE;
      } else if ( s.toLowerCase().equals( "step" ) ) {
        type = STEP;
      } else if ( s.toLowerCase().equals( "linear" ) ) {
        type = LINEAR;
      } else if ( s.toLowerCase().equals( "ramp" ) ) {
        type = RAMP;
      } else {
        Debug.error(true, "Can't parse interpolation string! " + s );
      }
    }
    
    public boolean isLinear() {
      return type == LINEAR || type == RAMP;
    }
    public boolean isRamp() {
      return isLinear();
    }
  }

  public Interpolation interpolation = new Interpolation();

  protected final int id = HasIdImpl.getNext();

  protected Object owner = null;


  /**
   * For the convenience of referring to the effect method.
   */
  protected static Method setValueMethod1 = getSetValueMethod();
  //protected static Method setValueMethod2 = getSetValueMethod2();
  protected static Method addNumberMethod = null;
  protected static Method addNumberAtTimeMethod = null;
  protected static Method addNumberForTimeRangeMethod = null;
  protected static Method addMapMethod = null;
  protected static Method subtractNumberMethod = null;
  protected static Method subtractNumberAtTimeMethod = null;
  protected static Method subtractNumberForTimeRangeMethod = null;
  protected static Method subtractMapMethod = null;
  protected static Method multiplyNumberMethod = null;
  protected static Method multiplyNumberAtTimeMethod = null;
  protected static Method multiplyNumberForTimeRangeMethod = null;
  protected static Method multiplyMapMethod = null;

  protected static Method divideNumberMethod = null;
  protected static Method divideNumberAtTimeMethod = null;
  protected static Method divideNumberForTimeRangeMethod = null;
  protected static Method divideMapMethod = null;

  /**
   * Floating effects are those whose time or duration is changing. They must be
   * removed from TimeVaryingMap's map before they change; else, they will
   * corrupt the map. Before changing, they are placed in this floatingEffects
   * list, and after changing they are removed from this list and added back to
   * the map.
   */
  protected List< TimeValue > floatingEffects = new ArrayList< TimeValue >();

  protected String name;

  protected Class<V> type = null;

  protected Set<Effect> appliedSet = new HashSet<Effect>();

  protected static Map< Method, Integer > effectMethods = initEffectMethods();

  protected static Map< Method, Method > inverseMethods;

  protected static Comparator< Method > methodComparator;

  protected static Collection< Method > arithmeticMethods;



  public class TimeValue extends Pair< Parameter< Long>, V >
                               implements HasParameters {

    protected final int id = HasIdImpl.getNext();

    public TimeValue( Parameter< Long> t, V v ) {
      super( t, v );
    }

    @Override
    public void deconstruct() {
      maybeDeconstructParameter( TimeVaryingMap.this, first );
      maybeDeconstructParameter( TimeVaryingMap.this, second );
    }

    @Override
    public boolean isStale() {
      return HasParameters.Helper.isStale( this, false, null, false );
    }

    @Override
    public void setStale( boolean staleness ) {
      Debug.error( true, false, "BAD!!!!!!!!!!!!!!   THIS SHOULD NOT BE GETTING CALLED!  setStale(" + staleness + "): "
                     + toShortString() );
      Assert.assertTrue( "This method is not supported!", false );
    }

    @Override
    public Set< Parameter< ? > > getParameters( boolean deep,
                                                Set< HasParameters > seen ) {
      Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
      if ( pair.first ) return Utils.getEmptySet();
      seen = pair.second;
      //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
      return HasParameters.Helper.getParameters( this, deep, null, false );
    }

    @Override
    public Set< Parameter< ? > > getFreeParameters( boolean deep,
                                                    Set< HasParameters > seen ) {
      Assert.assertTrue( "This method is not supported!", false );
      return null;
    }

    @Override
    public void setFreeParameters( Set< Parameter< ? > > freeParams,
                                   boolean deep,
                                   Set< HasParameters > seen ) {
      Assert.assertTrue( "This method is not supported!", false );
    }

    @Override
    public boolean isFreeParameter( Parameter< ? > parameter, boolean deep,
                                    Set< HasParameters > seen ) {
      // TODO -- REVIEW -- not sure about this
      Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
      if ( pair.first ) return false;
      seen = pair.second;
      //if ( Utils.seen( this, deep, seen ) ) return false;
      return HasParameters.Helper.isFreeParameter( this, parameter, deep, seen, false );
    }

    @Override
    public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                                 Set< HasParameters > seen ) {
      Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
      if ( pair.first ) return false;
      seen = pair.second;
      //if ( Utils.seen( this, deep, seen ) ) return false;
      return HasParameters.Helper.hasParameter( this, parameter, deep, seen, false );
    }

    @Override
    public boolean substitute( Parameter< ? > p1, Parameter< ? > p2,
                               boolean deep,
                               Set< HasParameters > seen ) {
      return substitute( p1, (Object)p2, deep, seen );
    }
    @Override
    public boolean substitute( Parameter< ? > p1, Object p2,
                               boolean deep,
                               Set< HasParameters > seen ) {
      breakpoint();
      Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
      if ( pair.first ) return false;
      seen = pair.second;
      //if ( Utils.seen( this, deep, seen ) ) return false;
      return HasParameters.Helper.substitute( this, p1, p2, deep, seen, false );
    }

    @Override
    public Integer getId() {
      return id;
    }
    @Override
    public int hashCode() {
      return id;
    }

    @Override
    public String toShortString() {
      return MoreToString.Helper.toShortString( this, null, true );
    }

    @Override
    public String toString( boolean withHash, boolean deep, Set< Object > seen ) {
      return toString( withHash, deep, seen, null );
    }

    @Override
    public String toString( boolean withHash, boolean deep, Set< Object > seen,
                            Map< String, Object > otherOptions ) {
      return MoreToString.Helper.toString(this, withHash, deep, seen,
                                          otherOptions, false );
    }

  }

  public static boolean before( Parameter< Long > o1, Parameter< Long > o2 ) {
    int c = compareTo( o1, o2 );
    return c < 0;
  }
  public static boolean after( Parameter< Long > o1, Parameter< Long > o2 ) {
    int c = compareTo( o1, o2 );
    return c > 0;
  }
  public static int compareTo( Parameter< Long > o1, Parameter< Long > o2 ) {
    return TimeComparator.instance.compare( o1, o2 );
  }
  
  public static class TimeComparator implements Comparator< Parameter< Long > > {

    public boolean propagate = false;
    public boolean checkId = true;
    public static TimeComparator instance = new TimeComparator();

    @Override
    public int compare( Parameter< Long > o1, Parameter< Long > o2 ) {
      if ( o1 == o2 ) return 0;
      if ( o1 == null ) return -1;
      if ( o2 == null ) return 1;
      if ( ( o1.getType() != null && !Long.class.isAssignableFrom( o1.getType() ) ) ||
           ( o2.getType() != null && !Long.class.isAssignableFrom( o2.getType() ) ) ) {
        return o1.compareTo( o2, checkId );
      }
      int compare = 0;
      Long v1 = null;
      Long v2 = null;
      try {
        try {
          v1 = Expression.evaluate( o1, Long.class, propagate );
        } catch ( IllegalAccessException e ) {
          // TODO Auto-generated catch block
          //e.printStackTrace();
        } catch ( InvocationTargetException e ) {
          // TODO Auto-generated catch block
          //e.printStackTrace();
        } catch ( InstantiationException e ) {
          // TODO Auto-generated catch block
          //e.printStackTrace();
        }
        try {
          v2 = Expression.evaluate( o2, Long.class, propagate );
        } catch ( IllegalAccessException e ) {
          // TODO Auto-generated catch block
          //e.printStackTrace();
        } catch ( InvocationTargetException e ) {
          // TODO Auto-generated catch block
          //e.printStackTrace();
        } catch ( InstantiationException e ) {
          // TODO Auto-generated catch block
          //e.printStackTrace();
        }
      } catch (ClassCastException e) {
        // May be possible to get here if a value was not an Long.
        return o1.compareTo( o2, checkId );
      }
      compare = CompareUtils.compare( v1, v2, true );
      if ( compare != 0 ) return compare;
      return o1.compareTo( o2, checkId );
    }
  }

  public TimeVaryingMap() {
    super(TimeComparator.instance);
  }

  /**
   *
   */
  public TimeVaryingMap( String name ) {
    this();
    this.name = name;
  }
  public TimeVaryingMap( String name, String fileName ) {
    this(name);
        fromCsvFile( fileName, type );
  }


  public TimeVaryingMap( String name, Class<V> type ) {
    this(name);
    this.type = type;
  }
  protected TimeVaryingMap( String name, String fileName, Class<V> type ) {
    this(name, type);
        fromCsvFile( fileName, type );
  }

  protected TimeVaryingMap( String name, V defaultValue, Class<V> type ) {
    this(name,type);
    // REVIEW -- consider forcing all constructors to provide non-null type
    V valueToInsert = null;
    if ( this.type == null ) {
      if ( defaultValue != null ) {
        setType( defaultValue.getClass() );
      }
      valueToInsert = defaultValue;
    } else {
      try {
        valueToInsert = Expression.evaluate( defaultValue, this.type, false, true );
      } catch ( ClassCastException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      }
    }
    Parameter< Long> t = new Parameter< Long>(null, null, 0L, this);
    Debug.errorOnNull(true,"this should neeeever be null", t.getValue(false) );
    put( t, valueToInsert );
    if ( Debug.isOn() || checkConsistency ) isConsistent();
  }

  public TimeVaryingMap( String name, String fileName,
                         V defaultValue, Class<V> type ) {
    this( name, defaultValue, type );
        fromCsvFile( fileName, type );
      }

//  public TimeVaryingMap( String name, V defaultValue ) {
//    this(name, defaultValue, null);
//  }
//
  public TimeVaryingMap( String name, String fileName, V defaultValue ) {
    this(name, defaultValue, null);
    fromCsvFile( fileName, type );
  }

  /**
   * @param obj object to cast from
   * @param cls Class to which to cast o
   * @return obj cast to T or {@code null} if the cast fails
   */
  public static < T > T tryCast( Object obj, Class<T> cls ) {
    if ( cls != null && obj != null ) {
      try {
        if ( Number.class.isAssignableFrom( cls ) && ClassUtils.isNumber( obj.getClass() ) ) {
          T x = ClassUtils.castNumber( (Number)obj, cls );
          if ( x != null ) return x;
        }
        return cls.cast( obj );
      } catch ( ClassCastException e ) {
        // ignore
      }
    }
    return null;
  }

  /**
   * @param obj object to cast from
   * @return obj cast to V or {@code null} if the cast fails
   */
  @SuppressWarnings( "unchecked" )
  public V tryCastValue( Object obj ) {
    if ( getType() != null ) {
      return tryCast( obj, getType() );
    }
    try {
      return (V)obj;
    } catch ( ClassCastException e ) {
      // ignore
    }
    return null;
  }

  /**
   * @param obj object to cast from
   * @return obj cast to V or {@code null} if the cast fails
   */
  @SuppressWarnings( "unchecked" )
  public static Parameter< Long> tryCastTimepoint( Object obj ) {
    if ( obj instanceof Parameter ) {
      Parameter<?> p = (Parameter< ? >)obj;
      Object val = p.getValueNoPropagate();
      if ( val == null || val instanceof Long ) {
        return (Parameter< Long >)obj;
      }
    }
    return null;
  }


  /**
   * @param obj object to cast from
   * @param propagate whether to propagate dependencies as part of the evaluation
   * @return obj cast to V or {@code null} if the cast fails
   */
  public V tryEvaluateValue( Object obj, boolean propagate ) {
    try {
      return Expression.evaluate( obj, getType(), propagate, true );
    } catch ( Throwable e ) {
      // ignore
    }
    return null;
  }

  /**
   * @param obj
   *          object to cast or convert to a timepoint
   * @param propagate
   *          whether to propagate dependencies as part of the evaluation
   * @return obj evaluated as Timepoint or {@code null} if the conversion or
   *         cast fail
   */
  public Parameter< Long> tryEvaluateTimepoint( Object obj, boolean propagate ) {
    return tryEvaluateTimepoint( obj, propagate, true );
  }

  private static Parameter< Long> param = new Parameter< Long >( "", null, 0L, null );
  private static Class< ? extends Parameter > pcls = param.getClass();
      //(Class< ? extends Parameter< Long >>)param.getClass();

  //(Class< ? extends Parameter< Long >>)( isEmpty() ? Parameter.class : firstKey().getClass() );

  /**
   * @param obj
   *          object to cast or convert to a timepoint
   * @param propagate
   *          whether to propagate dependencies as part of the evaluation
   * @param okToWrap
   *          whether a Parameter may be created to wrap an Long value into a
   *          timepoint
   * @return obj evaluated as Timepoint or {@code null} if the conversion or
   *         cast fail
   */
  @SuppressWarnings( "unchecked" )
  public static Parameter< Long> tryEvaluateTimepoint( Object obj, boolean propagate, boolean okToWrap ) {
    try {
      return Expression.evaluate( obj, (Class<Parameter< Long>>)pcls, propagate, okToWrap );
    } catch ( Throwable e ) {
      // ignore
    }
    return null;
  }

  public TimeVaryingMap( String name, Method initialValueFunction,
                         Object o, long samplePeriod, long horizonDuration ) {
    super(TimeComparator.instance);
    this.name = name;
    samplePeriod = correctSamplePeriod( samplePeriod, horizonDuration );
      for ( long t = 0; t < horizonDuration; t += samplePeriod ) {
        // WARNING: creating Parameter< Long> with no owner in order to avoid
        // unnecessary overhead with constraint processing. If modified while in
        // the map, it can corrupt the map.
        Pair< Boolean, Object > p = ClassUtils.runMethod( false, o, initialValueFunction, t );
        if ( p.first ) {
          Parameter< Long > tp = makeTempTimepoint( t, false );
          if ( tp == null || tp.getValue( false ) == null ) {
            System.err.println( "Error - inserting null timepoint!" );
          } else {
            put( tp,// new Parameter< Long>( "", t, this ),
                 tryCastValue( p.second ) );
          }
        }
      }
    if ( Debug.isOn() || checkConsistency ) isConsistent();
  }

  public TimeVaryingMap( String name, TimeVaryingMap<V> tvm ) {
    this( name, null, null, tvm.type );
    owner = tvm.owner;
    interpolation = tvm.interpolation;
    floatingEffects.clear();
    floatingEffects.addAll( tvm.floatingEffects );
    appliedSet.clear();
    appliedSet.addAll( tvm.appliedSet );
    clear(); // clears the default value.
    putAll( tvm );
  }

  public <VV>TimeVaryingMap( String name, TimeVaryingMap<VV> tvm, Class<V> cls ) {
    this( name, null, null, cls );
    owner = tvm.owner;
    interpolation = tvm.interpolation;
    floatingEffects.clear();
    List< TimeVaryingMap< VV >.TimeValue > effects = tvm.floatingEffects;
    List< TimeVaryingMap< V >.TimeValue > newEffects = new ArrayList< TimeVaryingMap<V>.TimeValue >();
    for ( TimeVaryingMap< VV >.TimeValue f : effects ) {
      TimeVaryingMap< V >.TimeValue tv = new TimeValue( f.first, tryCastValue( f.second ) );
      newEffects.add(tv);
    }
    floatingEffects.addAll( newEffects );
    appliedSet.clear();
    appliedSet.addAll( tvm.appliedSet );
    clear(); // clears the default value.
    for ( Map.Entry< Parameter< Long >, VV > e : tvm.entrySet() ) {
      V v = tryCast( e.getValue(), cls );
      if ( v != null || e.getValue() == null ) {
        setValue( e.getKey(), v );
      }
    }
  }

  public TimeVaryingMap( TimeVaryingMap<V> tvm ) {
    this( tvm.getName(), tvm );
  }
  public <VV> TimeVaryingMap( TimeVaryingMap<VV> tvm, Class<V> cls ) {
    this( tvm.getName(), tvm, cls );
  }

  @Override
  public TimeVaryingMap<V> clone() {
    TimeVaryingMap<V> tvm = new TimeVaryingMap<V>(this);
    return tvm;
  }
  public <VV> TimeVaryingMap<VV> clone(Class<VV> cls) {
    TimeVaryingMap<VV> tvm = new TimeVaryingMap<VV>(this, cls);
    return tvm;
  }
  public TimeVaryingMap< V > emptyClone() {
    TimeVaryingMap<V> tvm = new TimeVaryingMap<V>(this.name, this.type);
    return tvm;
  }
  public <T> TimeVaryingMap< T > emptyClone(Class<T> cls) {
    TimeVaryingMap<T> tvm = new TimeVaryingMap<T>(this.name, cls);
    return tvm;
  }

  public TimeVaryingMap< V > makeNew() {
    return makeNew(this.getName());
  }
  public TimeVaryingMap< V > makeNew(String name) {
    TimeVaryingMap< V > copy = emptyClone();
    copy.setName( name );
    return copy;
  }

  public static void maybeDeconstructParameter( ParameterListener pl,
                                                Object maybeParam ) {
    if ( maybeParam instanceof Parameter ) {
      Parameter<?> param = (Parameter< ? >)maybeParam;
      if ( param.getOwner() == null || param.getOwner() == pl ) {
        param.deconstruct();
      }
    }
  }

  public static <KEY,VAL> void deconstructMap( ParameterListener pl, TreeMap<KEY,VAL> map ) {
    List<VAL> values = new LinkedList< VAL >();
    // need to remove keys before values in case the values are keys!
    while (!map.isEmpty()) {
      Entry< KEY, VAL > front = map.firstEntry();
      KEY key = front.getKey();
      VAL value = front.getValue();
      map.remove( key );
      values.add( value );
      maybeDeconstructParameter( pl, key );
    }
    for ( VAL value : values ) {
      maybeDeconstructParameter( pl, value );
    }
  }

  @Override
  public void deconstruct() {
    name = "DECONSTRUCTED_" + name;
    deconstructMap( this, this );
    for ( TimeValue tv : floatingEffects ) {
      tv.deconstruct();
    }
    floatingEffects.clear();
  }

  protected void breakpoint() {}

  public Parameter< Long> getTimepointBefore( Parameter< Long> t ) {
    if ( t == null ) return null;
    Parameter< Long > k = this.lowerKey( t );
    if ( k != null && k.getValueNoPropagate() == null ) {
      Debug.error( true, "Error! Null timepoint " + k + " in TimeVaryingMap: "
                         + this );
    }
    return k;
  }

  public Parameter< Long> getTimepointBefore( Long t ) {
    if ( t == null ) return null;
    Parameter< Long> tp = makeTempTimepoint( t, false );
    return getTimepointBefore( tp );
  }

  public Parameter< Long> getTimepointEarlier( Parameter< Long> t ) {
    if ( t == null ) return null;
    return getTimepointEarlier( t.getValue( false ) );
  }

  public Parameter< Long> getTimepointEarlier( Long t ) {
    if ( t == null ) return null;
    Long justBeforeTimeVal = t;
    Parameter< Long> justBeforeTime = getTimepointBefore( t );
    while ( justBeforeTime != null && justBeforeTime.getValue( false ) != null ) {
      justBeforeTimeVal = justBeforeTime.getValue( false );
      if ( justBeforeTimeVal < t ) {
        break;
      }
      justBeforeTime = getTimepointBefore( justBeforeTime );
    }
    return justBeforeTime;
  }

  public Parameter< Long> getTimepointAfter( Parameter< Long> t ) {
    if ( t == null ) return null;
    Parameter< Long > k = this.higherKey( t );
    if ( k != null && k.getValueNoPropagate() == null ) {
      Debug.error( true, "Error! Null timepoint " + k + " in TimeVaryingMap: "
                         + this );
    }
    return k;
  }

  public Parameter< Long> getTimepointAfter( Long t ) {
    if ( t == null ) return null;
    Parameter< Long> tp = makeTempTimepoint( t, true );
    return getTimepointAfter( tp );
  }

  public Parameter< Long> getTimepointLater( Parameter< Long> t ) {
    if ( t == null ) return null;
    return getTimepointLater( t.getValue( false ) );
  }

  public Parameter< Long> getTimepointLater( Long t ) {
    if ( t == null ) return null;
    Long justAfterTimeVal = t;
    Parameter< Long> justAfterTime = getTimepointAfter( t );
    while ( justAfterTime != null ) {
      justAfterTimeVal = justAfterTime.getValue( false );
      if ( justAfterTimeVal > t ) {
        break;
      }
      justAfterTime = getTimepointAfter( justAfterTime );
    }
    return justAfterTime;
  }

  public V getValueBefore( Parameter< Long> t ) {
    if ( t == null ) return null;
    Parameter< Long> justBeforeTime = getTimepointBefore( t );
    V valBefore = null;
    if ( justBeforeTime != null ) {
      valBefore = get( justBeforeTime );
    }
    return valBefore;
  }

  public V getValueBefore( Long t ) {
    if ( t == null ) return null;
    Parameter< Long> justBeforeTime = getTimepointBefore( t );
    V valBefore = null;
    if ( justBeforeTime != null ) {
      valBefore = get( justBeforeTime );
    }
    return valBefore;
  }

  public V getValueEarlier( Parameter< Long> t ) {
    if ( t == null ) return null;
    return getValueEarlier( t.getValue( false ) );
  }

  public V getValueEarlier( Long t ) {
    if ( t == null ) return null;
    Parameter< Long> justEarlierTime = getTimepointEarlier( t );
    V valBefore = null;
    if ( justEarlierTime != null ) {
      valBefore = get( justEarlierTime );
    }
    return valBefore;
  }

  public V getValueAfter( Parameter< Long> t ) {
    if ( t == null ) return null;
    Parameter< Long> justAfterTime = getTimepointAfter( t );
    V valAfter = null;
    if ( justAfterTime != null ) {
      valAfter = get( justAfterTime );
    }
    return valAfter;
  }

  public V getValueAfter( Long t ) {
    if ( t == null ) return null;
    Parameter< Long> justAfterTime = getTimepointAfter( t );
    V valAfter = null;
    if ( justAfterTime != null ) {
      valAfter = get( justAfterTime );
    }
    return valAfter;
  }

  public Parameter< Long> makeTempTimepoint( Long t, boolean maxName ) {
    //if ( t == null ) return null;
    String n = ( maxName ? StringDomain.typeMaxValue : null );
    Parameter< Long> tp = new Parameter< Long>( n, null, t, this );
    return tp;
  }

  protected static long correctSamplePeriod( long samplePeriod,
                                            long horizonDuration ) {
    if ( samplePeriod == 0 ) {
      samplePeriod = Math.max( 1, horizonDuration / 10 );
    }
    return samplePeriod;
  }

  @Override
  public void handleValueChangeEvent( Parameter< ? > parameter, Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > p = Utils.seen( this, true, seen );
    if (p.first) return;
    seen = p.second;

    breakpoint();
    if ( parameter == null ) return;
    if ( parameter.getValueNoPropagate() instanceof Long ) {
      unfloatEffects( parameter );
    }
  }

  protected void floatEffects( Parameter< Long> t ) {
    breakpoint();
    if ( t == null ) return;
    assert t.getValueNoPropagate() != null;
    if ( !containsKey( t ) ) return;
    V value = get( t );
    if ( Debug.isOn() ) Debug.outln( getName() + ": floating effect, (" + t + ", " + value + ")" );
    floatingEffects.add( new TimeValue( t, value ) );
    remove( t );
    if ( Debug.isOn() || checkConsistency ) isConsistent();
  }

  protected void unfloatEffects( Parameter<?> t ) {
    breakpoint();
    if ( t == null ) return;
    if ( t.getValueNoPropagate() == null ) return;
    ArrayList<TimeValue> copy = new ArrayList<TimeValue>( floatingEffects );
    for ( TimeValue e : copy ) {
      if ( e.first.compareTo( t ) == 0 ) {
        put( e.first, e.second );
        if ( Debug.isOn() ) Debug.outln( getName() + ": unfloated effect, " + e );
      }
      floatingEffects.remove( e );
    }
    if ( Debug.isOn() || checkConsistency ) isConsistent();
  }

  @Override
  public void handleDomainChangeEvent( Parameter< ? > param, Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > p = Utils.seen( this, true, seen );
    if (p.first) return;
    seen = p.second;

    unfloatEffects( tryCastTimepoint( param ) );
  }

  @Override
  public String getName() {
    if ( name != null && !name.isEmpty() ) return name;
    return getClass().getSimpleName();
  }

  public void setName( String newName ) {
    if ( Utils.isNullOrEmpty( newName ) ) {
      newName = getClass().getSimpleName();
    }
    this.name = newName;
  }

  @Override
  public Object getOwner() {
    return owner;
  }
  @Override
  public void setOwner( Object owner ) {
    this.owner = owner;
  }
  public List< TimeValue > getFloatingEffects() {
    return floatingEffects;
  }
  public void setFloatingEffects( List< TimeValue > floatingEffects ) {
    this.floatingEffects = floatingEffects;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasParameters#getParameters(boolean, java.util.Set)
   * Add startTimes, durations, values that are Parameters, and (if deep)
   * parameters of effects.
   */
  @Override
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set< HasParameters > seen ) {
    // Not allowing public access since some are not owned by this object.
    return Utils.getEmptySet();
  }

  public Set< Parameter< ? > > getParameters() {
    return getParameters( false, null );
    // Not allowing public access since some are not owned by this object.
//    HashSet< HasParameters > seen = new HashSet< HasParameters >();
//    Set< Parameter< ? > > params = new HashSet< Parameter< ? > >();
//    params = Utils.addAll( params, HasParameters.Helper.getParameters( keySet(), false, seen, true ) );
//    params = Utils.addAll( params, HasParameters.Helper.getParameters( values(), false, seen, true ) );
//    params = Utils.addAll( params, HasParameters.Helper.getParameters( floatingEffects, false, seen, true ) );
//    return params;
  }

  @Override
  public Set< Parameter< ? > > getFreeParameters( boolean deep,
                                                  Set< HasParameters > seen ) {
    Debug.error( true, "This method is not supported!" );
    return null;
  }
  @Override
  public void setFreeParameters( Set< Parameter< ? > > freeParams,
                                 boolean deep,
                                 Set< HasParameters > seen) {
    Debug.error( true, "This method is not supported!" );
  }


  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                               Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;

    if ( HasParameters.Helper.hasParameter( this, parameter, deep, seen, false ) ) {
      return true;
    }
    if ( HasParameters.Helper.hasParameter( floatingEffects, parameter, deep, seen, true ) ) {
      return true;
    }
    return false;
  }

  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep,
                             Set< HasParameters > seen ) {
    return substitute( p1, (Object)p2, deep, seen );
  }
  @Override
  public boolean substitute( Parameter< ? > p1, Object p2, boolean deep,
                             Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;

    breakpoint();
    if ( HasParameters.Helper.substitute( this, p1, p2, deep, seen, false ) ) {
      if ( Debug.isOn() || checkConsistency ) isConsistent();
      return true;
    }
    if ( HasParameters.Helper.substitute( floatingEffects, p1, p2, deep, seen, true ) ) {
      if ( Debug.isOn() || checkConsistency ) isConsistent();
      return true;
    }
    return false;

  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.LazyUpdate#isStale()
   */
  @Override
  public boolean isStale() {
    if ( !floatingEffects.isEmpty() ) return true;
    return ( HasParameters.Helper.isStale( this, false, null, false ) );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.LazyUpdate#setStale(boolean)
   */
  @Override
  public void setStale( boolean staleness ) {
      Debug.getInstance()
      .logForce( "BAD!!!!!!!!!!!!!!   THIS SHOULD NOT BE GETTING CALLED!  setStale(" + staleness + "): "
                     + toShortString() );
    if ( Debug.isOn() ) Debug.outln( "setStale(" + staleness + ") to " + this );
    Assert.assertTrue( "This method is not supported!", false );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListener#setStaleAnyReferencesTo(gov.nasa.jpl.ae.event.Parameter)
   */
  @Override
  public void setStaleAnyReferencesTo( Parameter< ? > changedParameter,
                                       Set< HasParameters > seen ) { // ignoring since there's no recursive call here
    breakpoint();

    Pair< Boolean, Set< HasParameters > > p = Utils.seen( this, true, seen );
    if (p.first) return;
    seen = p.second;

    if ( Debug.isOn() ) Debug.outln( getName() + ".setStaleAnyReferencesTo(" + changedParameter + ")" );
    if ( changedParameter == null ) return;
    if ( containsKey( changedParameter ) ) {
      if ( Debug.isOn() ) Debug.outln( getName() + ".setStaleAnyReferencesTo(" + changedParameter + "): does contain" );
      floatEffects( tryCastTimepoint( changedParameter ) );
    } else {
      if ( Debug.isOn() ) Debug.outln( getName() + ".setStaleAnyReferencesTo(" + changedParameter + "): does not contain" );
    }
    if ( Debug.isOn() || checkConsistency ) isConsistent();
    
//    // Pass message up the chain if necessary.
//    Object o = getOwner();
//    if ( o instanceof Parameter ) {
//      o = ((Parameter<?>)o).getOwner();
//    }
//    if ( o instanceof ParameterListener ) {
//      ((ParameterListener)o).setStaleAnyReferencesTo( changedParameter, seen );
//    }
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListener#refresh(gov.nasa.jpl.ae.event.Parameter)
   */
  @Override
  public boolean refresh( Parameter< ? > parameter ) {
    // TODO -- REVIEW -- do nothing? owner's responsibility?
    // TODO -- TimeVaryingMap should own the values if they are Parameters and
    // maybe any Parameters the value has itself, unless the value is a
    // ParameterListener and owns its own Parameters.
    return false;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.TimeVarying#getValue(gov.nasa.jpl.ae.event.Parameter< Long>)
   */
  @Override
  public V getValue( Parameter< Long > t ) {
    return getValue( t, true );
  }
  public V getValue( Parameter< Long > t, boolean valuesEqualForKeysOk ) {
    if ( t == null ) return null;
    NavigableMap< Parameter< Long >, V > m = headMap( t, true );
    if ( !m.isEmpty() ) {
      Entry< Parameter< Long >, V > e = m.lastEntry();
      if ( e.getKey().equals( t )
           || ( valuesEqualForKeysOk && Expression.valuesEqual( e.getKey(), t,
                                                                Long.class ) ) ) {
        return e.getValue();
      }
    }
    if ( Debug.isOn() || checkConsistency ) isConsistent();
    // Saving this check until later in case a null time value is acceptable,
    // and get(t) above works.
    if ( t.getValue( false ) == null ) return null;
    if ( valuesEqualForKeysOk ) {
      return getValue( t.getValue( false ) );
    }
    V v1 = null, v2 = null;
    if ( interpolation.type == Interpolation.STEP ) {
      if ( !m.isEmpty() ) {
        v1 = m.lastEntry().getValue();
      }
      if ( Debug.isOn() ) {
        v2 = getValueBefore( t );
        Assert.assertEquals( v1, v2 );
        Debug.outln("getValue() change looks good.");
      }
      return v1; //
    } else if ( interpolation.type == Interpolation.NONE ) {
      return null;
    } else if ( interpolation.type == Interpolation.LINEAR ) {
      if ( m.isEmpty() ) return null;
      V v = interpolatedValue( t, m.lastEntry() );
      return v;
    }
    Debug.error( true,
                 "TimeVaryingMap.getValue(): invalid interpolation type! "
                     + interpolation.type );
    return null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.TimeVarying#getValue(java.lang.Long)
   */
  @Override
  public V getValue( Long t ) {
    if ( t == null ) return null;
    if ( isEmpty() ) return null;
    Parameter< Long> tp = makeTempTimepoint( t, true );
    // Find the entry for this timepoint or the preceding timepoint. 
    Entry< Parameter< Long>, V > e = this.floorEntry( tp );
    if ( Debug.isOn() || checkConsistency ) isConsistent();
    if ( e != null ) {
      Parameter< Long > k = e.getKey();
      if ( k != null && k.getValue( false ) != null
           && LongDomain.defaultDomain.equals( t, k.getValue( false ) ) ) {
        return e.getValue();
      }
      if ( interpolation.type == Interpolation.NONE ) {
        return null;
      }
      if ( interpolation.type == Interpolation.STEP ) {
        return e.getValue();
      }
      if ( interpolation.isLinear() ) {
        V v = interpolatedValue( tp, e );
        return v;
      } else {
        Debug.error( true, "Interpolation " + interpolation + " not expected!");
      }
    }
    // If we couldn't find a value, then there are no values defined for the point.
    // See if the first entry could work in case of multiple entries at the same time.
    if ( !isEmpty() ) {
      Entry< Parameter< Long>, V > f = firstEntry();
      Parameter< Long> k = f.getKey();
      if ( LongDomain.defaultDomain.lessEquals( k.getValue( false ), t ) ) {
        return f.getValue();
      }
    }
    return null;
  }
  
  public V interpolatedValue(Parameter< Long> t, Entry< Parameter< Long>, V > entryBefore) {
    if ( t == null ) return null;
    if ( entryBefore == null ) return null;
    Parameter< Long> t1 = null;
    V v1 = null, v2 = null;
    t1 = entryBefore.getKey();
    v1 = entryBefore.getValue();
    if ( Debug.isOn() ) {
      Assert.assertEquals( t1, getTimepointBefore( t ) );
      Assert.assertEquals( v1, get( t1 ) );
      Debug.outln("getValue() change looks good.");
    }
    Parameter< Long> t2 = getTimepointAfter( t );
    //v1 = get( t1 );
    if ( t1.valueEquals( t2 ) ) return v1;
    v2 = get( t2 );
    if ( v1 == null ) return null;
    if ( v2 == null ) return v1;
    // floorVal+(ceilVal-floorVal)*(key-floorKey)/(ceilKey-floorKey)
    try {
      v1 = Functions.plus( v1,
                           Functions.divide( Functions.times( Functions.minus( v2, v1 ),
                                                              Functions.minus( t.getValue(), t1.getValue() ) ),
                                             Functions.minus( t2.getValue(), t1.getValue() ) ) );
    } catch ( ClassCastException e ) {
      e.printStackTrace();
    } catch ( IllegalAccessException e ) {
      e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      e.printStackTrace();
    } catch ( InstantiationException e ) {
      e.printStackTrace();
    }
    return v1;
  }

  /**
   * Return whether there exists a Parameter< Long>1 for an entry
   * (Parameter< Long>1, value1) such that
   * {@code Parameter< Long>1 == tp} or, if
   * {@code mustBeAtSameTimepoint} is false, {@code Parameter< Long>1.value}
   * equals {@code tp.value}, and value1 equals value.
   *
   * @param value
   *          the value to match with a value in the map
   * @param tp
   *          the time value required for the returned key
   * @param mustBeSameTimepoint
   *          if true, the matching entry must have this timepoint key (tested
   *          with ==)
   * @return true iff the {@code Parameter< Long>} key in the map, whose time
   *         value equals {@code tp}'s value and whose map entry equals value or
   *         {@code null} if there is no such Parameter< Long>.
   */
  public boolean hasValueAt( V value, Parameter< Long> tp,
                             boolean mustBeSameTimepoint ) {
    if ( tp == null ) return false;
    V v = get( tp );
    if ( value == v && ( v != null || containsKey( tp ) ) ) return true;
    if ( v != null ) return Expression.valuesEqual( value, v );// value.equals( v );
    // Saving this null check until later in case a null time value is
    // acceptable, and get(t) above works.
    if ( mustBeSameTimepoint || tp.getValue( false ) == null ) return false;
    return hasValueAt( value, tp.getValueNoPropagate() );
  }

  /**
   * Return the {@code Parameter< Long>1} for an entry
   * {@code (Parameter< Long>1, value1)} such that
   * {@code Parameter< Long>1 == tp} or if no such key exists
   * {@code Parameter< Long>1.value} equals {@code tp.value}, and
   * {@code value1} equals {@code value}.
   *
   * @param value
   *          the value to match with a value in the map
   * @param tp
   *          the time required for the returned key
   * @return a {@code Parameter< Long>} key in the map, which equals
   *         {@code tp} or whose time value equals that of {@code tp} and whose
   *         map entry equals {@code value} or {@code null} if there is no such
   *         key.
   */
  public Parameter< Long> keyForValueAt( V value, Parameter< Long> tp ) {
    if ( tp == null ) return null;
    if ( containsKey(tp) && Utils.valuesEqual( get( tp ), value ) ) return tp;
    return keyForValueAt( value, tp.getValueNoPropagate() );
  }

  /**
   * Return the {@code Parameter< Long>1} for an entry
   * {@code (Parameter< Long>1, value1)} such that
   * {@code Parameter< Long>1.value} equals {@code t}, and {@code value1}
   * equals {@code value}.
   *
   * @param value
   *          the value to match with a value in the map
   * @param t
   *          the time value required for the returned key
   * @return a {@code Parameter< Long>} key in the map, whose time value
   *         equals {@code t} and whose map entry equals {@code value} or
   *         {@code null} if there is no such key.
   */
  public Parameter< Long> keyForValueAt( V value, Long t ) {
    // REVIEW -- use getKeys()?
    if ( t == null ) return null;
    Parameter< Long> tp = makeTempTimepoint( t, false );
    Entry< Parameter< Long>, V > e = this.floorEntry( tp );
    Parameter< Long> startKey = null;
    if ( e != null ) {
      startKey = e.getKey();
    } else if ( !isEmpty() ) {
      startKey = firstEntry().getKey();
    } else {
      return null;
    }
    NavigableMap< Parameter< Long >, V > tailMap =
        this.tailMap( startKey, true );
    for ( java.util.Map.Entry< Parameter< Long >, V > te : tailMap.entrySet() ) {
      Object mVal = te.getValue();
      if ( Utils.valuesEqual( value, mVal )
           && TimeDomain.defaultDomain.equals( t, te.getKey()
                                                    .getValueNoPropagate() ) ) {
        return te.getKey();
      }
      if ( TimeDomain.defaultDomain.greater( te.getKey().getValueNoPropagate(),
                                             t ) ) break;
    }
    return null;
  }

  /**
   * @param value
   *          the value to match
   * @param t
   *          the time value to match
   * @return whether there is a key with a time value equal to {@code t} and and
   *         a value equal to {@code value}.
   */
  public boolean hasValueAt( V value, Long t ) {
    return keyForValueAt( value, t ) != null;
  }

//  public V setValue( Long t, V value ) {
//  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.TimeVarying#setValue(gov.nasa.jpl.ae.event.Parameter< Long>, java.lang.Object)
   */
  @Override
  public V setValue( Parameter< Long > t, V value ) {
    breakpoint();
    if ( Debug.isOn() ) Debug.outln( getName() + " setValue(" + t + ", " + value + ")" );
    if ( t == null ) {
      if ( Debug.isOn() ) Debug.error( false, "Error! trying to insert a null Parameter< Long> into the map!" );
      return null;
    }
    if ( t.getValueNoPropagate() == null ) {
      if ( Debug.isOn() ) Debug.error( false, "Error! trying to insert a null Parameter< Long> value into the map!" );
      return null;
    }
    if ( Debug.isOn() ) {
      if ( t.getOwner() == null ) {
        Debug.error( false, "Warning: inserting a Parameter< Long> with null owner into the map--may be detached!" );
      }
      if ( value != null && value instanceof Parameter
           && ( (Parameter<?>)value ).getOwner() == null ) {
        Debug.error( true, "Warning: trying to insert a value with a null owner into the map--may be detached!" );
      }
    }
    V oldValue = null;
    Parameter< Long> tp = keyForValueAt( value, t );
    if ( Debug.isOn() || checkConsistency ) isConsistent();
    if ( tp != null && tp != t ) {
      remove( tp );
    }
    if ( getType() != null && value != null && !(getType().isAssignableFrom( value.getClass() ) ) ) {
      V valueBefore = value;
      try {
        value = Expression.evaluate( value, getType(), true, true );
      } catch ( ClassCastException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      }
      // TODO better message for null case ..  if ( valueBefore==nu)
      if ( valueBefore != value ) {
        String warningMsg = null;
        if ( value == null ) {
          warningMsg =
          "Warning: tried to insert value of wrong type. Expected type is "
              + getType().getSimpleName() + ".  Inserting null.";
        } else {
          warningMsg =
              "Warning: tried to insert value of wrong type, "
                  + valueBefore.getClass().getSimpleName()
                  + ". Expected type is " + getType().getSimpleName()
                  + ".  Inserting value of type "
                  + value.getClass().getSimpleName() + " instead. value = "
                  + MoreToString.Helper.toLongString( value ) + "; this = "
                  + this.toString( true, true, null );
        }
        if ( value != null && !getType().isAssignableFrom( value.getClass() ) ) {
          Debug.error( false, warningMsg );
        } else {
          if ( Debug.isOn() ) Debug.errln( warningMsg );
        }
      }
    }
    if ( tp != t ) {
      oldValue = put( t, value );
      if ( value != null &&
           ( type == null || value.getClass().isAssignableFrom( type ) ) ) {
        setType( value.getClass() );
      }
    }
    if ( Debug.isOn() || checkConsistency ) isConsistent();
    if ( Debug.isOn() ) Debug.outln( getName() + "setValue(" + t + ", " + value
                                     + ") returning oldValue=" + oldValue );
    return oldValue;
  }

  /**
   * @param n the number by which this map is multiplied
   * @return this map after multiplying each value by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap<V> multiply( Number n ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( isEmpty() ) return this;
    if ( TimeVaryingMap.class.isAssignableFrom( getType() ) ) {
      for ( java.util.Map.Entry< Parameter< Long >, V > e : entrySet() ) {
        V v = e.getValue();
        if ( v instanceof TimeVaryingMap ) {
          TimeVaryingMap<?> tvm = (TimeVaryingMap< ? >)v;
          tvm.multiply( n );
        }
      }
      return this;
    }
    return multiply( n, firstKey(), null );
  }

  /**
   * @param n the number by which the map is multiplied
   * @return a copy of the map whose values are each multiplied by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap<V> times( Number n ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( isEmpty() ) return this.clone();
    return times( n, firstKey(), null );
  }

  /**
   * @param n the number by which the map is multiplied
   * @param fromKey
   *          the key from which all values are multiplied by {@code n}.
   * @return this map after multiplying each value in the range [fromKey,
   *         toKey) by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< V > multiply( Number n, Parameter< Long > fromKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return multiply( n, fromKey, null );
  }

  /**
   * For nested TimeVaryingMaps, an operation on elements is called recursively
   * on sub-maps. If the call is non-static, then it is assumed that the sub-map
   * is the object whose method or enclosed member's constructor is called. If
   * the call is static, then the sub-map replaces an argument to the call. The
   * argument chosen is the first whose type is compatible with TimeVaryingMap
   * and is null (if any such arguments are null). If there are no such
   * arguments, then the object is replaced if the type is compatible; else, an
   * IllegalArgumentException is raised.
   *
   * @param call
   *          the Call to apply to sub-maps
   * @return this or a copy of this depending on the call
   */
  public TimeVaryingMap< V > applyToSubMaps( Call call ) {
    // if ( TimeVaryingMap.class.isAssignableFrom( getType() ) ) {
    Integer indexOfBestArgumentToReplace = null;
    boolean isStatic = call.isStatic();
    for ( java.util.Map.Entry< Parameter< Long >, V > e : entrySet() ) {
      V v = e.getValue();
      Object retVal = null;
      if ( v instanceof TimeVaryingMap ) {
        TimeVaryingMap< ? > tvm = (TimeVaryingMap< ? >)v;
        // TODO -- REVIEW -- it would be good to be sure that this
        // substitution makes sense.
        if ( !isStatic ) {
          call.setObject( tvm );
          try {
            retVal = call.evaluate( true );
          } catch ( IllegalAccessException e1 ) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
          } catch ( InvocationTargetException e1 ) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
          } catch ( InstantiationException e1 ) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
          }
        } else {
          boolean gotTVM = false, gotNullTVM = false;
          for ( int i = 0; i < call.getArgumentArray().length; ++i ) {
            Object arg = call.getArgument( i );
            Class< ? > argType = call.getParameterTypes()[ i ];
            if ( arg == null && argType.isAssignableFrom( TimeVaryingMap.class ) ) {
              if ( indexOfBestArgumentToReplace == null && !gotNullTVM ) {
                indexOfBestArgumentToReplace = i;
                gotNullTVM = true;
                call.setArgument( i, tvm );
                break;
              }
            } else if ( arg instanceof TimeVaryingMap && !gotTVM ) {
              if ( indexOfBestArgumentToReplace == null && !gotTVM && !gotNullTVM ) {
                indexOfBestArgumentToReplace = i;
                gotTVM = true;
              }
            }
          }
          if ( indexOfBestArgumentToReplace != null ) {
            call.setArgument( indexOfBestArgumentToReplace, tvm );
          } else {
            // TODO -- REVIEW -- it would be good to be sure that this
            // substitution makes sense.
            if ( ( TimeVaryingMap.class.isAssignableFrom( call.getMember()
                                                           .getDeclaringClass() ) )
                 || call.getObject() instanceof TimeVaryingMap ) {
              call.setObject( tvm );
            } else {
              throw new IllegalArgumentException( "Warning! cannot apply call, "
                                                  + call + ", to map value, " + v );
            }
          }
        }
        try {
          retVal = call.evaluate( true );
        } catch ( IllegalAccessException e1 ) {
          // TODO Auto-generated catch block
          //e1.printStackTrace();
        } catch ( InvocationTargetException e1 ) {
          // TODO Auto-generated catch block
          //e1.printStackTrace();
        } catch ( InstantiationException e1 ) {
          // TODO Auto-generated catch block
          //e1.printStackTrace();
        }
      } else {
        Debug.error( false, "Warning! cannot apply call, "
                            + MoreToString.Helper.toLongString( call )
                            + ", to non-map value, "
                            + MoreToString.Helper.toLongString( v ) );
      }
    }
    // }
    return this;
  }

  
  /**
   * @param n the number by which the map is multiplied
   * @param fromKey the first key whose value is multiplied by {@code n}
   * @param toKey is not multiplied.  To include the last key, pass {@code null} for {@code toKey}.
   * @return this map after multiplying each value in the range [{@code fromKey}, {@code toKey})
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< V > multiply( Number n, Parameter< Long > fromKey,
                                        Parameter< Long > toKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {

    Map< Parameter< Long >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      map = subMap( fromKey, true, toKey, true );
    } else {
      boolean same = toKey.equals(fromKey);  // include the key if same
      map = subMap( fromKey, true, toKey, same );
    }
    for ( Map.Entry< Parameter< Long >, V > e : map.entrySet() ) {
      e.setValue( Functions.times(e.getValue(), n ) );
    }
    return this;
  }

  /**
   * Multiply this map with another. This achieves for all {@code t} in
   * {@code thisBefore.keySet()} and {@code tvm.keySet()},
   * {@code thisAfter.get(t) == thisBefore.get(t) * tvm.get(t)}.
   *
   * @param tvm
   *          the {@code TimeVaryingMap} with which this map is multiplied
   * @return this {@code TimeVaryingMap} after multiplying by {@code tvm}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public <VV> TimeVaryingMap< V > multiply( TimeVaryingMap< VV > tvm ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tvm == null ) return null;
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    keys.addAll( tvm.keySet() );
    for ( Parameter< Long > k : keys ) {
      VV v = tvm.getValue( k, false );
      Number n = Expression.evaluate( v, Number.class, false );
      //if ( n != null ) {// n.doubleValue() != 0 ) {
      multiply( n, k, k );
      //}
    }
    return removeDuplicates();
  }
  /**
   * @param n the number by which the map is multiplied
   * @param fromKey
   *          the key from which all values are multiplied by {@code n}.
   * @return a copy of this map for which each value in the range [{@code fromKey},
   *         {@code toKey}) is multiplied by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< V > times( Number n, Parameter< Long > fromKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return times( n, fromKey, null );
  }

  /**
   * Return the product of this map with another. This achieves for all
   * {@code t} in {@code this.keySet()} and {@code tvm.keySet()},
   * {@code newTvm.get(t) == this.get(t) * tvm.get(t)}.
   *
   * @param n
   *          the number by which the map is multiplied
   * @return a copy of this map multiplied by {@code tvm}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public < VV > TimeVaryingMap< V > timesOld( TimeVaryingMap< VV > tvm ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.multiply( tvm );
    return newTvm;
  }
  
  
  public <VV extends Number> TimeVaryingMap< V > times( TimeVaryingMap< VV > map ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return applyOperation( map, MathOperation.TIMES );
//    TimeVaryingMap< V > newTvm = emptyClone();
//    newTvm.clear();
//    Set< Parameter< Long > > keys =
//        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
//    keys.addAll( this.keySet() );
//    keys.addAll( map.keySet() );
//    for ( Parameter< Long> k : keys ) {
//      V v1 = this.getValue( k );
//      VV v2 = map.getValue( k );
//      Object v3 = Functions.times( v1, v2 );
//      // Multiplying a number and null will return the number.
//      // But this doesn't make sense when the interpolation is NONE.
//      if ( v3 == null && interpolation != NONE ) {
//        v3 = v1 != null ? v1 : v2;
//      }
//      V v4 = tryCastValue( v3 );
//      if ( v4 != null ) {
//        newTvm.setValue( k, v4 );
//      }
//    }
//    return newTvm;
  }


  public static < VV1, VV2 extends Number > TimeVaryingMap< VV1 > times( TimeVaryingMap< VV1 > tvm1,
                                                                         TimeVaryingMap< VV2 > tvm2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return tvm1.times( tvm2 );
  }

  /**
   * @param tvm the {@code TimeVaryingMap} with which this map is multiplied
   * @return a copy of this map for which each value in the range [fromKey,
   *         toKey) is multiplied by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public <VV> TimeVaryingMap< VV > times( Number n, Parameter< Long > fromKey,
                                     Parameter< Long > toKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<VV> cls = (Class<VV>)ClassUtils.dominantTypeClass( getType(), n.getClass() );    
    //TimeVaryingMap< VV > newTvm = new TimeVaryingMap< VV >(getName() + "_times_" + n, this, cls); 
    TimeVaryingMap< VV > newTvm = clone(cls);
    newTvm.setName( getName() + "_times_" + n );
    newTvm.multiply( n, fromKey, toKey );
    return newTvm;
  }


  /**
   * @param n the power by which this map is raised
   * @return this map after multiplying each value by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap<V> power( Number n ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( isEmpty() ) return this;
    if ( TimeVaryingMap.class.isAssignableFrom( getType() ) ) {
      for ( java.util.Map.Entry< Parameter< Long >, V > e : entrySet() ) {
        V v = e.getValue();
        if ( v instanceof TimeVaryingMap ) {
          TimeVaryingMap<?> tvm = (TimeVaryingMap< ? >)v;
          tvm.power( n );
        }
      }
      return this;
    }
    return power( n, firstKey(), null );
  }

  /**
   * @param n the power to which the map is raised
   * @return a copy of the map whose values are each raised to the {@code n} power
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap<V> pow( Number n ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( isEmpty() ) return this.clone();
    return pow( n, firstKey(), null );
  }

  /**
   * @param n the number by which the map is multiplied
   * @param fromKey
   *          the key from which all values are multiplied by {@code n}.
   * @return this map after multiplying each value in the range [fromKey,
   *         toKey) by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< V > power( Number n, Parameter< Long > fromKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return power( n, fromKey, null );
  }

  // FIXME -- need to repeat changing of type like in the power() method below
  // for other math operations!

  /**
   * @param n the number by which the map is multiplied
   * @param fromKey the first key whose value is multiplied by {@code n}
   * @param toKey is not multiplied.  To include the last key, pass {@code null} for {@code toKey}.
   * @return this map after multiplying each value in the range [{@code fromKey}, {@code toKey})
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< V > power( Number n, Parameter< Long > fromKey,
                                    Parameter< Long > toKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {

    Map< Parameter< Long >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      map = subMap( fromKey, true, toKey, true );
    } else {
      boolean same = toKey.equals(fromKey);  // include the key if same
      map = subMap( fromKey, true, toKey, same );
    }
    if ( !map.isEmpty() && n.doubleValue() < 0.0 || n.doubleValue() != n.longValue() ) {
      Class<?> cls = ClassUtils.mostSpecificCommonSuperclass( new Class<?>[]{Double.class, getType()} );
      setType( cls );
    }
    for ( Map.Entry< Parameter< Long >, V > e : map.entrySet() ) {
      e.setValue( tryCast(Functions.pow(e.getValue(), n ), getType()) );
    }
    return this;
  }
  

  /**
   * Multiply this map with another. This achieves for all {@code t} in
   * {@code thisBefore.keySet()} and {@code tvm.keySet()},
   * {@code thisAfter.get(t) == thisBefore.get(t) * tvm.get(t)}.
   *
   * @param tvm
   *          the {@code TimeVaryingMap} with which this map is multiplied
   * @return this {@code TimeVaryingMap} after multiplying by {@code tvm}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public <VV> TimeVaryingMap< V > power( TimeVaryingMap< VV > tvm ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tvm == null ) return null;
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    keys.addAll( tvm.keySet() );
    for ( Parameter< Long > k : keys ) {
      VV v = tvm.getValue( k, false );
      Number n = Expression.evaluate( v, Number.class, false );
      power( n, k, k );
    }
    return removeDuplicates();
  }

  /**
   * @param n the number by which the map is multiplied
   * @param fromKey
   *          the key from which all values are multiplied by {@code n}.
   * @return a copy of this map for which each value in the range [{@code fromKey},
   *         {@code toKey}) is multiplied by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< V > pow( Number n, Parameter< Long > fromKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return pow( n, fromKey, null );
  }

  /**
   * Return the product of this map with another. This achieves for all
   * {@code t} in {@code this.keySet()} and {@code tvm.keySet()},
   * {@code newTvm.get(t) == this.get(t) * tvm.get(t)}.
   *
   * @param tvm
   *          the map/timeline by which the map is multiplied
   * @return a copy of this map multiplied by {@code tvm}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public < VV > TimeVaryingMap< V > powOld( TimeVaryingMap< VV > tvm ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.power( tvm );
    return newTvm;
  }

  public < VV > TimeVaryingMap< V > pow( TimeVaryingMap< VV > map ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return applyOperation( map, MathOperation.POW );
//    TimeVaryingMap< V > newTvm = emptyClone();
//    newTvm.clear();
//    Set< Parameter< Long > > keys =
//        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
//    keys.addAll( this.keySet() );
//    keys.addAll( map.keySet() );
//    for ( Parameter< Long> k : keys ) {
//      V v1 = this.getValue( k );
//      VV v2 = map.getValue( k );
//      Object v3 = Functions.pow( v1, v2 );
//      // Multiplying a number and null will return the number.
//      // But this doesn't make sense when the interpolation is NONE.
//      if ( v3 == null && interpolation != NONE ) {
//        v3 = v1 != null ? v1 : v2;
//      }
//      V v4 = tryCastValue( v3 );
//      if ( v4 != null ) {
//        newTvm.setValue( k, v4 );
//      }
//    }
//    return newTvm;
  }


  public static < VV1, VV2 extends Number > TimeVaryingMap< VV1 > pow( TimeVaryingMap< VV1 > tvm1,
                                                                       TimeVaryingMap< VV2 > tvm2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return tvm1.pow( tvm2 );
  }

  /**
   * @param tvm the {@code TimeVaryingMap} with which this map is multiplied
   * @return a copy of this map for which each value in the range [fromKey,
   *         toKey) is multiplied by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public <VV> TimeVaryingMap< VV > pow( Number n, Parameter< Long > fromKey,
                                  Parameter< Long > toKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<VV> cls = (Class<VV>)ClassUtils.dominantTypeClass( getType(), n.getClass() );    
    TimeVaryingMap< VV > newTvm = this.clone(cls);
    newTvm.setName( "pow_" + getName() + "_" + n );
    //TimeVaryingMap< VV > newTvm = new TimeVaryingMap< VV >("pow_" + getName() + "_" + n, this, cls); 
    newTvm.power( n, fromKey, toKey );
    return newTvm;
  }


  
  /**
   * @param n the number by which this map is multiplied
   * @return this map after multiplying each value by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap<V> npower( Number n ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( isEmpty() ) return this;
    if ( TimeVaryingMap.class.isAssignableFrom( getType() ) ) {
      for ( java.util.Map.Entry< Parameter< Long >, V > e : entrySet() ) {
        V v = e.getValue();
        if ( v instanceof TimeVaryingMap ) {
          TimeVaryingMap<?> tvm = (TimeVaryingMap< ? >)v;
          tvm.npower( n );
        }
      }
      return this;
    }
    return npower( n, firstKey(), null );
  }

  /**
   * @param n the number to raise to the power of the map
   * @return a copy of the map with the values, {@code v}, replaced by {@code n^v}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap<V> npow( Number n ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( isEmpty() ) return this.clone();
    return npow( n, firstKey(), null );
  }

  /**
   * @param n the number by which the map is multiplied
   * @param fromKey
   *          the key from which all values are multiplied by {@code n}.
   * @return this map after multiplying each value in the range [fromKey,
   *         toKey) by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< Double > npower( Number n, Parameter< Long > fromKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return npower( n, fromKey, null );
  }

  /**
   * @param n the number by which the map is multiplied
   * @param fromKey the first key whose value is multiplied by {@code n}
   * @param toKey is not multiplied.  To include the last key, pass {@code null} for {@code toKey}.
   * @return this map after multiplying each value in the range [{@code fromKey}, {@code toKey})
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public <VV> TimeVaryingMap< VV > npower( Number n, Parameter< Long > fromKey,
                                    Parameter< Long > toKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    Map< Parameter< Long >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      map = subMap( fromKey, true, toKey, true );
    } else {
      boolean same = toKey.equals(fromKey);  // include the key if same
      map = subMap( fromKey, true, toKey, same );
    }
    if ( !map.isEmpty() ) {
      setType( Double.class );
    }
    for ( Map.Entry< Parameter< Long >, V > e : map.entrySet() ) {
      Number r = Functions.pow(n, e.getValue() );
      e.setValue( tryCastValue( (Double)r.doubleValue() ) );
    }
    return (TimeVaryingMap< VV >)this;
  }

  /**
   * Multiply this map with another. This achieves for all {@code t} in
   * {@code thisBefore.keySet()} and {@code tvm.keySet()},
   * {@code thisAfter.get(t) == thisBefore.get(t) * tvm.get(t)}.
   *
   * @param tvm
   *          the {@code TimeVaryingMap} with which this map is multiplied
   * @return this {@code TimeVaryingMap} after multiplying by {@code tvm}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public <VV> TimeVaryingMap< V > npower( TimeVaryingMap< VV > tvm ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tvm == null ) return null;
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    keys.addAll( tvm.keySet() );
    for ( Parameter< Long > k : keys ) {
      VV v = tvm.getValue( k, false );
      Number n = Expression.evaluate( v, Number.class, false );
      npower( n, k, k );
    }
    return removeDuplicates();
  }

  /**
   * @param n the number by which the map is multiplied
   * @param fromKey
   *          the key from which all values are multiplied by {@code n}.
   * @return a copy of this map for which each value in the range [{@code fromKey},
   *         {@code toKey}) is multiplied by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< V > npow( Number n, Parameter< Long > fromKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return npow( n, fromKey, null );
  }

  /**
   * Return the product of this map with another. This achieves for all
   * {@code t} in {@code this.keySet()} and {@code tvm.keySet()},
   * {@code newTvm.get(t) == this.get(t) * tvm.get(t)}.
   *
   * @param n
   *          the number by which the map is multiplied
   * @return a copy of this map multiplied by {@code tvm}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public < VV > TimeVaryingMap< V > npow( TimeVaryingMap< VV > tvm ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.npower( tvm );
    return newTvm;
  }

  public static < VV1, VV2 extends Number > TimeVaryingMap< VV1 > npow( TimeVaryingMap< VV1 > tvm1,
                                                                       TimeVaryingMap< VV2 > tvm2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return tvm1.npow( tvm2 );
  }

  /**
   * @param tvm the {@code TimeVaryingMap} with which this map is multiplied
   * @return a copy of this map for which each value in the range [fromKey,
   *         toKey) is multiplied by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public <VV> TimeVaryingMap< VV > npow( Number n, Parameter< Long > fromKey,
                                  Parameter< Long > toKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<VV> cls = (Class<VV>)ClassUtils.dominantTypeClass( getType(), n.getClass() );    
    //TimeVaryingMap< VV > newTvm = new TimeVaryingMap< VV >("now_" + getName() + "_" + n, this, cls); 
    TimeVaryingMap< VV > newTvm = this.clone(cls);
    newTvm.setName( "now_" + getName() + "_" + n );
    newTvm.npower( n, fromKey, toKey );
    return newTvm;
  }

  public enum MathOperation { PLUS, MINUS, NEG, TIMES, DIVIDE, POW, LOG, MIN, MAX,
                              MOD, AND, OR, NOT, LT, LTE, GT, GTE, EQ, NEQ };

  public static boolean symmetric( MathOperation op ) {
    switch ( op ) {
      case DIVIDE:
      case LOG:
      case MINUS:
      case MOD:
      case POW:
      case NOT:
      case LT:
      case LTE:
      case GT:
      case GTE:
      case NEG:
        return false;
      case MIN:
      case MAX:
      case PLUS:
      case TIMES: 
      case AND:
      case OR:
      case EQ:
      case NEQ:
        return true;        
      default:
        Debug.error("Unknown MathOperation " + op + "!");
    };
    return false;
  }
  
  /**
   * Return the result of applying a binary operation to this map and another.
   * This achieves for all {@code t} in {@code this.keySet()} and
   * {@code tvm.keySet()}, {@code newTvm.get(t) == this.get(t) OP tvm.get(t)}
   * for operation OP.
   *
   * @param tvm
   *          the map/timeline by which the map is multiplied
   * @param op
   *          the binary operation
   * @return a copy of this map multiplied by {@code tvm}
   * @throws InstantiationException
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   * @throws ClassCastException
   */
  public < VV, VVV > TimeVaryingMap< VVV > applyOperation( TimeVaryingMap< VV > map, MathOperation op ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<VVV> resultClass = (Class< VVV >)ClassUtils.dominantTypeClass( getType(), map.getType() );
    return applyOperation( map, op, resultClass );
  }
  public < VV, VVV > TimeVaryingMap< VVV > applyOperation( TimeVaryingMap< VV > map, MathOperation op, Class<VVV> resultType ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    //Class<VVV> cls = (Class< VVV >)ClassUtils.dominantTypeClass( getType(), map.getType() );
    TimeVaryingMap< VVV > newTvm = emptyClone(resultType);
    newTvm.setType( resultType );
    newTvm.clear();
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    if ( map != null ) {
      keys.addAll( map.keySet() );
    }
    for ( Parameter< Long> k : keys ) {
      V v1 = this.getValue( k );
      VV v2 = map == null ? null : map.getValue( k );
      Object v3 = null;
      switch ( op ) {
        case DIVIDE:
          v3 = Functions.divide( v1, v2 );
          break;
        case LOG:
          //v3 = Functions.log( v1, v2 );
          throw new IllegalArgumentException("log(TimeVaryingMap) not yet supported!");
          //break;
        case MAX:
          v3 = Functions.max( v1, v2 );
          break;
        case MIN:
          v3 = Functions.min( v1, v2 );
          break;
        case MINUS:
          v3 = Functions.minus( v1, v2 );
          break;
        case MOD:
          //v3 = Functions.mod( v1, v2 );
          throw new IllegalArgumentException("mod(TimeVaryingMap) not yet supported!");
          //break;
        case PLUS:
          v3 = Functions.plus( v1, v2 );
          break;
        case POW:
          v3 = Functions.pow( v1, v2 );
          break;
        case TIMES: 
          v3 = Functions.times( v1, v2 );
          break;
        case NEG: 
          v3 = Functions.negative( v1 );
          break;
        case AND:
          v3 = Functions.and( v1, v2 );
          break;
        case OR:
          throw new IllegalArgumentException( "or(V1,V1) not yet implemented!" );
          //v3 = Functions.or( v1, v2 );
          //break;
        case NOT:
          throw new IllegalArgumentException( "not(V1,V1) not yet implemented!" );
          //v3 = Functions.not( v1 );
          //break;
        case LT:
          v3 = Functions.lessThan( v1, v2 );
          break;
        case LTE:
          v3 = Functions.lessThanOrEqual( v1, v2 );
          break;
        case GT:
          throw new IllegalArgumentException( "greaterThan(V1,V1) not yet implemented!" );
          //v3 = Functions.greaterThan( v1, v2 );
          //break;
        case GTE:
          throw new IllegalArgumentException( "greaterThanOrEqual(V1,V1) not yet implemented!" );
          //v3 = Functions.greaterThanOrEqual( v1, v2 );
          //break;
        case EQ:
          throw new IllegalArgumentException( "equals(V1,V1) not yet implemented!" );
          //v3 = Functions.equals( v1, v2 );
          //break;
        case NEQ:
          throw new IllegalArgumentException( "notEquals(V1,V1) not yet implemented!" );
          //v3 = Functions.notEquals( v1, v2 );
          //break;
        default:
          throw new IllegalArgumentException("Unknown MathOperation " + op + "!");
      };
      // Applying the operation to a number and null (in that order) will return
      // the number. If the order of the operands is (null, number), then the
      // number is used only if the operation is symmetric. This behavior may be
      // interpreted as not modifying the existing timeline if the other operand
      // does not exist.
      // But this doesn't make sense when the interpolation is NONE, so nothing
      // is set in that case.
      if ( v3 == null && (interpolation != NONE || map.interpolation != NONE || op != MathOperation.TIMES ) ) {
        v3 = v1 != null ? v1 : (symmetric(op) ? v2 : null);
      }
      VVV v4 = newTvm.tryCastValue( v3 );
      if ( v4 != null ) {
        newTvm.setValue( k, v4 );
      }
    }
    return newTvm;
  }
  

  /**
   * @param n the number by which this map is divided
   * @return this map after dividing each value by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap<V> divide( Number n ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( isEmpty() ) return this;
    return divide( n, firstKey(), null );
  }



  public Call getCallForThisMethod(Object...args) {
    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
    FunctionCall c = new FunctionCall( null, getClass(), methodName, args, (Class<?>)null );
    return c;
  }

  public static Call getCallForThisMethod( Class<?> cls, Object...args ) {
    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
    if ( cls == null ) {
      String className = Thread.currentThread().getStackTrace()[2].getClassName();
      try {
        cls = Class.forName( className );
      } catch ( ClassNotFoundException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    FunctionCall c = new FunctionCall( null, cls, methodName, args, (Class<?>)null );
    return c;
  }

  /**
   * @param n the number by which the map is divided
   * @return a copy of the map whose values are each divided by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public <TT> TimeVaryingMap<TT> dividedBy( Number n ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( isEmpty() ) return (TimeVaryingMap<TT>)this.clone();
    Call c = getCallForThisMethod( n );
    if ( TimeVaryingMap.class.isAssignableFrom( getType() ) ) {
      applyToSubMaps( c );
      return (TimeVaryingMap<TT>)this;
    }
    return (TimeVaryingMap<TT>)dividedBy( n, firstKey(), null );
  }

  /**
   * @param n the number by which the map is divided
   * @param fromKey
   *          the key from which all values are divided by {@code n}.
   * @return this map after divideing each value in the range [fromKey,
   *         toKey) by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< V > divide( Number n, Parameter< Long > fromKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return divide( n, fromKey, null );
  }

  /**
   * @param n the number by which the map is divided
   * @param fromKey the first key whose value is divided by {@code n}
   * @param toKey is not divided.  To include the last key, pass {@code null} for {@code toKey}.
   * @return this map after dividing each value in the range [{@code fromKey}, {@code toKey})
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< V > divide( Number n, Parameter< Long > fromKey,
                                     Parameter< Long > toKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    Map< Parameter< Long >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      map = subMap( fromKey, true, toKey, true );
    } else {
      boolean same = toKey.equals(fromKey);  // include the key if same
      map = subMap( fromKey, true, toKey, same );
    }
    for ( Map.Entry< Parameter< Long >, V > e : map.entrySet() ) {
      V v = Functions.divide(e.getValue(), n );
      e.setValue( v );
    }
    return this;
  }

  /**
   * Multiply this map with another. This achieves for all {@code t} in
   * {@code thisBefore.keySet()} and {@code tvm.keySet()},
   * {@code thisAfter.get(t) == thisBefore.get(t) * tvm.get(t)}.
   *
   * @param tvm
   *          the {@code TimeVaryingMap} with which this map is multiplied
   * @return this {@code TimeVaryingMap} after multiplying by {@code tvm}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public <VV> TimeVaryingMap< V > divide( TimeVaryingMap< VV > tvm ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tvm == null ) return null;
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    keys.addAll( tvm.keySet() );
    for ( Parameter< Long > k : keys ) {
      VV v = tvm.getValue( k, false );
      Number n = Expression.evaluate( v, Number.class, false );
      //if ( n != null ) {// n.doubleValue() != 0 ) {
      divide( n, k, k );
      //}
    }
    return removeDuplicates();
  }
  
  /**
   * @param n the number by which the map is divided
   * @param fromKey
   *          the key from which all values are divided by {@code n}.
   * @return a copy of this map for which each value in the range [{@code fromKey},
   *         {@code toKey}) is divided by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public <VV> TimeVaryingMap< VV > dividedBy( Number n, Parameter< Long > fromKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return dividedBy( n, fromKey, null );
  }

  /**
   * @param n the number by which the map is divided
   * @param fromKey the first key whose value is divided by {@code n}
   * @param toKey
   *          is not divided. To include the last key, pass {@code null} for {@code toKey}.
   * @return a copy of this map for which each value in the range [fromKey,
   *         toKey) is divided by {@code n}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public <VV> TimeVaryingMap< VV > dividedBy( Number n, Parameter< Long > fromKey,
                                        Parameter< Long > toKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<VV> cls = (Class<VV>)ClassUtils.dominantTypeClass( getType(), n.getClass() );    
    //TimeVaryingMap< VV > newTvm = new TimeVaryingMap< VV >(getName() + "_div_" + n, this, cls); 
    TimeVaryingMap< VV > newTvm = clone(cls);
    newTvm.setName( getName() + "_div_" + n );
    newTvm.divide( n, fromKey, toKey );
    return newTvm;
  }

  /**
   * Return the quotient of this map with another. This achieves for all
   * {@code t} in {@code this.keySet()} and {@code tvm.keySet()},
   * {@code newTvm.get(t) == this.get(t) / tvm.get(t)}.
   *
   * @param tvm
   *          the map by which this map is divided
   * @return a copy of this map multiplied by {@code tvm}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public < VV extends Number > TimeVaryingMap< V > dividedByOld( TimeVaryingMap< VV > tvm ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.divide( tvm );
    return newTvm;
  }
  
  public <VV> TimeVaryingMap< V > dividedBy( TimeVaryingMap< VV > map ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return applyOperation( map, MathOperation.DIVIDE );
//    TimeVaryingMap< V > newTvm = emptyClone();
//    newTvm.clear();
//    Set< Parameter< Long > > keys =
//        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
//    keys.addAll( this.keySet() );
//    keys.addAll( map.keySet() );
//    for ( Parameter< Long> k : keys ) {
//      V v1 = this.getValue( k );
//      VV v2 = map.getValue( k );
//      Object v3 = Functions.divide( v1, v2 );
//      // Multiplying a number and null will return the number.
//      // But this doesn't make sense when the interpolation is NONE.
//      if ( v3 == null && interpolation != NONE ) {
//        v3 = v1 != null ? v1 : v2;
//      }
//      V v4 = tryCastValue( v3 );
//      if ( v4 != null ) {
//        newTvm.setValue( k, v4 );
//      }
//    }
//    return newTvm;
  }



  /**
   * Return the quotient of two maps. This achieves for all
   * {@code t} in {@code tvm1.keySet()} and {@code tvm2.keySet()},
   * {@code newTvm.get(t) == tvm1.get(t) / tvm2.get(t)}.
   *
   * @param tvm1
   * @param tvm1
   * @return a copy of {@code tvm1} divided by {@code tvm2}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public static < VV1, VV2 > TimeVaryingMap< VV1 > dividedBy( TimeVaryingMap< VV1 > tvm1,
                                                                             TimeVaryingMap< VV2 > tvm2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return tvm1.dividedBy( tvm2 );
  }


  /**
   * Return the quotient of two maps. This achieves for all
   * {@code t} in {@code tvm1.keySet()} and {@code tvm2.keySet()},
   * {@code newTvm.get(t) == tvm1.get(t) / tvm2.get(t)}.
   *
   * @param tvm1
   * @param tvm1
   * @return a copy of {@code tvm1} divided by {@code tvm2}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public static < VV1 extends Number, VV2, VV3 > TimeVaryingMap< VV3 > dividedBy( VV1 o1,
                                                                             TimeVaryingMap< VV2 > tvm2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null ) return null;
    TimeVaryingMap<VV3> tvm1 = (TimeVaryingMap< VV3 >)tvm2.emptyClone();
    tvm1.setValue( new SimpleTimepoint( 0L ), (VV3)o1 );
    return dividedBy(tvm1, tvm2);
  }


  /**
   * @param n
   * @return a this map after adding {@code n} to each value
   */
  public TimeVaryingMap<V> add( Number n ) {
    if ( isEmpty() ) return this;
    return add( n, firstKey(), null );
  }

  /**
   * @param n
   * @return a copy of this map for which {@code n} is added to each value
   */
  public TimeVaryingMap<V> plus( Number n ) {
    if ( isEmpty() ) return this.clone();
    return plus( n, firstKey(), null );
  }

  /**
   * @param n
   * @return a copy of this map for which {@code n} is min'd with each value
   */
  public TimeVaryingMap<V> minClone( Number n ) {
    if ( isEmpty() ) return this.clone();
    return minClone( n, firstKey(), null );
  }

  /**
   * @param n
   * @return a copy of this map for which {@code n} is max'd with each value
   */
  public TimeVaryingMap<V> maxClone( Number n ) {
    if ( isEmpty() ) return this.clone();
    return maxClone( n, firstKey(), null );
  }

//  /**
//   * @param n
//   * @return a copy of this map for which {@code n} is lessThan'd with each value
//   */
//  public TimeVaryingMap<V> lessThanClone( Number n ) {
//    if ( isEmpty() ) return this.clone();
//    return lessThanClone( n, firstKey(), null );
//  }
//
//  /**
//   * @param n
//   * @return a copy of this map for which {@code n} is lessThanOrEqual'd with each value
//   */
//  public TimeVaryingMap<V> lessThanOrEqualClone( Number n ) {
//    if ( isEmpty() ) return this.clone();
//    return lessThanOrEqualClone( n, firstKey(), null );
//  }

  public boolean contains( Long t ) {
    return getKey( t ) != null;
  }
  public Parameter< Long> getKey( Long t ) {
    if ( isEmpty() ) return null;
    Parameter< Long > tt = makeTempTimepoint( t, false );
    return getKey( tt, true );
//    Parameter< Long > bk = ceilingKey(tt);
//    if ( bk != null  && bk.getValueNoPropagate().equals( t ) ) {
//      return bk;
//    }
//    bk = lowerKey(tt);
//    if ( bk != null  && bk.getValueNoPropagate().equals( t ) ) {
//      return bk;
//    }
//    return null;
  }


  /**
   * Returns a view of the portion of this map whose keys range from
   * {@code fromKey} to {@code toKey}.  If {@code fromKey} and
   * {@code toKey} are equal, the returned map is empty unless
   * {@code fromExclusive} and {@code toExclusive} are both true.  The
   * returned map is backed by this map, so changes in the returned map are
   * reflected in this map, and vice-versa.  The returned map supports all
   * optional map operations that this map supports.
   *
   * <p>The returned map will throw an {@code IllegalArgumentException}
   * on an attempt to insert a key outside of its range, or to construct a
   * submap either of whose endpoints lie outside its range.
   *
   * @param fromKey low endpoint of the keys in the returned map
   * @param fromInclusive {@code true} if the low endpoint
   *        is to be included in the returned view
   * @param toKey high endpoint of the keys in the returned map
   * @param toInclusive {@code true} if the high endpoint
   *        is to be included in the returned view
   * @return a view of the portion of this map whose keys range from
   *         {@code fromKey} to {@code toKey}
   * @throws ClassCastException if {@code fromKey} and {@code toKey}
   *         cannot be compared to one another using this map's comparator
   *         (or, if the map has no comparator, using natural ordering).
   *         Implementations may, but are not required to, throw this
   *         exception if {@code fromKey} or {@code toKey}
   *         cannot be compared to keys currently in the map.
   */
  public NavigableMap< Parameter< Long >, V > subMap( Long timeFrom,
                                                         boolean fromInclusive,
                                                         Long timeTo,
                                                         boolean toInclusive ) {
    if ( isEmpty() ) return this;
    Parameter< Long> tpe = getTimepointEarlier( timeFrom );
    Parameter< Long > f = firstKey();
    if ( tpe == null ) {
      tpe = f;
      fromInclusive = true;
    }
    Parameter< Long> tpl = getTimepointLater( timeTo );
    if ( tpl == null ) {
      tpl = lastKey();
      toInclusive = true;
    }
    int c = compareTo( tpe, tpl );

    if ( c > 0 ) return this.subMap( f, false, f, false );
    if ( c == 0 ) {
      if ( tpe.valueEquals( timeFrom ) ) {
        return subMap( f, true, f, true );
      }
      return this.subMap( f, false, f, false );
    }
    return subMap(tpe, fromInclusive, tpl, toInclusive);

//    Parameter< Long> fromKey, toKey;
//    fromKey = getKey( timeFrom );
//    toKey = getKey( nextTime );
//    return this.subMap( fromKey, fromInclusive, toKey, toInclusive );
  }

  /**
   * @param tt
   *          the timepoint to which a key is sought to match
   * @return all keys in the map that match the input timepoint {@code tt}.
   */
  public Set< Parameter< Long > > getKeys( Parameter< Long> tt ) {
    return getKeys( tt.getValueNoPropagate() );
  }
  /**
   * @param t
   *          the timepoint to which a key is sought to match
   * @return all keys in the map that match the input time value {@code t}.
   */
  public Set< Parameter< Long > > getKeys( Long t ) {
    return subMap( t, true, t, true ).keySet();
/*
  if ( isEmpty() ) return Collections.emptySet();
    Parameter< Long> tpe = getTimepointEarlier( t );
    boolean includeFrom = ( tpe == null );
    if ( includeFrom ) {
      tpe = firstKey();
    }
    Parameter< Long> tpl = getTimepointLater( t );
    boolean includeTo = ( tpl == null );
    if ( includeTo ) {
      tpl = lastKey();
    }
    int c = compareTo( tpe, tpl );
    if ( c > 0 ) return Collections.emptySet();
    if ( c == 0 ) {
      if ( tpe.valueEquals( t ) ) {
        Set< Parameter< Long >> s = new HashSet< Parameter< Long >>();
        s.add( tpe );
        return s;
      }
      return Collections.emptySet();
    }
    return subMap(tpe, includeFrom, tpl, includeTo).keySet();
*/
  }
  /**
   * @param tt
   *          the timepoint to which a key is sought to match
   * @param equalValuesOk
   *          specifies whether {@code key.equals(tt)} must be true for the
   *          returned {@code key} or if matching {@code Long} values are
   *          good enough.
   * @param firstOrLast if true, the first of the set of matching keys is returned; else, the last of the set is returned.
   * @return the key in the map that matches the input timepoint {@code tt}.
   */
  public Parameter< Long> getKey( Parameter< Long > tt, boolean equalValuesOk,
                                    boolean firstOrLast ) {
    Long v = tt.getValue(false);
    NavigableMap< Parameter< Long >, V > m = subMap( v, true, v, true );
    if ( m.isEmpty() ) return null;
    Parameter< Long > timepoint = firstOrLast ? m.firstKey() : m.lastKey();
    return timepoint;
  }
  /**
   * @param tt
   *          the timepoint to which a key is sought to match
   * @param equalValuesOk
   *          specifies whether {@code key.equals(tt)} must be true for the
   *          returned {@code key} or if matching {@code Long} values are
   *          good enough.
   * @return the key in the map that matches the input timepoint {@code tt}.
   */
  public Parameter< Long> getKey( Parameter< Long > tt, boolean equalValuesOk ) {
    NavigableMap< Parameter< Long >, V > m = headMap( tt, true );

    Parameter< Long > k;
    if ( !m.isEmpty() ) {
      k = m.lastKey();
      if ( k != null && k.getValue( false ) == null ) {
        Debug.error( true, "Error! Found null key "
                           + MoreToString.Helper.toLongString( k )
                           + " in TimeVaryingMap: "
                           + MoreToString.Helper.toLongString( this ) );
      }
      if ( k == tt || ( equalValuesOk && k.equals( tt ) ) ) return k;
    }

    if ( !equalValuesOk ) return null;
    Parameter< Long > bk = getTimepointAfter(tt);
    if ( bk != null && bk.valueEquals( tt ) ) {
      return bk;
    }
//    bk = getTimepointBefore( tt );
//    if ( bk != null && bk.equals( tt ) ) {
//      return bk;
//    }
    return null;

//    Parameter< Long > bk = lowerKey(tt);
//    if ( k == null )
//    bk = ceilingKey(tt);
//    if ( bk != null && bk.equals( tt ) ) {
//      return bk;
//    }
//    return null;
  }

  /**
   * If not already in the map, insert the timepoint, {@code key} (or a clone of
   * {@code key} if owned by another object), along with the value of this map
   * at that timepoint. If {@code onlyIfDifferentValue} is true, then
   * {@code key} is considered in the map if a key with the same {@code Long}
   * value exists.
   *
   * @param key
   * @param onlyIfDifferentValue
   * @return the inserted key or the existing key if already in the map
   */
  public Parameter< Long > putKey( Parameter< Long > key,
                                      boolean onlyIfDifferentValue ) {

    if ( key == null ) return null;
    Parameter< Long > k = getKey( key, onlyIfDifferentValue );
    if ( k != null ) return k;
//    if ( key.getOwner() != this ) {
//      if ( key.getOwner() != null ) {
//        key = (Parameter< Long >)key.clone();
//      }
//      key.setOwner( this );
//    }
    put( key, getValue( key, onlyIfDifferentValue ) );
    return key;
  }

  /**
   * @param n
   * @param fromKey
   * @param toKey
   *          the first key after {@code fromKey} to whose value is not added {@code n}. To
   *          include the last key, pass {@code null} for {@code toKey}.
   * @return this map after adding {@code n} to each value in the range [{@code fromKey},
   *         {@code toKey})
   */
  public TimeVaryingMap< V > add1( Number n, Parameter< Long > fromKey,
                                   Parameter< Long > toKey ) {
//    if ( fromKey.getValueNoPropagate() == 10 ) {
//      Debug.outln("");
//    }
    fromKey = putKey( fromKey, true );
    toKey = putKey( toKey, true );
    Map< Parameter< Long >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      if ( before( toKey, fromKey ) ) toKey = fromKey;
      map = subMap( fromKey, true, toKey, true );
    } else {
      boolean same = toKey.equals(fromKey);  // include the key if same
      map = subMap( fromKey, true, toKey, same );
    }
    for ( Map.Entry< Parameter< Long >, V > e : map.entrySet() ) {
      if ( e.getValue() instanceof Double ) {
        Double v = (Double)e.getValue();
        v = v + n.doubleValue();
        try {
          e.setValue( tryCastValue( v ) );
        } catch ( Exception exc ) {
          // ignore
        }
      } else if ( e.getValue() instanceof Long ) {
        Long v = (Long)e.getValue();
        // TODO -- handle Long?
        v = (long)( v + n.doubleValue() );
        try {
          e.setValue( tryCastValue( v ) );
        } catch ( Exception exc ) {
          // ignore
        }
      } else if ( e.getValue() instanceof Integer ) {
        Integer v = (Integer)e.getValue();
        // TODO -- handle Long?
        v = (int)( v + n.doubleValue() );
        try {
          e.setValue( tryCastValue( v ) );
        } catch ( Exception exc ) {
          // ignore
        }
      }
    }
    return this;
  }

  /**
   * @param n
   * @param fromKey
   *          the key from which {@code n} is added to all values.
   * @return this map after adding {@code n} to each value in the range [{@code fromKey},
   *         {@code toKey})
   */
  public TimeVaryingMap< V > add( Number n, Parameter< Long > fromKey ) {
    return add( n, fromKey, null );
  }

  public V integral(Parameter< Long > fromKey, Parameter< Long > toKey) {
    TimeVaryingMap< V > tvm = integrate(fromKey, toKey, null);
    if ( tvm == null || tvm.isEmpty() ) return tryCastValue( 0 );
    return tvm.getValue( tvm.lastKey() );
  }
  
  public TimeVaryingMap< V > integrate(Parameter< Long > fromKey,
                                       Parameter< Long > toKey) {
    return integrate( fromKey, toKey, null );
  }
  public TimeVaryingMap< V > integrate(Parameter< Long > fromKey,
                                       Parameter< Long > toKey, TimeVaryingMap< V > tvm ) {
    if ( tvm == null ) tvm = new TimeVaryingMap< V >( this.name + "Integral", this.type );
    if ( this.interpolation.type == Interpolation.STEP ) {
      tvm.interpolation.type = Interpolation.LINEAR;
    } else {
      Debug.error( true, "No support yet for quadratic interpolation as integral of linear function!" );
    }
    
    boolean same = toKey == fromKey;  // include the key if same
    Parameter< Long > insertedFromKey = null;
    Parameter< Long > insertedToKey = null;
    if ( !containsKey( fromKey ) ) {
      fromKey = putKey( fromKey, false );
      insertedFromKey = fromKey;
    }
    if ( same ) {
      toKey = fromKey;
    } else {
      if ( !containsKey( toKey ) ) {
        toKey = putKey( toKey, false );
        insertedToKey = toKey;
      }
    }
    Map< Parameter< Long >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      if ( before( toKey, fromKey ) ) toKey = fromKey;
      map = subMap( fromKey, true, toKey, true );
    } else {
      if ( before( toKey, fromKey) ) toKey = fromKey;
      map = subMap( fromKey, true, toKey, same );
    }
    boolean succeededSomewhere = false;
    Map.Entry< Parameter< Long >, V > ePrev = null;
    V lastIntegralValue = tryCastValue( 0 );
    for ( Map.Entry< Parameter< Long >, V > e : map.entrySet() ) {
      V integralValue = null;
      Long timeDiff = 0L;
      if ( ePrev != null && ePrev.getKey() != null ) {
        Long v2 = e.getKey().getValueNoPropagate();
        Long v1 = ePrev.getKey().getValueNoPropagate();
        if ( v2 != null && v1 != null ) {
          timeDiff = v2 - v1;
        }
      }
      try {
          if ( ePrev == null ) {
            integralValue = tryCastValue( (Double)0.0 );
          } else {
            V endValue = ePrev.getValue();
            if ( this.interpolation.isLinear() ) {
              endValue = e.getValue();
            }
            if ( lastIntegralValue == null || endValue == null || ePrev.getValue() == null ) {
              integralValue = null;
            } else {
              integralValue =
                  tryCastValue( ( (Double)lastIntegralValue )
                                + ( ( ( (Double)endValue ) + (Double)ePrev.getValue() ) / 2.0 )
                                * timeDiff );
            }
            succeededSomewhere = integralValue != null;
          }
      } catch ( ClassCastException exc ) {
        exc.printStackTrace();
      }
      if ( integralValue != null ) {
        tvm.put( e.getKey(), integralValue );
        lastIntegralValue = integralValue;
      }
      ePrev = e;
    }
    if ( insertedFromKey != null ) {
      remove(insertedFromKey);
    }
    if ( insertedToKey != null ) {
      remove(insertedToKey);
    }
    //if (succeededSomewhere) appliedSet.add(  )
    return tvm;
  }
  
  public TimeVaryingMap< V > integrate() {
    return integrate(new SimpleTimepoint(0), new SimpleTimepoint(Timepoint.horizonDuration));
  }
    
  
  /**
   * @param n
   * @param fromKey
   * @param toKey
   *          the first key after {@code fromKey} to whose value is not added {@code n}. To
   *          include the last key, pass {@code null} for {@code toKey}.  null values are
   *          treated as zero when adding with a non-null.
   * @return this map after adding {@code n} to each value in the range [{@code fromKey},
   *         {@code toKey})
   */
  public TimeVaryingMap< V > add( Number n, Parameter< Long > fromKey,
                                  Parameter< Long > toKey ) {

    //if ( n == null ) return; //REVIEW
    boolean same = toKey == fromKey;  // include the key if same
    fromKey = putKey( fromKey, false );
    if ( same ) {
      toKey = fromKey;
    } else {
      toKey = putKey( toKey, false );
    }
    Map< Parameter< Long >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      if ( before( toKey, fromKey ) ) toKey = fromKey;
      map = subMap( fromKey, true, toKey, true );
    } else {
      map = subMap( fromKey, true, toKey, same );
    }
    boolean succeededSomewhere = false;
    for ( Map.Entry< Parameter< Long >, V > e : map.entrySet() ) {
      if ( e.getValue() == null ) {
        try {
          e.setValue( tryCastValue( n ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Double ) {
        Double v = (Double)e.getValue();
        if ( n == null ) n = 0.0;
        v = v + n.doubleValue();
        try {
          e.setValue( tryCastValue( v ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Float ) {
        Float v = (Float)e.getValue();
        if ( n == null ) n = 0.0;
        v = v + n.floatValue();
        try {
          e.setValue( tryCastValue( v ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Long ) {
        Long v = (Long)e.getValue();
        if ( n == null ) n = 0.0;
        v = (long)( v + n.doubleValue() );
        try {
          e.setValue( tryCastValue( v ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Integer ) {
        Integer v = (Integer)e.getValue();
        if ( n == null ) n = 0.0;
        v = (int)( v + n.doubleValue() );
        try {
          e.setValue( tryCastValue( v ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      }
    }
    //if (succeededSomewhere) appliedSet.add(  )
    return this;
  }

  /**
   * @param n
   * @param fromKey
   * @param toKey
   *          the first key after {@code fromKey} to whose value is not min'd {@code n}. To
   *          include the last key, pass {@code null} for {@code toKey}.  null values are
   *          treated as zero when min'ing with a non-null.
   * @return this map after min'ing {@code n} with each value in the range [{@code fromKey},
   *         {@code toKey})
   */
  public TimeVaryingMap< V > min( Number n, Parameter< Long > fromKey,
                                  Parameter< Long > toKey ) {

    //if ( n == null ) return; //REVIEW
    boolean same = toKey == fromKey;  // include the key if same
    fromKey = putKey( fromKey, false );
    if ( same ) {
      toKey = fromKey;
    } else {
      toKey = putKey( toKey, false );
    }
    Map< Parameter< Long >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      if ( before( toKey, fromKey ) ) toKey = fromKey;
      map = subMap( fromKey, true, toKey, true );
    } else {
      map = subMap( fromKey, true, toKey, same );
    }
    boolean succeededSomewhere = false;
    for ( Map.Entry< Parameter< Long >, V > e : map.entrySet() ) {
      if ( e.getValue() == null ) {
        try {
          e.setValue( tryCastValue( n ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Double ) {
        Double v = (Double)e.getValue();
        if ( n == null ) n = 0.0;
        v = Math.min(v, n.doubleValue() );
        try {
          e.setValue( tryCastValue( v ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Float ) {
        Float v = (Float)e.getValue();
        if ( n == null ) n = 0.0;
        v = Math.min( v, n.floatValue() );
        try {
          e.setValue( tryCastValue( v ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Long ) {
        Long v = (Long)e.getValue();
        if ( n == null ) n = 0.0;
        v = (long)( Math.min( v, n.doubleValue() ) );
        try {
          e.setValue( tryCastValue( v ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Integer ) {
        Integer v = (Integer)e.getValue();
        if ( n == null ) n = 0.0;
        v = (int)( Math.min(v, n.doubleValue() ) );
        try {
          e.setValue( tryCastValue( v ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      }
    }
    //if (succeededSomewhere) appliedSet.add(  )
    return this;
  }

  /**
   * @param tvm the {@code TimeVaryingMap} to be min'd with this {@code TimeVaryingMap}
   * @return this {@code TimeVaryingMap} after min'ing {@code tvm}
   */
  public <VV> TimeVaryingMap< V > min( TimeVaryingMap< VV > tvm ) {
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    keys.addAll( tvm.keySet() );
    for ( Parameter< Long > k : keys ) {
      VV v = tvm.getValue( k, false );
      Number n;
      try {
        n = Expression.evaluate( v, Number.class, false );
        min( n, k, k );
      } catch ( ClassCastException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return removeDuplicates();
  }

  /**
   * @param n
   * @param fromKey
   * @param toKey
   *          the first key after {@code fromKey} to whose value is not max'd {@code n}. To
   *          include the last key, pass {@code null} for {@code toKey}.  null values are
   *          treated as zero when maxing with a non-null.
   * @return this map after maxing {@code n} with each value in the range [{@code fromKey},
   *         {@code toKey})
   */
  public TimeVaryingMap< V > max( Number n, Parameter< Long > fromKey,
                                  Parameter< Long > toKey ) {

    //if ( n == null ) return; //REVIEW
    boolean same = toKey == fromKey;  // include the key if same
    fromKey = putKey( fromKey, false );
    if ( same ) {
      toKey = fromKey;
    } else {
      toKey = putKey( toKey, false );
    }
    Map< Parameter< Long >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      if ( before( toKey, fromKey ) ) toKey = fromKey;
      map = subMap( fromKey, true, toKey, true );
    } else {
      map = subMap( fromKey, true, toKey, same );
    }
    boolean succeededSomewhere = false;
    for ( Map.Entry< Parameter< Long >, V > e : map.entrySet() ) {
      if ( e.getValue() == null ) {
        try {
          e.setValue( tryCastValue( n ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Double ) {
        Double v = (Double)e.getValue();
        if ( n == null ) n = 0.0;
        v = Math.max(v, n.doubleValue() );
        try {
          e.setValue( tryCastValue( v ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Float ) {
        Float v = (Float)e.getValue();
        if ( n == null ) n = 0.0;
        v = Math.max( v, n.floatValue() );
        try {
          e.setValue( tryCastValue( v ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Long ) {
        Long v = (Long)e.getValue();
        if ( n == null ) n = 0.0;
        v = (long)( Math.max( v, n.doubleValue() ) );
        try {
          e.setValue( tryCastValue( v ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Integer ) {
        Integer v = (Integer)e.getValue();
        if ( n == null ) n = 0.0;
        v = (int)( Math.max(v, n.doubleValue() ) );
        try {
          e.setValue( tryCastValue( v ) );
          succeededSomewhere = true;
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      }
    }
    //if (succeededSomewhere) appliedSet.add(  )
    return this;
  }

  /**
   * @param tvm the {@code TimeVaryingMap} to be max'd to this {@code TimeVaryingMap}
   * @return this {@code TimeVaryingMap} after maxing {@code tvm}
   */
  public <VV> TimeVaryingMap< V > max( TimeVaryingMap< VV > tvm ) {
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    keys.addAll( tvm.keySet() );
    for ( Parameter< Long > k : keys ) {
      VV v = tvm.getValue( k, false );
      Number n;
      try {
        n = Expression.evaluate( v, Number.class, false );
        max( n, k, k );
      } catch ( ClassCastException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return removeDuplicates();
  }

//  /**
//   * @param n
//   * @param fromKey
//   * @param toKey
//   *          the first key after {@code fromKey} to whose value is not max'd {@code n}. To
//   *          include the last key, pass {@code null} for {@code toKey}.  null values are
//   *          treated as zero when maxing with a non-null.
//   * @return this map after maxing {@code n} with each value in the range [{@code fromKey},
//   *         {@code toKey})
//   */
//  public TimeVaryingMap< Boolean > lessThan( Number n, Parameter< Long > fromKey,
//                                             Parameter< Long > toKey ) {
//
//    //if ( n == null ) return; //REVIEW
//    boolean same = toKey == fromKey;  // include the key if same
//    fromKey = putKey( fromKey, false );
//    if ( same ) {
//      toKey = fromKey;
//    } else {
//      toKey = putKey( toKey, false );
//    }
//    Map< Parameter< Long >, V > map = null;
//    if ( toKey == null ) {
//      toKey = lastKey();
//      if ( before( toKey, fromKey ) ) toKey = fromKey;
//      map = subMap( fromKey, true, toKey, true );
//    } else {
//      map = subMap( fromKey, true, toKey, same );
//    }
//    boolean succeededSomewhere = false;
//    for ( Map.Entry< Parameter< Long >, V > e : map.entrySet() ) {
//      if ( e.getValue() == null ) {
//        try {
//          e.setValue( tryCastValue( n ) );
//          succeededSomewhere = true;
//        } catch ( ClassCastException exc ) {
//          exc.printStackTrace();
//        }
//      } else if ( e.getValue() instanceof Double ) {
//        Double v = (Double)e.getValue();
//        if ( n == null ) n = 0.0;
//        v = Math.max(v, n.doubleValue() );
//        try {
//          e.setValue( tryCastValue( v ) );
//          succeededSomewhere = true;
//        } catch ( ClassCastException exc ) {
//          exc.printStackTrace();
//        }
//      } else if ( e.getValue() instanceof Float ) {
//        Float v = (Float)e.getValue();
//        if ( n == null ) n = 0.0;
//        v = Math.max( v, n.floatValue() );
//        try {
//          e.setValue( tryCastValue( v ) );
//          succeededSomewhere = true;
//        } catch ( ClassCastException exc ) {
//          exc.printStackTrace();
//        }
//      } else if ( e.getValue() instanceof Integer ) {
//        Integer v = (Integer)e.getValue();
//        if ( n == null ) n = 0.0;
//        v = (int)( Math.max(v, n.doubleValue() ) );
//        try {
//          e.setValue( tryCastValue( v ) );
//          succeededSomewhere = true;
//        } catch ( ClassCastException exc ) {
//          exc.printStackTrace();
//        }
//      } else if ( e.getValue() instanceof Long ) {
//        Long v = (Long)e.getValue();
//        if ( n == null ) n = 0.0;
//        v = (long)( Math.max( v, n.doubleValue() ) );
//        try {
//          e.setValue( tryCastValue( v ) );
//          succeededSomewhere = true;
//        } catch ( ClassCastException exc ) {
//          exc.printStackTrace();
//        }
//      }
//    }
//    //if (succeededSomewhere) appliedSet.add(  )
//    return this;
//  }
//
//  /**
//   * @param tvm the {@code TimeVaryingMap} to be max'd to this {@code TimeVaryingMap}
//   * @return this {@code TimeVaryingMap} after maxing {@code tvm}
//   */
//  public <VV> TimeVaryingMap< V > lessThan( TimeVaryingMap< VV > tvm ) {
//    Set< Parameter< Long > > keys =
//        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
//    keys.addAll( this.keySet() );
//    keys.addAll( tvm.keySet() );
//    for ( Parameter< Long > k : keys ) {
//      VV v = tvm.getValue( k, false );
//      Number n;
//      try {
//        n = Expression.evaluate( v, Number.class, false );
//        max( n, k, k );
//      } catch ( ClassCastException e ) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      } catch ( IllegalAccessException e ) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      } catch ( InvocationTargetException e ) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      } catch ( InstantiationException e ) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
//    }
//    return removeDuplicates();
//  }
//

  /**
   * @param tvm the {@code TimeVaryingMap} to be added to this {@code TimeVaryingMap}
   * @return this {@code TimeVaryingMap} after adding {@code tvm}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public <VV> TimeVaryingMap< V > add( TimeVaryingMap< VV > tvm ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    for ( Parameter< Long >  k : tvm.keySet() ) {
      putKey( k, true );
    }
    for ( Parameter< Long > k : keySet() ) {
      VV v = tvm.getValue( k, false );
      Number n = Expression.evaluate( v, Number.class, false );
      add( n, k, k );
    }
    return removeDuplicates();
  }

  /**
   * @param n
   * @return this map after subtracting {@code n} from each value
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap<V> subtract( Number n ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( isEmpty() ) return this;
    return subtract( n, firstKey(), null );
  }

  /**
   * @param n
   * @param fromKey
   *          the key from which {@code n} is subtracted from all values.
   * @return this map after subtracting {@code n} from each value in the range [{@code fromKey},
   *         {@code toKey})
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< V > subtract( Number n, Parameter< Long > fromKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return subtract( n, fromKey, null );
  }

  /**
   * @param n
   * @param fromKey
   * @param toKey
   *          the first key after {@code fromKey} to whose value is not subtracted by {@code n}.
   *          To include the last key, pass {@code null} for {@code toKey}.
   * @return this map after subtracting {@code n} from each value in the range [{@code fromKey},
   *         {@code toKey})
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< V > subtract( Number n, Parameter< Long > fromKey,
                                       Parameter< Long > toKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return add( Functions.times(n, -1), fromKey, toKey );
  }

  /**
   * @param tvm
   *          the {@code TimeVaryingMap} to be subtracted from this
   *          {@code TimeVaryingMap}
   * @return this {@code TimeVaryingMap} after subtracting {@code tvm}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public <VV> TimeVaryingMap< V > subtract( TimeVaryingMap< VV > tvm ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tvm == null ) return null;
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    keys.addAll( tvm.keySet() );
    //System.out.println( "this - tvm -> this\n" + this.toString( true, false, null ) + "\n + " + tvm.toString( true, false, null ) );
    for ( Parameter< Long > k : keys ) {
      VV v = tvm.getValue( k, false );
      Number n = Expression.evaluate( v, Number.class, false );
      //if ( n != null ) {// n.doubleValue() != 0 ) {
      subtract( n, k, k );
      //}
    }
    //System.out.println( " = " + this.toString( true, false, null ) );
    removeDuplicates();
    //System.out.println( " = " + this.toString( true, false, null ) );
    return this;
  }

  /**
   * @return this map after removing all but one entry from each set of adjacent
   *         entries with the same values.
   */
  public <VV> TimeVaryingMap< V > removeDuplicates() {
    if ( Debug.isOn() ) Debug.outln( "before removing duplicates " + this );
    List<Parameter< Long > > dups = new ArrayList< Parameter< Long > >();
    Parameter< Long> lastKey = null;
    V lastValue = null;
    for ( java.util.Map.Entry< Parameter< Long >, V > e : entrySet() ) {
      Parameter< Long > key = e.getKey();
      V value = e.getValue();
      if ( Utils.valuesEqual( lastKey, key ) ) {
        dups.add( lastKey );
      } else if ( Utils.valuesEqual( lastValue, value ) ) {
        dups.add( key );
        key = lastKey;
      }
      lastKey = key;
      lastValue = value;
    }
    for ( Parameter< Long> k : dups ) {
      remove( k );
    }
    if ( Debug.isOn() ) Debug.outln( " after removing duplicates " + this );
     return this;
  }

  
  /**
   * @param n
   * @param fromKey
   *          the key from which {@code n} is added to all values.
   * @return a copy of this map for which {@code n} is added to each value in the range
   *         [{@code fromKey}, {@code toKey})
   */
  public TimeVaryingMap< V > plus( Number n, Parameter< Long > fromKey ) {
    return plus( n, fromKey, null );
  }
  /**
   * @param n
   * @param fromKey
   * @param toKey
   *          the first key after {@code fromKey} to whose value is not added {@code n}. To
   *          include the last key, pass {@code null} for {@code toKey}.
   * @return a copy of this map for which {@code n} is added to each value in the range
   *         [{@code fromKey}, {@code toKey})
   */
  public <VV> TimeVaryingMap< VV > plus( Number n, Parameter< Long > fromKey,
                                    Parameter< Long > toKey ) {
    Class<VV> cls = (Class< VV >)ClassUtils.dominantTypeClass( getType(), n.getClass() );
    //TimeVaryingMap< VV > newTvm = new TimeVaryingMap<VV>(getName() + "_plus_" + n, this, cls);//this.clone();
    TimeVaryingMap< VV > newTvm = clone(cls);
    newTvm.setName( getName() + "_plus_" + n );
    newTvm.add( n, fromKey, toKey );
    return newTvm;
  }

  /**
   * @param map
   * @return a copy of this TimeVaryingMap with {@code map} added to it
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public <VV extends Number> TimeVaryingMap< V > plusOld( TimeVaryingMap< VV > map ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.add( map );
    return newTvm;
  }

  public <VV extends Number> TimeVaryingMap< V > plus( TimeVaryingMap< VV > map ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return applyOperation( map, MathOperation.PLUS );
//    TimeVaryingMap< V > newTvm = emptyClone();
//    newTvm.clear();
//    Set< Parameter< Long > > keys =
//        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
//    keys.addAll( this.keySet() );
//    keys.addAll( map.keySet() );
//    for ( Parameter< Long> k : keys ) {
//      V v1 = this.getValue( k );
//      VV v2 = map.getValue( k );
//      Object v3 = Functions.plus( v1, v2 );
//      // Adding null to a number should return the number. This is important
//      // when interpolation is NONE.
//      if ( v3 == null ) {
//        v3 = v1 != null ? v1 : v2; 
//      }
//      V v4 = tryCastValue( v3 );
//      if ( v4 != null ) {
//        newTvm.setValue( k, v4 );
//      }
//    }
//    return newTvm;
  }

  
  /**
   * @param map1
   * @param map2
   * @return a new map that sums {@code map1} and {@code map2}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public static < VV1, VV2 extends Number > TimeVaryingMap< VV1 > plus( TimeVaryingMap< VV1 > map1,
                                                                        TimeVaryingMap< VV2 > map2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return map1.plus( map2 );
  }

  /**
   * @param map
   * @return a copy of this TimeVaryingMap with {@code map} subtracted from it
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public < VV extends Number > TimeVaryingMap< V > minus( TimeVaryingMap< VV > map ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.subtract( map );
    return newTvm;
  }

  /**
   * @param map1
   * @param map2
   * @return a new map that is {@code map1} minus {@code map2}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public static < VV1, VV2 extends Number > TimeVaryingMap< VV1 > minus( TimeVaryingMap< VV1 > map1,
                                                                         TimeVaryingMap< VV2 > map2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return map1.minus( map2 );
  }

  /**
   * @param n
   * @return a copy of this map for which {@code n} is subtracted from each value
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap<V> minus( Number n ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( isEmpty() ) return this.clone();
    return minus( n, firstKey(), null );
  }

  /**
   * @param n
   * @param fromKey
   *          the key from which {@code n} is subtracted from all values.
   * @return a copy of this map for which {@code n} is subtracted from each value in the range
   *         [{@code fromKey}, {@code toKey})
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public TimeVaryingMap< V > minus( Number n, Parameter< Long > fromKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return minus( n, fromKey, null );
  }
  /**
   * @param n
   * @param fromKey
   * @param toKey
   *          the first key after {@code fromKey} to whose value is not subtracted by {@code n}. To
   *          include the last key, pass {@code null} for {@code toKey}.
   * @return a copy of this map for which {@code n} is subtracted from each value in the range
   *         [{@code fromKey}, {@code toKey})
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public <VV> TimeVaryingMap< VV > minus( Number n, Parameter< Long > fromKey,
                                          Parameter< Long > toKey ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    //TimeVaryingMap< V > newTvm = this.clone();
    Class<VV> cls = (Class< VV >)ClassUtils.dominantTypeClass( getType(), n.getClass() );
    TimeVaryingMap< VV > newTvm = new TimeVaryingMap< VV >(getName() + "_minus_" + n, this, cls);
    newTvm.subtract( n, fromKey, toKey );
    return newTvm;
  }


  /**
   * @param n
   * @param fromKey
   *          the key from which {@code n} is min'd with all values.
   * @return a copy of this map for which {@code n} is min'd with each value in the range
   *         [{@code fromKey}, {@code toKey})
   */
  public TimeVaryingMap< V > min( Number n, Parameter< Long > fromKey ) {
    return min( n, fromKey, null );
  }
  /**
   * @param n
   * @param fromKey
   * @param toKey
   *          the first key after {@code fromKey} to whose value is not min'd {@code n}. To
   *          include the last key, pass {@code null} for {@code toKey}.
   * @return a copy of this map for which {@code n} is min'd with each value in the range
   *         [{@code fromKey}, {@code toKey})
   */
  public <VV> TimeVaryingMap< VV > minClone( Number n, Parameter< Long > fromKey,
                                             Parameter< Long > toKey ) {
    Class<VV> cls = (Class< VV >)ClassUtils.dominantTypeClass( getType(), n.getClass() );
    TimeVaryingMap< VV > newTvm = this.clone(cls);
    newTvm.min( n, fromKey, toKey );
    return newTvm;
  }

  /**
   * @param map
   * @return a copy of this TimeVaryingMap with {@code map} min'd with it
   */
  public <VV extends Number> TimeVaryingMap< V > minCloneOld( TimeVaryingMap< VV > map ) {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.min( map );
    return newTvm;
  }

  public <VV,VVV> TimeVaryingMap< VVV > minClone( TimeVaryingMap< VV > tvm ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return applyOperation( tvm, MathOperation.MIN );
  }


  /**
   * @param map1
   * @param map2
   * @return a new map that mins {@code map1} and {@code map2}
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   */
  public static < VV1, VV2 extends Number > TimeVaryingMap< VV1 > min( TimeVaryingMap< VV1 > map1,
                                                                       TimeVaryingMap< VV2 > map2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return map1.minClone( map2 );
  }

  /**
   * @param n
   * @param fromKey
   *          the key from which {@code n} is max'd with all values.
   * @return a copy of this map for which {@code n} is max'd with each value in the range
   *         [{@code fromKey}, {@code toKey})
   */
  public TimeVaryingMap< V > max( Number n, Parameter< Long > fromKey ) {
    return max( n, fromKey, null );
  }
  /**
   * @param n
   * @param fromKey
   * @param toKey
   *          the first key after {@code fromKey} to whose value is not max'd {@code n}. To
   *          include the last key, pass {@code null} for {@code toKey}.
   * @return a copy of this map for which {@code n} is max'd with each value in the range
   *         [{@code fromKey}, {@code toKey})
   */
  public <VV> TimeVaryingMap< VV > maxClone( Number n, Parameter< Long > fromKey,
                                       Parameter< Long > toKey ) {
    Class<VV> cls = (Class< VV >)ClassUtils.dominantTypeClass( getType(), n.getClass() );
    //TimeVaryingMap< VV > newTvm = this.clone(cls);
    TimeVaryingMap< VV > newTvm = this.clone(cls);
    newTvm.setName( "max_" + getName() + "_" + n );
    //TimeVaryingMap< VV > newTvm = new TimeVaryingMap< VV >("max_" + getName() + "_" + n, this, cls); 
    newTvm.max( n, fromKey, toKey );
    return newTvm;
  }

  /**
   * @param map
   * @return a copy of this TimeVaryingMap with {@code map} max'd with it
   */
  public <VV> TimeVaryingMap< V > maxClone( TimeVaryingMap< VV > tvm ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return applyOperation( tvm, MathOperation.MAX );
  }

  public <VV extends Number> TimeVaryingMap< V > maxCloneOld( TimeVaryingMap< VV > map ) {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.max( map );
    return newTvm;
  }

  /**
   * @param map1
   * @param map2
   * @return a new map that maxes {@code map1} and {@code map2}
   * @throws InstantiationException 
   * @throws IllegalAccessException 
   * @throws InvocationTargetException 
   * @throws ClassCastException 
   */
  public static < VV1, VV2 extends Number > TimeVaryingMap< VV1 > max( TimeVaryingMap< VV1 > map1,
                                                                       TimeVaryingMap< VV2 > map2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return map1.maxClone( map2 );
  }

  public static < L, VV1, VV2 > TimeVaryingMap< L > argmax( L l1, TimeVaryingMap< VV1 > map1,
                                                            L l2, TimeVaryingMap< VV2 > map2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return argminormax( l1, map1, l2, map2, false );
  }
  public static < L, VV1, VV2 > TimeVaryingMap< L > argmin( L l1, TimeVaryingMap< VV1 > map1,
                                                            L l2, TimeVaryingMap< VV2 > map2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return argminormax( l1, map1, l2, map2, true );
  }
  public static < L, VV1, VV2 > TimeVaryingMap< L > argminormax( L l1, TimeVaryingMap< VV1 > map1,
                                                                 L l2, TimeVaryingMap< VV2 > map2,
                                                                 boolean isMin ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    // Handle null and empty maps; if both null, return null; if both are empty,
    // return an empty map; if one is empty, return a map with the other label
    // starting at the start of the non-empty map.
    if ( map1 == null && map2 == null ) return null;
    TimeVaryingMap< L > newTvm = new TimeVaryingMap< L >();
    boolean m1empty = Utils.isNullOrEmpty( map1 );
    boolean m2empty = Utils.isNullOrEmpty( map2 );
    if ( m1empty && m2empty ) { return newTvm; }
    if ( m1empty ) {
      L label = l2 instanceof TimeVaryingMap ? (L)((TimeVaryingMap<?>)l2).getValue( map2.firstKey() ) : l2;
      newTvm.setValue( map2.firstKey(), label );
      return newTvm;
    }
    if ( m2empty ) {
      L label = l1 instanceof TimeVaryingMap ? (L)((TimeVaryingMap<?>)l1).getValue( map1.firstKey() ) : l1;
      newTvm.setValue( map1.firstKey(), l1 );
      return newTvm;
    }

    // Collect keys and compute entries for each.
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    keys.addAll( map1.keySet() );
    keys.addAll( map2.keySet() );
    for ( Parameter< Long> k : keys ) {
      VV1 v1 = map1.getValue( k );
      VV2 v2 = map2.getValue( k );
      L label = null;
      if ( v2 == null ) label = l1;
      else if ( v1 == null ) label = l2;
      else {
        int comp = CompareUtils.compare( v1, v2 );
        label = (comp == (isMin ? 1 : -1)) ? l2 : l1;
      }
      if ( label instanceof TimeVaryingMap ) {
        label = (L)((TimeVaryingMap<?>)label).getValue( k );
      }
      newTvm.setValue( k, label );
    }
    newTvm.removeDuplicates();
    return newTvm;
  }

  public static < L, VV1, VV2 > TimeVaryingMap< L > argminormax( L l1, TimeVaryingMap< VV1 > map1,
                                                                 L l2, TimeVaryingMap< VV2 > map2,
                                                                 L l3, TimeVaryingMap< VV2 > map3,
                                                                 boolean isMin ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    TimeVaryingMap< L > newTvm = new TimeVaryingMap< L >();
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    keys.addAll( map1.keySet() );
    keys.addAll( map2.keySet() );
    keys.addAll( map3.keySet() );
    for ( Parameter< Long> k : keys ) {
      VV1 v1 = map1.getValue( k );
      VV2 v2 = map2.getValue( k );
      VV2 v3 = map3.getValue( k );
      L label = null;
      Object vminormax = null;
      int comp = 0;
      if ( v2 == null ) {
        label = l1;
        vminormax = v1;
      }
      else if ( v1 == null ) {
        label = l2;
        vminormax = v2;
      }
      else {
        comp = CompareUtils.compare( v1, v2 );
        label = (comp == (isMin ? 1 : -1)) ? l2 : l1;
        vminormax = (comp == (isMin ? 1 : -1)) ? v2 : v1;
      }
      if ( v3 != null ) {
        comp = CompareUtils.compare( vminormax, v3 );
        label = (comp == (isMin ? 1 : -1)) ? l3 : label;
      }
      if ( label instanceof TimeVaryingMap ) {
        label = (L)((TimeVaryingMap<?>)label).getValue( k );
      }
      newTvm.setValue( k, label );
    }
    return newTvm;
  }

  public static < L, VV1, VV2 > TimeVaryingMap< L > argmax( L l1, TimeVaryingMap< VV1 > map1,
                                                            L l2, VV2 v2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return argminormax( l1, map1, l2, v2, false );
  }
  public static < L, VV1, VV2 > TimeVaryingMap< L > argmin( L l1, TimeVaryingMap< VV1 > map1,
                                                            L l2, VV2 v2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return argminormax( l1, map1, l2, v2, true );
  }
  public static < L, VV1, VV2 > TimeVaryingMap< L > argminormax( L l1, TimeVaryingMap< VV1 > map1,
                                                                 L l2, VV2 v2,
                                                                 boolean isMin ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    TimeVaryingMap< L > newTvm = new TimeVaryingMap< L >();
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    keys.addAll( map1.keySet() );
    for ( Parameter< Long> k : keys ) {
      VV1 v1 = map1.getValue( k );
      int comp = CompareUtils.compare( v1, v2 );
      L label = (comp == (isMin ? 1 : -1)) ? l2 : l1;
      if ( label instanceof TimeVaryingMap ) {
        label = (L)((TimeVaryingMap<?>)label).getValue( k );
      }
      newTvm.setValue( k, label );
    }
    return newTvm;
  }

  public static enum Inequality { EQ, NEQ, LT, LTE, GT, GTE }; 
  public static enum BoolOp { AND, OR, XOR, NOT }; 

  public static TimeVaryingMap<Boolean> equals(TimeVaryingMap<?> map, Number n) {
    return compare(map, n, false, Inequality.EQ);
  }
  public static TimeVaryingMap<Boolean> equals(Number n, TimeVaryingMap<?> map) {
    return compare(map, n, true, Inequality.EQ);
  }
  public static TimeVaryingMap<Boolean> equals(TimeVaryingMap<?> map1, TimeVaryingMap<?> map2) {
    return compare(map1, map2, Inequality.EQ);
  }

  public static TimeVaryingMap<Boolean> notEquals(TimeVaryingMap<?> map, Number n) {
    return compare(map, n, false, Inequality.NEQ);
  }
  public static TimeVaryingMap<Boolean> notEquals(Number n, TimeVaryingMap<?> map) {
    return compare(map, n, true, Inequality.NEQ);
  }
  public static TimeVaryingMap<Boolean> notEquals(TimeVaryingMap<?> map1, TimeVaryingMap<?> map2) {
    return compare(map1, map2, Inequality.NEQ);
  }

  public static TimeVaryingMap<Boolean> lessThan(TimeVaryingMap<?> map, Number n) {
    return compare(map, n, false, Inequality.LT);
  }
  public static TimeVaryingMap<Boolean> lessThan(Number n, TimeVaryingMap<?> map) {
    return compare(map, n, true, Inequality.LT);
  }
  public static TimeVaryingMap<Boolean> lessThan(TimeVaryingMap<?> map1, TimeVaryingMap<?> map2) {
    return compare(map1, map2, Inequality.LT);
  }
  public static TimeVaryingMap<Boolean> lessThanOrEqual(TimeVaryingMap<?> map, Number n) {
    return compare(map, n, false, Inequality.LTE);
  }
  public static TimeVaryingMap<Boolean> lessThanOrEqual(Number n, TimeVaryingMap<?> map) {
    return compare(map, n, true, Inequality.LTE);
  }
  public static TimeVaryingMap<Boolean> lessThanOrEqual(TimeVaryingMap<?> map1, TimeVaryingMap<?> map2) {
    return compare(map1, map2, Inequality.LTE);
  }

  public static TimeVaryingMap<Boolean> greaterThan(TimeVaryingMap<?> map, Number n) {
    return compare(map, n, false, Inequality.GT);
  }
  public static TimeVaryingMap<Boolean> greaterThan(Number n, TimeVaryingMap<?> map) {
    return compare(map, n, true, Inequality.GT);
  }
  public static TimeVaryingMap<Boolean> greaterThan(TimeVaryingMap<?> map1, TimeVaryingMap<?> map2) {
    return compare(map1, map2, Inequality.GT);
  }
  public static TimeVaryingMap<Boolean> greaterThanOrEqual(TimeVaryingMap<?> map, Number n) {
    return compare(map, n, false, Inequality.GTE);
  }
  public static TimeVaryingMap<Boolean> greaterThanOrEqual(Number n, TimeVaryingMap<?> map) {
    return compare(map, n, true, Inequality.GTE);
  }
  public static TimeVaryingMap<Boolean> greaterThanOrEqual(TimeVaryingMap<?> map1, TimeVaryingMap<?> map2) {
    return compare(map1, map2, Inequality.GTE);
  }

  
  public static TimeVaryingMap<Boolean> compare(TimeVaryingMap<?> map, Number n, boolean reverse, Inequality i) {
    if ( map == null || n == null ) return null;
    TimeVaryingMap<Boolean> result = new TimeVaryingMap< Boolean >();  // REVIEW -- give it a name?
    for ( Map.Entry< Parameter< Long >, ? > e : map.entrySet() ) {
      Object mapVal = map.getValue( e.getKey() );
      Boolean value = null;
      if ( mapVal != null ) {
        value = reverse ? doesInequalityHold( n, mapVal, i )
                        : doesInequalityHold( mapVal, n, i );
      }
      result.setValue( e.getKey(), value );
    }
    result.removeDuplicates();
    return result;
  }

  public static TimeVaryingMap<Boolean> compare(TimeVaryingMap<?> map1, TimeVaryingMap<?> map2, Inequality i) {
    if ( map1 == null || map2 == null ) return null;
    TimeVaryingMap<Boolean> result = new TimeVaryingMap< Boolean >();  // REVIEW -- give it a name?
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >(map1.keySet());
    keys.addAll( map2.keySet() );
    for ( Parameter< Long> t : keys ) {
      Object v1 = map1.getValue(t);
      Object v2 = map2.getValue(t);
      Boolean value = null;
      if ( v1 != null && v2 != null ) {
        value = doesInequalityHold( v1, v2, i );
      }
      result.setValue( t, value );
    }
    result.removeDuplicates();
    return result;
  }
  
  public static boolean doesInequalityHold( Object v1, Object v2, Inequality i) {
    boolean value = false;
    int comp = CompareUtils.compare( v1, v2 );
    switch (i) {
      case EQ:
        value = comp == 0;
        break;
      case NEQ:
        value = comp != 0;
        break;
      case LT:
        value = comp < 0;
        break;
      case LTE:
        value = comp <= 0;
        break;
      case GT:
        value = comp > 0;
        break;
      case GTE:
        value = comp >= 0;
        break;
      default:
        // TODO -- error
    }
    return value;
  }
  
  public static TimeVaryingMap<Boolean> and(TimeVaryingMap<?> map, Boolean n) {
    return applyBool(map, n, false, BoolOp.AND);
  }
  public static TimeVaryingMap<Boolean> and(Boolean n, TimeVaryingMap<?> map) {
    return applyBool(map, n, true, BoolOp.AND);
  }
  public static TimeVaryingMap<Boolean> and(TimeVaryingMap<?> map1, TimeVaryingMap<?> map2) {
    return applyBool(map1, map2, BoolOp.AND);
  }

  public static TimeVaryingMap<Boolean> or(TimeVaryingMap<?> map, Boolean n) {
    return applyBool(map, n, false, BoolOp.OR);
  }
  public static TimeVaryingMap<Boolean> or(Boolean n, TimeVaryingMap<?> map) {
    return applyBool(map, n, true, BoolOp.OR);
  }
  public static TimeVaryingMap<Boolean> or(TimeVaryingMap<?> map1, TimeVaryingMap<?> map2) {
    return applyBool(map1, map2, BoolOp.OR);
  }

  public static TimeVaryingMap<Boolean> xor(TimeVaryingMap<?> map, Boolean n) {
    return applyBool(map, n, false, BoolOp.XOR);
  }
  public static TimeVaryingMap<Boolean> xor(Boolean n, TimeVaryingMap<?> map) {
    return applyBool(map, n, true, BoolOp.XOR);
  }
  public static TimeVaryingMap<Boolean> xor(TimeVaryingMap<?> map1, TimeVaryingMap<?> map2) {
    return applyBool(map1, map2, BoolOp.XOR);
  }

  public static TimeVaryingMap<Boolean> not(TimeVaryingMap<?> map) {
    return applyBool(map, null, false, BoolOp.NOT);
  }

  public static TimeVaryingMap< Boolean > applyBool( TimeVaryingMap< ? > map,
                                                      Boolean n, boolean reverse,
                                                      BoolOp boolOp ) {
    if ( map == null || n == null ) return null;
    TimeVaryingMap<Boolean> result = new TimeVaryingMap< Boolean >();  // REVIEW -- give it a name?
    for ( Map.Entry< Parameter< Long >, ? > e : map.entrySet() ) {
      Object mapVal = map.getValue( e.getKey() );
      Boolean value = null;
      if ( mapVal != null ) {
        value = reverse ? applyOp( n, mapVal, boolOp )
                        : applyOp( mapVal, n, boolOp );
      }
      result.setValue( e.getKey(), value );
    }
    result.removeDuplicates();
    return result;
  }

  public static TimeVaryingMap< Boolean > applyBool( TimeVaryingMap< ? > map1,
                                                      TimeVaryingMap< ? > map2,
                                                      BoolOp boolOp ) {
    if ( map1 == null || map2 == null ) return null;
    TimeVaryingMap<Boolean> result = new TimeVaryingMap< Boolean >();  // REVIEW -- give it a name?
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >(map1.keySet());
    keys.addAll( map2.keySet() );
    for ( Parameter< Long> t : keys ) {
      Object v1 = map1.getValue(t);
      Object v2 = map2.getValue(t);
      Boolean value = null;
      if ( v1 != null && v2 != null ) {
        value = applyOp( v1, v2, boolOp );
      }
      result.setValue( t, value );
    }
    result.removeDuplicates();
    return result;
  }
  
  public static Boolean applyOp( Object v1, Object v2, BoolOp boolOp ) {
    Boolean b1 = Utils.isTrue( v1, true ); 
    Boolean b2 = Utils.isTrue( v2, true );
    if ( b1 == null && b2 == null ) return null;
    boolean isOneNull = b1 == null || b2 == null;
    boolean nonNullValue = b1 == null ? b2.booleanValue() : b1.booleanValue();
    switch( boolOp ) {
      case AND:
        if ( isOneNull ) {
          if ( !nonNullValue ) return false;
          return null;
        }
        return b1 && b2;
      case OR:
        if ( isOneNull ) {
          if ( nonNullValue ) return true;
          return null;
        }
        return b1 || b2;
      case XOR:
        if ( isOneNull ) return null;
        return b1 ^ b2;
      case NOT:
        return !b1;
      default:
        return null;
    }
  }
  
  public static Object getValueAtTime(Object object, Parameter< Long> t) {
    if ( object instanceof TimeVarying ) {
      return ((TimeVarying< Long, ?>)object).getValue( t );
    }
    return object;
  }
  
  public Object ifThenElse( Object condition, Object thenObj, Object elseObj ) {
    Pair< Boolean, TimeVaryingMap< ? > > p = Functions.booleanOrTimeline( condition );
    if ( p.second != null ) {
      TimeVaryingMap<Object> newTvm = getEmptyMap( thenObj, elseObj );
      newTvm = p.second.ifThenElseToTarget( thenObj, elseObj, newTvm );
      return newTvm;
//      return p.second.ifThenElse( thenObj, elseObj );
    }
    Boolean b = Utils.isTrue( condition, false );
    if ( b == null ) return null;
    if ( b ) return thenObj;
    return elseObj;
  }
  
  protected static TimeVaryingMap<?> mapToClone( Object thenObj, Object elseObj ) {
    TimeVaryingMap<?> mapToClone = null;
    if ( thenObj instanceof TimeVaryingMap ) {
      if ( elseObj instanceof TimeVaryingMap ) {
        Class<?> cls = ClassUtils.dominantObjectType( thenObj, elseObj );
        if ( cls != null && cls.isInstance( thenObj ) ) {
          mapToClone = (TimeVaryingMap<?>)thenObj;
        } else if ( cls != null && cls.isInstance( elseObj ) ) {
          mapToClone = (TimeVaryingMap<?>)elseObj;
        }
      } else {
        mapToClone = (TimeVaryingMap<?>)thenObj;
      }
    } else if ( elseObj instanceof TimeVaryingMap ) {
      mapToClone = (TimeVaryingMap<?>)elseObj;
    }
    return mapToClone;
  }

  protected TimeVaryingMap<Object> getEmptyMap( Object thenObj, Object elseObj ) {
    TimeVaryingMap<?> mapToClone = mapToClone(thenObj, elseObj);
    TimeVaryingMap<Object> newTvm = (TimeVaryingMap< Object >)( mapToClone == null ? new TimeVaryingMap<Object>() : mapToClone.emptyClone() );
    return newTvm;
  }
  
  public TimeVaryingMap<Object> ifThenElse( Object thenObj, Object elseObj ) {
    TimeVaryingMap<Object> newTvm = getEmptyMap( thenObj, elseObj );
    newTvm = ifThenElseToTarget( thenObj, elseObj, newTvm );
    return newTvm;
  }
  
  public TimeVaryingMap<Object> ifThenElseToTarget( Object thenObj, Object elseObj, TimeVaryingMap<Object> newTvm ) {
    Set< Parameter< Long > > keys =
        new TreeSet< Parameter< Long > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    if ( thenObj instanceof TimeVaryingMap ) {
      keys.addAll( ((TimeVaryingMap<?>)thenObj).keySet() );
    }
    if ( elseObj instanceof TimeVaryingMap ) {
      keys.addAll( ((TimeVaryingMap<?>)elseObj).keySet() );
    }
    for ( Parameter< Long> k : keys ) {
      Object v3 = null;
      V v1 = this.getValue( k );
      if ( Utils.isTrue( v1 ) == Boolean.TRUE ) {
        v3 = getValueAtTime(thenObj, k);
      } else {
        v3 = getValueAtTime(elseObj, k);
      }
      newTvm.setValue( k, v3 );
    }
    if ( newTvm != null ) newTvm.removeDuplicates();
    return newTvm;
  }

  
  /**
   * Validate the consistency of the map for individual and adjacent entries.
   * @return whether or not the entries in the map make sense.
   */
  public boolean isConsistent() {
    Parameter< Long> lastTp = null;
    long lastTime = -1;
    boolean ok = true;
    ArrayList<V> valuesAtSameTime = new ArrayList< V >();
    ArrayList< Parameter< Long > > timesAtSameTime =
        new ArrayList< Parameter< Long > >();
    boolean firstEntry = true;
    for ( Map.Entry< Parameter< Long>, V >  entry : entrySet() ) {
      Parameter< Long> tp = entry.getKey();
      V value = null;
      try {
        value = entry.getValue();
      } catch ( ClassCastException cce ) {
        ok = false;
        System.err.println( "Error! Value "
                            + MoreToString.Helper.toLongString( entry.getValue() )
                            + " has the wrong type in TimeVaryingMap! "
                            + MoreToString.Helper.toLongString( this ) );
        cce.printStackTrace();
      }
      boolean timepointValueChanged = true;
      boolean duplicateValueAtTime = false;
      // Check for problems with the key.
      // No null keys.
      if ( tp == null ) {
        Debug.error( true, "Error! null key in TimeVaryingMap " + getName() );
        ok = false;
      } else if ( tp.getValueNoPropagate() == null ) {
        // No null values for Parameter< Long> key.
        Debug.errorOnNull( true,
                           "Error! null param value for Parameter< Long> "
                               + MoreToString.Helper.toLongString( tp )
                               + " in TimeVaryingMap " + getName(),
                           tp.getValueNoPropagate() );
        ok = false;
      } else {
       timepointValueChanged = !tp.getValueNoPropagate().equals( lastTime );
        if ( !firstEntry && tp.getValueNoPropagate() < lastTime ) {
          // Time cannot decrease.
          Debug.error( true, "Error! time value for entry "
                             + MoreToString.Helper.toLongString( entry )
                             + " should be >= "
                             + MoreToString.Helper.toLongString( lastTime )
                             + " in TimeVaryingMap " + getName() );
          ok = false;
        } else {
          lastTime = tp.getValueNoPropagate();
        }
      }
      if ( timepointValueChanged ) {
        valuesAtSameTime.clear();
        timesAtSameTime.clear();
      }
      timesAtSameTime.add( tp );
      duplicateValueAtTime = valuesAtSameTime.contains( value );
      if ( tp != null && tp == lastTp ) {
        // A key should have only one entry.
        Debug.error( true, "Error! Parameter< Long> has duplicate entry "
                           + MoreToString.Helper.toLongString( entry )
                           + " in TimeVaryingMap " + getName() );
        ok = false;
      }

      // Check for problems with the value.
      if ( value != null && getType() != null
           && !getType().isAssignableFrom( value.getClass() ) ) {
        Debug.error( true,
                     "Error! value " + MoreToString.Helper.toLongString( value )
                         + " at time " + MoreToString.Helper.toLongString( tp )
                         + " has an incompatible type "
                         + value.getClass().getCanonicalName()
                         + "; getType() = " + getType().getCanonicalName() );
        ok = false;
      }
      if ( !firstEntry && duplicateValueAtTime ) {
        if ( Debug.isOn() ) {
          Debug.err( "Warning! duplicate entry of value "
                     + MoreToString.Helper.toLongString( value ) + " at time "
                     + MoreToString.Helper.toLongString( tp ) + " with time set "
                     + MoreToString.Helper.toLongString( timesAtSameTime )
                     + " and value set "
                     + MoreToString.Helper.toLongString( valuesAtSameTime )
                     + " for TimeVaryingMap " + getName() );
        }
        ok = false;
      }
      lastTp = tp;
      if ( !duplicateValueAtTime ) {
        valuesAtSameTime.add( value );
      }
      firstEntry = false;
    }
    if ( ok ) {
      if ( Debug.isOn() ) Debug.outln( getName() + " is consistent" );
    } else {
      if ( Debug.isOn() ) Debug.outln( getName()
                                       + " is not consistent: "
                                       + MoreToString.Helper.toLongString( this ) );
    }
    return ok;
  }

  @Override
  public V unsetValue( Parameter< Long > t, V value ) {
    breakpoint();
    if ( t == null ) {
      if ( Debug.isOn() ) {
        Debug.error( false,
                     "Warning! unsetValue("
                         + MoreToString.Helper.toLongString( t ) + ", "
                         + MoreToString.Helper.toLongString( value )
                         + "): Null Parameter< Long> key for TimeVaryingMap "
                         + MoreToString.Helper.toLongString( this ) );
      }
      return null;
    }
    if ( t.getValueNoPropagate() == null ) {
      if ( Debug.isOn() ) {
        Debug.error( false,
                     "Warning! unsetValue("
                         + MoreToString.Helper.toLongString( t ) + ", "
                         + MoreToString.Helper.toLongString( value )
                         + "): Null value for Parameter< Long> key for TimeVaryingMap "
                         + MoreToString.Helper.toLongString( this ) );
      }
      return null;
    }
    V oldValue = null;
    Parameter< Long> tpInMap = keyForValueAt( value, t );
// possible fix to a bug, but fixing it elsewhere
//    Parameter< Long> tI = tryCastTimepoint( t );
//    Parameter< Long> tpInMap = keyForValueAt( value, tI.getValueNoPropagate() );
    if ( tpInMap != t ) {
      if ( Debug.isOn() ) {
        Debug.error( false,
                     "Warning! unsetValue("
                         + MoreToString.Helper.toLongString( t ) + ", "
                         + MoreToString.Helper.toLongString( value )
                         + "): Parameter< Long> key is not in the map for TimeVaryingMap "
                         + MoreToString.Helper.toLongString( this ) );
      }
      return null;
    }
    if ( tpInMap == null ) {
      if ( Debug.isOn() ) {
        Debug.error( false,
                     "Warning! unsetValue("
                         + MoreToString.Helper.toLongString( t ) + ", "
                         + MoreToString.Helper.toLongString( value )
                         + "): no matching entry in TimeVaryingMap "
                         + MoreToString.Helper.toLongString( this ) );
      }
    } else {
      oldValue = get( tpInMap );
      if ( Debug.isOn() ) {
        Debug.outln( "unsetValue(" + MoreToString.Helper.toLongString( t )
                     + ", " + MoreToString.Helper.toLongString( value )
                     + "): removing entry ("
                     + MoreToString.Helper.toLongString( tpInMap ) + ", "
                     + MoreToString.Helper.toLongString( oldValue ) + ") in "
                     + this.toString( true, true, null ) );
      }
      remove( tpInMap );
    }
    if ( Debug.isOn() || checkConsistency ) isConsistent();
    return oldValue;
  }

  public void unapply( Effect effect ) {
    unapply( effect, true );
  }
  public void unapply( Effect effect, boolean timeArgFirst ) {
    if ( isArithmeticEffect( effect ) ) {
      Effect inverseEffect = getInverseEffect( effect );
      if ( Debug.isOn() ) {
        Debug.outln( "unapply("
                     + MoreToString.Helper.toString( effect, true, false, null )
                     + ") : inverseEffect = "
                     + MoreToString.Helper.toString( effect, true, false,
                                                     null ) );
      }
      if ( canBeApplied( inverseEffect ) ) {
        inverseEffect.applyTo( this, true );
        appliedSet.remove(effect);
        appliedSet.remove( inverseEffect );
      } else {
        Debug.error( true, "Error! Cannot unapply effect: "
                           + MoreToString.Helper.toLongString( effect ) );
      }
//      return;
    }
    Pair< Parameter< Long>, V > p =
        getTimeAndValueOfEffect( effect, timeArgFirst );
    if ( p != null ) {
      unsetValue( p.first, p.second );
      appliedSet.remove(effect);
    }
  }

  public Effect getInverseEffect( Effect effect ) {
    if ( isArithmeticEffect( effect ) && effect instanceof EffectFunction) {
      EffectFunction eff = (EffectFunction)effect;
      Method inverseMethod = getInverseMethod( eff.getMethod() );
      if ( inverseMethod == null ) return null;
      EffectFunction invEff = new EffectFunction( eff );
      invEff.setMethod( inverseMethod );
      return invEff;
    }
    // REVIEW -- Should we include setValue() & unsetValue()?
    return null;
  }

  public Method getInverseMethod( Method method ) {
    if ( method == null ) return null;  // REVIEW -- complain?
    Method inv = getInverseMethods().get( method );
    return inv;
  }
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListener#detach(gov.nasa.jpl.ae.event.Parameter)
   */
  @Override
  public void detach( Parameter< ? > parameter ) {
    if ( parameter instanceof Parameter ) {
      remove( parameter );
    }
    Set<Parameter<?>> detachSet = new HashSet< Parameter<?> >();
    for ( Entry< Parameter< Long>, V > e : this.clone().entrySet() ) {
      if ( e.getValue() instanceof HasParameters ) {
        if ( ( (HasParameters)e.getValue() ).hasParameter( parameter, false,
                                                           null ) ) {
          detachSet.add( e.getKey() );
        }
      }
    }
    for ( Parameter<?> p : detachSet ) detach( p );
  }


  @Override
  public boolean isFreeParameter( Parameter< ? > parameter, boolean deep,
                                  Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    return HasParameters.Helper.isFreeParameter( this, parameter, deep, seen, false );
  }

  /* (non-Javadoc)
   * @see java.util.AbstractMap#equals(java.lang.Object)
   * Note: this class has a natural ordering that is inconsistent with equals.
   */
  @Override
  public boolean equals( Object o ) {
    if ( this == o ) return true;
    if ( o instanceof TimeVarying ) {
      return ( compareTo( (TimeVarying< Long, V>)o, false ) == 0 );
    }
    return false;
  }

  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo( TimeVarying< Long, V > o ) {
    return compareTo( o, true );
  }
  public int compareTo( TimeVarying< Long, V > o, boolean checkId ) {
    if ( o == null ) return 1;
    if ( checkId ) return CompareUtils.compare( getId(), o.getId() );
    int compare = 0;
    if ( o instanceof TimeVaryingMap ) {
      TimeVaryingMap<?> otvm = (TimeVaryingMap<?>)o;
      compare = CompareUtils.compare( getName(), otvm.getName(), true );
      if ( compare != 0 ) return compare;
    }
    if ( Debug.isOn() ) Debug.err( "TimeVaryingMap.compareTo() may compare values, which, if changed while this is in a map, can corrupt the map." );
    compare = CompareUtils.compare( this, o ); // WARNING: values change!!!
    if ( compare != 0 ) return compare;
    return compare;
  }

  // TODO -- use getValue() to compare TimeVaryingMaps!!!
  //public int compareValues

  public static Method getSetValueMethod() {
    return getSetValueWithParameterMethod();
  }
  public static Method getSetValueWithParameterMethod() {
    if ( setValueMethod1 == null ) {
      for ( Method m : TimeVaryingMap.class.getMethods() ) {
        if ( m.getName().equals("setValue") && m.getParameterTypes() != null
             && m.getParameterTypes().length == 2
             && m.getParameterTypes() [0] == Parameter.class ) {
          setValueMethod1 = m;
        }
      }
    }
    return setValueMethod1;
  }

  protected static void setArithmeticMethods() {
    //Parameter< Long> t = new Parameter< Long >( null );
    Class< TimeVaryingMap > cls = TimeVaryingMap.class;
    if ( arithmeticMethods == null ) {
      arithmeticMethods = new TreeSet< Method >( methodComparator );
    } else {
      arithmeticMethods.clear();
    }
    try {
      addNumberMethod =
          cls.getMethod( "add", new Class<?>[] { Number.class } );
      arithmeticMethods.add( addNumberMethod );
      addNumberAtTimeMethod =
          cls.getMethod( "add", new Class<?>[] { Number.class,
                                                 Parameter.class } );
      arithmeticMethods.add( addNumberAtTimeMethod );
      addNumberForTimeRangeMethod =
          cls.getMethod( "add", new Class<?>[] { Number.class,
                                                 Parameter.class,
                                                 Parameter.class } );
      arithmeticMethods.add( addNumberForTimeRangeMethod );
      addMapMethod =
          cls.getMethod( "add", new Class<?>[] { TimeVaryingMap.class } );
      arithmeticMethods.add( addMapMethod );

      subtractNumberMethod =
          cls.getMethod( "subtract", new Class<?>[] { Number.class } );
      arithmeticMethods.add( subtractNumberMethod );
      subtractNumberAtTimeMethod =
          cls.getMethod( "subtract", new Class<?>[] { Number.class,
                                                 Parameter.class } );
      arithmeticMethods.add( subtractNumberAtTimeMethod );
      subtractNumberForTimeRangeMethod =
          cls.getMethod( "subtract", new Class<?>[] { Number.class,
                                                 Parameter.class,
                                                 Parameter.class } );
      arithmeticMethods.add( subtractNumberForTimeRangeMethod );
      subtractMapMethod =
          cls.getMethod( "subtract", new Class<?>[] { TimeVaryingMap.class } );
      arithmeticMethods.add( subtractMapMethod );

      multiplyNumberMethod =
          cls.getMethod( "multiply", new Class<?>[] { Number.class } );
      arithmeticMethods.add( multiplyNumberMethod );
      multiplyNumberAtTimeMethod =
          cls.getMethod( "multiply", new Class<?>[] { Number.class,
                                                 Parameter.class } );
      arithmeticMethods.add( multiplyNumberAtTimeMethod );
      multiplyNumberForTimeRangeMethod =
          cls.getMethod( "multiply", new Class<?>[] { Number.class,
                                                 Parameter.class,
                                                 Parameter.class } );
      arithmeticMethods.add( multiplyNumberForTimeRangeMethod );
      multiplyMapMethod =
          cls.getMethod( "multiply", new Class<?>[] { TimeVaryingMap.class } );
      arithmeticMethods.add( multiplyMapMethod );

      divideNumberMethod =
          cls.getMethod( "divide", new Class<?>[] { Number.class } );
      arithmeticMethods.add( divideNumberMethod );
      divideNumberAtTimeMethod =
          cls.getMethod( "divide", new Class<?>[] { Number.class,
                                                 Parameter.class } );
      arithmeticMethods.add( divideNumberAtTimeMethod );
      divideNumberForTimeRangeMethod =
          cls.getMethod( "divide", new Class<?>[] { Number.class,
                                                 Parameter.class,
                                                 Parameter.class } );
      arithmeticMethods.add( divideNumberForTimeRangeMethod );
      divideMapMethod =
          cls.getMethod( "divide", new Class<?>[] { TimeVaryingMap.class } );
      arithmeticMethods.add( divideMapMethod );

    } catch ( SecurityException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( NoSuchMethodException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  protected static Method getAddNumberAtTimeMethod() {
    if ( addNumberAtTimeMethod == null ) {
      setArithmeticMethods();
    }
    return addNumberAtTimeMethod;
  }
  protected static Method getAddNumberForTimeRangeMethod() {
    if ( addNumberForTimeRangeMethod == null ) {
      setArithmeticMethods();
    }
    return addNumberForTimeRangeMethod;
  }
  public static Method getAddNumberMethod() {
    if ( addNumberMethod == null ) {
      setArithmeticMethods();
    }
    return addNumberMethod;
  }
  public static Method getAddMapMethod() {
    if ( addMapMethod == null ) {
      setArithmeticMethods();
    }
    return addMapMethod;
  }

  public Interpolation getInterpolation() {
    return interpolation;
  }
  public static Method getSubtractNumberMethod() {
    if ( subtractNumberMethod == null ) {
      setArithmeticMethods();
    }
    return subtractNumberMethod;
  }
  public static Method getSubtractNumberAtTimeMethod() {
    if ( subtractNumberAtTimeMethod == null ) {
      setArithmeticMethods();
    }
    return subtractNumberAtTimeMethod;
  }
  public static Method getSubtractNumberForTimeRangeMethod() {
    if ( subtractNumberForTimeRangeMethod == null ) {
      setArithmeticMethods();
    }
    return subtractNumberForTimeRangeMethod;
  }
  public static Method getSubtractMapMethod() {
    if ( subtractMapMethod == null ) {
      setArithmeticMethods();
    }
    return subtractMapMethod;
  }
  public static Method getMultiplyNumberMethod() {
    if ( multiplyNumberMethod == null ) {
      setArithmeticMethods();
    }
    return multiplyNumberMethod;
  }
  public static Method getMultiplyNumberAtTimeMethod() {
    if ( multiplyNumberAtTimeMethod == null ) {
      setArithmeticMethods();
    }
    return multiplyNumberAtTimeMethod;
  }
  public static Method getMultiplyNumberForTimeRangeMethod() {
    if ( multiplyNumberForTimeRangeMethod == null ) {
      setArithmeticMethods();
    }
    return multiplyNumberForTimeRangeMethod;
  }
  public static Method getMultiplyMapMethod() {
    if ( multiplyMapMethod == null ) {
      setArithmeticMethods();
    }
    return multiplyMapMethod;
  }

  public static Method getDivideNumberMethod() {
    if ( divideNumberMethod == null ) {
      setArithmeticMethods();
    }
    return divideNumberMethod;
  }
  public static Method getDivideNumberAtTimeMethod() {
    if ( divideNumberAtTimeMethod == null ) {
      setArithmeticMethods();
    }
    return divideNumberAtTimeMethod;
  }
  public static Method getDivideNumberForTimeRangeMethod() {
    if ( divideNumberForTimeRangeMethod == null ) {
      setArithmeticMethods();
    }
    return divideNumberForTimeRangeMethod;
  }
  public static Method getDivideMapMethod() {
    if ( divideMapMethod == null ) {
      setArithmeticMethods();
    }
    return divideMapMethod;
  }


  public static Map< Method, Method > getInverseMethods() {
    if ( inverseMethods == null ) {
      initEffectMethods();
    }
    return inverseMethods;
  }
  public static Comparator< Method > getMethodComparator() {
    return methodComparator;
  }

  Parameter< Long> getTimeOfEffect( EffectFunction effectFunction ) {
    Integer i = getIndexOfTimepointArgument( effectFunction );
    if ( i == null ) {
      i = getIndexOfFirstTimepointParameter( effectFunction );
    }
    if ( i != null ) {
      return tryEvaluateTimepoint( effectFunction.arguments.get( i ), false );
    }
    return null;
  }

  public Integer getIndexOfTimepointArgument( EffectFunction effectFunction ) {
    Integer i = getEffectMethodsMap().get( effectFunction.getMethod() );
    if ( Debug.isOn() ) Debug.outln( "getIndexOfTimepointArgument("
                                     + effectFunction.getMethod().getName()
                                     + ") = " + i );
    return i;
  }

  public <TT> Pair< Parameter< Long>, TT > getTimeAndValueOfEffect( Effect effect ) {
    return getTimeAndValueOfEffect( effect, null );
  }

  /**
   * The time of the effect and the value applied are picked from the effect
   * functions arguments according to their types and whether the first argument
   * is suggested (by {@code timeFirst}) to be the timepoint. the arguments.
   *
   * @param effect
   *          the effect, from whose arguments the time and value are identified
   * @param timeFirst
   *          is a suggestion by the caller as to whether it believes that the
   *          first argument is the time argument as it is for setValue() and
   *          other effect methods. If it is null, then the timepoint is
   *          identified more carefully and the first argument is chosen in the
   *          case that there appear to be multiple viable candidates, from
   *          which no more evidence is found for one over others.
   * @return the timepoint at which the effect takes place and the value applied
   *         in a Pair
   */
  public < TT > Pair< Parameter< Long >, TT >
      getTimeAndValueOfEffect( Effect effect, Boolean timeFirst ) {
    // REVIEW -- Why not use <T>? Can't enforce it?
    if ( !( effect instanceof EffectFunction ) ) {
      return null;
    }
    EffectFunction effectFunction = (EffectFunction)effect;
    if ( effectFunction == null || effectFunction.getMethod() == null ) {
      if ( Debug.isOn() ) {
        Debug.errln( getName() + ".getTimeAndValueOfEffect(Effect=" + effect
                     + ") called with no effect method! "
                     + MoreToString.Helper.toLongString( this ) );
      }
      return null;
    }
    if ( effectFunction.getMethod().getParameterTypes().length < 2 ) {
      Debug.error( getName() + ".getTimeAndValueOfEffect(Effect="
                   + MoreToString.Helper.toLongString( effect )
                   + ") Error! Method takes "
                   + effectFunction.getMethod().getParameterTypes().length
                   + " parameters, but 2 are required." );
      return null;
    }

    if ( effectFunction.arguments == null || effectFunction.arguments.size() < 2 ) {
      Debug.error( getName() + ".getTimeAndValueOfEffect(Effect="
                   + MoreToString.Helper.toLongString( effect )
                   + ") Error! Method has "
                   + effectFunction.getMethod().getParameterTypes().length
                   + " arguments, but 2 are required." );
      return null;
    }
    boolean complainIfNotTimepoint = timeFirst != null;

    timeFirst = ( timeFirst == null ) || timeFirst;
    Parameter< Long > tp = null;
    TT value = null;

    Integer idx = getIndexOfTimepointArgument( effectFunction );
    if ( idx != null ) {
      // Object arg = effectFunction.arguments.get( idx );
      // if ( isTimepoint( arg ) ) {
      // tp = tryEvaluateTimepoint(arg, true);
      timeFirst = ( idx == 0 );
      // }
    }

    Object arg1 = effectFunction.arguments.get( timeFirst ? 0 : 1 );
    Object arg2 = effectFunction.arguments.get( timeFirst ? 1 : 0 );
    tp = tryEvaluateTimepoint( arg1, true );

    value = (TT)tryEvaluateValue( arg2, true );

    if ( isTimepoint(tp) ) {
      return new Pair< Parameter< Long >, TT >( tp, value );
    }

    Pair< Object, Parameter< Long >> p =
        whichIsTheTimepoint( arg1, arg2, timeFirst && complainIfNotTimepoint,
                             !timeFirst && complainIfNotTimepoint, timeFirst );
    if ( p == null ) {
      return null;
    }
    tp = p.second;
    Object tpArg = ( p.first == arg1 ) ? arg1 : arg2;
    Object valueArg = ( p.first == arg1 ) ? arg2 : arg1;
    value = (TT)tryEvaluateValue( valueArg, true );
    Pair< Parameter< Long >, TT > pair =
        new Pair< Parameter< Long >, TT >( tp, value );
    if ( value == null ) {
      if ( valueArg != null ) {
        Parameter< Long > otp = tryEvaluateTimepoint( valueArg, true, true );
        if ( otp != null ) {
          TT ov = (TT)tryEvaluateValue( tpArg, true );
          if ( Debug.isOn() ) {
            Debug.err( "Looks like timepoint and value are reversed in call to getTimeAndValueOfEffect( Effect "
                       + MoreToString.Helper.toLongString( effect ) + ", "
                       + "Boolean " + MoreToString.Helper.toLongString( timeFirst )
                       + ") = " + MoreToString.Helper.toLongString( pair ) );
          }
        }
      }
    }
    return pair;
  }


  public static Integer getFirstParameterOfType( Method method, Object clsOrObj ) {
    if ( clsOrObj instanceof Class ) {
      Class<?> cls = (Class<?>)clsOrObj;
      Integer pos = getIndexOfFirstParameterOfType( method, cls );
      if ( pos != null && pos >= 0 ) {
        return pos;
      }
    }
    return null;  // TODO -- HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  }

  public static Integer getIndexOfFirstParameterOfType( Method method, Class<?> cls ) {
    if ( method == null ) return null;
    Class< ? >[] pTypes = method.getParameterTypes();
    if ( pTypes == null || pTypes.length <= 0 ) {
      return null;
    }
    Integer best = null;
    //boolean assigned = false;
    for ( Integer i = 0; i < pTypes.length; ++i ) {
      Class<?> pType = pTypes[i];
      if ( pType == null ) continue;
      if ( !cls.isAssignableFrom( pType ) ) continue;
      if ( best == null ) {
        best = i;
      }
//      TypeVariable< ? >[] gTypes = pType.getTypeParameters();
//      if ( gTypes == null ) continue;
//      for ( TypeVariable< ? > typeVar : gTypes ) {
//        if ( typeVar == null ) continue;
//      }
    }
    return best;
  }

  public static Integer getIndexOfFirstTimepointParameter( Method method ) {
    Parameter< Long> p = new Parameter< Long >("objForCls", null, null );
    Class< Parameter< Long> > cls = (Class< Parameter< Long >>)p.getClass();
    return getIndexOfFirstParameterOfType( method, cls );
//    if ( method == null ) return null;
//    Class< ? >[] pTypes = method.getParameterTypes();
//    if ( pTypes == null || pTypes.length <= 0 ) {
//      return null;
//    }
//    Integer best = null;
//    for ( Integer i = 0; i < pTypes.length; ++i ) {
//      Class<?> pType = pTypes[i];
//      if ( pType == null ) continue;
//      if ( !Parameter.class.isAssignableFrom( pType ) ) continue;
//      if ( best == null ) best = i;
//      TypeVariable< ? >[] gTypes = pType.getTypeParameters();
//      if ( gTypes == null ) continue;
//      for ( TypeVariable< ? > typeVar : gTypes ) {
//        if ( typeVar == null ) continue;
//      }
//    }
//    return null;
  }

  public static Integer getIndexOfFirstTimepointParameter( EffectFunction effectFunction ) {
    return getIndexOfFirstTimepointParameter( effectFunction.getMethod() );
  }

  public static boolean testGetFirstTimepointParameter() {
    boolean debugWasOn = Debug.isOn();
    if ( !debugWasOn ) Debug.turnOn();

    boolean succ = true;
    TimeVaryingMap tvm = new TimeVaryingMap< Long >("imap", null, 0L, Long.class);
    EffectFunction f =
        new EffectFunction( tvm,
                            TimeVaryingMap.getSetValueMethod(),
                            new Object[] { new Parameter< Long >( "four",
                                                                     null, tvm ),
                                           new Long( 14 ) } );
    assert ( 0 == tvm.getIndexOfFirstTimepointParameter( f ) );
    Method.setAccessible( TimeVaryingMap.class.getDeclaredMethods(), true );
    for ( Method method : TimeVaryingMap.class.getDeclaredMethods() ) {
      f = new EffectFunction( tvm, method );
      if ( Debug.isOn() ) Debug.outln( "method " + method
                   + " has first timepoint parameter at position "
                   + tvm.getIndexOfFirstTimepointParameter( f ) );
      Class<?> clss = ParameterListenerImpl.class;
      Class< ? >[] pTypes = method.getParameterTypes();
      if ( pTypes == null || pTypes.length <= 0 ) {
        continue;
      }
      for ( Integer i = 0; i < pTypes.length; ++i ) {
        Class<?> pType = pTypes[i];
        if ( pType == null ) continue;
        //if ( !clss.isAssignableFrom( pType ) ) continue;
        TypeVariable< ? >[] gTypes = pType.getTypeParameters();
        if ( gTypes == null ) continue;
        for ( TypeVariable< ? > typeVar : gTypes ) {
          if ( typeVar == null ) continue;
          if ( Debug.isOn() ) Debug.outln( "method=" + method.getName() + ", parameter type="
                       + pType + ", type variable=" + typeVar
                       + ", typeVar.getName()=" + typeVar.getName()
          + ", typeVar.getBounds()=" + typeVar.getBounds()
          + ", typeVar.getGenericDeclaration()=" + typeVar.getGenericDeclaration()
          );
        }
      }
    }


    if ( !debugWasOn ) Debug.turnOff();
    return succ;
  }


  /**
   * Struct used to help compare objects to tell which is a timepoint key and
   * which is a V value for the enclosing map.  The object may be a timepoint
   * and will be checked.  Boolean attributes may be null to indicate that they
   * have not been evaluated.
   */
  class CandidateTimepoint {
    /**
     * object to evaluate
     */
    protected Object o = null;
    /**
     * conversion of object to timepoint
     */
    protected Parameter< Long> tp = null;
    /**
     * whether tp is actually a timepoint
     */
    protected Boolean isATimepoint = null;
    /**
     *  whether the value of the timepoint is valid
     */
    protected Boolean inDomain = null;
    /**
     * whether or not the object needed to be converted to get the timepoint
     */
    protected Boolean neededConversion = null;
    /**
     * whether or not the object needed to be wrapped with a Parameter to be
     * converted to a timepoint
     */
    protected Boolean neededWrapping = null;

    /**
     * The default constructor
     */
    public CandidateTimepoint() {}

    /**
     * Constructor with all fields.
     *
     * @param o
     *          object to evaluate
     * @param tp
     *          conversion of object to timepoint
     * @param isATimepoint
     *          whether tp is actually a timepoint
     * @param inDomain
     *          whether the value of the timepoint is valid
     */
    public CandidateTimepoint( Object o, Parameter< Long > tp ) {
      super();
      if ( tp == null ) {
        setObject(o);
      } else {
        this.o = o;
        setTp(tp);
      }
    }

    /**
     * @return the candidate object
     */
    public Object getObject() {
      if ( o == null && tp != null ) {
        return tp;
      }
      return o;
    }

    /**
     * @return the converted timepoint
     */
    public Parameter< Long > getTimepoint() {
      if ( tp == null && o != null ) {
        setTimepointToObject();
      }
      return tp;
    }

    public void setObject( Object o ) {
      this.o = o;
//      Assert.assertNull(tp);
//      setTimepointToObject();
      isATimepoint = null;//isTimepoint( o );
      inDomain = null;
    }
    public void setTp( Parameter< Long > tp) {
      this.tp = tp;
      isATimepoint = null;//isTimepoint( tp );
      inDomain = null;
//      if ( o == null ) o = tp;
    }
    public boolean knowIfIsATimepoint() {
      return ( isATimepoint != null );
    }
    public boolean knowIfInDomain() {
      return ( inDomain != null );
    }
    protected boolean setTimepointToObject() {
      if ( tp != null ) {
        if ( Debug.isOn() ) Debug.err( "Warning! Trying to replace existing tp with o." );
      }
      tp = tryCastTimepoint( o );
      if ( tp == o ) {
        setNeededConversion( false );
        setNeededWrapping( false );
      }
      return tp == o;
    }
    public boolean isInDomain() {
      if ( inDomain == null ) {
        if ( tp == null && o != null ) {
          setTimepointToObject();
        }
        inDomain = Timepoint.defaultDomain.contains( tp.getValue(false) );
      }
      return inDomain;
    }
    public boolean isATimepoint() {
      if ( isATimepoint == null ) {
        if ( tp == null ) {
          isATimepoint = setTimepointToObject();
        } else {
          isATimepoint = isTimepoint( tp );
        }
      }
      return isATimepoint;
    }

    public boolean knowIfNeededConversion() {
      return ( neededConversion != null );
    }
    public boolean knowIfNeededWrapping() {
      return ( neededWrapping != null );
    }
    /**
     * @return neededConversion
     */
    public Boolean neededConversion() {
      return neededConversion;
    }

    /**
     * @param neededConversion the neededConversion to set
     */
    public void setNeededConversion( Boolean neededConversion ) {
      this.neededConversion = neededConversion;
    }

    /**
     * @return neededWrapping
     */
    public Boolean neededWrapping() {
      return neededWrapping;
    }

    /**
     * @param neededWrapping the neededWrapping to set
     */
    public void setNeededWrapping( Boolean neededWrapping ) {
      this.neededWrapping = neededWrapping;
    }

//    public boolean isWrapped() {
//      if ( tp == null || o == null ) return false;
//      return false;
//    }

    /**
     * Just try to convert using wrapping.
     * @param propagate
     * @return
     */
    public boolean tryWrapping( boolean propagate ) {
      setTp( tryEvaluateTimepoint( o, propagate, true ) );
      return isATimepoint();
    }

    public boolean doConversion( boolean propagate, boolean okToWrap ) {
      setTimepointToObject();
      if ( isATimepoint() ) {
        setNeededConversion( false );
        setNeededWrapping( false );
        return true;
      }
      setTp( tryEvaluateTimepoint( o, propagate, false ) );
      if ( isATimepoint() ) {
        setNeededConversion( true );
        setNeededWrapping( false );
        return true;
      }
      if ( okToWrap ) {
        if ( tryWrapping( propagate ) ) {
          setNeededWrapping( true );
        }
      }
      return isATimepoint();
    }

  }

  /**
   * @param o1
   *          candidate timepoint
   * @param o2
   *          candidate timepoint
   * @param o1ShouldBe
   * @param o2ShouldBe
   * @param o1WinsTie
   * @return the object, call it A, paired with its successful conversion to a
   *         timepoint, iff A meets higher priority criteria than the other object, B, for the following
   *         (with 1=highest priority, 4=lowest): (1)
   *         the other, B, cannot be converted successfully, (2) A is expected
   *         to be a timepoint ([A]ShouldBe=True and [B]ShouldBe=False), (3) The timepoint value of one is not in the default timepoint domain. (4) A
   *         is more easily transformed into a Timepoint than B (casting is
   *         easier than evaluating, which is easier than wrapping with a
   *         Parameter ), or (5) A wins in the tie (o1WinsTie == (A==o1));
   *         otherwise, neither can be converted, and null is returned.
   */
  public Pair< Object, Parameter< Long > > whichIsTheTimepoint( Object o1,
                                                                   Object o2,
                                                                   boolean o1ShouldBe,
                                                                   boolean o2ShouldBe,
                                                                   boolean o1WinsTie ) {
    // check for null values
    if ( o1 == null && o2 == null ) return null;
    if ( o1 != null && o2 == null ) {
      return new Pair< Object, Parameter< Long >>(o1, tryEvaluateTimepoint( o1, true ));
    }
    if ( o1 == null && o2 != null ) {
      return new Pair< Object, Parameter< Long >>(o2, tryEvaluateTimepoint( o2, true ));
    }
    // make some convenient inferences
    boolean strongPreference = o1WinsTie ? o1ShouldBe : o2ShouldBe;
    boolean strongPreferenceFor1 = strongPreference && o1ShouldBe;
    boolean strongPreferenceFor2 = strongPreference && o2ShouldBe;
    boolean o1IsPreferred = strongPreferenceFor1 || (!strongPreferenceFor2 && o1WinsTie );
    boolean o2IsPreferred = !o1IsPreferred;
    boolean inconsistent = o1WinsTie ? o2IsPreferred : o1IsPreferred;
    if ( inconsistent ) {
      Debug.error( true, "Error! Inconsistent arguments. The expected object is not also the tie breaker." );
    }

    // make oi the preferred choice and oii the other
    Object preferredObj = o1IsPreferred ? o1 : o2;
    Object nonPreferredObj = o1IsPreferred ? o2 : o1;

    CandidateTimepoint preferred = new CandidateTimepoint( preferredObj, null );
    CandidateTimepoint nonPreferred = new CandidateTimepoint( nonPreferredObj, null );

    // Check if one is already a timepoint, and
    // see if one is not in the default time domain and the other is.
    Pair< Object, Parameter< Long > > p =
        selectTimepoint(preferred, nonPreferred, strongPreference, true, true );
    if ( p != null ) return p;

    // Try to get a timepoint from evaluation of the object and check domains.
    preferred.doConversion( true, false );
    nonPreferred.doConversion( true, false );
    p = selectTimepoint( preferred, nonPreferred, strongPreference, true, true );
    if ( p != null ) return p;

    // Try to get a timepoint from evaluation of the object while allowing
    // wrapping and check domains.
    preferred.tryWrapping( true );
    nonPreferred.tryWrapping( true );
    p = selectTimepoint( preferred, nonPreferred, strongPreference, true, true );
    if ( p != null ) return p;

    // If couldn't make either on a timepoint, fail--no more magic tricks.
    if ( !preferred.isATimepoint() && !nonPreferred.isATimepoint() ) return null;

    // See if one works as a value and not the other
    if ( strongPreference ) {
      V valPref = tryEvaluateValue( nonPreferredObj, true );
      if ( valPref != null ) {
        return new Pair< Object, Parameter< Long > >( preferred.getObject(),
                                                         preferred.getTimepoint() );
      }
    } else {
      V valNonPref = tryEvaluateValue( preferredObj, true );
      if ( valNonPref != null ) {
        return new Pair< Object, Parameter< Long > >( nonPreferred.getObject(),
                                                         nonPreferred.getTimepoint() );
      }
    }

    if ( preferred.isATimepoint() ) {
      return new Pair< Object, Parameter< Long > >( preferred.getObject(),
                                                       preferred.getTimepoint() );
    }
    return null;
  }

  /**
   * Determine which of the two candidates better resembles a timepoint
   * according to preferences.
   *
   * @param pref
   *          the preferred candidate
   * @param nonPref
   *          the non-preferred candidate
   * @param strongPreference
   *          whether or not the preferred candidate wins in a tie
   * @return the preferred object paired with its conversion to a timepoint, the
   *         non-preferred object and timepoint if the preferred isn't
   *         available, or null
   */
  protected Pair< Object, Parameter< Long > >
      selectTimepoint( CandidateTimepoint pref, CandidateTimepoint nonPref,
                       boolean strongPreference ) {
    return selectTimepoint( pref, nonPref, strongPreference, true, false );
  }

  /**
   * Determine which of the two candidates better resembles a timepoint
   * according to preferences.
   *
   * @param pref
   *          the preferred candidate
   * @param nonPref
   *          the non-preferred candidate
   * @param strongPreference
   *          whether or not the preferred candidate wins in a tie
   * @param checkIfTimepoint
   * @param checkIfInDomain
   * @return the preferred object paired with its conversion to a timepoint, the
   *         non-preferred object and timepoint if the preferred isn't
   *         available, or null
   */
  protected Pair< Object, Parameter< Long > >
      selectTimepoint( CandidateTimepoint pref, CandidateTimepoint nonPref,
                       boolean strongPreference, boolean checkIfTimepoint,
                       boolean checkIfInDomain ) {
    if ( pref == null || nonPref == null ) return null;
    // check whether the candidates are found to be timepoints (Parameter< Long>)
    if ( checkIfTimepoint ) {
      if ( pref.isATimepoint()
           && ( strongPreference || !nonPref.isATimepoint() ) ) {
        return new Pair< Object, Parameter< Long >>( pref.getObject(),
                                                        pref.getTimepoint() );
      }
      if ( !strongPreference && nonPref.isATimepoint() ) { // !isPrefTp
        return new Pair< Object, Parameter< Long >>( nonPref.getObject(),
                                                        nonPref.getTimepoint() );
      }
    }
    boolean bothAreTimepoints = pref.isATimepoint() && nonPref.isATimepoint();
    if ( bothAreTimepoints  ) {
      // check if an actual Timepoint (as opposed to just Parameter< Long>)
      if ( strongPreference && pref.getTimepoint() instanceof Timepoint
           && !( nonPref.getTimepoint() instanceof Timepoint ) ) {
        return new Pair< Object, Parameter< Long >>( pref.getObject(),
                                                        pref.getTimepoint() );
      }
      // check if value is a legal time value
      if ( checkIfInDomain ) {
        if ( pref.isInDomain() && ( strongPreference || !nonPref.isInDomain() ) ) {
          return new Pair< Object, Parameter< Long >>( pref.getObject(),
                                                          pref.getTimepoint() );
        }
        if ( !strongPreference && nonPref.isInDomain() ) { // !isPrefTp is known
          return new Pair< Object, Parameter< Long >>(
                                                          nonPref.getObject(),
                                                          nonPref.getTimepoint() );
        }
      }
    }
    return null;

  }


  public boolean isTimepoint( Object tp ) {
    boolean isParam = tp instanceof Parameter;
    if ( !isParam ) return false;
    Parameter<?> p = (Parameter<?>)tp;
    boolean isIntParam = p.getValueNoPropagate() instanceof Long;
    return isIntParam;
  }

  @Override
  public boolean isApplied( Effect effect ) {
    return isApplied(effect, getSetValueMethod(), getSetValueMethod() );//, true );
  }

  public static boolean isArithmeticEffect( Effect effect ) {
    if ( effect instanceof EffectFunction ) {
      EffectFunction f = (EffectFunction)effect;
      if (getArithmeticMethods().contains(f.getMethod())) return true;
    }
    return false;
  }

  public static Collection<Method> getArithmeticMethods() {
    if ( arithmeticMethods == null ) {
      setArithmeticMethods();
    }
    return arithmeticMethods;
  }

  public boolean isApplied( Effect effect, Method method1, Method method2 ) {
    breakpoint();
    if ( Debug.isOn() || checkConsistency ) isConsistent();
    if ( isArithmeticEffect( effect ) ) {
      return appliedSet.contains(effect); // HACK! We have a problem here! We can't know if it's
                   // applied unless we keep track of all effects here!
    }

    Pair< Parameter< Long>, V > p = getTimeAndValueOfEffect( effect );//, method1, method2 ); //, timeArgFirst );
    if ( p == null ) return false;
    Object t = p.first;
    V value = p.second;
//    if ( value != null ) {
    // FIXME!!! t is converted to a Parameter even if the time of the effect is
    // an integer.
      if ( t instanceof Parameter
           && ( (Parameter<?>)t ).getValueNoPropagate() instanceof Long ) {
        return hasValueAt( value, tryCastTimepoint( t ), true );  
      } if ( t instanceof Long ) {
        return hasValueAt( value, (Long)t );
      }
//    }
    return false;
  }

  /**
   * Parses a value from a string of the same type as the map value, V.
   * @param s
   * @param form
   * @return
   */
  public V valueFromString( String s ) {
    V value = null;
    if ( type == Double.class || type == double.class ) {
      value = type.cast( Double.parseDouble( s ) );
    } else if ( type == Float.class || type == float.class ) {
      value = type.cast( Float.parseFloat( s ) );
    } else if ( type == Long.class || type == long.class ) {
      value = type.cast( Long.parseLong( s ) );
    } else if ( type == Integer.class || type == int.class ) {
      value = type.cast( Integer.parseInt( s ) );
    } else if ( type == Boolean.class || type == boolean.class ) {
      value = type.cast( Boolean.parseBoolean( s ) );
    } else if ( type != null ) {
      value = type.cast( s );
    } else {
      List<Object> objs = new ArrayList< Object >();
      objs.add( s );
      try {
        objs.add( Double.parseDouble( s ) );
      } catch (NumberFormatException e) {
      }
      try {
        objs.add( Long.parseLong( s ) );
      } catch (NumberFormatException e) {
      }
      objs.add( Boolean.parseBoolean( s ) );
      for ( Object o : objs ) {
        try {
          value = tryCastValue( o );
          if ( value != null ) break;
        } catch ( ClassCastException e ) {
        }
      }
    }
    if ( value == null && s != null ) {
      try {
        value = tryCastValue( s );
      } catch ( ClassCastException e ) {
        e.printStackTrace();
      }
    }
    return value;
  }

  public void fromStringMapWithJulianDates( Map<String,String> map, Class<V> cls ) {
    clear();
    for ( Entry<String, String> ss : map.entrySet() ) {
      Long key = null;
      try {
          Double dKey = Double.parseDouble( ss.getKey() );
          key = Timepoint.julianToInteger( dKey );
      } catch (NumberFormatException e) {
      }
      if ( key != null && key >= 0 ) {
        Timepoint tp = new Timepoint( null, key, this );
        V value = valueFromString( ss.getValue() );
        setValue( tp, value );
      }
    }    
  }
  
  
  public void fromStringMap( Map<String,String> map, Class<V> cls ) {
    clear();
    SimpleTimepoint pre_tp = null;
    V pre_value = null;
    TimeZone gmtZone = TimeZone.getTimeZone( "GMT" );

    int ct = 0;
    for ( Entry<String, String> ss : map.entrySet() ) {
      Long key = null;
      Date d = TimeUtils.dateFromTimestamp( ss.getKey(), gmtZone );
      if ( d != null ) key = Timepoint.fromDateToInteger( d );
      if ( key == null ) {
        try {
          key = Long.parseLong( ss.getKey() );
        } catch (NumberFormatException e) {
          try {
            Double dKey = Double.parseDouble( ss.getKey() );
            key = dKey.longValue();
          } catch (NumberFormatException ee) {
          }
        }
      }
      if ( key != null ) {
        
        SimpleTimepoint tp = new SimpleTimepoint( null, key, this );
        // add time-value pair if time is within the horizon.
        Long t = tp.getValueNoPropagate();
        if ( t != null ) 
        {
           if(t < 0 ) {
             if ( pre_tp == null || tp.compareTo( pre_tp ) > 0 )
             {
               pre_tp = tp;
               pre_value = valueFromString( ss.getValue() );
             }
           }
           else if ( t < Timepoint.getHorizonDuration() ) {
               V value = valueFromString( ss.getValue() );
               setValue( tp, value );
           } else {
             //Debug.breakpoint();
           }
        }
      }
      ++ct;
    }
    if( pre_tp != null )
    {
      if( getValue(0L) == null )
      {
        SimpleTimepoint zero_tp = new SimpleTimepoint( null, 0L, this ); 
        setValue( zero_tp, pre_value );
      }
    }
  }

  public void fromString( String s, Class<V> cls ) {
    Map<String,String> map = new TreeMap<String,String>();
    Pattern p = Pattern.compile( "([^{@]*)?([^{@]*)(@[^{]*)?" );
    Matcher matcher = p.matcher( s );
    int end = 0;
    if ( matcher.find() ) {
      end = matcher.end();
      if ( matcher.groupCount() >= 1 ) {
        if ( Utils.isNullOrEmpty( matcher.group( 1 ) ) ) {
          interpolation.fromString( matcher.group( 1 ) );
        }
        name = matcher.group( 2 );
      }
      if ( matcher.groupCount() == 2 ) {
        name = matcher.group( 1 );
      }
    }
    MoreToString.Helper.fromString( map, s.substring( end ) );
    fromStringMap( map, cls );
  }

  public void fromCsvString( String s, Class<V> cls ) {
    Map<String,String> map = new TreeMap<String,String>();
    MoreToString.Helper.fromString( map, s, "", "\\s*", "", "", "\\s*,\\s*", "" );
    fromStringMap( map, cls );
  }

  public void fromCsvFile( String fileName ) {
    fromCsvFile( fileName, null );
  }
  public void fromCsvFile( String fileName, Class<V> cls ) {
    if ( fileName == null ) return;
    try {
      File f = FileUtils.findFile( fileName );
      if (f != null ) System.out.println(f.toString());
      ArrayList< ArrayList< String > > lines = FileUtils.fromCsvFile( f );
      //String s = FileUtils.fileToString( f );
      Map<String,String> map = new TreeMap<String,String>();
      //MoreToString.Helper.fromString( map, s, "", "\\s+", "", "", "[ ]*,[ ]*", "" );
      for ( ArrayList<String> line : lines ) {
        if ( line.size() >= 2 ) {
          map.put( line.get(0), line.get(1) );
        }
      }
      fromStringMap( map, cls );
      if ( Debug.isOn() ) Debug.outln( "read map from file, " + fileName + ":\n" + this.toString() );
    } catch ( IOException e ) {
      e.printStackTrace();
    }
  }
  public void toCsvFile( String fileName, String header, String format, Calendar cal ) {
    String s = toCsvString(header, format, cal);
    if ( Debug.isOn() ) Debug.outln( "wrote map to file, " + fileName + ":\n" + s );
    FileUtils.stringToFile( s, fileName );
  }

  public void toCsvFile( String fileName ) {
    toCsvFile(fileName, null, null, null);
  }

  public String toCsvString() {
    return toCsvString( null, null, null );
  }

  public String toCsvString(String header, String dateFormat, Calendar cal ) {
    StringBuffer sb = new StringBuffer();
    if ( !Utils.isNullOrEmpty( header ) ) {
      sb.append(header + "\n");
    }
    for ( java.util.Map.Entry< Parameter< Long >, V > e : entrySet() ) {
      String timeString = null;
      if ( dateFormat != null  ) {
        if ( cal == null ) cal = TimeUtils.gmtCal;
        if ( e.getKey() == null || e.getKey().getValueNoPropagate() == null ) {
          continue;
        }
        long t = e.getKey().getValueNoPropagate();
        timeString = Timepoint.toTimestamp( t, dateFormat, cal );
      } else {
        timeString = e.getKey().toShortString();
      }
      sb.append( timeString + ","
                 + MoreToString.Helper.toShortString( e.getValue() ) + "\n" );
    }
    return sb.toString();
  }

  @Override
  public String toShortString() {
    return getName();
  }

  @Override
  public String toString() {
    return toString( Debug.isOn(), false, null );
  }

  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen ) {
    return Parameter.toString( this, true, withHash, deep, seen );
  }

  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen,
                          Map< String, Object > otherOptions ) {
    StringBuffer sb = new StringBuffer();
    sb.append( interpolation + " " );
    sb.append( this.getName() );
    if ( withHash ) sb.append( "@" + hashCode() );
    sb.append( MoreToString.Helper.toString( this, withHash, deep, seen,
                                             otherOptions, CURLY_BRACES, false ) );
    return sb.toString();
  }

  @Override
  public < T > boolean pickParameterValue( Variable< T > variable ) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Integer getId() {
    return id;
  }
  @Override
  public int hashCode() {
    return id;
  }

  /**
   * @return the type
   */
  @Override
  public Class<V> getType() {
    if ( type == null ) {
      for ( V t : values() ) {
        if ( t != null ) {
          setType( t.getClass() );
          if ( type != null ) break;
        }
      }
    }
    return type;
  }

  /**
   * @param type the type to set
   */
  @SuppressWarnings( "unchecked" )
  public void setType( Class< ? > type ) {
    Class<V> oldType = this.type;
    while ( type != null && this.type == oldType ) {
      try {
        this.type = (Class< V >)type;
        break;
      } catch ( ClassCastException e ) {
        // ignore
      }
      type = type.getSuperclass();
    }
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Wraps#getTypeNameForClassName(java.lang.String)
   */
  @Override
  public String getTypeNameForClassName( String className ) {
    // There should only be one generic parameter of type V
    return ClassUtils.parameterPartOfName( className, false );
  }

  /*
   * (non-Javadoc)
   *
   * @see gov.nasa.jpl.ae.solver.Wraps#getPrimitiveType()
   */
  @Override
  public Class< ? > getPrimitiveType() {
    Class< ? > c = null;
    Class< ? > t = getType();
    if ( t != null ) {
      c = ClassUtils.primitiveForClass( t );
      //V v = getFirstValue();
      if ( c == null ) {
        for ( V v : values() ) {
          if ( c == null && v != null
              && Wraps.class.isInstance( v ) ) {// isAssignableFrom( getType() ) ) {
            c = ( (Wraps< ? >)v ).getPrimitiveType();
          }
          if ( c != null ) break;
        }
      }
    }
    return c;
  }

  public V firstValue() {
    V value = this.firstEntry().getValue();
    return value;
  }

  @Override
  public V getValue( boolean propagate ) {
    if ( isEmpty() ) return null;
    V value = firstValue();
    if ( size() > 1 ) {
      Debug.error(false, "Warning! Getting value " + value  + " for multi-valued TimeVaryingMap: " + this );
    }
    return value;
  }

  @Override
  public void setValue( V value ) {
    if ( size() == 0 ) {
      Parameter< Long> t = new Parameter< Long>(null, null, 0L, this);
      put( t, value );
    } else if ( size() > 0 ) {
      if ( size() > 1 ) {
        Debug.error(false, "Warning! Setting all values  to " + value + " in " + this );
      }
      for ( java.util.Map.Entry< Parameter< Long >, V > e : entrySet() ) {
        e.setValue( value );
      }
    }
  }

  /*  Getting the average is painful.  Should probably integrate.  TODO
   * 
  public Number zero() {
    if ( getType() == null ) return null;
    if (Number.class.isAssignableFrom( getType() )) {
      return (Number)tryCast(0, getType());
    }
    return null;
  }
  public Number sum() {
    Number sum = zero();
    if ( sum == null ) return null;
    Functions.plus( o1, o2 );
     gov.nasa.jpl.ae.util.Math.plus()
  }

  public Number avg() {
    Number sum = zero();
    if ( sum == null ) return null;
    Functions.plus( o1, o2 );
     gov.nasa.jpl.ae.util.Math.plus()
  }
  */
  public Number min() {
    return getMinValue();
  }
  public Number max() {
    return getMaxValue();
  }
  public Number getMinValue() {
    return getMinOrMaxValue( true );
  }
  public Number getMaxValue() {
    return getMinOrMaxValue( false );
  }
  public Number getMinOrMaxValue( boolean isMin ) {
    if ( !ClassUtils.isNumber( getType() ) ) return null;
    boolean isInt = ClassUtils.isLong( getType() );
    Long minInt = Long.MAX_VALUE;
    Double minDouble = Double.MAX_VALUE;
    long mul = isMin ? 1 : -1;
    for ( V v : values() ) {
      if ( v != null &&  v instanceof Number ) {
        if ( isInt ) {
          Long i = ((Number)v).longValue();
          if ( mul * i.longValue() < mul * minInt.longValue() ) {
            minInt = i;
          }
        } else {
          Double d = ((Number)v).doubleValue();
          if ( mul * d.doubleValue() < mul * minDouble.doubleValue() ) {
            minDouble = d;
          }
        }
      }
    }
    return isInt ? minInt : minDouble;
  }

  @Override
  public String toAspenMdl( String tlName ) {
    if ( Utils.isNullOrEmpty( tlName ) ) {
      tlName = getName();
    }
    StringBuffer sb = new StringBuffer();
    if ( ClassUtils.isNumber( getType() ) ) {
      Number minVal = getMinValue();
      Number maxVal = getMaxValue();
      // If all values are positive, set minVal to 0 unless it would make it
      // hard to see the difference.
      if ( minVal.doubleValue() > 0.0 && maxVal.doubleValue() / minVal.doubleValue() > 1.2 ) {
        minVal = 0;
      }
      sb.append( "resource " + tlName + " {\n" );
      sb.append( "  type = depletable;\n" );
      sb.append( "  min_value = " + minVal.doubleValue() + ";\n" );
      sb.append( "  capacity = " + maxVal.doubleValue() + ";\n" );
      sb.append( "  default = " + minVal.doubleValue() + ";\n" );
      sb.append( "}\n" );

      sb.append( "activity " + tlName + "Changer {\n" );
      sb.append( "  duration = 1;\n" );
      //sb.append( "  " + (isInt?"int":"real") + " value;\n");
      sb.append( "  real value;\n");
      sb.append( "  reservations = " + tlName + " use value;\n");
      sb.append( "}\n" );
    } else {
      sb.append( "state_variable " + tlName + " {\n  default = \"\";\n}\n" );
      sb.append( "activity " + tlName + "Changer {\n" );
      sb.append( "  duration = 1;\n" );
      sb.append( "  string value;\n" );
      sb.append( "  reservations = " + tlName + " change_to value at_start;\n");
      sb.append("}\n" );
    }
    return sb.toString();
  }

  @Override
  public String toAspenIni( String tlName ) {
    StringBuffer sb = new StringBuffer();
    boolean isNum = ClassUtils.isNumber( getType() );
    int ctr = 0;
    for ( Map.Entry< Parameter< Long >, V > e : entrySet() ) {
      if ( e.getKey() == null || e.getKey().getValue(false) == null ) continue;
      long startTime = e.getKey().getValue(false).longValue();
      String q = isNum ? "" : "\"";
      Object value = null;
      try {
        value = Expression.evaluate( e.getValue(), null, false );
      } catch ( ClassCastException e1 ) {
        // TODO Auto-generated catch block
        //e1.printStackTrace();
      } catch ( IllegalAccessException e1 ) {
        // TODO Auto-generated catch block
        //e1.printStackTrace();
      } catch ( InvocationTargetException e1 ) {
        // TODO Auto-generated catch block
        //e1.printStackTrace();
      } catch ( InstantiationException e1 ) {
        // TODO Auto-generated catch block
        //e1.printStackTrace();
      }
      if ( isNum && value == null ) {
        value = new Double(getMinValue().doubleValue());
      }
      if ( isNum && value != null ) {
        Number d = (Number)value;
        value = d.doubleValue();
      }
      value = q + value + q;
      sb.append( tlName + "Changer " + tlName + "Changer_"  + (ctr++) + " {\n" );
      sb.append( "  start_time = " + Math.max( 0, Math.min( 1073741823, startTime)) + ";\n" );
      sb.append( "  value = " + value + ";\n" );
      sb.append("}\n" );
    }
    sb.append("\n");
    return sb.toString();
  }

  public static Map< Method, Integer > effectMethodsMap() {
    if ( effectMethods == null ) effectMethods = initEffectMethods();
    return effectMethods;
  }
  public Map< Method, Integer > getEffectMethodsMap() {
    if ( effectMethods == null ) effectMethods = initEffectMethods();
    return effectMethods;
  }

  @Override
  public Collection< Method > getEffectMethods() {
    return effectMethods();
  }

  public static Collection< Method > effectMethods() {
    Map< Method, Integer > m = effectMethodsMap();
    if ( m == null ) return null;
    return m.keySet();
  }

  protected static LinkedHashSet< String > effectNames = null; 
  public static Set< String > effectMethodNames() {
    if ( effectNames == null ) {
      effectNames = new LinkedHashSet< String >();
      for ( Method m : effectMethods() ) {
        effectNames.add( m.getName() );
      }
    }
    return effectNames;
  }

  @Override
  public boolean doesAffect( Method method ) {
    return affects(method);
  }
  public static boolean affects( Method method ) {
    return effectMethods().contains( method );
  }
  
  protected static Map< Method, Integer > initEffectMethods() {
    methodComparator = new Comparator< Method >() {

      @Override
      public int compare( Method o1, Method o2 ) {
        int cmp;
        if (o1 == o2) return 0;
        if (o1 == null) return -1;
        if (o2 == null) return 1;
        cmp = o1.getName().compareTo( o2.getName() );
        if ( cmp != 0 ) return cmp;
        cmp = o1.getDeclaringClass().getName()
                .compareTo( o2.getDeclaringClass().getName() );
        if ( cmp != 0 ) return cmp;
        cmp = CompareUtils.compareCollections( o1.getParameterTypes(),
                                               o2.getParameterTypes(),
                                               true, true );
        return cmp;
      }
    };
    effectMethods = new TreeMap< Method, Integer >( methodComparator  );
    if ( inverseMethods == null ) {
      inverseMethods = new TreeMap< Method, Method >( methodComparator );
    }
    inverseMethods.clear();

    Method m = getSetValueMethod();
    if ( m != null ) effectMethods.put( m, 0 );
    m = getAddNumberAtTimeMethod();
    inverseMethods.put( m, getSubtractNumberAtTimeMethod() );
    if ( m != null ) effectMethods.put( m, 1 );
    m = getAddNumberForTimeRangeMethod();
    inverseMethods.put( m, getSubtractNumberForTimeRangeMethod() );
    if ( m != null ) effectMethods.put( m, 1 );
    m = getSubtractNumberAtTimeMethod();
    inverseMethods.put( m, getAddNumberAtTimeMethod() );
    if ( m != null ) effectMethods.put( m, 1 );
    m = getSubtractNumberForTimeRangeMethod();
    inverseMethods.put( m, getAddNumberForTimeRangeMethod() );
    if ( m != null ) effectMethods.put( m, 1 );
    m = getMultiplyNumberAtTimeMethod();
    inverseMethods.put( m, getDivideNumberAtTimeMethod() );
    if ( m != null ) effectMethods.put( m, 1 );
    m = getMultiplyNumberForTimeRangeMethod();
    inverseMethods.put( m, getDivideNumberForTimeRangeMethod() );
    if ( m != null ) effectMethods.put( m, 1 );
    m = getDivideNumberAtTimeMethod();
    inverseMethods.put( m, getMultiplyNumberAtTimeMethod() );
    if ( m != null ) effectMethods.put( m, 1 );
    m = getDivideNumberForTimeRangeMethod();
    inverseMethods.put( m, getMultiplyNumberForTimeRangeMethod() );
    if ( m != null ) effectMethods.put( m, 1 );

    //m = getSetValueMethod2();
    //m = TimeVaryingMap.class.getMethod("unsetValue");
    //m = TimeVaryingMap.class.getMethod("unapply");
    return effectMethods;
  }

  // TODO -- make this a JUnit
  public static void main( String[] args ) {

    boolean succ = testGetFirstTimepointParameter();
    Assert.assertTrue( succ );

    String fileName1 = "integerTimeline.csv";
    String fileName2 = "aggregateLoad.csv";
    
    TimeVaryingMap< Integer > intMap1 = null;
    TimeVaryingMap< Double > doubleMap2 =null;
    TimeVaryingMap< Double > doubleMap3 = null;
    TimeVaryingMap< Integer > intMap4 = null;
    TimeVaryingMap< Integer > intMap5 = null;
    TimeVaryingMap< Double > doubleMap6 = null;

    intMap1 = new TimeVaryingMap< Integer >( "integer_map", fileName1, Integer.class );
    System.out.println( "map1 loaded from " + fileName1 + ":\n" + intMap1 );
    doubleMap2 = new TimeVaryingMap< Double >( "double_map", fileName2, Double.class );

    Assert.assertTrue( intMap1.isConsistent() );
    Assert.assertTrue( doubleMap2.isConsistent() );

    System.out.println( "\nmap2 loaded from " + fileName2 + ":\n" + doubleMap2 );
    try {
      intMap1.multiply( 2, intMap1.firstKey(), null );
      System.out.println( "\nmap1 multiplied by 2:\n" + intMap1 );
      doubleMap3 = doubleMap2.plus( 12.12 );
      System.out.println( "\nnew map3 = map2 plus 12.12:\n" + doubleMap3 );
      doubleMap3 = doubleMap2.times( 1111, doubleMap2.firstKey(), doubleMap2.lastKey() );
      System.out.println( "\nmap3 = map2 times 1111 (except for the last entry):\n" + doubleMap3 );
      doubleMap3 = doubleMap2.times( 1111, doubleMap2.lastKey(), doubleMap2.lastKey() );
      System.out.println( "\nmap3 = map2 times 1111 (for just the last entry):\n" + doubleMap3 );

    Assert.assertTrue( intMap1.isConsistent() );
    Assert.assertTrue( doubleMap2.isConsistent() );
    Assert.assertTrue( doubleMap3.isConsistent() );

    doubleMap3.add( intMap1 );
    System.out.println( "\nmap3 = map3 + map1:\n" + doubleMap3);
    doubleMap3.divide( 0.5 );
    System.out.println( "\nmap3 /= 0.5:\n" + doubleMap3);
    System.out.println( "map2:\n" + doubleMap2);
    doubleMap3 = doubleMap2.dividedBy( 2.0 );
    System.out.println( "\nmap3 = map2 / 2.0:\n" + doubleMap3);
    System.out.println( "map2:\n" + doubleMap2);
    doubleMap3 = doubleMap2.dividedBy( 2 );
    System.out.println( "\nmap3 = map2 / 2:\n" + doubleMap3);
    System.out.println( "map2:\n" + doubleMap2);

    Assert.assertTrue( intMap1.isConsistent() );
    Assert.assertTrue( doubleMap2.isConsistent() );
    Assert.assertTrue( doubleMap3.isConsistent() );

    System.out.println( "map1:\n" + intMap1);
    doubleMap3 = intMap1.dividedBy( 2.0 );
    System.out.println( "\nmap3 = map1 / 2.0:\n" + doubleMap3 );

    Assert.assertTrue( intMap1.isConsistent() );
    try {
      Assert.assertTrue( doubleMap3.isConsistent() ); // TODO -- THIS CURRENTLY FAILS!
    } catch ( AssertionFailedError e ) {
      System.err.println("Caught assertion failure and continuing.");
    }

    intMap1.clear();
    doubleMap2.clear();
    doubleMap3.clear();
    intMap4 = new TimeVaryingMap< Integer >();

    // some timepoints to use
    Timepoint zero = new Timepoint( "zero", 0L, null );
    Timepoint one = new Timepoint( "one", 1L, null );
    Timepoint two = new Timepoint( "two", 2L, null );
    Timepoint four = new Timepoint( "four", 4L, null );
    Timepoint eight = new Timepoint( "eight", 8L, null );
    Timepoint zero2 = new Timepoint( "zero", 0L, null );
    Timepoint one2 = new Timepoint( "one", 1L, null );
    Timepoint two2 = new Timepoint( "two", 2L, null );
    Timepoint four2 = new Timepoint( "four", 4L, null );
    Timepoint eight2 = new Timepoint( "eight", 8L, null );

    intMap1.setValue( zero, 0 );
    intMap1.setValue( one, 2 );
    intMap1.setValue( two, 4 );

    intMap4.setValue( eight, 0 );

    intMap5 = intMap1.plus( intMap4 );
    System.out.println( "\nmap5 = map1 + map4 = " + intMap1 + " + " + intMap4
                        + " = " + intMap5 + "\n");
    intMap5 = intMap1.minus( intMap4 );
    System.out.println( "\nmap5 = map1 - map4 = " + intMap1 + " - " + intMap4
                        + " = " + intMap5 + "\n" );
    intMap5 = intMap4.plus( intMap1 );
    System.out.println( "\nmap5 = map4 + map1 = " + intMap4 + " + " + intMap1
                        + " = " + intMap5 + "\n" );
    intMap5 = intMap4.minus( intMap1 );
    System.out.println( "\nmap5 = map4 - map1 = " + intMap4 + " - " + intMap1
                        + " = " + intMap5 + "\n" );

    intMap4.setValue( four, 21 );
    intMap4.setValue( eight, 3 );

    intMap5 = intMap1.plus( intMap4 );
    System.out.println( "\nmap5 = map1 + map4 = " + intMap1 + " + " + intMap4
                        + " = " + intMap5 + "\n" );
    intMap5 = intMap1.minus( intMap4 );
    System.out.println( "\nmap5 = map1 - map4 = " + intMap1 + " - " + intMap4
                        + " = " + intMap5 + "\n" );
    intMap5 = intMap4.plus( intMap1 );
    System.out.println( "\nmap5 = map4 + map1 = " + intMap4 + " + " + intMap1
                        + " = " + intMap5 + "\n" );
    intMap5 = intMap4.minus( intMap1 );
    System.out.println( "\nmap5 = map4 - map1 = " + intMap4 + " - " + intMap1
                        + " = " + intMap5 + "\n" );

    Assert.assertTrue( intMap1.isConsistent() );
    Assert.assertTrue( intMap4.isConsistent() );
    Assert.assertTrue( intMap5.isConsistent() );

    doubleMap2.setValue( zero, 0.0 );
    doubleMap2.setValue( one, 2.0 );
    doubleMap2.setValue( two, 4.0 );

    doubleMap3.setValue( eight, 0.0 );

    doubleMap6 = doubleMap2.plus( doubleMap3 );
    System.out.println( "\nmap6 = map2 + map3 = " + doubleMap2 + " + " + doubleMap3
                        + " = " + doubleMap6 );
    doubleMap6 = doubleMap2.minus( doubleMap3 );
    System.out.println( "\nmap6 = map2 - map3 = " + doubleMap2 + " - " + doubleMap3
                        + " = " + doubleMap6 );
    doubleMap6 = doubleMap3.plus( doubleMap2 );
    System.out.println( "\nmap6 = map3 + map2 = " + doubleMap3 + " + " + doubleMap2
                        + " = " + doubleMap6 );
    doubleMap6 = doubleMap3.minus( doubleMap2 );
    System.out.println( "\nmap6 = map3 - map2 = " + doubleMap3 + " - " + doubleMap2
                        + " = " + doubleMap6 );

    doubleMap3.setValue( four, 21.0 );
    doubleMap3.setValue( eight, 3.0 );

    doubleMap6 = doubleMap2.plus( doubleMap3 );
    System.out.println( "\nmap6 = map2 + map3 = " + doubleMap2 + " + " + doubleMap3
                        + " = " + doubleMap6 );
    doubleMap6 = doubleMap2.minus( doubleMap3 );
    System.out.println( "\nmap6 = map2 - map3 = " + doubleMap2 + " - " + doubleMap3
                        + " = " + doubleMap6 );
    doubleMap6 = doubleMap3.plus( doubleMap2 );
    System.out.println( "\nmap6 = map3 + map2 = " + doubleMap3 + " + " + doubleMap2
                        + " = " + doubleMap6 );
    doubleMap6 = doubleMap3.minus( doubleMap2 );
    System.out.println( "\nmap6 = map3 - map2 = " + doubleMap3 + " - " + doubleMap2
                        + " = " + doubleMap6 );

    // multiple timepoints with the same time value
    System.out.println("\nmultiple timepoints with the same time value");
    doubleMap2.putAll( doubleMap3 );
    doubleMap2.removeDuplicates();
    doubleMap2.substitute( zero, zero2, false, null );
    doubleMap2.substitute( two, two2, false, null );
    doubleMap3.substitute( eight, eight2, false, null );

    doubleMap6 = doubleMap2.plus( doubleMap3 );
    System.out.println( "\nmap6 = map2 + map3 = " + doubleMap2 + " + " + doubleMap3
                        + " = " + doubleMap6 );
    doubleMap6 = doubleMap2.minus( doubleMap3 );
    System.out.println( "\nmap6 = map2 - map3 = " + doubleMap2 + " - " + doubleMap3
                        + " = " + doubleMap6 );
    doubleMap6 = doubleMap3.plus( doubleMap2 );
    System.out.println( "\nmap6 = map3 + map2 = " + doubleMap3 + " + " + doubleMap2
                        + " = " + doubleMap6 );
    doubleMap6 = doubleMap3.minus( doubleMap2 );
    System.out.println( "\nmap6 = map3 - map2 = " + doubleMap3 + " - " + doubleMap2
                        + " = " + doubleMap6 );

    Assert.assertTrue( doubleMap2.isConsistent() );
    Assert.assertTrue( doubleMap3.isConsistent() );
    Assert.assertTrue( doubleMap6.isConsistent() );


    System.out.println("\nmaps with nulls");

    doubleMap3.setValue( zero, null);
    doubleMap3.setValue( one, null );
    doubleMap3.setValue( two, 4.0 );
    doubleMap3.setValue( four, null );
    doubleMap3.setValue( eight, null );

    doubleMap6 = doubleMap2.plus( doubleMap3 );
    System.out.println( "\nmap6 = map2 + map3 = " + doubleMap2 + " + " + doubleMap3
                        + " = " + doubleMap6 );
    doubleMap6 = doubleMap2.minus( doubleMap3 );
    System.out.println( "\nmap6 = map2 - map3 = " + doubleMap2 + " - " + doubleMap3
                        + " = " + doubleMap6 );
    doubleMap6 = doubleMap3.plus( doubleMap2 );
    System.out.println( "\nmap6 = map3 + map2 = " + doubleMap3 + " + " + doubleMap2
                        + " = " + doubleMap6 );
    doubleMap6 = doubleMap3.minus( doubleMap2 );
    System.out.println( "\nmap6 = map3 - map2 = " + doubleMap3 + " - " + doubleMap2
                        + " = " + doubleMap6 );

    HashMap< String, TimeVaryingMap<?> > tvmapMap = new HashMap< String, TimeVaryingMap<?> >();
    tvmapMap.put( "map1", intMap1 );
    tvmapMap.put( "map2", doubleMap2 );
    tvmapMap.put( "map3", doubleMap3 );
    tvmapMap.put( "map4", intMap4 );
    tvmapMap.put( "map5", intMap5 );
    tvmapMap.put( "map6", doubleMap6 );

    System.out.println("\nargmin and argmax");

    for ( Map.Entry< String, TimeVaryingMap< ? > > e1 : tvmapMap.entrySet() ) {
      for ( Map.Entry< String, TimeVaryingMap< ? > > e2 : tvmapMap.entrySet() ) {
        TimeVaryingMap<String> labelMap = argmax( e1.getKey(), e1.getValue(), e2.getKey(), e2.getValue() );
        System.out.println( "\nlabelMap = argmax(" + e1.getKey() + ", " + e2.getKey() + ") = argmax(" + e1.getValue() + "," + e2.getValue() + ") = " + labelMap );
        Assert.assertTrue( labelMap.isConsistent() );
        labelMap = argmin( e1.getKey(), e1.getValue(), e2.getKey(), e2.getValue() );
        System.out.println( "labelMap = argmin(" + e1.getKey() + ", " + e2.getKey() + ") = argmin(" + e1.getValue() + "," + e2.getValue() + ") = " + labelMap );
        Assert.assertTrue( labelMap.isConsistent() );
      }
    }
//    TimeVaryingMap<String> labelMapMax26 = argmax( "map2", doubleMap2, "map6", doubleMap6 );
//    System.out.println( "\nlabelMap = argmax(map2, map6) = argmin(" + doubleMap2 + "," + doubleMap6 + ") = " + labelMapMax26 );
//    
//    TimeVaryingMap<String> labelMapMin36 = argmin( "map3", doubleMap3, "map6", doubleMap6 );
//    System.out.println( "\nlabelMap = argmin(map3, map6) = argmin(" + doubleMap3 + "," + doubleMap6 + ") = " + labelMapMin36 );

    Assert.assertTrue( doubleMap2.isConsistent() );
    Assert.assertTrue( doubleMap3.isConsistent() );
    Assert.assertTrue( doubleMap6.isConsistent() );

    testJavaPlot( null );
    } catch ( IllegalAccessException e1 ) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    } catch ( InvocationTargetException e1 ) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    } catch ( InstantiationException e1 ) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }
  
    public static void testJavaPlot(String[] args) {
//        JavaPlot p = new JavaPlot();
//        p.addPlot("sin(x)");
//        p.plot();
    }

  @Override
  public boolean canBeApplied( Effect effect ) {
    // checks to see if the timepoint is valid
    if ( effect instanceof EffectFunction ) {
      EffectFunction effectFunction = (EffectFunction)effect;
      Parameter< Long > t = getTimeOfEffect( effectFunction );
      return isTimepoint( t );
    }
    return true;
  }

  /**
   * Both checks and asserts that an effect was applied.
   * @param effect
   * @return whether the effect was applied before this call
   */
  public boolean wasApplied( Effect effect ) {
    return !appliedSet.add(effect);
  }

  @Override
  public < T > T translate( Variable< T > p , Object o , Class< ? > type  ) {
    return null;
  }

  /**
   * @return a generated map of the changes in values from the previous point.
   *         <p>
   *         For example, the deltaMap of { 0=0.0, 3=4.4, 7=10.0 } is { 0=0.0,
   *         3=4.4, 7=5.6 }. The map is fully computed on each call. For any
   *         Consumable c, c.initializeFromDeltaMap(c.getDeltaMap()) should not
   *         change the entries in c.
   *         
   *         TODO -- REVIEW -- This is similar to differentiation.
   */
 public TimeVaryingMap< Double > getDeltaMap() {
    TimeVaryingPlottableMap< Double > deltaMap =
      new TimeVaryingPlottableMap< Double >( "delta_" + name );
    Double lastValue = 0.0;
    for ( Entry< Parameter< Long>, V > e : entrySet() ) {
       Double thisValue = 0.0; // null value is interpreted as 0.0
      if ( e.getValue() instanceof Number ) {
        thisValue = ( (Number)e.getValue() ).doubleValue();
      }
      deltaMap.put( e.getKey(), thisValue - lastValue );
      lastValue = thisValue;
    }
    deltaMap.interpolation = NONE;
    return deltaMap;
  }
  @Override
  public String getQualifiedName( Set< Object > seen ) {
    String n = HasOwner.Helper.getQualifiedName( this, null );
    return n;
  }
  @Override
  public String getQualifiedId( Set< Object > seen ) {
    String n = HasOwner.Helper.getQualifiedId( this, null );
    return n;
  }

}
