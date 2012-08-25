/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.event.TimeVaryingMapWithEffects.ValueAndEffects;
import gov.nasa.jpl.ae.solver.StringDomain;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Map.Entry;

import junit.framework.Assert;

/**
 * 
 * TimeVaryingMap is a {@link TreeMap} for implementing {@link TimeVarying},
 * mapping a {@link Timepoint} to {@link EffectInstance}s (although the map
 * value has a different encoding, {@link ValueAndEffects}). It is also
 * implements {@link ParameterListener} in order to maintain {@link TreeMap}
 * consistency. It "floats" entries before its {@link Timepoint} changes to
 * protect the data structure and reinserts the entry after the
 * {@link Timepoint} has changed.
 * 
 * @author bclement
 * 
 */
public class TimeVaryingMapWithEffects< T >
  extends TreeMap< Timepoint, TimeVaryingMapWithEffects< T >.ValueAndEffects > 
  //extends TreeMap< Integer, TimeVaryingMap< T >.ValueAndEffects >
  implements TimeVarying< T >, ParameterListener {

  private static final long serialVersionUID = -2428504938515591538L;

  // TODO -- Simplify this by getting rid of effect set. Just need value since
  // it's instantaneous--should only be one setValue for a Timepoint.
  // TODO -- Create constraint(s) that the value can only be set once at any
  // time. If implementing Constraint, the constraint needs to be added to those
  // of the parent event/class.
  public class ValueAndEffects extends Pair< T, Set< Effect > > //Set< EffectInstance > >
                               implements HasParameters {

    public ValueAndEffects( T v, Set< Effect > m ) {
      super( v, m );
    }

    @Override
    public boolean isStale() {
      return HasParameters.Helper.isStale( this, false );
    }

    @Override
    public void setStale( boolean staleness ) {
      Assert.assertTrue( "This method is not supported!", false );
    }

    @Override
    public Set< Parameter< ? > > getParameters( boolean deep ) {
      return HasParameters.Helper.getParameters( this, deep );
    }

    @Override
    public Set< Parameter< ? > > getFreeParameters( boolean deep ) {
      Assert.assertTrue( "This method is not supported!", false );
      return null;
    }

    @Override
    public void setFreeParameters( Set< Parameter< ? >> freeParams ) {
      Assert.assertTrue( "This method is not supported!", false );
    }
    
    @Override
    public boolean isFreeParameter( Parameter< ? > parameter, boolean deep ) {
      // TODO -- REVIEW -- not sure about this
      if ( HasParameters.Helper.isFreeParameter( first,
                                               parameter, deep ) ) {
        return true;
      }
      return HasParameters.Helper.isFreeParameter( second, parameter, deep );
    }

    @Override
    public boolean hasParameter( Parameter< ? > parameter, boolean deep ) {
      if ( HasParameters.Helper.hasParameter( first,
                                            parameter, deep ) ) {
        return true;
      }
      return HasParameters.Helper.hasParameter( second, parameter, deep );
    }

    @Override
    public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep ) {
      boolean subbed = false;
      if ( first == p1 ) {
        first = (T)p2;
        subbed = true;
      }
      if ( HasParameters.Helper.substitute( second, p1, p2, deep ) ) {
        subbed = true;
      }
      return subbed;
    }

  }
  // Floating effects are those whose time or duration is changing. They must be
  // removed from TimeVaryingMap's map before they change; else, they will
  // corrupt the map. Before changing, they are placed in this floatingEffects
  // list, and after changing they are removed from this list and added back to
  // the map.
  protected List< EffectInstance > floatingEffects = new ArrayList< EffectInstance >();

  /**
   * 
   */
  public TimeVaryingMapWithEffects() {
    super();
  }

//  public boolean hasParameter( ValueAndEffects valueAndEffects,
//                               Parameter< ? > parameter, boolean deep ) {
//    boolean found = false;
//    if ( valueAndEffects != null ) {
//      if ( HasParameters.Util.hasParameter( valueAndEffects.first,
//                                            parameter, deep ) ) {
//        found = true;
//      }
//    }
//    Map< Duration, Effect > durationEffectMap = valueAndEffects.second;
//    if ( !found && durationEffectMap != null ) {
//      for ( Map.Entry< Duration, Effect > me : durationEffectMap.entrySet() ) {
//        if ( me.getKey() == parameter ) {
//          found = true;
//          break;
//        } else if ( HasParameters.Util.hasParameter( me.getValue(),
//                                                     parameter, deep ) ) {
//          found = true;
//          break;
//        }
//      }
//    }
//    return found;
//  }
  public TimeVaryingMapWithEffects<T> effectsWithParameter( Parameter< ? > parameter ) {
    TimeVaryingMapWithEffects<T> effectMap = new TimeVaryingMapWithEffects<T>();
    for ( Map.Entry< Timepoint, ValueAndEffects > e : this.entrySet() ) {
      boolean found = false;
      if ( e.getKey().compareTo( parameter ) == 0 ) {
        found = true;
      } else {
        found = HasParameters.Helper.hasParameter( e.getValue(), parameter, false );
      }
      if ( found ) {
        effectMap.put( e.getKey(), e.getValue() );
      }
    }
    return effectMap;
  }
  
  @Override
  public void handleValueChangeEvent( Parameter< ? > parameter ) {
    for ( EffectInstance e : floatingEffects ) {
      if ( e.hasParameter( parameter, true ) ) {
        e.applyTo( this, true );
        floatingEffects.remove( e );
      }
    }
  }

  protected void floatEffects( Timepoint t ) {
    ValueAndEffects effects = get( t );
    if ( effects != null ) {
      if ( effects.second != null ) {
        for ( Effect e : effects.second ) {
          EffectInstance r = new EffectInstance( t, 1, e );
          floatingEffects.add( r );
        }
      }
      // REVIEW -- Should we check to see if the T value is a parameter and do an owner.handleValueChangeEvent()
//      if ( effects.first != null ) {
//        if ( effects.first instanceof ParameterListener ) {
//        ((ParameterListener)effects.first).handleValueChangeEvent( ? )
      // Make sure the value of the timeline is stales
      if ( effects.first != null && effects.first instanceof LazyUpdate ) {
        ((LazyUpdate)effects.first).setStale( true );
      }
    }
    remove( t );
  }
  
  protected void unfloatEffects( Timepoint t ) {
    for ( EffectInstance effectInstance : floatingEffects ) {
      assert effectInstance.startTime != null; 
      if ( effectInstance.startTime.compareTo( t ) == 0 ) {
        assert effectInstance.effect != null;
        effectInstance.applyTo( this, true );
      }
    }
    floatingEffects.remove( t );
  }

  @Override
  public void handleDomainChangeEvent( Parameter< ? > parameter ) {
    // TODO Auto-generated method stub
    Assert.assertTrue( "Not yet implemented!", false );
  }

  @Override
  public String getName() {
    return getClass().getName();
  }

  // Add startTimes, durations, values that are Parameters, and (if deep)
  // parameters of effects.
  @Override
  public Set< Parameter< ? > > getParameters( boolean deep ) {
    Assert.assertTrue( "Not yet implemented!", false );
    return null;
  }

  @Override
  public Set< Parameter< ? > > getFreeParameters( boolean deep ) {
    Assert.assertTrue( "Not yet implemented!", false );
    return null;
    // Passing self (this) to a HasParameter helper function often results in
    // infinite loop!
    //return HasParameters.Helper.getFreeParameters( this, deep );
  }

  @Override
  public void setFreeParameters( Set< Parameter< ? >> freeParams ) {
    // TODO Auto-generated method stub
    Assert.assertTrue( "Not yet implemented!", false );
  }
  
  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep ) {
    Assert.assertTrue( "Not yet implemented!", false );
    return false;
    // Passing self (this) to a HasParameter helper function often results in
    // infinite loop!
    //return HasParameters.Helper.hasParameter( this, parameter, deep );
  }
  
  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep ) {
    Assert.assertTrue( "Not yet implemented!", false );
    return false;
    // Passing self (this) to a HasParameter helper function often results in
    // infinite loop!
    //return HasParameters.Helper.substitute( this, p1, p2, deep );
  }

  @Override
  public boolean isStale() {
    if ( !floatingEffects.isEmpty() ) return true;
    Assert.assertTrue( "Not yet implemented!", false );
    return false;
    // Passing self (this) to a HasParameter helper function often results in
    // infinite loop!
    //return ( HasParameters.Helper.isStale( this, false ) );
  }

  @Override
  public void setStale( boolean staleness ) {
    Assert.assertTrue( "This method is not supported!", false );
  }

  @Override
  public void setStaleAnyReferencesTo( Parameter< ? > changedParameter ) {
    if ( containsKey( changedParameter ) ) {
      floatEffects( (Timepoint)changedParameter );
    }
    // TODO -- REVIEW -- should we float EffectInstances that have the parameter
    // and set a stale flag in EffeectInstances?
  }

  @Override
  public boolean refresh( Parameter< ? > parameter ) {
    // TODO -- REVIEW -- do nothing? owner's responsibility?
    // TODO -- TimeVaryingMap should own the values if they are Parameters and
    // maybe any Parameters the value has itself, unless the value is a
    // ParameterListener and owns its own Parameters.
    return false;
  }

  @Override
  public T getValue( Timepoint t ) {
    return get( t ).first;
  }

  @Override
  public T setValue( Timepoint t, T value ) {
    T oldValue = null;
    ValueAndEffects valueAndEffects = get( t );
    if ( valueAndEffects == null ) {
      valueAndEffects = 
          new ValueAndEffects( value, new HashSet< Effect >() );
    } else {
      // TODO -- REVIEW -- Are two different effects setting the value at the
      // same time?!
      oldValue = valueAndEffects.first;
      valueAndEffects.first = value;
    }
    if ( valueAndEffects.second == null ) {
      valueAndEffects.second = new HashSet< Effect >();
    }
    EffectFunction setEffect = null;
    try {
      Vector< Object > args = new Vector<Object>();
      args.add( value );
      Method m = this.getClass().getMethod( "setValue", 
                                            new Class[] { Timepoint.class,
                                                          value.getClass() } );
      // FIXME -- There should either be no effect stored, or the effect should
      // be passed as an argument.
      setEffect = new EffectFunction( this, m, args );
    } catch ( NoSuchMethodException | SecurityException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    valueAndEffects.second.add( setEffect );
    return oldValue;
  }

  @Override
  public T unsetValue( Timepoint t, T value ) {
    T oldValue = null;
    ValueAndEffects valueAndEffects = get( t );
    assert valueAndEffects != null;
    oldValue = valueAndEffects.first;
    assert oldValue == value;
    assert ( valueAndEffects.second == null || valueAndEffects.second.isEmpty()
             || valueAndEffects.second.size() == 1 );
    remove( t );
    return oldValue;
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > parameter, boolean deep ) {
    return HasParameters.Helper.isFreeParameter( this, parameter, deep );
  }

  @Override
  public int compareTo( TimeVarying< T > o ) {
    return Integer.compare( this.hashCode(), o.hashCode() );
    // TODO -- REVIEW -- Need this for DurativeEvents.effects map. Hash code
    // could cause a problem with small probability.
  }

  public static Method getSetValueMethod() {
    for ( Method m : TimeVaryingMapWithEffects.class.getMethods() ) {
      if ( m.getName().equals( "setValue" ) ) {
        return m;
      }
    }
    return null;
  }

  @Override
  public boolean isApplied( Effect effect ) {
    if ( !( effect instanceof EffectFunction ) ) {
      return false;
    }
    EffectFunction effectFunction = (EffectFunction)effect;
    if ( effectFunction.method == getSetValueMethod() ) {
      if ( effectFunction.arguments != null && effectFunction.arguments.size() >= 2 ) {
        Timepoint t = (Timepoint)effectFunction.arguments.get( 0 );
        Object o = effectFunction.arguments.get( 1 );
        T value = null;
        try {
          value = (T)o;
        } catch( Exception e ) {
          e.printStackTrace();
        }
        if ( value != null ) {
          return value.equals( getValue( t ) );
        }
      }
    }
    return false;
  }

  @Override
  public T getValue( int t ) {
    Timepoint tp = new Timepoint( StringDomain.typeMaxValue, t, null );
    Entry< Timepoint, ValueAndEffects > e = this.floorEntry( tp );
    if ( e != null ) return e.getValue().first;
    if ( !isEmpty() ) {
      return firstEntry().getValue().first;
    }
    return null;
  }

}
