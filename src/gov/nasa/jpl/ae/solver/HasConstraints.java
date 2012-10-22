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
 * 
 */
public interface HasConstraints {
  public Collection< Constraint > getConstraints( boolean deep,
                                                  Set< HasConstraints > seen );

  /**
   * This helper class provides static methods for making calls on Objects and
   * Collections (from other objects) that may or may not implement HasConstraint
   * or contain objects that do.
   */
  public static class Helper {
    
    // WARNING! Do not call from o.getConstraints() -- infinite loop
    public static Set< Constraint > getConstraints( Object o, 
                                                    boolean deep,
                                                    Set< HasConstraints > seen) {
      if ( o == null ) return Utils.getEmptySet();
      if ( !( o instanceof Constraint ) && !( o instanceof HasConstraints ) ) {
        if ( o instanceof Object[] ) {
          return getConstraints( (Object[])o, deep, seen );
        } else if ( o instanceof Map ) {
          return getConstraints( (Map< ?, ? >)o, deep, seen );
        } else if ( o instanceof Collection ) {
          return getConstraints( (Collection< ? >)o, deep, seen );
        } else if ( o instanceof Pair ) {
          return getConstraints( (Pair< ?, ? >)o, deep, seen );
        }
      }

      Set< Constraint > set = new HashSet< Constraint >();
      if ( o instanceof Constraint ) {
        set.add( (Constraint)o );
      }
      if ( o != null && o instanceof HasConstraints ) {
//        if ( Utils.seen( (HasConstraints)o, deep, seen ) )
//          return Utils.getEmptySet();
        Collection< Constraint > oSet = ((HasConstraints)o).getConstraints( deep, seen );
        set.addAll( oSet );
      }
      if ( o instanceof Parameter ) {
        set.addAll( getConstraints(((Parameter)o).getValueNoPropagate(), deep, seen ) );
      }
      return set;
    }
    
    public static < K, V > Set< Constraint > getConstraints( Map< K, V > map,
                                                             boolean deep,
                                                             Set< HasConstraints > seen ) {
      Set< Constraint > set = new HashSet< Constraint >();
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        set.addAll( getConstraints( me.getKey(), deep, seen ) );
        set.addAll( getConstraints( me.getValue(), deep, seen ) );
      }
      return set;
    }
    
    public static < T > Set< Constraint > getConstraints( Collection< T > c,
                                                          boolean deep,
                                                          Set< HasConstraints > seen ) {
      Set< Constraint > set = new HashSet< Constraint >();
      for ( T t : c ) {
        set.addAll( getConstraints( t, deep, seen ) );
      }
      return set;
    }
    
    public static Set< Constraint > getConstraints( Object[] c,
                                                       boolean deep,
                                                       Set< HasConstraints > seen ) {
      Set< Constraint > set = new HashSet< Constraint >();
      for ( Object t : c ) {
        set.addAll( getConstraints( t, deep, seen ) );
      }
      return set;
    }
  
    // static implementations on Pair
    
    public static < T1, T2 > Set< Constraint > getConstraints( Pair< T1, T2 > p,
                                                                  boolean deep,
                                                                  Set< HasConstraints > seen ) {
      Set< Constraint > set = new HashSet< Constraint >();
      set.addAll( getConstraints( p.first, deep, seen ) );
      set.addAll( getConstraints( p.second, deep, seen ) );
      return set;
    }
    

  }


}
