package gov.nasa.jpl.ae.sysml;

import java.lang.reflect.Method;
import java.util.Collection;
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

    public SystemModelToAeExpression(SM model) {
        setModel(model);
    }

    public <X> X evaluateExpression( Object expressionElement, Class<X> cls ) {
      Expression<X> expression = toAeExpression( expressionElement );
      return expression.evaluate( true );
    }
    
    /**
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
                
                // Get the value of the argument based on type:
                Collection<U> argValPropNodes = null;
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
     * for the ViewEditor demo.  Will try to make this version handle 
     * all cases.
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
          
          // If it is a elementValue:
          if (!Utils.isNullOrEmpty(valueOfElemNodes)) {
                          
            // valueOfElemNodes should always be size 1 b/c elementValueOfElement
            // is a single NodeRef
            valueOfElementNode = valueOfElemNodes.iterator().next();
            //Debug.outln( "valueOfElementNode = " + valueOfElementNode );
            
          }
          
          // Otherwise just use the node itself:
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
              // Otherwise, the Operation is an command argument:
              else {
                if (!Utils.isNullOrEmpty(operNames)) {
                  
                  // TODO what to do here?
                }
              }
              
            }
            
            // If it is a Expression type then process that Expression
            // and add to argument list:
            else if (typeString.equals("Expression")) {
              
              arguments.add(toAeExpression(valueOfElementNode));
            }
            
            // If it is a Parameter then ?
            else if (typeString.equals("Parameter")) {
              // TODO
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
                
                String argType = null;
  
                // TODO can we assume this will always be size one?
                Object argValueNode = argValueNodes.iterator().next();
                Debug.outln( "\nargValueNode = " + argValueNode );
                
                // Get the value of the argument based on type:
                Collection<U> argValPropNodes = null;
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
                       
          } // ends if valueOfElementNode != null
          
        } // ends for loop
        
      } // ends !Utils.isNullOrEmpty(properties)
      
      return operationName;
    }
    
    public <X> Expression<X> toAeExpression( Object expressionElement ) {
      
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
        operationName = parseExpression(expressionElement, arguments);
          
        Debug.outln( "\n\nCalling Operator w/ args......");
        
        //Class< Function>            
        Call call = null; 
        if ( operationName != null ) {
            if ( arguments.size() == 1 ) {
                call = JavaToConstraintExpression.unaryOpNameToEventFunction( operationName.toString() );
            } else if ( arguments.size() == 2 ) {
                call = JavaToConstraintExpression.binaryOpNameToEventFunction( operationName.toString() );
            } else if ( arguments.size() == 3
                        && operationName.toString().equalsIgnoreCase( "if" ) ) {
                call = JavaToConstraintExpression.getIfThenElseConstructorCall();
            }
            if ( call != null ) {
                call.setArguments( arguments );
            } else {
                Object object = null;
                Method method =
                        ClassUtils.getJavaMethodForCommonFunction( operationName.toString(),
                                                                   arguments.toArray() );
                if ( method == null ) {
                    method = ClassUtils.getMethodForArgs( model.getClass(),
                                                          operationName.toString(),
                                                          arguments.toArray() );
                    if ( method != null ) object = model;
                }
                // TODO -- if it *still* fails, maybe search through all classes of all
                // packages for a method with this name.
                if ( method != null ) {
                    // Check for a call to the SysML API.
                    call = new FunctionCall( object, method, arguments );
                }
            }
        }
        if ( call != null ) {
            expression = new Expression< X >( call.evaluate( true, false) );
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
    public <X> Expression<X> toAeExpression( Object operationElement, 
                                             Object... parameters) {
      
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
      Collection< P > paramElements = model.getProperty( operationElement, "operationParameter");
      
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
        
      Debug.outln( "\n\nCalling Operator w/ args......");
      
      //Class< Function>            
      Call call = null; 
      if ( operationName != null ) {
          if ( arguments.size() == 1 ) {
              call = JavaToConstraintExpression.unaryOpNameToEventFunction( operationName.toString() );
          } else if ( arguments.size() == 2 ) {
              call = JavaToConstraintExpression.binaryOpNameToEventFunction( operationName.toString() );
          } else if ( arguments.size() == 3
                      && operationName.toString().equalsIgnoreCase( "if" ) ) {
              call = JavaToConstraintExpression.getIfThenElseConstructorCall();
          }
          if ( call != null ) {
              call.setArguments( arguments );
          } else {
              Object object = null;
              Method method =
                      ClassUtils.getJavaMethodForCommonFunction( operationName.toString(),
                                                                 arguments.toArray() );
              if ( method == null ) {
                  method = ClassUtils.getMethodForArgs( model.getClass(),
                                                        operationName.toString(),
                                                        arguments.toArray() );
                  if ( method != null ) object = model;
              }
              // TODO -- if it *still* fails, maybe search through all classes of all
              // packages for a method with this name.
              if ( method != null ) {
                  // Check for a call to the SysML API.
                  call = new FunctionCall( object, method, arguments );
              }
          }
      }
      if ( call != null ) {
          expression = new Expression< X >( call.evaluate( true, false) );
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
