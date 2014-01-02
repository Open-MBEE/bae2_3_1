/**
 * 
 */
package gov.nasa.jpl.ae.util;

import gov.nasa.jpl.ae.event.Expression;
import japa.parser.ast.body.Parameter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import junit.framework.Assert;

/**
 * @author bclement
 *
 */
public class ClassUtils {

  /**
     * Compare argument types to determine how well a function call matches.
     */
    public static class ArgTypeCompare< T > {
  
      //public Class<?>[] candidateArgTypes = null;
      public Class<?>[] referenceArgTypes = null;
  //    public boolean isVarArgs = false;
  
      public int numMatching = 0;
      public int numNull = 0;
      public int numDeps = 0;
      public boolean okNumArgs = false;
  
      public T best = null;
      public boolean gotOkNumArgs = false;
      public int mostMatchingArgs = 0;
      public int mostDeps = 0;
      public boolean allArgsMatched = false;
      public boolean allNonNullArgsMatched = false;
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
        numNull = 0;
        numDeps = 0;
        boolean debugWasOn = Debug.isOn();
        if ( debugWasOn ) Debug.turnOff();
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
            ++numNull;
            ++numDeps;
            //++numMatching;
            continue;
          }
          if ( candidateArgTypes[ i ] == null ) {
            if ( Debug.isOn() ) Debug.outln( "null arg for args[ " + i
                + " ].getClass()=" + referenceArgTypes[ i ] );
            Debug.error(false, true, "null arg for args[ " + i
                + " ].getClass()=" + referenceArgTypes[ i ] );
            //++numNull;
            //++numDeps;
            continue;
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
//          } else if ( Parameter.class.isAssignableFrom( candidateArgTypes[ i ] ) &&
//                      Expression.class.isAssignableFrom( referenceArgTypes[ i ] ) ) {
//              if ( Debug.isOn() ) Debug.outln( "argTypes1[ " + i + " ]="
//                           + candidateArgTypes[ i ]
//                           + " could be made dependent on args[ " + i
//                           + " ].getClass()=" + referenceArgTypes[ i ] );
//            ++numDeps;
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
         allNonNullArgsMatched = ( numMatching + numNull >= candidateArgsLength );
           if ( Debug.isOn() ) Debug.outln( "new match " + o + ", mostMatchingArgs="
                        + mostMatchingArgs + ",  allArgsMatched = "
                        + allArgsMatched + " = numMatching(" + numMatching
                        + ") >= candidateArgTypes.length("
                        + candidateArgsLength + "), numDeps=" + numDeps );
        }
        if ( debugWasOn ) Debug.turnOn();
      }
    }

    // TODO -- this would be useful for TimeVaryingMap.valueFromString();
//  public static <V> V valueFromString() {
//    V value = null;
//    
//    return value;
//  }
    
//    // boolean, byte, char, short, int, long, float, and double
//    public static Class< ? > primitiveForClass( Class< ? > nonPrimClass ) {
//      if ( nonPrimClass == Integer.class ) {
//        return int.class;
//      }
//      if ( nonPrimClass == Double.class ) {
//        return double.class;
//      }
//      if ( nonPrimClass == Boolean.class ) {
//        return boolean.class;
//      }
//      if ( nonPrimClass == Long.class ) {
//        return long.class;
//      }
//      if ( nonPrimClass == Float.class ) {
//        return float.class;
//      }
//      if ( nonPrimClass == Character.class ) {
//        return char.class;
//      }
//      if ( nonPrimClass == Short.class ) {
//        return short.class;
//      }
//      if ( nonPrimClass == Byte.class ) {
//        return byte.class;
//      }
//      return null;
//    }
    
  // boolean, byte, char, short, int, long, float, and double
  public static Class< ? > classForPrimitive( Class< ? > primClass ) {
    return getPrimToNonPrim().get( primClass );
  }
  // boolean, byte, char, short, int, long, float, and double
  public static Class< ? > primitiveForClass( Class< ? > nonPrimClass ) {
    return getNonPrimToPrim().get( nonPrimClass );
  }
  
  
  private static Map<String,Class<?>> nonPrimitives = initializeNonPrimitives();
  private static Map<String,Class<?>> primitives = initializePrimitives();
  private static Map<Class<?>,Class<?>> primToNonPrim = initializePrimToNonPrim();
  private static Map<Class<?>,Class<?>> nonPrimToPrim = initializeNonPrimToPrim();
  
  public static Map<String,Class<?>> getPrimitives() {
    if ( primitives == null ) initializePrimitives();
    return primitives;
  }
  public static Map<String,Class<?>> getNonPrimitives() {
    if ( nonPrimitives == null ) initializeNonPrimitives();
    return nonPrimitives;
  }
  public static Map< Class< ? >,Class< ? > > getPrimToNonPrim() {
    if ( primToNonPrim == null ) initializePrimToNonPrim();
    return primToNonPrim;
  }
  public static Map< Class< ? >,Class< ? > > getNonPrimToPrim() {
    if ( nonPrimToPrim == null ) initializeNonPrimToPrim();
    return nonPrimToPrim;
  }
  
  // boolean, byte, char, short, int, long, float, and double
  public static Class< ? > classForPrimitive( String primClass ) {
    return getPrimitives().get( primClass );
  }

  // boolean, byte, char, short, int, long, float, and double
  private static Map< String, Class< ? > > initializeNonPrimitives() {
    nonPrimitives = new TreeMap< String, Class<?> >();
    for ( Class< ? > c : getNonPrimToPrim().keySet() ) {
      nonPrimitives.put( c.getSimpleName(), c );
    }
    return nonPrimitives;
  }
  
  // boolean, byte, char, short, int, long, float, and double
  private static Map< String, Class< ? > > initializePrimitives() {
    primitives = new TreeMap< String, Class<?> >();
    for ( Class< ? > c : getPrimToNonPrim().keySet() ) {
      primitives.put( c.getSimpleName(), c );
    }
//    primitives.put( "boolean", boolean.class );
//    primitives.put( "byte", byte.class );
//    primitives.put( "char", char.class );
//    primitives.put( "short", short.class );
//    primitives.put( "int", int.class );
//    primitives.put( "long", long.class );
//    primitives.put( "float", float.class );
//    primitives.put( "double", double.class );
//    primitives.put( "void", void.class );
    return primitives;
  }
  
  // boolean, byte, char, short, int, long, float, double, and void
  private static Map< Class< ? >, Class< ? > > initializePrimToNonPrim() {
    primToNonPrim = new HashMap< Class< ? >, Class<?> >();
    primToNonPrim.put( boolean.class, Boolean.class );
    primToNonPrim.put( byte.class, Byte.class );
    primToNonPrim.put( char.class, Character.class );
    primToNonPrim.put( short.class, Short.class );
    primToNonPrim.put( int.class, Integer.class );
    primToNonPrim.put( long.class, Long.class );
    primToNonPrim.put( float.class, Float.class );
    primToNonPrim.put( double.class, Double.class );
    primToNonPrim.put( void.class, Void.class );
    return primToNonPrim;
  }

  // boolean, byte, char, short, int, long, float, and double
  private static Map< Class< ? >, Class< ? > > initializeNonPrimToPrim() {
    nonPrimToPrim = new HashMap< Class< ? >, Class<?> >();
    for ( Entry< Class< ? >, Class< ? > > e : getPrimToNonPrim().entrySet() ) {
      nonPrimToPrim.put( e.getValue(), e.getKey() );
    }
    return nonPrimToPrim;
  }


  /**
   * @param type
   * @param preferredPackage
   * @return the Class corresponding to the string
   */
  public static Class< ? > getNonPrimitiveClass( String type, String preferredPackage ) {
    if ( type == null ) return null;
    Class< ? > cls = null;
    cls = getClassForName( type, null, preferredPackage, false );
    return getNonPrimitiveClass( cls );
  }

  /**
   * @param cls the possibly primitive Class object
   * @return the non-primitive class (e.g. Integer.class) corresponding to the
   *         input cls if cls is a primitive class (e.g. int.class); otherwise,
   *         cls (e.g. String.class)
   */
  public static Class< ? > getNonPrimitiveClass( Class< ? > cls ) {
    if ( cls == null ) return null;
    Class< ? > result = classForPrimitive( cls );
    if ( result == null ) return cls;
    return result;
  }

  /**
   * @param o
   * @return whether the object is a primitive class (like int or Integer) or an instance of one 
   */
  public static boolean isPrimitive( Class<?> c ) {
    return ( getNonPrimitives().containsKey( c.getSimpleName() ) ||
             getPrimitives().containsKey( c.getSimpleName() ) );
  }
  
  
  /**
   * @param o
   * @return whether the object is a primitive class (like int or Integer) or an instance of one 
   */
  public static boolean isPrimitive( Object o ) {
    if ( o instanceof Class ) {
      return isPrimitive( (Class<?>)o ); 
    }
    return isPrimitive( o.getClass() );
  }

  public static String addBackParametersToQualifiedName( String className,
                                                  String qualifiedName ) {
    String parameters = parameterPartOfName( className, true );
    String strippedName = noParameterName( className );
    if ( !Utils.isNullOrEmpty( parameters ) ) {
      String parameters2 = parameterPartOfName( qualifiedName, true );
      if ( Utils.isNullOrEmpty( parameters2 )
           && qualifiedName.endsWith( strippedName ) ) {
        return qualifiedName + parameters;
      }
    }
    return qualifiedName;
  }
  
  public static String getNonPrimitiveClassName( String type ) {
    if ( type == null ) return null;
    String simpleName = simpleName( type );
    Class<?> prim = getPrimitives().get( simpleName );
    String newName = type;
    if ( prim != null ) {
      Class<?> nonPrim = getPrimToNonPrim().get( prim );
      Assert.assertNotNull( nonPrim );
      newName = nonPrim.getSimpleName();
    }    
//    Class< ? > cls = getNonPrimitiveClass( type );
//    String newName = type;
//    if ( cls != null ) {
//      if ( cls.isArray() ) {
//        newName = cls.getSimpleName();
//      } else {
//        newName = cls.getName();
//      }
//      newName = addBackParametersToQualifiedName( type, newName );
//    }
    return newName;
  }

  /**
   * @param c1
   * @param c2
   * @return whether a class, c1, is a subclass of another class, c2
   */
  public static boolean isSubclassOf( Class<?> c1, Class<?> c2 ) {
    try {
      c1.asSubclass( c2 );
    } catch( ClassCastException e ) {
      return false;
    }
    return true;
  }

  /**
   * @param cls
   * @return the class of the single generic Type parameter for the input Class
   *         or null if there is not exactly one.
   */
  public static Class< ? > getSingleGenericParameterType( Class< ? > cls ) {
    if ( cls == null ) return null;
    TypeVariable< ? >[] params = cls.getTypeParameters();
    if ( params != null && params.length == 1 ) {
      Class< ? > cls2;
      try {
        cls2 = Class.forName( params[ 0 ].getName() );
        return cls2;
      } catch ( ClassNotFoundException e ) {
      }
    }
    return null;
  }
  
  /**
   * @see gov.nasa.jpl.ae.util.ClassUtil#mostSpecificCommonSuperclass(Collection)
   * @param coll
   *          the collection
   * @return the lowest upper bound superclass assignable from all elements in
   *         the collection.
   *         <p>
   *         For example, If coll contains Integer.class and Double.class,
   *         leastUpperBoundSuperclass( coll ) returns Number.class because they
   *         both inherit from Number and Object, and Number is lower (more
   *         specific) than Object.
   */
  public static Class<?> leastUpperBoundSuperclass( Collection< ? > coll ) {
    return mostSpecificCommonSuperclass( coll );
  }
  
  /**
   * @param coll
   *          the collection
   * @return the most specific (lowest) common superclass assignable from all
   *         elements in the collection.
   *         <p>
   *         For example, If coll contains Integer.class and Double.class,
   *         leastUpperBoundSuperclass( coll ) returns Number.class because they
   *         both inherit from Number and Object, and Number is lower (more
   *         specific) than Object.
   *         <p>
   *         Note that if the the classes implement several common interfaces
   *         but directly extend Object, Object.class will be returned.
   */
  public static Class<?> mostSpecificCommonSuperclass( Collection< ? > coll ) {
    if ( Utils.isNullOrEmpty( coll ) ) return null; 
    Class<?> most = null;
    for ( Object o : coll ) {
      if ( o == null ) continue;
      Class<?> cls = getNonPrimitiveClass( o.getClass() );
      if ( most == null ) {
        most = cls;
        continue;
      }
      while ( cls != null && !cls.isAssignableFrom( most ) ) {
        cls = cls.getSuperclass();
      }
      if ( cls != null && cls.isAssignableFrom( most ) ) {
        most = cls;
      }
    }
    return most;
  }
  
  public static Class< ? > tryClassForName( String className, 
                                            boolean initialize ) {
    return tryClassForName( className, initialize, null );
  }

  public static Class< ? > classForName( String className ) throws ClassNotFoundException {
    if (Utils.isNullOrEmpty( className )) return null;
    Thread t = Thread.currentThread();
    Class< ? > cls = null;
    ClassLoader[] loaders = new ClassLoader[] { t.getContextClassLoader(), 
                                                ClassLoader.getSystemClassLoader(),
                                                ClassUtils.class.getClassLoader() };
    for ( ClassLoader cl : loaders ) {
        if ( cl != null ) {
            try {
                cls = cl.loadClass(className);
                if ( cls != null ) {
                    Debug.outln( "classForName(" + className + ") = " + cls.getSimpleName() );
                    break;
                }
            } catch ( Throwable e ) {
                Debug.errln( "classForName(" + className
                             + ") failed for loader: " + cl + "\n"
                             + e.getLocalizedMessage() );
            }
        }
    }
    return cls;
  }

  public static Class< ? > tryClassForName( String className, 
                                            boolean initialize,
                                            ClassLoader myLoader ) {
    if ( Debug.isOn() ) Debug.outln( "trying tryClassForName( " + className + " )");
    Class< ? > classForName = null;
    if ( myLoader == null ) myLoader = Utils.loader; 
    //if ( myLoader == null ) myLoader = gov.nasa.jpl.ae.event.Expression.class.getClassLoader(); 
    try {
      classForName = classForName( className );
    } catch ( ClassNotFoundException e1 ) {
      // ignore
    }
    if ( classForName == null ) {
      try {
        if ( myLoader == null ) {
          classForName = Class.forName( className );
        } else {
        classForName = Class.forName( className, initialize, myLoader );//, initialize, Utils.class.getClassLoader() );
        }
      } catch ( NoClassDefFoundError e ) {
        // ignore
      } catch ( ClassNotFoundException e ) {
        // ignore
      }
    }
    if ( classForName == null ) {
      classForName = getClassOfClass( className, "", initialize );
    }
    if ( Debug.isOn() ) Debug.outln( "tryClassForName( " + className + " ) = " + classForName );
    return classForName;
  }

  public static String replaceAllGenericParameters( String className, char replacement ) {
    if ( Utils.isNullOrEmpty( className ) ) return className;
    StringBuffer newName = new StringBuffer( className );
    int parameterDepth = 0;
    for ( int i=0; i<className.length(); ++i ) {
      char c = className.charAt( i );
      boolean replace = false;
      switch( c ) {
        case '>':
          --parameterDepth;
          replace = true;
          break;
        case '<':
          ++parameterDepth;
          replace = true;
          break;
        default:
          if ( parameterDepth > 0 ) replace = true;
      }
      if ( replace ) {
        newName.setCharAt( i, replacement );
      }
      if ( parameterDepth < 0 ) {
        Debug.error( false,
                     "Error! Generic parameter nesting is invalid for class name:\n  \""
                         + className + "\" at char #" + Integer.toString( i )
                         + "\n" + Utils.spaces( i + 3 ) + "^" );
      }
    }
    return newName.toString();
  }
  
  public static Class<?> getClassOfClass( String longClassName,
                                          String preferredPackageName,
                                          boolean initialize ) {
    Class< ? > classOfClass = null;
    String clsOfClsName = replaceAllGenericParameters( longClassName, '~' );
    int pos = clsOfClsName.lastIndexOf( '.' );
    if ( pos >= 0 ) {
      String clsOfClsName1 = longClassName.substring( 0, pos );
      String clsOfClsName2 = longClassName.substring( pos+1 );
      classOfClass =
          getClassOfClass( clsOfClsName1, clsOfClsName2, preferredPackageName,
                           initialize );
    }
    return classOfClass;
  }

  public static Class<?> getClassOfClass( String className,
                                          String clsOfClsName,
                                          String preferredPackageName,
                                          boolean initialize ) {
    Class< ? > classOfClass = null;
    Class< ? > classForName =
        getClassForName( className, null, preferredPackageName, initialize );
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

  // TODO -- expand to include member names, too: className -> memberName -> Class
  public static Map< String, Class< ? > > classCache =
      Collections.synchronizedMap( new HashMap< String, Class< ? > >() );
  
  public static Class<?> getClassForName( String className, String memberName,
                                          String preferredPackage, boolean initialize ) {
    if ( Utils.isNullOrEmpty( className ) ) return null;
    Class<?> cls = null;
    char firstChar = className.charAt( 0 );
    if ( firstChar >= 'a' && firstChar <= 'z' ) {
      cls = classForPrimitive( className );
      if ( cls != null
           && ( Utils.isNullOrEmpty( memberName ) || hasMember( cls, memberName ) ) ) {
        return cls;
      }
    }
    Class< ? > cls2 = classCache.get( className );
    if ( cls2 != null ) {
      if ( Utils.isNullOrEmpty( memberName ) || hasMember( cls2, memberName ) ) {
        return cls2;
      }
      if ( cls == null ) cls = cls2;
    }
    List< Class<?>> classList = getClassesForName( className, initialize );
    if ( !Utils.isNullOrEmpty( classList ) && initialize && !isPackageName( className ) ) {  // REVIEW
      classList = getClassesForName( className, !initialize );
    }
    if ( !Utils.isNullOrEmpty( classList ) ) {
//        Class< ? > best = null;
//        boolean bestHasSpecifier = false;
//        boolean bestInPreferredPackage = false;
//        for ( Class< ? > c : classList ) {
//          boolean inPreferredPackage = false;
//          boolean hasSpecifier = hasMember( c, specifier );
//          if ( inPackage( c, preferredPackage ) ) {
//            classCache.put( className, c );
//            if ( hasSpecifier ) return c;
//            inPreferredPackage = true;
//          }
//        }
      cls2 = getClassFromClasses( classList, memberName, preferredPackage );
      if ( cls2 != null ) cls = cls2;
    }
    if ( cls != null ) classCache.put( className, cls );
    return cls;
  }
  //  public static Class<?> getClassForName( String className,
  //                                          boolean initialize ) {    
  //    return getClassFromClasses( getClassesForName( className, initialize ) );
  //  }

  public static HashMap< String, List< Class<?> > > classesCache =
      new HashMap< String, List<Class<?>> >();

  public static List< Class< ? > > getClassesForName( String className,
                                                        boolean initialize ) {
  //                                                    ClassLoader loader,
  //                                                    Package[] packages) {
    if ( Debug.isOn() ) Debug.outln( "getClassesForName( " + className + " )" );
    List< Class< ? > > classList = classesCache.get( className );
    if ( Debug.isOn() ) Debug.outln("classList " + classList + " from classesCache " + classesCache );
    if ( !Utils.isNullOrEmpty( classList ) ) {
      if ( Debug.isOn() ) Debug.outln( "getClassesForName( " + className + " ) returning " + classList );
      return classList;
    }
    classList = new ArrayList< Class< ? > >();
    if ( Utils.isNullOrEmpty( className ) ) {
      if ( Debug.isOn() ) Debug.outln( "getClassesForName( " + className + " ) rempty className - returning null" );
      return null;
    }
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
      Class< ? > classForName = tryClassForName( className, initialize );//, loader );
      if ( classForName != null ) classList.add( classForName );
      String strippedClassName = noParameterName( className );
      if ( Debug.isOn() ) Debug.outln( "getClassesForName( " + className + " ): strippedClassName = "
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
      if ( Debug.isOn() ) Debug.outln( "getClassesForName( " + className + " ): fully qualified names = "
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
      if ( !Utils.isNullOrEmpty( classList ) ) {
        classesCache.put( className, classList );
      }
      if ( Debug.isOn() ) Debug.outln( "getClassesForName( " + className + " ) returning " + classList );
      return classList;
    }

  public static Collection<String> getPackageStrings(Package[] packages) {
    Set<String> packageStrings = new TreeSet<String>();
    if ( Utils.isNullOrEmpty( packages ) ) {
      packages = Package.getPackages();
    }
    for (Package aPackage : packages ) {
      packageStrings.add(aPackage.getName());
    }
    return packageStrings;
  }
  
  public static boolean isPackageName( String packageName ) {
    Package[] packages = Package.getPackages();
    for (Package aPackage : packages ) {
      String packageString = aPackage.getName();
      if ( packageString.startsWith( packageName ) ) {
        if ( packageString.charAt( packageName.length() ) == '.' ) return true;
      }
    }
//    Collection<String> packageStrings = getPackageStrings( null );
//    if ( packageStrings.contains( packageName ) ) return true;
//    for ( String packageString : packageStrings ) {
//      if ( packageString.startsWith( packageName ) ) {
//        if ( packageString.charAt( packageName.length() ) == '.' ) return true;
//      }
//    }
    return false;
  }
  
  /**
   * @param c
   * @param packageName
   * @return whether the Class is in the package with the given name
   */
  public static boolean inPackage( Class<?> c, String packageName ) {
    return isInPackage( c, packageName );
  }
  /**
   * @param c
   * @param packageName
   * @return whether the Class is in the package with the given name
   */
  public static boolean isInPackage( Class<?> c, String packageName ) {
    if ( c.getPackage().getName().equals( packageName ) ) {
      if ( Debug.isOn() ) Debug.errln("Found package! " + packageName );
      return true;
    }
    return false;
  }

  public static String simpleName( String longName ) {
    if ( longName == null ) return null;
    int pos = longName.lastIndexOf( "." );
    return longName.substring( pos+1 ); // pos is -1 if no '.'
  }

  public static String getFullyQualifiedName( String classOrInterfaceName,
                                              boolean doTypeParameters ) {
    String typeParameters = "";
    if ( classOrInterfaceName.contains( "<" )
         && classOrInterfaceName.contains( ">" ) ) {
      typeParameters = parameterPartOfName( classOrInterfaceName, false );
      // TODO -- how does this work for multiple parameters? e.g., Map<String,Float>
      String check = replaceAllGenericParameters( typeParameters, '~' );
      Assert.assertFalse( check.contains( "," ) );
      if ( typeParameters != null && typeParameters.contains( "Customer" ) ) {
        Debug.breakpoint();
      }
      typeParameters = "<" + ( doTypeParameters
                               ? getFullyQualifiedName( typeParameters, true )
                               : getNonPrimitiveClassName( typeParameters ) ) + ">";
      classOrInterfaceName =
          classOrInterfaceName.substring( 0, classOrInterfaceName.indexOf( '<' ) );
    }
    List< String > names = getFullyQualifiedNames( classOrInterfaceName );
    if ( Utils.isNullOrEmpty( names ) ) {
      names = getFullyQualifiedNames( simpleName( classOrInterfaceName ) );
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
    //if ( Debug.isOn() ) Debug.outln( "getFullyQualifiedNames( " + simpleClassOrInterfaceName
    //             + " ): packages = " + packageStrings );
    for (String aPackage : packageStrings) {
        try {
            String fqn = aPackage + "." + simpleClassOrInterfaceName;
            Class.forName(fqn);
            fqns.add(fqn);
        } catch (NoClassDefFoundError e) {
          // Ignore
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
    Class< ? > classForName = getClassForName( className, null, preferredPackage, false );
    if ( classForName == null ) {
      System.err.println( "Couldn't find the class " + className
                          + " to get constructor with args=" + Utils.toString( args, false ) );
      return null;
    }
    return getConstructorForArgs( classForName, args );
  }

  public static Constructor< ? > getConstructorForArgTypes( String className,
                                                            Class<?>[] argTypes,
                                                            String preferredPackage ) {
    Class< ? > classForName = getClassForName( className, null, preferredPackage, false );
    if ( classForName == null ) {
      System.err.println( "Couldn't find the class " + className
                          + " to get constructor with args=" + Utils.toString( argTypes, false ) );
      return null;
    }
    return getConstructorForArgTypes( classForName, argTypes );
  }

  public static Class<?>[] getClasses( Object[] objects ) {
    //return toClass( objects );
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
                   + Utils.toString( args ) );
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
    boolean nonStaticInnerClass = isInnerClass( eventClass );
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
        getConstructorForArgs( eventClass, newArgs );
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
    if ( atc.best != null && !atc.allNonNullArgsMatched ) {
      System.err.println( "constructor returned (" + atc.best
                          + ") only matches " + atc.mostMatchingArgs
                          + " args: " + Utils.toString( argTypes, false ) );
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
      if ( atc.best != null && !atc.allNonNullArgsMatched ) {
        System.err.println( "constructor returned (" + atc.best
                            + ") only matches " + atc.mostMatchingArgs
                            + " args: " + Utils.toString( argTypes, false ) );
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
                   + ", argTypes=" + Utils.toString( argTypes ) + " )" );
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

  public static boolean isInnerClass( Class< ? > eventClass ) {
    return ( !Modifier.isStatic( eventClass.getModifiers() )
             && eventClass.getEnclosingClass() != null );
  }

  public static Class< ? > getClassFromClasses( List< Class< ? > > classList,
                                                String specifier,
                                                String preferredPackage ) {
    if ( Utils.isNullOrEmpty( classList ) ) {
      return null;
    }
    if ( classList.size() > 1 ) {
      if ( Debug.isOn() ) {
        Debug.outln( "getClassFromClasses(" + classList + ", specifier="
                     + specifier + ", preferredPackage=" + preferredPackage
                     + ")" );
        Debug.outln( "Got multiple class candidates for constructor! "
                     + classList );
      }
      Class<?> bestCls = null;
      int bestLength = Integer.MAX_VALUE;
      int bestLengthOfCommonPreferredPkgPrefix = 0;
      int bestLengthOfCommonPkgPrefix = 0;
      //boolean bestHasMember = false;
      boolean bestHasSpecifier = false;
      boolean bestInPreferredPackage = false;
      
      for ( Class<?> c : classList ) {
        boolean best = false;
        int lengthOfCommonPkgPrefix = Integer.MAX_VALUE;
        int lengthOfCommonPreferredPkgPrefix = Integer.MAX_VALUE;
        int length = 0;
        boolean hasSpecifier = hasMember( c, specifier );
        boolean inPreferredPackage = inPackage( c, preferredPackage );
        if ( !inPreferredPackage ) {
          lengthOfCommonPreferredPkgPrefix =
              lengthOfCommonPrefix( c.getPackage().getName(), preferredPackage );
          lengthOfCommonPkgPrefix =
              lengthOfCommonPrefix( c.getPackage().getName(),
                                    ClassUtils.class.getPackage().getName() );
          length = c.getPackage().getName().length();
        }
        best = ( bestCls == null ||
                 ( hasSpecifier && !bestHasSpecifier ) ||
                 ( inPreferredPackage && !bestInPreferredPackage ) ||
                 ( lengthOfCommonPreferredPkgPrefix > bestLengthOfCommonPreferredPkgPrefix ) ||
                 ( ( lengthOfCommonPreferredPkgPrefix == bestLengthOfCommonPreferredPkgPrefix ) &&
                     ( ( lengthOfCommonPkgPrefix > bestLengthOfCommonPkgPrefix ) || 
                       ( ( lengthOfCommonPkgPrefix == bestLengthOfCommonPkgPrefix ) &&
                         ( length < bestLength ) ) ) ) );
        if ( best ) {
          bestCls = c;
          bestLengthOfCommonPreferredPkgPrefix = lengthOfCommonPreferredPkgPrefix;
          bestLengthOfCommonPkgPrefix = lengthOfCommonPkgPrefix;
          bestLength = length;
        }
      }
      System.out.println( "Best class " + bestCls.getCanonicalName()
                          + " has length, " + bestLength
                          + ", and common prefix length of packages, "
                          + bestLengthOfCommonPkgPrefix + ", pkg="
                          + bestCls.getPackage().getName()
                          + ", ClassUtils pkg="
                          + ClassUtils.class.getPackage().getName()
                          + ", and common prefix length of packages, "
                          + bestLengthOfCommonPreferredPkgPrefix
                          + ", preferredPackage pkg=" + preferredPackage );
      if ( Debug.isOn() && !bestHasSpecifier && !bestInPreferredPackage ) {
        Debug.errln( "Warning! Picked non-matching candidate from multiple class candidates! " + classList );
      }

      return bestCls;
    }
    assert( classList.size() == 1 );
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
        new Class< ? >[] { Math.class, //StringUtils.class,
                           Integer.class,
                           Double.class, Character.class, Boolean.class,
                           String.class,
                           //org.apache.commons.lang.ArrayUtils.class,
                           Arrays.class };
    for ( Class<?> c : classes ) {
      Method m = getMethodForArgTypes( c, functionName, argTypes );
      if ( m != null ) return m;
    }
    return null;
  }

  public static Method getMethodForArgs( String className,
                                         String preferredPackage,
                                         String callName,
                                         Object... args ) {
    Class< ? > classForName = getClassForName( className, callName, preferredPackage, false );
    if ( Debug.errorOnNull( "Couldn't find the class " + className + " for method "
                      + callName + ( args == null ? "" : Utils.toString( args, false ) ),
                      classForName ) ) {
      return null;
    }
    return getMethodForArgs( classForName, callName, args );
  }

  public static Method getMethodForArgs( Class< ? > cls, String callName,
                                           Object... args ) {
      if ( Debug.isOn() ) Debug.outln( "getMethodForArgs( cls=" + cls.getName() + ", callName="
          + callName + ", args=" + Utils.toString( args ) + " )" );
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

  private static int lengthOfCommonPrefix( String s1, String s2 ) {
    if ( s1 == null || s2 == null ) return 0;
    int i=0;
    for ( ; i < Math.min( s1.length(), s2.length() ); ++i ) {
      if ( s1.charAt(i) != s2.charAt(i) ) {
        break;
      }
    }
    return i;
  }
  
  public static Method getMethodForArgTypes( String className,
                                             String preferredPackage,
                                             String callName,
                                             Class<?>[] argTypes,
                                             boolean complainIfNotFound ) {
    //Debug.turnOff();  // DELETE ME -- FIXME
    Debug.outln("=========================start===============================");
    //Debug.errln("=========================start===============================");
    //Class< ? > classForName = getClassForName( className, preferredPackage, false );
    String classNameNoParams = noParameterName( className );
    List< Class< ? > > classesForName = getClassesForName( classNameNoParams, false );
    //Debug.err("classForName = " + classForName );
    Debug.outln("classesForName = " + classesForName );
    if ( Utils.isNullOrEmpty( classesForName ) ) {
      if ( complainIfNotFound ) {
        System.err.println( "Couldn't find the class " + className + " for method "
                     + callName
                     + ( argTypes == null ? "" : Utils.toString( argTypes, false ) ) );
      }
      Debug.outln("===========================end==============================");
      //Debug.errln("===========================end==============================");
      //Debug.turnOff();  // DELETE ME -- FIXME
      return null;
    }
    Method best = null;
    Method nextBest = null;
    int charsInCommonWithPreferredPackage = 0;
    boolean tie = false;
    for ( Class<?> cls : classesForName ) {
      //Method m = getMethodForArgTypes( classForName, callName, argTypes );
      Method m = getMethodForArgTypes( cls, callName, argTypes, false );
      if ( m != null ) {
        int numCharsInCommon = lengthOfCommonPrefix(preferredPackage,cls.getCanonicalName());
        if ( best == null ) {
          best = m;
          charsInCommonWithPreferredPackage = numCharsInCommon;
        } else {
          if ( numCharsInCommon > charsInCommonWithPreferredPackage ) {
            best = m;
            charsInCommonWithPreferredPackage = numCharsInCommon;
          } else if ( charsInCommonWithPreferredPackage == numCharsInCommon ) {
            tie = true;
            nextBest = m;
          }
        }
      }
    }
    if ( tie ) {
      System.err.println( "Warning! Multiple candidate methods found by getMethodForArgTypes("
          + className + "." + callName
          + Utils.toString( argTypes, false )
          + "): (1) " + best + " (2) " + nextBest );
    }
    if ( Debug.isOn() ) Debug.errorOnNull( "getMethodForArgTypes(" + className + "." + callName
                 + Utils.toString( argTypes, false ) + "): Could not find method!", best );
    Debug.outln("===========================end==============================");
    //Debug.errln("===========================end==============================");
    //Debug.turnOff();  // DELETE ME -- FIXME
    return best;
  }

  public static Method oldGetMethodForArgTypes( String className,
                                                String preferredPackage,
                                                String callName,
                                                Class<?>[] argTypes,
                                                boolean complainIfNotFound ) {
    Class< ? > classForName = getClassForName( className, callName, preferredPackage, false );
    if ( classForName == null ) {
      if ( complainIfNotFound ) {
        System.err.println( "Couldn't find the class " + className + " for method "
                     + callName
                     + ( argTypes == null ? "" : Utils.toString( argTypes, false ) ) );
      }
      return null;
    }
    Method m = getMethodForArgTypes( classForName, callName, argTypes );
    if ( Debug.isOn() ) Debug.errorOnNull( "getMethodForArgTypes(" + className + "." + callName
                 + Utils.toString( argTypes, false ) + "): Could not find method!", m );
    return m;
  }

  public static Method getMethodForArgTypes( Class< ? > cls, String callName,
                                             Class<?>... argTypes ) {
    return getMethodForArgTypes( cls, callName, argTypes, true );
  }
  public static Method getMethodForArgTypes( Class< ? > cls, String callName,
                                             Class<?>[] argTypes, boolean complain ) {
  //    return getMethodForArgTypes( cls, callName, argTypes, 10.0, 2.0, null );
  //  }
  //  public static Method getMethodForArgTypes( Class< ? > cls, String callName,
  //                                             Class<?>[] argTypes,
  //                                             double numArgsCost,
  //                                             double argMismatchCost,
  //                                             Map< Class< ? >, Map< Class< ? >, Double > > transformCost ) {
      if ( argTypes == null ) argTypes = new Class<?>[] {};
      if ( Debug.isOn() ) Debug.outln( "getMethodForArgTypes( cls=" + cls.getName() + ", callName="
                   + callName + ", argTypes=" + Utils.toString( argTypes ) + " )" );
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
      if ( Debug.isOn() ) Debug.outln( "--> got methods: " + Utils.toString( methods ) );
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
      if ( Debug.isOn() ) Debug.errln( "getMethodForArgTypes( cls="
                                       + cls.getName() + ", callName="
                                       + callName + ", argTypes="
                                       + Utils.toString( argTypes )
                                       + " ): method returned (" + atc.best
                                       + ") only matches "
                                       + atc.mostMatchingArgs + " args: "
                                       + Utils.toString( argTypes ) );
      } else if ( atc.best == null && complain ) {
        System.err.println( "method " + callName + "(" + Utils.toString( argTypes ) + ")"
                            + " not found for " + cls.getSimpleName() );
      }
      return (Method)atc.best;
    }

  /**
   * Find and invoke the named method from the given object with the given
   * arguments.
   * 
   * @param o
   * @param methodName
   * @param args
   * @param suppressErrors
   * @return in a Pair whether the invocation was successful and the return
   *         value (or null)
   */
  public static Pair< Boolean, Object > runMethod( boolean suppressErrors,
                                                   Object o, String methodName,
                                                   Object... args ) {
    Method m = getMethodForArgs( o.getClass(), methodName, args );
    return runMethod( suppressErrors, o, m, args );
  }

  /**
   * Invoke the method from the given object with the given arguments.
   * 
   * @param o
   * @param methodName
   * @param args
   * @param suppressErrors
   * @return in a Pair whether the invocation was successful and the return
   *         value (or null)
   */
  public static Pair< Boolean, Object > runMethod( boolean suppressErrors,
                                                   Object o, Method method,
                                                   Object... args ) {
    Pair< Boolean, Object > p = new Pair< Boolean, Object >( false, null );
    List<Throwable> errors = new ArrayList< Throwable >();
    try {
      p = runMethod( o, method, args );
    } catch ( IllegalArgumentException e ) {
      if ( !suppressErrors ) {
        errors.add( e );
      }
    } catch ( IllegalAccessException e ) {
      if ( !suppressErrors ) {
        errors.add( e );
      }
    } catch ( InvocationTargetException e ) {
      if ( !suppressErrors ) {
        errors.add( e );
      }
    }
    if ( !p.first && isStatic( method ) && o != null ) {
      List< Object > l = Utils.newList( o );
      l.addAll( Arrays.asList( args ) );
      p = runMethod( true, null, method, l.toArray() );
      if ( !p.first && l.size() > 1 ) {
        p = runMethod( true, null, method, new Object[] { o } );
      }
    }
    if ( !suppressErrors && !p.first ) {
      Debug.error( false,
                   "runMethod( " + o + ", " + method + ", " +
                       Utils.toString( args, true ) + " ) failed!" );
    }
    for ( Throwable e : errors ) {
      e.printStackTrace();
    }
    return p;
  }
  
  public static boolean isStatic( Class<?> cls ) {
    if ( cls == null ) return false;
    return ( Modifier.isStatic( cls.getModifiers() ) );
  }

  public static boolean isStatic( Member method ) {
    if ( method == null ) return false;
    return ( Modifier.isStatic( method.getModifiers() ) );
  }

  /**
   * Find and invoke the named method from the given object with the given
   * arguments.
   * 
   * @param o
   * @param methodName
   * @param args
   * @return in a Pair whether the invocation was successful and the return
   *         value (or null)
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static Pair< Boolean, Object >
      runMethod( Object o, String methodName,
                 Object... args ) throws IllegalArgumentException,
                                         IllegalAccessException,
                                         InvocationTargetException {
    Method m = getMethodForArgs( o.getClass(), methodName, args );
    return runMethod( o, m, args );
  }
  
  /**
   * Invoke the method from the given object with the given arguments.
   * 
   * @param o
   * @param m
   * @param args
   * @return in a Pair whether the invocation was successful and the return
   *         value (or null)
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static Pair< Boolean, Object >
    runMethod( Object o, Method m,
               Object... args ) throws IllegalArgumentException,
                                       IllegalAccessException,
                                       InvocationTargetException {
    Pair< Boolean, Object > p = new Pair< Boolean, Object >( false, null );
    if ( m == null ) {
      return p;
    }
    p.second = m.invoke( o, args );
    p.first = true;
    return p;
  }
  
  /**
   * @param o
   * @param fieldName
   * @return
   */
  public static Object getFieldValue( Object o, String fieldName ) {
    return getFieldValue( o, fieldName, false );
  }

  /**
   * Get the value of the object's field with with the given fieldName using
   * reflection.
   * 
   * @param o
   *          the object whose field value is sought
   * @param fieldName
   * @param suppressExceptions
   * @return the value of the field
   */
  public static Object getFieldValue( Object o, String fieldName,
                                      boolean suppressExceptions ) {
    if ( o == null || Utils.isNullOrEmpty( fieldName ) ) {
      return null;
    }
    Exception ex = null;
    Field f = null;
    try {
      f = o.getClass().getField( fieldName );
      if ( !f.isAccessible() ) f.setAccessible( true );
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
    if ( !suppressExceptions && f == null && ex != null ) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
    }
    return null;
  }

  /**
   * Return true if and only if the object has a field with the given name.
   * @param o
   * @param fieldName
   * @return
   */
  public static boolean hasField( Object o, String fieldName ) {
    return getField( o, fieldName, true ) != null;
  }

  /**
   * Get the Field of the Class of the object with the given fieldName using
   * reflection.
   * 
   * @param o
   *          the object whose field is sought
   * @param fieldName
   * @param suppressExceptions
   *          if true, do not throw or write out any exceptions
   * @return the Field of the Class
   */
  public static Field getField( Object o, String fieldName, boolean suppressExceptions ) {
    if ( o == null || Utils.isNullOrEmpty( fieldName ) ) {
      if ( !suppressExceptions ) {
        Debug.error("Null input not allowed! getField(" + o + ", " + fieldName + ")");
      }
      return null;
    }
    Field f = null;
    Class< ? > cls = null;
    if ( o instanceof Class< ? > ) {
      cls = (Class< ? >)o;
      f = getField(cls, fieldName, true );
    }
    if ( f == null ) {
      cls = o.getClass();
      f = getField(cls, fieldName, true );
    }
    // TODO -- remove this and handle from caller!
    if ( f == null && o instanceof gov.nasa.jpl.ae.event.Parameter ) {
      f = getField( ( (gov.nasa.jpl.ae.event.Parameter)o ).getValueNoPropagate(),
                    fieldName, suppressExceptions );
    }
    return f;
  }

  /**
   * Get the Field of the Class with the given fieldName using reflection.
   * 
   * @param cls
   *          the Class whose field is sought
   * @param fieldName
   * @param suppressExceptions
   *          if true, do not throw or write out any exceptions
   * @return the Field of the Class
   */
  public static Field getField( Class< ? > cls, String fieldName, boolean suppressExceptions ) {

    if ( cls == null || Utils.isNullOrEmpty( fieldName ) ) {
      if ( !suppressExceptions ) {
        Debug.error("Null input not allowed! getField(" + cls + ", " + fieldName + ")");
      }
      return null;
    }
    Exception ex = null;
    Field f = null;
    try {
      f = cls.getField( fieldName );
      return f;
    } catch ( NoSuchFieldException e ) {
      ex = e;
    } catch ( SecurityException e ) {
      ex = e;
    } catch ( IllegalArgumentException e ) {
      ex = e;
    }
    if ( !suppressExceptions && f == null && ex != null ) {
      ex.printStackTrace();
    }
    return null;
  }

  public static boolean hasMember( Class< ? > c, String memberName ) {
    boolean has = hasField( c, memberName );
    if ( !has ) has = hasMethod( c, memberName );
    return has;
  }

  
  public static String parameterPartOfName( String longName ) {
    return parameterPartOfName( longName, true );
  }
  public static String parameterPartOfName( String longName,
                                            boolean includeBrackets ) {
    int pos1 = longName.indexOf( '<' );
    if ( pos1 == -1 ) {
      return null;
    }
    int pos2 = longName.lastIndexOf( '>' );
    assert( pos2 >= 0 );
    if ( pos2 == -1 ) return null;
    if ( !includeBrackets ) {
      pos1 += 1;
      pos2 -=1;
    }
    String paramPart = longName.substring( pos1, pos2+1 ).trim();
    return paramPart;
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

  public static String toString( Class<?> type ) {
      if (type == null) return "";
      if ( type.isArray() ) {
        Class<?> compType = type.getComponentType();
        String compString = toString( compType );
        if ( Utils.isNullOrEmpty( compString ) ) {
            return "";
        }
        compString = Utils.replaceSuffix(compString, ".class", "" );
        return compString + "[]" + ".class";
      }
      StringBuffer sb = new StringBuffer();
      String typeName = type.getName();
      if ( typeName != null ) typeName = typeName.replace( '$', '.' );
      sb.append( ClassUtils.noParameterName( typeName ) 
                           + ".class" );
      return sb.toString();
  }
  
  /**
   * @param type
   * @return whether the input type is a number class or a number primitive.
   */
  public static boolean isNumber( Class< ? > type ) {
    if ( type == null ) return false;
    Class<?> forPrim = classForPrimitive( type );
    if ( forPrim != null ) type = forPrim;
    return ( Number.class.isAssignableFrom( type ) );
  }

  /**
   * @param type
   * @return whether the input type has an integer domain (int, short, long, etc).
   */
  public static boolean isInteger( Class< ? > type ) {
    if ( type == null ) return false;
    return ( Integer.class.isAssignableFrom( type ) );
//    Class<?> forPrim = classForPrimitive( type );
//    if ( forPrim != null ) type = forPrim;
  }

  /**
   * Convert a number of one type to a number of another type.
   * 
   * @param n
   *            the number to convert
   * @param cls
   *            the type of number to which to convert
   * @return the converted number or null if the cast fails or an exception is
   *         caught
   */
  public static Number castNumber( Number n, Class<? extends Number> cls ) {
    try {
      Class<?> c = ClassUtils.classForPrimitive( cls );
      if ( c == null ) c = cls;
      if ( c == Long.class ) return (Long)n.longValue(); 
      if ( c == Short.class ) return (Short)n.shortValue(); 
      if ( c == Double.class ) return (Double)n.doubleValue(); 
      if ( c == Integer.class ) return (Integer)n.intValue(); 
      if ( c == Float.class ) return (Float)n.floatValue(); 
//          if ( c == Character.class ) return (TT)(Character)n.shortValue();
//        if ( c == Long.class ) return cls.cast( n.longValue() ); 
    } catch ( Exception e ) {
      // ignore
    }
    return null;
  }

  /**
   * @param cls
   * @param methodName
   * @return all public methods of {@code cls} (or inherited by {@code cls})
   *         that have the simple name, {@code methodName}.
   */
  public static Method[] getMethodsForName( Class< ? > cls, String methodName ) {
    List< Method > methods = new ArrayList< Method >();
    for ( Method m : cls.getMethods() ) {
      if ( m.getName().equals(methodName) ) {
        methods.add( m );
      }
    }
    Method[] mArr = new Method[methods.size()];
    boolean succ = Utils.toArrayOfType( methods, mArr, Method.class );
    if ( !succ ) {
      Debug.error( "Error! Cast to Method[] failed for getMethodsForName(" +
                   cls + ", " + methodName + ")" );
    }
    return mArr;
  }

  /**
   * @param o
   * @param methodName
   * @return whether the named method can be called from the Object, o
   */
  public static boolean hasMethod( Object o, String methodName ) {
    if ( o instanceof Class && hasMethod( (Class< ? >)o, methodName ) ) return true;
    return hasMethod( o.getClass(), methodName );
  }

  /**
   * @param cls
   * @param methodName
   * @return whether the Class has a method with the given name 
   */
  public static boolean hasMethod( Class< ? > cls, String methodName ) {
    return !Utils.isNullOrEmpty( getMethodsForName( cls, methodName ) );
  }

  /**
   * @param o
   * @param methodName
   * @param args
   * @return whether the named method can be called from the given Object with
   *         the given arguments
   */
  public static boolean hasMethod( Object o, String methodName, Object[] args ) {
    if ( o instanceof Class && hasMethod( (Class< ? >)o, methodName, args ) ) return true;
    return hasMethod( o.getClass(), methodName, args );
  }

  /**
   * @param cls
   * @param methodName
   * @param args
   * @return whether the Class has a method with the given name that can be called with the
   *         the given arguments
   */
  public static boolean hasMethod( Class< ? > cls, String methodName, Object[] args ) {
    return getMethodForArgs( cls, methodName, args ) != null;
  }

  /**
   * This function helps avoid compile warnings by just having the warning here.
   * @param tt
   * @return Collection.class cast as having a type parameter
   */
  public static <TT> Class< Collection<TT> > getCollectionClass( TT tt ) {
    return (Class< Collection<TT> >)Collection.class.asSubclass( Collection.class  );
//    Collection<TT> coll = new ArrayList< TT >();
//    Class< Collection<TT> > ccls = (Class< Collection<TT> >)coll.getClass();
//    return ccls;
  }

  /**
   * This function helps avoid compile warnings by just having the warning here.
   * @param ttcls
   * @return Collection.class cast as having a type parameter
   */
  public static <TT> Class< Collection<TT> > getCollectionClass( Class<TT> ttcls ) {
    return (Class< Collection<TT> >)Collection.class.asSubclass( Collection.class  );
//    Collection<TT> coll = new ArrayList< TT >();
//    //Class< Collection<TT> > ccls = (Class< Collection<TT> >)coll.getClass();
//    Class< Collection<TT> > ccls = (Class< Collection<TT> >)coll.getClass().asSubclass( Collection.class );
//    return ccls;
  }

  //  public static <TT> Collection<TT> makeCollection( TT tt, Class<TT> cls ) {
  //    ArrayList< TT > ttList = new ArrayList< TT >();
  //    ttList.add( tt );
  //    return ttList;
  //  }

  public static < TT > Collection< TT >
      makeCollection( TT tt, Class< ? extends TT > cls ) {
    // return Utils2.newList( tt );
    ArrayList< TT > ttList = new ArrayList< TT >();
    ttList.add( tt );
    return ttList;
  }

//  public static < TT > Collection< TT >
//      makeCollection( Parameter< TT > tt, Class< ? extends TT > cls ) {
//    // return Utils2.newList( tt.getValue() );
//    ArrayList< TT > ttList = new ArrayList< TT >();
//    ttList.add( tt.getValue() );
//    return ttList;
//  }
  
  public static class A {
    int a;
    public A( int a ) { this.a = a; }
    public int get() { return a; }
  }
  public static class B extends A {
    int b;
    public B( int a, int b ) { super(a); this.b = b; }
    public int get() { return b; }
  }
  public static void main( String[] args ) {
    A a = new A(1);
    System.out.println("A.class = " + A.class );
    B b = new B(2,3);
    Collection<B> collB = new ArrayList<B>();
    ArrayList<B> arrListB = new ArrayList<B>();

    Class<?> cls1 = collB.getClass();
    System.out.println("collB.getClass() as Class<?> = " + cls1 );

    Class< ? extends Collection > clsb = collB.getClass();
    System.out.println("collB.getClass() as Class< ? extends Collection > = " + clsb );

    Class< ? extends ArrayList > clsa = arrListB.getClass();
    System.out.println("arrListB.getClass() as Class< ? extends ArrayList > = " + clsa );

    Class< ? extends Collection > clssb = arrListB.getClass().asSubclass( Collection.class );
    System.out.println("arrListB.getClass().asSubclass( Collection.class ) as Class< ? extends Collection > = " + clssb );
    
  }
  public static Field[] getAllFields( Class< ? extends Object > cls ) {
    List<Field> fieldList =  getListOfAllFields( cls );
    Field[] fieldArr = new Field[fieldList.size()];
    fieldList.toArray( fieldArr );
    return fieldArr;
  }
  public static List<Field> getListOfAllFields( Class< ? extends Object > cls ) {
    if ( cls == null ) return null;
    ArrayList<Field> fieldList = new ArrayList< Field >();
    for ( Field f : cls.getDeclaredFields() ) {
      f.setAccessible( true );
      fieldList.add( f );
    }
    List< Field > superFields = getListOfAllFields( cls.getSuperclass() );
    if ( superFields != null ) fieldList.addAll( superFields );
    return fieldList;
  }
  
  /**
   * Try to convert an object into one of the specified class.
   * 
   * @param o
   *          the object to convert into type cls
   * @param cls
   *          the {@link Class} of the object to return
   * @param propagate
   * @return an object of the type specified or null if the conversion was
   *         unsuccessful.
   */
  public static <T> Pair<Boolean, T> coerce( Object o, Class<T> cls, boolean propagate ) {
    // REVIEW -- How is Wraps involved in wrapping in Expression? Can evaluate()
    // be moved out of Expression into a Wraps factory helper, or part into
    // ClassUtils and the other to Wraps?
    // TODO -- REVIEW -- Can null be a valid return value? Should
    // Expression.evaluate() also return a pair?
    if ( o == null ) return new Pair< Boolean, T >( false, null );
    Object v = //Expression.
        evaluate( o, cls, propagate );
    Boolean succ = null;
    T t = null;
    if ( v != null ) {
      succ = true;
      try {
        if ( cls == null ) {
          t = (T)v;
        } else {
          t = cls.cast( v );
        }
      } catch ( ClassCastException e ) {
        succ = false;
      }
      if ( t == null ) succ = false;
    }
    return new Pair< Boolean, T >( succ, t );
  }

  /**
   * Evaluate/dig or wrap the object of the given type cls from the object o,
   * which may be a Parameter or an Expression.
   * 
   * @param object
   *          the object to evaluate
   * @param cls
   *          the type of the object to find
   * @return o if o is of type cls, an object of type cls that is an evaluation
   *         of o, or null otherwise.
   */
  public static <TT> TT evaluate( Object object, Class< TT > cls,
                                  boolean propagate ) throws ClassCastException {
    if ( object == null ) return null;
    // Check if object is already what we want.
    if ( cls != null && cls.isInstance( object ) || cls == object.getClass() ) {
      return (TT)object;
    }
    
    // Try to evaluate object or dig inside to get the object of the right type. 
    Object value = null;
//    if ( object instanceof Parameter ) {
//      value = ( (Parameter)object ).getValue( propagate );
//      return evaluate( value, cls, propagate, allowWrapping );  
//    } 
//    else if ( object instanceof Expression ) {
//      Expression< ? > expr = (Expression<?>)object;
//      if ( cls != null && cls.isInstance( expr.expression ) &&
//           expr.form != Form.Function) {
//        return (TT)expr.expression;
//      }
//      value = expr.evaluate( propagate );
//      return evaluate( value, cls, propagate, allowWrapping );  
//    }
//    else if ( object instanceof Call) {
//      value = ( (Call)object ).evaluate( propagate );
//      return evaluate( value, cls, propagate, allowWrapping );  
//    } else 
    if ( cls != null && ClassUtils.isNumber( cls ) &&
                ClassUtils.isNumber( object.getClass() ) ) {
      try {
//        int f = 5;
//        Integer t = 3;
//        f = (int)(Integer)t.intValue();
        Number n = (Number)object;
        Class<?> c = ClassUtils.classForPrimitive( cls );
        if ( c == null ) c = cls;
        // TODO -- instead of returning here, assign to object and reuse try/catch below
        if ( c == Long.class ) return (TT)(Long)n.longValue(); 
        if ( c == Short.class ) return (TT)(Short)n.shortValue(); 
        if ( c == Double.class ) return (TT)(Double)n.doubleValue(); 
        if ( c == Integer.class ) return (TT)(Integer)n.intValue(); 
        if ( c == Float.class ) return (TT)(Float)n.floatValue(); 
//        if ( c == Character.class ) return (TT)(Character)n.shortValue();
//      if ( c == Long.class ) return cls.cast( n.longValue() ); 
//      if ( c == Short.class ) return cls.cast( n.shortValue() ); 
//      if ( c == Double.class ) return cls.cast( n.doubleValue() ); 
//      if ( c == Integer.class ) return cls.cast( n.intValue() ); 
//      if ( c == Float.class ) return cls.cast( n.floatValue() ); 
//      if ( c == Character.class ) return cls.cast( n );
      } catch ( Exception e ) {
        // ignore
      }
    }
//    else if ( allowWrapping && cls != null ){
//      // If evaluating doesn't work, maybe we need to wrap the value in a parameter.
//      if ( cls.isAssignableFrom( Parameter.class ) ) {
//        if ( Debug.isOn() ) Debug.error( false, "Warning: wrapping value with a parameter with null owner!" );
//        return (TT)( new Parameter( null, null, object, null ) );
//      } else if ( cls.isAssignableFrom( Expression.class ) ) {
//        return (TT)( new Expression( object ) );
//      }
//    }
    TT r = null;
    try {
      r = (TT)object;
    } catch ( ClassCastException cce ) {
      Debug.errln( "Warning! No evaluation of " + object + " with type " + cls.getName() + "!" );
      throw cce;
    }
    if ( cls != null && cls.isInstance( r ) || ( r != null && cls == r.getClass() ) ) {
      return r;
    }
    return null;
  }
  
  /**
   * Determine whether the values of two objects are equal by evaluating them. 
   * @param o1
   * @param o2
   * @return whether the evaluations of o1 and o2 are equal.
   * @throws ClassCastException
   */
  public static boolean valuesEqual( Object o1, Object o2 ) throws ClassCastException {
    return valuesEqual( o1, o2, null, false, false );
  }
  public static boolean valuesEqual( Object o1, Object o2, Class<?> cls ) throws ClassCastException {
    return valuesEqual( o1, o2, cls, false, false );
  }
  public static boolean valuesEqual( Object o1, Object o2, Class<?> cls,
                                     boolean propagate,
                                     boolean allowWrapping ) throws ClassCastException {
    if ( o1 == o2 ) return true;
    if ( o1 == null || o2 == null ) return false;
    if ( (o1 instanceof Float && o2 instanceof Double ) || (o2 instanceof Float && o1 instanceof Double ) ) {
      Debug.out( "" );
    }
    Object v1 = evaluate( o1, cls, propagate );//, false );
    Object v2 = evaluate( o2, cls, propagate );//, false );
    if ( Utils.valuesEqual( v1, v2 ) ) return true;
    Class< ? > cls1 = null;
    if ( v1 != null ) {
      cls1 = v1.getClass();
    }
    if ( v1 != o1 || v2 != o2 ) {
      if ( cls1 != null && cls1 != cls && valuesEqual( v2, v1, cls1 ) ) return true;
    }
    if ( v2 != null ) {
      if ( v1 != o2 || v2 != o1 ) {
        Class< ? > cls2 = v2.getClass();
        if ( cls2 != cls && cls2 != cls1 && valuesEqual( v1, v2, cls2 ) ) return true;
      }
    }
    return false;
    /*
    Class< ? > cls1 =
        ( cls != null ) ? cls : ( ( v1 == null ) ? null : v1.getClass() );
    Class< ? > cls2 =
        ( cls != null ) ? cls : ( ( v2 == null ) ? null : v2.getClass() );
    v1 = evaluate( v1, cls1, propagate, allowWrapping );
    v2 = evaluate( v2, cls1, propagate, allowWrapping );
    if ( Utils.valuesEqual( v1, v2 ) ) return true;
    Class< ? > cls1 =
        ( cls != null ) ? cls : ( ( v1 == null ) ? null : v1.getClass() );
    Class< ? > cls2 =
        ( cls != null ) ? cls : ( ( v2 == null ) ? null : v2.getClass() );
    Object v1 = evaluate( o1, cls1, propagate, allowWrapping );
    Object v2 = evaluate( o2, cls1, propagate, allowWrapping );
    if ( Utils.valuesEqual( v1, v2 ) ) return true;
    if ( cls1 != cls2 ) {
      v1 = evaluate( o1, cls2, propagate, allowWrapping );
      v2 = evaluate( o2, cls2, propagate, allowWrapping );      
    }
    return Utils.valuesEqual( v1, v2 );
    */ 
  }

  /**
   * @param o
   * @return a collection of o's Class, superclasses, and interfaces
   */
  public static List< Class< ? > > getAllClasses( Object o ) {
    Class< ? > cls = (Class< ? >)(o instanceof Class ? o : o.getClass() );
    HashSet< Class< ? > > set = new HashSet< Class< ? > >();
    List< Class< ? > > classes = new ArrayList< Class< ? > >();
    List<Class<?>> queue = new ArrayList< Class<?> >();
    queue.add( cls );
    while ( !queue.isEmpty() ) { // REVIEW -- could probably use iterator and use classes as the queue
      Class< ? > c = queue.get( 0 );
      queue.remove( 0 );
      if ( set.contains( c ) ) continue;
      Class< ? > parent = cls.getSuperclass();
      if ( parent != null && !set.contains( parent ) ) queue.add( parent );
      queue.addAll( Arrays.asList( cls.getInterfaces() ) );
      classes.add( 0, c );
      set.add( c );
    }
    return classes;
  }
}
