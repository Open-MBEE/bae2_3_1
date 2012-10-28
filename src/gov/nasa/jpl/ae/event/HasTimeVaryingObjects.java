package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public interface HasTimeVaryingObjects {
  public Set< TimeVarying< ? > > getTimeVaryingObjects( boolean deep,
                                                        Set<HasTimeVaryingObjects> seen );

  /**
   * This helper class provides static methods for making calls on Objects and
   * Collections (from other objects) that may or may not implement
   * HasTimeVaryingObjects or contain objects that do.
   */
  public static class Helper {

    // WARNING! Do not call from o.getTimeVaryingObjects() -- infinite loop
    public static Set< TimeVarying< ? > >
        getTimeVaryingObjects( Object o, boolean deep,
                               Set< HasTimeVaryingObjects > seen ) {
      if ( o == null ) return Utils.getEmptySet();
      if ( !( o instanceof TimeVarying )
           && !( o instanceof HasTimeVaryingObjects ) ) {
        if ( o instanceof Object[] ) {
          return getTimeVaryingObjects( (Object[])o, deep, seen );
        } else if ( o instanceof Map ) {
          return getTimeVaryingObjects( (Map< ?, ? >)o, deep, seen );
        } else if ( o instanceof Collection ) {
          return getTimeVaryingObjects( (Collection< ? >)o, deep, seen );
        } else if ( o instanceof Pair ) {
          return getTimeVaryingObjects( (Pair< ?, ? >)o, deep, seen );
        }
      }
      Set< TimeVarying< ? > > set = new TreeSet< TimeVarying< ? > >();
      if ( o instanceof TimeVarying< ? > ) {
        set.add( (TimeVarying< ? >)o );
      }
      if ( o instanceof HasTimeVaryingObjects ) {
        // if ( Utils.seen( (HasTimeVaryingObjects)o, deep, seen ) )
        // return Utils.getEmptySet();
        Set< TimeVarying< ? > > oSet =
            ( (HasTimeVaryingObjects)o ).getTimeVaryingObjects( deep, seen );
        set = Utils.addAll( set, oSet );
      }
      if ( o instanceof Parameter ) {
        set = Utils.addAll( set, getTimeVaryingObjects( ( (Parameter)o ).getValueNoPropagate(),
                                           deep, seen ) );
      }
      return set;
    }

    public static < K, V > Set< TimeVarying< ? > >
        getTimeVaryingObjects( Map< K, V > map, boolean deep,
                               Set< HasTimeVaryingObjects > seen ) {
      Set< TimeVarying< ? > > set = new TreeSet< TimeVarying< ? > >();
      for ( Map.Entry< K, V > me : map.entrySet() ) {
        set.addAll( getTimeVaryingObjects( me.getKey(), deep, seen ) );
        set.addAll( getTimeVaryingObjects( me.getValue(), deep, seen ) );
      }
      return set;
    }

    public static < T > Set< TimeVarying< ? > >
        getTimeVaryingObjects( Collection< T > c, boolean deep,
                               Set< HasTimeVaryingObjects > seen ) {
      Set< TimeVarying< ? > > set = new TreeSet< TimeVarying< ? > >();
      for ( T t : c ) {
        set = Utils.addAll( set, getTimeVaryingObjects( t, deep, (Set<HasTimeVaryingObjects>)seen ) );
      }
      return set;
    }

    public static Set< TimeVarying< ? > >
        getTimeVaryingObjects( Object[] c, boolean deep,
                               Set< HasTimeVaryingObjects > seen ) {
      Set< TimeVarying< ? > > set = new TreeSet< TimeVarying< ? > >();
      for ( Object t : c ) {
        set = Utils.addAll( set, getTimeVaryingObjects( t, deep, seen ) );
      }
      return set;
    }

    // static implementations on Pair

    public static < T1, T2 > Set< TimeVarying< ? > >
        getTimeVaryingObjects( Pair< T1, T2 > p, boolean deep,
                               Set< HasTimeVaryingObjects > seen ) {
      Set< TimeVarying< ? > > set = new TreeSet< TimeVarying< ? > >();
      set = Utils.addAll( set, getTimeVaryingObjects( p.first, deep, seen ) );
      set = Utils.addAll( set, getTimeVaryingObjects( p.second, deep, seen ) );
      return set;
    }

  }

}
