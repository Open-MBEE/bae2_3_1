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
    
    public <X> Expression<X> toAeExpression( Object expressionElement ) {
      
        N operationName = null;

        if ( expressionElement == null ) return null;
        if ( model == null ) {
            Debug.error( "Model cannot be null!" );
            return null;
        }
        Expression<X> expression = null;
        Vector< Object > arguments = new Vector< Object >();
        
        // If it is not an Expression than we cannot process it:
        String expressionType = model.getTypeString(expressionElement, null);
        if (!expressionType.equals("sysml:Expression")) {
          Debug.error( "Passed expression is not an Expression type, got type "+ expressionType);
          return null;
        }
        
        // Get all operand properties of the element
        // TODO: should be using Acm.ACM_OPERAND in getProperty but they have Acm in view_repo.util package
        Collection< P > properties = model.getProperty( expressionElement, "sysml:operand");
        Debug.outln( "toAeExpression(" + expressionElement + " ): operands = "
                     + properties );
        
        if (!Utils.isNullOrEmpty(properties)) {
      
          // Loop through all of the operand property values to find the operand arguments
          // and Operation:
          for (P operandProp : properties) {
                        
            // Get the valueOfElementProperty node:
            Collection< P > valueOfElemNodes = 
                    model.getProperty(operandProp, "sysml:elementValueOfElement");
            Debug.outln( "elementValueOfElement property of operand prop = "
                         + valueOfElemNodes );
            
            if (!Utils.isNullOrEmpty(valueOfElemNodes)) {
                            
              // valueOfElemNodes should always be size 1 b/c elementValueOfElement
              // is a single NodeRef
              P valueOfElementNode = valueOfElemNodes.iterator().next();
              Debug.outln( "valueOfElementNode = " + valueOfElementNode );
              String typeString = model.getTypeString(valueOfElementNode, null);
              Debug.outln( "typeString of valueOfElementNode = " + typeString );
              
              
              // If it is a Operation type then get the operator name:
              if (typeString.equals("sysml:Operation")) {
                
                Collection<N> operNames = model.getName(valueOfElementNode);
                
                if (!Utils.isNullOrEmpty(operNames)) {
                  operationName = operNames.iterator().next();
                  Debug.outln( "operationName = " + operationName);
                }
                
              }
              
              // If it is a Expression type then process that Expression
              // and add to argument list:
              else if (typeString.equals("sysml:Expression")) {
                
                arguments.add(toAeExpression(valueOfElementNode));
              }
              
              // Otherwise, it must be a command arg, so get the argument values:
              else if (typeString.equals("sysml:Property")) {
                
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
                  Debug.outln( "argValueNode = " + argValueNode );
                  
                  // Get the value of the argument based on type:
                  Collection<U> argValPropNodes = null;
                  if (model.getTypeString(argValueNode, null).equals("sysml:LiteralInteger")) {
                    
                    argValPropNodes = model.getValue(argValueNode, "sysml:integer");
                    argType = "Integer";
                  }
                  else if (model.getTypeString(argValueNode, null).equals("sysml:LiteralReal")) {
                    
                    argValPropNodes = model.getValue(argValueNode, "sysml:double");
                    argType = "Double";
                  }
                  else if (model.getTypeString(argValueNode, null).equals("sysml:LiteralBoolean")) {
                    
                    argValPropNodes = model.getValue(argValueNode, "sysml:boolean");
                    argType = "Boolean";
                  }
                  else if (model.getTypeString(argValueNode, null).equals("sysml:LiteralUnlimitedNatural")) {
                    
                    argValPropNodes = model.getValue(argValueNode, "sysml:naturalValue");
                    argType = "Integer";
                  }
                  else if (model.getTypeString(argValueNode, null).equals("sysml:LiteralString")) {
                    
                    argValPropNodes = model.getValue(argValueNode, "sysml:string");
                    argType = "String";
                  }
                  else {
                    // TODO rest of the argument types if we need them
                  }
                  
                  Object argValProp = null;
                  if (!Utils.isNullOrEmpty(argValPropNodes)) {
                    
                    // TODO can we assume this is always size 1?  
                    argValProp = argValPropNodes.iterator().next();
                    Debug.outln( "argValProp = " + argValProp );
                  }
                  
                  // Wrap the argument in a Parameter:
                  if (argType != null) {
                    // Parameter also has a type
                    param = (Parameter<Object>)classData.getParameter( null, argValName, argType, false, true, true, false );
                  }
                  else {
                    param = (Parameter<Object>)classData.getParameter( null, argValName, false, true, true, false );
                  }
                  
                  // Set value of the param to value if it has one,
                  // and add to the argument list:
                  if (param != null) {
                    
                    Debug.outln( "param = " + param );
                    if (argValProp != null) {
                      param.setValue(argValProp);
                    }
                    arguments.add(param);
                  }
                  
                  // Creating param failed, so just add the value object itself:
                  else {
                    arguments.add(argValProp);
                  }
                  
                  
                } // ends !Utils.isNullOrEmpty(argValueNodes)
                
                // Argument node does not have a value, so just add to argument list 
                // for the operator with no set value:
                else {
                  
                  // Wrap the argument in a Parameter:
                  param = (Parameter<Object>)classData.getParameter( null, argValName, false, true, true, false );
                  
                  if (param != null) {
                    arguments.add(param);
                  }
                } // ends else argument node does not have a value

              } // ends else if it is Property (ie a command arg)
                
            } // ends !Utils.isNullOrEmpty(valueOfElemNodes
           
          } // ends for loop
          
        } // ends !Utils.isNullOrEmpty(properties)
          
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
                if ( method != null ) {
                    // Check for a call to the SysML API.
                    call = new FunctionCall( object, method, arguments );
                }
            }
        }
        if ( call != null ) {
            expression = new Expression< X >( call.evaluate( true ) );
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
}
