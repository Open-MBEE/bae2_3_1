package gov.nasa.jpl.ae.sysml;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Vector;

import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.util.JavaToConstraintExpression;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Utils;
import sysml.SystemModel;

public class SystemModelToAeExpression< T, P, N, U, SM extends SystemModel< ?, ?, T, P, N, ?, U, ?, ?, ?, ? > > {
    
    protected SM model = null;
    
    public SystemModelToAeExpression(SM model) {
        setModel(model);
    }

    public <X> X evaluateExpression( Object expressionElement ) {
      Expression<X> expression = toAeExpression( expressionElement );
      return expression.evaluate( true );
    }
    
    public <X> Expression<X> toAeExpression( Object expressionElement ) {
      
        N operationName = null;

        if ( expressionElement == null ) return null;
        if ( model == null ) {
            Debug.error( "Model cannot be null!" );
        }
        Expression<X> expression = null;
        Vector< Object > arguments = new Vector< Object >();
        
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
              
              // Otherwise, it must be a command arg, so get the argument values:
              else {
                                
                // Get the argument Node:
                Collection<U > argValueNodes = 
                    model.getValue(valueOfElementNode, null);
                
                if (!Utils.isNullOrEmpty(argValueNodes)) {
                  
                  Debug.outln( "argValueNodes = " + argValueNodes );

                  // TODO can we assume this will always be size one?
                  Object argValueNode = argValueNodes.iterator().next();
                  Debug.outln( "argValueNode = " + argValueNode );
                  
                  // Get the value of the argument based on type:
                  Collection<U> argValPropNodes = null;
                  if (model.getTypeString(argValueNode, null).equals("sysml:LiteralInteger")) {
                    
                    argValPropNodes = model.getValue(argValueNode, "sysml:integer");
                  }
                  else if (model.getTypeString(argValueNode, null).equals("sysml:LiteralReal")) {
                    
                    argValPropNodes = model.getValue(argValueNode, "sysml:double");
                  }
                  else if (model.getTypeString(argValueNode, null).equals("sysml:LiteralBoolean")) {
                    
                    argValPropNodes = model.getValue(argValueNode, "sysml:boolean");
                  }
                  else if (model.getTypeString(argValueNode, null).equals("sysml:LiteralUnlimitedNatural")) {
                    
                    argValPropNodes = model.getValue(argValueNode, "sysml:naturalValue");
                  }
                  else {
                    // TODO rest of the argument types if we need them
                  }
                  
                  if (!Utils.isNullOrEmpty(argValPropNodes)) {
                    
                    Object argValProp = argValPropNodes.iterator().next();
                    arguments.add(argValProp);
                    Debug.outln( "argValProp = " + argValProp );

                  }
                  
                } // ends !Utils.isNullOrEmpty(argValueNodes)

              } // ends else it is a command arg
                
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
            expression = new Expression< X >( call );
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
