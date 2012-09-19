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
      //Timepoint.timestampFormat.replace( "Z", "" ); //"HH:mm:ss.SSS";

  /**
   * @param o 
   */
  public Duration(ParameterListener o) {
    super("duration", IntegerDomain.positiveDomain, o);
  }

  /**
   * @param o 
   * @param name
   */
  public Duration(String name, ParameterListener o) {
    super(name, IntegerDomain.positiveDomain, o);
  }

	/**
	 * @param o 
	 * @param v
	 */
	public Duration(Integer value, ParameterListener o) {
		super( "duration", IntegerDomain.positiveDomain, value, o );
	}

  /**
   * @param o 
   * @param n
   * @param v
   */
  public Duration(String name, Integer value, ParameterListener o) {
    super( name, IntegerDomain.positiveDomain, value, o );
  }

	public Duration(Duration duration) {
		super( duration );
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
    return ticksToMillis( getValue() );
	}
	
  public String toFormattedString() {
    // TODO -- 
    SimpleDateFormat sdf = new SimpleDateFormat( durationFormat );
    String tString =
        sdf.format( new Date( toMillis() 
                              - TimeZone.getDefault().getRawOffset() ) );
    return tString;
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
	
	
}
