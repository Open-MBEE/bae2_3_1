package gov.nasa.jpl.ae.util;

import gov.nasa.jpl.ae.event.ConstructorCall;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.ParameterListenerImpl;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Utils;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ClassData {

  /**
   * A struct for packaging a name, type, and value as Strings.
   */
  public static class Param implements Comparator< Param >,
                            Comparable< Param > {
    public String name;
    public String type;
    public String value;
  
    public Param( String name, String type, String value ) {
      this.name = name;
      this.type = type;
      this.value = value;
    }
  
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
      return "(" + name + ", " + type + ", " + value + ")";
    }

    /** 
     * Only the name and type are compared.
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo( Param o ) {
      return compare( this, o );
    }

    /** 
     * Only the name and type are compared.
     * (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare( Param o1, Param o2 ) {
      int compare = CompareUtils.compare( o1.name, o2.name );
      if ( compare != 0 ) return compare;
      compare = CompareUtils.compare( o1.type, o2.type );
      if ( compare != 0 ) return compare;
      return 0;
    }

  }

  public static final Set< MethodDeclaration > emptyMethodDeclarationSet =
      new TreeSet<MethodDeclaration>(new CompareUtils.GenericComparator< MethodDeclaration >());

  // protected String currentEnclosingClassName;
  // protected String currentScopedClassName;
  protected Map< String, Boolean > isStaticMap =
      new TreeMap< String, Boolean >();
  // Map: longName -> method name -> set of javaparser.MethodDeclarations
  protected Map< String, Map< String, Set< MethodDeclaration > > > methodTable =
      new TreeMap< String, Map< String, Set< MethodDeclaration > > >();
  // // Map class name (long?) -> set of javaparser.ConstructorDeclarations
  // protected Map< String, Set< ConstructorDeclaration> >
  // constructorDeclarations =
  // new TreeMap< String, Set< ConstructorDeclaration > >();

  // Map: inner class name -> enclosing class name
  // protected Map< String, Set< String > > innerToEnclosingClassNames =
  // new TreeMap< String, Set< String > >();
  // WARNING! TODO -- Two classes could nest classes with the same name, so
  // the Map<S,Set<S>> above is correct, but maybe not useful. Instead, will
  // keep the current enclosing class, and the current scoped name.
  protected Map< String, String > nestedToEnclosingClassNames =
      new TreeMap< String, String >();

  /**
   * The package for all of the classes.
   */
  protected String packageName = null;
  
  /**
   * Map: longName -> parameter name -> Param
   */
  protected Map< String, Map< String, Param > > paramTable =
      new TreeMap< String, Map< String, Param > >();

  /**
   * Parameters may be created for evaluation at parse time.
   */
  protected Map< ClassData.Param, Parameter< ? > > parameterMap =
      new TreeMap< ClassData.Param, Parameter<?> >();
  
  protected Map< String, ParameterListenerImpl > aeClasses = // REVIEW -- rename to aeObjects??!
      new TreeMap< String, ParameterListenerImpl >();

  /**
   * The long name of the class currently being processed.
   */
  protected String currentClass = null;

  protected ParameterListenerImpl currentAeClass = null;

  private int counter = 0;

  /**
   * Map: simpleName -> javaparser.CompilationUnit
   */
  private Map< String, CompilationUnit > classes =
      new TreeMap< String, CompilationUnit >();

  /**
   * The javaparser.CompilationUnit for the class currently being processed.
   */
  private CompilationUnit currentCompilationUnit = null;


  
  /**
   * Try to figure out the scope of the class name if an inner class, and return
   * the scoped class name.
   * 
   * @param className
   * @return
   */
  public String getClassNameWithScope( String className ) {
    if ( Utils.isNullOrEmpty( className ) ) return null;
    // Return input class name if in table.
    if ( paramTable.keySet().contains( className ) ) {
      // Note: this.paramTable is used because it is populated at the beginning.
      // this.methodTable could also be used. this.classes cannot be used since
      // it only contains classes processed so far.
      return className;
    }
    // See if the class is an inner class of the current class
    String classNameWithScope = this.currentClass + "." + className;
    if ( paramTable.keySet().contains( classNameWithScope ) ) {
      return classNameWithScope;
    }

    // Loop through existing class names and find those that end with the input
    // name. Pick the one that seems to be "best" and print a warning if not
    // sure.
    String otherClassName = null;
    for ( String n : paramTable.keySet() ) {
      if ( n.endsWith( className )
           && ( n.length() == className.length() || n.charAt( n.lastIndexOf( className ) - 1 ) == '.' ) ) {
        if ( otherClassName != null && otherClassName.endsWith( className ) ) {
          if ( n.endsWith( classNameWithScope ) ) {
            if ( otherClassName.endsWith( classNameWithScope ) ) {
              if ( n.contains( classNameWithScope ) ) {
                if ( otherClassName.contains( classNameWithScope ) ) {
                  System.err.println( "Warning! Got more than one candidate class for "
                                      + className
                                      + ": "
                                      + otherClassName
                                      + ", " + n );
                  if ( n.length() < otherClassName.length() ) {
                    otherClassName = n;
                  }
                } else {
                  otherClassName = n;
                }
              }
            } else {
              otherClassName = n;
            }
          }
        } else {
          otherClassName = n;
        }
      }
    }

    // if ( Utils.isNullOrEmpty( otherClassName ) ) {
    // otherClassName = ClassUtils.getFullyQualifiedName( className, false );
    // }
    return otherClassName;
  }

  public String getClassNameWithScope( String classOrInterfaceName,
                                       boolean doTypeParameters ) {
    if ( Utils.isNullOrEmpty( classOrInterfaceName ) ) return null;
    String typeParameters = "";
    if ( classOrInterfaceName.contains( "<" )
         && classOrInterfaceName.contains( ">" ) ) {
      typeParameters =
          classOrInterfaceName.substring( classOrInterfaceName.indexOf( '<' ) + 1,
                                          classOrInterfaceName.lastIndexOf( '>' ) )
                              .trim();
      if ( doTypeParameters ) {
        String tpNameWithScope = getClassNameWithScope( typeParameters, true );
        if ( !Utils.isNullOrEmpty( tpNameWithScope ) ) {
          typeParameters = tpNameWithScope;
        }
      }
      typeParameters =
          "<" + ClassUtils.getNonPrimitiveClassName( typeParameters ) + ">";
      classOrInterfaceName =
          classOrInterfaceName.substring( 0, classOrInterfaceName.indexOf( '<' ) );
    }
    String classNameWithScope = getClassNameWithScope( classOrInterfaceName );
    if ( !Utils.isNullOrEmpty( classNameWithScope ) ) {
      classOrInterfaceName = classNameWithScope;
    }
    return classOrInterfaceName + typeParameters;
  }

  public Set< MethodDeclaration > getClassMethodsWithName( String methodName,
                                                           String className ) {
    if ( Debug.errorOnNull( false,
                            "Passed null to getClassMethodsWithName( methodName="
                                + methodName + ", className=" + className + ")",
                            methodName, className ) ) {
      return emptyMethodDeclarationSet;
    }
    
    Map< String, Set< MethodDeclaration > > classMethods =
        methodTable.get( className );
    if ( classMethods == null ) {
      classMethods = methodTable.get( ClassUtils.simpleName( className ) );
    }
    if ( classMethods == null ) {
      String scopedName = getClassNameWithScope( className );
      if ( !Utils.isNullOrEmpty( scopedName ) ) {
        classMethods = methodTable.get( scopedName );
      }
    }
    Set< MethodDeclaration > methodSet = emptyMethodDeclarationSet;
    if ( classMethods == null && isInnerClass( className ) ) {
      methodSet = getClassMethodsWithName( methodName, getEnclosingClassName( className ) );
    }
    if ( Utils.isNullOrEmpty( methodSet ) && classMethods == null ) {
      if ( Debug.isOn() ) Debug.outln( "getClassMethodsWithName(" + methodName + ", " + className
                   + ") couldn't find class and method in ClassData methodTable cache!\nmethodTable="
                   + methodTable.toString() );
      return methodSet;
    }
    if ( Utils.isNullOrEmpty( methodSet ) ) {
      methodSet = classMethods.get( methodName );
    }
    if ( Utils.isNullOrEmpty( methodSet ) ) {
      if ( Debug.isOn() ) Debug.outln( "getClassMethodsWithName(" + methodName + ", " + className
                   + ") = null\nmethodTable=" + methodTable.toString() );
    }
    return methodSet;
  }

  public String getFullyQualifiedName( String classOrInterfaceName,
                                       boolean doTypeParameters ) {
    String typeParameters = "";
    if ( classOrInterfaceName.contains( "<" )
         && classOrInterfaceName.contains( ">" ) ) {
      typeParameters =
          classOrInterfaceName.substring( classOrInterfaceName.indexOf( '<' ) + 1,
                                          classOrInterfaceName.lastIndexOf( '>' ) )
                              .trim();
      typeParameters =
          "<"
              + ( doTypeParameters
                                  ? getFullyQualifiedName( typeParameters, true )
                                  : ClassUtils.getNonPrimitiveClassName( typeParameters ) )
              + ">";
      classOrInterfaceName =
          classOrInterfaceName.substring( 0, classOrInterfaceName.indexOf( '<' ) );
    }
    String n = ClassUtils.getFullyQualifiedName( classOrInterfaceName, false );
    if ( Utils.isNullOrEmpty( n ) || n.equals( classOrInterfaceName ) ) {
      n = getClassNameWithScope( classOrInterfaceName );
    }
    if ( Utils.isNullOrEmpty( n ) || n.equals( classOrInterfaceName ) ) {
      n = getClassNameWithScope( ClassUtils.simpleName( classOrInterfaceName ) );
    }
    if ( Utils.isNullOrEmpty( n ) || n.equals( classOrInterfaceName ) ) {
      n = classOrInterfaceName;
    }
    n = n + typeParameters;
    if ( Debug.isOn() ) Debug.outln( "getFullyQualifiedName("
                                     + classOrInterfaceName + ", "
                                     + doTypeParameters + ") = " + n );
    return n;
  }

  /**
   * @param className
   * @param paramName
   * @param lookOutsideClassData
   * @return
   */
  public Parameter<?> getParameter( String className, String paramName,
                                       boolean lookOutsideClassData,
                                       boolean setCurrentClass,
                                       boolean addIfNotFound,
                                       boolean complainIfNotFound ) {
    Param param = getParam( className, paramName, lookOutsideClassData,
                            setCurrentClass, addIfNotFound, complainIfNotFound );
    Parameter<?> parameter = parameterMap.get( param );
    return parameter;
  }
  
  /**
   * Find the Param with the given name, paramName, in the given class,
   * className. Create the param if it does not exist with null type and value.
   * This sets the currentClass!
   * 
   * @param className
   * @param paramName
   * @param lookOutsideClassData
   * @return the found or created Param or null if the paramName is null or "".
   */
  public Param getParam( String className, String paramName,
                         boolean lookOutsideClassData, boolean setCurrentClass,
                         boolean addIfNotFound, boolean complainIfNotFound ) {
    if ( className == null ) className = getCurrentClass();
    ParameterListenerImpl aeClass = getAeClass( className, true );
    className = aeClass.getName();
    if ( setCurrentClass ) {
      setCurrentAeClass( aeClass );
    }
    Param p = lookupMemberByName( className, paramName, lookOutsideClassData, false );
    if ( p == null && paramName != null && addIfNotFound ) {
      p = makeParam( className, paramName, null, null );
    }
      Debug.errorOnNull( complainIfNotFound, complainIfNotFound, "Could not " +
                         ( addIfNotFound ? "create" : "find" ) +
                         " parameter " + className + "." + paramName, p );
    return p;
  }
  
  /**
   * @param className
   *          the name of the AE class
   * @param createIfNotFound
   *          whether to create the AE class if it does not exist
   * @return the AE class (ParameterListenerImpl) with the given name or null if
   *         there isn't one and one is not created (based on createIfNotFound)
   */
  public ParameterListenerImpl getAeClass( String className,
                                           boolean createIfNotFound ) {
    ParameterListenerImpl aeClass = null;
    if ( className == null ) {
      className = "GeneratedClass" + this.counter++;
    }
    if ( aeClasses.containsKey( className ) ) {
      aeClass = aeClasses.get( className );
    } else if ( createIfNotFound ) {
      aeClass = new ParameterListenerImpl( className );
    }
    return aeClass;
  }

//  public < T > Parameter< T > makeParameter( String className, String paramName, Class< T > type, T value ) {
//    ParameterListenerImpl aeClass = getAeClass( className, true );
//    
//    Parameter < T > parameter = new Parameter< T >( p.name, null, (T)p.value, aeClass );
//    HERE!!
//    return parameter;
//  }
  public < T > Parameter< T > makeParameter( String className, Param p ) {//, Class< T > type ) {
    ParameterListenerImpl aeClass = getAeClass( className, true );
    Parameter < T > parameter = constructParameter( className, p );//new Parameter< T >( p.name, null, p.value, aeClass );
    aeClass.getParameters().add( parameter );
    parameterMap.put( p, parameter );
    return parameter;
  }
  
  /**
   * Create a Param with the given name, type, and value Strings, and add the
   * Param to the paramTable for the given class name. If the Param already
   * exists in the table, it will be overwritten.
   * 
   * @param className
   * @param paramName
   * @param type
   * @param value
   * @return the created Param
   */
  public Param makeParam( String className, String paramName, String type,
                           String value ) {
    Param p = new Param( paramName, type, value );
    Param existingParam = Utils.put( paramTable, className, paramName, p );
    Parameter< ? > parameter = parameterMap.get( p );
    if ( parameter == null ) {
      parameter = makeParameter(className, p );// constructParameter( className, p );
    }// else 
    if ( existingParam != null ) {
      if ( parameterMap.containsKey( existingParam ) ) {
        parameterMap.remove( existingParam );
        parameterMap.put( p, parameter );
      }
    }
    return p;
  }

  /**
   * a struct of two types and an array
   */
  public static class PTA {
    public PTA( Class< ? extends Parameter< ? >> paramType,
                Class< ? > genericType, Object[] argArr ) {
      this.paramType = paramType;
      this.genericType = genericType;
      this.argArr = argArr;
    }
    public Class< ? extends Parameter< ? > > paramType = null;
    public Class< ? > genericType = null;
    public Object[] argArr = null;
  }
  
//  public Pair< Pair< String, String >, String[] >
//  convertToParameterTypeAndConstructorArgs( String paramName, String paramTypeName,
//                                            String classOfParameterName ) {
//    
//  }
  /**
   * Determines the AE translated parameter type, generic parameter types, and arguments.  
   * @param p
   * @param classNameOfParameter
   * @return
   */
  public PTA
      convertToParameterTypeAndConstructorArguments( String paramName,
                                                     String paramTypeName,
                                                     String classNameOfParameter ) {
    PTA typesAndArgs = null;
    
    Class< ? extends Parameter< ? > > paramType = null;
    Class< ? > genericParamType = null;
    Object[] argArr = null;
    
    if ( Utils.isNullOrEmpty( paramTypeName ) ) {
      Param pDef =
          lookupMemberByName( classNameOfParameter, paramName, true, true );
      if ( pDef != null ) {
        paramTypeName = pDef.type;
      }
    }
    String type = "Parameter";
    String parameterTypes = paramTypeName;

    if ( paramType != null && paramTypeName.equals( "Generation" ) ) {
      Debug.out( "" );
    }

    // parameterTypes = getFullyQualifiedName( parameterTypes, true );
    parameterTypes = getClassNameWithScope( parameterTypes, true );
    String castType = parameterTypes;
    // TODO -- REVIEW -- Why is p.value in args by default, but recognized types
    // do not include p.value?
//    String valueArg = "null";
//    String typePlaceholder = "!TYPE!";
    // if ( valueArg.equals( "null" )
    // || ( valueArg.startsWith( "new Expression" ) &&
    // valueArg.endsWith( "(null)" ) ) ) {
//    valueArg = "(" + typePlaceholder + ")" + valueArg; // replacing !TYPE! later
    // }
    argArr = new Object[]{ paramName, null, null, getCurrentAeClass() };
    //String args = "\"" + paramName + "\", null, " + valueArg + ", this";
    String parameterClass =
        typeToParameterType( paramTypeName );
    if ( Utils.isNullOrEmpty( paramTypeName ) ) {
      System.err.println( "Error! creating a field " + paramName + ":" + paramTypeName + " of unknown type!" );
    } else if ( !parameterClass.equals( paramTypeName ) ) {
      type = parameterClass;
      if ( !type.equals( "Parameter" ) ) {
        parameterTypes = null;
        if ( !Utils.isNullOrEmpty( castType ) ) {
//          args = "\"" + paramName + "\", " + valueArg + ", this";
          argArr = new Object[]{ paramName, null, getCurrentAeClass() };
        }
      }
    } else if ( paramTypeName.toLowerCase().equals( "time" ) ) {
      type = "Timepoint";
      parameterTypes = null;
      // args = "\"" + p.name + "\", this";
      if ( !Utils.isNullOrEmpty( castType ) ) {
//        args = "\"" + paramName + "\", " + valueArg + ", this";
        argArr = new Object[]{ paramName, null, getCurrentAeClass() };
      }
    } else if ( paramTypeName.toLowerCase().startsWith( "int" )
                || paramTypeName.toLowerCase().startsWith( "long" ) // TODO -- Need a
                                                             // LongParameter
                || paramTypeName.trim().replaceAll( " ", "" )
                         .equals( "Parameter<Integer>" ) ) {
      type = "IntegerParameter";
      parameterTypes = null; // "Integer";
      // args = "\"" + p.name + "\", this";
      if ( !Utils.isNullOrEmpty( castType ) ) {
//        args = "\"" + paramName + "\", " + valueArg + ", this";
        argArr = new Object[]{ paramName, null, getCurrentAeClass() };
      }
    } else if ( paramTypeName.toLowerCase().equals( "double" )
                || paramTypeName.trim().replaceAll( " ", "" )
                         .equals( "Parameter<Double>" ) ) {
      type = "DoubleParameter";
      parameterTypes = null;
      // args = "\"" + p.name + "\", this";
      if ( !Utils.isNullOrEmpty( castType ) ) {
//        args = "\"" + paramName + "\", " + valueArg + ", this";
        argArr = new Object[]{ paramName, null, getCurrentAeClass() };
      }
    } else if ( paramTypeName.toLowerCase().equals( "boolean" )
                || paramTypeName.trim().replaceAll( " ", "" )
                         .equals( "Parameter<Boolean>" ) ) {
      type = "BooleanParameter";
      parameterTypes = null;
      // args = "\"" + p.name + "\", this";
      if ( !Utils.isNullOrEmpty( castType ) ) {
//        args = "\"" + paramName + "\", " + valueArg + ", this";
        argArr = new Object[]{ paramName, null, getCurrentAeClass() };
      }
    } else if ( paramTypeName.equals( "String" )
                || paramTypeName.trim().replaceAll( " ", "" )
                         .equals( "Parameter<String>" ) ) {
      type = "StringParameter";
      parameterTypes = null;
      // args = "\"" + p.name + "\", this";
      // } else if ( p.type.startsWith( "TimeVaryingMap" ) ) {
      // args = "\"" + p.name + "\", this";
    }
//    if ( Utils.isNullOrEmpty( castType ) ) {
//      typePlaceholder = "(" + typePlaceholder + ")";
//      args = args.replace( typePlaceholder, "" );
//    } else {
//      args = args.replace( typePlaceholder, castType );
//    }

//    // HACK -- TODO
//    if ( args.contains( ", new FunctionCall" ) ) {
//      args += ", true";
//    }

    type = type + ( Utils.isNullOrEmpty( parameterTypes ) ? "" : "<" + parameterTypes + ">" );
    paramType =
        (Class< ? extends Parameter< ? > >)ClassUtils.getClassForName( type,
                                                                       null,
                                                                       getPackageName(),
                                                                       false );
    genericParamType = ClassUtils.getClassForName( parameterTypes, null, getPackageName(), false );

    //types = (T)new Pair< Class<? extends Parameter<?>>, Class<?> >( paramType, genericParamType );
    //typesAndArgs = (R)new Pair< T, Object[] >( types, argArr );
    typesAndArgs = new PTA( paramType, genericParamType, argArr );
    return typesAndArgs;
  }

  public ClassData.PTA
  convertToEventParameterTypeAndConstructorArguments( ClassData.Param p,
                                                      String classNameOfParameter ) {
    return convertToParameterTypeAndConstructorArguments( p.name, p.type,
                                                          classNameOfParameter );
  }
  
  /**
   * Create a Parameter based on the type
   * @param className
   * @param param
   * @return a parameter
   */
  public < P extends Parameter< ? > > P constructParameter( String className,
                                                            Param param ) {
    PTA pta =
        convertToParameterTypeAndConstructorArguments( param.name, param.type, className );
    Class< P > cls = (Class< P >)pta.paramType;
    ConstructorCall call = new ConstructorCall( null, cls, pta.argArr );
    P parameter = (P)call.evaluate( true );
    return parameter;
  }

  
  protected Param lookupMemberByName( String className, String paramName,
                                      boolean lookOutsideClassData ) {
    return lookupMemberByName( className, paramName, lookOutsideClassData, true );
  }

  public Param lookupMemberByName( String className, String paramName,
                                      boolean lookOutsideClassData,
                                      boolean complainIfNotFound ) {
    if ( Debug.errorOnNull( complainIfNotFound, complainIfNotFound,
                            "Passing null in lookupMemberByName(" + className
                            + ", " + paramName + ")", className, paramName ) ) {
      return null;
    }
    if ( className.equals( "this" ) ) {
      className = currentClass;
    }
    // Check if the className is known.
    Map< String, Param > params = paramTable.get( className );
    // If the name is not in the table, make sure it's the scoped name.
    String classNameWithScope = null;
    if ( params == null ) {
      classNameWithScope = getClassNameWithScope( className );
      if ( classNameWithScope != null
           || ( !lookOutsideClassData && complainIfNotFound && !Debug.errorOnNull( false,
                                                                             "Error! Could not find a class definition for "
                                                                                 + className
                                                                                 + " when looking for member "
                                                                                 + paramName
                                                                                 + ".",
                                                                             classNameWithScope ) ) ) {
        // if ( Utils.isNullOrEmpty( classNameWithScope ) ) {
        params = paramTable.get( classNameWithScope );
      }
    }
    Param p = null;
    if ( params != null ) {
      p = params.get( paramName );
    }
    // If not in the table and an inner class, check enclosing class's scope.
    if ( p == null && isInnerClass( className ) ) {
      String enclosingClassName = getEnclosingClassName( className );
      if ( !Utils.isNullOrEmpty( enclosingClassName ) ) {
        p =
            lookupMemberByName( enclosingClassName, paramName, lookOutsideClassData,
                                complainIfNotFound && lookOutsideClassData );
      }
    }
    Class< ? > classForName = null;
    if ( p == null && lookOutsideClassData ) {
      classForName =
          ClassUtils.getClassForName( className, paramName, getPackageName(),
                                      false );
      if ( classForName != null ) {
        Field field = ClassUtils.getField( classForName, paramName, true );
        if ( field != null ) {
          p = new Param( paramName, ClassUtils.toString( field.getType() ),
                         null );
        }
      }
    }
    if ( Debug.isOn() ) Debug.outln( "lookupMemberByName( className="
                                     + className + ", paramName=" + paramName
                                     + ") returning " + p );
    if ( p == null && complainIfNotFound ) {
      Debug.errorOnNull( false, "lookupMemberByName(" + className + ", "
                                + paramName
                                + "): no parameter found\n  paramTable =\n"
                                + paramTable + "\n  enclosingClasses =\n"
                                + nestedToEnclosingClassNames, p );
    }
    return p;
  }

  public Param lookupCurrentClassMember( String name, boolean complainIfNotFound ) {
    return lookupMemberByName( currentClass, name, false, complainIfNotFound );
  }

  /**
   * @param memberName
   * @param lookOutsideClassDataForTypes
   * @param complainIfNotFound
   * @return a Param with the input member name for the current class
   */
  public Param lookupCurrentClassMember( String memberName,
                                          boolean lookOutsideClassDataForTypes,
                                          boolean complainIfNotFound ) {
    return lookupMemberByName( currentClass, memberName, lookOutsideClassDataForTypes,
                               complainIfNotFound );
  }

  /**
   * @param className
   * @return the class within which the class with the input name is declared or
   *         null if there is no such class
   */
  public String getEnclosingClassName( String className ) {
      if ( className == null ) return null;
      String enclosingClassName = nestedToEnclosingClassNames.get( className ); 
      if ( !Utils.isNullOrEmpty( enclosingClassName ) ) {
        if ( Debug.isOn() ) Debug.outln( "getEnclosingClassName(" + className + ") = "
                     + enclosingClassName );
        return enclosingClassName;
      }
      String scopedName = getClassNameWithScope( className );
      if ( !Utils.isNullOrEmpty( scopedName ) ) {
        enclosingClassName = nestedToEnclosingClassNames.get( scopedName ); 
  //      if ( !Utils.isNullOrEmpty( enclosingClassName ) ) {
  //        return true;
  //      }
      }
      if ( Debug.isOn() ) Debug.outln( "getEnclosingClassName(" + className + ") = "
                   + enclosingClassName );
      return enclosingClassName;
  //    if ( className == null ) return null;
  //    if ( nestedToEnclosingClassNames.containsKey( className ) ) {
  //      return nestedToEnclosingClassNames.get( className );
  //    }
  //    String scopedName = getClassNameWithScope( className );
  //    if ( scopedName == null ) return null;
  //    if ( !nestedToEnclosingClassNames.containsKey( scopedName ) ) {
  //      return nestedToEnclosingClassNames.get( scopedName );
  //    }
  ////    if ( scopedName != null ) {
  ////      int pos = scopedName.lastIndexOf( '.' );
  ////      if ( pos == -1 ) return null;
  ////    }
  //    return null;
    }

  /**
   * @param className
   * @return whether the class with the input name is declared as a static class
   */
  public boolean isClassStatic( String className ) {
    if ( isStatic( className ) ) return true;
    String scopedName = getClassNameWithScope( className );
    return isStatic( scopedName );
  }

  /**
   * @param className
   * @return whether the class with the input name is non-static class declared
   *         within another class
   */
  public boolean isInnerClass( String className ) {
    // TODO -- should have a ClassDeclaration stub class to collect this info.
    boolean is = !isClassStatic( className ) && isNested( className );
    if ( Debug.isOn() ) Debug.outln( "isInnerClass( " + className + ") = " + is );
    return is;
  }

  /**
   * @param memberName
   * @return whether the member with the input name is declared as static
   */
  public boolean isMemberStatic( String memberName ) {
    String className = currentClass;
    return isMemberStatic( className, memberName );
  }

  // TODO -- is generated Java for member getting the static tag?  
  /**
   * @param className
   * @param memberName
   * @return whether the member named memberName is declared in the class named
   *         className as static
   */
  public boolean isMemberStatic( String className, String memberName ) {
    String memberShortName = memberName;
    int pos = memberName.lastIndexOf( '.' ); 
    if ( pos >= 1 ) {
      className = memberName.substring( 0, pos );
      memberShortName = memberName.substring( pos+1 );
    }
    String tryName = className + "." + memberShortName;
    if ( isStatic( tryName ) ) return true;
    String scopedName = getClassNameWithScope( className );
    if ( !Utils.isNullOrEmpty( scopedName ) ) {
      tryName = scopedName + "." + memberShortName;
      if ( isStatic( tryName ) ) return true;
    }
    return false;
  }

  /**
   * @param className
   * @return whether the class with the input name is declared within another
   *         class
   */
  public boolean isNested( String className ) {
    String ecn = getEnclosingClassName( className );
    boolean is = !Utils.isNullOrEmpty( ecn );
    if ( Debug.isOn() ) Debug.outln( "isNested( " + className + ") = " + is + ": "
                 + ( is ? ecn : "" ) );
    return is;
  }

  /**
   * @param name
   * @return whether there is some element with the input name that is declared
   *         as static
   */
  public boolean isStatic( String name ) {
    if ( Utils.isNullOrEmpty( name ) ) return false;
    Boolean s = isStaticMap.get( name );
    return s != null && s;
  }

  /**
   * @param className
   * @return whether there is a record of whether the class with the input name
   *         is declared as static or not
   */
  public boolean knowIfClassIsStatic( String className ) {
    if ( knowIfStatic( className ) ) return true;
    String scopedName = getClassNameWithScope( className );
    return knowIfStatic( scopedName );
  }

  /**
   * @param name
   * @return whether there is a record of whether an element with the input name
   *         is declared as static or not
   */
  public boolean knowIfStatic( String name ) {
    if ( Utils.isNullOrEmpty( name ) ) return true;
    Boolean s = isStaticMap.get( name );
    return s != null;
  }

  /**
   * @return the isStaticMap
   */
  public Map< String, Boolean > getIsStaticMap() {
    return isStaticMap;
  }

  /**
   * @param isStaticMap the isStaticMap to set
   */
  public void setIsStaticMap( Map< String, Boolean > isStaticMap ) {
    this.isStaticMap = isStaticMap;
  }

  /**
   * @param className
   * @return constructors for the class with the given className that is defined
   *         in the XML.
   */
  public Set< ConstructorDeclaration > getConstructors( String className ) {
    ClassOrInterfaceDeclaration classDecl = getClassDeclaration( className );
    if ( classDecl == null ) return null;
    if ( classDecl.getMembers() == null ) return null;
    Set< ConstructorDeclaration > s = new TreeSet<ConstructorDeclaration>(new CompareUtils.GenericComparator< ConstructorDeclaration >());
    for ( BodyDeclaration m : classDecl.getMembers() ) {
      if ( m instanceof ConstructorDeclaration ) {
        if ( !s.contains( m ) ) {
          s.add( (ConstructorDeclaration)m );
        }
      }
    }
    return s;
  }

  /**
   * Look for a class declaration of a particular name nested inside another class declaration.
   * @param className
   * @param classDecl
   * @return
   */
  public static ClassOrInterfaceDeclaration getClassDeclaration( String className,
                                                                 ClassOrInterfaceDeclaration classDecl ) {
    // First check and see if this is "the one."
    if ( classDecl.getName().equals( className ) ) {
      return classDecl;
    } else {
      // Now check nested classes.
      if ( classDecl != null && classDecl.getMembers() != null ) {
        for ( BodyDeclaration bd : classDecl.getMembers() ) {
          if ( bd instanceof ClassOrInterfaceDeclaration ) {
            ClassOrInterfaceDeclaration nestedClassDecl = (ClassOrInterfaceDeclaration)bd;
            nestedClassDecl = getClassDeclaration( className, nestedClassDecl );
            if ( nestedClassDecl != null ) return nestedClassDecl;
          }
        }
      }
    }
    return null;
  }
  
  public static ClassOrInterfaceDeclaration getClassDeclaration( String className,
                                                          CompilationUnit cu ) {
    if ( cu == null || cu.getTypes() == null ) return null;
    for ( TypeDeclaration t : cu.getTypes() ) {
      if ( t instanceof ClassOrInterfaceDeclaration ) {
        ClassOrInterfaceDeclaration classDecl = 
            getClassDeclaration( className, (ClassOrInterfaceDeclaration)t );
        if ( classDecl != null ) return classDecl;
      }
    }
    return null;
  }
  
  public ClassOrInterfaceDeclaration getClassDeclaration( String className ) {
    className = ClassUtils.simpleName( className );
    ClassOrInterfaceDeclaration classDecl = null;
    CompilationUnit cu = getClasses().get( className );
    if ( cu == null ) {
      // See if enclosing class declaration has this one's.
      String parentClassName = getEnclosingClassName( className );
      if ( !Utils.isNullOrEmpty( parentClassName ) ) {
        ClassOrInterfaceDeclaration parentDecl = getClassDeclaration( parentClassName );
        if ( parentDecl != null && parentDecl.getMembers() != null ) {
          classDecl = getClassDeclaration( className, parentDecl );
          return classDecl;
        }
      }
      return null;
    }
    classDecl = getClassDeclaration( className, cu );
    if ( classDecl == null ) {
      for ( CompilationUnit cu2 : getClasses().values() ) {
        if ( cu == cu2 ) continue;
        classDecl = getClassDeclaration( className, cu );
        if ( classDecl != null ) {
          return classDecl;
        }
      }
    }
    return classDecl;
  }

  
  /**
   * @return the methodTable
   */
  public Map< String, Map< String, Set< MethodDeclaration >>> getMethodTable() {
    return methodTable;
  }

  /**
   * @param methodTable the methodTable to set
   */
  public
      void
      setMethodTable( Map< String, Map< String, Set< MethodDeclaration >>> methodTable ) {
    this.methodTable = methodTable;
  }

  /**
   * @return the nestedToEnclosingClassNames
   */
  public Map< String, String > getNestedToEnclosingClassNames() {
    return nestedToEnclosingClassNames;
  }

  /**
   * @param nestedToEnclosingClassNames the nestedToEnclosingClassNames to set
   */
  public
      void
      setNestedToEnclosingClassNames( Map< String, String > nestedToEnclosingClassNames ) {
    this.nestedToEnclosingClassNames = nestedToEnclosingClassNames;
  }

  /**
   * @return the packageName
   */
  public String getPackageName() {
    return packageName;
  }

  /**
   * @param packageName the packageName to set
   */
  public void setPackageName( String packageName ) {
    this.packageName = packageName;
  }

  /**
   * @return the paramTable
   */
  public Map< String, Map< String, Param >> getParamTable() {
    return paramTable;
  }

  /**
   * @param paramTable the paramTable to set
   */
  public void setParamTable( Map< String, Map< String, Param >> paramTable ) {
    this.paramTable = paramTable;
  }

  /**
   * @return the parameterMap
   */
  public Map< ClassData.Param, Parameter< ? >> getParameterMap() {
    return parameterMap;
  }

  /**
   * @param parameterMap the parameterMap to set
   */
  public void
      setParameterMap( Map< ClassData.Param, Parameter< ? >> parameterMap ) {
    this.parameterMap = parameterMap;
  }

  /**
   * @return the currentClass
   */
  public String getCurrentClass() {
    return currentClass;
  }

  /**
   * @param currentClass the currentClass to set
   */
  public void setCurrentClass( String currentClass ) {
    this.currentClass = currentClass;
  }

  /**
   * @return the aeClasses
   */
  public Map< String, ParameterListenerImpl > getAeClasses() {
    return aeClasses;
  }

  /**
   * @param aeClasses the aeClasses to set
   */
  public void setAeClasses( Map< String, ParameterListenerImpl > aeClasses ) {
    this.aeClasses = aeClasses;
  }

  /**
   * @return the currentAeClass
   */
  public ParameterListenerImpl getCurrentAeClass() {
    return currentAeClass;
  }

  /**
   * @param currentAeClass the currentAeClass to set
   */
  public void setCurrentAeClass( ParameterListenerImpl currentAeClass ) {
    this.currentAeClass = currentAeClass;
    setCurrentClass( ( currentAeClass == null ? null : currentAeClass.getName() ) );
  }

  public static String typeToParameterType( String type ) {
    if ( Utils.isNullOrEmpty( type ) ) {
      //type = "null";
    } else if ( type.toLowerCase().equals( "time" ) ) {
      type = "Timepoint";
    } else if ( type.toLowerCase().equals( "duration" ) ) {
      type = "Duration";
    } else {
      String classType = JavaToConstraintExpression.typeToClass( type );
      final String[] primClassesSame =
          new String[] { "Boolean", //"Character",
                         //"Byte", "Short", 
                         "Integer",
                         //"Long", "Float",
                         "Double",// "Void"
                         "String" };
      if ( Arrays.asList( primClassesSame ).contains( classType ) ) {
        type = classType;
      } else {
      //if ( classType.equals( type ) ) {
        type = "";
      }
      type = type + "Parameter";
    }
    return type;
  }

/**
 * @return the classes
 */
public Map< String, CompilationUnit > getClasses() {
    return classes;
}

/**
 * @param classes the classes to set
 */
public void setClasses( Map< String, CompilationUnit > classes ) {
    this.classes = classes;
}

/**
 * @return the currentCompilationUnit
 */
public CompilationUnit getCurrentCompilationUnit() {
    return currentCompilationUnit;
}

/**
 * @param currentCompilationUnit the currentCompilationUnit to set
 */
public void setCurrentCompilationUnit( CompilationUnit currentCompilationUnit ) {
    this.currentCompilationUnit = currentCompilationUnit;
}

}
