/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.HasConstraints;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.mbee.util.Evaluatable;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

/**
 * @author bclement
 *
 */
public abstract class AbstractParameterConstraint implements ParameterConstraint, HasConstraints {
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.HasConstraints#getConstraints(boolean, java.util.Set)
   */
  @Override
  public Collection< Constraint > getConstraints( boolean deep,
                                                  Set< HasConstraints > seen ) {
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    Set< Constraint > set = new HashSet< Constraint >();
    set.add( this );
    return set;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.HasConstraints#getNumberOfResolvedConstraints(boolean, java.util.Set)
   */
  @Override
  public long getNumberOfResolvedConstraints( boolean deep,
                                              Set< HasConstraints > seen ) {
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return 0;
    seen = pair.second;
    return isSatisfied( false, null ) ? 1 : 0;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.HasConstraints#getNumberOfUnresolvedConstraints(boolean, java.util.Set)
   */
  @Override
  public long getNumberOfUnresolvedConstraints( boolean deep,
                                                Set< HasConstraints > seen ) {
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return 0;
    seen = pair.second;
    return isSatisfied( false, null ) ? 0 : 1;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.HasConstraints#getNumberOfConstraints(boolean, java.util.Set)
   */
  @Override
  public long getNumberOfConstraints( boolean deep, Set< HasConstraints > seen ) {
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return 0;
    seen = pair.second;
    return 1;
  }

  // freeParameters if not null specifies which parameters can be reassigned
  // values for satisfy().
  protected Set< Parameter< ? > > freeParameters = null;

  /**
   * 
   */
//  public AbstractParameterConstraint() {
//    // TODO Auto-generated constructor stub
//  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#getVariables()
   */
  @Override
  public Set< Variable< ? > > getVariables() {
    return ParameterConstraint.Helper.getVariables( this, false, null );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#pickValue(gov.nasa.jpl.ae.solver.Variable)
   */
  @Override
  public < T > boolean pickParameterValue( Variable< T > v ) {
    return ParameterConstraint.Helper.pickValue( this, v );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#restrictDomain(gov.nasa.jpl.ae.solver.Variable)
   */
  @Override
  public < T > boolean restrictDomain( Variable< T > v ) {
    return ParameterConstraint.Helper.restrictDomain( this, v );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#isFree(gov.nasa.jpl.ae.solver.Variable)
   */
  @Override
  public < T > boolean isFree( Variable< T > v ) {
    return ParameterConstraint.Helper.isFree( this, v, false, null );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#isDependent(gov.nasa.jpl.ae.solver.Variable)
   */
  @Override
  public < T > boolean isDependent( Variable< T > v ) {
    return ParameterConstraint.Helper.isDependent( this, v, false, null );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#getFreeVariables()
   */
  @Override
  public Set< Variable< ? > > getFreeVariables() {
    return ParameterConstraint.Helper.getFreeVariables( this, false, null );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#setFreeVariables(java.util.Set)
   */
  @Override
  public void setFreeVariables( Set< Variable< ? >> freeVariables ) {
    ParameterConstraint.Helper.setFreeVariables( this, freeVariables, false, null );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Satisfiable#isSatisfied()
   */
  @Override
  public abstract boolean isSatisfied(boolean deep, Set< Satisfiable > seen);

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Satisfiable#satisfy()
   */
  @Override
  public abstract boolean satisfy(boolean deep, Set< Satisfiable > seen);

  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo( Constraint o ) {
    return ParameterConstraint.Helper.compareTo( this, o );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.LazyUpdate#isStale()
   */
  @Override
  public boolean isStale() {
    return ParameterConstraint.Helper.isStale( this, false, null );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.LazyUpdate#setStale(boolean)
   */
  @Override
  public void setStale( boolean staleness ) {
    ParameterConstraint.Helper.setStale( this, staleness );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasParameters#getParameters(boolean)
   */
  @Override
  public abstract Set< Parameter< ? >> getParameters( boolean deep,
                                                      Set<HasParameters> seen);

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasParameters#getFreeParameters(boolean)
   */
  @Override
  public Set< Parameter< ? >> getFreeParameters( boolean deep,
                                                 Set<HasParameters> seen ) {
    return freeParameters;
  }

  /**
   * @param freeParameters
   *          the freeParameters to set
   */
  public void setFreeParameters( Set< Parameter< ? > > freeParameters ) {
    this.freeParameters = freeParameters;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasParameters#isFreeParameter(gov.nasa.jpl.ae.event.Parameter, boolean)
   */
  @Override
  public boolean isFreeParameter( Parameter< ? > parameter, boolean deep,
                                  Set<HasParameters> seen ) {
    //return ParameterConstraint.Helper.isFreeParameter( this, parameter, deep );
    return getFreeParameters( deep, seen ).contains( parameter);
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasParameters#hasParameter(gov.nasa.jpl.ae.event.Parameter, boolean)
   */
  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                               Set<HasParameters> seen ) {
    return ParameterConstraint.Helper.hasParameter( this, parameter, deep, seen );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasParameters#substitute(gov.nasa.jpl.ae.event.Parameter, gov.nasa.jpl.ae.event.Parameter, boolean)
   */
  @Override
  public boolean
      substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep,
                  Set<HasParameters> seen ) {
    Assert.assertTrue( "This method is not yet supported!", false );
    //return ParameterConstraint.Helper.substitute( this, p2, p2, deep );
    return false;
  }

  @Override
  public boolean substitute( Parameter< ? > p1, Object exp, boolean deep,
                             Set< HasParameters > seen ) {
    Assert.assertTrue( "This method is not yet supported!", false );
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public < T > T evaluate( Class< T > cls, boolean propagate ) {
    T t = Evaluatable.Helper.evaluate( this, cls, true, propagate, false, null );
    if ( t != null ) return t;
    if ( cls != null && cls.isAssignableFrom( Boolean.class ) ) {
      Boolean b = isSatisfied( false, null );
      return (T)b;
    }
    return null;
  }
  
}
