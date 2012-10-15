package gov.nasa.jpl.ae.solver;

public interface RangeDomain< T > extends Domain< T > {
  public T getLowerBound();

  public T getUpperBound();

  public boolean setLowerBound( T lowerBound );

  public boolean setUpperBound( T upperBound );

  public boolean setBounds( T lowerBound, T upperBound );

  public boolean excludeLowerBound();
  public boolean includeLowerBound();
  public boolean isLowerBoundIncluded();

  public boolean excludeUpperBound();
  public boolean includeUpperBound();
  public boolean isUpperBoundIncluded();

  public RangeDomain< T > clone();
}
