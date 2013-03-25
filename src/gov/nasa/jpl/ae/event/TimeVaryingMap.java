/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.StringDomain;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.ClassUtils;
import gov.nasa.jpl.ae.util.CompareUtils;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.FileUtils;
import gov.nasa.jpl.ae.util.MoreToString;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.nomagic.magicdraw.uml.actions.SetEmptyTagsDefaultsAction;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

/**
 * 
 * TimeVaryingMap is a {@link TreeMap} for implementing {@link TimeVarying},
 * mapping a {@link Parameter<Integer>} to a generic class, V. It is also
 * implements {@link ParameterListener} in order to maintain {@link TreeMap}
 * consistency when the key changes. It "floats" entries before its
 * {@link Parameter<Integer>} changes to protect the data structure and
 * reinserts the entry after the {@link Parameter<Integer>} has changed.
 * 
 */
public class TimeVaryingMap< V > extends TreeMap< Parameter<Integer>, V >
                                 implements Cloneable,
                                            TimeVarying< V >,
                                            Affectable,
                                            ParameterListener,
                                            AspenTimelineWritable {

  private static final long serialVersionUID = -2428504938515591538L;
  
  public static class Interpolation  {
    protected static final byte STEP = 0; // value for key = get(floorKey( key ))
    protected static final byte LINEAR = 1; // floorVal+(ceilVal-floorVal)*(key-floorKey)/(ceilKey-floorKey)
    protected static final byte RAMP = 2; // linear
    protected static final byte NONE = Byte.MAX_VALUE; // value for key = get(key)
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



  public class TimeValue extends Pair< Parameter<Integer>, V >
                               implements HasParameters {

    protected final int id = HasIdImpl.getNext();

    public TimeValue( Parameter<Integer> t, V v ) {
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
      breakpoint();
      Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
      if ( pair.first ) return false;
      seen = pair.second;
      //if ( Utils.seen( this, deep, seen ) ) return false;
      return HasParameters.Helper.substitute( this, p1, p2, deep, seen, false );
    }

    @Override
    public int getId() {
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

  public static class TimeComparator implements Comparator< Parameter< Integer > > {

    public boolean propagate = false;
    public boolean checkId = true;

    @Override
    public int compare( Parameter< Integer > o1, Parameter< Integer > o2 ) {
      if ( o1 == o2 ) return 0;
      if ( o1 == null ) return -1;
      if ( o2 == null ) return 1;
      if ( ( o1.getType() != null && !Integer.class.isAssignableFrom( o1.getType() ) ) ||
           ( o2.getType() != null && !Integer.class.isAssignableFrom( o2.getType() ) ) ) {
        return o1.compareTo( o2, checkId );
      }
      int compare = 0;
      Integer v1 = null;
      Integer v2 = null;
      try {
        v1 = Expression.evaluate( o1, Integer.class, propagate );
        v2 = Expression.evaluate( o2, Integer.class, propagate );
      } catch (ClassCastException e) {
        // May be possible to get here if a value was not an Integer.
        return o1.compareTo( o2, checkId );
      }
      compare = CompareUtils.compare( v1, v2, true );
      if ( compare != 0 ) return compare;
      return o1.compareTo( o2, checkId );
    }
  }

  /**
   * 
   */
  public TimeVaryingMap( String name ) {
    super(new TimeComparator());
    this.name = name;
  }
  public TimeVaryingMap( String name, String fileName ) {
    super(new TimeComparator());
    this.name = name;
    if ( fileName != null ) {
      try {
        fromCsvFile( fileName, type );
      } catch ( FileNotFoundException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }


  public TimeVaryingMap( String name, Class<V> type ) {
    this(name);
    this.type = type;
  }
  protected TimeVaryingMap( String name, String fileName, Class<V> type ) {
    this(name);
    this.type = type;
    if ( fileName != null ) {
      try {
        fromCsvFile( fileName, type );
      } catch ( FileNotFoundException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  protected TimeVaryingMap( String name, V defaultValue, Class<V> type ) {
    super(new TimeComparator());
    this.name = name;
    this.type = type;
    // REVIEW -- consider forcing all constructors to provide non-null type
    V valueToInsert = null;
    if ( this.type == null ) {
      if ( defaultValue != null ) {
        setType( defaultValue.getClass() );
      }
      valueToInsert = defaultValue;
    } else {
      valueToInsert = Expression.evaluate( defaultValue, this.type, false, true );
    }
    Parameter<Integer> t = new Parameter<Integer>(null, null, 0, this);
    Debug.errorOnNull(true,"this should neeeever be null", t.getValue(false) );
    put( t, valueToInsert );
    if ( Debug.isOn() ) isConsistent();
  }

  public TimeVaryingMap( String name, String fileName,
                         V defaultValue, Class<V> type ) {
    this( name, defaultValue, type );
    if ( fileName != null ) {
      try {
        fromCsvFile( fileName, type );
      } catch ( FileNotFoundException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

//  public TimeVaryingMap( String name, V defaultValue ) {
//    this(name, defaultValue, null);
//  }
//
  public TimeVaryingMap( String name, String fileName, V defaultValue ) {
    this(name, defaultValue, null);
    if ( fileName != null ) {
      try {
        fromCsvFile( fileName, type );
      } catch ( FileNotFoundException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  /**
   * @param obj object to cast from 
   * @param cls Class to which to cast o
   * @return obj cast to T or {@code null} if the cast fails
   */
  public static < T > T tryCast( Object obj, Class<T> cls ) {
    if ( cls != null ) {
      try {
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
  public Parameter<Integer> tryCastTimepoint( Object obj ) {
    if ( obj instanceof Parameter ) {
      Parameter<?> p = (Parameter< ? >)obj;
      Object val = p.getValueNoPropagate();
      if ( val == null || val instanceof Integer ) {
        return (Parameter< Integer >)obj;
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
    } catch ( ClassCastException e ) {
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
  public Parameter<Integer> tryEvaluateTimepoint( Object obj, boolean propagate ) {
    return tryEvaluateTimepoint( obj, propagate, true );
  }
  
  /**
   * @param obj
   *          object to cast or convert to a timepoint
   * @param propagate
   *          whether to propagate dependencies as part of the evaluation
   * @param okToWrap
   *          whether a Parameter may be created to wrap an Integer value into a
   *          timepoint
   * @return obj evaluated as Timepoint or {@code null} if the conversion or
   *         cast fail
   */
  @SuppressWarnings( "unchecked" )
  public Parameter<Integer> tryEvaluateTimepoint( Object obj, boolean propagate, boolean okToWrap ) {
    try {
      Class< ? extends Parameter<Integer> > pcls = (Class< ? extends Parameter< Integer >>)( isEmpty() ? Parameter.class : firstKey().getClass() );
      return Expression.evaluate( obj, (Class<Parameter<Integer>>)pcls, propagate, okToWrap );
    } catch ( ClassCastException e ) {
      // ignore
    }
    return null;
  }
  
  public TimeVaryingMap( String name, Method initialValueFunction,
                         Object o, int samplePeriod, int horizonDuration ) {
    super(new TimeComparator());
    this.name = name;
    samplePeriod = correctSamplePeriod( samplePeriod, horizonDuration );
    try {
      for ( int t = 0; t < horizonDuration; t += samplePeriod ) {
        // WARNING: creating Parameter<Integer> with no owner in order to avoid
        // unnecessary overhead with constraint processing. If modified while in
        // the map, it can corrupt the map.
        Object v = initialValueFunction.invoke( o, t );
        Parameter< Integer > tp = makeTempTimepoint( t, false );
        if (tp == null || tp.getValue(false) == null) {
          System.err.println("Error - inserting null timepoint!");
        } else {
          put( tp,//new Parameter<Integer>( "", t, this ),
               tryCastValue( v ) );
        }
      }
    } catch ( IllegalAccessException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( IllegalArgumentException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if ( Debug.isOn() ) isConsistent();
  }

  public TimeVaryingMap( TimeVaryingMap<V> tvm ) {
    this( tvm.name, null, null, tvm.type );
    owner = tvm.owner;
    clear(); // clears the default value.
    putAll( tvm );
  }

  public TimeVaryingMap<V> clone() {
    TimeVaryingMap<V> tvm = new TimeVaryingMap<V>(this);
    return tvm;
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

  public Parameter<Integer> getTimepointBefore( Parameter<Integer> t ) {
    if ( t == null ) return null;
    return this.lowerKey( t );
  }

  public Parameter<Integer> getTimepointBefore( Integer t ) {
    if ( t == null ) return null;
    Parameter<Integer> tp = makeTempTimepoint( t, false );
    Parameter<Integer> k = this.lowerKey( tp );
    return k;
  }

  public Parameter<Integer> getTimepointEarlier( Parameter<Integer> t ) {
    if ( t == null ) return null;
    return getTimepointEarlier( t.getValue( false ) );
  }

  public Parameter<Integer> getTimepointEarlier( Integer t ) {
    if ( t == null ) return null;
    Integer justBeforeTimeVal = t;
    Parameter<Integer> justBeforeTime = getTimepointBefore( t );
    while ( justBeforeTime != null ) {
      justBeforeTimeVal = justBeforeTime.getValue( false ); 
      if ( justBeforeTimeVal < t ) {
        break;
      }
      justBeforeTime = getTimepointBefore( justBeforeTime );
    }
    return justBeforeTime;
  }

  public Parameter<Integer> getTimepointAfter( Parameter<Integer> t ) {
    if ( t == null ) return null;
    return this.higherKey( t );
  }

  public Parameter<Integer> getTimepointAfter( Integer t ) {
    if ( t == null ) return null;
    Parameter<Integer> tp = makeTempTimepoint( t, false );
    return this.higherKey( tp );
  }

  public Parameter<Integer> getTimepointLater( Parameter<Integer> t ) {
    if ( t == null ) return null;
    return getTimepointLater( t.getValue( false ) );
  }

  public Parameter<Integer> getTimepointLater( Integer t ) {
    if ( t == null ) return null;
    Integer justAfterTimeVal = t;
    Parameter<Integer> justAfterTime = getTimepointAfter( t );
    while ( justAfterTime != null ) {
      justAfterTimeVal = justAfterTime.getValue( false ); 
      if ( justAfterTimeVal < t ) {
        break;
      }
      justAfterTime = getTimepointAfter( justAfterTime );
    }
    return justAfterTime;
  }

  public V getValueBefore( Parameter<Integer> t ) {
    if ( t == null ) return null;
    Parameter<Integer> justBeforeTime = getTimepointBefore( t );
    V valBefore = null;
    if ( justBeforeTime != null ) {
      valBefore = get( justBeforeTime );
    }
    return valBefore;
  }
  
  public V getValueBefore( Integer t ) {
    if ( t == null ) return null;
    Parameter<Integer> justBeforeTime = getTimepointBefore( t );
    V valBefore = null;
    if ( justBeforeTime != null ) {
      valBefore = get( justBeforeTime );
    }
    return valBefore;
  }
  
  public V getValueEarlier( Parameter<Integer> t ) {
    if ( t == null ) return null;
    return getValueEarlier( t.getValue( false ) );
  }
  
  public V getValueEarlier( Integer t ) {
    if ( t == null ) return null;
    Parameter<Integer> justEarlierTime = getTimepointEarlier( t );
    V valBefore = null;
    if ( justEarlierTime != null ) {
      valBefore = get( justEarlierTime );
    }
    return valBefore;
  }

  public V getValueAfter( Parameter<Integer> t ) {
    if ( t == null ) return null;
    Parameter<Integer> justAfterTime = getTimepointAfter( t );
    V valAfter = null;
    if ( justAfterTime != null ) {
      valAfter = get( justAfterTime );
    }
    return valAfter;
  }
  
  public V getValueAfter( Integer t ) {
    if ( t == null ) return null;
    Parameter<Integer> justAfterTime = getTimepointAfter( t );
    V valAfter = null;
    if ( justAfterTime != null ) {
      valAfter = get( justAfterTime );
    }
    return valAfter;
  }

  public Parameter<Integer> makeTempTimepoint( Integer t, boolean maxName ) {
    //if ( t == null ) return null;
    String n = ( maxName ? StringDomain.typeMaxValue : null );
    Parameter<Integer> tp = new Parameter<Integer>( n, null, t, this );
    return tp;
  }

  protected static int correctSamplePeriod( int samplePeriod,
                                            int horizonDuration ) {
    if ( samplePeriod == 0 ) {
      samplePeriod = Math.max( 1, horizonDuration / 10 );
    }
    return samplePeriod;
  }

  @Override
  public void handleValueChangeEvent( Parameter< ? > parameter ) {
    breakpoint();
    if ( parameter == null ) return;
    if ( parameter.getValueNoPropagate() instanceof Integer ) {
      unfloatEffects( (Parameter<?>)parameter );
    }
  }

  protected void floatEffects( Parameter<Integer> t ) {
    breakpoint();
    if ( t == null ) return;
    assert t.getValueNoPropagate() != null;
    if ( !containsKey( t ) ) return;
    V value = get( t );
    if ( Debug.isOn() ) Debug.out( getName() + ": floating effect, (" + t + ", " + value + ")" );
    floatingEffects.add( new TimeValue( t, value ) );
    remove( t );
    if ( Debug.isOn() ) isConsistent();
  }

  protected void unfloatEffects( Parameter<?> t ) {
    breakpoint();
    if ( t == null ) return;
    if ( t.getValueNoPropagate() == null ) return;
    ArrayList<TimeValue> copy = new ArrayList<TimeValue>( floatingEffects );
    for ( TimeValue e : copy ) {
      if ( e.first.compareTo( t ) == 0 ) {
        put( e.first, e.second );
        if ( Debug.isOn() ) Debug.out( getName() + ": unfloated effect, " + e );
      }
      floatingEffects.remove( e );
    }
    if ( Debug.isOn() ) isConsistent();
  }

  @Override
  public void handleDomainChangeEvent( Parameter< ? > p ) {
    unfloatEffects( tryCastTimepoint( p ) );
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

  public Object getOwner() {
    return owner;
  }
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
  public boolean
      substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep,
                  Set< HasParameters > seen ) {
    breakpoint();
    if ( HasParameters.Helper.substitute( this, p1, p2, deep, seen, false ) ) {
      if ( Debug.isOn() ) isConsistent();
      return true;
    }
    if ( HasParameters.Helper.substitute( floatingEffects, p1, p2, deep, seen, true ) ) {
      if ( Debug.isOn() ) isConsistent();
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
    if ( Debug.isOn() ) Debug.outln( "setStale(" + staleness + ") to " + this );
    Assert.assertTrue( "This method is not supported!", false );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListener#setStaleAnyReferencesTo(gov.nasa.jpl.ae.event.Parameter)
   */
  @Override
  public void setStaleAnyReferencesTo( Parameter< ? > changedParameter ) {
    breakpoint();
    if ( Debug.isOn() ) Debug.outln( getName() + ".setStaleAnyReferencesTo(" + changedParameter + ")" );
    if ( changedParameter == null ) return;
    if ( containsKey( changedParameter ) ) {
      if ( Debug.isOn() ) Debug.outln( getName() + ".setStaleAnyReferencesTo(" + changedParameter + "): does contain" );
      floatEffects( tryCastTimepoint( changedParameter ) );
    } else {
      if ( Debug.isOn() ) Debug.outln( getName() + ".setStaleAnyReferencesTo(" + changedParameter + "): does not contain" );
    }
    if ( Debug.isOn() ) isConsistent();
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
   * @see gov.nasa.jpl.ae.event.TimeVarying#getValue(gov.nasa.jpl.ae.event.Parameter<Integer>)
   */
  @Override
  public V getValue( Parameter< Integer > t ) {
    return getValue( t, true );
  }
  public V getValue( Parameter< Integer > t, boolean valuesEqualForKeysOk ) {
    if ( t == null ) return null;
    NavigableMap< Parameter< Integer >, V > m = headMap( t, true );
    if ( !m.isEmpty() ) {
      Entry< Parameter< Integer >, V > e = m.lastEntry();
      if ( e.getKey().equals( t )
           || ( valuesEqualForKeysOk && Expression.valuesEqual( e.getKey(), t,
                                                                Integer.class ) ) ) {
        return m.lastEntry().getValue();
      }
    }
    if ( Debug.isOn() ) isConsistent();
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
      Parameter<Integer> t1 = null;
      if ( !m.isEmpty() ) {
        t1 = m.lastEntry().getKey();
        v1 = m.lastEntry().getValue();
      }
      if ( Debug.isOn() ) {
        Assert.assertEquals( t1, getTimepointBefore( t ) );
        Assert.assertEquals( v1, get( t1 ) );
        Debug.outln("getValue() change looks good.");
      }
      Parameter<Integer> t2 = getTimepointAfter( t );
      //v1 = get( t1 );
      if ( t1.valueEquals( t2 ) ) return v1;
      v2 = get( t2 );
      if ( v1 == null ) return null;
      if ( v2 == null ) return v1;
      // floorVal+(ceilVal-floorVal)*(key-floorKey)/(ceilKey-floorKey)
      v1 = Functions.plus( v1,
                           Functions.divide( Functions.times( Functions.minus( v2, v1 ),
                                                              Functions.minus( t, t1 ) ),
                                             Functions.minus( t2, t1 ) ) );
      return v1;
    }
    Debug.error( true,
                 "TimeVaryingMap.getValue(): invalid interpolation type! "
                     + interpolation.type );
    return null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.TimeVarying#getValue(java.lang.Integer)
   */
  @Override
  public V getValue( Integer t ) {
    if ( t == null ) return null;
    Parameter<Integer> tp = makeTempTimepoint( t, true );
    Entry< Parameter<Integer>, V > e = this.floorEntry( tp );
    if ( Debug.isOn() ) isConsistent();
    if ( e != null ) return e.getValue();
//  if ( !isEmpty() && firstEntry().getKey().getValue() <= t ) {
//    return firstEntry().getValue();
//  }
    if ( !isEmpty() ) {
      Entry< Parameter<Integer>, V > f = firstEntry(); 
      Parameter<Integer> k = f.getKey();
      if ( IntegerDomain.defaultDomain.lessEquals( k.getValue( false ), t ) ) {
        return f.getValue();
      }
    }
    return null;
  }

  /**
   * Return the Parameter<Integer>1 for an entry (Parameter<Integer>1, value1) such that
   * Parameter<Integer>1.value equals {@code tp}.value, and value1 equals value.
   * 
   * @param value
   *          the value to match with a value in the map
   * @param tp
   *          the time value required for the returned key
   * @return a Parameter<Integer> key in the map, whose time value equals {@code tp}'s value and
   *         whose map entry equals value or {@code null} if there is no such Parameter<Integer>.
   */
  public boolean hasValueAt( V value, Parameter<Integer> tp ) {
    if ( tp == null ) return false;
    V v = get( tp );
    if ( value == v ) return true;
    if ( v != null ) return Expression.valuesEqual( value, v );// value.equals( v );
    // Saving this null check until later in case a null time value is
    // acceptable, and get(t) above works.
    if ( tp.getValue( false ) == null ) return false;
    return hasValueAt( value, tp.getValueNoPropagate() );
  }
  
  /**
   * Return the Parameter<Integer>1 for an entry (Parameter<Integer>1, value1) such that
   * Parameter<Integer>1.value equals {@code t}, and value1 equals value.
   * 
   * @param value
   *          the value to match with a value in the map
   * @param t
   *          the time value required for the returned key
   * @return a Parameter<Integer> key in the map, whose time value equals {@code t} and whose map
   *         entry equals value or {@code null} if there is no such Parameter<Integer>.
   */
  public Parameter<Integer> keyForValueAt( V value, Integer t ) {
    // REVIEW -- use getKeys()?
    if ( t == null ) return null;
    Parameter<Integer> tp = makeTempTimepoint( t, false );
    Entry< Parameter<Integer>, V > e = this.floorEntry( tp );
    Parameter<Integer> startKey = null;
    if ( e != null ) {
      startKey = e.getKey();
    } else if ( !isEmpty() ) {
      startKey = firstEntry().getKey();
    } else {
      return null;
    }
    NavigableMap< Parameter< Integer >, V > tailMap =
        this.tailMap( startKey, true );
    for ( java.util.Map.Entry< Parameter< Integer >, V > te : tailMap.entrySet() ) {
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
   * @param t
   * @return
   */
  public boolean hasValueAt( V value, Integer t ) {
    return keyForValueAt( value, t ) != null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.TimeVarying#setValue(gov.nasa.jpl.ae.event.Parameter<Integer>, java.lang.Object)
   */
  @Override
  public V setValue( Parameter< Integer > t, V value ) {
    breakpoint();
    if ( Debug.isOn() ) Debug.outln( getName() + " setValue(" + t + ", " + value + ")" );
    if ( t == null ) {
      if ( Debug.isOn() ) Debug.error( false, "Error! trying to insert a null Parameter<Integer> into the map!" );
      return null;
    }
    if ( t.getValueNoPropagate() == null ) {
      if ( Debug.isOn() ) Debug.error( false, "Error! trying to insert a null Parameter<Integer> value into the map!" );
      return null;
    }
    if ( Debug.isOn() ) {
      if ( t.getOwner() == null ) {
        Debug.error( false, "Warning: inserting a Parameter<Integer> with null owner into the map--may be detached!" );
      }
      if ( value != null && value instanceof Parameter
           && ( (Parameter<?>)value ).getOwner() == null ) {
        Debug.error( true, "Warning: trying to insert a value with a null owner into the map--may be detached!" );
      }
    }
    V oldValue = null;
    Parameter<Integer> tp = keyForValueAt( value, t.getValue( false ) );
    if ( Debug.isOn() ) isConsistent();
    if ( tp != null && tp != t ) {
      remove( tp );
    }
    if ( getType() != null && value != null && !(getType().isAssignableFrom( value.getClass() ) ) ) {
      V valueBefore = value;
      value = Expression.evaluate( value, getType(), true, true );
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
                + valueBefore.getClass().getSimpleName() + ". Expected type is "
                + getType().getSimpleName() + ".  Inserting value of type "
                + value.getClass().getSimpleName() + " instead. value = " + value
                + "; this = " + this.toString( true, true, null );
        }
        if ( value != null && !getType().isAssignableFrom( value.getClass() ) ) {
          Debug.error( false, warningMsg );
        } else {
          Debug.errln( warningMsg );
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
    if ( Debug.isOn() ) isConsistent();
    if ( Debug.isOn() ) Debug.outln( getName() + "setValue(" + t + ", " + value
                                     + ") returning oldValue=" + oldValue );
    return oldValue;
  }

  /**
   * @param n the number by which this map is multiplied
   * @return this map after multiplying each value by {@code n}
   */
  public TimeVaryingMap<V> multiply( Number n ) {
    if ( TimeVaryingMap.class.isAssignableFrom( getType() ) ) {
      for ( java.util.Map.Entry< Parameter< Integer >, V > e : entrySet() ) {
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
   */
  public TimeVaryingMap<V> times( Number n ) {
    return times( n, firstKey(), null );
  }
  
  /**
   * @param n the number by which the map is multiplied
   * @param fromKey
   *          the key from which all values are multiplied by {@code n}.
   * @return this map after multiplying each value in the range [fromKey,
   *         toKey) by {@code n}
   */
  public TimeVaryingMap< V > multiply( Number n, Parameter< Integer > fromKey ) {
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
    for ( java.util.Map.Entry< Parameter< Integer >, V > e : entrySet() ) {
      V v = e.getValue();
      Object retVal = null;
      if ( v instanceof TimeVaryingMap ) {
        TimeVaryingMap< ? > tvm = (TimeVaryingMap< ? >)v;
        // TODO -- REVIEW -- it would be good to be sure that this
        // substitution makes sense.
        if ( !isStatic ) {
          call.setObject( tvm );
          retVal = call.evaluate( true );
        } else {
          boolean gotTVM = false, gotNullTVM = false;
          for ( int i = 0; i < call.getArguments().size(); ++i ) {
            Object arg = call.getArguments().get( i );
            Class< ? > argType = call.getParameterTypes()[ i ];
            if ( arg == null && argType.isAssignableFrom( TimeVaryingMap.class ) ) {
              if ( indexOfBestArgumentToReplace == null && !gotNullTVM ) {
                indexOfBestArgumentToReplace = i;
                gotNullTVM = true;
                call.getArguments().set( i, tvm );
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
            call.getArguments().set( indexOfBestArgumentToReplace, tvm );
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
        retVal = call.evaluate( true );
      } else {
        Debug.error( false, "Warning! cannot apply call, " + call
                            + ", to non-map value, " + v );
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
   */
  public TimeVaryingMap< V > multiply( Number n, Parameter< Integer > fromKey,
                                        Parameter< Integer > toKey ) {
    
    Map< Parameter< Integer >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      map = subMap( fromKey, true, toKey, true );
    } else {
      boolean same = toKey.equals(fromKey);  // include the key if same
      map = subMap( fromKey, true, toKey, same );
    }
    for ( Map.Entry< Parameter< Integer >, V > e : map.entrySet() ) {
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
   */
  public <VV> TimeVaryingMap< V > multiply( TimeVaryingMap< VV > tvm ) {
    if ( tvm == null ) return null;
    Set< Parameter< Integer > > keys =
        new TreeSet< Parameter< Integer > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    keys.addAll( tvm.keySet() );
    for ( Parameter< Integer > k : keys ) {
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
   */
  public TimeVaryingMap< V > times( Number n, Parameter< Integer > fromKey ) {
    return times( n, fromKey, null );
  }
  
  /**
   * @param n the number by which the map is multiplied
   * @param fromKey the first key whose value is multiplied by {@code n}
   * @param toKey
   *          is not multiplied. To include the last key, pass {@code null} for {@code toKey}.
   * @return a copy of this map for which each value in the range [fromKey,
   *         toKey) is multiplied by {@code n}
   */
  public TimeVaryingMap< V > times( Number n, Parameter< Integer > fromKey,
                                     Parameter< Integer > toKey ) {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.multiply( n, fromKey, toKey );
    return newTvm;
  }


  /**
   * @param n the number by which this map is divided
   * @return this map after dividing each value by {@code n}
   */
  public TimeVaryingMap<V> divide( Number n ) {
    return divide( n, firstKey(), null );
  }
  
  
  
  public Call getCallForThisMethod(Object...args) {
    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
    FunctionCall c = new FunctionCall( null, getClass(), methodName, args );
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
    FunctionCall c = new FunctionCall( null, cls, methodName, args );
    return c;
  }
  
  /**
   * @param n the number by which the map is divided
   * @return a copy of the map whose values are each divided by {@code n}
   */
  public TimeVaryingMap<V> dividedBy( Number n ) {
    Call c = getCallForThisMethod( n );
    if ( TimeVaryingMap.class.isAssignableFrom( getType() ) ) {
      applyToSubMaps( c );
      return this;
    }
    return dividedBy( n, firstKey(), null );
  }
  
  /**
   * @param n the number by which the map is divided
   * @param fromKey
   *          the key from which all values are divided by {@code n}.
   * @return this map after divideing each value in the range [fromKey,
   *         toKey) by {@code n}
   */
  public TimeVaryingMap< V > divide( Number n, Parameter< Integer > fromKey ) {
    return divide( n, fromKey, null );
  }
  
  /**
   * @param n the number by which the map is divided
   * @param fromKey the first key whose value is divided by {@code n}
   * @param toKey is not divided.  To include the last key, pass {@code null} for {@code toKey}.
   * @return this map after dividing each value in the range [{@code fromKey}, {@code toKey})
   */
  public TimeVaryingMap< V > divide( Number n, Parameter< Integer > fromKey,
                                     Parameter< Integer > toKey ) {
    Map< Parameter< Integer >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      map = subMap( fromKey, true, toKey, true );
    } else {
      boolean same = toKey.equals(fromKey);  // include the key if same
      map = subMap( fromKey, true, toKey, same );
    }
    for ( Map.Entry< Parameter< Integer >, V > e : map.entrySet() ) {
      e.setValue( Functions.divide(e.getValue(), n ) );
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
   */
  public <VV> TimeVaryingMap< V > divide( TimeVaryingMap< VV > tvm ) {
    if ( tvm == null ) return null;
    Set< Parameter< Integer > > keys =
        new TreeSet< Parameter< Integer > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    keys.addAll( tvm.keySet() );
    for ( Parameter< Integer > k : keys ) {
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
   */
  public TimeVaryingMap< V > dividedBy( Number n, Parameter< Integer > fromKey ) {
    return dividedBy( n, fromKey, null );
  }
  
  /**
   * @param n the number by which the map is divided
   * @param fromKey the first key whose value is divided by {@code n}
   * @param toKey
   *          is not divided. To include the last key, pass {@code null} for {@code toKey}.
   * @return a copy of this map for which each value in the range [fromKey,
   *         toKey) is divided by {@code n}
   */
  public TimeVaryingMap< V > dividedBy( Number n, Parameter< Integer > fromKey,
                                     Parameter< Integer > toKey ) {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.divide( n, fromKey, toKey );
    return newTvm;
  }


  public TimeVaryingMap<V> add( Number n ) {
    return add( n, firstKey(), null );
  }
  
  public TimeVaryingMap<V> plus( Number n ) {
    return plus( n, firstKey(), null );
  }
  
  public boolean contains( Integer t ) {
    return getKey( t ) != null;
  }
  public Parameter<Integer> getKey( Integer t ) {
    if ( isEmpty() ) return null;
    Parameter< Integer > tt = makeTempTimepoint( t, false );
    return getKey( tt, true );
//    Parameter< Integer > bk = ceilingKey(tt);
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
  public NavigableMap< Parameter< Integer >, V > subMap( Integer timeFrom,
                                                         boolean fromInclusive,
                                                         Integer timeTo,
                                                         boolean toInclusive ) {
    if ( isEmpty() ) return this;
    Parameter<Integer> tpe = getTimepointEarlier( timeFrom );
    Parameter< Integer > f = firstKey();
    if ( tpe == null ) {
      tpe = f;
      fromInclusive = true;
    }
    Parameter<Integer> tpl = getTimepointLater( timeTo );
    if ( tpl == null ) {
      tpl = lastKey();
      toInclusive = true;
    }
    int c = tpe.compareTo( tpl );
    
    if ( c > 0 ) return this.subMap( f, false, f, false );
    if ( c == 0 ) {
      if ( tpe.valueEquals( timeFrom ) ) {
        return subMap( f, true, f, true );
      }
      return this.subMap( f, false, f, false );
    }
    return subMap(tpe, fromInclusive, tpl, toInclusive);

//    Parameter<Integer> fromKey, toKey;
//    fromKey = getKey( timeFrom );
//    toKey = getKey( nextTime );
//    return this.subMap( fromKey, fromInclusive, toKey, toInclusive );
  }

  /**
   * @param tt
   *          the timepoint to which a key is sought to match
   * @return all keys in the map that match the input timepoint {@code tt}.
   */
  public Set< Parameter< Integer > > getKeys( Parameter<Integer> tt ) {
    return getKeys( tt.getValueNoPropagate() );
  }
  /**
   * @param t
   *          the timepoint to which a key is sought to match
   * @return all keys in the map that match the input time value {@code t}.
   */
  public Set< Parameter< Integer > > getKeys( Integer t ) {
    return subMap( t, true, t, true ).keySet();
/*
  if ( isEmpty() ) return Collections.emptySet();
    Parameter<Integer> tpe = getTimepointEarlier( t );
    boolean includeFrom = ( tpe == null ); 
    if ( includeFrom ) {
      tpe = firstKey();
    }
    Parameter<Integer> tpl = getTimepointLater( t );
    boolean includeTo = ( tpl == null ); 
    if ( includeTo ) {
      tpl = lastKey();
    }
    int c = tpe.compareTo( tpl );
    if ( c > 0 ) return Collections.emptySet();
    if ( c == 0 ) {
      if ( tpe.valueEquals( t ) ) {
        Set< Parameter< Integer >> s = new HashSet< Parameter< Integer >>();
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
   *          returned {@code key} or if matching {@code Integer} values are
   *          good enough.
   * @param firstOrLast if true, the first of the set of matching keys is returned; else, the last of the set is returned.  
   * @return the key in the map that matches the input timepoint {@code tt}.
   */
  public Parameter<Integer> getKey( Parameter< Integer > tt, boolean equalValuesOk,
                                    boolean firstOrLast ) {
    Integer v = tt.getValue(false);
    NavigableMap< Parameter< Integer >, V > m = subMap( v, true, v, true );
    if ( m.isEmpty() ) return null;
    Parameter< Integer > timepoint = firstOrLast ? m.firstKey() : m.lastKey();
    return timepoint;
  }
  /**
   * @param tt
   *          the timepoint to which a key is sought to match
   * @param equalValuesOk
   *          specifies whether {@code key.equals(tt)} must be true for the
   *          returned {@code key} or if matching {@code Integer} values are
   *          good enough.
   * @return the key in the map that matches the input timepoint {@code tt}.
   */
  public Parameter<Integer> getKey( Parameter< Integer > tt, boolean equalValuesOk ) {
    NavigableMap< Parameter< Integer >, V > m = headMap( tt, true );
    
    Parameter< Integer > k;
    if ( !m.isEmpty() ) {
      k = m.lastKey();
      if ( k.equals( tt ) ) return k; 
    }
    
    if ( !equalValuesOk ) return null;
    Parameter< Integer > bk = getTimepointAfter(tt);
    if ( bk != null && bk.valueEquals( tt ) ) {
      return bk;
    }
//    bk = getTimepointBefore( tt );
//    if ( bk != null && bk.equals( tt ) ) {
//      return bk;
//    }
    return null;

//    Parameter< Integer > bk = lowerKey(tt);
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
   * {@code key} is considered in the map if a key with the same {@code Integer}
   * value exists.
   * 
   * @param key
   * @param onlyIfDifferentValue
   * @return the inserted key or the existing key if already in the map
   */
  public Parameter< Integer > putKey( Parameter< Integer > key,
                                      boolean onlyIfDifferentValue ) {
    
    if ( key == null ) return null;
    Parameter< Integer > k = getKey( key, onlyIfDifferentValue );
    if ( k != null ) return k;
//    if ( key.getOwner() != this ) {
//      if ( key.getOwner() != null ) {
//        key = (Parameter< Integer >)key.clone();
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
  public TimeVaryingMap< V > add1( Number n, Parameter< Integer > fromKey,
                                   Parameter< Integer > toKey ) {
//    if ( fromKey.getValueNoPropagate() == 10 ) {
//      Debug.outln("");
//    }
    fromKey = putKey( fromKey, true );
    toKey = putKey( toKey, true );
    Map< Parameter< Integer >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      if ( toKey.compareTo( fromKey ) < 0 ) toKey = fromKey;
      map = subMap( fromKey, true, toKey, true );
    } else {
      boolean same = toKey.equals(fromKey);  // include the key if same
      map = subMap( fromKey, true, toKey, same );
    }
    for ( Map.Entry< Parameter< Integer >, V > e : map.entrySet() ) {
      if ( e.getValue() instanceof Double ) {
        Double v = (Double)e.getValue();
        v = v + n.doubleValue();
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
  public TimeVaryingMap< V > add( Number n, Parameter< Integer > fromKey ) {
    return add( n, fromKey, null );
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
  public TimeVaryingMap< V > add( Number n, Parameter< Integer > fromKey,
                                  Parameter< Integer > toKey ) {
    
    //if ( n == null ) return; //REVIEW
    boolean same = toKey == fromKey;  // include the key if same
    fromKey = putKey( fromKey, false );
    if ( same ) {
      toKey = fromKey;
    } else {
      toKey = putKey( toKey, false );
    }
    Map< Parameter< Integer >, V > map = null;
    if ( toKey == null ) {
      toKey = lastKey();
      if ( toKey.compareTo( fromKey ) < 0 ) toKey = fromKey;
      map = subMap( fromKey, true, toKey, true );
    } else {
      map = subMap( fromKey, true, toKey, same );
    }
    boolean succeededSomewhere = false;
    for ( Map.Entry< Parameter< Integer >, V > e : map.entrySet() ) {
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
      }
    }
    //if (succeededSomewhere) appliedSet.add(  )
    return this;
  }

  /**
   * @param tvm the {@code TimeVaryingMap} to be added to this {@code TimeVaryingMap}
   * @return this {@code TimeVaryingMap} after adding {@code tvm}
   */
  public <VV> TimeVaryingMap< V > add( TimeVaryingMap< VV > tvm ) {
    Set< Parameter< Integer > > keys =
        new TreeSet< Parameter< Integer > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    keys.addAll( tvm.keySet() );
    for ( Parameter< Integer > k : keys ) {
      VV v = tvm.getValue( k, false );
      Number n = Expression.evaluate( v, Number.class, false );
      add( n, k, k );
    }
    return removeDuplicates();
  }
  
  /**
   * @param n
   * @return this map after subtracting {@code n} from each value
   */
  public TimeVaryingMap<V> subtract( Number n ) {
    return subtract( n, firstKey(), null );
  }
  
  /**
   * @param n
   * @param fromKey
   *          the key from which {@code n} is subtracted from all values.
   * @return this map after subtracting {@code n} from each value in the range [{@code fromKey},
   *         {@code toKey})
   */
  public TimeVaryingMap< V > subtract( Number n, Parameter< Integer > fromKey ) {
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
   */
  public TimeVaryingMap< V > subtract( Number n, Parameter< Integer > fromKey,
                                       Parameter< Integer > toKey ) {
    return add( Functions.times(n, -1), fromKey, toKey );
  }
  
  /**
   * @param tvm
   *          the {@code TimeVaryingMap} to be subtracted from this
   *          {@code TimeVaryingMap}
   * @return this {@code TimeVaryingMap} after subtracting {@code tvm}
   */
  public <VV> TimeVaryingMap< V > subtract( TimeVaryingMap< VV > tvm ) {
    if ( tvm == null ) return null;
    Set< Parameter< Integer > > keys =
        new TreeSet< Parameter< Integer > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    keys.addAll( tvm.keySet() );
    for ( Parameter< Integer > k : keys ) {
      VV v = tvm.getValue( k, false );
      Number n = Expression.evaluate( v, Number.class, false );
      //if ( n != null ) {// n.doubleValue() != 0 ) {
      subtract( n, k, k );
      //}
    }
    return removeDuplicates();
  }
  
  /**
   * @return this map after removing all but one entry from each set of adjacent
   *         entries with the same values.
   */
  public <VV> TimeVaryingMap< V > removeDuplicates() {
    Debug.outln( "before removing duplicates " + this );
    List<Parameter< Integer > > dups = new ArrayList< Parameter< Integer > >();
    Parameter<Integer> lastKey = null;
    V lastValue = null;
    for ( java.util.Map.Entry< Parameter< Integer >, V > e : entrySet() ) {
      Parameter< Integer > key = e.getKey();
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
    for ( Parameter<Integer> k : dups ) {
      remove( k );
    }
    Debug.outln( " after removing duplicates " + this );
    return this;
  }
  
  
  /**
   * @param n
   * @param fromKey
   *          the key from which {@code n} is added to all values.
   * @return a copy of this map for which {@code n} is added to each value in the range
   *         [{@code fromKey}, {@code toKey})
   */
  public TimeVaryingMap< V > plus( Number n, Parameter< Integer > fromKey ) {
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
  public TimeVaryingMap< V > plus( Number n, Parameter< Integer > fromKey,
                                    Parameter< Integer > toKey ) {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.add( n, fromKey, toKey );
    return newTvm;
  }  

  /**
   * @param map
   * @return a copy of this TimeVaryingMap with {@code map} added to it
   */
  public TimeVaryingMap< V > plus( TimeVaryingMap< V > map ) {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.add( map );
    return newTvm;
  }

  /**
   * @param map1
   * @param map2
   * @return a new map that sums {@code map1} and {@code map2}
   */
  public static <VV> TimeVaryingMap< VV > plus( TimeVaryingMap< VV > map1, TimeVaryingMap< VV > map2 ) {
    TimeVaryingMap< VV > newTvm = map1.clone();
    newTvm.add( map2 );
    return newTvm;
  }

  /**
   * @param map
   * @return a copy of this TimeVaryingMap with {@code map} subtracted from it
   */
  public TimeVaryingMap< V > minus( TimeVaryingMap< V > map ) {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.subtract( map );
    return newTvm;
  }

  /**
   * @param map1
   * @param map2
   * @return a new map that is {@code map1} minus {@code map2}
   */
  public static <VV> TimeVaryingMap< VV > minus( TimeVaryingMap< VV > map1, TimeVaryingMap< VV > map2 ) {
    TimeVaryingMap< VV > newTvm = map1.clone();
    newTvm.subtract( map2 );
    return newTvm;
  }

  /**
   * @param n
   * @param fromKey
   *          the key from which {@code n} is subtracted from all values.
   * @return a copy of this map for which {@code n} is subtracted from each value in the range
   *         [{@code fromKey}, {@code toKey})
   */
  public TimeVaryingMap< V > minus( Number n, Parameter< Integer > fromKey ) {
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
   */
  public TimeVaryingMap< V > minus( Number n, Parameter< Integer > fromKey,
                                    Parameter< Integer > toKey ) {
    TimeVaryingMap< V > newTvm = this.clone();
    newTvm.subtract( n, fromKey, toKey );
    return newTvm;
  }  


  /**
   * Validate the consistency of the map for individual and adjacent entries.
   * @return whether or not the entries in the map make sense.
   */
  public boolean isConsistent() {    
    Parameter<Integer> lastTp = null;
    int lastTime = -1;
    boolean ok = true;
    ArrayList<V> valuesAtSameTime = new ArrayList< V >();
    boolean firstEntry = true;
    for ( Map.Entry< Parameter<Integer>, V >  entry : entrySet() ) {
      Parameter<Integer> tp = entry.getKey();
      V value = null;
      try {
        value = entry.getValue();
      } catch ( ClassCastException cce ) {
        ok = false;
        System.err.println( "Error! Value " + entry.getValue() 
                            + " has the wrong type in TimeVaryingMap! "
                            + this );
        cce.printStackTrace();
      }
      boolean TimepointValueChanged = true;
      boolean duplicateValueAtTime = false;
      // Check for problems with the key.
      // No null keys.
      if ( tp == null ) {
        Debug.error( true, "Error! null key in TimeVaryingMap " + getName() );
        ok = false;
      } else if ( tp.getValueNoPropagate() == null ) {
        // No null values for Parameter<Integer> key.  
        Debug.errorOnNull( true, "Error! null Parameter<Integer> value in TimeVaryingMap "
                                 + getName(), tp.getValueNoPropagate() );
        ok = false;
      } else {
       TimepointValueChanged = !tp.getValueNoPropagate().equals( lastTime );
        if ( !firstEntry && tp.getValueNoPropagate() < lastTime ) {
          // Time cannot decrease.
          Debug.error( true, "Error! time value for entry " + entry
                             + " should be >= " + lastTime
                             + " in TimeVaryingMap " + getName() );
          ok = false;
        } else {
          lastTime = tp.getValueNoPropagate();
        }
      }
      if ( TimepointValueChanged ) {
        valuesAtSameTime.clear();
      }
      duplicateValueAtTime = valuesAtSameTime.contains( value );
      if ( tp != null && tp == lastTp ) {
        // A key should have only one entry.
        Debug.error( true, "Error! Parameter<Integer> has duplicate entry " + entry
                           + " in TimeVaryingMap " + getName() );
        ok = false;
      }
      
      // Check for problems with the value.
      if ( value != null && getType() != null
           && !getType().isAssignableFrom( value.getClass() ) ) {
        Debug.error( true, "Error! value " + value + " at time " + tp
                           + " has an incompatible type "
                           + value.getClass().getCanonicalName()
                           + "; getType() = " + getType().getCanonicalName() );
        ok = false;
      }
      if ( !firstEntry && duplicateValueAtTime ) {
        Debug.error( true, "Error! duplicate entry of value " + value
                           + " at time " + tp + " with value set "
                           + valuesAtSameTime + " for TimeVaryingMap "
                           + getName() );
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
      if ( Debug.isOn() ) Debug.outln( getName() + " is not consistent: " + this );
    }
    return ok;
  }
  
  @Override
  public V unsetValue( Parameter< Integer > t, V value ) {
    breakpoint();
    if ( t == null ) {
      if ( Debug.isOn() ) Debug.error( false, "Warning! unsetValue(" + t + ", " + value
                          + "): Null Parameter<Integer> key for TimeVaryingMap " + this );
      return null;
    }
    if ( t.getValueNoPropagate() == null ) {
      if ( Debug.isOn() ) Debug.error( false,
                   "Warning! unsetValue(" + t + ", " + value
                       + "): Null value for Parameter<Integer> key for TimeVaryingMap "
                       + this );
      return null;
    }
    V oldValue = null;
    Parameter<Integer> tpInMap = keyForValueAt( value, t.getValueNoPropagate() );
// possible fix to a bug, but fixing it elsewhere
//    Parameter<Integer> tI = tryCastTimepoint( t );
//    Parameter<Integer> tpInMap = keyForValueAt( value, tI.getValueNoPropagate() );
    if ( tpInMap != t ) {
      if ( Debug.isOn() ) Debug.error( false, "Warning! unsetValue(" + t + ", " + value 
                          + "): Parameter<Integer> key is not in the map for TimeVaryingMap "
                          + this );
    }
    if ( tpInMap == null ) {
      if ( Debug.isOn() ) Debug.error( false, "Warning! unsetValue(" + t + ", " + value
                          + "): no matching entry in TimeVaryingMap " + this );
    } else {
      oldValue = get( tpInMap );
      if ( Debug.isOn() ) Debug.outln( "unsetValue(" + t + ", " + value + "): removing entry ("
                   + tpInMap + ", " + oldValue + ") in " + this );
      remove( tpInMap );
    }
    if ( Debug.isOn() ) isConsistent();
    return oldValue;
  }

  public void unapply( Effect effect ) {
    if ( isArithmeticEffect( effect ) ) {
      Effect inverseEffect = getInverseEffect( effect );
      if ( canBeApplied( inverseEffect ) ) {
        inverseEffect.applyTo( this, true );
        appliedSet.remove(effect);
      } else {
        Debug.error(true, "Error! Cannot unapply effect: " + effect );
      }
      return;
    }
    Pair< Parameter<Integer>, V > p = 
        getTimeAndValueOfEffect( effect, true );
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
    for ( Entry< Parameter<Integer>, V > e : ( (TimeVaryingMap< V >)this.clone() ).entrySet() ) {
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

  @Override
  public boolean equals( Object o ) {
    if ( this == o ) return true;
    if ( o instanceof TimeVarying ) {
      return ( compareTo( (TimeVarying<V>)o, false ) == 0 );
    }
    return false;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo( TimeVarying< V > o ) {
    return compareTo( o, true );
  }
  public int compareTo( TimeVarying< V > o, boolean checkId ) {
    if ( o == null ) return 1;
    if ( checkId ) return CompareUtils.compare( getId(), o.getId() );
    int compare = 0;
    if ( o instanceof TimeVaryingMap ) {
      TimeVaryingMap<?> otvm = (TimeVaryingMap<?>)o;
      compare = CompareUtils.compare( getName(), otvm.getName(), true );
      if ( compare != 0 ) return compare;
    }
    Debug.err( "TimeVaryingMap.compareTo() may compare values, which, if changed while this is in a map, can corrupt the map." );
    compare = CompareUtils.compare( this, o ); // WARNING: values change!!!
    if ( compare != 0 ) return compare;
    return compare;
  }
  
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
    //Parameter<Integer> t = new Parameter< Integer >( null );
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

  Parameter<Integer> getTimeOfEffect( EffectFunction effectFunction ) {
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
    Integer i = effectMethods.get( effectFunction.getMethod() );
    if ( Debug.isOn() ) Debug.outln( "getIndexOfTimepointArgument("
                                     + effectFunction.getMethod().getName()
                                     + ") = " + i );
    return i;
  }
  
  public <TT> Pair< Parameter<Integer>, TT > getTimeAndValueOfEffect( Effect effect ) {
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
  public < TT > Pair< Parameter< Integer >, TT >
      getTimeAndValueOfEffect( Effect effect, Boolean timeFirst ) {
    // REVIEW -- Why not use <T>? Can't enforce it?
    if ( !( effect instanceof EffectFunction ) ) {
      return null;
    }
    EffectFunction effectFunction = (EffectFunction)effect;
    if ( effectFunction == null || effectFunction.getMethod() == null ) {
      if ( Debug.isOn() ) Debug.errln( getName()
                                       + ".getTimeAndValueOfEffect(Effect="
                                       + effect
                                       + ") called with no effect method! "
                                       + this );
      return null;
    }
    if ( effectFunction.getMethod().getParameterTypes().length < 2 ) {
      Debug.error( getName() + ".getTimeAndValueOfEffect(Effect=" + effect
                   + ") Error! Method takes "
                   + effectFunction.getMethod().getParameterTypes().length
                   + " parameters, but 2 are required." );
      return null;
    }

    if ( effectFunction.arguments == null || effectFunction.arguments.size() < 2 ) {
      Debug.error( getName() + ".getTimeAndValueOfEffect(Effect=" + effect
                   + ") Error! Method has "
                   + effectFunction.getMethod().getParameterTypes().length
                   + " arguments, but 2 are required." );
      return null;
    }
    boolean complainIfNotTimepoint = timeFirst != null;
    
    timeFirst = ( timeFirst == null ) || timeFirst;
    Parameter< Integer > tp = null;
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
    
    if ( isTimepoint(arg1) ) {
      return new Pair< Parameter< Integer >, TT >( tp, value );
    }
    
    Pair< Object, Parameter< Integer >> p =
        whichIsTheTimepoint( arg1, arg2, timeFirst && complainIfNotTimepoint,
                             !timeFirst && complainIfNotTimepoint, timeFirst );
    if ( p == null ) {
      return null;
    }
    tp = p.second;
    Object tpArg = ( p.first == arg1 ) ? arg1 : arg2;
    Object valueArg = ( p.first == arg1 ) ? arg2 : arg1;
    value = (TT)tryEvaluateValue( valueArg, true );
    Pair< Parameter< Integer >, TT > pair =
        new Pair< Parameter< Integer >, TT >( tp, value );
    if ( value == null ) {
      if ( valueArg != null ) {
        Parameter< Integer > otp = tryEvaluateTimepoint( valueArg, true, true );
        if ( otp != null ) {
          TT ov = (TT)tryEvaluateValue( tpArg, true );
          Debug.err("Looks like timepoint and value are reversed in call to getTimeAndValueOfEffect( Effect "
                           + effect+ ", " + "Boolean " + timeFirst + ") = "
                           + pair );
        }
      }
    }
    return pair;        
  }

  
  public Integer getFirstParameterOfType( Method method, Object clsOrObj ) {
    if ( clsOrObj instanceof Class ) {
      Class<?> cls = (Class<?>)clsOrObj;
      Integer pos = getIndexOfFirstParameterOfType( method, cls );
      if ( pos != null && pos >= 0 ) {
        return pos;
      }
    }
    return null;  // TODO -- HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  }

  public Integer getIndexOfFirstParameterOfType( Method method, Class<?> cls ) {
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

  public Integer getIndexOfFirstTimepointParameter( Method method ) {
    Parameter<Integer> p = new Parameter< Integer >("objForCls", null, this );
    Class< Parameter<Integer> > cls = (Class< Parameter< Integer >>)p.getClass();
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

  public Integer getIndexOfFirstTimepointParameter( EffectFunction effectFunction ) {
    return getIndexOfFirstTimepointParameter( effectFunction.getMethod() );
  }

  public static boolean testGetFirstTimepointParameter() {
    boolean debugWasOn = Debug.isOn();
    if ( !debugWasOn ) Debug.turnOn();
    
    boolean succ = true;
    TimeVaryingMap tvm = new TimeVaryingMap< Integer >("imap", null, 0, Integer.class);
    EffectFunction f =
        new EffectFunction( tvm,
                            TimeVaryingMap.getSetValueMethod(),
                            new Object[] { new Parameter< Integer >( "four",
                                                                     null, tvm ),
                                           new Integer( 14 ) } );
    assert ( 0 == tvm.getIndexOfFirstTimepointParameter( f ) );
    Method.setAccessible( TimeVaryingMap.class.getDeclaredMethods(), true );
    for ( Method method : TimeVaryingMap.class.getDeclaredMethods() ) {
      f = new EffectFunction( tvm, method );
      Debug.outln( "method " + method.getName()
                   + " has first timepoint parameter at position "
                   + tvm.getIndexOfFirstTimepointParameter( f ) );
      Class<?> clss = ParameterListenerImpl.class;
      Class< ? >[] pTypes = method.getParameterTypes();
      if ( pTypes == null || pTypes.length <= 0 ) {
        return false;
      }
      for ( Integer i = 0; i < pTypes.length; ++i ) {
        Class<?> pType = pTypes[i];
        if ( pType == null ) continue;
        //if ( !clss.isAssignableFrom( pType ) ) continue;
        TypeVariable< ? >[] gTypes = pType.getTypeParameters();
        if ( gTypes == null ) continue;
        for ( TypeVariable< ? > typeVar : gTypes ) {
          if ( typeVar == null ) continue;
          Debug.outln( "method=" + method.getName() + ", parameter type="
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
    protected Parameter<Integer> tp = null;
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
    public CandidateTimepoint( Object o, Parameter< Integer > tp ) {
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
    public Parameter< Integer > getTimepoint() {
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
    public void setTp( Parameter< Integer > tp) {
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
        Debug.err( "Warning! Trying to replace existing tp with o." );
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
  public Pair< Object, Parameter< Integer > > whichIsTheTimepoint( Object o1,
                                                                   Object o2,
                                                                   boolean o1ShouldBe,
                                                                   boolean o2ShouldBe,
                                                                   boolean o1WinsTie ) {
    // check for null values
    if ( o1 == null && o2 == null ) return null;
    if ( o1 != null && o2 == null ) {
      return new Pair< Object, Parameter< Integer >>(o1, tryEvaluateTimepoint( o1, true ));
    }
    if ( o1 == null && o2 != null ) {
      return new Pair< Object, Parameter< Integer >>(o2, tryEvaluateTimepoint( o2, true ));
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
    Pair< Object, Parameter< Integer > > p =
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
        return new Pair< Object, Parameter< Integer > >( preferred.getObject(),
                                                         preferred.getTimepoint() );
      }
    } else {
      V valNonPref = tryEvaluateValue( preferredObj, true );
      if ( valNonPref != null ) {
        return new Pair< Object, Parameter< Integer > >( nonPreferred.getObject(),
                                                         nonPreferred.getTimepoint() );
      }
    }
    
    if ( preferred.isATimepoint() ) {
      return new Pair< Object, Parameter< Integer > >( preferred.getObject(),
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
  protected Pair< Object, Parameter< Integer > >
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
  protected Pair< Object, Parameter< Integer > >
      selectTimepoint( CandidateTimepoint pref, CandidateTimepoint nonPref,
                       boolean strongPreference, boolean checkIfTimepoint,
                       boolean checkIfInDomain ) {
    if ( pref == null || nonPref == null ) return null;
    // check whether the candidates are found to be timepoints (Parameter<Integer>)
    if ( checkIfTimepoint ) {
      if ( pref.isATimepoint()
           && ( strongPreference || !nonPref.isATimepoint() ) ) {
        return new Pair< Object, Parameter< Integer >>( pref.getObject(),
                                                        pref.getTimepoint() );
      }
      if ( !strongPreference && nonPref.isATimepoint() ) { // !isPrefTp
        return new Pair< Object, Parameter< Integer >>( nonPref.getObject(),
                                                        nonPref.getTimepoint() );
      }
    }
    boolean bothAreTimepoints = pref.isATimepoint() && nonPref.isATimepoint();
    if ( bothAreTimepoints  ) {
      // check if an actual Timepoint (as opposed to just Parameter<Integer>)
      if ( strongPreference && pref.getTimepoint() instanceof Timepoint
           && !( nonPref.getTimepoint() instanceof Timepoint ) ) {
        return new Pair< Object, Parameter< Integer >>( pref.getObject(),
                                                        pref.getTimepoint() );
      }
      // check if value is a legal time value 
      if ( checkIfInDomain ) {
        if ( pref.isInDomain() && ( strongPreference || !nonPref.isInDomain() ) ) {
          return new Pair< Object, Parameter< Integer >>( pref.getObject(),
                                                          pref.getTimepoint() );
        }
        if ( !strongPreference && nonPref.isInDomain() ) { // !isPrefTp is known
          return new Pair< Object, Parameter< Integer >>(
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
    boolean isIntParam = p.getValueNoPropagate() instanceof Integer;
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
  public boolean isTimeArgFirst( Effect effect ) {
    // TODO!
    return true;
    // uncomment below!!!
//    Integer ti = effect.whichArgIsTimepoint();
//    return ti == 0 ;
  }
  public boolean isApplied( Effect effect, Method method1, Method method2 ) {
    breakpoint();
    if ( Debug.isOn() ) isConsistent();
    if ( isArithmeticEffect( effect ) ) {
      return appliedSet.contains(effect); // HACK! We have a problem here! We can't know if it's
                   // applied unless we keep track of all effects here!
    }

    Pair< Parameter<Integer>, V > p = getTimeAndValueOfEffect( effect );//, method1, method2 ); //, timeArgFirst );
    if ( p == null ) return false;
    Object t = p.first;
    V value = p.second;
    if ( value != null ) {
      if ( t instanceof Parameter
           && ( (Parameter<?>)t ).getValueNoPropagate() instanceof Integer ) {
        return hasValueAt( value, tryCastTimepoint( t ) );
      } if ( t instanceof Integer ) {
        return hasValueAt( value, (Integer)t );
      }
    }
    return false;
  }
  
  /**
   * Parses a value from a string of the same type as the map value, V. 
   * @param s
   * @param type
   * @return
   */
  public V valueFromString( String s ) {
    V value = null;
    if ( type == Double.class || type == double.class ) {
      value = type.cast( Double.parseDouble( s ) );
    } else if ( type == Float.class || type == float.class ) {
      value = type.cast( Float.parseFloat( s ) );
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
        objs.add( Integer.parseInt( s ) );
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
  
  public void fromStringMap( Map<String,String> map, Class<V> cls ) {
    clear();
    for ( Entry<String, String> ss : map.entrySet() ) {
      Integer key = null;
      try {
        key = Integer.parseInt( ss.getKey() );
      } catch (Exception e) {
        key = (int)Double.parseDouble( ss.getKey() );
      }
      Timepoint tp = new Timepoint( null, key, this );
      V value = valueFromString( ss.getValue() );
      setValue( tp, value );
    }
  }
  
  public void fromString( String s, Class<V> cls ) {
    Map<String,String> map = new HashMap<String,String>();
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
    Map<String,String> map = new HashMap<String,String>();
    MoreToString.Helper.fromString( map, s, "", "\\s*", "", "", "\\s*,\\s*", "" );
    fromStringMap( map, cls );
  }
  
  public void fromCsvFile( String fileName ) throws FileNotFoundException {
    fromCsvFile( fileName, null );
  }
  public void fromCsvFile( String fileName, Class<V> cls ) throws FileNotFoundException {
    File f = FileUtils.findFile( fileName );
    String s = FileUtils.fileToString( f );
    Map<String,String> map = new HashMap<String,String>();
    MoreToString.Helper.fromString( map, s, "", "\\s+", "", "", "[ ]*,[ ]*", "" );
    fromStringMap( map, cls );
    Debug.outln( "read map from file, " + fileName + ":\n" + this.toString() );
  }
  public void toCsvFile( String fileName ) {
    String s = toCsvString();
    Debug.outln( "wrote map to file, " + fileName + ":\n" + s );
    FileUtils.stringToFile( s, fileName );
  }
  
  public String toCsvString() {
    StringBuffer sb = new StringBuffer();
    for ( java.util.Map.Entry< Parameter< Integer >, V > e : entrySet() ) {
      sb.append( e.getKey().toShortString() + ","
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
  public < T > boolean pickValue( Variable< T > variable ) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int getId() {
    return id;
  }
  @Override
  public int hashCode() {
    return id;
  }

  /**
   * @return the type
   */
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

  public Number getMinValue() {
    return getMinOrMaxValue( true );
  }
  public Number getMaxValue() {
    return getMinOrMaxValue( false );
  }
  public Number getMinOrMaxValue( boolean isMin ) {
    if ( !ClassUtils.isNumber( getType() ) ) return null;
    boolean isInt = ClassUtils.isInteger( getType() );
    Integer minInt = Integer.MAX_VALUE;
    Double minDouble = Double.MAX_VALUE;
    int mul = isMin ? 1 : -1;
    for ( V v : values() ) {
      if ( v != null &&  v instanceof Number ) {
        if ( isInt ) {
          Integer i = ((Number)v).intValue();
          if ( mul * i.intValue() < mul * minInt.intValue() ) {
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
    for ( Map.Entry< Parameter< Integer >, V > e : entrySet() ) {
      if ( e.getKey() == null || e.getKey().getValue(false) == null ) continue;
      int startTime = e.getKey().getValue(false).intValue();
      String q = isNum ? "" : "\"";
      Object value = Expression.evaluate( e.getValue(), null, false );
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

  public Map< Method, Integer > getEffectMethodsMap() {
    if ( effectMethods == null ) effectMethods = initEffectMethods();
    return effectMethods;
  }

  @Override
  public Collection< Method > getEffectMethods() {
    Map< Method, Integer > m = getEffectMethodsMap();
    if ( m == null ) return null;
    return effectMethods.keySet();
  }

  @Override
  public boolean doesAffect( Method method ) {
    return getEffectMethods().contains( method );
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
    TimeVaryingMap< Integer > intMap1 =
        new TimeVaryingMap< Integer >( "integer_map", fileName1, Integer.class );
    System.out.println( "map1 loaded from " + fileName1 + ":\n" + intMap1 );
    TimeVaryingMap< Double > doubleMap2 =
        new TimeVaryingMap< Double >( "double_map", fileName2, Double.class );

    Assert.assertTrue( intMap1.isConsistent() );
    Assert.assertTrue( doubleMap2.isConsistent() );
    
    System.out.println( "\nmap2 loaded from " + fileName2 + ":\n" + doubleMap2 );
    intMap1.multiply( 2, intMap1.firstKey(), null );
    System.out.println( "\nmap1 multiplied by 2:\n" + intMap1 );
    TimeVaryingMap< Double > doubleMap3 = doubleMap2.plus( 12.12 );
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

    TimeVaryingMap< Integer > intMap4 = null;
    System.out.println( "map1:\n" + intMap1);
    intMap4 = intMap1.dividedBy( 2.0 );
    System.out.println( "\nmap4 = map1 / 2.0:\n" + intMap4);
    
    Assert.assertTrue( intMap1.isConsistent() );
    try {
      Assert.assertTrue( intMap4.isConsistent() ); // TODO -- THIS CURRENTLY FAILS!
    } catch ( AssertionFailedError e ) {
      System.err.println("Caught assertion failure and continuing.");
    }

    intMap1.clear();
    doubleMap2.clear();
    doubleMap3.clear();
    intMap4.clear();
    TimeVaryingMap< Integer > intMap5 = null;
    TimeVaryingMap< Double > doubleMap6 = null;
    
    // some timepoints to use
    Timepoint zero = new Timepoint( "zero", 0, null );
    Timepoint one = new Timepoint( "one", 1, null );
    Timepoint two = new Timepoint( "two", 2, null );
    Timepoint four = new Timepoint( "four", 4, null );
    Timepoint eight = new Timepoint( "eight", 8, null );
    
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

    Assert.assertTrue( doubleMap2.isConsistent() );
    Assert.assertTrue( doubleMap3.isConsistent() );
    Assert.assertTrue( doubleMap6.isConsistent() );


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

    Assert.assertTrue( doubleMap2.isConsistent() );
    Assert.assertTrue( doubleMap3.isConsistent() );
    Assert.assertTrue( doubleMap6.isConsistent() );
    
  }

  @Override
  public boolean canBeApplied( Effect effect ) {
    // checks to see if the timepoint is valid
    if ( effect instanceof EffectFunction ) {
      EffectFunction effectFunction = (EffectFunction)effect;
      Parameter< Integer > t = getTimeOfEffect( effectFunction );
      return isTimepoint( t );
    }
    return true;
  }

  public boolean wasApplied( Effect effect ) {
    return !appliedSet.add(effect);
  }

}
