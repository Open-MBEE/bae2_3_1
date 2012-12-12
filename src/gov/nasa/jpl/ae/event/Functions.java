/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.AbstractRangeDomain;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.Debug;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

/**
 * @author bclement
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
    public SuggestiveFunctionCall( Object object, Method method,
                                   Object[] arguments ) {
      super( object, method, arguments );
    }

    @Override
    public < T > T pickValue( Variable< T > variable ) {
      return pickValueBF( this, variable );
    }
  }
  
  public static class Binary< T , R >
                  extends Expression< R > {
    public SuggestiveFunctionCall functionCall = null;
    public Binary( Expression< T > o1, Expression< T > o2,
                   String functionMethod ) {
      super( new SuggestiveFunctionCall( (Object)null,
                                         getFunctionMethod( functionMethod ),
                                         new Object[]{ o1, o2 } ) );
      functionCall = (SuggestiveFunctionCall)this.expression;
    }
//    public Binary( Expression< T > o1, FunctionCall o2, String functionMethod ) {
//      this( o1, new Expression<T>(o2), functionMethod );
//    }
//    public Binary( FunctionCall o1, Expression< T > o2, String functionMethod ) {
//      this( new Expression<T>(o1), o2, functionMethod );
//    }
//    public Binary( FunctionCall o1, FunctionCall o2, String functionMethod ) {
//      this( new Expression<T>(o1), new Expression<T>(o2), functionMethod );
//    }
    public Binary( Object o1, Object o2, String functionMethod ) {
      this( forceExpression(o1), forceExpression(o2), functionMethod );
//      this( ( o1 instanceof Expression ) ? )
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
  }

  public static class BooleanBinary< T > extends Binary< T, Boolean >
                                         implements Suggester {

    public BooleanBinary( Expression< T > o1, Expression< T > o2,
                          String functionMethod ) {
      super( o1, o2, functionMethod );
    }
    public BooleanBinary( Object o1, Object o2,
                          String functionMethod ) {
      super( o1, o2, functionMethod );
    }
//    public BooleanBinary( Expression< T > o1, FunctionCall o2,
//                          String functionMethod ) {
//      super( o1, o2, functionMethod );
//    }
//    public BooleanBinary( FunctionCall o1, Expression< T > o2,
//                          String functionMethod ) {
//      super( o1, o2, functionMethod );
//    }

    @Override
    public < T1 > T1 pickValue( Variable< T1 > variable ) {
      return pickValueBB(this, variable);
    }
  }
    
  public static class Unary< T , R > extends Expression< R > {
    public FunctionCall functionCall = null;
    public Unary( Expression< T > o, String functionMethod ) {
      super( new FunctionCall( (Object)null,
                           getFunctionMethod( functionMethod ) ) );
      functionCall = (FunctionCall)this.expression;
      Vector< Object > v = new Vector< Object >();
      v.add( o );
      functionCall.arguments = v;
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
  }

  
  // Simple math functions

  public static class Sum< T , R > extends Binary< T, R > {
    public Sum( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "add" );
    }
    public Sum( Object o1, Object c ) {
      super( o1, c, "add" );
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
    }
    public Plus( Object o1, Object c ) {
      super( o1, c );
    }
  }

  public static class Sub< T extends Comparable< ? super T >,
                           R > extends Binary< T, R > {
    public Sub( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "subtract" );
    }
    public Sub( Object o1, Object c ) {
      super( o1, c, "subtract" );
    }
  }
  public static class Minus< T  extends Comparable< ? super T >,
                             R  extends Comparable< ? super R > > extends Sub< T, R > {
    public Minus( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
    public Minus( Object o1, Object c ) {
      super( o1, c );
    }
  }

  public static class Times< T , R > extends Binary< T, R > {
    public Times( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "times" );
    }
    public Times( Object o1, Object c ) {
      super( o1, c, "times" );
    }
  }
  public static class Divide< T , R > extends Binary< T, R > {
    public Divide( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "divide" );
    }
    public Divide( Object o1, Object c ) {
      super( o1, c, "divide" );
    }
  }

  public static < T > java.lang.Number add( Expression< T > o1,
                                            Expression< T > o2 ) {
    if ( o1 == null || o2 == null ) return null;
    T r1 = o1.evaluate( false );
    T r2 = o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    Number result = null;
    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      double rd1 = ( (Double) r1).doubleValue();
      double rd2 = ( (Double) r2).doubleValue();
      // check for overflow
      if ( rd1 >= 0 && Double.MAX_VALUE - rd1 <= rd2 ) {
        result = Double.MAX_VALUE;
      } else if ( rd1 < 0 && -Double.MAX_VALUE - rd1 >= rd2 ) {
        result = -Double.MAX_VALUE;
      } else {
        result = ( (Double)r1 ) + ( (Double)r2 );
      }
    } else if ( r1.getClass().isAssignableFrom( java.lang.Integer.class ) ) {
      int rd1 = ( (Integer) r1).intValue();
      int rd2 = ( (Integer) r2).intValue();
      // check for overflow
      if ( rd1 >= 0 && Integer.MAX_VALUE - rd1 <= rd2 ) {
        result = Integer.MAX_VALUE;
      } else if ( rd1 < 0 && Integer.MIN_VALUE - rd1 >= rd2 ) {
        result = Integer.MIN_VALUE;
      } else {
        result = ( (Integer)r1 ) + ( (Integer)r2 );
      }
    } else {
      try {
        throw new IllegalAccessException();
      } catch ( IllegalAccessException e ) {
        e.printStackTrace();
      }
    }
    if ( Debug.isOn() ) Debug.outln( r1 + " + " + r2 + " = " + result );
    return result;
  }
  
  public static < T > java.lang.Number subtract( Expression< T > o1, 
                                                 Expression< T > o2 ) {
    if ( o1 == null || o2 == null ) return null;
    T r1 = o1.evaluate( false );
    T r2 = o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    Number result = null;
    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      double rd1 = ( (Double) r1).doubleValue();
      double rd2 = ( (Double) r2).doubleValue();
      // check for overflow
      if ( rd1 >= 0.0 && Double.MAX_VALUE - rd1 <= - rd2 ) {
        result = Double.MAX_VALUE;
      } else if ( rd1 <= 0.0 && -Double.MAX_VALUE - rd1 >= - rd2 ) {
        result = -Double.MAX_VALUE;
      } else {
        result = ( (Double)r1 ) - ( (Double)r2 );
      }
    } else if ( r1.getClass().isAssignableFrom( java.lang.Integer.class ) ) {
      int rd1 = ( (Integer) r1).intValue();
      int rd2 = ( (Integer) r2).intValue();
      // check for overflow
      if ( rd1 >= 0 && Integer.MAX_VALUE - rd1 <= - rd2 ) {
        result = Integer.MAX_VALUE;
      } else if ( rd1 < 0 && Integer.MIN_VALUE - rd1 >= - rd2 ) {
        result = Integer.MIN_VALUE;
      } else {
        result = ( (Integer)r1 ) - ( (Integer)r2 );
      }
    } else {
      try {
        throw new IllegalAccessException();
      } catch ( IllegalAccessException e ) {
        e.printStackTrace();
      }
    }
    if ( Debug.isOn() ) Debug.outln( r1 + " - " + r2 + " = " + result );
    return result;
  }

  public static < T > java.lang.Number times( Expression< T > o1,
                                              Expression< T > o2 ) {
    if ( o1 == null || o2 == null ) return null;
    T r1 = o1.evaluate( false );
    T r2 = o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    Number result = null;
    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      double rd1 = ( (Double) r1).doubleValue();
      double rd2 = ( (Double) r2).doubleValue();
      // check for overflow
      if ( Double.MAX_VALUE / rd1 <= rd2 ) {
        result = Double.MAX_VALUE;
      } else if ( -Double.MAX_VALUE / rd1 >= rd2 ) {
        result = -Double.MAX_VALUE;
      } else {
        result = ( (Double)r1 ) * ( (Double)r2 );
      }
    } else if ( r1.getClass().isAssignableFrom( java.lang.Integer.class ) ) {
      int rd1 = ( (Integer) r1).intValue();
      int rd2 = ( (Integer) r2).intValue();
      // check for overflow
      if ( Integer.MAX_VALUE / rd1 <= rd2 ) {
        result = Integer.MAX_VALUE;
      } else if ( Integer.MIN_VALUE / rd1 >= rd2 ) {
        result = Integer.MIN_VALUE;
      } else {
        result = ( (Integer)r1 ) * ( (Integer)r2 );
      }
    } else {
      try {
        throw new IllegalAccessException();
      } catch ( IllegalAccessException e ) {
        e.printStackTrace();
      }
    }
    if ( Debug.isOn() ) Debug.outln( r1 + " * " + r2 + " = " + result );
    return result;
  }
  
  public static < T > java.lang.Number divide( Expression< T > o1,
                                               Expression< T > o2 ) {
    if ( o1 == null || o2 == null ) return null;
    T r1 = o1.evaluate( false );
    T r2 = o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    Number result = null;
    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      double rd1 = ( (Double) r1).doubleValue();
      double rd2 = ( (Double) r2).doubleValue();
      // check for overflow
      if ( rd2 >= 0.0 && rd2 < 1.0 && Double.MAX_VALUE * rd2 <= rd1 ) {
        result = Double.MAX_VALUE;
      } else if ( rd2 < 0.0 && rd2 > -1.0 && -Double.MAX_VALUE * rd2 >= rd1 ) {
        result = -Double.MAX_VALUE;
      } else {
        result = ( (Double)r1 ) / ( (Double)r2 );
      }
    } else if ( r1.getClass().isAssignableFrom( java.lang.Integer.class ) ) {
      int rd1 = ( (Integer) r1).intValue();
      int rd2 = ( (Integer) r2).intValue();
      // check for divide by 0
      int posNeg = ((rd2 < 0) ^ (rd1 < 0)) ? -1 : 1;
      if ( rd2 == 0 ) {
        result = posNeg * Double.MAX_VALUE;
      } else {
        result = ( (Integer)r1 ) / ( (Integer)r2 );
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
  }
  
  public static class Negative<T> extends Unary< T, T > {
    public Negative( Expression< T > o ) {
      super( o, "negative" );
    }
  }
  public static <T> java.lang.Number negative( Expression< T > o ) {
    if ( o == null ) return null;
    T r = o.evaluate( false );
    Number result = null;
    if ( r == null ) return null;
    if ( r.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      result = ((Double)r) * -1.0;
    } else if ( r.getClass().isAssignableFrom( java.lang.Integer.class ) ) {
      result = ((Integer)r) * -1;
    }
    if ( Debug.isOn() ) Debug.outln( "-" + o + " = " + result );
    return result;
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

  public static class LT< T >
                        extends BooleanBinary< T > {
    public LT( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "lessThan" );
    }
    public LT( Object o1, Object o2 ) {
      super( o1, o2, "lessThan" );
    }
  }
  public static class Less< T > extends LT< T > {
    public Less( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
    public Less( Object o1, Object o2 ) {
      super( o1, o2 );
    }
  }

  public static class LTE< T >
                        extends BooleanBinary< T > {
    public LTE( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "lessThanOrEqual" );
    }
    public LTE( Object o1, Object o2 ) {
      super( o1, o2, "lessThanOrEqual" );
    }
  }
  public static class LessEquals< T > extends LTE< T > {
    public LessEquals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
    public LessEquals( Object o1, Object o2 ) {
      super( o1, o2 );
    }
  }

  public static class GT< T extends Comparable< ? super T > >
                        extends BooleanBinary< T > {
    public GT( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "greaterThan" );
    }
    public GT( Object o1, Object o2 ) {
      super( o1, o2, "greaterThan" );
    }
  }
  public static class Greater< T extends Comparable< ? super T > > extends GT< T > {
    public Greater( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
    public Greater( Object o1, Object o2 ) {
      super( o1, o2 );
    }
    public boolean restrictDomains( boolean targetResult ) {
      if ( functionCall.arguments.size() < 2 ) return false;
      Expression<T> e1 = (Expression<T>)functionCall.arguments.get( 0 );
      Expression<T> e2 = (Expression<T>)functionCall.arguments.get( 1 );
      Domain<T> d1 = e1.getDomain(false, null); 
      Domain<T> d2 = e2.getDomain(false, null); 
      if ( d1 instanceof AbstractRangeDomain ) {
        AbstractRangeDomain<T> ard1 = (AbstractRangeDomain< T >)d1; 
        if ( e2.getDomain(false, null) instanceof AbstractRangeDomain ) {
          AbstractRangeDomain<T> ard2 = (AbstractRangeDomain< T >)d2; 
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
                        extends BooleanBinary< T > {
    public GTE( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "greaterThanOrEqual" );
    }
    public GTE( Object o1, Object o2 ) {
      super( o1, o2, "greaterThanOrEqual" );
    }
  }

  public static class GreaterEquals< T extends Comparable< ? super T > > extends
                                                                 GTE< T > {

    public GreaterEquals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
    public GreaterEquals( Object o1, Object o2 ) {
      super( o1, o2 );
    }
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
    boolean b = r1.compareTo( r2 ) < 0;
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
    boolean b = r1.compareTo( r2 ) <= 0;
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
    boolean b = r1.compareTo( r2 ) > 0;
    if ( Debug.isOn() ) Debug.outln( o1 + " > " + o2 + " = " + b );
    return b;
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
    boolean b = r1.compareTo( r2 ) >= 0;
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
  }
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

  public static class Not extends Unary< Boolean, Boolean > {
    public Not( Expression< Boolean > o ) {
      super( o, "not" );
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

  public static <T, T1> T1 pickValueBB( BooleanBinary< T > booleanBinary, Variable< T1 > variable ) {
    T1 newValue = pickValueBF( booleanBinary.functionCall, variable );
    return newValue;
  }
  public static <T1> T1 pickValueBF( FunctionCall f, Variable< T1 > variable ) {
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
            if ( ( (Expression< T1 >)otherArg ).type == Expression.Type.Parameter ) {
              otherVariable =
                  (Variable< T1 >)( (Expression< T1 >)otherArg ).expression;
            }
          }
        }
      }
    }
    Object value = null;
    if ( otherArg != null && found ) {
      if ( f.method.getName().equalsIgnoreCase( "equals" ) ) {
        value = variable.getValue( propagate );
        Object otherValue = null;
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
        try {
          r = (Boolean)f.method.invoke( null, new Expression<T1>(v), otherArg );
        } catch ( IllegalAccessException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch ( IllegalArgumentException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch ( InvocationTargetException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        if ( r ) {
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
