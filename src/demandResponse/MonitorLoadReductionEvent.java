/**
 * 
 */
package demandResponse;

import gov.nasa.jpl.ae.event.DoubleParameter;
import gov.nasa.jpl.ae.event.DurativeEvent;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.Functions;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.Timepoint;

/**
 * @author bclement
 *
 */
public class MonitorLoadReductionEvent extends DurativeEvent {

	public Parameter<Double> projectedLoadReduction; // input
	public double defaultProjectedLoadReduction = 1000.0;
	public Parameter<Double> actualLoadReduction; // input
  public double defaultActualLoadReduction = 1000.0;
	public Parameter<Double> acceptableMargin; // input
  public double defaultAcceptableMargin = 200.0;
  public Parameter<Double> actualMargin; // input
  //public double defaultActualMargin = 0.0;
  
	/**
	 * 
	 */
	public MonitorLoadReductionEvent( Expression<Integer> startTimeExpression,
	                                  Expression<Double> projectedLoadReductionExpression ) {
		// TODO Auto-generated constructor stub
	  super();
//	  if ( startTimeExpression.type == Expression.Type.Parameter ) {
//	    this.substitute( (Parameter<Integer>)startTime,
//	                     (Parameter<Integer>)startTimeExpression.expression );
//	    startTime = (event.Timepoint)startTimeExpression.expression;
//	  } else {
	    addDependency( startTime, startTimeExpression );
//	  }
	  if ( projectedLoadReductionExpression == null ) {
	    projectedLoadReduction = 
	        new DoubleParameter( "projectedLoadReductionBasedOnResponse",
	                             defaultProjectedLoadReduction, this );
	  } else if ( projectedLoadReductionExpression.type == Expression.Type.Parameter ) {
	    projectedLoadReduction = 
	        (Parameter< Double >)projectedLoadReductionExpression.expression;
	  }  else {
	    addDependency( projectedLoadReduction, projectedLoadReductionExpression );
	  }
	  actualLoadReduction = 
        new DoubleParameter( "actualLoadReductionBasedOnResponse",
                             defaultActualLoadReduction, this );
	  acceptableMargin = 
	      new DoubleParameter("acceptableMargin", defaultAcceptableMargin, this);
    actualMargin = 
        new DoubleParameter("actualMargin", this);
    Expression< Double > alr = new Expression< Double >( actualLoadReduction );
    Expression< Double > plr = new Expression< Double >( projectedLoadReduction );
    Expression< Double > subtractExpression = null;
    subtractExpression = new Functions.Sub< Double, Double >( alr, plr );
    
    addDependency( actualMargin, subtractExpression );
	  parameters.add( projectedLoadReduction );
    parameters.add( actualLoadReduction );
    parameters.add( acceptableMargin );
    parameters.add( actualMargin );
	}

  // TODO -- Override loadParameters()

}
