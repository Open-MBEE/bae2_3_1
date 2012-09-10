/**
 * 
 */
package gov.nasa.jpl.ae.solver;

/**
 * @author bclement
 *
 */
public class IntegerDomain extends AbstractFiniteRangeDomain< Integer > {

  public static final int typeMaxValue = Integer.MAX_VALUE;
  public static final int typeMinValue = Integer.MIN_VALUE;

  public static IntegerDomain domain = new IntegerDomain(); 
	public static IntegerDomain positiveDomain =
			new IntegerDomain(0, typeMaxValue);
  public static IntegerDomain defaultDomain = new IntegerDomain();

//	protected int lowerBound = Integer.MIN_VALUE;
//	protected int upperBound = Integer.MAX_VALUE;
	
	public IntegerDomain() {
	  lowerBound = typeMinValue;
	  upperBound = typeMaxValue;
	}
	
	public IntegerDomain(int minValue, int maxValue) {
		this.lowerBound = minValue;
		this.upperBound = maxValue;
	}

	public IntegerDomain( RangeDomain< Integer > domain ) {
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
    if ( lowerBound == upperBound ) return 1;
		return ((long)getUpperBound()) - ((long)getLowerBound());
	}

	/* (non-Javadoc)
	 * @see event.Domain#getLowerBound()
	 */
	@Override
	public Integer getLowerBound() {
		return lowerBound;
	}

	/* (non-Javadoc)
	 * @see event.Domain#getUpperBound()
	 */
	@Override
	public Integer getUpperBound() {
		return upperBound;
	}

	/* (non-Javadoc)
	 * @see event.Domain#pickRandomValue()
	 */
	@Override
	public Integer pickRandomValue() {
		//return (int) Math.abs( getLowerBound() + Math.random() * size() );
    // a bunch of tricks to avoid overflow
    double r1 = Random.global.nextDouble();
    double r2 = Random.global.nextDouble();
    double middle = getMiddleValue();
    double half = getUpperBound() - middle;
    if ( r1 < 0.5 ) {
      return (int)(middle - r2 * half);
    }
    return (int)(middle + r2 * half);
	}

	private Integer getMiddleValue() {
    // TODO -- should interpret null as zero 
    if ( lowerBound == null || upperBound == null ) return 0;
    return (int)(lowerBound + size()/2); // this is a floor
  }

  @Override
	public String toString() {
		return "[" + getLowerBound() + " " + getUpperBound() + "]";
	}

  @Override
  public boolean contains( Integer t ) {
    if ( t == null && 
         ( lowerBound != null  || upperBound != null ) ) return false;
    return lowerBound <= t && upperBound >= t;
  }

  // counts from zero!!!
  @Override
  public Integer getNthValue( long n ) {
    return (int)(((long)getLowerBound()) + n);
  }

  @Override
  public boolean greater( Integer t1, Integer t2 ) {
    return t1 > t2;
  }

  @Override
  public boolean less( Integer t1, Integer t2 ) {
    return t1 < t2;
  }

//  @Override
//  public boolean equals( Integer t1, Integer t2 ) {
//    return t1 >= t2;
//  }

  @Override
  public boolean greaterEquals( Integer t1, Integer t2 ) {
    return t1 >= t2;
  }

  @Override
  public boolean lessEquals( Integer t1, Integer t2 ) {
    return t1 <= t2;
  }

  @Override
  public AbstractFiniteRangeDomain< Integer > clone() {
    return new IntegerDomain( this );
  }

  @Override
  public Class< Integer > getType() {
    return Integer.class;
  }

  @Override
  public Class< ? > getPrimitiveType() {
    return int.class;
  }

  @Override
  public Integer getTypeMaxValue() {
    return typeMaxValue;
  }

  @Override
  public Integer getTypeMinValue() {
    return typeMinValue;
  }

  @Override
  public RangeDomain< Integer > getDefaultDomain() {
    return defaultDomain ;
  }

  @Override
  public void setDefaultDomain( Domain< Integer > domain ) {
    if ( domain instanceof IntegerDomain ) {
      defaultDomain = (IntegerDomain)domain;
    } else if ( domain instanceof RangeDomain ) {
      defaultDomain = new IntegerDomain((RangeDomain< Integer >)domain);
    }
  }
}
