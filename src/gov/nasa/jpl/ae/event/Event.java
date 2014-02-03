package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.HasConstraints;
import gov.nasa.jpl.mbee.util.Pair;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Vector;


public interface Event extends HasConstraints {

  public void execute();

  public void addEffect( Parameter< ? > sv, Object obj,
                         Method effectFunction, Vector< Object > arguments );

  public void addEffect( Parameter< ? > sv, Object obj,
                         Method method, Object arg );

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
  public List< Parameter< ? > > getParameters();

  /**
   * @param parameters
   *          the parameters to set
   */
  public void setParameters( List< Parameter< ? > > parameters );

  /**
   * @return the effects
   */
  public List< Pair< Parameter< ? >, Set< Effect > > > getEffects();

  /**
   * @param effects
   *          the effects to set
   */
  public void setEffects( List< Pair< Parameter< ? >, Set< Effect > > > effects );

  /**
   * @return the constraints
   */
  public List< ConstraintExpression > getConstraintExpressions();

  /**
   * @param constraints
   *          the constraints to set
   */
  public void setConstraintExpressions( List< ConstraintExpression > constraints );

  /**
   * @return the dependencies
   */
  public Collection< Dependency< ? > > getDependencies();

  /**
   * @param dependencies
   *          the dependencies to set
   */
  public void setDependencies( Collection< Dependency< ? >> dependencies );

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
  public void deconstruct();

  public String getName();
  public void setName( String name );

}