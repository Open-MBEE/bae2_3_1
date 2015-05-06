/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.mbee.util.Random;
import gov.nasa.jpl.ae.solver.RangeDomain;

/**
 * @author bclement
 *
 */
public class TimeDomain extends IntegerDomain {

  public static IntegerDomain horizonDomain =
      new IntegerDomain( 0, Timepoint.getHorizonDuration() );
  /**
   * 
   */
  public TimeDomain() {
    super(horizonDomain);
  }

  /**
   * @param minValue
   * @param maxValue
   */
  public TimeDomain( int minValue, int maxValue ) {
    super( minValue, maxValue );
  }

  /**
   * @param domain
   */
  public TimeDomain( RangeDomain< Integer > domain ) {
    super( domain );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.IntegerDomain#pickRandomValue()
   */
  @Override
  public Integer pickRandomValue() {
    if ( getLowerBound() < Timepoint.getHorizonDuration() &&
         getUpperBound() > Timepoint.getHorizonDuration() ) {
      double r1 = Random.global.nextDouble();
      if ( r1 < 0.5 ) {
        return pickRandomValueLessThan( Timepoint.getHorizonDuration() );
      }
    }
    return super.pickRandomValue();
  }
}
