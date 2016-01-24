package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.ae.event.LazyUpdate;
import gov.nasa.jpl.mbee.util.HasId;

import java.util.Set;

public interface Constraint extends Satisfiable, Comparable< Constraint >,
                                    LazyUpdate, HasId<Integer> {
  public Set< Variable< ? > > getVariables();

  public < T > boolean pickParameterValue( Variable< T > v ); // not implemented

  public < T > boolean restrictDomain( Variable< T > v ); // not implemented

  public < T > boolean isFree( Variable< T > v );

  public < T > boolean isDependent( Variable< T > v );

  /**
   * @return the freeVariables
   */
  public Set< Variable< ? > > getFreeVariables();

  /**
   * @param freeParameters
   *          the freeVariables to set
   */
  public void setFreeVariables( Set< Variable< ? > > freeVariables );

}