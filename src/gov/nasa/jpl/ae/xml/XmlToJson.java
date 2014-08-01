/**
 * 
 */
package gov.nasa.jpl.ae.xml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Assert;

/**
 * @author bclement
 * 
 * Just translating XML to JSON.
 * 
 */
public class XmlToJson {

  public static String xmlToJson( String inputFileName, String outputFileName,
                                String encoding ) {
    if ( inputFileName == null | inputFileName.isEmpty() ) {
      Assert.fail("Error! No file! Need to supply the name of a file to read!");
      return null;
    }
    if (outputFileName == null | outputFileName.isEmpty() ) {
      outputFileName = inputFileName + ".json.txt";
    }
    if ( encoding == null |  encoding.isEmpty() ) {
      encoding = "UTF-8";
    }
    
    String xmlString = fileToString(inputFileName, encoding);

    JSONObject jsonObject = null;
    try {
      jsonObject = XML.toJSONObject( xmlString );  
      stringToFile( jsonObject.toString(2), outputFileName, encoding );
    } catch ( JSONException e ) {
      e.printStackTrace();
    }
    return xmlString;
  }

  public static String fileToString( String fileName, String encoding ) {
    try {

      FileInputStream fis = null;
      fis = new FileInputStream( fileName );
      InputStreamReader in = null;
      in = new InputStreamReader( fis, encoding );
      BufferedReader reader = new BufferedReader( in );
      StringBuffer xmlStringBuf = new StringBuffer();
      String line;
      while ( ( line = reader.readLine() ) != null ) {
        xmlStringBuf.append( line );
      }
      return xmlStringBuf.toString();

    } catch ( FileNotFoundException e ) {
      e.printStackTrace();
    } catch ( UnsupportedEncodingException e ) {
      e.printStackTrace();
    } catch ( IOException e ) {
      e.printStackTrace();
    }
    return null;
  }

  public static void stringToFile( String string, String outputFileName,
                                    String encoding ) {
    try {
      Writer w =
          new OutputStreamWriter( new FileOutputStream( outputFileName ),
                                  encoding );
      w.write( string );
      w.close();
    } catch ( UnsupportedEncodingException e ) {
      e.printStackTrace();
    } catch ( FileNotFoundException e ) {
      e.printStackTrace();
    } catch ( IOException e ) {
      e.printStackTrace();
    }
  }

  /**
   * @param args
   */
  public static void main( String[] args ) {
    // Expected args: String inputFileName = null, outputFileName = null,
    // encoding = null;
    String inputOutputAndEncoding[] = { null, null, null };

    for ( int i = 0; i < Math.min( args.length, inputOutputAndEncoding.length ); ++i ) {
      inputOutputAndEncoding[ i ] = args[ i ];
    }

    String xmlString =
        xmlToJson( inputOutputAndEncoding[ 0 ], inputOutputAndEncoding[ 1 ],
                   inputOutputAndEncoding[ 2 ] );
    System.out.println( xmlString );
  }

}
