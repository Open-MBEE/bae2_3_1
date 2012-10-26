/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.StringDomain;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import junit.framework.Assert;

/**
 * 
 * TimeVaryingMap is a {@link TreeMap} for implementing {@link TimeVarying},
 * mapping a {@link Timepoint} to {@link EffectInstance}s (although the map
 * value has a different encoding, {@link TimeValue}). It is also
 * implements {@link ParameterListener} in order to maintain {@link TreeMap}
 * consistency. It "floats" entries before its {@link Timepoint} changes to
 * protect the data structure and reinserts the entry after the
 * {@link Timepoint} has changed.
 * 
 */
public class TimeVaryingMap< T > extends TreeMap< Timepoint, T >
                                 implements TimeVarying< T >,
                                            ParameterListener {
                                            //Comparable< TimeVaryingMap< T > > {

  private static final long serialVersionUID = -2428504938515591538L;

  public class TimeValue extends Pair< Timepoint, T >
                               implements HasParameters {

    public TimeValue( Timepoint t, T v ) {
      super( t, v );
    }
    
    @Override
    public boolean isStale() {
      return HasParameters.Helper.isStale( this, false, null );
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
      return HasParameters.Helper.getParameters( this, deep, null );
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
      return HasParameters.Helper.isFreeParameter( this, parameter, deep, seen );
    }

    @Override
    public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                                 Set< HasParameters > seen ) {
      Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
      if ( pair.first ) return false;
      seen = pair.second;
      //if ( Utils.seen( this, deep, seen ) ) return false;
      return HasParameters.Helper.hasParameter( this, parameter, deep, seen );
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
      return HasParameters.Helper.substitute( this, p1, p2, deep, seen );
    }

  }

  /**
   * For the convenience of referring to the effect method.
   */
  protected static Method setValueMethod1 = getSetValueMethod1();
  protected static Method setValueMethod2 = getSetValueMethod2();
  /**
   * Floating effects are those whose time or duration is changing. They must be
   * removed from TimeVaryingMap's map before they change; else, they will
   * corrupt the map. Before changing, they are placed in this floatingEffects
   * list, and after changing they are removed from this list and added back to
   * the map.
   */
  protected List< TimeValue > floatingEffects = new ArrayList< TimeValue >();

  protected String name;
  
  /**
   * 
   */
  public TimeVaryingMap( String name ) {
    super();
    this.name = name;
  }

  public TimeVaryingMap( String name, T defaultValue ) {
    super();
    this.name = name;
    Timepoint t = new Timepoint(null, 0, this);
    //System.out.println(name + " put(" + t + ", " + defaultValue + ")" );
    put( t, defaultValue );
    if ( Debug.isOn() ) isConsistent();
  }

  @SuppressWarnings( "unchecked" )
  public TimeVaryingMap( String name, Method initialValueFunction,
                         Object o, int samplePeriod, int horizonDuration ) {
    super();
    this.name = name;
    samplePeriod = correctSamplePeriod( samplePeriod, horizonDuration );
    try {
      for ( int t = 0; t < horizonDuration; t += samplePeriod ) {
        // WARNING: creating Timepoint with no owner in order to avoid
        // unnecessary overhead with constraint processing. If modified while in
        // the map, it can corrupt the map.
        setValue( makeTempTimepoint( t, false ),//new Timepoint( "", t, this ),
                  (T)initialValueFunction.invoke( o, t ) );
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

  protected void breakpoint() {}

  public Timepoint getTimepointBefore( Timepoint t ) {
    if ( t == null ) return null;
    return this.lowerKey( t );
  }

  public Timepoint getTimepointAfter( Timepoint t ) {
    if ( t == null ) return null;
    return this.higherKey( t );
  }

  public T getValueBefore( Timepoint t ) {
    Timepoint justBeforeTime = getTimepointBefore( t );
    T valBefore = null;
    if ( justBeforeTime != null ) {
      valBefore = get( justBeforeTime );
    }
    return valBefore;
  }
  

  public Timepoint makeTempTimepoint( Integer t, boolean maxName ) {
    //if ( t == null ) return null;
    String n = ( maxName ? StringDomain.typeMaxValue : null );
    Timepoint tp = new Timepoint( n, t, null );
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
    if ( parameter instanceof Timepoint ) {
      unfloatEffects( (Timepoint)parameter );
    }
  }

  protected void floatEffects( Timepoint t ) {
    breakpoint();
    if ( t == null ) return;
    assert t.getValueNoPropagate() != null;
    if ( !containsKey( t ) ) return;
    T value = get( t );
    if ( Debug.isOn() ) Debug.out( getName() + ": floating effect, (" + t + ", " + value + ")" );
    floatingEffects.add( new TimeValue( t, value ) );
//    if ( effects != null ) {
//      if ( effects.second != null ) {
//        for ( Effect e : effects.second ) {
//          EffectInstance r = new EffectInstance( t, 1, e );
//          floatingEffects.add( r );
//        }
//      }
//      // REVIEW -- Should we check to see if the T value is a parameter and do
//      // an owner.handleValueChangeEvent()
//      // if ( effects.first != null ) {
//      // if ( effects.first instanceof ParameterListener ) {
//      // ((ParameterListener)effects.first).handleValueChangeEvent( ? )
//      // Make sure the value of the timeline is stales
//      if ( effects.first != null && effects.first instanceof LazyUpdate ) {
//        ( (LazyUpdate)effects.first ).setStale( true );
//      }
//    }
    remove( t );
    if ( Debug.isOn() ) isConsistent();
  }

  protected void unfloatEffects( Timepoint t ) {
    breakpoint();
    if ( t == null ) return;
    if ( t.getValueNoPropagate() == null ) return;
    ArrayList<TimeValue> copy = new ArrayList<TimeValue>( floatingEffects );
    for ( TimeValue e : copy ) {
      if ( e.first.compareTo( t ) == 0 ) { // REVIEW -- Do we need to use
                                           // compareTo instead of equals
                                           // elsewhere?
        put( e.first, e.second );
        if ( Debug.isOn() ) Debug.out( getName() + ": unfloated effect, " + e );
      }
      floatingEffects.remove( e );
    }
    if ( Debug.isOn() ) isConsistent();
//    for ( EffectInstance effectInstance : floatingEffects ) {
//      assert effectInstance.startTime != null;
//      if ( effectInstance.startTime.compareTo( t ) == 0 ) {
//        assert effectInstance.effect != null;
//        effectInstance.applyTo( this );
//      }
//    }
//    floatingEffects.remove( t );
  }

  @Override
  public void handleDomainChangeEvent( Parameter< ? > parameter ) {
    if ( parameter instanceof Timepoint ) {
      unfloatEffects( (Timepoint)parameter );
    }
  }

  @Override
  public String getName() {
    if ( name != null && !name.isEmpty() ) return name;
    return getClass().getSimpleName();
  }

  public void setName( String newName ) {
    if ( newName == null || newName.isEmpty() ) {
      Formatter formatter = new Formatter(Locale.US);
      newName = getClass().getSimpleName();
    }
    this.name = newName;
  }

  // Add startTimes, durations, values that are Parameters, and (if deep)
  // parameters of effects.
  @Override
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set< HasParameters > seen ) {
    //return Utils.getEmptySet();
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< Parameter< ? > > params = new HashSet< Parameter< ? > >();
    params.addAll( HasParameters.Helper.getParameters( this, deep, seen ) );
    params.addAll( HasParameters.Helper.getParameters( floatingEffects, deep, seen ) );
    return params;
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
                                 Set< HasParameters > seen) {
    Assert.assertTrue( "This method is not supported!", false );
  }
  

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                               Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    if ( HasParameters.Helper.hasParameter( this, parameter, deep, seen ) ) {
      return true;
    }
    if ( HasParameters.Helper.hasParameter( floatingEffects, parameter, deep, seen ) ) {
      return true;
    }
    return false;
  }

  @Override
  public boolean
      substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep,
                  Set< HasParameters > seen ) {
    breakpoint();
    if ( HasParameters.Helper.substitute( this, p1, p2, deep, seen ) ) {
      if ( Debug.isOn() ) isConsistent();
      return true;
    }
    if ( HasParameters.Helper.substitute( floatingEffects, p1, p2, deep, seen ) ) {
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
    return ( HasParameters.Helper.isStale( this, false, null ) );
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
      floatEffects( (Timepoint)changedParameter );
    } else {
      if ( Debug.isOn() ) Debug.outln( getName() + ".setStaleAnyReferencesTo(" + changedParameter + "): does not contain" );
    }
    if ( Debug.isOn() ) isConsistent();
    // TODO -- REVIEW -- should we float EffectInstances that have the parameter
    // and set a stale flag in EffeectInstances?
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
   * @see gov.nasa.jpl.ae.event.TimeVarying#getValue(gov.nasa.jpl.ae.event.Timepoint)
   */
  @Override
  public T getValue( Timepoint t ) {
    if ( t == null ) return null;
    T v = get( t ); //.first;
    if ( Debug.isOn() ) isConsistent();
    if ( v != null ) return v;
    // Saving this check until later in case a null time value is acceptable,
    // and get(t) above works.
    if ( t.getValue() == null ) return null;
    return getValue( t.getValue() );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.TimeVarying#getValue(java.lang.Integer)
   */
  @Override
  public T getValue( Integer t ) {
    if ( t == null ) return null;
    Timepoint tp = makeTempTimepoint( t, true );
    Entry< Timepoint, T > e = this.floorEntry( tp );
    if ( Debug.isOn() ) isConsistent();
    if ( e != null ) return e.getValue();
//  if ( !isEmpty() && firstEntry().getKey().getValue() <= t ) {
//    return firstEntry().getValue();
//  }
    if ( !isEmpty() ) {
      Entry< Timepoint, T > f = firstEntry(); 
      Timepoint k = f.getKey();
      if ( IntegerDomain.defaultDomain.lessEquals( k.getValue(), t ) ) {
        return f.getValue();
      }
    }
    return null;
  }

  /**
   * Return the timepoint1 for an entry (timepoint1, value1) such that
   * timepoint1.value equals tp.value, and value1 equals value.
   * 
   * @param value
   *          the value to match with a value in the map
   * @param tp
   *          the time value required for the returned key
   * @return a Timepoint key in the map, whose time value equals tp's value and
   *         whose map entry equals value or null if there is no such Timepoint.
   */
  public boolean hasValueAt( T value, Timepoint tp ) {
    if ( tp == null ) return false;
    T v = get( tp ); //.first;
    if ( value == v ) return true;
    if ( v != null ) return value.equals( v );
    // Saving this check until later in case a null time value is acceptable,
    // and get(t) above works.
    if ( tp.getValue() == null ) return false;
    return hasValueAt( value, tp.getValueNoPropagate() );
  }
  
  /**
   * Return the timepoint1 for an entry (timepoint1, value1) such that
   * timepoint1.value equals t, and value1 equals value.
   * 
   * @param value
   *          the value to match with a value in the map
   * @param t
   *          the time value required for the returned key
   * @return a Timepoint key in the map, whose time value equals t and whose map
   *         entry equals value or null if there is no such Timepoint.
   */
  public Timepoint keyForValueAt( T value, Integer t ) {
    if ( t == null ) return null;
    Timepoint tp = makeTempTimepoint( t, false );//new Timepoint( null, t, null );
    Entry< Timepoint, T > e = this.floorEntry( tp );
    Timepoint startKey = null;
    if ( e != null ) {
      startKey = e.getKey();
    } else if ( !isEmpty() ) {
      startKey = firstEntry().getKey();
    } else {
      return null;
    }
    NavigableMap< Timepoint, T > tailMap = this.tailMap( startKey, true );
    for ( java.util.Map.Entry< Timepoint, T > te : tailMap.entrySet() ) {
      Object mVal = te.getValue();
      if ( Utils.valuesEqual( value, mVal ) &&
          TimeDomain.defaultDomain.equals( t, te.getKey().getValueNoPropagate() ) ) {
        return te.getKey();
      }
      if ( TimeDomain.defaultDomain.greater( te.getKey().getValueNoPropagate(), t ) ) break;
    }
//    } else if ( !isEmpty() ) {
//      Object mVal = firstEntry().getValue();
//      if ( ( ( value == null && mVal == null ) || ( value != null && value.equals( mVal ) ) ) &&
//          t.equals( firstEntry().getKey().getValueNoPropagate() ) ) {
//        return firstEntry().getKey();
//      }
//    }
    return null;
  }
  
  /**
   * @param value
   * @param t
   * @return
   */
  public boolean hasValueAt( T value, Integer t ) {
    return keyForValueAt( value, t ) != null;
  }

//  /**
//   * Inserts the value in the map if there is not 
//   * @param t
//   * @param value
//   * @return
//   */
//  public T setValue( Integer t, T value ) {
//    breakpoint();
//    if ( t == null ) {
//      if ( Debug.isOn() ) Debug.error( true, "Error! trying to insert a null Timepoint into the map" );
//      return null;
//    }
//    T oldValue = null;
//    Timepoint tp = keyForValueAt( value, t );
//    if ( Debug.isOn() ) isConsistent();
//    if ( tp == null ) {
//      tp = makeTempTimepoint( t, false );//new Timepoint( "", t, null );
//      oldValue = put( tp, value );
//      if ( Debug.isOn() ) isConsistent();
//    }
//    return oldValue;
//  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.TimeVarying#setValue(gov.nasa.jpl.ae.event.Timepoint, java.lang.Object)
   */
  @Override
  public T setValue( Timepoint t, T value ) {
    breakpoint();
    if ( t == null ) {
      if ( Debug.isOn() ) Debug.error( true, "Error! trying to insert a null Timepoint into the map" );
      return null;
    }
    T oldValue = null;
    Timepoint tp = keyForValueAt( value, t.getValue() );
    if ( Debug.isOn() ) isConsistent();
    if ( tp != null && tp != t ) {
      remove( tp );
    }
    if ( tp != t ) {
      oldValue = put( t, value );
    }
    if ( Debug.isOn() ) isConsistent();
    return oldValue;
  }

  /**
   * Validate the consistency of the map for individual and adjacent entries.
   * @return whether or not the entries in the map make sense.
   */
  public boolean isConsistent() {
    Timepoint lastTp = null;
    int lastTime = -1;
    T lastValue = null;
    boolean ok = true;
    ArrayList<T> valuesAtSameTime = new ArrayList< T >();
    boolean firstEntry = true;
    for ( Map.Entry< Timepoint, T >  entry : entrySet() ) {
      Timepoint tp = entry.getKey();
      T value = null;
      try {
        value = entry.getValue();
      } catch ( ClassCastException cce ) {
        ok = false;
        System.err.println( "Error! Value " + entry.getValue() 
                            + " has the wrong type in TimeVaryingMap! "
                            + this );
        cce.printStackTrace();
      }
      int time = -1;
      boolean timepointValueChanged = true;
      boolean valueChanged = true;
      boolean duplicateValueAtTime = false;
      // Check for problems with the key.
      // No null keys.
      if ( tp == null ) {
        if ( Debug.isOn() ) {
          Debug.error( true, "Error! null key in TimeVaryingMap " + getName() );
        }
        ok = false;
      } else if ( tp.getValueNoPropagate() == null ) {
        // No null values for Timepoint key.  
        if ( Debug.isOn() ) Debug.errorOnNull( true, "Error! null timepoint value in TimeVaryingMap "
                                 + getName(), tp.getValueNoPropagate() );
        ok = false;
      } else {
        timepointValueChanged = !tp.getValueNoPropagate().equals( lastTime );
        if ( !firstEntry && tp.getValueNoPropagate() < lastTime ) {
          // Time cannot decrease.
          if ( Debug.isOn() ) Debug.error( true, "Error! time value for entry " + entry
                             + " should be >= " + lastTime
                             + " in TimeVaryingMap " + getName() );
          ok = false;
        } else {
          lastTime = tp.getValueNoPropagate();
        }
      }
      if ( timepointValueChanged ) {
        valuesAtSameTime.clear();
      }
      duplicateValueAtTime = valuesAtSameTime.contains( value );
      valueChanged = value != lastValue; 
      if ( tp != null && tp == lastTp ) {
        // A key should have only one entry.
        if ( Debug.isOn() ) Debug.error( true, "Error! Timepoint has duplicate entry " + entry
                     + " in TimeVaryingMap " + getName() );
        ok = false;
      }
      
      // Check for problems with the value.
      if ( !firstEntry && duplicateValueAtTime ) {
        if ( Debug.isOn() ) Debug.error( true, "Error! duplicate entry of value " + value
                     + " at time " + tp + " with value set "
                     + valuesAtSameTime + " for TimeVaryingMap "
                     + getName() );
        ok = false;
      } else if ( !firstEntry && !valueChanged ) {
        if ( Debug.isOn() ) Debug.error( false, "Warning! value " + value
                           + " repeated for adjacent entry " + entry + " at time "
                           + tp + " for TimeVaryingMap " + this );
      }
      lastTp = tp;
      lastValue = value;
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
  public T unsetValue( Timepoint t, T value ) {
    breakpoint();
    if ( t == null ) {
      if ( Debug.isOn() ) Debug.error( false, "Warning! unsetValue(" + t + ", " + value
                          + "): Null Timepoint key for TimeVaryingMap " + this );
      return null;
    }
    if ( t.getValueNoPropagate() == null ) {
      if ( Debug.isOn() ) Debug.error( false,
                   "Warning! unsetValue(" + t + ", " + value
                       + "): Null value for Timepoint key for TimeVaryingMap "
                       + this );
      return null;
    }
    T oldValue = null;
    Timepoint tpInMap = keyForValueAt( value, t.getValueNoPropagate() );
    if ( tpInMap != t ) {
      if ( Debug.isOn() ) Debug.error( false, "Warning! unsetValue(" + t + ", " + value 
                          + "): Timepoint key is not in the map for TimeVaryingMap "
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
    Pair< Timepoint, T > p = 
        getTimepointAndValueOfEffect( effect,
                                      getSetValueMethod1(),
                                      getSetValueMethod1() );
    if ( p != null ) {
      unsetValue( p.first, p.second );
    }
  }
  
  @Override
  public void detach( Parameter< ? > parameter ) {
    if ( parameter instanceof Timepoint ) {
      remove( (Timepoint)parameter );
    }
    for ( Entry< Timepoint, T > e : ( (TimeVaryingMap< T >)this.clone() ).entrySet() ) {
      if ( e.getValue() instanceof HasParameters ) {
        if ( ( (HasParameters)e.getValue() ).hasParameter( parameter, false,
                                                           null ) ) {
          detach( e.getKey() );
        }
      }
    }
  }


  @Override
  public boolean isFreeParameter( Parameter< ? > parameter, boolean deep,
                                  Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    return HasParameters.Helper.isFreeParameter( this, parameter, deep, seen );
  }

  @Override
  public boolean equals( Object o ) {
    if ( this == o ) return true;
    if ( o instanceof TimeVarying ) {
      return ( compareTo( (TimeVarying< T >)o ) == 0 );
    }
    return false;
  }
  
  @Override
  public int compareTo( TimeVarying< T > o ) {
    if ( o == null ) return 1;
    int compare = 0;
    if ( o instanceof TimeVaryingMap ) {
      TimeVaryingMap<?> otvm = (TimeVaryingMap)o;
      compare = Utils.compareTo( getName(), otvm.getName() );
      if ( compare != 0 ) return compare;
    }
    Debug.err( "Warning: TimeVaryingMap.compareTo() may compare values, which, if changed while this is in a map, can corrupt the map." );
    compare = Utils.compareTo( this, o ); // WARNING: values change!!!
    if ( compare != 0 ) return compare;
    return compare;
  }
  
  public static Method getSetValueMethod1() {
    if ( setValueMethod1 == null ) {
      for ( Method m : TimeVaryingMap.class.getMethods() ) {
        if ( m.getName().equals("setValue") && m.getParameterTypes() != null
             && m.getParameterTypes().length == 2 
             && m.getParameterTypes() [0] == Timepoint.class ) {
          setValueMethod1 = m;
        }
      }
    }
    return setValueMethod1;
  }
  
  public static Method getSetValueMethod2() {
    if ( setValueMethod2 == null ) {
      for ( Method m : TimeVaryingMap.class.getMethods() ) {
        if ( m.getName().equals("setValue") && m.getParameterTypes() != null
             && m.getParameterTypes().length == 2 
             && m.getParameterTypes() [0] == Integer.class) {
          setValueMethod2 = m;
        }
      }
    }
    return setValueMethod2;
  }

  public <TT> Pair< Timepoint, TT > getTimepointAndValueOfEffect( Effect effect,
                                                                  Method method1,
                                                                  Method method2 ) {
    Pair< Object, TT > p1 = getTimeAndValueOfEffect( effect, method1, method2 );
    if ( p1 == null ) return null;
    Object t = p1.first;//effectFunction.arguments.get( 0 );
    TT value = p1.second;
    Timepoint tp = null;
    if ( t instanceof Integer ) {
      tp = makeTempTimepoint( (Integer)t, true );
    } else if ( t instanceof Timepoint ) {
      tp = (Timepoint)t;
    } else {
      System.err.println( "Effect(" + effect + ") has wrong arguments to "
                          + method1.getName() + " for " + this );
      return null;
    }
    Pair< Timepoint, TT > p2 = new Pair( tp, value );
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
      if ( effectFunction.arguments != null && effectFunction.arguments.size() >= 2 ) {
        Object t = effectFunction.arguments.get( 0 );
        Object o = effectFunction.arguments.get( 1 );
        TT value = null;
        try {
          value = (TT)o;
        } catch( Exception e ) {
          //e.printStackTrace();
        }
        return new Pair< Object, TT >( t, value ); 
      }
    }
    return null;
  }
  
  @Override
  public boolean isApplied( Effect effect ) {
    return isApplied(effect, getSetValueMethod1(), getSetValueMethod1()//getSetValueMethod2()
                     );
  }
  // FIXME -- TODO -- should check to see if value matches!!!
  public boolean isApplied( Effect effect, Method method1, Method method2 ) {
    breakpoint();
    if ( Debug.isOn() ) isConsistent();
    Pair< Object, T > p = getTimeAndValueOfEffect( effect, method1, method2 );
    if ( p == null ) return false;
    Object t = p.first;
    T value = p.second;
    if ( value != null ) {
      if ( t instanceof Timepoint ) {
        return hasValueAt( value, (Timepoint)t );
      } if ( t instanceof Integer ) {
        return hasValueAt( value, (Integer)t );
      }
    }
    return false;
  }
  
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append( this.getName() );
    sb.append( super.toString() );
    return sb.toString();
  }

  @Override
  public < T > boolean pickValue( Variable< T > variable ) {
    // TODO Auto-generated method stub
    return false;
  }

}
