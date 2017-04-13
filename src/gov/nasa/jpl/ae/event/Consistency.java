package gov.nasa.jpl.ae.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import gov.nasa.jpl.ae.event.Functions.EQ;
import gov.nasa.jpl.ae.event.Functions.Plus;
import gov.nasa.jpl.ae.event.Functions.Times;
import gov.nasa.jpl.ae.solver.BooleanDomain;
import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.DoubleDomain;
import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.mbee.util.Pair;

public class Consistency {
  
  /**
   * A map for saving away the original domains of the variables in case we want to reset.
   */
  public Map< Variable< ? >, Domain< ? > > oldDomains =
      new LinkedHashMap< Variable< ? >, Domain< ? > >();

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
          Pair<Domain<Boolean>,Boolean> p = cx.restrictDomain( BooleanDomain.trueDomain, true, null );
          restrictedSomething = restrictedSomething || p.second; 
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
    Parameter<Integer> z = new IntegerParameter( "z", new IntegerDomain( 10, 15 ), 2, null );
    Parameter<Integer> y = new IntegerParameter( "y", new IntegerDomain( 1, 5 ), 1, null );
    Parameter<Integer> x = new IntegerParameter( "x", new IntegerDomain( 4, 8 ), 1, null );
    Parameter<Double> w = new DoubleParameter( "w", new DoubleDomain( 0.0, 10.0 ), 2.0, null );
 
    // test for inverse functions
    System.out.println( "x = " + x + "; domain = " + x.getDomain() );
    System.out.println( "y = " + y + "; domain = " + y.getDomain() );
    System.out.println( "z = " + z + "; domain = " + z.getDomain() );
//    EQ< Integer > eq = new EQ< Integer >( x, y );
    Plus<Integer, Integer> XplusY = new Plus<Integer, Integer>(x, y);
    EQ< Integer > sum = new EQ< Integer >(XplusY, z);
    ConstraintExpression s = new ConstraintExpression( sum );
    System.out.println( "ConstraintExpression: " + s );
//    ConstraintExpression c = new ConstraintExpression( eq );
    
//    Collection< Constraint > list = new ArrayList< Constraint >();
//    list.add( c );
//    arcConsistency( list  );
//    System.out.println( "x = " + x + "; domain = " + x.getDomain() );
//    System.out.println( "y = " + y + "; domain = " + y.getDomain() );    
    
    Collection< Constraint > list1 = new ArrayList< Constraint >();
    Collection< Constraint > list2 = new ArrayList< Constraint >();
    Collection< Constraint > list3 = new ArrayList< Constraint >();

    list1.add( s );
    arcConsistency( list1 );
    System.out.println();
    System.out.println( "x = " + x + "; new domain = " + x.getDomain() );
    System.out.println( "should be  x domain = [5 8]\n" );
    System.out.println( "y = " + y + "; new domain = " + y.getDomain() );
    System.out.println( "should be  y domain = [2 5]\n" );
    System.out.println( "z = " + z + "; new domain = " + z.getDomain() );
    System.out.println( "should be  z domain = [10 13]\n" );
    

    Times<Integer, Integer> XtimesY = new Times<Integer, Integer>(x, y);
    EQ< Integer > product = new EQ< Integer >(XtimesY, z);
    ConstraintExpression p = new ConstraintExpression( product );

    System.out.println( "ConstraintExpression: " + p );

    list2.add( p );
    arcConsistency( list2 );
    System.out.println();
    System.out.println( "x = " + x + "; new domain = " + x.getDomain() );
    System.out.println( "should be  x domain = [5 5]\n" );
    System.out.println( "y = " + y + "; new domain = " + y.getDomain() );
    System.out.println( "should be  y domain = [2 2]\n" );
    System.out.println( "z = " + z + "; new domain = " + z.getDomain() );
    System.out.println( "should be  z domain = [10 12]\n" );

    
    System.out.println( "ConstraintExpression: " + s );
    System.out.println( "ConstraintExpression: " + p );

    list3.add( s );
    list3.add( p );
    
    arcConsistency( list3 );
    System.out.println();
    System.out.println( "x = " + x + "; new domain = " + x.getDomain() );
    System.out.println( "should be  x domain = []\n" );
    System.out.println( "y = " + y + "; new domain = " + y.getDomain() );
    System.out.println( "should be  y domain = []\n" );
    System.out.println( "z = " + z + "; new domain = " + z.getDomain() );
    System.out.println( "should be  z domain = []\n" );
    
//    Parameter<Integer> z = new Parameter<Integer>( "z", new IntegerDomain( 10, 15 ), 2, null );
    ((IntegerDomain)z.getDomain()).setBounds( 10, 15 );
//    Parameter<Integer> y = new Parameter<Integer>( "y", new IntegerDomain( 1, 5 ), 1, null );
    ((IntegerDomain)y.getDomain()).setBounds( 1, 5 );
//    Parameter<Integer> x = new Parameter<Integer>( "x", new IntegerDomain( 4, 8 ), 1, null );
    ((IntegerDomain)x.getDomain()).setBounds( 4, 8 );
//    Parameter<Double> w = new Parameter<Double>( "w", new DoubleDomain( 0.0, 10.0 ), 2.0, null );
    Functions.LT<Integer> lt = new Functions.LT< Integer >(XplusY, z);
    s = new ConstraintExpression( lt );

    System.out.println( "ConstraintExpression: " + s );

    list1.clear();
    list1.add(s);
    arcConsistency( list1 );
    System.out.println();
    System.out.println( "x = " + x + "; new domain = " + x.getDomain() );
    System.out.println( "should be  x domain = []\n" );
    System.out.println( "y = " + y + "; new domain = " + y.getDomain() );
    System.out.println( "should be  y domain = []\n" );
    System.out.println( "z = " + z + "; new domain = " + z.getDomain() );
    System.out.println( "should be  z domain = []\n" );
    
    }
}