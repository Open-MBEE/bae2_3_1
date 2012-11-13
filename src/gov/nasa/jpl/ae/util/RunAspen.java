package gov.nasa.jpl.ae.util;

import java.io.File;
import java.io.IOException;

public class RunAspen {

  /**
   * @param args
   */
  public static void main( String[] args ) {
    System.out.println("args = " + args );
    if ( args.length < 2 ) {
      System.out.println( "usage: RunAspen <mdl file name> <ini file name>" );
    } else {
//      //get object which represents the workspace  
//      IWorkspace workspace = ResourcesPlugin.getWorkspace();  
//      //get location of workspace (java.io.File)  
//      File workspaceDirectory = workspace.getRoot().getLocation().toFile()
      File file = FileUtils.findFile( "runaspen.sh" );
      String OS = System.getProperty("os.name").toLowerCase();
      boolean isWin = (OS.indexOf("win") >= 0);
      String ext = isWin ? "cmd" : "sh";
      String cmd = "runaspen." + ext + " " + args[0] + " " + args[1];
      try {
        Runtime.getRuntime().exec( cmd, null, file.getParentFile() );
      } catch ( IOException e ) {
        e.printStackTrace();
      }
    }
  }

}
