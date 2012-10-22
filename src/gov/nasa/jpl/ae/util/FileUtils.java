/**
 * 
 */
package gov.nasa.jpl.ae.util;

import java.io.File;
import java.net.URL;
//import java.nio.file.FileSystems;
//import java.nio.file.FileVisitOption;
//import java.nio.file.FileVisitResult;
//import java.nio.file.FileVisitor;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bclement
 *
 */
public final class FileUtils {

  public static boolean pathExists( String path ) {
    File f = existingFile( path );
    return ( f != null );
  }
  
  public static String existingPath( String path ) {
    if ( path != null && pathExists( path ) ) {
      return path;
    }
    return null;
  }
  
  public static String existingPath( URL url ) {
    if ( url != null && pathExists( url.getFile() ) ) {
      return url.getFile();
    }
    return null;
  }
  
  public static File existingFile( String path ) {
    if ( path == null ) return null;
    File f = new File( path );
    if ( f.exists() ) return f;
    return null;
  }

  public static File existingFile( URL url ) {
    if ( url == null ) return null;
    return existingFile( url.getFile() );
  }
  
  public static String existingFolder( String path ) {
    File f = existingFile( path );
    if ( f == null ) return null;
    if ( f.isDirectory() ) return f.getPath();
    return f.getParent();
  }

  protected static String existingFolder( URL url ) {
    File f = existingFile( url );
    if ( f == null ) return null;
    if ( f.isDirectory() ) return f.getPath();
    return f.getParent();
  }
  
  protected static String getCurrentWorkingDirectory() {
    String curDir = System.getProperty("user.dir");
    return curDir;
  }
  
  public static File findFile( final String fileName ) {
    File file = existingFile( fileName );
    if ( file == null ) {
      File cwd = new File( getCurrentWorkingDirectory() );
      assert cwd.exists();
      List< File > q = new ArrayList< File >();
      List< File > files = new ArrayList< File >();
      q.add( cwd );
      while ( !q.isEmpty() ) {
        File f = q.get( 0 );
        q.remove( 0 );
        if ( f.isDirectory() ) {
          File[] dirFiles = f.listFiles();
          q.addAll( Arrays.asList( dirFiles ) );
        }
        if ( f.getAbsolutePath().endsWith( fileName ) ) {
          files.add( f );
        }
      }
      long latestModified = 0;
      File latestModifiedFile = null;
      for ( File f : files ) {
        long t = f.lastModified();
        if ( t > latestModified ) {
          latestModified = t;
          latestModifiedFile = f;
        }
      }
      file = latestModifiedFile;
    }
    return file;
  }
  
}
