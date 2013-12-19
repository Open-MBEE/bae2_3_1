/**
 * 
 */
package gov.nasa.jpl.ae.magicdrawPlugin.modelQuery;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.NameExpr;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Utils;
import gov.nasa.jpl.ae.xml.JavaToConstraintExpression;

/**
 * As a JavaToConstraintExpression, this class parses Java. JavaModelQueryParser
 * extends JavaToConstraintExpression to parse an extension of Java to reference
 * model elements and their relationships through EMF (Eclipse Modeling Framework).
 */
public class JavaModelQueryParser extends JavaToConstraintExpression {

  protected EObject model = null;
  /**
   * @param packageName
   */
  public JavaModelQueryParser( String packageName ) {
    super( packageName );
  }

  /**
   * @param packageName
   */
  public JavaModelQueryParser( String packageName, EObject model ) {
    super( packageName );
    setModel( model );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.xml.JavaToConstraintExpression#fieldExprToAeType(japa.parser.ast.expr.Expression, boolean)
   */
  @Override
  public String fieldExprToAeType( japa.parser.ast.expr.FieldAccessExpr expr,
                                   boolean lookOutsideClassData ) {
    String type = super.fieldExprToAeType( expr, lookOutsideClassData );
    if ( Utils.isNullOrEmpty( type ) ) {
      // Could not determine the type based on Java reflection alone.  See if
      // the type can be determined with respect to model elements.
      
      ModelReference< ? > ref1 = new ModelReference( expr.toString(), "Java", null );
      ModelReference< ? > ref2 =
          new ModelReference( null, expr.getScope().toString(),
                              expr.getField(), null,
                              new Parameter< EObject >( "", null, model, null ),
                              null, null, null, true );
      @SuppressWarnings( { "unchecked", "rawtypes" } )
      ModelReference< ? > ref3 =
          new ModelReference( model, expr.getScope().toString(), null, null, true );
      List< ModelReference< ? > > ref4 =
          ModelReference.getAlternatives( ref3, expr.getField(), null );
      HERE!!!;
    }
    return type;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.xml.JavaToConstraintExpression#fieldExprToAe(japa.parser.ast.expr.FieldAccessExpr, boolean, boolean, boolean, boolean, boolean, boolean)
   */
  @Override
  public String fieldExprToAe( FieldAccessExpr fieldAccessExpr,
                               boolean lookOutsideClassDataForTypes,
                               boolean complainIfDeclNotFound,
                               boolean wrapInFunction, boolean evaluateCall,
                               boolean getParameterValue, boolean propagate ) {
    // TODO Auto-generated method stub
    String aeExpr = super.fieldExprToAe( fieldAccessExpr, lookOutsideClassDataForTypes,
                                         complainIfDeclNotFound, wrapInFunction,
                                         evaluateCall, getParameterValue, propagate );
    if ( Utils.isNullOrEmpty( aeExpr ) ) {
      // Could not convert the expression based on Java reflection alone.  See if
      // the type can be determined with respect to model elements.
      HERE!! TODO!!;
    }
    return type;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.xml.JavaToConstraintExpression#fieldExprToAeExpression(japa.parser.ast.expr.FieldAccessExpr, boolean, boolean, boolean, boolean, boolean, boolean)
   */
  @Override
  public Expression< ? >
      fieldExprToAeExpression( FieldAccessExpr fieldAccessExpr,
                               boolean convertFcnCallArgsToExprs,
                               boolean lookOutsideClassDataForTypes,
                               boolean complainIfDeclNotFound,
                               boolean wrapInFunction, boolean evaluateCall,
                               boolean getParameterValue, boolean propagate ) {

    Expression< ? > aeExpr =
        super.fieldExprToAeExpression( fieldAccessExpr, convertFcnCallArgsToExprs,
                                       lookOutsideClassDataForTypes,
                                       false, wrapInFunction,
                                       evaluateCall, getParameterValue,
                                       propagate );
    if ( aeExpr != null ) return aeExpr;
    
    // Could not parse the expression based on Java reflection alone.  See if
    // parent and field can be identified as model elements.
    Object parentExpr = 
        super.astToAeExpression( fieldAccessExpr.getScope(), null,
                                 fieldAccessExpr.getField(),
                                 convertFcnCallArgsToExprs,
                                 lookOutsideClassDataForTypes, complainIfDeclNotFound, evaluateCall );
    ModelReference< ? > dotReference = null;
    if ( parentExpr instanceof ModelReference ) {
      ModelReference< ? > mr = (ModelReference< ? >)parentExpr;
      dotReference = 
          new ModelReference( mr, fieldAccessExpr.getField(), null, null, true );
      if ( dotReference.numberOfAlternatives() > 0 ) {
        aeExpr = dotReference;
      }
    }
    Object result = null;
    if ( aeExpr != null ) result = aeExpr.evaluate( propagate );
    if ( ( complainIfDeclNotFound || Debug.isOn() ) && ( aeExpr == null || !aeExpr.didEvaluationSucceed() ) ) {
      Debug.error( complainIfDeclNotFound, "Could not parse FieldAccessExpr = " + fieldAccessExpr +
                   "; aeExpr = " + aeExpr + "; parentExpr = " + parentExpr +
                   "; dotReference = " + dotReference );
    } else {
      Debug.outln( "Parsed FieldAccessExpr = " + fieldAccessExpr +
                   " as a ModelReference: aeExpr = " + aeExpr +
                   "; parentExpr = " + parentExpr + "; result = " + result );
    }
    
//    Object o = 
//        super.astToAeExpression( fieldAccessExpr.getScope(), null,
//                                 convertFcnCallArgsToExprs,
//                                 lookOutsideClassDataForTypes,
//                                 complainIfDeclNotFound, evaluateCall );
    return aeExpr;
  }


  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.xml.JavaToConstraintExpression#nameExprToAeExpression(japa.parser.ast.expr.NameExpr, boolean, boolean, boolean, boolean)
   */
  @Override
  public < T > Expression< T > nameExprToAeExpression( NameExpr nameExpr,
                                                       boolean wrapInFunction,
                                                       boolean evaluateCall,
                                                       boolean addIfNotFound,
                                                       boolean propagate ) {
    Expression< ? > aeExpr =
        super.nameExprToAeExpression( nameExpr, wrapInFunction, evaluateCall,
                                      addIfNotFound, propagate );
    if ( aeExpr == null ) {
      // Could not convert the expression based on Java reflection alone.  See if
      // the type can be determined with respect to model elements.
      String name = nameExpr.getName();
      ModelReference< ? > tmpMR =
          new ModelReference( getModel(), currentClass, null, null, false );
      ModelReference< ? > mr =
          tmpMR.findAlternatives( name, null, propagate );
      aeExpr = mr;
    }
    return (Expression< T >)aeExpr;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.xml.JavaToConstraintExpression#nameExprToAe(japa.parser.ast.expr.NameExpr, boolean, boolean, boolean, boolean)
   */
  @Override
  public String nameExprToAe( NameExpr nameExpr, boolean wrapInFunction,
                              boolean evaluateCall, boolean getParameterValue,
                              boolean propagate ) {
    String aeExpr = super.nameExprToAe( nameExpr, wrapInFunction, evaluateCall,
                               getParameterValue, propagate );
    if ( Utils.isNullOrEmpty( aeExpr ) ) {
      // Could not convert the expression based on Java reflection alone.  See if
      // the type can be determined with respect to model elements.
      
      { HERE!!;}
      // TODO!!!
    }
    return aeExpr;
  }

  /**
   * @return the model
   */
  public EObject getModel() {
    return model;
  }

  /**
   * @param model the model to set
   */
  public void setModel( EObject model ) {
    this.model = model;
  }

  /**
   * @param args
   */
  public static void main( String[] args ) {
    // TODO Auto-generated method stub

  }

}
