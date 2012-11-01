/**
 * 
 */
package demandResponse;

import gov.nasa.jpl.ae.event.LinearTimeline;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.event.Timepoint.Units;

/**
 * @author bclement
 *
 */
public class Generation extends LinearTimeline {

  private static final long serialVersionUID = -8766093636354666392L;
  
  protected double maxRateIncrease = 1000.0; // kW/sec
  protected double minRateIncrease = 0.0; // kW/sec
  protected double maxRateDecrease = 2000.0; // kW/sec
  protected double minRateDecrease = 0.0; // kW/sec
  protected double min = 0.0;
  protected double max = 10200.0; // 10.2 GW
  
  public static double nightGenerationLevel = 5.0e2; // in kWatts, 5.0e6 = 5 GW
  public static double middayGenerationLevel = 8.0e2; // 8 GW
  public static LinearTimeline dayProfile = initDayProfile();

  
  protected void init() {
    System.out.println("Initializing Generation with dayProfile = " + dayProfile );
    // Copy the dayProfile into this map based on the epoch offset from midnight.
    int timeSinceMidnight = Timepoint.timeSinceMidnight( Timepoint.getEpoch() );
    System.out.println( "timeSinceMidnight = " + timeSinceMidnight );
    //putAll( dayProfile );
    Timepoint tp = dayProfile.floorKey( new Timepoint( "", timeSinceMidnight, dayProfile ) );
    System.out.println( "dayProfile.floorKey(timeSinceMidnight) = " + tp );
    int _24hours = (int)( 24.0 / Units.conversionFactor( Units.hours ) );
    System.out.println( "_24hours = " + _24hours );
    // Start with time zero == epoch.
    int timeSinceEpoch = 0;
    while ( timeSinceEpoch < Timepoint.getHorizonDuration() ) {
      // Translate the time to the time of day.
      int timeOfDay = (timeSinceEpoch + timeSinceMidnight) % _24hours; 
      System.out.println( "Generation.setValue(" + timeSinceEpoch
                          + ", dayProfile.getValue(" + timeOfDay + ") = "
                          + dayProfile.getValue( timeOfDay ) );
      setValue( new Timepoint( "", timeSinceEpoch, this ),
                dayProfile.getValue( timeOfDay ) );
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
  }

  // define a profile of generation for a given day
  private static LinearTimeline initDayProfile() {
    LinearTimeline profile = new LinearTimeline( "dayProfile" );
    double conversionFactor = 1.0 / Units.conversionFactor( Units.hours );
    profile.setValue( new Timepoint( "", new Integer(0), null ), nightGenerationLevel );
    profile.setValue( new Timepoint( "", new Integer((int)( 6.0 * conversionFactor )), null ),
                      nightGenerationLevel  );
    profile.setValue( new Timepoint( "", new Integer((int)( 11.0 * conversionFactor )), null ),
                      middayGenerationLevel );
    profile.setValue( new Timepoint( "", new Integer((int)( 18.0 * conversionFactor )), null ),
                      middayGenerationLevel );
    profile.setValue( new Timepoint( "", new Integer((int)( 23.0 * conversionFactor )), null ),
                      nightGenerationLevel );
    profile.setValue( new Timepoint( "", new Integer((int)( 24.0 * conversionFactor )), null ),
                      nightGenerationLevel );
    return profile;
  }

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

  /**
   * @param args
   */
  public static void main( String[] args ) {
    // TODO Auto-generated method stub

  }

}
