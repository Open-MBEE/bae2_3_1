package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public interface HasParameters extends LazyUpdate {
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set< HasParameters > seen );

  public Set< Parameter< ? > > getFreeParameters( boolean deep,
                                                  Set< HasParameters > seen );

  public void setFreeParameters( Set< Parameter< ? > > freeParams, boolean deep,
                                 Set< HasParameters > seen );

  public boolean isFreeParameter( Parameter< ? > p, boolean deep,
                                  Set< HasParameters > seen );

  public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                               Set< HasParameters > seen );
  
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2,
                             boolean deep, Set< HasParameters > seen );
  
  /**
   * This helper class provides static methods for making calls on Objects and
   * Collections (from other objects) that may or may not implement HasParameter
   * or contain objects that do.
   */
  public static class Helper {
    
    // static implementations on Object

    // WARNING! don't call this from o.isStale() -- infinite loop
    public static boolean isStale( Object o, boolean deep,
                                   Set< HasParameters > seen ) {
      if ( o instanceof LazyUpdate ) {
        return ((LazyUpdate)o).isStale();  // the potential infinite loop
      }
      if ( o != null && o instanceof HasParameters ) {
        //if ( Utils.seen( (HasParameters)o, deep, seen ) ) return false;
        for ( Parameter< ? > p : ((HasParameters)o).getParameters( deep, seen ) ) {
          if ( p.isStale() ) return true;
        }
      }
      return false;
    }
    
    // WARNING! don't call this from o.setStale() -- infinite loop
    public static void setStale( Object o, boolean staleness ) {
      if ( o instanceof LazyUpdate ) {
        ((LazyUpdate)o).setStale( staleness );  // the potential infinite loop
      }
    }
    
    // WARNING! don't call this from o.hasParameter() -- infinite loop
    public static boolean hasParameter( Object o, Parameter< ? > p,
                                        boolean deep, Set< HasParameters > seen ) {
      if ( o == p ) return true;
      if ( o != null && o instanceof HasParameters ) {
        //if ( Utils.seen( (HasParameters)o, deep, seen ) ) return false;
        if ( ((HasParameters)o).hasParameter( p, deep, seen ) ) {
          return true;
        }
      }
      return false;
    }
    
    // WARNING! Do not call from o.getParameters() -- infinite loop
    public static Set< Parameter< ? > > getParameters( Object o, 
                                                       boolean deep,
                                                       Set< HasParameters > seen) {
      Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
      if ( o instanceof Parameter ) {
        set.add( (Parameter< ? >)o );
      }
      if ( o != null && o instanceof HasParameters ) {
//        if ( Utils.seen( (HasParameters)o, deep, seen ) )
//          return Utils.getEmptySet();
        set.addAll( ((HasParameters)o).getParameters( deep, seen ) );
      }
      return set;
    }
    
    // WARNING! Do not call from o.getFreeParameters() -- infinite loop
    public static Set< Parameter< ? > > getFreeParameters( Object o, 
                                                           boolean deep,
                                                           Set< HasParameters > seen ) {
      Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
      if ( o != null && o instanceof HasParameters ) {
//        if ( Utils.seen( (HasParameters)o, deep, seen ) )
//          return Utils.getEmptySet();
        for ( Parameter< ? > p : ( (HasParameters)o ).getParameters( deep, seen ) ) {
          if ( ( (HasParameters)o ).isFreeParameter( p, true, seen ) ) {
            set.add( p );
          }
        }
      }
      return set;
    }

    // WARNING! Do not call from o.isFreeParameter() -- infinite loop
    public static boolean isFreeParameter( Object o, Parameter< ? > p,
                                           boolean deep,
                                           Set< HasParameters > seen ) {
      if ( deep ) {
        if ( p.getOwner() != null )
          return p.getOwner().isFreeParameter( p, deep, seen );
      }
      if ( o != null && o instanceof HasParameters ) {
//        if ( Utils.seen( (HasParameters)o, deep, seen ) ) return false;
        if ( ((HasParameters)o).isFreeParameter( p, deep, seen ) ) {
          return true;
        }
      }
      return false;
    }
    
    // WARNING! Do not call from o.isFreeParameter() -- infinite loop
    public static boolean substitute( Object o,
                                      Parameter< ? > p1,
                                      Parameter< ? > p2,
                                      boolean deep,
                                      Set< HasParameters > seen ) {
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      if ( o instanceof HasParameters ) {
//        if ( Utils.seen( (HasParameters)o, deep, seen ) ) return false;
        return ((HasParameters)o).substitute( p1, p2, deep, seen );
      }
      return false;
    }
    
    // static implementations on Map
    
    public static < K, V > boolean isStale( Map< K, V > map,
                                            boolean deep,
                                            Set< HasParameters > seen ) {
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        if ( isStale( me.getKey(), deep, seen ) ) return true;
        if ( isStale( me.getValue(), deep, seen ) ) return true;
      }
      return false;
    }
    
//    // Warning! It should be unusual to set all the keys and values stale!
//    public static < K, V > void setStale( Map< K, V > map, boolean staleness ) {
//      for ( Map.Entry< K, V > me : map.entrySet() ) {
//        setStale( me.getKey(), staleness );
//        setStale( me.getValue(), staleness );
//      }
//    }
    
    public static < K, V > boolean hasParameter( Map< K, V > map,
                                                 Parameter< ? > parameter,
                                                 boolean deep,
                                                 Set< HasParameters > seen ) {
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        if ( hasParameter( me.getKey(), parameter, deep, seen ) ||
             hasParameter( me.getValue(), parameter, deep, seen ) ) {
          return true;
        }
      }      
      return false;
    }
    
    public static < K, V > Set< Parameter< ? > > getParameters( Map< K, V > map,
                                                                boolean deep,
                                                                Set< HasParameters > seen ) {
      Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        set.addAll( getParameters( me.getKey(), deep, seen ) );
        set.addAll( getParameters( me.getValue(), deep, seen ) );
      }
      return set;
    }
    
    public static < K, V > boolean isFreeParameter( Map< K, V > map,
                                                    Parameter< ? > parameter,
                                                    boolean deep,
                                                    Set< HasParameters > seen ) {
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        if ( isFreeParameter( me.getKey(), parameter, deep, seen ) ||
             isFreeParameter( me.getValue(), parameter, deep, seen ) ) {
          return true;
        }
      }      
      return false;
    }
    
    
    public static < K, V > boolean substitute( Map< K, V > map,
                                               Parameter< ? > p1,
                                               Parameter< ? > p2,
                                               boolean deep,
                                               Set< HasParameters > seen ) {
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      boolean subbed = false;
      // Try and safely substitute for p1 as a key.
      // Checking if contained first because the value may be null, and we want
      // to re-insert null in that case.
      if ( map.keySet().contains( p1 ) ) {
        // Now, p1 must be a K and p2 is effectively the same type as p1 since
        // the type parameters are not checked at runtime.
        V value = map.get( p1 );
        map.remove( p1 );
        map.put( (K)p2, value );
        subbed = true;
      }
      // Now try and substitute values.
      for ( Entry< K, V > e : map.entrySet() ) {
        if ( e.getValue() == p1 ) {
          e.setValue( (V)p2 );
        } else {
          if ( substitute( e.getValue(), p1, p2, deep, seen ) ) {
            subbed = true;
          }
        }
      }
      return subbed;
    }

    // static implementations on Collection
    
    public static < T > boolean isStale( Collection< T > c, boolean deep,
                                         Set< HasParameters > seen ) {
      for ( T t : c ) {
        if ( isStale( t, deep, seen ) ) return true;
      }
      return false;
    }
    
//    // Warning! It should be unusual to set all the keys and values stale!
//    public static < K, V > void setStale( Map< K, V > map, boolean staleness ) {
//      for ( Map.Entry< K, V > me : map.entrySet() ) {
//        setStale( me.getKey(), staleness );
//        setStale( me.getValue(), staleness );
//      }
//    }
    
    public static < T > boolean hasParameter( Collection< T > c,
                                              Parameter< ? > parameter,
                                              boolean deep,
                                              Set< HasParameters > seen ) {
      for ( T t : c ) {
        if ( hasParameter( t, parameter, deep, seen ) ) {
          return true;
        }
      }      
      return false;
    }
    
    public static < T > Set< Parameter< ? > > getParameters( Collection< T > c,
                                                             boolean deep,
                                                             Set< HasParameters > seen ) {
      Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
      for ( T t : c ) {
        set.addAll( getParameters( t, deep, seen ) );
      }
      return set;
    }
    
    // getFreeParameters( Object ) suffices for Map

    public static < T > boolean isFreeParameter( Collection< T > c,
                                                 Parameter< ? > parameter,
                                                 boolean deep,
                                                 Set< HasParameters > seen ) {
      for ( T t : c ) {
        if ( isFreeParameter( t, parameter, deep, seen ) ) {
          return true;
        }
      }      
      return false;
    }
    
    
    public static < T > boolean substitute( Collection< T > c,
                                            Parameter< ? > p1,
                                            Parameter< ? > p2,
                                            boolean deep,
                                            Set< HasParameters > seen ) {
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      boolean subbed = false;
      for ( T t : c ) {
        if ( substitute( t, p1, p2, deep, seen ) ) {
          subbed = true;
        }
      }      
      return subbed;
    }
   
    // static implementations on array
    
    public static boolean isStale( Object[] c, boolean deep,
                                   Set< HasParameters > seen ) {
      for ( Object t : c ) {
        if ( isStale( t, deep, seen ) ) return true;
      }
      return false;
    }
    
//    // Warning! It should be unusual to set all the keys and values stale!
//    public static < K, V > void setStale( Map< K, V > map, boolean staleness ) {
//      for ( Map.Entry< K, V > me : map.entrySet() ) {
//        setStale( me.getKey(), staleness );
//        setStale( me.getValue(), staleness );
//      }
//    }
    
    /**
     * @param c
     * @param parameter
     * @param deep
     * @param seen TODO
     * @return
     */
    public static boolean hasParameter( Object[] c,
                                        Parameter< ? > parameter,
                                        boolean deep,
                                        Set< HasParameters > seen ) {
      for ( Object t : c ) {
        if ( hasParameter( t, parameter, deep, seen ) ) {
          return true;
        }
      }      
      return false;
    }
    
    public static Set< Parameter< ? > > getParameters( Object[] c,
                                                       boolean deep,
                                                       Set< HasParameters > seen ) {
      Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
      for ( Object t : c ) {
        set.addAll( getParameters( t, deep, seen ) );
      }
      return set;
    }
    
    // getFreeParameters( Object ) suffices for Map

    public static boolean isFreeParameter( Object[] c,
                                           Parameter< ? > parameter,
                                           boolean deep,
                                           Set< HasParameters > seen ) {
      for ( Object t : c ) {
        if ( isFreeParameter( t, parameter, deep, seen ) ) {
          return true;
        }
      }      
      return false;
    }    
    
    public static boolean substitute( Object[] c, Parameter< ? > p1,
                                      Parameter< ? > p2,
                                      boolean deep,
                                      Set< HasParameters > seen ) {
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      boolean subbed = false;
      for ( Object t : c ) {
        if ( substitute( t, p1, p2, deep, seen ) ) {
          subbed = true;
        }
      }      
      return subbed;
    }
   
    // static implementations on Pair
    
    public static < T1, T2 > boolean isStale( Pair< T1, T2 > p, boolean deep,
                                              Set< HasParameters > seen ) {
      if ( isStale( p.first, deep, seen ) ) return true;
      if ( isStale( p.second, deep, seen ) ) return true;
      return false;
    }
    
    public static < T1, T2 > boolean hasParameter( Pair< T1, T2 > p,
                                                   Parameter< ? > parameter,
                                                   boolean deep,
                                                   Set< HasParameters > seen ) {
      if ( hasParameter( p.first, parameter, deep, seen ) ) return true;
      if ( hasParameter( p.second, parameter, deep, seen ) ) return true;
      return false;
    }
    
    public static < T1, T2 > Set< Parameter< ? > > getParameters( Pair< T1, T2 > p,
                                                                  boolean deep,
                                                                  Set< HasParameters > seen ) {
      Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
      set.addAll( getParameters( p.first, deep, seen ) );
      set.addAll( getParameters( p.second, deep, seen ) );
      return set;
    }
    
    // getFreeParameters( Object ) suffices for Map

    public static < T1, T2 > boolean isFreeParameter( Pair< T1, T2 > p,
                                                 Parameter< ? > parameter,
                                                 boolean deep,
                                                 Set< HasParameters > seen ) {
      if ( isFreeParameter( p.first, parameter, deep, seen ) ) return true;
      if ( isFreeParameter( p.second, parameter, deep, seen ) ) return true;
      return false;
    }
    
    
    public static < T1, T2 > boolean substitute( Pair< T1, T2 > p,
                                            Parameter< ? > p1,
                                            Parameter< ? > p2,
                                            boolean deep,
                                            Set< HasParameters > seen ) {
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      boolean subbed = false;
      if ( substitute( p.first, p1, p2, deep, seen ) ) subbed = true;
      if ( substitute( p.second, p1, p2, deep, seen ) ) subbed = true;
      return subbed;
    }
    
    public static boolean isSatisfied( Object o, boolean deep,
                                       Set< Satisfiable > seen ) {
      boolean sat = true;
      // REVIEW -- can getParameters be shallow?
      for ( Parameter< ? > p : getParameters( o, deep, null ) ) {
        if ( !p.isSatisfied( deep, seen ) ) {
          sat = false;
          break;
        }
      }
      return sat;
    }

    public static boolean satisfy( Object o, boolean deep,
                                   Set< Satisfiable > seen ) {
      boolean sat = true;
      // REVIEW -- can getParameters be shallow?
      for ( Parameter< ? > p : getParameters( o, deep, null ) ) {
        if ( !p.satisfy(deep, seen) ) {
          sat = false;
        }
      }
      return sat;
    }

  }
}
