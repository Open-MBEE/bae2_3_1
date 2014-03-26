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
            
            // TODO finish
          }
          
          // This is all wrong:
          Collection< ? > values = model.getValue(properties.iterator().next(), null);
          System.out.println( "\n*toAeExpression() values: "
              + MoreToString.Helper.toLongString( values ) );
            
          
          //Collection< T > operandTypes = model.getType( null, "Operation" );
          if ( values != null ) {
            for ( Object val : values ) {
              
                Collection< T > types = model.getType( val, null );
                
                if (types != null && !types.isEmpty()) {
                  
                  T type =  types.iterator().next(); // TODO can we assume there is exactly one type?
                  if ( operationName == null && type != null &&
                       model.getName(type).equals("sysml:operand") ) { // TODO NPE
                    
                      Collection< N > operationNames = model.getName( val );
                      if ( operationNames != null && !operationNames.isEmpty() ) {
                          operationName = operationNames.iterator().next();
                      }
                  } 
                  else {
                      // assume this is an argument
                      arguments.add( toAeExpression( val ) );
                  }
                }
                else {
                  // assume this is an argument
                  arguments.add( toAeExpression( val ) );
                }
            }
            
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
            
        }
          
          // Old:
//        // get all properties of the element
//        Collection< P > properties = model.getProperty( expressionElement, null );
//        
//        Vector< Object > arguments = new Vector< Object >();
//        Collection< T > operandTypes = model.getType( null, "Operation" );
//        N operationName = null;
//        if ( properties != null ) {
//          for ( P p : properties ) {
//              Collection< T > type = model.getType( p, null );
//              if ( operationName == null && 
//                   ( operandTypes.contains( type ) ||
//                     model.getName( type ).equals("operand") ) ) {
//                  Collection< N > operationNames = model.getName( p );
//                  if ( operationNames != null ) {
//                      operationName = operationNames.iterator().next();
//                  }
//              } else {
//                  // assume this is an argument
//                  arguments.add( toAeExpression( p ) );
//              }
//          }
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
