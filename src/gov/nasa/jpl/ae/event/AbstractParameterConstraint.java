/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Variable;

import java.util.Set;

import junit.framework.Assert;

/**
 * @author bclement
 *
 */
public abstract class AbstractParameterConstraint implements ParameterConstraint {
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
    return ParameterConstraint.Helper.getVariables( this );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#pickValue(gov.nasa.jpl.ae.solver.Variable)
   */
  @Override
  public < T > boolean pickValue( Variable< T > v ) {
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
    return ParameterConstraint.Helper.isFree( this, v );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#isDependent(gov.nasa.jpl.ae.solver.Variable)
   */
  @Override
  public < T > boolean isDependent( Variable< T > v ) {
    return ParameterConstraint.Helper.isDependent( this, v );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#getFreeVariables()
   */
  @Override
  public Set< Variable< ? > > getFreeVariables() {
    return ParameterConstraint.Helper.getFreeVariables( this );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#setFreeVariables(java.util.Set)
   */
  @Override
  public void setFreeVariables( Set< Variable< ? >> freeVariables ) {
    ParameterConstraint.Helper.setFreeVariables( this, freeVariables );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Satisfiable#isSatisfied()
   */
  @Override
  public abstract boolean isSatisfied();

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Satisfiable#satisfy()
   */
  @Override
  public abstract boolean satisfy();

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
    return ParameterConstraint.Helper.isStale( this, false );
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
  public abstract Set< Parameter< ? >> getParameters( boolean deep );

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasParameters#getFreeParameters(boolean)
   */
  @Override
  public Set< Parameter< ? >> getFreeParameters( boolean deep ) {
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
  public boolean isFreeParameter( Parameter< ? > parameter, boolean deep ) {
    //return ParameterConstraint.Helper.isFreeParameter( this, parameter, deep );
    return getFreeParameters( deep ).contains( parameter);
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasParameters#hasParameter(gov.nasa.jpl.ae.event.Parameter, boolean)
   */
  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep ) {
    return ParameterConstraint.Helper.hasParameter( this, parameter, deep );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasParameters#substitute(gov.nasa.jpl.ae.event.Parameter, gov.nasa.jpl.ae.event.Parameter, boolean)
   */
  @Override
  public boolean
      substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep ) {
    Assert.assertTrue( "This method is not yet supported!", false );
    //return ParameterConstraint.Helper.substitute( this, p2, p2, deep );
    return false;
  }

}
