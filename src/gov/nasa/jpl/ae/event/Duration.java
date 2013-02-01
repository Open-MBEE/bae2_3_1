package gov.nasa.jpl.ae.event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import gov.nasa.jpl.ae.event.Timepoint.Units;
import gov.nasa.jpl.ae.solver.IntegerDomain;


/**
 * @author bclement
 *
 */
public class Duration extends IntegerParameter { // TODO -- LongParameter

  protected static int counter = 0;
  
  // TODO -- add accessors, toString(), and fromString(), and constructors from
  // String instead of Integer.
  protected Timepoint.Units units = Timepoint.getUnits();

  public static final String durationFormat = "yyyy-DDD'T'HH:mm:ss.SSS";
  public static final String durationFormatForIdentifier = "yyyy-DDD'T'HH.mm.ss.SSS";
      //Timepoint.timestampFormat.replace( "Z", "" ); //"HH:mm:ss.SSS";

  /**
   * @param o 
   */
  public Duration(ParameterListener o) {
    super("duration", TimeDomain.horizonDomain, o);
  }

  /**
   * @param o 
   * @param name
   */
  public Duration(String name, ParameterListener o) {
    super(name, TimeDomain.horizonDomain, o);
  }

	/**
	 * @param o 
	 * @param v
	 */
	public Duration(Integer value, ParameterListener o) {
		super( "duration", TimeDomain.horizonDomain, value, o );
	}

  /**
   * @param o 
   * @param n
   * @param v
   */
  public Duration(String name, Integer value, ParameterListener o) {
    super( name, TimeDomain.horizonDomain, value, o );
  }

	public Duration(Duration duration) {
		super( duration );
	}
	
	public Duration( String name, double durVal,
                   Units durUnits, ParameterListener o ) {
    super( name, TimeDomain.horizonDomain,
           new Integer( (int)(durVal * Timepoint.Units.conversionFactor( durUnits )) ),
           o );
  }

  // parse a Duration from a String in either the duration format or
	// as a long followed by a unit specification, like 100s.
	public static synchronized Duration fromString( String duration ) {
    long t = 0;
    Timepoint.Units units = Timepoint.getUnits();  // default
    DateFormat df = new SimpleDateFormat( durationFormat );
    try {
      Date d = df.parse( duration );
      assert ( d != null );
      t = (long)( Units.conversionFactor( Units.milliseconds, units )
                  * d.getTime() );
    } catch ( java.text.ParseException e1 ) {
      String numberString = duration.replaceFirst( "([^a-z]*).*", "$1" ).trim();
      String unitsString = duration.replaceFirst( "[^a-z]*(.*)", "$1" ).trim();
      try {
        t = Long.parseLong( numberString );
        units = Timepoint.Units.fromString( unitsString );
        if ( units == null ) {
          return null;
        }
      } catch ( NumberFormatException e) {
        e.printStackTrace();
        return null;
      }
    }
    Duration d = new Duration( "duration" + ( counter++ ), (int)t, null );
    d.units = units;
    return d;
    // TODO -- REVIEW -- other formats?  0.0003s?
	}

	public String toStringWithUnits( boolean includeName, boolean includeOwner ) {
	  String s = "";
	  if ( includeOwner && owner != null ) {
	    s = owner.getName() + ":";
	  }
	  if ( includeName ) {
	    s = s + getName() + "=";
	  }
//	  if ( getValue() == null ) {
//	    s = s + "null";
//	  }
	  return s + getValueNoPropagate() + units.toShortString();
	}
	
	public long toMillis() {
    return ticksToMillis( getValue(false) );
	}
	
  public String toFormattedString() {
    return toFormattedString( toMillis() );
  }
  public String toFormattedStringForIdentifier() {
    return toFormattedString( toMillis() );
  }
  public static String toFormattedStringForIdentifier( long millis ) {
    return fixYear(toFormattedString( millis, durationFormatForIdentifier ));
  }
  public static String toShortFormattedStringForIdentifier( long millis ) {
    String s = fixYear(toFormattedString( millis, durationFormatForIdentifier ));
    return removeLeadingZeroValues( s );
  }
  public static String toFormattedString( long millis ) {
    return fixYear(toFormattedString( millis, durationFormat ));
  }
  public static String toFormattedString( long millis, String format ) {
    SimpleDateFormat sdf = new SimpleDateFormat( format );
    String tString =
        sdf.format( new Date( millis 
                              - TimeZone.getDefault().getRawOffset() ) );
    return tString;
  }

  public static String fixYear( String dateString ) {
    if ( dateString.length() >= 5 ) {
      String yearStr = dateString.substring( 0, 4 );
      Integer year = Integer.parseInt( yearStr );
      String newYearStr = "0000"; 
      if ( year > 1970 ) {
        year = year - 1970;
        newYearStr = year.toString() + dateString.substring( 4, 5 );
      }
      return newYearStr + dateString.substring( 4 );
    }
    return dateString;
  }
  
  public static String removeLeadingZeroValues( String s ) {
    int posOfNewStr = 0;
    boolean done = false;
    // remove all-zero numbers and delimiters that follow
    while ( posOfNewStr < s.length() && !done && s.charAt( posOfNewStr ) == '0' ) {
      int newPos = posOfNewStr;
      // skip zeroes
      while ( newPos < s.length() && Character.isDigit( s.charAt( newPos ) ) ) {
        if ( s.charAt( newPos ) == '0' ) {
          ++newPos;
        } else {
          newPos = posOfNewStr;
          done = true;
          break;
        }
      }
      if ( !done ) {
        // non-zero digits were not found; skip over delimiter(s)
        while ( newPos < s.length() && !Character.isDigit( s.charAt( newPos ) ) ) {
          ++newPos;
        }
      }
      posOfNewStr = newPos;
    }
    return s.substring( posOfNewStr );
  }

  @Override
  public String toString() {
	  return toStringWithUnits( true, true );
  }

  public static long ticksToMillis( Integer d ) {
    return durationToMillis( d );
  }
  public static long durationToMillis( Integer d ) {
    return (long)( ((double)d)
                   * Timepoint.Units.conversionFactor( Units.milliseconds ) );
  }

  public static int lengthOfOne( Units u ) {
    return (int) (1.0 / Units.conversionFactor( u ) );
  }
	
	
}
