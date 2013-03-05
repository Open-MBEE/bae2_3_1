/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author bclement
 *
 */
public interface Satisfiable extends HasId {
  public boolean isSatisfied(boolean deep, Set< Satisfiable > seen);
	public boolean satisfy(boolean deep, Set< Satisfiable > seen);
  /**
   * This helper class provides static methods for making calls on Objects and
   * Collections (from other objects) that may or may not implement Satisfiable
   * or contain objects that do.
   */
	public static class Helper {
	  
    // WARNING! Do not call from o.isSatisfied() -- infinite loop
    public static boolean isSatisfied( Object o, 
                                       boolean deep,
                                       Set< Satisfiable > seen) {
      if ( o == null ) return true;
      if ( o instanceof Satisfiable ) {
        return ( (Satisfiable)o ).isSatisfied( deep, seen );
      } else if ( o instanceof Object[] ) {
        return isSatisfied( (Object[])o, deep, seen );
      } else if ( o instanceof Map ) {
        return isSatisfied( (Map< ?, ? >)o, deep, seen );
      } else if ( o instanceof Collection ) {
        return isSatisfied( (Collection< ? >)o, deep, seen );
      } else if ( o instanceof Pair ) {
        return isSatisfied( (Pair< ?, ? >)o, deep, seen );
      }
      return true;
    }
    
    public static < K, V > boolean isSatisfied( Map< K, V > map,
                                                boolean deep,
                                                Set< Satisfiable > seen ) {
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        if (!isSatisfied( me.getKey(), deep, seen ) ) return false;
        if (!isSatisfied( me.getValue(), deep, seen ) ) return false;
      }
      return true;
    }
    
    public static < T > boolean isSatisfied( Collection< T > c,
                                             boolean deep,
                                             Set< Satisfiable > seen ) {
      for ( T t : c ) {
        if (!isSatisfied( t, deep, seen ) ) return false;
      }
      return true;
    }
    
    public static boolean isSatisfied( Object[] c,
                                       boolean deep,
                                       Set< Satisfiable > seen ) {
      for ( Object t : c ) {
        if (!isSatisfied( t, deep, seen ) ) return false;
      }
      return true;
    }
  
    // static implementations on Pair
    
    public static < T1, T2 > boolean isSatisfied( Pair< T1, T2 > p,
                                                  boolean deep,
                                                  Set< Satisfiable > seen ) {
      if (!isSatisfied( p.first, deep, seen ) ) return false;
      if (!isSatisfied( p.second, deep, seen ) ) return false;
      return true;
    }
    
    public static boolean satisfy( Object o, 
                                   boolean deep,
                                   Set< Satisfiable > seen) {
      if ( o == null ) return true;
      if ( o instanceof Satisfiable ) {
        return ( (Satisfiable)o ).satisfy( deep, seen );
      } else if ( o instanceof Object[] ) {
        return satisfy( (Object[])o, deep, seen );
      } else if ( o instanceof Map ) {
        return satisfy( (Map< ?, ? >)o, deep, seen );
      } else if ( o instanceof Collection ) {
        return satisfy( (Collection< ? >)o, deep, seen );
      } else if ( o instanceof Pair ) {
        return satisfy( (Pair< ?, ? >)o, deep, seen );
      }
      return true;
    }
    
    public static < K, V > boolean satisfy( Map< K, V > map,
                                            boolean deep,
                                            Set< Satisfiable > seen ) {
      boolean succ = true;
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        if (!satisfy( me.getKey(), deep, seen ) ) succ = false;
        if (!satisfy( me.getValue(), deep, seen ) ) succ = false;
      }
      return succ;
    }
    
    public static < T > boolean satisfy( Collection< T > c,
                                         boolean deep,
                                         Set< Satisfiable > seen ) {
      boolean succ = true;
      for ( T t : c ) {
        if (!satisfy( t, deep, seen ) ) succ = false;
      }
      return succ;
    }
    
    public static boolean satisfy( Object[] c,
                                   boolean deep,
                                   Set< Satisfiable > seen ) {
      boolean succ = true;
      for ( Object t : c ) {
        if (!satisfy( t, deep, seen ) ) succ = false;
      }
      return succ;
    }
  
    // static implementations on Pair
    
    public static < T1, T2 > boolean satisfy( Pair< T1, T2 > p,
                                              boolean deep,
                                              Set< Satisfiable > seen ) {
      boolean succ = true;
      if (!satisfy( p.first, deep, seen ) ) succ = false;
      if (!satisfy( p.second, deep, seen ) ) succ = false;
      return succ;
    }    
	
	}
	
}
