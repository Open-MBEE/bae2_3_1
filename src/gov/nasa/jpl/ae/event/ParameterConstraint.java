/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Variable;

/**
 * @author bclement
 *
 */
public interface ParameterConstraint extends Constraint, HasParameters {
  /**
   * This helper class provides static methods for making calls on Objects and
   * Maps that may or may not implement HasParameter or contain objects that do.
   * 
   * @author bclement
   *
   */
  public static class Helper {

    // Below are some of the same functions defined by HasParameters.Helper but
    // allow the caller to pass self (this) without resulting in an infinite
    // loop.
    public static boolean isStale( HasParameters o, boolean deep ) {
      if ( o != null  ) {
        for ( Parameter< ? > p : o.getParameters( deep ) ) {
          if ( p.isStale() ) return true;
        }
      }
      return false;
    }
    
    public static void setStale( Object o, boolean staleness ) {
      // TODO -- REVIEW
      assert false;
    }
    
    public static boolean hasParameter( HasParameters o, Parameter< ? > p,
                                        boolean deep ) {
      return o.getParameters( deep ).contains( p );
    }
    
    public static boolean isFreeParameter( HasParameters o, Parameter< ? > p,
                                           boolean deep ) {
      return o.getFreeParameters( deep ).contains( p );
    }

    // End of functions also defined by HaParameters.Helper
    
    public static Set< Variable< ? > > getVariables( HasParameters o ) {
      Set< Variable< ? > > s = new HashSet< Variable< ? > >();
      s.addAll( o.getParameters( true ) );
      return s;
    }
    
    public static < T > void pickValue( Constraint c, Variable< T > v ) {
      // TODO Auto-generated method stub
      assert false;
    }

    public static < T > void restrictDomain( Constraint c, Variable< T > v ) {
      // TODO Auto-generated method stub
      assert false;
    }

    public static < T > boolean isFree( HasParameters o, Variable< T > v ) {
      if ( v instanceof Parameter<?> ) {
        Set< Parameter< ? > > params = o.getFreeParameters(false);
        if ( params == null ) {
          return false;
        }
        return params.contains( v );
      }
      return true; // REVIEW -- Is true ok for "don't know" case?
    }
    
    public static < T > boolean isDependent( HasParameters o, Variable< T > v ) {
      if ( v instanceof Parameter ) {
        if ( !o.hasParameter( (Parameter< ? >)v, true ) ) {
          return true;  // REVIEW -- Is true ok for "don't know" case?
        }
      }
      if ( o.getFreeParameters( true ).contains( v ) ) {
        return false;
      }
      return true; // REVIEW -- Is true ok for "don't know" case?
    }
    
    public static void setFreeVariables( HasParameters o, Set< Variable< ? > > freeVariables ) {
      boolean deep = false;
      Set< Parameter< ? > > freeParams = o.getFreeParameters( deep );
      if ( freeParams == null ) {
        freeParams = new HashSet< Parameter< ? > >();
        o.setFreeParameters( freeParams );
      }
      for ( Variable< ? > v : freeVariables ) {
        if ( v instanceof Parameter< ? > ) {
          freeParams.add( (Parameter< ? >)v );
        }
      }
    }

   public static Set< Variable< ? > > getFreeVariables( HasParameters o ) {
      Set< Variable< ? > > s = new HashSet< Variable< ? > >();
      s.addAll( o.getFreeParameters( true ) );
      return s;
    }

   public static int compareTo( Object o, Constraint c ) {
     return o.toString().compareTo( c.toString() );
//   if ( o instanceof ConstraintExpression ) {
//   ConstraintExpression oc = (ConstraintExpression)o;
//   return this.toString().compareTo( oc.toString() );
// }
// return ((Object)this).getClass().getName().compareTo( o.getClass().getName() );
   }
   
  }

}
