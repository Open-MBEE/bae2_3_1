/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.mbee.util.Random;

/**
 * @author bclement
 *
 */
public class StringDomain extends AbstractRangeDomain<String> {
	
	public static final int maxStringSize = 8;
	public static final String typeMaxValue = String.format("%0" + maxStringSize + "d", 0).replace( "0", "" + (char)( '\0' + 255 ) ); 
	public static final String typeMinValue = "";
	public static StringDomain defaultDomain = new StringDomain();
	
  public StringDomain() {
    lowerBound = typeMinValue;
    upperBound = typeMaxValue;
  }
  
  public StringDomain(String minValue, String maxValue) {
    this.lowerBound = minValue;
    this.upperBound = maxValue;
  }

  public StringDomain( RangeDomain<String> stringDomain ) {
    super( stringDomain );
  }

	/* (non-Javadoc)
	 * @see event.Domain#isInfinite()
	 */
	@Override
	public boolean isInfinite() {
		return true;
	}

	/* (non-Javadoc)
	 * @see event.Domain#size()
	 */
	@Override
	public long size() {
	  // TODO?
    if ( lowerBound == null || upperBound == null ) return 0;
    if ( lowerBound.equals( upperBound ) ) return 1;
		return -1;
	}

	/* (non-Javadoc)
	 * @see event.Domain#pickRandomValue()
	 */
	@Override
	public String pickRandomValue() {
	  // REVIEW -- Not a uniform distribution.
		int length = Random.global.nextInt( maxStringSize );
		StringBuffer s = new StringBuffer();
		while (length > 0) {
      s.append( (char)( '\0' + Random.global.nextInt( 256 ) ) );
			--length;
		}
		return s.toString();
	}

	@Override
	public String toString() {
		if ( lowerBound == typeMinValue && upperBound == typeMaxValue ) {
			return "*";
		}
		// TODO -- escapes? substitute \" and \n  for '"' and newline, etc.?
		return "[\"" + getLowerBound() + "\" \"" + getUpperBound() + "\"]";
	}

  @Override
  public String getNthValue( long n ) {
    // TODO?
    return null;
  }

  // TODO
  // TODO
  // TODO
  // TODO Since T in Domain<T> is Comparable<T>, can't we move these
  // TODO inequalities into AbstractDomainRange??
  // TODO
  // TODO
  // TODO
  @Override
  public boolean greater( String t1, String t2 ) {
    if ( t1 == null ) return false;
    if ( t2 == null ) return t1 != null;
    return t1.compareTo( t2 ) > 0;
  }

  @Override
  public boolean less( String t1, String t2 ) {
    if ( t1 == null ) return t2 != null;
    if ( t2 == null ) return false;
    return t1.compareTo( t2 ) < 0;
  }

  @Override
  public boolean greaterEquals( String t1, String t2 ) {
    if ( t1 == null ) return t2 == null;
    if ( t2 == null ) return true;
    return t1.compareTo( t2 ) >= 0;
  }

  @Override
  public boolean lessEquals( String t1, String t2 ) {
    if ( t1 == null ) return true;
    if ( t2 == null ) return t1 == null;
    return t1.compareTo( t2 ) <= 0;
  }

  @Override
  public AbstractRangeDomain< String > clone() {
    return new StringDomain( this );
  }

  @Override
  public String getTypeMaxValue() {
    return typeMaxValue;
  }

  @Override
  public String getTypeMinValue() {
    return typeMinValue;
  }

  @Override
  public RangeDomain< String > getDefaultDomain() {
    return defaultDomain;
  }

  @Override
  public void setDefaultDomain( Domain< String > domain ) {
    if ( domain instanceof StringDomain ) {
      defaultDomain = (StringDomain)domain;
    } else if ( domain instanceof RangeDomain ) {
      defaultDomain = new StringDomain((RangeDomain< String >)domain);
    }
  }

  @Override
  public Class< String > getType() {
    return String.class;
  }

  @Override
  public Class< ? > getPrimitiveType() {
    return String.class;  // TODO -- REVIEW -- return null??
  }

}
