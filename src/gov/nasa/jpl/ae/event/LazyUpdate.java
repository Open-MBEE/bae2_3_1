/**
 * 
 */
package gov.nasa.jpl.ae.event;

/**
 * @author bclement
 *
 */
public interface LazyUpdate {
  public boolean isStale();
  public void setStale( boolean staleness );
}
