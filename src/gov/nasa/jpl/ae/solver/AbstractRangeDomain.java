/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import gov.nasa.jpl.ae.event.ConstraintExpression;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Functions;
import gov.nasa.jpl.ae.event.Groundable;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;

/**
 *
 */
public abstract class AbstractRangeDomain< T > extends HasIdImpl
                        implements RangeDomain< T > {

  //protected static RangeDomain defaultDomain;
  protected T lowerBound = null;
  protected T upperBound = null;
  protected boolean lowerIncluded;
  protected boolean upperIncluded;
  protected boolean nullInDomain = false;
  
  //protected DomainListener owner;  // REVIEW ??
  //protected static Object typeMinValue;
  //protected static Object typeMaxValue;
  protected static Method isGroundedMethod = getIsGroundedMethod();
  
	
  public AbstractRangeDomain( T lowerBound, T upperBound,
                              boolean includeLowerBound,
                              boolean includeUpperBound,
                              boolean nullInDomain ) {
    setBounds( lowerBound, upperBound );
    this.lowerIncluded = includeLowerBound;
    this.upperIncluded = includeUpperBound;
    setNullInDomain(nullInDomain);
  }

  public AbstractRangeDomain() {
//    this( getTypeMinValue(), getTypeMaxValue() );
  }
  
  public AbstractRangeDomain(T lowerBound, T upperBound ) {
    setBounds( lowerBound, upperBound );
  }

  public AbstractRangeDomain(T lowerBound, T upperBound, boolean nullInDomain ) {
    setBounds( lowerBound, upperBound );
    this.nullInDomain = nullInDomain;
  }

	public AbstractRangeDomain( RangeDomain<T> domain ) {
	  this( domain.getLowerBound(), domain.getUpperBound(),
	        domain.includeLowerBound(), domain.includeUpperBound(),
	        domain.isNullInDomain() );
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
	
  @Override
	public boolean isEmpty() {
    return ( less( getUpperBound(), getLowerBound() ) ||
             ( equals( getUpperBound(), getLowerBound() ) &&
               !lowerIncluded && !upperIncluded ) );
  }


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


  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Wraps#getValue(boolean)
   */
  @Override
  public T getValue( boolean propagate ) {
    if ( this.size() == 1 ) {
      if ( getLowerBound() != null && lowerIncluded ) return getLowerBound();
      if ( getUpperBound() != null && upperIncluded ) return getUpperBound();
      return getLowerBound();
    }
    Debug.error( true ,"" );
    return null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Wraps#setValue(java.lang.Object)
   */
  @Override
  public void setValue( T value ) {
    setBounds( value, value );
    lowerIncluded = true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.jpl.ae.solver.Wraps#getPrimitiveType()
   */
  @Override
  public Class< ? > getPrimitiveType() {
    Class< ? > c = null;
    if ( getType() != null ) {
      c = ClassUtils.primitiveForClass( getType() );
      if ( c == null && getLowerBound() != null
           && Wraps.class.isInstance( getLowerBound() )
//           && ( lowerIncluded || !upperIncluded || upperBound == null ) 
           ) {
        c = ( (Wraps< ? >)getLowerBound() ).getPrimitiveType();
      }
      if ( (c == null || !lowerIncluded )&& getUpperBound() != null
           && Wraps.class.isInstance( getUpperBound() ) ) {
        Class<?> cu = ( (Wraps< ? >)getUpperBound() ).getPrimitiveType();
        if ( cu != null && ( c == null || !lowerIncluded ) ) {
          c = cu;
        }
      }
    }
    return c;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Wraps#getType()
   */
  @Override
  public Class< ? > getType() {
    if ( lowerBound != null && ( lowerIncluded || upperBound == null ) ) {
      return lowerBound.getClass();
    }
    if ( upperBound != null ) return upperBound.getClass();
    return null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Wraps#getTypeNameForClassName(java.lang.String)
   */
  @Override
  public String getTypeNameForClassName( String className ) {
    return ClassUtils.parameterPartOfName( className, false );
  }

  /**
   * @param n
   * @return the nth element in the domain counting from zero if there are at
   *         least n+1 elements, and the number of elements is countable.
   *         Otherwise, if the domain is uncountable and n is zero, return the
   *         lower bound; else return null.
   */
	public abstract T getNthValue( long n );
	
	/* (non-Javadoc)
	 * @see event.Domain#pickRandomValue()
	 */
	@Override
	public abstract T pickRandomValue();
	
  // TODO -- should probably just make this abstract and redefine instead of
  // trying to make it work for many types.
	public static <TT> TT pickRandomValue( AbstractRangeDomain<TT> d1,
	                                       AbstractRangeDomain<TT> d2 ) {
    long zl = d1.size();
    long zu = d2.size();
    long totalSize = zl + zu;
    if ( zl == 0 && zu == 0 ) return null;
    if ( zl == 0 ) return d2.pickRandomValue();
    if ( zu == 0 ) return d1.pickRandomValue();
    double totalSizeDouble = totalSize;
    Number wl = zl;
    Number wu = zu;
    if ( totalSize < 0 ) {
      wl = d1.width();
      wu = d2.width();
    }
    Number totalWidth = Functions.plus( wl, wu );
    totalSizeDouble = totalWidth.doubleValue();
    double r = Random.global.nextDouble() * totalSizeDouble;
    if ( r < wl.byteValue() ) {
      if ( wl instanceof Long || wl instanceof Integer ) {
        return d1.getNthValue( (long)r );
      }
      return Functions.plus( d1.getLowerBound(), (Double)r );
    }
    if ( wl instanceof Long || wl instanceof Integer ) {
      return d2.getNthValue( ((long)r) - zl );
    }
    return Functions.plus( d2.getLowerBound(),
                           (Double)( r - wl.doubleValue() ) );
	}
	
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.RangeDomain#width()
   */
	@Override
  public Number width() {
	  T lb = getLowerBound();
    T ub = getUpperBound();
    if ( lb instanceof Number && ub instanceof Number ) {
      T diff = Functions.minus( ub, lb );
      if ( diff instanceof Number ) return (Number)diff;
    }
    Debug.error( "width() needs to be redefined for "
                 + getClass().getCanonicalName() );
    return 0;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#pickRandomValueNotEqual()
   */
  @Override
  public T pickRandomValueNotEqual( T t ) {
    long z = size();
    if ( z == 0 ) return null;
    if ( z == 1 ) {
      if ( this.contains( t ) ) {
        return null;
      } else {
        return this.getNthValue( 0 );
      }
    }
    T tt = pickRandomValue();
    if ( tt.equals( t ) ) {
      // bummer -- better not loop on calling pickRandomValue()
      AbstractRangeDomain<T> dLower = createSubDomainBelow( t, false );
      AbstractRangeDomain<T> dUpper = createSubDomainAbove( t, false );
      tt = pickRandomValue( dLower, dUpper );
    }
    return tt;
  }
	
  public void makeEmpty() {
    lowerIncluded = true;
    upperIncluded = true;
    if ( getLowerBound() != null ) {
      setUpperBound( getLowerBound() );
    } else if ( getLowerBound() != null ) {
      setLowerBound( getUpperBound() );
    }
    lowerIncluded = false;
    upperIncluded = false;
  }

  public T min( T t1, T t2 ) {
    if ( lessEquals( t1, t2 ) ) return t1;
    return t2;
  }
  public T max( T t1, T t2 ) {
    if ( greaterEquals( t1, t2 ) ) return t1;
    return t2;
  }

  public AbstractRangeDomain<T> createSubDomainAbove( T t, boolean include ) {
    if ( t == null ) return null;
    AbstractRangeDomain<T> d = this.clone();
    boolean belowLb = less( t, getLowerBound() );
    boolean eqLb = t.equals( getLowerBound() );
    if ( !belowLb || eqLb ) {
      d.lowerIncluded = true; // temporary to make sure setUpperBound() works
    }
    if ( belowLb || eqLb || d.setLowerBound( t ) ) {
      if ( !belowLb || eqLb ) {
        d.lowerIncluded = include;
      }
      return d; 
    }
    d.makeEmpty();
    return d;
  }

  public AbstractRangeDomain<T> createSubDomainBelow( T t, boolean include ) {
    if ( t == null ) return null;
    AbstractRangeDomain<T> d = this.clone();
    boolean aboveUb = greater( t, getUpperBound() );
    boolean eqUb = t.equals( getUpperBound() );
    if ( !aboveUb || eqUb ) {
      d.upperIncluded = true; // temporary to make sure setUpperBound() works
    }
    if ( aboveUb || eqUb || d.setUpperBound( t ) ) {
      if ( !aboveUb || eqUb ) {
        d.upperIncluded = include;
      }
      return d; 
    }
    d.makeEmpty();
    return d;
  }

  public T pickRandomValueGreaterThanOrEqual( T t ) {
    return pickRandomValueGreater( t, true );
  }
  public T pickRandomValueGreaterThan( T t ) {
    return pickRandomValueGreater( t, false );
  }
  public T pickRandomValueGreater( T t, boolean include ) {
    if ( t == null ) t = getLowerBound();
    AbstractRangeDomain<T> d = createSubDomainAbove( t, include );
    if ( d != null ) {
      return d.pickRandomValue();
    }
    return null;
  }
  public T pickRandomValueLessThanOrEqual( T t ) {
    return pickRandomValueLess( t, true );
  }
  public T pickRandomValueLessThan( T t ) {
    return pickRandomValueLess( t, false );
  }
  public T pickRandomValueLess( T t, boolean include ) {
    if ( t == null ) t = getUpperBound();
    AbstractRangeDomain<T> d = createSubDomainBelow( t, include );
    if ( d != null ) {
      return d.pickRandomValue();
    }
    return null;
  }
  
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
    if ( greater( getLowerBound(), getTypeMinValue() ) ) {
      args = new Object[] { getLowerBound(), t.getValue( propagate ) };
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
      args = new Object[] { getLowerBound(), expr };
      cList.add( new ConstraintExpression( new FunctionCall( this, method,
                                                             args ) ) );
      gotBoundConstraint = true;
    }
    // upper bound constraint
    if ( less( getUpperBound(), getTypeMaxValue() ) ) {
      args = new Object[] { getUpperBound(), t.getValue( propagate ) };
      method = ClassUtils.getMethodForArgs( getClass(), "greaterEquals", args );
      Expression< T > expr = 
        new Expression< T >( new FunctionCall( t, Variable.class, "getValue",
                                               new Object[]{ propagate } ) );
      args = new Object[] { getUpperBound(), expr };

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

  @Override
  public boolean isNullInDomain() {
    return nullInDomain;
  }
  public void setNullInDomain( boolean b ) {
    nullInDomain = b;
  }

}
