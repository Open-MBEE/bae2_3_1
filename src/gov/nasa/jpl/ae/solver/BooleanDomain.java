/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.mbee.util.Random;

/**
 * @author bclement
 *
 */
public class BooleanDomain extends AbstractFiniteRangeDomain<Boolean> {

	public static BooleanDomain defaultDomain = new BooleanDomain();
	
//  protected boolean lowerBound = false;
//  protected boolean upperBound = true;

  public BooleanDomain() {
    this( false, true );
  }

  public BooleanDomain( BooleanDomain booleanDomain ) {
    super( booleanDomain );
  }

  public BooleanDomain( boolean lowerBound, boolean upperBound ) {
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
    //assert( lowerBound < upperBound );  // < not supported for boolean
    assert( lowerBound == false || upperBound == true || lowerBound == upperBound );
  }
  public BooleanDomain( RangeDomain< Boolean > domain ) {
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
	public int size() {
    if ( lowerBound == null || upperBound == null ) return 0;
	  if ( lowerBound == upperBound ) return 1;
		return 2;
	}

	/* (non-Javadoc)
	 * @see event.Domain#pickRandomValue()
	 */
	@Override
	public Boolean pickRandomValue() {
		return Random.global.nextBoolean();
	}

//	@Override
//	public String toString() {
//		return "[" + getLowerBound() + " " + getUpperBound() + "]";
//	}
//
	// REVIEW -- Sometimes ranges don't include upperBound, just the limit to it.
	@Override
  public boolean contains( Boolean t ) {
    return ( t != null && ( lowerBound.equals( t ) || upperBound.equals( t ) ) );
  }

  @Override
  public BooleanDomain clone() {
    return new BooleanDomain( this );
  }

  @Override
  public Class< Boolean > getType() {
    return Boolean.class;
  }

  @Override
  public Class< ? > getPrimitiveType() {
    return boolean.class;
  }

  @Override
  public Boolean getNthValue( long n ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean greater( Boolean t1, Boolean t2 ) {
    if ( t1 == null ) return false;
    if ( t2 == null ) return t1 != null;
    return t1 && !t2;
  }

  @Override
  public boolean less( Boolean t1, Boolean t2 ) {
    if ( t1 == null ) return t2 != null;
    if ( t2 == null ) return false;
    return !t1 && t2;
  }

  @Override
  public boolean greaterEquals( Boolean t1, Boolean t2 ) {
    if ( t1 == null ) return t2 == null;
    if ( t2 == null ) return true;
    return t1 || !t2;
  }

  @Override
  public boolean lessEquals( Boolean t1, Boolean t2 ) {
    if ( t1 == null ) return true;
    if ( t2 == null ) return t1 == null;
    return !t1 || t2;
  }

  @Override
  public Boolean getTypeMaxValue() {
    return true;
  }

  @Override
  public Boolean getTypeMinValue() {
    return false;
  }

  @Override
  public RangeDomain< Boolean > getDefaultDomain() {
    return defaultDomain;
  }

  @Override
  public void setDefaultDomain( Domain< Boolean > domain ) {
    if ( domain instanceof BooleanDomain ) {
      defaultDomain = (BooleanDomain)domain;
    } else if ( domain instanceof RangeDomain ) {
      defaultDomain = new BooleanDomain((RangeDomain< Boolean >)domain);
    }
  }
}
