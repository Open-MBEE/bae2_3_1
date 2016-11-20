/**
 * 
 */
package demandResponse;

import java.util.Map;
import java.util.Vector;

import gov.nasa.jpl.ae.event.DoubleParameter;
import gov.nasa.jpl.ae.event.DurativeEvent;
import gov.nasa.jpl.ae.event.ElaborationRule;
import gov.nasa.jpl.ae.event.Event;
import gov.nasa.jpl.ae.event.EventInvocation;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.Functions.LT;
import gov.nasa.jpl.ae.event.Functions.Sub;

/**
 * @author bclement
 * 
 */
public class AssessDRCommitmentEvent extends DurativeEvent {

  public DoubleParameter desiredLoadReduction; // input
  protected double defaultDesiredLoadReduction = 1000.0;  // default hardcoded as 1 MW
  
  public DoubleParameter projectedLoadReductionBasedOnResponse;  // input
  protected double defaultProjectedLoadReductionBasedOnResponse = 1000.0;  // default hardcoded as 1 MW

  public DoubleParameter maximumMargin; // input
  protected double defaultMaximumMargin = 200.0;  // default hardcoded as 0.2 MW

  //public MonitorLoadReductionEvent monitorLoadReductionEvent;

  /**
	 * 
	 */
  public AssessDRCommitmentEvent( Expression< Integer > startTimeExpression ) {
    super();
    initMembers( true );
    addDependency( startTime, startTimeExpression );
  }

  /**
   * @param durativeEvent
   */
  public AssessDRCommitmentEvent( DurativeEvent durativeEvent ) {
    super( durativeEvent );
    // TODO Auto-generated constructor stub
  }

  //public Boolean willNextMonitor()
  
  protected void initMembers( boolean force ) {
    if ( force || projectedLoadReductionBasedOnResponse == null ) {
      projectedLoadReductionBasedOnResponse =
          new DoubleParameter( "projectedLoadReductionBasedOnResponse", 
                               defaultProjectedLoadReductionBasedOnResponse,
                               this );
      parameters.add( projectedLoadReductionBasedOnResponse );
    }
    if ( force || maximumMargin == null ) {
      maximumMargin =
          new DoubleParameter( "maximumMargin", defaultMaximumMargin, this );
      parameters.add( maximumMargin );
    }
    if ( force || desiredLoadReduction == null ) {
      desiredLoadReduction =
          new DoubleParameter( "desiredLoadReduction",
                               defaultDesiredLoadReduction, this );
      parameters.add( desiredLoadReduction );
    }
    if ( force || elaborations.isEmpty() || getEvents( false, null ).isEmpty() ) {
      // Create elaboration:
      //  if ( desiredLoadReduction - projectedLoadReductionBasedOnResponse <
      //       maximumMargin ) then
      //    do MonitorLoadReduction( this.endTime )
      elaborations.clear(); // TODO -- REVIEW -- memory leak??
      Vector< EventInvocation > invocation = new Vector< EventInvocation >();
      Expression< ? >[] arguments = new Expression< ? >[2];
      arguments[0] = new Expression< Integer >( endTime );
      arguments[1] = new Expression< Double >( projectedLoadReductionBasedOnResponse );
      //Class< ? > parameterTypes[] = { Expression.class, Expression.class };
      try {
        invocation.add( new EventInvocation( MonitorLoadReductionEvent.class,
                                             null,
                                             null, //MonitorLoadReductionEvent.class.getConstructor( parameterTypes ),
                                             arguments,
                                             null,
                                             (Map< String, Object >)null ) );
      } catch ( Exception e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      Vector< Event > eventVector = new Vector< Event >();
      
      ElaborationRule rule = null;
      Expression< Double > dlr =
          new Expression< Double >( desiredLoadReduction );
      Expression< Double > plrbor =
          new Expression< Double >( projectedLoadReductionBasedOnResponse );
      Expression< Double > mm = new Expression< Double >( maximumMargin );
      Sub< Double, Double > s = new Sub< Double, Double >( dlr, plrbor );
      LT< Double > lt = new LT< Double >( s, mm );
      rule = new ElaborationRule( new Expression<Boolean>( lt ), invocation );
      if ( rule != null ) {
        elaborations.put( rule, eventVector );
      }
      //assessDRCommitment = new AssessDRCommitmentEvent();
    }
//    if ( force || controlRealTimeLoadReductionEvent == null ) {
//      controlRealTimeLoadReductionEvent = new ControlRealTimeLoadReductionEvent();   
//    }
  }

  
  // TODO -- Override loadParameters()
  
}
