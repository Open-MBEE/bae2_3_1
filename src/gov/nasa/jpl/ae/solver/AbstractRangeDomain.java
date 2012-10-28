/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.swing.text.AbstractDocument;

import gov.nasa.jpl.ae.event.ConstraintExpression;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Functions;
import gov.nasa.jpl.ae.event.Functions.NotEquals;
import gov.nasa.jpl.ae.event.Groundable;
import gov.nasa.jpl.ae.util.ClassUtils;
import gov.nasa.jpl.ae.util.Debug;

import org.junit.Assert;

/**
 *
 */
public abstract class AbstractRangeDomain< T >
                        implements RangeDomain< T > {

  //protected static RangeDomain defaultDomain;
  protected T lowerBound = null;
  protected T upperBound = null;
  protected boolean lowerIncluded;
  protected boolean upperIncluded;
  //protected DomainListener owner;  // REVIEW ??
  //protected static Object typeMinValue;
  //protected static Object typeMaxValue;
  protected static Method isGroundedMethod = getIsGroundedMethod();
  
	
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

  public boolean excludeLowerBound() {
    lowerIncluded = false;
    return this.size() != 0;
  }
  public boolean includeLowerBound() {
    lowerIncluded = true;
    return this.size() != 0;
  }
  public boolean isLowerBoundIncluded() {
    return lowerIncluded;
  }

  public boolean excludeUpperBound() {
    upperIncluded = false;
    return this.size() != 0;
  }
  public boolean includeUpperBound() {
    upperIncluded = true;
    return this.size() != 0;
  }
  public boolean isUpperBoundIncluded() {
    return upperIncluded;
  }


  
  public abstract T getTypeMaxValue();

  public abstract T getTypeMinValue();
  
  /**
   * @return the domain
   */
  @Override
  public abstract RangeDomain<T> getDefaultDomain();

  /**
   * @param domain the domain to set
   */
  @Override
  public abstract void setDefaultDomain( Domain< T > domain );

  
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

  public static Method getIsGroundedMethod() {
    if ( isGroundedMethod == null ) {
      try {
        isGroundedMethod =
            Groundable.class.getDeclaredMethod( "isGrounded",
                                                new Class<?>[]{ boolean.class,
                                                                Set.class } );
      } catch ( SecurityException e ) {
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        e.printStackTrace();
      }
    }
    return isGroundedMethod;
  }
  
  public Collection< Constraint > getConstraints( Variable< T > t ) {
    List< Constraint > cList= new ArrayList< Constraint >();
    Object args[] = null;
    Method method = null;
    boolean gotBoundConstraint = false;
    boolean propagate = false; // setting this to true can cause sets/maps of parameters/constraints to have problems
    // lower bound constraint
    if ( greater( lowerBound, getTypeMinValue() ) ) {
      args = new Object[] { lowerBound, t.getValue( propagate ) };
      method = ClassUtils.getMethodForArgs( getClass(), "lessEquals", args );
        //getClass().getMethod( "lessEquals", Class< ? >[]{} );
      Expression< T > expr = 
          new Expression< T >( new FunctionCall( t, Variable.class, "getValue",
                                                 new Object[]{ propagate } ) );
//      if ( t.getValue() instanceof Variable ) {
//        expr = 
//            new Expression< T >( new FunctionCall( null, Variable.class, "getValue",
//                                                   new Object[]{ propagate }, (FunctionCall)expr.expression ) );
//      }
      args = new Object[] { lowerBound, expr };
      cList.add( new ConstraintExpression( new FunctionCall( this, method,
                                                             args ) ) );
      gotBoundConstraint = true;
    }
    // upper bound constraint
    if ( less( upperBound, getTypeMaxValue() ) ) {
      args = new Object[] { upperBound, t.getValue( propagate ) };
      method = ClassUtils.getMethodForArgs( getClass(), "greaterEquals", args );
      Expression< T > expr = 
        new Expression< T >( new FunctionCall( t, Variable.class, "getValue",
                                               new Object[]{ propagate } ) );
      args = new Object[] { upperBound, expr };

      cList.add( new ConstraintExpression( new FunctionCall( this, method,
                                                             args ) ) );
      gotBoundConstraint = true;
    }
    // grounded constraint
    if ( t instanceof Groundable && !gotBoundConstraint ) {
      Groundable g = (Groundable)t;
      args = new Object[] { false, (Set< Groundable >)null };
      method = getIsGroundedMethod();
      if ( method != null ) {
        cList.add( new ConstraintExpression( new FunctionCall( g, method, args ) ) );
      }
      //Utils.getMethodForArgs( Groundable.class, "isGrounded", args );
    }
    return cList;
  }


}
