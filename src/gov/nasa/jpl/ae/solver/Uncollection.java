/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author bclement
 *
 */
public class Uncollection< HasE, E > implements Collection< E > {

  Collection< HasE > eContainers; 
  //Collection<E> 
  /**
   * 
   */
  public Uncollection() {
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see java.util.Collection#size()
   */
  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  /* (non-Javadoc)
   * @see java.util.Collection#isEmpty()
   */
  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see java.util.Collection#contains(java.lang.Object)
   */
  @Override
  public boolean contains( Object o ) {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see java.util.Collection#iterator()
   */
  @Override
  public Iterator< E > iterator() {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see java.util.Collection#toArray()
   */
  @Override
  public Object[] toArray() {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see java.util.Collection#toArray(T[])
   */
  @Override
  public < T > T[] toArray( T[] a ) {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see java.util.Collection#add(java.lang.Object)
   */
  @Override
  public boolean add( E e ) {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see java.util.Collection#remove(java.lang.Object)
   */
  @Override
  public boolean remove( Object o ) {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see java.util.Collection#containsAll(java.util.Collection)
   */
  @Override
  public boolean containsAll( Collection< ? > c ) {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see java.util.Collection#addAll(java.util.Collection)
   */
  @Override
  public boolean addAll( Collection< ? extends E > c ) {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see java.util.Collection#removeAll(java.util.Collection)
   */
  @Override
  public boolean removeAll( Collection< ? > c ) {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see java.util.Collection#retainAll(java.util.Collection)
   */
  @Override
  public boolean retainAll( Collection< ? > c ) {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see java.util.Collection#clear()
   */
  @Override
  public void clear() {
    // TODO Auto-generated method stub

  }

}
