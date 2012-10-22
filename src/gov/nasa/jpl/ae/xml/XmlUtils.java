package gov.nasa.jpl.ae.xml;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
// import javax.xml.stream.XMLStreamException;
// import javax.xml.stream.events.XMLEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;
// import javax.xml.xpath.XPathExpression;

import org.hamcrest.core.IsNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import gov.nasa.jpl.ae.event.DurativeEvent;
import gov.nasa.jpl.ae.event.ElaborationRule;
import gov.nasa.jpl.ae.event.Event;
import gov.nasa.jpl.ae.event.EventInvocation;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.FileUtils;
import gov.nasa.jpl.ae.util.Utils;
import gov.nasa.jpl.ae.xml.EventXmlToJava.Param;

@SuppressWarnings( "unused" )
public class XmlUtils {

  public static final String defaultXsdFileName = "eventSchema.xsd";
  public static final String defaultXsdFilePath = getXmlFilePath( defaultXsdFileName );
  public static final String defaultXmlFolder = FileUtils.existingFolder( defaultXsdFilePath );

  /*
  public XmlUtils( String xmlFileName )
      throws SAXException, IOException, ParserConfigurationException {

    // Translate XML to a DOM Document.
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware( true );
    DocumentBuilder builder;
    // XPathExpression expr = null; // alternative way to access XML
    builder = factory.newDocumentBuilder();
    xmlDocDOM = builder.parse( xmlFileName );

    if ( !validateXML( xmlFileName, xmlDocDOM ) ) {
      System.err.println( "Warning! XML file "
                          + xmlFileName
                          + " does not validate against its schema definition.  "
                          + "Continuing anyway." );
    }

    Node scenarioNode = findNode( xmlDocDOM, "scenario" );
    Assert.assertNotNull( scenarioNode );
    
    // get epoch
    String epochString = getChildElementText( scenarioNode, "epoch" );
    if ( epochString == null || epochString.isEmpty() ) {
      System.out.println( "no epoch specified; using default" );
    }

    // process classes first
    NodeList nodeList = xmlDocDOM.getElementsByTagName( "classes" );
    Assert.assertTrue( nodeList.getLength() < 2 );
    if ( nodeList.getLength() == 1 ) {
      List< Node > nList = getChildNodes( nodeList.item( 0 ), "class" );
      for ( int i = 0; i < nList.size(); i++ ) {
        Node node = nList.get( i );
        //processClass( node );
      }
    }
  }

*/

  public static int getDurationInSeconds( String durStr ) {
    if ( Utils.isNullOrEmpty( durStr ) ) return -1;
    if ( durStr.charAt( 0 ) != 'P' ) {
      gov.nasa.jpl.ae.event.Duration duration =
          gov.nasa.jpl.ae.event.Duration.fromString(durStr);
      return (int)(duration.toMillis() / 1000);
    }
    DatatypeFactory f;
    try {
      f = DatatypeFactory.newInstance();
      //String fullDurationString = buildFullDurationString(durStr);
      javax.xml.datatype.Duration d = f.newDuration( durStr );//fullDurationString );
      if ( d != null ) {
        return (int)(d.getTimeInMillis( Timepoint.getEpoch() ) / 1000);
      }
    } catch ( DatatypeConfigurationException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return -1;
  }
  
  public static String buildFullDurationString( String durStr ) {
    StringBuilder fullDurationString = new StringBuilder("P");
    String[] fields = new String[] { "Y", "M", "D", "H", "M", "S" };
    int[] numbers = new int[] { 0, 0, 0, 0, 0, 0, 0 };
    TreeMap<String,Integer> map = new TreeMap< String, Integer >();
    for ( String f : fields ) {
      map.put( f, 0 );
    }
    int pos1 = 1, pos2 = 1;
    int len = durStr.length();
    while( pos2 < len ) {
      pos1 = pos2;
      while ( pos2 < len && Character.isDigit( durStr.charAt( pos2 ) ) ) {
        ++pos2;
      }
      if ( pos2 < len && pos2 - pos1 > 0 ) {
        int num = Utils.toInteger( durStr.substring( pos1, pos2 ) );
        pos1 = pos2;
        while ( pos2 < len && !Character.isDigit( durStr.charAt( pos2 ) ) ) {
          ++pos2;
        }
        String field = durStr.substring( pos1, pos2 );
        // just getting first character in case 'T' follows 'D' for the T format 
        map.put( field.substring( 0, 1 ).toUpperCase(), num );
      }
    }
    for ( String f : fields ) {
      fullDurationString.append( map.get( f ) );
      fullDurationString.append( f );
      if ( f.equals( "D" ) ) {
        fullDurationString.append( "T" );
      }
    }
    return fullDurationString.toString();
  }

  // Validate the AE XML.
  public static boolean
      validateXML( String aeXmlFilePathName, Document xmlDoc ) throws SAXException,
                                                                      IOException {
    String xsdFileName = defaultXsdFilePath; // default
    String xsdFilePathName = defaultXsdFilePath; // default

    // Make sure we know where to find the xmlFileName.
    if ( !FileUtils.pathExists( aeXmlFilePathName ) ) {
      aeXmlFilePathName = getXmlFilePath( aeXmlFilePathName );
    }
    if (!FileUtils.pathExists( aeXmlFilePathName ) ) {
      System.err.println( EventXmlToJava.class.getSimpleName()
                          + ".validateXML() could not find xml file \""
                          + aeXmlFilePathName  + "\"" );
      return false;
    }
    
    // Try to find a schema file reference in the XML.
    NodeList nodeList = xmlDoc.getElementsByTagName( "scenario" );
    Assert.assertTrue( nodeList.getLength() < 2 );
    if ( nodeList.getLength() == 1 ) {
      NamedNodeMap map = nodeList.item( 0 ).getAttributes();
      if ( map != null ) {
        for ( int i = 0; i < map.getLength(); ++i ) {
          Node a = map.item( i );
          if ( a.getNodeValue().endsWith( ".xsd" ) ) {
            xsdFileName = a.getNodeValue();
            break;
          }
        }
      }
    }
    
    // Find the path to the XSD file.
    xsdFilePathName = FileUtils.existingPath( xsdFileName );
    // Check in the same directory as the XML file if not easily found.
    if ( xsdFilePathName == null ) {
      // Change path for the XSD file to the same as that for the XML file if no
      // path is specified.
      if ( !xsdFileName.contains( File.separator )
           && aeXmlFilePathName.contains( File.separator ) ) {
        xsdFilePathName = FileUtils.existingFolder( aeXmlFilePathName )
        // xmlFileName.substring( 0, xmlFileName.lastIndexOf( File.separator ) )
                      + File.separator + xsdFileName;
      }
    }
    // If not there, see if we can find it in some general places where XML
    // files can be found.
    if ( xsdFilePathName == null ) {
      xsdFilePathName = getXmlFilePath( xsdFileName );
    }
    // If not there, try to use the default.
    if ( xsdFilePathName == null ) {
      xsdFilePathName = FileUtils.existingPath( defaultXsdFilePath );
    }
    // Complain if still not found
    if ( xsdFilePathName == null ) {
      System.err.println( EventXmlToJava.class.getSimpleName()
                          + ".validateXML() could not find xsd file \""
                          + xsdFileName  + "\"" );
      return false;
    }
    return validateXML( aeXmlFilePathName, xsdFilePathName );
  }

  public static boolean validateXML( String xmlFileName, String xsdFileName )
                          throws SAXException, IOException {
    if ( Debug.isOn() ) Debug.outln( "validateXML(" + xmlFileName + ", " + xsdFileName 
                        + ")" );
    Source schemaFile = new StreamSource( new File( xsdFileName ) );
    Source xmlFile = new StreamSource( new File( xmlFileName ) );
    SchemaFactory schemaFactory =
        SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
    Schema schema = schemaFactory.newSchema( schemaFile );
    Validator validator = schema.newValidator();
    try {
      validator.validate( xmlFile );
      System.out.println( xmlFile.getSystemId() + " is valid" );
    } catch ( SAXException e ) {
      System.err.println( xmlFile.getSystemId() + " is NOT valid" );
      System.err.println( "Reason: " + e.getLocalizedMessage() );
      return false;
    }
    return true;
  }

  // Utility XML DOM helpers

  /**
   * Gets the value of the named attribute for the node.
   * 
   * @param node
   *          the node with the attribute
   * @param attributeName
   *          the name of the attribute whose node is to be returned
   * @return the node of the named attribute of the input node or null if the
   *         attribute does not exist for the node or if the node or
   *         attributeName are null.
   */
  public static Node getAttributeNode( Node node, String attributeName ) {
    if ( node == null ) return null;
    if ( Utils.isNullOrEmpty( attributeName ) ) return null;
    Node attributeNode = node.getAttributes().getNamedItem( attributeName );
    return attributeNode;
  }
  
  /**
   * Gets the value of the named attribute for the node.
   * 
   * @param node
   *          the node with the attribute
   * @param attributeName
   *          the name of the attribute whose value is to be returned
   * @return the value of the named attribute for the input node or null if the
   *         attribute does not exist for the node or if the node or
   *         attributeName are null.
   */
  public static String getAttributeValue( Node node, String attributeName ) {
    if ( node == null ) return null;
    if ( Utils.isNullOrEmpty( attributeName ) ) return null;
    Node attributeNode = getAttributeNode( node, attributeName );
    if ( attributeNode != null ) {
      attributeNode.getNodeValue();
    }
    return null;
  }
  
  // Return the first child of node that has the name of childTag.
  public static Node getChildNode( Node node, String childTag ) {
    if ( node == null ) return null;
    NodeList nodeList = node.getChildNodes();
    for ( int i = 0; i < nodeList.getLength(); i++ ) {
      Node childNode = nodeList.item( i );
      if ( childNode != null && childNode.getLocalName() != null
           && childNode.getLocalName().equals( childTag ) ) {
        return childNode;
      }
    }
    return null;
  }

  // Return a the children of node that have a name of childTag.
  public static List< Node > getChildNodes( Node node, String childTag ) {
    ArrayList< Node > nodes = new ArrayList< Node >();
    if ( node == null ) return nodes;
    NodeList nodeList = node.getChildNodes();
    for ( int i = 0; i < nodeList.getLength(); i++ ) {
      Node childNode = nodeList.item( i );
      if ( childNode != null && childNode.getLocalName() != null
           && childNode.getLocalName().equals( childTag ) ) {
        nodes.add( childNode );
      }
    }
    return nodes;
  }

  // Return the text content of the first child of node that has the name of
  // childTag.
  public static String getChildElementText( Node node, String childTag ) {
    if ( node == null ) return null;
    Node n = getChildNode( node, childTag );
    if ( n != null ) return n.getTextContent().trim();
    return null;
  }

  // Return the text content of all children of node that have the name of
  // childTag.
  public static List< String > getChildrenElementText( Node node,
                                                          String childTag ) {
    ArrayList< String > stringList = new ArrayList< String >();
    if ( node == null ) return stringList;
    List< Node > list = getChildNodes( node, childTag );
    for ( Node n : list ) {
      if ( n != null ) stringList.add( n.getTextContent().trim() );
    }
    return stringList;
  }

  // Recursively find all nodes in the tree from root with a local name of tag.
  public static List< Node > findNodes( Node root, String tag ) {
    List< Node > nodes = new ArrayList< Node >();
    if ( root != null && root.getLocalName() != null
         && root.getLocalName().equals( tag ) ) {
      nodes.add( root );
    }
    NodeList nodeList = root.getChildNodes();
    for ( int i = 0; i < nodeList.getLength(); i++ ) {
      Node childNode = nodeList.item( i );
      nodes.addAll( findNodes( childNode, tag ) );
    }
    return nodes;
  }

  // Return the first discovered node in the tree from root with a local name of
  // tag.
  public static Node findNode( Node root, String tag ) {
    if ( root != null && root.getLocalName() != null
         && root.getLocalName().equals( tag ) ) {
      return root;
    }
    NodeList nodeList = root.getChildNodes();
    for ( int i = 0; i < nodeList.getLength(); i++ ) {
      Node childNode = nodeList.item( i );
      Node firstNode = findNode( childNode, tag );
      if ( firstNode != null ) {
        return firstNode;
      }
    }
    return null;
  }

  // Return the text content of the first discovered node that has the name of
  // childTag.
  public static String findElementText( Node node, String childTag ) {
    if ( node == null ) return null;
    Node n = findNode( node, childTag );
    if ( n != null ) return n.getTextContent().trim();
    return null;
  }

  // Return the text content of the all nodes that have the name of
  // childTag.
  public static List< String > findElementsText( Node node,
                                                           String childTag ) {
    ArrayList< String > stringList = new ArrayList< String >();
    if ( node == null ) return stringList;
    List< Node > list = findNodes( node, childTag );
    for ( Node n : list ) {
      if ( n != null ) stringList.add( n.getTextContent().trim() );
    }
    return stringList;
  }
  
  public static Node getEnclosingNodeWithName( Node node, String name ) {
    while ( node != null ) {
      if ( node.getLocalName() != null && node.getLocalName().equals( name ) ) {
        return node;
      }
      node = node.getParentNode(); 
    }
    return null;
  }

  public static boolean isAttributeTrue( Node node, String attributeName ) {
    String staticValue = XmlUtils.getAttributeValue( node, attributeName );
    return ( staticValue != null && ( staticValue.toLowerCase().equals( "true" )
                                      || staticValue.equals( "1" ) ) );
  }
  
  // XML file/path helpers
  
//public static String getDefaultXsdFileName() {
//URL url = EventXmlToJava.class.getResource( "eventSchema.xsd" );
//if ( url == null ) {
//  return ( defaultXmlFolder == null ? "" : defaultXmlFolder
//                                           + File.separator )
//         + "eventSchema.xsd";
//}
//String javaFile = url.getFile();
//if ( javaFile == null || javaFile.isEmpty() ) return null;
//return null;
//}

  public static String getDefaultXsdFilePath() {
    String filePath = getXmlFilePath( defaultXsdFileName );
    if ( filePath == defaultXsdFileName ) { // getFilePath() probably failed
      URL url = EventXmlToJava.class.getResource( "EventXmlToJava.java" );
      String fileName = FileUtils.existingPath( url );
      if ( fileName != null ) {
        filePath = fileName;
      }
    }
    return filePath;
  }

  // Try to find a file in general places where XML files can be found.
  public static String getXmlFilePath( String fileName ) {
    String filePath = null;
    URL url = EventXmlToJava.class.getResource( fileName );
    if ( url != null ) {
      filePath = FileUtils.existingPath( url );
    }
    if ( filePath == null ) {
      Enumeration< URL > urls = null;
      try {
        urls =
            Thread.currentThread().getContextClassLoader()
                  .getResources( fileName );
        if ( urls == null || !urls.hasMoreElements() ) {
          Thread.currentThread().getContextClassLoader();
          urls = ClassLoader.getSystemResources( fileName );
        }
      } catch ( IOException e ) {
        e.printStackTrace();
      }
      if ( urls == null || !urls.hasMoreElements() ) return null;
      while ( urls.hasMoreElements() ) {
        url = urls.nextElement();
        filePath = FileUtils.existingPath( url );
        if ( filePath != null ) return filePath;
      }
    }
    if ( filePath == null ) {
      filePath = fileName;
    }
    return filePath;
  }

}
