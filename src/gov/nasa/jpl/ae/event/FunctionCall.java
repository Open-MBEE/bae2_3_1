package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.util.ClassUtils;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.MoreToString;
import gov.nasa.jpl.ae.util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * 
 */

/**
 * 
 */
public class FunctionCall extends Call {

  protected Method method = null;
  protected boolean monotonic = false;
  //protected Object object = null; // object from which method is invoked
  //protected Vector< Object > arguments = null; // arguments to method

  /**
   * A function call on the result of this function call.
   */
//  protected Parameter<FunctionCall> nestedCall = null;
  
  /**
   * Construct a call to a static method.
   * @param method
   */
  public FunctionCall( Method method ) {
    this.method = method; // the method must be static
  }

  /**
   * Construct a call to a static method.
   * @param cls
   * @param methodName
   */
  public FunctionCall( Class<?> cls, String methodName ) {
    this.method = ClassUtils.getMethodForArgTypes( cls, methodName, (Class<?>[])null ); 
  }

  /**
   * @param object
   * @param method
   */
  public FunctionCall( Object object, Method method ) {
    this.object = object;
    this.method = method;
  }

  public FunctionCall( Object object, Class<?> cls, String methodName ) {
    this( cls, methodName );
    this.object = object;
  }

  /**
   * @param object
   * @param method
   * @param arguments
   */
  public FunctionCall( Object object, Method method, Vector< Object > arguments ) {
    this.object = object;
    this.method = method;
    this.arguments = arguments;
    hasTypeErrors();
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   */
  public FunctionCall( Object object, Class<?> cls, String methodName,
                       Vector< Object > arguments ) {
    this.object = object;
    Object argArr[] = null;
    if ( !Utils.isNullOrEmpty( arguments ) ) {
      argArr = arguments.toArray();
    }
    this.method = ClassUtils.getMethodForArgs( cls, methodName, argArr );
    this.arguments = arguments;
    hasTypeErrors();
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public FunctionCall( Object object, Method method, Vector< Object > arguments,
                       Call nestedCall ) {
    this(object, method, arguments);
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public FunctionCall( Object object, Method method, Vector< Object > arguments,
                       Parameter<Call> nestedCall ) {
    this(object, method, arguments);
    
    this.nestedCall = nestedCall;
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   * @param nestedCall
   */
  public FunctionCall( Object object, Class<?> cls, String methodName,
                       Vector< Object > arguments,
                       Call nestedCall ) {
    this(object, cls, methodName, arguments);
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
  }

  public FunctionCall( Object object, Class<?> cls, String methodName,
                       Vector< Object > arguments,
                       Parameter<Call> nestedCall ) {
    this(object, cls, methodName, arguments);
    this.nestedCall = nestedCall;
  }

  /**
   * @param object
   * @param method
   * @param arguments
   */
  public FunctionCall( Object object, Method method, Object argumentsA[] ) {
    this.object = object;
    this.method = method;
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    hasTypeErrors();
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   */
  public FunctionCall( Object object, Class<?> cls, String methodName,
                       Object argumentsA[] ) {
    this.object = object;
    this.method = ClassUtils.getMethodForArgs( cls, methodName, argumentsA );
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    hasTypeErrors();
  }

  public FunctionCall( Object object, Method method, Object argumentsA[],
                       Call nestedCall ) {
    this.object = object;
    this.method = method;
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
    hasTypeErrors();
  }

  public FunctionCall( Object object, Method method, Object argumentsA[],
                       Parameter<Call> nestedCall ) {
    this.object = object;
    this.method = method;
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    this.nestedCall = nestedCall;
    hasTypeErrors();
  }

  public FunctionCall( Object object, Class<?> cls, String methodName,
                       Object argumentsA[], Call nestedCall ) {
    this( object, cls, methodName, argumentsA );
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
  }
  public FunctionCall( Object object, Class<?> cls, String methodName,
                       Object argumentsA[], Parameter<Call> nestedCall ) {
    this( object, cls, methodName, argumentsA );
    this.nestedCall = nestedCall;
  }

  /**
   * @param e
   */
  public FunctionCall( FunctionCall e ) {
    this.object = e.object;
    this.method = e.method;
    this.arguments = e.arguments;
    this.nestedCall = e.nestedCall;
    hasTypeErrors();
  }

  @Override
  public FunctionCall clone() {
    FunctionCall f = new FunctionCall(this);
    return f;
  }
  
  /**
   * A monotonic function is always increasing or always decreasing.
   */
  public boolean isMonotonic() {
    // TODO -- redefine for functions in Functions.java
    return monotonic ;
  }
  
  /**
   * @param monotonic the monotonic to set
   */
  public void setMonotonic( boolean monotonic ) {
    this.monotonic = monotonic;
  }

  /**
   * A monotonic function is always increasing or always decreasing.
   */
  public boolean isContinuous() {
    // TODO -- redefine for functions in Functions.java
    return false;
  }
  
  @Override
  public Class<?>[] getParameterTypes() {
    if ( method == null ) return null;
    return method.getParameterTypes();
  }
  
  @Override
  public Member getMember() {
    return method;
  }

  @Override
  public boolean isVarArgs() {
    if ( method == null ) return false;
    return method.isVarArgs();
  }
  
  @Override
  public Object invoke( Object evaluatedObject, Object[] evaluatedArgs ) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
    if ( method == null ) {
      Debug.errln( "Warning! Tried to invoke a null method! " + this );
      return null;
    }
    if ( !Modifier.isStatic( method.getModifiers() )  && evaluatedObject == null ) {
      Debug.errln( "Warning! Tried to invoke a non-static method without an instance! " + this );
      return null;
    }
    if ( hasTypeErrors( evaluatedArgs ) ) {
      Debug.errln( "Warning! Tried calling " + this
                   + " with bad argument types! "
                   + MoreToString.Helper.toString( evaluatedArgs ) );
      return null;
    }
    Object result = null;
    try {
      result = method.invoke( evaluatedObject, evaluatedArgs ); 
    } catch (IllegalArgumentException e) {
      System.err.println( "FunctionCall.invoke " + method.getName() + "("
                          + Utils.toString( evaluatedArgs, false )
                          + "): FunctionCall{" + this + "}" + e.getMessage() );
      if ( method.getParameterTypes().length != evaluatedArgs.length ||
           !Arrays.asList( evaluatedArgs ).contains( null ) ) {
        e.printStackTrace();
      }
    }
   
    return result;
  }
  
  // TODO -- delete this when version is stable -- the same implementation is in Call
  // Try to match arguments to parameters by evaluating or creating expressions.
  protected Object[] evaluateArgs( boolean propagate ) {
    if ( method == null ) return null;
    Class< ? >[] paramTypes = method.getParameterTypes();
    return evaluateArgs( propagate, paramTypes, arguments, method.isVarArgs() );
  }

  // Getters and setters 
  
  /**
   * @return the method
   */
  public Method getMethod() {
    return method;
  }

  /**
   * @param method the method to set
   */
  public void setMethod( Method method ) {
    this.method = method;
  }

  @Override
  public Class< ? > getReturnType() {
    if ( getMember() != null ) {
      return getMethod().getReturnType();
    }
    return null;
  }

  /**
   * A deep search looking for FunctionCalls
   */
  public List< FunctionCall > getFunctionCallsRecursively() {
    List< FunctionCall > calls = new ArrayList< FunctionCall >();
    calls.add( this );
    if ( arguments != null ) {
      for ( Object arg : arguments ) {
        FunctionCall argCall = null;
        try {
          argCall = Expression.evaluate( arg, FunctionCall.class, false, false );
        } catch ( ClassCastException e ) {
          ; // ignore -- this is expected
        }
        if ( argCall != null ) {
          calls.addAll( argCall.getFunctionCallsRecursively() );
        }
      }
    }
    return calls;
  }
  
}
