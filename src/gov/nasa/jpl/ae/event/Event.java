package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.HasConstraints;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Vector;


public interface Event extends HasConstraints, Comparable< Event > {

  public < T extends Comparable< ? super T > > void addDependency( Parameter< T > p,
                                                          Expression< T > e );
  public < T extends Comparable< ? super T > > boolean removeDependency( Parameter< T > p );

  public void execute();

  public void addEffect( Parameter< ? extends TimeVarying< ? > > sv, Object obj,
                         Method effectFunction, Vector< Object > arguments );

  public void addEffect( Parameter< ? extends TimeVarying< ? > > sv, Object obj,
                         Method method, Object arg );

  public Set< Parameter< ? > > getDependentParameters( boolean deep );

  // Conditionally create child event instances from this event instance.
  public void elaborate( boolean force );

  // satisfy each sub-event
//  public boolean satisfyElaborations();
  
  public String executionToString();

  /**
   * @return the startTime
   */
  public Timepoint getStartTime();

  /**
   * @param startTime
   *          the startTime to set
   */
  public void setStartTime( Timepoint startTime );

  /**
   * @return the duration
   */
  public Duration getDuration();

  /**
   * @param duration
   *          the duration to set
   */
  public void setDuration( Duration duration );

  /**
   * @return the endTime
   */
  public Timepoint getEndTime();

  /**
   * @param endTime
   *          the endTime to set
   */
  public void setEndTime( Timepoint endTime );

  /**
   * @return the parameters
   */
  public SortedSet< Parameter< ? >> getParameters();

  /**
   * @param parameters
   *          the parameters to set
   */
  public void setParameters( SortedSet< Parameter< ? >> parameters );

  /**
   * @return the effects
   */
  public Map< Parameter< ? extends TimeVarying< ? > >, Set< Effect > > getEffects();

  /**
   * @param effects
   *          the effects to set
   */
  public void setEffects( SortedMap< Parameter< ? extends TimeVarying< ? > >, Set< Effect > > effects );

  /**
   * @return the constraints
   */
  public Vector< ConstraintExpression > getConstraintExpressions();

  /**
   * @param constraints
   *          the constraints to set
   */
  public void setConstraintExpressions( Vector< ConstraintExpression > constraints );

  /**
   * @return the dependencies
   */
  public Vector< Dependency< ? >> getDependencies();

  /**
   * @param dependencies
   *          the dependencies to set
   */
  public void setDependencies( Vector< Dependency< ? >> dependencies );

  /**
   * @return the elaborations
   */
  public Map< ElaborationRule, Vector< Event >> getElaborations();

  /**
   * @param elaborations
   *          the elaborations to set
   */
  public void setElaborations( Map< ElaborationRule,
                                    Vector< Event > > elaborations );

  // Try to remove others' references to this, possibly because it is being
  // deleted.
  public void detach();

  public String getName();
  public void setName( String name );

}