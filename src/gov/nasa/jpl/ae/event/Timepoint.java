package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.TimeVariable;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.TimeUtils;
import gov.nasa.jpl.mbee.util.TimeUtils.Units;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 
 */

/**
 *
 */
public class Timepoint extends IntegerParameter implements TimeVariable {

  // epoch is a timestamp corresponding to TimePoint = 0 as the date/time that
  // the simulation starts. It is an offset of the time since Jan 1, 1970.
  // For example, if epoch == 1341614935000 milliseconds, then a TimePoint or
  // int value of 0 corresponds to Fri, Jul 06, 2012 3:48:55 PM.
  // This number comes from using the 'date' unix command:
  //    $ date; date '+%s'
  //    Fri, Jul 06, 2012  3:48:55 PM
  //    1341614935
  // The units of time and the epoch are specified by Units units below.  
  protected static Date epoch = new Date();
  // The unit of time for the epoch and all other integer values of time.  
  protected static TimeUtils.Units units = TimeUtils.Units.seconds;

  protected static int counter = 0;
  protected static int horizonDuration = 24 * 3600;
  
  private final static Timepoint epochTimepoint = new Timepoint( "", 0, null );

  protected static IntegerDomain defaultDomain = IntegerDomain.positiveDomain;
                                               //= TimeDomain.horizonDomain;
  
  public int timeSinceMidnight() {
    if ( !isGrounded( false, null ) ) return 0;
    double f = conversionFactor( TimeUtils.Units.milliseconds );
    long v =  (long)(getValue(false) * f + getEpoch().getTime());
    Date d = new Date( v );
    return timeSinceMidnight( d );
//    return (int)( ( ( (long)( v * f ) ) % ( 24 * 3600 * 1000 ) ) / f );
  }
  

  /**
	 * @param name
	 * @param o 
	 */
	public Timepoint(String name, ParameterListener o) {
		super(name, defaultDomain, o);
	}

	/**
	 * @param o 
	 * @param n
	 * @param v
	 */
	public Timepoint(String name, Integer value, ParameterListener o) {
		super(name, defaultDomain, value, o);
	}

  /**
   * @param value
   */
  public Timepoint( int value ) {
    this( "", value, null );
  }

	/**
	 * @param timepoint
	 */
	public Timepoint(Timepoint timepoint) {
		super(timepoint);
	}
	
  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo( Parameter< ? > o ) {
    return compareTo( o, false, true );
  }
  public int compareTo( Parameter< ? > o, boolean propagate, boolean checkId ) {
    if ( this == o ) return 0;
    if ( o == null ) return 1; // REVIEW -- okay for o to be null? complain?
    int compare = 0;
    if ( o instanceof Timepoint ) {
      Integer v1 = null;
      Integer v2 = null;
      try {
        v1 = Expression.evaluate( this, Integer.class, propagate );
      } catch ( ClassCastException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      }
      try {
        v2 = Expression.evaluate( o, Integer.class, propagate );
      } catch ( ClassCastException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      }
      compare = CompareUtils.compare( v1, v2, true );
      if ( compare != 0 ) return compare;
    }
    return super.compareTo( o, checkId );
  }
	
  public static synchronized Timepoint fromMillis( long millis ) {
    return new Timepoint( "timepoint" + ( counter++ ),
                          fromMillisToInteger( millis ), null );
  }

  public static synchronized Timepoint fromDate( Date date) {
    return new Timepoint( "timepoint" + ( counter++ ),
                          fromDateToInteger( date ), null );
  }

	public static synchronized Timepoint fromTimestamp( String timestamp ) {
    return new Timepoint( "timepoint" + ( counter++ ),
                          fromTimestampToInteger( timestamp ), null );
    // REVIEW -- other formats?
	}

  public static double conversionFactor( Units toUnits) {
    return Units.conversionFactor( Timepoint.units, toUnits );
  }

  public long convertTo( TimeUtils.Units toUnit ) {
	  Double f = new Double( conversionFactor( toUnit ) );
	  f *= getValue(false);
	  return (long)( f.longValue() );
	}
	
  public static Integer fromMillisToInteger( long millis ) {
    int t = (int)( Units.conversionFactor( Units.milliseconds, Timepoint.units )
                   * ( millis - Timepoint.epoch.getTime() ) );
    return t;
  }

  public static Integer fromDateToInteger( Date date ) {
    return fromMillisToInteger( date.getTime() );
  }

	public Date getDate() {
	  if ( !isGrounded( false, null ) ) return null;
	  Date d = new Date( (long)(conversionFactor( TimeUtils.Units.milliseconds )
	                            * getValue(false)) );
	  return d;
	}
	
  public static Integer fromTimestampToInteger( String timestamp ) {
    Integer t = null;
    DateFormat df = new SimpleDateFormat( TimeUtils.timestampFormat );
    try {
      Date d = df.parse( timestamp );
      assert ( d != null );
      t = fromDateToInteger(d);
    } catch ( java.text.ParseException e1 ) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    return t;
  }

  // Converts time offset to a date-time String in Timepoint.timestamp format.
  // Assumes t is an offset from Timepoint.epoch in Timepoint.units. 
  public static String toTimestamp( long t ) {
    Calendar cal = Calendar.getInstance();
    double cf = conversionFactor( Units.milliseconds );
    cal.setTimeInMillis( (long)( Timepoint.getEpoch().getTime() + t * cf  ) );
    String timeString =
        new SimpleDateFormat( TimeUtils.timestampFormat ).format( cal.getTime() );
    return timeString;
  }

  public String toTimestamp() {
    return toTimestamp( getValue(false) );
//    Double cf = Units.conversionFactor( Units.milliseconds );
//    return millisToTimestamp( (long)( getValue() * cf ) );
////    Calendar cal = Calendar.getInstance();
////    cal.setTimeInMillis( (long)( ( getEpoch().getTime() + getValue() ) * cf ) );
////    String timeString =
////        new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" ).format( cal.getTime() );
////    return timeString;
  }
  
  public static int timeSinceMidnight( Date start ) {
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    c1.setTime( start );
    c2.setTime( start );
    c2.set( Calendar.HOUR_OF_DAY, 0 );
    c2.set(Calendar.MINUTE, 0);
    c2.set(Calendar.SECOND, 0);
    c2.set(Calendar.MILLISECOND, 0);
    long diffMillis = (int)( c1.getTimeInMillis() - c2.getTimeInMillis() ); 
    double f = conversionFactor( Units.milliseconds );
    return (int)( diffMillis / f );
  }

  @Override
  public String toString() {
    //return toTimestamp();
    return super.toString();
  }

  /**
   * @return the epoch
   */
  public synchronized static Date getEpoch() {
    return epoch;
  }
  public synchronized static Timepoint getEpochTimepoint() {
    return epochTimepoint;
  }


  /**
   * @param epoch the epoch to set
   */
  public synchronized static void setEpoch( Date epoch ) {
    Timepoint.epoch = epoch;
    System.out.println("Epoch set to " + epoch );
  }

  public static void setEpoch( String epochString ) {
    setEpoch( TimeUtils.dateFromTimestamp( epochString ) );
  }

  /**
   * @return the units
   */
  public static TimeUtils.Units getUnits() {
    return units;
  }

  /**
   * @param units the units to set
   */
  public static void setUnits( TimeUtils.Units units ) {
    System.out.println("Units set to " + units );
    Timepoint.units = units;
  }

  /**
   * @param timeUnits
   */
  public static void setUnits( String timeUnits ) {
    setUnits( TimeUtils.Units.fromString( timeUnits ) );
    
  }

  /**
   * @param durationString the horizon duration to set 
   */
  public static void setHorizonDuration( int duration ) {
    horizonDuration = duration;
    System.out.println("Horizon duration set to " + horizonDuration + " " + units );
    TimeDomain.horizonDomain.setUpperBound( horizonDuration );
  }

  /**
   * @return the horizon duration
   */
  public static Integer getHorizonDuration() {
    return horizonDuration;
  }


  public static Timepoint now() {
    return fromDate( Calendar.getInstance().getTime() );
  }

}
