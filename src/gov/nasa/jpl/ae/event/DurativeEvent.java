package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.ConstraintLoopSolver;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasConstraints;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.solver.Solver;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.Debug;
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
public class DurativeEvent implements Event, Cloneable,
                           HasEvents, Groundable, Satisfiable,
                           Comparable< Event >, ParameterListener,
                           HasTimeVaryingObjects {

  // Constants
  
  final double timeoutSeconds = 3.0;

  // Static members
  
  protected static int counter = 0;

  // Other Members

  // TODO -- REVIEW -- can we avoid name members for Parameters and use
  // reflection? Doing this for events as of 2012-06-22.
  protected String name = null;
  public Timepoint startTime = new Timepoint( "startTime", this );
  public Duration duration = new Duration( this );
  public Timepoint endTime = new Timepoint( "endTime", this );
  // TODO -- REVIEW -- Try to not construct members below until/unless necessary.
  protected SortedSet< Parameter< ? > > parameters =
      new TreeSet< Parameter< ? > >();
  // TODO -- REVIEW -- create TimeVariableParameter and EffectMap classes for
  // effects?
  protected Map< Parameter< ? extends TimeVarying< ? > >, Set< Effect > > effects =
      new TreeMap< Parameter< ? extends TimeVarying< ? > >, Set< Effect > >();
  protected Vector< ConstraintExpression > constraintExpressions = new Vector< ConstraintExpression >();
  // TODO -- REVIEW -- should dependencies these be folded in with effects?
  protected Vector< Dependency< ? > > dependencies =
      new Vector< Dependency< ? > >();
  protected Map< ElaborationRule, Vector< Event > > elaborations =
      new TreeMap< ElaborationRule, Vector< Event > >();

  protected Solver solver = new ConstraintLoopSolver( timeoutSeconds );

  protected SortedSet< ? extends TimeVarying< ? > > timeVaryingObjects =
      new TreeSet< TimeVarying< ? > >();

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
      areEffectsOnTimeVaryingSatisfied( Parameter< ? extends TimeVarying< ? > > variable,
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
        satisfyEffectsOnTimeVarying( Parameter< ? extends TimeVarying< ? > > variable,
                                     Set< Effect > effects ) {
      boolean satisfied = true;
      if ( !variable.isGrounded() ) {
        variable.ground();
      }
      if ( !variable.isSatisfied() ) {
        variable.satisfy();
      }
      for ( Effect e : effects ) {
        if ( !e.isApplied( variable ) ) {
          if ( !variable.isGrounded() ) {
            satisfied = false;
          } else {
            TimeVarying<?> value = variable.getValue();
            e.applyTo( value, true );
          }
        }
      }
      return satisfied;
    }
    
    @Override
    public boolean satisfy() {
      boolean satisfied = true;
      for ( Entry< Parameter< ? extends TimeVarying< ? > >, Set< Effect > > entry : getEffects().entrySet() ) {
        Set< Effect > set = entry.getValue();
        Parameter< ? extends TimeVarying< ? > > variable = entry.getKey();
        if ( !satisfyEffectsOnTimeVarying( variable, set ) ) {
          satisfied = false;
        }
      }
      return satisfied;
    }
    
    @Override
    public boolean isSatisfied() {
      for ( Entry< Parameter< ? extends TimeVarying< ? > >, Set< Effect > > entry : getEffects().entrySet() ) {
        Set< Effect > set = entry.getValue();
        Parameter< ? extends TimeVarying< ? > > variable = entry.getKey();
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
    addDependency( endTime, sum );
    Functions.Sub< Integer, Integer > sub1 =
        new Functions.Sub< Integer, Integer >(
            new Expression< Integer >( endTime ),
            new Expression< Integer >( duration ) );
    addDependency( startTime, sub1 );
    Functions.Sub< Integer, Integer > sub2 =
        new Functions.Sub< Integer, Integer >(
            new Expression< Integer >( endTime ),
            new Expression< Integer >( startTime ) );
    addDependency( duration, sub2 );
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
    for ( Map.Entry< Parameter< ? extends TimeVarying< ? > >, Set< Effect > > e : 
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
      effects.put( (Parameter< ? extends TimeVarying< ? > >)e.getKey().clone(),
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

  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep ) {
    boolean subbed = false;
    for ( ConstraintExpression c : constraintExpressions ) {
      boolean s = c.substitute( p1, p2, deep );
      subbed = subbed || s;
    }
    HasParameters.Helper.substitute( effects, p1, p2, deep );
    for ( Entry< Parameter< ? extends TimeVarying< ? >>, Set< Effect >> e : effects.entrySet() ) {
      if ( e.getValue() instanceof HasParameters ) {
        ( (HasParameters)e.getValue() ).substitute( p1, p2, deep );
      }
    }
    for ( Dependency< ? > d : dependencies ) {
      if ( d instanceof HasParameters ) {
        ( (HasParameters)d ).substitute( p1, p2, deep );
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

  // Build a list of pairs of parameters, each in this event paired with the
  // corresponding ones in another event. Subclasses may need to override this
  // to add their own parameters.
  protected List< Pair< Parameter< ? >, Parameter< ? > > >
      buildSubstitutionList( DurativeEvent durativeEvent ) {
    ArrayList< Pair< Parameter< ? >, Parameter< ? > > > subList =
        new ArrayList< Pair< Parameter< ? >, Parameter< ? > > >();
    Iterator< Parameter< ? > > i1 = parameters.iterator();
    Iterator< Parameter< ? > > i2 = durativeEvent.parameters.iterator();
    while ( i1.hasNext() ) {
      subList.add( new Pair< Parameter< ? >, Parameter< ? > >( i1.next(),
                                                               i2.next() ) );
    }
    return subList;
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
        sb.append( ", " + ((Parameter<?>)p).toString( false ) );
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
    boolean satisfied = satisfy();
    if ( !satisfied ) {
      satisfied = solver.solve( getConstraints( true ) );
    }
    if ( !satisfied ) {
      Collection< Constraint > unsatisfiedConstraints =
          ConstraintLoopSolver.getUnsatisfiedConstraints( getConstraints( true ) );
      if ( !unsatisfiedConstraints.isEmpty() ) {
//      if ( !solver.getUnsatisfiedConstraints().isEmpty() ) {
        System.err.println( "Could not resolve the following constraints for " + getName() + ":" );
        for ( Constraint c : unsatisfiedConstraints ) {
          System.err.println( c.toString() );
        }
      } else {
        System.err.println( getName() + "'s constraints were not satisfied!" );
      }
    }
    
    // TODO -- REVIEW -- ???
    for ( Map.Entry< Parameter< ? extends TimeVarying< ? > >, Set< Effect > > e : 
          effects.entrySet() ) {
      TimeVarying< ? > tv = e.getKey().getValue();
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
    simulate(1, System.out);
  }

  public void simulate(long tickInMilliseconds, java.io.OutputStream os ) {
    EventSimulation sim = new EventSimulation( getEvents( true ) );
    sim.add( this );
    for ( TimeVarying< ? > tv : getTimeVaryingObjects( true ) ) {
      if ( tv instanceof TimeVaryingMap ) {
        sim.add( (TimeVaryingMap< ? >)tv );
      }
    }
    sim.simulate( 0, os );
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
/* Doing this in EventInvocation
    // If elaborating a non-static inner class, need to add parent object as
    // the first argument to the constructor.
    boolean nonStaticInnerClass = Utils.isInnerClass( eventClass );
    Object newArgs[] = arguments;
    if ( nonStaticInnerClass ) {
      newArgs = new Object[arguments.length + 1];
      // Warning!  Assuming that this is the parent object for the inner class.
      assert eventClass.getEnclosingClass().isAssignableFrom( this.getClass() );
      assert enclosingInstance != null;
      newArgs[0] = enclosingInstance;
      for ( int i = 0; i < arguments.length; ++i ) {
        newArgs[ i + 1 ] = arguments[ i ];
      }
    }
    // Collect argument types to search for the constructor.
    int length = newArgs.length;
    Class< ? > constructorParamTypes[] = new Class< ? >[ length ];
    for ( int i = 0; i < length; ++i ) {
      if ( newArgs[i] == null ) {
        // TODO -- This is an assumption.  We need a better way to find the constructor.
        constructorParamTypes[i] = Expression.class;
        continue;
      }
      Class< ? > c = newArgs[ i ].getClass();
      if ( newArgs[ i ] instanceof Expression ) {
        c = Expression.class;
      }
      constructorParamTypes[i] = c;
    }
*/
    
    // Now create the EventInvocation from the constructor and arguments. 
    Vector<EventInvocation> invocation = new Vector<EventInvocation>();
    EventInvocation newInvocation = null;
/*  Doing this in EventInvocation
    Constructor< ? > ctor = Utils.getConstructorForArgs( eventClass, newArgs );
*/
    newInvocation =
        new EventInvocation( eventClass, eventName,
                             //ctor.getParameterTypes(),
                             //eventClass.getConstructor( constructorParamTypes ),
                             enclosingInstance,
                             arguments,//newArgs,
                             (Map< String, Object >)null );
    invocation.add(newInvocation);
    Vector<Event> eventVector = new Vector<Event>();
    ElaborationRule elaborationRule = new ElaborationRule(condition, invocation);
    elaborations.put(elaborationRule, eventVector);
    return elaborationRule;
  }

  // TODO -- separate this method and removeDependency from Event to
  // HasDependencies?
  /* (non-Javadoc)
   * @see event.Event#addDependency(event.Parameter, event.Expression)
   */
  @Override
  public < T > void addDependency( Parameter< T > p, Expression< T > e ) {
    Dependency< T > d = new Dependency< T >( p, e );
    dependencies.add( d );
  }

  public < T > void addDependency( Parameter< T > p, Parameter< T > source ) {
    addDependency( p, new Expression< T >( source ) );
  }

  /* (non-Javadoc)
   * @see event.Event#removeDependency(event.Parameter)
   */
  @Override
  public < T > boolean
      removeDependency( Parameter< T > p ) {
    boolean removed = false;
    Iterator< Dependency< ? > > i = dependencies.iterator();
    while ( i.hasNext() ) {
      Dependency< ? > d = i.next();
      if ( d.parameter == p ) {
        i.remove();
        removed = true;
      }
    }
    return removed;
  }
  
  /* (non-Javadoc)
   * @see event.Event#addEffect(event.TimeVarying, java.lang.reflect.Method, java.util.Vector)
   */
  @Override
  public void addEffect( Parameter< ? extends TimeVarying< ? > > sv,
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
  public void addEffect( Parameter< ? extends TimeVarying< ? > > sv,
                         Object obj, Method method, Object arg ) {
    Vector< Object > v = new Vector< Object >();
    v.add( arg );
    addEffect( sv, obj, method, v );
  }

  @Override
  public boolean isGrounded() {
    for ( Parameter< ? > p : parameters ) {
      if ( !p.isGrounded() ) return false;
    }
    return true;
  }

  @Override
  public boolean ground() {
    final double timeoutMilliseconds = 50.0;
    double startTime = System.currentTimeMillis();
    double curTime = startTime;
    
    boolean satisfied = false;
    boolean first = true;
    while ( first
            || ( !satisfied && ( ( curTime = System.currentTimeMillis() )
                                 - startTime < timeoutMilliseconds ) ) ) {
      satisfied = true;
      first = false;
      for ( Parameter< ? > p : getFreeParameters( false ) ) {
        if ( !p.isGrounded() ) {
          if ( !p.ground() ) {
            satisfied = false;
          }
        }
      }
      for ( Dependency< ? > d : getDependencies() ) {
        if ( !d.isSatisfied() ) {
          if ( !d.satisfy() ) {
            satisfied = false;
          }
        }
      }
    }
    return satisfied;
  }

  public Vector< ConstraintExpression > getUnsatisfiedConstraints() {
    Vector< ConstraintExpression > v = new Vector< ConstraintExpression >();
    for ( ConstraintExpression c : constraintExpressions ) {
      if ( !c.isSatisfied() ) {
        v.add( c );
      }
    }
    return v;
  }

  // @Override
  // public Set<Parameter<?>> getAll() {
  // return parameters;
  // }

  // TODO -- This is not finished. Need to get deep dependents.
  /* (non-Javadoc)
   * @see event.Event#getDependentParameters(boolean)
   */
  @Override
  public Set< Parameter< ? > > getDependentParameters( boolean deep ) {
    TreeSet< Parameter< ? > > set = new TreeSet< Parameter< ? > >();
    for ( Dependency< ? > d : dependencies ) {
      set.add( d.parameter );
    }
    return set;
  }

  // Gather any parameter instances contained by this event.
  // NOTE: Uses reflection to dig events out of members, but does not look in
  // arrays or collections other than this.parameters, so this may need
  // redefinition in subclasses.
  @Override
  public Set< Parameter< ? > > getParameters( boolean deep ) {
    // TODO
    TreeSet< Parameter< ? > > set = new TreeSet< Parameter< ? > >();
    set.addAll( getParameters() );
    if ( deep ) {
      for ( Event e : getEvents( deep ) ) {
        if ( e instanceof HasParameters ) {
          set.addAll( ((HasParameters)e).getParameters( deep ) );
        }
      }
      // TODO -- getDeclaredFields won't allow access to protected members
      // for ( Field f : this.getClass().getDeclaredFields() ) {
      // try {
      // Object o = f.get(this);
      // if ( o != null && o != this.parameters ) {
      // if ( o instanceof Parameter<?> ) {
      // set.add( (Parameter<?>) o );
      // }
      // if ( o instanceof HasParameter ) {
      // set.addAll( ((HasParameter) o).getParameters( deep ) );
      // }
      // }
      // } catch (IllegalArgumentException e) {
      // // TODO Auto-generated catch block
      // e.printStackTrace();
      // } catch (IllegalAccessException e) {
      // // TODO Auto-generated catch block
      // e.printStackTrace();
      // }
      // }
    }
    return set;
  }

  // TODO -- This is not finished. Need to get deep dependents.
  @Override
  public Set< Parameter< ? > > getFreeParameters( boolean deep ) {
    Assert.assertFalse( "This method does not yet support deep=true!", deep );
    Set< Parameter< ? > > set = getParameters( deep );
    Set< Parameter< ? > > dependents = getDependentParameters( deep );
    set.removeAll( dependents );
    return set;
  }
  @Override
  public void setFreeParameters( Set< Parameter< ? >> freeParams ) {
    Assert.assertTrue( "This method is not supported!", false );
  }

  @Override
  public boolean isSatisfied() {
    for ( Constraint c : getConstraints() ) {
      if ( !c.isSatisfied() ) {
        return false;
      }
    }
    return true;
  }

  // TODO -- How does this work with elaborations? Do we need a "deep"
  // parameter?
  // TODO -- REVIEW -- Given that dependencies may flow from parent event to
  // child event and vice-versa, there needs to be something similar to PCN
  // propagation--this will be useful for constraint satisfaction, elaboration, and effects, too. Events
  // have Dependencies. Should Parameters instead link them (and constraints)?  Have an global PCN that links them?
  @Override
  public boolean satisfy() {
    double clockStart = System.currentTimeMillis();
    
    boolean satisfied = false;
    double curTimeLeft =
        ( timeoutSeconds * 1000.0 - ( System.currentTimeMillis() - clockStart ) );
    
    // TODO -- REVIEW -- How do we get elaborations (conditional constraints
    // into the problem)? Add all possible constraints as implied by conditions?
    // Treat each elaboration as a constraint?
    while (!satisfied && ( curTimeLeft > 0.0 ) ) {
      Debug.outln( this.getClass().getName() + " satisfy loop with " + curTimeLeft );
      ground();
      Debug.outln( this.getClass().getName() + " satisfy loop called ground() " );
      
      satisfyElaborations();
      Debug.outln( this.getClass().getName() + " satisfy loop called satisfyElaborations() " );
      
      Collection< Constraint > allConstraints = getConstraints( true );
      satisfied = solver.solve( allConstraints );
      
      satisfied = isSatisfied();
      Debug.outln( this.getClass().getName()
                          + " satisfy loop called solve(): satisfied = "
                          + satisfied );
      curTimeLeft =
          ( timeoutSeconds * 1000.0 - ( System.currentTimeMillis() - clockStart ) );
    }
    return satisfied;
  }

  @Override
  public Collection< Constraint > getConstraints() {
    return getConstraints( true );
  }

  public Collection< Constraint > getConstraints( boolean deep ) {
    Set< Constraint > set = new TreeSet< Constraint >();
    set.addAll( constraintExpressions );
    set.add( elaborationsConstraint );
    set.add( effectsConstraint );
    set.addAll( dependencies );
    if ( deep ) {
//      for ( Entry< ElaborationRule, Vector< Event > > e : elaborations.entrySet() ) {
        for ( Event event : getEvents( deep ) ) {
          if ( event instanceof HasConstraints ) {
            set.addAll( ( (HasConstraints)event ).getConstraints() );
          }
        }
//      }
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

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasTimeVaryingObjects#getTimeVaryingObjects(boolean)
   */
  @Override
  public Set< TimeVarying< ? > > getTimeVaryingObjects( boolean deep ) {
    Set< TimeVarying< ? > > s = new HashSet< TimeVarying< ? > >();
    s.addAll( timeVaryingObjects );
    // Rebuilding the set in case parameter values change. 
    for ( Parameter< ? > p : getParameters( deep ) ) {
      Object value = p.getValue();
      if ( value != null ) {
        if ( value instanceof TimeVarying ) {
          s.add( (TimeVarying< ? >)value );
        }
        if ( deep && value instanceof HasTimeVaryingObjects ) {
          s.addAll( ( (HasTimeVaryingObjects)value ).getTimeVaryingObjects( deep ) );
        }
      }
    }
    return s;
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
   * @see event.Event#getParameters()
   */
  @Override
  public SortedSet< Parameter< ? >> getParameters() {
    return parameters;
  }

  /* (non-Javadoc)
   * @see event.Event#setParameters(java.util.SortedSet)
   */
  @Override
  public void setParameters( SortedSet< Parameter< ? >> parameters ) {
    this.parameters = parameters;
  }

  /* (non-Javadoc)
   * @see event.Event#getEffects()
   */
  @Override
  public Map< Parameter< ? extends TimeVarying< ? > >, Set< Effect > > getEffects() {
    return effects;
  }

  /* (non-Javadoc)
   * @see event.Event#setEffects(java.util.SortedMap)
   */
  @Override
  public void setEffects( SortedMap< Parameter< ? extends TimeVarying< ? > >, Set< Effect > > effects ) {
    this.effects = effects;
  }

  /**
   * @return the solver
   */
  public Solver getSolver() {
    return solver;
  }

  /**
   * @param solver the solver to set
   */
  public void setSolver( Solver solver ) {
    this.solver = solver;
  }

  /* (non-Javadoc)
   * @see event.Event#getConstraints()
   */
  @Override
  public Vector< ConstraintExpression > getConstraintExpressions() {
    return constraintExpressions;
  }

  /* (non-Javadoc)
   * @see event.Event#setConstraints(java.util.Vector)
   */
  @Override
  public void setConstraintExpressions( Vector< ConstraintExpression > constraints ) {
    this.constraintExpressions = constraints;
  }

  /* (non-Javadoc)
   * @see event.Event#getDependencies()
   */
  @Override
  public Vector< Dependency< ? >> getDependencies() {
    return dependencies;
  }

  /* (non-Javadoc)
   * @see event.Event#setDependencies(java.util.Vector)
   */
  @Override
  public void setDependencies( Vector< Dependency< ? >> dependencies ) {
    this.dependencies = dependencies;
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

  @Override
  public int compareTo( Event o ) {
    int compare = getClass().getName().compareTo( o.getClass().getName() );
    if ( compare != 0 ) return compare;
    compare = startTime.compareTo( o.getStartTime() );
    if ( compare != 0 ) return compare;
    compare = endTime.compareTo( o.getEndTime() );
    if ( compare != 0 ) return compare;
    compare = Utils.compareSets( parameters, o.getParameters() );
    return compare;
  }

  // An event owns all of its parameters because it is required to contain any
  // dependency that sets one of its parameters and has some connection through
  // its other members to any reference or constraint on it. Thus, an event must
  // know about the parent event from which it is elaborated if the parent
  // references the parameter.
  // handleValueChangeEvent( parameter, newValue ) updates dependencies,
  // effects, and elaborations for the changed parameter.
  @Override
  public void handleValueChangeEvent( Parameter< ? > parameter ) {
    // REVIEW -- Should we be passing in a set of parameters? Find review/todo
    // note on staleness table.
    
    // Alert affected dependencies.
    for ( Dependency<?> d : getDependencies() ) {
      d.handleValueChangeEvent( parameter );
    }
    
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

    Iterator< Entry< Parameter< ? extends TimeVarying< ? >>, Set< Effect >>> i =  
        getEffects().entrySet().iterator();
    Map< Parameter< ? extends TimeVarying< ? >>, Set< Effect >> removedForReinserting =
        new TreeMap< Parameter< ? extends TimeVarying< ? >>, Set< Effect > >();
    while ( i.hasNext() ) {
      Entry< Parameter< ? extends TimeVarying< ? > >, Set< Effect > > e = i.next();
      i.remove();
    //for ( Entry< Parameter< ? extends TimeVarying< ? > >, Set< Effect > > e : getEffects().entrySet() ) {
      // Temporarily remove in case propagation in TimeVarying can corrupt entry keys.
      //getEffects().entrySet().remove( e );
      Parameter< ? extends TimeVarying< ? > > tlParam = e.getKey();
      TimeVarying< ? > timeline = tlParam.getValue();
      Set< Effect > effectSet = e.getValue();
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
        removedForReinserting.put( e.getKey(), e.getValue() );
        //getEffects().put( e.getKey(), e.getValue() );
      }
    }
    getEffects().putAll( removedForReinserting );

  }

  @Override
  public void handleDomainChangeEvent( Parameter< ? > parameter ) {
                                       //Domain< ? > newDomain ) {
    // TODO -- What are we supposed to do? call satisfy()??? Not caching
    // constraint violations as of 2012-08-03.
  }

  //@Override
  public boolean satisfyElaborations() {
    boolean satisfied = true;
    elaborate( true );
    for ( Vector< Event > v : elaborations.values() ) {
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

  @Override
  public String getName() {
    if ( name != null ) return name;
    return getClass().getSimpleName();
  }

  @Override
  public void setName( String newName ) {
    if ( newName == null || newName.isEmpty() ) {
      Formatter formatter = new Formatter(Locale.US);
      newName = getClass().getSimpleName() + formatter.format( "%06d", (counter++) );
    }
    this.name = newName;
  }
  
  // Try to remove others' references to this, possibly because it is being
  // deleted.
  @Override
  public void detach() {
    for ( Parameter< ? > p : getParameters( false ) ) {
      if ( p.getOwner() == this ) {
        p.setOwner( null );
      }
    }
  }

  @Override
  public boolean isStale() {
    for ( Parameter< ? > p : getParameters() ) {
      if ( p.isStale() ) return true;
    }
    return false;
  }

  @Override
  public void setStale( boolean staleness ) {
    // TODO -- REVIEW -- Need anything here?
    assert false;
  }

  @Override
  public boolean refresh( Parameter< ? > parameter ) {
    boolean didRefresh = false;
    
    // Alert affected dependencies.
    for ( Dependency<?> d : getDependencies() ) {
      if ( d.refresh( parameter ) ) didRefresh = true;
    }

    if ( !didRefresh ) {
      for ( Event e : getEvents( false ) ) {
        if ( e instanceof ParameterListener ) {
          if ( ((ParameterListener)e).refresh( parameter ) ) return true;
        }
      }
    }
    return didRefresh;
  }

  @Override
  public void setStaleAnyReferencesTo( Parameter< ? > changedParameter ) {
    // Alert affected dependencies.
    for ( Dependency<?> d : getDependencies() ) {
      d.setStaleAnyReferencesTo( changedParameter );
    }

    for ( Event e : getEvents( false ) ) {
      if ( e instanceof ParameterListener ) {
        ((ParameterListener)e).setStaleAnyReferencesTo( changedParameter );
      }
    }

    // REVIEW -- DurativeEvent.handleValueChangeEvent() checks elaborations, so
    // maybe nothing needs to be done here.
//    for ( ElaborationRule rule : elaborations.keySet() ) {
//    }

    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep ) {
    return getParameters( deep ).contains( parameter );
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep ) {
    return !getDependentParameters( deep ).contains( p );
  }

}
