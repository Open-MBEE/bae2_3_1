/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.List;
import java.util.Set;

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
  // TODO -- need to pass a seen set in handle*() methods.
  /**
   * Propagate this parameter's change to other objects. This may involve
   * updating dependencies, re-elaboration of events, and maybe constraint
   * satisfaction.
   * 
   * @param parameter the parameter whose value has changed
   */
  void handleValueChangeEvent( Parameter< ? > parameter, Set< HasParameters > seen );

  /**
   * Propagate this change to the parameter's domain to other objects. This may
   * involve updating domains of related parameters or their values.
   * 
   * @param parameter the parameter whose domain has changed
   */
  void handleDomainChangeEvent( Parameter< ? > parameter, Set< HasParameters > seen );

  /**
   * Set to stale anything that references the parameter whose value just
   * changed.
   * 
   * Lazy updating is added so that maps keyed with parameters (like
   * TimeVaryingMap) have a chance to pull out entries before they are
   * corrupted.
   * 
   * @param changedParameter the parameter whose value is about to change
   * @param seen TODO
   */
  void setStaleAnyReferencesTo( Parameter< ? > changedParameter, Set< HasParameters > seen );

  /**
   * Remove any references to the parameter.  
   * 
   * @param parameter the parameter that is being detached
   */
  void detach( Parameter< ? > parameter );
  
  /**
   * Update this parameter's value or domain so that it does not depend on stale
   * information.
   * 
   * @param parameter the parameter to refresh
   * @return whether or not the parameter was refreshed successfully
   */
  boolean refresh( Parameter< ? > parameter );

  /**
   * Pick a new value for the {@link Variable}, possibly to help resolve constraints.
   * 
   * @param variable
   * @return
   */
  <T> boolean pickParameterValue( Variable< T > variable );

  /**
   * Get the name of this object. 
   *
   * @return a name
   */
  String getName();
  
  
  /**
   * Adjust the value assigned to a variable to make sure it is in in the
   * domain.
   * 
   * @param v the variable whose value is to be assigned
   * @param o the object to translate
   * @param type the variable's type
   * @return the new value to use instead of the object passed in
   */
  <T> T translate( Variable<T> v, Object o, Class< ? > type  );  // HACK -- remove this if possible

  /**
   * Find the variables on which the input variable depends.
   * @param variable the dependent variable
   * @return a list of variables
   */
  List<Variable<?>> getVariablesOnWhichDepends( Variable<?> variable );
  // TODO -- need to add "deep" and "seen" parameters to method for recursing into contained objects.

}
