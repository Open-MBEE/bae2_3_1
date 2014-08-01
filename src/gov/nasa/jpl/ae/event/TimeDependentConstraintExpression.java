/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.TimeDependentConstraint;
import gov.nasa.jpl.ae.solver.TimeVariable;

/**
 * @author bclement
 *
 */
public class TimeDependentConstraintExpression extends ConstraintExpression
                                               implements TimeDependentConstraint {

  protected TimeVariable applicableStartTime = null;
  protected TimeVariable applicableEndTime = null;

  // TODO -- need setX() accessors and maybe constructors for new members
  /**
   * @param value
   */
  public TimeDependentConstraintExpression( Boolean value, TimeVariable start, TimeVariable end ) {
    super( value );
    applicableStartTime = start;
    applicableEndTime = end;
  }

  /**
   * @param parameter
   */
  public TimeDependentConstraintExpression( Parameter< Boolean > parameter, TimeVariable start, TimeVariable end ) {
    super( parameter );
    applicableStartTime = start;
    applicableEndTime = end;
  }

  /**
   * @param expression
   */
  public TimeDependentConstraintExpression( Expression< Boolean > expression, TimeVariable start, TimeVariable end ) {
    super( expression );
    applicableStartTime = start;
    applicableEndTime = end;
  }

  /**
   * @param function
   */
  public TimeDependentConstraintExpression( FunctionCall function, TimeVariable start, TimeVariable end ) {
    super( function );
    applicableStartTime = start;
    applicableEndTime = end;
  }

  /* (non-Javadoc)
   * @see solver.TimeDependentConstraint#getApplicableStartTime()
   */
  @Override
  public TimeVariable getApplicableStartTime() {
    return applicableStartTime;
  }

  /* (non-Javadoc)
   * @see solver.TimeDependentConstraint#getApplicableEndTime()
   */
  @Override
  public TimeVariable getApplicableEndTime() {
    return applicableEndTime;
  }

  
  
}
