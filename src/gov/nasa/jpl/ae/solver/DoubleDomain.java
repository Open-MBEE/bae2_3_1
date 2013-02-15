/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.ae.util.Debug;

import org.junit.Assert;

/**
 * @author bclement
 *
 */
public class DoubleDomain extends AbstractRangeDomain< Double > {

  protected static final Double typeMinValue = -Double.MAX_VALUE;
  protected static final Double typeMaxValue = Double.MAX_VALUE;

  public static DoubleDomain defaultDomain = new DoubleDomain();
  protected static final DoubleDomain positiveDomain =
      new DoubleDomain( 0.0, typeMaxValue );
  protected static final DoubleDomain negativeDomain =
      new DoubleDomain( typeMinValue, 0.0 );

//  protected double lowerBound = ((Double)getTypeMinValue()).doubleValue();
//  protected double upperBound = ((Double)getTypeMaxValue()).doubleValue();

  /**
   * 
   */
  public DoubleDomain() {
    super();
    lowerBound = ((Double)getTypeMinValue()).doubleValue();
    upperBound = ((Double)getTypeMaxValue()).doubleValue();
  }

  /**
   * @param lowerBound
   * @param upperBound
   */
  public DoubleDomain( Double lowerBound, Double upperBound ) {
    super( lowerBound, upperBound );
  }

  /**
   * @param domain
   */
  public DoubleDomain( RangeDomain< Double > domain ) {
    super( domain );
  }

  @Override
  public Double getTypeMaxValue() {
    return typeMaxValue;
  }

  @Override
  public Double getTypeMinValue() {
    return typeMinValue;
  }

  /* (non-Javadoc)
   * @see solver.AbstractRangeDomain#isInfinite()
   */
  @Override
  public boolean isInfinite() {
    return true;
  }

  /* (non-Javadoc)
   * @see solver.AbstractRangeDomain#size()
   */
  @Override
  public long size() {
    if ( lowerBound == null || upperBound == null ) return 0;
    if ( lowerBound.equals( upperBound ) ) return 1;
    return -1;
  }

  /* (non-Javadoc)
   * @see solver.AbstractRangeDomain#getNthValue(int)
   */
  @Override
  public Double getNthValue( long n ) {
    return null;
  }

  public Double getMiddleValue() {
    // TODO -- should interpret null as zero 
    if ( lowerBound == null || upperBound == null ) return 0.0;
    return 0.5 * getLowerBound() + 0.5 * getUpperBound();
  }
  
  /* (non-Javadoc)
   * @see solver.AbstractRangeDomain#pickRandomValue()
   */
  @Override
  public Double pickRandomValue() {
    // a bunch of tricks to avoid overflow
    if ( this.isEmpty() ) {
      return null;
    }
    double r1 = Random.global.nextDouble();
    double r2 = Random.global.nextDouble();
    double middle = getMiddleValue();
    double half = getUpperBound() - middle;
    if ( r1 < 0.5 ) {
      return middle - r2 * half;
    }
    return middle + r2 * half;
  }

  /* (non-Javadoc)
   * @see solver.AbstractRangeDomain#greater(java.lang.Comparable, java.lang.Comparable)
   */
  @Override
  public boolean greater( Double t1, Double t2 ) {
    if ( t1 == null ) return false;
    if ( t2 == null ) return t1 != null;
    return t1 > t2;
  }

  /* (non-Javadoc)
   * @see solver.AbstractRangeDomain#less(java.lang.Comparable, java.lang.Comparable)
   */
  @Override
  public boolean less( Double t1, Double t2 ) {
    if ( t1 == null ) return t2 != null;
    if ( t2 == null ) return false;
    return t1 < t2;
  }

//  /* (non-Javadoc)
//   * @see solver.AbstractRangeDomain#equals(java.lang.Comparable, java.lang.Comparable)
//   */
//  @Override
//  public boolean equals( Double t1, Double t2 ) {
//    return t1.equals( t2 );
//  }

  /* (non-Javadoc)
   * @see solver.AbstractRangeDomain#greaterEquals(java.lang.Comparable, java.lang.Comparable)
   */
  @Override
  public boolean greaterEquals( Double t1, Double t2 ) {
    if ( t1 == null ) return t2 == null;
    if ( t2 == null ) return true;
    return t1 >= t2;
  }

  /* (non-Javadoc)
   * @see solver.AbstractRangeDomain#lessEquals(java.lang.Comparable, java.lang.Comparable)
   */
  @Override
  public boolean lessEquals( Double t1, Double t2 ) {
    if ( t1 == null ) return true;
    if ( t2 == null ) return t1 == null;
    return t1 <= t2;
  }

  @Override
  public Double getLowerBound() {
    return lowerBound;
  }
  
  @Override
  public Double getUpperBound() {
    return upperBound;
  }
  
  /* (non-Javadoc)
   * @see solver.AbstractRangeDomain#clone()
   */
  @Override
  public DoubleDomain clone() {
    return new DoubleDomain( this );
  }

  /* (non-Javadoc)
   * @see solver.AbstractRangeDomain#getType()
   */
  @Override
  public Class< Double > getType() {
    return Double.class;
  }

  /* (non-Javadoc)
   * @see solver.AbstractRangeDomain#getPrimitiveType()
   */
  @Override
  public Class< ? > getPrimitiveType() {
    return double.class;
  }
  
  public static void main( String args[] ) {
    DoubleDomain d = new DoubleDomain();
    if ( Debug.isOn() ) Debug.outln( " new d = " + d );
  }

  @Override
  public RangeDomain< Double > getDefaultDomain() {
    return defaultDomain;
  }

  @Override
  public void setDefaultDomain( Domain< Double > domain ) {
    if ( domain instanceof DoubleDomain ) {
      defaultDomain = (DoubleDomain)domain;
    } else if ( domain instanceof RangeDomain ) {
      defaultDomain = new DoubleDomain((RangeDomain< Double >)domain);
    }
  }

}
