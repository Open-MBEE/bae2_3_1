/**
 * 
 */
package gov.nasa.jpl.ae.util;

import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.solver.Random;
import japa.parser.ast.body.Parameter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

/**
 * A set of miscellaneous utility functions.
 */
public class Utils {
  
  public static ClassLoader loader = null;
  @SuppressWarnings( "rawtypes" )
  public static final Set<?> emptySet = new TreeSet();
  public static <T> Set<T> getEmptySet() {
    return (Set< T >)emptySet;
  }  
  @SuppressWarnings( "rawtypes" )
  public static final Map<?,?> emptyMap = new TreeMap();
  public static <T1,T2> Map<T1,T2> getEmptyMap() {
    return (Map< T1, T2 >)emptyMap;
  }

  /**
   * Compare argument types to determine how well a function call matches.
   */
  public static class ArgTypeCompare< T > {

    //public Class<?>[] candidateArgTypes = null;
    public Class<?>[] referenceArgTypes = null;
//    public boolean isVarArgs = false;

    public int numMatching = 0;
    public int numDeps = 0;
    public boolean okNumArgs = false;

    public T best = null;
    public boolean gotOkNumArgs = false;
    public int mostMatchingArgs = 0;
    public int mostDeps = 0;
    public boolean allArgsMatched = false;
    public double bestScore = Double.MAX_VALUE;

    /**
     * @param argTypes1
     * @param argTypes2
     * @param isVarArgs
     */
    public ArgTypeCompare( Class< ? >[] referenceArgTypes ) {
      super();
      this.referenceArgTypes = referenceArgTypes;
    }

    public void compare( T o, Class<?>[] candidateArgTypes,
                         boolean isVarArgs ) {
      numMatching = 0;
      numDeps = 0;
//      double score = numArgsCost + argMismatchCost * argTypes.length;
      int candidateArgsLength =
          candidateArgTypes == null ? 0 : candidateArgTypes.length;
      int referenceArgsLength =
          referenceArgTypes == null ? 0 : referenceArgTypes.length;
      okNumArgs =
          ( candidateArgsLength == referenceArgsLength )
            || ( isVarArgs
                 && ( candidateArgsLength < referenceArgsLength 
                      || candidateArgsLength == 1 ) );
      if ( Debug.isOn() ) Debug.outln( "okNumArgs = " + okNumArgs );
//      if ( okNumArgs ) score -= numArgsCost;
      for ( int i = 0; i < Math.min( candidateArgsLength,
                                     referenceArgsLength ); ++i ) {
        if ( referenceArgTypes[ i ] == null ) {
          if ( Debug.isOn() ) Debug.outln( "null arg[ " + i + " ]" );
          continue;
        }
        if ( candidateArgTypes[ i ] == null ) {
          if ( Debug.isOn() ) Debug.outln( "null arg for args[ " + i
              + " ].getClass()=" + referenceArgTypes[ i ] );
          ++numDeps;
        } else if ( candidateArgTypes[ i ].isAssignableFrom( referenceArgTypes[ i ] ) ) {
            if ( Debug.isOn() ) Debug.outln( "argTypes1[ " + i + " ]="
                         + candidateArgTypes[ i ] + " matches args[ " + i
                         + " ].getClass()=" + referenceArgTypes[ i ] );
          ++numMatching;
        } else if ( candidateArgTypes[ i ].isPrimitive() &&
                    classForPrimitive(candidateArgTypes[ i ]).isAssignableFrom( referenceArgTypes[ i ] ) ) {
          if ( Debug.isOn() ) Debug.outln( "argTypes1[ " + i + " ]="
                       + candidateArgTypes[ i ] + " matches args[ " + i
                       + " ].getClass()=" + referenceArgTypes[ i ] );
          ++numMatching;
        } else if ( Parameter.class.isAssignableFrom( candidateArgTypes[ i ] ) &&
                    Expression.class.isAssignableFrom( referenceArgTypes[ i ] ) ) {
            if ( Debug.isOn() ) Debug.outln( "argTypes1[ " + i + " ]="
                         + candidateArgTypes[ i ]
                         + " could be made dependent on args[ " + i
                         + " ].getClass()=" + referenceArgTypes[ i ] );
          ++numDeps;
        } else {
            if ( Debug.isOn() ) Debug.outln( "argTypes1[ " + i + " ]="
                         + candidateArgTypes[ i ]
                         + " does not match args[ " + i + " ].getClass()="
                         + referenceArgTypes[ i ] );
        }
      }
      if ( ( best == null )
          || ( !gotOkNumArgs && okNumArgs )
          || ( ( gotOkNumArgs == okNumArgs )
               && ( ( numMatching > mostMatchingArgs )
                    || ( ( numMatching == mostMatchingArgs ) 
                         && ( numDeps > mostDeps ) ) ) ) ) {
       best = o;
       gotOkNumArgs = okNumArgs;
       mostMatchingArgs = numMatching;
       mostDeps = numDeps;
       allArgsMatched = ( numMatching >= candidateArgsLength );
         if ( Debug.isOn() ) Debug.outln( "new match " + o + ", mostMatchingArgs="
                      + mostMatchingArgs + ",  allArgsMatched = "
                      + allArgsMatched + " = numMatching(" + numMatching
                      + ") >= candidateArgTypes.length("
                      + candidateArgsLength + "), numDeps=" + numDeps );
      }
    }
  }

  // boolean, byte, char, short, int, long, float, and double
  public static Class< ? > classForPrimitive( Class< ? > primClass ) {
    if ( !primClass.isPrimitive() ) return null;
    if ( primClass == int.class ) {
      return Integer.class;
    }
    if ( primClass == double.class ) {
      return Double.class;
    }
    if ( primClass == boolean.class ) {
      return Boolean.class;
    }
    if ( primClass == long.class ) {
      return Long.class;
    }
    if ( primClass == float.class ) {
      return Float.class;
    }
    if ( primClass == char.class ) {
      return Character.class;
    }
    if ( primClass == short.class ) {
      return Short.class;
    }
    if ( primClass == byte.class ) {
      return Byte.class;
    }
    return null;
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
      seen = new HashSet< T >();
    }
    if ( seen != null ) seen.add( o );
    return new Pair< Boolean, Set< T > >( false, seen );
  }
  
  public static < T extends Comparable< T > > int compareCollections( Collection< T > s1,
                                                                      Collection< T > s2 ) {
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
                                            ClassLoader myLoader ) {
    if ( Debug.isOn() ) Debug.outln( "trying tryClassForName( " + className + " )");
    Class< ? > classForName = null;
    if ( myLoader == null ) myLoader = getLoader(); 
    try {
      if ( myLoader == null ) {
        classForName = Class.forName( className );
      } else {
        classForName = Class.forName( className, initialize, myLoader );//, initialize, Utils.class.getClassLoader() );
      }
    } catch ( Exception e ) {
      // ignore
    } catch ( NoClassDefFoundError e) {
      // ignore
    }
    if ( classForName == null ) {
      classForName = getClassOfClass( className, "", initialize );
    }
    if ( Debug.isOn() ) Debug.outln( "tryClassForName( " + className + " ) = " + classForName );
    return classForName;
  }

  /**
   * @return the loader
   */
  public static ClassLoader getLoader() {
    if ( loader == null ) {
      loader = gov.nasa.jpl.ae.util.Utils.class.getClassLoader();//fileManager.getClassLoader(null);
    }
    if ( loader == null ) {
      loader = ClassLoader.getSystemClassLoader();
    }
    return loader;
  }

  public static Package getPackage( String packageName ) {
    Package pkg = Package.getPackage( packageName );
    return pkg;
  }
  
  public static Class<?> getClassOfClass( String clsOfClsName,
                                          String preferredPackageName,
                                          boolean initialize ) {
    Class< ? > classOfClass = null;
    int pos = clsOfClsName.lastIndexOf( '.' );
    if ( pos >= 0 ) {
      classOfClass = getClassOfClass( clsOfClsName.substring( 0, pos ),
                                      clsOfClsName.substring( pos+1 ),
                                      preferredPackageName, initialize );
    }
    return classOfClass;
  }
  public static Class<?> getClassOfClass( String className,
                                          String clsOfClsName,
                                          String preferredPackageName,
                                          boolean initialize ) {
    Class< ? > classOfClass = null;
    Class< ? > classForName =
        getClassForName( className, preferredPackageName, initialize );
    if ( classForName == null ) {
      classForName =
          getClassOfClass( className, preferredPackageName, initialize );
    }
    if ( classForName != null ) {
      classOfClass = getClassOfClass( classForName, clsOfClsName );
    }
    return classOfClass;
  }
  
  public static Class<?> getClassOfClass( Class<?> cls,
                                          String clsOfClsName ) {
    if ( cls != null ) {
      Class< ? >[] classes = cls.getClasses();
      if ( classes != null ) {
        String clsName = cls.getName(); 
        String clsSimpleName = cls.getSimpleName();
        String longName = clsName + "." + clsOfClsName;
        String shorterName = clsSimpleName + "." + clsOfClsName;
        for ( int i = 0; i < classes.length; ++i ) {
          String n = classes[ i ].getName();
          String sn = classes[ i ].getSimpleName();
          if ( n.equals( longName )
               || n.equals( shorterName )
               || sn.equals( clsOfClsName )
//               || n.endsWith( "." + longName )
//               || n.endsWith( "." + shorterName )
               || n.endsWith( "." + clsOfClsName ) ) {
            return classes[ i ];
          }
        }
      }
    }
    return null;
  }

  public static List<Package> getPackagesForNames( Collection< String > packageNames ) {
    ArrayList<Package> packages = new ArrayList< Package >();
    for ( String pkgName : packageNames ) {
      Package pkg = getPackage( pkgName );
      if ( pkg != null ) packages.add( pkg );
    }
    return packages;
  }
  
  public static Class<?> getClassForName( String className,
                                          String preferredPackage,
                                          boolean initialize ) {
    String[] packageNames = new String[] { preferredPackage, "gov.jpl.nasa.ae" };
    List<Package> packages = getPackagesForNames( Arrays.asList( packageNames ) );
    Package pkg = gov.nasa.jpl.ae.util.Utils.class.getPackage();
    packages.add( pkg );
    Package[] pkgArray = new Package[packages.size()];
    toArrayOfType( packages.toArray(), pkgArray, Package.class );
    List< Class<?>> classList = getClassesForName( className, initialize, getLoader(),
                                                   pkgArray );
    if ( !isNullOrEmpty( classList ) ) {
      for ( Class< ? > c : classList ) {
        if ( c.getPackage().getName().equals( preferredPackage ) ) {
          if ( Debug.isOn() ) Debug.errln("Found preferred package! " + preferredPackage );
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
  
  public static List< Class< ? > > getClassesForName( String className,
                                                      boolean initialize,// ) {
                                                      ClassLoader loader,
                                                      Package[] packages) {
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
    if ( Debug.isOn() ) Debug.outln( "getClassForName( " + className + " )" );
    Class< ? > classForName = tryClassForName( className, initialize, loader );
    if ( classForName != null ) classList.add( classForName );
    String strippedClassName = noParameterName( className );
    if ( Debug.isOn() ) Debug.outln( "getClassForName( " + className + " ): strippedClassName = "
                 + strippedClassName );
    boolean strippedWorthTrying = false;
    if ( !Utils.isNullOrEmpty( strippedClassName ) ) {
      strippedWorthTrying = !strippedClassName.equals( className ); 
      if ( strippedWorthTrying ) {
        classForName = tryClassForName( strippedClassName, initialize, loader );
        if ( classForName != null ) classList.add( classForName );
      }
    }
    List<String> FQNs = getFullyQualifiedNames( className, packages );
    if ( Debug.isOn() ) Debug.outln( "getClassForName( " + className + " ): fully qualified names = "
        + FQNs );
    if ( FQNs.isEmpty() && strippedWorthTrying ) {
      FQNs = getFullyQualifiedNames( strippedClassName, packages );
    }
    if ( !FQNs.isEmpty() ) {
      for ( String fqn : FQNs ) {
        classForName = tryClassForName( fqn, initialize, loader );
        if ( classForName != null ) classList.add( classForName );
      }
    }
    return classList;
  }
  
  public static Collection<String> getPackageStrings(Package[] packages) {
    List<String> packageStrings = new ArrayList<String>();
    if ( !isNullOrEmpty( packages ) ) {
    }
    if ( !isNullOrEmpty( packages ) ) {
      for (Package aPackage : packages ) {
        if ( aPackage != null ) {
          if ( !packageStrings.contains( aPackage.getName() ) ) {
            packageStrings.add(aPackage.getName());
          }
        }
      }
    }
    packages = Package.getPackages();
    if ( !isNullOrEmpty( packages ) ) {
      for (Package aPackage : packages ) {
        if ( aPackage != null ) {
          if ( !packageStrings.contains( aPackage.getName() ) ) {
            packageStrings.add(aPackage.getName());
          }
        }
      }
    }

    if ( Debug.isOn() ) Debug.out( "getPackageStrings("
                                   + toString( packages, false ) + ") = "
                                   + packageStrings );
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

  public static String getFullyQualifiedName( String classOrInterfaceName,
                                              boolean doTypeParameters ) {
    String typeParameters = "";
    if ( classOrInterfaceName.contains( "<" )
         && classOrInterfaceName.contains( ">" ) ) {
      typeParameters =
          classOrInterfaceName.substring( classOrInterfaceName.indexOf( '<' ) + 1,
                                          classOrInterfaceName.lastIndexOf( '>' ) ).trim();
      typeParameters = "<" + ( doTypeParameters
                               ? getFullyQualifiedName( typeParameters, true )
                               : typeParameters ) + ">";
      classOrInterfaceName =
          classOrInterfaceName.substring( 0, classOrInterfaceName.indexOf( '<' ) );
    }
    List< String > names = Utils.getFullyQualifiedNames( classOrInterfaceName );
    if ( Utils.isNullOrEmpty( names ) ) {
      names = Utils.getFullyQualifiedNames( Utils.simpleName( classOrInterfaceName ) );
    }
    if ( !Utils.isNullOrEmpty( names ) ) {
      for ( String n : names ) {
        if ( n.endsWith( classOrInterfaceName ) ) {
          classOrInterfaceName = n;
          break;
        }
      }
    }
    return classOrInterfaceName + typeParameters;
  }
  
  public static List<String> getFullyQualifiedNames(String simpleClassOrInterfaceName ) {
    return getFullyQualifiedNames( simpleClassOrInterfaceName, null );
  }
  public static List<String> getFullyQualifiedNames(String simpleClassOrInterfaceName, Package[] packages) {
    Collection<String> packageStrings = getPackageStrings( packages );

    List<String> fqns = new ArrayList<String>();
    if ( Debug.isOn() ) Debug.outln( "getFullyQualifiedNames( " + simpleClassOrInterfaceName
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
    if ( Debug.isOn() ) Debug.outln( "getFullyQualifiedNames( " + simpleClassOrInterfaceName
                 + " ): returning " + fqns );
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

  public static Constructor< ? > getConstructorForArgTypes( String className,
                                                            Class<?>[] argTypes,
                                                            String preferredPackage ) {
    Class< ? > classForName = getClassForName( className, preferredPackage, false );
    if ( classForName == null ) {
      System.err.println( "Couldn't find the class " + className
                          + " to get constructor with args=" + toString( argTypes ) );
      return null;
    }
    return getConstructorForArgTypes( classForName, argTypes );
  }
  
  public static Class<?>[] getClasses( Object[] objects ) {
    //return ClassUtils.toClass( objects );
    Class< ? > argTypes[] = null;
    if ( objects != null ) {
      argTypes = new Class< ? >[ objects.length ];
      for ( int i = 0; i < objects.length; ++i ) {
        if ( objects[ i ] == null ) {
          argTypes[ i ] = null;
        } else {
          argTypes[ i ] = objects[ i ].getClass();
        }
      }
    }
    return argTypes;
  }

  /**
   * Determines whether the array contain only Class<?> objects possibly with
   * some (but not all nulls).
   * 
   * @param objects
   * @return
   */
  public static boolean areClasses( Object[] objects ) {
    boolean someClass = false;
    boolean someNotClass = false;
    for ( Object o : objects ) {
      if ( o == null ) continue;
      if ( o instanceof Class ) {
        someClass = true;
      } else {
        someNotClass = true;
      }
    }
    return !someNotClass && someClass;
  }
  
  public static Constructor< ? > getConstructorForArgs( Class< ? > cls,
                                                        Object[] args ) {
    if ( Debug.isOn() ) Debug.outln( "getConstructorForArgs( " + cls.getName() + ", "
                 + toString( args ) );
    Class< ? > argTypes[] = getClasses( args );
    return getConstructorForArgTypes( cls, argTypes );
/*    //Method matchingMethod = null;
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
*/  }

  public static Pair< Constructor< ? >, Object[] >
      getConstructorForArgs( Class< ? > eventClass, Object[] arguments,
                             Object enclosingInstance ) {
    boolean nonStaticInnerClass = Utils.isInnerClass( eventClass );
    if ( Debug.isOn() ) Debug.outln( eventClass.getName() + ": nonStaticInnerClass = " + nonStaticInnerClass );
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

  public static < T > T getBestArgTypes( Map< T, Pair< Class< ? >[], Boolean > > candidates,
                                         // ConstructorDeclaration[] ctors,
                                         Class< ? >... argTypes ) {
    ArgTypeCompare< T > atc =
        new ArgTypeCompare< T >( argTypes );
    for ( Entry< T, Pair< Class< ? >[], Boolean > > e : candidates.entrySet() ) {
      atc.compare( e.getKey(), e.getValue().first, e.getValue().second );
    }
    if ( atc.best != null && !atc.allArgsMatched ) {
      System.err.println( "constructor returned (" + atc.best
                          + ") only matches " + atc.mostMatchingArgs
                          + " args: " + toString( argTypes, false ) );
    } else if ( atc.best == null ) {
      System.err.println( "best args not found in " + candidates );
    }
    return atc.best;
  }

  public static Constructor< ? > getConstructorForArgTypes( Constructor<?>[] ctors,
                                                            Class< ? >... argTypes ) {
    //Constructor< ? >[] ctors = null;
    ArgTypeCompare< Constructor< ? > > atc =
        new ArgTypeCompare< Constructor< ? > >( argTypes );
    for ( Constructor< ? > aCtor : ctors) {
      atc.compare( aCtor, aCtor.getParameterTypes(), aCtor.isVarArgs() );
    }
    if ( atc.best != null && !atc.allArgsMatched ) {
      System.err.println( "constructor returned (" + atc.best
                          + ") only matches " + atc.mostMatchingArgs
                          + " args: " + toString( argTypes, false ) );
    } else if ( atc.best == null ) {
      System.err.println( "constructor not found in " + ctors );
//                          cls.getSimpleName()
//                          + toString( argTypes, false ) + " not found for "
//                          + cls.getSimpleName() );
    }
    return atc.best;
  }
  
  public static Constructor< ? > getConstructorForArgTypes( Class< ? > cls,
                                                            Class< ? >... argTypes ) {
    if ( argTypes == null ) argTypes = new Class< ? >[] {};
    if ( Debug.isOn() ) Debug.outln( "getConstructorForArgTypes( cls=" + cls.getName()
                 + ", argTypes=" + toString( argTypes ) + " )" );
    return getConstructorForArgTypes( cls.getConstructors(), argTypes );
/*    ArgTypeCompare atc = new ArgTypeCompare( argTypes );
    for ( Constructor< ? > aCtor : cls.getConstructors() ) {
      atc.compare( aCtor, aCtor.getParameterTypes(), aCtor.isVarArgs() );
    }
    if ( atc.best != null && !atc.allArgsMatched ) {
      System.err.println( "constructor returned (" + atc.best
                          + ") only matches " + atc.mostMatchingArgs
                          + " args: " + toString( argTypes, false ) );
    } else if ( atc.best == null ) {
      System.err.println( "constructor " + cls.getSimpleName()
                          + toString( argTypes, false ) + " not found for "
                          + cls.getSimpleName() );
    }
    return (Constructor< ? >)atc.best;
*/  }
  public static Constructor< ? >
    getConstructorForArgTypes( Class< ? > cls, String packageName ) {
    Pair< Constructor< ? >, Object[] > p = 
        getConstructorForArgs(cls, new Object[]{}, packageName );
    if ( p == null ) return null;
    return p.first;
  }

//  public static Constructor< ? >
//      getConstructorForArgTypes( Class< ? > cls, String packageName,
//                                 Class< ? >... argTypes ) {
//    // Pair< Constructor< ? >, Object[] > p =
//    return getConstructorForArgTypes( cls, argTypes );
//    // if ( p == null ) return null;
//    // return p.first;
//  }

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
  
  public static Method getJavaMethodForCommonFunction( String functionName,
                                                       List<Object> args ) {
    return getJavaMethodForCommonFunction( functionName, args.toArray() );
  }
  // TODO -- feed this through ArgTypeCompare to avoid mismatches
  public static Method getJavaMethodForCommonFunction( String functionName,
                                                       Object[] args ) {
//    boolean alreadyClass = areClasses( args );
    Class<?>[] argTypes = null;
//    if ( alreadyClass ) {
//      argTypes = new Class<?>[args.length];
//      boolean ok = toArrayOfType( args, argTypes, Class.class );
//      assert ok;
//    } else {
      argTypes = getClasses(args); 
//    }
    return getJavaMethodForCommonFunction( functionName, argTypes );
  }
  public static Method getJavaMethodForCommonFunction( String functionName,
                                                       Class[] argTypes ) {
    // REVIEW -- Could use external Reflections library to get all classes in a
    // package:
    //   Reflections reflections = new Reflections("my.project.prefix");
    //   Set<Class<? extends Object>> allClasses = 
    //      reflections.getSubTypesOf(Object.class);
    //   use on:
    //     java.lang
    //     org.apache.commons.lang.
    //     java.util?
    Class< ? >[] classes =
        new Class< ? >[] { Math.class, StringUtils.class, Integer.class,
                          Double.class, Character.class, Boolean.class,
                          String.class,
                          org.apache.commons.lang.ArrayUtils.class,
                          Arrays.class };
    for ( Class<?> c : classes ) {
      Method m = getMethodForArgTypes( c, functionName, argTypes );
      if ( m != null ) return m;
    }
    return null;
  }
  
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

  public static Method getMethodForArgs( String className,
                                         String preferredPackage,
                                         String callName,
                                         Object... args ) {
    Class< ? > classForName = getClassForName( className, preferredPackage, false );
    if ( Debug.errorOnNull( "Couldn't find the class " + className + " for method "
                      + callName + ( args == null ? "" : toString( args ) ),
                      classForName ) ) {
      return null;
    }
    return getMethodForArgs( classForName, callName, args );
  }

  public static Method getMethodForArgs( Class< ? > cls, String callName,
                                         Object... args ) {
    if ( Debug.isOn() ) Debug.outln( "getMethodForArgs( cls=" + cls.getName() + ", callName="
        + callName + ", args=" + toString( args ) + " )" );
    Class< ? > argTypes[] = null;
//    boolean allClasses = areClasses( args ); 
//    if ( allClasses ) {
//      //argTypes = (Class< ? >[])args;
//      boolean ok = toArrayOfType( args, argTypes, Class.class );
//      assert ok;
//    } else {
      argTypes = getClasses( args );
//    }
    return getMethodForArgTypes( cls, callName, argTypes );
  }

  public static Method getMethodForArgTypes( String className,
                                             String preferredPackage,
                                             String callName,
                                             Class<?>... argTypes ) {
    return getMethodForArgTypes( className, preferredPackage, callName,
                                 argTypes, true );
  }
  public static Method getMethodForArgTypes( String className,
                                             String preferredPackage,
                                             String callName,
                                             Class<?>[] argTypes,
                                             boolean complainIfNotFound ) {
    Class< ? > classForName = getClassForName( className, preferredPackage, false );
    if ( classForName == null ) {
      if ( complainIfNotFound ) {
        System.err.println( "Couldn't find the class " + className + " for method "
                     + callName
                     + ( argTypes == null ? "" : toString( argTypes ) ) );
      }
      return null;
    }
    Method m = getMethodForArgTypes( classForName, callName, argTypes );
    if ( Debug.isOn() ) Debug.errorOnNull( "getMethodForArgTypes(" + className + "." + callName
                 + toString( argTypes, false ) + "): Could not find method!", m );
    return m;
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
    if ( argTypes == null ) argTypes = new Class<?>[] {};
    if ( Debug.isOn() ) Debug.outln( "getMethodForArgTypes( cls=" + cls.getName() + ", callName="
                 + callName + ", argTypes=" + toString( argTypes ) + " )" );
//    Method matchingMethod = null;
//    boolean gotOkNumArgs = false;
//    int mostMatchingArgs = 0;
//    int mostDeps = 0;
//    boolean allArgsMatched = false;
//    double bestScore = Double.MAX_VALUE;
    boolean debugWasOn = Debug.isOn();
    //Debug.turnOff();
    Method[] methods = null;
    if ( Debug.isOn() ) Debug.outln( "calling getMethods() on class " + cls.getName() );
    try {
      methods = cls.getMethods();
    } catch ( Exception e ) {
      System.err.println( "Got exception calling " + cls.getName()
                          + ".getMethod(): " + e.getMessage() );
    }
    if ( Debug.isOn() ) Debug.outln( "--> got methods: " + toString( methods ) );
    ArgTypeCompare atc = new ArgTypeCompare( argTypes );
    if ( methods != null ) {
      for ( Method m : methods ) {
        if ( m.getName().equals( callName ) ) {
          atc.compare( m, m.getParameterTypes(), m.isVarArgs() );
        }
      }
    }
    if ( debugWasOn ) {
      Debug.turnOn();
    }
    if ( atc.best != null && !atc.allArgsMatched ) {
      if ( Debug.isOn() ) Debug.errln( "method returned (" + atc.best + ") only matches "
                   + atc.mostMatchingArgs + " args: " + toString( argTypes ) );
    } else if ( atc.best == null ) {
      System.err.println( "method " + callName + "(" + toString( argTypes ) + ")"
                          + " not found for " + cls.getSimpleName() );
    }
    return (Method)atc.best;
  }
  
  public static Object getFieldValue( Object o, String fieldName ) {
    if ( o == null || isNullOrEmpty( fieldName ) ) {
      return null;
    }
    Exception ex = null;
    Field f = null;
    try {
      f = o.getClass().getField( fieldName );
      return f.get( o );
    } catch ( NoSuchFieldException e ) {
      ex = e;
    } catch ( SecurityException e ) {
      ex = e;
    } catch ( IllegalArgumentException e ) {
      ex = e;
    } catch ( IllegalAccessException e ) {
      ex = e;
    }
    if ( f == null && o instanceof gov.nasa.jpl.ae.event.Parameter ) {
        return getFieldValue( ( (gov.nasa.jpl.ae.event.Parameter)o ).getValueNoPropagate(),
                              fieldName );
    }
    if ( f == null && ex != null ) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
    }
    return null;
  }

  public static String join( Collection<String> strings, String delim ) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for ( String s : strings ) {
      if ( first ) first = false;
      else sb.append( delim );
      sb.append( s );
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
  
}
