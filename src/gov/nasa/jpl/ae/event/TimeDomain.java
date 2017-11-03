/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.LongDomain;
import gov.nasa.jpl.mbee.util.Random;
import gov.nasa.jpl.ae.solver.RangeDomain;

/**
 * @author bclement
 *
 */
public class TimeDomain extends LongDomain {

  public static LongDomain horizonDomain =
      new LongDomain( 0, Timepoint.getHorizonDuration() );
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
  public TimeDomain( long minValue, long maxValue ) {
    super( minValue, maxValue );
  }

  /**
   * @param domain
   */
  public TimeDomain( RangeDomain< Long > domain ) {
    super( domain );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.IntegerDomain#pickRandomValue()
   */
  @Override
  public Long pickRandomValue() {
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
