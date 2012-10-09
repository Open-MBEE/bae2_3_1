/**
 * 
 */
package gov.nasa.jpl.ae.tests;

import gov.nasa.jpl.ae.util.FileUtils;
import gov.nasa.jpl.ae.util.Utils;
import gov.nasa.jpl.ae.xml.EventXmlToJava;
import gov.nasa.jpl.ae.xml.XmlUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


/**
 * @author bclement
 *
 */
public class TestEventXmlToJava {

  public EventXmlToJava translator = null;
  public String xmlFileName = "exampleDRScenario.xml";
  public String directory = null;
  public String packageName = "generated";
  
  public TestEventXmlToJava(String xmlFileName, String packageName ) {
    this.xmlFileName = xmlFileName;
    if ( !Utils.isNullOrEmpty( packageName ) ) {
      this.packageName = packageName;
    }
    initialize();
  }
  
  public EventXmlToJava initialize() {
    File file = FileUtils.findFile( xmlFileName );
    if ( file != null && file.exists() ) {
      xmlFileName = file.getAbsolutePath();
    }
    String fp = XmlUtils.getXmlFilePath( xmlFileName );
    if ( fp != null ) {
      xmlFileName = fp;
    }
    URL xmlUrl = EventXmlToJava.class.getResource(xmlFileName);
    if ( xmlUrl == null && Utils.isNullOrEmpty( xmlFileName ) ) {
//    File f = new File(xmlFileName);
//    if ( !f.exists() ) {
//      String newXmlFileName = "src" + File.separator + "xml" + File.separator + xmlFileName;
//      f = new File(newXmlFileName);
//    }
//    if ( !f.exists() ) {
      System.err.println( "File \"" + xmlFileName + "\" does not exist!" );
      System.exit(1);
    }
    //xmlFileName = f.getAbsolutePath();
    if ( xmlUrl != null && Utils.isNullOrEmpty( xmlUrl.getFile() ) ) {
      xmlFileName = xmlUrl.getFile();
    }
    //String directory = "src";
    directory = FileUtils.existingFolder( xmlFileName );
    directory = directory.substring( 0, directory.indexOf("CS")+3 ) + "src";
    System.out.println( "file \"" + xmlFileName + "\"" );
    System.out.println( "directory \"" + directory + "\"" );
    
    // Now translate the XML file into Java Event class files.
    try {
      translator =
          new EventXmlToJava( xmlFileName, packageName );
    } catch ( Exception e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return translator;
  }
  
  public void writeFiles() {
    if ( translator != null ) {
      String targetDirectory = null;
      if ( directory == null ) {
        targetDirectory = packageName;
      } else {
        targetDirectory = directory + File.separator + packageName;
      }
      File targetDirectoryFile = new File( targetDirectory );
      if ( !targetDirectoryFile.exists() ) {
        if ( !targetDirectoryFile.mkdirs() );
      } else {
        assert targetDirectoryFile.isDirectory();
      }
      try {
        translator.writeJavaFiles( targetDirectory );
      } catch ( IOException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  /**
   * @param args
   */
  public static void main( String[] args ) {
    System.out.println("Started TestEventXmlToJava " + Utils.toString(args));
    // Try to find the XML file to read in.
    String xmlFileName = "exampleDRScenario.xml";
    
    if ( args.length >= 1 ) {
      xmlFileName = args[0];
    } else {
//      xmlFileName =
//          "src" + File.separator + "xml" + File.separator
//              + "exampleDRScenario.xml";
    }
    String pkgName = null;
    if ( args.length >= 2 ) {
      pkgName = args[1];
    }
    TestEventXmlToJava textj = new TestEventXmlToJava( xmlFileName, pkgName );
    EventXmlToJava translator = textj.translator;

    textj.writeFiles();
    translator.compileLoadAndRun(null);
//    translator.compileJavaFiles( "src" + File.separator + translator.getPackageName() );
//    translator.loadClasses( "bin" + File.separator
//                                  + translator.getPackageName()
//                                            .replace( '.', File.separatorChar ),
//                            translator.getPackageName() );
    
    System.out.println("Completed TestEventXmlToJava");
  }

}
