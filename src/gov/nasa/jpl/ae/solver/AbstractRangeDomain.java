/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.text.AbstractDocument;

import gov.nasa.jpl.ae.event.ConstraintExpression;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Functions;
import gov.nasa.jpl.ae.event.Functions.NotEquals;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Utils;

import org.junit.Assert;

/**
 * @author bclement
 *
 */
public abstract class AbstractRangeDomain< T  >
                        implements RangeDomain< T > {

  //protected static RangeDomain defaultDomain;
  protected T lowerBound = null;
  protected T upperBound = null;
  //protected DomainListener owner;  // REVIEW ??
  //protected static Object typeMinValue;
  //protected static Object typeMaxValue;
	
  public AbstractRangeDomain() {
//    this( getTypeMinValue(), getTypeMaxValue() );
  }
  
  public AbstractRangeDomain(T lowerBound, T upperBound) {
    setBounds( lowerBound, upperBound );
  }

	public AbstractRangeDomain( RangeDomain<T> domain ) {
	  this( domain.getLowerBound(), domain.getUpperBound() );
  }

  public void restrictToValue( T v ) {
    setBounds( v, v );
  }

	
  /* (non-Javadoc)
	 * @see event.Domain#isInfinite()
	 */
	@Override
	public abstract boolean isInfinite();

	/* (non-Javadoc)
	 * @see event.Domain#size()
	 */
	@Override
	public abstract long size();

	/* (non-Javadoc)
	 * @see event.Domain#getLowerBound()
	 */
	@Override
	public T getLowerBound() {
		return lowerBound;
	}

	/* (non-Javadoc)
	 * @see event.Domain#getUpperBound()
	 */
	@Override
	public T getUpperBound() {
		return upperBound;
	}

	public abstract T getNthValue( long n );
	
	/* (non-Javadoc)
	 * @see event.Domain#pickRandomValue()
	 */
	@Override
	public abstract T pickRandomValue();
	
	@Override
	public String toString() {
	  if ( getLowerBound() == null || getUpperBound() == null ) {
	    return "[]";
	  } 
	  // REVIEW -- Is this okay or should we just have [lb,ub] for this case?
	  if ( getLowerBound() == getUpperBound() ) {
	    return "[" + getLowerBound() + "]";
	  }
		return "[" + getLowerBound() + ", " + getUpperBound() + "]";
	}

  //public abstract RangeDomain() fromString(String);

	public abstract boolean greater( T t1, T t2 ); 
  public abstract boolean less( T t1, T t2 );
  public boolean equals( T t1, T t2 ) {
    return t1.equals( t2 );
  }
  //public abstract boolean equals( T t1, T t2 ); 
  public abstract boolean greaterEquals( T t1, T t2 ); 
  public abstract boolean lessEquals( T t1, T t2 );
  public boolean notEquals( T t1, T t2 ) {
    return !equals( t1, t2 );
  }
	
	@Override
  public boolean contains( T t ) {
	  if ( t == null ) return lowerBound == null && upperBound == null;
    return lessEquals( lowerBound, t ) && greaterEquals( upperBound, t );
  }

  @Override
  public abstract AbstractRangeDomain< T > clone();
// Redefine as
//  {
//    return new MyRangeDomain( this );
//  }

  public boolean intersectRestrict( AbstractRangeDomain<T> o ) {
    if ( less(lowerBound, o.lowerBound) ) {
      lowerBound = o.lowerBound;
    }
    if ( greater(upperBound, o.upperBound) ) {
      upperBound = o.upperBound;
    }
    return this.size() != 0;
  }
  
  public abstract T getTypeMaxValue();
//  {
//    //Debug.outln("abstract max = " + typeMaxValue );
//    Assert.fail("Need to override getTypeMaxValue in subclass of AbstractRangeDomain");
//    return null;//typeMaxValue;
//  }
////  public static synchronized void setTypeMaxValue( Object max ) {
////    typeMaxValue = max;
////  }

  public abstract T getTypeMinValue();
//  public static synchronized Object getTypeMinValue() {
//    Assert.fail("Need to override getTypeMaxValue in subclass of AbstractRangeDomain");
//    return null;
//    //return typeMinValue;
//  }
////  public static synchronized void setTypeMinValue( Object min ) {
////    typeMinValue = min;
////  }
  
  /**
   * @return the domain
   */
  @Override
  public abstract RangeDomain<T> getDefaultDomain();
//  {
//    return defaultDomain;
//  }

  /**
   * @param domain the domain to set
   */
  @Override
  public abstract void setDefaultDomain( Domain< T > domain );
//  {
//    AbstractRangeDomain.defaultDomain = domain;
//  }



  
  @Override
  public abstract Class< T > getType();

  @Override
  public abstract Class< ? > getPrimitiveType();
 
  @Override
  public boolean setLowerBound( T lowerBound ) {
    if ( lessEquals(lowerBound, this.upperBound ) ) {
      this.lowerBound = lowerBound;
      return true;
    }
    return false;
  }

  @Override
  public boolean setUpperBound( T upperBound ) {
    if ( lessEquals( this.lowerBound, upperBound ) ) {
      this.upperBound = upperBound;
      return true;
    }
    return false;
  }

  public boolean setBounds( T lowerBound, T upperBound ) {
    if ( less( lowerBound, upperBound ) ) {
      this.lowerBound = lowerBound;
      this.upperBound = upperBound;
      return true;
    }
    return false;
  }

  public Collection< Constraint > getConstraints( T t ) {
    List< Constraint > cList= new ArrayList< Constraint >();
    Object args[] = null;
    Method method = null;
    if ( !lowerBound.equals( getTypeMinValue() ) ) {
      args = new Object[] { lowerBound, t };
      method = Utils.getMethodForArgs( getClass(), "lessEquals", args );
        //getClass().getMethod( "lessEquals", Class< ? >[]{} );
      cList.add( new ConstraintExpression( new FunctionCall( this, method,
                                                           args ) ) );
    }
    if ( !upperBound.equals( getTypeMaxValue() ) ) {
      args = new Object[] { upperBound, t };
      method = Utils.getMethodForArgs( getClass(), "greaterEquals", args );
      cList.add( new ConstraintExpression( new FunctionCall( this, method,
                                                             args ) ) );
    }
    return cList;
  }


}
