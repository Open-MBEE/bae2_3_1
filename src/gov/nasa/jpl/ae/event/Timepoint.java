package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.TimeVariable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;


/**
 * 
 */

/**
 * @author bclement
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
  protected static Units units = Units.seconds;

  protected static int counter = 0; 

  public static final String timestampFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

  public static enum Units { 
    days(24*3600*1e9), hours(3600*1e9), minutes(60*1e9),
    seconds(1e9), milliseconds(1e6), microseconds(1e3),
    nanoseconds(1);
    
    private double factor;
    
    Units(double f) {
      factor = f;
    }
    
    public static double conversionFactor( Units fromUnits, Units toUnits) {
      double f = ((double)fromUnits.factor)/toUnits.factor;
//      Debug.outln( "conversionFactor(" + fromUnits + ", " + toUnits
//                          + ") = " + fromUnits.factor + " / " + toUnits.factor
//                          + " = " + f );
      return f;
    }
    public static double conversionFactor( Units toUnits) {
      return Units.conversionFactor( units, toUnits );
    }
    public String toShortString() {
      switch (this) {
        case days:
          return "d";
        case hours:
          return "h";
        case minutes:
          return "m";
        case seconds:
          return "s";
        case milliseconds:
          return "ms";
        case microseconds:
          return "\u00B5s";
        case nanoseconds:
          return "ns";
        default:
          return null;
      }
    }

    public static Units fromString( String unitsString ) {
      Units unit = null;
      try {
        if ( unitsString == null || unitsString.length() == 0 ) {
          Assert.fail( "Parse of units from \"" + unitsString + "\" failed!" );
        }
        if ( unitsString.equals( microseconds.toShortString() ) ) {
          unit = microseconds;
        } else {
          switch ( unitsString.charAt( 0 ) ) {
            case 'd':
              unit = days;
              break;
            case 'h':
              unit = hours;
              break;
            case 's':
              unit = seconds;
              break;
            case 'n':
              unit = nanoseconds;
              break;
            case 'm':
              if ( unitsString.length() == 1 ) {
                unit = minutes;
                break;
              } else {
                switch ( unitsString.charAt( 1 ) ) {
                  case 'i':
                    if ( unitsString.length() <= 2 ) {
                      Assert.fail( "Parse of units from \"" + unitsString
                                   + "\" failed!" );
                    } else {
                      switch ( unitsString.charAt( 2 ) ) {
                        case 'n':
                          unit = minutes;
                          break;
                        case 'l':
                          unit = milliseconds;
                          break;
                        case 'c':
                          unit = microseconds;
                          break;
                        default:
                          Assert.fail( "Parse of units from \"" + unitsString
                                       + "\" failed!" );
                      }
                    }
                    break;
                  case 's':
                    unit = milliseconds;
                    break;
                  default:
                    Assert.fail( "Parse of units from \"" + unitsString
                                 + "\" failed!" );
                }
              }
              break;
            default:
              Assert.fail( "Parse of units from \"" + unitsString
                           + "\" failed!" );
          }
        }
        if ( unit != null && !unitsString.equals( unit.toString() )
             && !unitsString.equals( unit.toShortString() ) ) {
          Assert.fail( "Parse of units from \"" + unitsString + "\" failed!" );
        }
      } catch ( Exception e ) {
        e.printStackTrace();
      }
      return unit;
    }
  }

  /**
	 * @param name
	 * @param o 
	 */
	public Timepoint(String name, ParameterListener o) {
		super(name, IntegerDomain.positiveDomain, o);
	}

	/**
	 * @param o 
	 * @param n
	 * @param v
	 */
	public Timepoint(String name, Integer value, ParameterListener o) {
		super(name, IntegerDomain.positiveDomain, value, o);
	}

	public Timepoint(Timepoint timepoint) {
		super(timepoint);
	}
	
	public static synchronized Timepoint fromString( String timestamp ) {
	  return new Timepoint( "timepoint" + (counter ++), (int)fromTimestamp( timestamp ), null );
    // REVIEW -- other formats?
	}

	public long convertTo( Units toUnit ) {
	  Double f = new Double( Units.conversionFactor( toUnit ) );
	  f *= getValue();
	  return (long)( f.longValue() );
	}
	
  public static Date dateFromTimestamp( String timestamp ) {
    String formatsToTry[] = { Timepoint.timestampFormat,
                              Timepoint.timestampFormat.replace( ".SSS", "" ),
                              Timepoint.timestampFormat.replace( "Z", "" ),
                              Timepoint.timestampFormat.replace( ".SSSZ", "" ),
                              "EEE MMM dd HH:mm:ss zzz yyyy" };
//    ArrayList formatsToTry = new ArrayList();
//    format
    int pos = timestamp.lastIndexOf( ':' );
    if ( pos == timestamp.length() - 3
         && timestamp.replaceAll( "[^:]", "" ).length() == 3 ) {
      timestamp = timestamp.replaceFirst( ":([0-9][0-9])$", "$1" );
    }
    //for ( String format : formatsToTry ) {
    for ( int i = 0; i < formatsToTry.length; ++i ) {
      String format = formatsToTry[i];
      DateFormat df = new SimpleDateFormat( format );
      try {
        Date d = df.parse( timestamp );
        return d;
      } catch ( IllegalArgumentException e1 ) {
        if ( i == formatsToTry.length - 1 ) {
          e1.printStackTrace();
        }
      } catch ( ParseException e ) {
        if ( i == formatsToTry.length - 1 ) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }
  
  public static long fromTimestamp( String timestamp ) {
    long t = 0;
    DateFormat df = new SimpleDateFormat( timestampFormat );
    try {
      Date d = df.parse( timestamp );
      assert ( d != null );
      t = (long)( Units.conversionFactor( Units.milliseconds, Timepoint.units )
                  * d.getTime() );
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
    double cf = Units.conversionFactor( Units.milliseconds );
    cal.setTimeInMillis( (long)( getEpoch().getTime() + t * cf  ) );
    String timeString =
        new SimpleDateFormat( timestampFormat ).format( cal.getTime() );
    return timeString;
  }

  public String toTimestamp() {
    return toTimestamp( getValue() );
//    Double cf = Units.conversionFactor( Units.milliseconds );
//    return millisToTimestamp( (long)( getValue() * cf ) );
////    Calendar cal = Calendar.getInstance();
////    cal.setTimeInMillis( (long)( ( getEpoch().getTime() + getValue() ) * cf ) );
////    String timeString =
////        new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" ).format( cal.getTime() );
////    return timeString;
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

  /**
   * @param epoch the epoch to set
   */
  public synchronized static void setEpoch( Date epoch ) {
    Timepoint.epoch = epoch;
  }

  public static void setEpoch( String epochString ) {
    setEpoch( dateFromTimestamp( epochString ) );
  }

  /**
   * @return the units
   */
  public static Units getUnits() {
    return units;
  }

  /**
   * @param units the units to set
   */
  public static void setUnits( Units units ) {
    Timepoint.units = units;
  }

  public static void setUnits( String timeUnits ) {
    setUnits( Units.fromString( timeUnits ) );
    
  }

}
