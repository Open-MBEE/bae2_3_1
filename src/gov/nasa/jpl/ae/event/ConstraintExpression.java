package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.Debug;

import java.util.HashSet;
import java.util.Set;


/**
 * TODO -- Look at javaparser at http://code.google.com/p/javaparser/ and JavaCC
 * TODO -- Consider using Ptolemy's expression parser
 */

/**
 * @author bclement
 * 
 */
public class ConstraintExpression extends Expression< Boolean >
                                  implements Constraint { // ,
                                                          // ParameterListener
                                                          // {

  // freeParameters if not null specifies which parameters can be reassigned
  // values for satisfy().
  protected Set< Parameter< ? > > freeParameters = null;

  /**
   * @param value
   */
  public ConstraintExpression( Boolean value ) {
    super( value );
  }

  /**
   * @param parameter
   */
  public ConstraintExpression( Parameter< Boolean > parameter ) {
    super( parameter );
  }

  // /**
  // * @param method
  // */
  // public Constraint(Method method) {
  // super(method);
  // }

  /**
   * @param expression
   */
  public ConstraintExpression( Expression< Boolean > expression ) {
    super( expression, true );
  }

  /**
   * @param function
   */
  public ConstraintExpression( FunctionCall function ) {
    super( function );
  }

  /*
   * (non-Javadoc)
   * 
   * @see event.Constraint#isSatisfied()
   */
  @Override
  public boolean isSatisfied() {
    boolean sat = evaluate(false);
    Debug.outln( "ConstraintExpression.isSatisfied() = " + sat + ": " + this );
    return sat;
  }

  /*
   * (non-Javadoc)
   * 
   * @see event.Constraint#satisfy()
   */
  @Override
  public boolean satisfy() {
//    Set< Parameter< ? > > params = getFreeParameters();
//    if ( params == null || params.isEmpty() ) {
//      params = getParameters( true );
//    }
    // TODO -- HERE!! -- uncomment above
//    if ( )
    return false;
  }

  /**
   * @return the freeParameters
   */
  public Set< Parameter< ? > > getFreeParameters() {
    return freeParameters;
  }

  /**
   * @param freeParameters
   *          the freeParameters to set
   */
  public void setFreeParameters( Set< Parameter< ? > > freeParameters ) {
    this.freeParameters = freeParameters;
  }

  @Override
  public Set< Variable< ? > > getVariables() {
    return ParameterConstraint.Helper.getVariables( this );
//    Set< Variable< ? > > s = new HashSet< Variable< ? > >();
//    s.addAll( getParameters( true ) );
//    return s;
  }

  @Override
  public < T > void pickValue( Variable< T > v ) {
    ParameterConstraint.Helper.pickValue( this, v );
  }

  @Override
  public < T > void restrictDomain( Variable< T > v ) {
    ParameterConstraint.Helper.restrictDomain( this, v );
  }

  @Override
  public < T > boolean isFree( Variable< T > v ) {
    return ParameterConstraint.Helper.isFree( this, v );
//    if ( v instanceof Parameter<?> ) {
//      return freeParameters.contains( v );
//    }
//    return true; // REVIEW -- Is true ok for "don't know" case?
  }

  @Override
  public < T > boolean isDependent( Variable< T > v ) {
    return ParameterConstraint.Helper.isDependent( this, v );
//    return false;
  }

  @Override
  public Set< Variable< ? > > getFreeVariables() {
    return ParameterConstraint.Helper.getFreeVariables( this );
//    Set< Variable< ? > > s = new HashSet< Variable< ? > >();
//    s.addAll( getFreeParameters( true ) );
//    return s;
  }

  @Override
  public void setFreeVariables( Set< Variable< ? > > freeVariables ) {
    ParameterConstraint.Helper.setFreeVariables( this, freeVariables );
//    if ( freeParameters == null ) {
//      freeParameters = new HashSet< Parameter< ? > >();
//    }
//    for ( Variable< ? > v : freeVariables ) {
//      if ( v instanceof Parameter< ? > ) {
//        freeParameters.add( (Parameter< ? >)v );
//      }
//    }
  }

  @Override
  public int compareTo( Constraint o ) {
    return ParameterConstraint.Helper.compareTo( this, o );
//    return this.toString().compareTo( o.toString() );
  }

  @Override
  public boolean isStale() {
    return ParameterConstraint.Helper.isStale( this, false );
    //return HasParameters.Helper.isStale( this, false );
//    for ( Parameter< ? > p : getParameters( false ) ) {
//      if ( p.isStale() ) return true;
//    }
//    return false;
  }

  @Override
  public void setStale( boolean staleness ) {
    ParameterConstraint.Helper.setStale( this, staleness );
  }
  
}
