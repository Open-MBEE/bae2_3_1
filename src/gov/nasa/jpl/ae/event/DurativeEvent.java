package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.ConstraintLoopSolver;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasConstraints;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.solver.Solver;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import junit.framework.Assert;



/**
 * 
 */

/**
 * @author bclement
 * 
 */
public class DurativeEvent extends ParameterListenerImpl implements Event, Cloneable,
                           HasEvents, Groundable, Satisfiable,
                           //Comparable< Event >, 
                           ParameterListener,
                           HasTimeVaryingObjects {

  // Constants
  
  final double timeoutSeconds = 30.0;

  // Static members
  
  protected static int counter = 0;

  // Other Members

  public Timepoint startTime = new Timepoint( "startTime", this );
  public Duration duration = new Duration( this );
  public Timepoint endTime = new Timepoint( "endTime", this );
  // TODO -- REVIEW -- create TimeVariableParameter and EffectMap classes for
  // effects?
  protected Map< Parameter< ? >, Set< Effect > > effects =
      new TreeMap< Parameter< ? >, Set< Effect > >();
  protected Map< ElaborationRule, Vector< Event > > elaborations =
      new TreeMap< ElaborationRule, Vector< Event > >();

  protected Dependency startTimeDependency = null;
  
  protected Dependency endTimeDependency = null;
  
  protected Dependency durationDependency = null;
  
  // TODO -- consider breaking elaborations up into separate constraints
  protected Constraint elaborationsConstraint = 
      new AbstractParameterConstraint() {

    @Override
    public boolean satisfy() {
      boolean satisfied = true;
      for ( Entry< ElaborationRule, Vector< Event > > er : elaborations.entrySet() ) {
        if ( isElaborated( er ) != er.getKey().isConditionSatisfied() ) {
          if ( er.getKey().attemptElaboration( er.getValue(), true ) ) {
            if ( !er.getKey().isConditionSatisfied() ) satisfied = false;
          } else {
            if ( er.getKey().isConditionSatisfied() ) satisfied = false;
          }
        }
      }
      return satisfied;
    }
    
    @Override
    public boolean isSatisfied() {
      for ( Entry< ElaborationRule, Vector< Event > > er : elaborations.entrySet() ) {
        if ( isElaborated( er ) != er.getKey().isConditionSatisfied() ) {
          return false;
        }
      }
      return true;
    }
    
    @Override
    public Set< Parameter< ? > > getParameters( boolean deep ) {
      Set< Parameter< ? > > s = new HashSet< Parameter< ? > >();
      for ( Entry< ElaborationRule, Vector< Event > > er : elaborations.entrySet() ) {
        s.addAll( er.getKey().getCondition().getParameters( false ) );
      }
      return s;
    }

    @Override
    public String toString() {
      // TODO -- should make this look evaluable, ex: condition -> eventExists
      return getName() + ".elaborationsConstraint";
    }
    
  };  // end of elaborationsConstraint
  
  // TODO -- consider breaking effects into separate constraints
  protected Constraint effectsConstraint = new AbstractParameterConstraint() {

    protected boolean
      areEffectsOnTimeVaryingSatisfied( Parameter< ? > variable,
                                        Set< Effect > effects ) {
      if ( !variable.isGrounded() ) {
        return false;
      }
      for ( Effect e : effects ) {
        if ( !e.isApplied( variable ) ) {
          if ( !variable.isGrounded() ) {
            return false;
          }
        }
      }
      return true;
    }

    protected boolean
        satisfyEffectsOnTimeVarying( Parameter< ? > variable,
                                     Set< Effect > effects ) {
      boolean satisfied = true;
      if ( !variable.isGrounded() ) {
        variable.ground();
      }
      if ( !variable.isSatisfied() ) {
        variable.satisfy();
      }
      for ( Effect e : effects ) {
        if ( !e.isApplied( (Parameter< ? >)variable ) ) {
          if ( !variable.isGrounded() ) {
            satisfied = false;
          } else {
            TimeVarying<?> value = (TimeVarying< ? >)variable.getValue();
            e.applyTo( value, true );
          }
        }
      }
      return satisfied;
    }
    
    @Override
    public boolean satisfy() {
      boolean satisfied = true;
      for ( Entry< Parameter< ? >, Set< Effect > > entry : getEffects().entrySet() ) {
        Set< Effect > set = entry.getValue();
        Parameter< ? > variable = entry.getKey();
        if ( !satisfyEffectsOnTimeVarying( variable, set ) ) {
          satisfied = false;
        }
      }
      return satisfied;
    }
    
    @Override
    public boolean isSatisfied() {
      for ( Entry< Parameter< ? >, Set< Effect > > entry : getEffects().entrySet() ) {
        Set< Effect > set = entry.getValue();
        Parameter< ? > variable = entry.getKey();
        if ( !areEffectsOnTimeVaryingSatisfied( variable, set ) ) {
          return false;
        }
      }
      return true;
    }

    @Override
    public Set< Parameter< ? >> getParameters( boolean deep ) {
      Set< Parameter< ? > > s = new HashSet< Parameter< ? > >();
      for ( Set< Effect > set : getEffects().values() ) {
        for ( Effect e : set ) {
          if ( e instanceof HasParameters ) {
            s.addAll( ( (HasParameters)e ).getParameters( false ) );
          }
        }
      }
      return s;
    }

    @Override
    public String toString() {
      // TODO -- maybe make this look evaluable, ex: v.getValue(t) == fCall.arg0
      return getName() + ".effectsConstraint";
    }
  };  // end of effectsConstraint

  
  // Constructors

  public DurativeEvent() {
    this((String)null);
  }
  public DurativeEvent( String name ) {
    setName( name );
    loadParameters();

    // create the dependency, endTime = startTime + duration
    // TODO -- REVIEW -- should this dependency just be a constraint instead?
    Functions.Sum< Integer, Integer > sum =
        new Functions.Sum< Integer, Integer >(
            new Expression< Integer >( startTime ),
            new Expression< Integer >( duration ) );
    endTimeDependency  = addDependency( endTime, sum );
    Functions.Sub< Integer, Integer > sub1 =
        new Functions.Sub< Integer, Integer >(
            new Expression< Integer >( endTime ),
            new Expression< Integer >( duration ) );
    startTimeDependency = addDependency( startTime, sub1 );
    Functions.Sub< Integer, Integer > sub2 =
        new Functions.Sub< Integer, Integer >(
            new Expression< Integer >( endTime ),
            new Expression< Integer >( startTime ) );
    durationDependency = addDependency( duration, sub2 );
  }

  public DurativeEvent( DurativeEvent durativeEvent ) {
    this( null, durativeEvent );
  }
  public DurativeEvent( String name, DurativeEvent durativeEvent ) {
    setName( name );
    copyParameters( durativeEvent );
    loadParameters();

    // copy containers after clearing
    constraintExpressions.clear();
    effects.clear();
    dependencies.clear();
    for ( ConstraintExpression c : durativeEvent.constraintExpressions ) {
      ConstraintExpression nc = new ConstraintExpression( c );
      constraintExpressions.add( nc );
    }
    for ( Map.Entry< Parameter< ? >, Set< Effect > > e : 
          durativeEvent.effects.entrySet() ) {
      Set< Effect > ns = new HashSet< Effect >();
      try {
        for ( Effect eff : e.getValue() ) {
          Effect ne = eff.clone();
          ns.add( ne );
        }
      } catch ( CloneNotSupportedException e1 ) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      effects.put( (Parameter< ? >)e.getKey().clone(),
                   ns );
    }
    for ( Dependency< ? > d : durativeEvent.dependencies ) {
      Dependency< ? > nd = new Dependency( d );
      dependencies.add( nd );
    }

    // We need to make sure the copied constraints are on this event's
    // parameters and not that of the copied constraints.
    List< Pair< Parameter< ? >, Parameter< ? > > > subList =
        buildSubstitutionList( durativeEvent );
    for ( Pair< Parameter< ? >, Parameter< ? > > p : subList ) {
      substitute( p.first, p.second, false );
    }
    // }
  }

  public void fixTimeDependencies() {
    boolean gotStart = false, gotEnd = false, gotDur = false;
    boolean stillHaveStart = false, stillHaveEnd = false, stillHaveDur = false;
    int numGot = 0;
    int numHave = 0;
    // see what time dependencies we have
    for ( Dependency< ? > d : getDependencies() ) {
      if ( d.parameter == startTime ) { 
        if ( d == startTimeDependency ) {
          stillHaveStart = true;
          ++numHave;
        } else {
          gotStart = true;
          ++numGot;
        }
      } else if ( d.parameter == endTime ) {
        if ( d == endTimeDependency ) {
          stillHaveEnd = true;
          ++numHave;
        } else {
          gotEnd = true;
          ++numGot;
        }
      } else if ( d.parameter == duration ) {
        if ( d == durationDependency ) {
          stillHaveDur = true;
          ++numHave;
        } else {
          gotDur = true;
          ++numGot;
        }
      }
    }
    // get rid of already replaced dependencies
    if ( gotStart && stillHaveStart ) {
      getDependencies().remove( startTimeDependency );
      stillHaveStart = false;
      --numHave;
    }
    if ( gotEnd && stillHaveEnd ) {
      getDependencies().remove( endTimeDependency );
      stillHaveEnd = false;
      --numHave;
    }
    if ( gotDur && stillHaveDur ) {
      getDependencies().remove( durationDependency );
      stillHaveDur = false;
      --numHave;
    }
    // only want one dependency among the three
    if ( numHave > 1 ) {
      if ( stillHaveEnd ) {
        if ( stillHaveStart ) {
          getDependencies().remove( startTimeDependency );
        }
        if ( stillHaveDur ) {
          getDependencies().remove( durationDependency );
        }
      } else if ( stillHaveDur ) {
        if ( stillHaveStart ) {
          getDependencies().remove( startTimeDependency );
        }
      } else {
        assert false;
      }
    }
  }
  
  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep ) {
    boolean subbed = super.substitute( p1, p2, deep );
    HasParameters.Helper.substitute( effects, p1, p2, deep );
    for ( Entry< Parameter< ?>, Set< Effect >> e : effects.entrySet() ) {
      if ( e.getValue() instanceof HasParameters ) {
        boolean s = ( (HasParameters)e.getValue() ).substitute( p1, p2, deep );
        subbed = subbed || s;
      }
    }
    return subbed;
  }

  // Methods

  // Subclasses should override this to add their own parameters.
  protected void loadParameters() {
    parameters.clear();
    parameters.add( startTime );
    parameters.add( duration );
    parameters.add( endTime );
  }

  // Subclasses should override this to add their own parameters.
  // TODO -- Can this just iterate through parameters container to avoid
  // overriding in subclasses?
  protected void copyParameters( DurativeEvent event ) {
    // Inefficiently reallocating these parameters--maybe create
    // Parameter.copy(Parameter)
    startTime = new Timepoint( event.startTime );
    duration = new Duration( event.duration );
    endTime = new Timepoint( event.endTime );
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#clone()
   */
  @Override
  public Object clone() {
    return new DurativeEvent( this );
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append( getClass().getName() ); // super.toString() adds hash code
    Parameter<?> firstParams[] = { startTime, duration, endTime };  // Could use Arrays.sort() .search()
    List< Parameter< ? > > allParams = new ArrayList< Parameter< ? > >(Arrays.asList( firstParams ));
    Set< Parameter< ? > > restParams = getParameters( false );
    restParams.removeAll( allParams );
    allParams.addAll( restParams );
    for ( Object p : allParams ) {
      if ( p instanceof Parameter ) {
        sb.append( ", " + ((Parameter<?>)p).toString( false, false ) );
      } else {
        sb.append( ", " + p.toString() );
      }
    }
    return sb.toString();
  }

  /* (non-Javadoc)
   * @see event.Event#execute()
   */
  @Override
  public void execute() { // differentiate between execute for simulation and
    // execute in external environment?
    Debug.outln( getName() + ".execute()" );
    boolean satisfied = satisfy();
    Debug.outln( getName() + ".execute() called satisfy() --> " + satisfied );
    if ( !satisfied ) {
      satisfied = solver.solve( getConstraints( true ) );
      Debug.outln( getName() + ".execute() called solve() --> " + satisfied );
    }
    if ( satisfied ) {
      Debug.outln( "All constraints were satisfied!" );
    } else {
      Collection< Constraint > unsatisfiedConstraints =
          ConstraintLoopSolver.getUnsatisfiedConstraints( getConstraints( true ) );
      if ( unsatisfiedConstraints.isEmpty() ) {
        System.err.println( getName() + "'s constraints were not satisfied!" );
      } else {
        System.err.println( "Could not resolve the following "
                            + unsatisfiedConstraints.size()
                            + " constraints for " + getName() + ":" );
        for ( Constraint c : unsatisfiedConstraints ) {
          c.isSatisfied();
          System.err.println( c.toString() );
        }
      }
    }
    
    // TODO -- REVIEW -- ???
    for ( Map.Entry< Parameter< ? >, Set< Effect > > e : 
          effects.entrySet() ) {
      TimeVarying< ? > tv = (TimeVarying< ? >)e.getKey().getValue();
      for ( Effect eff : e.getValue() ) {
        eff.applyTo( tv, false ); //, startTime, duration );
      }
    }
    
    // HACK -- Sleeping to separate system.err from system.out.
    try {
      Thread.sleep( 100 );
    } catch ( InterruptedException e1 ) {
      e1.printStackTrace();
    }

    System.out.println("execution:\n" + executionToString());
    simulate(1.0e6, System.out);
  }

  public void simulate(double timeScale, java.io.OutputStream os ) {
    EventSimulation sim = new EventSimulation( getEvents( true ) );
    sim.add( this );
    for ( TimeVarying< ? > tv : getTimeVaryingObjects( true ) ) {
      if ( tv instanceof TimeVaryingMap ) {
        sim.add( (TimeVaryingMap< ? >)tv );
      }
    }
    sim.simulate( timeScale, os );
  }
  
  // Create an ElaborationRule for constructing an eventClass with
  // constructorParamTypes.
  // This method assumes that there is a constructor whose parameters match the
  // types of the arguments.
  public < T extends Event > ElaborationRule
      addElaborationRule( Expression< Boolean > condition,
                          Parameter< ? > enclosingInstance,
                          Class< T > eventClass,
                          String eventName,
                          Expression<?>[] arguments ) {
    
    // Now create the EventInvocation from the constructor and arguments. 
    Vector<EventInvocation> invocation = new Vector<EventInvocation>();
    EventInvocation newInvocation = null;
    newInvocation =
        new EventInvocation( eventClass, eventName,
                             enclosingInstance, arguments,
                             (Map< String, Object >)null );
    invocation.add(newInvocation);
    Vector<Event> eventVector = new Vector<Event>();
    ElaborationRule elaborationRule = new ElaborationRule(condition, invocation);
    elaborations.put(elaborationRule, eventVector);
    return elaborationRule;
  }

  public < T extends Event > ElaborationRule
  addElaborationRule( Expression< Boolean > condition,
                      Object enclosingInstance,
                      Class< T > eventClass,
                      String eventName,
                      Expression<?>[] arguments ) {
    return addElaborationRule( condition,
                               new Parameter< Object >( "", null,
                                                        enclosingInstance, null ),
                               eventClass, eventName, arguments );
  }
    /* (non-Javadoc)
   * @see event.Event#addEffect(event.TimeVarying, java.lang.reflect.Method, java.util.Vector)
   */
  @Override
  public void addEffect( Parameter< ? > sv,
                         Object obj, Method effectFunction,
                         Vector< Object > arguments ) {
    Effect e = new EffectFunction( obj, effectFunction, arguments );
    Set< Effect > effectSet = null;
    if ( effects.containsKey( sv ) ) {
      effectSet = effects.get( sv );
    }
    if ( effectSet == null ) {
      effectSet = new HashSet< Effect >();
      effects.put( sv, effectSet );
    }
    effectSet.add( e );
  }

  /* (non-Javadoc)
   * @see event.Event#addEffect(event.TimeVarying, java.lang.Object, java.lang.reflect.Method, java.lang.Object)
   */
  @Override
  public void addEffect( Parameter< ? > sv,
                         Object obj, Method method, Object arg ) {
    Vector< Object > v = new Vector< Object >();
    v.add( arg );
    addEffect( sv, obj, method, v );
  }

  // Gather any parameter instances contained by this event.
  @Override
  public Set< Parameter< ? > > getParameters( boolean deep ) {
    Set< Parameter< ? > > set = super.getParameters( deep );
    if ( deep ) {
      for ( Event e : getEvents( deep ) ) {
        if ( e instanceof HasParameters ) {
          set.addAll( ((HasParameters)e).getParameters( deep ) );
        }
      }
    }
    return set;
  }

  @Override
  protected boolean tryToSatisfy() {
    boolean satisfied = super.tryToSatisfy();
    satisfyElaborations();
    Debug.outln( this.getClass().getName() + " satisfy loop called satisfyElaborations() " );
    return satisfied;
  }
  
  public Collection< Constraint > getConstraints( boolean deep ) {
    Collection< Constraint > set = super.getConstraints( deep );
    set.add( elaborationsConstraint );
    set.add( effectsConstraint );
    if ( deep ) {
      for ( Event event : getEvents( deep ) ) {
        if ( event instanceof HasConstraints ) {
          set.addAll( ( (HasConstraints)event ).getConstraints() );
        }
      }
    }
    return set;
  }

  // Gather any event instances contained by this event.
  // NOTE: Uses reflection to dig events out of members, but does not look in
  // arrays or collections other than elaborations, so this may need
  // redefinition in subclasses.
  @Override
  public Set< Event > getEvents( boolean deep ) {
    Set< Event > set = new TreeSet< Event >();
    for ( Entry< ElaborationRule, Vector< Event > > e :
          elaborations.entrySet() ) {
      for ( Event event : e.getValue() ) {
        set.add( event );
        if ( deep ) {
          if ( event instanceof HasEvents )
          set.addAll( ((HasEvents)event).getEvents( deep ) );
        }
      }
    }
    return set;
  }

  // Conditionally create child event instances from this event instance.
  /* (non-Javadoc)
   * @see event.Event#elaborate(boolean)
   */
  @Override
  public void elaborate( boolean force ) {
    if ( elaborations == null ) {
      elaborations = new TreeMap< ElaborationRule, Vector< Event > >();
    }
    for ( Entry< ElaborationRule, Vector< Event > > er : elaborations.entrySet() ) {
      Debug.outln( getName() + " trying to elaborate " + er );
      elaborate( er, force );
    }
  }

  /**
   * @param entry
   * @return
   */
  protected boolean isElaborated( Entry< ElaborationRule, Vector< Event > > entry ) {
    return !( entry.getValue() == null || entry.getValue().isEmpty() );
  }
  
  /**
   * @param entry
   * @param force
   * @return
   */
  public boolean elaborate( Entry< ElaborationRule, Vector< Event > > entry,
                            boolean force ) {
    boolean elaborated = true;
    if ( !isElaborated( entry ) || force ) {
      Vector< Event > oldEvents = entry.getValue(); 
      elaborated = entry.getKey().attemptElaboration( oldEvents, true );
    }
    return elaborated;
  }
  
  /* (non-Javadoc)
   * @see event.Event#executionToString()
   */
  @Override
  public String executionToString() {
    StringBuffer sb = new StringBuffer();
    Set< Event > events = new TreeSet< Event >();
    events.add( this );
    events.addAll( getEvents( true ) );
    for ( Event e : events ) {
      sb.append( e.toString() + "\n" );
    }
    for ( TimeVarying<?> tv : getTimeVaryingObjects( true ) ) {
      sb.append( tv.toString() + "\n" );
    }
    return sb.toString();
  }

 // getters and setters

  /* (non-Javadoc)
   * @see event.Event#getStartTime()
   */
  @Override
  public Timepoint getStartTime() {
    return startTime;
  }

  /* (non-Javadoc)
   * @see event.Event#setStartTime(event.Timepoint)
   */
  @Override
  public void setStartTime( Timepoint startTime ) {
    this.startTime = startTime;
  }

  /* (non-Javadoc)
   * @see event.Event#getDuration()
   */
  @Override
  public Duration getDuration() {
    return duration;
  }

  /* (non-Javadoc)
   * @see event.Event#setDuration(event.Duration)
   */
  @Override
  public void setDuration( Duration duration ) {
    this.duration = duration;
  }

  /* (non-Javadoc)
   * @see event.Event#getEndTime()
   */
  @Override
  public Timepoint getEndTime() {
    return endTime;
  }

  /* (non-Javadoc)
   * @see event.Event#setEndTime(event.Timepoint)
   */
  @Override
  public void setEndTime( Timepoint endTime ) {
    this.endTime = endTime;
  }

  /* (non-Javadoc)
   * @see event.Event#getEffects()
   */
  @Override
  public Map< Parameter< ? >, Set< Effect > > getEffects() {
    return effects;
  }

  /* (non-Javadoc)
   * @see event.Event#setEffects(java.util.SortedMap)
   */
  @Override
  public void setEffects( SortedMap< Parameter< ? >, Set< Effect > > effects ) {
    this.effects = effects;
  }

  /* (non-Javadoc)
   * @see event.Event#getElaborations()
   */
  @Override
  public Map< ElaborationRule, Vector< Event >> getElaborations() {
    return elaborations;
  }

  /* (non-Javadoc)
   * @see event.Event#setElaborations(java.util.Map)
   */
  @Override
  public void setElaborations( Map< ElaborationRule, 
                               Vector< Event > > elaborations ) {
    this.elaborations = elaborations;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#compareTo(gov.nasa.jpl.ae.event.ParameterListenerImpl)
   */
  @Override
  public int compareTo( ParameterListenerImpl o ) {
    int compare = 0;//super.compareTo( o );
    if ( compare != 0 ) return compare;
    compare = getClass().getName().compareTo( o.getClass().getName() );
    if ( compare != 0 ) return compare;
    if ( o instanceof DurativeEvent && this instanceof DurativeEvent) {
      Event oe = (DurativeEvent)o;
      compare = startTime.compareTo( oe.getStartTime() );
      if ( compare != 0 ) return compare;
      compare = endTime.compareTo( oe.getEndTime() );
      if ( compare != 0 ) return compare;
    }
    compare = Utils.compareSets( parameters, o.getParameters() );
    return compare;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#handleValueChangeEvent(gov.nasa.jpl.ae.event.Parameter)
   * 
   * An event owns all of its parameters because it is required to contain any
   * dependency that sets one of its parameters and has some connection through
   * its other members to any reference or constraint on it. Thus, an event must
   * know about the parent event from which it is elaborated if the parent
   * references the parameter.
   * handleValueChangeEvent( parameter, newValue ) updates dependencies,
   * effects, and elaborations for the changed parameter.
   */
  @Override
  public void handleValueChangeEvent( Parameter< ? > parameter ) {
    // REVIEW -- Should we be passing in a set of parameters? Find review/todo
    // note on staleness table.

    // The super class updates the dependencies.
    super.handleValueChangeEvent( parameter );
    
    // Update elaborations.
    for ( Entry< ElaborationRule, Vector< Event > > entry : 
          getElaborations().entrySet() ) {
      //entry.getKey().handleValueChangeEvent( parameter );

      // Initialize some local variables.
      Vector< Event > elaboratedEvents = entry.getValue();  
      ElaborationRule elaborationRule = entry.getKey();
      
      // REVIEW -- Do we want to pass in true here?
      boolean elaborated = 
          elaborationRule.attemptElaboration( elaboratedEvents, false );
      
      // Make sure that elaborated events have handled parameter change.
      if ( elaborated ) { // conditionSatisfied == true here
        assert( elaborationRule.getEventInvocations().size() ==
                elaboratedEvents.size() );
        for ( int i=0; i < elaboratedEvents.size(); ++i ) {
          EventInvocation inv = elaborationRule.getEventInvocations().get( i );
          if ( inv.getParameters( true ).contains( parameter ) ) {
            Event event = elaboratedEvents.get( i );
            if ( event instanceof ParameterListener ) {
              ((ParameterListener)event).handleValueChangeEvent( parameter );
            }
          }
        }
      }
    }

//    Iterator< Entry< Parameter< ? >, Set< Effect >>> i =  
//        getEffects().entrySet().iterator();
//    Map< Parameter< ? >, Set< Effect >> removedForReinserting =
//        new TreeMap< Parameter< ? >, Set< Effect > >();
    ArrayList< Pair< Parameter< ? >, Set< Effect > > > a =
        new ArrayList< Pair< Parameter< ? >, Set< Effect > > >();
    for ( Entry< Parameter< ? >, Set< Effect > > e : getEffects().entrySet() ) {
      a.add( new Pair< Parameter< ? >, Set< Effect > >( e.getKey(), e.getValue() ) );
    }
    getEffects().clear();
//    while ( i.hasNext() ) {
//      Entry< Parameter< ? >, Set< Effect > > e = i.next();
//    //for ( Entry< Parameter< ? >, Set< Effect > > e : getEffects().entrySet() ) {
//      // Temporarily remove in case propagation in TimeVarying can corrupt entry keys.
//      //getEffects().entrySet().remove( e );
    for ( Pair< Parameter< ? >, Set< Effect > > p : a ) {
      Parameter< ? > tlParam = p.first;
//      Parameter< ? > tlParam = e.getKey();
      TimeVarying< ? > timeline = (TimeVarying< ? >)tlParam.getValue();
      Set< Effect > effectSet = p.second;
//      Set< Effect > effectSet = e.getValue();
//      //i.remove();
      for (Effect effect : effectSet ) {
        boolean hasParameter = false;
        if ( timeline instanceof HasParameters ) {
          if ( ( (HasParameters)timeline ).hasParameter( parameter, false ) ) {
            hasParameter = true;
            if ( timeline instanceof ParameterListener ) {
              ( (ParameterListener)timeline ).handleValueChangeEvent( parameter );
            }
          }
        }
        if ( !hasParameter && effect instanceof HasParameters ) {
          hasParameter =
              ( (HasParameters)effect ).hasParameter( parameter, false );
        }
        if ( hasParameter && timeline != null ) {
          // TODO -- REVIEW -- is this right?
          effect.unApplyTo( timeline ); // , startTime, duration );
          effect.applyTo( timeline, true ); // , startTime, duration );
        }
        getEffects().put( tlParam, effectSet );
        //getEffects().put( e.getKey(), e.getValue() );
      }
    }
//    getEffects().putAll( removedForReinserting );

  }

  /**
   * @return whether or not the constraints of the elaborations have been
   *         satisfied.
   */
  public boolean satisfyElaborations() {
    boolean satisfied = true;
    elaborate( true );
    Vector< Vector< Event > > elaboratedEvents = new Vector< Vector<Event> >();
    for ( Vector< Event > v : elaborations.values() ) {
      elaboratedEvents.add( new Vector< Event >( v ) );
    }
    for ( Vector< Event > v : elaboratedEvents ) {
      for ( Event e : v ) {
        if ( e instanceof Satisfiable ) {
          if ( !( (Satisfiable)e ).isSatisfied() ) {
            if ( !( (Satisfiable)e ).satisfy() ) {
              satisfied = false;
            }
          }
        }
      }
    }
    return satisfied;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#refresh(gov.nasa.jpl.ae.event.Parameter)
   */
  @Override
  public boolean refresh( Parameter< ? > parameter ) {
    boolean didRefresh = super.refresh( parameter );
    
    if ( !didRefresh ) {
      for ( Event e : getEvents( false ) ) {
        if ( e instanceof ParameterListener ) {
          if ( ((ParameterListener)e).refresh( parameter ) ) return true;
        }
      }
    }
    return didRefresh;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#setStaleAnyReferencesTo(gov.nasa.jpl.ae.event.Parameter)
   */
  @Override
  public void setStaleAnyReferencesTo( Parameter< ? > changedParameter ) {
    
    // Alert affected dependencies.
    super.setStaleAnyReferencesTo( changedParameter );

    for ( Event e : getEvents( false ) ) {
      if ( e instanceof ParameterListener ) {
        ((ParameterListener)e).setStaleAnyReferencesTo( changedParameter );
      }
    }
  }

}
