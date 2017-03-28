/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Vector;

/**
 * A {@link FunctionCall} that interprets {@link TimeVaryingMap}s as arguments to generate a
 * {@link TimeVaryingMap} result.
 */
public class TimeVaryingFunctionCall extends FunctionCall {

  
  public boolean returnsTimeVaryingMap( Object object ) {
    if ( object instanceof TimeVaryingMap ) {
      return true;
    }
    FunctionCall f = null;
    try {
      f = Expression.evaluate( object, FunctionCall.class, true, false );
      if ( f != null ) {
        TimeVaryingFunctionCall tvfc = new TimeVaryingFunctionCall( f );
        if ( tvfc.returnsTimeVaryingMap() ) {
          return true;
        }
      }
    } catch ( ClassCastException | IllegalAccessException
              | InvocationTargetException | InstantiationException e ) {
      e.printStackTrace();
    }
    try {
      TimeVaryingMap< ? > t =
          Expression.evaluate( object, TimeVaryingMap.class, true, false );
      if ( t != null ) {
        return true;
      }
    } catch ( ClassCastException | IllegalAccessException
              | InvocationTargetException | InstantiationException e ) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean returnsTimeVaryingMap() {
    if ( returnType != null && TimeVaryingMap.class.isAssignableFrom( returnType ) ) {
      return true;
    }
//    if ( !isStatic() && object != null && !TimeVaryingMap.class.isAssignableFrom( method.getDeclaringClass() ) && returnsTimeVaryingMap(object) ) {
//      return true;
//    }
    
    // Process the object of the method.
    Call call = this;
    TimeVaryingMap<?> objectMap = null;
    Object obj = call.getObject();
    Member member = call.getMember();
    if ( obj != null && !call.isStatic() && obj instanceof TimeVaryingMap ) {
      Class<?> methodClass = member.getDeclaringClass();
      objectMap = (TimeVaryingMap<?>)obj;
      // If the TVM values are compatible with the method's class or, in the case that the TVM value type is not known, and the TVM itself is not compatible with the method's class, then assume that the TVM values are meant to be the instance of the call.
      if ( ( objectMap.getType() != null && methodClass.isAssignableFrom( objectMap.getType() ) ) ||
          (objectMap.getType() == null && !methodClass.isInstance( obj ) ) ) {
        return true;
      }
    }

    // Process the arguments.
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
        // If the TVM values are compatible with the method's class or, in the case that the TVM value type is not known, and the TVM itself is not compatible with the method's class, then assume that the TVM values are meant to be the instance of the call.
        if ( arg != null && pType != null && ( ( tv.getType() != null && pType.isAssignableFrom( tv.getType() ) ) ||
            (tv.getType() == null && !pType.isInstance( obj ) ) ) ) {
          return true;
        }
      }
      ++i;
    }
    
    return false;
  }

  @Override
  public Object evaluate( boolean propagate ) throws IllegalAccessException,
                                              InvocationTargetException,
                                              InstantiationException {
    // TODO Auto-generated method stub
    return super.evaluate( propagate );
  }
  

  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#evaluate(boolean, boolean)
   */
  @Override
  public synchronized Object
         evaluate( boolean propagate,
                   boolean doEvalArgs ) throws IllegalAccessException,
                                        InvocationTargetException,
                                        InstantiationException {
    return super.evaluate( propagate, doEvalArgs );
  }



  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#evaluate(boolean, boolean, boolean)
   */
  @Override
  public synchronized Object
         evaluate( boolean propagate, boolean doEvalArgs,
                   boolean useAlternatives ) throws IllegalAccessException,
                                             InvocationTargetException,
                                             InstantiationException {
    return super.evaluate( propagate, doEvalArgs, useAlternatives );
  }



  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#evaluateWithSetArguments(boolean, boolean)
   */
  @Override
  public synchronized Object
         evaluateWithSetArguments( boolean propagate,
                                   boolean doEvalArgs ) throws IllegalAccessException,
                                                        InvocationTargetException,
                                                        InstantiationException,
                                                        IllegalArgumentException {
    return super.evaluateWithSetArguments( propagate, doEvalArgs );
  }



  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#evaluateArgs(boolean)
   */
  @Override
  public Object[] evaluateArgs( boolean propagate ) throws ClassCastException,
                                                    IllegalAccessException,
                                                    InvocationTargetException,
                                                    InstantiationException {
    return super.evaluateArgs( propagate );
  }



  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#evaluateArg(boolean, java.lang.Class, java.lang.Object, boolean, boolean)
   */
  @Override
  public Object evaluateArg( boolean propagate, Class< ? > c,
                             Object unevaluatedArg, boolean isVarArg,
                             boolean complainIfError ) throws ClassCastException,
                                                       IllegalAccessException,
                                                       InvocationTargetException,
                                                       InstantiationException {
    return super.evaluateArg( propagate, c, unevaluatedArg, isVarArg,
                              complainIfError );
  }



  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#evaluateArgs(boolean, java.lang.Class[], java.util.Vector, boolean, boolean)
   */
  @Override
  public Object[]
         evaluateArgs( boolean propagate, Class< ? >[] paramTypes,
                       Vector< Object > args, boolean isVarArgs,
                       boolean complainIfError ) throws ClassCastException,
                                                 IllegalAccessException,
                                                 InvocationTargetException,
                                                 InstantiationException {
    return super.evaluateArgs( propagate, paramTypes, args, isVarArgs,
                               complainIfError );
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
    // TODO Auto-generated constructor stub
  }

  /**
   * @param args
   */
  public static void main( String[] args ) {
    // TODO Auto-generated method stub

  }

}
