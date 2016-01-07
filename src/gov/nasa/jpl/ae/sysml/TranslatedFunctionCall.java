/**
 * 
 */
package gov.nasa.jpl.ae.sysml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.util.ClassData;

/**
 * A TranslatedFunctionCall tries to smartly swap between using some Object or
 * the Parameter to which it is translated in order to match the type of the
 * parameter into which the argument is passed and in order to match with the 
 * the return value type.  
 * 
*/ (HERE!) /*
 * That is passed FunctionCalls to
 * wrap objects, such as a SysML element, in Parameter, such that API functions
 * can accept and return the objects and match them back up with their
 * associated Parameters as done by
 * SystemModelToAeExpression.elementValueToAeExpression() using
 * SystemModelToAeExpression.classData.getParameter().
 * <p>
 * For example, <br>
 * <code>
 * E f(E e) { ... }
 * </code> <br>
 * <code>
 * int g(int x) { .. }
 * </code><br>
 * If e1 and e2 are elements with integer values, and there's a constraint,
 * <code>
 * e1 == g(f(e2))
 * </code>, we need to make sure that the return value of f is paired back up
 * with it's Parameter because the Parameter can change during solving even
 * though the element value does not.
 */
public class TranslatedFunctionCall extends FunctionCall {

  ClassData classData = null;
  protected Objectp;
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#evaluate(boolean, boolean)
   */
  @Override
  public synchronized
      Object evaluate( boolean propagate, boolean doEvalArgs )
                                                       throws IllegalAccessException,
                                                       InvocationTargetException,
                                                       InstantiationException {
    // Make sure the arguments passed into the function are replaced with their
    // corresponding Parameters where appropriate.
    parameterizeArguments();
    Object returnValue = super.evaluate( propagate, doEvalArgs );
    // Swap in the Parameter corresponding to the returned object if it exists.
    returnValue = parameterizeResult(returnValue);
    return returnValue;
  }

  /**
   * Make sure the arguments passed into the function are replaced with their
   * corresponding Parameters where appropriate. It is appropriate to replace an
   * argument if the class of the argument is incompatible with the
   * corresponding parameter type of the function.  If the translated parameter , and the 
   * 
   * @param result
   * @return
   */
  protected Object parameterizeResult( Object result ) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * 
   */
  protected void parameterizeArguments() {
    this.evaluatedArguments = evaluateArgs( false );
    this.originalArguments = arguments;
    Object[] swapped;
  }

  /**
   * @param method
   */
  public TranslatedFunctionCall( Method method ) {
    super( method );
  }

  /**
   * @param cls
   * @param methodName
   */
  public TranslatedFunctionCall( Class< ? > cls, String methodName ) {
    super( cls, methodName );
  }

  /**
   * @param object
   * @param method
   */
  public TranslatedFunctionCall( Object object, Method method ) {
    super( object, method );
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName ) {
    super( object, cls, methodName );
  }

  /**
   * @param object
   * @param method
   * @param arguments
   */
  public TranslatedFunctionCall( Object object, Method method,
                            Vector< Object > arguments ) {
    super( object, method, arguments );
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName,
                            Vector< Object > arguments ) {
    super( object, cls, methodName, arguments );
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Method method,
                            Vector< Object > arguments, Call nestedCall ) {
    super( object, method, arguments, nestedCall );
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Method method,
                            Vector< Object > arguments,
                            Parameter< Call > nestedCall ) {
    super( object, method, arguments, nestedCall );
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName,
                            Vector< Object > arguments, Call nestedCall ) {
    super( object, cls, methodName, arguments, nestedCall );
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName,
                            Vector< Object > arguments,
                            Parameter< Call > nestedCall ) {
    super( object, cls, methodName, arguments, nestedCall );
  }

  /**
   * @param object
   * @param method
   * @param argumentsA
   */
  public TranslatedFunctionCall( Object object, Method method, Object[] argumentsA ) {
    super( object, method, argumentsA );
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName,
                            Object[] argumentsA ) {
    super( object, cls, methodName, argumentsA );
  }

  /**
   * @param object
   * @param method
   * @param argumentsA
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Method method, Object[] argumentsA,
                            Call nestedCall ) {
    super( object, method, argumentsA, nestedCall );
  }

  /**
   * @param object
   * @param method
   * @param argumentsA
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Method method, Object[] argumentsA,
                            Parameter< Call > nestedCall ) {
    super( object, method, argumentsA, nestedCall );
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName,
                            Object[] argumentsA, Call nestedCall ) {
    super( object, cls, methodName, argumentsA, nestedCall );
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName,
                            Object[] argumentsA, Parameter< Call > nestedCall ) {
    super( object, cls, methodName, argumentsA, nestedCall );
  }

  /**
   * @param e
   */
  public TranslatedFunctionCall( FunctionCall e ) {
    super( e );
  }

}
