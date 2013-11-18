package gov.nasa.jpl.ae.util;

import gov.nasa.jpl.ae.event.Affectable;
import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.ConstructorCall;
import gov.nasa.jpl.ae.event.EffectFunction;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.TimeVarying; // don't remove!!
import gov.nasa.jpl.ae.event.TimeVaryingMap; // don't remove!!
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.ObjectCreationExpr;

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

public class JavaForFunctionCall {
  /**
   * 
   */
  //private final EventXmlToJava xmlToJava;
  private final JavaToConstraintExpression exprXlator;//.expressionTranslator;
  public MethodCallExpr methodCallExpr = null;
  public ObjectCreationExpr objectCreationExpr = null;
  public boolean methodOrConstructor = true; 
  public String object = null;
  public gov.nasa.jpl.ae.event.Expression< ? > objectExpr = null;
  public String objectTypeName;
  public Class<?> objectType;
  public String className = null;
  public String callName = null;
  public Constructor< ? > matchingConstructor = null;
  public ConstructorDeclaration constructorDecl = null;
  public Method matchingMethod = null;
  public MethodDeclaration methodDecl = null;
  public String methodJava = null;  // Java text for getting java.reflect.Method
  public boolean isEffectFunction = false;
  public String argumentArrayJava = null;
  public Vector<Object> args = new Vector<Object>();
  private boolean convertingArgumentsToExpressions = false;
  public boolean evaluateCall = false;
  
  protected Call call = null;
  
  /**
   * When expressions are passed to functions that are expecting parameters, a
   * dependency can be formed.
   */
  public ArrayList<FieldDeclaration> generatedDependencies =
      new ArrayList< FieldDeclaration >();
  
  public JavaForFunctionCall( JavaToConstraintExpression expressionTranslator,
                              Expression expression,
                              boolean convertArgumentsToExpressions,
                              String preferredPackageName ) {
    this( expressionTranslator, expression, convertArgumentsToExpressions,
          preferredPackageName, false );
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
                                boolean evaluateCall ) {

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

    assert expression != null;
    
    if ( expression instanceof MethodCallExpr ) {
      methodCallExpr = (MethodCallExpr)expression;
      methodOrConstructor = true;
    } else if ( expression instanceof ObjectCreationExpr ) {
      objectCreationExpr = (ObjectCreationExpr)expression;
      methodOrConstructor = false;
    } else {
      assert false;
    }
    
    this.exprXlator = exprTranslator;
    //this.xmlToJava = eventXml2Java;
    // REVIEW -- How do we know when we want to convert args to Expressions?
    // Constructors of events (and probably classes) convert args to
    // Expressions.  For now, do not convert args for any other calls.
    this.convertingArgumentsToExpressions = convertArgumentsToExpressions;
    this.evaluateCall = evaluateCall;
    callName =
        methodOrConstructor ? methodCallExpr.getName()
                            : objectCreationExpr.getType().toString();
    className = this.exprXlator.getCurrentClass();
    
    // Get object from scope
    Expression scope = getScope();
    object = ( (JavaToConstraintExpression)this.exprXlator ).getObjectFromScope( scope );
    if ( scope != null && scope.toString().contains("getValue") ) {
      Debug.out("");
    }
    objectTypeName = this.exprXlator.astToAeExprType( scope, true, true );
    if ( objectTypeName != null ) {
      className = objectTypeName;
    }
    
    if ( Utils.isNullOrEmpty( object ) ) {
      if ( methodOrConstructor ||
          exprXlator.getClassData().isInnerClass( objectTypeName ) ) {
        object = "this";
      } else {
        object = "null";
      }
    }

    if ( !Utils.isNullOrEmpty( className ) &&
         className != exprXlator.getCurrentClass() ) {
      objectType = ClassUtils.getClassForName( className, preferredPackageName, true );
    }

    // Assemble Java text for finding the java.reflect.Method for callName
    // TODO -- move most of this to ClassData, instantiate this.call, and
    // use it to generate methodJava text.

    StringBuffer methodJavaSb = new StringBuffer();
    Class< ? >[] argTypesArr = null;
    List<Expression> argExprs = null;
    if ( methodOrConstructor ) {
      argExprs = methodCallExpr.getArgs();
    } else {
      argExprs = objectCreationExpr.getArgs();      
    }
    if ( argExprs != null ) {
      argTypesArr = new Class< ? >[ argExprs.size() ];
      for ( int i = 0; i < argExprs.size(); ++i ) {
        argTypesArr[ i ] =
            ClassUtils.getClassForName( exprXlator.astToAeExprType( argExprs.get( i ),
                                                              true, true ),
                                                              preferredPackageName,
                                                              false );
        Object arg = exprXlator.astToAeExpression( argExprs.get( i ),
                                                   ClassUtils.toString( argTypesArr[ i ] ),
                                                   null,
                                                   isConvertArgumentsToExpressions(),
                                                   true, true, evaluateCall );
        if ( isConvertArgumentsToExpressions() &&
             !( arg instanceof gov.nasa.jpl.ae.event.Expression ) ) {
          arg = new gov.nasa.jpl.ae.event.Expression( arg );
        }
        this.args.add( arg );
      }
    }
    if ( methodOrConstructor ) {
      String classNameString;
      if ( Utils.isNullOrEmpty( className ) ) {
        classNameString = "null";
      } else {
        classNameString = "\"" + className + "\"";
      }
      String preferredPackageNameString;
      if ( Utils.isNullOrEmpty( preferredPackageName ) ) {
        preferredPackageNameString = "null";
      } else {
        preferredPackageNameString = "\"" + preferredPackageName + "\"";
      }
      Assert.assertFalse( Utils.isNullOrEmpty( callName ) );
      methodJavaSb.append( "ClassUtils.getMethodForArgTypes(" + classNameString
                           + ", " + preferredPackageNameString + ", \""
                           + callName + "\"" );

      // Try using reflection to find the method, but class may not exist.
      matchingMethod  = null;
      matchingMethod =
          ClassUtils.getMethodForArgTypes( className, preferredPackageName,
                                           callName, argTypesArr, false );

      // Get the list of methods with the same name (callName).
      Set< MethodDeclaration > classMethods =
          this.exprXlator.getClassData().getClassMethodsWithName( callName, className );
      // Find the right MethodDeclaration if it exists.
      if ( !Utils.isNullOrEmpty( classMethods ) ) {

        methodDecl  = null;
        methodDecl =
            getBestArgTypes( classMethods, argTypesArr, preferredPackageName );
        if ( methodDecl == null ) {
          // Warning just grabs the first method of this name!
          if ( classMethods.size() > 1 ) {
            System.err.println( "Warning! " + classMethods.size()
                                + " methods with name " + callName + " in "
                                + className + ": just grabbing the first!" );
          }
          // Add vector of argument types to getMethod() call
          methodDecl = classMethods.iterator().next();
        }
        assert ( methodDecl != null );
        for ( japa.parser.ast.body.Parameter parameter : methodDecl.getParameters() ) {
          methodJavaSb.append( ", " );
          methodJavaSb.append( ClassUtils.noParameterName( parameter.getType().toString() )
                               + ".class" );
        }

      } else { // if ( !classMethods.isEmpty() ) {
        if ( matchingMethod != null && matchingMethod.getParameterTypes() != null ) {
          for ( Class< ? > type : matchingMethod.getParameterTypes() ) {
            methodJavaSb.append( ", " + ClassUtils.toString( type ) );
//            methodJavaSb.append( ", " );
//            String typeName = type.getName();
//            if ( typeName != null ) typeName = typeName.replace( '$', '.' );
//            methodJavaSb.append( ClassUtils.noParameterName( typeName )
//                                 + ".class" );
          }
        }
      }
    } else {
      methodJavaSb.append( "ClassUtils.getConstructorForArgTypes(" 
                           + ClassUtils.noParameterName( callName )
                           + ".class" );
      // Find the right ConstructorDeclaration if it exists.
      Set< ConstructorDeclaration > ctors =
          ( exprXlator == null
            ? null 
            : ( exprXlator.getClassData() == null
                ? null 
                : exprXlator.getClassData().getConstructors( callName ) ) );
      constructorDecl  = null;
      if ( !Utils.isNullOrEmpty( ctors ) ) {
        constructorDecl =
            getBestArgTypes( ctors, argTypesArr, preferredPackageName );
        if ( constructorDecl == null ) {
          constructorDecl = ctors.iterator().next();
          // Warning just grabs the first constructor!
          if ( ctors.size() > 1 ) {
            System.err.println( "Warning! " + ctors.size()
                                + " constructors for " + callName
                                + ": just grabbing the first!" );
          }
        }
        assert ( constructorDecl != null );
        if ( constructorDecl != null && constructorDecl.getParameters() != null ) {
          for ( japa.parser.ast.body.Parameter parameter : 
            constructorDecl.getParameters() ) {
            methodJavaSb.append( ", " );
            methodJavaSb.append( ClassUtils.noParameterName( parameter.getType().toString() )
                                 + ".class" );
          }
        }
      }
      if ( constructorDecl == null ) {
        // Try using reflection to find the method, but class may not exist.
        matchingConstructor =
            ClassUtils.getConstructorForArgTypes( callName, argTypesArr,
                                                  preferredPackageName );
        if ( matchingConstructor != null ) {
          for ( Class< ? > type : matchingConstructor.getParameterTypes() ) {
            methodJavaSb.append( ", " + ClassUtils.toString( type ) );
//            if ( type.isArray() ) 
//            String typeName = type.getName();
//            if ( typeName != null ) typeName = typeName.replace( '$', '.' );
//            methodJavaSb.append( ClassUtils.noParameterName( typeName ) 
//                                 + ".class" );
          }
        }
      }
    }
    methodJavaSb.append( " )" );
    methodJava = methodJavaSb.toString();
    
    // Determine whether the function is regular or actually an effect.
    // For example, TimeVaryingMap.setValue(...) is an effect function, but
    // TimeVaryingMap.getValue(...) is not.
    // TODO -- REVIEW -- type is never used! -- is it supposed to be used in assigning isEffectFunction???
    Class<?> type = null;
    if ( matchingMethod != null ) {
      type = matchingMethod.getDeclaringClass();
    } else if ( objectType != null ) {
      type = objectType;
    }
    // Assume it's an effect if the object it's called from is Affectable, and
    // try to prove that the function is not one of the effect functions for
    // that class.
    isEffectFunction = objectType != null && Affectable.class.isAssignableFrom( objectType );
    // HACK -- not going to try and prove it isn't.
//    if ( isEffectFunction && type != null ) {
//      Class<?>[] types = new Class<?>[]{ TimeVaryingMap, TimeVaryingList, ObjectFlow, Consumable, 
//      isEffectFunction =  
//    }


    // Build Java text to construct an array enclosing the arguments to be
    // passed to the method call.
    StringBuffer argumentArraySb = new StringBuffer();
    argumentArraySb.append( "new Object[]{ " );
    boolean first = true;
    if ( argExprs != null ) {
      for ( Expression a : argExprs ) {
        if ( first ) {
          first = false;
        } else {
          argumentArraySb.append( ", " );
        }
        if ( convertArgumentsToExpressions ) {
          String e = 
              exprXlator.astToAeExpr( a, convertArgumentsToExpressions, true, true );
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
    argumentArrayJava = argumentArraySb.toString();
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
    if ( methodCallExpr != null ) {
      return methodCallExpr.getScope();
    } else if ( objectCreationExpr != null ) {
      return objectCreationExpr.getScope();
    }
    return null;
  }

  /**
   * @return the convertArgumentsToExpressions
   */
  public boolean isConvertArgumentsToExpressions() {
    return convertingArgumentsToExpressions;
  }

  public boolean isStatic() {
    if ( methodOrConstructor ) {
      if ( exprXlator.getClassData().knowIfStatic( callName ) ) {
        return exprXlator.getClassData().isStatic( callName );
      }
      if ( matchingMethod != null &&
           Modifier.isStatic( matchingMethod.getModifiers() ) ) {
        return true;
      }
      if ( methodDecl != null &&
           ModifierSet.isStatic( methodDecl.getModifiers() ) ) {
        return true;
      }
    } else {
      if ( exprXlator.getClassData().knowIfClassIsStatic( callName ) ) {
        return exprXlator.getClassData().isClassStatic( callName );
      }
      if ( matchingConstructor != null &&
           Modifier.isStatic( matchingConstructor.getModifiers() ) ) {
        return true;
      }
      if ( constructorDecl != null &&
           ModifierSet.isStatic( constructorDecl.getModifiers() ) ) {
        return true;
      }
    }
    return Utils.isNullOrEmpty( object );
  }
  
  /**
   * @param convertArgumentsToExpressions the convertArgumentsToExpressions to set
   */
  public void
      setConvertArgumentsToExpressions( boolean convertArgumentsToExpressions ) {
    this.convertingArgumentsToExpressions = convertArgumentsToExpressions;
  }
  
  public Call toNewFunctionCall() {
    call = null;
    objectExpr = exprXlator.javaToAeExpression( object, objectTypeName, false );
    // This call causes infinite recursion!
    // The problem is that we may be trying to create a method that does not yet exist!
    // In Call, object and args are expressions; make the method/constructor an expression, too!
//    gov.nasa.jpl.ae.event.Expression< ? > methodExpr =
//        exprXlator.javaToAeExpression( methodJava, null, false );
    gov.nasa.jpl.ae.event.Expression< ? > argumentArrayExpr =
        exprXlator.javaToAeExpression( argumentArrayJava, "Object[]", false );
    if ( methodCallExpr == null ) {
      if ( matchingConstructor == null ) {
        Debug.error( true, "Cannot create constructor! " + this );
      } else {
          call = new ConstructorCall( objectExpr,
                                      matchingConstructor,
                                      //(Constructor< ? >)methodExpr.evaluate( true ),
                                      //(Object[])argumentArrayExpr.evaluate( true ) );
                                      args );
      }
    } else {
      if ( matchingMethod == null ) {
        Debug.error( true, "Cannot create method! " + this );
      } else if ( isEffectFunction ) {
        call = new EffectFunction( objectExpr,
                                   matchingMethod,
                                   //(Method)methodExpr.evaluate( true ),
                                   //(Object[])argumentArrayExpr.evaluate( true ) );
                                   args );
      } else {
        call = new FunctionCall( objectExpr,
                                 matchingMethod,
                                 //(Method)methodExpr.evaluate( true ),
                                 //(Object[])argumentArrayExpr.evaluate( true ) );
                                 args );
      }
    }
    return call;
  }

  public String toNewFunctionCallString() {
    String fcnCallStr = null;
    String callTypeName = 
        ( methodCallExpr == null ? "ConstructorCall"
                                 : ( isEffectFunction ? "EffectFunction"
                                                      : "FunctionCall" ) ); 
    if ( object.startsWith( "new FunctionCall" ) 
         || object.startsWith( "new ConstructorCall" )
      || object.startsWith( "new EffectFunction" ) ) {
      // nest the function calls
      fcnCallStr = "new " + callTypeName + "( null, " + methodJava + ", "
                   + argumentArrayJava + ", " + object + " )";
    } else {
      String instance = object;
      if ( isStatic() ) {
        instance = "null";
      }
      fcnCallStr = "new " + callTypeName + "( " + instance + ", " + methodJava
                   + ", " + argumentArrayJava + " )";
    }
    if ( evaluateCall && !Utils.isNullOrEmpty( fcnCallStr ) ) {
      if ( !convertingArgumentsToExpressions ) {
        fcnCallStr = "(" + fcnCallStr + ").evaluate(true)";
      }
    }
    return fcnCallStr;
  }
  public String toNewExpressionString() {
    return "new Expression( " + toNewFunctionCallString() + " )";
  }
  
  @Override
  public String toString() {
    return toNewFunctionCallString();
  }

  public <T> gov.nasa.jpl.ae.event.Expression< T > toNewExpression() {
    return new gov.nasa.jpl.ae.event.Expression< T >( toNewFunctionCall() );
  }
  
}
//public Date epoch = new Date();