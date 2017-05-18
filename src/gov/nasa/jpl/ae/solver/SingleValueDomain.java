/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Evaluatable;
import gov.nasa.jpl.mbee.util.Wraps;

/**
 *
 */
public class SingleValueDomain< T > extends HasIdImpl implements Domain< T > {

  protected static final SingleValueDomain<?> nullDomain = new SingleValueDomain<Object>();
  public static <T1> SingleValueDomain<T1> getNullDomain() {
    return (SingleValueDomain< T1 >)nullDomain;
  }
  
  protected T value = null;
  protected boolean nullInDomain = false;
  
  // REVIEW -- this won't work; two different types would share the same default.
  // REVIEW -- make this class and get/setDefaultDomain() abstract?
  //protected static SingleValueDomain< ? > defaultDomain = null;
  
  /**
   * 
   */
  public SingleValueDomain() {
    nullInDomain = false;
  }

  public SingleValueDomain( SingleValueDomain< T > singleValueDomain ) {
    value = singleValueDomain.value;
    nullInDomain = singleValueDomain.nullInDomain;
  }

  public SingleValueDomain( T singleValue ) {
    value = singleValue;
    if ( value == null ) {
      nullInDomain = true;
    }
  }

  @Override
  public SingleValueDomain< T > clone() {
    return new SingleValueDomain< T >( this );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#size()
   */
  @Override
  public long magnitude() {
    return isEmpty() ? 0 : 1;
  }

  public long size() {
    return magnitude();
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#contains(java.lang.Object)
   */
  @Override
  public boolean contains( T t ) {
    return ( t == null && value == null && isNullInDomain() )
           || ( t != null && t.equals( value ) );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#pickRandomValue()
   */
  @Override
  public T pickRandomValue() {
    return value;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#isInfinite()
   */
  @Override
  public boolean isInfinite() {
    return false;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#getDefaultDomain()
   */
  @Override
  public Domain< T > getDefaultDomain() {
    return this; // defaultDomain;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#getType()
   */
  @Override
  public Class< ? > getType() {
    if ( value != null ) {
      return value.getClass();
    }
    return null;
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
      if ( c == null && value != null
           && Wraps.class.isInstance( value ) ) {// isAssignableFrom( getType() ) ) {
        c = ( (Wraps< ? >)value ).getPrimitiveType();
      }
    }
    return c;
  }


  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Wraps#getTypeNameForClassName(java.lang.String)
   */
  @Override
  public String getTypeNameForClassName( String className ) {
    return ClassUtils.parameterPartOfName( className, false );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#restrictToValue(java.lang.Object)
   */
  @Override
  public boolean restrictToValue( T v ) {
    if ( value != v && ( value == null || !value.equals( v ) ) ) {
      value = v;
      return true;
    }
    return false;
  }

  @Override
  public boolean isEmpty() {
    return value == null && !nullInDomain;
  }

  @Override
  public T pickRandomValueNotEqual( T t ) {
    return null;
  }

  @Override
  public boolean isNullInDomain() {
    return value == null && nullInDomain;
  }

  @Override
  public T getValue( boolean propagate ) {
    return value;
  }

  @Override
  public void setValue( T value ) {
    this.value = value;
    if ( value == null ) {
      nullInDomain = true;
    }
  }

  @Override
  public <TT> boolean restrictTo( Domain< TT > domain ) {
    try {
      if ( domain == null ) return false;
      if ( domain.getType() != null ) {
        TT tt = (TT)ClassUtils.evaluate( value, domain.getType(), true );
        if ( domain.contains( tt ) ) {
          return false;
        }
      } else if ( domain.magnitude() == 1 ) {
        if ( ClassUtils.valuesEqual( value, domain.getValue( false ) ) ) {
          return false;
        }
      }
      if ( !domain.contains( (TT)value ) ) {
        return clearValues();
      }
    } catch ( ClassCastException e ) {
      return clearValues();
    }
    return false;
  }

  @Override
  public boolean clearValues() {
    if ( value != null || nullInDomain ) {
      value = null;
      nullInDomain = false;
      return true;
    }
    return false;
  }

  @Override
  public < V > V evaluate( Class< V > cls, boolean propagate ) {
    V v = Evaluatable.Helper.evaluate( this, cls, true, propagate, false, null );
    if ( v != null ) return v;

    T t = getValue( propagate );
    if ( cls == null || cls.isInstance( t ) ) {
      @SuppressWarnings( "unchecked" )
      V vv = (V)t;
      return vv;
    }
    
    v = Evaluatable.Helper.evaluate( this, cls, true, propagate, true, null );
    
    return v;
  }

  @Override
  public < TT > Domain< TT > subtract( Domain< TT > domain ) {
    SingleValueDomain< T > clone = clone();
    try {
      if ( domain.contains( (TT)clone.value ) ) {
        clone.value = null;
      }
    } catch ( ClassCastException e ) {}
    return (Domain< TT >)clone;
  }

  @Override
  public String toString() {
    return "SingleValueDomain(" + value + ")";
  }
  
}
