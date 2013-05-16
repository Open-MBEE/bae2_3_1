package gov.nasa.jpl.ae.util;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class Timer implements StopWatch< Vector< Long > > {

  public class SingleTimer implements StopWatch< Long > {
    /**
     * The sum total of time between stops and starts since the last reset()
     */
    protected long total;
    
    /**
     * The time at the last reset. 
     */
    protected long start;
    
    /**
     * The time at the last start. 
     */
    protected long lastStart;
    
    /**
     * The time at the last stop. 
     */
    protected long lastStop;
    
    /**
     * The time cached from the last call to getTime().  
     */
    protected long timeSinceStart;
    
    /**
     * The object from which getTimeMethod is invoked.
     */
    protected Object getTimeObject;

    /**
     * The system method to call to get the time measurement.
     */
    protected Method getTimeMethod;

    /**
     * The arguments to pass to the getTimeMethod when it is invoked.
     */
    protected Object[] getTimeArgs;
    
    public SingleTimer( Method getTimeMethod ) {
      this( null, getTimeMethod, null );
    }
    public SingleTimer( Object getTimeObject, Method getTimeMethod, Object[] getTimeArgs ) {
      this.getTimeObject = getTimeObject;
      this.getTimeMethod = getTimeMethod;
      this.getTimeArgs = getTimeArgs;
      reset();
    }
    
    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.util.StopWatch#reset()
     */
    @Override
    public void reset() {
      total = 0;
      start = getSystemTime();
      timeSinceStart = 0;
      lastStart = 0;
      lastStop = 0;
    }
    
    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.util.StopWatch#getTime()
     */
    @Override
    public Long getTime() {
      return getTime( true );
    }
    public Long getTime( boolean getCurrentTime ) {
      if ( getCurrentTime ) {
        timeSinceStart = getSystemTime() - start;
      }
      return timeSinceStart;
    }
    
    /**
     * @return the system time as a Long integer (possibly the number of
     *         milliseconds since the epoch)
     */
    public Long getSystemTime() {
      Object t;
      t = ClassUtils.runMethod( false, getTimeObject, getTimeMethod,
                                getTimeArgs ).second;
      if ( t instanceof Long ) {
        return (Long)t;
      }
      return -1L;
    }
    
    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.util.StopWatch#stop()
     */
    @Override
    public Long stop() {
      if ( lastStop <= lastStart ) {
        lastStop = getTime();
        total += lastStop - lastStart;
      }
      return total;
    }
    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.util.StopWatch#start()
     */
    @Override
    public void start() {
      if ( lastStart >= lastStop ) return;
      lastStart = getTime();
    }
//    /* (non-Javadoc)
//     * @see gov.nasa.jpl.ae.util.StopWatch#getTimeSinceStart()
//     */
//    @Override
//    public Long getTimeSinceStart() {
//      return getTime() - start;
//    }
    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.util.StopWatch#getTimeSinceLastStart()
     */
    @Override
    public Long getTimeSinceLastStart() {
      return getTimeSinceLastStart( true );
    }
    public Long getTimeSinceLastStart(boolean getCurrentTime ) {
      return getTime( getCurrentTime ) - lastStart;
    }
    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.util.StopWatch#getTimeSinceLastStop()
     */
    @Override
    public Long getTimeSinceLastStop() {
      return getTimeSinceLastStart( true );
    }
    public Long getTimeSinceLastStop( boolean getCurrentTime ) {
      return getTime( getCurrentTime ) - lastStop;
    }
    @Override
    public Long getTimeOfLastClock() {
      return getTimeOfLastClock( true );
    }
    public Long getTimeOfLastClock( boolean getCurrentTime ) {
      if ( lastStartOrStopWasStart ) {
        return getTimeSinceLastStart( getCurrentTime );
      }
      return getTimeSinceLastStart( getCurrentTime ) -
             getTimeSinceLastStop( false );
    }
    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.util.StopWatch#getTotalTime()
     */
    @Override
    public Long getTotalTime() {
      return getTotalTime( true );
    }
    public Long getTotalTime( boolean getCurrentTime ) {
      return total + ( lastStart >= lastStop ?
                       getTime( getCurrentTime ) - lastStart : 0 );
    }
  }

  private static final Class< ? >[] emptyClassArray = new Class< ? >[]{};
  private static final Object[] emptyObjectArray = new Object[]{};
  
  protected static ThreadMXBean bean = ManagementFactory.getThreadMXBean();
  
  protected SingleTimer wallClockTimer;
  protected SingleTimer cpuTimer;
  protected SingleTimer userTimer;
  protected StopWatch< Long > systemTimer;
  protected Vector< StopWatch< Long > > timers;
  protected boolean lastStartOrStopWasStart = true;

  public Timer() {
    cpuTimer =
        new SingleTimer( bean, getGetTimeMethod( ThreadMXBean.class,
                                                 "getCurrentThreadCpuTime" ),
                         emptyObjectArray );
    userTimer =
        new SingleTimer( bean, getGetTimeMethod( ThreadMXBean.class,
                                                 "getCurrentThreadUserTime" ),
                         emptyObjectArray );
    wallClockTimer =
        new SingleTimer( null, getGetTimeMethod( System.class, "nanoTime" ),
                         emptyObjectArray );
    systemTimer = initSystemTimer();
    timers = new Vector< StopWatch< Long > >();
    timers.add( wallClockTimer );
    timers.add( cpuTimer );
    timers.add( userTimer );
    timers.add( systemTimer );
  }

  /**
   * @return the system timer. The system time is computed as the difference
   *         between the cpu time and the user time. cpu = user + system. This
   *         anonymous class makes the simplifying assumption that methods of
   *         the cpu and user Timers are always invoked together.
   */
  private StopWatch< Long > initSystemTimer() {
    return new StopWatch< Long >() {
      @Override
      public void reset() {}
      @Override
      public Long getTime() {
        return getCpuTimer().timeSinceStart - getUserTimer().timeSinceStart;
      }
      @Override
      public Long stop() {
        return getTotalTime();
      }
      @Override
      public void start() {
      }
      @Override
      public Long getTimeSinceLastStart() {
        return ( getCpuTimer().getTimeSinceLastStart( false ) -
                 getUserTimer().getTimeSinceLastStart( false ) );
      }
      @Override
      public Long getTimeSinceLastStop() {
        return ( getCpuTimer().getTimeSinceLastStop( false ) -
                 getUserTimer().getTimeSinceLastStop( false ) );
      }
      @Override
      public Long getTotalTime() {
        return getCpuTimer().getTotalTime( false ) - getUserTimer().getTotalTime( false );
      }
      @Override
      public Long getTimeOfLastClock() {
        return ( getCpuTimer().getTimeOfLastClock( false ) -
                 getUserTimer().getTimeOfLastClock( false ) );
      }
    };
  }

  protected Method getGetTimeMethod( Class<?> cls, String methodName ) {
    try {
      return cls.getMethod( methodName, emptyClassArray );
    } catch ( SecurityException e ) {
      e.printStackTrace();
    } catch ( NoSuchMethodException e ) {
      e.printStackTrace();
    }
    return null;
  }
  
  @Override
  public void reset() {
    lastStartOrStopWasStart = true;
    for ( StopWatch< Long > t : timers ) {
      t.reset();
    }
  }

  @Override
  public Vector< Long > getTime() {
    return getTime( true );
  }
  public Vector< Long > getTime( boolean getCurrentTime ) {
    Vector< Long > times = new Vector< Long >( timers.size() );
    for ( StopWatch< Long > t : timers ) {
      Long time;
      if ( t instanceof SingleTimer ) {
        time = ((SingleTimer)t).getTime( getCurrentTime );
      } else {
        time = t.getTime();
      }
      times.add( time );
    }
    return times;
  }

  @Override
  public void start() {
    lastStartOrStopWasStart = true;
    for ( StopWatch< Long > t : timers ) {
      t.start();
    }
  }
  
  @Override
  public Vector< Long > stop() {
    lastStartOrStopWasStart = false;
    Vector< Long > times = new Vector< Long >( timers.size() );
    //int ctr = 0;
    for ( StopWatch< Long > t : timers ) {
      times.add( t.stop() );
    }
    return times;
  }

  @Override
  public Vector< Long > getTimeSinceLastStart() {
    return getTimeSinceLastStart( true );
  }
  public Vector< Long > getTimeSinceLastStart( boolean getCurrentTime ) {
    Vector< Long > times = new Vector< Long >( timers.size() );
    for ( StopWatch< Long > t : timers ) {
      Long time;
      if ( t instanceof SingleTimer ) {
        time = ((SingleTimer)t).getTimeSinceLastStart( getCurrentTime );
      } else {
        time = t.getTimeSinceLastStart();
      }
      times.add( time );
    }
    return times;
  }

  @Override
  public Vector< Long > getTimeSinceLastStop() {
    return getTimeSinceLastStop( true );
  }
  public Vector< Long > getTimeSinceLastStop( boolean getCurrentTime ) {
    Vector< Long > times = new Vector< Long >( timers.size() );
    for ( StopWatch< Long > t : timers ) {
      Long time;
      if ( t instanceof SingleTimer ) {
        time = ((SingleTimer)t).getTimeSinceLastStop( getCurrentTime );
      } else {
        time = t.getTimeSinceLastStop();
      }
      times.add( time );
    }
    return times;
  }

  @Override
  public Vector< Long > getTotalTime() {
    return getTotalTime( true );
  }
  public Vector< Long > getTotalTime( boolean getCurrentTime ) {
    Vector< Long > times = new Vector< Long >( timers.size() );
    for ( StopWatch< Long > t : timers ) {
      Long time;
      if ( t instanceof SingleTimer ) {
        time = ((SingleTimer)t).getTotalTime( getCurrentTime );
      } else {
        time = t.getTotalTime();
      }
      times.add( time );
    }
    return times;
  }

//  public int getWallClockTimerIndex() {
//    return 0;
//  }
//  public int getCpuTimerIndex() {
//    return 1;
//  }
//  public int getUserTimerIndex() {
//    return 2;
//  }

  /**
   * @return the cpuTimer
   */
  public SingleTimer getCpuTimer() {
    return cpuTimer;
  }

  /**
   * @return the userTimer
   */
  public SingleTimer getUserTimer() {
    return userTimer;
  }

  /**
   * @return the userTimer
   */
  public StopWatch< Long > getSystemTimer() {
    return systemTimer;
  }

  /**
   * @return the wallClockTimer
   */
  public SingleTimer getWallClockTimer() {
    return wallClockTimer;
  }
  
  String writeNanosAsSeconds( long nanos ) {
    double nanosd = nanos;
    double seconds = nanosd / 1.0e9;
    return String.format( "%.3f", seconds );
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    final String[] timerNames = new String[]{ "wall clock", "cpu", "user", "system" };
    Vector< String > s = new Vector< String >( timers.size() );

    // collect stats before writing the string
    Vector< Long > currentTime = getTime( true );
    Vector< Long > totalTime = getTotalTime( false );
    Vector< Long > lastTimeStartedOrStopped =
        lastStartOrStopWasStart ? getTimeSinceLastStart( false )
                                : getTimeSinceLastStop( false );
        Vector< Long > timeOfLastClock = getTimeOfLastClock( false );

    // time of last start/stop watch
    if ( !timeOfLastClock.equals( totalTime ) ) {
      s.clear();
      for ( int i = 0; i < timers.size(); ++i ) {
        s.add( timerNames[ i ] + "="
               + writeNanosAsSeconds( timeOfLastClock.get( i ) ) + "s" );
      }
      sb.append( "last time segment: " + s + "\n" );
    }

    // total time
    s.clear();
    for ( int i = 0; i < timers.size(); ++i ) {
      s.add( timerNames[ i ] + "=" + writeNanosAsSeconds( totalTime.get( i ) )
             + "s" );
    }
    sb.append( "total time: " + s + "\n" );

    // current time
    if ( !totalTime.equals( currentTime ) ) {
      for ( int i = 0; i < timers.size(); ++i ) {
        s.add( timerNames[ i ] + "="
               + writeNanosAsSeconds( currentTime.get( i ) ) + "s" );
      }
      sb.append( "time since created: " + s + "\n" );
    }

    sb.append( "\n" );
    
    return sb.toString();
  }

  // some simple static methods
  
  /** Get CPU time in nanoseconds. */
  public static long getCpuTime() {
    ThreadMXBean bean = ManagementFactory.getThreadMXBean();
    return bean.isCurrentThreadCpuTimeSupported() ? bean.getCurrentThreadCpuTime()
                                                  : 0L;
  }

  /** Get user time in nanoseconds. */
  public static long getUserTime() {
    ThreadMXBean bean = ManagementFactory.getThreadMXBean();
    return bean.isCurrentThreadCpuTimeSupported() ? bean.getCurrentThreadUserTime()
                                                  : 0L;
  }

  /** Get system time in nanoseconds. */
  public static long getSystemTime() {
    ThreadMXBean bean = ManagementFactory.getThreadMXBean();
    return bean.isCurrentThreadCpuTimeSupported() ? ( bean.getCurrentThreadCpuTime() - bean.getCurrentThreadUserTime() )
                                                  : 0L;
  }

  @Override
  public Vector< Long > getTimeOfLastClock() {
    return getTotalTime( true );
  }
  public Vector< Long > getTimeOfLastClock( boolean getCurrentTime ) {
    Vector< Long > times = new Vector< Long >( timers.size() );
    for ( StopWatch< Long > t : timers ) {
      Long time;
      if ( t instanceof SingleTimer ) {
        time = ((SingleTimer)t).getTimeOfLastClock( getCurrentTime );
      } else {
        time = t.getTimeOfLastClock();
      }
      times.add( time );
    }
    return times;
  }


}
