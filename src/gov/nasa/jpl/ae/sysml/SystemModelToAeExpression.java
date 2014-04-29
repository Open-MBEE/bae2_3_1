package gov.nasa.jpl.ae.sysml;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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

    public <X> X evaluateExpression( Object expressionElement, Class<X> cls ) {
      Expression<X> expression = toAeExpression( expressionElement );
      return expression.evaluate( true );
    }
    
    /**
     * Return a Call object based on the passed operation and arguments
     * 
     * @param operationName
     * @param arguments
     * @return
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
        
        // TODO -- if it *still* fails, maybe search through all classes of all
        // packages for a method with this name.
        
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
     * @param argValueNode
     * @param argValName
     * @param arguments
     * @return
     */
    private void addParametertoArgs(Object argValueNode, String argValName,
                                    Vector<Object> arguments) {
      
      String argType = null;
      Parameter<Object> param = null;
      Collection<U> argValPropNodes = null;

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
      else {
        // TODO rest of the argument types if we need them
      }
      
      Object argValProp = null;
      if (!Utils.isNullOrEmpty(argValPropNodes)) {
        
        // TODO can we assume this is always size 1?  
        argValProp = argValPropNodes.iterator().next();
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
          param.setValue(argValProp);
        }
        arguments.add(new Expression<Object>(param));
      }
      
      // Creating param failed, so just add the value object itself:
      else {
        arguments.add(new Expression<Object>(argValProp));
      }
      
    }
    
    /**
     * TODO remove this once we prove parseExpressionVE handles all
     * cases
     * 
     * Parses the passed Expression for a operatorName and arguments
     * 
     * @param expressionElement
     * @param operationName
     * @param arguments
     * @return The operationName
     */
    private N parseExpression(Object expressionElement, Vector<Object> arguments) {
      
      N operationName = null;
      
      // Get all operand properties of the element
      // TODO: should be using Acm.JSON_OPERAND in getProperty but they have Acm in view_repo.util package
      Collection< P > properties = model.getProperty( expressionElement, "operand");
      //Debug.outln( "toAeExpression(" + expressionElement + " ): operands = "+ properties );
      
      if (!Utils.isNullOrEmpty(properties)) {
    
        // Loop through all of the operand property values to find the operand arguments
        // and Operation:
        for (P operandProp : properties) {
                      
          // Get the valueOfElementProperty node:
          Collection< P > valueOfElemNodes = 
                  model.getProperty(operandProp, "elementValueOfElement");
          //Debug.outln( "elementValueOfElement property of operand prop = "+ valueOfElemNodes );
          
          if (!Utils.isNullOrEmpty(valueOfElemNodes)) {
                          
            // valueOfElemNodes should always be size 1 b/c elementValueOfElement
            // is a single NodeRef
            P valueOfElementNode = valueOfElemNodes.iterator().next();
            //Debug.outln( "valueOfElementNode = " + valueOfElementNode );
            String typeString = model.getTypeString(valueOfElementNode, null);
            //Debug.outln( "typeString of valueOfElementNode = " + typeString );
            
            
            // If it is a Operation type then get the operator name:
            if (typeString.equals("Operation")) {
              
              Collection<N> operNames = model.getName(valueOfElementNode);
              
              if (!Utils.isNullOrEmpty(operNames)) {
                operationName = operNames.iterator().next();
                Debug.outln( "\noperationName = " + operationName);
              }
              
            }
            
            // If it is a Expression type then process that Expression
            // and add to argument list:
            else if (typeString.equals("Expression")) {
              
              arguments.add(toAeExpression(valueOfElementNode));
            }
            
            // Otherwise, it must be a command arg, so get the argument values:
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
                
                String argType = null;

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
              
          } // ends !Utils.isNullOrEmpty(valueOfElemNodes
         
        } // ends for loop
        
      } // ends !Utils.isNullOrEmpty(properties)
      
      return operationName;
    }
    
    /**
     * Parses the passed Expression for a operatorName and arguments
     * for the ViewEditor demo.  Adds to the passed arguments list.
     * 
     * @param expressionElement
     * @param operationName
     * @param arguments
     * @return The operationName
     */
    private N parseExpressionVE(Object expressionElement, Vector<Object> arguments) {
      
      N operationName = null;
      
      // Get all operand properties of the element
      // TODO: should be using Acm.JSON_OPERAND in getProperty but they have Acm in view_repo.util package
      Collection< P > properties = model.getProperty( expressionElement, "operand");
      //Debug.outln( "toAeExpression(" + expressionElement + " ): operands = "+ properties );
      
      if (!Utils.isNullOrEmpty(properties)) {
    
        // Loop through all of the operand property values to find the operand arguments
        // and Operation:
        for (P operandProp : properties) {
          
          P valueOfElementNode = null;

          // Get the valueOfElementProperty node:
          Collection< P > valueOfElemNodes = 
                  model.getProperty(operandProp, "elementValueOfElement");
          //Debug.outln( "elementValueOfElement property of operand prop = "+ valueOfElemNodes );
          
          // If it is a elementValue, then this will be non-empty:
          if (!Utils.isNullOrEmpty(valueOfElemNodes)) {
                          
            // valueOfElemNodes should always be size 1 b/c elementValueOfElement
            // is a single NodeRef
            valueOfElementNode = valueOfElemNodes.iterator().next();
            //Debug.outln( "valueOfElementNode = " + valueOfElementNode );
            
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
              
              Collection<N> operNames = model.getName(valueOfElementNode);
              
              // Get the operationName if have not already.  
              // This assumes that the first operandProp
              // is the operation name, as the arguments can now be Operations
              // also.
              if (operationName == null) {
                if (!Utils.isNullOrEmpty(operNames)) {
                  operationName = operNames.iterator().next();
                  Debug.outln( "\noperationName = " + operationName);
                }
                else {
                  Debug.error("Error getting operation name!");
                  return null;
                }
              }
              // Otherwise, the Operation an argument (of an already found
              // Operation), but it has no arguments itself (and should not expect
              // any if it is an actual Operation element).  So, find a Java 
              // function corresponding to the name this must be a
              // 
              else {
                
                N opArgName = null;
                if (!Utils.isNullOrEmpty(operNames)) {
                  
                  opArgName = operNames.iterator().next();
                  Vector<Object> opEmptyArgs = new Vector<Object>();
                  Debug.outln( "\nopArgName = " + opArgName);
                  
                  // Create a Call for the argument 
                  Call argCall = createCall(opArgName, opEmptyArgs);
                  
                  if ( argCall != null ) {
                    
                    // Add to the argument list:
                    //arguments.add( argCall );
                    // TODO dont think we need to wrap it in an Expression?
                    arguments.add(new Expression<Object>(argCall));

                  } // Ends if argCall != null
                                   
                } // Ends if operNames is not null or empty
                
              } // Ends else (the Operation is an argument)
              
            } // Ends if type is an Operation
            
            // If it is a Expression type then process that Expression
            // and add to argument list:
            else if (typeString.equals("Expression")) {
              
              arguments.add(toAeExpression(valueOfElementNode, false));
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
            // it (hopefully it is a Literal type):
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
      
      return operationName;
    }
    
    /**
     * Converts the passed sysml Expression to an Ae Expression.
     *
     */
    public <X> Expression<X> toAeExpression( Object expressionElement ) {
      
       return toAeExpression(expressionElement, true);
    }
    
    /**
     * Converts the passed sysml Expression to an Ae Expression.
     *
     */
    public <X> Expression<X> toAeExpression( Object expressionElement,
                                             boolean evalCall) {
      
       
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
        
        // Parse the operation name and arguments out of the Expression:
        operationName = parseExpressionVE(expressionElement, arguments);
          
        Debug.outln( "\n\nCalling Operator w/ args......");
        
        //Class< Function>            
        Call call = createCall(operationName, arguments);

        if ( call != null ) {
            
            if (evalCall) {
              expression = new Expression< X >( call.evaluate( true, false) );
            }
            else {
              expression = new Expression< X >(call);
            }
            return expression;
        }
        
        expression = new Expression< X >( expressionElement );
        return expression;
    }
    
    /**
     * 
     * @param operationElement
     * @param parameters 
     * @return
     */
    public <X> Expression<X> operationToAeExpression( Object operationElement, 
                                                      Collection<P> parameters) {
      
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
        Debug.error( "Passed operation is not an Operation type, got type "+ operationType);
        return null;
      }
      
      // Get the Expression from the operationExpression property of the Operation
      // element:
      Collection< P > expressions =
              model.getProperty( operationElement, "operationExpression");
      P expressionElement = null;
      if ( !Utils.isNullOrEmpty( expressions ) ) {
        expressionElement = expressions.iterator().next();
      }
      else {
        Debug.error("Passed operation element did not have a operationExpression property!");
        return null;
      }
      
      // Get the parameters from the operationParameter property of the Operation
      // element:
      Collection< P > paramElements = model.getProperty( operationElement, 
                                                         "operationParameter");
      
      P paramElement = null;
      // Assuming that this is size 1 for now, ie assuming that the ViewPoint
      // can only have one operationParameter:
      // TODO: will probably need to make it handle more than one
      //       operationParameter
      if ( !Utils.isNullOrEmpty( expressions ) ) {
        paramElement = paramElements.iterator().next();
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
      
      // Parse the operation name and arguments out of the Expression:
      operationName = parseExpressionVE(expressionElement, arguments);
      
      // Determine which Parameter was the "exposed" argument by comparing
      // it with the operandParameter of the Operation:
      Parameter<Object> exposeParam = null;
      if (paramElement != null) {
        for (Entry<P, Parameter<Object>> entry : exprParamMap.entrySet()) {
          
          if (entry.getKey().equals( paramElement )) {
            exposeParam = entry.getValue();
            break;
          }
        }
      }
      
      // Set the value of the exposed Parameter to the passed in
      // exposed model elements:
      if (exposeParam != null) {
        exposeParam.setValue( parameters );
      }
        
      Debug.outln( "\n\nCalling Operator w/ args......");
      
      //Class< Function>       
      Call call = createCall(operationName, arguments);

      if ( call != null ) {
          expression = new Expression< X >( call.evaluate( true, true) );
          return expression;
      }
      
      expression = new Expression< X >( expressionElement );
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
