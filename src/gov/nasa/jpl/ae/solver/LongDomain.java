/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.ae.util.Math;
import gov.nasa.jpl.mbee.util.Random;

/**
 * @author bclement
 *
 */
public class LongDomain extends AbstractFiniteRangeDomain< Long > {

  public static final long typeMaxValue = Long.MAX_VALUE;
  public static final long typeMinValue = Long.MIN_VALUE;

  //public static LongDomain domain = new LongDomain();  // REVIEW -- why is this not defaultDomain?
	public static final LongDomain positiveDomain =
			new LongDomain(0, typeMaxValue);
  public static final LongDomain defaultDomain = new LongDomain();  // REVIEW -- make this final?

	public LongDomain() {
    super(typeMinValue, typeMaxValue);
	}
	
	public LongDomain(long minValue, long maxValue) {
	  super(minValue, maxValue);
	}

	public LongDomain( RangeDomain< Long > domain ) {
	  super( domain );
  }

  /* (non-Javadoc)
	 * @see event.Domain#isInfinite()
	 */
	@Override
	public boolean isInfinite() {
		return false;
	}

	/* (non-Javadoc)
	 * @see event.Domain#size()
	 */
	@Override
	public long size() {
    if ( lowerBound == null || upperBound == null ) return 0;
    if ( lowerBound.equals( upperBound ) ) return 1;
    return Math.plus( (long)upperBound, (long)-lowerBound );
	}

	/* (non-Javadoc)
	 * @see event.Domain#getLowerBound()
	 */
	@Override
	public Long getLowerBound() {
		return lowerBound;
	}

	/* (non-Javadoc)
	 * @see event.Domain#getUpperBound()
	 */
	@Override
	public Long getUpperBound() {
		return upperBound;
	}

	/* (non-Javadoc)
	 * @see event.Domain#pickRandomValue()
	 */
	@Override
	public Long pickRandomValue() {
    if ( this.isEmpty() ) {
      return null;
    }
		//return (long) Math.abs( getLowerBound() + Math.random() * size() );
    // a bunch of tricks to avoid overflow
    double r1 = Random.global.nextDouble();
    double r2 = Random.global.nextDouble();
    double middle = getMiddleValue();
    double half = getUpperBound() - middle;
    if ( r1 < 0.5 ) {
      return (long)(middle - r2 * half);
    }
    return (long)(middle + r2 * half);
	}

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.AbstractRangeDomain#pickRandomValueLessThan(java.lang.Object)
   */
  @Override
	public Long pickRandomValueLessThan( Long maxVal ) {
	   long u = getUpperBound();
	   setUpperBound( maxVal );
	   long p = pickRandomValue();
	   setUpperBound( u );
	   return p;
	 }

	
	private Long getMiddleValue() {
    // TODO -- should interpret null as zero 
    if ( lowerBound == null || upperBound == null ) return (long)0;
    
    return (long)(lowerBound + upperBound/2 - lowerBound/2); // this is a floor
  }

  @Override
	public String toString() {
		return "[" + getLowerBound() + " " + getUpperBound() + "]";
	}

  @Override
  public boolean contains( Long t ) {
    if ( t == null && 
         ( lowerBound != null  || upperBound != null ) ) return false;
    if ( t == null ) return nullInDomain;
    if ( lowerBound == null && upperBound == null ) return false;
    return (lowerBound == null || lowerBound <= t) && (upperBound == null || upperBound >= t);
  }

  // counts from zero!!!
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.AbstractFiniteRangeDomain#getNthValue(long)
   */
  @Override
  public Long getNthValue( long n ) {
    return (long)(((long)getLowerBound()) + n);
  }

  @Override
  public boolean greater( Long t1, Long t2 ) {
    if ( t1 == null ) return false;
    if ( t2 == null ) return t1 != null;
    return t1 > t2;
  }

  @Override
  public boolean less( Long t1, Long t2 ) {
    if ( t1 == null ) return t2 != null;
    if ( t2 == null ) return false;
    return t1 < t2;
  }

//  @Override
//  public boolean equals( Long t1, Long t2 ) {
//    return t1 >= t2;
//  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.AbstractFiniteRangeDomain#greaterEquals(java.lang.Comparable, java.lang.Comparable)
   */
  @Override
  public boolean greaterEquals( Long t1, Long t2 ) {
    if ( t1 == null ) return t2 == null;
    if ( t2 == null ) return true;
    return t1 >= t2;
  }

  @Override
  public boolean lessEquals( Long t1, Long t2 ) {
    if ( t1 == null ) return true;
    if ( t2 == null ) return t1 == null;
    return t1 <= t2;
  }

  @Override
  public AbstractFiniteRangeDomain< Long > clone() {
    return new LongDomain( this );
  }

  @Override
  public Class< Long > getType() {
    return Long.class;
  }

  @Override
  public Class< ? > getPrimitiveType() {
    return long.class;
  }

  @Override
  public Long getTypeMaxValue() {
    return typeMaxValue;
  }

  @Override
  public Long getTypeMinValue() {
    return typeMinValue;
  }

  @Override
  public RangeDomain< Long > getDefaultDomain() {
    return defaultDomain ;
  }

//  @Override
//  public void setDefaultDomain( Domain< Long > domain ) {
//    if ( domain instanceof LongDomain ) {
//      defaultDomain = (LongDomain)domain;
//    } else if ( domain instanceof RangeDomain ) {
//      defaultDomain = new LongDomain((RangeDomain< Long >)domain);
//    }
//  }
  
  @Override
  public LongDomain make( Long lowerBound, Long upperBound ) {
    return new LongDomain(lowerBound, upperBound);
  }

  @Override
  public Long getNextGreaterValue( Long t ) {
    if (t == null || equals(t, getTypeMaxValue()) ) return null;
    return t + 1;
  }

  @Override
  public Long getPreviousLesserValue( Long t ) {
    if (t == null || equals(t, getTypeMinValue()) ) return null;
    return t - 1;
  }

  @Override
  public int compareTo( Domain< Long > o ) {
    return super.compare( o );
  }


}
