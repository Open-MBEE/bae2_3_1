package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Variable;

public interface Suggester {
  public <T> T pickValue( Variable< T > variable );
}
