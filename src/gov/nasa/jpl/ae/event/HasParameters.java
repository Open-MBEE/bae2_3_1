package gov.nasa.jpl.ae.event;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public interface HasParameters extends LazyUpdate {
  public Set< Parameter< ? > > getParameters( boolean deep );

  public Set< Parameter< ? > > getFreeParameters( boolean deep );

  public void setFreeParameters( Set< Parameter< ? > > freeParams );

  public boolean isFreeParameter( Parameter< ? > p, boolean deep );

  public boolean hasParameter( Parameter< ? > parameter, boolean deep );
  
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2,
                             boolean deep );
  
  /**
   * This helper class provides static methods for making calls on Objects and
   * Collections (from other objects) that may or may not implement HasParameter
   * or contain objects that do.
   * 
   * @author bclement
   * 
   */
  public static class Helper {
    
    // static implementations on Object

    // WARNING! don't call this from o.isStale() -- infinite loop
    public static boolean isStale( Object o, boolean deep ) {
      if ( o instanceof LazyUpdate ) {
        return ((LazyUpdate)o).isStale();  // the potential infinite loop
      }
      if ( o != null && o instanceof HasParameters ) {
        for ( Parameter< ? > p : ((HasParameters)o).getParameters( deep ) ) {
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
                                        boolean deep ) {
      if ( o == p ) return true;
      if ( o != null && o instanceof HasParameters ) {
        if ( ((HasParameters)o).hasParameter( p, deep ) ) {
          return true;
        }
      }
      return false;
    }
    
    // WARNING! Do not call from o.getParameters() -- infinite loop
    public static Set< Parameter< ? > > getParameters( Object o, 
                                                       boolean deep ) {
      Set< Parameter< ? > > set = new TreeSet< Parameter< ? > >();
      if ( o instanceof Parameter ) {
        set.add( (Parameter< ? >)o );
      }
      if ( o != null && o instanceof HasParameters ) {
        set.addAll( ((HasParameters)o).getParameters( deep ) );
      }
      return set;
    }
    
    // WARNING! Do not call from o.getFreeParameters() -- infinite loop
    public static Set< Parameter< ? > > getFreeParameters( Object o, 
                                                           boolean deep ) {
      Set< Parameter< ? > > set = new TreeSet< Parameter< ? > >();
      if ( o != null && o instanceof HasParameters ) {
        for ( Parameter< ? > p : ( (HasParameters)o ).getParameters( deep ) ) {
          if ( ( (HasParameters)o ).isFreeParameter( p, true ) ) {
            set.add( p );
          }
        }
      }
      return set;
    }

    // WARNING! Do not call from o.isFreeParameter() -- infinite loop
    public static boolean isFreeParameter( Object o, Parameter< ? > p,
                                           boolean deep ) {
      if ( deep ) {
        if ( p.getOwner() != null ) return p.getOwner().isFreeParameter( p, deep );
      }
      if ( o != null && o instanceof HasParameters ) {
        if ( ((HasParameters)o).isFreeParameter( p, deep ) ) {
          return true;
        }
      }
      return false;
    }
    
    // WARNING! Do not call from o.isFreeParamter() -- infinite loop
    public static boolean substitute( Object o,
                                      Parameter< ? > p1,
                                      Parameter< ? > p2,
                                      boolean deep ) {
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      if ( o instanceof HasParameters ) {
        return ((HasParameters)o).substitute( p1, p2, deep );
      }
      return false;
    }
    
    // static implementations on Map
    
    public static < K, V > boolean isStale( Map< K, V > map, boolean deep ) {
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        if ( isStale( me.getKey(), deep ) ) return true;
        if ( isStale( me.getValue(), deep ) ) return true;
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
                                                 boolean deep ) {
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        if ( hasParameter( me.getKey(), parameter, deep ) ||
             hasParameter( me.getValue(), parameter, deep ) ) {
          return true;
        }
      }      
      return false;
    }
    
    public static < K, V > Set< Parameter< ? > > getParameters( Map< K, V > map,
                                                                boolean deep ) {
      Set< Parameter< ? > > set = new TreeSet< Parameter< ? > >();
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        set.addAll( HasParameters.Helper.getParameters( me.getKey(), deep ) );
        set.addAll( HasParameters.Helper.getParameters( me.getValue(), deep ) );
      }
      return set;
    }
    
    public static < K, V > boolean isFreeParameter( Map< K, V > map,
                                                    Parameter< ? > parameter,
                                                    boolean deep ) {
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        if ( isFreeParameter( me.getKey(), parameter, deep ) ||
             isFreeParameter( me.getValue(), parameter, deep ) ) {
          return true;
        }
      }      
      return false;
    }
    
    
    public static < K, V > boolean substitute( Map< K, V > map,
                                               Parameter< ? > p1,
                                               Parameter< ? > p2,
                                               boolean deep ) {
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
          if ( substitute( e.getValue(), p1, p2, deep ) ) {
            subbed = true;
          }
        }
      }
      return subbed;
    }

    // static implementations on Collection
    
    public static < T > boolean isStale( Collection< T > c, boolean deep ) {
      for ( T t : c ) {
        if ( isStale( t, deep ) ) return true;
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
                                              boolean deep ) {
      for ( T t : c ) {
        if ( hasParameter( t, parameter, deep ) ) {
          return true;
        }
      }      
      return false;
    }
    
    public static < T > Set< Parameter< ? > > getParameters( Collection< T > c,
                                                             boolean deep ) {
      Set< Parameter< ? > > set = new TreeSet< Parameter< ? > >();
      for ( T t : c ) {
        set.addAll( HasParameters.Helper.getParameters( t, deep ) );
      }
      return set;
    }
    
    // getFreeParameters( Object ) suffices for Map

    public static < T > boolean isFreeParameter( Collection< T > c,
                                                 Parameter< ? > parameter,
                                                 boolean deep ) {
      for ( T t : c ) {
        if ( isFreeParameter( t, parameter, deep ) ) {
          return true;
        }
      }      
      return false;
    }
    
    
    public static < T > boolean substitute( Collection< T > c,
                                            Parameter< ? > p1,
                                            Parameter< ? > p2,
                                            boolean deep ) {
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      boolean subbed = false;
      for ( T t : c ) {
        if ( substitute( t, p1, p2, deep ) ) {
          subbed = true;
        }
      }      
      return subbed;
    }
   
    // static implementations on Pair
    
    public static < T1, T2 > boolean isStale( Pair< T1, T2 > p, boolean deep ) {
      if ( isStale( p.first, deep ) ) return true;
      if ( isStale( p.second, deep ) ) return true;
      return false;
    }
    
    public static < T1, T2 > boolean hasParameter( Pair< T1, T2 > p,
                                                   Parameter< ? > parameter,
                                                   boolean deep ) {
      if ( hasParameter( p.first, parameter, deep ) ) return true;
      if ( hasParameter( p.second, parameter, deep ) ) return true;
      return false;
    }
    
    public static < T1, T2 > Set< Parameter< ? > > getParameters( Pair< T1, T2 > p,
                                                                  boolean deep ) {
      Set< Parameter< ? > > set = new TreeSet< Parameter< ? > >();
      set.addAll( HasParameters.Helper.getParameters( p.first, deep ) );
      set.addAll( HasParameters.Helper.getParameters( p.second, deep ) );
      return set;
    }
    
    // getFreeParameters( Object ) suffices for Map

    public static < T1, T2 > boolean isFreeParameter( Pair< T1, T2 > p,
                                                 Parameter< ? > parameter,
                                                 boolean deep ) {
      if ( isFreeParameter( p.first, parameter, deep ) ) return true;
      if ( isFreeParameter( p.second, parameter, deep ) ) return true;
      return false;
    }
    
    
    public static < T1, T2 > boolean substitute( Pair< T1, T2 > p,
                                            Parameter< ? > p1,
                                            Parameter< ? > p2,
                                            boolean deep ) {
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      boolean subbed = false;
      if ( substitute( p.first, p1, p2, deep ) ) subbed = true;
      if ( substitute( p.second, p1, p2, deep ) ) subbed = true;
      return subbed;
    }
    
  }
}
