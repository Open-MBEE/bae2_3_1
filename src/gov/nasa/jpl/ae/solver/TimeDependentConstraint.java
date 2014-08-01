/**
 * 
 */
package gov.nasa.jpl.ae.solver;

/**
 * @author bclement
 * 
 * TODO -- REVIEW -- Should this extend TimeVarying< Constraint >?
 * TODO -- REVIEW -- How could we leverage seqr tms code?
 */
public interface TimeDependentConstraint extends Constraint {

  public TimeVariable getApplicableStartTime();

  public TimeVariable getApplicableEndTime();

}
