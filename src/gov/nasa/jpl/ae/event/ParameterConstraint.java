/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.CompareUtils;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

/**
 * 
 */
public interface ParameterConstraint extends Constraint, HasParameters {
  /**
   * This helper class provides static methods for making calls on Objects and
   * Maps that may or may not implement HasParameter or contain objects that do.
   * 
   */
  public static class Helper {

    // Below are some of the same functions defined by HasParameters.Helper but
    // allow the caller to pass self (this) without resulting in an infinite
    // loop.
    public static boolean isStale( HasParameters o, boolean deep,
                                   Set<HasParameters> seen ) {
      if ( o != null  ) {
        for ( Parameter< ? > p : o.getParameters( deep, seen ) ) {
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
                                        boolean deep,
                                        Set<HasParameters> seen ) {
      return o.getParameters( deep, seen ).contains( p );
    }
    
    public static boolean isFreeParameter( HasParameters o, Parameter< ? > p,
                                           boolean deep,
                                           Set<HasParameters> seen ) {
      //if ( Utils.seen( o, deep, seen ) ) return false;
      return o.getFreeParameters( deep, seen ).contains( p );
    }

    // End of functions also defined by HaParameters.Helper
    
    public static Set< Variable< ? > > getVariables( HasParameters o,
                                                     boolean deep,
                                                     Set<HasParameters> seen ) {
      //if ( !Utils.seen( o, deep, seen ) ) return Utils.getEmptySet();
      Set< Variable< ? > > s = new HashSet< Variable< ? > >();
      s.addAll( o.getParameters( deep, seen ) );
      return s;
    }
    
    public static < T > boolean pickValue( Constraint c, Variable< T > v ) {
      // TODO Auto-generated method stub
      //assert false;
      if ( v == null ) return false;
      return v.pickValue();
      //return false;
    }

    public static < T > boolean restrictDomain( Constraint c, Variable< T > v ) {
      // TODO Auto-generated method stub
      //assert false;
      return false;
    }

    public static < T > boolean isFree( HasParameters o, Variable< T > v,
                                        boolean deep, Set<HasParameters> seen ) {
      //if ( Utils.seen( o, deep, seen ) ) return true; // REVIEW -- Is true ok for "don't know" case?
      if ( v instanceof Parameter<?> ) {
        Set< Parameter< ? > > params = o.getFreeParameters(deep, seen);
        if ( params == null ) {
          return false;
        }
        return params.contains( v );
      }
      return true; // REVIEW -- Is true ok for "don't know" case?
    }
    
    public static < T > boolean isDependent( HasParameters o, Variable< T > v,
                                             boolean deep, Set<HasParameters> seen ) {
      //if ( Utils.seen( o, deep, seen ) ) return true; // REVIEW -- Is true ok for "don't know" case?
      if ( v instanceof Parameter ) {
        if ( !o.hasParameter( (Parameter< ? >)v, deep, seen ) ) {
          return true;  // REVIEW -- Is true ok for "don't know" case?
        }
      }
      if ( o.getFreeParameters( true, seen ).contains( v ) ) {
        return false;
      }
      return true; // REVIEW -- Is true ok for "don't know" case?
    }
    
    public static void setFreeVariables( HasParameters o,
                                         Set< Variable< ? > > freeVariables,
                                         boolean deep, Set<HasParameters> seen) {
      //if ( Utils.seen( o, deep, seen ) ) return;
      Set< Parameter< ? > > freeParams = o.getFreeParameters( deep, seen );
      if ( freeParams == null ) {
        freeParams = new HashSet< Parameter< ? > >();
        o.setFreeParameters( freeParams, deep, seen );
      }
      for ( Variable< ? > v : freeVariables ) {
        if ( v instanceof Parameter< ? > ) {
          freeParams.add( (Parameter< ? >)v );
        }
      }
    }

    public static Set< Variable< ? > > getFreeVariables( HasParameters o,
                                                         boolean deep,
                                                         Set<HasParameters> seen ) {
      Pair< Boolean, Set< HasParameters > > pair = Utils.seen( o, deep, seen );
      if ( pair.first ) return Utils.getEmptySet();
      seen = pair.second;
      Set< Variable< ? > > s = null;
      Set< Parameter< ? > > oSet = o.getFreeParameters( deep, seen );
      if ( oSet != null ) {
        s = new HashSet< Variable< ? > >();
        s.addAll( oSet );
      } else {
        s = Utils.getEmptySet();
      }
      return s;
    }

   public static int compareTo( Object o, Constraint c ) {
     int compare = CompareUtils.compare( o, c, true, true );
     //if ( compare != 0 ) return compare;
     return compare;
   }
   
  }

}
