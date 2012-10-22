package gov.nasa.jpl.ae.util;

public interface StopWatch< T > {

  public abstract void reset();

  /**
   * @return total time between starts and stops 
   */
  public abstract T stop();

  public abstract void start();

  /**
   * @return time since StopWatch reset()
   */
  public abstract T getTime();

//  public abstract T getTimeSinceStart(); // getTime will return this

  public abstract T getTimeSinceLastStart();

  public abstract T getTimeSinceLastStop();

  public abstract T getTimeOfLastClock();

  public abstract T getTotalTime();

}