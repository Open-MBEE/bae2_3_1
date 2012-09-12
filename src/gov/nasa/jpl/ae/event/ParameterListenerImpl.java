/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
import junit.framework.Assert;

import gov.nasa.jpl.ae.solver.AbstractRangeDomain;
import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.ConstraintLoopSolver;
import gov.nasa.jpl.ae.solver.Random;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.solver.Solver;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

/**
 * @author bclement
 *
 */
public class ParameterListenerImpl implements Cloneable, Groundable,
                                              Satisfiable,
                                              ParameterListener,
                                              HasTimeVaryingObjects,
                                              Comparable< ParameterListenerImpl > {
  // Constants
  
  final double timeoutSeconds = 3.0;

  // Static members
  
  protected static int counter = 0;

  // Other Members

  protected String name = null;
  protected SortedSet< Parameter< ? > > parameters =
      new TreeSet< Parameter< ? > >();
  protected Vector< ConstraintExpression > constraintExpressions = new Vector< ConstraintExpression >();
  // TODO -- REVIEW -- should dependencies these be folded in with effects?
  protected Vector< Dependency< ? > > dependencies =
      new Vector< Dependency< ? > >();
  protected Solver solver = new ConstraintLoopSolver( timeoutSeconds * 1000.0 );

  protected SortedSet< TimeVarying< ? > > timeVaryingObjects =
      new TreeSet< TimeVarying< ? > >();

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
  public ParameterListenerImpl( String name, ParameterListenerImpl parameterListenerImpl ) {
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
      substitute( p.first, p.second, false );
    }
  }

  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep ) {
    boolean subbed = false;
    for ( ConstraintExpression c : constraintExpressions ) {
      boolean s = c.substitute( p1, p2, deep );
      subbed = subbed || s;
    }
    for ( Dependency< ? > d : dependencies ) {
      if ( d instanceof HasParameters ) {
        ( (HasParameters)d ).substitute( p1, p2, deep );
      }
    }
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
    Iterator< Parameter< ? > > i2 = parameterListener.getParameters(false).iterator();
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
    StringBuffer sb = new StringBuffer();
    sb.append( getClass().getName() ); // super.toString() adds hash code
    Set< Parameter< ? > > allParams = getParameters( false );
    for ( Object p : allParams ) {
      if ( p instanceof Parameter ) {
        sb.append( ", " + ((Parameter<?>)p).toString( false, false ) );
      } else {
        sb.append( ", " + p.toString() );
      }
    }
    return sb.toString();
  }

  // TODO -- separate this method and removeDependency from Event to
  // HasDependencies?
  public < T > Dependency addDependency( Parameter< T > p, Expression< T > e ) {
    Dependency< T > d = new Dependency< T >( p, e );
    if ( e.type == Expression.Type.Parameter ) {
      Parameter< T > ep = (Parameter< T >)e.expression;
      if ( ep.getDomain() instanceof AbstractRangeDomain &&
          p.getDomain() instanceof AbstractRangeDomain ) {
        AbstractRangeDomain< T > ard = (AbstractRangeDomain< T >)p.getDomain();
        AbstractRangeDomain< T > eard = (AbstractRangeDomain< T >)ep.getDomain();
        eard.intersectRestrict( ard );
        ard.intersectRestrict( eard );
      }
    }
    dependencies.add( d );
    return d;
  }

  public < T > Dependency addDependency( Parameter< T > p, Parameter< T > source ) {
    return addDependency( p, new Expression< T >( source ) );
  }

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

  // TODO -- This is not finished. Need to get deep dependents.
  public Set< Parameter< ? > > getDependentParameters( boolean deep ) {
    TreeSet< Parameter< ? > > set = new TreeSet< Parameter< ? > >();
    for ( Dependency< ? > d : dependencies ) {
      set.add( d.parameter );
    }
    return set;
  }

  // Gather any parameter instances contained by this event.
  public Set< Parameter< ? > > getParameters( boolean deep ) {
    // TODO
    TreeSet< Parameter< ? > > set = new TreeSet< Parameter< ? > >();
    set.addAll( getParameters() );
    if ( deep ) {
      // TODO -- Get parameters from members that implement HasPaameters?
      /*
      for ( Field f : this.getClass().getFields() ) {
        try {
          Object o = f.get( this );
          if ( o != null && o != this.parameters ) {
            if ( o instanceof Parameter< ? > ) {
              set.add( (Parameter< ? >)o );
            }
            if ( o instanceof HasParameters ) {
              set.addAll( ( (HasParameters)o ).getParameters( deep ) );
            }
          }
        } catch ( IllegalArgumentException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch ( IllegalAccessException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      */
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

  @Override
  public boolean satisfy() {
    if ( isSatisfied() ) return true;
    double clockStart = System.currentTimeMillis();
    
    boolean satisfied = false;
    double curTimeLeft =
        ( timeoutSeconds * 1000.0 - ( System.currentTimeMillis() - clockStart ) );
    
    // TODO -- REVIEW -- How do we get elaborations (conditional constraints
    // into the problem)? Add all possible constraints as implied by conditions?
    // Treat each elaboration as a constraint?
    while (!satisfied && ( curTimeLeft > 0.0 ) ) {
      Debug.outln( this.getClass().getName() + " satisfy loop with "
          + curTimeLeft + "milliseconds left" );
      satisfied = tryToSatisfy();
      curTimeLeft =
          ( timeoutSeconds * 1000.0 - ( System.currentTimeMillis() - clockStart ) );
    }
    return satisfied;
  }

  protected boolean tryToSatisfy() {
    ground();
    Debug.outln( this.getClass().getName() + " satisfy loop called ground() " );
    
    Collection< Constraint > allConstraints = getConstraints( true );
    boolean satisfied = solver.solve( allConstraints );
    
    satisfied = isSatisfied();
    Debug.outln( this.getClass().getName()
                 + ".tryToSatisfy() called solve(): satisfied = " + satisfied );
    return satisfied;
  }
  public Collection< Constraint > getConstraints() {
    return getConstraints( true );
  }

  public Collection< Constraint > getConstraints( boolean deep ) {
    Set< Constraint > set = new TreeSet< Constraint >();
    for ( Parameter< ? > p : getParameters( deep ) ) {
      set.addAll( p.getConstraints() );
    }
    set.addAll( constraintExpressions );
    set.addAll( dependencies );
    return set;
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

  public SortedSet< Parameter< ? > > getParameters() {
    return parameters;
  }

  public void setParameters( SortedSet< Parameter< ? >> parameters ) {
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

  public Vector< ConstraintExpression > getConstraintExpressions() {
    return constraintExpressions;
  }

  public void setConstraintExpressions( Vector< ConstraintExpression > constraints ) {
    this.constraintExpressions = constraints;
  }

  public Vector< Dependency< ? > > getDependencies() {
    return dependencies;
  }

  public void setDependencies( Vector< Dependency< ? > > dependencies ) {
    this.dependencies = dependencies;
  }
  
  @Override
  public int compareTo( ParameterListenerImpl o ) {
    int compare = getClass().getName().compareTo( o.getClass().getName() );
    if ( compare != 0 ) return compare;
    compare = Utils.compareSets( parameters, o.getParameters() );
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
  
  // Try to remove others' references to this, possibly because it is being
  // deleted.
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
  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep ) {
    return getParameters( deep ).contains( parameter );
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep ) {
    return !getDependentParameters( deep ).contains( p );
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
