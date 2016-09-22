package gov.nasa.jpl.ae.solver;

public interface RangeDomain< T > extends Domain< T > {
  public RangeDomain<T> make(T lowerBound, T upperBound);

  public T getLowerBound();

  public T getUpperBound();

  public boolean setLowerBound( T lowerBound );

  public boolean setUpperBound( T upperBound );

  public boolean setBounds( T lowerBound, T upperBound );

  /**
   * Remove the lower bound from the domain if it is in the domain.
   * 
   * @return whether there are any values in the domain given this change
   */
  public boolean excludeLowerBound();

  /**
   * Make the lower bound part of the domain if it is not already.
   * 
   * @return whether there are any values in the domain given this change
   */
  public boolean includeLowerBound();

  /**
   * @return whether the lower bound itself is considered part of the domain or
   *         just values greater than the lower bound (that are within the upper
   *         bound).
   */
  public boolean isLowerBoundIncluded();

  /**
   * Remove the upper bound from the domain if it is in the domain.
   * 
   * @return whether there are any values in the domain given this change
   */
  public boolean excludeUpperBound();

  /**
   * Make the upper bound part of the domain if it is not already.
   * 
   * @return whether there are any values in the domain given this change
   */
  public boolean includeUpperBound();

  /**
   * @return whether the upper bound itself is considered part of the domain or
   *         just values less than the upper bound (that are within the lower
   *         bound).
   */
  public boolean isUpperBoundIncluded();
  
  /**
   * @return the size of the domain as the difference in the lower and upper
   *         bounds; for example, the width of the range, [1.2, 1.5], is 0.3
   */
  public Number width();

  public RangeDomain< T > clone();
}
