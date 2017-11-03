/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.ae.event.ConstructorCall;
import gov.nasa.jpl.ae.event.Functions;
import gov.nasa.jpl.ae.event.Groundable;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Evaluatable;
import gov.nasa.jpl.mbee.util.Random;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Vector;

/**
 * A Domain as a discrete set of unordered objects
 *
 */
public class ObjectDomain< T > extends LinkedHashSet<T> implements Domain< T > {

  protected HasIdImpl hasId = new HasIdImpl();
  protected Class<T> type;
  protected boolean nullInDomain = false;
  protected Object enclosingObject = null;

  /**
   * Create a domain for objects of the specified type. 
   */
  public ObjectDomain( Class<T> type ) {
    this.type = type;
  }
  
  
  public ObjectDomain( Class<T> type, Object o ) {
    this.type = type;
    this.enclosingObject = o;
  }

  public ObjectDomain( ObjectDomain< T > objectDomain ) {
    this.type = (Class< T >)objectDomain.getType();
    addAll( objectDomain );
  }

  public ObjectDomain( Collection< T > objects ) {
    this( objects, (Class< T >)ClassUtils.mostSpecificCommonSuperclass( objects ) );
  }
  
  public ObjectDomain( Collection< T > objects, Class<T> cls ) {
    addAll( objects );
    this.type = cls;
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
    clearValues();
    add( value );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#clone()
   */
  @Override
  public ObjectDomain< T > clone() {
    ObjectDomain<T> d = new ObjectDomain< T >( this );
    return d;
  }
  
  
  @Override
  public boolean contains(Object t) {
    if (isEmpty()) {
      return isNullInDomain();
    }
    return super.contains( t );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Domain#size()
   */
  @Override
  public int size() {
    return super.size();
  }

  @Override
  public long magnitude() {
    return size();
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
    if (isEmpty()) {
      return null;
    }
    T t = Functions.get( this, Random.global.nextInt( size() ) );
    return t;
  }
  
  
  
  public T constructObject() {
    if (getType() == null) {
      return null;
    }
    try {
      ConstructorCall cc = new ConstructorCall( enclosingObject, getType(),
                                                new Vector<Object>(),
                                                getType() );
      Object o = cc.evaluate(true);
      if (o instanceof Groundable) {
        ( (Groundable)o ).ground( true, null );
      }
      return (T)o;
    } catch ( InstantiationException e ) {
      e.printStackTrace();
    } catch ( IllegalAccessException e ) {
      e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      e.printStackTrace();
    }
    return null;
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
   * @see gov.nasa.jpl.ae.solver.Domain#restrictToValue(java.lang.Object)
   */
  @Override
  public boolean restrictToValue( T v ) {
    boolean changed = false;
    if ( clearValues() ) changed = true;
    if ( add( v ) ) changed = true;
    return changed;
  }

  @Override
  public < TT > boolean restrictTo( Domain< TT > domain ) {
    if ( domain instanceof SingleValueDomain ) {
      return this.restrictToValue( ((SingleValueDomain< T >)domain).value );
    } else {
      // TODO???
      Debug.error("");
    }
    return false;
  }

  @Override
  public boolean clearValues() {
    if ( !isEmpty() ) {
      clear();
      return true;
    }
    return false;
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
  public < TT > Domain< TT > subtract( Domain< TT > domain ) {
    ObjectDomain<T> clone = clone();
    java.util.Iterator<T> i = clone.iterator();
    while ( i.hasNext() ) {
      T t = i.next();
      try {
        if ( domain.contains( (TT)t ) ) {
          i.remove();
        }
      } catch (ClassCastException e) {}
    }
    return (Domain< TT >)clone;
  }


}
