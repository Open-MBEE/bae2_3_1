/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.ae.util.ClassUtils;

/**
 *
 */
public class SingleValueDomain< T > extends HasIdImpl implements Domain< T > {

  protected static final SingleValueDomain<?> nullDomain = new SingleValueDomain<Object>( null );
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
  public long size() {
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
  public void restrictToValue( T v ) {
    value = v;
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

}
