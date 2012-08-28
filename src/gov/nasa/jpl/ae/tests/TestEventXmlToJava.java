/**
 * 
 */
package gov.nasa.jpl.ae.tests;

import gov.nasa.jpl.ae.util.FileUtils;
import gov.nasa.jpl.ae.xml.EventXmlToJava;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


/**
 * @author bclement
 *
 */
public class TestEventXmlToJava {

  /**
   * @param args
   */
  public static void main( String[] args ) {
    // Try to find the XML file to read in.
    String xmlFileName = "exampleDRScenario.xml";
    
    if ( args.length == 1 ) {
      xmlFileName = args[0];
    } else {
//      xmlFileName =
//          "src" + File.separator + "xml" + File.separator
//              + "exampleDRScenario.xml";
    }
    URL xmlUrl = EventXmlToJava.class.getResource(xmlFileName);
    if ( xmlUrl == null ) {
//    File f = new File(xmlFileName);
//    if ( !f.exists() ) {
//      String newXmlFileName = "src" + File.separator + "xml" + File.separator + xmlFileName;
//      f = new File(newXmlFileName);
//    }
//    if ( !f.exists() ) {
      System.err.println( "File \"" + xmlFileName + "\" does not exist!" );
      //System.exit(1);
    }
    //xmlFileName = f.getAbsolutePath();
    xmlFileName = xmlUrl.getFile();
    String directory = "src";//FileUtils.existingFolder( xmlUrl.getPath() );
    System.err.println( "file \"" + xmlFileName + "\"" );
    System.err.println( "directory \"" + directory + "\"" );
    
    
    // Now translate the XML file into Java Event class files.
    EventXmlToJava translator = null;
    try {
      translator =
          new EventXmlToJava( xmlFileName, "" );
    } catch ( ParserConfigurationException | SAXException | IOException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if ( translator != null ) {
      try {
        translator.writeJavaFiles( directory + File.separator + "generated" );
      } catch ( IOException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

}
