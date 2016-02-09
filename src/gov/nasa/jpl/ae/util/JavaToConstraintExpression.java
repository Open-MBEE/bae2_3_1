/**
 * 
 */
package gov.nasa.jpl.ae.util;

import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.ConstructorCall;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Functions;
import gov.nasa.jpl.ae.event.Functions.Binary;
import gov.nasa.jpl.ae.event.Functions.Unary;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.ParameterListenerImpl;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.NameTranslator;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;
import japa.parser.ASTParser;
import japa.parser.ParseException;
import japa.parser.ast.expr.ArrayCreationExpr;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.CharLiteralExpr;
import japa.parser.ast.expr.ClassExpr;
import japa.parser.ast.expr.ConditionalExpr;
import japa.parser.ast.expr.DoubleLiteralExpr;
import japa.parser.ast.expr.EnclosedExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.LiteralExpr;
import japa.parser.ast.expr.LongLiteralExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import japa.parser.ast.expr.ThisExpr;
import japa.parser.ast.expr.UnaryExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.AssertStmt;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.CatchClause;
import japa.parser.ast.stmt.DoStmt;
import japa.parser.ast.stmt.ExplicitConstructorInvocationStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.ForeachStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.LabeledStmt;
import japa.parser.ast.stmt.ReturnStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.SwitchEntryStmt;
import japa.parser.ast.stmt.SwitchStmt;
import japa.parser.ast.stmt.SynchronizedStmt;
import japa.parser.ast.stmt.ThrowStmt;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.stmt.TypeDeclarationStmt;
import japa.parser.ast.stmt.WhileStmt;

import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import junit.framework.Assert;

/**
 * This class is a collection of utilities for taking Java expressions and
 * creating Parameters and Constraints for problem solving.
 */
public class JavaToConstraintExpression { // REVIEW -- Maybe inherit from ClassData?

//  // Map: longName -> parameter name -> Param
//  protected Map< String, Map< String, Param > > paramTable =
//      new TreeMap< String, Map< String, Param > >();
//
//  // Map: longName -> method name -> set of javaparser.MethodDeclarations
//  protected Map< String, Map< String, Set< MethodDeclaration > > > methodTable =
//      new TreeMap< String, Map< String, Set< MethodDeclaration > > >();
//
//  protected Map< String, String > nestedToEnclosingClassNames =
//      new TreeMap< String, String >();
////  protected String currentEnclosingClassName;
////  protected String currentScopedClassName;
//  protected Map< String, Boolean > isStaticMap = new TreeMap< String, Boolean >();
//  
//  /**
//   * The package for all of the classes.
//   */
//  protected String packageName = null;

  // This is for handling class names outside Java syntax.
  protected NameTranslator nameTranslator = new NameTranslator();

  //private String currentClass = null;

  private ClassData classData = new ClassData();

  //protected EventXmlToJava xmlToJava = null;
  
  public JavaToConstraintExpression( String packageName ) {
    // TODO -- shouldn't need this, but there is a remaining complication with
    // EventXmlToJava.getConstructors().
    //xmlToJava = eventXmlToJava;
    getClassData().setPackageName( packageName );
  }

  public static String operatorResultType( BinaryExpr.Operator o, String argType1,
                                     String argType2 ) {
    switch ( o ) {
      case or: // ||
      case and: // &&
      case equals: // ==
      case notEquals: // !=
      case less: // <
      case greater: // >
      case lessEquals: // <=
      case greaterEquals: // >=
        return "Boolean";
      case binOr: // |
      case binAnd: // &
      case xor: // ^
      case lShift: // <<
      case rSignedShift: // >>
      case rUnsignedShift: // >>>
      case plus: // +
      case minus: // -
      case times: // *
      case divide: // /
      case remainder: // %
      default:
        return ClassUtils.dominantType( argType1, argType2 );
    }
  }

  public static String
      operatorResultType( UnaryExpr.Operator operator, String argType ) {
    return argType;
  }

  public static BinaryExpr.Operator assignOpToBinaryOp( AssignExpr.Operator ao ) {
    // REVIEW -- pull this out and make it a static array;
    BinaryExpr.Operator bo = null;
    switch ( ao ) {
      case assign:
        System.err.println( "Error! Trying to translate an assignment with the '=' operator to a binary operator." );
        break; // leave null
      case plus:
        bo = BinaryExpr.Operator.plus;
        break;
      case minus:
        bo = BinaryExpr.Operator.minus;
        break;
      case star:
        bo = BinaryExpr.Operator.times;
        break;
      case slash:
        bo = BinaryExpr.Operator.divide;
        break;
      case and:
        bo = BinaryExpr.Operator.and;
        break;
      case or:
        bo = BinaryExpr.Operator.or;
        break;
      case xor:
        bo = BinaryExpr.Operator.xor;
        break;
      case rem:
        bo = BinaryExpr.Operator.remainder;
        break;
      case lShift:
        bo = BinaryExpr.Operator.lShift;
        break;
      case rSignedShift:
        bo = BinaryExpr.Operator.rSignedShift;
        break;
      case rUnsignedShift:
        bo = BinaryExpr.Operator.rUnsignedShift;
        break;
      default:
        System.err.println( "Error! Trying to translate an unknow assignment operator "
                            + ao + " to a binary operator." );
    }
    return bo;
  }
  
  public static Call javaCallToEventFunction( String fName,
                                              Class<?> returnType,
                                              Vector<Object> arguments,
                                              Class<?>... argTypes) {
    
    Class< ? > cls = null;
    String[] packages = new String[]{"gov.nasa.jpl.view_repo.sysml"};
    Method method = null;
    Call call = null;
    Constructor<?> constructor = null;
    
    // Search by memberName:
    cls = ClassUtils.getClassForName(null, fName,
                                     packages,  false);
    
    if (cls != null) {
    
      method = ClassUtils.getMethodForArgTypes( cls, fName, argTypes);
      
      if (method != null) {
        call = new FunctionCall(null, method, arguments, returnType);
      }
      else {
        Debug.errln( "javaCallToEventFunction( " + fName +
                    "): no method found!" );
        return null;      
      }
    }
    // It must be a ConstructorCall:
    else {
      
      // Search by class:
      cls = ClassUtils.getClassForName(fName, null,
                                       packages,  false);
      
      if ( cls == null ) {
          Debug.errln( "javaCallToEventFunction( " + fName +
                 "): no class found!" );
          return null;
      }

      constructor = ClassUtils.getConstructorForArgTypes( cls, argTypes );
      
      if (constructor != null) {
        
        // Add a empty argument expressions if arguments size is 0, but
        // the constructor has more than one argument:
        // Could also check if they are not equal and deal with it more
        // intelligently
        int numConstructorArgs = constructor.getParameterTypes().length;
        if (arguments.size() == 0 && numConstructorArgs > 0) {
          
          Vector<Object> emptyArgs = new Vector<Object>();
          for(int i = 0; i < numConstructorArgs; ++i) {
            emptyArgs.add( emptyExpression );
          }
          
          call = new ConstructorCall( null, constructor, emptyArgs, returnType);
        }
        else {
          call = new ConstructorCall( null, constructor, arguments, returnType );
        }
      }
      else {
        Debug.errln( "javaCallToEventFunction( " + fName +
            "): no constructor found!" );
        return null; 
      }
      
    }
    
    if ( returnType != null && (call.getReturnType() == null || !call.getReturnType().equals( returnType ) ) ) {
      call.setReturnType( returnType );
    }
    
    return call;
  }    
  
  public static String
      astUnaryOpToEventFunctionName( UnaryExpr.Operator operator ) {
    return "" + Character.toUpperCase( operator.toString().charAt( 0 ) )
           + operator.toString().substring( 1 );
  }

  public static < T, R > ConstructorCall
      javaUnaryOpToEventFunction( UnaryExpr.Operator operator, Class< ? > returnType  ) {
    if ( operator == null ) return null;
    return unaryOpNameToEventFunction( operator.toString(), returnType );
  }
  public static < T, R > ConstructorCall
      unaryOpNameToEventFunction( String fName, Class< ? > returnType ) {
    
    String op2func = unaryOperatorSymbolToFunctionName( fName );
    if ( op2func != null ) {
      fName = op2func;
    } else {
      fName = Utils.toCamelCase( fName );
    }
    
    Class< ? extends Unary< T, R > > cls = (Class< ? extends Unary< T, R >>)
        getFunctionClassOfType(fName, Unary.class);

    if ( cls == null ) {
        Debug.error( "javaUnaryOpToEventFunction( " + fName +
                     "): no function found!" );
        return null;
    }
    ConstructorCall ctorCall =
        new ConstructorCall( null, cls, new Object[] { emptyExpression }, returnType );
    return ctorCall;
    
  }

    protected static List< Class< ? extends FunctionCall > > functionClasses =
            null;
    /**
     * @return a list of built-in, common FunctionCall classes. 
     */
    public static List< Class< ? extends FunctionCall > > getFunctionClasses() {
        if ( functionClasses != null ) return functionClasses;
        functionClasses = new ArrayList< Class< ? extends FunctionCall > >();
        Class< ? >[] classes = Functions.class.getClasses();
        for ( Class< ? > cls : classes ) {
            System.out.println("cls = " + cls );
            if ( cls != null && FunctionCall.class.isAssignableFrom( cls ) ) {
                @SuppressWarnings( "unchecked" )
                Class< ? extends FunctionCall > fcls =
                        (Class< ? extends FunctionCall >)cls;
                functionClasses.add( fcls );
            }
        }
        return functionClasses;
    }

    public static String binaryOperatorSymbolToFunctionName( String op ) {
      int length = op.trim().length();
      char first = length >= 1 ? op.trim().charAt( 0 ) : 0;
      char second = length >= 2 ? op.trim().charAt( 1 ) : 0;
      if ( length == 2 ) {
        switch ( first ) {
          case '&':
            if ( second == '&' ) return "And";
            return null;
          case '|':
            if ( second == '|' ) return "Or";
            return null;
          default:
            // unknown
            return null;
        }
      }
      if ( length == 1 ) {
        switch ( first ) {
          case '+':
            return "Plus";
          case '-':
            return "Minus";
          case '*':
            return "Times";
          case '/':
            return "DividedBy";
          case '&':
            return "And"; // bitwise?
          case '|':
            return "Or"; // bitwise?
          case '^':
            return "Xor"; // bitwise?
          case '%':
            return "Mod"; // TODO -- add to Functions.java
          default:
            // unknown
            return null;
        }
      }
      return null;
    }
    
    public static String unaryOperatorSymbolToFunctionName( String op ) {
      int length = op.trim().length();
      char first = length >= 1 ? op.trim().charAt( 0 ) : 0;
      char second = length >= 2 ? op.trim().charAt( 1 ) : 0;
      if ( length == 2 ) {
        switch ( first ) {
          case '+':
            if ( second == '+' ) return "Increment"; // TODO -- add to Functions.java
            return null;
          case '-':
            if ( second == '-' ) return "Decrement"; // TODO -- add to Functions.java
            return null;
          default:
            // unknown
            return null;
        }
      }
      if ( length == 1 ) {
        switch ( first ) {
          case '+':
            return "Noop"; // TODO -- add to Functions.java
          case '-':
            return "Negative";
          case '!':
            return "Not";
          default:
            // unknown
            return null;
        }
      }
      return null;
    }
    
  // \([A-Za-z]*\), //\(.*\)
  // case \1: // \2
    public static < T, R > Class< ? extends Binary< T, R > > 
      binaryOpNameToFunctionClass( String opName ) {
        Class< ? extends Binary > foo = 
                getFunctionClassOfType( opName, Binary.class );;
        return (Class< ? extends Binary< T, R > >)foo ;
    }
     
    public static < T > Class< ? extends T >
            getFunctionClassOfType( String opName, Class< T > type ) {
      
        if ( Utils.isNullOrEmpty( opName ) ) return null;
        
        String fName = opName;
        Class< ? extends T > cls = null;
        
        // Capitalize the first character of the fName:
        fName = "" + Character.toUpperCase( fName.toString().charAt( 0 ) )
                        + fName.toString().substring( 1 );
                
        try {
          
            // See if is a class in Functions.java:
            for ( Class< ? extends FunctionCall > fcls : getFunctionClasses() ) {
                if ( ( type == null || type.isAssignableFrom( fcls ) ) && 
                     fcls.getSimpleName().equalsIgnoreCase( fName ) ) {
                    @SuppressWarnings( "unchecked" )
                    Class< ? extends T > tcls = (Class< ? extends T >)fcls;
                    return tcls;
                }
            }
          
            String[] packages = new String[]{"gov.nasa.jpl.ae.event"};
            @SuppressWarnings( "unchecked" )
            Class< ? extends T > foo = (Class< ? extends T >)
                                          ClassUtils.getClassForName( fName, null, 
                                                                      packages, false );
            cls = foo;
            
            // If class was not found yet, then search some more:
            if ( cls == null) {
              
                @SuppressWarnings( "unchecked" )
                Class< ? extends T > foo2 = (Class< ? extends T >)
                                              ClassUtils.getClassForName( "Functions." + fName, 
                                                                          null,
                                                                          "gov.nasa.jpl.ae.event",
                                                                           false );
                cls = foo2;
            }
          } catch ( ClassCastException e ) {
            e.printStackTrace();
          }
        
        return cls;
    }
  
    public static String
            javaBinaryOpToEventFunctionName( BinaryExpr.Operator operator ) {
        if ( operator == null ) return null;
        Class< ? extends Binary< ?, ? > > cls = 
                binaryOpNameToFunctionClass( operator.toString() );
        return cls.getSimpleName();
    }  

  static final Class< gov.nasa.jpl.ae.event.Expression > aeExprCls =
      gov.nasa.jpl.ae.event.Expression.class;

  public static < T, R > ConstructorCall javaBinaryOpToEventFunction( BinaryExpr.Operator operator, Class<?> returnType ) {
      //String fName = javaBinaryOpToEventFunctionName( operator );
      if ( operator == null ) return null;
      return binaryOpNameToEventFunction( operator.toString(), returnType );
  }
  public static < T, R > ConstructorCall
      binaryOpNameToEventFunction( String fName, Class< ? > returnType  ) {
    Class< ? extends Functions.Binary< T, R > > cls = null;
    cls = binaryOpNameToFunctionClass( fName );
    if ( cls == null ) {
        if ( Debug.isOn() ) {
          Debug.error( "javaBinaryOpToEventFunction( " + fName +
                       "): no function found!" );
        }
        return null;
    }
    ConstructorCall ctorCall =
        new ConstructorCall( null, cls, new Object[] { emptyExpression,
                                                       emptyExpression },
                                                       returnType );
    return ctorCall;
  }

  public static gov.nasa.jpl.ae.event.Expression< Object > emptyExpression =
          new gov.nasa.jpl.ae.event.Expression< Object >( (Object)null );  
  public static < T, R > ConstructorCall getIfThenElseConstructorCall(Class<?> returnType) {
    Class< Functions.Conditional > cls = Functions.Conditional.class;
    ConstructorCall ctorCall =
        new ConstructorCall( null, cls, new Object[] { emptyExpression, 
                                                       emptyExpression,
                                                       emptyExpression },
                                                       returnType );
    return ctorCall;
  }

  /**
   * Translate the input type/class name to the corresponding non-primitive
   * Class name. The case of the letters is largely ignored, and some
   * non-standard type names are considered: for example, typeToClass("real")
   * returns "Float" even though "real" is not a Java type.
   * 
   * @param type
   * @return the name of the non-primitive Class that corresponds to the input
   *         type name.
   * 
   */
  public static String typeToClass( String type ) {
    
    if ( Utils.isNullOrEmpty( type ) ) {
      //type = "null";
    } else if ( type.toLowerCase().equals( "time" ) 
                || type.toLowerCase().startsWith( "int" )
                || type.toLowerCase().startsWith( "integer" )
                || type.toLowerCase().startsWith( "long" ) ) {
      type = "Integer";
    } else if ( type.toLowerCase().startsWith( "float" )
                || type.toLowerCase().startsWith( "real" ) ) {
      type = "Float";
    } else if ( type.toLowerCase().equals( "double" ) 
                || type.toLowerCase().startsWith( "float" )
                || type.toLowerCase().startsWith( "real" ) ) {
      type = "Double";
    } else if ( type.toLowerCase().equals( "boolean" )
                || type.toLowerCase().equals( "bool" ) ) {
      type = "Boolean";
    } else if ( type.equals( "string" ) ) {
      type = "String";
    }
    
    return type;
  }

  public static String classToPrimitive( String type ) {
    
    if ( Utils.isNullOrEmpty( type ) ) {
      return type;
    }
    int pos = type.lastIndexOf( "Parameter" );
    if ( pos >= 0 ) {
      type = type.substring( 0, pos );
    }
    Class<?> c = null;
    try {
      c = Class.forName( type );
    } catch ( ClassNotFoundException e ) {
    }
    if ( c != null ) {
      if ( c.isPrimitive() ) {
        return c.getSimpleName().replace( '$', '.' );
      }
      type = c.getSimpleName().replace( '$', '.' );
    }
    final String[] primClassesSame =
        new String[] { "Boolean", //"Character",
                       "Byte", "Short", //"Integer",
                       "Long", "Float", "Double", "Void" };
    if ( Arrays.asList( primClassesSame ).contains( type ) ) {
      return type.toLowerCase();
    }
    if ( type.toLowerCase().equals( "integer" ) 
         || type.toLowerCase().equals( "time" )
         || type.toLowerCase().equals( "timepoint" ) 
         || type.toLowerCase().equals( "duration" ) ) { 
      return "int";
    }
    if ( type.toLowerCase().equals( "real" ) ) {
      return "double";
    }
    if ( type.toLowerCase().equals( "character" ) ) {
      return "char";
    }
    return type;
  }

  public String astToAeExpr( Expression expr,
                             boolean convertFcnCallArgsToExprs,
                             boolean lookOutsideClassDataForTypes,
                             boolean complainIfDeclNotFound ) {
    return astToAeExpr( expr, null, convertFcnCallArgsToExprs,
                        lookOutsideClassDataForTypes, complainIfDeclNotFound );
  }

  public String astToAeExpr( Expression expr, String type,
                             boolean convertFcnCallArgsToExprs,
                             boolean lookOutsideClassDataForTypes,
                             boolean complainIfDeclNotFound ) {
    return astToAeExpr( expr, type, convertFcnCallArgsToExprs,
                        lookOutsideClassDataForTypes, complainIfDeclNotFound, false );
  }

  public String astToAeExpr( Expression expr, String type,
                             boolean convertFcnCallArgsToExprs,
                             boolean lookOutsideClassDataForTypes,
                             boolean complainIfDeclNotFound,
                             boolean evaluateCall ) {
    
    Class<?> returnType = null;  // TODO?
    String returnTypeString = "(Class<?>)null";
    
    type = JavaToConstraintExpression.typeToClass( type );
    if ( Utils.isNullOrEmpty( type ) ) {
      type = astToAeExprType( expr, null,
                              lookOutsideClassDataForTypes, complainIfDeclNotFound );
    }
    if ( !Utils.isNullOrEmpty( type ) ) {
      type = ClassUtils.getNonPrimitiveClassName( type );
    }
    final String prefix =
        "new Expression" + ( Utils.isNullOrEmpty( type ) ? "" : "<" + type + ">" ) + "( ";
    final String suffix = " )";
    String middle = null;
    /*** BinaryExpr ***/
    if ( expr.getClass() == BinaryExpr.class ) {
        BinaryExpr be = ( (BinaryExpr)expr );
        middle =
          "new Functions."
               + JavaToConstraintExpression.javaBinaryOpToEventFunctionName( be.getOperator() ) + "( "
               + astToAeExpr( be.getLeft(), true,
                              lookOutsideClassDataForTypes,
                              complainIfDeclNotFound ) + ", "
               + astToAeExpr( be.getRight(), 
                              true,
                              lookOutsideClassDataForTypes,
                              complainIfDeclNotFound)  + " )";
//        if ( !convertFcnCallArgsToExprs ) {
//          middle = "(" + middle + ").functionCall";
//        }
        if ( evaluateCall ) {
          middle = "(" + middle + ").evaluate(true)"; 
        }
    } else
    /*** UnaryExpr ***/
    if ( expr.getClass() == UnaryExpr.class ) {
        UnaryExpr ue = ( (UnaryExpr)expr );
        middle = "new Functions."
                 + JavaToConstraintExpression.astUnaryOpToEventFunctionName( ue.getOperator() ) + "( "
                 + astToAeExpr( ue.getExpr(), type,
                                true, lookOutsideClassDataForTypes,
                                complainIfDeclNotFound ) + " )";
//        if ( !convertFcnCallArgsToExprs ) {
//          middle = "(" + middle + ").functionCall";
//        }
        if ( evaluateCall ) {
          middle = "(" + middle + ").evaluate(true)"; 
        }
    } else
    /*** ConditionalExpr ***/
    if ( expr.getClass() == ConditionalExpr.class ) {
      ConditionalExpr be = ( (ConditionalExpr)expr );
      middle = "new Functions.Conditional(" + 
               astToAeExpr( be.getCondition(), true, lookOutsideClassDataForTypes, complainIfDeclNotFound ) + ", " + 
               astToAeExpr( be.getThenExpr(), true, lookOutsideClassDataForTypes, complainIfDeclNotFound ) + ", " +
               astToAeExpr( be.getElseExpr(), true, lookOutsideClassDataForTypes, complainIfDeclNotFound ) + " ) ";
      if ( evaluateCall ) {
        middle = "(" + middle + ").evaluate(true)"; 
      }
    } else
    /*** EnclosedExpr ***/
    if ( expr.getClass() == EnclosedExpr.class ) {
        middle =
            astToAeExpr( ( (EnclosedExpr)expr ).getInner(), type,
                         convertFcnCallArgsToExprs, lookOutsideClassDataForTypes,
                         complainIfDeclNotFound);
    /*** NameExpr ***/
    } else if ( expr.getClass() == NameExpr.class ) {
      middle = nameExprToAe( (NameExpr)expr, true, evaluateCall, false, true );
    /*** ThisExpr ***/
    } else if ( expr.getClass() == ThisExpr.class ) {
      middle = expr.toString(); // just "this", right?
    /*** FieldAccessExpr ***/
    } else if ( expr.getClass() == FieldAccessExpr.class ) {
      FieldAccessExpr fieldAccessExpr = (FieldAccessExpr)expr;
      middle = fieldExprToAe( fieldAccessExpr, lookOutsideClassDataForTypes,
                              complainIfDeclNotFound, true, evaluateCall,
                              false, true );
    /*** AssignExpr ***/
    } else if ( expr.getClass() == AssignExpr.class ) {
        AssignExpr ae = (AssignExpr)expr;
        String result = null;
        ClassData.Param p = getClassData().lookupCurrentClassMember( ae.getTarget().toString(),
                                            lookOutsideClassDataForTypes, false );
        if ( ae.getOperator() == AssignExpr.Operator.assign ) {
          if ( p == null ) {
            result = ae.getTarget().toString() + " = "
                     + astToAeExpr( ae.getValue(), false, lookOutsideClassDataForTypes,
                                    complainIfDeclNotFound );
          } else {
            result =
                ae.getTarget().toString() + ".setValue( "
                    + astToAeExpr( ae.getValue(), convertFcnCallArgsToExprs,
                                   lookOutsideClassDataForTypes,
                                   complainIfDeclNotFound ) + " )";
          }
          return result;
        }
        if ( p != null ) {
          BinaryExpr abe = new BinaryExpr();
          abe.setLeft( ae.getTarget() );
          abe.setRight( ae.getValue() );
          abe.setOperator( JavaToConstraintExpression.assignOpToBinaryOp( ae.getOperator() ) );
          Assert.assertNotNull( abe.getOperator() );
          result =
              ae.getTarget().toString() + ".setValue( "
                  + astToAeExpr( abe, convertFcnCallArgsToExprs,
                                 lookOutsideClassDataForTypes,
                                 complainIfDeclNotFound ) + " )";
          return result;
        }
        middle = ae.toString();
    /*** MethodCallExpr ***/
    /*** ObjectCreationExpr ***/
    } else if ( expr.getClass() == MethodCallExpr.class ||
                expr.getClass() == ObjectCreationExpr.class ) {
        JavaForFunctionCall javaForFunctionCall =
            new JavaForFunctionCall( this, expr, convertFcnCallArgsToExprs,
                                     getClassData().getPackageName() , evaluateCall, returnType );
        //if ( convertFcnCallArgsToExprs ) {
          middle = javaForFunctionCall.toNewFunctionCallString();
//        } else {
//          if ( Utils.isNullOrEmpty( javaForFunctionCall.getScope() ) ) {
//          middle = javaForFunctionCall.getScope() + ".";
//          if (javaForFunctionCall.methodOrConstructor) {
//            middle += javaForFunctionCall.callName + "(" + javaForFunctionCall.argumentArrayJava + ")";
//          } else {
//            middle += "new " javaForFunctionCall.callName + "(" + javaForFunctionCall.argumentArrayJava + ")";            
//          }
//        }
    } else if ( expr.getClass().getSimpleName().endsWith( "LiteralExpr" ) ) {
      if ( expr.getClass() == NullLiteralExpr.class ) {
        return "null";
      } else {
        middle = expr.toString(); 
      }
    } else  { //if ( expr.getClass() == ConditionalCallExpr.class ) {
      //case "ConditionalExpr": // TODO
        middle = expr.toString();
    }
    if ( !convertFcnCallArgsToExprs ) {
      return middle;
    }
    return prefix + middle + suffix;
  }
  
  public Object //gov.nasa.jpl.ae.event.Expression< ? >
      astToAeExpression( Expression expr, boolean convertFcnCallArgsToExprs,
                         boolean lookOutsideClassDataForTypes,
                         boolean complainIfDeclNotFound ) {
    return astToAeExpression( expr, null, convertFcnCallArgsToExprs,
                              lookOutsideClassDataForTypes, complainIfDeclNotFound );
  }

  public Object //gov.nasa.jpl.ae.event.Expression< ? >
      astToAeExpression( Expression expr, String type,
                         boolean convertFcnCallArgsToExprs,
                         boolean lookOutsideClassDataForTypes,
                         boolean complainIfDeclNotFound ) {
    return astToAeExpression( expr, type, null,
                              convertFcnCallArgsToExprs, lookOutsideClassDataForTypes,
                              complainIfDeclNotFound, false );
  }

  /**
   * @param expr
   * @param type
   * @param specifier
   *          a String representation of a member of the type to help
   *          disambiguate
   * @param convertFcnCallArgsToExprs
   * @param lookOutsideClassDataForTypes
   * @param complainIfDeclNotFound
   * @param evaluateCall
   * @return
   */
  public Object astToAeExpression( Expression expr,
                                   String type,
                                   String specifier, // TODO -- this is never used!!!!
                                   boolean convertFcnCallArgsToExprs,
                                   boolean lookOutsideClassDataForTypes,
                                   boolean complainIfDeclNotFound,
                                   boolean evaluateCall ) {
      if ( expr == null ) return null;
      
      Class< ? > returnType = null;
      
      type = JavaToConstraintExpression.typeToClass( type );
      if ( Utils.isNullOrEmpty( type ) ) {
        type = astToAeExprType( expr, specifier,
                                lookOutsideClassDataForTypes, complainIfDeclNotFound );
      }
      if ( !Utils.isNullOrEmpty( type ) ) {
        type = ClassUtils.getNonPrimitiveClassName( type );
      }
      gov.nasa.jpl.ae.event.Expression<?> aeExpr = null;
      final String prefix =
          "new Expression" + ( Utils.isNullOrEmpty( type ) ? "" : "<" + type + ">" ) + "( ";
      final String suffix = " )";
      //String middle = null;
      /*** BinaryExpr ***/
      if ( expr.getClass() == BinaryExpr.class ) {
          BinaryExpr be = ( (BinaryExpr)expr );
          ConstructorCall call = javaBinaryOpToEventFunction( be.getOperator(), returnType  );
          Debug.errorOnNull( true, "A Functions class must exist for every Java binary operator", call );
          Vector< Object > args = new Vector< Object >();
          args.add(astToAeExpression( be.getLeft(), true,
                                      lookOutsideClassDataForTypes,
                                      complainIfDeclNotFound ) );
          args.add(astToAeExpression( be.getRight(), 
                                      true,
                                      lookOutsideClassDataForTypes,
                                      complainIfDeclNotFound ) );
          call.setArguments( args );
          if ( evaluateCall ) {
            try {
              aeExpr = new gov.nasa.jpl.ae.event.Expression( call.evaluate( true ) );
            } catch ( IllegalAccessException e ) {
              // TODO Auto-generated catch block
              //e.printStackTrace();
            } catch ( InvocationTargetException e ) {
              // TODO Auto-generated catch block
              //e.printStackTrace();
            } catch ( InstantiationException e ) {
              // TODO Auto-generated catch block
              //e.printStackTrace();
            }
          } else {
            aeExpr = new gov.nasa.jpl.ae.event.Expression( call );
          }
          //return aeExpr;
      } else
      /*** UnaryExpr ***/
      if ( expr.getClass() == UnaryExpr.class ) {
          UnaryExpr ue = ( (UnaryExpr)expr );
          //          middle = "new Functions."
//                   + JavaToConstraintExpression.astUnaryOpToEventFunctionName( ue.getOperator() ) + "( "
//                   + astToAeExpr( ue.getExpr(), type,
//                                  true, lookOutsideClassDataForTypes,
//                                  complainIfDeclNotFound ) + " )";
//  //        if ( !convertFcnCallArgsToExprs ) {
//  //          middle = "(" + middle + ").functionCall";
//  //        }
//          if ( evaluateCall ) {
//            middle = "(" + middle + ").evaluate(true)"; 
//          }
          ConstructorCall call = javaUnaryOpToEventFunction( ue.getOperator(), returnType  );
          if ( call != null ) {
              Vector< Object > args = new Vector< Object >(1);
              args.add( astToAeExpression( ue.getExpr(), true,
                                     lookOutsideClassDataForTypes,
                                     complainIfDeclNotFound ) );
              call.setArguments( args );
              if ( evaluateCall ) {
                try {
                  aeExpr = new gov.nasa.jpl.ae.event.Expression( call.evaluate( true ) );
                } catch ( IllegalAccessException e ) {
                  // TODO Auto-generated catch block
                  //e.printStackTrace();
                } catch ( InvocationTargetException e ) {
                  // TODO Auto-generated catch block
                  //e.printStackTrace();
                } catch ( InstantiationException e ) {
                  // TODO Auto-generated catch block
                  //e.printStackTrace();
                }
              } else {
                aeExpr = new gov.nasa.jpl.ae.event.Expression( call );
              }
          }
          //return aeExpr;
     } else
     /*** ConditionalExpr ***/
     if ( expr.getClass() == ConditionalExpr.class ) {
       ConditionalExpr be = ( (ConditionalExpr)expr );
       ConstructorCall call = getIfThenElseConstructorCall(returnType);
       Debug.errorOnNull( true, "A Functions class must exist for every Java binary operator", call );
       Vector< Object > args = new Vector< Object >();
       args.add(astToAeExpression( be.getCondition(), true,
                                   lookOutsideClassDataForTypes,
                                   complainIfDeclNotFound ) );
       args.add(astToAeExpression( be.getThenExpr(), 
                                   true,
                                   lookOutsideClassDataForTypes,
                                   complainIfDeclNotFound ) );
       args.add(astToAeExpression( be.getElseExpr(), 
                                   true,
                                   lookOutsideClassDataForTypes,
                                   complainIfDeclNotFound ) );
       call.setArguments( args );
       if ( evaluateCall ) {
         try {
          aeExpr = new gov.nasa.jpl.ae.event.Expression( call.evaluate( true ) );
        } catch ( IllegalAccessException e ) {
          // TODO Auto-generated catch block
          //e.printStackTrace();
        } catch ( InvocationTargetException e ) {
          // TODO Auto-generated catch block
          //e.printStackTrace();
        } catch ( InstantiationException e ) {
          // TODO Auto-generated catch block
          //e.printStackTrace();
        }
       } else {
         aeExpr = new gov.nasa.jpl.ae.event.Expression( call );
       }
     } else
      /*** EnclosedExpr ***/
      if ( expr.getClass() == EnclosedExpr.class ) {
          Object o =
              astToAeExpression( ( (EnclosedExpr)expr ).getInner(), type,
                           convertFcnCallArgsToExprs, lookOutsideClassDataForTypes,
                           complainIfDeclNotFound);
      aeExpr =
          (gov.nasa.jpl.ae.event.Expression< ? >)( ( o instanceof gov.nasa.jpl.ae.event.Expression )
                                                   ? o
                                                   : new gov.nasa.jpl.ae.event.Expression( o ) );
          //return aeExpr;
      /*** NameExpr ***/
      } else if ( expr.getClass() == NameExpr.class ) {
        aeExpr = nameExprToAeExpression( (NameExpr)expr, true, evaluateCall, false, true, false );
        //return aeExpr;
      /*** ThisExpr ***/
      } else if ( expr.getClass() == ThisExpr.class ) {
        // REVIEW -- is this right?
        aeExpr =
          new gov.nasa.jpl.ae.event.Expression< ParameterListenerImpl >( getClassData().getCurrentAeClass(),
                                                                         ParameterListenerImpl.class );
        /*** FieldAccessExpr ***/
      } else if ( expr.getClass() == FieldAccessExpr.class ) {
        FieldAccessExpr fieldAccessExpr = (FieldAccessExpr)expr;
        aeExpr = fieldExprToAeExpression( fieldAccessExpr,
                                          convertFcnCallArgsToExprs,
                                          lookOutsideClassDataForTypes,
                                          complainIfDeclNotFound, true,
                                          evaluateCall, false, true );
      /*** AssignExpr ***/
      } else if ( expr.getClass() == AssignExpr.class ) {
          AssignExpr ae = (AssignExpr)expr;
          String result = null;
          ClassData.Param p = getClassData().lookupCurrentClassMember( ae.getTarget().toString(),
                                              lookOutsideClassDataForTypes, false );
//          if ( p == null ) {
//            p = getClassData().getParam( null, ae.getTarget().toString(), lookOutsideClassDataForTypes, true, true );
//          }
          Parameter< Object > parameter =
            (Parameter< Object >)getClassData().getParameter( null,ae.getTarget().toString(),
                                                         lookOutsideClassDataForTypes,
                                                         true, true, complainIfDeclNotFound );
          if ( ae.getOperator() == AssignExpr.Operator.assign ) {
            Object value = astToAeExpression(ae.getValue(), false,
                                             lookOutsideClassDataForTypes,
                                             complainIfDeclNotFound);
            FunctionCall fc = new FunctionCall( parameter, Parameter.class, "assignValue", new Object[]{ value }, (Class<?>)null );
            aeExpr = new gov.nasa.jpl.ae.event.Expression( fc );
            //return aeExpr;
          } else
          if ( p != null ) {
            BinaryExpr abe = new BinaryExpr();
            abe.setLeft( ae.getTarget() );
            abe.setRight( ae.getValue() );
            abe.setOperator( JavaToConstraintExpression.assignOpToBinaryOp( ae.getOperator() ) );
            Assert.assertNotNull( abe.getOperator() );
            Object value =
                astToAeExpression( abe, convertFcnCallArgsToExprs,
                                   lookOutsideClassDataForTypes,
                                   complainIfDeclNotFound );
            FunctionCall fc =
                new FunctionCall( parameter, Parameter.class, "assignValue",
                                  new Object[] { value }, (Class<?>)null );
            aeExpr = new gov.nasa.jpl.ae.event.Expression( fc );
            //return aeExpr;
          }
          // TODO -- what happens here??!!
          //middle = ae.toString();
          if ( aeExpr == null && complainIfDeclNotFound ) {
            Debug.error( "Error! could not parse AssignExpr: " + ae );
          }
          //return null;
      /*** MethodCallExpr ***/
      /*** ObjectCreationExpr ***/
      } else if ( expr.getClass() == MethodCallExpr.class ||
                  expr.getClass() == ObjectCreationExpr.class ) {
          JavaForFunctionCall javaForFunctionCall =
              new JavaForFunctionCall( this, expr, convertFcnCallArgsToExprs,
                                       getClassData().getPackageName(),
                                       evaluateCall,
                                       returnType);
          aeExpr = javaForFunctionCall.toNewExpression();
          //return aeExpr;
//          //if ( convertFcnCallArgsToExprs ) {
//            middle = javaForFunctionCall.toNewFunctionCallString();
//  //        } else {
//  //          if ( Utils.isNullOrEmpty( javaForFunctionCall.getScope() ) ) {
//  //          middle = javaForFunctionCall.getScope() + ".";
//  //          if (javaForFunctionCall.methodOrConstructor) {
//  //            middle += javaForFunctionCall.callName + "(" + javaForFunctionCall.argumentArrayJava + ")";
//  //          } else {
//  //            middle += "new " javaForFunctionCall.callName + "(" + javaForFunctionCall.argumentArrayJava + ")";            
//  //          }
//  //        }
      } else if ( expr instanceof LiteralExpr ) { //.getClass().getSimpleName().endsWith( "LiteralExpr" ) ) {
          aeExpr = parseNonNullLiteralExpression( (LiteralExpr)expr );
          //return aeExpr;
      } else  { // TODO!!!!!!!!  More expressions to parse!! //if ( expr.getClass() == ConditionalCallExpr.class ) {
        //case "ConditionalExpr": // TODO
          //middle = expr.toString();
        Class<?> cls = ClassUtils.getClassForName( type, null, getClassData().getPackageName(), false );
        aeExpr = new gov.nasa.jpl.ae.event.Expression< Class >( cls, Class.class );
      }
      if ( !convertFcnCallArgsToExprs && aeExpr != null ) {
        return aeExpr.expression;
      }
      return aeExpr;//prefix + middle + suffix;
    }

  public gov.nasa.jpl.ae.event.Expression< ? >
      parseNonNullLiteralExpression( LiteralExpr expr ) {
    if ( expr.getClass() == NullLiteralExpr.class ) {
      return new gov.nasa.jpl.ae.event.Expression< Object >( (Object)null );
    } else {
      if ( expr instanceof BooleanLiteralExpr ) {
        Boolean b = ( (BooleanLiteralExpr)expr ).getValue();
        return new gov.nasa.jpl.ae.event.Expression< Boolean >( b );
      }
      String s = null;
      if ( expr instanceof StringLiteralExpr ) {
        if ( expr instanceof IntegerLiteralExpr ) {
          Integer i = Utils.toInteger( ( (IntegerLiteralExpr)expr ).getValue() );
          return new gov.nasa.jpl.ae.event.Expression< Integer >( i );
        }
        if ( expr instanceof LongLiteralExpr ) {
          Long d = Utils.toLong( ( (LongLiteralExpr)expr ).getValue() );
          return new gov.nasa.jpl.ae.event.Expression< Long >( d );
        }
        if ( expr instanceof DoubleLiteralExpr ) {
          Double d = Utils.toDouble( ( (DoubleLiteralExpr)expr ).getValue() );
          return new gov.nasa.jpl.ae.event.Expression< Double >( d );
        }
        if ( expr instanceof CharLiteralExpr ) {
          s = ( (StringLiteralExpr)expr ).getValue();
          return new gov.nasa.jpl.ae.event.Expression< String >( s );
        }
        // Just a plain StringLiteralExpr
        s = ( (StringLiteralExpr)expr ).getValue();
        return new gov.nasa.jpl.ae.event.Expression< String >( s );
      }
    }
    Debug.error( true, "Error! Unrecognized expression: " + expr );
    return null;
  }

    // TODO -- should probably import and do a switch on all classes in
    // japa.parser.ast.expr.*
    public String astToAeExprType( Expression expr, String specifier,
                                   boolean lookOutsideClassData, boolean complainIfNotFound ) {
      ClassData.Param p = null;
      String name = null;
      String result = null;
      if ( expr == null ) return null;
      String className = expr.getClass().getSimpleName();
      // Inefficient string compare.
      if ( Debug.isOn() ) Debug.outln( "starting astToAeExprType(" + className + ":" + expr + ")" );
      if ( expr.getClass() == ConditionalExpr.class ) {
          ConditionalExpr ce = ( (ConditionalExpr)expr );
          String type1 = astToAeExprType( ce.getThenExpr(), specifier,
                                          lookOutsideClassData, complainIfNotFound );
          String type2 = astToAeExprType( ce.getElseExpr(), specifier,
                                          lookOutsideClassData, complainIfNotFound );
          result = ClassUtils.dominantType( type1, type2 );
      } else if ( expr.getClass() == ArrayCreationExpr.class ) {
        ArrayCreationExpr be = ( (ArrayCreationExpr)expr );
        result = be.getType().toString();
      } else if ( expr.getClass() == BinaryExpr.class ) {
          BinaryExpr be = ( (BinaryExpr)expr );
          result =
              JavaToConstraintExpression.operatorResultType( be.getOperator(),
                                  astToAeExprType( be.getLeft(), specifier, lookOutsideClassData, complainIfNotFound ),
                                  astToAeExprType( be.getRight(), specifier, lookOutsideClassData, complainIfNotFound ) );
      } else if ( expr.getClass() == UnaryExpr.class ) {
          UnaryExpr ue = ( (UnaryExpr)expr );
          result =
              JavaToConstraintExpression.operatorResultType( ue.getOperator(),
                                  astToAeExprType( ue.getExpr(), specifier, lookOutsideClassData, complainIfNotFound ) );
      } else if ( expr.getClass() == ConditionalExpr.class ) {
        ConditionalExpr be = ( (ConditionalExpr)expr );
        result =
            ClassUtils.dominantType( astToAeExprType( be.getThenExpr(), specifier, lookOutsideClassData, complainIfNotFound ),
                          astToAeExprType( be.getElseExpr(), specifier, lookOutsideClassData, complainIfNotFound ) );
      } else if ( expr.getClass() == EnclosedExpr.class ) {
          result = astToAeExprType( ( (EnclosedExpr)expr ).getInner(), specifier, lookOutsideClassData, complainIfNotFound );
      } else if ( expr.getClass() == MethodCallExpr.class ) {
        Class< ? > returnType = null;
        JavaForFunctionCall javaForFunctionCall =
            new JavaForFunctionCall( this, expr, false,
                                     getClassData().getPackageName(), false,
                                     returnType  );
        Method mm = javaForFunctionCall.getMatchingMethod();
        if ( mm != null ) {
          Class<?> type = mm.getReturnType(); 
          Class<?> cls = mm.getDeclaringClass();
          boolean typeWasObject = type == Object.class;
          // TODO -- REVIEW -- Should below be something like Utils.replaceSuffix(ClassUtils.toString( expression.getType() ), ".class", "" );
          if ( type != null && !typeWasObject ) {
            result = ( type.isArray() ? type.getSimpleName() : type.getName() );
          }
          if ( ( type == null || typeWasObject ) && cls != null
              && mm.getName().startsWith( "getValue" )
              //&& mm.getName().equals( "getValue" )
  //            && result.equals( "java.lang.Object" )
              && Wraps.class.isAssignableFrom( cls ) ) {
            Object n = null;
            try {
              n = cls.newInstance();
            } catch ( InstantiationException e ) {
              // ignore
            } catch ( IllegalAccessException e ) {
              // ignore
            }
            if ( n != null && n instanceof Wraps ) {
              result = ( (Wraps< ? >)n ).getTypeNameForClassName( javaForFunctionCall.getClassName() );
              if ( result != null && result.endsWith( "Object" ) ) {
                typeWasObject = false;
              }
              type = ( (Wraps< ? >)n ).getType();
            }
          }
          if ( type == null ) {
            if ( result == null ) {
              result = ClassUtils.parameterPartOfName( javaForFunctionCall.getClassName(), false );
            }
  //          if ( result == null && typeWasObject ) {
  //            result = "Object";
  //          }
  //          //type = ClassUtils.getClassForName( javaForFunctionCall.className, null, false );
          } else {
            result = ( type.isArray() ? type.getSimpleName() : type.getName() );
          }
          if ( result != null && result.endsWith( "Object" ) && typeWasObject ) {
            result = null;
          }
        }
        if ( result == null ) {
          // don't worry about it--special purpose code is called later for this
          result = "null";
          if ( Debug.isOn() ) Debug.outln( "astToAeExprType(" + expr + ") = " + result
                       + "; ok for MethodCallExpr!" );
          Debug.out( "" ); // delete me!!
          complainIfNotFound = false;
        }
      } else if ( expr.getClass() == NameExpr.class ) {
        name = ( (NameExpr)expr ).getName();
  /*      // HACK? to avoid errors for package name prefix.  What about org?
        if ( name.equals( "java" ) ) {
          result = "java";
        }
        if ( result == null ) {
          // Maybe it's just a class or package name.
          Class< ? > cls = ClassUtils.getClassForName( expr.toString(), packageName, false );
          if ( cls != null ) {
            // REVIEW -- Is it's type itself because it is a type (class name)?
            result = expr.toString();
          }
          if ( result == null ) {
            // REVIEW -- Is it's type itself because it is a package name?
            Package pkg = Package.getPackage( expr.toString() );
            if ( pkg != null ) {
              result = expr.toString();
            }
            // try this? ClassUtils.getPackageStrings( packages );
          }
        }
  *///      below doesn't work.
  //      if ( name == "True" ) name = "true";
  //      if ( name == "False" ) name = "false";
      } else if ( expr.getClass() == ThisExpr.class ) {
        result = getCurrentClass();
      } else if ( expr.getClass() == FieldAccessExpr.class ) {
        FieldAccessExpr fieldAccessExpr = (FieldAccessExpr)expr;
        result = fieldExprToAeType( fieldAccessExpr, lookOutsideClassData );
        if ( result == null ) {
          // REVIEW -- This probably won't work! What case is this?
          if ( Debug.isOn() ) Debug.err( "Can't determine type from FieldAccessExpr: " + expr );
          name = expr.toString();
        }
      } else if ( expr.getClass() == ObjectCreationExpr.class ) {
        ObjectCreationExpr oce = (ObjectCreationExpr)expr;
        result = oce.getType().toString();
      } else if ( expr.getClass() == ClassExpr.class ) {
        ClassExpr ce = (ClassExpr)expr;
        //String pType = astToAeExprType( ce.getType(), lookOutsideXml, complainIfNotFound );
        String c = getClassData().getClassNameWithScope( ce.getType().toString(), true );
        if ( Utils.isNullOrEmpty( c ) ) {
          Class<?> cc = 
              ClassUtils.getClassForName( ce.getType().toString(), specifier,
                                          getClassData().getPackageName(),
                                          false );
          if ( cc != null ) {
            c = ClassUtils.getNonPrimitiveClassName( cc.getName() );
          } else {
            c = "?";
          }
        }
        result = "Class<" + c + ">";
      } else {
          if ( className.endsWith( "LiteralExpr" ) ) {
            // get the part before "LiteralExpr"
            String typeOfLiteral =
                className.substring( 0, className.length() - 11 );
            if ( typeOfLiteral.equals( "Null" ) ) {
              result = "null"; // BAD!  REVIEW -- Do we want void or String?
            } else {
              result = typeOfLiteral;
            }
          } else if ( className.contains( "Literal" ) ) {
            result = className.substring( 0, className.indexOf( "Literal" ) );
          }
          name = expr.toString();
      }
  
      if ( result == null &&  name != null ) {
        if ( name.startsWith( "\"" ) ) {
          result = "String";
        } else {
          p = getClassData().lookupCurrentClassMember( name, lookOutsideClassData, false );
          result = ( p == null ) ? null : p.type;
        }
  
        // Maybe it's just a class or package name.
        // REVIEW -- Is it's type itself because it is a type?
        if ( result == null ) {
          Class< ? > cls =
              ClassUtils.getClassForName( expr.toString(), specifier,
                                          getClassData().getPackageName(), false );
          if ( cls != null ) {
            result = expr.toString();
  //        } else {
  //          if ( ClassUtils.isPackageName( expr.toString() ) ) {
  //            result = expr.toString(); // ??
  //          }
          }
        }
      }
      
      if ( complainIfNotFound && Utils.isNullOrEmpty( result ) )
        Debug.errorOnNull( "Error! null type for expression " + expr + "!", result );
      if ( Debug.isOn() ) Debug.outln( "astToAeExprType(" + expr + ") = " + result );
      // Nested type cannot be referenced by its binary name.
      if ( result != null ) result = result.replace( '$', '.' );
      return result;
    }

  public String
      fieldExprToAeType( FieldAccessExpr fieldAccessExpr, boolean lookOutsideClassData ) {
    String result = null;

    // Maybe it's just a class or package name.
    Class< ? > cls =
        ClassUtils.getClassForName( fieldAccessExpr.toString(), null,
                                    getClassData().getPackageName(), false );
    if ( cls != null ) {
      // REVIEW -- Is it's type itself because it is a type (class name)?
      result = fieldAccessExpr.toString();
    } else {

      //FieldAccessExpr fieldAccessExpr = (FieldAccessExpr)expr;
      // The member/field type is defined in its parent's class, and the parent
      // class can be found by getting the type of the FiedAccessExpr's scope.
      // if ( fieldAccessExpr.getScope() instanceof FieldAccessExpr ) {
      if ( fieldAccessExpr.toString().startsWith( "x." ) ) {
        Debug.out( "" );
      }
      String parentType =
          astToAeExprType( fieldAccessExpr.getScope(), fieldAccessExpr.getField(),
                           lookOutsideClassData, false );
      ClassData.Param p = null;
      if ( !Utils.isNullOrEmpty( parentType ) ) {
        p = getClassData().lookupMemberByName( parentType,
                                               fieldAccessExpr.getField(),
                                               lookOutsideClassData, false );
      }
      // }
      if ( p == null ) {
        // If the member is static, then the scope is a class name, and we can
        // try looking it up. // TODO -- Check to see if it's static.
        p = getClassData().lookupMemberByName( fieldAccessExpr.getScope().toString(),
                                               fieldAccessExpr.getField(),
                                               lookOutsideClassData, false );
      }
      if ( p != null ) {
        result = p.type;
      } else {
        // Maybe it's not a field access, but an enclosed class.
        if ( Utils.isNullOrEmpty( parentType ) ) {
          parentType = fieldAccessExpr.getScope().toString();
        }
        Class< ? > classForName =
            ClassUtils.getClassOfClass( parentType,
                                        fieldAccessExpr.getField().toString(),
                                        getClassData().getPackageName(), false );
        if ( classForName != null ) {
          result = classForName.getName();
        }
      }
    }
    return result;
  }

  public String fieldExprToAe( FieldAccessExpr fieldAccessExpr,
                               boolean lookOutsideClassDataForTypes,
                               boolean complainIfDeclNotFound,
                               boolean wrapInFunction, boolean evaluateCall,
                               boolean getParameterValue, boolean propagate ) {
    // TODO -- make this more like fieldExprToAeExpression(), which handles more
    // cases (not just when scope is FieldAccessExpr and NameExpr) and passes
    // convertFcnCallArgsToExprs arg through.
    String aeString = null;
    if ( fieldAccessExpr.getScope() != null
         && ( fieldAccessExpr.getScope() instanceof FieldAccessExpr || fieldAccessExpr.getScope() instanceof NameExpr ) ) {
      /* */
      // Maybe it's just a class or package name.
      Class< ? > cls =
          ClassUtils.getClassForName( fieldAccessExpr.toString(),
                                      null, getClassData().getPackageName(), false );
      if ( cls != null ) {
        return fieldAccessExpr.toString();
      }
      Package pkg = Package.getPackage( fieldAccessExpr.toString() );
      if ( pkg != null ) {
        return fieldAccessExpr.toString();
      }
      /* */
      String parentType =
          astToAeExprType( fieldAccessExpr.getScope(),
                           null, lookOutsideClassDataForTypes, complainIfDeclNotFound );
      if ( !Utils.isNullOrEmpty( parentType ) ) {
        ClassData.Param p =
            getClassData().lookupMemberByName( parentType,
                                          fieldAccessExpr.getField(), false,
                                          false );
        String parentString = null;
        if ( fieldAccessExpr.getScope() instanceof FieldAccessExpr ) {
          parentString =
              fieldExprToAe( (FieldAccessExpr)fieldAccessExpr.getScope(),
                             lookOutsideClassDataForTypes,
                             complainIfDeclNotFound, wrapInFunction, false,
                             true, propagate );
        } else {
          parentString =
              nameExprToAe( (NameExpr)fieldAccessExpr.getScope(),
                            wrapInFunction, evaluateCall, !wrapInFunction,
                            propagate );
        }
        aeString =
            packageExpressionString( parentString, fieldAccessExpr,
                                     wrapInFunction, evaluateCall,
                                     p != null && getParameterValue, propagate );
//        if ( wrapInFunction ) {
//          aeString =
//              "new FunctionCall(" + parentString
//                  + ", Parameter.class, \"getMember\", " + "new Object[]{\""
//                  + fieldAccessExpr.getField() + "\"})";
//        } else {
//          aeString = parentString + "." + fieldAccessExpr.getField();
//        }
//        if ( p != null && getParameterValue ) {
//          if ( wrapInFunction ) {
//            // nesting function calls
//            aeString =
//                "new FunctionCall(null, Parameter.class, \"getValue\", "
//                    + "new Object[]{ true }, " + aeString + ")";
//          } else {
//            aeString += ".getValue(" + propagate + ")";
//          }
//        }
//        if ( wrapInFunction && evaluateCall ) {
//          aeString = "(" + aeString + ").evaluate(" + propagate + ")";
//        }
      }
    } else if ( fieldAccessExpr.getScope() instanceof ThisExpr ) {
      aeString = fieldAccessExpr.toString();
    }
    return aeString;
  }
  
  public gov.nasa.jpl.ae.event.Expression< ? >
    fieldExprScopeToAeExpression( FieldAccessExpr fieldAccessExpr,
                                  boolean convertFcnCallArgsToExprs,
                                  boolean lookOutsideClassDataForTypes,
                                  boolean complainIfDeclNotFound,
                                  boolean wrapInFunction, boolean evaluateCall,
                                  boolean propagate ) {
    if ( fieldAccessExpr.getScope() == null ) {
      Debug.error( false, "Warning! Got FieldAccessExpr with null scope! "
                          + fieldAccessExpr );
      return null;
    }
    gov.nasa.jpl.ae.event.Expression< ? > parentExpr = null;
    if ( fieldAccessExpr.getScope() instanceof FieldAccessExpr ) {
      parentExpr =
          fieldExprToAeExpression( (FieldAccessExpr)fieldAccessExpr.getScope(),
                                   convertFcnCallArgsToExprs,
                                   lookOutsideClassDataForTypes,
                                   complainIfDeclNotFound, wrapInFunction,
                                   false, true, propagate );
    } else if ( fieldAccessExpr.getScope() instanceof NameExpr ) {
      String type = astToAeExprType( fieldAccessExpr.getScope(),
                                     null,
                                     lookOutsideClassDataForTypes, complainIfDeclNotFound );
      boolean addIfNotFound = !type.equals( fieldAccessExpr.getScope().toString() ) && !wrapInFunction;
      parentExpr =
          nameExprToAeExpression( (NameExpr)fieldAccessExpr.getScope(),
                                  wrapInFunction, evaluateCall,
                                  addIfNotFound, propagate, complainIfDeclNotFound );
    } else {
      Object o =
          astToAeExpression( fieldAccessExpr.getScope(), null,
                             null,
                             convertFcnCallArgsToExprs, lookOutsideClassDataForTypes,
                             complainIfDeclNotFound, evaluateCall );
      if ( o instanceof gov.nasa.jpl.ae.event.Expression ) {
        parentExpr = (gov.nasa.jpl.ae.event.Expression< ? >)o;
      } else if ( o instanceof Parameter ) {
        parentExpr = new gov.nasa.jpl.ae.event.Expression( (Parameter<?>)o );
      } else if ( o instanceof FunctionCall ) {
        parentExpr = new gov.nasa.jpl.ae.event.Expression( (FunctionCall)o );
      }
    }
    return parentExpr;
  }
  
  public gov.nasa.jpl.ae.event.Expression< ? >
      fieldExprToAeExpression( FieldAccessExpr fieldAccessExpr,
                               boolean convertFcnCallArgsToExprs,
                               boolean lookOutsideClassDataForTypes,
                               boolean complainIfDeclNotFound,
                               boolean wrapInFunction, boolean evaluateCall,
                               boolean getParameterValue, boolean propagate ) {
    gov.nasa.jpl.ae.event.Expression< ? > aeExpr = null;
    gov.nasa.jpl.ae.event.Expression< ? > parentExpr = null;

    // Maybe it's just a class or package name.
    Class< ? > cls =
        ClassUtils.getClassForName( fieldAccessExpr.toString(),
                                    null, getClassData().getPackageName(), false );
    if ( cls != null ) {
      return new gov.nasa.jpl.ae.event.Expression< Class >( cls, Class.class );
    }
    Package pkg = Package.getPackage( fieldAccessExpr.toString() );
    if ( pkg != null ) {
      return new gov.nasa.jpl.ae.event.Expression< Package >( pkg,
                                                              Package.class );
    }

    if ( fieldAccessExpr.getScope() == null ) {
      Debug.error( false, "Warning! Got FieldAccessExpr with null scope! "
                          + fieldAccessExpr );
      NameExpr nameExpr = new NameExpr( fieldAccessExpr.getField() );
      aeExpr =
          nameExprToAeExpression( nameExpr, wrapInFunction, evaluateCall,
                                  wrapInFunction, propagate, complainIfDeclNotFound );
    } else {
      parentExpr =
          fieldExprScopeToAeExpression( fieldAccessExpr,
                                        convertFcnCallArgsToExprs,
                                        lookOutsideClassDataForTypes,
                                        complainIfDeclNotFound, wrapInFunction,
                                        evaluateCall, propagate );
      if ( parentExpr != null ) {
        FunctionCall functionCall =
            new FunctionCall( parentExpr.expression, Parameter.class,
                              "getMember",
                              new Object[] { "" + fieldAccessExpr.getField(), true }, (Class<?>)null );
        aeExpr = new gov.nasa.jpl.ae.event.Expression( functionCall );
        String parentType =
            astToAeExprType( fieldAccessExpr.getScope(),
                             null, lookOutsideClassDataForTypes, complainIfDeclNotFound );
        ClassData.Param p =
            getClassData().lookupMemberByName( parentType,
                                          fieldAccessExpr.getField(), false,
                                          false );
        aeExpr =
            packageExpression( aeExpr, wrapInFunction, evaluateCall,
                               p != null && getParameterValue, propagate );
//        if ( p != null && getParameterValue ) {
//          // if ( wrapInFunction ) {
//          // nesting function calls
//          functionCall =
//              new FunctionCall( aeExpr, Parameter.class, "getValue",
//                                new Object[] { true } );
//          aeExpr = new gov.nasa.jpl.ae.event.Expression( functionCall );
//        }
//        if ( evaluateCall ) { // && wrapInFunction ) {
//          Object result = null;
//          result = aeExpr.evaluate( propagate );
//          if ( !aeExpr.didEvaluationSucceed() ) {
//            aeExpr = null;
//          } else if ( !( result instanceof gov.nasa.jpl.ae.event.Expression ) ) {
//            aeExpr = new gov.nasa.jpl.ae.event.Expression( result );
//          }
//        }
      }
    }
    return aeExpr;
  }
  
  public gov.nasa.jpl.ae.event.Expression< ? >
      packageExpression( Object object,
                         boolean wrapInFunction, boolean evaluateCall,
                         boolean getParameterValue, boolean propagate ) {
    gov.nasa.jpl.ae.event.Expression< ? > aeExpr =
        ( object instanceof Expression ) ? (gov.nasa.jpl.ae.event.Expression< ? >)object
                                         : null;
    if ( getParameterValue || wrapInFunction ) {
      // nesting function calls
      FunctionCall functionCall =
          new FunctionCall( object, Parameter.class, "getValue",
                            new Object[] { true }, (Class<?>)null );
      aeExpr = new gov.nasa.jpl.ae.event.Expression( functionCall );
    }
    if ( evaluateCall && aeExpr != null ) { // && wrapInFunction ) {
      Object result = null;
      try {
        result = aeExpr.evaluate( propagate );
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      }
      if ( !aeExpr.didEvaluationSucceed() ) {
        aeExpr = null;
      } else if ( !( result instanceof gov.nasa.jpl.ae.event.Expression ) ) {
        aeExpr = new gov.nasa.jpl.ae.event.Expression( result );
      }
    }
    if ( aeExpr == null && !wrapInFunction && object instanceof Parameter ) {
      aeExpr = new gov.nasa.jpl.ae.event.Expression( ((Parameter< ? >)object).getValue( propagate ) );
    }
    return aeExpr;
  }

  public String packageExpressionString( String parentString,
                                         FieldAccessExpr fieldAccessExpr,
                                         boolean wrapInFunction,
                                         boolean evaluateCall,
                                         boolean getParameterValue,
                                         boolean propagate ) {
    String aeString;
    if ( wrapInFunction ) {
      aeString =
          "new FunctionCall(" + parentString
              + ", Parameter.class, \"getMember\", " + "new Object[]{\""
              + fieldAccessExpr.getField() + "\"})";
    } else {
      aeString = parentString + "." + fieldAccessExpr.getField();
    }
    if ( getParameterValue ) {
      if ( wrapInFunction ) {
        // nesting function calls
        aeString =
            "new FunctionCall(null, Parameter.class, \"getValue\", "
                + "new Object[]{ true }, " + aeString + ")";
      } else {
        aeString += ".getValue(" + propagate + ")";
      }
    }
    if ( wrapInFunction && evaluateCall ) {
      aeString = "(" + aeString + ").evaluate(" + propagate + ")";
    }
    return aeString;
  }
  

    protected Expression fixExpression( Expression expr ) {
      if ( expr == null ) return null;
      String newExprString = astToAeExpr( expr, false, true, false );
      Expression newExpr = parseExpression( newExprString );
      return newExpr;
    }

    protected List<Expression> fixExpressions( List<Expression> exprs ) {
      if ( exprs == null ) return null;
      List<Expression> newExprs = new ArrayList<Expression>();
      for ( Expression expr : exprs) {
        Expression newExpr = fixExpression( expr );
        if ( newExpr != null ) newExprs.add( newExpr );
        else newExprs.add( expr );
      }
      return newExprs;
    }

    /**
       * Convert the Java statement into an AE Java statement by converting 
       * expressions with astToAeExpr().
       * @param stmt the statement to fix
       */
      public void fixStatement( Statement stmt ) {
        if ( stmt == null ) return;
        if ( stmt instanceof ExpressionStmt ) {
          ExpressionStmt exprStmt = (ExpressionStmt)stmt;
          Expression expr = fixExpression( exprStmt.getExpression() );
          exprStmt.setExpression( expr );
    //      if ( exprStmt.getExpression() instanceof AssignExpr ) {
    //        AssignExpr assignExpr = (AssignExpr)exprStmt.getExpression();
    //        String newExprString = astToAeExpr( assignExpr, false, true, false );
    //        
    //        assignExpr.getValue();
    //        Param p = lookupMemberByName(className, paramName, false, false);
    //      }
        } else if ( stmt instanceof BlockStmt ) {
          BlockStmt bs = (BlockStmt)stmt;
          fixStatements( bs.getStmts() );
        } else if ( stmt instanceof AssertStmt ) {
          AssertStmt assertStmt = (AssertStmt)stmt;
          Expression expr = fixExpression( assertStmt.getCheck() );
          if ( expr != null ) assertStmt.setCheck( expr );
        } else if ( stmt instanceof DoStmt ) {
          DoStmt doStmt = (DoStmt)stmt;
          //List<Statement> stmtList = new ArrayList<Statement>();
          fixStatement( doStmt.getBody() );
          Expression expr = fixExpression( doStmt.getCondition() );
          if ( expr != null ) doStmt.setCondition( expr );
          //doStmt.setBody( stmtList.get( 0 ) );
        } else if ( stmt instanceof ExplicitConstructorInvocationStmt ) {
          ExplicitConstructorInvocationStmt ecis = (ExplicitConstructorInvocationStmt)stmt;
          List< Expression > exprs = fixExpressions( ecis.getArgs() );
          if ( exprs != null ) ecis.setArgs( exprs );
          Expression expr = fixExpression( ecis.getExpr() );
          if ( expr != null ) ecis.setExpr( expr );
        } else if ( stmt instanceof ForeachStmt ) {
          ForeachStmt foreachStmt = (ForeachStmt)stmt;
          fixStatement( foreachStmt.getBody() );
          Expression expr = fixExpression( foreachStmt.getIterable() );
          if ( expr != null ) foreachStmt.setIterable(expr);
          expr = fixExpression( foreachStmt.getVariable() );
          if ( expr != null && expr instanceof VariableDeclarationExpr ) {
            foreachStmt.setVariable( (VariableDeclarationExpr)expr );
          }
        } else if ( stmt instanceof ForStmt ) {
          ForStmt forStmt = (ForStmt)stmt;
          fixStatement( forStmt.getBody() );
          List< Expression > exprs = fixExpressions( forStmt.getInit() );
          if ( exprs != null ) forStmt.setInit( exprs );
          Expression expr = fixExpression( forStmt.getCompare() );
          if ( expr != null ) forStmt.setCompare( expr );
          exprs = fixExpressions( forStmt.getUpdate() );
          if ( exprs != null ) forStmt.setUpdate( exprs );        
        } else if ( stmt instanceof IfStmt ) {
          IfStmt ifStmt = (IfStmt)stmt;
          List<Statement> stmtList = new ArrayList<Statement>();
          stmtList.add( ifStmt.getThenStmt() );
          stmtList.add( ifStmt.getElseStmt() );
          fixStatements( stmtList );
          Expression expr = fixExpression( ifStmt.getCondition() );
          if ( expr != null ) ifStmt.setCondition( expr );
        } else if ( stmt instanceof LabeledStmt ) {
          LabeledStmt labeledStmt = (LabeledStmt)stmt;
          fixStatement( labeledStmt.getStmt() );
        } else if ( stmt instanceof ReturnStmt ) {
          ReturnStmt returnStmt = (ReturnStmt)stmt;
          Expression expr = fixExpression( returnStmt.getExpr() );
          if ( expr != null ) returnStmt.setExpr( expr );
        } else if ( stmt instanceof SwitchEntryStmt ) {
          SwitchEntryStmt switchStmt = (SwitchEntryStmt)stmt;
          fixStatements( switchStmt.getStmts() );
          Expression expr = fixExpression( switchStmt.getLabel() );
          if ( expr != null ) switchStmt.setLabel( expr );
        } else if ( stmt instanceof SwitchStmt ) {
          SwitchStmt switchStmt = (SwitchStmt)stmt;
          fixStatements( switchStmt.getEntries() );
          Expression expr = fixExpression( switchStmt.getSelector() );
          if ( expr != null ) switchStmt.setSelector( expr );
        } else if ( stmt instanceof SynchronizedStmt ) {
          SynchronizedStmt synchStmt = (SynchronizedStmt)stmt;
          fixStatement( synchStmt.getBlock() );
          Expression expr = fixExpression( synchStmt.getExpr() );
          if ( expr != null ) synchStmt.setExpr( expr );
        } else if ( stmt instanceof ThrowStmt ) {
          ThrowStmt throwStmt = (ThrowStmt)stmt;
          Expression expr = fixExpression( throwStmt.getExpr() );
          if ( expr != null ) throwStmt.setExpr( expr );
        } else if ( stmt instanceof TryStmt ) {
          TryStmt tryStmt = (TryStmt)stmt;
          List<Statement> stmtList = new ArrayList<Statement>();
          stmtList.add( tryStmt.getTryBlock() );
          stmtList.add( tryStmt.getFinallyBlock() );
          for ( CatchClause cc : tryStmt.getCatchs() ) {
            stmtList.add( cc.getCatchBlock() );
          }
          fixStatements( stmtList );
        } else if ( stmt instanceof TypeDeclarationStmt ) {
          // TODO
//          TypeDeclarationStmt typeDeclStmt = (TypeDeclarationStmt)stmt;
//          for ( BodyDeclaration member : typeDeclStmt.getTypeDeclaration().getMembers() ) {
//            //member
//          }
        } else if ( stmt instanceof WhileStmt ) {
          WhileStmt whileStmt = (WhileStmt)stmt;
          fixStatement( whileStmt.getBody() );
          Expression expr = fixExpression( whileStmt.getCondition() );
          if ( expr != null ) whileStmt.setCondition( expr );
        } else {
          System.err.println( "fixStatement(): got unhandled Statement type: "
                              + stmt.getClass().getName() );
        }
      }

    /**
     * Converts Java statements into AE Java statements by converting expressions
     * with astToAeExpr().
     * @param className
     * @param stmts
     */
    protected void fixStatements( List<? extends Statement> stmts ) {
      if ( stmts == null ) return;
      for ( Statement stmt : stmts ) {
        fixStatement( stmt );
      }
    }

    /**
       * Fix all names in the string so that they can be used as Java identifiers
       * or type names.  This currently just calls fixName( value ) since fixName()
       * does not change number tokens in the string.
       * 
       * @param value
       *          The string to be fixed.
       * @return A translation of name into a valid Java identifier or type name.
       */
      public String fixValue( String value ) {
        return value;
    /*    if ( value == null ) return null;
        //String javaValue = nameTranslator.substitute( value, "xml", "java" );
        String javaValue = fixName( value );
        //if ( Debug.isOn() ) Debug.outln("fixName(\"" + value + "\") = \"" + javaValue + "\"" );
        return javaValue;
    */  }

    public String getObjectFromScope( Expression scopeExpr ) {
      String object = null;
      if ( scopeExpr != null) {
        object = astToAeExpr( scopeExpr, false, true, false );
      }
      if ( object == null ) object = "null";
      return object;
    }
    public Object getObjectExpressionFromScope( Expression scopeExpr ) {
      Object object = null;
      if ( scopeExpr != null) {
        object = astToAeExpression( scopeExpr, false, true, false );
      }
      if ( object == null ) object = "null";
      return object;
    }
    

    /**
     * Don't call this!  It can cause an infinite loop
     *   JavaToConstraintExpression.javaToAeExpression(String, String, boolean) line: 1339    
     *   JavaForFunctionCall.toNewFunctionCall() line: 452   
     *   JavaForFunctionCall.toNewExpression() line: 507 
     *   JavaToConstraintExpression.astToAeExpression(Expression, String, String, boolean, boolean, boolean, boolean) line: 661  
     *   JavaToConstraintExpression.astToAeExpression(Expression, String, boolean, boolean, boolean) line: 499   
     *   JavaToConstraintExpression.javaToAeExpression(String, String, boolean) line: 1339   
     * <p>TODO -- Find out why toNewFunctionCall() calls this instead of javaToAeExpr()!!!
     * <p>Fixing by commenting out first line and changing fourth line.
     * 
     * @param exprString
     * @param type
     * @param convertFcnCallArgsToExprs
     * @return
     */
    public gov.nasa.jpl.ae.event.Expression< ? > javaToAeExpression( String exprString, String type, 
                                boolean convertFcnCallArgsToExprs ) {
      //String exprStr = javaToAeExpr( exprString, type, convertFcnCallArgsToExprs );
      
      gov.nasa.jpl.ae.event.Expression< ? > expr = null;
      //Expression astExpr = parseExpression( exprStr );
      Expression astExpr = parseExpression( exprString );
      Object o = astToAeExpression( astExpr, type, null, convertFcnCallArgsToExprs, true, true, true );
      expr = (gov.nasa.jpl.ae.event.Expression< ? >)( o instanceof gov.nasa.jpl.ae.event.Expression
               ? o
               : new gov.nasa.jpl.ae.event.Expression( o ) );
      return expr;
    }
    
    public String javaToAeExpr( String exprString, String type, 
                                boolean convertFcnCallArgsToExprs ) {
      return javaToAeExpr( exprString, type, convertFcnCallArgsToExprs, false );
    }

    public String javaToAeExpr( String exprString, String type, 
                                boolean convertFcnCallArgsToExprs,
                                boolean evaluateCall ) {
      Expression expr = parseExpression( exprString );
      return astToAeExpr( expr, type, convertFcnCallArgsToExprs, true, true,
                          evaluateCall );
    }

  public < T > gov.nasa.jpl.ae.event.Expression< T >
      nameExprToAeExpression( NameExpr nameExpr, boolean wrapInFunction,
                              boolean evaluateCall, boolean addIfNotFound,
                              boolean propagate, boolean complainIfNotFound ) {
      
      String aeString = nameExpr.getName();
      //ClassData.Param p = classData.lookupCurrentClassMember( aeString, false, false );
      //if ( p == null ) 
      ClassData.Param p = getClassData().getParam( null, aeString, true, true,
                                                   addIfNotFound, complainIfNotFound );
      Parameter< T > parameter =
          (Parameter< T >)( p == null ? null : getClassData().getParameterMap().get( p ) );

      // REVIEW -- Why not check for things other than a member?
      if ( parameter == null ) {
        return null;
      }

    gov.nasa.jpl.ae.event.Expression< T > aeExpression =
        (gov.nasa.jpl.ae.event.Expression< T >)packageExpression( parameter,
                                                                  wrapInFunction,
                                                                  evaluateCall,
                                                                  false,
                                                                  propagate );
//      gov.nasa.jpl.ae.event.Expression< T > aeExpression = null;
//      
//      if ( wrapInFunction ) {
//        aeExpression =
//            new gov.nasa.jpl.ae.event.Expression<T>( new FunctionCall( parameter,
//                                                                    Parameter.class,
//                                                                    "getValue",
//                                                                    new Object[] { true } ) );
//        if ( evaluateCall ) {
//          aeExpression =
//              new gov.nasa.jpl.ae.event.Expression<T>( aeExpression.evaluate( propagate ) );
//        }
//      } else {
//        aeExpression =
//            new gov.nasa.jpl.ae.event.Expression<T>( parameter.getValue( propagate ) );
//      }
      return aeExpression;
    }

  public String nameExprToAe( NameExpr nameExpr, boolean wrapInFunction,
                              boolean evaluateCall, boolean getParameterValue,
                              boolean propagate ) {
    if ( nameExpr == null ) return null;
    if ( !getParameterValue ) return nameExpr.getName();
    String aeString = nameExpr.getName();
    ClassData.Param p =
        getClassData().lookupCurrentClassMember( aeString, false, false );
    if ( p == null ) {
      return aeString;
    }
    if ( wrapInFunction ) {
      aeString =
          "new FunctionCall(" + aeString + ", Parameter.class, \"getValue\", "
              + "new Object[]{ true })";
      if ( evaluateCall ) {
        aeString = "(" + aeString + ").evaluate(" + propagate + ")";
      }
    } else {
      aeString += ".getValue(" + propagate + ")";
    }
    return aeString;
  }

  public static Expression parseExpression( String exprString ) {
    if ( Debug.isOn() ) Debug.outln( "trying to parse Java expression \""
                                     + exprString + "\"" );
    ASTParser parser = new ASTParser( new StringReader( exprString ) );
    Expression expr = null;
    try {
      expr = parser.Expression();
    } catch ( ParseException e ) {
      e.printStackTrace();
    }
    return expr;
  }

  public String[] convertToEventParameterTypeAndConstructorArgs( ClassData.Param p ) {
    return convertToEventParameterTypeAndConstructorArgs( p, getCurrentClass() );
  }

  /**
   * Determines the AE translated parameter type, generic parameter types, and arguments.  
   * @param p
   * @param classOfParameterName
   * @return
   */
  public String[]
      convertToEventParameterTypeAndConstructorArgs( ClassData.Param p,
                                                     String classOfParameterName ) {
    String ret[] = new String[ 3 ];
    if ( p.type == null || p.type.isEmpty() || p.type.equalsIgnoreCase( "null" ) ) {
      ClassData.Param pDef =
          getClassData().lookupMemberByName( classOfParameterName, p.name, true,
                                        true );
      if ( pDef != null ) {
        p.type = pDef.type;
      }
    }
    String type = "Parameter";
    String parameterTypes = p.type;

    if ( p.type.equals( "Generation" ) ) {
      Debug.out( "" );
    }

    // parameterTypes = getFullyQualifiedName( parameterTypes, true );
    parameterTypes = getClassData().getClassNameWithScope( parameterTypes, true );
    String castType = parameterTypes;
    if ( Utils.isNullOrEmpty( p.value ) ) {
      p.value = "null";
    }
    // TODO -- REVIEW -- Why is p.value in args by default, but recognized types
    // do not include p.value?
    String valueArg = javaToAeExpr( p.value, p.type, false, true );
    String typePlaceholder = "!TYPE!";
    // if ( valueArg.equals( "null" )
    // || ( valueArg.startsWith( "new Expression" ) &&
    // valueArg.endsWith( "(null)" ) ) ) {
    valueArg = "(" + typePlaceholder + ")" + valueArg; // replacing !TYPE! later
    // }
    String args = "\"" + p.name + "\", null, " + valueArg + ", this";
    String parameterClass =
        ClassData.typeToParameterType( p.type );
    if ( Utils.isNullOrEmpty( p.type ) ) {
      System.err.println( "Error! creating a field " + p + " of unknown type!" );
    } else if ( !parameterClass.equals( p.type ) ) {
      type = parameterClass;
      if ( !type.equals( "Parameter" ) ) {
        parameterTypes = null;
        if ( !Utils.isNullOrEmpty( castType ) ) {
          args = "\"" + p.name + "\", " + valueArg + ", this";
        }
      }
    } else if ( p.type.toLowerCase().equals( "time" ) ) {
      type = "Timepoint";
      parameterTypes = null;
      // args = "\"" + p.name + "\", this";
      if ( !Utils.isNullOrEmpty( castType ) ) {
        args = "\"" + p.name + "\", " + valueArg + ", this";
      }
    } else if ( p.type.toLowerCase().startsWith( "int" )
                || p.type.toLowerCase().startsWith( "long" ) // TODO -- Need a
                                                             // LongParameter
                || p.type.trim().replaceAll( " ", "" )
                         .equals( "Parameter<Integer>" ) ) {
      type = "IntegerParameter";
      parameterTypes = null; // "Integer";
      // args = "\"" + p.name + "\", this";
      if ( !Utils.isNullOrEmpty( castType ) ) {
        args = "\"" + p.name + "\", " + valueArg + ", this";
      }
    } else if ( p.type.toLowerCase().equals( "double" )
                || p.type.trim().replaceAll( " ", "" )
                         .equals( "Parameter<Double>" ) ) {
      type = "DoubleParameter";
      parameterTypes = null;
      // args = "\"" + p.name + "\", this";
      if ( !Utils.isNullOrEmpty( castType ) ) {
        args = "\"" + p.name + "\", " + valueArg + ", this";
      }
    } else if ( p.type.toLowerCase().equals( "boolean" )
                || p.type.trim().replaceAll( " ", "" )
                         .equals( "Parameter<Boolean>" ) ) {
      type = "BooleanParameter";
      parameterTypes = null;
      // args = "\"" + p.name + "\", this";
      if ( !Utils.isNullOrEmpty( castType ) ) {
        args = "\"" + p.name + "\", " + valueArg + ", this";
      }
    } else if ( p.type.equals( "String" )
                || p.type.trim().replaceAll( " ", "" )
                         .equals( "Parameter<String>" ) ) {
      type = "StringParameter";
      parameterTypes = null;
      // args = "\"" + p.name + "\", this";
      // } else if ( p.type.startsWith( "TimeVaryingMap" ) ) {
      // args = "\"" + p.name + "\", this";
    }
    if ( Utils.isNullOrEmpty( castType ) ) {
      typePlaceholder = "(" + typePlaceholder + ")";
      args = args.replace( typePlaceholder, "" );
    } else {
      args = args.replace( typePlaceholder, castType );
    }

    // HACK -- TODO
    if ( args.contains( ", new FunctionCall" ) ) {
      args += ", true";
    }

    ret[ 0 ] = type;
    ret[ 1 ] = parameterTypes;
    ret[ 2 ] = args;
    return ret;
  }

  /**
   * Determines the AE translated parameter type, generic parameter type, and
   * arguments.
   * <p>
   * 
   * For example, for p = new Param( "anInt", "int", "17" ), the return value is
   * new Object[]{IntegerParameter.class, null, Arrays.asList(new Object[]{new
   * Expression("anInt"), new Expression(17), currentParameterListener )})};
   * 
   * @param p
   * @param classNameOfParameter
   * @return an array containing the Parameter class, any generic parameter
   *         classes, and arguments as expressions.
   */
  public ClassData.PTA
      convertToEventParameterTypeAndConstructorArguments( ClassData.Param p,
                                                          String classNameOfParameter ) {
    
    // TODO -- Remove overlap of this and ClassData.convertToParameterTypeAndConstructorArguments()
    
    String[] result = convertToEventParameterTypeAndConstructorArgs( p, classNameOfParameter );
//    Object ret[] = new Object[ 3 ];
    Class< ? > type = ClassUtils.getClassForName( result[0], null, getClassData().getPackageName(), false );
    Class< ? > parameterType = ClassUtils.getClassForName( result[1], null, getClassData().getPackageName(), false );
    String fooFunc = "fooFunc(" + result[2] + ")";
    Expression expr = parseExpression( fooFunc );
    ArrayList< Object > args = new ArrayList< Object >();
    if ( expr instanceof MethodCallExpr ) {
      List< Expression > argExprs;
      argExprs = ((MethodCallExpr)expr).getArgs();
      for ( Expression arg : argExprs ) {
        args.add( astToAeExpression( arg, false, true, true ) );
      }
    } else {
      Debug.error( true,
                   "Error! convertToEventParameterTypeAndConstructorArguments("
                       + p + ", " + classNameOfParameter + "): Expected "
                       + fooFunc
                       + " to parse as a MethodCallExpr.  Instead, got " + expr );
    }
    ClassData.PTA pta =
        new ClassData.PTA( (Class< ? extends Parameter< ? >>)type,
                           parameterType, args.toArray() );
    return pta;
//    ret[ 0 ] = type;
//    ret[ 1 ] = parameterType;
//    ret[ 2 ] = args;
//    return ret;
  }

  
//  /**
//   * @param className
//   * @param param
//   * @param type
//   * @return a parameter
//   */
//  public < P extends Parameter< ? > > P
//      constructParameter( String className, ClassData.Param param ) {
//    ClassData.PTA ctorArgs =
//        convertToEventParameterTypeAndConstructorArguments( param, className );
//    Class< P > cls = (Class< P >)ctorArgs.paramType;
//    Object[] argumentsA = (Object[])ctorArgs.argArr;
//    ConstructorCall call = new ConstructorCall( null, cls, argumentsA );
//    P parameter = (P)call.evaluate( true );
//    return parameter;
//  }

    /**
     * @param args
     */
    public static void main( String[] args ) {
      // TODO Auto-generated method stub
    }

    /**
     * @return the currentClass
     */
    public String getCurrentClass() {
      if ( getClassData() == null ) return null;
      return getClassData().getCurrentClass();
    }

    /**
     * @return the classData
     */
    public ClassData getClassData() {
        return classData;
    }

    /**
     * @param classData the classData to set
     */
    public void setClassData( ClassData classData ) {
        this.classData = classData;
    }


}
