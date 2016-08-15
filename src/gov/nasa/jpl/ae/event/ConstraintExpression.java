package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.event.Functions.SuggestiveFunctionCall;
import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Utils;

import java.lang.reflect.InvocationTargetException;
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
   * @param functionCall
   */
  public ConstraintExpression( FunctionCall functionCall ) {
    super( functionCall );
  }

  /**
   * (non-Javadoc)
   * 
   * @see event.Constraint#isSatisfied()
   */
  @Override
  public boolean isSatisfied(boolean deep, Set< Satisfiable > seen) {
    Boolean sat = null;
    try {
      sat = evaluate(false);
    } catch ( IllegalAccessException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( InstantiationException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if ( sat == null ) sat = new Boolean( false );
    if ( false && deep & sat ) {
      sat = HasParameters.Helper.isSatisfied( this, false, null );
    }
    if ( Debug.isOn() ) Debug.outln( "ConstraintExpression.isSatisfied() = " + sat + ": " + this );
    return sat;
  }

  /**
   * (non-Javadoc)
   * 
   * @see event.Constraint#satisfy()
   */
  @Override
  public boolean satisfy(boolean deep, Set< Satisfiable > seen) {
    //boolean wasDebugOn = Debug.isOn();
    boolean satisfied = true;
    //Debug.turnOn();
    if ( Debug.isOn() ) Debug.outln( "ConstraintExpression.satisfy() for " + this );
    if ( isSatisfied(deep, seen) ) return true;
    
    // Try satisfying the contained Parameters individually to make sure they
    // have valid values.
    HasParameters.Helper.satisfy( this, true, null );
    
    // Now try choosing new values for the variables to meet this constraint.
    if ( Parameter.allowPickValue && !isSatisfied(deep, seen) ) {
      Set< Variable< ? > > vars = getVariables();
      Variable<?>[] a = new Variable<?>[vars.size()];
      vars.toArray( a );
      if ( Debug.isOn() ) Debug.outln("ConstraintExpression.isSatisfied()   Picking values for " + vars + " in " + this);
      for ( Variable< ? > v : Utils.scramble(a) ) {
        // Make sure the variable is not dependent and not locked.
        if ( ( !( v instanceof Parameter ) || !( (Parameter)v ).isDependent() )
             && ( v.getDomain() == null || v.getDomain().size() != 1 ) ) {
          pickParameterValue( v );  
        }
        if ( isSatisfied(deep, seen) ) break;
      }
    }
    satisfied = isSatisfied(deep, seen);
    //if ( !wasDebugOn ) Debug.turnOff();
    return satisfied;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#getVariables()
   */
  @Override
  public Set< Variable< ? > > getVariables() {
    return ParameterConstraint.Helper.getVariables( this, false, null );
  }

  @Override
  public < T > boolean pickParameterValue( Variable< T > v ) {
    if ( expression instanceof Suggester ) {
      T newValue = ((Suggester)expression).pickValue( v );
      if ( newValue != null ) {
        //Debug.getInstance().logForce( "////////////////////   picking " + newValue + " for " + v + " in " + this );
        if ( Debug.isOn() ) Debug.outln( "////////////////////   picking " + newValue + " for " + v + " in " + this );
        setValue( v, newValue );
        return true;
      }
    }
    // TODO
    if ( Debug.isOn() ) Debug.outln( "////////////////////   not picking value for " + v + " in " + this );
    return false;
  }

  protected < T > void setValue( Variable<T> v, T newValue ) {
    v.setValue( newValue );
  }
  
  @Override
  public < T > boolean restrictDomain( Variable< T > v ) {
    switch ( form ) {
      case Function:
        FunctionCall f = (FunctionCall)expression;
        if ( f instanceof SuggestiveFunctionCall ) {
          //((SuggestiveFunctionCall)f).res
        }
      case Constructor:
      case Parameter:
      case Value:
      case None:
    }
    // TODO
    assert(false);
    return ParameterConstraint.Helper.restrictDomain( this, v );
  }

  @Override
  public < T > boolean isFree( Variable< T > v ) {
    return ParameterConstraint.Helper.isFree( this, v, false, null );
  }

  @Override
  public < T > boolean isDependent( Variable< T > v ) {
    return ParameterConstraint.Helper.isDependent( this, v, false, null );
  }

  @Override
  public Set< Variable< ? > > getFreeVariables() {
    // TODO
    assert(false);
    return null;
//    Set< Variable< ? > > s = new TreeSet< Variable< ? > >();
//    s.addAll( getFreeParameters( true ) );
//    return s;
  }

  @Override
  public void setFreeVariables( Set< Variable< ? > > freeVariables ) {
    ParameterConstraint.Helper.setFreeVariables( this, freeVariables, false, null );
    // TODO
    assert(false);
//    if ( freeParameters == null ) {
//      freeParameters = new TreeSet< Parameter< ? > >();
//    }
//    for ( Variable< ? > v : freeVariables ) {
//      if ( v instanceof Parameter< ? > ) {
//        freeParameters.add( (Parameter< ? >)v );
//      }
//    }
  }

  @Override
  public int compareTo( Constraint o ) {
    return compareTo( o, true );
  }
  public int compareTo( Constraint o, boolean checkId ) {
    if ( this == o ) return 0;
    if ( o == null ) return -1;
    if ( checkId ) return CompareUtils.compare( getId(), o.getId() );
    if ( o instanceof Expression ) {
      int compare = super.compareTo( (Expression< ? >)o );
      if ( compare != 0 ) return compare;
    }
    return ParameterConstraint.Helper.compareTo( this, o );
//    return this.toString().compareTo( o.toString() );
  }

//  @Override
//  public boolean isStale() {
//    if ( expression instanceof La)
//    return ParameterConstraint.Helper.isStale( this, false, null );
//    //return HasParameters.Helper.isStale( this, false );
////    for ( Parameter< ? > p : getParameters( false ) ) {
////      if ( p.isStale() ) return true;
////    }
////    return false;
//  }

//  @Override
//  public void setStale( boolean staleness ) {
//    if ( Debug.isOn() ) Debug.outln( "setStale(" + staleness + ") to " + this );
//    ParameterConstraint.Helper.setStale( this, staleness );
//  }
  
}
