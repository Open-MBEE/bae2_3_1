/**
 * 
 */
package gov.nasa.jpl.ae.magicdrawPlugin.modelQuery;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.ecore.EObject;

import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.NameExpr;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.util.JavaToConstraintExpression;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Utils;

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
    if ( !Utils.isNullOrEmpty( type ) ) return type;
      // Could not determine the type based on Java reflection alone.  See if
      // the type can be determined with respect to model elements.
      Expression< ? > expression =
          fieldExprToAeExpression( expr, false,
                                   lookOutsideClassData,
                                   false, false,
                                   false, false, false );
      if ( expression == null ) return null;
      return Utils.replaceSuffix(ClassUtils.toString( expression.getType() ), ".class", "" );
//      ModelReference< ? > ref1 = new ModelReference( expr.toString(), "Java", null );
//      ModelReference< ? > ref2 =
//          new ModelReference( null, expr.getScope().toString(),
//                              expr.getField(), null,
//                              new Parameter< EObject >( "", null, model, null ),
//                              null, null, null, true );
//      @SuppressWarnings( { "unchecked", "rawtypes" } )
//      ModelReference< ? > ref3 =
//          new ModelReference( model, expr.getScope().toString(), null, null, true );
//      List< ModelReference< ? > > ref4 =
//          ModelReference.getAlternatives( ref3, expr.getField(), null );
//    return type;
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
//    boolean convertFcnCallArgsToExprs = true; // TODO -- should this be passed in?
//    Expression<?> expression = fieldExprToAeExpression( fieldAccessExpr, convertFcnCallArgsToExprs, lookOutsideClassDataForTypes, complainIfDeclNotFound, wrapInFunction, evaluateCall, getParameterValue, propagate );
    String aeExpr = super.fieldExprToAe( fieldAccessExpr, lookOutsideClassDataForTypes,
                                         complainIfDeclNotFound, wrapInFunction,
                                         evaluateCall, getParameterValue, propagate );
    if ( Utils.isNullOrEmpty( aeExpr ) ) {
      // Could not convert the expression based on Java reflection alone.  See if
      // the type can be determined with respect to model elements.
      boolean convertFcnCallArgsToExprs = true; // TODO -- should this be passed in?
      Expression< ? > expression =
          fieldExprToAeExpression( fieldAccessExpr, convertFcnCallArgsToExprs,
                                   lookOutsideClassDataForTypes,
                                   complainIfDeclNotFound, wrapInFunction,
                                   evaluateCall, getParameterValue, propagate );
      return expression.toString(); // TODO -- TEST -- REVIEW
    }
    return aeExpr;
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
        super.astToAeExpression( fieldAccessExpr.getScope(),
                                 null,
                                 fieldAccessExpr.getField(),
                                 convertFcnCallArgsToExprs,
                                 lookOutsideClassDataForTypes,
                                 complainIfDeclNotFound,
                                 evaluateCall );
    ModelReference< ?, ? > dotReference = null;
    if ( parentExpr instanceof ModelReference ) {
      ModelReference< ?, ? > mr = (ModelReference< ?, ? >)parentExpr;
      dotReference = 
          new ModelReference( mr, fieldAccessExpr.getField(), null, null, true );
      if ( dotReference.numberOfAlternatives() > 0 ) {
        aeExpr = dotReference;
      }
    }
    if ( ( complainIfDeclNotFound || Debug.isOn() ) && ( aeExpr == null || !aeExpr.didEvaluationSucceed() ) ) {
      Debug.error( complainIfDeclNotFound, "Could not parse FieldAccessExpr = " + fieldAccessExpr +
                   "; aeExpr = " + aeExpr + "; parentExpr = " + parentExpr +
                   "; dotReference = " + dotReference );
    } else {
      // TODO -- remove result since it's only used for debug output and the
      // evaluation might have an unintended side-effect
      try {
        Object result = ( aeExpr == null ) ? null : aeExpr.evaluate( propagate );
        if ( Debug.isOn() ) Debug.outln( "Parsed FieldAccessExpr = " + fieldAccessExpr +
                                         " as a ModelReference: aeExpr = " + aeExpr +
                                         "; parentExpr = " + parentExpr + "; result = " + result );
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

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
                                                       boolean propagate,
                                                       boolean complainIfNotFound ) {
    Expression< ? > aeExpr =
        super.nameExprToAeExpression( nameExpr, wrapInFunction, evaluateCall,
                                      addIfNotFound, propagate, complainIfNotFound );
    if ( aeExpr != null ) return (Expression< T >)aeExpr;
    // Could not convert the expression based on Java reflection alone.  See if
    // the type can be determined with respect to model elements.
    String name = nameExpr.getName();
    ModelReference< ?, ? > tmpMR =
        new ModelReference( getModel(), getCurrentClass(), null, null, false );
    ModelReference< ?, ? > mr = tmpMR.findAlternatives( name, null, propagate );
    aeExpr = mr;
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
    if ( !Utils.isNullOrEmpty( aeExpr ) ) return aeExpr;
    // Could not convert the expression based on Java reflection alone.  See if
    // the type can be determined with respect to model elements.
    boolean addIfNotFound = false;
    boolean complainIfNotFound = false;
    Expression< ? > aeExpression =
        nameExprToAeExpression( nameExpr, wrapInFunction, evaluateCall,
                                addIfNotFound, propagate, complainIfNotFound );
    if ( aeExpression == null ) return null;
    //aeExpr = aeExpression.toString(); // REVIEW????
    aeExpr = nameExpr.getName();  // REVIEW????
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
