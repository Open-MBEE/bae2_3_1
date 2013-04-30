package gov.nasa.jpl.ae.solver;

import java.util.Set;

public interface Variable< T > extends HasDomain, HasId, Wraps< T > {
  
  //public Domain< T > getDomain( boolean propagate, Set< HasDomain > seen );
  public Domain< T > getDomain();

  public void setDomain( Domain< T > domain );

  public T pickRandomValue();
  
  public boolean pickValue();

}
