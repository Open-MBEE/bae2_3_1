/**
 * 
 */
package gov.nasa.jpl.ae.tests;

import java.lang.reflect.ParameterizedType;

import demandResponse.PlanAndControlLoadReductionEvent;

import gov.nasa.jpl.ae.event.StringParameter;

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
    top.DREventStartTime.setValue( 1200 );
    top.DREventEndTime.setValue( 1800 );
    top.startTime.setValue( 0 );
    top.duration.setValue( 10000 );
    top.execute();
    System.out.println( "execution:\n" + top.executionToString() );
  }
}
