/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.mbee.util.ClassUtils;
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
  
  // REVIEW -- this won't work; two different types would share the same default.
  // REVIEW -- make this class and get/setDefaultDomain() abstract?
  //protected static SingleValueDomain< ? > defaultDomain = null;
  
  /**
   * 
   */
  public SingleValueDomain() {
  }

  public SingleValueDomain( SingleValueDomain< T > singleValueDomain ) {
    value = singleValueDomain.value;
  }

  public SingleValueDomain( T singleValue ) {
    value = singleValue;
  }

  @Override
  public SingleValueDomain< T > clone() {
    return new SingleValueDomain< T >( this );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#size()
   */
  @Override
  public int size() {
    return 1;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#contains(java.lang.Object)
   */
  @Override
  public boolean contains( T t ) {
    return t.equals( value );
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
   * @see gov.nasa.jpl.ae.solver.Domain#setDefaultDomain(gov.nasa.jpl.ae.solver.Domain)
   */
  @Override
  public void setDefaultDomain( Domain< T > domain ) {
    // REVIEW -- make this class, get/setDefaultDomain(), and get[Primitive]Type
    // abstract?
    assert false;
    //defaultDomain = domain;
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
    if ( value != v && (value == null || !value.equals( v ) ) ) {
      value = v;
      return true;
    }
    return false;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public T pickRandomValueNotEqual( T t ) {
    return null;
  }

  @Override
  public boolean isNullInDomain() {
    return value == null;
  }

  @Override
  public T getValue( boolean propagate ) {
    return value;
  }

  @Override
  public void setValue( T value ) {
    this.value = value;
  }

  @Override
  public <TT> boolean restrictTo( Domain< TT > domain ) {
    try {
      if ( !domain.contains( (TT)value ) ) {
        if ( value != null ) {
          value = null;  // REVIEW -- Do we want to do this??!!
          return true;
        }
      }
    } catch ( ClassCastException e ) {
      if ( value != null ) {
        value = null;  // REVIEW -- Do we want to do this??!!
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean clearValues() {
    if ( value != null ) {
      value = null;  // REVIEW -- Do we want to do this??!!
      return true;
    }
    return false;
  }

}
