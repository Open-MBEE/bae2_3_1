/**
 * 
 */
package gov.nasa.jpl.ae.solver;

/**
 * @author bclement
 *
 */
public class HasIdImpl implements HasId {

  protected static int counter = 0;
  
  protected final int id = getNext();
  
  public static int getNext() {
    return counter++;
  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.HasId#getId()
   */
  @Override
  public int getId() {
    return id; 
  }

  @Override
  public int hashCode() {
    return id;
  }
  
}
