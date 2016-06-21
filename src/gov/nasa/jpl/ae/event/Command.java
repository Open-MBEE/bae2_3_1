/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.lang.reflect.Method;
import java.util.Vector;

/**
 *
 */
public class Command extends FunctionCall {

  
  // FIXME -- need to change constructors to take a return type!
  
  
  public static TimeVaryingList<Command> commandSequence = 
      new TimeVaryingList< Command >( "commandSequence" );
  
  /**
   * @param method
   */
  public Command( Method method ) {
    super( method, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param cls
   * @param methodName
   */
  public Command( Class< ? > cls, String methodName ) {
    super( cls, methodName, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   */
  public Command( Object object, Method method ) {
    super( object, method, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   */
  public Command( Object object, Class< ? > cls, String methodName ) {
    super( object, cls, methodName, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   * @param arguments
   */
  public Command( Object object, Method method, Vector< Object > arguments ) {
    super( object, method, arguments, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   */
  public Command( Object object, Class< ? > cls, String methodName,
                  Vector< Object > arguments ) {
    super( object, cls, methodName, arguments, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public Command( Object object, Method method, Vector< Object > arguments,
                  Call nestedCall ) {
    super( object, method, arguments, nestedCall, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public Command( Object object, Method method, Vector< Object > arguments,
                  Parameter< Call > nestedCall ) {
    super( object, method, arguments, nestedCall, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   * @param nestedCall
   */
  public Command( Object object, Class< ? > cls, String methodName,
                  Vector< Object > arguments, Call nestedCall ) {
    super( object, cls, methodName, arguments, nestedCall, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   * @param nestedCall
   */
  public Command( Object object, Class< ? > cls, String methodName,
                  Vector< Object > arguments, Parameter< Call > nestedCall ) {
    super( object, cls, methodName, arguments, nestedCall, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   * @param argumentsA
   */
  public Command( Object object, Method method, Object[] argumentsA ) {
    super( object, method, argumentsA, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   */
  public Command( Object object, Class< ? > cls, String methodName,
                  Object[] argumentsA ) {
    super( object, cls, methodName, argumentsA, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   * @param argumentsA
   * @param nestedCall
   */
  public Command( Object object, Method method, Object[] argumentsA,
                  Call nestedCall ) {
    super( object, method, argumentsA, nestedCall, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param method
   * @param argumentsA
   * @param nestedCall
   */
  public Command( Object object, Method method, Object[] argumentsA,
                  Parameter< Call > nestedCall ) {
    super( object, method, argumentsA, nestedCall, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   * @param nestedCall
   */
  public Command( Object object, Class< ? > cls, String methodName,
                  Object[] argumentsA, Call nestedCall ) {
    super( object, cls, methodName, argumentsA, nestedCall, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   * @param nestedCall
   */
  public Command( Object object, Class< ? > cls, String methodName,
                  Object[] argumentsA, Parameter< Call > nestedCall ) {
    super( object, cls, methodName, argumentsA, nestedCall, (Class<?>)null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param e
   */
  public Command( FunctionCall e ) {
    super( e );
    // TODO Auto-generated constructor stub
  }

}
