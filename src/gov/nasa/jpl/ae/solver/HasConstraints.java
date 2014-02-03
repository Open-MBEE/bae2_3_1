/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.HasId;
import gov.nasa.jpl.mbee.util.Utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 */
public interface HasConstraints extends HasId {
  public Collection< Constraint > getConstraints( boolean deep,
                                                  Set< HasConstraints > seen );
  public long getNumberOfResolvedConstraints(boolean deep, Set< HasConstraints > seen);
  public long getNumberOfUnresolvedConstraints(boolean deep, Set< HasConstraints > seen);
  public long getNumberOfConstraints(boolean deep, Set< HasConstraints > seen);
  //public Iterator<Constraint> iterator();
  public CollectionTree getConstraintCollection(boolean deep, Set< HasConstraints > seen);

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
        set = Utils.addAll( set, getConstraints(((Parameter)o).getValueNoPropagate(), deep, seen ) );
      }
      return set;
    }
    
    public static < K, V > Set< Constraint > getConstraints( Map< K, V > map,
                                                             boolean deep,
                                                             Set< HasConstraints > seen ) {
      Set< Constraint > set = new HashSet< Constraint >();
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        set = Utils.addAll( set, getConstraints( me.getKey(), deep, seen ) );
        set = Utils.addAll( set, getConstraints( me.getValue(), deep, seen ) );
      }
      return set;
    }
    
    public static < T > Set< Constraint > getConstraints( Collection< T > c,
                                                          boolean deep,
                                                          Set< HasConstraints > seen ) {
      Set< Constraint > set = new HashSet< Constraint >();
      for ( T t : c ) {
        set = Utils.addAll( set, getConstraints( t, deep, seen ) );
      }
      return set;
    }
    
    public static Set< Constraint > getConstraints( Object[] c,
                                                       boolean deep,
                                                       Set< HasConstraints > seen ) {
      Set< Constraint > set = new HashSet< Constraint >();
      for ( Object t : c ) {
        set = Utils.addAll( set, getConstraints( t, deep, seen ) );
      }
      return set;
    }
  
    // static implementations on Pair
    
    public static < T1, T2 > Set< Constraint > getConstraints( Pair< T1, T2 > p,
                                                                  boolean deep,
                                                                  Set< HasConstraints > seen ) {
      Set< Constraint > set = new HashSet< Constraint >();
      set = Utils.addAll( set, getConstraints( p.first, deep, seen ) );
      set = Utils.addAll( set, getConstraints( p.second, deep, seen ) );
      return set;
    }
    
    // getNumberOfResolvedConstraints
    
    public static long getNumberOfResolvedConstraints( Object o, 
                                   boolean deep,
                                   Set< HasConstraints > seen) {
      if ( o == null ) return 0;
      if ( o instanceof HasConstraints ) {
        return ( (HasConstraints)o ).getNumberOfResolvedConstraints( deep, seen );
      } else if ( o instanceof Object[] ) {
        return getNumberOfResolvedConstraints( (Object[])o, deep, seen );
      } else if ( o instanceof Map ) {
        return getNumberOfResolvedConstraints( (Map< ?, ? >)o, deep, seen );
      } else if ( o instanceof Collection ) {
        return getNumberOfResolvedConstraints( (Collection< ? >)o, deep, seen );
      } else if ( o instanceof Pair ) {
        return getNumberOfResolvedConstraints( (Pair< ?, ? >)o, deep, seen );
      }
      return 0;
    }
    
    public static < K, V > long getNumberOfResolvedConstraints( Map< K, V > map,
                                            boolean deep,
                                            Set< HasConstraints > seen ) {
      long num = 0;
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        num += getNumberOfResolvedConstraints( me.getKey(), deep, seen );
        num += getNumberOfResolvedConstraints( me.getValue(), deep, seen );
      }
      return num;
    }
    
    public static < T > long getNumberOfResolvedConstraints( Collection< T > c,
                                         boolean deep,
                                         Set< HasConstraints > seen ) {
      long num = 0;
      for ( T t : c ) {
        num += getNumberOfResolvedConstraints( t, deep, seen );
      }
      return num;
    }
    
    public static long getNumberOfResolvedConstraints( Object[] c,
                                   boolean deep,
                                   Set< HasConstraints > seen ) {
      long num = 0;
      for ( Object t : c ) {
        num += getNumberOfResolvedConstraints( t, deep, seen );
      }
      return num;
    }
  
    // static implementations on Pair
    
    public static < T1, T2 > long getNumberOfResolvedConstraints( Pair< T1, T2 > p,
                                              boolean deep,
                                              Set< HasConstraints > seen ) {
      long num = 0;
      num += getNumberOfResolvedConstraints( p.first, deep, seen );
      num += getNumberOfResolvedConstraints( p.second, deep, seen );
      return num;
    }    

    // getNumberOfUnresolvedConstraints
    
    public static long getNumberOfUnresolvedConstraints( Object o, 
                                   boolean deep,
                                   Set< HasConstraints > seen) {
      if ( o == null ) return 0;
      if ( o instanceof HasConstraints ) {
        return ( (HasConstraints)o ).getNumberOfUnresolvedConstraints( deep, seen );
      } else if ( o instanceof Object[] ) {
        return getNumberOfUnresolvedConstraints( (Object[])o, deep, seen );
      } else if ( o instanceof Map ) {
        return getNumberOfUnresolvedConstraints( (Map< ?, ? >)o, deep, seen );
      } else if ( o instanceof Collection ) {
        return getNumberOfUnresolvedConstraints( (Collection< ? >)o, deep, seen );
      } else if ( o instanceof Pair ) {
        return getNumberOfUnresolvedConstraints( (Pair< ?, ? >)o, deep, seen );
      }
      return 0;
    }
    
    public static < K, V > long getNumberOfUnresolvedConstraints( Map< K, V > map,
                                            boolean deep,
                                            Set< HasConstraints > seen ) {
      long num = 0;
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        num += getNumberOfUnresolvedConstraints( me.getKey(), deep, seen );
        num += getNumberOfUnresolvedConstraints( me.getValue(), deep, seen );
      }
      return num;
    }
    
    public static < T > long getNumberOfUnresolvedConstraints( Collection< T > c,
                                         boolean deep,
                                         Set< HasConstraints > seen ) {
      long num = 0;
      for ( T t : c ) {
        num += getNumberOfUnresolvedConstraints( t, deep, seen );
      }
      return num;
    }
    
    public static long getNumberOfUnresolvedConstraints( Object[] c,
                                   boolean deep,
                                   Set< HasConstraints > seen ) {
      long num = 0;
      for ( Object t : c ) {
        num += getNumberOfUnresolvedConstraints( t, deep, seen );
      }
      return num;
    }
  
    // static implementations on Pair
    
    public static < T1, T2 > long getNumberOfUnresolvedConstraints( Pair< T1, T2 > p,
                                              boolean deep,
                                              Set< HasConstraints > seen ) {
      long num = 0;
      num += getNumberOfUnresolvedConstraints( p.first, deep, seen );
      num += getNumberOfUnresolvedConstraints( p.second, deep, seen );
      return num;
    }    

    // getNumberOfConstraints
    
    public static long getNumberOfConstraints( Object o, 
                                   boolean deep,
                                   Set< HasConstraints > seen) {
      if ( o == null ) return 0;
      if ( o instanceof HasConstraints ) {
        return ( (HasConstraints)o ).getNumberOfConstraints( deep, seen );
      } else if ( o instanceof Object[] ) {
        return getNumberOfConstraints( (Object[])o, deep, seen );
      } else if ( o instanceof Map ) {
        return getNumberOfConstraints( (Map< ?, ? >)o, deep, seen );
      } else if ( o instanceof Collection ) {
        return getNumberOfConstraints( (Collection< ? >)o, deep, seen );
      } else if ( o instanceof Pair ) {
        return getNumberOfConstraints( (Pair< ?, ? >)o, deep, seen );
      }
      return 0;
    }
    
    public static < K, V > long getNumberOfConstraints( Map< K, V > map,
                                            boolean deep,
                                            Set< HasConstraints > seen ) {
      long num = 0;
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        num += getNumberOfConstraints( me.getKey(), deep, seen );
        num += getNumberOfConstraints( me.getValue(), deep, seen );
      }
      return num;
    }
    
    public static < T > long getNumberOfConstraints( Collection< T > c,
                                         boolean deep,
                                         Set< HasConstraints > seen ) {
      long num = 0;
      for ( T t : c ) {
        num += getNumberOfConstraints( t, deep, seen );
      }
      return num;
    }
    
    public static long getNumberOfConstraints( Object[] c,
                                   boolean deep,
                                   Set< HasConstraints > seen ) {
      long num = 0;
      for ( Object t : c ) {
        num += getNumberOfConstraints( t, deep, seen );
      }
      return num;
    }
  
    // static implementations on Pair
    
    public static < T1, T2 > long getNumberOfConstraints( Pair< T1, T2 > p,
                                              boolean deep,
                                              Set< HasConstraints > seen ) {
      long num = 0;
      num += getNumberOfConstraints( p.first, deep, seen );
      num += getNumberOfConstraints( p.second, deep, seen );
      return num;
    }    

  }


}
