/**
 * 
 */
package gov.nasa.jpl.ae.tests;

import gov.nasa.jpl.ae.event.TimeVaryingMaps;
import gov.nasa.jpl.mbee.util.MoreToString;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class GenerateInputsFromMeterData {

  private static void processArgs( String[] args, Map<String,Object> options ) {
    // set values from command line args
    for ( String arg : args ) {
      if ( arg.contains( "=" ) ) {
        String[] keyValue = arg.split( "=", 1 );
        if ( options.containsKey( keyValue[0] ) ) {
          Object existingValue = options.get( keyValue[0] );
          if ( existingValue instanceof Class ) {
            try {
              options.put( keyValue[0], Class.forName( keyValue[1] ) );
            } catch ( ClassNotFoundException e ) {
              e.printStackTrace();
            }
          } else if ( existingValue instanceof String ) {
            options.put( keyValue[0], keyValue[1] );
          } else {
            System.err.println( "Error! Case for argument of type "
                                + existingValue.getClass() + " is not handled!" );
          }
        }
      }
    }
  }
  
  private static void aggregateDataToFile( String folder, String outputFile,
                                           String filePattern,
                                           Class< ? > dataType ) {

    // read TimeVaryingMaps from files in folder
    TimeVaryingMaps< Object > tvm =
        new TimeVaryingMaps< Object >( "test", Object.class );
    tvm.setComputeAvg( true );
    tvm.fromFolder( folder, filePattern, dataType );
    System.out.println( "tvm map averaging values from folder " + folder
                        + " with file names matching " + filePattern + ":\n"
                        + tvm );
    tvm.toCsvFile( outputFile );
  }
  
  
  
  /**
   * @param args
   */
  public static void processJob( String[] args ) {
    System.out.println( "processing job with args "
        + MoreToString.Helper.toString( args ) );

    // default values
    String folder = "data/meterData";
    String outputFile = "data/allAvg.csv";
    String filePattern = ".*";
    Class< ? > dataType = Double.class;

    // put options in map to make it easy to override
    Map< String, Object > options = new TreeMap< String, Object >();
    options.put( "folder", folder );
    options.put( "outputFile", outputFile );
    options.put( "filePattern", filePattern );
    options.put( "dataType", dataType );

    processArgs( args, options );

    // set variables to new values
    folder = (String)options.get( "folder" );
    outputFile = (String)options.get( "outputFile" );
    filePattern = (String)options.get( "filePattern" );
    dataType = (Class< ? >)options.get( "dataType" );

    aggregateDataToFile( folder, outputFile, filePattern, dataType );
  }
  
  /**
   * @param args
   */
  public static void main( String[] args ) {
    System.out.println( "running with args "
        + MoreToString.Helper.toString( args ) );
    if ( Arrays.asList( args ).contains( "byMonth" ) ) {
      doByMonth( args );
    } else if ( Arrays.asList( args ).contains( "byId" ) ) {
      doById( args );
    } else {
      processJob( args );
    }
  }

  private static void doById( String[] args ) {
  }

  private static void doByMonth( String[] args ) {
  }

}
