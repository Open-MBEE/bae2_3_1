/**
 * 
 */
package gov.nasa.jpl.ae.sysml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;
import java.util.Vector;

import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.ConstructorCall;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.HasParameters;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Utils;

/**
 * A {@link TranslatedConstructorCall} tries to smartly swap between using some {@link Object} or
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
 * solving even though the element value does not.  The {@link TranslatedConstructorCall}
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
public class TranslatedConstructorCall<P> extends ConstructorCall implements TranslatedCall {

  public boolean on = true;
  
  protected TranslatedCallHelper<P> translatedCallHelper = null;
  
  //protected ClassData _classData = null;
  protected Vector<Object> originalArguments = null;
//  public SystemModelToAeExpression< ?, ?, P, ?, ?, ? > systemModelToAeExpression = null;
  
//  public ClassData classData() {
//    if ( systemModelToAeExpression == null ) return null;
//    return systemModelToAeExpression.classData;
//  }
  
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#evaluate(boolean, boolean)
   */
  @Override
  public synchronized Object evaluate( boolean propagate, boolean doEvalArgs )
      throws IllegalAccessException,
      InvocationTargetException,
      InstantiationException {
    // Make sure the arguments passed into the function are replaced with their
    // corresponding Parameters where appropriate.
    if ( on ) {
      translatedCallHelper.parameterizeArguments();
    }
    Object returnValue = super.evaluate( propagate, doEvalArgs );
    // Swap in the Parameter corresponding to the returned object if it exists.
    if ( on ) {
      returnValue = translatedCallHelper.parameterizeResult(returnValue);
    }
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
//  protected Object parameterizeResult( Object result ) {
//    if ( on ) {
//      Parameter< Object > parameter = systemModelToAeExpression.getExprParamMap().get( result );
//      if ( parameter != null ) return parameter;
//    }
//    return result;
//  }

  /**
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   * 
   */
//  protected void parameterizeArguments()
//      throws ClassCastException, IllegalAccessException, InvocationTargetException,
//             InstantiationException {
//    if ( systemModelToAeExpression == null ) return;
//    this.originalArguments = arguments;
//    this.evaluatedArguments = evaluateArgs( false );
//    
//    //arguments = new Vector< Object >( arguments.size() );
//    
//    for ( int i = 0; i < originalArguments.size(); ++i ) {
//      Object originalArg = originalArguments.get( i );
//      //Object evaluatedArg = evaluatedArguments[ i ];
//      //boolean isVarArg = i >= getParameterTypes().length-1 && isVarArgs();
//      Class<?> parameterType = getParameterTypes()[ Math.min(i, getParameterTypes().length-1)];
//
//      Object newEvaluatedArg = parameterizeArgument( originalArg, parameterType );
//      if ( newEvaluatedArg != null ) evaluatedArguments[ i ] = newEvaluatedArg;      
//    }
//  }
//
//  protected Object parameterizeArgument(Object originalArg, Class< ? > parameterType  )
//      throws ClassCastException, IllegalAccessException, InvocationTargetException,
//             InstantiationException {
//    if ( originalArg == null ) return null;
//    Parameter<?> parameter = null;
//    Expression<?> paramExpression = null;
//    
//    if ( originalArg instanceof Parameter ) {
//      parameter = (Parameter<?>)originalArg;
//      paramExpression = new Expression< Object >( parameter );
//    } else if ( originalArg instanceof Expression &&  
//                ((Expression<?>)originalArg).expression instanceof Parameter ) {
//      parameter = (Parameter< ? >)((Expression<?>)originalArg).expression;
//      paramExpression = (Expression<?>)originalArg;
//    } else {
//      if ( systemModelToAeExpression == null ) {
//        System.err.println("systemModelToAeExpression = null for " + this);
//        return null;
//      } else if ( systemModelToAeExpression.model == null ) {
//        System.err.println("systemModelToAeExpression.model = null for " + this);
//        return null;
//      }
//      P p = systemModelToAeExpression.model.asProperty( originalArg );
//      paramExpression = systemModelToAeExpression.elementArgumentToAeExpression( p );
//    }
//    // If originalArg is an AE Parameter already, see if we need to get the
//    // source element to match the type of the parameter of this call's method.
//    if ( paramExpression != null ) {
//      if ( systemModelToAeExpression == null ) {
//        System.err.println("systemModelToAeExpression = null for " + this);
//        return null;
//      }
//      P sourceObject = systemModelToAeExpression.getElementForAeParameter( paramExpression );
//      
//      if ( ClassUtils.isArgumentBetterForType( sourceObject, paramExpression,
//                                               parameterType ) ) {
//        P newArg = sourceObject;
//        Object newEvaluatedArg = evaluateParameterizedArg(true, parameterType, newArg, false, false );
//        return newEvaluatedArg;
//      }
//    }
//    return null;
//  }

  @Override
  public Object evaluateArg( boolean propagate,
                             Class< ? > c,
                             Object unevaluatedArg,
                             boolean isVarArg,
                             boolean complainIfError ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
      return translatedCallHelper.evaluateParameterizedArg( propagate, c, unevaluatedArg, isVarArg, true );
  }
  public Object parentEvaluateArg( boolean propagate, Class< ? > c,
                                   Object unevaluatedArg, boolean isVarArg )
                                        throws ClassCastException,
                                               IllegalAccessException,
                                               InvocationTargetException,
                                               InstantiationException {
    Object evaluatedArg =
        super.evaluateArg( propagate, c, unevaluatedArg, isVarArg, false );
    return evaluatedArg;
  }
//  public Object evaluateParameterizedArg( boolean propagate,
//                                          Class< ? > c,
//                                          Object unevaluatedArg,
//                                          boolean isVarArg,
//                                          boolean parameterize ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
//    Object obj = super.evaluateArg( propagate, c, unevaluatedArg, isVarArg, false );
//    if ( on && parameterize ) {
//      Object parameterizedObj = parameterizeArgument( obj, c );
//      if ( parameterizedObj != null ) return parameterizedObj;
//    }
//    return obj;
//  }
//  
//  protected boolean isParameter( Object o ) {
//    boolean isParam = o instanceof Parameter ||
//        (o instanceof Expression &&
//         ((Expression<?>)o).expression instanceof Parameter);
//    return isParam;
//  }

  
  @Override
  public Object invoke( Object evaluatedObject,  // TODO -- should consider swapping out object, too.
                        Object[] evaluatedArgs )
                            throws IllegalArgumentException,
                            InstantiationException,
                            IllegalAccessException,
                            InvocationTargetException {
    Object result = null;
    boolean triedTwice = false;
    try {
      result = super.invoke( evaluatedObject, evaluatedArgs );
    } catch ( IllegalArgumentException e ) {
      try {
        triedTwice = true;
        result = backupInvoke(evaluatedObject, evaluatedArgs);
      } catch ( Throwable t ) {
        throw e;
      }
    } catch ( InvocationTargetException e ) {
      try {
        triedTwice = true;
        result = backupInvoke(evaluatedObject, evaluatedArgs);
      } catch ( Throwable t ) {
        throw e;
      }
    }
    if ( !triedTwice && !evaluationSucceeded ) {
      result = backupInvoke(evaluatedObject, evaluatedArgs);
    }

    if ( !on ) return result;
    
    // If the result is a Call, add an ArgHelper to it to handle its arguments.
    if ( result instanceof Call && !(result instanceof TranslatedCall ) ) {
      Call call = (Call)result;
      call.argHelper = new ArgHelper() {

        @Override
        public void helpArgs( Call call ) {
          TranslatedCall tCall = translatedCallHelper.makeTranslatedCall( call );
          try {
            ((TranslatedCall)tCall).getTranslatedCallHelper().parameterizeArguments();
            call.setEvaluatedArguments( tCall.getEvaluatedArguments() );
          } catch ( Exception e ) {
            e.printStackTrace();
          }
        }
          
      };
    }
    return result;
  }

  protected Object backupInvoke( Object evaluatedObject,  // TODO -- should consider swapping out object, too.
                                 Object[] evaluatedArgs ) {
    Object result = null;
    boolean didReverse;
    try {
      didReverse = translatedCallHelper.reverseArgs();
      if ( didReverse ) {
        if ( Debug.isOn() ) Debug.getInstance().logForce( "reversed args for " + this );
        evaluatedArgs =
            Arrays.copyOf( evaluatedArguments, evaluatedArguments.length );
        result = super.invoke( evaluatedObject, getEvaluatedArguments() );
      }
    } 
    catch ( ClassCastException e ) {}
    catch ( IllegalAccessException e ) {}
    catch ( InvocationTargetException e ) {} 
    catch ( InstantiationException e ) {}
    return result;
  }

  
  @Override
  public void setStaleAnyReferencesTo(gov.nasa.jpl.ae.event.Parameter<?> changedParameter) {
    translatedCallHelper.setStaleAnyReferencesTo( changedParameter );
    super.setStaleAnyReferencesTo( changedParameter );
  };
  

  @Override
  public Set<Parameter<?>> getParameters(boolean deep, Set<HasParameters> seen) {
    Set<Parameter<?>> parameters = super.getParameters( deep, seen );
    Set<Parameter<?>> more = translatedCallHelper.getTranslatedParameters( deep, seen );
    if ( more != null ) parameters.addAll( more );
    return parameters;
  };
  

  protected void init(SystemModelToAeExpression< ?, ?, P, ?, ?, ? > sysmlToAeExpression ) {
    //this.systemModelToAeExpression = sysmlToAeExpression;
    this.translatedCallHelper = new TranslatedCallHelper< P >( this, sysmlToAeExpression );
  }

  /**
   * @param cls
   */
  public TranslatedConstructorCall( Class< ? > cls,
                                    Class<?> returnType,
                                    SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( cls, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param constructor
   */
  public TranslatedConstructorCall( Constructor< ? > constructor, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( constructor, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param constructorCall
   */
  public TranslatedConstructorCall( ConstructorCall constructorCall, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( constructorCall );
    // If the passed in ConstructorCall is constructing a Call, we probably ought to construct a TranslatedCall.
//    Class<?> c  = constructor.getDeclaringClass();
//    if ( Call.class.isAssignableFrom( c ) ) {
//      
//    }
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param cls
   * @param argumentsA
   * @param nestedCall
   */
  public TranslatedConstructorCall( Object object, Class< ? > cls,
                                    Object[] argumentsA, Call nestedCall, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, argumentsA, nestedCall, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param cls
   * @param argumentsA
   * @param nestedCall
   */
  public TranslatedConstructorCall( Object object, Class< ? > cls,
                                    Object[] argumentsA,
                                    Parameter< Call > nestedCall, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, argumentsA, nestedCall, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param cls
   * @param argumentsA
   */
  public TranslatedConstructorCall( Object object, Class< ? > cls,
                                    Object[] argumentsA, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, argumentsA, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param cls
   * @param arguments
   * @param nestedCall
   */
  public TranslatedConstructorCall( Object object, Class< ? > cls,
                                    Vector< Object > arguments, Call nestedCall, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, arguments, nestedCall, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param cls
   * @param arguments
   * @param nestedCall
   */
  public TranslatedConstructorCall( Object object, Class< ? > cls,
                                    Vector< Object > arguments,
                                    Parameter< Call > nestedCall, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, arguments, nestedCall, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param cls
   * @param arguments
   */
  public TranslatedConstructorCall( Object object, Class< ? > cls,
                                    Vector< Object > arguments, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, arguments, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param cls
   */
  public TranslatedConstructorCall( Object object, Class< ? > cls, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, cls, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param constructor
   * @param argumentsA
   * @param nestedCall
   */
  public TranslatedConstructorCall( Object object,
                                    Constructor< ? > constructor,
                                    Object[] argumentsA,
                                    ConstructorCall nestedCall, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, constructor, argumentsA, nestedCall, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param constructor
   * @param argumentsA
   * @param nestedCall
   */
  public TranslatedConstructorCall( Object object,
                                    Constructor< ? > constructor,
                                    Object[] argumentsA,
                                    Parameter< Call > nestedCall, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, constructor, argumentsA, nestedCall, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param constructor
   * @param argumentsA
   */
  public TranslatedConstructorCall( Object object,
                                    Constructor< ? > constructor,
                                    Object[] argumentsA, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, constructor, argumentsA, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param constructor
   * @param arguments
   * @param nestedCall
   */
  public TranslatedConstructorCall( Object object,
                                    Constructor< ? > constructor,
                                    Vector< Object > arguments, Call nestedCall, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, constructor, arguments, nestedCall, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param constructor
   * @param arguments
   * @param nestedCall
   */
  public TranslatedConstructorCall( Object object,
                                    Constructor< ? > constructor,
                                    Vector< Object > arguments,
                                    Parameter< Call > nestedCall, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, constructor, arguments, nestedCall, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param constructor
   * @param arguments
   */
  public TranslatedConstructorCall( Object object,
                                    Constructor< ? > constructor,
                                    Vector< Object > arguments, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, constructor, arguments, returnType );
    init( systemModelToAeExpression );
  }


  /**
   * @param object
   * @param constructor
   */
  public TranslatedConstructorCall( Object object, Constructor< ? > constructor, Class< ? > returnType, SystemModelToAeExpression<?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    super( object, constructor, returnType );
    init( systemModelToAeExpression );
  }
  
  /**
   * @return the originalArguments
   */
  public Vector< Object > getOriginalArguments() {
    return originalArguments;
  }

  /**
   * @param originalArguments the originalArguments to set
   */
  public void setOriginalArguments( Vector< Object > originalArguments ) {
    this.originalArguments = originalArguments;
  }

  @Override
  public TranslatedCallHelper< ? > getTranslatedCallHelper() {
    return translatedCallHelper;
  }

  /**
   * @return the on
   */
  @Override
  public boolean isOn() {
    return on;
  }

  /**
   * @param on the on to set
   */
  @Override
  public void setOn( boolean on ) {
    this.on = on;
  }

}
