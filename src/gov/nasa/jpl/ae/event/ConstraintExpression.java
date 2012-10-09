package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Satisfiable;
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
  public boolean isSatisfied(boolean deep, Set< Satisfiable > seen) {
    Boolean sat = evaluate(false);
    if ( sat == null ) sat = new Boolean( false );
    if ( sat ) {
      sat = HasParameters.Helper.isSatisfied( this, true, null );
    }
    Debug.outln( "ConstraintExpression.isSatisfied() = " + sat + ": " + this );
    return sat;
  }

  /*
   * (non-Javadoc)
   * 
   * @see event.Constraint#satisfy()
   */
  @Override
  public boolean satisfy(boolean deep, Set< Satisfiable > seen) {
    Debug.outln( "ConstraintExpression.satisfy() for " + this );
    if ( isSatisfied(deep, seen) ) return true;
    HasParameters.Helper.satisfy( this, true, null );
    if ( !isSatisfied(deep, seen) ) {
      for ( Variable< ? > v : getVariables() ) {
        pickValue( v );
        if ( isSatisfied(deep, seen) ) break;
      }
    }
    return isSatisfied(deep, seen);
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

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#getVariables()
   */
  @Override
  public Set< Variable< ? > > getVariables() {
    return ParameterConstraint.Helper.getVariables( this, false, null );
//    Set< Variable< ? > > s = new HashSet< Variable< ? > >();
//    s.addAll( getParameters( true ) );
//    return s;
  }

  @Override
  public < T > boolean pickValue( Variable< T > v ) {
    if ( type.equals( Type.Function ) ) {
      if ( expression instanceof Suggester ) {
        T newValue = ((Suggester)expression).pickValue( v );
        if ( newValue != null ) {
          v.setValue( newValue );
          return true;
        }
      }
    }
    // TODO
//    Set< Variable< ? > > vars = getVariables();
    return ParameterConstraint.Helper.pickValue( this, v );
  }

  @Override
  public < T > boolean restrictDomain( Variable< T > v ) {
    return ParameterConstraint.Helper.restrictDomain( this, v );
  }

  @Override
  public < T > boolean isFree( Variable< T > v ) {
    return ParameterConstraint.Helper.isFree( this, v, false, null );
//    if ( v instanceof Parameter<?> ) {
//      return freeParameters.contains( v );
//    }
//    return true; // REVIEW -- Is true ok for "don't know" case?
  }

  @Override
  public < T > boolean isDependent( Variable< T > v ) {
    return ParameterConstraint.Helper.isDependent( this, v, false, null );
//    return false;
  }

  @Override
  public Set< Variable< ? > > getFreeVariables() {
    return ParameterConstraint.Helper.getFreeVariables( this, false, null );
//    Set< Variable< ? > > s = new HashSet< Variable< ? > >();
//    s.addAll( getFreeParameters( true ) );
//    return s;
  }

  @Override
  public void setFreeVariables( Set< Variable< ? > > freeVariables ) {
    ParameterConstraint.Helper.setFreeVariables( this, freeVariables, false, null );
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
    return ParameterConstraint.Helper.isStale( this, false, null );
    //return HasParameters.Helper.isStale( this, false );
//    for ( Parameter< ? > p : getParameters( false ) ) {
//      if ( p.isStale() ) return true;
//    }
//    return false;
  }

  @Override
  public void setStale( boolean staleness ) {
    Debug.outln( "setStale(" + staleness + ") to " + this );
    ParameterConstraint.Helper.setStale( this, staleness );
  }
  
}
