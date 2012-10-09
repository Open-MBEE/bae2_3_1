/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.util.Pair;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
      Set< Constraint > set = new TreeSet< Constraint >();
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
      Set< Constraint > set = new TreeSet< Constraint >();
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        set.addAll( getConstraints( me.getKey(), deep, seen ) );
        set.addAll( getConstraints( me.getValue(), deep, seen ) );
      }
      return set;
    }
    
    public static < T > Set< Constraint > getConstraints( Collection< T > c,
                                                          boolean deep,
                                                          Set< HasConstraints > seen ) {
      Set< Constraint > set = new TreeSet< Constraint >();
      for ( T t : c ) {
        set.addAll( getConstraints( t, deep, seen ) );
      }
      return set;
    }
    
    public static Set< Constraint > getConstraints( Object[] c,
                                                       boolean deep,
                                                       Set< HasConstraints > seen ) {
      Set< Constraint > set = new TreeSet< Constraint >();
      for ( Object t : c ) {
        set.addAll( getConstraints( t, deep, seen ) );
      }
      return set;
    }
  
    // static implementations on Pair
    
    public static < T1, T2 > Set< Constraint > getConstraints( Pair< T1, T2 > p,
                                                                  boolean deep,
                                                                  Set< HasConstraints > seen ) {
      Set< Constraint > set = new TreeSet< Constraint >();
      set.addAll( getConstraints( p.first, deep, seen ) );
      set.addAll( getConstraints( p.second, deep, seen ) );
      return set;
    }
    

  }


}
