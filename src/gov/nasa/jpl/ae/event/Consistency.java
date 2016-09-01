package gov.nasa.jpl.ae.event;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import gov.nasa.jpl.ae.event.Functions.EQ;
import gov.nasa.jpl.ae.event.Functions.Less;
import gov.nasa.jpl.ae.event.Functions.Plus;
import gov.nasa.jpl.ae.event.Functions.Sum;
import gov.nasa.jpl.ae.solver.BooleanDomain;
import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.DoubleDomain;
import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.mbee.util.Utils;

public class Consistency {

  public static boolean arcConsistency( Collection<Constraint> constraints ) {
//    LinkedHashSet< Variable< ? > > variables =
//        new LinkedHashSet< Variable< ? > >();
//    LinkedHashMap< Variable< ? >, Set< Constraint > > constraintsForVariables =
//        new LinkedHashMap< Variable< ? >, Set< Constraint > >();
//    for ( Constraint c : constraints ) {
//      Set< Variable< ? > > vars = c.getVariables();
//      variables.addAll( vars );
//      for ( Variable< ? > var : vars ) {
//        Utils.add( constraintsForVariables, var, c );
//      }
//    }
//    
//    for ( Variable< ? > v1 : variables ) {
//      for ( Constraint c : constraintsForVariables.get( v1 ) ) {
//        c.restrictDomain( v1 );
//      }
//    }

    long ct = 0;
    long maxCount = constraints.size() * constraints.size();
    while ( ct < maxCount ) {
      boolean restrictedSomething = false;
      for ( Constraint c : constraints ) {
        if ( c instanceof ConstraintExpression ) {
          ConstraintExpression cx = (ConstraintExpression)c;
          cx.restrictDomain( BooleanDomain.trueDomain, true, null );
        } else {
          Set< Variable< ? > > vars = c.getVariables();
          for ( Variable< ? > v : vars ) {
            boolean b = c.restrictDomain( v );
            restrictedSomething = restrictedSomething || b;
          }
        }
      }
      if ( !restrictedSomething ) {
        return true;
      }
      ++ct;
    }
    return false;
  }
  
  public static void main( String[] args ) {
//    Parameter<Double> z = new Parameter<Double>( "z", (Domain<Double>)(new DoubleDomain( 0.0, 10.0 )), 10.0, null );
    Parameter<Integer> z = new Parameter<Integer>( "z", new IntegerDomain( 10, 15 ), 2, null );
    Parameter<Integer> y = new Parameter<Integer>( "y", new IntegerDomain( 1, 5 ), 1, null );
    Parameter<Integer> x = new Parameter<Integer>( "x", new IntegerDomain( 4, 8 ), 1, null );
    Parameter<Double> w = new Parameter<Double>( "w", new DoubleDomain( 0.0, 10.0 ), 2.0, null );
 
    // test for inverse functions
    System.out.println( "x = " + x + "; domain = " + x.getDomain() );
    System.out.println( "y = " + y + "; domain = " + y.getDomain() );
//    EQ< Integer > eq = new EQ< Integer >( x, y );
    Plus<Integer, Integer> XplusY = new Plus<Integer, Integer>(x, y);
    EQ< Integer > sum = new EQ< Integer >(XplusY, z);
    ConstraintExpression s = new ConstraintExpression( sum );
//    ConstraintExpression c = new ConstraintExpression( eq );
    
//    Collection< Constraint > list = new ArrayList< Constraint >();
//    list.add( c );
//    arcConsistency( list  );
//    System.out.println( "x = " + x + "; domain = " + x.getDomain() );
//    System.out.println( "y = " + y + "; domain = " + y.getDomain() );    
    
    Collection< Constraint > list2 = new ArrayList< Constraint >();
    list2.add( s );
    arcConsistency( list2 );
    System.out.println( "x = " + x + "; new domain = " + x.getDomain() );
    System.out.println( "y = " + y + "; new domain = " + y.getDomain() );
    System.out.println( "z = " + z + "; domain = " + z.getDomain() );
    
    
    }
}