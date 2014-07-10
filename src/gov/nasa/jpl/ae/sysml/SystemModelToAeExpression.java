package gov.nasa.jpl.ae.sysml;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.util.ClassData;
import gov.nasa.jpl.ae.util.JavaToConstraintExpression;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Utils;
import sysml.SystemModel;

public class SystemModelToAeExpression< T, P, N, U, SM extends SystemModel< ?, ?, T, P, N, ?, U, ?, ?, ?, ? > > {
    
    protected SM model = null;
    protected ClassData classData = new ClassData();
    
    /**
     * Maps the parsed Expression Parameters to the Parameter objects
     * we create for them.
     */
    private Map<P, Parameter<Object>> exprParamMap = new HashMap<P,Parameter<Object>>();

    public SystemModelToAeExpression(SM model) {
        setModel(model);
    }

    /**
     * Converts the passed sysml expression to an Ae expression, and
     * returns its evaluation.
     * 
     * @param expressionElement The sysml expression to convert/evaluate
     * @return The evaluated AE converted expression
     */
    public <X> X evaluateExpression( Object expressionElement ) {
      Expression<X> expression = toAeExpression( expressionElement );
      return expression.evaluate( true );
    }
    
    /**
     * Return a Call object based on the passed operation and arguments
     * 
     * @param operationName The name of operation used to search for the
     *                      equivalent java call
     * @param arguments The arguments for operation
     * @return Call object or null if the operationName is not a java call
     */
    private Call createCall(N operationName, Vector< Object > arguments) {
      
      Call call = null;
      Method method = null;
      Object object = null;

      /*
       * We will look for the corresponding Constructor or FunctionCall in
       * the following order:
       * 
       * 1. The current model class, ie EmsSystemModel (assume its a FunctionCall)
       * 2. The view_repo.syml package (assume its a ConstructorCall or FunctionCall)
       * 3. The Functions.java and ae.event package (assume its a ConstructorCall)
       * 4. Common Java classes (assume its a FunctionCall)
       * 5. The mbee.util package (assume its a FunctionCall) 
       * 
       */
      if ( operationName != null ) {
                  
        ArrayList<Class<?>> argTypes = new ArrayList<Class<?>>();
        
        // Finding out the argument types:
        for (Object arg : arguments) {
          
          if (arg instanceof Expression) {
            argTypes.add( ((Expression<?>)arg).getType());
          }
          else {
            Debug.error( "Expecting an Expression for the argument: " + arg );
          }
        }
        
        // 1.
        method = ClassUtils.getMethodForArgTypes( model.getClass(),
                                                  operationName.toString(),
                                                  argTypes.toArray(new Class[]{}));
        
        if ( method != null ) {
          object = model;
        }
        else {
          // 2.
          call = JavaToConstraintExpression.javaCallToEventFunction(operationName.toString(),
                                                                    arguments,
                                                                    argTypes.toArray(new Class[]{}));

          if (call == null) {
            //3.
            if ( arguments.size() == 1 ) {
                call = JavaToConstraintExpression.unaryOpNameToEventFunction( operationName.toString() );
            } 
            else if ( arguments.size() == 2 ) {
                call = JavaToConstraintExpression.binaryOpNameToEventFunction( operationName.toString() );
            } 
            else if ( arguments.size() == 3
                        && operationName.toString().equalsIgnoreCase( "if" ) ) {
                call = JavaToConstraintExpression.getIfThenElseConstructorCall();
            }
            
            if ( call != null ) {
              call.setArguments( arguments );
            } 
            else {
              // 4.
              method = ClassUtils.getJavaMethodForCommonFunction( operationName.toString(),
                                                                  arguments.toArray() );
            
              // 5.
              if (method == null) {
                // TODO implement checking the mbee.util package
              }
            
            }
            
          } // Ends call == null

        }
        
        if ( call == null && method == null ) {
          // TODO -- if it *still* fails, maybe search through all classes of all
          // packages for a method with this name.
        }
        
        // Make the FunctionCall if it was not a ConstructorCall:
        if ( method != null ) {
          call = new FunctionCall( object, method, arguments );
        }
        
      } // end if operationName != null
      
      return call;
    }
    
    /**
     * Creates a Parameter out of the passed argValueNode and adds it to
     * arguments
     * 
     * @param argValueNode The node to create a Parameter object for
     * @param argValName The name to use for the created Parameter
     * @param arguments The argument list to add the created Parameter to
     */
    private void addParametertoArgs(Object argValueNode, String argValName,
                                    Vector<Object> arguments) {
      arguments.add( elementValueToAeExpression( argValueNode, argValName ) );
    }
    
    private Expression<?> elementValueToAeExpression(Object argValueNode,
                                                     String argValName ) {
      String argType = null;
      Parameter<Object> param = null;
      Collection<U> argValPropNodes = null;
      Object argValProp = null;

      // Get the value of the argument based on type:
      if (model.getTypeString(argValueNode, null).equals("LiteralInteger")) {
        
        argValPropNodes = model.getValue(argValueNode, "integer");
        argType = "Integer";
      }
      else if (model.getTypeString(argValueNode, null).equals("LiteralReal")) {
        
        argValPropNodes = model.getValue(argValueNode, "double");
        argType = "Double";
      }
      else if (model.getTypeString(argValueNode, null).equals("LiteralBoolean")) {
        
        argValPropNodes = model.getValue(argValueNode, "boolean");
        argType = "Boolean";
      }
      else if (model.getTypeString(argValueNode, null).equals("LiteralUnlimitedNatural")) {
        
        argValPropNodes = model.getValue(argValueNode, "naturalValue");
        argType = "Integer";
      }
      else if (model.getTypeString(argValueNode, null).equals("LiteralString")) {
        
        argValPropNodes = model.getValue(argValueNode, "string");
        argType = "String";
      }
      // Otherwise, will just set the value of the parameter to the node itself:
      else {
          argValProp = argValueNode;
          argType = "Object";
      }
      
      if (!Utils.isNullOrEmpty(argValPropNodes)) {
        
        // TODO can we assume this is always size 1?  
        if ( argValPropNodes.size() == 1 ) {
          argValProp = argValPropNodes.iterator().next();
        } else {
          argValProp = argValPropNodes;
        }
        Debug.outln( "\nargValProp = " + argValProp );
      }
      
      // Wrap the argument in a Parameter:
      // Note: argType can be null
      param = (Parameter<Object>)classData.getParameter( null, argValName, argType, false, true, true, false );

      // Set value of the param to value if it has one,
      // and add to the argument list:
      if (param != null) {
        
        Debug.outln( "\nparam = " + param );
        if (argValProp != null) {
          if ( Collection.class.isAssignableFrom( param.getType() ) ) {
            param.setValue(argValPropNodes);
          } else {
            param.setValue(argValProp);
          }
        }
        return new Expression<Object>(param);
      }
      
      // Creating param failed, so just add the value object itself wrapped in an
      // Expression:
      else {
        return new Expression<Object>(argValProp);
      }
      
    }
     
    /**
     * Parses the passed sysml expression for a operationName and arguments
     * Adds the parsed arguments to the passed arguments list.
     * 
     * @param expressionElement The sysml expression to parse
     * @param arguments Adds the parsed arguments to this list
     * @return The found operationName
     */
    private N parseExpression(Object expressionElement, Vector<Object> arguments) {
      
      // running example !:
      //   Viewpoint vp exposes exposed and has an Operation vp_op(foo), which 
      //   is defined as the Expression, List(map(foo, Name)).
      // 
      //   1st call: List = parseExpression(List(map(foo, Name), [map(foo, Name)])
      //   2nd call: map = parseExpression( map(foo, Name), [foo, Name])
      
      N operationName = null;
      
      // Get all operand properties of the element
      // TODO: should be using Acm.JSON_OPERAND in getProperty but they have Acm in view_repo.util package
      Collection< P > properties = model.getProperty( expressionElement, "operand");
      //Debug.outln( "toAeExpression(" + expressionElement + " ): operands = "+ properties );
      
      if (!Utils.isNullOrEmpty(properties)) {
    
        // Loop through all of the operand property values to find the operand arguments
        // and Operation:
          
        // Loop through the operand array backwards so that we process the operation
        // name last, in case it has operandParameters that we need to set the value
        // of.  
        // TODO REVIEW  the above strategy will only work for operand args that
        //      are also Operations with operandParameters if it is precedes
        //      the other args that we are using for parameter values in the
        //      operand list.
        //
        //  Looping backwards causes issues b/c all the operators expect their
        //  args in the normal order.  Looping through the properties twice and
        //  processing the Operation types on the second loop through will only 
        //  work the operand names, but not operand args b/c of the same ordering
        //  issue.  
        //
        //List<P> propertyList = Utils.asList( properties );
        //ListIterator<P> iterator = propertyList.listIterator( propertyList.size() );
        //P operandProp = null;
          
        P valueOfElementNode = null;
        //while (iterator.hasPrevious()) {
        for (P operandProp : properties) {
           
          //operandProp = iterator.previous();

          // Get the valueOfElementProperty node:
          Collection< P > valueOfElemNodes = 
                  model.getProperty(operandProp, "elementValueOfElement");
          
          // If it is a elementValue, then this will be non-empty:
          if (!Utils.isNullOrEmpty(valueOfElemNodes)) {
                          
            // valueOfElemNodes should always be size 1 b/c elementValueOfElement
            // is a single NodeRef
            valueOfElementNode = valueOfElemNodes.iterator().next();
            
          }
          
          // Otherwise just use the node itself as we are not dealing with
          // elementValue types:
          else {
            valueOfElementNode = operandProp;
          }
          
          if (valueOfElementNode != null) {
            
            String typeString = model.getTypeString(valueOfElementNode, null);
            
            // If it is a Operation type then get the operator name:
            if (typeString.equals("Operation")) {
                            
              // Get the operationName if have not already.  
              // This assumes that the first operandProp
              // is the operation name, as the arguments can be Operations
              // also.
              if (operationName == null) {
              //if (!iterator.hasPrevious()) {
                  
                  // running example !:
                  //   Viewpoint vp exposes exposed and has an Operation vp_op(foo), which 
                  //   is defined as the Expression, List(map(foo, Name)).
                  // 
                  //   1st call: List = parseExpression(List(map(foo, Name), [map(foo, Name)])
                  //   2nd call: map = parseExpression( map(foo, Name), [foo, Name])
                  //
                  //   Below, for 1st call, List = processOperation(List, [], false)
                  //          for 2nd call, map = processOperation(map, [], false) 

                  operationName = processOperation(valueOfElementNode,
                                                   arguments, false);
                  Debug.outln( "\noperationName = " + operationName);
                  
                  if (operationName == null) {
                    Debug.error("Error getting operation name!");
                    return null;
                  }

              }
              // Otherwise, the Operation is an argument (of an already found
              // Operation):
              else {
                                  
                  // running example 1:
                  //   Viewpoint vp exposes exposed and has an Operation vp_op(foo), which 
                  //   is defined as the Expression, List(map(foo, Name)).
                  // 
                  //   1st call: List = parseExpression(List(map(foo, Name), [map(foo, Name)])
                  //   2nd call: map = parseExpression( map(foo, Name), [foo, Name])
                  //
                  //   Below, for 1st call, map = processOperation(map(foo, Name), [], true)

                  N opArgName = processOperation(valueOfElementNode,
                                                 arguments, true);
                  Debug.outln( "\nopArgName = " + opArgName);
                             
              } // Ends else (the Operation is an argument)
              
            } // Ends if type is an Operation
            
            // If it is a Expression type then process that Expression
            // and add to argument list:
            else if (typeString.equals("Expression")) {
              
              // running example 1:
              //   Viewpoint vp exposes exposed and has an Operation vp_op(foo), which 
              //   is defined as the Expression, List(map(foo, Name)).
              // 
              //   1st call: List = parseExpression(List(map(foo, Name), [map(foo, Name)])
              //
              //   Below, map(foo, Name) = toAeExpression(map(foo, Name))

              arguments.add(toAeExpression(valueOfElementNode));
            }
            
            // If it is a Parameter then add it the map for later use,
            // and create a Parameter for it:
            else if (typeString.equals("Parameter")) {
            
              Parameter<Object> param = null;

              // Get the name of the argument Node:
              Collection<N > argValueNames = model.getName(valueOfElementNode);
              String argValName = Utils.isNullOrEmpty(argValueNames) ? null : 
                                                                       argValueNames.iterator().next().toString();
              
              // TODO get the parameterType property and set it the Parameter type
              
              // Wrap the argument in a Parameter:
              param = (Parameter<Object>)classData.getParameter( null, argValName, false, true, true, false );
              
              if (param != null) {
                // Add to argument list:
                arguments.add(new Expression<Object>(param));
                
                // Add to map:
                exprParamMap.put( valueOfElementNode, param );
              }
              
            }
                        
            // Its a Property command arg, so get the argument values:
            else if (typeString.equals("Property")) {
              
              Parameter<Object> param = null;
              
              // Get the argument Node:
              Collection<U > argValueNodes = model.getValue(valueOfElementNode, null);
  
              // Get the name of the argument Node:
              Collection<N > argValueNames = model.getName(valueOfElementNode);
              String argValName = Utils.isNullOrEmpty(argValueNames) ? null : 
                                                                       argValueNames.iterator().next().toString();
              
              // If the argument node has a value:
              if (!Utils.isNullOrEmpty(argValueNodes)) {
                
                // TODO can we assume this will always be size one?
                Object argValueNode = argValueNodes.iterator().next();
                Debug.outln( "\nargValueNode = " + argValueNode );
                
                // Create a Parameter for the argument and add to arguments:
                addParametertoArgs(argValueNode, argValName, arguments);
                
              } // ends !Utils.isNullOrEmpty(argValueNodes)
              
              // Argument node does not have a value, so just add to argument list 
              // for the operator with no set value:
              else {
                
                // Wrap the argument in a Parameter:
                param = (Parameter<Object>)classData.getParameter( null, argValName, false, true, true, false );
                
                if (param != null) {
                  arguments.add(new Expression<Object>(param));
                }
                
              } // ends else argument node does not have a value
  
            } // ends else if it is Property (ie a command arg)
            
            // All other cases failed, then just create a Parameter for
            // it, ie it is plain ole Element, a LiteralInt, etc:
            else {
              
              // Get the name of the argument Node:
              Collection<N > argValueNames = model.getName(valueOfElementNode);
              String argValName = Utils.isNullOrEmpty(argValueNames) ? null : 
                                                                       argValueNames.iterator().next().toString();
              
              // Create a Parameter for the argument and add to arguments:
              addParametertoArgs(valueOfElementNode, argValName, arguments);
            }
                       
          } // ends if valueOfElementNode != null
          
        } // ends for loop through all the operand property values
             
      } // ends !Utils.isNullOrEmpty(properties)
      
      // Return the found operation name:
      return operationName;
    }
    
    /**
     * Processes the passes Operation element and adds
     * to the argument list
     * 
     */
    private N processOperation(P valueOfElementNode,
                               Vector<Object> arguments,
                               boolean isOperandArg) {
        
        // running example !:
        //   Viewpoint vp exposes exposed and has an Operation vp_op(foo), which 
        //   is defined as the Expression, List(map(foo, Name)).
        // 
        //   1st call: List = processOperation(List, [], false)
        //   2nd call: map = processOperation(map, [], false)
        //   3rd call: map = processOperation(map, [], true)
      
        N operationName = null;
        Collection<N> operNames = model.getName(valueOfElementNode);
        
        // TODO should we add to the arguments list even if its
        // the operand operation name and not a operand arg?
        // ie get_names.  No!  how should we handle this.
        // quick fix is below, not quite right b/c at times
        // we will need to add the arg list, this is when the
        // operantionName is not a java call but just a model element.
        // Perhaps do the createCall() checks on it in here to check?
        // Thats not very robust.

        if (!Utils.isNullOrEmpty(operNames)) {

            operationName = operNames.iterator().next();
            
            // Only add to argument list if its a operand arg:
            if (isOperandArg) {
                
                Collection<P> opExpProps = model.getProperty( valueOfElementNode, 
                                                              "expression" );
                Collection<P> opParamProps = model.getProperty( valueOfElementNode, 
                                                               "parameter" );
                
                // If the Operation has no expression then 
                // make a Call for it:
                if (Utils.isNullOrEmpty( opExpProps )) {
                    
                    Vector<Object> opEmptyArgs = new Vector<Object>();
    
                    // If it has parameter make a empty arg
                    // for each parameter:
                    if (!Utils.isNullOrEmpty( opParamProps )) {
                        
                        for (int i = 0; i < opParamProps.size(); ++i) {
                            opEmptyArgs.add( new Expression< Object >( (Object)null ) );
                        }
                    }
                    
                    // Create a Call for the argument 
                    Call argCall = createCall(operationName, opEmptyArgs);
                    
                    if ( argCall != null ) {
                      
                      // Add to the argument list:
                      arguments.add(new Expression<Object>(argCall));
            
                    } // Ends if argCall != null
                      
                }
                
                // TODO FIXME this is no longer correct below:
                // If the Operation has a expression then
                // use operationToAeExpression to process
                else {
                  
                  
                  Debug.error("Error: dont know how to process a Operation arg with a expression!");

//                    List<Object> parameterValues = new ArrayList<Object>();
//                    
//                    // If it has parameters then pass in the
//                    // parameter values:
//                    if (!Utils.isNullOrEmpty( opParamProps )) {
//                        // If the argument is a Parameter (wrapped in an expression),
//                        // then get its value:
//                        for (Object arg : arguments) {
//                            if (arg instanceof Expression) {
//                                Expression<?> expr = (Expression<?>)arg;
//                                
//                                if (expr.form.equals(Expression.Form.Parameter)) {
//                                    Parameter<?> param = (Parameter<?>)expr.expression;
//                                    parameterValues.add( param.getValue() );
//                                }
//                            }
//                        }
//                    }
//                    
//                    arguments.add( operationToAeExpression(valueOfElementNode,
//                                                           parameterValues));
//                
                }
            
            } // ends if operand arg
         
        }
        
        return operationName;
    }
    
    
    /**
     * Get a name for the operation whether an Operation element, a String, or a
     * name of type N.
     * 
     * @param operation
     * @return
     * @throws ClassCastException
     */
    N getOperationName( Object operation ) throws ClassCastException {
      if ( operation == null ) return null;
      N operationName = null;
      Collection< N > operationNames = model.getName( operation );
      if ( !Utils.isNullOrEmpty( operationNames  ) ) {
        operationName = operationNames.iterator().next();
      } else {
        try {
          operationName = (N)operation;
        } catch ( ClassCastException ignore ) {
          operationName = (N)("" + operation); // this may throw ClassCastException
        }
      }
      return operationName;
    }
    
    /**
     * Converts the passed sysml expression to an AE expression.
     *
     * @param expressionElement The sysml expression to parse
     * @return The converted to AE expression
     */
    public <X> Expression<X> toAeExpression2( Object expressionElement) {
      
      Expression<X> expression = null;

      // Check for null input.
      if ( expressionElement == null ) return null;
      if ( model == null ) {
          Debug.error( "Model cannot be null!" );
          return null;
      }

      // If it is not an Expression than we cannot process it:
      String expressionType = model.getTypeString(expressionElement, null);
      if (!expressionType.equals("Expression")) {
        Debug.error( "Passed expression is not an Expression type, got type "+ expressionType);
        return null;
      }

      // Pull out the operation, and recursively process the arguments. 
      
      Collection< P > operands = model.getProperty( expressionElement, "operand");
      
      if ( Utils.isNullOrEmpty( operands ) ) return null;

      // first operand must be the operation
      Iterator< P > it = operands.iterator();
      P operation = it.next();
      
      // The other operands are model element arguments to the operation. 
//      ArrayList< Object > argElements = new ArrayList<Object>();
      Vector< Object > arguments = new Vector< Object >();
      while (it.hasNext() ) {
        arguments.add( elementArgumentToAeExpression( it.next() ) );
//        argElements.add( it.next() );
      }
      return operationToAeExpression2( operation, arguments );
    }
    
    protected <X> Expression<X> operationToAeExpression2(P operation,
                                                         Vector< Object > aeArgs ) {
      Expression<X> expression = null;
      // If the operation is a SysML Operation, call operationToAeExpression2() to get the expression.
      String operationType = model.getTypeString(operation, null);
      if ( operationType != null && operationType.equals( "Operation" ) ) {
        Collection< P > opExpProps =
            model.getProperty( operation, "expression" );
        Collection< P > opParamProps =
            model.getProperty( operation, "parameter" );

        // Replace the parameters with the arguments.
        if ( !Utils.isNullOrEmpty( opExpProps ) ) {
          Expression< X > opExpression = 
              toAeExpression2( opExpProps.iterator().next() );
          if ( !Utils.isNullOrEmpty( opParamProps ) ) {
            Iterator< Object > it = aeArgs.iterator();
            // TODO -- Check to see if last Parameter is for a variable number of arguments!
            for ( P param : opParamProps ) {
              if ( !it.hasNext() ) {
                // TODO -- ERROR! Length is not the same!
                Debug.error( true, true, "ERROR! The number of arguments to "
                             + operation
                             + " is not the same as the number of parameters!" );
                break;
              }
              Object aeArg = it.next();
              if (exprParamMap.containsKey(param)) {
                Parameter< Object > aeParam = exprParamMap.get( param );
                opExpression.substitute( aeParam, aeArg, true, null );
              }
            }
            if ( it.hasNext() ) {
              // TODO -- ERROR!  Length is not the same!
              Debug.error( true, true,
                           "ERROR! The number of arguments to " + operation
                               + " is not the same as the number of parameters!"
                               + "  Parameters representing a variable number of arguments are not yet supported");
            }
          }
          expression = opExpression;
        }
        //expression = operationToAeExpression2( operation, arguments  );
      }
      
      // If operation is not an Operation model element, or if the Operation does
      // not have an Expression defining it, use its name to try and match against
      // other implementations that may be intended.
      if ( expression == null ) {
        N operationName = getOperationName( operation );
        Collection< N > operationNames = model.getName( operation );

        Call call = createCall(operationName, aeArgs );
        
        expression = new Expression( call );
      }
      
      return expression;
    }

//    /**
//     * 
//     * @param operation
//     * @param aeArgs
//     * @return
//     */
//    public < X > Expression< X > operationToAeExpression2( P operation,
//                                                           List< Object > aeArgs ) {
//      // TODO Auto-generated method stub
//      return null;
//    }


    /**
     * Translate a SysML Operation, SysML Expression, or name to an AE Expression.  
     * @param arg
     * @return
     */
    private Object elementArgumentToAeExpression( P arg ) {
      // Get the valueOfElementProperty node:
      Collection< P > valueOfElemNodes = 
              model.getProperty(arg, "elementValueOfElement");
      
      // If it is an elementValue, then this will be non-empty:
      if (!Utils.isNullOrEmpty(valueOfElemNodes)) {
                      
        // valueOfElemNodes should always be size 1 b/c elementValueOfElement
        // is a single NodeRef
        arg = valueOfElemNodes.iterator().next();
        
      }
      
      String typeString = model.getTypeString(arg, null);
      
      // If it is an Operation, then it may be meant as a function pointer for
      // functional programming use, like with map.
      if (typeString.equals("Operation")) {
        // REVIEW -- WARNING -- don't know how this works
        Vector<Object> v = new Vector<Object>();
        processOperation( arg, v, true);
        
        if ( !Utils.isNullOrEmpty( v ) ) {
          return v.firstElement();
        }
        
//        if ( !Utils.isNullOrEmpty( v ) ) {
//          if ( v.size() == 1 ) {
//             // HERE!!! Don't know how this is done!
//            Collection< N > names = model.getName( arg );
//            if (!Utils.isNullOrEmpty( names ) ) {
//              N name = names.iterator().next();
//              return new Expression< N >(name);
//            } else {
//              Debug.error( true, true,
//                           "ERROR!  Don't know how to handle Operation as argument!" );
//            }
//          }
//        }
      }
      
      if (typeString.equals("Expression")) {
        return toAeExpression(arg);
      }
      
      // If it is a Parameter then add it the map for later use,
      // and create a Parameter for it:
      else if (typeString.equals("Parameter")) {
      
        Parameter<Object> param = null;

        // Get the name of the argument Node:
        Collection<N > argValueNames = model.getName(arg);
        String argValName = Utils.isNullOrEmpty(argValueNames) ? null : 
                                                                 argValueNames.iterator().next().toString();
        
        // TODO get the parameterType property and set it the Parameter type
        
        // Wrap the argument in a Parameter:
        param = (Parameter<Object>)classData.getParameter( null, argValName, false, true, true, false );
        
        if (param != null) {
          
          // Add to map:
          exprParamMap.put( arg, param );
          return new Expression<Object>(param);

        }
        
      }
                  
      // Its a Property command arg, so get the argument values:
      else if (typeString.equals("Property")) {
        
        Parameter<Object> param = null;
        
        // Get the argument Node:
        Collection<U > argValueNodes = model.getValue(arg, null);

        // Get the name of the argument Node:
        Collection<N > argValueNames = model.getName(arg);
        String argValName = Utils.isNullOrEmpty(argValueNames) ? null : 
                                                                 argValueNames.iterator().next().toString();
        
        // If the argument node has a value:
        if (!Utils.isNullOrEmpty(argValueNodes)) {
          
          // TODO can we assume this will always be size one?
          Object argValueNode = argValueNodes.iterator().next();
          Debug.outln( "\nargValueNode = " + argValueNode );
          
          // Create a Parameter for the argument and add to arguments:
          return elementValueToAeExpression(argValueNode, argValName);
          
        } // ends !Utils.isNullOrEmpty(argValueNodes)
        
        // Argument node does not have a value, so just add to argument list 
        // for the operator with no set value:
        else {
          
          // Wrap the argument in a Parameter:
          param =
              (Parameter< Object >)classData.getParameter( null, argValName,
                                                           false, true, true,
                                                           false );
          
          if (param != null) {
            return new Expression<Object>(param);
          }
          
        } // ends else argument node does not have a value

      } // ends else if it is Property (ie a command arg)
      
      // All other cases failed, then just create a Parameter for
      // it, ie it is plain ole Element, a LiteralInt, etc:
      else {
        
        // Get the name of the argument Node:
        Collection<N > argValueNames = model.getName(arg);
        String argValName =
            Utils.isNullOrEmpty( argValueNames ) ? null
                                                : argValueNames.iterator().next()
                                                               .toString();
        
        // Create a Parameter for the argument and add to arguments:
        return elementValueToAeExpression(arg, argValName);
      }
      
      // TODO Auto-generated method stub
      return null;
    }
    
    /**
     * Converts the passed sysml expression to an AE expression.
     *
     * @param expressionElement The sysml expression to parse
     * @return The converted to AE expression
     */
    public <X> Expression<X> toAeExpression( Object expressionElement) {
      
        // running example !:
        //   Viewpoint vp exposes exposed and has an Operation vp_op(foo), which 
        //   is defined as the Expression, List(map(foo, Name)).
        // 
        //   1st call: List = map(foo, Name) = toAeExpression(map(foo, Name))
       
        N operationName = null;
        Expression<X> expression = null;
        Vector< Object > arguments = new Vector< Object >();
        
        if ( expressionElement == null ) return null;
        if ( model == null ) {
            Debug.error( "Model cannot be null!" );
            return null;
        }

        // If it is not an Expression than we cannot process it:
        String expressionType = model.getTypeString(expressionElement, null);
        if (!expressionType.equals("Expression")) {
          Debug.error( "Passed expression is not an Expression type, got type "+ expressionType);
          return null;
        }
        
        // running example !:
        //   Viewpoint vp exposes exposed and has an Operation vp_op(foo), which 
        //   is defined as the Expression, List(map(foo, Name)).
        // 
        //   1st call: List = map(foo, Name) = toAeExpression(map(foo, Name))
        //
        //   Below, map = parseExpression( map(foo, Name), [foo, Name])
       
        // Parse the operation name and arguments out of the Expression:
        operationName = parseExpression(expressionElement, arguments);
                  
        // running example !:
        //   Viewpoint vp exposes exposed and has an Operation vp_op(foo), which 
        //   is defined as the Expression, List(map(foo, Name)).
        // 
        //   1st call: List = map(foo, Name) = toAeExpression(map(foo, Name))
        //
        //   Below, map(foo, Name) = createCall( map, [foo, Name])

        //Class< Function>
        Call call = createCall(operationName, arguments);

        if ( call != null ) {

            expression = new Expression< X >( call );
            return expression;
        }
        
        expression = new Expression< X >( expressionElement );
        return expression;
    }
    
    /**
     * Converts the passed sysml Operation to an AE Expression
     * 
     * @param operationElement the sysml operation to convert
     * @param parameterValues a List of parameter values for the operation
     *        The order of the elements of this list must match the
     *        order of elements of parameter for the passed
     *        Operation.
     * @return the AE expression
     */
    public <X> Expression<X> operationToAeExpression( Object operationElement, 
                                                      List<Object> parameterValues) {
      
      // Converting parameterValues to AeExpressions:
      Iterator< Object > it = parameterValues.iterator();
      Vector< Object > args = new Vector< Object >();
      while (it.hasNext() ) {
        args.add( elementArgumentToAeExpression( (P)it.next() ) ); // FIXME dont like warning
      }
      
      if ( true ) {
        return operationToAeExpression2( (P)operationElement, // FIXME dont like warning
                                         args );
      }
      
      // running example !:
      //   Viewpoint vp exposes exposed and has an Operation vp_op(foo), which 
      //   is defined as the Expression, List(map(foo, Name)).
      // 
      //   1st call = operationToAeExpression( vp_op( foo ), exposed )
      
      // REMOVE CODE BELOW IF ABOVE CODE IS WORKING!
      
      N operationName = null;
      Expression<X> expression = null;
      Vector< Object > arguments = new Vector< Object >();
      
      if ( operationElement == null ) return null;
      if ( model == null ) {
          Debug.error( "Model cannot be null!" );
          return null;
      }
      
      // If it is not an Operation than we cannot process it:
      String operationType = model.getTypeString(operationElement, null);
      if (!operationType.equals("Operation")) {
          Debug.error( true, false, ( new Date() )
                       + "Passed operation is not an Operation type, got type "
                       + operationType );
          return null;
      }
      
      // Get the Expression from the expression property of the Operation
      // element:
      Collection< P > expressions =
              model.getProperty( operationElement, "expression");
      P expressionElement = null;
      if ( !Utils.isNullOrEmpty( expressions ) ) {
        expressionElement = expressions.iterator().next();
      }
      else {
        Debug.error(true, false,
                    "Passed operation element did not have a expression property!");
        return null;
      }
      
      // Get the parameters from the parameter property of the Operation
      // element:
      Collection< P > paramElements = model.getProperty( operationElement, 
                                                         "parameter");
      
      // Warn the user if no parameters are found, but parameters
      // were passed in:
      if (Utils.isNullOrEmpty( paramElements ) && 
          !Utils.isNullOrEmpty( parameterValues )) {
          
          Debug.error(true, false,
                      "Passed operation had no parameter, but passed in parameter values!");
      }
      
      // Warn user if found parameters, but not parameters were passed in:
      if (!Utils.isNullOrEmpty( paramElements ) && 
          Utils.isNullOrEmpty( parameterValues )) {
              
              Debug.error(true, false,
                          "Passed operation had parameter, but no passed in parameter values were supplied!");
      }
      
      // Warn user if number of parameters found does not equal
      // to the number of passed parameters:
      if (Utils.isNullOrEmpty( paramElements ) && 
          Utils.isNullOrEmpty( parameterValues ) && 
          (paramElements.size() != parameterValues.size())) {
          
          Debug.error(true, false,
                      "Number of passed parameter values "+parameterValues.size()+" does not equal number of found parameters "+paramElements.size());
      }
      
      // TODO: fix this below if needed
//      if ( viewpointExpr == null ) {
//          if ( operationElement != null ) {
//              Collection< Object > exprs =
//                      model.op( Operation.GET,
//                                Utils.newList( ModelItem.PROPERTY ),
//                                Utils.newList( new Item( operationElement,
//                                                         ModelItem.PROPERTY ) ),
//                                Utils.newList( new Item( "Expression",
//                                                         ModelItem.TYPE ) ),
//                                null, false );
//              if ( !Utils.isNullOrEmpty( exprs ) ) {
//                  for ( Object o : exprs ) {
//                      if ( o instanceof P ) {
//                          viewpointExpr = (P)o;
//                      }
//                  }
//              }
//
//          }
//      }
      
      // If it is not an Expression than we cannot process it:
      String expressionType = model.getTypeString(expressionElement, null);
      if (!expressionType.equals("Expression")) {
        Debug.error( "Passed expression is not an Expression type, got type "+ expressionType);
        return null;
      }
      
      // running example !:
      //   Viewpoint vp exposes exposed and has an Operation vp_op(foo), which 
      //   is defined as the Expression, List(map(foo, Name)).
      // 
      //   1st call = operationToAeExpression( vp_op( foo ), exposed )
      //   Below, List = parseExpression(List(map(foo, Name), [map(foo, Name)])

      // Parse the operation name and arguments out of the Expression:
      operationName = parseExpression(expressionElement, arguments);
      // operationName = get_names
      // expressionElement = get_two_names_expression
      // arguments = [param(elem1),param(elem2),List(Name(s1),Name(s2))]
      
      // Set each parameter of the Operation to the passed in
      // Parameter values:
      P myParam = null;
      if (!Utils.isNullOrEmpty( paramElements ) &&
          !Utils.isNullOrEmpty( parameterValues )) {
          
        int minSize = Math.max( paramElements.size(), parameterValues.size() );
        Iterator<P> paramElemIter = paramElements.iterator();
        Iterator<Object> paramValIter = parameterValues.iterator();

        for (int i = 0; i < minSize; ++i) {
            
            myParam = paramElemIter.next();
            
            // If the map contains the parameter node, then set its value
            // to the passed in value:
            if (exprParamMap.containsKey(myParam)) {
                exprParamMap.get(myParam).setValue(paramValIter.next());
            }
        } // ends for loop

      } 
               
      //Class< Function>       
      Call call = createCall(operationName, arguments);

      if ( call != null ) {
          expression = new Expression< X >( call );
          return expression;
      }
      else {
          // TODO
          // trying to just return the argument expression created
          // Return the first expression with Function form?
          if (!Utils.isNullOrEmpty( arguments )) {
              for (Object arg : arguments) {
                  if (arg instanceof Expression) {
                      Expression<X> expr = (Expression<X>)arg;
                      if (expr.form.equals( Expression.Form.Function ) ||
                          expr.form.equals( Expression.Form.Constructor )) {
                          
                          return expr;
                      }
                          
                  }
              }
          }
      }
      
      expression = new Expression< X >( operationElement );
      return expression;
  }
    
    // accessors
    
    public SM getModel() {
        return model;
    }
    
    public void setModel( SM model ) {
        this.model = model;
    }
    
    public ClassData getClassData() {
      return classData;
    }

    public void setClassData( ClassData classData ) {
      this.classData = classData;
    }

}
