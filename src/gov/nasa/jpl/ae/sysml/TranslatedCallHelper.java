/**
 * 
 */
package gov.nasa.jpl.ae.sysml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;

import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.ConstructorCall;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.ParameterListener;
import gov.nasa.jpl.ae.event.Expression.Form;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.util.ClassData;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;

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
    on = translatedCall.isOn();
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
    if ( !on || true ) { // TODO -- turned off because of problems; instead of
                         // worrying about fixing the output, we always require
                         // fixing the input.
      return result;
    }

    if ( on )  {
      P p = ((P)systemModelToAeExpression.model.asProperty( result ));
      if ( p != null ) {
        Class<?> returnType = (Class< ? >)translatedCall.getReturnType();
        Expression< ? > paramExpression =
            systemModelToAeExpression.elementArgumentToAeExpression( p, returnType );
        if ( paramExpression != null && paramExpression.expression instanceof Parameter ) {
          if (Debug.isOn()) Debug.outln("o o o o o  parameterizeResult(" + result + ") = " + paramExpression);
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
    if (Debug.isOn()) Debug.outln("o o o o o  parameterizeResult(" + result + ") returning argument");
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
    
    replaceCalls();

    Vector< Object > originalArguments = translatedCall.getArguments();
    
    translatedCall.setEvaluatedArguments( translatedCall.evaluateArgs( false ) );
    
    for ( int i = 0; i < originalArguments.size(); ++i ) {
      Object originalArg = originalArguments.get( i );
      Object evaluatedArg = translatedCall.getEvaluatedArguments()[ i ];
      //boolean isVarArg = i >= getParameterTypes().length-1 && isVarArgs();
      Class< ? > parameterType =
          translatedCall.getParameterTypes()[ Math.min( i, translatedCall.getParameterTypes().length - 1 ) ];

      Object newEvaluatedArg = parameterizeArgument( originalArg, evaluatedArg, parameterType );
      if ( newEvaluatedArg != null ) translatedCall.getEvaluatedArguments()[ i ] = newEvaluatedArg;      
    }
  }

  protected void replaceCall(Expression<?> expr) {
    if ( !on ) return;
    Call c = null;
    if ( expr.form == Form.Function ) {
      c = (FunctionCall)expr.expression;
    } else     if ( expr.form == Form.Constructor ) {
      c = (ConstructorCall)expr.expression;
    }
    if ( c != null && c instanceof TranslatedCall ) {
      expr.expression = makeTranslatedCall( c );
    }
  }
  
  protected TranslatedCall makeTranslatedCall( Call c ) {
    TranslatedCall tc = null;
    if ( c instanceof FunctionCall ) {
      tc = new TranslatedFunctionCall< P >( (FunctionCall)c,
                                            systemModelToAeExpression );
      tc.setOn( on );
    } else if ( c instanceof ConstructorCall ) {
      tc = new TranslatedConstructorCall< P >( (ConstructorCall)c,
                                               systemModelToAeExpression );
      tc.setOn( on );
    }
    return tc;
  }

  // NO!!!!! Don't dig it out!!!!  Replace in place!!!!
//  protected TranslatedCall makeTranslatedCall( Expression<?> expr ) {
//    return makeTranslatedCall( expr.expression );
//  }

//  protected TranslatedCall makeTranslatedCall( Object arg ) {
//    if ( arg instanceof Expression ) {
//      return makeTranslatedCall( arg );
//      replaceCall( (Expression< ? >)arg );
//    } else if ( arg instanceof Call && !( arg instanceof TranslatedCall ) ) {
//      replaceCall(i, (Call)arg);
//    } else if ( arg instanceof Parameter ) {
//      Parameter<?> p = (Parameter< ? >)arg;
//      Object o = p.getValueNoPropagate();
//      if ( o )
//    }
//  }  
  
  protected void replaceCall( int i ) {
    if ( !on ) return;
    
    Object arg = translatedCall.getArguments().get( i );
    if ( arg instanceof Expression ) {
      replaceCall( (Expression< ? >)arg );
    } else if ( arg instanceof Call && !( arg instanceof TranslatedCall ) ) {
      replaceCall(i, (Call)arg);
    } else if ( arg instanceof Parameter ) {
      Parameter<Call> p = (Parameter< Call >)arg;
      Object o = p.getValueNoPropagate();
      if ( o instanceof Call && !( o instanceof TranslatedCall ) ) {
        TranslatedCall tc = makeTranslatedCall( (Call)o );
        if ( tc != null ) {
          p.setValue( (Call)tc );
        }
      }
    } else if ( arg instanceof Wraps ) {
      // TODO -- REVIEW -- if this does the same thing as the cases above, then remove the cases above.
      Wraps w = (Wraps)arg;
      Object o = w.getValue( false );
      if ( o instanceof Call && !( o instanceof TranslatedCall ) ) {
        TranslatedCall tc = makeTranslatedCall( (Call)o );
        if ( tc != null ) {
          w.setValue( (Call)tc );
        }
      }
    }
  }
  protected void replaceCalls() {
    if ( !on ) return;
    for ( int i = 0; i < translatedCall.getArguments().size(); ++i ) {
      replaceCall( i );
    }
  }
  
  protected void replaceCall(int i, Call arg) {
    if ( !on ) return;
    translatedCall.getArguments().set( i, makeTranslatedCall( arg ) );
  }

  protected Object parameterizeArgument(Object originalArg, Object evaluatedArg,
                                        Class< ? > parameterType  )
      throws ClassCastException, IllegalAccessException, InvocationTargetException,
             InstantiationException {

    if ( !on ) return evaluatedArg;

    String callName =
        translatedCall == null ? "null" : ( (Call)translatedCall ).getName();
    if ( Debug.isOn() ) Debug.outln( "SSSS parameterizeArgument(" + callName
                                     + ", " + originalArg + ", " + evaluatedArg
                                     + ", " + parameterType + ")" );
    if ( originalArg == null ) return null;

    if ( originalArg instanceof Expression && ( (Expression)originalArg ).form == Form.Parameter ) {
      originalArg = ( (Expression)originalArg).expression;
    }
    if ( originalArg instanceof Parameter ) {
      Parameter p = (Parameter)originalArg;
      if ( systemModelToAeExpression.model.getElementClass().isInstance( p.getValue() ) ) {
        originalArg = p.getValue();
      }
    }
    
    boolean propertyTypeIsNonSpecific =
        parameterType == null || parameterType.equals( Object.class )
            || Expression.class.isAssignableFrom( parameterType );

    // Return the element if appropriate.
    if ( !propertyTypeIsNonSpecific ) {
//         && parameterType.equals( systemModelToAeExpression.model.getElementClass() ) ) {
        if ( parameterType.isInstance( evaluatedArg ) ) {
        if ( Debug.isOn() ) Debug.outln( "FFFF parameterizeArgument("
                                         + callName + ", " + originalArg + ", "
                                         + evaluatedArg + ", " + parameterType
                                         + ") returning evaluatedArg: "
                                         + evaluatedArg );
          return evaluatedArg;
        }
        if ( parameterType.isInstance( originalArg ) ) {
        if ( Debug.isOn() ) Debug.outln( "FFFF parameterizeArgument("
                                         + callName + ", " + originalArg + ", "
                                         + evaluatedArg + ", " + parameterType
                                         + ") returning originalArg: "
                                         + originalArg );
          return originalArg;
        }
    }
    
    // See if the argument wraps the right kind of value and, if so, return it.
    if ( !propertyTypeIsNonSpecific && evaluatedArg != null
         && evaluatedArg instanceof Wraps ) {
      Class<?> argType = ((Wraps)evaluatedArg).getType();
      if ( argType != null && parameterType.isAssignableFrom( argType ) ) {
        if ( Debug.isOn() ) Debug.outln( "FFFF parameterizeArgument("
                                         + callName + ", " + originalArg + ", "
                                         + evaluatedArg + ", " + parameterType
                                         + ") returning Wraps evaluatedArg: "
                                         + evaluatedArg );
        return evaluatedArg;
      }
    }
    
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
      return evaluatedArg;
//      Collection<?> c = (Collection<?>)evaluatedArg;
//      if ( !c.isEmpty() ) {
//        ArrayList<Object> arr = new ArrayList< Object >();
//        for ( Object o : c ) {
//          arr.add( parameterizeArgument( o, o, Object.class ) );
//        }
//        evaluatedArg = arr;
//      }
    } else if ( evaluatedArg != null && evaluatedArg.getClass().isArray() ) {
      return evaluatedArg;
//      Object[] c = (Object[])evaluatedArg;
//      if ( c.length > 0 ) {
//        Object[] arr = new Object[c.length];
//        for ( int i = 0; i < c.length; i++ ) {
//          arr[i] = parameterizeArgument( c[i], c[i], Object.class );
//        }
//        evaluatedArg = arr;
//      }
    } else {
      if ( systemModelToAeExpression == null ) {
        System.err.println("systemModelToAeExpression = null for " + this);
        if (Debug.isOn())
          Debug.outln( "FFFF parameterizeArgument(" + callName + ", "
                       + originalArg + ", " + evaluatedArg + ", "
                       + parameterType + ") returning null" );
        return null;
      } else if ( systemModelToAeExpression.model == null ) {
        System.err.println("systemModelToAeExpression.model = null for " + this);
        if (Debug.isOn())
          Debug.outln( "FFFF parameterizeArgument(" + callName + ", "
                       + originalArg + ", " + evaluatedArg + ", " 
                       + parameterType + ") returning null" );
        return null;
      }

      Call call = null;
      if ( translatedCall instanceof Call ) {
        call = (Call)translatedCall;
      }
      P p = systemModelToAeExpression.model.asProperty( evaluatedArg );
      if ( p != null ) {
        Class<?> returnType = (Class< ? >)translatedCall.getReturnType();
        paramExpression =
            systemModelToAeExpression.elementArgumentToAeExpression( p, returnType );
        if ( paramExpression != null && paramExpression.expression instanceof Parameter ) {
          parameter = (Parameter< ? >)paramExpression.expression;
          if ( parameter != null ) {
            parameterValue = parameter.getValueNoPropagate();
            if ( parameterValue != null ) { // already checked for element type --> && parameterType != systemModelToAeExpression.model.getElementClass() ) {
              boolean isAeCall = false;
              if ( call != null && call.getMember() != null ) {
                isAeCall =
                    call.getMember().getDeclaringClass().getPackage().getName()
                        .contains( "gov.nasa.jpl.ae" );          
              }
              if ( isAeCall ) {
                if ( Expression.class.isAssignableFrom( parameterType ) ) {
                  if ( Debug.isOn() )
                    Debug.outln( "FFFF parameterizeArgument("
                                 + callName + ", " + originalArg + ", "
                                 + evaluatedArg + ", " + parameterType
                                 + ") returning paramExpression for AE call: "
                                 + paramExpression );
                  return paramExpression;
                } else {
                  if ( Debug.isOn() )
                    Debug.outln( "FFFF parameterizeArgument("
                                 + callName + ", " + originalArg + ", "
                                 + evaluatedArg + ", " + parameterType
                                 + ") returning parameter for AE call: "
                                 + parameter );
                  return parameter;
                }
              }
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
      if ( result != null ) {
        if ( Debug.isOn() ) Debug.outln( "FFFF parameterizeArgument("
                                         + callName + ", " + originalArg + ", "
                                         + evaluatedArg + ", " + parameterType
                                         + ") returning best arg: " + result );
        return result;
      }
    }
    if ( Debug.isOn() ) Debug.outln( "FFFF parameterizeArgument(" + callName
                                     + ", " + originalArg + ", " + evaluatedArg
                                     + ", " + parameterType
                                     + ") returning default evaluatedArg: "
                                     + evaluatedArg );
    return evaluatedArg;
  }
  
  public Object evaluateParameterizedArg( boolean propagate,
                                          Class< ? > c,
                                          Object unevaluatedArg,
                                          boolean isVarArg,
                                          boolean parameterize ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Object evaluatedArg =
        translatedCall.parentEvaluateArg( propagate, c, unevaluatedArg,
                                          isVarArg );
    if ( !on ) return evaluatedArg;
    
    if ( Debug.isOn() ) {
      String msg =
          "###  ###  ###  parentEvaluateArg(" + unevaluatedArg + ", " + c
              + ") = " + evaluatedArg;
      Debug.outln(msg );
    }
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
  
  /**
   * This checks to see if any argument is the element corresponding to this Parameter.  
   * @param changedParameter
   */
  public <T> void setStaleAnyReferencesTo( Parameter< T > changedParameter ) {
    if ( !on ) return;

    // get element for parameter if it exists
    P sourceObject =
        systemModelToAeExpression.getElementForAeParameter( new Expression<T>( changedParameter ) );

    if ( sourceObject == null ) return;
    
    Vector< Object > args = translatedCall.getArguments();
    for ( Object arg : args ) {
      if ( sourceObject.equals( arg ) ) {
        ((Call)translatedCall).setStale( true );
      }
    }
  }

  private Set< P > getProperties() {
    Set< P > properties = new LinkedHashSet< P >();
    Vector< Object > args = translatedCall.getArguments();
    for ( Object arg : args ) {
      if ( systemModelToAeExpression.model.getPropertyClass().isInstance( arg ) ) {
        properties.add( (P)arg );
      }
    }
    return properties;
  }


}
