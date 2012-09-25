/**
 * 
 */
package gov.nasa.jpl.ae.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
  
  public static class FileFinder implements FileVisitor< Path > {
    public String fileName;
    public FileFinder( String fileName ) {
      this.fileName = fileName;
    }
    public List< File > files = new ArrayList<File>();
    @Override
    public
        FileVisitResult
        preVisitDirectory( Path dir, BasicFileAttributes attrs )
                                                             throws IOException {
      return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult
        visitFile( Path file, BasicFileAttributes attrs ) throws IOException {
      //Debug.outln("visiting file " + file.toString() );
      if ( file.endsWith( fileName ) ) {
        files.add( file.toFile() );
      }
      return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult
        visitFileFailed( Path file, IOException exc ) throws IOException {
      System.err.println(exc);
      return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult
        postVisitDirectory( Path dir, IOException exc ) throws IOException {
      return FileVisitResult.CONTINUE;
    }};
  
  public static File findFile( final String fileName ) {
    File file = existingFile( fileName );
    if ( file == null ) {
//      PathMatcher matcher =
//          FileSystems.getDefault().getPathMatcher( fileName );
      FileFinder fv = new FileFinder( fileName );
      try {
        Set<FileVisitOption> s = new HashSet<FileVisitOption>();
        Path cwd = FileSystems.getDefault().getPath( getCurrentWorkingDirectory() );
        Files.walkFileTree( cwd, s, 100, fv );
        long latestModified = 0;
        File latestModifiedFile = null;
        for ( File f : fv.files ) {
          long t = f.lastModified();
          if ( t > latestModified ) {
            latestModified = t;
            latestModifiedFile = f;
          }
        }
        file = latestModifiedFile;
      } catch ( IOException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return file;
  }
  
}
