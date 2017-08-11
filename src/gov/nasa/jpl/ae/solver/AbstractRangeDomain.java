/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import gov.nasa.jpl.ae.event.ConstraintExpression;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Functions;
import gov.nasa.jpl.ae.event.Groundable;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Random;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Evaluatable;
import gov.nasa.jpl.mbee.util.Wraps;

/**
 *
 */
/**
 * @author bclement
 *
 * @param <T>
 */
public abstract class AbstractRangeDomain< T > extends HasIdImpl
                        implements RangeDomain< T > {

  //protected static RangeDomain defaultDomain;
  protected T lowerBound = null;
  protected T upperBound = null;
  /**
   * Whether the lower bound itself is considered part of the domain or just
   * values greater than the lower bound (that are within the upper bound).
   */
  protected boolean lowerIncluded;
  /**
   * Whether the upper bound itself is considered part of the domain or just
   * values less than the upper bound (that are within the lower bound).
   */
  protected boolean upperIncluded;
  public boolean nullInDomain = false;
  
  //protected DomainListener owner;  // REVIEW ??
  //protected static Object typeMinValue;
  //protected static Object typeMaxValue;
  protected static Method isGroundedMethod = getIsGroundedMethod();
  
	
  public AbstractRangeDomain( T lowerBound, T upperBound,
                              boolean includeLowerBound,
                              boolean includeUpperBound,
                              boolean nullInDomain ) {
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
    this.lowerIncluded = includeLowerBound;
    this.upperIncluded = includeUpperBound;
    this.nullInDomain = nullInDomain;
  }

  public AbstractRangeDomain() {
//    this( getTypeMinValue(), getTypeMaxValue() );
  }
  
  public AbstractRangeDomain(T lowerBound, T upperBound ) {
    lowerIncluded = true;
    upperIncluded = true;
    setBounds( lowerBound, upperBound );
  }

  public AbstractRangeDomain(T lowerBound, T upperBound, boolean nullInDomain ) {
    this(lowerBound, upperBound);
    this.nullInDomain = nullInDomain;
  }

	public AbstractRangeDomain( RangeDomain<T> domain ) {
	  this( domain.getLowerBound(), domain.getUpperBound(),
	        domain.isLowerBoundIncluded(), domain.isUpperBoundIncluded(),
	        domain.isNullInDomain() );
  }
	
  /**
   * Copy the attributes of the input domain.
   * 
   * @param domain
   * @return true iff any change was made
   */
	public boolean copy( AbstractRangeDomain< T > domain ) {
	  if ( equals(domain) ) return false;
    this.lowerBound = domain.lowerBound;
    this.upperBound = domain.upperBound;
    this.lowerIncluded = domain.lowerIncluded;
    this.upperIncluded = domain.lowerIncluded;
    this.nullInDomain = domain.nullInDomain;
    return true;
	}

	@Override
  public boolean restrictToValue( T v ) {
    if ( !contains( v ) ) {
      if ( magnitude() > 0 ) {
        clearValues();
        return true;
      }
      return false;
    }
    boolean restricted = setBounds( v, v );
    return restricted;
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
	public long magnitude() {
	  return size();
	}
	
  @Override
	public boolean isEmpty() {
    if ( getLowerBound() == null || getUpperBound() == null ) {
      return true;
    }
    return ( less( getUpperBound(), getLowerBound() ) ||
             ( equals( getUpperBound(), getLowerBound() ) &&
               (!lowerIncluded || !upperIncluded) ) );
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
   * @see gov.nasa.jpl.mbee.util.Wraps#getValue(boolean)
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
   * @see gov.nasa.jpl.mbee.util.Wraps#setValue(java.lang.Object)
   */
  @Override
  public void setValue( T value ) {
    setBounds( value, value );
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.jpl.mbee.util.Wraps#getPrimitiveType()
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
   * @see gov.nasa.jpl.mbee.util.Wraps#getType()
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
   * @see gov.nasa.jpl.mbee.util.Wraps#getTypeNameForClassName(java.lang.String)
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
    Number totalWidth;
    try {
      totalWidth = Functions.plus( wl, wu );
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
    } catch ( ClassCastException e ) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    } catch ( IllegalAccessException e ) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    } catch ( InstantiationException e ) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    }
    return null;
	}
	
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.RangeDomain#width()
   */
	@Override
  public Number width() {
	  T lb = getLowerBound();
    T ub = getUpperBound();
    if ( lb instanceof Number && ub instanceof Number ) {
      T diff = null;
      try {
        diff = Functions.minus( ub, lb );
      } catch ( ClassCastException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      }
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
	
  @Override
  public boolean clearValues() {
    boolean e1 = isEmpty();
    if (!e1) {
      makeEmpty();
      return true;
    }
    return false;
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
    try{
      t = (T)ClassUtils.castNumber( (Number)t, getType() );
    }catch( ClassCastException e ){
      //ignore
    }
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
    if ( !aboveUb || eqUb ) { //what should be here?
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
    if ( getLowerBound() == null || getUpperBound() == null || isEmpty() ) {
      return "[]";
    }
	  // REVIEW -- Is this okay or should we just have [lb,ub] for this case?
	  if ( equals(getLowerBound(), getUpperBound()) ) {
	    return "[" + getLowerBound() + "]";
	  }
    String lc = isLowerBoundIncluded() ? "[" : "(";
    String uc = isUpperBoundIncluded() ? "]" : ")";
		return lc + getLowerBound() + ", " + getUpperBound() + uc;
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

  public boolean less( T t1 ) {
    if ( t1 == null || upperBound == null ) return false;
    return greater(t1, upperBound) || (!isUpperBoundIncluded() && equals(t1, upperBound));
  }
  // WARNING! This fails for finite domains, where if upper is not included
  // and t1 is is the previous value to upper, then this should return true,
  // but it will return false;
  public boolean lessEquals( T t1 ) {
    if ( t1 == null || upperBound == null ) return false;
    return greaterEquals(t1, upperBound);
  }
  public boolean greater( T t1 ) {
    if ( t1 == null || lowerBound == null ) return false;
    return less(t1, lowerBound) || (!isLowerBoundIncluded() && equals(t1, lowerBound));
  }
  // WARNING! This fails for finite domains, where if lower is not included
  // and t1 is is the next value after lower, then this should return true,
  // but it will return false;
  public boolean greaterEquals( T t1 ) {
    if ( t1 == null || lowerBound == null ) return false;
    return lessEquals(t1, lowerBound);
  }

  public boolean less( AbstractRangeDomain<T> t1 ) {
    if ( t1 == null ) return false;
    return less(t1.lowerBound) || t1.greater(upperBound);
  }
  public boolean lessEquals( AbstractRangeDomain<T> t1 ) {
    if ( t1 == null ) return false;
    return lessEquals(t1.lowerBound) || t1.greaterEquals(upperBound);
  }
  public boolean greater( AbstractRangeDomain<T> t1 ) {
    if ( t1 == null ) return false;
    return greater(t1.upperBound) || t1.less(lowerBound);
  }
  public boolean greaterEquals( AbstractRangeDomain<T> t1 ) {
    if ( t1 == null ) return false;
    return greaterEquals(t1.upperBound) || t1.lessEquals(lowerBound);
  }
  
  public static <TT> boolean less( AbstractRangeDomain<TT> t1, TT t2 ) {
    return t1.less( t2 );
  }
  public static <TT> boolean lessEquals( AbstractRangeDomain<TT> t1, TT t2 ) {
    return t1.lessEquals( t2 );
  }
  public static <TT> boolean greater( AbstractRangeDomain<TT> t1, TT t2 ) {
    return t1.greater( t2 );
  }
  public static <TT> boolean greaterEquals( AbstractRangeDomain<TT> t1, TT t2 ) {
    return t1.greaterEquals( t2 );
  }

  public static <TT> boolean less( TT t1, AbstractRangeDomain<TT> t2 ) {
    if ( t1 == null || t2 == null || t2.lowerBound == null ) return false;
    return t2.greaterEquals( t1 );
  }
  public static <TT> boolean lessEquals( TT t1, AbstractRangeDomain<TT> t2 ) {
    if ( t1 == null || t2 == null || t2.lowerBound == null ) return false;
    return t2.less( t1 );
  }
  public boolean greater( T t1, AbstractRangeDomain<T> t2 ) {
    if ( t1 == null || t2 == null || t2.upperBound == null ) return false;
    return t2.lessEquals( t1 );
  }
  public boolean greaterEquals( T t1, AbstractRangeDomain<T> t2 ) {
    if ( t1 == null || t2 == null || t2.upperBound == null ) return false;
    return t2.less( t1 );
  }

  
	@Override
  public boolean contains( T t ) {
	  if ( t == null ) return lowerBound == null && upperBound == null;
	  if ( magnitude() == 0 ) return false;
	  if ( equals(t, lowerBound) ) return isLowerBoundIncluded();
    if ( equals(t, upperBound) ) return isUpperBoundIncluded();
    return lessEquals( lowerBound, t ) && greaterEquals( upperBound, t );
  }

  public boolean contains( T t, boolean strictly ) {
    boolean containsNotStrict = contains( t );
    if ( !containsNotStrict ) {
      return false;
    }
    if ( !strictly ) {
      return true;
    }
    if ( equals(t, lowerBound) ) return false;
    if ( equals(t, upperBound) ) return false;
    return true;
  }

	
  @Override
  public abstract AbstractRangeDomain< T > clone();
// Redefine as
//  {
//    return new MyRangeDomain( this );
//  }

  @Override
  public boolean equals( Object obj ) {
    if ( obj == null ) {
      throw new NullPointerException( "Error! Cannot check equality on a null domain.");
    }
    if (this == obj) return true;
    int comp = compare(obj);
    return comp == 0;
  }
  
  public int compare( Object obj ) {
    if ( obj == null ) {
      throw new NullPointerException( "Error! Cannot compare against a null.");
    }
    if ( this == obj ) return 0;
    if ( obj instanceof ComparableDomain ) {
      ComparableDomain<?> r = (ComparableDomain<?>)obj;
      int comp = this.compare( r );
      return comp;
    }
    int comp = CompareUtils.compare( this, obj );
    return comp;
  }

  public int compare( ComparableDomain< ? > r ) {
    if ( r == null ) {
      throw new NullPointerException( "Error! Cannot compare against a null domain.");
    }
    if (this == r) return 0;
    //if ( this.equals( r ) ) return 0;
    if ( this.isEmpty() && r.isEmpty() ) return 0;
    if ( this.isEmpty()  ) return -1;
    if ( r.isEmpty() ) return 1;
    int comp = CompareUtils.compare( getLowerBound(), r.getLowerBound() );
    if ( comp != 0 ) return comp;
    if ( !isLowerBoundIncluded() && r.isLowerBoundIncluded() ) return -1;
    if ( isLowerBoundIncluded() && !r.isLowerBoundIncluded() ) return 1;
    comp = CompareUtils.compare( getUpperBound(), r.getUpperBound() );
    if ( comp != 0 ) return comp;
    if ( !isUpperBoundIncluded() && r.isUpperBoundIncluded() ) return -1;
    if ( isUpperBoundIncluded() && !r.isUpperBoundIncluded() ) return 1;
    if ( !isInfinite() && r.isInfinite() ) return -1;
    if ( isInfinite() && !r.isInfinite() ) return 1;
    comp = CompareUtils.compare( magnitude(), r.magnitude() );
    if ( comp != 0 ) return comp;
    return 0;
  }

  @Override
  public int compareTo( ComparableDomain< T > r ) {
    return compare( r );
  }

  /**
   * 
   * @param ard
   * @return true iff the end of this range meets the beginning of the input
   *         range with overlap of no more than one point, and the meeting point
   *         must be included by at least one of the two domains.
   */
  public boolean meets( AbstractRangeDomain< T > ard ) {
    if ( getUpperBound() == null || ard.getLowerBound() == null ) return false;
    if ( equals( getUpperBound(), ard.getLowerBound() )
         && ( isUpperBoundIncluded() || ard.isLowerBoundIncluded() ) ) {
      return true;
    }
    return false;
  }
  
  /**
   * Modify this domain to be the union of it and the input domain iff they
   * overlap or meet.
   * 
   * @param ard
   * @return
   */
  public boolean union( AbstractRangeDomain<T> ard ) {
    if ( ard == null ) return false;
    if ( contains(ard, false) ) return false;
    if ( !overlaps( ard ) && !meets(ard) && !ard.meets(this) ) return false;
    T lb = getLowerBound();
    T ub = getUpperBound();
    T olb = ard.getLowerBound();
    T oub = ard.getUpperBound();
    if ( lb == null || ub == null || olb == null || oub == null ) return false;
    if ( lessEquals( olb, lb ) ) {
      setLowerBound( olb );
      if ( ard.isLowerBoundIncluded() ) {
        includeLowerBound();
      }
    }
    if ( greaterEquals( oub, ub ) ) {
      setUpperBound( oub );
      if ( ard.isUpperBoundIncluded() ) {
        includeUpperBound();
      }
    }
    return true;
  }

  public static <TT> Domain<TT> union( AbstractRangeDomain<TT> ard1, AbstractRangeDomain<TT> ard2 ) {
    AbstractRangeDomain<TT> ardUnion = ard1.clone();
    boolean changed = ardUnion.union( ard2 );
    if ( changed ) return ardUnion;
    MultiDomain< TT > md =
        new MultiDomain< TT >( (Class< TT >)ard1.getType(),
                               Utils.newSet( (Domain< TT >)ardUnion,
                                             (Domain< TT >)ard2.clone() ),
                               null );
    return md;
  }
  
  /**
   * Restrict this domain to its intersection with the input domain.
   * 
   * @param o
   * @return true iff there is an intersection, in which case this domain is not
   *         empty.
   */
  public boolean intersectRestrict( AbstractRangeDomain<T> o ) {
    T lb = (T)ClassUtils.evaluate( o.lowerBound, getType(), true );
    if ( lessEquals( lowerBound, lb) ) {
      lowerBound = lb;
      if ( !o.isLowerBoundIncluded() ) excludeLowerBound();
      //else if ( equals( lowerBound, o.lowerBound ) && includeLowerBound()
    }
    T ub = (T)ClassUtils.evaluate( o.upperBound, getType(), true );
    if ( greaterEquals( upperBound, ub) ) {
      upperBound = ub;
      if ( !o.isUpperBoundIncluded() ) excludeUpperBound();
    }
    return this.size() != 0;
  }

  /**
   * Determines whether this domain intersects with another without modifying
   * either.  This is equivalent to {@link #overlaps(AbstractRangeDomain)}.
   * 
   * @param ard2
   * @return true iff this domain and the input domain have one or more common
   *         values.
   */
  public boolean intersects( AbstractRangeDomain<T> ard2 ) {
    AbstractRangeDomain< T > ard1 = clone();
    return ard1.intersectRestrict( ard2 );
  }
  
  /**
   * Determines whether this domain's bounds overlap with another's without modifying
   * either.  There are no constraints on which bounds are part of the overlap.
   * This is not the Allen overlaps relation.  In terms of Allen relations,
   * this returns true iff the Allen relation is not before, after, meets,
   * or imeets.  This is equivalent to {@link #intersects(AbstractRangeDomain)}.
   * 
   * @param ard2
   * @return true iff this domain and the input domain have one or more common
   *         values.
   */
  public boolean overlaps( AbstractRangeDomain<T> ard2 ) {
    return intersects( ard2 );
  }

  public < TT > boolean restrictTo( AbstractRangeDomain< TT > domain ) {
    AbstractRangeDomain< T > originalDomain = clone();
    intersectRestrict( (AbstractRangeDomain< T >)domain );
    boolean changed = !this.equals( originalDomain );
    return changed;
  }

  public < TT > boolean restrictTo( SingleValueDomain< TT > domain ) {
    Object v = domain.getValue( true );
    T t = (T)ClassUtils.evaluate( v, getType(), true );
    boolean changed = false;
    if ( t != null ) {
      changed = this.restrictToValue( t );
    }
    return changed;
  }

  public < TT > boolean restrictTo( MultiDomain< TT > domain ) {
    boolean changed = false;
    // The restricted set is the union of restrictions with each domain in the
    // flattened set.
    Set< Domain< TT > > s = domain.getFlattenedSet();
    Set< Domain< T > > newSet = new LinkedHashSet< Domain< T > >();
    for ( Domain< TT > d : s ) {
      AbstractRangeDomain< T > copy = this.clone();
      if ( copy.restrictTo( d ) ) {
        if ( !copy.isEmpty() ) {
          newSet.add( copy );
        }
      } else {
        // It was not restricted at all by part of the flattened set
        return false;
      }
    }
    MultiDomain< T > md =
        new MultiDomain< T >( (Class< T >)getType(), (Set< Domain< T > >)newSet,
                              (Set< Domain< T > >)null );
    newSet = md.getFlattenedSet();
    if ( newSet.size() == 0 ) {
      if ( !isEmpty() ) {
        this.clearValues();
        changed = true;
      }
    } else { // newSet.size() > 0
      AbstractRangeDomain< T > merge = null;
      // Just get the min and max of the bounds
      for ( Domain< T > dd : newSet ) {
        if ( dd instanceof AbstractRangeDomain ) {
          AbstractRangeDomain< T > ard = (AbstractRangeDomain< T >)dd;
          if ( merge == null ) merge = ard;
          else merge.extend( ard );
        }
      }
      if ( !this.equals( merge ) ) {
        this.copy( (AbstractRangeDomain< T >)merge );
        changed = true;
      }
    }
    return changed;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#restrictTo(gov.nasa.jpl.ae.solver.Domain)
   */
  @Override
  public < TT > boolean restrictTo( Domain< TT > domain ) {
    boolean changed = false;
    if ( domain instanceof AbstractRangeDomain ) {
      return restrictTo((AbstractRangeDomain< TT >)domain);
    }
    if ( domain instanceof SingleValueDomain ) {
      return restrictTo((SingleValueDomain< TT >)domain);
    }
    if ( domain instanceof MultiDomain ) {
      return restrictTo((MultiDomain< TT >)domain);
    }
    // TODO - other cases???
    Debug.error( "Cannot restrict " + this + " to domain " + domain
                 + " of type " + domain.getClass().getCanonicalName() );
    return changed;
  }
  
  public boolean extend( AbstractRangeDomain<T> ard ) {
    if ( ard == null ) return false;
    if ( contains(ard, false) ) return false;
    T lb = getLowerBound();
    T ub = getUpperBound();
    T olb = ard.getLowerBound();
    T oub = ard.getUpperBound();
    if ( lb == null || ub == null || olb == null || oub == null ) return false;
    if ( lessEquals( olb, lb ) ) {
      setLowerBound( olb );
      if ( ard.isLowerBoundIncluded() ) {
        includeLowerBound();
      }
    }
    if ( greaterEquals( oub, ub ) ) {
      setUpperBound( oub );
      if ( ard.isUpperBoundIncluded() ) {
        includeUpperBound();
      }
    }
    return true;
  }
  
//  public boolean subtract( Object o ) {
//    if ( o == null ) return false;  // REVIEW -- return this.clone() instead of null?
//    if ( o instanceof Domain ) {
//      return subtract((Domain<?>)o);
//    }
//    Class<?> cls = getType();
//    Object t = null;
//    if ( cls != null ) {
//      t = ClassUtils.evaluate( o, cls, false );
//    }
//    if ( t == null ) t = o;
//    boolean changed = false;
//    if ( lowerBound != null && isLowerBoundIncluded() && lowerBound.equals( t ) ) {
//      excludeLowerBound();
//      changed = true;
//    }
//    if ( upperBound != null && isUpperBoundIncluded() && upperBound.equals( t ) ) {
//      excludeUpperBound();
//      changed = true;
//    }
//    try {
//      if (!changed && lowerBound != null && upperBound != null && getType() != null && getType().isInstance( t ) && less( lowerBound, (T)t ) && less((T)t, upperBound)) {
//        System.err.println( "Warning!  Can't subtract " + t + " from the middle of " + this );
//      }
//    } catch (ClassCastException e) {
//      // ignore
//    }
//    return changed;
//  }

  public static <TT,TTT> Domain<TT> domainFromSet(Set<AbstractRangeDomain<TTT>> arDomains, Class<Domain<TT>> cls) {
    Set< Domain< TT > > domains = Utils.asSet( arDomains, cls );
    if ( domains == null ) return null;
    if ( domains.size() == 1 ) {
      return domains.iterator().next();
    }
    MultiDomain<TT> md = new MultiDomain<TT>( domains, (Set< Domain< TT > >)null );
    return md;
  }
  
  @Override
  public < TT > Domain<TT> subtract( Domain< TT > domain ) {
    if ( domain == null ) return null;  // REVIEW -- return this.clone() instead of null?
    if ( domain instanceof AbstractRangeDomain ) {
      Set< AbstractRangeDomain< TT > > arDomains = AbstractRangeDomain.subtract( (AbstractRangeDomain< TT >)this, (AbstractRangeDomain< TT >)domain );
      Domain<TT> d = domainFromSet(arDomains,  (Class< Domain< TT > >)domain.getClass() );
      if ( d == null ) {
        // TODO
      }
      return d;
      //changed = subtract( (AbstractRangeDomain< T >)domain );
    } else if ( domain instanceof SingleValueDomain ) {
      SingleValueDomain< T > svd = (SingleValueDomain< T >)domain;
      Domain<T> d = clone();
      d.restrictToValue( svd.value );
      Domain< T > tsd = this.subtract( d );
      if ( tsd instanceof AbstractRangeDomain ) {
        return (Domain< TT >)tsd;
      }
       // arDomains;
      if ( tsd instanceof MultiDomain ) {
        MultiDomain<T> md = (MultiDomain<T>)tsd;
        Set< ? extends AbstractRangeDomain > arDomains = Utils.asSet( md.includeSet, this.getClass() );
        Domain<TT> dd = domainFromSet( (Set< AbstractRangeDomain< T > >)arDomains, (Class< Domain< TT > >)domain.getClass()  );
        return dd;
      } else {
        // TODO ???
        Debug.error("Unexpected return value, " + tsd + " from subtracting " + d + " from " + this);
      }
    } else {
      // TODO???
      Debug.error("Cannot restrict " + this +" to domain " + domain + " of type " + domain.getClass().getCanonicalName() );
    }
    return null;
  }
  
  public boolean contains( AbstractRangeDomain<T> o, boolean strictly ) {
    // check input
    if ( o == null || lowerBound == null || upperBound == null || o.lowerBound == null || o.upperBound == null ) {
      System.err.println( "Error! AbstractRangeDomain.subtract() called with a null in the bounds." );
      return false;
    }
    if ( !this.contains( o.lowerBound ) ) return false;
    if ( !this.contains( o.upperBound ) ) return false;
    if ( !strictly ) return true;
    if (!contains(o.lowerBound, true) ) return false;
    if (!contains(o.upperBound, true) ) return false;
    return true;
  }
  
  /**
   * Remove the values in the input from those of this domain.  Warning, if the
   * input domain is strictly 
   * @param o
   * @return whether this domain changed as a result
   */
  public boolean subtract( AbstractRangeDomain<T> o ) {
    // check input
    if ( o == null || lowerBound == null || upperBound == null || o.lowerBound == null || o.upperBound == null ) {
      System.err.println( "Error! AbstractRangeDomain.subtract() called with a null in the bounds." );
      return false;
    }
    if ( this.contains( o, true ) ) {
      Debug.error( "Cannot subtract " + o + " out of the middle of " + this + ".  No change.");
      return false;
    }
    // TODO -- Copying the original below is not as efficient as detecting changes directly.
    AbstractRangeDomain<T> original = clone();
    if ( lessEquals( o.lowerBound, lowerBound ) && lessEquals( lowerBound, o.upperBound) ) {
      lowerBound = o.upperBound;
      if ( o.isUpperBoundIncluded() ) excludeLowerBound();
      //else if ( equals( lowerBound, o.lowerBound ) && includeLowerBound()
    }
    if ( lessEquals( upperBound, o.upperBound ) && lessEquals( o.lowerBound, upperBound) ) {
      upperBound = o.lowerBound;
      if ( o.isLowerBoundIncluded() ) excludeUpperBound();
      //else if ( equals( lowerBound, o.lowerBound ) && includeLowerBound()
    }
    return !this.equals(original);
  }

  public static < T1 > Set< AbstractRangeDomain< T1 > >
         subtract( AbstractRangeDomain< T1 > d1,
                   AbstractRangeDomain< T1 > d2 ) {
    // check input
    if ( d1 == null || d2 == null || d1.lowerBound == null || d1.upperBound == null || d2.lowerBound == null || d2.upperBound == null ) {
      System.err.println( "Error! AbstractRangeDomain.subtract(d1,d2) called with a null in the bounds." );
      return null;
    }
    
    Set< AbstractRangeDomain< T1 > > result =
        new LinkedHashSet< AbstractRangeDomain< T1 > >();

    // check for easy cases
    if ( d2.isEmpty() || d1.isEmpty() ) {
      AbstractRangeDomain< T1 > r = d1.clone();
      result.add( r );
      return result;
    }
    
    // The only case where two domains are returned instead of one is when d1
    // contains d2. Otherwise, the normal subtract works.
    boolean d1ContainsD2 = d1.lessEquals( d1.lowerBound, d2.lowerBound )
                           && d1.lessEquals( d2.upperBound, d1.upperBound )
                           && ( !d1.lowerBound.equals( d2.lowerBound )
                                || !d1.isLowerBoundIncluded() || d2.isLowerBoundIncluded() )
                           && ( !d1.upperBound.equals( d2.upperBound )
                                || !d1.isUpperBoundIncluded() || d2.isUpperBoundIncluded() );

    AbstractRangeDomain< T1 > r;

    if ( !d1ContainsD2 ) {
      r = d1.clone();
      boolean c = r.subtract( d2 );
      result.add( r );
      return result;
    }
    
    // Get first piece
    r = d1.clone();
    r.upperBound = d2.lowerBound;
    r.upperIncluded = !d2.lowerIncluded; 
    result.add( r );
    
    // Get second piece
    r = d1.clone();
    r.lowerBound = d2.upperBound;
    r.lowerIncluded = !d2.upperIncluded;
    result.add( r );

    return result;
  }

  @Override
  public boolean excludeLowerBound() {
    lowerIncluded = false;
    return this.size() != 0;
  }
  @Override
  public boolean includeLowerBound() {
    lowerIncluded = true;
    return this.size() != 0;
  }
  @Override
  public boolean isLowerBoundIncluded() {
    return lowerIncluded;
  }

  @Override
  public boolean excludeUpperBound() {
    upperIncluded = false;
    return this.size() != 0;
  }
  @Override
  public boolean includeUpperBound() {
    upperIncluded = true;
    return this.size() != 0;
  }
  @Override
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

//  /**
//   * @param domain the domain to set
//   */
//  @Override
//  public abstract void setDefaultDomain( Domain< T > domain );

  
  @Override
  public boolean setLowerBound( T lowerBound ) {
    if ( lessEquals(lowerBound, this.upperBound ) ) {
      this.lowerBound = lowerBound;
      //lowerIncluded = true;
      return true;
    }
    return false;
  }

  @Override
  public boolean setUpperBound( T upperBound ) {
    if ( lessEquals( this.lowerBound, upperBound ) ) {
      this.upperBound = upperBound;
      //upperIncluded = true;
      return true;
    }
    return false;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.RangeDomain#setBounds(java.lang.Object, java.lang.Object)
   */
  public boolean setBounds( T lowerBound, T upperBound ) {
    if ( lessEquals( lowerBound, upperBound )
         && ( !equals(lowerBound, this.lowerBound) || !equals(upperBound, this.upperBound)
              || !this.lowerIncluded || !this.upperIncluded ) ) {
      this.lowerBound = lowerBound;
      this.upperBound = upperBound;
      lowerIncluded = true;
      upperIncluded = true;
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
                                                 new Object[]{ propagate }, (Class<?>)null ) );
//      if ( t.getValue() instanceof Variable ) {
//        expr = 
//            new Expression< T >( new FunctionCall( null, Variable.class, "getValue",
//                                                   new Object[]{ propagate }, (FunctionCall)expr.expression ) );
//      }
      args = new Object[] { getLowerBound(), expr };
      cList.add( new ConstraintExpression( new FunctionCall( this, method,
                                                             args, (Class<?>)null ) ) );
      gotBoundConstraint = true;
    }
    // upper bound constraint
    if ( less( getUpperBound(), getTypeMaxValue() ) ) {
      args = new Object[] { getUpperBound(), t.getValue( propagate ) };
      method = ClassUtils.getMethodForArgs( getClass(), "greaterEquals", args );
      Expression< T > expr = 
        new Expression< T >( new FunctionCall( t, Variable.class, "getValue",
                                               new Object[]{ propagate }, (Class<?>)null ) );
      args = new Object[] { getUpperBound(), expr };

      cList.add( new ConstraintExpression( new FunctionCall( this, method,
                                                             args, (Class<?>)null ) ) );
      gotBoundConstraint = true;
    }
    // grounded constraint
    if ( t instanceof Groundable && !gotBoundConstraint ) {
      Groundable g = (Groundable)t;
      args = new Object[] { false, (Set< Groundable >)null };
      method = getIsGroundedMethod();
      if ( method != null ) {
        cList.add( new ConstraintExpression( new FunctionCall( g, method, args, (Class<?>)null ) ) );
      }
      //Utils.getMethodForArgs( Groundable.class, "isGrounded", args );
    }
    return cList;
  }

  @Override
  public boolean isNullInDomain() {
    return nullInDomain;
  }
  public boolean setNullInDomain( boolean b ) {
    nullInDomain = b;
    return true;
  }

  @Override
  public < V > V evaluate( Class< V > cls, boolean propagate ) {
    V v = Evaluatable.Helper.evaluate( this, cls, true, propagate, false, null );
    if ( v != null ) return v;
    
    if ( size() == 1 ) {
      T t = null;
      if ( cls == null || getType() == null || cls.isAssignableFrom( getType() ) ) {
        t = getValue( propagate );
        if ( cls == null || cls.isInstance( t ) ) {
          return (V)t;
        }
      }
      v = Evaluatable.Helper.evaluate( t, cls, true, propagate, true, null );
      if (v != null) {
        return v;
      }
    }
    if ( cls == null || cls.equals( Object.class ) ) {
      return (V)this;
    }
    return null;
  }

  @Override
  public boolean less( ComparableDomain<T> t1 ) {
    if ( t1 == null ) return false;
    return less(t1.getLowerBound()) || t1.greater(upperBound);
  }
  @Override
  public boolean lessEquals( ComparableDomain<T> t1 ) {
    if ( t1 == null ) return false;
    return lessEquals(t1.getLowerBound()) || t1.greaterEquals(upperBound);
  }
  @Override
  public boolean greater( ComparableDomain<T> t1 ) {
    if ( t1 == null ) return false;
    return greater(t1.getUpperBound()) || t1.less(lowerBound);
  }
  @Override
  public boolean greaterEquals( ComparableDomain<T> t1 ) {
    if ( t1 == null ) return false;
    return greaterEquals(t1.getUpperBound()) || t1.lessEquals(lowerBound);
  }
  
}
