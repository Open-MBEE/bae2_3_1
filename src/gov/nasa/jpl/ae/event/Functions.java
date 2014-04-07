/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.event.Expression.Form;
import gov.nasa.jpl.ae.solver.AbstractFiniteRangeDomain;
import gov.nasa.jpl.ae.solver.AbstractRangeDomain;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.RangeDomain;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;
import java.util.Vector;

/**
 * 
 *         TODO -- Try to define methods with <T> arguments instead of
 *         Expression<T>. FunctionCall may now support both with evaluateArgs().
 * 
 */
public class Functions {

  private static boolean complainAboutBadExpressions = true;
  
  private static Expression forceExpression ( Object o ) {
    return Expression.evaluate( o, Expression.class, false, true );
//    if ( o instanceof Expression ) return (Expression)o;
//    //if ( o instanceof FunctionCall ) return new Expression(o);
//    return new Expression( o );
  }

  // Abstract n-ary functions
  public static class SuggestiveFunctionCall extends FunctionCall implements Suggester {
    public FunctionCall pickFunctionCall = null;
    public FunctionCall reversePickFunctionCall = null;
    public FunctionCall isGrounded = null;
    
    public SuggestiveFunctionCall( //Method isGroundedMethod,
                                   Object object, Method method,
                                   Object[] arguments ) {
      super( object, method, arguments );
      //if 
    }

    public SuggestiveFunctionCall( SuggestiveFunctionCall suggestiveFunctionCall ) {
      super(suggestiveFunctionCall);
      pickFunctionCall = suggestiveFunctionCall.pickFunctionCall;
      reversePickFunctionCall = suggestiveFunctionCall.reversePickFunctionCall;
    }

    public SuggestiveFunctionCall( FunctionCall functionCall ) {
      super(functionCall);
    }

    public SuggestiveFunctionCall( FunctionCall functionCall,
                                   FunctionCall pickFunctionCall,
                                   FunctionCall reversePickFunctionCall ) {
      super(functionCall);
      this.pickFunctionCall = pickFunctionCall;
      this.reversePickFunctionCall = reversePickFunctionCall;
    }

    // TODO -- Need a value argument -- the target return value! Then, this can
    // be renamed "inverse()."
    @Override
    public < T > T pickValue( Variable< T > variable ) {
      return pickValueBF2( this, variable );
    }

    @Override
    public SuggestiveFunctionCall clone() {
      SuggestiveFunctionCall c = new SuggestiveFunctionCall(this);
      return c;
    }

  }
  
  public static class Binary< T , R > extends SuggestiveFunctionCall implements Suggester {
    //public SuggestiveFunctionCall functionCall = null;
    //public SuggestiveFunctionCall pickFunctionCall = null;
    //public SuggestiveFunctionCall reversePickFunctionCall = null;

    public Binary( Variable< T > o1, Expression< T > o2,
                   String functionMethod ) {
      super( (Object)null,
                                         getFunctionMethod( functionMethod ),
                                         new Object[]{ o1, o2 } );
      //functionCall = this;//(SuggestiveFunctionCall)this.expression;
    }

    public Binary( Expression< T > o1, Expression< T > o2,
                   String functionMethod ) {
      super( //new SuggestiveFunctionCall( 
             (Object)null,
                                         getFunctionMethod( functionMethod ),
                                         new Object[]{ o1, o2 } 
             //)
             );
      //functionCall = this;//(SuggestiveFunctionCall)this.expression;
    }

    public Binary( Expression< T > o1, Expression< T > o2,
                   String functionMethod,
                   String pickFunctionMethod1,
                   String pickFunctionMethod2 ) {
      this( o1, o2, functionMethod );
      //functionCall.
      pickFunctionCall =
          new FunctionCall( (Object)null,
                            getFunctionMethod( pickFunctionMethod1 ),
                            //functionCall.
                            getArguments().toArray() );
      Vector< Object > args = new Vector<Object>( //functionCall.
          getArguments() );
      Collections.reverse( args );
      //functionCall.
      reversePickFunctionCall =
          new FunctionCall( (Object)null,
                            getFunctionMethod( pickFunctionMethod2 ),
                            args.toArray() );
    }

    public Binary( Object o1, Object o2, String functionMethod,
                   String pickFunctionMethod1, String pickFunctionMethod2 ) {
      this( forceExpression( o1 ), forceExpression( o2 ),
            functionMethod,
            pickFunctionMethod1, pickFunctionMethod2 );
      // this( ( o1 instanceof Expression ) ? )
    }

    public Binary( Object o1, Object o2, String functionMethod ) {
      this( forceExpression( o1 ), forceExpression( o2 ), functionMethod );
    }

    private static Method getFunctionMethod( String functionMethod ) {
      Method m = null;
      try {
        m = Functions.class.getMethod( functionMethod, 
                                       Expression.class,
                                       Expression.class );
      } catch ( SecurityException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return m;
    }
    
    @Override
    public < T1 > T1 pickValue( Variable< T1 > variable ) {
      return pickValueBF2( this,//functionCall, 
                           variable );
    }
  }

  public static class BooleanBinary< T > extends Binary< T, Boolean >
                                         implements Suggester {

    public BooleanBinary( Expression< T > o1, Expression< T > o2,
                          String functionMethod ) {
      super( o1, o2, functionMethod );
    }
    public BooleanBinary( Expression< T > o1, Expression< T > o2,
                          String functionMethod,
                          String pickFunctionMethod1, String pickFunctionMethod2 ) {
      super( o1, o2, functionMethod, pickFunctionMethod1, pickFunctionMethod2 );
    }
    public BooleanBinary( Object o1, Object o2, String functionMethod ) {
      super( o1, o2, functionMethod );
    }
    public BooleanBinary( Object o1, Object o2,
                          String functionMethod,
                          String pickFunctionMethod1, String pickFunctionMethod2 ) {
      super( o1, o2, functionMethod, pickFunctionMethod1, pickFunctionMethod2 );
    }
////    public BooleanBinary( Expression< T > o1, FunctionCall o2,
////                          String functionMethod ) {
////      super( o1, o2, functionMethod );
////    }
////    public BooleanBinary( FunctionCall o1, Expression< T > o2,
////                          String functionMethod ) {
////      super( o1, o2, functionMethod );
////    }
//
//    @Override
//    public < T1 > T1 pickValue( Variable< T1 > variable ) {
//      return pickValueBB2(this, variable, functionMethod1,
//                          functionMethod2 );
//    }
  }
    
  public abstract static class Unary< T , R > extends Expression< R > implements Suggester {
    public SuggestiveFunctionCall functionCall = null;

    public Unary( Expression< T > o, String functionMethod ) {
      super( new SuggestiveFunctionCall( (Object)null,
                                         getFunctionMethod( functionMethod ),
                                         new Object[]{ o } ) );
      functionCall = (SuggestiveFunctionCall)this.expression;
//      Vector< Object > v = new Vector< Object >();
//      v.add( o );
//      functionCall.arguments = v;
    }
    public Unary( Object o, String functionMethod ) {
      this( forceExpression( o ), functionMethod );
    }
    private static Method getFunctionMethod( String functionMethod ) {
      Method m = null;
      try {
        m = Functions.class.getMethod( functionMethod, 
                                       Expression.class );
      } catch ( SecurityException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return m;
    }
//    @Override
//    public < T > T pickValue( Variable< T > variable ) {
//      // TODO Auto-generated method stub
//      Debug.error(true, "Need to redefine Unary.pickValue(v)!" );
//      return null;
//    }
  }

  public static class Conditional< T > extends SuggestiveFunctionCall implements Suggester {
    public Conditional( Expression<Boolean> condition, Expression< T > thenExpr, Expression< T > elseExpr ) {
      super( null, getIfThenElseMethod(), new Object[] {condition, thenExpr, elseExpr} );
    }

    public static Method getIfThenElseMethod() {
      Method m = null;
      try {
        m = Functions.class.getMethod( "ifThenElse", 
                                       Expression.class,
                                       Expression.class,
                                       Expression.class );
      } catch ( SecurityException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return m;
    }
  }

  public static < T > T ifThenElse( boolean b, T thenT, T elseT ) {
    if ( b ) return thenT;
    return elseT;
  }
  
  /**
   * Evaluate if-then-else conditional function. If the condition evaluates to
   * null, it is interpreted as "false."
   * 
   * @param conditionExpr
   * @param thenExpr
   * @param elseExpr
   * @return the evaluation of thenExpr if conditionExpr evaluates to true, else
   *         the evaluation of elseExpr
   */
  public static < T > T ifThenElse( Expression< Boolean > conditionExpr,
                                    Expression< T > thenExpr,
                                    Expression< T > elseExpr ) {
    if ( conditionExpr == null && elseExpr == null ) return null;
    Boolean b = (conditionExpr == null ? null : conditionExpr.evaluate( false ) );
    T thenT = (thenExpr == null ? null : thenExpr.evaluate( false ) );
    T elseT = (elseExpr == null ? null : elseExpr.evaluate( false ) );
    if ( b == null || !b.booleanValue() ) return elseT;
    return thenT;
  }
  
  
  // Simple math functions

  public static class Sum< T, R > extends Binary< T, R > {
    public Sum( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "add" );
      setMonotonic( true );
    }
    public Sum( Object o1, Object c ) {
      super( o1, c, "add" );
      setMonotonic( true );
    }
  }
  public static class Add< T , R > extends Sum< T, R > {
    public Add( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
    public Add( Object o1, Object c ) {
      super( o1, c );
    }
  }

  public static class Plus< T , R > extends Sum< T, R > {
    public Plus( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
      //functionCall.
    }
    public Plus( Object o1, Object c ) {
      super( o1, c );
    }
  }

  public static class Sub< T extends Comparable< ? super T >,
                           R > extends Binary< T, R > {
    public Sub( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "subtract" );
      //functionCall.
      setMonotonic( true );
    }
    public Sub( Object o1, Object c ) {
      super( o1, c, "subtract" );
      //functionCall.
      setMonotonic( true );
    }
  }
  public static class Minus< T  extends Comparable< ? super T >,
                             R  extends Comparable< ? super R > > extends Sub< T, R > {
    public Minus( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
      //functionCall.
      setMonotonic( true );
    }
    public Minus( Object o1, Object c ) {
      super( o1, c );
      //functionCall.
      setMonotonic( true );
    }
  }

  public static class Times< T , R > extends Binary< T, R > {
    public Times( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "times" );
      //functionCall.
      setMonotonic( true );
    }
    public Times( Object o1, Object c ) {
      super( o1, c, "times" );
      //functionCall.
      setMonotonic( true );
    }
  }
  public static class Divide< T , R > extends Binary< T, R > {
    public Divide( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "divide" );
      //functionCall.
      setMonotonic( true );
    }
    public Divide( Object o1, Object c ) {
      super( o1, c, "divide" );
      //functionCall.
      setMonotonic( true );
    }
  }

  // TODO -- Create a plus(Double, Double) that does the overflow and call from
  // here. add(Expr, Expr) should call this fcn.
  public static <V1, V2> V1 plus( V1 o1, V2 o2 ) {
    if ( o1 == null || o2 == null ) return null;
      Object result = null;
    if ( o1 instanceof String || o2 instanceof String ) {
        String s = "" + o1 + o2;
        //String s = MoreToString.Helper.toString( o1 ) + MoreToString.Helper.toString( o2 ); 
    } else {
      TimeVaryingMap<?> map = null;
      try {
        map = Expression.evaluate( o1, TimeVaryingMap.class, false );
      } catch ( ClassCastException e ) {
        //ignore
      }
      if ( map != null ) result = plus( map, o2 );
      else {
        try {
        map = Expression.evaluate( o2, TimeVaryingMap.class, false );
        } catch ( ClassCastException e ) {
          //ignore
        }
        if ( map != null ) result = plus( o1, map );
        else {
    Number n1 = Expression.evaluate( o1, Number.class, false );
    Number n2 = Expression.evaluate( o2, Number.class, false );
    if ( n1 != null && n2 != null ) {
      if ( n1 instanceof Double || n2 instanceof Double ) {
        result = ((Double)n1.doubleValue()) + ((Double)n2.doubleValue());
      } else if ( n1 instanceof Float || n2 instanceof Float ) {
        result = ((Float)n1.floatValue()) + ((Float)n2.floatValue());
      } else if ( n1 instanceof Long || n2 instanceof Long ) {
        result = ((Long)n1.longValue()) + ((Long)n2.longValue());
      } else if ( n1 instanceof Integer || n2 instanceof Integer ) {
        result = ((Integer)n1.intValue()) + ((Integer)n2.intValue());
      } else {
        result = ((Integer)n1.intValue()) + ((Integer)n2.intValue());
      }
    }
    }
      }
    }
    try {
      if ( o1 != null ) {
        Object x = (V1)Expression.evaluate( result, o1.getClass(), false );
        if ( x == null ) x = result;
        return (V1)x;
      }
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static <V1, V2> V1 times( V1 o1, V2 o2 ) {
    if ( o1 == null || o2 == null ) return null;
    Object result = null;
    TimeVaryingMap<?> map = null;
    try {
      map = Expression.evaluate( o1, TimeVaryingMap.class, false );
    } catch ( ClassCastException e ) {
      //ignore
    }
    if ( map != null ) result = times( map, o2 );
    else {
      try {
      map = Expression.evaluate( o2, TimeVaryingMap.class, false );
      } catch ( ClassCastException e ) {
        //ignore
      }
      if ( map != null ) result = times( o1, map );
      else {
    Number n1 = Expression.evaluate( o1, Number.class, false );
    Number n2 = Expression.evaluate( o2, Number.class, false );
    if ( n1 != null && n2 != null ) {
      // TODO -- other types, like BigDecimal
      if ( n1 instanceof Double || n2 instanceof Double ) {
        result = ((Double)n1.doubleValue()) * ((Double)n2.doubleValue());
      } else if ( n1 instanceof Float || n2 instanceof Float ) {
        result = ((Float)n1.floatValue()) * ((Float)n2.floatValue());
      } else if ( n1 instanceof Long || n2 instanceof Long ) {
        result = ((Long)n1.longValue()) * ((Long)n2.longValue());
      } else if ( n1 instanceof Integer || n2 instanceof Integer ) {
        result = ((Integer)n1.intValue()) * ((Integer)n2.intValue());
      } else {
        result = ((Integer)n1.intValue()) * ((Integer)n2.intValue());
      }
    }
      }
    }
    try {
      if ( o1 != null ) {
        Object x = (V1)Expression.evaluate( result, o1.getClass(), false );
        if ( x == null ) x = result;
        return (V1)x;
      }
      return (V1)result;
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static <V1, V2> V1 divide( V1 o1, V2 o2 ) {
    Object result = null;
    TimeVaryingMap<?> map = null;
    try {
      map = Expression.evaluate( o1, TimeVaryingMap.class, false );
    } catch ( ClassCastException e ) {
      //ignore
    }
    if ( map != null ) result = divide( map, o2 );
    else {
      try {
      map = Expression.evaluate( o2, TimeVaryingMap.class, false );
      } catch ( ClassCastException e ) {
        //ignore
      }
      if ( map != null ) result = divide( o1, map );
      else {
    Number n1 = Expression.evaluate( o1, Number.class, false );
    Number n2 = Expression.evaluate( o2, Number.class, false );
    if ( n1 != null && n2 != null ) {
      // TODO -- other types, like BigDecimal
      if ( n1 instanceof Double || n2 instanceof Double ) {
        result = ((Double)n1.doubleValue()) / ((Double)n2.doubleValue());
      } else if ( n1 instanceof Float || n2 instanceof Float ) {
        result = ((Float)n1.floatValue()) / ((Float)n2.floatValue());
      } else if ( n1 instanceof Long || n2 instanceof Long ) {
        result = ((Long)n1.longValue()) / ((Long)n2.longValue());
      } else if ( n1 instanceof Integer || n2 instanceof Integer ) {
        result = ((Integer)n1.intValue()) / ((Integer)n2.intValue());
      } else {
        result = ((Integer)n1.intValue()) / ((Integer)n2.intValue());
      }
    }
      }
    }
    try {
      if ( o1 != null ) {
        Object x = (V1)Expression.evaluate( result, o1.getClass(), false );
        if ( x == null ) x = result;
        return (V1)x;
      }
      return (V1)result;
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static <V1, V2> V1 minus( V1 o1, V2 o2 ) {
    return plus( o1, times( -1, o2 ) );
  }
  
  public static < T, TT > T add( Expression< T > o1,
                                  Expression< TT > o2 ) {
    if ( o1 == null || o2 == null ) return null;
    T r1 = o1.evaluate( false );
    TT r2 = o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    return plus(r1,r2);
/*    Object result = null;
    if ( r1.getClass().isAssignableFrom( java.lang.String.class ) ||
         r2.getClass().isAssignableFrom( java.lang.String.class ) ) {
      String s = "" + r1 + r2;
      result = s;
    } else if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ||
                r2.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      double rd1 = ClassUtils.castNumber( (Number)r1, Double.class ).doubleValue();
      double rd2 = ClassUtils.castNumber( (Number)r2, Double.class ).doubleValue();
      // check for overflow
      if ( rd1 >= 0 && Double.MAX_VALUE - rd1 <= rd2 ) {
        result = Double.MAX_VALUE;
      } else if ( rd1 < 0 && -Double.MAX_VALUE - rd1 >= rd2 ) {
        result = -Double.MAX_VALUE;
      } else {
        result = ( (Double)rd1 ) + ( (Double)rd2 );
      }
    } else if ( r1.getClass().isAssignableFrom( java.lang.Float.class ) ||
                r2.getClass().isAssignableFrom( java.lang.Float.class ) ) {
      float rd1 = ClassUtils.castNumber( (Number)r1, Float.class ).floatValue();
      float rd2 = ClassUtils.castNumber( (Number)r2, Float.class ).floatValue();
      // check for overflow
      if ( rd1 >= 0 && Float.MAX_VALUE - rd1 <= rd2 ) {
        result = Float.MAX_VALUE;
      } else if ( rd1 < 0 && -Float.MAX_VALUE - rd1 >= rd2 ) {
         result = -Float.MAX_VALUE;
      } else {
        result = ( (Float)rd1 ) + ( (Float)rd2 );
      }
    } else if ( java.lang.Integer.class.isAssignableFrom( r1.getClass() ) && 
                java.lang.Integer.class.isAssignableFrom( r2.getClass() ) ) {
      int rd1 = ClassUtils.castNumber( (Number)r1, Integer.class ).intValue();
      int rd2 = ClassUtils.castNumber( (Number)r2, Integer.class ).intValue();
      // check for overflow
      if ( rd1 >= 0 && Integer.MAX_VALUE - rd1 <= rd2 ) {
        result = Integer.MAX_VALUE;
      } else if ( rd1 < 0 && Integer.MIN_VALUE - rd1 >= rd2 ) {
        result = Integer.MIN_VALUE;
      } else {
        result = ( (Integer)rd1 ) + ( (Integer)rd2 );
      }
    } else {
      result = (Number)plus( r1, r2 );
//      try {
//        throw new IllegalAccessException();
//      } catch ( IllegalAccessException e ) {
//        e.printStackTrace();
//      }
    }
    if ( Debug.isOn() ) Debug.outln( r1 + " + " + r2 + " = " + result );
    return result;
*/  }
  
  public static < T, TT > T subtract( Expression< T > o1, 
                                      Expression< TT > o2 ) {
    if ( o1 == null || o2 == null ) return null;
    T r1 = o1.evaluate( false );
    TT r2 = o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    return minus( r1, r2 );  // REVIEW -- should this be subtract(r1,r2)?
/*    Number result = null;
    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ||
         r2.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      double rd1 = ClassUtils.castNumber( (Number)r1, Double.class ).doubleValue();
      double rd2 = ClassUtils.castNumber( (Number)r2, Double.class ).doubleValue();
      // check for overflow
      if ( rd1 >= 0.0 && Double.MAX_VALUE - rd1 <= - rd2 ) {
        result = Double.MAX_VALUE;
      } else if ( rd1 <= 0.0 && -Double.MAX_VALUE - rd1 >= - rd2 ) {
        result = -Double.MAX_VALUE;
      } else {
        result = ( (Double)rd1 ) - ( (Double)rd2 );
      }
    } else if ( r1.getClass().isAssignableFrom( java.lang.Float.class ) ||
                r2.getClass().isAssignableFrom( java.lang.Float.class ) ) {
       float rd1 = ClassUtils.castNumber( (Number)r1, Float.class ).floatValue();
       float rd2 = ClassUtils.castNumber( (Number)r2, Float.class ).floatValue();
       // check for overflow
       if ( rd1 >= 0.0 && Double.MAX_VALUE - rd1 <= - rd2 ) {
         result = Float.MAX_VALUE;
       } else if ( rd1 <= 0.0 && -Float.MAX_VALUE - rd1 >= - rd2 ) {
         result = -Double.MAX_VALUE;
       } else {
         result = ( (Float)rd1 ) - ( (Float)rd2 );
       }
    } else if ( java.lang.Integer.class.isAssignableFrom( r1.getClass() ) && 
                java.lang.Integer.class.isAssignableFrom( r2.getClass() )) {
//    } else if ( r1.getClass().isAssignableFrom( java.lang.Integer.class ) &&
//                r2.getClass().isAssignableFrom( java.lang.Integer.class ) ) {
      int rd1 = ClassUtils.castNumber( (Number)r1, Integer.class ).intValue();
      int rd2 = ClassUtils.castNumber( (Number)r2, Integer.class ).intValue();
      // check for overflow
      if ( rd1 >= 0 && Integer.MAX_VALUE - rd1 <= - rd2 ) {
        result = Integer.MAX_VALUE;
      } else if ( rd1 < 0 && Integer.MIN_VALUE - rd1 >= - rd2 ) {
        result = Integer.MIN_VALUE;
      } else {
        result = ( (Integer)rd1 ) - ( (Integer)rd2 );
      }
    } else {
      result = (Number)minus( r1, r2 );
//    try {
//      throw new IllegalAccessException();
//    } catch ( IllegalAccessException e ) {
//      e.printStackTrace();
//    }
    }
    if ( Debug.isOn() ) Debug.outln( r1 + " - " + r2 + " = " + result );
    return result;
*/  }

  public static < T, TT > T times( Expression< T > o1,
                                                  Expression< TT > o2 ) {
    if ( o1 == null || o2 == null ) return null;
    T r1 = o1.evaluate( false );
    TT r2 = o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    return times(r1, r2);
/*    Number result = null;
    if ( r1.getClass().isAssignableFrom( TimeVaryingMap.class ) ) {
    } else if ( r2.getClass().isAssignableFrom( TimeVaryingMap.class ) ) {
    } if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ||
         r2.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      double rd1 = ClassUtils.castNumber( (Number)r1, Double.class ).doubleValue();
      double rd2 = ClassUtils.castNumber( (Number)r2, Double.class ).doubleValue();
      // check for overflow
      boolean signsEqual = (rd1 > 0) == (rd2 > 0);
      double ad1 = Math.abs( rd1 );
      double ad2 = Math.abs( rd2 ); //if they're the same sign, take abs...
      if ( rd1 != 0 && rd2 != 0){ //REVIEW - zeroes?
        if ( Double.MAX_VALUE / ad1 <= ad2 ) result = Double.MAX_VALUE * ( signsEqual? 1 : -1);
        else result = ( (Double)rd1 ) * ( (Double)rd2 );
      }
      else  result = ( (Double)rd1 ) * ( (Double)rd2 );
    } else if ( r1.getClass().isAssignableFrom( java.lang.Float.class ) ||
                r2.getClass().isAssignableFrom( java.lang.Float.class ) ) {
      float rd1 = ClassUtils.castNumber( (Number)r1, Float.class ).floatValue();
      float rd2 = ClassUtils.castNumber( (Number)r2, Float.class ).floatValue();
      // check for overflow
      boolean signsEqual = (rd1 > 0) == (rd2 > 0);
      float ad1 = Math.abs( rd1 );
      float ad2 = Math.abs( rd2 ); //if they're the same sign, take abs...
      if ( rd1 != 0 && rd2 != 0){ //REVIEW - zeroes?
        if ( Float.MAX_VALUE / ad1 <= ad2 ) result = Float.MAX_VALUE * ( signsEqual? 1 : -1);
        else result = ( (Float)rd1 ) * ( (Float)rd2 );
      }
      else  result = ( (Float)rd1 ) * ( (Float)rd2 );
    } else if ( java.lang.Integer.class.isAssignableFrom( r1.getClass() ) && 
                java.lang.Integer.class.isAssignableFrom( r2.getClass() ) ) {
      int rd1 = ClassUtils.castNumber( (Number)r1, Integer.class ).intValue();
      int rd2 = ClassUtils.castNumber( (Number)r2, Integer.class ).intValue();
      // check for overflow
      boolean signsEqual = (rd1 > 0) == (rd2 > 0);
      int ad1 = Math.abs( rd1 );
      int ad2 = Math.abs( rd2 ); //if they're the same sign, take abs...
      if ( rd1 != 0 && rd2 != 0){ //REVIEW - zeroes?
        if ( Integer.MAX_VALUE / ad1 <= ad2 ) result = Integer.MAX_VALUE * ( signsEqual? 1 : -1);
        else result = ( (Integer)rd1 ) * ( (Integer)rd2 );
      }
      else  result = ( (Integer)rd1 ) * ( (Integer)rd2 );
    } else {
      try {
        throw new IllegalAccessException();
      } catch ( IllegalAccessException e ) {
        e.printStackTrace();
      }
    }
    if ( Debug.isOn() ) Debug.outln( r1 + " * " + r2 + " = " + result );
    return result;
*/  }
  
  public static < T, TT > T divide( Expression< T > o1,
                                    Expression< TT > o2 ) {
    if ( o1 == null || o2 == null ) return null;
    T r1 = o1.evaluate( false );
    TT r2 = o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    return divide(r1, r2);
/*    Number result = null;
    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ||
         r2.getClass().isAssignableFrom( java.lang.Double.class )) {
      double rd1 = ClassUtils.castNumber( (Number)r1, Double.class ).doubleValue();
      double rd2 = ClassUtils.castNumber( (Number)r2, Double.class ).doubleValue();
      // check for overflow
      if ( rd2 >= 0.0 && rd2 < 1.0 && Double.MAX_VALUE * rd2 <= rd1 ) {
        result = Double.MAX_VALUE;
      } else if ( rd2 < 0.0 && rd2 > -1.0 && -Double.MAX_VALUE * rd2 >= rd1 ) {
        result = -Double.MAX_VALUE;
      } else {
        result = ( (Double)rd1 ) / ( (Double)rd2 );
      }
    } else if ( r1.getClass().isAssignableFrom( java.lang.Float.class ) ||
                r2.getClass().isAssignableFrom( java.lang.Float.class )) {
      float rd1 = ClassUtils.castNumber( (Number)r1, Float.class ).floatValue();
      float rd2 = ClassUtils.castNumber( (Number)r2, Float.class ).floatValue();
      // check for overflow
      if ( rd2 >= 0.0 && rd2 < 1.0 && Float.MAX_VALUE * rd2 <= rd1 ) {
        result = Float.MAX_VALUE;
      } else if ( rd2 < 0.0 && rd2 > -1.0 && -Float.MAX_VALUE * rd2 >= rd1 ) {
        result = -Float.MAX_VALUE;
      } else {
        result = ( (Float)rd1 ) / ( (Float)rd2 );
      }
    } else if ( java.lang.Integer.class.isAssignableFrom( r1.getClass() ) && 
                java.lang.Integer.class.isAssignableFrom( r2.getClass() )) {
      int rd1 = ClassUtils.castNumber( (Number)r1, Integer.class ).intValue();
      int rd2 = ClassUtils.castNumber( (Number)r2, Integer.class ).intValue();
      // check for divide by 0
      int posNeg = ((rd2 < 0) ^ (rd1 < 0)) ? -1 : 1;
      if ( rd2 == 0 ) {
        result = posNeg * Double.MAX_VALUE;
      } else {
        result = ( (Integer)rd1 ) / ( (Integer)rd2 );
      }
    } else {
      try {
        throw new IllegalAccessException();
      } catch ( IllegalAccessException e ) {
        e.printStackTrace();
      }
    }
    if ( Debug.isOn() ) Debug.outln( r1 + " / " + r2 + " = " + result );
    return result;
*/  }
  
  public static class Negative<T> extends Unary< T, T > {
    public Negative( Expression< T > o ) {
      super( o, "negative" );
      functionCall.setMonotonic( true );
    }
    
    // TODO -- handle cast errors
    @Override
    public < T > T pickValue( Variable< T > variable ) {
      Object arg = ((FunctionCall)this.expression).getArguments().get( 0 );
      if ( arg == variable ) {
        return (T)negative( variable );
      }
      return pickValueE( variable, arg );
//      else if ( arg instanceof Suggester ) {
//        return (T)(negative(((Number)((Suggester)arg).pickValue( variable ))));
//      } else if ( arg instanceof Expression && ((Expression)arg).expression instanceof Suggester ) {
//        return (T)(negative((Number)((Suggester)((Expression)arg).expression).pickValue( variable )));
//      }
//      return null;
    }
  }

  public static <T> java.lang.Number negative( Variable< T > v ) {
    T r = v.getValue( false );
    if ( r instanceof Number ) {
      return negative( (Number)r );
    }
    return null;
  }

  public static Number negative( Number n ) {
    if ( n == null ) return null;
    Number result = null;
    if ( n.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      result = ((Double)n) * -1.0;
    } else if ( n.getClass().isAssignableFrom( java.lang.Integer.class ) ) {
      result = ((Integer)n) * -1;
    }
    return result;
  }
  
  public static <T> java.lang.Number negative( Expression< T > o ) {
    if ( o == null ) return null;
    T r = o.evaluate( false );
    if ( r instanceof Number ) {
      return negative( (Number)r );
    }
    return null;
  }


  // Inequality functions

  public static class EQ< T >
                        extends BooleanBinary< T > {
    public EQ( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "equals" );
    }
    public EQ( Object o1, Object o2 ) {
      super( o1, o2, "equals" );
    }
/*
    @Override
    public < T1 > T1 pickValue( Variable< T1 > variable ) {
      T1 newValue = null;
      FunctionCall f = (FunctionCall)this.expression;
      Variable< T1 > otherArg = null;
      boolean found = false;
      for ( Object arg : f.arguments ) {
        if ( variable == arg ) {
          found = true;
        } else if ( arg instanceof Expression
                    && ( ( (Expression)arg ).expression == variable ) ) {
          found = true;
        } else if ( arg instanceof Expression
                    && !( ( (Expression)arg ).expression instanceof Parameter )
                    && variable instanceof Parameter ) {
          if ( ( (Expression< T1 >)arg ).hasParameter( (Parameter< T1 >)variable,
                                                       false, null ) ) {
            newValue = variable.pickRandomValue();
          }
        } else if ( arg instanceof Variable ) {
          if ( otherArg == null ) {
            otherArg = (Variable< T1 >)arg;
          }
        }
      }
      if ( otherArg != null && found ) {
        if ( Debug.isOn() ) Debug.outln( "suggesting value " + otherArg.getValue() + " to make "
                     + getClass().getSimpleName() + " true" );
        return otherArg.getValue();
      }
      if ( newValue == null ) {
        if ( Debug.isOn() ) Debug.outln( "suggesting same value " + variable.getValue() + " for "
            + getClass().getSimpleName() + " true" );
        return variable.getValue();
      }
      if ( Debug.isOn() ) Debug.outln( "suggesting value " + newValue + " for "
                   + getClass().getSimpleName() + " true" );
      return newValue;
    }
    
*/
  }
    
  public static class Equals< T > extends EQ< T > {
    public Equals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
    public Equals( Object o1, Object o2 ) {
      super( o1, o2 );
    }
  }

  public static class NEQ< T > 
                        extends BooleanBinary< T > {
    public NEQ( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "notEquals" );
    }
    public NEQ( Object o1, Object o2 ) {
      super( o1, o2, "notEquals" );
    }

//    @Override
//    public < T1 > T1 pickValue( Variable< T1 > variable ) {
//      return pickValueBB( this, variable );
//    }
/*      T1 newValue = null;
      FunctionCall f = (FunctionCall)this.expression;
      Variable< T1 > otherArg = null;
      boolean found = false;
      for ( Object arg : f.arguments ) {
        if ( variable == arg ) {
          found = true;
        } else if ( arg instanceof Expression
                    && ( ( (Expression)arg ).expression == variable ) ) {
          found = true;
        } else if ( arg instanceof Expression
                    && !( ( (Expression)arg ).expression instanceof Parameter )
                    && variable instanceof Parameter ) {
          if ( ( (Expression< T1 >)arg ).hasParameter( (Parameter< T1 >)variable,
                                                       false ) ) {
            newValue = variable.pickRandomValue();
          }
        } else if ( arg instanceof Variable ) {
          if ( otherArg == null ) {
            otherArg = (Variable< T1 >)arg;
          }
        }
      }
      if ( otherArg != null && found ) {
        newValue = null;
        for ( int i=0; i < 5; ++i ) {
          T1 v = variable.pickRandomValue();
          if ( v != otherArg.getValue() ) {
            if ( newValue == null ) {
              newValue = v;
            } else if ( otherArg.getDomain() == null || otherArg.getDomain().contains( v ) ) {
              return v;
            }
          }
        }
      }
      if ( newValue == null ) {
        return variable.getValue();
      }
      return newValue;
    }
    */
  }

  public static class NotEquals< T > extends NEQ< T > {
    public NotEquals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }

    public NotEquals( Object o1, Object o2 ) {
      super( o1, o2 );
    }
  }

  public static class LT< T > extends BooleanBinary< T > {
    public LT( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "lessThan" );
      // functionCall.
      setMonotonic( true );
    }

    public LT( Object o1, Object o2 ) {
      super( o1, o2, "lessThan" );
      // functionCall.
      setMonotonic( true );
    }
  }

  public static class Less< T > extends LT< T > {
    public Less( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
      // functionCall.
      setMonotonic( true );
    }

    public Less( Object o1, Object o2 ) {
      super( o1, o2 );
      // functionCall.
      setMonotonic( true );
    }
  }

  public static class LTE< T > extends BooleanBinary< T > {
    public LTE( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "lessThanOrEqual" );
      // functionCall.
      setMonotonic( true );
    }

    public LTE( Object o1, Object o2 ) {
      super( o1, o2, "lessThanOrEqual" );
      // functionCall.
      setMonotonic( true );
    }
  }

  public static class LessEquals< T > extends LTE< T > {
    public LessEquals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
      // functionCall.
      setMonotonic( true );
    }

    public LessEquals( Object o1, Object o2 ) {
      super( o1, o2 );
      // functionCall.
      setMonotonic( true );
    }
  }

  public static class GT< T extends Comparable< ? super T > > extends
                                                              BooleanBinary< T > {
    public GT( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "greaterThan", "pickGreaterThan", "pickLessThanOrEqual" );
      // functionCall.
      setMonotonic( true );
    }

    public GT( Object o1, Object o2 ) {
      super( o1, o2, "greaterThan", "pickGreaterThan", "pickLessThanOrEqual" );
      // functionCall.
      setMonotonic( true );
    }
    //    @Override
//    public < T1 > T1 pickValue( Variable< T1 > variable ) {
//      Vector< Object > args = this.//functionCall.
//                                   getArguments();
//      if ( args.size() < 2 ) return null;
//      Object arg1 = args.get( 0 );
//      Object arg2 = args.get( 1 );
//      Expression< T1 > o1 = null;
//      Expression< T1 > o2 = null;
//      if ( arg1 instanceof Expression ) {
//        o1 = (Expression< T1 >)arg1;
//      }
//      if ( arg2 instanceof Expression ) {
//        o2 = (Expression< T1 >)arg2;
//      }
//      if ( o1 == null && variable == null && o2 ) return Functions.pickGreaterThan( Comparable<T1>) o2 );
//      if ( args.get( 0 ).equals( variable ) ) {
//        return Functions.pickLessThanOrEqual( o1, o2 );
//      }
//      return pickValueBB(this, variable, getClass().getMethod( "pickGreaterThan", parameterTypes ) );
//    }
  }

  public static class Greater< T extends Comparable< ? super T > > extends
                                                                   GT< T > {
    public Greater( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
      // functionCall.
      setMonotonic( true );
    }

    public Greater( Object o1, Object o2 ) {
      super( o1, o2 );
      // functionCall.
      setMonotonic( true );
    }

    public boolean restrictDomains( boolean targetResult ) {
      if ( // functionCall.
      arguments.size() < 2 ) return false;
      Expression< T > e1 = (Expression< T >)// functionCall.
          arguments.get( 0 );
      Expression< T > e2 = (Expression< T >)// functionCall.
          arguments.get( 1 );
      Domain< T > d1 = e1.getDomain( false, null );
      Domain< T > d2 = e2.getDomain( false, null );
      if ( d1 instanceof AbstractRangeDomain ) {
        AbstractRangeDomain< T > ard1 = (AbstractRangeDomain< T >)d1;
        if ( e2.getDomain( false, null ) instanceof AbstractRangeDomain ) {
          AbstractRangeDomain< T > ard2 = (AbstractRangeDomain< T >)d2;
          if ( targetResult == true ) {
            if ( ard1.lessEquals( ard1.getLowerBound(), ard2.getLowerBound() ) ) {
              ard1.setLowerBound( ard2.getLowerBound() );
              ard1.excludeLowerBound();
            }
            if ( ard2.greater( ard2.getUpperBound(), ard1.getUpperBound() ) ) {
              ard2.setUpperBound( ard1.getUpperBound() );
              ard2.excludeUpperBound();
            }
          }
        }
      }
      return false;
    }
  }

  public static class GTE< T extends Comparable< ? super T > >
                                                               extends
                                                               BooleanBinary< T > {
    public GTE( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "greaterThanOrEqual", "pickGreaterThanOrEqual", "pickLessThan" );
      // functionCall.
      setMonotonic( true );
    }

    public GTE( Object o1, Object o2 ) {
      super( o1, o2, "greaterThanOrEqual", "pickGreaterThanOrEqual", "pickLessThan" );
      // functionCall.
      setMonotonic( true );
    }
  }

  public static class GreaterEquals< T extends Comparable< ? super T > >
                                                                         extends
                                                                         GTE< T > {

    public GreaterEquals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }

    public GreaterEquals( Object o1, Object o2 ) {
      super( o1, o2 );
    }
  }

  public static class DoesThereExist< T > extends BooleanBinary< T > {

    public DoesThereExist( Variable< T > variable,
    // Domain<T> d,
                           Expression< Boolean > o ) {
      super( variable, o, "thereExists" );
      // functionCall.
      setMonotonic( Functions.isMonotonic( o ) );
    }
  }  

  public static class ForAll< T > extends BooleanBinary< T > {
    // Collection<?> quantifiedVariables = null;
    // public ForAll( Collection< Variable<?> > variables,
    // Expression< Boolean > o ) {
    // super( variables, o, "forAll" );
    // }
    public ForAll( Variable< T > variable,
    // Domain<T> d,
                   Expression< Boolean > o ) {
      super( variable, o, "forAll" );
      // functionCall.
      setMonotonic( Functions.isMonotonic( o ) );
    }
  }

  public static <T extends Comparable<T>> Boolean forAll( Variable<T> variable,
                                                          Expression< Boolean > o ) {
    if ( variable == null ) return null; // TODO -- error?
    if ( o == null ) return true;
    if ( variable instanceof Parameter &&
         !o.hasParameter( (Parameter< T >)variable, true, null ) ) {
      return o.evaluate( false );
    }
    Domain<T> d = variable.getDomain();
    Boolean b = null;
    if ( d == null || d.size() == 0 ) {
      b = o.evaluate( false );
    }
    RangeDomain< T > rd = null;
    if ( b == null && d instanceof RangeDomain ) {
      rd = (RangeDomain<T>)d;
    }
    if ( b == null && isMonotonic( o ) && d instanceof RangeDomain ) {
      variable.setValue( rd.getLowerBound() );
      if ( !o.evaluate( true ) ) b = false; 
      variable.setValue( rd.getUpperBound() );
      if ( !o.evaluate( true ) ) b = false;
      b = true;
    }
    if ( b == null && d.size() > 0 && d instanceof AbstractFiniteRangeDomain ) {//!d.isInfinite() ) {
      AbstractFiniteRangeDomain<T> afrd = (AbstractFiniteRangeDomain<T>)d;
      for ( long i=0; i<d.size(); ++i ) {
        variable.setValue( afrd.getNthValue( i ) );
        if ( !o.evaluate( true ) ) b = false; 
      }
      b = true;
    }
    if ( Debug.isOn() ) Debug.outln( "forAll(" + variable + " in " + d + ", " + o + " = " + b );
    return b;
  }


  public static boolean isMonotonic( Expression< ? > o ) {
    if ( o.form == Form.Function ) {
      if ( ((FunctionCall)o.expression).isMonotonic() ) {
        return true;
      }
    }
    return false;
  }
  
  
  private static boolean expressionsAreOkay( boolean complain,
                                             Expression< ? >... exprs ) {
    if ( exprs == null ) return true;
    for ( Expression<?> expr : exprs ) {
      if ( expr == null || !expr.isGrounded( false, null ) ) {
        if ( complain ) {
          System.err.println( "Expression is not grounded! " + expr );
          
        }
        return false;
      }
    }
    return true;
  }
  
  public static < T extends Comparable< ? super T > > Boolean
      lessThan( Expression< T > o1, Expression< T > o2 ) {
//    if ( !expressionsAreOkay( complainAboutBadExpressions, o1, o2 ) ) {
////    if ( !o1.isGrounded() || !o2.isGrounded() ) {      
//        return false;
////      }
////      return greaterThan( o2, o1 );
//    }
    if ( o1 == o2 ) return false;
    if ( o1 == null || o2 == null ) return (o2 != null);
    T r1 = o1.evaluate( false );
    T r2 = o2.evaluate( false );
    if ( r1 == r2 ) return false;
    if ( r1 == null || r2 == null ) return (r2 != null);
    boolean b;
    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ||
         r2.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      Number n1 = Expression.evaluate( o1, Number.class, false );
      Number n2 = Expression.evaluate( o2, Number.class, false );      
      b = n1.doubleValue() < n2.doubleValue();
    } else {
      b = r1.compareTo( r2 ) < 0;
    }
    if ( Debug.isOn() ) Debug.outln( o1 + " < " + o2 + " = " + b );
    return b;
  }

  public static < T extends Comparable< ? super T > > Boolean
      lessThanOrEqual( Expression< T > o1, Expression< T > o2 ) {
//    if ( !expressionsAreOkay( complainAboutBadExpressions, o1, o2 ) ) {
////    if ( !o1.isGrounded() || !o2.isGrounded() ) {
//        return false;
////      }
////      return greaterThanOrEqual( o2, o1 );
//    }
    if ( o1 == o2 ) return true;
    if ( o1 == null || o2 == null ) return (o1 == null);
    T r1 = o1.evaluate( false );
    T r2 = o2.evaluate( false );
    if ( r1 == r2 ) return true;
    if ( r1 == null || r2 == null ) return (r1 == null);
    boolean b;
    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ||
         r2.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      Number n1 = Expression.evaluate( o1, Number.class, false );
      Number n2 = Expression.evaluate( o2, Number.class, false );      
      b = n1.doubleValue() <= n2.doubleValue();
    } else {
      b = r1.compareTo( r2 ) <= 0;
    }
    if ( Debug.isOn() ) Debug.outln( o1 + " <= " + o2 + " = " + b );
    return b;
  }

  public static < T extends Comparable< ? super T > > Boolean
      greaterThan( Expression< T > o1, Expression< T > o2 ) {
//    if ( !expressionsAreOkay( complainAboutBadExpressions, o1, o2 ) ) {
////    if ( !o1.isGrounded() || !o2.isGrounded() ) {
//        return false;  // TODO -- REVIEW -- throw exception?
////      }
////      return lessThan( o2, o1 );
//    }
    if ( o1 == o2 ) return false;
    if ( o1 == null || o2 == null ) return (o1 != null);
    T r1 = o1.evaluate( false );
    T r2 = o2.evaluate( false );
    if ( r1 == r2 ) return false;
    if ( r1 == null || r2 == null ) return (r1 != null);
    boolean b;
    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ||
         r2.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      Number n1 = Expression.evaluate( o1, Number.class, false );
      Number n2 = Expression.evaluate( o2, Number.class, false );      
      b = n1.doubleValue() > n2.doubleValue();
    } else {
      b = r1.compareTo( r2 ) > 0;
    }
    if ( Debug.isOn() ) Debug.outln( o1 + " > " + o2 + " = " + b );
    return b;
  }
  
  // HERE!!! TODO
  public static < T > T
      pickGreaterThan( Expression< T > o1, Expression< T > o2 ) {
    return pickGreater( o1, o2, false );
  }

  public static < T > T pickGreaterThanOrEqual( Expression< T > o1,
                                                Expression< T > o2 ) {
    return pickGreater( o1, o2, true );
  }

  public static < T > T pickGreaterThan( Expression< T > o ) {
    return pickGreater( o, false );
  }

  public static < T > T pickGreaterThanOrEqual( Expression< T > o ) {
    return pickGreater( o, true );
  }

  public static < T > T pickGreaterThan( Expression< T > o,
                                         AbstractRangeDomain< T > domain ) {
    return pickGreater( o, domain, false );
  }

  public static
      < T >
      T
      pickGreaterThanOrEqual( Expression< T > o, AbstractRangeDomain< T > domain ) {
    return pickGreater( o, domain, true );
  }

  public static < T > T pickGreater( Expression< T > o,
                                     AbstractRangeDomain< T > domain,
                                     boolean orEquals ) {
    T t = null;
    if ( o == null ) return null;
    T r = o.evaluate( false );
    if ( r == null ) return null;
    t = domain.pickRandomValueGreater( r, orEquals );
    return t;
  }

  public static < T > T pickGreater( Expression< T > o, boolean orEquals ) {
    if ( o == null ) return null;
    Domain< T > d = o.getDomain( false, null );
    if ( d != null && d instanceof AbstractRangeDomain ) {
      Domain< T > dfd = d.getDefaultDomain();
      if ( dfd instanceof AbstractRangeDomain ) {
        return pickGreater( o, (AbstractRangeDomain< T >)dfd, orEquals );
      }
    }
    return null;
  }

  public static < T > T pickGreater( Expression< T > o1, Expression< T > o2,
                                     boolean orEquals ) {
    Domain< T > d = o1.getDomain( false, null );
    if ( d instanceof AbstractRangeDomain ) {
      AbstractRangeDomain< T > ard = (AbstractRangeDomain< T >)d;
      return pickGreater( o2, ard, orEquals );
    }
    return pickGreater( o2, orEquals );
  }

  public static < T > T pickLessThan( Expression< T > o1, Expression< T > o2 ) {
    return pickLess( o1, o2, false );
  }

  public static < T > T pickLessThanOrEqual( Expression< T > o1,
                                             Expression< T > o2 ) {
    return pickLess( o1, o2, true );
  }

  public static < T > T pickLessThan( Expression< T > o ) {
    return pickLess( o, false );
  }

  public static < T > T pickLessThanOrEqual( Expression< T > o ) {
    return pickLess( o, true );
  }

  public static < T > T pickLessThan( Expression< T > o,
                                      AbstractRangeDomain< T > domain ) {
    return pickLess( o, domain, false );
  }

  public static < T > T pickLessThanOrEqual( Expression< T > o,
                                             AbstractRangeDomain< T > domain ) {
    return pickLess( o, domain, true );
  }

  public static < T > T pickLess( Expression< T > o,
                                  AbstractRangeDomain< T > domain,
                                  boolean orEquals ) {
    T t = null;
    if ( o == null ) return null;
    T r = o.evaluate( false );
    if ( r == null ) return null;
    t = domain.pickRandomValueLess( r, orEquals );
    return t;
  }

  public static < T > T pickLess( Expression< T > o, boolean orEquals ) {
    if ( o == null ) return null;
    Domain< T > d = o.getDomain( false, null );
    if ( d != null && d instanceof AbstractRangeDomain ) {
      Domain< T > dfd = d.getDefaultDomain();
      if ( dfd instanceof AbstractRangeDomain ) {
        return pickLess( o, (AbstractRangeDomain< T >)dfd, orEquals );
      }
    }
    return null;
  }

  public static < T > T pickLess( Expression< T > o1, Expression< T > o2,
                                  boolean orEquals ) {
    Domain< T > d = o1.getDomain( false, null );
    if ( d instanceof AbstractRangeDomain ) {
      AbstractRangeDomain< T > ard = (AbstractRangeDomain< T >)d;
      return pickLess( o2, ard, orEquals );
    }
    return pickLess( o2, orEquals );
  }

  public static < T extends Comparable< ? super T > > Boolean
      greaterThanOrEqual( Expression< T > o1, Expression< T > o2 ) {
//    if ( !expressionsAreOkay( complainAboutBadExpressions, o1, o2 ) ) {
////    if ( !o1.isGrounded() || !o2.isGrounded() ) {
//        return false;
////      }
////      return lessThanOrEqual( o2, o1 );
//    }
    if ( o1 == o2 ) return true;
    if ( o1 == null || o2 == null ) return (o2 == null);
    T r1 = o1.evaluate( false );
    T r2 = o2.evaluate( false );
    if ( r1 == r2 ) return true;
    if ( r1 == null || r2 == null ) return (r2 == null);
    boolean b;
    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ||
         r2.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      Number n1 = Expression.evaluate( o1, Number.class, false );
      Number n2 = Expression.evaluate( o2, Number.class, false );      
      b = n1.doubleValue() >= n2.doubleValue();
    } else {
      b = r1.compareTo( r2 ) >= 0;
    }
    if ( Debug.isOn() ) Debug.outln( o1 + " >= " + o2 + " = " + b );
    return b;
  }

  public static < T > Boolean
      equals( Expression< T > o1, Expression< T > o2 ) {
    if ((o1 ==null) && (o2 == null)){
      Debug.outln( "" );
    }
    if ( o1 == o2 ) return true;
    //if ( o1 == null || o2 == null ) return false;
    T r1 = o1 == null ? null : o1.evaluate( false );
    T r2 = o2 == null ? null : o2.evaluate( false );
    if ((r1 ==null) && (r2 == null)){
      Debug.outln( "" );
    }
    if ( r1 == r2 ) return true;
    if ( r1 == null || r2 == null ) 
      return false;
    boolean b = true;
    if ( r1 instanceof Comparable ) {
      if ( r1 instanceof Parameter && !( r2 instanceof Parameter ) ) {
        return ((Parameter<T>)r1).valueEquals( r2 );
      }
      if ( r2 instanceof Parameter && !( r1 instanceof Parameter ) ) {
        return ((Parameter<T>)r2).valueEquals( r1 );
      }
      b = ( (Comparable<T>)r1 ).compareTo( r2 ) == 0;  
    } else {
      b = r1.equals( r2 );
    }
    if ( Debug.isOn() ) Debug.outln( o1 + " == " + o2 + " = " + b );
    return b;
  }
  public static < T > Boolean
      notEquals( Expression< T > o1, Expression< T > o2 ) {
//    if ( o1 == o2 ) return false;
//    if ( o1 == null || o2 == null ) return true;
//    T r1 = o1.evaluate( false );
//    T r2 = o2.evaluate( false );
//    if ( r1 == r2 ) return false;
//    if ( r1 == null || r2 == null ) return true;
    boolean b = !equals( o1, o2 );
//    if ( r1 instanceof Comparable ) {
//      b = ( (Comparable<T>)r1 ).compareTo( r2 ) != 0;
//    } else {
//      b = !r1.equals( r2 );
//    }
    if ( Debug.isOn() ) Debug.outln( o1 + " != " + o2 + " = " + b );
    return b;
  }

  // Logic functions
  
  public static class And extends BooleanBinary< Boolean > {
    public And( Expression< Boolean > o1, Expression< Boolean > o2 ) {
      super( o1, o2, "and" );
    }
    public And( Expression< Boolean > o1, FunctionCall o2 ) {
      super( o1, o2, "and" );
    }
    public And(  FunctionCall o2, Expression< Boolean > o1 ) {
      super( o1, o2, "and" );
    }
    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.event.Expression#isGrounded(boolean, java.util.Set)
     */
    @Override
    public boolean isGrounded(boolean deep, Set< Groundable > seen) {
      return evaluate( false ) != null;
    }
  }
  // REVIEW -- Should a propagate flag be added?  Currently false.
  public static Boolean and(Expression<Boolean> o1, Expression<Boolean> o2) {
    if ( o1 == null && o2 == null ) return null;
    Boolean r1 = (o1 == null ? null : o1.evaluate( false ));
    Boolean r2 = (o2 == null ? null : o2.evaluate( false ));
    if ( ( r1 != null && r1 == false ) || ( r2 != null && r2 == false ) ) return false;
    if ( r1 == null || r2 == null ) return null;
    boolean b = r1 && r2;
    if ( Debug.isOn() ) Debug.outln( o1 + " && " + o2 + " = " + b );
    return b;
  }

  public static class Or extends BooleanBinary< Boolean > {
    public Or( Expression< Boolean > o1, Expression< Boolean > o2 ) {
      super( o1, o2, "or" );
    }
    public Or( Expression< Boolean > o1, FunctionCall o2 ) {
      super( o1, o2, "or" );
    }
    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.event.Expression#isGrounded(boolean, java.util.Set)
     */
    @Override
    public boolean isGrounded(boolean deep, Set< Groundable > seen) {
      return evaluate( false ) != null;
    }
  }
  public static Boolean or( Expression< Boolean > o1, Expression< Boolean > o2 ) {
    if ( o1 == null && o2 == null ) return null;
    Boolean r1 = (o1 == null ? null : o1.evaluate( false ));
    Boolean r2 = (o2 == null ? null : o2.evaluate( false ));
    if ( ( r1 != null && r1 == true ) || ( r2 != null && r2 == true ) ) return true;
    if ( r1 == null || r2 == null ) return null;
    boolean b = r1 || r2;
    if ( Debug.isOn() ) Debug.outln( o1 + " || " + o2 + " = " + b );
    return b;
  }

  public static class Xor extends BooleanBinary< Boolean > {
    public Xor( Expression< Boolean > o1, Expression< Boolean > o2 ) {
      super( o1, o2, "xor" );
    }
    public Xor( Expression< Boolean > o1, FunctionCall o2 ) {
      super( o1, o2, "xor" );
    }
  }
  public static Boolean
      xor( Expression< Boolean > o1, Expression< Boolean > o2 ) {
    if ( o1 == null || o2 == null ) return null;
    Boolean r1 = o1.evaluate( false );
    Boolean r2 = o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    boolean b = ( r1 ^ r2 );
    if ( Debug.isOn() ) Debug.outln( o1 + " ^ " + o2 + " = " + b );
    return b;
  }

  public static class Not extends Unary< Boolean, Boolean > implements Suggester {
    public Not( Expression< Boolean > o ) {
      super( o, "not" );
    }

    @Override
    public < T > T pickValue( Variable< T > variable ) {
      Object arg = ((FunctionCall)this.expression).getArguments().get( 0 );
      if ( arg == variable ) {
        return (T)(Boolean)false;
      }
      return pickValueE( variable, arg );
//      else if ( arg instanceof Suggester ) {
//        return (T)(Boolean)(!((Boolean)((Suggester)arg).pickValue( variable )));
//      }
//      return null;
    }
  }

  public static Boolean not( Expression< Boolean > o ) {
    if ( o == null ) return null;
    Boolean r = o.evaluate( false );
    if ( r == null ) return null;
    boolean b = !r;
    if ( Debug.isOn() ) Debug.outln( "!" + o + " = " + b );
    return b;
  }

  // TimeVaryingMap functions
//  public static < T > TimeVaryingMap< T > times( Object o1,
//                                                 Object o2 ) {
//    if ( o1 == null || o2 == null ) return null;
//    Number n = Expression.evaluate( o1, Number.class, false );
//    if ( n != null ) return times( n, o2 );
//    TimeVaryingMap< ? extends Number > tvm =
//        Expression.evaluate( o, TimeVaryingMap.class, false );
//    if ( tvm != null ) return times( tv, tvm );
//    return null;
//  }
  public static < T > TimeVaryingMap< T > times( Object o,
                                                 TimeVaryingMap< T > tv ) {
    return times(tv, o);
  }
  public static < T > TimeVaryingMap< T > times( TimeVaryingMap< T > tv,
                                                 Object o ) {
    if ( tv == null || o == null ) return null;
    Number n = Expression.evaluate( o, Number.class, false );
    if ( n != null ) return tv.times( n );
    TimeVaryingMap< ? extends Number > tvm =
        Expression.evaluate( o, TimeVaryingMap.class, false );
    if ( tvm != null ) return times( tv, tvm );
    return null;
  }
  public static < T, TT extends Number > TimeVaryingMap< T > times( TimeVaryingMap< T > tv1,
                                                                    TimeVaryingMap< TT > tv2 ) {
    return TimeVaryingMap.times( tv1, tv2 );
  }

  public static < T > TimeVaryingMap< T > divide( TimeVaryingMap< T > tv,
                                                  Object o ) {
    if ( tv == null || o == null ) return null;
    Number n = Expression.evaluate( o, Number.class, false );
    if ( n != null ) return tv.divide( n );
    TimeVaryingMap< ? extends Number > tvm =
        Expression.evaluate( o, TimeVaryingMap.class, false );
    if ( tvm != null ) return divide( tv, tvm );
    return null;
  }
  public static < T, TT extends Number > TimeVaryingMap< T > divide( TimeVaryingMap< T > tv1,
                                                                     TimeVaryingMap< TT > tv2 ) {
    return TimeVaryingMap.dividedBy( tv1, tv2 );
  }

  
  public static < T > TimeVaryingMap< T > plus( Object o,
                                                TimeVaryingMap< T > tv ) {
    return plus( tv, o );
  }
  public static < T > TimeVaryingMap< T > plus( TimeVaryingMap< T > tv,
                                                Object o ) {
    if ( tv == null || o == null ) return null;
    Number n = Expression.evaluate( o, Number.class, false );
    if ( n != null ) return tv.plus( n );
    TimeVaryingMap< ? extends Number > tvm =
        Expression.evaluate( o, TimeVaryingMap.class, false );
    if ( tvm != null ) return plus( tv, tvm );
    return null;
  }   
  public static < T, TT extends Number > TimeVaryingMap< T > plus( TimeVaryingMap< T > tv1,
                                                                   TimeVaryingMap< TT > tv2 ) {
     return TimeVaryingMap.plus( tv1, tv2 );
   }
  
  public static < T > TimeVaryingMap< T > minus( Object o,
                                                 TimeVaryingMap< T > tv ) {
    return minus( tv, o );
  }
  public static < T > TimeVaryingMap< T > minus( TimeVaryingMap< T > tv,
                                                 Object o ) {
    if ( tv == null || o == null ) return null;
    Number n = Expression.evaluate( o, Number.class, false );
    if ( n != null ) return tv.minus( n );
    TimeVaryingMap< ? extends Number > tvm =
        Expression.evaluate( o, TimeVaryingMap.class, false );
    if ( tvm != null ) return minus( tv, tvm );
    return null;
  }   
  public static < T, TT extends Number > TimeVaryingMap< T > minus( TimeVaryingMap< T > tv1,
                                                                    TimeVaryingMap< TT > tv2 ) {
     return TimeVaryingMap.minus( tv1, tv2 );
   }
  
  public static <T1> T1 pickValueE( Variable< T1 > variable,
                                        Object object ) {
    if ( object instanceof Suggester ) {
      return (T1)((Suggester)object).pickValue( variable );
    } else if ( object instanceof Expression && 
                ((Expression<?>)object).expression instanceof Suggester ) {
      return (T1)((Suggester)((Expression<?>)object).expression).pickValue( variable );
    }
    return null;
  }

  protected static <T, T1> T1 pickValueBB2( BooleanBinary< T > booleanBinary,
                                            Variable< T1 > variable ) {
    T1 newValue = pickValueBF2( variable, booleanBinary.//functionCall.
                                pickFunctionCall,
                                booleanBinary.//functionCall.
                                reversePickFunctionCall );
    return newValue;
  }
  protected static <T1> T1 pickValueBF2( Variable< T1 > variable,
                                         FunctionCall pickFunctionCall ) {
    return (T1)pickFunctionCall.evaluate( false );
  }
  protected static <T1> T1 pickValueBF2( SuggestiveFunctionCall functionCall,
                                         Variable< T1 > variable ) {
    T1 newValue = pickValueBF2( variable, functionCall.pickFunctionCall,
                                functionCall.reversePickFunctionCall );
    return newValue;
  }
  protected static <T1> T1 pickValueBF2( Variable< T1 > variable,
                                         FunctionCall pickFunctionCall,
                                         FunctionCall reversePickFunctionCall ) {
//  public < T1 > T1 pickValue( Variable< T1 > variable ) {
    Vector< Object > args = pickFunctionCall.getArguments();
    if ( args.size() < 2 ) return null;
    Object arg1 = args.get( 0 );
    Object arg2 = args.get( 1 );
    Expression< T1 > o1 = null;
    Expression< T1 > o2 = null;
    if ( arg1 instanceof Expression ) {
      o1 = (Expression< T1 >)arg1;
    }
    if ( arg2 instanceof Expression ) {
      o2 = (Expression< T1 >)arg2;
    }
    //if ( o1 == null && variable == null ) return (T1)reverseFunctionCall.evaluate( false );
    Variable<?> v1 = Expression.evaluate( o1, Variable.class, false, true );
    if ( v1 == variable ) return (T1)pickFunctionCall.evaluate( false );
    Variable<?> v2 = Expression.evaluate( o2, Variable.class, false, true );
    if ( v2 == variable ) return (T1)reversePickFunctionCall.evaluate( false );
    if ( v1.equals( variable ) ) return (T1)pickFunctionCall.evaluate( false );
    if ( v2.equals( variable ) ) return (T1)reversePickFunctionCall.evaluate( false );
    
//    Debug.error( true,
//                 "Error! Functions.pickValue(variable, fcn, reverseFcn) could not find variable "
//                     + variable + " in arguments of "
//                     + pickFunctionCall.toString( Debug.isOn(), true, null ) );
    return null; //(T1)functionCall.evaluate( false );
    //return pickValueBB(this, variable, getClass().getMethod( "pickGreaterThan", parameterTypes ) );
  }

  // delete this function
  private static <T, T1> T1 pickValueBB( BooleanBinary< T > booleanBinary,
                                         Variable< T1 > variable ) {
    T1 newValue = pickValueBF( booleanBinary,//.functionCall, 
                               variable );
    return newValue;
  }
  // delete this function
  private static <T1> T1 pickValueBF( FunctionCall f, Variable< T1 > variable ) {
    boolean propagate = true;
    T1 newValue = null;
//    FunctionCall f = (FunctionCall)booleanBinary.expression;
    //Variable< T1 > otherArg = null;
    Object otherArg = null;
    Variable<T1> otherVariable = null;
    boolean found = false;
    for ( Object arg : f.arguments ) {
      if ( variable == arg ) {
        found = true;
      } else if ( arg instanceof Expression
                  && ( ( (Expression< ? >)arg ).expression == variable ) ) {
        found = true;
      } else if ( arg instanceof Expression
                  && !( ( (Expression< ? >)arg ).expression instanceof Parameter )
                  && variable instanceof Parameter ) {
        if ( ( (Expression< ? >)arg ).hasParameter( (Parameter< T1 >)variable,
                                                     false, null ) ) {
          newValue = variable.pickRandomValue();
        }
      } else {
//        if ( arg instanceof Variable ) {
        if ( otherArg == null ) {
          otherArg = arg;// (Variable< T1 >)arg;
          if ( otherArg instanceof Variable ) {
            otherVariable = (Variable< T1 >)otherArg;
          } else if ( otherArg instanceof Expression ) {
            if ( ( (Expression< T1 >)otherArg ).form == Expression.Form.Parameter ) {
              otherVariable =
                  (Variable< T1 >)( (Expression< T1 >)otherArg ).expression;
            }
          }
        }
      }
    }
    Object value = null;
    Object otherValue = null;
//    if ( found ) {
//      if ( otherArg == null ) {
//        otherValue = 
//      }
//    }
    if ( otherArg != null && found ) {
      if ( f.method.getName().equalsIgnoreCase( "equals" ) ) {
        value = variable.getValue( propagate );
        if ( otherArg instanceof Variable ) {
          otherValue = ( (Variable<?>)otherArg ).getValue( propagate );
        } else if ( otherArg instanceof Expression ) {
          otherValue = ( (Expression<?>)otherArg ).evaluate( propagate );
        }
        if ( otherValue instanceof Variable ) {
          if ( value != null
               && !value.getClass().isInstance( otherValue )
               && value.getClass()
                       .isInstance( ( (Variable< ? >)otherValue ).getValue( propagate ) ) ) {
            otherValue = ( (Variable< ? >)otherValue ).getValue( propagate );
          }
        }
        if ( Debug.isOn() ) Debug.outln( "suggesting other arg value " + otherValue + " to make "
                     + f.getClass().getSimpleName() + " true" );
        return (T1)otherValue;
      }
      newValue = null;
      Boolean r = false;
      for ( int i=0; i < 5; ++i ) {
        T1 v = variable.pickRandomValue();
        Pair< Boolean, Object > p = null;
        try {
          p = ClassUtils.runMethod( false, (Object)null, f.method,
                                    new Expression< T1 >( v ), otherArg );
          r = (Boolean)p.second;
        } catch ( IllegalArgumentException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        if ( p != null && p.first && r ) {
          if ( newValue == null ) {
            newValue = v;
          } else if ( otherVariable != null && otherVariable.getDomain() == null || otherVariable.getDomain().contains( v ) ) {
            if ( Debug.isOn() ) Debug.outln( "suggesting value " + v + " to try make "
                         + f.getClass().getSimpleName() + " true" );
            return v;
          }
        }
      }
    }
    if ( value == null ) {
      value = variable.getValue( propagate );
    }
    if ( newValue == null ) {
      if ( Debug.isOn() ) Debug.outln( "suggesting same value " + value + " for "
          + f.getClass().getSimpleName() + " true" );
      return (T1)value;
    }
    if ( Debug.isOn() ) Debug.outln( "suggesting value " + newValue + " for "
        + f.getClass().getSimpleName() + " true" );
    return newValue;
  }

  
}
