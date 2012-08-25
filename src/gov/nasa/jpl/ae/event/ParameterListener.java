/**
 * 
 */
package gov.nasa.jpl.ae.event;

/**
 * ParameterListener should be implemented by classes whose members or methods
 * depend on the volatility of Parameter value or domain changes.
 * 
 * @author bclement
 * 
 */
public interface ParameterListener extends HasParameters {
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
   * Update this parameter's value or domain so that it does not depend on stale
   * information.
   * 
   * @param parameter the parameter to refresh
   * @return whether or not the parameter was refreshed successfully
   */
  public boolean refresh( Parameter< ? > parameter ); 

  /**
   * The initial motivation for {@code getName()} was for debug output.  As of 2012-08-05, 
   *
   * @return a name
   */
  public String getName();
}
