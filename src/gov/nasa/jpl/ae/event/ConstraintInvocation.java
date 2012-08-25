/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Constraint;

import java.lang.reflect.Constructor;
import java.util.Map;


/**
 * @author bclement
 * 
 *         TODO -- This class is not done and may need to be scrapped in favor
 *         of expressions with references (pointers to members of objects that
 *         may not yet be instantiated).
 */
public class ConstraintInvocation {
  protected Class< ? extends ConstraintExpression > constraintClass = null;
  protected Map< String, Expression< ? >> arguments = null;
  protected Map< String, Object > memberAssignments = null; // TODO -- REVIEW --
                                                            // better to call
                                                            // constructor!
  protected Constructor< ? extends ConstraintExpression > constructor = null;

  public ConstraintInvocation( Class< ? extends ConstraintExpression > eventClass,
                               Map< String, Expression< ? >> arguments ) {
    this.constraintClass = eventClass;
    this.arguments = arguments;
  }

  // TODO -- create a superclass for invocations to be shared by EventInvocation
  public Constraint invoke() {
    // TODO
    return null;
  }

}
