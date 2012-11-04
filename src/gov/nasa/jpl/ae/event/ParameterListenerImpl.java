/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;

import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.ConstraintLoopSolver;
import gov.nasa.jpl.ae.solver.HasConstraints;
import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.ae.solver.Random;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.solver.Solver;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.CompareUtils;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

/**
 * A class that manages Parameters, Dependencies, and Constraints.
 *
 */
public class ParameterListenerImpl extends HasIdImpl
                                   implements Cloneable, Groundable,
                                              Satisfiable,
                                              ParameterListener,
                                              HasConstraints,
                                              HasTimeVaryingObjects,
                                              Comparable< ParameterListenerImpl > {
  // Constants
  
  protected double timeoutSeconds = 1800.0;
  protected int maxLoopsWithNoProgress = 14;
  protected long maxPassesAtConstraints = 500;
  protected boolean usingTimeLimit = false;
  protected boolean usingLoopLimit = true;

  protected boolean snapshotSimulationDuringSolve = true;
  protected boolean snapshotToSameFile = true;
  protected String baseSnapshotFileName = "simulationSnapshot.txt";
  protected boolean amTopEventToSimulate = false;
  

  // Static members
  
  protected static int counter = 0;

  // Other Members

  protected String name = null;
  protected List< Parameter< ? > > parameters =
      new ArrayList< Parameter< ? > >();
  protected List< ConstraintExpression > constraintExpressions =
      new ArrayList< ConstraintExpression >();
  // TODO -- REVIEW -- should dependencies these be folded in with effects?
  protected ArrayList< Dependency< ? > > dependencies =
      new ArrayList< Dependency< ? > >();
  protected Solver solver = new ConstraintLoopSolver();

  protected Set< TimeVarying< ? > > timeVaryingObjects =
      new HashSet< TimeVarying< ? > >();

  // TODO -- Need to keep a collection of ParameterListeners (just as
  // DurativeEvent has getEvents())
  
  
  public ParameterListenerImpl() {
    this((String)null);
  }
  public ParameterListenerImpl( String name ) {
    setName( name );
  }
  public ParameterListenerImpl( ParameterListenerImpl parameterListenerImpl ) {
    this( null, parameterListenerImpl );
  }
  public ParameterListenerImpl( String name,
                                ParameterListenerImpl parameterListenerImpl ) {
    setName( name );

    // copy containers after clearing
    constraintExpressions.clear();
    dependencies.clear();
    for ( ConstraintExpression c : parameterListenerImpl.constraintExpressions ) {
      ConstraintExpression nc = new ConstraintExpression( c );
      constraintExpressions.add( nc );
    }
    for ( Dependency< ? > d : parameterListenerImpl.dependencies ) {
      Dependency< ? > nd = new Dependency( d );
      dependencies.add( nd );
    }

    // We need to make sure the copied constraints are on this event's
    // parameters and not that of the copied constraints.
    List< Pair< Parameter< ? >, Parameter< ? > > > subList =
        buildSubstitutionList( parameterListenerImpl );
    for ( Pair< Parameter< ? >, Parameter< ? > > p : subList ) {
      substitute( p.first, p.second, false, null );
    }
  }

  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2,
                             boolean deep,
                             Set<HasParameters> seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    boolean subbed = false;
    boolean s = HasParameters.Helper.substitute( constraintExpressions, p1, p2,
                                                 deep, seen );
    subbed = subbed || s;
    s = HasParameters.Helper.substitute( dependencies, p1, p2,
                                         deep, seen );
    subbed = subbed || s;
    return subbed;
  }

  // Build a list of pairs of parameters, each in this event paired with the
  // corresponding ones in another event. Subclasses may need to override this
  // to add their own parameters.
  protected List< Pair< Parameter< ? >, Parameter< ? > > >
      buildSubstitutionList( ParameterListener parameterListener ) {
    ArrayList< Pair< Parameter< ? >, Parameter< ? > > > subList =
        new ArrayList< Pair< Parameter< ? >, Parameter< ? > > >();
    Iterator< Parameter< ? > > i1 = parameters.iterator();
    Iterator< Parameter< ? > > i2 = parameterListener.getParameters(false, null).iterator();
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
    return new ParameterListenerImpl( this );
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return toString( false, null );
  }
  public String toString( boolean deep, Set< Object > seen ) {
    Pair< Boolean, Set< Object > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) deep = false;
    seen = pair.second;
    StringBuffer sb = new StringBuffer();
    sb.append( getClass().getName() ); // super.toString() adds hash code
    Set< Parameter< ? > > allParams = getParameters( false, null );
    if ( deep ) {
      for ( Object p : allParams ) {
        if ( p instanceof Parameter ) {
          sb.append( ", " + ((Parameter<?>)p).toString( false, false, deep, seen ) );
        } else {
          sb.append( ", " + p.toString() );
        }
      }
    }
    return sb.toString();
  }

  // TODO -- separate this method and removeDependency from Event to
  // HasDependencies?
  public < T > Dependency< ? > addDependency( Parameter< T > p, Expression< T > e ) {
    Debug.errorOnNull( "try to add a dependency on null", p );
    
    // Check if p is in enclosing class and call the enclosing class's addDependency() 
    if ( p.getOwner() != null && p.getOwner() != this ) {
      if ( p.getOwner() instanceof ParameterListenerImpl ) {
        ParameterListenerImpl pli = (ParameterListenerImpl)p.getOwner();
        return pli.addDependency( p, e );
      }
      Debug.error(getName() + " adding a dependency on a parameter it doesn't own.");
    }
    removeDependenciesForParameter( p );
    Dependency< T > d = new Dependency< T >( p, e );
// Default domains are shared.  The domains need to be cloned before intersecting them.
//    if ( e.type == Expression.Type.Parameter ) {
//      Parameter< T > ep = (Parameter< T >)e.expression;
//      if ( ep.getDomain() instanceof AbstractRangeDomain &&
//          p.getDomain() instanceof AbstractRangeDomain ) {
//        AbstractRangeDomain< T > ard = (AbstractRangeDomain< T >)p.getDomain();
//        AbstractRangeDomain< T > eard = (AbstractRangeDomain< T >)ep.getDomain();
//        eard.intersectRestrict( ard );
//        ard.intersectRestrict( eard );
//      }
//    }
    dependencies.add( d );
    return d;
  }

  public < T > Dependency< ? > addDependency( Parameter< T > p, Parameter< T > source ) {
    return addDependency( p, new Expression< T >( source ) );
  }

  public < T > boolean
      removeDependenciesForParameter( Parameter< T > p ) {
    //assert p.getOwner() == null || p.getOwner() == this;
    boolean removed = false;
    int ct = dependencies.size() - 1;
    while ( ct >= 0 ) {
      Dependency< ? > d = dependencies.get( ct );
      if ( //d != null && 
           d.parameter == p ) {
        dependencies.remove( ct );
        removed = true;
      }
      --ct;
    }
    return removed;
  }
  
//  public void deleteme() {
//    dependencies.remove( 0 );
//    dependencies.remove( new Dependency(null) );
//    dependencies.iterator();
//    dependencies.listIterator();
//    dependencies.listIterator(2);
//    dependencies.removeAll( dependencies );
//    dependencies.retainAll( dependencies );
//    dependencies.set( 0, null );
//    dependencies.toArray( null );
//  }
  
  public < T > Dependency< ? > getDependency( Parameter< T > p ) {
//    Iterator< Dependency< ? > > i = dependencies.iterator();
//    while ( i.hasNext() ) {
      for ( Dependency< ? > d : dependencies ) {
//      Dependency< ? > d = i.next();
      if ( //d != null && 
           d.parameter == p ) {
        return d;
      }
    }
    return null;
  }

  @Override
  public boolean isGrounded(boolean deep, Set< Groundable > seen) {
    Pair< Boolean, Set< Groundable > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return true;
    seen = pair.second;
    for ( Parameter< ? > p : parameters ) {
      if ( !p.isGrounded(deep, seen) ) return false;
    }
    return true;
  }

  @Override
  public boolean ground(boolean deep, Set< Groundable > seen) {
    Pair< Boolean, Set< Groundable > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return true;
    seen = pair.second;
    //final double timeoutMilliseconds = 50.0;
    //double startTime = System.currentTimeMillis();
    //double curTime = startTime;
    long numLoops = 0;
    
    boolean satisfied = false;
    boolean first = true;
    while ( first && numLoops < this.maxPassesAtConstraints ) {
//            || ( !satisfied && ( ( curTime = System.currentTimeMillis() )
//                                 - startTime < timeoutMilliseconds ) ) ) {
      ++numLoops;
      satisfied = true;
      first = false;
      Collection< Parameter< ? > > freeParams = getFreeParameters( false, null );
      for ( Parameter< ? > p : freeParams ) {
        if ( !p.isGrounded(deep, null) ) {
          if ( !p.ground(deep, seen) ) {
            satisfied = false;
          }
        }
      }
      for ( Dependency< ? > d : getDependencies() ) {
        if ( !d.isSatisfied(true, null) ) {
          if ( !d.satisfy(true, null) ) {
            satisfied = false;
          }
        }
      }
    }
    return satisfied;
  }

  public List< ConstraintExpression > getUnsatisfiedConstraints() {
    List< ConstraintExpression > list = new ArrayList< ConstraintExpression >();
    for ( ConstraintExpression c : constraintExpressions ) {
      if ( !c.isSatisfied(false, null) ) {
        list.add( c );
      }
    }
    return list;
  }

  // TODO -- This is not finished. Need to get deep dependents.
  public Set< Parameter< ? > > getDependentParameters( boolean deep,
                                                       Set<HasParameters> seen ) {
    Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
    for ( Dependency< ? > d : dependencies ) {
      set.add( d.parameter );
    }
    return set;
  }

  // Gather any parameter instances contained by this event.
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasParameters#getParameters(boolean, java.util.Set)
   */
  @Override
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set<HasParameters> seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
//    set.addAll( getParameters() );
//    if ( deep ) {
//      for ( Parameter<?> p : getParameters() ) {
//        if ( p.getValueNoPropagate() != null && 
//             p.getValueNoPropagate() instanceof HasParameters ) {
//          set = Utils.addAll( set, ( (HasParameters)p.getValueNoPropagate() ).getParameters( deep, seen ) );
//        }
//      }
//    }
    set = Utils.addAll( set,
                        HasParameters.Helper.getParameters( getParameters(),
                                                            deep, seen, true ) );
    if ( deep ) {
      set = Utils.addAll( set,
                          HasParameters.Helper.getParameters( getDependencies(),
                                                              deep, seen, true ) );
      set = Utils.addAll( set,
                          HasParameters.Helper.getParameters( getConstraintExpressions(),
                                                              deep, seen, true ) );
    }
    return set;
  }

  // TODO -- define this in HasParameters
  /**
   * @param deep
   * @param seen
   * @return parameters that are Timepoints
   */
  public Set< Timepoint > getTimepoints( boolean deep,
                                         Set<HasParameters> seen ) {
   Set< Timepoint > set = new HashSet< Timepoint >();
   for ( Parameter<?> p : getParameters( deep, seen ) ) {
     if ( p instanceof Timepoint ) {
       set.add((Timepoint)p);
     }
   }
   return set;
 }

 // TODO -- This is not finished. Need to get deep dependents.
  @Override
  public Set< Parameter< ? > > getFreeParameters( boolean deep,
                                                  Set<HasParameters> seen ) {
    Assert.assertFalse( "This method does not yet support deep=true!", deep );
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< Parameter< ? > > set = getParameters( deep, seen );
    Set< Parameter< ? > > dependents = getDependentParameters( deep, seen );
    set.removeAll( dependents );
    return set;
  }

  @Override
  public void setFreeParameters( Set< Parameter< ? > > freeParams,
                                 boolean deep,
                                 Set<HasParameters> seen ) {
    Assert.assertTrue( "This method is not supported!", false );
  }

  @Override
  public boolean isSatisfied(boolean deep, Set< Satisfiable > seen) {
    for ( Constraint c : getConstraints( true, null ) ) { // REVIEW -- why is true passed in?
      if ( Debug.isOn() ) Debug.outln( "ParameterListenerImpl.isSatisfied(): checking " + c );
      if ( !c.isSatisfied(deep, seen) ) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean satisfy(boolean deep, Set< Satisfiable > seen) {
    Pair< Boolean, Set< Satisfiable > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return true;
    seen = pair.second;
    
    if ( isSatisfied(deep, null) ) return true;
    double clockStart = System.currentTimeMillis();
    long numLoops = 0;
    int mostResolvedConstraints = 0;
    int numLoopsWithNoProgress = 0;
    
    boolean satisfied = false;
    double curTimeLeft =
        ( timeoutSeconds * 1000.0 - ( System.currentTimeMillis() - clockStart ) );
    
    while ( !satisfied
            && numLoopsWithNoProgress < maxLoopsWithNoProgress
            && ( !usingTimeLimit || curTimeLeft > 0.0 )
            && ( !usingLoopLimit || numLoops < maxPassesAtConstraints ) ) {
      if ( usingTimeLimit ) {
        if ( Debug.isOn() || this.amTopEventToSimulate ) {
          Debug.outln( this.getClass().getName() + " satisfy loop with "
                       + curTimeLeft + " milliseconds left" );
        }
      }
      if ( usingLoopLimit ) {
        if ( Debug.isOn() || this.amTopEventToSimulate ) {
          System.out.println( this.getClass().getName() + " satisfy loop round "
              + (numLoops+1) + " out of " + maxPassesAtConstraints );
        }
      }
      satisfied = tryToSatisfy(deep, null);

      int numResolvedConstraints = solver.getConstraints().size() -
                                   solver.getUnsatisfiedConstraints().size();
      boolean improved = numResolvedConstraints > mostResolvedConstraints; 
      // TODO -- Move call to doSnapshotSimulation() into tryToSatisfy() in order to
      // move it out of this class and into DurativeEvent since Events simulate.
      if ( snapshotSimulationDuringSolve && this.amTopEventToSimulate ) {
        doSnapshotSimulation( improved );
      }
      
      if ( !satisfied && !improved ) {
        ++numLoopsWithNoProgress;
        if ( numLoopsWithNoProgress >= maxLoopsWithNoProgress
             && ( Debug.isOn() || amTopEventToSimulate ) ) {
          System.out.println( "\nPlateaued at " + mostResolvedConstraints + " constraints satisfied." );
          System.out.println( solver.getUnsatisfiedConstraints().size() + " unresolved constraints = " + solver.getUnsatisfiedConstraints() );
        }
      } else {
        mostResolvedConstraints = numResolvedConstraints;
        numLoopsWithNoProgress = 0;
      }
      
      curTimeLeft =
          ( timeoutSeconds * 1000.0 - ( System.currentTimeMillis() - clockStart ) );
      ++numLoops;
    }
    return satisfied;
  }

  // TODO -- Move call to doSnapshotSimulation() into tryToSatisfy() in order to
  // move it out of this class and into DurativeEvent.
  public void doSnapshotSimulation() {
    doSnapshotSimulation( false );
  }
  public void doSnapshotSimulation( boolean improved ) {
    // override!
  }
  protected boolean tryToSatisfy(boolean deep, Set< Satisfiable > seen) {
    ground(deep, null);
    if ( Debug.isOn() ) Debug.outln( this.getClass().getName() + " satisfy loop called ground() " );
    
    Collection< Constraint > allConstraints = getConstraints( deep, null );
    if ( Debug.isOn() || amTopEventToSimulate ) {
      System.out.println( this.getClass().getName() + " - " + getName()
                          + ".tryToSatisfy() calling solve() with "
                          + allConstraints.size() + " constraints" );
    }
    // REVIEW -- why not call satisfy() here and solve elsewhere??
    boolean satisfied = solver.solve( allConstraints );
    if ( Debug.isOn() || amTopEventToSimulate ) {
      System.out.println( this.getClass().getName() + " - " + getName()
                          + ".tryToSatisfy() called solve(): satisfied "
                          + ( allConstraints.size() -
                              solver.getUnsatisfiedConstraints().size() )
                          + " constraints; failed to resolve "
                          + solver.getUnsatisfiedConstraints().size()
                          + " constraints" );
    }

    satisfied = isSatisfied(deep, null);
    if ( Debug.isOn() || amTopEventToSimulate ) {
      System.out.println( this.getClass().getName() + " - " + getName()
                          + ".tryToSatisfy() called solve(): satisfied = "
                          + satisfied );
    }
    return satisfied;
  }

//  @Override
//  public Collection< Constraint > getConstraints() {
//    return getConstraints( false, null );
//  }

  @Override
  public Collection< Constraint > getConstraints( boolean deep,
                                                  Set<HasConstraints> seen ) {
    boolean mayHaveBeenPropagating = Parameter.mayPropagate; 
    Parameter.mayPropagate = false;
    boolean mayHaveBeenChanging = Parameter.mayChange; 
    Parameter.mayChange = false;
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) {
      Parameter.mayPropagate = mayHaveBeenPropagating;
      Parameter.mayChange = mayHaveBeenChanging;
      return Utils.getEmptySet();
    }
    seen = pair.second;
    Set< Constraint > set = new HashSet< Constraint >();
    set = Utils.addAll( set, HasConstraints.Helper.getConstraints( getParameters( false, null ), deep, seen ) );
    set = Utils.addAll( set, HasConstraints.Helper.getConstraints( constraintExpressions, deep, seen ) );
    set = Utils.addAll( set, HasConstraints.Helper.getConstraints( dependencies, deep, seen ) );
//    for ( Parameter< ? > p : getParameters( false, null ) ) {
//      set = Utils.addAll( set, p.getConstraints( deep, seen ) );
//    }
//    set = Utils.addAll( set, constraintExpressions );
//    set = Utils.addAll( set, dependencies );
    Parameter.mayPropagate = mayHaveBeenPropagating;
    Parameter.mayChange = mayHaveBeenChanging;
    return set;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasTimeVaryingObjects#getTimeVaryingObjects(boolean)
   */
  @Override
  public Set< TimeVarying< ? > > getTimeVaryingObjects( boolean deep,
                                                        Set<HasTimeVaryingObjects> seen ) {
    Pair< Boolean, Set< HasTimeVaryingObjects > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< TimeVarying< ? > > s = new HashSet< TimeVarying< ? > >();
    s.addAll( timeVaryingObjects );
    s = Utils.addAll( s, HasTimeVaryingObjects.Helper.getTimeVaryingObjects( getParameters( false,
                                                                                 null ),
                                                                  deep, seen ) );
    if ( deep ) {
      s = Utils.addAll( s, HasTimeVaryingObjects.Helper.getTimeVaryingObjects( getDependencies(),
                                                                    deep, seen ) );
      s = Utils.addAll( s, HasTimeVaryingObjects.Helper.getTimeVaryingObjects( getConstraintExpressions(),
                                                                    deep, seen ) );
    }
    return s;
  }

  public List< Parameter< ? > > getParameters() {
    return parameters;
  }

  public void setParameters( List< Parameter< ? > > parameters ) {
    this.parameters = parameters;
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

  public List< ConstraintExpression > getConstraintExpressions() {
    return constraintExpressions;
  }

  public void setConstraintExpressions( List< ConstraintExpression > constraints ) {
    this.constraintExpressions = constraints;
  }

  public Collection< Dependency< ? > > getDependencies() {
    return dependencies;
  }

  public void setDependencies( Collection< Dependency< ? > > dependencies ) {
    this.dependencies = new ArrayList< Dependency< ? > >( dependencies );
  }
  
  @Override
  public int compareTo( ParameterListenerImpl o ) {
    return compareTo( o, true );
  }
  public int compareTo( ParameterListenerImpl o, boolean checkId ) {
    if ( this == o ) return 0;
    if ( o == null ) return -1;
    if ( checkId ) return CompareUtils.compare( getId(), o.getId() );
    int compare = getClass().getName().compareTo( o.getClass().getName() );
    if ( compare != 0 ) return compare;
    compare = getName().compareTo( o.getName() );
    if ( compare != 0 ) return compare;
    Debug.errln("ParameterListenerImpl.compareTo() potentially accessing value information");
    compare = CompareUtils.compareCollections( parameters, o.getParameters(),
                                               true, checkId );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compareCollections( dependencies, o.dependencies,
                                               true, checkId );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compareCollections( constraintExpressions,
                                               o.constraintExpressions,
                                               true, checkId );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( this, o, false, checkId );
    if ( compare != 0 ) return compare;
    return compare;
  }

  // An event owns all of its parameters because it is required to contain any
  // dependency that sets one of its parameters, and has some connection through
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
    // Alert affected timelines.
    for ( TimeVarying<?> tv : getTimeVaryingObjects( true, null ) ) {
      if ( tv instanceof ParameterListener ) {
        ((ParameterListener)tv).handleValueChangeEvent( parameter );
      }
    }
  }

  @Override
  public void handleDomainChangeEvent( Parameter< ? > parameter ) {
                                       //Domain< ? > newDomain ) {
    // TODO -- What are we supposed to do? call satisfy()??? Not caching
    // constraint violations as of 2012-08-03.
  }

  @Override
  public String getName() {
    if ( name != null ) return name;
    return getClass().getSimpleName();
  }

  public void setName( String newName ) {
    if ( newName == null || newName.isEmpty() ) {
      Formatter formatter = new Formatter(Locale.US);
      newName = getClass().getSimpleName() + formatter.format( "%06d", (counter++) );
    }
    this.name = newName;
  }
  
  /**
   * Try to remove others' references to this, possibly because it is being
   * deleted.
   */
  public void detach() {
    for ( Parameter< ? > p : getParameters( false, null ) ) {
      if ( p.getOwner() != null ) {
        p.getOwner().detach( p );
      }
      if ( p.getOwner() == this ) {
        p.setOwner( null );
      }
    }
    name = "DETACHED_" + name;
    dependencies.clear();
    constraintExpressions.clear();
    this.timeVaryingObjects.clear();
    parameters.clear();
  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListener#detach(gov.nasa.jpl.ae.event.Parameter)
   */
  @Override
  public void detach( Parameter< ? > parameter ) {
    // Remove local dependencies referencing the parameter.
    ArrayList< Dependency< ? > > dependenciesCopy =
        new ArrayList< Dependency< ? > >( getDependencies() );
    Collections.reverse( dependenciesCopy );
    int i = dependenciesCopy.size() - 1;
    for ( Dependency<?> d : dependenciesCopy ) {
      boolean hasParam = d.hasParameter( parameter, false, null );
      d.detach( parameter );
      if ( hasParam ) {
        getDependencies().remove( i );
      }
      --i;
    }
    // Detach from constraints.
    ArrayList< ConstraintExpression > constraints =
        new ArrayList< ConstraintExpression >( getConstraintExpressions() );
    Collections.reverse( constraints );
    i = constraints.size() - 1;
    for ( ConstraintExpression c : constraints ) {
      if ( c.hasParameter( parameter, false, null ) ) {
        getConstraintExpressions().remove( i );
      }
      --i;
    }
    // Detach from timelines.
    for ( TimeVarying<?> tv : getTimeVaryingObjects( true, null ) ) {
      if ( tv instanceof ParameterListener ) {
        ((ParameterListener)tv).detach( parameter );
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
    
    boolean triedRefreshing = false;
    // Alert affected dependencies.
    for ( Dependency<?> d : getDependencies() ) {
      if ( d.parameter == parameter ) {
        triedRefreshing = true;
        if ( d.refresh( parameter ) ) didRefresh = true;
      }
    }

    // TODO -- Need to keep a collection of ParameterListeners (just as
    // DurativeEvent has getEvents())
    parameter.setStale( triedRefreshing && !didRefresh );
    
    return didRefresh;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListener#setStaleAnyReferencesTo(gov.nasa.jpl.ae.event.Parameter)
   */
  @Override
  public void setStaleAnyReferencesTo( Parameter< ? > changedParameter ) {
    // Alert affected dependencies.
    for ( Dependency<?> d : getDependencies() ) {
      d.setStaleAnyReferencesTo( changedParameter );
    }
    for ( TimeVarying<?> tv : getTimeVaryingObjects( true, null ) ) {
      if ( tv instanceof ParameterListener ) {
        ((ParameterListener)tv).setStaleAnyReferencesTo( changedParameter );
      }
    }
  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                               Set<HasParameters> seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    return getParameters( deep, seen ).contains( parameter );
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep,
                                  Set<HasParameters> seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;

    return !getDependentParameters( deep, seen ).contains( p );
  }

  @Override
  public <T> boolean pickValue( Variable< T > variable ) {
    for ( Dependency<?> d : getDependencies() ) {
      if ( d.pickValue( variable ) ) return true;
    }
    if ( variable instanceof Parameter && Random.global.nextBoolean() ) {
      return ((Parameter<?>)variable).ownerPickValue();
    }
    T value = variable.pickRandomValue();
    if ( value != null ) {
      variable.setValue( value );
      return true;
    }
    return false;
  }

}
