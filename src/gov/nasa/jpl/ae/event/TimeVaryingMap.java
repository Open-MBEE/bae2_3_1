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

import junit.framework.Assert;

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

  protected static Collection< Method > effectMethods = initEffectMethods();

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
  public TimeVaryingMap( String name, String fileName, Class<V> type ) {
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

  public TimeVaryingMap( String name, V defaultValue, Class<V> type ) {
    super(new TimeComparator());
    this.name = name;
    this.type = type;
    // REVIEW -- consider forcing all constructors to provide non-null type
    if ( this.type == null && defaultValue != null ) {
      setType( defaultValue.getClass() );
    }
    Parameter<Integer> t = new Parameter<Integer>(null,null, 0, this);
    put( t, defaultValue );
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
        setValue( makeTempTimepoint( t, false ),//new Parameter<Integer>( "", t, this ),
                  tryCastValue( v ) );
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
    return this.lowerKey( tp );
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
    V v = get( t ); //.first;
    if ( Debug.isOn() ) isConsistent();
    if ( v != null ) return v;
    // Saving this check until later in case a null time value is acceptable,
    // and get(t) above works.
    if ( t.getValue( false ) == null ) return null;
    if ( valuesEqualForKeysOk ) {
      return getValue( t.getValue( false ) );
    }
    if ( interpolation.type == Interpolation.STEP ) {
      return getValueBefore( t );
    } else if ( interpolation.type == Interpolation.NONE ) {
      return null;
    } else if ( interpolation.type == Interpolation.LINEAR ) {
      Parameter<Integer> t1 = getTimepointBefore( t );
      Parameter<Integer> t2 = getTimepointAfter( t );
      V v1 = get( t1 );
      if ( t1.valueEquals( t2 ) ) return v1;
      V v2 = get( t2 );
      if ( v1 == null ) return null;
      if ( v2 == null ) return v1;
      // floorVal+(ceilVal-floorVal)*(key-floorKey)/(ceilKey-floorKey)
      v = plus( v1, dividedBy( times( minus( v2, v1 ), minus( t, t1 ) ),
                                      minus( t2, t1 ) ) );
      return v;
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
    if ( v != null ) return value.equals( v );
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
      e.setValue( times(e.getValue(), n ) );
    }
    return this;
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
  
  /**
   * @param n the number by which the map is divided
   * @return a copy of the map whose values are each divided by {@code n}
   */
  public TimeVaryingMap<V> dividedBy( Number n ) {
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
      e.setValue( dividedBy(e.getValue(), n ) );
    }
    return this;
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
  
  // TODO -- THESE ARE ALL DEFINED IN FUNCTIONS!  ALSO CHECK DOMAIN!
  public static <V1, V2> V1 times( V1 o1, V2 o2 ) {
    return Functions.times( o1, o2 );
/*    Number result = null;
    Number n1 = Expression.evaluate( o1, Number.class, false );
    Number n2 = Expression.evaluate( o2, Number.class, false );
    if ( n1 != null && n2 != null ) {
      if ( n1 instanceof Double || n2 instanceof Double ) {
        result = n1.doubleValue() * n2.doubleValue();
      } else {
        // TODO -- what if they are longs?
        result = n1.intValue() * n2.intValue();
      }
    }
    return (V1)result;
*/  }
  
  public static <V1, V2> V1 minus( V1 o1, V2 o2 ) {
    return Functions.minus( o1, o2 );
//    return plus( o1, times( -1, o2 ) );
  }
  public static <V1, V2> V1 plus( V1 o1, V2 o2 ) {
    return Functions.plus( o1, o2 );
/*    Number result = null;
    Number n1 = Expression.evaluate( o1, Number.class, false );
    Number n2 = Expression.evaluate( o2, Number.class, false );
    if ( n1 != null && n2 != null ) {
      if ( n1 instanceof Double || n2 instanceof Double ) {
        result = n1.doubleValue() + n2.doubleValue();
      } else {
        // TODO -- what if they are longs?
        result = n1.intValue() + n2.intValue();
      }
    }
    return (V1)result;
*/  }
  
  public static <V1, V2> V1 dividedBy( V1 o1, V2 o2 ) {
    return Functions.divide( o1, o2 );
/*    Number result = null;
    Number n1 = Expression.evaluate( o1, Number.class, false );
    Number n2 = Expression.evaluate( o2, Number.class, false );
    if ( n1 != null && n2 != null ) {
      if ( n1 instanceof Double || n2 instanceof Double ) {
        if ( n2.doubleValue() == 0 ) {
          return (V1)o1.getClass().cast( Double.NaN );
        }
        result = n1.doubleValue() / n2.doubleValue();
      } else {
        if ( n2.doubleValue() == 0 ) {
          return (V1)o1.getClass().cast( Integer.MAX_VALUE );
        }
        // TODO -- what if they are longs?
        result = n1.intValue() / n2.intValue();
      }
    }
    return (V1)result;
*/  }

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
  
  public Parameter<Integer> getKey( Parameter< Integer > tt, boolean equalValuesOk ) {
    if ( containsKey( tt ) ) return tt;
    if ( !equalValuesOk ) return null;
    Parameter< Integer > bk = getTimepointAfter(tt);
    if ( bk != null && bk.equals( tt ) ) {
      return bk;
    }
    bk = getTimepointBefore( tt );
    if ( bk != null && bk.equals( tt ) ) {
      return bk;
    }
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
   *          include the last key, pass {@code null} for {@code toKey}.
   * @return this map after adding {@code n} to each value in the range [{@code fromKey},
   *         {@code toKey})
   */
  public TimeVaryingMap< V > add( Number n, Parameter< Integer > fromKey,
                                   Parameter< Integer > toKey ) {
    
    if ( n == null) return this; //REVIEW
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
    for ( Map.Entry< Parameter< Integer >, V > e : map.entrySet() ) {
      if ( e.getValue() == null ) {
        try {
          e.setValue( tryCastValue( n ) );
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Double ) {
        Double v = (Double)e.getValue();
        v = v + n.doubleValue();
        try {
          e.setValue( tryCastValue( v ) );
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      } else if ( e.getValue() instanceof Integer ) {
        Integer v = (Integer)e.getValue();
        // TODO -- handle Long?
        v = (int)( v + n.doubleValue() );
        try {
          e.setValue( tryCastValue( v ) );
        } catch ( ClassCastException exc ) {
          exc.printStackTrace();
        }
      }
    }
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
      if ( n != null && n.doubleValue() != 0 ) {
        add( n, k, k );
      }
    }
    return removeDuplicates();
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
    return add( times(n, -1), fromKey, toKey );
  }
  
  /**
   * @param tvm
   *          the {@code TimeVaryingMap} to be subtracted from this
   *          {@code TimeVaryingMap}
   * @return this {@code TimeVaryingMap} after subtracting {@code tvm}
   */
  public <VV> TimeVaryingMap< V > subtract( TimeVaryingMap< VV > tvm ) {
    Set< Parameter< Integer > > keys =
        new TreeSet< Parameter< Integer > >( Collections.reverseOrder() );
    keys.addAll( this.keySet() );
    keys.addAll( tvm.keySet() );
    for ( Parameter< Integer > k : keys ) {
      VV v = tvm.getValue( k, false );
      Number n = Expression.evaluate( v, Number.class, false );
      if ( n != null && n.doubleValue() != 0 ) {
        subtract( n, k, k );
      }
    }
    return removeDuplicates();
  }
  
  /**
   * @return this map after removing all but one entry from each set of adjacent
   *         entries with the same values.
   */
  public <VV> TimeVaryingMap< V > removeDuplicates() {
    List<Parameter< Integer > > dups = new ArrayList< Parameter< Integer > >();
    Parameter<Integer> lastKey = null;
    for ( java.util.Map.Entry< Parameter< Integer >, V > e : entrySet() ) {
      Parameter< Integer > key = e.getKey();
      if ( Utils.valuesEqual( lastKey, key ) ) {
        dups.add( lastKey );
      }
      lastKey = key;
    }
    for ( Parameter<Integer> k : dups ) {
      remove( k );
    }
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
    Pair< Parameter<Integer>, V > p = 
        getTimepointAndValueOfEffect( effect,
                                      getSetValueMethod(),
                                      getSetValueMethod() );
    if ( p != null ) {
      unsetValue( p.first, p.second );
    }
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
      return ( compareTo( (TimeVarying<V>)o ) == 0 );
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
  
  public <TT> Pair< Parameter<Integer>, TT > getTimepointAndValueOfEffect( Effect effect,
                                                                           Method method1,
                                                                           Method method2 ) {
    Pair< Object, TT > p1 = getTimeAndValueOfEffect( effect, method1, method2 );
    if ( p1 == null ) return null;
    Object t = p1.first;//effectFunction.arguments.get( 0 );
    TT value = p1.second;
    Parameter<Integer> tp = null;
    if ( t instanceof Integer ) {
      tp = makeTempTimepoint( (Integer)t, true );
    } else if ( t instanceof Parameter ) {
      tp = tryCastTimepoint( t );
    } else {
      System.err.println( "Effect(" + effect + ") has wrong arguments to "
                          + method1.getName() + " for " + this );
      return null;
    }
    Pair< Parameter< Integer >, TT > p2 =
        new Pair< Parameter< Integer >, TT >( tp, value );
    return p2;
  }

  public <TT> Pair< Object, TT > getTimeAndValueOfEffect( Effect effect,
                                                          Method method1,
                                                          Method method2 ) {
    if ( !( effect instanceof EffectFunction ) ) {
      return null;
    }
    EffectFunction effectFunction = (EffectFunction)effect;
    if ( effectFunction == null || effectFunction.getMethod() == null ) {
      if ( Debug.isOn() ) Debug.errln( getName() + ".getTimeAndValueOfEffect(Effect="
                   + effect + ", Method=" + method1 + ", Method=" + method2
                   + ") called with no effect method! " + this );
      return null;
    }
    boolean isMethod1 = effectFunction.getMethod().equals(method1);
    boolean isMethod2 =  effectFunction.getMethod().equals( method2);
    if ( isMethod1  || isMethod2 ) {
      if ( effectFunction.arguments != null
           && effectFunction.arguments.size() >= 2 ) {
        Object t = effectFunction.arguments.get( 0 );
        Object o = effectFunction.arguments.get( 1 );
        TT value = null;
        try {
          value = (TT)o;
        } catch( ClassCastException e ) {
          // ignore
        }
        return new Pair< Object, TT >( t, value ); 
      }
    }
    return null;
  }
  
  @Override
  public boolean isApplied( Effect effect ) {
    return isApplied(effect, getSetValueMethod(), getSetValueMethod() );
  }

  public boolean isApplied( Effect effect, Method method1, Method method2 ) {
    breakpoint();
    if ( Debug.isOn() ) isConsistent();
    Pair< Object, V > p = getTimeAndValueOfEffect( effect, method1, method2 );
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
      put( tp, value );
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

  @Override
  public Collection< Method > getEffectMethods() {
    if ( effectMethods == null ) effectMethods = initEffectMethods();
    return effectMethods;
  }

  @Override
  public boolean doesAffect( Method method ) {
    return getEffectMethods().contains( method );
  }

  protected static Collection< Method > initEffectMethods() {
    effectMethods = new HashSet<Method>();
    Method m = getSetValueMethod();
    if ( m != null ) effectMethods.add( m );
    //m = getSetValueMethod2();
    //m = TimeVaryingMap.class.getMethod("unsetValue");
    //m = TimeVaryingMap.class.getMethod("unapply");
    return effectMethods;
  }

  // TODO -- make this a JUnit
  public static void main( String[] args ) {
    String fileName1 = "integerTimeline.csv";
    String fileName2 = "aggregateLoad.csv";
    TimeVaryingMap< Integer > tvm1 =
        new TimeVaryingMap< Integer >( "integer_map", fileName1, Integer.class );
    System.out.println( "map1 loaded from " + fileName1 + ":\n" + tvm1 );
    TimeVaryingMap< Double > tvm2 =
        new TimeVaryingMap< Double >( "double_map", fileName2, Double.class );

    Assert.assertTrue( tvm1.isConsistent() );
    Assert.assertTrue( tvm2.isConsistent() );
    
    System.out.println( "\nmap2 loaded from " + fileName2 + ":\n" + tvm2 );
    tvm1.multiply( 2, tvm1.firstKey(), null );
    System.out.println( "\nmap1 multiplied by 2:\n" + tvm1 );
    TimeVaryingMap< Double > tvm3 = tvm2.plus( 12.12 );
    System.out.println( "\nnew map3 = map2 plus 12.12:\n" + tvm3 );
    tvm3 = tvm2.times( 1111, tvm2.firstKey(), tvm2.lastKey() );
    System.out.println( "\nmap3 = map2 times 1111 (except for the last entry):\n" + tvm3 );
    tvm3 = tvm2.times( 1111, tvm2.lastKey(), tvm2.lastKey() );
    System.out.println( "\nmap3 = map2 times 1111 (for just the last entry):\n" + tvm3 );

    Assert.assertTrue( tvm1.isConsistent() );
    Assert.assertTrue( tvm2.isConsistent() );
    Assert.assertTrue( tvm3.isConsistent() );
    
    tvm3.add( tvm1 );
    System.out.println( "\nmap3 = map3 + map1:\n" + tvm3);
    tvm3.divide( 0.5 );
    System.out.println( "\nmap3 /= 0.5:\n" + tvm3);
    System.out.println( "map2:\n" + tvm2);
    tvm3 = tvm2.dividedBy( 2.0 );
    System.out.println( "\nmap3 = map2 / 2.0:\n" + tvm3);
    System.out.println( "map2:\n" + tvm2);
    tvm3 = tvm2.dividedBy( 2 );
    System.out.println( "\nmap3 = map2 / 2:\n" + tvm3);
    System.out.println( "map2:\n" + tvm2);
    
    Assert.assertTrue( tvm1.isConsistent() );
    Assert.assertTrue( tvm2.isConsistent() );
    Assert.assertTrue( tvm3.isConsistent() );

    TimeVaryingMap< Integer > tvm4 = null;
    System.out.println( "map1:\n" + tvm1);
    tvm4 = tvm1.dividedBy( 2.0 );
    System.out.println( "\nmap4 = map1 / 2.0:\n" + tvm4);
    
    Assert.assertTrue( tvm1.isConsistent() );
    Assert.assertTrue( tvm4.isConsistent() ); // TODO -- THIS CURRENTLY FAILS!

    System.out.println( "map1:\n" + tvm1);
    tvm4 = tvm1.dividedBy( 2 );
    System.out.println( "\nmap4 = map1 / 2:\n" + tvm4);
    System.out.println( "map1:\n" + tvm1);

    Assert.assertTrue( tvm1.isConsistent() );
    Assert.assertTrue( tvm4.isConsistent() );
  
  }

}
