/**
 * 
 */
package gov.nasa.jpl.ae.solver;

/**
 * A domain whose elements may be compared with =, !=, <, <=, >, and >=.
 */
public interface ComparableDomain< T > extends Domain< T >, Comparable<Domain<T>> {
  public boolean greater( T t1, T t2 );
  public boolean less( T t1, T t2 );
  public boolean equals( T t1, T t2 );
  public boolean greaterEquals( T t1, T t2 );
  public boolean lessEquals( T t1, T t2 );
  public boolean notEquals( T t1, T t2 );
  public boolean less( T t1 );
  public boolean lessEquals( T t1 );
  public boolean greater( T t1 );
  public boolean greaterEquals( T t1 );
  public boolean less( ComparableDomain<T> t1 );
  public boolean lessEquals( ComparableDomain<T> t1 );
  public boolean greater( ComparableDomain<T> t1 );
  public boolean greaterEquals( ComparableDomain<T> t1 );
  
  public int compareTo( ComparableDomain<T> domain );
  
  public T getLowerBound();
  public T getUpperBound();
  /**
   * @return whether the lower bound itself is considered part of the domain or
   *         just values greater than the lower bound (that are within the upper
   *         bound).
   */
  public boolean isLowerBoundIncluded();

  /**
   * @return whether the upper bound itself is considered part of the domain or
   *         just values less than the upper bound (that are within the lower
   *         bound).
   */
  public boolean isUpperBoundIncluded();

}
