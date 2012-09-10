package gov.nasa.jpl.ae.solver;

public interface Variable< T > {
  public Domain< T > getDomain();

  public void setDomain( Domain< T > domain );

  public T getValue();

  public void setValue( T value );
  
  public T pickRandomValue();
  
  public boolean pickValue();

}
