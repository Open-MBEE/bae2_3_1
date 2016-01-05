/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.ae.event.Functions;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Random;

import java.util.LinkedHashSet;

/**
 * A Domain as a discrete set of unordered objects
 *
 */
public class ObjectDomain< T > extends LinkedHashSet<T> implements Domain< T > {

  protected HasIdImpl hasId = new HasIdImpl();
  protected Class<T> type;
  protected boolean nullInDomain = false;

  /**
   * 
   */
  public ObjectDomain( Class<T> type ) {
    this.type = type;
  }

  public ObjectDomain( ObjectDomain< T > objectDomain ) {
    this.type = (Class< T >)objectDomain.getType();
    addAll( objectDomain );
  }

  @Override
  public boolean add( T e ) {
    if ( e == null ) nullInDomain = true;
    return super.add( e );
  }
  
  @Override
  public boolean remove( Object o ) {
    if ( o == null ) nullInDomain = false;
    return super.remove( o );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.mbee.util.HasId#getId()
   */
  @Override
  public Integer getId() {
    return hasId.getId();
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.mbee.util.Wraps#getType()
   */
  @Override
  public Class< ? > getType() {
    return type;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.mbee.util.Wraps#getTypeNameForClassName(java.lang.String)
   */
  @Override
  public String getTypeNameForClassName( String className ) {
    return getType().getSimpleName();
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.mbee.util.Wraps#getPrimitiveType()
   */
  @Override
  public Class< ? > getPrimitiveType() {
    Class< ? > c = null;
    if ( getType() != null ) {
      c = ClassUtils.primitiveForClass( getType() );
    }
    return c;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.mbee.util.Wraps#getValue(boolean)
   */
  @Override
  public T getValue( boolean propagate ) {
    if ( !isEmpty() ) return iterator().next();
    return null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.mbee.util.Wraps#setValue(java.lang.Object)
   */
  @Override
  public void setValue( T value ) {
    clear();
    add( value );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#clone()
   */
  @Override
  public Domain< T > clone() {
    Domain<T> d = new ObjectDomain< T >( this );
    return d;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#size()
   */
  @Override
  public int size() {
    return super.size();
  }

//  /* (non-Javadoc)
//   * @see gov.nasa.jpl.ae.solver.Domain#isEmpty()
//   */
//  @Override
//  public boolean isEmpty() {
//    // TODO Auto-generated method stub
//    return false;
//  }

//  /* (non-Javadoc)
//   * @see gov.nasa.jpl.ae.solver.Domain#contains(java.lang.Object)
//   */
//  @Override
//  public boolean contains( T t ) {
//    // TODO Auto-generated method stub
//    return false;
//  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#pickRandomValue()
   */
  @Override
  public T pickRandomValue() {
    T t = Functions.get( this, Random.global.nextInt( size() ) );
    return t;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#pickRandomValueNotEqual(java.lang.Object)
   */
  @Override
  public T pickRandomValueNotEqual( T t ) {
    int indexPicked = Random.global.nextInt( size() );
    T tt = Functions.get( this, indexPicked );
    
    int nextIndex = indexPicked;
    
    if ( tt == t || ( tt != null && tt.equals( t ) ) ) {
      if ( size() <= 1 ) {
        return null;
      }
      tt = Functions.get( this, indexPicked +1 % size() );      
    }
    return tt;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#isInfinite()
   */
  @Override
  public boolean isInfinite() {
    return false;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#isNullInDomain()
   */
  @Override
  public boolean isNullInDomain() {
    return nullInDomain;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#getDefaultDomain()
   */
  @Override
  public Domain< T > getDefaultDomain() {
    return new ObjectDomain< T >( type );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#setDefaultDomain(gov.nasa.jpl.ae.solver.Domain)
   */
  @Override
  public void setDefaultDomain( Domain< T > domain ) {
    // TODO Auto-generated method stub

  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#restrictToValue(java.lang.Object)
   */
  @Override
  public void restrictToValue( T v ) {
    clear();
    add( v );
  }

}
