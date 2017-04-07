/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;

import gov.nasa.jpl.ae.event.TimeVaryingMap.Interpolation;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;

/**
 * A {@link FunctionCall} that interprets {@link TimeVaryingMap}s as arguments to generate a
 * {@link TimeVaryingMap} result.
 */
public class TimeVaryingFunctionCall extends FunctionCall {

  public static TimeVaryingMap<?> evaluateAsTimeVaryingMap( Object object, boolean recursive, Set<Object> seen ) {
    Pair< Boolean, Set< Object > > p = Utils.seen( object, recursive, seen );
    if ( p.first ) return null;
    seen = p.second;
    
    Object result = null;
    if ( object instanceof TimeVaryingMap ) {
      return (TimeVaryingMap< ? >)object;
    }
    // See if it naturally evaluates to a TVM
    try {
      result = Expression.evaluate( object, null, true );
      boolean isTvm = result instanceof TimeVaryingMap;
      if ( isTvm ) return (TimeVaryingMap< ? >)result;
      // If it's not a TVM and it's not wrapping another value, then it's not
      // supposed to be a TVM.
      if ( !( result instanceof Wraps) ) return null;
      // Else, it's wrapping something, and we can evaluate that.
      result = ((Wraps<?>)result).getValue( true );
      result = evaluateAsTimeVaryingMap( result, recursive, seen );
      return (TimeVaryingMap< ? >)result;
    } catch ( Throwable e1 ) {
    }

    // Got an exception -- otherwise it would have returned.
    // The exception may have been from trying to use a TVM where it's not legal.
    // So, look recursively for unexpected TVMs in inner Calls.  
    FunctionCall f = null;
    try {
      f = Expression.evaluate( object, FunctionCall.class, true, false );
      if ( f != null ) {
        TimeVaryingFunctionCall tvfc = new TimeVaryingFunctionCall( f );
        if ( tvfc.returnsTimeVaryingMap(recursive, seen) ) {
          result = tvfc.evaluate( true );
          boolean isTvm = result instanceof TimeVaryingMap;
          if ( isTvm ) return (TimeVaryingMap< ? >)result;
        }
      }
    } catch ( Throwable e1 ) {
    }
    ConstructorCall c = null;
    try {
      c = Expression.evaluate( object, ConstructorCall.class, true, false );
      if ( c != null ) {
        TimeVaryingConstructorCall tvcc = new TimeVaryingConstructorCall( c );
        if ( tvcc.returnsTimeVaryingMap(recursive, seen) ) {
          result = tvcc.evaluate( true );
          boolean isTvm = result instanceof TimeVaryingMap;
          if ( isTvm ) return (TimeVaryingMap< ? >)result;
        }
      }
    } catch ( Throwable e1 ) {
    }
    
    // Try to evaluate it as a TimeVaryingMap? This would be an unnatural
    // evaluation since we checked the natural one earlier.
    try {
      TimeVaryingMap< ? > t =
          Expression.evaluate( object, TimeVaryingMap.class, true, false );
      if ( t != null ) {
        return t;
      }
    } catch ( Throwable e1 ) {
    }
    return null;
  }
  
  public static boolean returnsTimeVaryingMap( Object object, boolean recursive, Set<Object> seen ) {
    Pair< Boolean, Set< Object > > p = Utils.seen( object, recursive, seen );
    if ( p.first ) return false;
    seen = p.second;
    
    if ( object instanceof TimeVaryingMap ) {
      return true;
    }
    // See if it naturally evaluates to a TVM
    try {
      Object result = Expression.evaluate( object, null, true );
      boolean isTvm = result instanceof TimeVaryingMap;
      if ( isTvm ) return true;
      // If it's not a TVM and it's not wrapping another value, then it's not
      // supposed to be a TVM.
      if ( !( result instanceof Wraps) ) return false;
      // Else, it's wrapping something, and we can evaluate that.
      result = ((Wraps<?>)result).getValue( true );
      isTvm = returnsTimeVaryingMap( result, recursive, seen );
      return isTvm;
    } catch ( Throwable e1 ) {
    }

    // Got an exception -- otherwise it would have returned.
    // The exception may have been from trying to use a TVM where it's not legal.
    // So, look recursively for unexpected TVMs in inner Calls.  
    FunctionCall f = null;
    try {
      f = Expression.evaluate( object, FunctionCall.class, true, false );
      if ( f != null ) {
        TimeVaryingFunctionCall tvfc = new TimeVaryingFunctionCall( f );
        if ( tvfc.returnsTimeVaryingMap(recursive, seen) ) {
          return true;
        }
      }
    } catch ( ClassCastException | IllegalAccessException
              | InvocationTargetException | InstantiationException e ) {
    }
    ConstructorCall c = null;
    try {
      c = Expression.evaluate( object, ConstructorCall.class, true, false );
      if ( c != null ) {
        TimeVaryingConstructorCall tvcc = new TimeVaryingConstructorCall( c );
        if ( tvcc.returnsTimeVaryingMap(recursive, seen) ) {
          return true;
        }
      }
    } catch ( Throwable e1 ) {
    }
    
    // Try to evaluate it as a TimeVaryingMap? This would be an unnatural
    // evaluation since we checked the natural one earlier. So skip this with a
    // cryptic if condition that always fails to avoid the compile warning.
    if ( "a".contains( "b" ) ) {
      try {
        TimeVaryingMap< ? > t =
            Expression.evaluate( object, TimeVaryingMap.class, true, false );
        if ( t != null ) {
          return true;
        }
      } catch ( Throwable e1 ) {
      }
    }
    return false;
  }

  public boolean returnsTimeVaryingMap(boolean recursive, Set<Object> seen) {
    return returnsTimeVaryingMap( this, recursive, seen );
  }
  
  public static boolean returnsTimeVaryingMap(Call call, boolean recursive, Set<Object> seen) {
    Pair< Boolean, Set< Object > > p = Utils.seen( call.object, recursive, seen );
    if ( p.first ) return false;
    seen = p.second;

    // Check return value.
    if ( call.returnType != null && TimeVaryingMap.class.isAssignableFrom( call.returnType ) ) {
      return true;
    }
//    if ( !isStatic() && object != null && !TimeVaryingMap.class.isAssignableFrom( method.getDeclaringClass() ) && returnsTimeVaryingMap(object) ) {
//      return true;
//    }
    
    // Check the object of the method.
    TimeVaryingMap<?> objectMap = null;
    Object obj = call.getObject();
    Member member = call.getMember();
    Class<?> methodClass = member.getDeclaringClass();
    if ( obj != null && !call.isStatic() && obj instanceof TimeVaryingMap ) {
      objectMap = (TimeVaryingMap<?>)obj;
      // If the TVM values are compatible with the method's declaring class or,
      // in the case that the TVM value type is not known, and the TVM itself is
      // not compatible with the method's declaring class, then assume that the
      // TVM values are meant to be the object/instance, on which the call is made.
      if ( ( objectMap.getType() != null && methodClass.isAssignableFrom( objectMap.getType() ) ) ||
           ( objectMap.getType() == null && !methodClass.isInstance( obj ) ) ) {
        return true;
      }
    } else if ( recursive && obj != null && !( obj instanceof TimeVaryingMap )
                && !TimeVaryingMap.class.isAssignableFrom( methodClass ) ) {
      // Check and see if the object will evaluate to a TVM unexpectedly
      // (meaning that it does even though the call's declaring class is not a TVM).
      seen.remove( obj );
      if ( returnsTimeVaryingMap( obj, recursive, seen ) ) {
        return true;
      }
    }

    // Check the arguments for unexpected TVM values.
    ArrayList< TimeVaryingMap< ? > > argMaps =
        new ArrayList< TimeVaryingMap< ? > >();
    Class< ? >[] paramTypes = call.getParameterTypes();
    Object[] args = call.getArgumentArray();
    int i = 0;
    Class<?> pType = null;
    for ( Object arg : args ) {
      if ( arg instanceof TimeVaryingMap ) {
        TimeVaryingMap<?> tv = (TimeVaryingMap<?>)arg;
        if ( i < paramTypes.length ) {  // helps with variable arguments
          pType = paramTypes[i];
        }
        // If the TVM values are compatible with the method's class or, in the
        // case that the TVM value type is not known, and the TVM itself is not
        // compatible with the method's class, then assume that the TVM values
        // are meant to be the instance of the call.
        if ( arg != null && pType != null
             && !TimeVaryingMap.class.isAssignableFrom( pType )
             && ( ( tv.getType() != null
                    && pType.isAssignableFrom( tv.getType() ) )
                  || ( tv.getType() == null && !pType.isInstance( obj ) ) ) ) {
          return true;
        }
      } else if ( recursive && arg != null && pType != null
                  && !TimeVaryingMap.class.isAssignableFrom( pType ) ) {
        // Check and see if the argument will evaluate to a TVM unexpectedly
        // (meaning that it does even though the parameter type is not a TVM).
        if ( returnsTimeVaryingMap( arg, recursive, seen ) ) {
          return true;
        }
      }
      ++i;
    }
    
    return false;
  }
  
  
  @Override
  public Object invoke( Object evaluatedObject,
                        Object[] evaluatedArgs ) {//throws IllegalArgumentException,
    Object result = invoke( this, evaluatedObject, evaluatedArgs );
    evaluationSucceeded = true;
    return result;
  }

  public static Object invoke( Call call, Object evaluatedObject,
                               Object[] evaluatedArgs ) {//throws IllegalArgumentException,
                                                 //InstantiationException,
                                                 //IllegalAccessException,
                                                // InvocationTargetException {
    TimeVaryingMap< ? > tvm = new TimeVaryingMap<>( "", call.getReturnType(), call, null );
    return tvm;
  }
  

  @Override
  public Object evaluateObject( boolean propagate ) throws ClassCastException,
                                                    IllegalAccessException,
                                                    InvocationTargetException,
                                                    InstantiationException {
  
    Throwable t = null;
    ClassCastException e1 = null;
    IllegalAccessException e2 = null;
    InvocationTargetException e3 = null;
    InstantiationException e4 = null;     
    Object o = null;
    
    try {
      o = super.evaluateObject( propagate );
    } catch ( ClassCastException e ) {
      e1 = e;
      t = e;
    } catch ( IllegalAccessException e ) {
      e2 = e;
      t = e;
    } catch ( InvocationTargetException e ) {
      e3 = e;
      t = e;
    } catch ( InstantiationException e ) {
      e4 = e;
      t = e;
    }
    
    if ( o == null ) o = object;
    TimeVaryingMap<?> map = evaluateAsTimeVaryingMap( object, true, null );
    if ( map != null ) o = map;
    
    if ( o == null && t != null ) {
      if ( e1 != null ) throw e1;
      if ( e2 != null ) throw e2;
      if ( e3 != null ) throw e3;
      if ( e4 != null ) throw e4;
    }
    return o;
  }
  
  @Override
  public Boolean hasTypeErrors( Object[] evaluatedArgs ) {
    return false;
    // TODO Auto-generated method stub
    //return super.hasTypeErrors( evaluatedArgs );
  }

  @Override
  public synchronized Boolean hasTypeErrors() {
    return false;
    // TODO Auto-generated method stub
    //return super.hasTypeErrors();
  }
  
  @Override
  public Object evaluateArg( Object unevaluatedArg, Class< ? > c,
                             boolean propagate ) throws ClassCastException,
                                                 IllegalAccessException,
                                                 InvocationTargetException,
                                                 InstantiationException {
    Throwable t = null;
    ClassCastException e1 = null;
    IllegalAccessException e2 = null;
    InvocationTargetException e3 = null;
    InstantiationException e4 = null;     
    Object o = null;
    
    try {
      o = super.evaluateArg( unevaluatedArg, c, propagate );
    } catch ( ClassCastException e ) {
      e1 = e;
      t = e;
    } catch ( IllegalAccessException e ) {
      e2 = e;
      t = e;
    } catch ( InvocationTargetException e ) {
      e3 = e;
      t = e;
    } catch ( InstantiationException e ) {
      e4 = e;
      t = e;
    }
    
    if ( o == null ) o = object;
    TimeVaryingMap<?> map = evaluateAsTimeVaryingMap( o, true, null );
    if ( map != null ) o = map;
    
    if ( o == null && t != null ) {
      if ( e1 != null ) throw e1;
      if ( e2 != null ) throw e2;
      if ( e3 != null ) throw e3;
      if ( e4 != null ) throw e4;
    }
    return o;
  }


  /**
   * @param method
   * @param returnType
   */
  public TimeVaryingFunctionCall( Method method, Class< ? > returnType ) {
    super( method, returnType );
  }

  /**
   * @param cls
   * @param methodName
   * @param returnType
   */
  public TimeVaryingFunctionCall( Class< ? > cls, String methodName,
                                  Class< ? > returnType ) {
    super( cls, methodName, returnType );
  }

  /**
   * @param object
   * @param method
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Method method,
                                  Class< ? > returnType ) {
    super( object, method, returnType );
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Class< ? > cls,
                                  String methodName, Class< ? > returnType ) {
    super( object, cls, methodName, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Method method,
                                  Vector< Object > arguments,
                                  Class< ? > returnType ) {
    super( object, method, arguments, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Class< ? > cls,
                                  String methodName, Vector< Object > arguments,
                                  Class< ? > returnType ) {
    super( object, cls, methodName, arguments, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Method method,
                                  Vector< Object > arguments, Call nestedCall,
                                  Class< ? > returnType ) {
    super( object, method, arguments, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Method method,
                                  Vector< Object > arguments,
                                  Parameter< Call > nestedCall,
                                  Class< ? > returnType ) {
    super( object, method, arguments, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Class< ? > cls,
                                  String methodName, Vector< Object > arguments,
                                  Call nestedCall, Class< ? > returnType ) {
    super( object, cls, methodName, arguments, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Class< ? > cls,
                                  String methodName, Vector< Object > arguments,
                                  Parameter< Call > nestedCall,
                                  Class< ? > returnType ) {
    super( object, cls, methodName, arguments, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   * @param argumentsA
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Method method,
                                  Object[] argumentsA, Class< ? > returnType ) {
    super( object, method, argumentsA, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   */
  public TimeVaryingFunctionCall( Object object, Class< ? > cls,
                                  String methodName, Object[] argumentsA ) {
    super( object, cls, methodName, argumentsA );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Class< ? > cls,
                                  String methodName, Object[] argumentsA,
                                  Class< ? > returnType ) {
    super( object, cls, methodName, argumentsA, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   * @param argumentsA
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Method method,
                                  Object[] argumentsA, Call nestedCall,
                                  Class< ? > returnType ) {
    super( object, method, argumentsA, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   * @param argumentsA
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Method method,
                                  Object[] argumentsA,
                                  Parameter< Call > nestedCall,
                                  Class< ? > returnType ) {
    super( object, method, argumentsA, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Class< ? > cls,
                                  String methodName, Object[] argumentsA,
                                  Call nestedCall, Class< ? > returnType ) {
    super( object, cls, methodName, argumentsA, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingFunctionCall( Object object, Class< ? > cls,
                                  String methodName, Object[] argumentsA,
                                  Parameter< Call > nestedCall,
                                  Class< ? > returnType ) {
    super( object, cls, methodName, argumentsA, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param e
   */
  public TimeVaryingFunctionCall( FunctionCall e ) {
    super( e );
  }

//  /**
//   * @param args
//   */
//  public static void main( String[] args ) {
//  }

}
