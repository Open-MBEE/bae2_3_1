/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.FileUtils;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 *
 */
public class TimeVaryingMaps< V > extends TimeVaryingMap< V > {

  private static final long serialVersionUID = 2827237765938827119L;

  public final static File dataDirectory = FileUtils.findFile( "data" );
  

  protected List< Pair< Number, TimeVaryingMap< V > > > maps =
      new ArrayList< Pair< Number, TimeVaryingMap< V > > >(); 
  
  protected Number numberOfSubmaps = 0;
  protected String profileName = null;

  public TimeVaryingMaps( String name ) {
    super( name );
  }

  /**
   * @param name
   */
  public TimeVaryingMaps( String name, Map<Number, String> fileNames ) {
    super( name );
    fromCsvFiles( fileNames );
  }

  public void fromCsvFiles( Map< Number, String > fileNames ) {
    String prefix = name;
    fromCsvFiles( fileNames, prefix );
  }
  public void fromCsvFiles( Map< Number, String > fileNames,
                            String prefix ) {
    int count = 0;
    maps.clear();
    numberOfSubmaps = 0;
    for ( java.util.Map.Entry< Number, String > e : fileNames.entrySet() ) {
      Number numInstances = e.getKey();
      if ( numInstances.doubleValue() != 0 ) {
        String instanceName = prefix + Utils.numberWithLeadingZeroes( count++, 6 );
        TimeVaryingMap< V > tvm =
            new TimeVaryingMap< V >( instanceName, e.getValue() );
        maps.add( new Pair< Number, TimeVaryingMap<V> >( numInstances, tvm ) );
        if ( numInstances.doubleValue() > 1 ) {
          tvm = tvm.times( numInstances );
          this.add( tvm );
        }
      }
      numberOfSubmaps = add(numberOfSubmaps, numInstances);
    }
  }

  /*
   * Populate maps with all of the files in the directory but not subdirectories.
   */
  public void fromFolder( String folderName, String fileNamePattern ) {
    maps.clear();
    numberOfSubmaps = 0;
    File[] dirFiles = FileUtils.filesInDirectory( folderName );
    if ( dirFiles != null ) {
      String mapPrefix = name;
      if ( Utils.isNullOrEmpty( name ) ) {
        File f = FileUtils.existingFile( folderName );
        mapPrefix = f.getName();
      }
      Map< Number, String > fileNames = new TreeMap< Number, String >();
      Pattern p = Pattern.compile( fileNamePattern );
      for ( File f : dirFiles ) {
        if ( f.isFile() ) {
          if ( p.matcher( f.getName() ).find() ) {
            fileNames.put( 1, f.getAbsolutePath() );
          }
        }
      }
      fromCsvFiles( fileNames, mapPrefix );
    }
  }
  
  /**
   * @param args
   */
  public static void main( String[] args ) {
    TimeVaryingMaps<Double> tvm = new TimeVaryingMaps< Double >( "test" );
    tvm.fromFolder( "data/meterData", ".*" );
    tvm.toCsvFile( "data/all.csv" );
  }

}
