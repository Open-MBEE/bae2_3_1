/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.mbee.util.Debug;

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
  public LinearTimeline( String name, Double defaultValue, boolean projected ) {
    super( name, null, defaultValue, Double.class, projected );
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
  
  public LinearTimeline( LinearTimeline timeline ) {
    super(timeline);
  }
  
  @Override
  public LinearTimeline clone() {
    return new LinearTimeline( this );
  }
  
  @Override
  public LinearTimeline emptyClone() {
    LinearTimeline timeline = new LinearTimeline( getName(), null, isProjection() );
    return timeline;
  }

  @Override
  public Double getValue( Integer t ) {
    if ( t == null ) return null;
    if ( Debug.isOn() ) isConsistent();
    Parameter<Integer> tp = makeTempTimepoint( t, true );
    Entry< Parameter<Integer>, Double > eBefore = this.floorEntry( tp );
    if ( eBefore == null ) return null;
    Entry< Parameter<Integer>, Double > eAfter = this.ceilingEntry( tp );
    if ( eAfter == null ) return eBefore.getValue();
    double timeDiff =
        eAfter.getKey().getValue( false ) - eBefore.getKey().getValue( false );
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
      if ( timeDiff <= 0.0 ) {
        interpolatedValue = eBefore.getValue() + valueDiff / 2.0;
      } else {
        interpolatedValue =
            eBefore.getValue() + 
            valueDiff * ( t - eBefore.getKey().getValue( false ) ) / timeDiff;
      }
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
