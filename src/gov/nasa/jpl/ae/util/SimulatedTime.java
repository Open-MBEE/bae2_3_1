package gov.nasa.jpl.ae.util;

import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.mbee.util.TimeUtils;
import gov.nasa.jpl.mbee.util.TimeUtils.Units;

public class SimulatedTime {
    protected double timeScale = 1.0;
    public double simToActualConversionFactor = 1.0;
    public long actualStartMillis = -1;
    public long systemMillis;
    public long simStart = 0;
    public long actualMillisPassed = 0;
    public long actualDelta = 0;
    public long simTimePassed = 0;
    public double simDelta = 0;
    public TimeUtils.Units units = Timepoint.getUnits();
    
    public SimulatedTime( double timeScale ) {
      reset( timeScale );
    }
    public SimulatedTime( TimeUtils.Units units, double timeScale ) {
      reset( units, timeScale );
    }
    
    public void reset() {
      reset(this.timeScale);
    }
    public void reset( TimeUtils.Units units, double timeScale ) {
      this.units = units;
      reset(timeScale);
    }
    
    public void reset( double timeScale ) {
      updateTime();
      setTimeScale( timeScale );
      actualStartMillis = systemMillis;
      simStart = 0;
      actualMillisPassed = 0;
      actualDelta = 0;
      simTimePassed = 0;
      simDelta = 0;
    }
    
    public void updateTime() {
      long tmp = System.currentTimeMillis();
      actualDelta = tmp - systemMillis;
      systemMillis = tmp;
      actualMillisPassed += actualDelta;
      simDelta  = actualDelta / simToActualConversionFactor;
//      if (Debug.isOn()) Debug.out("simDelta = actualDelta("+actualDelta+") / simToActualConversionFactor("+simToActualConversionFactor+")=" + simDelta);
      simTimePassed += simDelta;
//      if (Debug.isOn()) Debug.out("simTimePassed = "+simTimePassed);
////  if ( Debug.isOn() ) Debug.outln("timePassed = " + timePassed );
////  if ( Debug.isOn() ) Debug.outln("nextEventTime = " + nextEventTime );
////  if ( Debug.isOn() ) Debug.outln("nextEventTimeScaled = " + nextEventTimeScaled );
////  if ( Debug.isOn() ) Debug.outln("waitMillis = " + waitMillis );
    }
    
    public void sleepUntilSimTime( long simTime ) throws InterruptedException {
      if ( simTime <= 0 ) return;
      updateTime();
      long actualMillisToSleep = convertSimToActualTimePassed( simTime ) - actualMillisPassed;
      if ( actualMillisToSleep > 0 ) {
        Thread.sleep( actualMillisToSleep );
      }
    }
    
    /**
     * @return the timeScale
     */
    public double getTimeScale() {
      return timeScale;
    }

    /**
     * @param timeScale the timeScale to set
     */
    public void setTimeScale( double timeScale ) {
      updateTime();
      this.timeScale = timeScale;
      simToActualConversionFactor = Timepoint.conversionFactor( TimeUtils.Units.milliseconds ) / timeScale;
    }

    public long convertSimToActualDuration( long simDuration ) {
      return (long)( simDuration * simToActualConversionFactor );
    }

    /**
     * Predicts the actual time that the simulation reached the input simTime,
     * assuming that the time scale does not change. Likewise, if the input
     * simTime is before the sim time of the last update(), then the actual time
     * returned assumes (possibly incorrectly) that the time scale did not change
     * in the past.
     * 
     * @param simTime
     * @return
     */
    public long convertSimToActualTimePassed( long simTime ) {
      // NOTE: No need to call update() here.
      if ( simTime < simStart + simTimePassed ) {
//        Debug.errln("Warning: If time scale changed, past sim time may be incorrectly translated to actual.");
      }
      long simDeltaToFutureTime = simTime - ( simStart + simTimePassed );
      return actualMillisPassed + convertSimToActualDuration( simDeltaToFutureTime );
    }

    public long getSimTimePassed() {
      updateTime();
      return simTimePassed;
    }

    public boolean passedHorizon() {
      return getSimTimePassed() > Timepoint.getHorizonDuration();
    }
    
    public long getActualMillisSinceStart() {
      updateTime();
      return actualMillisPassed;
    }
    
    public double timeToHorizon() {
      return Timepoint.getHorizonDuration() - getSimTimePassed(); 
    }
    
  }