/**
 * 
 */
package demandResponse;

import gov.nasa.jpl.ae.event.Consumable;
import gov.nasa.jpl.ae.event.LinearTimeline;
import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.event.Timepoint.Units;

/**
 * @author bclement
 *
 */
public class Generation extends LinearTimeline {

  private static final long serialVersionUID = -8766093636354666392L;
  
  protected Double maxRateIncrease = 1000.0; // kW/sec
  protected Double minRateIncrease = 0.0; // kW/sec
  protected Double maxRateDecrease = 2000.0; // kW/sec
  protected Double minRateDecrease = 0.0; // kW/sec
  protected Double min = 0.0;
  protected Double max = 10200.0; // 10.2 GW
  
  public static LinearTimeline dayProfile = initDayProfile();

  private static Double nightGenerationLevel = 5.0e6; // 5 GW
  private static Double middayGenerationLevel = 8.0e6; // 8 GW
  
  protected void init() {
    // Copy the dayProfile into this map based on the epoch offset from midnight.
    int timeSinceMidnight = Timepoint.timeSinceMidnight( Timepoint.getEpoch() );
    //putAll( dayProfile );
    Timepoint tp = dayProfile.floorKey( new Timepoint( "", timeSinceMidnight, null ) );
    int _24hours = (int)( 24.0 / Units.conversionFactor( Units.hours ) );
    // Start with time zero == epoch.
    int timeSinceEpoch = 0;
    while ( timeSinceEpoch < Timepoint.getHorizonDuration() ) {
      // Translate the time to the time of day.
      int timeOfDay = (timeSinceEpoch + timeSinceMidnight) % _24hours; 
      setValue( new Timepoint( "", timeSinceEpoch, null ),
                dayProfile.getValue( timeOfDay ) );
      int thisTime = tp.getValue();
      tp = dayProfile.getTimepointAfter( tp );
      // If we're at the end of the dayProfile points, roll over to the start.
      if ( tp == null ) {
        tp = dayProfile.firstKey();
        timeSinceEpoch += _24hours - thisTime + tp.getValue();
      } else {
        timeSinceEpoch += tp.getValue() - thisTime;
      }
    }
    // Set the value at the horizon end.
    timeSinceEpoch = Timepoint.getHorizonDuration() - 1;
    int tMod = (timeSinceEpoch + timeSinceMidnight) % _24hours; 
    setValue( new Timepoint( "", timeSinceEpoch, null ),
              dayProfile.getValue( tMod ) );
  }

  // define a profile of generation for a given day
  private static LinearTimeline initDayProfile() {
    LinearTimeline profile = new LinearTimeline( "dayProfile" );
    double conversionFactor = 1.0 / Units.conversionFactor( Units.hours );
    profile.setValue( new Timepoint( "", (int)0, null ), 6000.0 );
    profile.setValue( new Timepoint( "", (int)( 5.0 * conversionFactor ), null ),
                      nightGenerationLevel  );
    profile.setValue( new Timepoint( "", (int)( 11.0 * conversionFactor ), null ),
                      middayGenerationLevel );
    profile.setValue( new Timepoint( "", (int)( 17.0 * conversionFactor ), null ),
                      middayGenerationLevel );
    profile.setValue( new Timepoint( "", (int)( 23.0 * conversionFactor ), null ),
                      nightGenerationLevel );
    profile.setValue( new Timepoint( "", (int)( 24.0 * conversionFactor ), null ),
                      nightGenerationLevel );
    return null;
  }

  /**
   * @param name
   */
  public Generation( String name ) {
    super( name );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param name
   * @param defaultValue
   */
  public Generation( String name, Double defaultValue ) {
    super( name, defaultValue );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param args
   */
  public static void main( String[] args ) {
    // TODO Auto-generated method stub

  }

}
