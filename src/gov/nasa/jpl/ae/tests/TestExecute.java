/**
 * 
 */
package gov.nasa.jpl.ae.tests;

import java.lang.reflect.ParameterizedType;

import demandResponse.PlanAndControlLoadReductionEvent;

import gov.nasa.jpl.ae.event.StringParameter;
import gov.nasa.jpl.ae.event.Timepoint;

/**
 * @author bclement
 * 
 */
public class TestExecute {

  /**
   * @param args
   */
  public static void main( String[] args ) {
    // Just testing Java reflection
    StringParameter p = new StringParameter( "foo", null );
    System.out.println( "parameter to StringParameter may be "
                        + ( (ParameterizedType)p.getClass()
                                                .getGenericSuperclass() ).getActualTypeArguments()[ 0 ] );

    // The real scenario starts by instantiating a top-level event, assigning
    // input parameter values, and executing(), which grounds all values and
    // satisfies/solves constraints.
    PlanAndControlLoadReductionEvent top =
        new PlanAndControlLoadReductionEvent();
    top.DRArea.setValue( "Area 51" );
    top.LoadReductionNeeded.setValue( 10000.0 );
    top.DREventStartTime.setValue( 1200L );
    top.DREventEndTime.setValue( 1800L );
    top.startTime.setValue( 0L );
    top.duration.setValue( 10000L );
    double animationDuration = 30.0;
    top.executeAndSimulate(Timepoint.getHorizonDuration() / animationDuration);
    //System.out.println( "execution:\n" + top.executionToString() );
  }
}
