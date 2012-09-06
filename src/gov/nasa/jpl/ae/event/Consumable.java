/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.util.Debug;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import junit.framework.Assert;

/**
 * @author bclement
 *
 */
public class Consumable extends TimeVaryingMap< Double > {

  /**
   * 
   */
  private static final long serialVersionUID = 6930722265307957963L;
  
  public static Method addMethod = null;

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
  public Consumable( String name, Double defaultValue ) {
    super( name, defaultValue );
  }

  public Consumable( String name, Double initialValue,
                     Method deltaValueFunction, Object o,
                     int samplePeriod, int horizonDuration,
                     Double minCap, Double maxCap ) {
//  super( name, initialValueFunction, o, samplePeriod, horizonDuration );
    super( name, initialValue );
    this.minCap = minCap;
    this.maxCap = maxCap;
    samplePeriod =
        TimeVaryingMap.correctSamplePeriod( samplePeriod, horizonDuration );
    try {
      int lastT = 0;
      double lastValue = initialValue; 
      System.err.println("minCap=" + minCap + "; maxCap=" + maxCap );
      for ( int t = samplePeriod; t < horizonDuration; t += samplePeriod ) {
        double value = (Double)deltaValueFunction.invoke( o, lastT, lastValue, t );
        lastValue = add( new Timepoint( "", t, this ), value );
        lastT = t;
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
  
  public double add( Timepoint t, Double delta ) {
    Timepoint justBeforeTime = this.lowerKey( t );
    Double valBefore = new Double(0);
    if ( justBeforeTime != null ) {
      valBefore = get( justBeforeTime );
    }
    setValue( t, valBefore ); 
    SortedMap< Timepoint, Double > tail = this.tailMap( t );
    //for ( Map.Entry< Timepoint, N > e : tail.entrySet() ) {
//  Iterator< Entry< Timepoint, Double > > iter = tail.entrySet().iterator();
//  while ( iter.hasNext() ) {
//    Entry< Timepoint, Double > e = iter.next();
//    e.setValue( e.getValue() +  delta );
//  }

    List< Timepoint > laterTimes = new ArrayList< Timepoint >( tail.keySet() );
    for ( Timepoint tt : laterTimes ) {
      setValue( tt, getValue( tt ) + delta );
    }
    double valueSet = getValue( t );
    return valueSet;
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
          Timepoint justBeforeTime = this.lowerKey( t );
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
  public Double setValue( Timepoint t, Double value ) {
    assert minCap <= maxCap;
    if ( value < minCap ) {
      System.err.println("hit minCap!");
      value = minCap;
    }
    if ( value > maxCap ) {
      System.err.println("hit maxCap!");
      value = maxCap;
    }
    if ( value < 0.0 ) {
      System.err.println("why negative?!");
    }
    return super.setValue( t, value );
//    Debug.errln( "Error! Ignoring attempt to call setValue() on " + this + "!" );
//    return super.getValue( t );
  }

}
