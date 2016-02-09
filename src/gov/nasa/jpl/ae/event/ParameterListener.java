/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.mbee.util.HasName;

/**
 * ParameterListener should be implemented by classes whose members or methods
 * depend on the volatility of Parameter value or domain changes.
 * 
 * @author bclement
 * 
 */
public interface ParameterListener extends HasParameters, HasName< String > {
  /**
   * Propagate this parameter's change to other objects. This may involve
   * updating dependencies, re-elaboration of events, and maybe constraint
   * satisfaction.
   * 
   * @param parameter the parameter whose value has changed
   */
  public void handleValueChangeEvent( Parameter< ? > parameter );

  /**
   * Propagate this change to the parameter's domain to other objects. This may
   * involve updating domains of related parameters or their values.
   * 
   * @param parameter the parameter whose domain has changed
   */
  public void handleDomainChangeEvent( Parameter< ? > parameter );

  /**
   * Set to stale anything that references the parameter whose value just
   * changed.
   * 
   * Lazy updating is added so that maps keyed with parameters (like
   * TimeVaryingMap) have a chance to pull out entries before they are
   * corrupted.
   * 
   * @param changedParameter the parameter whose value is about to change
   */
  public void setStaleAnyReferencesTo( Parameter< ? > changedParameter );

  /**
   * Remove any references to the parameter.  
   * 
   * @param parameter the parameter that is being detached
   */
  public void detach( Parameter< ? > parameter );
  
  /**
   * Update this parameter's value or domain so that it does not depend on stale
   * information.
   * 
   * @param parameter the parameter to refresh
   * @return whether or not the parameter was refreshed successfully
   */
  public boolean refresh( Parameter< ? > parameter ); 

  /**
   * Pick a new value for the parameter, possibly to help resolve constraints.
   * 
   * @param parameter
   * @return
   */
  public <T> boolean pickParameterValue( Variable< T > variable );

  /**
   * The initial motivation for {@code getName()} was for if ( Debug.isOn() ) Debug.output.  As of 2012-08-05, 
   *
   * @return a name
   */
  public String getName();
  
  /**
   * Adjust the value assigned to a variable to make sure it is in in the
   * domain.
   * 
   * @param v the variable whose value is to be assigned
   * @param o the object to translate
   * @param type the variable's type
   * @return the new value to use instead of the object passed in
   */
  public <T> T translate( Variable<T> v, Object o, Class< ? > type  );  // HACK -- remove this if possible

}
