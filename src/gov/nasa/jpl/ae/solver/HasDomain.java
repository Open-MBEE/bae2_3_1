package gov.nasa.jpl.ae.solver;

import java.util.Set;

public interface HasDomain {
  public Domain< ? > getDomain( boolean propagate, Set< HasDomain > seen );
}
