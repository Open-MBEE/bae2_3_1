/**
 * 
 */
package demandResponse;

import gov.nasa.jpl.ae.event.Duration;
import gov.nasa.jpl.ae.event.DurativeEvent;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.event.Timepoint.Units;
import gov.nasa.jpl.ae.solver.Random;

/**
 * @author bclement
 *
 */
public class DRObject extends DurativeEvent {
  public enum ApplianceType {
    PCT(0.0,2000.0,20,Timepoint.Units.minutes,0.1,0.9,15,Timepoint.Units.hours),
    refrigerator(100.0,500.0,5,Timepoint.Units.minutes,0.01,0.15,15,Timepoint.Units.hours),
    EV(0.0,1000.0,5,Timepoint.Units.hours,0.0,1.0,2.5,Timepoint.Units.hours);
    
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
                           Timepoint.Units durationAtMaxUnits,
                           double minPercentTimeAtMax,
                           double maxPercentTimeAtMax,
                           double timeOfDayAtMaxPercentVal,
                           Timepoint.Units timeOfDayAtMaxPercentUnits ) {
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
  protected int DRStart = 5400;
  protected int DRDuration = 14400;
  protected int rampDuration = 3600;
  protected int timeOffset = Random.global.nextInt(rampDuration);
  
	/**
	 * 
	 */
	public DRObject() {
		super();
		startTime.setValue( (int)( DRStart / Units.conversionFactor( Units.seconds ) ) + timeOffset ); // FIXME -- HACK -- assumes seconds and not wrt epoch
		duration.setValue( (int)( DRDuration / Units.conversionFactor( Units.seconds ) )  + timeOffset );
		this.satisfy( true, null );
	}

  public DRObject( boolean b ) {
    this();
    timeOffset = rampDuration;
    duration.setValue( (int)( DRDuration / Units.conversionFactor( Units.seconds ) ) );
  }

  public double predictedLoadReduction( double t ) {
    return predictedLoadReduction( new Timepoint( "", (int)t, this ) );
  }
	public double predictedLoadReduction( Timepoint t ) {
	  if ( !t.isGrounded(false, null) || !isGrounded(false, null) ) return 0.0;
	  if ( t.getValue(false) < startTime.getValue(false) || t.getValue(false) > endTime.getValue(false) ) {
	    return 0.0;
	  }
	  int offsetTimeFromMax = applianceType.timeOfDayAtMaxPercent.getValue(false) - t.timeSinceMidnight();
	  boolean on = true;
	  int change = applianceType.durationAtMax.getValue(false) / 2;
	  int timeFromMax = change;
    final int lengthOfDay = Duration.lengthOfOne(Timepoint.Units.days);
	  while (offsetTimeFromMax > timeFromMax ) {
	    on = !on;
      change = (int)(((long)applianceType.durationAtMax.getValue(false)) * ( ( (double)(lengthOfDay - timeFromMax ) ) / lengthOfDay ) );
	    timeFromMax += change;
	  }
	  if ( on ) return applianceType.maxPower;
	  return applianceType.minPower;
	}
}
