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
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.StringParameter;
import gov.nasa.jpl.ae.event.Timepoint;

/**
 * @author bclement
 *
 */
public class PlanAndControlLoadReductionEvent extends DurativeEvent {

	// Members
	
	public Timepoint DREventStartTime;// = null; //new Timepoint("DREventStartTime");
	public Timepoint DREventEndTime;// = null; //new Timepoint("DREventEndTime");
	public Parameter<String> DRArea;// = null; //new StringParameter("DRArea");
	public Parameter<Double> LoadReductionNeeded;// = null; //new DoubleParameter("LoadReductionNeeded");

	protected AssessDRCommitmentEvent assessDRCommitment;// = null;
	protected ControlRealTimeLoadReductionEvent controlRealTimeLoadReductionEvent;// = null;
	
	//protected SufficientSwitch sufficientSwitch = new SufficientSwitch();

	// Constructors
	
	/**
	 * 
	 */
	public PlanAndControlLoadReductionEvent() {
		super();
		initMembers(false);
	}

	/**
	 * @param durativeEvent
	 */
	public PlanAndControlLoadReductionEvent(PlanAndControlLoadReductionEvent event) {
		super(event);
		copyParameters(event);
////		DREventStartTime = new Timepoint(event.DREventStartTime);
////		DREventEndTime = new Timepoint(event.DREventEndTime);
////		DRArea = new StringParameter(event.DRArea);
////		LoadReductionNeeded = new DoubleParameter(event.LoadReductionNeeded);
//		assessDRCommitment = new AssessDRCommitmentEvent(event.assessDRCommitment);
//		controlRealTimeLoadReductionEvent = new ControlRealTimeLoadReductionEvent(event.controlRealTimeLoadReductionEvent);
	}

	// Methods
	
	protected void initMembers( boolean force ) {
		if ( force || DREventStartTime == null ) {
			DREventStartTime = new Timepoint("DREventStartTime", this);
		}
		if ( force || DREventEndTime == null ) {
			DREventEndTime = new Timepoint("DREventEndTime", this);
		}
		if ( force || DRArea == null ) {
			DRArea = new StringParameter("DRArea", this);
		}
		if ( force || LoadReductionNeeded == null ) {
			LoadReductionNeeded = new DoubleParameter("LoadReductionNeeded", this);
		}
		if ( force || elaborations.isEmpty() || getEvents( false, null ).isEmpty() ) {
		  elaborations.clear(); // TODO -- REVIEW -- memory leak??
//		  Vector< EventInvocation > invocation = new Vector< EventInvocation >();
		  Expression< ? >[] arguments = new Expression< ? >[1];
		  arguments[0] = new Expression< Long >( startTime );
//      Class< ? > parameterTypes[] = { arguments[0].getClass() };
      addElaborationRule( new Expression< Boolean >( new Boolean( true ) ),
                          null, AssessDRCommitmentEvent.class, null, arguments );
//      try {
//        EventInvocation i =
//            new EventInvocation( AssessDRCommitmentEvent.class,
//                                 AssessDRCommitmentEvent.class.getConstructor( parameterTypes ),
//                                 arguments, (Map< String, Object >)null );
//        invocation.add( i );
//      } catch ( NoSuchMethodException | SecurityException e ) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
//      Vector< Event > eventVector = new Vector< Event >();
//      ElaborationRule rule = new ElaborationRule( new Expression< Boolean >( new Boolean( true ) ),
//                                                  invocation );
//      
//      elaborations.put( rule, eventVector );
//	    //assessDRCommitment = new AssessDRCommitmentEvent();
		}
//		if ( force || controlRealTimeLoadReductionEvent == null ) {
//	    controlRealTimeLoadReductionEvent = new ControlRealTimeLoadReductionEvent();   
//		}
	}
	
	@Override
	protected void loadParameters() {
		super.loadParameters();
		initMembers(false);
		parameters.add( DREventStartTime );
		parameters.add( DREventEndTime );
		parameters.add( DRArea );
		parameters.add( LoadReductionNeeded );
	}

	@Override
	protected void copyParameters(DurativeEvent event) {
		super.copyParameters(event);
		if ( event instanceof PlanAndControlLoadReductionEvent) {
			PlanAndControlLoadReductionEvent paclre = (PlanAndControlLoadReductionEvent) event;
			DREventStartTime = new Timepoint(paclre.DREventStartTime);
			DREventEndTime = new Timepoint(paclre.DREventEndTime);
			DRArea = new Parameter<String>(paclre.DRArea);
			LoadReductionNeeded = new Parameter<Double>(paclre.LoadReductionNeeded);
		}
	}

}
