/**
 * 
 */
package gov.nasa.jpl.ae.sysml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.util.ClassData;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Utils;

/**
 * A {@link TranslatedFunctionCall} tries to smartly swap between using some {@link Object} or
 * the {@link Parameter} to which it is translated in order to match the type of the
 * parameter into which the argument is passed and in order to translate the
 * result to a {@link Parameter}.The type parameter &lt;P&gt; is the parameter
 * or property type of the source object. 
 * <p>
 * For example, SysML elements may be translated to {@link Parameter}s, and a
 * TranslatedFunctionCall could wrap a call in the SysML API, such as
 * getOwner(e), to pass in the original element but then translate the returned
 * element to a Parameter for functions defined for the BAE API as done by
 * {@link SystemModelToAeExpression#elementValueToAeExpression(Object, String)} using
 * {@link ClassData#getParameter(String, String, String, boolean, boolean, boolean, boolean)`}.
 * <p>
 * For example, if we have the following functions: defined in a SysML API,
 * <pre>
 * E f(E element) { ... }
 * </pre>
 * and a general Java function in some class:
 * <pre>
 * class G {
 *   int g(int x) { .. }
 * }
 * </pre>
 * which is accessed through a {@link FunctionCall} in the BAE:
 * <pre>
 * FunctionCall("g", "G", Integer.class, new Class<?>[]{Integer.class});
 * </pre>
 * and elements e1 and e2 are in a constraint:
 * <pre>
 * e1 == g(f(e2))
 * </pre>
 * we need to make sure that the element return value of <code>f()</code> is replaced
 * with its corresponding {@link Parameter} to pass into <code>g()</code> because the {@link Parameter} can change during
 * solving even though the element value does not.  The {@link TranslatedFunctionCall}
 * achieves this when represented as:
 * <pre>
 * Parameter e2_parameter = new IntegerParameter("e2");
 * Parameter e1_parameter = new IntegerParameter("e1");
 * TranslatedFunctionCall g_f_call = 
 *     new TranslatedFunctionCall("g", "G", Integer.class, new Class<?>[]{Integer.class},
 *                                 new Object[]{TranslatedFunctionCall("f", "E", new Class<?>[]{E.class},
 *                                                        e2_parameter)});
 * new Equals(new Expression(e1), new Expression(g_f_call);
 * </pre> 
 */
public class TranslatedFunctionCall<P> extends FunctionCall {

  public boolean on = true;
  
  //protected ClassData _classData = null;
  protected Vector<Object> originalArguments = null;
  public SystemModelToAeExpression< ?, ?, P, ?, ?, ? > systemModelToAeExpression = null;
  
  public ClassData classData() {
    if ( systemModelToAeExpression == null ) return null;
    return systemModelToAeExpression.classData;
  }
  
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
    
    if ( on ) if ( systemModelToAeExpression != null ) parameterizeArguments();
    Object returnValue = super.evaluate( propagate, doEvalArgs );
    // Swap in the Parameter corresponding to the returned object if it exists.
    //if ( on ) if ( systemModelToAeExpression != null ) returnValue = parameterizeResult(returnValue);
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
    if ( on )  {
      Parameter< Object > parameter = systemModelToAeExpression.getExprParamMap().get( result );
      if ( parameter != null ) return parameter;
    }
    return result;
  }

  /**
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   * 
   */
  protected void parameterizeArguments()
      throws ClassCastException, IllegalAccessException, InvocationTargetException,
             InstantiationException {
    if ( systemModelToAeExpression != null ) return;
    this.originalArguments = arguments;
    this.evaluatedArguments = evaluateArgs( false );
    
    for ( int i = 0; i < originalArguments.size(); ++i ) {
      Object originalArg = originalArguments.get( i );
      Object evaluatedArg = evaluatedArguments[ i ];
      //boolean isVarArg = i >= getParameterTypes().length-1 && isVarArgs();
      Class<?> parameterType = getParameterTypes()[ Math.min(i, getParameterTypes().length-1)];

      Object newEvaluatedArg = parameterizeArgument( originalArg, evaluatedArg, parameterType );
      if ( newEvaluatedArg != null ) evaluatedArguments[ i ] = newEvaluatedArg;      
    }
  }

  protected Object parameterizeArgument(Object originalArg, Object evaluatedArg, Class< ? > parameterType  )
      throws ClassCastException, IllegalAccessException, InvocationTargetException,
             InstantiationException {
    if ( originalArg == null ) return null;
    Parameter<?> parameter = null;
    Expression<?> paramExpression = null;
    Object parameterValue = null;
    
    if ( originalArg instanceof Parameter ) {
      parameter = (Parameter<?>)originalArg;
      paramExpression = new Expression< Object >( parameter );
      parameterValue = parameter.getValueNoPropagate();
    } else if ( originalArg instanceof Expression &&  
                ((Expression<?>)originalArg).expression instanceof Parameter ) {
      parameter = (Parameter< ? >)((Expression<?>)originalArg).expression;
      paramExpression = (Expression<?>)originalArg;
      parameterValue = parameter.getValueNoPropagate();
    } else if ( evaluatedArg instanceof Parameter ) {
        parameter = (Parameter<?>)evaluatedArg;
        paramExpression = new Expression< Object >( parameter );
    } else if ( evaluatedArg instanceof Expression &&  
                ((Expression<?>)evaluatedArg).expression instanceof Parameter ) {
        parameter = (Parameter< ? >)((Expression<?>)evaluatedArg).expression;
        paramExpression = (Expression<?>)evaluatedArg;
        parameterValue = parameter.getValueNoPropagate();
    } else {
      if ( systemModelToAeExpression == null ) {
        System.err.println("systemModelToAeExpression = null for " + this);
        return null;
      } else if ( systemModelToAeExpression.model == null ) {
        System.err.println("systemModelToAeExpression.model = null for " + this);
        return null;
      }
      P p = systemModelToAeExpression.model.asProperty( originalArg );
      paramExpression = systemModelToAeExpression.elementArgumentToAeExpression( p );
    }
    // If originalArg is an AE Parameter already, see if we need to get the
    // source element to match the type of the parameter of this call's method.
    if ( paramExpression != null ) {
      if ( systemModelToAeExpression == null ) {
        System.err.println("systemModelToAeExpression = null for " + this);
        return null;
      }
      P sourceObject = systemModelToAeExpression.getElementForAeParameter( paramExpression );
      
      Object newEvaluatedArg =
          evaluateParameterizedArg( true, parameterType, parameter, false,
                                    false );

      Object result =
          ClassUtils.bestArgumentForType( Utils.newList( evaluatedArg,
                                                         sourceObject,
                                                         newEvaluatedArg,
                                                         paramExpression,
                                                         parameter,
                                                         parameterValue,
                                                         originalArg ),
                                          parameterType );
      if ( result != null ) return result;
/*
      if ( ClassUtils.isArgumentBetterForType( sourceObject, evaluatedArg,
                                               parameterType ) ) {
        P newArg = sourceObject;
        Object newEvaluatedArg = evaluateParameterizedArg(true, parameterType, newArg, false, false );
        if ( Debug.isOn() ) Debug.outln("% % % % %    isArgumentBetterForType(" + sourceObject + ", " + evaluatedArg + ", " + parameterType  + ") = true" );
        if ( Debug.isOn() ) Debug.outln("% % % % %    return " + newEvaluatedArg );
        return newEvaluatedArg;
      } else if ( ClassUtils.isArgumentBetterForType( paramExpression, evaluatedArg,
                                                      parameterType ) ) {
        if ( ClassUtils.isArgumentBetterForType( parameterValue, paramExpression,
                                                 parameterType ) ) {
          if ( Debug.isOn() ) Debug.outln("% % % % %    isArgumentBetterForType(" + paramExpression + ", " + evaluatedArg + ", " + parameterType  + ") = true" );
          if ( Debug.isOn() ) Debug.outln("% % % % %    return " + paramExpression );
          return parameterValue;
        } else {
          if ( Debug.isOn() ) Debug.outln("% % % % %    isArgumentBetterForType(" + paramExpression + ", " + evaluatedArg + ", " + parameterType  + ") = true" );
          if ( Debug.isOn() ) Debug.outln("% % % % %    return " + paramExpression );
        }
        return paramExpression;
      } else if ( ClassUtils.isArgumentBetterForType( parameterValue, evaluatedArg,
                                                      parameterType ) ) {
             if ( Debug.isOn() ) Debug.outln("% % % % %    isArgumentBetterForType(" + parameterValue + ", " + evaluatedArg + ", " + parameterType  + ") = true" );
             if ( Debug.isOn() ) Debug.outln("% % % % %    return " + parameterValue );
             return parameterValue;
      } else {
        if ( Debug.isOn() ) Debug.outln("% % % % %    isArgumentBetterForType(" + sourceObject + ", " + evaluatedArg + ", " + parameterType  + ") = false" );
        if ( Debug.isOn() ) Debug.outln("% % % % %    return " + evaluatedArg );
      }
    */
    }
    return evaluatedArg;
  }
  
  @Override
  public Object evaluateArg( boolean propagate,
                             Class< ? > c,
                             Object unevaluatedArg,
                             boolean isVarArg,
                             boolean complainIfError ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return evaluateParameterizedArg( propagate, c, unevaluatedArg, isVarArg, true );
  }
  public Object evaluateParameterizedArg( boolean propagate,
                                          Class< ? > c,
                                          Object unevaluatedArg,
                                          boolean isVarArg,
                                          boolean parameterize ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Object obj = super.evaluateArg( propagate, c, unevaluatedArg, isVarArg, false );
    if ( on && parameterize ) {
      Object parameterizedObj = parameterizeArgument( unevaluatedArg, obj, c );
      if ( parameterizedObj != null ) return parameterizedObj;
    }
    return obj;
  }
  
  protected boolean isParameter( Object o ) {
    boolean isParam = o instanceof Parameter ||
        (o instanceof Expression &&
         ((Expression<?>)o).expression instanceof Parameter);
    return isParam;
  }
  
  public TranslatedFunctionCall( Object object,
                                 Method method,
                                 Vector< Object > aeArguments,
                                 SystemModelToAeExpression< ?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super(object, method, aeArguments);
    this.systemModelToAeExpression = systemModelToAeExpression;
  }


  
  /**
   * @param method
   */
  public TranslatedFunctionCall( Method method, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( method );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param cls
   * @param methodName
   */
  public TranslatedFunctionCall( Class< ? > cls, String methodName, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( cls, methodName );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param object
   * @param method
   */
  public TranslatedFunctionCall( Object object, Method method, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, method );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, methodName );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName,
                            Vector< Object > arguments, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, methodName, arguments );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Method method,
                            Vector< Object > arguments, Call nestedCall, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, method, arguments, nestedCall );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Method method,
                            Vector< Object > arguments,
                            Parameter< Call > nestedCall, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, method, arguments, nestedCall );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName,
                            Vector< Object > arguments, Call nestedCall, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, methodName, arguments, nestedCall );
    this.systemModelToAeExpression = systemModelToAeExpression;
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
                            Parameter< Call > nestedCall, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, methodName, arguments, nestedCall );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param object
   * @param method
   * @param argumentsA
   */
  public TranslatedFunctionCall( Object object, Method method, Object[] argumentsA, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, method, argumentsA );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName,
                            Object[] argumentsA, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, methodName, argumentsA );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param object
   * @param method
   * @param argumentsA
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Method method, Object[] argumentsA,
                            Call nestedCall, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, method, argumentsA, nestedCall );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param object
   * @param method
   * @param argumentsA
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Method method, Object[] argumentsA,
                            Parameter< Call > nestedCall, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, method, argumentsA, nestedCall );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName,
                            Object[] argumentsA, Call nestedCall, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, methodName, argumentsA, nestedCall );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   * @param nestedCall
   */
  public TranslatedFunctionCall( Object object, Class< ? > cls, String methodName,
                            Object[] argumentsA, Parameter< Call > nestedCall, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, methodName, argumentsA, nestedCall );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  /**
   * @param e
   */
  public TranslatedFunctionCall( FunctionCall e, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( e );
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

}
