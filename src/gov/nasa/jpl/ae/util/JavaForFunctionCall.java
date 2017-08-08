package gov.nasa.jpl.ae.util;

import gov.nasa.jpl.ae.event.Affectable;
import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.ConstructorCall;
import gov.nasa.jpl.ae.event.EffectFunction;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.TimeVaryingMap; // don't remove!!
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.ObjectCreationExpr;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import junit.framework.Assert;

/**
 * @author bclement
 *
 */
public class JavaForFunctionCall {
  /**
   * 
   */
  // private final EventXmlToJava xmlToJava;
  protected final JavaToConstraintExpression exprXlator;// .expressionTranslator;

  protected Expression expression = null;
  protected MethodCallExpr methodCallExpr = null;
  protected ObjectCreationExpr objectCreationExpr = null;
  protected Boolean methodOrConstructor = null;
  protected String object = null;
  protected gov.nasa.jpl.ae.event.Expression< ? > objectExpr = null;
  protected String objectTypeName;
  protected Class< ? > objectType;
  protected String className = null;
  protected String callName = null;
  protected Constructor< ? > matchingConstructor = null;
  protected ConstructorDeclaration constructorDecl = null;
  protected Method matchingMethod = null;
  protected MethodDeclaration methodDecl = null;
  protected String methodJava = null; // Java text for getting
                                      // java.reflect.Method
  protected Boolean isEffectFunction = null;
  protected String argumentArrayJava = null;
  protected Vector< Object > args = null; // new Vector<Object>();
  protected boolean convertingArgumentsToExpressions = false;
  protected boolean evaluateCall = false;
  protected String preferredPackageName = null;

  protected Class< ? > returnType;

  protected Call call = null;

  protected Boolean isTimeVarying = null;
  protected Boolean timeVaryingCall = null;

  // /**
  // * When expressions are passed to functions that are expecting parameters, a
  // * dependency can be formed.
  // */
  // public ArrayList<FieldDeclaration> generatedDependencies =
  // new ArrayList< FieldDeclaration >();

  protected Class< ? >[] argTypes = null;

  public JavaForFunctionCall( JavaToConstraintExpression expressionTranslator,
                              Expression expression,
                              boolean convertArgumentsToExpressions,
                              String preferredPackageName,
                              Class< ? > returnType ) {
    this( expressionTranslator, expression, convertArgumentsToExpressions,
          preferredPackageName, false, returnType );
  }


  public JavaForFunctionCall( JavaToConstraintExpression exprTranslator,
                              Expression expression,
                              boolean convertArgumentsToExpressions,
                              String preferredPackageName, boolean evaluateCall,
                              Class< ? > returnType ) {

    // Arguments may be Expressions, Parameters, or other. Method parameter
    // types may also be Expressions, Parameters, or other.
    //
    // If argument is Expression, and parameter is Parameter,
    // see if the expression is a parameter and pass that instead.
    //
    // If argument is Expression, and parameter is other,
    // pass the evaluation of the Expression.
    //
    // If argument is Parameter, and parameter is Expression,
    // wrap the argument in an Expression.
    //
    // If argument is Parameter, and parameter is other,
    // pass the Parameter's value.
    //
    // If argument is other,
    // wrap the argument in an Expression or Parameter according to the type.
    //
    // If there is a choice of methods, prefer matched types to matches through
    // conversion, except when convertArgumentsToExpressions==true. Prefer those
    // that match Expressions to those that match Parameters and Parameters to
    // other.

    this.returnType = returnType;

    assert expression != null;

    setExpression( expression );
    this.exprXlator = exprTranslator;
    // REVIEW -- How do we know when we want to convert args to Expressions?
    // Constructors of events (and probably classes) convert args to
    // Expressions. For now, do not convert args for any other calls.
    setConvertingArgumentsToExpressions( convertArgumentsToExpressions );
    setEvaluateCall( evaluateCall );

    setPreferredPackageName( preferredPackageName );


    // Assemble Java text for finding the java.reflect.Method for callName
    // TODO -- move most of this to ClassData, instantiate this.call, and
    // use it to generate methodJava text.
    // code below replaced by getMethodJava()


    // code below replaced by isEffectFunction()
    // // Determine whether the function is regular or actually an effect.
    // // For example, TimeVaryingMap.setValue(...) is an effect function, but
    // // TimeVaryingMap.getValue(...) is not.
    // // TODO -- REVIEW -- type is never used! -- is it supposed to be used in
  }

  public < T > T getBestArgTypes( Set< T > declarations,
                                  Class< ? >[] argTypesArr,
                                  String preferredPackageName ) {
    Map< T, Pair< Class< ? >[], Boolean > > candidates =
        new TreeMap< T, Pair< Class< ? >[], Boolean > >( new CompareUtils.GenericComparator< T >() );
    for ( T cd : declarations ) {

      List< Parameter > params = null;
      if ( cd instanceof ConstructorDeclaration ) {
        params = ( (ConstructorDeclaration)cd ).getParameters();
      } else if ( cd instanceof MethodDeclaration ) {
        params = ( (MethodDeclaration)cd ).getParameters();
      }
      boolean gotParams = !Utils.isNullOrEmpty( params );
      int size = gotParams ? params.size() : 0;
      Class< ? >[] ctorArgTypes = new Class< ? >[ size ];
      int ct = 0;
      boolean isVarArgs = false;
      if ( gotParams ) {
        isVarArgs = params.get( size - 1 ).isVarArgs();
        for ( Parameter param : params ) {
          Class< ? > c =
              ClassUtils.getClassForName( param.getType().toString(), null,
                                          preferredPackageName, true );
          ctorArgTypes[ ct++ ] = c;
        }
      }
      candidates.put( cd, new Pair< Class< ? >[], Boolean >( ctorArgTypes,
                                                             isVarArgs ) );
    }
    T decl = ClassUtils.getBestArgTypes( candidates, argTypesArr );
    return decl;
  }

  public Expression getScope() {
    if ( isMethodOrConstructor() == true && getMethodCallExpr() != null ) {
      return getMethodCallExpr().getScope();
    } else if ( isMethodOrConstructor() == false
                && getObjectCreationExpr() != null ) {
      return getObjectCreationExpr().getScope();
    } else if ( getMethodCallExpr() != null ) {
      return getMethodCallExpr().getScope();
    } else if ( getObjectCreationExpr() != null ) {
      return getObjectCreationExpr().getScope();
    }
    return null;
  }

  public boolean isStatic() {
    if ( isMethodOrConstructor() ) {
      if ( exprXlator.getClassData().knowIfStatic( getCallName() ) ) {
        return exprXlator.getClassData().isStatic( getCallName() );
      }
      if ( getMatchingMethod() != null
           && Modifier.isStatic( getMatchingMethod().getModifiers() ) ) {
        return true;
      }
      if ( getMethodDecl() != null
           && ModifierSet.isStatic( getMethodDecl().getModifiers() ) ) {
        return true;
      }
    } else {
      if ( exprXlator.getClassData().knowIfClassIsStatic( getCallName() ) ) {
        return exprXlator.getClassData().isClassStatic( getCallName() );
      }
      if ( getMatchingConstructor() != null
           && Modifier.isStatic( getMatchingConstructor().getModifiers() ) ) {
        return true;
      }
      if ( getConstructorDecl() != null
           && ModifierSet.isStatic( getConstructorDecl().getModifiers() ) ) {
        return true;
      }
    }
    return Utils.isNullOrEmpty( getObject() );
  }

  public Call toNewFunctionCall() {
    if ( Debug.isOn() ) Debug.outln( "JavaForFunctionCall.toNewFunctionCall() --> "
                                     + getCall() );
    return getCall();
  }

  public String getReturnTypeString() {
    String s = returnType == null ? "(Class<?>)null" : returnType + ".class";
    return s;
  }

  public String getCallTypeName() {
    // call getMatchingMethod() to make find out if it's a TimeVaryingFunctionCall
    Method m = getMatchingMethod();
    Constructor c = getMatchingConstructor();

    if ( m == null && c != null ) setMethodOrConstructor( false );
    else if ( c == null && m != null ) setMethodOrConstructor( true );

    String prefix = (Boolean.TRUE.equals((getTimeVaryingCall())) ? "TimeVarying" : "");
    if ( !isMethodOrConstructor() ) return prefix + "ConstructorCall";
    if ( isEffectFunction() ) {
      if ( Boolean.TRUE.equals(timeVaryingCall) ) {
        Debug.error("TimeVarying EffectFunction not supported!!!");
      }
      return "EffectFunction";
    }
    return prefix + "FunctionCall";
  }

  public String toNewFunctionCallString() {
    String fcnCallStr = null;
    String callTypeName = getCallTypeName();
    String instance = getObject();
    if ( isStatic() ) {
      instance = "null";
    }
    fcnCallStr =
        "new " + callTypeName + "( " + instance + ", " + getMethodJava() + ", "
                 + getArgumentArrayJava() + ", " + getReturnTypeString() + " )";
    // }
    if ( isEvaluateCall() && !Utils.isNullOrEmpty( fcnCallStr ) ) {
      if ( !isConvertingArgumentsToExpressions() ) {
        fcnCallStr = "(" + fcnCallStr + ").evaluate(true)";
      }
    }
    if ( Debug.isOn() ) Debug.outln( "JavaForFunctionCall.toNewFunctionCallString() --> "
                                     + fcnCallStr );
    return fcnCallStr;
  }

  public String toNewExpressionString() {
    String s = "new Expression( " + toNewFunctionCallString() + " )";
    if ( Debug.isOn() ) Debug.outln( "JavaForFunctionCall.toNewExpressionString() --> "
                                     + s );
    return s;
  }

  @Override
  public String toString() {
    return "hellllloooooo";//toNewFunctionCallString();
  }

  public < T > gov.nasa.jpl.ae.event.Expression< T > toNewExpression() {
    gov.nasa.jpl.ae.event.Expression< T > r =
        new gov.nasa.jpl.ae.event.Expression< T >( toNewFunctionCall() );
    if ( Debug.isOn() ) Debug.outln( "JavaForFunctionCall.toNewExpression() --> "
                                     + r );
    return r;
  }

  public Expression getExpression() {
    return expression;
  }

  public void setExpression( Expression expression ) {
    if ( this.expression != expression ) {
      setObjectExpr( null );
      setObjectTypeName( null );
      setObjectType( null );

      this.expression = expression;

      boolean isMethodCall = expression instanceof MethodCallExpr;
      boolean isConstructorCall = expression instanceof ObjectCreationExpr;
      setMethodOrConstructor( isMethodCall ? true
                                           : isConstructorCall ? false : null );
      setMethodCallExpr( isMethodCall ? (MethodCallExpr)expression : null );
      setObjectCreationExpr( isConstructorCall ? (ObjectCreationExpr)expression
                                               : null );
      if ( !isMethodCall && !isConstructorCall ) {
        Debug.error( "Expression " + expression
                     + " must be a MethodCallExpr or an ObjectCreationExpr!" );
      }
    }
  }

  /**
   * @return the methodCallExpr
   */
  public MethodCallExpr getMethodCallExpr() {
    return methodCallExpr;
  }

  /**
   * @param methodCallExpr
   *          the methodCallExpr to set
   */
  public void setMethodCallExpr( MethodCallExpr methodCallExpr ) {
    if ( this.methodCallExpr != methodCallExpr ) {
      setCallName( null );
      setArgumentArrayJava( null );
      setCall( null );
      this.methodCallExpr = methodCallExpr;
    }
  }

  /**
   * @return the objectCreationExpr
   */
  public ObjectCreationExpr getObjectCreationExpr() {
    return objectCreationExpr;
  }

  /**
   * @param objectCreationExpr
   *          the objectCreationExpr to set
   */
  public void setObjectCreationExpr( ObjectCreationExpr objectCreationExpr ) {
    if ( this.objectCreationExpr != objectCreationExpr ) {
      setCallName( null );
      setArgumentArrayJava( null );
      setCall( null );
      this.objectCreationExpr = objectCreationExpr;
    }
  }

  public Boolean getIsTimeVarying() {
    if ( isTimeVarying == null ) {
      isTimeVarying =
              ( getObjectType() != null &&
                TimeVaryingMap.class.isAssignableFrom(getObjectType()) ) ||
              ( getObjectTypeName() != null &&
                getObjectTypeName().contains("TimeVarying") &&
                !getObjectTypeName().contains("Call") );
    }
    return isTimeVarying;
  }

  public void setTimeVarying(Boolean timeVarying) {
    isTimeVarying = timeVarying;
  }

  public Class<?> getWrappedType() {
    if ( getObjectType() != null ) {
      if ( Wraps.class.isAssignableFrom( getObjectType() ) ) {
        Class<?> genericType = ClassUtils.getSingleGenericParameterType( getObjectType() );
        return genericType;
      }
    }
    return null;
  }

  public Boolean getTimeVaryingCall() {
    return this.timeVaryingCall;
  }
  public void setTimeVaryingCall(Boolean timeVaryingCall) {
    this.timeVaryingCall = timeVaryingCall;
  }

  /**
   * @return the methodOrConstructor
   */
  public Boolean isMethodOrConstructor() {
    return methodOrConstructor;
  }

  /**
   * @param methodOrConstructor
   *          the methodOrConstructor to set
   */
  public void setMethodOrConstructor( Boolean methodOrConstructor ) {
    this.methodOrConstructor = methodOrConstructor;
  }

  public MethodDeclaration getMethodDeclInClass( String className ) {
    // Get the list of methods with the same name (callName).
    Set< MethodDeclaration > classMethods =
        this.exprXlator.getClassData().getClassMethodsWithName( getCallName(),
                                                                className );
    // Find alternative classes that have these methods.
    if ( getIsTimeVarying() ) {
      Class<?> cls = getWrappedType();
      if ( cls != null ) {
        String clsName = cls.getCanonicalName();
        if ( clsName == null ) {
          clsName = cls.getName();
        }
        Set<MethodDeclaration> moreClassMethods =
                this.exprXlator.getClassData().getClassMethodsWithName(getCallName(),
                        clsName);
        if ( moreClassMethods != null ) {
          System.out.println("WWWWWWWWWWWWWW   MORE CLASS METHODS  WWWWWWWWWWWWWW");
          if ( classMethods == null ) {
            classMethods = moreClassMethods;
          } else {
            classMethods.addAll(moreClassMethods);
          }
        }
      }
    }

    // Find the right MethodDeclaration if it exists.
    MethodDeclaration method = null;
    if ( !Utils.isNullOrEmpty( classMethods ) ) {
      method = getBestArgTypes( classMethods, getArgTypes(),
                                getPreferredPackageName() );
      if ( method == null ) {
        // Warning just grabs the first method of this name!
        // if ( classMethods.size() > 1 ) {
        // System.err.println( "Warning! " + classMethods.size()
        // + " methods with name " + getCallName() + " in "
        // + getClassName() + ": just grabbing the first!" );
        // }
        // Add vector of argument types to getMethod() call
      }
    }

    return method;
  }

  public String findClassNameWithMatchingMethod() {
    Debug.out("findClassNameWithMatchingMethod()");
    String s = "this";
    // Check if method is in class
    if ( getMethodDeclInClass( getClassName() ) != null ) {
      Debug.out("findClassNameWithMatchingMethod(): returning this");
      return s;
    }
    String prevClassName = getClassName();
    String className =
        exprXlator.getClassData().getEnclosingClassName( prevClassName );
    while ( className != null ) {
      if ( getMethodDeclInClass( className ) != null ) {
        Debug.out("findClassNameWithMatchingMethod(): returning " + className);
        return className;
      }
      prevClassName = className;
      className =
          exprXlator.getClassData().getEnclosingClassName( prevClassName );

    }
    Debug.out("findClassNameWithMatchingMethod(): no match; returning " + s);

    return s;

  }
  
  public String fullClassName( String entityName ) {
    String className = entityName;
    entityName = exprXlator.getClassData().getNestedToEnclosingClassNames()
                           .get( entityName );
    while ( entityName != null ) {
      className = entityName + "." + className;
      entityName = exprXlator.getClassData().getNestedToEnclosingClassNames()
                             .get( entityName );
    }
    return className;

}

  /**
   * @return the object
   */
  public String getObject() {

    if ( Utils.isNullOrEmpty( object ) ) {
      Debug.out("getObject()");

      object =
          ( (JavaToConstraintExpression)this.exprXlator ).getObjectFromScope( getScope() );
      if ( Utils.isNullOrEmpty( object ) ) {
        // if ( isMethodOrConstructor() ||
        // exprXlator.getClassData().isInnerClass( getObjectTypeName() ) ) {
        if ( isMethodOrConstructor() ) {
          String className = findClassNameWithMatchingMethod();
          if (className.equals("this")) {
            setObject( className );
          } else {
            setObject(className + ".this");
          }
          

        } else if ( getMethodCallExpr() != null
                    || exprXlator.getClassData() //need to fix this whole stuff 
                                 .isInnerClass( getObjectCreationExpr().getType() 
                                                                       .toString() )
                       && exprXlator.getClassData()
                                    .getEnclosingClassName( getObjectCreationExpr().getType()
                                                                                   .toString() )
                                    .equals( getClassName() ) ) {
          setObject( "this" );
        } else {
          setObject( "null" );
        }
      }
      Debug.out("getObject(): returning " + className);
    }
    return object;
  }

  /**
   * @param object
   *          the object to set
   */
  public void setObject( String object ) {
    this.object = object;
  }

  /**
   * @return the objectExpr
   */
  public gov.nasa.jpl.ae.event.Expression< ? > getObjectExpr() {
    if ( objectExpr == null ) {
      Object tmp =
          ( (JavaToConstraintExpression)this.exprXlator ).getObjectExpressionFromScope( getScope() );
      if ( tmp instanceof Expression ) {
        setObjectExpr( (gov.nasa.jpl.ae.event.Expression< ? >)tmp );
      } else {
        if ( tmp != null ) {
          setObjectExpr( new gov.nasa.jpl.ae.event.Expression< Object >( tmp ) );
        } else {
          if ( Debug.isOn() ) Debug.errln( "Warning!  JavaToFunctionCall.getCall() is having to parse the object text, \""
                                           + getObject()
                                           + "\" to create the expression of the caller!" );
          setObjectExpr( exprXlator.javaToAeExpression( getObject(),
                                                        getObjectTypeName(),
                                                        false ) );
        }
      }
    }
    return objectExpr;
  }

  /**
   * @param objectExpr
   *          the objectExpr to set
   */
  public void
         setObjectExpr( gov.nasa.jpl.ae.event.Expression< ? > objectExpr ) {
    if ( this.objectExpr != objectExpr ) {
      setCall( null );
      this.objectExpr = objectExpr;
    }
  }

  /**
   * @return the objectTypeName
   */
  public String getObjectTypeName() {
    if ( objectTypeName == null ) {
      String typeName = this.exprXlator.astToAeExprType( getScope(), null,
              true, true );
      setObjectTypeName( typeName );
    }
    return objectTypeName;
  }

  /**
   * @param objectTypeName
   *          the objectTypeName to set
   */
  public void setObjectTypeName( String objectTypeName ) {
    if ( this.objectTypeName != objectTypeName ) {
      setClassName( null );
      setObject( null );
      setEffectFunction( null );

      this.objectTypeName = objectTypeName;
    }
  }

  /**
   * @return the objectType
   */
  public Class< ? > getObjectType() {
    if ( objectType == null ) {
      if ( !Utils.isNullOrEmpty( getClassName() )
           && getClassName() != exprXlator.getCurrentClass() ) {
        setObjectType( ClassUtils.getClassForName( getClassName(),
                                                   getCallName(),
                                                   getPreferredPackageName(),
                                                   true ) );
      }
    }
    return objectType;
  }

  /**
   * @param objectType
   *          the objectType to set
   */
  public void setObjectType( Class< ? > objectType ) {
    this.objectType = objectType;
  }

  /**
   * @return the className
   */
  public String getClassName() {
    if ( Utils.isNullOrEmpty( className ) ) {
      if ( getObjectTypeName() != null ) {
        setClassName( getObjectTypeName() );
      } else {
        setClassName( this.exprXlator.getCurrentClass() );
      }
    }
    return className;
  }

  /**
   * @param className
   *          the className to set
   */
  public void setClassName( String className ) {
    if ( this.className != className ) {
      setObjectType( null );
      setMatchingMethod( null );
      setMatchingConstructor( null );
      setMethodDecl( null );
      setConstructorDecl( null );
      this.className = className;
    }
  }

  /**
   * @return the callName
   */
  public String getCallName() {
    if ( callName == null ) {
      setCallName( getMethodCallExpr() != null ? getMethodCallExpr().getName()
                                               : getObjectCreationExpr().getType()
                                                                        .toString() );
    }
    return callName;
  }

  /**
   * @param callName
   *          the callName to set
   */
  public void setCallName( String callName ) {
    if ( this.callName != callName ) {
      setObjectType( null );
      setMatchingMethod( null );
      setMatchingConstructor( null );
      setMethodDecl( null );
      setConstructorDecl( null );
      this.callName = callName;
    }
  }

  /**
   * @return the matchingConstructor
   */
  public Constructor< ? > getMatchingConstructor() {
    if ( matchingConstructor == null ) {
      // Try using reflection to find the method, but class may not exist.
      matchingConstructor =
          ClassUtils.getConstructorForArgTypes( getCallName(), getArgTypes(),
                                                getPreferredPackageName() );
    }
    return matchingConstructor;
  }

  /**
   * @param matchingConstructor
   *          the matchingConstructor to set
   */
  public void setMatchingConstructor( Constructor< ? > matchingConstructor ) {
    if ( this.matchingConstructor != matchingConstructor ) {
      setCall( null );
      this.matchingConstructor = matchingConstructor;
    }
  }

  /**
   * @return the constructorDecl
   */
  public ConstructorDeclaration getConstructorDecl() {
    if ( constructorDecl == null ) {
      // Find the right ConstructorDeclaration if it exists.
      Set< ConstructorDeclaration > ctors =
          ( exprXlator == null ? null
                               : ( exprXlator.getClassData() == null ? null
                                                                     : exprXlator.getClassData()
                                                                                 .getConstructors( getCallName() ) ) );
      constructorDecl = null;
      if ( !Utils.isNullOrEmpty( ctors ) ) {
        constructorDecl =
            getBestArgTypes( ctors, getArgTypes(), getPreferredPackageName() );
        if ( constructorDecl == null ) {
          constructorDecl = ctors.iterator().next();
          // Warning just grabs the first constructor!
          if ( ctors.size() > 1 ) {
            System.err.println( "Warning! " + ctors.size()
                                + " constructors for " + getCallName()
                                + ": just grabbing the first!" );
          }
        }
        assert ( constructorDecl != null );
      }
    }
    return constructorDecl;
  }

  /**
   * @param constructorDecl
   *          the constructorDecl to set
   */
  public void setConstructorDecl( ConstructorDeclaration constructorDecl ) {
    if ( this.constructorDecl != constructorDecl ) {
      setMethodJava( null );
      this.constructorDecl = constructorDecl;
    }
  }

  /**
   * @return the matchingMethod
   */
  public Method getMatchingMethod() {
    Debug.out("getMatchingMethod()");
    if ( matchingMethod == null ) {
      // Try using reflection to find the method, but class may not exist.
      Method m1 = ClassUtils.getMethodForArgTypes(getClassName(),
                                                 getPreferredPackageName(),
                                                 getCallName(),
                                                 getArgTypes(),
                                                 false);
      Debug.out("getMatchingMethod(): m1 = " + m1);
      Member mm = m1;

      // Compare
      if ( getIsTimeVarying() ) {
        Class<?> cls = getWrappedType();
        String clsName = cls == null ? null : cls.getCanonicalName();
        if ( clsName == null  && cls != null ) {
          clsName = cls.getName();
        }
        System.out.println("WWWWWWWWWWWWWWWWWWWW    Wrapped Type = " + clsName + "   WWWWWWWWWWWWWWWWWWWW");
        
        Call call = searchForCall(getCallName(), null, (List<Class<?>>) Utils.arrayAsList(getArgTypes()));
        Member m2 = call == null ? null : call.getMember();
//                ClassUtils.getMethodForArgTypes(clsName,
//                getPreferredPackageName(),
//                getCallName(),
//                getArgTypes(),
//                false);
        Debug.out("getMatchingMethod(): m2 = " + m2);
        Member[] methods = new Member[]{m1, m2};
        ClassUtils.ArgTypeCompare atc = new ClassUtils.ArgTypeCompare(null, null, getArgTypes());
        if (methods != null) {
          for (Member m : methods) {
            if (m != null && m.getName() != null ) {
              if ( m.getName().equals(callName) ) {
                Class<?>[] params = m instanceof Method ? ((Method)m).getParameterTypes() : ((Constructor<?>)m).getParameterTypes();
                boolean isVarArgs = m instanceof Method ? ((Method)m).isVarArgs() : ((Constructor<?>)m).isVarArgs();
                atc.compare(m, params, isVarArgs);
              }
            } else {
              System.out.println("WWWWWWWWWWWWWWWWWWWW    method has no name!!! " + m + "    WWWWWWWWWWWWWWWWWWWW");
            }
          }
        }
        if ( atc.best != null ) {
          mm = (Member) atc.best;
        }
        if ( mm != m1 && mm == m2 ) {
          System.out.println("WWWWWWWWWWWWWWWWWWWW    IS TIMEVARYING CALL   WWWWWWWWWWWWWWWWWWWW");
          setTimeVaryingCall(true);
        } else {
          System.out.println("WWWWWWWWWWWWWWWWWWWW    IS NOT TIMEVARYING CALL   WWWWWWWWWWWWWWWWWWWW");
        }
      }

      if ( mm instanceof Method ) {
        setMatchingMethod((Method)mm);
      } else {
        setMatchingConstructor((Constructor<?>)mm);
      }
    }
    Debug.out("getMatchingMethod(): returning " + matchingMethod);

    return matchingMethod;
  }

  /**
   * @param matchingMethod
   *          the matchingMethod to set
   */
  public void setMatchingMethod( Method matchingMethod ) {
    Debug.out("setMatchingMethod(" + matchingMethod + ")");

    if ( this.matchingMethod != matchingMethod ) {
      setCall( null );
      this.matchingMethod = matchingMethod;
    }
  }

  /**
   * @return the methodDecl
   */
  public MethodDeclaration getMethodDecl() {
    if ( methodDecl == null ) {
      // Get the list of methods with the same name (callName).
      Set< MethodDeclaration > classMethods =
          this.exprXlator.getClassData()
                         .getClassMethodsWithName( getCallName(),
                                                   getClassName() );
      // Find the right MethodDeclaration if it exists.
      if ( !Utils.isNullOrEmpty( classMethods ) ) {
        methodDecl = getBestArgTypes( classMethods, getArgTypes(),
                                      getPreferredPackageName() );
        if ( methodDecl == null ) {
          // Warning just grabs the first method of this name!
          if ( classMethods.size() > 1 ) {
            System.err.println( "Warning! " + classMethods.size()
                                + " methods with name " + getCallName() + " in "
                                + getClassName()
                                + ": just grabbing the first!" );
          }
          // Add vector of argument types to getMethod() call
          methodDecl = classMethods.iterator().next();
        }
        assert ( methodDecl != null );
      }
    }
    Debug.out("getMethodDecl(): returning " + methodDecl);

    return methodDecl;
  }

  /**
   * @param methodDecl
   *          the methodDecl to set
   */
  public void setMethodDecl( MethodDeclaration methodDecl ) {
    if ( this.methodDecl != methodDecl ) {
      setMethodJava( null );
      this.methodDecl = methodDecl;
    }
  }

  public List< Expression > getArgExpressions() {
    List< Expression > argExprs = null;
    // Can't use isMethodOrConstructor() here because sometimes a constructor is
    // parsed as a method (when there is no new).
    if ( this.expression instanceof MethodCallExpr ) {
      argExprs = getMethodCallExpr().getArgs();
    } else {
      argExprs = getObjectCreationExpr().getArgs();
    }
    return argExprs;
  }

  public Class< ? >[] getArgTypes() {
    if ( argTypes == null ) initArgs();
    return argTypes;
  }

  private void initArgs() {
    setArgTypes( null );
    setArgs( new Vector< Object >() );

    List< Expression > argExprs = getArgExpressions();
    if ( argExprs != null ) {
      argTypes = new Class< ? >[ argExprs.size() ];
      for ( int i = 0; i < argExprs.size(); ++i ) {
        String argClassName = exprXlator.astToAeExprType( argExprs.get( i ), null, true, true); 
        List< Class<?>> classList = ClassUtils.getClassesForName( argClassName, false);
        if (classList != null && classList.size() > 1) {
          boolean containsJavaPrimitive = false;
          boolean restScala = true;
          Class<?> javaPrimitiveClass = null;
          for (Class<?> c : classList) {
            if (ClassUtils.isPrimitive( c ) && c.toString().contains( "java" )) {
              containsJavaPrimitive = true;
              javaPrimitiveClass = c;
            } else if (!(c.toString().toLowerCase().contains( "scala" ))) {
              restScala = false;
            }
          }
          if (containsJavaPrimitive && restScala) {
            argTypes[i] = javaPrimitiveClass;
          }
        } else {
          argTypes[ i ] =
              ClassUtils.getClassForName( argClassName,
                                          null, getPreferredPackageName(),
                                          false );
        }
        Object arg =
            exprXlator.astToAeExpression( argExprs.get( i ),
                                          ClassUtils.toString( argTypes[ i ] ),
                                          null,
                                          isConvertingArgumentsToExpressions(),
                                          true, false, true, isEvaluateCall() );
        if ( isConvertingArgumentsToExpressions()
             && !( arg instanceof gov.nasa.jpl.ae.event.Expression ) ) {
          arg = new gov.nasa.jpl.ae.event.Expression( arg );
        }
        this.args.add( arg );
      }
    }
  }

  private void setArgTypes( Class< ? >[] argTypes ) {
    if ( this.argTypes != argTypes ) {
      setMethodJava( null );
      setArgumentArrayJava( null );
      setMatchingMethod( null );
      setMatchingConstructor( null );
      setMethodDecl( null );
      setConstructorDecl( null );
      this.argTypes = argTypes;
    }
  }

  /**
   * @return the methodJava
   */
  public String getMethodJava() {
    if ( Utils.isNullOrEmpty( methodJava ) ) {
      Debug.out("getMethodJava()" );

      // call getMatchingMethod() to make find out if it's a TimeVaryingFunctionCall
      Method m = getMatchingMethod();
      Constructor c = getMatchingConstructor();

      if ( m == null && c != null ) setMethodOrConstructor( false );
      else if ( c == null && m != null ) setMethodOrConstructor( true );

      StringBuffer methodJavaSb = new StringBuffer();
      if ( isMethodOrConstructor() ) {
        String classNameString;
        if ( Utils.isNullOrEmpty( getClassName() ) ) {
          classNameString = "null";
        } else {
          if ( Boolean.TRUE.equals(getTimeVaryingCall()) ) {
            if ( m != null ) {
              classNameString = m.getDeclaringClass().getCanonicalName();
            } else if ( getWrappedType() != null ) {
              classNameString = getWrappedType().getSimpleName();
            } else {
              classNameString = null;
            }
          } else {
            classNameString = findClassNameWithMatchingMethod();
          }
          if ( classNameString == null ) {
            classNameString = "null";
          } else if (classNameString.equals("this")) {
            classNameString = "\"" + fullClassName(getClassName()) + "\"";
          } else {
            classNameString = "\"" + fullClassName(classNameString) + "\"";
          }
        }
        String preferredPackageNameString;
        if ( Utils.isNullOrEmpty( getPreferredPackageName() ) ) {
          preferredPackageNameString = "null";
        } else {
          preferredPackageNameString = "\"" + getPreferredPackageName() + "\"";
        }
        //Assert.assertFalse( Utils.isNullOrEmpty( getCallName() ) );
        methodJavaSb.append( "ClassUtils.getMethodForArgTypes("
                             + classNameString + ", "
                             + preferredPackageNameString + ", \""
                             + getCallName() + "\"" );

        if ( getMethodDecl() != null ) {

          for ( japa.parser.ast.body.Parameter parameter : getMethodDecl().getParameters() ) {
            methodJavaSb.append( ", " );
            methodJavaSb.append( ClassUtils.noParameterName( parameter.getType()
                                                                      .toString() )
                                 + ".class" );
          }

        } else // if ( !classMethods.isEmpty() ) {
        if ( getMatchingMethod() != null
             && getMatchingMethod().getParameterTypes() != null ) {
          for ( Class< ? > type : getMatchingMethod().getParameterTypes() ) {
            methodJavaSb.append( ", " + ClassUtils.toString( type ) );
            // methodJavaSb.append( ", " );
            // String typeName = type.getName();
            // if ( typeName != null ) typeName = typeName.replace( '$', '.'
            // );
            // methodJavaSb.append( ClassUtils.noParameterName( typeName )
            // + ".class" );
          }
        } else {
          Class< ? >[] args = getArgTypes();
          if ( args != null ) {
            for ( Class< ? > a : args ) {
              methodJavaSb.append( ", " );
              if (a == null) {
                methodJavaSb.append( "Object.class" );
              } else {
                methodJavaSb.append( ClassUtils.noParameterName(a.getCanonicalName()) + ".class" );

              }
            }
          }
        }
        
      } else {
        methodJavaSb.append( "ClassUtils.getConstructorForArgTypes("
                             + ClassUtils.noParameterName( getCallName() )
                             + ".class" );
        if ( getConstructorDecl() != null ) {
          if ( getConstructorDecl() != null
               && getConstructorDecl().getParameters() != null ) {
            for ( japa.parser.ast.body.Parameter parameter : getConstructorDecl().getParameters() ) {
              methodJavaSb.append( ", " );
              methodJavaSb.append( ClassUtils.noParameterName( parameter.getType()
                                                                        .toString() )
                                   + ".class" );
            }
          }
        } else {
          // Try using reflection to find the method, but class may not
          // exist.
          if ( getMatchingConstructor() != null ) {
            for ( Class< ? > type : getMatchingConstructor().getParameterTypes() ) {
              methodJavaSb.append( ", " + ClassUtils.toString( type ) );
              // if ( type.isArray() )
              // String typeName = type.getName();
              // if ( typeName != null ) typeName = typeName.replace( '$', '.'
              // );
              // methodJavaSb.append( ClassUtils.noParameterName( typeName )
              // + ".class" );
            }
          }
        }
      }
      methodJavaSb.append( " )" );
      setMethodJava( methodJavaSb.toString() );
    }
    Debug.out("getMethodJava(): returning " + methodJava);
    return methodJava;
  }

  /**
   * @param methodJava
   *          the methodJava to set
   */
  public void setMethodJava( String methodJava ) {
    this.methodJava = methodJava;
  }

  /**
   * @return the isEffectFunction
   */
  public boolean isEffectFunction() {
    // Determine whether the function is regular or actually an effect.
    // For example, TimeVaryingMap.setValue(...) is an effect function, but
    // TimeVaryingMap.getValue(...) is not.
    // // TODO -- REVIEW -- type is never used! -- is it supposed to be used in

    // Assume it's an effect if the object it's called from is Affectable, and
    // try to prove that the function is not one of the effect functions for
    // that class.
    if ( getObjectType() != null
         && Affectable.class.isAssignableFrom( getObjectType() ) ) {
      if ( isMethodOrConstructor() && getMethodCallExpr() != null
           && getMethodCallExpr().getName() != null
           && !getMethodCallExpr().getName().startsWith( "getValue" )
           && ( getCallName() == null || getObjectType() != TimeVaryingMap.class
                || TimeVaryingMap.effectMethodNames()
                                 .contains( getCallName() ) ) ) {
        setEffectFunction( true );
      } else {
        setEffectFunction( false );
      }
    } else {
      setEffectFunction( false );
    }

    // HACK -- not going to try and prove it isn't.
    // if ( isEffectFunction && type != null ) {
    // Class<?>[] types = new Class<?>[]{ TimeVaryingMap, TimeVaryingList,
    // ObjectFlow, Consumable,
    // isEffectFunction =
    // }
    return isEffectFunction;
  }

  /**
   * @param isEffectFunction
   *          the isEffectFunction to set
   */
  public void setEffectFunction( Boolean isEffectFunction ) {
    if ( this.isEffectFunction != isEffectFunction ) {
      setCall( null );
      this.isEffectFunction = isEffectFunction;
    }
  }

  /**
   * @return the argumentArrayJava
   */
  public String getArgumentArrayJava() {
    if ( argumentArrayJava == null ) {
      // Build Java text to construct an array enclosing the arguments to be
      // passed to the method call.
      StringBuffer argumentArraySb = new StringBuffer();
      argumentArraySb.append( "new Object[]{ " );
      boolean first = true;
      List< Expression > argExprs = getArgExpressions();
      if ( argExprs != null ) {
        for ( Expression a : argExprs ) {
          if ( first ) {
            first = false;
          } else {
            argumentArraySb.append( ", " );
          }
          if ( isConvertingArgumentsToExpressions() ) {
            String e =
                exprXlator.astToAeExpr( a, isConvertingArgumentsToExpressions(),
                                        true, true );
            if ( Utils.isNullOrEmpty( e ) || e.matches( "[(][^()]*[)]null" ) ) {
              argumentArraySb.append( a );
            } else {
              argumentArraySb.append( e );
            }
          } else {
            argumentArraySb.append( a );
          }
        }
      }
      argumentArraySb.append( " } " );
      setArgumentArrayJava( argumentArraySb.toString() );
    }
    return argumentArrayJava;
  }

  /**
   * @param argumentArrayJava
   *          the argumentArrayJava to set
   */
  public void setArgumentArrayJava( String argumentArrayJava ) {
    this.argumentArrayJava = argumentArrayJava;
  }

  /**
   * @return the args
   */
  public Vector< Object > getArgs() {
    if ( Utils.isNullOrEmpty( args ) ) initArgs();
    return args;
  }

  /**
   * @param args
   *          the args to set
   */
  public void setArgs( Vector< Object > args ) {
    if ( this.args != args ) {
      setMethodJava( null );
      setArgumentArrayJava( null );
      setMatchingMethod( null );
      setMatchingConstructor( null );
      this.args = args;
    }
  }

  /**
   * @return the convertingArgumentsToExpressions
   */
  public boolean isConvertingArgumentsToExpressions() {
    return convertingArgumentsToExpressions;
  }

  /**
   * @param convertingArgumentsToExpressions
   *          the convertingArgumentsToExpressions to set
   */
  public void
         setConvertingArgumentsToExpressions( boolean convertingArgumentsToExpressions ) {
    this.convertingArgumentsToExpressions = convertingArgumentsToExpressions;
  }

  /**
   * @return the evaluateCall
   */
  public boolean isEvaluateCall() {
    return evaluateCall;
  }

  /**
   * @param evaluateCall
   *          the evaluateCall to set
   */
  public void setEvaluateCall( boolean evaluateCall ) {
    if ( this.evaluateCall != evaluateCall ) {
      setArgs( null );
      this.evaluateCall = evaluateCall;
    }
  }

  /**
   * @return the call
   */
  public Call getCall() {
    if ( call == null ) {
      // This call causes infinite recursion!
      // The problem is that we may be trying to create a method that does not
      // yet exist!
      // In Call, object and args are expressions; make the method/constructor
      // an expression, too!
      // gov.nasa.jpl.ae.event.Expression< ? > methodExpr =
      // exprXlator.javaToAeExpression( methodJava, null, false );

      // gov.nasa.jpl.ae.event.Expression< ? > argumentArrayExpr =
      // exprXlator.javaToAeExpression( getArgumentArrayJava(), "Object[]",
      // false );

      if ( getMethodCallExpr() == null ) {
        if ( getMatchingConstructor() == null ) {
          Debug.error( true, "Cannot create constructor! " + this );
        } else {
          setCall( new ConstructorCall( expression,
                                        getMatchingConstructor(),
                                        // (Constructor< ?
                                        // >)methodExpr.evaluate( true ),
                                        // (Object[])argumentArrayExpr.evaluate(
                                        // true ) );
                                        getArgs(), this.returnType ) );
        }
      } else {
        if ( getMatchingMethod() == null ) {
          Call scall = searchForCall( getCallName(), getArgs(), Utils.arrayAsList(getArgTypes()) );
          if ( scall == null ) {
            Debug.error( true, "Cannot create method! " + this );
          } else {
            setCall( scall );
          }
        } else if ( isEffectFunction() ) {
          setCall( new EffectFunction( expression, getMatchingMethod(),
                                       // (Method)methodExpr.evaluate( true ),
                                       // (Object[])argumentArrayExpr.evaluate(
                                       // true ) );
                                       getArgs(), this.returnType ) );
        } else {
          setCall( new FunctionCall( expression, getMatchingMethod(),
                                     // (Method)methodExpr.evaluate( true ),
                                     // (Object[])argumentArrayExpr.evaluate(
                                     // true ) );
                                     getArgs(), this.returnType ) );
        }
      }
    }
    return call;
  }

  public static Class< ? > getType( Object arg ) {
    if ( arg == null ) return null;
    if ( arg instanceof gov.nasa.jpl.ae.event.Expression ) {
      return ( (gov.nasa.jpl.ae.event.Expression< ? >)arg ).getType();
    } else {
      if ( arg instanceof Wraps ) {
        return ( (Wraps< ? >)arg ).getType();
      }
    }
    return arg.getClass();
  }

  protected static boolean checkedUtilPackages = false;

  /**
   * Return a Call object based on the passed operation and arguments
   * 
   * @param operationName
   *          The name of operation used to search for the equivalent java call
   * @param argumentss
   *          The arguments for operation
   * @return Call object or null if the operationName is not a java call
   */
  public Call searchForCall( String operationName,
                             Vector< Object > argumentss,
                             Collection<Class<?>> argTypess) {

    Call call = null;
    Method method = null;
    Object object = null;

    /*
     * We will look for the corresponding Constructor or FunctionCall in the
     * following order:
     * 
     * 1. The current model class, ie EmsSystemModel (assume its a FunctionCall)
     * 2. The view_repo.syml package (assume its a ConstructorCall or
     * FunctionCall)
     * 3. The Functions.java and ae.event package (assume its a
     * ConstructorCall)
     * 4. Common Java classes (assume its a FunctionCall)
     * 5. The mbee.util package (assume its a FunctionCall)
     * 
     */
    if ( operationName == null ) return null;

    if ( Utils.isNullOrEmpty(argTypess) ) {
      argTypess = new ArrayList<Class<?>>();
    }

    // Finding out the argument types:
    if ( argumentss != null && Utils.isNullOrEmpty(argTypes))
    for ( Object arg : argumentss ) {
      Class< ? > type = getType( arg );
      if ( type == null ) {
        Debug.error( "Expecting an Expression for the argument: " + arg );
      }
      argTypess.add( type );
    }

    int argSize = argumentss != null ? argumentss.size() : (argTypess != null ? argTypess.size() : 0);
    boolean argsEmpty = Utils.isNullOrEmpty(argumentss);
    boolean argTypesEmpty = Utils.isNullOrEmpty(argTypes);

    Class[] argTypeArray = argTypes == null ? null : Utils.toArrayOfType(argTypes, Class.class);


    // // 1. Search API
    // method = ClassUtils.getMethodForArgTypes( model.getClass(),
    // operationName.toString(),
    // argTypes.toArray(new Class[]{}));
    //
    // if ( method != null ) {
    // object = model;
    // }
    // else {
    // 2.
    call =
        JavaToConstraintExpression.javaCallToEventFunction( operationName, null,
                                                            argumentss,
                                                            argTypeArray );

    if ( call == null ) {
      // 3.
      if ( argSize == 1 ) {
        call =
            JavaToConstraintExpression.unaryOpNameToEventFunction( operationName.toString(),
                                                                   null,
                                                                   false );
      } else if ( argSize == 2 ) {
        call =
            JavaToConstraintExpression.binaryOpNameToEventFunction( operationName.toString(),
                                                                    null );
      } else if ( argSize == 3
                  && operationName.toString().equalsIgnoreCase( "if" ) ) {
        call = JavaToConstraintExpression.getIfThenElseConstructorCall( null );
      } else if ( argSize > 3 && operationName.toString().toLowerCase()
                                                       .startsWith( "argm" ) ) {
        call = JavaToConstraintExpression.getArgMinMaxConstructorCall(
                                                                       operationName.toString()
                                                                                    .toLowerCase(),
                                                                       argSize,
                                                                       null );
      }

      if ( call != null ) {
        call.setArguments( argumentss );
      } else {
        // 4.
        if ( argsEmpty && !argTypesEmpty ) {
          method = ClassUtils.getJavaMethodForCommonFunction(operationName.toString(),
                  argTypeArray);
        } else {
          method = ClassUtils.getJavaMethodForCommonFunction(operationName.toString(),
                                                             argumentss == null ? new Object[]{} : argumentss.toArray());
        }

        // 5.
        if ( method == null ) {
          // checking the ae.util and mbee.util packages
          String[] packages = new String[]{"gov.nasa.jpl.ae.util", "gov.nasa.jpl.mbee.util"};

          // Check to see if they exist.
          if ( !checkedUtilPackages ) {
            checkedUtilPackages = true;
            for (String ps : packages) {
              Package p = Package.getPackage(ps);
              if (p == null) {
                Debug.error(true, false, "Warning! Could not get package: " + ps);
              }
            }
          }

          call = JavaToConstraintExpression.javaCallToCall(packages, operationName,
                                                          null, argumentss,
                                                           argTypeArray);
        }

      }

    } // Ends call == null

    // }

    if ( call == null && method == null ) {
      // TODO -- if it *still* fails, maybe search through all classes of all
      // packages for a method with this name.
    }

    // Make the FunctionCall if it was not a ConstructorCall:
    if ( method != null ) {
      call = new FunctionCall( object, method, argumentss != null ? argumentss : new Vector<Object>(), null );
    }

    return call;
  }

  /**
   * @param call
   *          the call to set
   */
  public void setCall( Call call ) {
    this.call = call;
  }

  // /**
  // * @return the generatedDependencies
  // */
  // public ArrayList< FieldDeclaration > getGeneratedDependencies() {
  // return generatedDependencies;
  // }
  //
  // /**
  // * @param generatedDependencies the generatedDependencies to set
  // */
  // public
  // void
  // setGeneratedDependencies( ArrayList< FieldDeclaration >
  // generatedDependencies ) {
  // this.generatedDependencies = generatedDependencies;
  // }

  public void setPreferredPackageName( String preferredPackageName ) {
    if ( this.preferredPackageName != preferredPackageName ) {
      setObjectType( null );
      setMethodJava( null );
      setMatchingMethod( null );
      setMatchingConstructor( null );
      this.preferredPackageName = preferredPackageName;
    }
  }

  public String getPreferredPackageName() {
    return this.preferredPackageName;
  }

  /**
   * @return the exprXlator
   */
  public JavaToConstraintExpression getExprXlator() {
    return exprXlator;
  }

}
// public Date epoch = new Date();