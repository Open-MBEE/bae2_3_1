package gov.nasa.jpl.ae.event;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;

import gov.nasa.jpl.ae.event.Expression.Form;
import gov.nasa.jpl.ae.event.Functions.Equals;
import gov.nasa.jpl.ae.solver.CollectionTree;
import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasConstraints;
import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.ae.solver.Random;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.solver.Solver;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.CompareUtils;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.MoreToString;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;



/**
 * 
 */

/**
 * @author bclement
 * 
 */

// TODO -- REVIEW -- Staleness can be managed with a bit string (one bit for
// each node): Each node has a mask for setting downstream sinks (or checking
// upstream sources).

// TODO -- REVIEW -- Should this class be removed and have dependencies captured
// as time-invariant effects? Or should effects be TimeVarying Dependencies?

// TODO -- REVIEW -- How about constructing a constraint from a dependency
// ( parameter == expression )

// TODO -- REVIEW -- Should dependencies be applicable to a time period, like
// constraints (& effects)?
public class Dependency< T > extends HasIdImpl
             implements HasParameters, ParameterListener, Constraint,
                        LazyUpdate, HasConstraints, HasTimeVaryingObjects {

  protected Parameter< T > parameter;
  protected Expression< ? > expression;
  private ConstraintExpression constraint = null;
  protected boolean refreshing = false; // to prevent propagation cycles

  public <T2> Dependency( Parameter< T > p, Expression< T2 > e ) {
    parameter = p;
    parameter.setStale( true );
    expression = e;
  }

  public Dependency( Dependency< T > d ) {
    parameter = new Parameter< T >( d.parameter );
    expression = new Expression( d.expression, true );
  }

  @Override
  public void deconstruct() {
    //parameter = null; // expecting this parameter will be taken care of by the owner.
    //if ( expression != null ) {
      //expression.deconstruct();
    expression = null;
    //}
    //if ( constraint != null ) {
      //constraint.deconstruct();
      constraint = null;
    //}
  }

  public boolean apply() {
    return apply( true );
  }
  /**
   * @param propagate
   * @return whether the value was set to the evaluated expression
   */
  public boolean apply2( boolean propagate ) {
    // TODO -- REVIEW -- if ( isStale() ) ??
    if ( Debug.isOn() ) Debug.outln( "calling apply(" + propagate + ") on dependency " + this );
    if ( expression == null ) return false;
    if ( parameter == null ) return false;
    Object val = expression.evaluate(propagate);
    if ( parameter.isStale() || val != parameter.getValueNoPropagate() ) {
      T valT = Expression.evaluate( val, getType(), propagate, true );
      parameter.setValue( valT, propagate );
      return true;
    }
    if ( Debug.isOn() ) Debug.outln( "Not setting parameter to result value because either the value didn't change, or the parameter is not stale." );
    return false;
  }

  /**
   * @param propagate
   * @return whether the value was set to the evaluated expression
   */
  public boolean apply( boolean propagate ) {
    // TODO -- REVIEW -- if ( isStale() ) ??
    if ( Debug.isOn() ) Debug.outln( "calling apply(" + propagate + ") on dependency " + this );
    Object val = expression.evaluate(propagate);
    T value = Expression.evaluate( val, getType(), propagate, true );
    // Avoid setting to bad value.  How can we know if it's bad? Is null always bad?
    if ( value == null
        && parameter.isSatisfied( false, null )
        && ( parameter.getDomain() == null ||
             !parameter.getDomain().contains( null ) ) ) {
      if ( Debug.isOn() ) Debug.outln( "not applying dependency resulting in null value" );
      return false;
    }
    if ( parameter.isStale() || value != parameter.getValueNoPropagate() ) {
      if ( Debug.isOn() ) Debug.outln( "Setting the dependent parameter to the evaluation of expression = " + value );
      parameter.setValue( value, propagate );
      return true;
    }
    if ( Debug.isOn() ) Debug.outln( "Not setting parameter to result value because either the value didn't change, or the parameter is not stale." );
    return false;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Satisfiable#isSatisfied()
   */
  @Override
  public boolean isSatisfied(boolean deep, Set< Satisfiable > seen) {
    Pair< Boolean, Set< Satisfiable > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return true;
    seen = pair.second;
    boolean sat;
    // REVIEW -- The criteria for being satisfied should be reviewed since some
    // of these are short-circuited with false.
    if ( constraint != null ) {
      sat = constraint.isSatisfied(deep, seen);
      if ( Debug.isOn() && !sat ) Debug.outln( "Dependency.isSatisfied(): constraint not satisfied: " );// + this );
    } else if ( parameter == null ) {
      sat = false;
      if ( Debug.isOn() && !sat ) Debug.outln( "Dependency.isSatisfied(): parameter is null: " );// + this );
    } else if ( false && !parameter.isGrounded(deep, null) ) {
      sat = false;
      parameter.setStale( true );
      if ( Debug.isOn() ) Debug.outln( "Dependency.isSatisfied(): parameter not grounded: " );// + this );
    } else if ( expression == null ) {
      sat = false;
      if ( Debug.isOn() && !sat ) Debug.outln( "Dependency.isSatisfied(): expression is null: " );// + this );
    } else if ( !expression.isGrounded(deep, null) ) {
      sat = false;
      parameter.setStale( true );
      if ( Debug.isOn() ) Debug.outln( "Dependency.isSatisfied(): expression not grounded: " );// + this );
    } else if ( false && deep && !parameter.isSatisfied(deep, null) ) {
      sat = false;
      if ( Debug.isOn() ) Debug.outln( "Dependency.isSatisfied(): parameter not satisfied: " );// + this );
    } else if ( false && deep && !expression.isSatisfied(deep, null) ) {
      sat = false;
      if ( Debug.isOn() ) Debug.outln( "Dependency.isSatisfied(): expression not satisfied: " );// + this );
    } else {
      sat = Expression.valuesEqual( parameter, expression, getType() );
      if ( !sat ) {
        parameter.setStale( true );
        if ( Debug.isOn() && parameter != null && expression != null) {
          Debug.outln( "Dependency.isSatisfied(): parameter value (" + parameter.getValueNoPropagate()
                       + ") not equal to evaluated expression (" + expression.evaluate( false ) + ") " ); // + this );
        }
      }
    }
    if ( Debug.isOn() ) Debug.outln( "Dependency.isSatisfied() = " + sat + ": " + this );
    return sat;
  }

  public Class< T > getType() {
    if ( parameter != null ) {
      return (Class< T >)parameter.getType();
    }
    if ( expression != null ) {
      return (Class< T >)expression.resultType;
    }
    return null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Satisfiable#satisfy()
   */
  @Override
  public boolean satisfy(boolean deep, Set< Satisfiable > seen) {
    Pair< Boolean, Set< Satisfiable > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return true;
    seen = pair.second;
    if ( isSatisfied(deep, null) ) return true;
//    if ( Random.global.nextDouble() < 0.2 ) {
//      return getConstraintExpression().satisfy( deep, seen );
//    }
    if ( Debug.isOn() ) Debug.outln("Dependency.satisfy() calling ground: " + this );
    if ( expression == null ) return false;
    if ( parameter == null ) return false;
    expression.ground(deep, null);
    expression.satisfy(deep, seen);
    //if ( expression.isGrounded(deep, null) ) {
      boolean applied = apply( true );
    //} else {
    //  parameter.satisfy(deep, seen);
    //}
    boolean succ = isSatisfied( false, null );
    if ( expression.form == Form.Function
         && ( (FunctionCall)expression.expression ).getMethod() != null
         && ( (FunctionCall)expression.expression ).getMethod().getName()
                                                   .contains( "minus" ) ) {
      System.out.println( "minus dep was" + ( applied ? "" : " not" )
                          + " applied," + ( succ ? "" : " not" )
                          + " satisfied: " + this );
    }
    return succ;
  }

  public ConstraintExpression getConstraintExpression() {
    if ( expression == null ) return null;
    if ( parameter == null ) return null;
    if ( constraint == null ) {
      Equals< T > eq =
          new Functions.Equals< T >( new Expression< T >( parameter ),
                                     expression );
      constraint = new ConstraintExpression( eq );
    }
    return constraint;
  }
  
  public boolean evaluate() {
    return satisfy( true, null );
  }

  @Override
  public boolean substitute( Parameter< ? > t1, Parameter< ? > t2, boolean deep,
                             Set< HasParameters > seen ) {
    if ( expression == null ) return false;
    if ( parameter == null ) return false;
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    boolean subbed = false;
    if ( parameter == t1 ) {
      parameter = (Parameter< T >)t2;
      subbed = true;
    }
    if ( expression.substitute( t1, t2, deep, seen ) ) {
      subbed = true;
    }
    return subbed;
  }

  // Gather the parameters of the expression and the dependent parameter.
  @Override
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set< HasParameters > seen ) {
    if ( expression == null ) return Utils.getEmptySet();
    if ( parameter == null ) return Utils.getEmptySet();
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
    if ( parameter != null ) {
      set.add( parameter );
    }
    if ( expression != null ) {
      set = Utils.addAll( set, expression.getParameters( deep, seen ) );
    }
    return set;
  }

  // Add all parameters except the dependent parameter.
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasParameters#getFreeParameters(boolean, java.util.Set)
   */
  @Override
  public Set< Parameter< ? > > getFreeParameters( boolean deep,
                                                  Set< HasParameters > seen) {
    if ( expression == null ) return Utils.getEmptySet();
    if ( parameter == null ) return Utils.getEmptySet();
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;

    Set< Parameter< ? > > set = getConstraintExpression().getFreeParameters();
    set.remove( parameter );
    return set;
  }

  @Override
  public void setFreeParameters( Set< Parameter< ? >> freeParams, boolean deep,
                                 Set< HasParameters > seen ) {
    Assert.assertTrue( "This method is not supported!", false );
    // TODO Auto-generated method stub
  }
  
  @Override
  public Set< Variable< ? > > getVariables() {
    Set< Variable< ? > > s =
        new TreeSet< Variable< ? > >( getParameters( false, null ) );
    return s;
  }

  /* (non-Javadoc)
   * @see solver.Constraint#pickValue(solver.Variable)
   */
  @Override
  public < T1 > boolean pickValue( Variable< T1 > variable ) {
    if ( variable == null || !Solver.allowPickValue) return false;
    if ( Debug.isOn() ) Debug.outln( "Dependency.pickValue(" + variable + ") begin" );
    if ( variable == this.parameter ) {
      Object value = variable.getValue( false ); // DON'T CHANGE false
      if ( refresh( this.parameter ) ) {
        if ( !Utils.valuesEqual( variable.getValue( true ), value ) ) { // DON'T CHANGE true
          if ( Debug.isOn() ) Debug.outln( "Dependency.pickValue(" + variable + ") returning refreshed value" );
          return true;
        }
      }
      Variable< ? > var = pickRandomFreeVariable();
      if ( var == null ) var = pickRandomVariable();
      if ( var == null ) return false;
      Constraint c = getConstraintExpression();
      boolean changedSomething = false;
      if ( !(var instanceof Parameter) || !((Parameter) var).isDependent()){
        if ( c != null) {
          if ( c.pickValue( var ) ) changedSomething = true;
        } else {
          if ( var.pickValue() ) changedSomething = true;
        }
      }
      if ( Debug.isOn() ) Debug.outln( "Dependency.pickValue(" + variable + ") returns "
                   + changedSomething + " for target/sink param" );
      return changedSomething;
    }
    if ( variable instanceof Parameter
         && !hasParameter( (Parameter< T1 >)variable, false, null ) ) {
      return false;
    }
    Constraint c = getConstraintExpression();
    if ( c != null ) {
      if ( c.pickValue( variable ) ) return true;
    }
    // TODO Auto-generated method stub
    return false;
  }

  protected Variable< ? > pickRandomVariable() {
    Set< Variable< ? > > vars = getVariables();
    if ( !Utils.isNullOrEmpty( vars ) ) {
      int i = Random.global.nextInt( vars.size() );
      Variable<?> v = (Variable<?>)(vars.toArray())[i];
      return v;
    }
    return null;
  }

  protected Variable< ? > pickRandomFreeVariable() {
    Set< Variable< ? > > vars = getFreeVariables();
    if ( !Utils.isNullOrEmpty( vars ) ) {
      int i = Random.global.nextInt( vars.size() );
      Variable<?> v = (Variable<?>)(vars.toArray())[i];
      return v;
    }
    return null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#restrictDomain(gov.nasa.jpl.ae.solver.Variable)
   */
  @Override
  public < T1 > boolean restrictDomain( Variable< T1 > v ) {
    if ( expression == null ) return false;
    if ( parameter == null ) return false;
    if ( v == parameter ) {
      Object ov = expression.evaluate(true);
      T val = Expression.evaluate( ov, getType(), false, true );
      Domain<T1> d = v.getDomain().clone();
      d.restrictToValue( (T1)val );
      v.setDomain( d );
    } else {
      if ( getConstraintExpression() == null) return false;
      getConstraintExpression().restrictDomain( v );
    }
    return v.getDomain() != null && v.getDomain().size() > 0; 
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#isFree(gov.nasa.jpl.ae.solver.Variable)
   */
  @Override
  public < T1 > boolean isFree( Variable< T1 > v ) {
    return ( v != parameter );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#isDependent(gov.nasa.jpl.ae.solver.Variable)
   */
  @Override
  public < T1 > boolean isDependent( Variable< T1 > v ) {
    return ( v == parameter );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Constraint#getFreeVariables()
   */
  @Override
  public Set< Variable< ? > > getFreeVariables() {
    if ( getConstraintExpression() == null ) return Utils.getEmptySet();
    Set< Variable< ? > > set = getConstraintExpression().getFreeVariables();
    set.remove( parameter ); 
    return set;
  }

  @Override
  public void setFreeVariables( Set< Variable< ? > > freeVariables ) {
    if ( getConstraintExpression() == null ) return;
    getConstraintExpression().setFreeVariables( freeVariables );
  }

  @Override
  public int compareTo( Constraint o ) {
    return compareTo( o, true );
  }
  public int compareTo( Constraint o, boolean checkId ) {
    if ( this == o ) return 0;
    if ( o == null ) return -1;
    if ( checkId ) return CompareUtils.compare( getId(), o.getId() );
    // If checkId==true, this is a little weird since this Dependency and it's
    // constraintExpression have different ids.
    int compare = 0;
    if ( getConstraintExpression() != null ) {
      compare = getConstraintExpression().compareTo( o, checkId );
      if ( compare != 0 ) return compare;
    }
    compare = CompareUtils.compare( this, o, false, checkId );
    if ( compare != 0 ) return compare;
    return compare;
//    if ( o instanceof Dependency ) {
//      Dependency< ? > od = (Dependency< ? >)o;
//      int compare = parameter.compareTo( od.parameter );
//      if ( compare != 0 ) return compare;
//      compare = expression.compareTo( od.expression );
//      if ( compare != 0 ) return compare;
//      return this.toString().compareTo( od.toString() );
//    }
//    return ((Object)this).getClass().getName().compareTo( o.getClass().getName() );
  }

  @Override
  public void handleValueChangeEvent( Parameter< ? > parameter ) {
    if ( hasParameter( parameter, true, null )
         && this.parameter != parameter ) {
      apply( true );
    }
  }

  @Override
  public void handleDomainChangeEvent( Parameter< ? > parameter ) {
    // TODO -- REVIEW -- Anything to do?
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return getClass().getName();
  }

  @Override
  public String toShortString() {
    StringBuffer sb = new StringBuffer();

    sb.append("(");

    if ( parameter == null ) sb.append("null");
    else sb.append( parameter.toShortString() );

    sb.append( " <-- " );
    
    if ( expression == null ) sb.append( "null" );
    else sb.append( expression.toShortString() );

    sb.append(")");
    return sb.toString();
  }
  
  @Override
  public String toString() {
    return MoreToString.Helper.toString( this );
  }

  @Override
  public String toString(boolean withHash, boolean deep, Set< Object > seen,
                         Map< String, Object > otherOptions) {
    Pair< Boolean, Set< Object > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) deep = false;
    seen = pair.second;
    
    StringBuffer sb = new StringBuffer();
    
    sb.append("Dependency");
    if ( withHash ) sb.append( "@" + hashCode() );
    sb.append("(");
    
    if ( parameter == null ) sb.append("null");
    else sb.append( parameter.toString( withHash, deep, seen, otherOptions ) );

    sb.append( " <-- " );
    if ( expression == null ) sb.append( "null" );
    else sb.append( expression.toString( withHash, deep, seen, otherOptions ) );

    sb.append(")");
    return sb.toString();
  }

  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen ) {
    return toString( withHash, deep, seen, null );
  }

//  @Override
//  public String toString( boolean withHash, boolean deep, Set< Object > seen,
//                          Map< String, Object > otherOptions ) {
//    // TODO Auto-generated method stub
//    return null;
//  }

  @Override
  public boolean isStale() {
    boolean parameterStale = parameter.isStale();
    boolean expressionStale = expression.isStale();
    return parameterStale || expressionStale;
  }

  @Override
  public void setStale( boolean staleness ) {
    if ( Debug.isOn() ) Debug.outln( "setStale(" + staleness + ") to " + this );
    if ( parameter != null ) parameter.setStale( staleness );
  }

  @Override
  public boolean refresh( Parameter< ? > parameter ) {
    if ( this.parameter == parameter ) {
      if ( !refreshing ) {
        refreshing = true;
        apply();
        refreshing = false;
        return true;
      }
    }
    return false;
  }

  @Override
  public void setStaleAnyReferencesTo( Parameter< ? > changedParameter ) {
    if ( expression == null ) return;
    if ( expression.hasParameter( changedParameter, true, null ) ) {
      parameter.setStale( true );
    }
  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                               Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    if ( seen != null ) seen.remove( this ); // because getParameters checks seen set, too.
    return getParameters( deep, seen ).contains( parameter );
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep,
                                  Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    if ( p == parameter ) return false;
    if ( expression == null ) return false;
    return expression.isFreeParameter( p, deep, seen );
  }

  @Override
  public Collection< Constraint > getConstraints( boolean deep,
                                                  Set< HasConstraints > seen ) {
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< Constraint > set = new HashSet< Constraint >();
    set.add( this );
    if ( deep ) {
      Set< Constraint > pSet =
          HasConstraints.Helper.getConstraints( getParameters( false, null ),
                                                deep, seen );
      set = Utils.addAll( set, pSet );
    }
    return set;
  }

  @Override
  public long getNumberOfResolvedConstraints( boolean deep,
                                              Set< HasConstraints > seen ) {
    return isSatisfied( false, null ) ? 1 : 0;
  }

  @Override
  public long getNumberOfUnresolvedConstraints( boolean deep,
                                                Set< HasConstraints > seen ) {
    return isSatisfied( false, null ) ? 0 : 1;
  }

  @Override
  public long getNumberOfConstraints( boolean deep, Set< HasConstraints > seen ) {
    return 1;
  }
      
  @Override
  public Set< TimeVarying< ? > >
      getTimeVaryingObjects( boolean deep, Set< HasTimeVaryingObjects > seen ) {
    Pair< Boolean, Set< HasTimeVaryingObjects > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< TimeVarying< ? > > set = new HashSet< TimeVarying< ? > >();
    set = Utils.addAll( set, HasTimeVaryingObjects.Helper.getTimeVaryingObjects( parameter, deep, seen ) );
    set = Utils.addAll( set, HasTimeVaryingObjects.Helper.getTimeVaryingObjects( expression, deep, seen ) );
    return set;
  }

  @Override
  public void detach( Parameter< ? > parameter ) {
    // REVIEW -- nothing to do here, right?  The dependency gets detached from
    // the ParameterListener that has the dependency.
  }

  /**
   * @return the expression to whose value the Dependency's parameter is set
   */
  public Expression< ? > getExpression() {
    return expression;
  }

  @Override
  public CollectionTree getConstraintCollection(boolean deep, Set< HasConstraints > seen) {
    // TODO Auto-generated method stub
    return null;
  }

}
