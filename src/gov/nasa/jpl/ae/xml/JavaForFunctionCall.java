package gov.nasa.jpl.ae.xml;

import gov.nasa.jpl.ae.util.Utils;
import gov.nasa.jpl.ae.xml.EventXmlToJava.Param;
import gov.nasa.jpl.ae.event.TimeVarying; // don't remove!!
import gov.nasa.jpl.ae.event.TimeVaryingMap; // don't remove!!
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class JavaForFunctionCall {
  /**
   * 
   */
  private final EventXmlToJava xmlToJava;
  public String objectName;
  public String className;
  public String callName;
  public String pkg;
  public String methodJava;  // Java text for getting java.reflect.Method
  public String argumentArrayJava;
  public Vector<String> args;
  private boolean convertingArgumentsToExpressions;
  /**
   * When expressions are passed to functions that are expecting parameters, a
   * dependency can be formed.
   */
  public ArrayList<FieldDeclaration> generatedDependencies =
      new ArrayList< FieldDeclaration >();
  
  public JavaForFunctionCall( EventXmlToJava eventXmlToJava,
                              MethodCallExpr mce,
                              boolean convertArgumentsToExpressions ) {
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

    this.xmlToJava = eventXmlToJava;
    // REVIEW -- How do we know when we want to convert args to Expressions?
    // Constructors of events (and probably classes) convert args to
    // Expressions.  For now, do not convert args for any other calls.
    this.convertingArgumentsToExpressions = convertArgumentsToExpressions;
    callName = mce.getName();
    className = this.xmlToJava.currentClass;
    
    // Get object from scope
    Expression scope = mce.getScope();
    objectName = this.xmlToJava.getObjectFromScope( scope );
    pkg = this.xmlToJava.packageName + ".";
    if ( pkg.length() == 1 ) {
      pkg = "";
    }
    
    if ( Utils.isNullOrEmpty( objectName ) ) {
      objectName = "this";
    } else {
      // Get the class name from the declaration of the object.
      Param objectParam =
          this.xmlToJava.lookupMemberByName( className, objectName );
      if ( objectParam != null ) {
        className = objectParam.type;
      } else {
        // TODO -- In what other scope is this defined? See todo in
        // lookupMemberByName.
      }
    }
    
    if ( className != null && !className.isEmpty() ) {
      Class<?> classForName = Utils.getClassForName( className, true );
      if ( classForName == null && !pkg.isEmpty() ) {
        classForName = Utils.getClassForName( pkg + className, true );
      }
      if ( classForName != null ) {
        className = classForName.getName();
      }
    }
    
    // Assemble Java text for finding the java.reflect.Method for callName
    // uses Class<?>.getMethod( String callName, arg1Class, arg2Class, ...) 
    StringBuffer methodJavaSb = new StringBuffer();
//    methodJavaSb.append( "Utils.getMethodForArgs(\"" + className
//                         + "\", " + toString(mce.getArgs().toArray()) );
//    methodJavaSb.append( "Utils.getClassForSimpleName(\"" + className
//                       + "\", true).getMethod(\"" + callName + "\"" );
    methodJavaSb.append( "Utils.getMethodForArgTypes(\"" + className + "\", "
                         + "\"" + callName + "\"" );
//    methodJavaSb.append( "Class.forName(\"" + className
//                         + "\").getMethod(\"" + callName + "\"" );
    // Get the list of methods with the same name (callName).
    Set< MethodDeclaration > classMethods =
        this.xmlToJava.getClassMethodsWithName( callName, className );
    // Find the right MethodDeclaration if it exists.
    if ( Utils.isNullOrEmpty( classMethods ) ) {
//      try {
//        Class<?> classForName = Utils.getClassForSimpleName( className );
//        if ( classForName != null ) {
//        Method[] methods = classForName.getMethods();
        Method matchingMethod = null;
//        for ( Method m : methods ) {
//          if ( m.getName().equals( callName ) ) {
//            if ( matchingMethod == null ||
//                 m.getParameterTypes().length == mce.getArgs().size() ) {
//              matchingMethod = m;
//              if ( m.getParameterTypes().length == mce.getArgs().size() ) break;
//            }
//          }
//        }
      // Try using reflection to find the method, but class may not exist.
      Class< ? >[] argArr = null;
      if ( mce != null && mce.getArgs() != null ) {
        argArr = new Class< ? >[ mce.getArgs().size() ];
        for ( int i = 0; i < mce.getArgs().size(); ++i ) {
          argArr[ i ] =
              Utils.getClassForName( xmlToJava.astToAeExprType( mce.getArgs()
                                                                   .get( i ) ),
                                     false );
        }
        // argArr = mce.getArgs().toArray();
        // mce.getArgs().get( 0 ).
      }
      matchingMethod =
          Utils.getMethodForArgTypes( className, callName, argArr );
        if ( matchingMethod != null ) {
          for ( Class< ? > type : matchingMethod.getParameterTypes() ) {
            methodJavaSb.append( ", " );
            methodJavaSb.append( type.getName() + ".class" ); 
          }
        }
//        }
//        //Set< MethodDeclaration > methodSet = new HashSet< MethodDeclaration >();
//        // FIXME -- HERE!!! -- TODO
//        //methodSet.addAll(new ArrayList());
//      } catch ( Exception e ) {
//        System.err.println( "class not found: " + e.getLocalizedMessage() );//e.printStackTrace();
//      }
    } else { // if ( !classMethods.isEmpty() ) {
      // Warning just grabs the first method of this name!
      if ( classMethods.size() > 1 ) {
        System.err.println( "Warning! " + classMethods.size()
                            + " methods with name " + callName + " in "
                            + className + ": just grabbing the first!" );
      }
      // Add vector of argument types to getMethod() call
      MethodDeclaration methodDecl = classMethods.iterator().next();
      assert( methodDecl != null );
      for ( japa.parser.ast.body.Parameter parameter :
            methodDecl.getParameters() ) {
          methodJavaSb.append( ", " );
        methodJavaSb.append( parameter.getType().toString() + ".class" ); 
      }
    }
    methodJavaSb.append( " )" );
    methodJava = methodJavaSb.toString();
    
    // Build Java text to construct an array enclosing the arguments to be
    // passed to the method call.
    StringBuffer argumentArraySb = new StringBuffer();
    argumentArraySb.append( "new Object[]{ " );
    boolean first = true;
    if ( mce.getArgs() != null ) {
      for ( Expression a : mce.getArgs() ) {
        if ( first ) {
          first = false;
        } else {
          argumentArraySb.append( ", " );
        }
        if ( convertArgumentsToExpressions ) {
          String e = 
              xmlToJava.astToAeExpr( a, convertArgumentsToExpressions );
          argumentArraySb.append( e );
        } else {
          argumentArraySb.append( a );
        }
      }
    }
    argumentArraySb.append( " } " );
    argumentArrayJava = argumentArraySb.toString();
  }

  /**
   * @return the convertArgumentsToExpressions
   */
  public boolean isConvertArgumentsToExpressions() {
    return convertingArgumentsToExpressions;
  }

  /**
   * @param convertArgumentsToExpressions the convertArgumentsToExpressions to set
   */
  public void
      setConvertArgumentsToExpressions( boolean convertArgumentsToExpressions ) {
    this.convertingArgumentsToExpressions = convertArgumentsToExpressions;
  }
  
  public String toNewFunctionCallString() {
    return "new FunctionCall( (Object)" + objectName + ", " + methodJava
           + ", " + argumentArrayJava + " )";
  }
  public String toNewExpressionString() {
    return "new Expression( " + toNewFunctionCallString() + " )";
  }
  
  @Override
  public String toString() {
    return toNewFunctionCallString();
  }
  
}
//public Date epoch = new Date();