/**
 * 
 */
package gov.nasa.jpl.ae.event;

/**
 * Just a tag interface to distinguish between data to be plotted and data to not.
 *
 */
public interface Plottable {
  //public double getValue( double x );
  public boolean okToSample();
  // public int getSamplePeriod();
  
  /**
   * If the data is not projected, then it is considered to be live data unknown
   * to a simulation until the time of the data point has passed.  Thus, data
   * would be added to a live plot over the time of a simulation.  If the data
   * is projected, then future data is known, and all data can be plotted before
   * before the simulation begins.
   * 
   * @return whether the data is projected
   */
  public boolean isProjection();
}
