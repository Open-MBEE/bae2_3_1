/**
 *
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.mbee.util.HasId;

/**
 *
 */
public class HasIdImpl implements HasId<Integer> {

  protected static int counter = 0;

  protected final int id = getNext();

  public static int getNext() {
    return counter++;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.HasId#getId()
   */
  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public int hashCode() {
    return id;
  }

}
