package gov.nasa.jpl.ae.util;

import gov.nasa.jpl.ae.event.Affectable;
import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.ConstructorCall;
import gov.nasa.jpl.ae.event.EffectFunction;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.TimeVarying; // don't remove!!
import gov.nasa.jpl.ae.event.TimeVaryingMap; // don't remove!!
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import junit.framework.Assert;

/**
 * @author bclement
 *
 */
public class JavaForFunctionCall {
  /**
   * 
   */
  //private final EventXmlToJava xmlToJava;
  protected final JavaToConstraintExpression exprXlator;//.expressionTranslator;

  protected Expression expression = null;
  protected MethodCallExpr methodCallExpr = null;
  protected ObjectCreationExpr objectCreationExpr = null;
  protected Boolean methodOrConstructor = null; 
  protected String object = null;
  protected gov.nasa.jpl.ae.event.Expression< ? > objectExpr = null;
  protected String objectTypeName;
  protected Class<?> objectType;
  protected String className = null;
  protected String callName = null;
  protected Constructor< ? > matchingConstructor = null;
  protected ConstructorDeclaration constructorDecl = null;
  protected Method matchingMethod = null;
  protected MethodDeclaration methodDecl = null;
  protected String methodJava = null;  // Java text for getting java.reflect.Method
  protected Boolean isEffectFunction = null;
  protected String argumentArrayJava = null;
  protected Vector<Object> args = null; //new Vector<Object>();
  protected boolean convertingArgumentsToExpressions = false;
  protected boolean evaluateCall = false;
  protected String preferredPackageName = null;
  
  protected Class<?> returnType;
  
  protected Call call = null;
  
//  /**
//   * When expressions are passed to functions that are expecting parameters, a
//   * dependency can be formed.
//   */
//  public ArrayList<FieldDeclaration> generatedDependencies =
//      new ArrayList< FieldDeclaration >();

  private Class< ? >[] argTypes = null;
  
  public JavaForFunctionCall( JavaToConstraintExpression expressionTranslator,
                              Expression expression,
                              boolean convertArgumentsToExpressions,
                              String preferredPackageName,
                              Class<?> returnType ) {
    this( expressionTranslator, expression, convertArgumentsToExpressions,
          preferredPackageName, false, returnType );
  }
                              
//  public JavaForFunctionCall( EventXmlToJava eventXmlToJava,
//                              Expression expression,
//                              boolean convertArgumentsToExpressions,
//                              String preferredPackageName,
//                              boolean evaluateCall ) {
//    this( eventXmlToJava.expressionTranslator, eventXmlToJava, expression,
//          convertArgumentsToExpressions, preferredPackageName, evaluateCall );
//    
//  }
//  public JavaForFunctionCall( JavaToConstraintExpression exprTranslator,
//                              EventXmlToJava eventXml2Java,
//                              Expression expression,
//                              boolean convertArgumentsToExpressions,
//                              String preferredPackageName,
//                              boolean evaluateCall ) {

    public JavaForFunctionCall( JavaToConstraintExpression exprTranslator,
                                Expression expression,
                                boolean convertArgumentsToExpressions,
                                String preferredPackageName,
                                boolean evaluateCall, Class< ? > returnType  ) {

    // Arguments may be Expressions, Parameters, or other. Method parameter
    // types may also be Expressions, Parameters, or other.
    //
    // If argument is Expression, and parameter is Parameter,
    //   see if the expression is a parameter and pass that instead.    
    //
    // If argument is Expression, and parameter is other,
    //   pass the evaluation of the Expression.
    //
    // If argument is Parameter, and parameter is Expression,
    //   wrap the argument in an Expression.
    //
    // If argument is Parameter, and parameter is other,
    //   pass the Parameter's value.
    //
    // If argument is other,
    //   wrap the argument in an Expression or Parameter according to the type.
    //
    // If there is a choice of methods, prefer matched types to matches through
    // conversion, except when convertArgumentsToExpressions==true. Prefer those
    // that match Expressions to those that match Parameters and Parameters to
    // other.

     this.returnType = returnType;
      
    assert expression != null;
    
    setExpression( expression ); // this replaces commented out code below
//    if ( expression instanceof MethodCallExpr ) {
//      methodCallExpr = (MethodCallExpr)expression;
//      methodOrConstructor = true;
//    } else if ( expression instanceof ObjectCreationExpr ) {
//      objectCreationExpr = (ObjectCreationExpr)expression;
//      methodOrConstructor = false;
//    } else {
//      assert false;
//    }
//    
    this.exprXlator = exprTranslator;
    //this.xmlToJava = eventXml2Java;
    // REVIEW -- How do we know when we want to convert args to Expressions?
    // Constructors of events (and probably classes) convert args to
    // Expressions.  For now, do not convert args for any other calls.
    setConvertingArgumentsToExpressions( convertArgumentsToExpressions ); //this.convertingArgumentsToExpressions = convertArgumentsToExpressions;
    setEvaluateCall( evaluateCall ); //this.evaluateCall = evaluateCall;

    // code below replaced by getCallName() and getClassName() 
//    callName =
//        methodOrConstructor ? methodCallExpr.getName()
//                            : objectCreationExpr.getType().toString();
//    className = this.exprXlator.getCurrentClass();
    
    // Get object from scope
    // code below replaced by getObject() and getObjectExpr()
//    Expression scope = getScope();
//    object = ( (JavaToConstraintExpression)this.exprXlator ).getObjectFromScope( scope );
//    Object tmp = ( (JavaToConstraintExpression)this.exprXlator ).getObjectExpressionFromScope( scope );
//    if ( tmp instanceof Expression ) {
//      objectExpr = (gov.nasa.jpl.ae.event.Expression< ? >)tmp;
//    }

    // code below is replaced by getObjectTypeName() and getClassName()
//    objectTypeName = this.exprXlator.astToAeExprType( scope, true, true );
//    if ( objectTypeName != null ) {
//      className = objectTypeName;
//    }

    //code below is replaced by getObject()
//    if ( Utils.isNullOrEmpty( object ) ) {
//      if ( methodOrConstructor ||
//          exprXlator.getClassData().isInnerClass( objectTypeName ) ) {
//        object = "this";
//      } else {
//        object = "null";
//      }
//    }

    setPreferredPackageName( preferredPackageName );
    
    // code below replaced by getObjectType()
//    if ( !Utils.isNullOrEmpty( className ) &&
//         className != exprXlator.getCurrentClass() ) {
//      objectType = ClassUtils.getClassForName( className, preferredPackageName, true );
//    }

    // Assemble Java text for finding the java.reflect.Method for callName
    // TODO -- move most of this to ClassData, instantiate this.call, and
    // use it to generate methodJava text.

    // code below replaced by getMethodJava()
//    StringBuffer methodJavaSb = new StringBuffer();
//    Class< ? >[] argTypesArr = null;
//    List<Expression> argExprs = null;
//    if ( methodOrConstructor ) {
//      argExprs = methodCallExpr.getArgs();
//    } else {
//      argExprs = objectCreationExpr.getArgs();      
//    }
//    if ( argExprs != null ) {
//      argTypesArr = new Class< ? >[ argExprs.size() ];
//      for ( int i = 0; i < argExprs.size(); ++i ) {
//        argTypesArr[ i ] =
//            ClassUtils.getClassForName( exprXlator.astToAeExprType( argExprs.get( i ),
//                                                              true, true ),
//                                                              preferredPackageName,
//                                                              false );
//        Object arg = exprXlator.astToAeExpression( argExprs.get( i ),
//                                                   ClassUtils.toString( argTypesArr[ i ] ),
//                                                   null,
//                                                   isConvertArgumentsToExpressions(),
//                                                   true, true, evaluateCall );
//        if ( isConvertArgumentsToExpressions() &&
//             !( arg instanceof gov.nasa.jpl.ae.event.Expression ) ) {
//          arg = new gov.nasa.jpl.ae.event.Expression( arg );
//        }
//        this.args.add( arg );
//      }
//    }
//    if ( methodOrConstructor ) {
//      String classNameString;
//      if ( Utils.isNullOrEmpty( className ) ) {
//        classNameString = "null";
//      } else {
//        classNameString = "\"" + className + "\"";
//      }
//      String preferredPackageNameString;
//      if ( Utils.isNullOrEmpty( preferredPackageName ) ) {
//        preferredPackageNameString = "null";
//      } else {
//        preferredPackageNameString = "\"" + preferredPackageName + "\"";
//      }
//      Assert.assertFalse( Utils.isNullOrEmpty( callName ) );
//      methodJavaSb.append( "ClassUtils.getMethodForArgTypes(" + classNameString
//                           + ", " + preferredPackageNameString + ", \""
//                           + callName + "\"" );
//
//      // Try using reflection to find the method, but class may not exist.
//      matchingMethod  = null;
//      matchingMethod =
//          ClassUtils.getMethodForArgTypes( className, preferredPackageName,
//                                           callName, argTypesArr, false );
//
//      // Get the list of methods with the same name (callName).
//      Set< MethodDeclaration > classMethods =
//          this.exprXlator.getClassData().getClassMethodsWithName( callName, className );
//      // Find the right MethodDeclaration if it exists.
//      if ( !Utils.isNullOrEmpty( classMethods ) ) {
//
//        methodDecl  = null;
//        methodDecl =
//            getBestArgTypes( classMethods, argTypesArr, preferredPackageName );
//        if ( methodDecl == null ) {
//          // Warning just grabs the first method of this name!
//          if ( classMethods.size() > 1 ) {
//            System.err.println( "Warning! " + classMethods.size()
//                                + " methods with name " + callName + " in "
//                                + className + ": just grabbing the first!" );
//          }
//          // Add vector of argument types to getMethod() call
//          methodDecl = classMethods.iterator().next();
//        }
//        assert ( methodDecl != null );
//        for ( japa.parser.ast.body.Parameter parameter : methodDecl.getParameters() ) {
//          methodJavaSb.append( ", " );
//          methodJavaSb.append( ClassUtils.noParameterName( parameter.getType().toString() )
//                               + ".class" );
//        }
//
//      } else { // if ( !classMethods.isEmpty() ) {
//        if ( matchingMethod != null && matchingMethod.getParameterTypes() != null ) {
//          for ( Class< ? > type : matchingMethod.getParameterTypes() ) {
//            methodJavaSb.append( ", " + ClassUtils.toString( type ) );
////            methodJavaSb.append( ", " );
////            String typeName = type.getName();
////            if ( typeName != null ) typeName = typeName.replace( '$', '.' );
////            methodJavaSb.append( ClassUtils.noParameterName( typeName )
////                                 + ".class" );
//          }
//        }
//      }
//    } else {
//      methodJavaSb.append( "ClassUtils.getConstructorForArgTypes(" 
//                           + ClassUtils.noParameterName( callName )
//                           + ".class" );
//      // Find the right ConstructorDeclaration if it exists.
//      Set< ConstructorDeclaration > ctors =
//          ( exprXlator == null
//            ? null 
//            : ( exprXlator.getClassData() == null
//                ? null 
//                : exprXlator.getClassData().getConstructors( callName ) ) );
//      constructorDecl  = null;
//      if ( !Utils.isNullOrEmpty( ctors ) ) {
//        constructorDecl =
//            getBestArgTypes( ctors, argTypesArr, preferredPackageName );
//        if ( constructorDecl == null ) {
//          constructorDecl = ctors.iterator().next();
//          // Warning just grabs the first constructor!
//          if ( ctors.size() > 1 ) {
//            System.err.println( "Warning! " + ctors.size()
//                                + " constructors for " + callName
//                                + ": just grabbing the first!" );
//          }
//        }
//        assert ( constructorDecl != null );
//        if ( constructorDecl != null && constructorDecl.getParameters() != null ) {
//          for ( japa.parser.ast.body.Parameter parameter : 
//            constructorDecl.getParameters() ) {
//            methodJavaSb.append( ", " );
//            methodJavaSb.append( ClassUtils.noParameterName( parameter.getType().toString() )
//                                 + ".class" );
//          }
//        }
//      }
//      if ( constructorDecl == null ) {
//        // Try using reflection to find the method, but class may not exist.
//        matchingConstructor =
//            ClassUtils.getConstructorForArgTypes( callName, argTypesArr,
//                                                  preferredPackageName );
//        if ( matchingConstructor != null ) {
//          for ( Class< ? > type : matchingConstructor.getParameterTypes() ) {
//            methodJavaSb.append( ", " + ClassUtils.toString( type ) );
////            if ( type.isArray() ) 
////            String typeName = type.getName();
////            if ( typeName != null ) typeName = typeName.replace( '$', '.' );
////            methodJavaSb.append( ClassUtils.noParameterName( typeName ) 
////                                 + ".class" );
//          }
//        }
//      }
//    }
//    methodJavaSb.append( " )" );
//    methodJava = methodJavaSb.toString();

    // code below replaced by isEffectFunction()
//    // Determine whether the function is regular or actually an effect.
//    // For example, TimeVaryingMap.setValue(...) is an effect function, but
//    // TimeVaryingMap.getValue(...) is not.
//    // TODO -- REVIEW -- type is never used! -- is it supposed to be used in assigning isEffectFunction???
//    Class<?> type = null;
//    if ( matchingMethod != null ) {
//      type = matchingMethod.getDeclaringClass();
//    } else if ( objectType != null ) {
//      type = objectType;
//    }
//    // Assume it's an effect if the object it's called from is Affectable, and
//    // try to prove that the function is not one of the effect functions for
//    // that class.
//    isEffectFunction = objectType != null && Affectable.class.isAssignableFrom( objectType );
//    // HACK -- not going to try and prove it isn't.
////    if ( isEffectFunction && type != null ) {
////      Class<?>[] types = new Class<?>[]{ TimeVaryingMap, TimeVaryingList, ObjectFlow, Consumable, 
////      isEffectFunction =  
////    }

    // code below is replaced by getArgumentArrayJava()
//    // Build Java text to construct an array enclosing the arguments to be
//    // passed to the method call.
//    StringBuffer argumentArraySb = new StringBuffer();
//    argumentArraySb.append( "new Object[]{ " );
//    boolean first = true;
//    List<Expression> argExprs = getArgExpressions();
//    if ( argExprs != null ) {
//      for ( Expression a : argExprs ) {
//        if ( first ) {
//          first = false;
//        } else {
//          argumentArraySb.append( ", " );
//        }
//        if ( convertArgumentsToExpressions ) {
//          String e = 
//              exprXlator.astToAeExpr( a, convertArgumentsToExpressions, true, true );
//          if ( Utils.isNullOrEmpty( e ) || e.matches( "[(][^()]*[)]null" ) ) {
//            argumentArraySb.append( a );
//          } else {
//            argumentArraySb.append( e );
//          }
//        } else {
//          argumentArraySb.append( a );
//        }
//      }
//    }
//    argumentArraySb.append( " } " );
//    argumentArrayJava = argumentArraySb.toString();
  }

  public <T> T getBestArgTypes( Set< T > declarations,
                                Class< ? >[] argTypesArr,
                                String preferredPackageName ) {
    Map< T, Pair< Class< ? >[], Boolean > > candidates =
        new TreeMap< T, Pair< Class< ? >[], Boolean > >(new CompareUtils.GenericComparator< T >());
    for ( T cd : declarations ) {
      
      List< Parameter > params = null;
      if ( cd instanceof ConstructorDeclaration ) {
        params = ((ConstructorDeclaration)cd).getParameters();
      } else if ( cd instanceof MethodDeclaration ) {
        params = ((MethodDeclaration)cd).getParameters();
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
              ClassUtils.getClassForName( param.getType().toString(),
                                          null, preferredPackageName, true );
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
    if ( isMethodOrConstructor() == true && getMethodCallExpr() != null) {
      return getMethodCallExpr().getScope();
    } else if ( isMethodOrConstructor() == false && getObjectCreationExpr() != null ) {
      return getObjectCreationExpr().getScope();
    }
    return null;
  }

  public boolean isStatic() {
    if ( isMethodOrConstructor() ) {
      if ( exprXlator.getClassData().knowIfStatic( getCallName() ) ) {
        return exprXlator.getClassData().isStatic( getCallName() );
      }
      if ( getMatchingMethod() != null &&
           Modifier.isStatic( getMatchingMethod().getModifiers() ) ) {
        return true;
      }
      if ( getMethodDecl() != null &&
           ModifierSet.isStatic( getMethodDecl().getModifiers() ) ) {
        return true;
      }
    } else {
      if ( exprXlator.getClassData().knowIfClassIsStatic( getCallName() ) ) {
        return exprXlator.getClassData().isClassStatic( getCallName() );
      }
      if ( getMatchingConstructor() != null &&
           Modifier.isStatic( getMatchingConstructor().getModifiers() ) ) {
        return true;
      }
      if ( getConstructorDecl() != null &&
           ModifierSet.isStatic( getConstructorDecl().getModifiers() ) ) {
        return true;
      }
    }
    return Utils.isNullOrEmpty( getObject() );
  }
  
  public Call toNewFunctionCall() {
    if ( Debug.isOn() ) Debug.outln( "JavaForFunctionCall.toNewFunctionCall() --> " + getCall() );
    return getCall();
  }

  public String getReturnTypeString() {
    String s = returnType == null ? "(Class<?>)null" : returnType + ".class";
    return s;
  }
  
  public String toNewFunctionCallString() {
    String fcnCallStr = null;
    String callTypeName = 
        ( getMethodCallExpr() == null ? "ConstructorCall"
                                 : ( isEffectFunction() ? "EffectFunction"
                                                        : "FunctionCall" ) ); 
//    if ( getObject().startsWith( "new FunctionCall" ) 
//         || getObject().startsWith( "new ConstructorCall" )
//      || getObject().startsWith( "new EffectFunction" ) ) {
//      // nest the function calls
//      fcnCallStr = "new " + callTypeName + "( null, " + getMethodJava() + ", "
//                   + getArgumentArrayJava() + ", " + getObject() + ", " + getReturnTypeString() + " )";
//    } else {
      String instance = getObject();
      if ( isStatic() ) {
        instance = "null";
      }
      fcnCallStr = "new " + callTypeName + "( " + instance + ", " + getMethodJava()
                   + ", " + getArgumentArrayJava() + ", " + getReturnTypeString() + " )";
//    }
    if ( isEvaluateCall() && !Utils.isNullOrEmpty( fcnCallStr ) ) {
      if ( !isConvertingArgumentsToExpressions() ) {
        fcnCallStr = "(" + fcnCallStr + ").evaluate(true)";
      }
    }
    if ( Debug.isOn() ) Debug.outln( "JavaForFunctionCall.toNewFunctionCallString() --> " + fcnCallStr );
    return fcnCallStr;
  }
  public String toNewExpressionString() {
    String s = "new Expression( " + toNewFunctionCallString() + " )";
    if ( Debug.isOn() ) Debug.outln( "JavaForFunctionCall.toNewExpressionString() --> " + s );
    return s;
  }
  
  @Override
  public String toString() {
    return toNewFunctionCallString();
  }

  public <T> gov.nasa.jpl.ae.event.Expression< T > toNewExpression() {
    gov.nasa.jpl.ae.event.Expression< T > r =
        new gov.nasa.jpl.ae.event.Expression< T >( toNewFunctionCall() );
    if ( Debug.isOn() ) Debug.outln( "JavaForFunctionCall.toNewExpression() --> " + r );
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
      setMethodOrConstructor( isMethodCall ? true : isConstructorCall ? false : null );
      setMethodCallExpr( isMethodCall ? (MethodCallExpr)expression : null );
      setObjectCreationExpr( isConstructorCall ? (ObjectCreationExpr)expression : null );
      if ( !isMethodCall && !isConstructorCall )  {
        Debug.error("Expression " + expression + " must be a MethodCallExpr or an ObjectCreationExpr!" );
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
   * @param methodCallExpr the methodCallExpr to set
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
   * @param objectCreationExpr the objectCreationExpr to set
   */
  public void setObjectCreationExpr( ObjectCreationExpr objectCreationExpr ) {
    if ( this.objectCreationExpr != objectCreationExpr ) {
      setCallName( null );
      setArgumentArrayJava( null );
      setCall( null );
      this.objectCreationExpr = objectCreationExpr;
    }
  }

  /**
   * @return the methodOrConstructor
   */
  public Boolean isMethodOrConstructor() {
    return methodOrConstructor;
  }

  /**
   * @param methodOrConstructor the methodOrConstructor to set
   */
  public void setMethodOrConstructor( Boolean methodOrConstructor ) {
    this.methodOrConstructor = methodOrConstructor;
  }

  /**
   * @return the object
   */
  public String getObject() {
    if ( Utils.isNullOrEmpty( object ) ) {
      object = ( (JavaToConstraintExpression)this.exprXlator ).getObjectFromScope( getScope() );
      if ( Utils.isNullOrEmpty( object ) ) {
//        if ( isMethodOrConstructor() ||
//            exprXlator.getClassData().isInnerClass( getObjectTypeName() ) ) {
        if ( isMethodOrConstructor() ||
            exprXlator.getClassData().isInnerClass( getObjectCreationExpr().getType()
                                                    .toString() ) ) {
          setObject( "this" );
        } else {
          setObject( "null" );
        }
      }

    }
    return object;
  }

  /**
   * @param object the object to set
   */
  public void setObject( String object ) {
    this.object = object;
  }

  /**
   * @return the objectExpr
   */
  public gov.nasa.jpl.ae.event.Expression< ? > getObjectExpr() {
    if ( objectExpr == null ) {
      Object tmp = ( (JavaToConstraintExpression)this.exprXlator ).getObjectExpressionFromScope( getScope() );
      if ( tmp instanceof Expression ) {
        setObjectExpr( (gov.nasa.jpl.ae.event.Expression< ? >)tmp );
      } else {
        if ( tmp != null ) {
          setObjectExpr(new gov.nasa.jpl.ae.event.Expression<Object>( tmp ) );
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
   * @param objectExpr the objectExpr to set
   */
  public void setObjectExpr( gov.nasa.jpl.ae.event.Expression< ? > objectExpr ) {
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
      setObjectTypeName( this.exprXlator.astToAeExprType( getScope(), null, true, true ) );
    }
    return objectTypeName;
  }

  /**
   * @param objectTypeName the objectTypeName to set
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
      if ( !Utils.isNullOrEmpty( getClassName() ) &&
          getClassName() != exprXlator.getCurrentClass() ) {
        setObjectType( ClassUtils.getClassForName( getClassName(), getCallName(), getPreferredPackageName(), true ) );
      }
    }
    return objectType;
  }

  /**
   * @param objectType the objectType to set
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
   * @param className the className to set
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
      setCallName( isMethodOrConstructor() ? getMethodCallExpr().getName()
                                           : getObjectCreationExpr().getType()
                                                                    .toString() );
    }
    return callName;
  }

  /**
   * @param callName the callName to set
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
   * @param matchingConstructor the matchingConstructor to set
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
          ( exprXlator == null
            ? null 
            : ( exprXlator.getClassData() == null
                ? null 
                : exprXlator.getClassData().getConstructors( getCallName() ) ) );
      constructorDecl  = null;
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
   * @param constructorDecl the constructorDecl to set
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
    if ( matchingMethod == null ) {
      // Try using reflection to find the method, but class may not exist.
      setMatchingMethod( ClassUtils.getMethodForArgTypes( getClassName(),
                                                          getPreferredPackageName(),
                                                          getCallName(),
                                                          getArgTypes(), false ) );
    }
    return matchingMethod;
  }

  /**
   * @param matchingMethod the matchingMethod to set
   */
  public void setMatchingMethod( Method matchingMethod ) {
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
          this.exprXlator.getClassData().getClassMethodsWithName( getCallName(), getClassName() );
      // Find the right MethodDeclaration if it exists.
      if ( !Utils.isNullOrEmpty( classMethods ) ) {
        methodDecl =
            getBestArgTypes( classMethods, getArgTypes(), getPreferredPackageName() );
        if ( methodDecl == null ) {
          // Warning just grabs the first method of this name!
          if ( classMethods.size() > 1 ) {
            System.err.println( "Warning! " + classMethods.size()
                                + " methods with name " + getCallName() + " in "
                                + getClassName() + ": just grabbing the first!" );
          }
          // Add vector of argument types to getMethod() call
          methodDecl = classMethods.iterator().next();
        }
        assert ( methodDecl != null );
      }
    }
    return methodDecl;
  }

  /**
   * @param methodDecl the methodDecl to set
   */
  public void setMethodDecl( MethodDeclaration methodDecl ) {
    if ( this.methodDecl != methodDecl ) {
      setMethodJava( null );
      this.methodDecl = methodDecl;
    }
  }

  public List<Expression> getArgExpressions() {
    List<Expression> argExprs = null;
    if ( isMethodOrConstructor() ) {
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
    setArgs( new Vector<Object>() );

    List<Expression> argExprs = getArgExpressions();
    if ( isMethodOrConstructor() ) {
      argExprs = getMethodCallExpr().getArgs();
    } else {
      argExprs = getObjectCreationExpr().getArgs();      
    }
    if ( argExprs != null ) {
      argTypes = new Class< ? >[ argExprs.size() ];
      for ( int i = 0; i < argExprs.size(); ++i ) {
        argTypes[ i ] =
            ClassUtils.getClassForName( exprXlator.astToAeExprType( argExprs.get( i ),
                                                                    null, true, true ),
                                        null, getPreferredPackageName(), false );
        Object arg = exprXlator.astToAeExpression( argExprs.get( i ),
                                                   ClassUtils.toString( argTypes[ i ] ),
                                                   null,
                                                   isConvertingArgumentsToExpressions(),
                                                   true, true, isEvaluateCall() );
        if ( isConvertingArgumentsToExpressions() &&
             !( arg instanceof gov.nasa.jpl.ae.event.Expression ) ) {
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
      StringBuffer methodJavaSb = new StringBuffer();
      if ( isMethodOrConstructor() ) {
        String classNameString;
        if ( Utils.isNullOrEmpty( getClassName() ) ) {
          classNameString = "null";
        } else {
          classNameString = "\"" + getClassName() + "\"";
        }
        String preferredPackageNameString;
        if ( Utils.isNullOrEmpty( getPreferredPackageName() ) ) {
          preferredPackageNameString = "null";
        } else {
          preferredPackageNameString = "\"" + getPreferredPackageName() + "\"";
        }
        Assert.assertFalse( Utils.isNullOrEmpty( getCallName() ) );
        methodJavaSb.append( "ClassUtils.getMethodForArgTypes(" + classNameString
                             + ", " + preferredPackageNameString + ", \""
                             + getCallName() + "\"" );

        // code below replaced by getMatchingMethod()
//        // Try using reflection to find the method, but class may not exist.
//        matchingMethod  = null;
//        matchingMethod =
//            ClassUtils.getMethodForArgTypes( className, preferredPackageName,
//                                             callName, argTypes, false );

        if ( getMethodDecl() != null ) {
          
          // code below replaced by getMethodDecl()
//        // Get the list of methods with the same name (callName).
//        Set< MethodDeclaration > classMethods =
//            this.exprXlator.getClassData().getClassMethodsWithName( callName, className );
//        // Find the right MethodDeclaration if it exists.
//        if ( !Utils.isNullOrEmpty( classMethods ) ) {
//          methodDecl  = null;
//          methodDecl =
//              getBestArgTypes( classMethods, argTypes, preferredPackageName );
//          if ( methodDecl == null ) {
//            // Warning just grabs the first method of this name!
//            if ( classMethods.size() > 1 ) {
//              System.err.println( "Warning! " + classMethods.size()
//                                  + " methods with name " + callName + " in "
//                                  + className + ": just grabbing the first!" );
//            }
//            // Add vector of argument types to getMethod() call
//            methodDecl = classMethods.iterator().next();
//          }
//
//          assert ( getMethodDecl() != null );
          
          for ( japa.parser.ast.body.Parameter parameter : getMethodDecl().getParameters() ) {
            methodJavaSb.append( ", " );
            methodJavaSb.append( ClassUtils.noParameterName( parameter.getType().toString() )
                                 + ".class" );
          }

        } else { // if ( !classMethods.isEmpty() ) {
          if ( getMatchingMethod() != null && getMatchingMethod().getParameterTypes() != null ) {
            for ( Class< ? > type : getMatchingMethod().getParameterTypes() ) {
              methodJavaSb.append( ", " + ClassUtils.toString( type ) );
//              methodJavaSb.append( ", " );
//              String typeName = type.getName();
//              if ( typeName != null ) typeName = typeName.replace( '$', '.' );
//              methodJavaSb.append( ClassUtils.noParameterName( typeName )
//                                   + ".class" );
            }
          }
        }
      } else {
        methodJavaSb.append( "ClassUtils.getConstructorForArgTypes(" 
                             + ClassUtils.noParameterName( getCallName() )
                             + ".class" );
//        // Find the right ConstructorDeclaration if it exists.
//        Set< ConstructorDeclaration > ctors =
//            ( exprXlator == null
//              ? null 
//              : ( exprXlator.getClassData() == null
//                  ? null 
//                  : exprXlator.getClassData().getConstructors( getCallName() ) ) );
//        constructorDecl  = null;
//        if ( !Utils.isNullOrEmpty( ctors ) ) {
//          constructorDecl =
//              getBestArgTypes( ctors, argTypes, preferredPackageName );
//          if ( constructorDecl == null ) {
//            constructorDecl = ctors.iterator().next();
//            // Warning just grabs the first constructor!
//            if ( ctors.size() > 1 ) {
//              System.err.println( "Warning! " + ctors.size()
//                                  + " constructors for " + callName
//                                  + ": just grabbing the first!" );
//            }
//          }
//          assert ( constructorDecl != null );
        if ( getConstructorDecl() != null ) {
          if ( getConstructorDecl() != null && getConstructorDecl().getParameters() != null ) {
            for ( japa.parser.ast.body.Parameter parameter : 
              getConstructorDecl().getParameters() ) {
              methodJavaSb.append( ", " );
              methodJavaSb.append( ClassUtils.noParameterName( parameter.getType().toString() )
                                   + ".class" );
            }
          }
        } else {
          // code below replaced by getMatchingConstructor()
//          // Try using reflection to find the method, but class may not exist.
//          matchingConstructor =
//              ClassUtils.getConstructorForArgTypes( callName, argTypes,
//                                                    preferredPackageName );
          if ( getMatchingConstructor() != null ) {
            for ( Class< ? > type : getMatchingConstructor().getParameterTypes() ) {
              methodJavaSb.append( ", " + ClassUtils.toString( type ) );
//              if ( type.isArray() ) 
//              String typeName = type.getName();
//              if ( typeName != null ) typeName = typeName.replace( '$', '.' );
//              methodJavaSb.append( ClassUtils.noParameterName( typeName ) 
//                                   + ".class" );
            }
          }
        }
      }
      methodJavaSb.append( " )" );
      setMethodJava( methodJavaSb.toString() );
    }
    return methodJava;
  }

  /**
   * @param methodJava the methodJava to set
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
//    // TODO -- REVIEW -- type is never used! -- is it supposed to be used in assigning isEffectFunction???
//    Class<?> type = null;
//    if ( matchingMethod != null ) {
//      type = matchingMethod.getDeclaringClass();
//    } else if ( objectType != null ) {
//      type = objectType;
//    }
    // Assume it's an effect if the object it's called from is Affectable, and
    // try to prove that the function is not one of the effect functions for
    // that class.
    if ( getObjectType() != null && Affectable.class.isAssignableFrom( getObjectType() ) ) {
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
//    if ( isEffectFunction && type != null ) {
//      Class<?>[] types = new Class<?>[]{ TimeVaryingMap, TimeVaryingList, ObjectFlow, Consumable, 
//      isEffectFunction =  
//    }
    return isEffectFunction;
  }

  /**
   * @param isEffectFunction the isEffectFunction to set
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
      List<Expression> argExprs = getArgExpressions();
      if ( argExprs != null ) {
        for ( Expression a : argExprs ) {
          if ( first ) {
            first = false;
          } else {
            argumentArraySb.append( ", " );
          }
          if ( isConvertingArgumentsToExpressions() ) {
            String e = 
                exprXlator.astToAeExpr( a, isConvertingArgumentsToExpressions(), true, true );
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
   * @param argumentArrayJava the argumentArrayJava to set
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
   * @param args the args to set
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
   * @param convertingArgumentsToExpressions the convertingArgumentsToExpressions to set
   */
  public void setConvertingArgumentsToExpressions( boolean convertingArgumentsToExpressions ) {
    this.convertingArgumentsToExpressions = convertingArgumentsToExpressions;
  }

  /**
   * @return the evaluateCall
   */
  public boolean isEvaluateCall() {
    return evaluateCall;
  }

  /**
   * @param evaluateCall the evaluateCall to set
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
      // The problem is that we may be trying to create a method that does not yet exist!
      // In Call, object and args are expressions; make the method/constructor an expression, too!
//      gov.nasa.jpl.ae.event.Expression< ? > methodExpr =
//          exprXlator.javaToAeExpression( methodJava, null, false );

//      gov.nasa.jpl.ae.event.Expression< ? > argumentArrayExpr =
//          exprXlator.javaToAeExpression( getArgumentArrayJava(), "Object[]", false );

      if ( getMethodCallExpr() == null ) {
        if ( getMatchingConstructor() == null ) {
          Debug.error( true, "Cannot create constructor! " + this );
        } else {
            setCall( new ConstructorCall( getObjectExpr(),
                                          getMatchingConstructor(),
                                          //(Constructor< ? >)methodExpr.evaluate( true ),
                                          //(Object[])argumentArrayExpr.evaluate( true ) );
                                          getArgs(), this.returnType ) );
        }
      } else {
        if ( getMatchingMethod() == null ) {
          Call scall = searchForCall( getCallName(), getArgs() );
          if ( scall == null ) {
            Debug.error( true, "Cannot create method! " + this );
          } else {
            setCall( scall );
          }
        } else if ( isEffectFunction() ) {
          setCall( new EffectFunction( getObjectExpr(),
                                       getMatchingMethod(),
                                       //(Method)methodExpr.evaluate( true ),
                                       //(Object[])argumentArrayExpr.evaluate( true ) );
                                       getArgs(),
                                       this.returnType ) );
        } else {
          setCall( new FunctionCall( getObjectExpr(),
                                     getMatchingMethod(),
                                     //(Method)methodExpr.evaluate( true ),
                                     //(Object[])argumentArrayExpr.evaluate( true ) );
                                     getArgs(),
                                     this.returnType ) );
        }
      }
    }
    return call;
  }

  public static Class<?> getType( Object arg ) {
    if ( arg == null ) return null;
    if (arg instanceof gov.nasa.jpl.ae.event.Expression) {
      return ((gov.nasa.jpl.ae.event.Expression<?>)arg).getType();
    } else {
      if ( arg instanceof Wraps ) {
        return ((Wraps<?>)arg).getType();
      }
    }
    return arg.getClass();
  }
  
  /**
   * Return a Call object based on the passed operation and arguments
   * 
   * @param operationName The name of operation used to search for the
   *                      equivalent java call
   * @param arguments The arguments for operation
   * @return Call object or null if the operationName is not a java call
   */
  public Call searchForCall(String operationName, Vector< Object > arguments) {
    
    Call call = null;
    Method method = null;
    Object object = null;

    /*
     * We will look for the corresponding Constructor or FunctionCall in
     * the following order:
     * 
     * 1. The current model class, ie EmsSystemModel (assume its a FunctionCall)
     * 2. The view_repo.syml package (assume its a ConstructorCall or FunctionCall)
     * 3. The Functions.java and ae.event package (assume its a ConstructorCall)
     * 4. Common Java classes (assume its a FunctionCall)
     * 5. The mbee.util package (assume its a FunctionCall) 
     * 
     */
    if ( operationName == null ) return null;
                
      ArrayList<Class<?>> argTypes = new ArrayList<Class<?>>();
      
      // Finding out the argument types:
      for (Object arg : arguments) {
        Class<?> type = getType( arg );
        if ( type == null ) {
          Debug.error( "Expecting an Expression for the argument: " + arg );
        }
        argTypes.add( type );
      }
      
//      // 1. Search API
//      method = ClassUtils.getMethodForArgTypes( model.getClass(),
//                                                operationName.toString(),
//                                                argTypes.toArray(new Class[]{}));
//      
//      if ( method != null ) {
//        object = model;
//      }
//      else {
        // 2.
        call = JavaToConstraintExpression.javaCallToEventFunction(operationName,
                                                                  null,
                                                                  arguments,
                                                                  argTypes.toArray(new Class[]{}));

        if (call == null) {
          //3.
          if ( arguments.size() == 1 ) {
              call = JavaToConstraintExpression.unaryOpNameToEventFunction( operationName.toString(), null, false );
          } 
          else if ( arguments.size() == 2 ) {
              call = JavaToConstraintExpression.binaryOpNameToEventFunction( operationName.toString(), null );
          } 
          else if ( arguments.size() == 3
                      && operationName.toString().equalsIgnoreCase( "if" ) ) {
              call = JavaToConstraintExpression.getIfThenElseConstructorCall(null);
          }
          else if ( arguments.size() > 3
              && operationName.toString().toLowerCase().startsWith( "argm" ) ) {
            call = JavaToConstraintExpression.getArgMinMaxConstructorCall( operationName.toString().toLowerCase(),
                                                                           arguments.size(),
                                                                           null );
          }
          
          if ( call != null ) {
            call.setArguments( arguments );
          }
          else {
            // 4.
            method = ClassUtils.getJavaMethodForCommonFunction( operationName.toString(),
                                                                arguments.toArray() );
          
            // 5.
            if (method == null) {
              // TODO implement checking the mbee.util package
            }
          
          }
          
        } // Ends call == null

//      }
      
      if ( call == null && method == null ) {
        // TODO -- if it *still* fails, maybe search through all classes of all
        // packages for a method with this name.
      }
      
      // Make the FunctionCall if it was not a ConstructorCall:
      if ( method != null ) {
        call = new FunctionCall( object, method, arguments, null );
      }
    
    return call;
  }

  
  /**
   * @param call the call to set
   */
  public void setCall( Call call ) {
    this.call = call;
  }

//  /**
//   * @return the generatedDependencies
//   */
//  public ArrayList< FieldDeclaration > getGeneratedDependencies() {
//    return generatedDependencies;
//  }
//
//  /**
//   * @param generatedDependencies the generatedDependencies to set
//   */
//  public
//      void
//      setGeneratedDependencies( ArrayList< FieldDeclaration > generatedDependencies ) {
//    this.generatedDependencies = generatedDependencies;
//  }

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
//public Date epoch = new Date();