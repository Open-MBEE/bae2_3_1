/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Random;

/**
 * @author bclement
 *
 */
public abstract class AbstractFiniteRangeDomain< T extends Comparable< T > >
                        extends AbstractRangeDomain< T > {

//  protected static RangeDomain domain;
//  protected T lowerBound;
//  protected T upperBound;
//  protected static Object typeMinValue;
//  protected static Object typeMaxValue;
	
  public AbstractFiniteRangeDomain() {
    super();
  }
  
//  public static synchronized Object getTypeMaxValue() {
//    return typeMaxValue;
//  }
//  public static synchronized void setTypeMaxValue( Object max ) {
//    typeMaxValue = max;
//  }
//
//  public static synchronized Object getTypeMinValue() {
//    return typeMinValue;
//  }
//  public static synchronized void setTypeMinValue( Object min ) {
//    typeMinValue = min;
//  }
//  
//  /**
//   * @return the domain
//   */
//  @Override
//  public synchronized RangeDomain<T> getDefaultDomain() {
//    return domain;
//  }
//
//  /**
//   * @param domain the domain to set
//   */
//  public static synchronized void setDefaultDomain( RangeDomain domain ) {
//    AbstractFiniteRangeDomain.domain = domain;
//  }



  public AbstractFiniteRangeDomain(T lowerBound, T upperBound) {
    super( lowerBound, upperBound );
  }

  public AbstractFiniteRangeDomain( RangeDomain<T> domain ) {
    super(domain);
    fixToIncludeBounds();
  }
  	
  public AbstractFiniteRangeDomain( T lowerBound, T upperBound,
                              boolean includeLowerBound,
                              boolean includeUpperBound,
                              boolean nullInDomain ) {
    super( lowerBound, upperBound, includeLowerBound, includeUpperBound,
           nullInDomain );
    fixToIncludeBounds();
  }
  
  /**
   * Set the bounds such that they are included if possible without changing the
   * the represented range. This helps get avoid special cases where inherited
   * functions would otherwise fail. It also makes it easier to read. For
   * example, (2,4] becomes [3,4], and [5,6) becomes [5].
   * 
   * @return whether the bounds were changed
   */
  public boolean fixToIncludeBounds() {
    boolean changed = false;
    if (!lowerIncluded) {
      T t = getNextGreaterValue( lowerBound );
      if ( t != null ) {
        lowerBound = t;
        lowerIncluded = true;
        changed = true;
      }
    }
    if (!upperIncluded) {
      T t = getPreviousLesserValue( upperBound );
      if ( t != null ) {
        upperBound = t;
        upperIncluded = true;
        changed = true;
      }
    }
    return changed;
  }
	
	
  /* (non-Javadoc)
	 * @see event.Domain#isInfinite()
	 */
	@Override
	public boolean isInfinite() {
	  return false;
	}

  @Override
  public AbstractFiniteRangeDomain<T> createSubDomainAbove( T t, boolean include ) {
    AbstractRangeDomain<T> ard = super.createSubDomainAbove( t, include );
    if ( ard instanceof AbstractFiniteRangeDomain ) {
      AbstractFiniteRangeDomain<T> afrd = (AbstractFiniteRangeDomain<T>)ard;
      afrd.fixToIncludeBounds();
      return afrd;
    }
    Debug.error( true, true, "ERROR! createSubDomainAbove() failed to cast to AbstractFiniteRangeDomain!");
    return null;
  }
	
  @Override
  public AbstractFiniteRangeDomain<T> createSubDomainBelow( T t, boolean include ) {
    AbstractRangeDomain<T> ard = super.createSubDomainBelow( t, include );
    if ( ard instanceof AbstractFiniteRangeDomain ) {
      AbstractFiniteRangeDomain<T> afrd = (AbstractFiniteRangeDomain<T>)ard;
      afrd.fixToIncludeBounds();
      return afrd;
    }
    Debug.error( true, true, "ERROR! createSubDomainBelow() failed to cast to AbstractFiniteRangeDomain!");
    return null;
  }
  
  @Override
  public boolean intersectRestrict( AbstractRangeDomain<T> o ) {
    boolean r = super.intersectRestrict( o );
    fixToIncludeBounds();
    return r;
  }
  
  @Override
  public < TT > boolean restrictTo( Domain< TT > domain ) {
    boolean r = super.restrictTo( domain );
    fixToIncludeBounds();
    return r;
  }
  
  @Override
  public < TT > Domain<TT> subtract( Domain< TT > domain ) {
    Domain<TT> dd = super.subtract( domain );
    if ( dd instanceof AbstractFiniteRangeDomain ) {
      AbstractFiniteRangeDomain<?> afrd = (AbstractFiniteRangeDomain<?>)dd;
      afrd.fixToIncludeBounds();
    }
    return dd;
  }
  
  @Override
  public boolean subtract( AbstractRangeDomain<T> o ) {
    boolean changed = super.subtract( o );
    fixToIncludeBounds();
    return changed;
  }
	
  @Override
  public boolean lessEquals( T t1 ) {
    fixToIncludeBounds();
    return super.lessEquals( t1 );
  }

  @Override
  public boolean greaterEquals( T t1 ) {
    fixToIncludeBounds();
    return super.greaterEquals( t1 );
  }

  
//	/* (non-Javadoc)
//	 * @see event.Domain#size()
//	 */
//	@Override
//	public abstract long size();
//
//	/* (non-Javadoc)
//	 * @see event.Domain#getLowerBound()
//	 */
//	@Override
//	public T getLowerBound() {
//		return lowerBound;
//	}
//
//	/* (non-Javadoc)
//	 * @see event.Domain#getUpperBound()
//	 */
//	@Override
//	public T getUpperBound() {
//		return upperBound;
//	}

	// count from zero!!!
	public abstract T getNthValue( long n );
	
  /**
   * Return the smallest value greater than the input value. Neither value is
   * required to be in the domain.
   * 
   * @param t
   * @return the next greater value or null if there is no such value
   */
	public abstract T getNextGreaterValue( T t );
	
  /**
   * Return the greatest value less than the input value. Neither value is
   * required to be in the domain.
   * 
   * @param t
   * @return the previous lesser value or null if there is no such value
   */
  public abstract T getPreviousLesserValue( T t );
	
	/* (non-Javadoc)
	 * @see event.Domain#pickRandomValue()
	 */
	@Override
	public T pickRandomValue() {
		double r = Random.global.nextDouble();
		long n = (long)(((double)size()) * r);
		return getNthValue( n ); // counts from 0!!
	}
	
//	@Override
//	public String toString() {
//		return "[" + getLowerBound() + ", " + getUpperBound() + "]";
//	}

  //public abstract RangeDomain() fromString(String);

	public abstract boolean greater( T t1, T t2 ); 
  public abstract boolean less( T t1, T t2 ); 
//  public abstract boolean equals( T t1, T t2 ); 
  public abstract boolean greaterEquals( T t1, T t2 ); 
  public abstract boolean lessEquals( T t1, T t2 );
//  public boolean notEquals( T t1, T t2 ) {
//    return !equals( t1, t2 );
//  }
	
//	@Override
//  public boolean contains( T t ) {
//    return lessEquals( lowerBound, t ) && greaterEquals( upperBound, t );
//  }

  @Override
  public abstract AbstractFiniteRangeDomain< T > clone();
// Redefine as
//  {
//    return new MyFiniteRangeDomain( this );
//  }

//  @Override
//  public Domain< T > getDefaultDomain() {
//    return domain;
//  }

//  @Override
//  public abstract Class< T > getType();
//
//  @Override
//  public abstract Class< ? > getPrimitiveType();
 
//  @Override
//  public boolean setLowerBound( T lowerBound ) {
//    if ( lessEquals(lowerBound, this.upperBound ) ) {
//      this.lowerBound = lowerBound;
//      return true;
//    }
//    return false;
//  }
//
//  @Override
//  public boolean setUpperBound( T upperBound ) {
//    if ( lessEquals( this.lowerBound, upperBound ) ) {
//      this.upperBound = upperBound;
//      return true;
//    }
//    return false;
//  }
//
//  public boolean setBounds( T lowerBound, T upperBound ) {
//    if ( less( lowerBound, upperBound ) ) {
//      this.lowerBound = lowerBound;
//      this.upperBound = upperBound;
//      return true;
//    }
//    return false;
//  }


}
