/**
 * 
 */
package gov.nasa.jpl.ae.event;

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

  // Abstract n-ary functions

  public static class Binary< T , R >
                  extends Expression< R > {
    public Binary( Expression< T > o1, Expression< T > o2,
                   String functionMethod ) {
      super( new FunctionCall( (Object)null,
                           getFunctionMethod( functionMethod ) ) );
      FunctionCall f = (FunctionCall)this.expression;
      Vector< Object > v = new Vector< Object >();
      v.add( o1 );
      v.add( o2 );
      f.arguments = v;
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

  public static class BooleanBinary< T > extends Binary< T, Boolean > implements Suggester {

    public BooleanBinary( Expression< T > o1, Expression< T > o2,
                          String functionMethod ) {
      super( o1, o2, functionMethod );
    }

    @Override
    public < T1 > T1 pickValue( Variable< T1 > variable ) {
      return pickValueBB(this, variable);
    }
  }
    
  public static class Unary< T , R >
     extends Expression< R > {
    public Unary( Expression< T > o, String functionMethod ) {
      super( new FunctionCall( (Object)null,
                           getFunctionMethod( functionMethod ) ) );
      FunctionCall f = (FunctionCall)this.expression;
      Vector< Object > v = new Vector< Object >();
      v.add( o );
      f.arguments = v;
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
  }
  public static class Add< T , R > extends Sum< T, R > {
    public Add( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
  }

  public static class Plus< T , R > extends Sum< T, R > {
    public Plus( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
  }

  public static class Sub< T extends Comparable< ? super T >,
                           R > extends Binary< T, R > {
    public Sub( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "subtract" );
    }
  }
  public static class Minus< T  extends Comparable< ? super T >,
                             R  extends Comparable< ? super R > > extends Sub< T, R > {
    public Minus( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
  }
  
  public static < T > java.lang.Number add( Expression< T > o1,
                                            Expression< T > o2 ) {
    T r1 = o1.evaluate( false );
    T r2 = o2.evaluate( false );
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
    Debug.outln( r1 + " + " + r2 + " = " + result );
    return result;
  }
  
  public static < T > java.lang.Number subtract( Expression< T > o1, 
                                                 Expression< T > o2 ) {
    T r1 = o1.evaluate(false);
    T r2 = o2.evaluate(false);
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
    Debug.outln( r1 + " - " + r2 + " = " + result );
    return result;
  }

  // Inequality functions

  public static class EQ< T >
                        extends BooleanBinary< T > {
    public EQ( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "equals" );
    }

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
        Debug.outln( "suggesting value " + otherArg.getValue() + " to make "
                     + getClass().getSimpleName() + " true" );
        return otherArg.getValue();
      }
      if ( newValue == null ) {
        Debug.outln( "suggesting same value " + variable.getValue() + " for "
            + getClass().getSimpleName() + " true" );
        return variable.getValue();
      }
      Debug.outln( "suggesting value " + newValue + " for "
                   + getClass().getSimpleName() + " true" );
      return newValue;
    }
    
  }
    
  public static class Equals< T > extends EQ< T > {
    public Equals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
  }

  public static class NEQ< T > 
                        extends BooleanBinary< T > {
    public NEQ( Expression< T > o1, Expression< T > o2 ) {
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
  }

  public static class LT< T >
                        extends BooleanBinary< T > {
    public LT( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "lessThan" );
    }
  }
  public static class Less< T > extends LT< T > {
    public Less( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
  }

  public static class LTE< T >
                        extends BooleanBinary< T > {
    public LTE( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "lessThanOrEqual" );
    }
  }
  public static class LessEquals< T > extends LTE< T > {
    public LessEquals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
  }

  public static class GT< T extends Comparable< ? super T > >
                        extends BooleanBinary< T > {
    public GT( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "greaterThan" );
    }
  }
  public static class Greater< T extends Comparable< ? super T > > extends GT< T > {
    public Greater( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
  }

  public static class GTE< T extends Comparable< ? super T > >
                        extends BooleanBinary< T > {
    public GTE( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "greaterThanOrEqual" );
    }
  }

  public static class GreaterEquals< T extends Comparable< ? super T > > extends
                                                                 GTE< T > {

    public GreaterEquals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
  }


  public static < T extends Comparable< ? super T > > Boolean
      lessThan( Expression< T > o1, Expression< T > o2 ) {
    boolean b = o1.evaluate(false).compareTo( o2.evaluate(false) ) < 0;
    Debug.outln( o1 + " < " + o2 + " = " + b );
    return b;
  }

  public static < T extends Comparable< ? super T > > Boolean
      lessThanOrEqual( Expression< T > o1, Expression< T > o2 ) {
    boolean b = o1.evaluate(false).compareTo( o2.evaluate(false) ) <= 0;
    Debug.outln( o1 + " <= " + o2 + " = " + b );
    return b;
  }

  public static < T extends Comparable< ? super T > > Boolean
      greaterThan( Expression< T > o1, Expression< T > o2 ) {
    boolean b = o1.evaluate(false).compareTo( o2.evaluate(false) ) > 0;
    Debug.outln( o1 + " > " + o2 + " = " + b );
    return b;
  }

  public static < T extends Comparable< ? super T > > Boolean
      greaterThanOrEqual( Expression< T > o1, Expression< T > o2 ) {
    boolean b = o1.evaluate(false).compareTo( o2.evaluate(false) ) >= 0;
    Debug.outln( o1 + " >= " + o2 + " = " + b );
    return b;
  }

  public static < T extends Comparable< ? super T > > Boolean
      equals( Expression< T > o1, Expression< T > o2 ) {
    boolean b = o1.evaluate(false).compareTo( o2.evaluate(false) ) == 0;
    Debug.outln( o1 + " == " + o2 + " = " + b );
    return b;
  }
  public static < T > Boolean
      notEquals( Expression< T > o1, Expression< T > o2 ) {
    T r1 = o1.evaluate(false);
    T r2 = o2.evaluate(false);
    boolean b = false;
    if ( r1 instanceof Comparable ) {
      b = ( (Comparable<T>)r1 ).compareTo( r2 ) != 0;
    } else {
      b = r1.equals( r2 );
    }
    Debug.outln( o1 + " != " + o2 + " = " + b );
    return b;
  }

  // Logic functions
  
  public static class And extends BooleanBinary< Boolean > {
    public And( Expression< Boolean > o1, Expression< Boolean > o2 ) {
      super( o1, o2, "and" );
    }
  }
  public static Boolean and(Expression<Boolean> o1, Expression<Boolean> o2) {
    boolean b = o1.evaluate(false) && o2.evaluate(false);
    Debug.outln( o1 + " && " + o2 + " = " + b );
    return b;
  }

  public static class Or extends BooleanBinary< Boolean > {
    public Or( Expression< Boolean > o1, Expression< Boolean > o2 ) {
      super( o1, o2, "or" );
    }
  }
  public static Boolean or( Expression< Boolean > o1, Expression< Boolean > o2 ) {
    boolean b = o1.evaluate(false) && o2.evaluate(false);
    Debug.outln( o1 + " || " + o2 + " = " + b );
    return b;
  }

  public static class Xor extends BooleanBinary< Boolean > {
    public Xor( Expression< Boolean > o1, Expression< Boolean > o2 ) {
      super( o1, o2, "xor" );
    }
  }
  public static Boolean
      xor( Expression< Boolean > o1, Expression< Boolean > o2 ) {
    boolean b = ( o1.evaluate(false) ^ o2.evaluate(false) );
    Debug.outln( o1 + " ^ " + o2 + " = " + b );
    return b;
  }

  public static class Not extends Unary< Boolean, Boolean > {
    public Not( Expression< Boolean > o ) {
      super( o, "not" );
    }
  }
  public static Boolean not( Expression< Boolean > o ) {
    boolean b = !o.evaluate(false);
    Debug.outln( "!" + o + " = " + b );
    return b;
  }

  public static <T, T1> T1 pickValueBB( BooleanBinary< T > booleanBinary, Variable< T1 > variable ) {
    T1 newValue = null;
    FunctionCall f = (FunctionCall)booleanBinary.expression;
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
          } else if ( otherArg.getDomain() == null || otherArg.getDomain().contains( v ) ) {
            Debug.outln( "suggesting value " + v + " to make "
                         + booleanBinary.getClass().getSimpleName() + " true" );
            return v;
          }
        }
      }
    }
    if ( newValue == null ) {
      Debug.outln( "suggesting same value " + variable.getValue() + " for "
          + booleanBinary.getClass().getSimpleName() + " true" );
      return variable.getValue();
    }
    Debug.outln( "suggesting value " + newValue + " for "
        + booleanBinary.getClass().getSimpleName() + " true" );
    return newValue;
  }

  
}
