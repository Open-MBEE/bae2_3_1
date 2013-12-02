/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.util.ClassUtils;
import gov.nasa.jpl.ae.util.Debug;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 */
public class Consumable extends TimeVaryingPlottableMap< Double > {

  /**
   * 
   */
  private static final long serialVersionUID = 6930722265307957963L;
  
  private static Map< Method, Integer > effectMethods = Consumable.initEffectMethods();
  private static Method addMethod = getAddMethod();
  private static Method setValueMethod = getSetValueMethod();

  protected Double minCap = Double.NEGATIVE_INFINITY;
  protected Double maxCap = Double.POSITIVE_INFINITY;
  
  
  /**
   * @param name
   */
  public Consumable( String name ) {
    super( name );
  }

  /**
   * @param name
   * @param defaultValue
   */
  public Consumable( String name, Double defaultValue, boolean projected ) {
    super( name, null, defaultValue, Double.class, projected );
  }

  public Consumable( String name, Double initialValue,
                     Method deltaValueFunction, Object o,
                     int samplePeriod, int horizonDuration,
                     Double minCap, Double maxCap ) {
//  super( name, initialValueFunction, o, samplePeriod, horizonDuration );
    super( name, null, initialValue, Double.class, false );
    this.minCap = minCap;
    this.maxCap = maxCap;
    samplePeriod =
        TimeVaryingMap.correctSamplePeriod( samplePeriod, horizonDuration );
    try {
      int lastT = 0;
      double lastValue = initialValue; 
      if ( Debug.isOn() ) Debug.errln("minCap=" + minCap + "; maxCap=" + maxCap );
      for ( int t = samplePeriod; t < horizonDuration; t += samplePeriod ) {
      double value =
          (Double)ClassUtils.runMethod( false, o, deltaValueFunction, lastT,
                                        lastValue, t ).second;
        lastValue = add( makeTempTimepoint( t, false ), value );
        lastT = t;
      }
	  } catch ( IllegalArgumentException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  /**
   * @param name
   * @param deltaValueFunction
   * @param o
   * @param samplePeriod
   * @param horizonDuration
   */
  public Consumable( String name, Double initialValue,
                     Method deltaValueFunction, Object o,
                     int samplePeriod, int horizonDuration) {
    this(name, initialValue, deltaValueFunction, o, samplePeriod, horizonDuration,
         Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
  }
  
  public double add( Parameter<Integer> t, Double delta ) {
    Double valBefore = getValueBefore( t );
    setValue( t, valBefore );  // we're going to add delta to this below.
    SortedMap< Parameter<Integer>, Double > tail = this.tailMap( t );
    // Had trouble changing the value while iterating through the tail map, so
    // keys are copied to a list, and setValue() is called while walking the list.
    // So, this is nlogn when it should be n.
    List< Parameter<Integer> > laterTimes = new ArrayList< Parameter<Integer> >( tail.keySet() );
    for ( Parameter<Integer> tt : laterTimes ) {
      setValue( tt, getValue( tt ) + delta );
    }
    double valueSet = getValue( t );
    return valueSet;
  }

  /**
   * Clear this map and generate values from a deltaMap. A deltaMap is a map
   * whose values are interpreted to be changes from previous values. A
   * (t,delta) entry in the delta map is translated to a (t,sum) entry in this
   * map where sum = Sum_{all t' in keys() where t' <= t}(deltaMap.get(t')). For
   * any Consumable c, c.initializeFromDeltaMap(c.getDeltaMap()) should not
   * change the entries in c.
   * 
   * @param deltaMap
   */
  public void initializeFromDeltaMap( TimeVaryingMap< Double > deltaMap ) {
    clear();
//    double summedValue = 0.0;
    for ( java.util.Map.Entry< Parameter<Integer>, Double > e : deltaMap.entrySet() ) {
//      if ( e.getValue() != null && e.getValue() != 0 ) {
//        summedValue += e.getValue();
//      }
      add( e.getKey(), e.getValue() ); //summedValue );
    }
  }
  
  /**
   * @return a generated map of the changes in values from the previous point.
   *         <p>
   *         For example, the deltaMap of { 0=0.0, 3=4.4, 7=10.0 } is { 0=0.0,
   *         3=4.4, 7=5.6 }. The map is fully computed on each call. For any
   *         Consumable c, c.initializeFromDeltaMap(c.getDeltaMap()) should not
   *         change the entries in c.
   */
 public TimeVaryingMap< Double > getDeltaMap() {
    TimeVaryingPlottableMap< Double > deltaMap =
      new TimeVaryingPlottableMap< Double >( "delta_" + name );
    Double lastValue = 0.0;
    for ( Entry< Parameter<Integer>, Double > e : entrySet() ) {
      Double thisValue = 0.0; // null value is interpreted as 0.0
      if ( e.getValue() != null ) {
        thisValue = e.getValue();
      }
      deltaMap.put( e.getKey(), thisValue - lastValue );
      lastValue = thisValue;
    }
    return deltaMap;
  }
  
  public Double getValueBefore( Parameter<Integer> t ) {
    Parameter<Integer> justBeforeTime = getTimepointBefore( t );
    Double valBefore = new Double(0);
    if ( justBeforeTime != null ) {
      valBefore = get( justBeforeTime );
    }
    return valBefore;
  }
  
  /**
   * Treat this map as a deltaMap and add a deltaMap to it to represent the sum
   * of the two.
   * 
   * @param otherDeltaMap
   */
  public static TimeVaryingMap< Double >
      deltaMapPlusEquals( TimeVaryingMap< Double > deltaMap,
                          TimeVaryingMap< Double > otherDeltaMap ) {
    for ( Entry< Parameter<Integer>, Double > e : otherDeltaMap.entrySet() ) {
      Double v = deltaMap.get( e.getKey() );
      if ( v == null ) v = 0.0;
      if ( e.getValue() != null ) v += e.getValue();
      deltaMap.put( e.getKey(), v );
    }
    return deltaMap;
  }

  public Double getDeltaBetween( Timepoint t1, Timepoint t2 ) {
    return getValue(t2) - getValue(t1);
  }
  
  /**
   * @return the minCap
   */
  public Double getMinCap() {
    return minCap;
  }

  /**
   * @param minCap the minCap to set
   */
  public void setMinCap( Double minCap ) {
    this.minCap = minCap;
  }

  /**
   * @return the maxCap
   */
  public Double getMaxCap() {
    return maxCap;
  }

  /**
   * @param maxCap the maxCap to set
   */
  public void setMaxCap( Double maxCap ) {
    this.maxCap = maxCap;
  }

  /**
   * @return the Method for addMethod() 
   */
  public static Method getAddMethod() {
    if ( addMethod  == null ) {
      for ( Method m : TimeVaryingMap.class.getMethods() ) {
        if ( m.getName().equals("setValue") ) {
          addMethod = m;
        }
      }
    }
    return addMethod;
  }

  /**
   * @return the Method for addMethod() 
   */
  public static Method getSetValueMethod() {
    if ( setValueMethod  == null ) {
      for ( Method m : TimeVaryingMap.class.getMethods() ) {
        if ( m.getName().equals("setValue") ) {
          setValueMethod = m;
          break;
        }
      }
    }
    assert setValueMethod != null;
    return setValueMethod;
  }

  @Override
  public boolean isApplied( Effect effect ) {
    if ( !( effect instanceof EffectFunction ) ) {
      return false;
    }
    if ( super.isApplied( effect ) ) return true;
    EffectFunction effectFunction = (EffectFunction)effect;
    if ( effectFunction.method.equals( getAddMethod() ) ) {
      if ( effectFunction.arguments != null && effectFunction.arguments.size() >= 2 ) {
        Timepoint t = (Timepoint)effectFunction.arguments.get( 0 );
        Object o = effectFunction.arguments.get( 1 );
        Double value = null;
        try {
          value = (Double)o;
        } catch( Exception e ) {
          e.printStackTrace();
        }
        if ( value != null ) {
          Parameter<Integer> justBeforeTime = this.lowerKey( t );
          Double valBefore = new Double(0);
          if ( justBeforeTime != null ) {
            valBefore = get( justBeforeTime );
          }
          return value.equals( getValue( t ) - valBefore );
        }
      }
    }
    return false;
  }

  // As is, setValue() effectively is an add of the difference with the prior
  // map value.  It might be smart to somehow disallow the use of this in effects.
  @Override
  public Double setValue( Parameter< Integer > t, Double value ) {
    if ( value == null ) return null;
    if ( minCap != null && maxCap != null ) {
      assert minCap <= maxCap;
    }
    if ( minCap != null && value < minCap ) {
      if ( Debug.isOn() ) Debug.errln("hit minCap!");
      value = minCap;
    }
    if ( maxCap != null && value > maxCap ) {
      if ( Debug.isOn() ) Debug.errln("hit maxCap!");
      value = maxCap;
    }
    if ( value < 0.0 ) {
      if ( Debug.isOn() ) Debug.errln("why negative?!");
    }
    return super.setValue( t, value );
//    if ( Debug.isOn() ) Debug.errln( "Error! Ignoring attempt to call setValue() on " + this + "!" );
//    return super.getValue( t );
  }
  
  protected static Map< Method, Integer > initEffectMethods() {
    // copy to avoid polluting the superclass's list
    effectMethods = new TreeMap<Method,Integer>(methodComparator);
    effectMethods.putAll( TimeVaryingMap.initEffectMethods()) ;
    Method m = getAddMethod();
    if ( m != null ) effectMethods.put( m, 0 );
    effectMethods.remove( TimeVaryingMap.getSetValueMethod() );
    m = Consumable.getSetValueMethod();
    if ( m != null ) effectMethods.put( m, 0 );
    return effectMethods;
  }

  // This looks the same as parent's getEffectMethods(), but it uses its own
  // effectMethods and initEffectMethods(). So, DO NOT DELETE.
  @Override
  public Collection< Method > getEffectMethods() {
    if ( effectMethods == null ) initEffectMethods();
    return effectMethods.keySet();
  }



}
