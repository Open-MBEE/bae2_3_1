/**
 * 
 */
package demandResponse;

import gov.nasa.jpl.ae.event.LinearTimeline;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.event.Timepoint.Units;
import gov.nasa.jpl.ae.util.Debug;

/**
 * @author bclement
 *
 */
public class Generation extends LinearTimeline {

  private static final long serialVersionUID = -8766093636354666392L;
  
  DRObject drObject = null;
  boolean drEventExists = false;
  static int numberOfCustomers = 100;

  protected double maxRateIncrease = 1000.0; // kW/sec
  protected double minRateIncrease = 0.0; // kW/sec
  protected double maxRateDecrease = 2000.0; // kW/sec
  protected double minRateDecrease = 0.0; // kW/sec
  protected double min = 0.0;
  protected double max = 10200.0; // 10.2 GW
  
  public static double nightGenerationLevel = 1.8 * numberOfCustomers; // in kWatts, 5.0e6 = 5 GW
  public static double middayGenerationLevel = 4.0 * numberOfCustomers; // 8.0e6 = 8 GW
  public static LinearTimeline dayProfile = initDayProfile();

  /**
   * @param name
   */
  public Generation( String name ) {
    super( name );
    init();
  }

  /**
   * @param name
   * @param defaultValue
   */
  public Generation( String name, Double defaultValue ) {
    super( name, defaultValue );
    init();
  }

  protected void init() {
    if ( drEventExists ) {
      drObject = new DRObject( true );
    }
    System.out.println("Initializing Generation with dayProfile = " + dayProfile );
    // Copy the dayProfile into this map based on the epoch offset from midnight.
    int timeSinceMidnight = Timepoint.timeSinceMidnight( Timepoint.getEpoch() );
    System.out.println( "timeSinceMidnight = " + timeSinceMidnight );
    //putAll( dayProfile );
    Parameter< Integer > tp = dayProfile.floorKey( new Timepoint( "", timeSinceMidnight, dayProfile ) );
    System.out.println( "dayProfile.floorKey(timeSinceMidnight) = " + tp );
    int _24hours = (int)( 24.0 / Units.conversionFactor( Units.hours ) );
    System.out.println( "_24hours = " + _24hours );
    // Start with time zero == epoch.
    int timeSinceEpoch = 0;
    while ( timeSinceEpoch < Timepoint.getHorizonDuration() ) {
      // Translate the time to the time of day.
      int timeOfDay = (timeSinceEpoch + timeSinceMidnight) % _24hours; 

      // Reduce the midday generation by a predicted amount if having a DR event
      double generationAmount = dayProfile.getValue( timeOfDay );
      boolean reduceForDREvent = drEventExists && drObject != null && generationAmount == middayGenerationLevel; 
      if ( Debug.isOn() ) {
        Debug.out( "Generation.setValue(" + timeSinceEpoch
                     + ", dayProfile.getValue(" + timeOfDay + ")="
                     + dayProfile.getValue( timeOfDay ) + ")" );
      }
      if ( reduceForDREvent ) {
        generationAmount -= predictedLoadReduction();
        if ( Debug.isOn() ) {
          Debug.outln( " - predictedLoadReduction=" + predictedLoadReduction()
                     + " = " + generationAmount );
        }
      } else {
        Debug.outln( "" );
      }
      setValue( new Timepoint( "", timeSinceEpoch, this ), generationAmount );

      int thisTime = tp.getValue(false);
      tp = dayProfile.getTimepointAfter( tp );
      // If we're at the end of the dayProfile points, roll over to the start.
      if ( tp == null ) {
        tp = dayProfile.firstKey();
        timeSinceEpoch += _24hours - thisTime + tp.getValue(false);
      } else {
        timeSinceEpoch += tp.getValue(false) - thisTime;
      }
    }
    // Set the value at the horizon end.
    timeSinceEpoch = Timepoint.getHorizonDuration() - 1;
    int tMod = (timeSinceEpoch + timeSinceMidnight) % _24hours; 
    setValue( new Timepoint( "", timeSinceEpoch, this ),
              dayProfile.getValue( tMod ) );

    System.out.println( this );
    
/*  // Ramp down the generation with the   
    if ( drObject != null && drEventExists ) {
      Timepoint st = new Timepoint( drObject.getStartTime() );
      Timepoint et = new Timepoint( drObject.getEndTime() );
      double rt = drObject.rampDuration;
      st.setValue( (int)( st.getValue( true ) + rt ) );
      et.setValue( (int)( et.getValue( true ) + rt ) );

      double gs = getValue( st );
      double ge = getValue( et );
      gs -= predictedLoadReduction();
      ge -= predictedLoadReduction();

      setValue( st, gs );
      setValue( et, ge );
    }
*/
  }
  
  double predictedLoadReduction() {
    if ( drObject == null || !drEventExists ) return 0.0;
    return 0.15 * numberOfCustomers
           * ( drObject.applianceType.maxPower - drObject.applianceType.minPower )
           / 1000.0;
  }

  // define a profile of generation for a given day
  private static LinearTimeline initDayProfile() {
    LinearTimeline profile = new LinearTimeline( "dayProfile" );
    double conversionFactor = 1.0 / Units.conversionFactor( Units.hours );
    double[] t = new double[]{0.0, 2.0, 6.0, 11.0, 21.5, 24.0};
    profile.setValue( new Timepoint( "", new Integer( (int)t[0] ), null ),
                      nightGenerationLevel
                          + ( middayGenerationLevel - nightGenerationLevel )
                          * 2.0 / ( 24.0 - 21.5 + 2.0 ) );
    //    profile.setValue( new Timepoint( "", new Integer(0), null ), nightGenerationLevel );
    profile.setValue( new Timepoint( "", new Integer((int)( t[1] * conversionFactor )), null ),
                      nightGenerationLevel );
    profile.setValue( new Timepoint( "", new Integer((int)( t[2] * conversionFactor )), null ),
                      nightGenerationLevel  );
    profile.setValue( new Timepoint( "", new Integer((int)( t[3] * conversionFactor )), null ),
                      middayGenerationLevel );
    profile.setValue( new Timepoint( "", new Integer((int)( t[4] * conversionFactor )), null ),
                      middayGenerationLevel );
//    profile.setValue( new Timepoint( "", new Integer((int)( t[5] * conversionFactor )), null ),
//                      profile.getValue( (int)t[0] ) );
    return profile;
  }

}
