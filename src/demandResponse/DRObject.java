/**
 * 
 */
package demandResponse;

import gov.nasa.jpl.ae.event.Duration;
import gov.nasa.jpl.ae.event.DurativeEvent;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.mbee.util.Random;
import gov.nasa.jpl.mbee.util.TimeUtils;

/**
 * @author bclement
 *
 */
public class DRObject extends DurativeEvent {
  public enum ApplianceType {
    PCT(0.0,2000.0,20,TimeUtils.Units.minutes,0.1,0.9,15,TimeUtils.Units.hours),
    refrigerator(100.0,500.0,5,TimeUtils.Units.minutes,0.01,0.15,15,TimeUtils.Units.hours),
    EV(0.0,1000.0,5,TimeUtils.Units.hours,0.0,1.0,2.5,TimeUtils.Units.hours);
    
    /**
     * @param minPower
     * @param maxPower
     * @param durationAtMax
     * @param minPercentTimeAtMax
     * @param maxPercentTimeAtMax
     * @param timeOfDayAtMaxPercent
     */
    private ApplianceType( double minPower, double maxPower,
                           double durationAtMaxVal,
                           TimeUtils.Units durationAtMaxUnits,
                           double minPercentTimeAtMax,
                           double maxPercentTimeAtMax,
                           double timeOfDayAtMaxPercentVal,
                           TimeUtils.Units timeOfDayAtMaxPercentUnits ) {
      this.minPower = minPower;
      this.maxPower = maxPower;
      this.durationAtMax = new Duration( "durationAtMax",
                                         durationAtMaxVal, durationAtMaxUnits,
                                         null );
      this.minPercentTimeAtMax = minPercentTimeAtMax;
      this.maxPercentTimeAtMax = maxPercentTimeAtMax;
      this.timeOfDayAtMaxPercent = new Duration( "timeOfDayAtMaxPercent",
                                                 timeOfDayAtMaxPercentVal,
                                                 timeOfDayAtMaxPercentUnits,
                                                 null );
    }
    double minPower, maxPower;
    Duration durationAtMax = null; //new Duration( "duration at max", durVal, durUnits, this );
    double minPercentTimeAtMax, maxPercentTimeAtMax;
    Duration timeOfDayAtMaxPercent = null;
  } 
  
  protected ApplianceType applianceType = ApplianceType.PCT;
  protected long DRStart = 5400;
  protected long DRDuration = 14400;
  protected int rampDuration = 3600;
  protected int timeOffset = Random.global.nextInt(rampDuration);
  
	/**
	 * 
	 */
	public DRObject() {
		super();
		startTime.setValue( (long)( DRStart / Timepoint.conversionFactor( TimeUtils.Units.seconds ) ) + timeOffset ); // FIXME -- HACK -- assumes seconds and not wrt epoch
		duration.setValue( (long)( DRDuration / Timepoint.conversionFactor( TimeUtils.Units.seconds ) )  + timeOffset );
		this.satisfy( true, null );
	}

  public DRObject( boolean b ) {
    this();
    timeOffset = rampDuration;
    duration.setValue( (long)( DRDuration / Timepoint.conversionFactor( TimeUtils.Units.seconds ) ) );
  }

  public double predictedLoadReduction( double t ) {
    return predictedLoadReduction( new Timepoint( "", (long)t, this ) );
  }
	public double predictedLoadReduction( Timepoint t ) {
	  if ( !t.isGrounded(false, null) || !isGrounded(false, null) ) return 0.0;
	  if ( t.getValue(false) < startTime.getValue(false) || t.getValue(false) > endTime.getValue(false) ) {
	    return 0.0;
	  }
	  long offsetTimeFromMax = applianceType.timeOfDayAtMaxPercent.getValue(false) - t.timeSinceMidnight();
	  boolean on = true;
	  long change = applianceType.durationAtMax.getValue(false) / 2;
	  long timeFromMax = change;
    final long lengthOfDay = Duration.lengthOfOne(TimeUtils.Units.days);
	  while (offsetTimeFromMax > timeFromMax ) {
	    on = !on;
      change = (long)(((long)applianceType.durationAtMax.getValue(false)) * ( ( (double)(lengthOfDay - timeFromMax ) ) / lengthOfDay ) );
	    timeFromMax += change;
	  }
	  if ( on ) return applianceType.maxPower;
	  return applianceType.minPower;
	}
}
