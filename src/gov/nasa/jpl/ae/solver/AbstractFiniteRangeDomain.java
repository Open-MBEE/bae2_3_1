/**
 * 
 */
package gov.nasa.jpl.ae.solver;

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
	  super( domain );
  }

	
	
  /* (non-Javadoc)
	 * @see event.Domain#isInfinite()
	 */
	@Override
	public boolean isInfinite() {
	  return false;
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
	
	/* (non-Javadoc)
	 * @see event.Domain#pickRandomValue()
	 */
	@Override
	public T pickRandomValue() {
		double r = Random.global.nextDouble();
		int n = (int)(((double)size()) * r);
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
