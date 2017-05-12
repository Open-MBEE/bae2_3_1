package gov.nasa.jpl.ae.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
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
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;

public class Consistency {
  
  boolean debug = true;
  
  public Collection<Constraint> constraints = null;
  
  /**
   * A map for saving away the original domains of the variables in case we want to reset.
   */
  public Map< Variable< ? >, Domain< ? > > savedDomains =
      new LinkedHashMap< Variable< ? >, Domain< ? > >();

  
  
  public Set<Variable<?>> getVariables() {
    LinkedHashSet<Variable<?>> vars = new LinkedHashSet< Variable<?> >();
    if ( constraints == null ) {
      return vars;
    }
    for ( Constraint c : constraints ) {
      Set< Variable< ? > > someVars = c.getVariables();
      vars.addAll( someVars );
    }
    return vars;
  }
  
  public String constraintsToString() {
    StringBuffer sb = new StringBuffer();
    String s ="ConstraintExpressions:\n    " + Utils.join( constraints, "\n    ") + "\n";
    return s;
  }

  public String variablesToString() {
    StringBuffer sb = new StringBuffer();
    Set< Variable< ? > > vars = getVariables();
    for ( Variable<?> v : vars ) {
      sb.append( MoreToString.Helper.toLongString( v ) + " domain = " + v.getDomain() + "\n");//"; value = " + v.getValue( false ) + "\n" );
    }
    return sb.toString();
  }

  public void saveDomains() {
    savedDomains = new LinkedHashMap< Variable< ? >, Domain< ? > >();
    Set< Variable< ? > > vars = getVariables();
    for (Variable<?> v : vars) {
      Domain< ? > d = v.getDomain();
      if ( d != null ) {
        Domain< ? > copy = d.clone();
        v.setDomain( (Domain)copy );
        savedDomains.put( v, d );
      }
    }
    //System.out.println( "saved: " + MoreToString.Helper.toLongString( savedDomains ) );
  }

  public void restoreDomains() {
    //System.out.println( "restoring from : " + MoreToString.Helper.toLongString( savedDomains ) );
    for (  Entry< Variable< ? >, Domain< ? > > e : savedDomains.entrySet() ) {
      Variable v = e.getKey();
      v.setDomain( e.getValue() );
    }
  }


  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append( constraintsToString() );
    sb.append( "\n" );
    sb.append( variablesToString() );
    return sb.toString();
  }

  public static boolean arcConsistency( Collection<Constraint> constraints, boolean quiet ) {
    Consistency c = new Consistency();
    c.constraints = constraints;
    boolean succ = c.arcConsistency(quiet);
    return succ;
  }

  public boolean arcConsistency(boolean quiet) {
    saveDomains();
    if ( !quiet ) {
      System.out.println( "Arc consistency problem:\n" + toString() );
    }

    long ct = 0;
    boolean succeeded = false;
    long maxCount = constraints.size() * constraints.size() + 1;
    System.out.println("arc consistency max rounds = " + maxCount);
    while ( ct < maxCount && ct < 100 ) {
      System.out.println("arc consistency round " + (ct+1));
      boolean restrictedSomething = false;
      for ( Constraint c : constraints ) {
        if ( c instanceof ConstraintExpression ) {
          ConstraintExpression cx = (ConstraintExpression)c;
          Pair<Domain<Boolean>,Boolean> p = cx.restrictDomain( BooleanDomain.trueDomain, true, null );
          if ( p.second == Boolean.TRUE ) {
            System.out.println( "Restricted constraint " + MoreToString.Helper.toLongString( cx ) + " to domain " + p.first );
          }
          restrictedSomething = restrictedSomething || (p.second == Boolean.TRUE); 
        } else {
          Set< Variable< ? > > vars = c.getVariables();
          for ( Variable< ? > v : vars ) {
            if ( v.getDomain() != null ) {
              boolean b = c.restrictDomain( v );
              if ( b ) {
                System.out.println( "Restricted domain of " + MoreToString.Helper.toLongString( v ) + " to " + v.getDomain() + " in constraint " + c  );
              }
              restrictedSomething = restrictedSomething || b;
            }
          }
        }
      }
      if ( !restrictedSomething ) {
        succeeded = true;
        break;
        //return true;
      }
      ++ct;
    }
    
    if ( !quiet ) {
      System.out.println();
      if ( succeeded ) {
        System.out.println( "Arc consistency completed after " + ct + " passes at the constraints:" );
      } else {
        System.out.println( "Arc consistency failed to complete after " + ct + " passes at the constraints:" );
      }
      System.out.println();
      System.out.println( variablesToString() );
    }

    return succeeded;
  }
  
  public static void main( String[] args ) {
    
    Consistency c = new Consistency();
    
//    Parameter<Double> z = new Parameter<Double>( "z", (Domain<Double>)(new DoubleDomain( 0.0, 10.0 )), 10.0, null );
    Parameter<Integer> z = new IntegerParameter( "z", new IntegerDomain( 10, 15 ), 2, null );
    Parameter<Integer> y = new IntegerParameter( "y", new IntegerDomain( 1, 5 ), 1, null );
    Parameter<Integer> x = new IntegerParameter( "x", new IntegerDomain( 4, 8 ), 1, null );
    Parameter<Double> w = new DoubleParameter( "w", new DoubleDomain( 0.0, 10.0 ), 2.0, null );
 
    
    int probCount = 1;
    System.out.println("\n\n==================== PROBLEM " + probCount++ + " ====================\n");

    
    // test for inverse functions
//    System.out.println( "x = " + x + "; domain = " + x.getDomain() );
//    System.out.println( "y = " + y + "; domain = " + y.getDomain() );
//    System.out.println( "z = " + z + "; domain = " + z.getDomain() );
//    EQ< Integer > eq = new EQ< Integer >( x, y );
    Plus<Integer, Integer> XplusY = new Plus<Integer, Integer>(x, y);
    EQ< Integer > sum = new EQ< Integer >(XplusY, z);
    ConstraintExpression s = new ConstraintExpression( sum );
//    System.out.println( "ConstraintExpression: " + s );
////    ConstraintExpression c = new ConstraintExpression( eq );
    
//    Collection< Constraint > list = new ArrayList< Constraint >();
//    list.add( c );
//    arcConsistency( list  );
//    System.out.println( "x = " + x + "; domain = " + x.getDomain() );
//    System.out.println( "y = " + y + "; domain = " + y.getDomain() );    
    
    Collection< Constraint > list1 = new ArrayList< Constraint >();
    Collection< Constraint > list2 = new ArrayList< Constraint >();
    Collection< Constraint > list3 = new ArrayList< Constraint >();

    list1.add( s );
    c.constraints = list1;
    c.arcConsistency( false );

    System.out.println( "x = " + x + "; new domain = " + x.getDomain() );
    System.out.println( "should be  x domain = [5, 8]\n" );
    System.out.println( "y = " + y + "; new domain = " + y.getDomain() );
    System.out.println( "should be  y domain = [2, 5]\n" );
    System.out.println( "z = " + z + "; new domain = " + z.getDomain() );
    System.out.println( "should be  z domain = [10, 13]\n" );
    
    
    System.out.println("\n\n==================== PROBLEM " + probCount++ + " ====================\n");
    
    Times<Integer, Integer> XtimesY = new Times<Integer, Integer>(x, y);
    EQ< Integer > product = new EQ< Integer >(XtimesY, z);
    ConstraintExpression p = new ConstraintExpression( product );

//    System.out.println();
//    System.out.println();
//    System.out.println( "ConstraintExpression: " + p );
//    System.out.println( "x domain = " + x.getDomain() );
//    System.out.println( "y domain = " + y.getDomain() );
//    System.out.println( "z domain = " + z.getDomain() );

    list2.add( p );
    c.constraints = list2;
    c.arcConsistency( false );
    System.out.println();
    System.out.println( "x = " + x + "; new domain = " + x.getDomain() );
    System.out.println( "should be  x domain = [5, 6]\n" );
    System.out.println( "y = " + y + "; new domain = " + y.getDomain() );
    System.out.println( "should be  y domain = [2]\n" );
    System.out.println( "z = " + z + "; new domain = " + z.getDomain() );
    System.out.println( "should be  z domain = [10, 12]\n" );

    
    System.out.println("\n\n==================== PROBLEM " + probCount++ + " ====================\n");
    
//    System.out.println();
//    System.out.println();
//    System.out.println( "ConstraintExpression: " + s );
//    System.out.println( "ConstraintExpression: " + p );
//    System.out.println( "x domain = " + x.getDomain() );
//    System.out.println( "y domain = " + y.getDomain() );
//    System.out.println( "z domain = " + z.getDomain() );

    list3.add( s );
    list3.add( p );
    
    c.constraints = list3;
    c.arcConsistency( false );
    System.out.println();
    System.out.println( "x = " + x + "; new domain = " + x.getDomain() );
    System.out.println( "should be  x domain = []\n" );
    System.out.println( "y = " + y + "; new domain = " + y.getDomain() );
    System.out.println( "should be  y domain = []\n" );
    System.out.println( "z = " + z + "; new domain = " + z.getDomain() );
    System.out.println( "should be  z domain = []\n" );
    
    
    System.out.println("\n\n==================== PROBLEM " + probCount++ + " ====================\n");
    
    ((IntegerDomain)z.getDomain()).setBounds( 10, 15 );
    ((IntegerDomain)y.getDomain()).setBounds( 1, 5 );
    ((IntegerDomain)x.getDomain()).setBounds( 4, 8 );
    Functions.LT<Integer> lt = new Functions.LT< Integer >(XplusY, z);
    s = new ConstraintExpression( lt );

//    System.out.println();
//    System.out.println();
//    System.out.println( "ConstraintExpression: " + s );
//    System.out.println( "x domain = " + x.getDomain() );
//    System.out.println( "y domain = " + y.getDomain() );
//    System.out.println( "z domain = " + z.getDomain() );

    list1.clear();
    list1.add(s);
    c.constraints = list1;
    c.arcConsistency( false );
    System.out.println();
    System.out.println( "x = " + x + "; new domain = " + x.getDomain() );
    System.out.println( "should be  x domain = [4, 8]\n" );
    System.out.println( "y = " + y + "; new domain = " + y.getDomain() );
    System.out.println( "should be  y domain = [1, 5]\n" );
    System.out.println( "z = " + z + "; new domain = " + z.getDomain() );
    System.out.println( "should be  z domain = [10, 15]\n" );

    
    System.out.println("\n\n==================== PROBLEM " + probCount++ + " ====================\n");

    c.restoreDomains();
    ((IntegerDomain)z.getDomain()).setBounds( 6, 6 );
//    Functions.LT<Integer> lt = new Functions.LT< Integer >(XplusY, z);
//    s = new ConstraintExpression( lt );

//    System.out.println();
//    System.out.println();
//    System.out.println( "ConstraintExpression: " + s );
//    System.out.println( "x domain = " + x.getDomain() );
//    System.out.println( "y domain = " + y.getDomain() );
//    System.out.println( "z domain = " + z.getDomain() );

//    list1.clear();
//    list1.add(s);
    c.arcConsistency( false );
    System.out.println();
    System.out.println( "x = " + x + "; new domain = " + x.getDomain() );
    System.out.println( "should be  x domain = [4]\n" );
    System.out.println( "y = " + y + "; new domain = " + y.getDomain() );
    System.out.println( "should be  y domain = [1]\n" );
    System.out.println( "z = " + z + "; new domain = " + z.getDomain() );
    System.out.println( "should be  z domain = [6]\n" );


    System.out.println("\n\n==================== PROBLEM " + probCount++ + " ====================\n");

    c.restoreDomains();
    ((IntegerDomain)z.getDomain()).setBounds( 5, 8 );

    c.arcConsistency( false );
    System.out.println();
    System.out.println( "x = " + x + "; new domain = " + x.getDomain() );
    System.out.println( "should be  x domain = [4, 6]\n" );
    System.out.println( "y = " + y + "; new domain = " + y.getDomain() );
    System.out.println( "should be  y domain = [1, 3]\n" );
    System.out.println( "z = " + z + "; new domain = " + z.getDomain() );
    System.out.println( "should be  z domain = [6, 8]\n" );


    System.out.println("\n\n==================== PROBLEM " + probCount++ + " ====================\n");
    
    c.restoreDomains();
    
    Plus<Integer, Integer> XplusYplusZ = new Plus<Integer, Integer>(XplusY, z);
    Functions.GTE< Integer > sumGtr =
        new Functions.GTE< Integer >( new Expression<Integer>(XplusYplusZ), new Expression<Integer>(19) );
    c.constraints.clear();
    c.constraints.add( new ConstraintExpression( sumGtr ) );
    
    c.arcConsistency( false );

    System.out.println();
    System.out.println( "x = " + x + "; new domain = " + x.getDomain() );
    System.out.println( "should be  x domain = [6, 8]\n" );
    System.out.println( "y = " + y + "; new domain = " + y.getDomain() );
    System.out.println( "should be  y domain = [3, 5]\n" );
    System.out.println( "z = " + z + "; new domain = " + z.getDomain() );
    System.out.println( "should be  z domain = [6, 8]\n" );

    System.out.println("\n\n==================== PROBLEM " + probCount++ + " ====================\n");
    
    //Parameter<Integer> aa = new IntegerParameter( "aa", new IntegerDomain( 0, 10 ), 0, null );
    Parameter<Integer> aa = new IntegerParameter( "aa", null );
    Parameter<Integer> bb = new IntegerParameter( "bb", null );
    Parameter<Integer> cc = new IntegerParameter( "cc", null );
    Parameter<Integer> dd = new IntegerParameter( "dd", null );
    //Parameter<Double> dub = new DoubleParameter( "dub", new DoubleDomain( 0.0, 10.0 ), 2.0, null );
    ConstraintExpression ca = CE( GT( aa, 0 ) );
    ConstraintExpression cba1 = CE( GTE( bb, Plus( aa, 5 ) ) );
    ConstraintExpression cba2 = CE( LTE( bb, Plus( aa, 10 ) ) );
    ConstraintExpression ccb1 = CE( GTE( cc, Plus( bb, 5 ) ) );
    ConstraintExpression ccb2 = CE( LTE( cc, Plus( bb, 10 ) ) );
    ConstraintExpression cdc1 = CE( GTE( dd, Plus( cc, 5 ) ) );
    ConstraintExpression cdc2 = CE( LTE( dd, Plus( cc, 10 ) ) );
    ConstraintExpression cd = CE( LT( dd, 25 ) );
    
    list1.clear();
    list1.add(ca);
    list1.add(cba1);
    list1.add(cba2);
    list1.add(ccb1);
    list1.add(ccb2);
    list1.add(cdc1);
    list1.add(cdc2);
    list1.add(cd);
    c.constraints = list1;
    c.arcConsistency( false );
    
    System.out.println();
    System.out.println( "aa = " + aa + ";   new domain = " + aa.getDomain() );
    System.out.println( "should be          aa domain = [1, 9]\n" );
    System.out.println( "bb = " + bb + ";  new domain = " + bb.getDomain() );
    System.out.println( "should be          bb domain = [6, 14]\n" );
    System.out.println( "cc = " + cc + "; new domain = " + cc.getDomain() );
    System.out.println( "should be          cc domain = [11, 19]\n" );
    System.out.println( "dd = " + dd + "; new domain = " + dd.getDomain() );
    System.out.println( "should be          dd domain = [16, 24]\n" );
  
  }

  private static <TT> Functions.LT<TT> LT(Object o1, Object o2) {
    return new Functions.LT<TT>( o1, o2 );
  }
  private static <TT> Functions.LTE<TT> LTE(Object o1, Object o2) {
    return new Functions.LTE<TT>( o1, o2 );
  }
  private static <TT extends Comparable<? super TT>> Functions.GT<TT> GT(Object o1, Object o2) {
    return new Functions.GT<TT>( o1, o2 );
  }
  private static <TT extends Comparable<? super TT>> Functions.GTE<TT> GTE(Object o1, Object o2) {
    return new Functions.GTE<TT>( o1, o2 );
  }
  private static <T1,T2> Functions.Plus<T1,T2> Plus(Object o1, Object o2) {
    return new Functions.Plus<T1,T2>( o1, o2 );
  }
  private static ConstraintExpression CE(FunctionCall f) {
    return new ConstraintExpression( f );
  }
}