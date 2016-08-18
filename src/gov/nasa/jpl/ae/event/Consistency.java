package gov.nasa.jpl.ae.event;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import gov.nasa.jpl.ae.solver.BooleanDomain;
import gov.nasa.jpl.ae.solver.Constraint;
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
  
}
