package gov.nasa.jpl.ae.xml;

import gov.nasa.jpl.ae.xml.EventXmlToJava.Param;
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
  private final EventXmlToJava eventXmlToJava;
  public String objectName;
  public String className;
  public String callName;
  public String pkg;
  public String methodJava;  // Java text for getting java.reflect.Method
  public String argumentArrayJava;
  public Vector<String> args;
  private boolean convertingArgumentsToExpressions;
  
  public JavaForFunctionCall( EventXmlToJava eventXmlToJava, MethodCallExpr mce,
                              boolean convertArgumentsToExpressions ) {
    this.eventXmlToJava = eventXmlToJava;
    // REVIEW -- How do we know when we want to convert args to Expressions?
    // Constructors of events (and probably classes) convert args to
    // Expressions.  For now, do not convert args for any other calls.
    this.convertingArgumentsToExpressions = convertArgumentsToExpressions;
    callName = mce.getName();
    className = this.eventXmlToJava.currentClass;
    
    // Get object from scope
    Expression scope = mce.getScope();
    objectName = this.eventXmlToJava.getObjectFromScope( scope );
    pkg = this.eventXmlToJava.packageName + ".";
    if ( pkg.length() == 1 ) {
      pkg = "";
    }
    
    // Get the class name from the declaration of the object.
    Param objectParam = this.eventXmlToJava.lookupMemberByName( className, objectName );
    if ( objectParam != null ) {
      className = objectParam.type;
    } else {
      // TODO -- In what other scope is this defined? See todo in
      // lookupMemberByName.
    }
    
    if ( className != null && !className.isEmpty() ) {
      Class<?> classForName = getClassForSimpleName( className );
      if ( classForName == null && !pkg.isEmpty() ) {
        classForName = getClassForSimpleName( pkg + className );
      }
      if ( classForName != null ) {
        className = classForName.getName();
      }
    }
    
    // Assemble Java text for finding the java.reflect.Method for callName
    // uses Class<?>.getMethod( String callName, arg1Class, arg2Class, ...) 
    StringBuffer methodJavaSb = new StringBuffer();
    methodJavaSb.append( "Class.forName(\"" + className
                       + "\").getMethod(\"" + callName + "\"" );
    // Get the list of methods with the same name (callName).
    Set< MethodDeclaration > classMethods =
        this.eventXmlToJava.getClassMethodsWithName( callName, className );
    // Find the right MethodDeclaration if it exists.
    if ( classMethods.isEmpty() ) {
      try {
        Class<?> classForName = getClassForSimpleName( className );
        if ( classForName != null ) {
        Method[] methods = classForName.getMethods();
        Method matchingMethod = null;
        for ( Method m : methods ) {
          if ( m.getName().equals( callName ) ) {
            if ( matchingMethod == null ||
                 m.getParameterTypes().length == mce.getArgs().size() ) {
              matchingMethod = m;
              if ( m.getParameterTypes().length == mce.getArgs().size() ) break;
            }
          }
        }
        if ( matchingMethod != null ) {
          for ( Class< ? > type : matchingMethod.getParameterTypes() ) {
            methodJavaSb.append( ", " );
            methodJavaSb.append( type.getName() + ".class" ); 
          }
        }
        }
        //Set< MethodDeclaration > methodSet = new HashSet< MethodDeclaration >();
        // FIXME -- HERE!!! -- TODO
        //methodSet.addAll(new ArrayList());
      } catch ( Exception e ) {
        System.err.println( "class not found: " + e.getLocalizedMessage() );//e.printStackTrace();
      }
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
    for ( Expression a : mce.getArgs() ) {
      if ( first ) {
        first = false;
      } else {
        argumentArraySb.append( ", " );
      }
      if ( convertArgumentsToExpressions ) {
        argumentArraySb.append( this.eventXmlToJava.javaparserToAeExpression( a, convertArgumentsToExpressions ) );
      } else {
        argumentArraySb.append( a );
      }
    }
    argumentArraySb.append( " } " );
    argumentArrayJava = argumentArraySb.toString();
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
    List<String> FQNs = getFQNs( className );
    if ( FQNs.isEmpty() && pos >= 0 ) {
      FQNs = getFQNs( strippedClassName );
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
  public static List<String> getFQNs(String simpleName) {
    Collection<String> packages = null;
    packages = getPackages();

    List<String> fqns = new ArrayList<String>();
    for (String aPackage : packages) {
        try {
            String fqn = aPackage + "." + simpleName;
            Class.forName(fqn);
            fqns.add(fqn);
        } catch (Exception e) {
            // Ignore
        }
    }
    return fqns;
}  /**
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