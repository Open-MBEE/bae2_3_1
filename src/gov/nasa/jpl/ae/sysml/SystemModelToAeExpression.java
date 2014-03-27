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

public class SystemModelToAeExpression< T, P, N, SM extends SystemModel< ?, ?, T, P, N, ?, ?, ?, ?, ?, ? > > {
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
        
        // get all operand property of the element
        // TODO: should be using Acm.ACM_OPERAND in getProperty but they have Acm in view_repo.util package
        Collection< P > properties = model.getProperty( expressionElement, "sysml:operand");
        
        if (!Utils.isNullOrEmpty(properties)) {
      
          // Loop through all of the operand property values to find the operand arguments
          // and Operation:
          for (P operandProp : properties) {
                        
            // Get the valueOfElementProperty node:
            Collection< P > valueOfElemNodes = model.getProperty(operandProp, "sysml:elementValueOfElement");
            
            if (!Utils.isNullOrEmpty(valueOfElemNodes)) {
                            
              // valueOfElemNodes should always be size 1 b/c elementValueOfElement is a single NodeRef
              P valueOfElementNode = valueOfElemNodes.iterator().next();
            
              // TODO replace w/ model.getType() once we fix it
              // If it is a Operation type then get the operator name:
              if (model.getTypeString(valueOfElementNode, null).contains("Operation")) {
                
                // TODO: we need to implement model.getName() (perhaps call it getSysmlName())
                Collection<P> operNameNodes = model.getProperty(valueOfElementNode, "sysml:name");
                //operationName = operNameNodes.iterator().next();
              }
              
              // Otherwise, it must be a command arg, so get the argument values:
              else {
                                
                // Get the argument Node:
                Collection< P > argValueNodes = model.getProperty(valueOfElementNode, "sysml:value");
                
                if (!Utils.isNullOrEmpty(argValueNodes)) {
                  
                  // TODO can we assume this will always be size one?
                  P argValueNode = argValueNodes.iterator().next();
                  
                  // Get the value of the argument based on type:
                  Collection<P> argValPropNodes = null;
                  if (model.getTypeString(argValueNode, null).contains("LiteralInteger")) {
                    
                    // TODO: this isnt going to work b/c we get an integer back, but want a EmsScriptNode
                    //       need to figure out how we want to do this
                    argValPropNodes = model.getProperty(argValueNode, "sysml:integer");
                  }
                  else {
                    // TODO rest of the argument types (double, etc)
                  }
                  
                  if (!Utils.isNullOrEmpty(argValPropNodes)) {
                    P argValProp = argValPropNodes.iterator().next();
                    arguments.add(argValProp);
                  }
                  
                } // ends !Utils.isNullOrEmpty(argValueNodes)

              } // ends else it is a command arg
                
            } // ends !Utils.isNullOrEmpty(valueOfElemNodes
           
          } // ends for loop
          
        } // ends !Utils.isNullOrEmpty(properties)
          
        //Class< Function>
        //System.out.println("\n\n!!!!arguments: "+ arguments);
            
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
