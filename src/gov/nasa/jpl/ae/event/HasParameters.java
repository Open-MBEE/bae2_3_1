package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.HasId;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

public interface HasParameters extends LazyUpdate, HasId<Integer>, Deconstructable,
                                       MoreToString {
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
  public boolean substitute( Parameter< ? > p1, Object exp,
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
                                   Set< HasParameters > seen,
                                   boolean checkIfLazyUpdate ) {
      if ( o == null ) return false;
      if ( !( o instanceof LazyUpdate )
           && !( o instanceof HasParameters ) ) {
        if ( o instanceof Object[] ) {
          return isStale( (Object[])o, deep, seen );
        } else if ( o instanceof Map ) {
          return isStale( (Map< ?, ? >)o, deep, seen, false );
        } else if ( o instanceof Collection ) {
          return isStale( (Collection< ? >)o, deep, seen, false );
        } else if ( o instanceof Pair ) {
          return isStale( (Pair< ?, ? >)o, deep, seen, false );
        }
      }
      if ( checkIfLazyUpdate && o instanceof LazyUpdate ) {
        return ((LazyUpdate)o).isStale();  // the potential infinite loop
      }
      if ( o instanceof HasParameters ) {
        //if ( Utils.seen( (HasParameters)o, deep, seen ) ) return false;
        for ( Parameter< ? > p : ((HasParameters)o).getParameters( deep, seen ) ) {
          if ( p.isStale() ) return true;
        }
      }
      return false;
    }

    // WARNING! don't call this from o.setStale() -- infinite loop
    public static void setStale( Object o, boolean staleness ) {
      if ( o == null ) return;
      if ( o instanceof LazyUpdate ) {
        ((LazyUpdate)o).setStale( staleness );  // the potential infinite loop
//      } else {
//        if ( o instanceof Object[] ) {
//          setStale( (Object[])o, staleness );
//        } else if ( o instanceof Map ) {
//          setStale( (Map< ?, ? >)o, staleness );
//        } else if ( o instanceof Collection ) {
//          setStale( (Collection< ? >)o, staleness );
//        } else if ( o instanceof Pair ) {
//          setStale( (Pair< ?, ? >)o, staleness );
//        }
      }
    }

    // WARNING! don't call this from o.hasParameter() -- infinite loop
    public static boolean hasParameter( Object o, Parameter< ? > p,
                                        boolean deep, Set< HasParameters > seen,
                                        boolean checkIfHasParameters ) {
      if ( o == p ) return true;
      if ( o == null ) return false;
      if ( checkIfHasParameters && o instanceof HasParameters ) {
        //if ( Utils.seen( (HasParameters)o, deep, seen ) ) return false;
        if ( ((HasParameters)o).hasParameter( p, deep, seen ) ) {
          return true;
        }
      } else {
        if ( o instanceof Object[] ) {
          return hasParameter( (Object[])o, p, deep, seen );
        } else if ( o instanceof Map ) {
          return hasParameter( (Map< ?, ? >)o, p, deep, seen, false );
        } else if ( o instanceof Collection ) {
          return hasParameter( (Collection< ? >)o, p, deep, seen, false );
        } else if ( o instanceof Pair ) {
          return hasParameter( (Pair< ?, ? >)o, p, deep, seen, false );
        }
      }
      return false;
    }

    static int dbgCt = 0;

    // WARNING! Do not call from o.getParameters() -- infinite loop
    public static Set< Parameter< ? > > getParameters( Object o,
                                                       boolean deep,
                                                       Set< HasParameters > seen,
                                                       boolean checkIfHasParameters ) {
      if ( o == null ) return Utils.getEmptySet();
      Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
      if ( o instanceof Parameter ) {
        set.add( (Parameter< ? >)o );
        Object value = ( (Parameter< ? >)o ).getValueNoPropagate();
        if ( deep || value instanceof Parameter ) {
//          if ( Debug.isOn() ) {
//            if ( ++dbgCt % 100 == 0 ) {
//              Exception exception = new Exception();
//              if ( exception.getStackTrace().length > 400 ) {
//                System.out.print( "" ); // breakpoint here
//              }
//            }
//          }
          set = Utils.addAll( set, getParameters( value, deep, seen, true ) );
        }
      }
      if ( checkIfHasParameters && o instanceof HasParameters ) {
        set = Utils.addAll( set, ((HasParameters)o).getParameters( deep, seen ) );
      } else {
        if ( o instanceof Object[] ) {
          return getParameters( (Object[])o, deep, seen );
        } else if ( o instanceof Map ) {
          return getParameters( (Map< ?, ? >)o, deep, seen, false );
        } else if ( o instanceof Collection ) {
          return getParameters( (Collection< ? >)o, deep, seen, false );
        } else if ( o instanceof Pair ) {
          return getParameters( (Pair< ?, ? >)o, deep, seen, false );
        }
      }
      return set;
    }

    // WARNING! Do not call from o.getFreeParameters() -- infinite loop
    public static Set< Parameter< ? > > getFreeParameters( Object o,
                                                           boolean deep,
                                                           Set< HasParameters > seen,
                                                           boolean checkIfHasParameters ) {
      if ( o == null ) return Utils.getEmptySet();
      Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
      if ( checkIfHasParameters && o instanceof HasParameters ) {
//        if ( Utils.seen( (HasParameters)o, deep, seen ) )
//          return Utils.getEmptySet();
        for ( Parameter< ? > p : ( (HasParameters)o ).getParameters( deep, seen ) ) {
          if ( ( (HasParameters)o ).isFreeParameter( p, true, seen ) ) {
            set.add( p );
          }
        }
//      } else {
//        if ( o instanceof Object[] ) {
//          return getFreeParameters( (Object[])o, deep, seen );
//        } else if ( o instanceof Map ) {
//          return getFreeParameters( (Map< ?, ? >)o, deep, seen, true );
//        } else if ( o instanceof Collection ) {
//          return getFreeParameters( (Collection< ? >)o, deep, seen );
//        } else if ( o instanceof Pair ) {
//          return getFreeParameters( (Pair< ?, ? >)o, deep, seen );
//        }
      }
      return set;
    }

    // WARNING! Do not call from o.isFreeParameter() -- infinite loop
    public static boolean isFreeParameter( Object o, Parameter< ? > p,
                                           boolean deep,
                                           Set< HasParameters > seen,
                                           boolean checkIfHasParameters ) {
      if ( deep ) {
        if ( p.getOwner() != null )
          return p.getOwner().isFreeParameter( p, deep, seen );
      }
      if ( o == null ) return false;
      if ( checkIfHasParameters && o instanceof HasParameters ) {
//        if ( Utils.seen( (HasParameters)o, deep, seen ) ) return false;
        if ( ((HasParameters)o).isFreeParameter( p, deep, seen ) ) {
          return true;
        }
      } else {
        if ( o instanceof Object[] ) {
          return isFreeParameter( (Object[])o, p, deep, seen );
        } else if ( o instanceof Map ) {
          return isFreeParameter( (Map< ?, ? >)o, p, deep, seen, false );
        } else if ( o instanceof Collection ) {
          return isFreeParameter( (Collection< ? >)o, p, deep, seen, false );
        } else if ( o instanceof Pair ) {
          return isFreeParameter( (Pair< ?, ? >)o, p, deep, seen, false );
        }
      }
      return false;
    }

    // WARNING! Do not call from o.isFreeParameter() -- infinite loop
    public static boolean substitute( Object o,
                                      Parameter< ? > p1,
                                      Object p2,
                                      boolean deep,
                                      Set< HasParameters > seen,
                                      boolean checkIfHasParameters ) {
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      if ( o == null ) return false;
      if ( checkIfHasParameters && o instanceof HasParameters ) {
//        if ( Utils.seen( (HasParameters)o, deep, seen ) ) return false;
        if ( p2 instanceof Parameter ) {
          return ((HasParameters)o).substitute( p1, (Parameter< ? >)p2, deep, seen );
        }
        return ((HasParameters)o).substitute( p1, p2, deep, seen );
      } else {
        if ( o instanceof Object[] ) {
          return substitute( (Object[])o, p1, p2, deep, seen );
        } else if ( o instanceof Map ) {
          return substitute( (Map< ?, ? >)o, p1, p2, deep, seen, false );
        } else if ( o instanceof Collection ) {
          return substitute( (Collection< ? >)o, p1, p2, deep, seen, false );
        } else if ( o instanceof Pair ) {
          return substitute( (Pair< ?, ? >)o, p1, p2, deep, seen, false );
        }
      }
      return false;
    }

    // static implementations on Map

    public static < K, V > boolean isStale( Map< K, V > map,
                                            boolean deep,
                                            Set< HasParameters > seen,
                                            boolean checkIfHasParameters ) {
      if ( checkIfHasParameters && map instanceof LazyUpdate ) {
        return ((LazyUpdate)map).isStale();  // the potential infinite loop
      }
     for ( Map.Entry< K, V > me : map.entrySet() ) {
        if ( isStale( me.getKey(), deep, seen, true ) ) return true;
        if ( isStale( me.getValue(), deep, seen, true ) ) return true;
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
                                                 Set< HasParameters > seen,
                                                 boolean checkIfHasParameters) {
      if ( checkIfHasParameters && map instanceof HasParameters ) {
        return ((HasParameters)map ).hasParameter( parameter, deep, seen );
      }
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        if ( hasParameter( me.getKey(), parameter, deep, seen, true ) ||
             hasParameter( me.getValue(), parameter, deep, seen, true ) ) {
          return true;
        }
      }
      return false;
    }

    public static < K, V > Set< Parameter< ? > > getParameters( Map< K, V > map,
                                                                boolean deep,
                                                                Set< HasParameters > seen,
                                                                boolean checkIfHasParameters ) {
      Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
      if ( checkIfHasParameters && map instanceof HasParameters ) {
        set.addAll( ((HasParameters)map ).getParameters( deep, seen ) );
      } else {
        for ( Map.Entry< K, V > me : map.entrySet() ) {
          set = Utils.addAll( set, getParameters( me.getKey(), deep, seen, true ) );
          set = Utils.addAll( set, getParameters( me.getValue(), deep, seen, true ) );
        }
      }
      return set;
    }

    public static < K, V > boolean isFreeParameter( Map< K, V > map,
                                                    Parameter< ? > parameter,
                                                    boolean deep,
                                                    Set< HasParameters > seen,
                                                    boolean checkIfHasParameters ) {
      if ( checkIfHasParameters && map instanceof HasParameters ) {
        return ((HasParameters)map).isFreeParameter( parameter, deep, seen );
      }
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        if ( isFreeParameter( me.getKey(), parameter, deep, seen, true ) ||
             isFreeParameter( me.getValue(), parameter, deep, seen, true ) ) {
          return true;
        }
      }
      return false;
    }


    public static < K, V > boolean substitute( Map< K, V > map,
                                               Parameter< ? > p1,
                                               Object p2,
                                               boolean deep,
                                               Set< HasParameters > seen,
                                               boolean checkIfHasParameters ) {
      if ( checkIfHasParameters && map instanceof HasParameters ) {
        if ( p2 instanceof Parameter ) {
          return ((HasParameters)map).substitute( p1, (Parameter<?>)p2, deep, seen );
        }
        return ((HasParameters)map).substitute( p1, p2, deep, seen );
      }
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
          subbed = true;
        } else {
          if ( deep && substitute( e.getValue(), p1, p2, deep, seen, true ) ) {
            subbed = true;
          }
        }
      }
      return subbed;
    }

    // static implementations on Collection

    public static < T > boolean isStale( Collection< T > c, boolean deep,
                                         Set< HasParameters > seen,
                                         boolean checkIfLazyUpdate ) {
      if ( checkIfLazyUpdate && c instanceof LazyUpdate ) {
        return ((LazyUpdate)c).isStale();  // potential infinite loop
      }
      for ( T t : c ) {
        if ( isStale( t, deep, seen, true ) ) return true;
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
                                              Set< HasParameters > seen,
                                              boolean checkIfHasParameters ) {
      if ( checkIfHasParameters && c instanceof HasParameters ) {
        return ((HasParameters)c ).hasParameter( parameter, deep, seen );
      }
      for ( T t : c ) {
        if ( hasParameter( t, parameter, deep, seen, deep ) ) {
          return true;
        }
      }
      return false;
    }

    public static < T > Set< Parameter< ? > > getParameters( Collection< T > c,
                                                             boolean deep,
                                                             Set< HasParameters > seen,
                                                             boolean checkIfHasParameters ) {
      Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
      if ( checkIfHasParameters && c instanceof HasParameters ) {
        set = Utils.addAll( set, ((HasParameters)c).getParameters( deep, seen ) );
      } else {
        for ( T t : c ) {
          set = Utils.addAll( set, getParameters( t, deep, seen, true ) );
        }
      }
      return set;
    }

    // getFreeParameters( Object ) suffices for Map

    public static < T > boolean isFreeParameter( Collection< T > c,
                                                 Parameter< ? > parameter,
                                                 boolean deep,
                                                 Set< HasParameters > seen,
                                                 boolean checkIfHasParameters ) {
      if ( checkIfHasParameters && c instanceof HasParameters ) {
        return ((HasParameters)c).isFreeParameter( parameter, deep, seen );
      }
      for ( T t : c ) {
        if ( isFreeParameter( t, parameter, deep, seen, true ) ) {
          return true;
        }
      }
      return false;
    }


    public static < T > boolean substitute( Collection< T > c,
                                            Parameter< ? > p1,
                                            Object p2,
                                            boolean deep,
                                            Set< HasParameters > seen ) {
      if ( c instanceof HasParameters ) {
        if ( p2 instanceof Parameter ) {
          return ((HasParameters)c).substitute( p1, (Parameter<?>)p2, deep, seen );
        }
        return ((HasParameters)c).substitute( p1, p2, deep, seen );
      }
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      boolean subbed = false;
      for ( T t : c ) {
        if ( deep && substitute( t, p1, p2, deep, seen, true ) ) {
          subbed = true;
        }
      }
      return subbed;
    }

    // static implementations on array

    public static boolean isStale( Object[] c, boolean deep,
                                   Set< HasParameters > seen ) {
      for ( Object t : c ) {
        if ( isStale( t, deep, seen, true ) ) return true;
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
        if ( hasParameter( t, parameter, deep, seen, true ) ) {
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
        set = Utils.addAll( set, getParameters( t, deep, seen, true ) );
      }
      return set;
    }

    // getFreeParameters( Object ) suffices for Map

    public static boolean isFreeParameter( Object[] c,
                                           Parameter< ? > parameter,
                                           boolean deep,
                                           Set< HasParameters > seen ) {
      for ( Object t : c ) {
        if ( isFreeParameter( t, parameter, deep, seen, true ) ) {
          return true;
        }
      }
      return false;
    }
    
    public static boolean subParamsEqual( Object o, Parameter<?> p1 ) {
      if ( o == p1 ) return true;
      if ( o == null || p1 == null ) return false;
      // return o.equals( p1 );  // Can't use Parameter.equals() since it compares values.
      if ( o instanceof Parameter ) {
        if ( CompareUtils.compare( ((Parameter<?>)o).name, p1.name ) == 0 ) return true;
      }
      return false;
      //return Expression.valuesEqual( o, p1, Parameter.class, false, false );  // This is also too loose.
    }

    public static boolean substitute( Object[] c, Parameter< ? > p1,
                                      Object p2,
                                      boolean deep,
                                      Set< HasParameters > seen ) {
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      boolean subbed = false;
      for ( int i = 0; i < c.length; ++i ) {
        if ( subParamsEqual( c[i], p1 ) ) { //c[i] == p1 ) {
          c[i] = p2;
          subbed = true;
        } else {
          if ( substitute( c[i], p1, p2, deep, seen, true ) ) {
            subbed = true;
          }
        }
      }
      return subbed;
    }

    public static < V, VS extends V > boolean
        substitute( List< V > o, Parameter< ? > p1, VS p2, boolean deep,
                    Set< HasParameters > seen, boolean checkIfHasParameters ) {
      if ( o == null ) return false;
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;

      if ( checkIfHasParameters && o instanceof HasParameters ) {
        if ( p2 instanceof Parameter ) {
          return ((HasParameters)o).substitute( p1, (Parameter<?>)p2, deep, seen );
        }
        return ((HasParameters)o).substitute( p1, p2, deep, seen );
      }

      boolean subbed = false;
      for ( int i = 0; i < o.size(); ++i ) {
        V v = o.get( i );
        if ( subParamsEqual( v, p1 ) ) { //v == p1 ) {
          o.set( i, p2 );
          subbed = true;
        } else {
          if ( deep && v != null && substitute( v, p1, p2, deep, seen, true ) ) {
            subbed = true;
          }
        }
      }
      return subbed;
    }

    public static < V, VS extends V > boolean
      substitute( Set< V > o, Parameter< ? > p1, VS p2, boolean deep,
                Set< HasParameters > seen, boolean checkIfHasParameters ) {
      return substituteImpl( o, p1, p2, deep, seen, checkIfHasParameters );
    }
    
    public static < V, VS extends V > boolean
        substituteImpl( Collection< V > o, Parameter< ? > p1, VS p2, boolean deep,
                        Set< HasParameters > seen, boolean checkIfHasParameters ) {
      if ( o == null ) return false;
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      boolean subbed = false;
      
      if ( checkIfHasParameters && o instanceof HasParameters ) {
        if ( p2 instanceof Parameter ) {
          return ((HasParameters)o).substitute( p1, (Parameter<?>)p2, deep, seen );
        }
        return ((HasParameters)o).substitute( p1, p2, deep, seen );
      }

      // Try substituting members.
      //boolean contains = false;
      ArrayList< V > matches = new ArrayList< V >();
      for ( V v : o ) {
        if ( subParamsEqual( v, p1 ) ) {
          matches.add( v );
          //contains = true;
//          break;
        }
      }
      if ( !matches.isEmpty() ) {
        subbed = true;
        o.removeAll( matches );
        o.add( p2 );
      }
//      if ( o.contains( p1 ) ) {
//        o.remove( p1 );
//        o.add( p2 );
//        subbed = true;
//      }

      if ( deep ) {
        // This is painful but necessary, at least for some Collections, like TreeSet.
        for ( Object v : o.toArray() ) {
          if ( v != null && ( !(p2 instanceof Parameter) ||
                              !subParamsEqual( v, (Parameter<?>)p2 ) ) ) {
            o.remove( v );
            if ( substitute( v, p1, p2, deep, seen, true ) ) {
              subbed = true;
            }
            o.add( (V)v );
          }
        }
      }
      return subbed;
    }

    public static < V, VS extends V > boolean
    substitute( Collection< V > o, Parameter< ? > p1, VS p2, boolean deep,
                    Set< HasParameters > seen, boolean checkIfHasParameters ) {
      if ( o instanceof List ) {
        return substitute( (List<?>)o, p1, p2, deep, seen, checkIfHasParameters );
      }
      if ( o instanceof Set ) {
        return substitute( (Set<?>)o, p1, p2, deep, seen, checkIfHasParameters );
      }
      return substituteImpl( o, p1, p2, deep, seen, checkIfHasParameters );
    }
    
    // static implementations on Pair

    public static < T1, T2 > boolean isStale( Pair< T1, T2 > p, boolean deep,
                                              Set< HasParameters > seen,
                                              boolean checkIfLazyUpdate ) {
      if ( checkIfLazyUpdate && p instanceof LazyUpdate ) {
        return ((LazyUpdate)p).isStale();  // the potential infinite loop
      }
      if ( isStale( p.first, deep, seen, true ) ) return true;
      if ( isStale( p.second, deep, seen, true ) ) return true;
      return false;
    }

    public static < T1, T2 > boolean hasParameter( Pair< T1, T2 > p,
                                                   Parameter< ? > parameter,
                                                   boolean deep,
                                                   Set< HasParameters > seen,
                                                   boolean checkIfHasParameters ) {
      if ( checkIfHasParameters && p instanceof HasParameters ) {
        return ((HasParameters)p).hasParameter( parameter, deep, seen );
      }
      if ( hasParameter( p.first, parameter, deep, seen, true ) ) return true;
      if ( hasParameter( p.second, parameter, deep, seen, true ) ) return true;
      return false;
    }

    public static < T1, T2 > Set< Parameter< ? > > getParameters( Pair< T1, T2 > p,
                                                                  boolean deep,
                                                                  Set< HasParameters > seen,
                                                                  boolean checkIfHasParameters ) {
      Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
      if ( checkIfHasParameters && p instanceof HasParameters ) {
        set.addAll( ((HasParameters)p ).getParameters( deep, seen ) );
      } else {
        set = Utils.addAll( set, getParameters( p.first, deep, seen, true ) );
        set = Utils.addAll( set, getParameters( p.second, deep, seen, true ) );
      }
      return set;
    }

    // getFreeParameters( Object ) suffices for Map

    public static < T1, T2 > boolean isFreeParameter( Pair< T1, T2 > p,
                                                      Parameter< ? > parameter,
                                                      boolean deep,
                                                      Set< HasParameters > seen,
                                                      boolean checkIfHasParameters ) {
      if ( checkIfHasParameters && p instanceof HasParameters ) {
        return ((HasParameters)p).isFreeParameter( parameter, deep, seen );
      }
      if ( isFreeParameter( p.first, parameter, deep, seen, true ) ) return true;
      if ( isFreeParameter( p.second, parameter, deep, seen, true ) ) return true;
      return false;
    }


    public static < T1, T2 > boolean substitute( Pair< T1, T2 > p,
                                                 Parameter< ? > p1,
                                                 Object p2,
                                                 boolean deep,
                                                 Set< HasParameters > seen,
                                                 boolean checkIfHasParameters ) {
      if ( checkIfHasParameters && p instanceof HasParameters ) {
        if ( p2 instanceof Parameter ) {
          return ((HasParameters)p).substitute( p1, (Parameter<?>)p2, deep, seen );
        }
        return ((HasParameters)p).substitute( p1, p2, deep, seen );
      }
      if ( p1 == null ) return false;
      if ( p1 == p2 ) return true;
      boolean subbed = false;
      if ( substitute( p.first, p1, p2, deep, seen, true ) ) subbed = true;
      if ( substitute( p.second, p1, p2, deep, seen, true ) ) subbed = true;
      return subbed;
    }

    public static boolean isSatisfied( Object o, boolean deep,
                                       Set< Satisfiable > seen ) {
      boolean sat = true;
      // REVIEW -- can getParameters be shallow?
      for ( Parameter< ? > p : getParameters( o, deep, null, deep ) ) {
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
      for ( Parameter< ? > p : getParameters( o, deep, null, deep ) ) {
        if ( !p.satisfy(deep, seen) ) {
          sat = false;
        }
      }
      return sat;
    }

  }
}
