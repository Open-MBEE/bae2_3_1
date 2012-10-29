/**
 * 
 */
package gov.nasa.jpl.ae.util;

import gov.nasa.jpl.ae.event.ElaborationRule;
import gov.nasa.jpl.ae.event.Event;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

//import org.apache.commons.lang.StringUtils;

/**
 * A set of miscellaneous utility functions.
 */
public class Utils {
  
  // static members
  
  public static ClassLoader loader = null;
  
  // empty collection constants
  @SuppressWarnings( "rawtypes" )
  public static final List<?> emptyList = new ArrayList( 0 );
  @SuppressWarnings( "unchecked" )
  public static <T> List<T> getEmptyList() {
    return (List< T >)emptyList;
  }  
  @SuppressWarnings( "rawtypes" )
  public static final Set<?> emptySet = new TreeSet();
  @SuppressWarnings( "unchecked" )
  public static <T> Set<T> getEmptySet() {
    return (Set< T >)emptySet;
  }  
  @SuppressWarnings( "rawtypes" )
  public static final Map<?,?> emptyMap = new TreeMap();
  @SuppressWarnings( "unchecked" )
  public static <T1,T2> Map<T1,T2> getEmptyMap() {
    return (Map< T1, T2 >)emptyMap;
  }

  public static String toString( Object[] arr ) {
    return toString( arr, true );
  }
  public static String toString( Object[] arr, boolean square ) {
    if (arr == null) return "null";
    StringBuffer sb = new StringBuffer();
    if ( square ) {
      sb.append( "[" );
    } else {
      sb.append( "(" );
    }
    for ( int i = 0; i < arr.length; ++i ) {//Object o : arr ) {
      if ( i > 0 ) sb.append( "," );
      if ( arr[i] == null ) {
        sb.append("null");
      } else {
        sb.append( arr[i].toString() );
      }
    }
    if ( square ) {
      sb.append( "]" );
    } else {
      sb.append( ")" );
    }

    return sb.toString();
  }

  public static int intCompare( int i1, int i2 ) {
    if ( i1 < i2 ) return -1;
    if ( i1 > i2 ) return 1;
    return 0;
  }
  
  // Translate a string to an integer.  Return null if not an integer. 
  /**
   * Translate a string s to an Integer.
   * 
   * @param s
   *          is the string to parse as an Integer
   * @return the integer translation of string s, or return null if s is not an
   *         integer.
   */
  public static Integer toInteger( String s ) {
    Integer i = null;
    try {
      i = Integer.parseInt( s );
    } catch ( NumberFormatException e ) {
      // leave i = null
    }
    return i;
  }

  /**
   * Translate a string s to a Double.
   * 
   * @param s
   *          is the string to parse as a Double
   * @return the double translation of string s, or return null if s is not a
   *         double/integer.
   */
  public static Double toDouble( String s ) {
    Double i = null;
    try {
      i = Double.parseDouble( s );
    } catch ( NumberFormatException e ) {
      // leave i = null
    }
    return i;
  }

  /**
   * Generate a string that repeats/replicates a string a specified number of
   * times.
   * 
   * @param s
   *          is the string to repeat.
   * @param times
   *          is the number of times to repeat the string.
   * @return a concatenation of times instances of s.
   */
  public static String repeat( String s, int times ) {
    StringBuilder sb = new StringBuilder();
    for ( int i=0; i<times; ++i ) {
      sb.append(s);
    }
    return sb.toString();
  }

  public static String spaces( int n ) {
    return repeat( " ", n );
  }
  
  // Check if string has really got something.
  public static boolean isNullOrEmpty( String s ) {
    return ( s == null || s.isEmpty() ||
             s.trim().toLowerCase().equals( "null" ) );
  }

  // Check if array has really got something.
  public static boolean isNullOrEmpty( Object[] s ) {
    return ( s == null || s.length == 0 );
  }
  
  // Check if Collection has really got something.
  public static boolean isNullOrEmpty( Collection< ? > s ) {
    return ( s == null || s.isEmpty() );
  }

  // Check if Map has really got something.
  public static boolean isNullOrEmpty( Map< ?, ? > s ) {
    return ( s == null || s.isEmpty() );
  }

  // generic map<X, map<Y, Z> >.put(x, y, z)
  public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 > T3 put( Map< T1, Map< T2, T3 > > map, T1 t1, T2 t2, T3 t3 ) {
    if ( Debug.errorOnNull( "Error! Called Utils.put() with null argument!",
                            map, t1, t2, t3 ) ) {
      return null;
    }
    Map< T2, T3 > innerMap = map.get( t1 );
    if ( innerMap == null ) {
      innerMap = new TreeMap< T2, T3 >();
      map.put( t1, innerMap );
    }
    return innerMap.put( t2, t3 );
  }

  // generic map<X, map<Y, Z> >.get(x, y) --> z
  public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 > T3 get( Map< T1, Map< T2, T3 > > map, T1 t1, T2 t2 ) {
    if ( Debug.errorOnNull( "Error! Called Utils.get() with null argument!",
                            map, t1, t2 ) ) {
      return null;
    }
    Map< T2, T3 > innerMap = map.get( t1 );
    if ( innerMap != null ) {
      return innerMap.get( t2 );
    }
    return null;
  }

  // generic map< W, map<X, map<Y, Z> >.put(w, x, y, z)
  public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 > T4 put( Map< T1, Map< T2, Map< T3, T4 > > > map, T1 t1, T2 t2, T3 t3, T4 t4 ) {
    if ( Debug.errorOnNull( "Error! Called Utils.put() with null argument!",
                            map, t1, t2, t3, t4 ) ) {
      return null;
    }
    Map< T2, Map< T3, T4 > > innerMap = map.get( t1 );
    if ( innerMap == null ) {
      innerMap = new TreeMap< T2, Map< T3, T4 > >();
      map.put( t1, innerMap );
    }
    return put( innerMap, t2, t3, t4 );
  }

  // generic map< W, map<X, map<Y, Z> >.get(w, x, y) --> z
  public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 > T4 get( Map< T1, Map< T2, Map< T3, T4 > > > map, T1 t1, T2 t2, T3 t3 ) {
    if ( Debug.errorOnNull( "Error! Called Utils.get() with null argument!",
                            map, t1, t2, t3 ) ) {
      return null;
    }
    Map< T2, Map< T3, T4 > > innerMap = map.get( t1 );
    if ( innerMap != null ) {
      return get( innerMap, t2, t3 );
    }
    return null;
  }

//  private static long notSeenCt = 0;
//  private static long seenCt = 0;
  
  /**
   * Manages a "seen" set for avoiding infinite recursion.
   * @param o is the object visited
   * @param recursive is whether infinite recursion is possible 
   * @param seen is the set of objects already visited
   * @return whether the object has already been visited
   */
  public static < T > Pair< Boolean, Set< T > > seen( T o, boolean recursive,
                                                      Set< T > seen ) {
    if ( seen != null && seen.contains( o ) ) {
//      ++seenCt;
      return new Pair< Boolean, Set< T > >( true, seen );
    }
//    ++notSeenCt;
    if ( seen == null && recursive == true ) {
      seen = new HashSet< T >(); // ok to use hash here since we never iterate
                                 // over the contents
    }
    if ( seen != null ) seen.add( o );
    return new Pair< Boolean, Set< T > >( false, seen );
  }
  
  /**
   * Replace the last occurrence of the substring in s with the replacement. 
   * @param s
   * @param replacement
   * @return the result of the replacement
   */
  public static String replaceLast( String s, String substring,
                                    String replacement ) {
    int pos = s.lastIndexOf(substring);
    if ( pos == -1 ) return s;
    return s.substring( 0, pos ) + replacement
           + s.substring( pos + substring.length() );
  }
  
  

  

  

//  public static Constructor< ? >
//      getConstructorForArgTypes( Class< ? > cls, String packageName,
//                                 Class< ? >... argTypes ) {
//    // Pair< Constructor< ? >, Object[] > p =
//    return getConstructorForArgTypes( cls, argTypes );
//    // if ( p == null ) return null;
//    // return p.first;
//  }

  public static <T1, T2> boolean toArrayOfType( T1[] source, T2[] target,
                                                Class< T2 > newType ) {
    boolean succ = true;
    for ( int i=0; i < source.length; ++i ) {
      try {
        target[i] = newType.cast( source[i] );
      } catch ( ClassCastException e ) {
        succ = false;
        target[i] = null;
      }
    }
    return succ;
  }

  public static <T> String join( Collection<T> things, String delim ) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for ( T t : things ) {
      if ( first ) first = false;
      else sb.append( delim );
      sb.append( t );
    }
    return sb.toString();
  }
  public static <T> T[] scramble( T[] array ) {
    for ( int i=0; i < array.length; ++i ) {
      int j = Random.global.nextInt( array.length );
      if ( j != i ) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
      }
    }
    return array;
  }
  public static <T> T[] scramble( Collection< T > collection ) {
    if ( Utils.isNullOrEmpty( collection ) ) return (T[])new Object[]{};
    T[] a = (T[])new Object[collection.size()];
    collection.toArray( a );
    return scramble( a );
  }

  /**
   * @param o
   * @param T
   * @return the number of occurrences of o in the Collection c
   */
  public static < T > int occurrences( T value, Collection< T > c ) {
    if ( c == null ) return 0;
    int ct = 0;
    for ( T o : c ) {
      if ( valuesEqual( o, value ) ) ct++;
    }
    return ct;
  }
  
  /**
   * A potentially more efficient addAll() for Collections.
   * @param coll1
   * @param coll2
   * @return the longer of the two collections after adding the shorter to the longer.  
   */
  public static < T, C extends Collection<T> > C addAll( Collection<T> coll1, Collection<T> coll2 ) {
    if ( coll1.size() < coll2.size() ) {
      coll2.addAll( coll1 );
      return (C)coll2;
    }
    coll1.addAll( coll2 );
    return (C)coll1;
  }
  
  public static String addTimestampToFilename( String fileName ) {
    int pos = fileName.lastIndexOf( '.' );
    String prefix = fileName;
    String suffix = "";
    if ( pos != -1 ) {
      prefix = fileName.substring( 0, pos );
      suffix = fileName.substring( pos );
    }
    String newFileName = prefix + Timepoint.timestampForFile() + suffix;
    return newFileName;
  }

  public static <T1, T2> boolean valuesEqual( T1 v1, T2 v2 ) {
    return v1 == v2 || ( v1 != null && v1.equals( v2 ) );
  }

  public static String toStringNoHash( Object o ) {
//    if ( o == null ) return "null";
//    try {
//      if ( o.getClass().getMethod( "toString", (Class<?>[])null ).getDeclaringClass() == o.getClass() ) {
//        return o.toString();
//      }
//    } catch ( SecurityException e ) {
//      e.printStackTrace();
//    } catch ( NoSuchMethodException e ) {
//      e.printStackTrace();
//    }
    return o.toString().replace( Integer.toHexString(o.hashCode()), "" );
  }
  
}
