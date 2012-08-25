package gov.nasa.jpl.ae.solver;

public interface RangeDomain< T > extends Domain< T > {
  public T getLowerBound();

  public T getUpperBound();

  public boolean setLowerBound( T lowerBound );

  public boolean setUpperBound( T upperBound );

  public boolean setBounds( T lowerBound, T upperBound );

  public RangeDomain< T > clone();
}
