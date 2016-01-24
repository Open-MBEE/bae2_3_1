/**
 * 
 */
package gov.nasa.jpl.ae.sysml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
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
 * A {@link TranslatedCallHelper} tries to smartly swap between using some {@link Object} or
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
 * solving even though the element value does not.  The {@link TranslatedCallHelper}
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
public class TranslatedCallHelper<P> {

  public boolean on = true;
  
  //protected ClassData _classData = null;
  //protected Vector<Object> originalArguments = null;
  protected SystemModelToAeExpression< ?, ?, P, ?, ?, ? > systemModelToAeExpression = null;
  protected TranslatedCall translatedCall = null;
  
  public TranslatedCallHelper( TranslatedCall translatedCall,
                               //Vector<Object> originalArguments,
                               SystemModelToAeExpression< ?, ?, P, ?, ?, ? > systemModelToAeExpression ) {
    this.translatedCall = translatedCall;
    //this.originalArguments = originalArguments;
    this.systemModelToAeExpression = systemModelToAeExpression;
  }

  public ClassData classData() {
    if ( systemModelToAeExpression == null ) return null;
    return systemModelToAeExpression.classData;
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
      P p = systemModelToAeExpression.model.asProperty( result );
      if ( p != null ) {
        Expression< ? > paramExpression = systemModelToAeExpression.elementArgumentToAeExpression( p );
        if ( paramExpression != null && paramExpression.expression instanceof Parameter ) {
          return paramExpression;
        }
      }
//
//      Object e = systemModelToAeExpression.model.asElement( result );
//      if ( systemModelToAeExpression.model.getElementClass().isInstance( e ) ) {
//        
//      }
//      Parameter< Object > parameter = systemModelToAeExpression.getExprParamMap().get( result );
//      if ( parameter != null ) return parameter;
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
    if ( !on || systemModelToAeExpression == null ) return;
//    translatedCall.setOriginalArguments(translatedCall.getArguments());
//    Vector< Object > originalArguments = translatedCall.getOriginalArguments();
    Vector< Object > originalArguments = translatedCall.getArguments();

    translatedCall.setEvaluatedArguments( translatedCall.evaluateArgs( false ) );
    
    for ( int i = 0; i < originalArguments.size(); ++i ) {
      Object originalArg = originalArguments.get( i );
      Object evaluatedArg = translatedCall.getEvaluatedArguments()[ i ];
      //boolean isVarArg = i >= getParameterTypes().length-1 && isVarArgs();
      Class<?> parameterType = translatedCall.getParameterTypes()[ Math.min(i, translatedCall.getParameterTypes().length-1)];

      Object newEvaluatedArg = parameterizeArgument( originalArg, evaluatedArg, parameterType );
      if ( newEvaluatedArg != null ) translatedCall.getEvaluatedArguments()[ i ] = newEvaluatedArg;      
    }
  }

  protected Object parameterizeArgument(Object originalArg, Object evaluatedArg,
                                        Class< ? > parameterType  )
      throws ClassCastException, IllegalAccessException, InvocationTargetException,
             InstantiationException {
    if ( originalArg == null ) return null;
    Parameter<?> parameter = null;
    Expression<?> paramExpression = null;
    Object parameterValue = null;
    
    if ( originalArg instanceof Parameter ) {
      parameter = (Parameter<?>)originalArg;
      paramExpression = new Expression< Object >( parameter );
    } else if ( originalArg instanceof Expression &&  
                ((Expression<?>)originalArg).expression instanceof Parameter ) {
      parameter = (Parameter< ? >)((Expression<?>)originalArg).expression;
      paramExpression = (Expression<?>)originalArg;
    } else if ( evaluatedArg instanceof Parameter ) {
        parameter = (Parameter<?>)evaluatedArg;
        paramExpression = new Expression< Object >( parameter );
    } else if ( evaluatedArg instanceof Expression &&  
                ((Expression<?>)evaluatedArg).expression instanceof Parameter ) {
        parameter = (Parameter< ? >)((Expression<?>)evaluatedArg).expression;
        paramExpression = (Expression<?>)evaluatedArg;
    } else if ( evaluatedArg instanceof Collection ) {
      Collection<?> c = (Collection<?>)evaluatedArg;
      if ( !c.isEmpty() ) {
        ArrayList<Object> arr = new ArrayList< Object >();
        for ( Object o : c ) {
          arr.add( parameterizeArgument( o, o, Object.class ) );
        }
        evaluatedArg = arr;
      }
    } else if ( evaluatedArg != null && evaluatedArg.getClass().isArray() ) {
      Object[] c = (Object[])evaluatedArg;
      if ( c.length > 0 ) {
        Object[] arr = new Object[c.length];
        for ( int i = 0; i < c.length; i++ ) {
          arr[i] = parameterizeArgument( c[i], c[i], Object.class );
        }
        evaluatedArg = arr;
      }
    } else {
      if ( systemModelToAeExpression == null ) {
        System.err.println("systemModelToAeExpression = null for " + this);
        return null;
      } else if ( systemModelToAeExpression.model == null ) {
        System.err.println("systemModelToAeExpression.model = null for " + this);
        return null;
      }

      P p = systemModelToAeExpression.model.asProperty( evaluatedArg );
      if ( p != null ) {
        paramExpression = systemModelToAeExpression.elementArgumentToAeExpression( p );
        if ( paramExpression != null && paramExpression.expression instanceof Parameter ) {
          parameter = (Parameter< ? >)paramExpression.expression;
          if ( parameter != null ) {
            parameterValue = parameter.getValueNoPropagate();
            if ( parameterValue != null ) {
              return parameter;
            }
          }
        }
      }
    }
    // If originalArg is an AE Parameter already, see if we need to get the
    // source element to match the type of the parameter of this call's method.
    if ( paramExpression != null ) {
      //if ( parameter != null && parameter.toString().contains( "orkspace" ) ) {
        //Debug.error( "-->>  -->>  -->>  -->>  Got a strange parameter: " + parameter );
      //}
      if ( parameter != null && parameterValue == null ) {
        parameterValue = parameter.getValueNoPropagate();
      }
      if ( systemModelToAeExpression == null ) {
        System.err.println("systemModelToAeExpression = null for " + this);
        return null;
      }
      P sourceObject = systemModelToAeExpression.getElementForAeParameter( paramExpression );
      
      Object newEvaluatedArg =
          evaluateParameterizedArg( true, parameterType, parameter, false, false );

      Object result =
          ClassUtils.bestArgumentForType( Utils.newList( evaluatedArg,
                                                         sourceObject,
                                                         newEvaluatedArg,
                                                         paramExpression,
                                                         parameter,
                                                         parameterValue,
                                                         originalArg ),
                                          parameterType );
      if ( Debug.isOn() ) {
        Debug.outln( "\n% % % % %    return bestArgumentForType(evaluatedArg="
                     + evaluatedArg + ", sourceObject=" + sourceObject
                     + ", newEvaluatedArg=" + newEvaluatedArg
                     + ", paramExpression=" + paramExpression + ", parameter="
                     + parameter + ", parameterValue=" + parameterValue
                     + ", originalArg=" + originalArg + "), " + parameterType
                     + ") = " + result + "    % % % % %\n");
      }
      if ( result != null ) return result;
    }
    return evaluatedArg;
  }
  
  public Object evaluateParameterizedArg( boolean propagate,
                                          Class< ? > c,
                                          Object unevaluatedArg,
                                          boolean isVarArg,
                                          boolean parameterize ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Object evaluatedArg = translatedCall.parentEvaluateArg( propagate, c, unevaluatedArg, isVarArg );
    if ( on && parameterize ) {
      Object parameterizedObj = parameterizeArgument( unevaluatedArg, evaluatedArg, c );
      if ( parameterizedObj != null ) return parameterizedObj;
    }
    return evaluatedArg;
  }
  
  
  protected boolean isParameter( Object o ) {
    boolean isParam = o instanceof Parameter ||
        (o instanceof Expression &&
         ((Expression<?>)o).expression instanceof Parameter);
    return isParam;
  }

}
