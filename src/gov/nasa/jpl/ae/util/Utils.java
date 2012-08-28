/**
 * 
 */
package gov.nasa.jpl.ae.util;

import gov.nasa.jpl.ae.event.Event;
import gov.nasa.jpl.ae.event.Pair;
import japa.parser.ast.body.MethodDeclaration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;

/**
 * @author bclement
 *
 */
public class Utils {

  public static String toString( Object[] arr ) {
    StringBuffer sb = new StringBuffer();
    sb.append( "[" );
    for ( int i = 0; i < arr.length; ++i ) {//Object o : arr ) {
      if ( i > 0 ) sb.append( "," );
      if ( arr[i] == null ) {
        sb.append("null");
      } else {
        sb.append( arr[i].toString() );
      }
    }
    sb.append( "]" );
    return sb.toString();
  }
  
  // Translate a string to integer.  Return null if not an integer. 
  public static Integer toInteger( String s ) {
    Integer i = null;
    try {
      i = Integer.parseInt( s );
    } catch ( NumberFormatException e ) {
      // leave i = null
    }
    return i;
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
    if ( Utils.errorOnNull( "Error! Called Utils.put() with null argument!",
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
    if ( Utils.errorOnNull( "Error! Called Utils.get() with null argument!",
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
    if ( Utils.errorOnNull( "Error! Called Utils.put() with null argument!",
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
    if ( Utils.errorOnNull( "Error! Called Utils.get() with null argument!",
                            map, t1, t2, t3 ) ) {
      return null;
    }
    Map< T2, Map< T3, T4 > > innerMap = map.get( t1 );
    if ( innerMap != null ) {
      return get( innerMap, t2, t3 );
    }
    return null;
  }

  // Throws and catches an exception if any of the input objects are null.
  public static boolean errorOnNull( String msg, Object... a ) {
    try {
      for ( Object o : a ) {
        if ( o == null ) {
          throw new Exception();
        } 
      }
    } catch ( Exception e ) {
      System.err.println( msg );
      e.printStackTrace();
      return true;
    }
    return false;
  }

  public static < T extends Comparable< T > > int compareSets( SortedSet< T > s1,
                                                               SortedSet< T > s2 ) {
    Iterator< T > i1 = s1.iterator();
    Iterator< T > i2 = s2.iterator();
    int compare = 0;
    while ( i1.hasNext() && i2.hasNext() ) {
      T t1 = i1.next();
      T t2 = i2.next();
      compare = t1.compareTo( t2 );
      if ( compare != 0 ) {
        return compare;
      }
    }
    if ( i1.hasNext() ) return 1;
    if ( i2.hasNext() ) return -1;
    return 0;
  }

  public static Class< ? > tryClassForName( String className ) {
    Class< ? > classForName = null;
    try {
      classForName = Class.forName( className );
    } catch ( Exception e ) {
      // ignore
    }
    return classForName;
  }

  public static Class< ? > getClassForSimpleName( String className ) {
    Class< ? > classForName = tryClassForName( className );
    if ( classForName != null ) return classForName;
    int pos = className.indexOf( '<' );
    String strippedClassName = null;
    if ( pos >= 0 ){
      strippedClassName = className.substring( 0, pos );
      classForName = tryClassForName( strippedClassName );
      if ( classForName != null ) return classForName;
    }
    List<String> FQNs = getFullyQualifiedNames( className );
    if ( FQNs.isEmpty() && pos >= 0 ) {
      FQNs = getFullyQualifiedNames( strippedClassName );
    }
    if ( !FQNs.isEmpty() ) {
      for ( String fqn : FQNs ) {
        classForName = tryClassForName( fqn );
        if ( classForName != null ) return classForName;
      }
    }
    return classForName;
  }
  
  public static Collection<String> getPackages() {
    Set<String> packages = new HashSet<String>();
    for (Package aPackage : Package.getPackages()) {
        packages.add(aPackage.getName());
    }
    return packages;
  }
  
  public static List<String> getFullyQualifiedNames(String simpleClassOrInterfaceName) {
    Collection<String> packages = null;
    packages = getPackages();

    List<String> fqns = new ArrayList<String>();
    for (String aPackage : packages) {
        try {
            String fqn = aPackage + "." + simpleClassOrInterfaceName;
            Class.forName(fqn);
            fqns.add(fqn);
        } catch (Exception e) {
            // Ignore
        }
    }
    return fqns;
  }
  
  public static Constructor< ? > getConstructorForArgs( String className,
                                                        Object[] args ) {
    Class< ? > classForName = getClassForSimpleName( className );
    if ( classForName == null ) {
      System.err.println( "Couldn't find the class " + className
                          + " to get constructor with args=" + toString( args ) );
      return null;
    }
    return getConstructorForArgs( classForName, args );
  }

  public static Constructor< ? > getConstructorForArgs( Class< ? > cls,
                                                        Object[] args ) {
    Debug.outln( "getConstructorForArgs( " + cls.getName() + ", "
                 + toString( args ) );
    //Method matchingMethod = null;
    boolean gotOkNumArgs = false;
    int mostMatchingArgs = 0;
    boolean allArgsMatched = false;
    Constructor< ? > ctor = null;
    for ( Constructor< ? > aCtor : cls.getConstructors() ) {
        int numMatching = 0;
        boolean okNumArgs =
            ( aCtor.getParameterTypes().length == args.length )
                || ( aCtor.isVarArgs() 
                     && ( aCtor.getParameterTypes().length < args.length
                          || aCtor.getParameterTypes().length == 1 ) );
        if ( !okNumArgs ) continue;
        for ( int i = 0; i < Math.min( aCtor.getParameterTypes().length,
                                       args.length ); ++i ) {
          if ( args[ i ] == null ) continue;
          if ( ((Class<?>)aCtor.getParameterTypes()[ i ]).isAssignableFrom( args[ i ].getClass() ) ) {
            ++numMatching;
          }
        }
        if ( ( ctor == null ) || !gotOkNumArgs
             || ( numMatching > mostMatchingArgs ) ) {
          ctor = aCtor;
          gotOkNumArgs = okNumArgs;
          mostMatchingArgs = numMatching;
          allArgsMatched = ( numMatching >= aCtor.getParameterTypes().length );
        }
      }
    if ( ctor != null && !allArgsMatched ) {
      System.err.println( "constructor returned (" + ctor
                          + ") does not match all args: " + toString( args ) );
    } else if ( ctor == null ) {
      System.err.println( "constructor " + cls.getSimpleName() + toString( args )
                          + " not found" );
    }
    return ctor;
  }

  public static Pair< Constructor< ? >, Object[] >
      getConstructorForArgs( Class< ? > eventClass, Object[] arguments,
                             Object enclosingInstance ) {
    boolean nonStaticInnerClass = Utils.isInnerClass( eventClass );
    Debug.outln( eventClass.getName() + ": nonStaticInnerClass = " + nonStaticInnerClass );
    Object newArgs[] = arguments;
    if ( nonStaticInnerClass ) {
      newArgs = new Object[ arguments.length + 1 ];
      assert enclosingInstance != null;
      newArgs[ 0 ] = enclosingInstance;
      for ( int i = 0; i < arguments.length; ++i ) {
        newArgs[ i + 1 ] = arguments[ i ];
      }
    }
    Constructor< ? > constructor =
        Utils.getConstructorForArgs( eventClass, newArgs );
    return new Pair< Constructor< ? >, Object[] >(constructor, newArgs );
  }

  public static boolean isInnerClass( Class< ? > eventClass ) {
    return ( !Modifier.isStatic( eventClass.getModifiers() )
             && eventClass.getEnclosingClass() != null );
  }

  public static Method getMethodForArgs( String className, String callName,
                                         Object... args ) {
    Class< ? > classForName = getClassForSimpleName( className );
    if ( classForName == null ) {
      System.err.println( "Couldn't find the class " + className
                          + " for method " + callName + toString( args ) );
      return null;
    }
    return getMethodForArgs( classForName, callName, args );
  }

  public static Method getMethodForArgs( Class< ? > cls, String callName,
                                         Object... args ) {
    Method matchingMethod = null;
    boolean gotOkNumArgs = false;
    int mostMatchingArgs = 0;
    boolean allArgsMatched = false;
    Method[] methods = cls.getMethods();
    for ( Method m : methods ) {
      if ( m.getName().equals( callName ) ) {
        int numMatching = 0;
        boolean okNumArgs =
            ( m.getParameterTypes().length == args.length )
              || ( m.isVarArgs()
                   && ( m.getParameterTypes().length < args.length 
                        || m.getParameterTypes().length == 1 ) );
        if ( !okNumArgs ) continue;
        for ( int i = 0; i < Math.min( m.getParameterTypes().length,
                                       args.length ); ++i ) {
          if ( args[ i ] == null ) continue;
          if ( m.getParameterTypes()[ i ].isAssignableFrom( args[ i ].getClass() ) ) {
            ++numMatching;
          }
        }
        if ( ( matchingMethod == null ) || !gotOkNumArgs
             || ( numMatching > mostMatchingArgs ) ) {
          matchingMethod = m;
          gotOkNumArgs = okNumArgs;
          mostMatchingArgs = numMatching;
          allArgsMatched = ( numMatching >= m.getParameterTypes().length );
        }
      }
    }
    if ( matchingMethod != null && !allArgsMatched ) {
      System.err.println( "method returned (" + matchingMethod
                          + ") does not match all args: " + toString( args ) );
    } else if ( matchingMethod == null ) {
      System.err.println( "method " + callName + toString( args )
                          + " not found for " + cls.getSimpleName() );
    }
    return matchingMethod;
  }    
  
}
