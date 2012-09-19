/**
 * 
 */
package gov.nasa.jpl.ae.util;

import gov.nasa.jpl.ae.event.Expression;
import japa.parser.ast.body.Parameter;

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
    if (arr == null) return "null";
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
    return errorOnNull( true, msg, a );
  }
  public static boolean errorOnNull( boolean stackTrace, String msg,
                                     Object... a ) {
    try {
      for ( Object o : a ) {
        if ( o == null ) {
          throw new Exception();
        } 
      }
    } catch ( Exception e ) {
      System.err.println( msg );
      if ( stackTrace ) {
        e.printStackTrace();
      }
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

  public static boolean isSubclassOf( Class<?> c1, Class<?> c2 ) {
    try {
      c1.asSubclass( c2 );
    } catch( ClassCastException e ) {
      return false;
    }
    return true;
  }
  
  public static Class< ? > tryClassForName( String className, 
                                            boolean initialize ) {
    return tryClassForName( className, initialize, null );
  }
  
  public static Class< ? > tryClassForName( String className, 
                                            boolean initialize,
                                            ClassLoader loader ) {
    Class< ? > classForName = null;
    try {
      if ( loader == null ) {
        classForName = Class.forName( className );
      } else {
        classForName = Class.forName( className, initialize, loader );//, initialize, Utils.class.getClassLoader() );
      }
    } catch ( Exception e ) {
      // ignore
    }
    Debug.outln( "tryClassForName( " + className + " ) = " + classForName );
    return classForName;
  }

  public static Class<?> getClassForName( String className,
                                          String preferredPackage,
                                          boolean initialize ) {
    List< Class<?>> classList = getClassesForName( className, initialize );
    if ( !isNullOrEmpty( classList ) ) {
      for ( Class< ? > c : classList ) {
        if ( c.getPackage().getName().equals( preferredPackage ) ) {
          Debug.errln("Found preferred package! " + preferredPackage );
          return c;
        }
      }
    }
    return getClassFromClasses( classList );
  }
//  public static Class<?> getClassForName( String className,
//                                          boolean initialize ) {    
//    return getClassFromClasses( getClassesForName( className, initialize ) );
//  }
  
  public static List< Class< ? >> getClassesForName( String className,
                                            boolean initialize ) {
//                                            ClassLoader loader,
//                                            Package[] packages) {
    List< Class< ? > > classList = new ArrayList< Class< ? > >();
    if ( Utils.isNullOrEmpty( className ) ) return null;
//    ClassLoader loader = Utils.class.getClassLoader();
//    if ( loader != null ) {
//      for ( String pkgName : packagesToForceLoad ) {
//        try {
//          loader.getResources( pkgName);
//          loader.getResources( pkgName + File.separator + "*" );
//          loader.loadClass("");
//        } catch ( IOException e ) {
//          // ignore
//        } catch ( ClassNotFoundException e ) {
//          // ignore
//        }
//      }
//    }
    Debug.outln( "getClassForName( " + className + " )" );
    Class< ? > classForName = tryClassForName( className, initialize );//, loader );
    if ( classForName != null ) classList.add( classForName );
    String strippedClassName = noParameterName( className );
    Debug.outln( "getClassForName( " + className + " ): strippedClassName = "
                 + strippedClassName );
    boolean strippedWorthTrying = false;
    if ( !Utils.isNullOrEmpty( strippedClassName ) ) {
      strippedWorthTrying = !strippedClassName.equals( className ); 
      if ( strippedWorthTrying ) {
        classForName = tryClassForName( strippedClassName, initialize );//, loader );
        if ( classForName != null ) classList.add( classForName );
      }
    }
    List<String> FQNs = getFullyQualifiedNames( className );//, packages );
    Debug.outln( "getClassForName( " + className + " ): fully qualified names = "
        + FQNs );
    if ( FQNs.isEmpty() && strippedWorthTrying ) {
      FQNs = getFullyQualifiedNames( strippedClassName );//, packages );
    }
    if ( !FQNs.isEmpty() ) {
      for ( String fqn : FQNs ) {
        classForName = tryClassForName( fqn, initialize );//, loader );
        if ( classForName != null ) classList.add( classForName );
      }
    }
    return classList;
  }
  
  public static Collection<String> getPackageStrings(Package[] packages) {
    Set<String> packageStrings = new HashSet<String>();
    if ( isNullOrEmpty( packages ) ) {
      packages = Package.getPackages();
    }
    for (Package aPackage : packages ) {
      packageStrings.add(aPackage.getName());
    }
    return packageStrings;
  }
  
  public static String simpleName( String longName ) {
    int pos = longName.lastIndexOf( '.' );
    return longName.substring( pos+1 ); // pos is -1 if no '.'
  }

  public static String noParameterName( String longName ) {
    if ( longName == null ) return null;
    int pos = longName.indexOf( '<' );
    if ( pos == -1 ) {
      return longName;
    }
    String noParamName = longName.substring( 0, pos );
    return noParamName;
  }

  public static String parameterPartOfName( String longName ) {
    int pos1 = longName.indexOf( '<' );
    if ( pos1 == -1 ) {
      return null;
    }
    int pos2 = longName.lastIndexOf( '>' );
    assert( pos2 >= 0 );
    if ( pos2 == -1 ) return null;
    String paramPart = longName.substring( pos1, pos2+1 );
    return paramPart;
  }

  public static List<String> getFullyQualifiedNames(String simpleClassOrInterfaceName ) {
    return getFullyQualifiedNames( simpleClassOrInterfaceName, null );
  }
  public static List<String> getFullyQualifiedNames(String simpleClassOrInterfaceName, Package[] packages) {
    Collection<String> packageStrings = getPackageStrings( packages );

    List<String> fqns = new ArrayList<String>();
    Debug.outln( "getFullyQualifiedNames( " + simpleClassOrInterfaceName
                 + " ): packages = " + packageStrings );
    for (String aPackage : packageStrings) {
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
                                                        Object[] args,
                                                        String preferredPackage ) {
    Class< ? > classForName = getClassForName( className, preferredPackage, false );
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
        //if ( !okNumArgs ) continue;
        for ( int i = 0; i < Math.min( aCtor.getParameterTypes().length,
                                       args.length ); ++i ) {
          if ( args[ i ] == null ) continue;
          if ( ((Class<?>)aCtor.getParameterTypes()[ i ]).isAssignableFrom( args[ i ].getClass() ) ) {
            ++numMatching;
          }
        }
        if ( ( ctor == null ) || ( okNumArgs && !gotOkNumArgs )
             || ( ( okNumArgs && !gotOkNumArgs ) 
                  && ( numMatching > mostMatchingArgs ) ) ) {
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

  public static Class<?> getClassFromClasses( List< Class< ? > > classList ) {
    if ( isNullOrEmpty( classList ) ) {
      return null;
    }
    if ( classList.size() > 1 ) {
      System.err.println( "Error! Got multiple class candidates for constructor! " + classList );
    }
    return classList.get( 0 );
  }
  
  public static Method getMethodForArgs( String className,
                                         String preferredPackage,
                                         String callName,
                                         Object... args ) {
    Class< ? > classForName = getClassForName( className, preferredPackage, false );
    if ( errorOnNull( "Couldn't find the class " + className + " for method "
                      + callName + ( args == null ? "" : toString( args ) ),
                      classForName ) ) {
      return null;
    }
    return getMethodForArgs( classForName, callName, args );
  }

  public static Method getMethodForArgs( Class< ? > cls, String callName,
                                         Object... args ) {
    Debug.outln( "getMethodForArgs( cls=" + cls.getName() + ", callName="
        + callName + ", args=" + toString( args ) + " )" );
    Class< ? > argTypes[] = new Class< ? >[ args.length ];
    for ( int i = 0; i < args.length; ++i ) {
      if ( args[ i ] == null ) {
        argTypes[ i ] = null;
      } else {
        argTypes[ i ] = args[ i ].getClass();
      }
    }
    return getMethodForArgTypes( cls, callName, argTypes );
  }

  public static Method getMethodForArgTypes( String className,
                                             String preferredPackage,
                                             String callName,
                                             Class<?>... argTypes ) {
    Class< ? > classForName = getClassForName( className, preferredPackage, false );
    if ( errorOnNull( "Couldn't find the class " + className + " for method "
                          + callName
                          + ( argTypes == null ? "" : toString( argTypes ) ),
                      classForName ) ) {
      return null;
    }
    return getMethodForArgTypes( classForName, callName, argTypes );
  }

  public static Method getMethodForArgTypes( Class< ? > cls, String callName,
                                             Class<?>... argTypes ) {
//    return getMethodForArgTypes( cls, callName, argTypes, 10.0, 2.0, null );
//  }
//  public static Method getMethodForArgTypes( Class< ? > cls, String callName,
//                                             Class<?>[] argTypes,
//                                             double numArgsCost,
//                                             double argMismatchCost,
//                                             Map< Class< ? >, Map< Class< ? >, Double > > transformCost ) {
    Debug.outln( "getMethodForArgTypes( cls=" + cls.getName() + ", callName="
                 + callName + ", argTypes=" + toString( argTypes ) + " )" );
    Method matchingMethod = null;
    boolean gotOkNumArgs = false;
    int mostMatchingArgs = 0;
    int mostDeps = 0;
    boolean allArgsMatched = false;
    double bestScore = Double.MAX_VALUE;
    Method[] methods = null;
    Debug.outln( "calling getMethods() on class " + cls.getName() );
    try {
      methods = cls.getMethods();
    } catch ( Exception e ) {
      System.err.println( "Got exception calling " + cls.getName()
                          + ".getMethod(): " + e.getMessage() );
    }
    Debug.outln( "--> got methods: " + toString( methods ) );
    if ( methods != null ) {
    for ( Method m : methods ) {
      if ( m.getName().equals( callName ) ) {
//        double score = numArgsCost + argMismatchCost * argTypes.length;
        int numMatching = 0;
        int numDeps = 0;
        boolean okNumArgs =
            ( m.getParameterTypes().length == argTypes.length )
              || ( m.isVarArgs()
                   && ( m.getParameterTypes().length < argTypes.length 
                        || m.getParameterTypes().length == 1 ) );
        Debug.outln( "okNumArgs = " + okNumArgs );
//        if ( okNumArgs ) score -= numArgsCost;
        for ( int i = 0; i < Math.min( m.getParameterTypes().length,
                                       argTypes.length ); ++i ) {
          if ( argTypes[ i ] == null ) {
            Debug.outln( "null arg[ " + i + " ]" );
            continue;
          }
          if ( m.getParameterTypes()[ i ].isAssignableFrom( argTypes[ i ] ) ) {
              Debug.outln( "m.getParameterTypes()[ " + i + " ]="
                           + m.getParameterTypes()[ i ] + " matches args[ " + i
                           + " ].getClass()=" + argTypes[ i ] );
            ++numMatching;
          } else if ( Parameter.class.isAssignableFrom( m.getParameterTypes()[ i ] ) &&
                      Expression.class.isAssignableFrom( argTypes[ i ] ) ) {
              Debug.outln( "m.getParameterTypes()[ " + i + " ]="
                           + m.getParameterTypes()[ i ]
                           + " could be made dependent on args[ " + i
                           + " ].getClass()=" + argTypes[ i ] );
            ++numDeps;
          } else {
              Debug.outln( "m.getParameterTypes()[ " + i + " ]="
                           + m.getParameterTypes()[ i ]
                           + " does not match args[ " + i + " ].getClass()="
                           + argTypes[ i ] );
          }
        }
        if ( ( matchingMethod == null )
             || ( !gotOkNumArgs && okNumArgs )
             || ( ( gotOkNumArgs == okNumArgs )
                  && ( ( numMatching > mostMatchingArgs )
                       || ( ( numMatching == mostMatchingArgs ) 
                            && ( numDeps > mostDeps ) ) ) ) ) {
          matchingMethod = m;
          gotOkNumArgs = okNumArgs;
          mostMatchingArgs = numMatching;
          mostDeps = numDeps;
          allArgsMatched = ( numMatching >= m.getParameterTypes().length );
            Debug.outln( "new match " + m + ", mostMatchingArgs="
                         + mostMatchingArgs + ",  allArgsMatched = "
                         + allArgsMatched + " = numMatching(" + numMatching
                         + ") >= m.getParameterTypes().length("
                         + m.getParameterTypes().length + "), numDeps=" + numDeps );
        }
      }
    }
    }
    if ( matchingMethod != null && !allArgsMatched ) {
      System.err.println( "method returned (" + matchingMethod
                          + ") only matches " + mostMatchingArgs
                          + " args: " + toString( argTypes ) );
    } else if ( matchingMethod == null ) {
      System.err.println( "method " + callName + toString( argTypes )
                          + " not found for " + cls.getSimpleName() );
    }
    return matchingMethod;
  }    
  
}
