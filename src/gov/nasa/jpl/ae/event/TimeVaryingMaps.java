/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.Debug;
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

  public TimeVaryingMaps( String name, Class<V> cls ) {
    super( name );
  }

  /**
   * @param name
   */
  public TimeVaryingMaps( String name, Map<String, Number> fileNames, Class<V> cls ) {
    super( name, cls );
    fromCsvFiles( fileNames, cls );
  }

  public void fromCsvFiles( Map< String, Number > fileNames, Class<V> cls ) {
    String prefix = name;
    fromCsvFiles( fileNames, prefix, cls );
  }
  public <VV extends V> void fromCsvFiles( Map< String, Number > fileNames,
                                           String prefix, Class<VV> cls ) {
    int count = 0;
    maps.clear();
    numberOfSubmaps = 0;
    for ( java.util.Map.Entry< String, Number > e : fileNames.entrySet() ) {
      Number numInstances = e.getValue();
      if ( numInstances.doubleValue() != 0 ) {
        String instanceName = prefix + Utils.numberWithLeadingZeroes( count++, 6 );
        TimeVaryingMap< VV > tvm =
            new TimeVaryingMap< VV >( instanceName, e.getKey(), null, cls );
        maps.add( new Pair< Number, TimeVaryingMap<V> >( numInstances,
                                                         (TimeVaryingMap< V >)tvm ) );
        if ( numInstances.doubleValue() != 1.0 ) {
          tvm = tvm.times( numInstances );
        }
        this.add( tvm );
      }
      numberOfSubmaps = plus(numberOfSubmaps, numInstances);
    }
  }

  /*
   * Populate maps with all of the files in the directory but not subdirectories.
   */
  public <VV extends V> void fromFolder( String folderName, String fileNamePattern, Class<VV> cls ) {
    maps.clear();
    numberOfSubmaps = 0;
    File[] dirFiles = FileUtils.filesInDirectory( folderName );
    if ( dirFiles != null ) {
      String mapPrefix = name;
      if ( Utils.isNullOrEmpty( name ) ) {
        File f = FileUtils.existingFile( folderName );
        mapPrefix = f.getName();
      }
      Map< String, Number > fileNames = new TreeMap< String, Number >();
      Pattern p = Pattern.compile( fileNamePattern );
      for ( File f : dirFiles ) {
        if ( f.isFile() ) {
          if ( p.matcher( f.getName() ).find() ) {
            fileNames.put( f.getAbsolutePath(), 1 );
          }
        }
      }
      fromCsvFiles( fileNames, mapPrefix, cls );
    }
  }
  
  /**
   * @param args
   */
  public static void main( String[] args ) {
    String folder = "data/meterData";
    TimeVaryingMaps< Object > tvm =
        new TimeVaryingMaps< Object >( "test", Object.class );
    tvm.fromFolder( folder, ".*", Double.class );
    System.out.println( "tvm map summing values from folder " + folder + ":\n"
                        + tvm );
    tvm.toCsvFile( "data/all.csv" );
  }

}
