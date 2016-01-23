package gov.nasa.jpl.ae.sysml;

import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

public interface TranslatedCall {

  /**
   * @return the evaluatedArguments
   */
  public Object[] getEvaluatedArguments();
  
  /**
   * @param evaluatedArguments the evaluatedArguments to set
   */
  public void setEvaluatedArguments( Object[] evaluatedArguments );
  
  /**
   * @return the arguments
   */
  public Vector< Object > getArguments();

  
  public Object evaluate( boolean propagate, boolean doEvalArgs )
                                                   throws IllegalAccessException,
                                                   InvocationTargetException,
                                                   InstantiationException;
  /**
   * Make sure the arguments passed into the function are replaced with their
   * corresponding Parameters where appropriate. It is appropriate to replace an
   * argument if the class of the argument is incompatible with the
   * corresponding parameter type of the function.  If the translated parameter , and the 
   * 
   * @param result
   * @return
   */
  //public Object parameterizeResult( Object result );

  /**
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws ClassCastException 
   * 
   */
//  public void parameterizeArguments() throws ClassCastException,
//                                             IllegalAccessException,
//                                             InvocationTargetException,
//                                             InstantiationException;
//
//  public Object parameterizeArgument(Object originalArg, Object evaluatedArg,
//                                     Class< ? > parameterType  )
//                                         throws ClassCastException, 
//                                         IllegalAccessException,
//                                         InvocationTargetException,
//                                         InstantiationException;
  
  public Object evaluateArg( boolean propagate,
                             Class< ? > c,
                             Object unevaluatedArg,
                             boolean isVarArg,
                             boolean complainIfError )
                                 throws ClassCastException,
                                        IllegalAccessException,
                                        InvocationTargetException,
                                        InstantiationException;

  public Object parentEvaluateArg( boolean propagate, Class< ? > c,
                                   Object unevaluatedArg, boolean isVarArg )
                                       throws ClassCastException,
                                              IllegalAccessException,
                                              InvocationTargetException,
                                              InstantiationException;

  
//  public Object evaluateParameterizedArg( boolean propagate,
//                                          Class< ? > c,
//                                          Object unevaluatedArg,
//                                          boolean isVarArg,
//                                          boolean parameterize )
//                                              throws ClassCastException,
//                                              IllegalAccessException,
//                                              InvocationTargetException,
//                                              InstantiationException;
//
//  public Object evaluateParameterizedArg( boolean propagate,
//                                          Class< ? > c,
//                                          Object unevaluatedArg,
//                                          Object evaluatedArg,
//                                          boolean isVarArg,
//                                          boolean parameterize )
//                                              throws ClassCastException,
//                                                     IllegalAccessException,
//                                                     InvocationTargetException,
//                                                     InstantiationException;

  public Object[] evaluateArgs( boolean propagate )
                                  throws ClassCastException,
                                         IllegalAccessException,
                                         InvocationTargetException,
                                         InstantiationException;

  public Class< ? >[] getParameterTypes();

}
