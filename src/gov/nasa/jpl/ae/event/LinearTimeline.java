/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.util.Debug;

import java.lang.reflect.Method;
import java.util.Map.Entry;

/**
 * LinearTimeline is a TimeVaryingMap of Doubles that interpolates values
 * linearly with time between entries. TimeVaryingMap uses the last value seen
 * and is, thus, a step function.
 * 
 */
public class LinearTimeline extends TimeVaryingPlottableMap< Double > {

  /**
   * 
   */
  private static final long serialVersionUID = -4707739577094130809L;


  /**
   * @param name
   */
  public LinearTimeline( String name ) {
    super( name );
  }

  /**
   * @param name
   * @param defaultValue
   */
  public LinearTimeline( String name, Double defaultValue ) {
    super( name, defaultValue );
  }

  /**
   * @param name
   * @param initialValueFunction
   * @param o
   * @param samplePeriod
   * @param horizonDuration
   */
  public LinearTimeline( String name, Method initialValueFunction, Object o,
                         int samplePeriod, int horizonDuration ) {
    super( name, initialValueFunction, o, samplePeriod, horizonDuration );
  }

  @Override
  public Double getValue( Integer t ) {
    if ( t == null ) return null;
    if ( Debug.isOn() ) isConsistent();
    Timepoint tp = makeTempTimepoint( t, true );
    Entry< Timepoint, Double > eBefore = this.floorEntry( tp );
    if ( eBefore == null ) return null;
    Entry< Timepoint, Double > eAfter = this.ceilingEntry( tp );
    if ( eAfter == null ) return eBefore.getValue();
    double timeDiff =
        eAfter.getKey().getValue( false ) - eBefore.getKey().getValue( false );
    assert timeDiff >= 0.0;
    double valueDiff = 0.0;
    if ( eAfter.getValue() != null ) {
      if ( eBefore.getValue() == null ) {
        valueDiff = eAfter.getValue();
      } else {
        valueDiff = eAfter.getValue() - eBefore.getValue();
      }
    }
    double interpolatedValue = 0.0;
    if ( eBefore.getValue() == null ) {
      interpolatedValue = valueDiff; 
    } else {
      interpolatedValue =
        eBefore.getValue() + 
        valueDiff * ( t - eBefore.getKey().getValue( false ) ) / timeDiff;
    }
    return interpolatedValue;
  }

  
//  /**
//   * @param args
//   */
//  public static void main( String[] args ) {
//    // TODO Auto-generated method stub
//
//  }

}
