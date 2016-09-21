package gov.nasa.jpl.ae.xml;

import org.json.JSONObject;
import org.json.XML;

import japa.parser.ASTHelper;
import japa.parser.ASTParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.body.VariableDeclaratorId;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ExplicitConstructorInvocationStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.Type;
import japa.parser.ast.type.VoidType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;
// import javax.xml.xpath.XPathExpression;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// Keep these for resolving class references. 
import gov.nasa.jpl.ae.event.*;
import gov.nasa.jpl.ae.util.ClassData;
import gov.nasa.jpl.ae.util.JavaForFunctionCall;
import gov.nasa.jpl.ae.util.JavaToConstraintExpression;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.ae.fuml.*;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.FileUtils;
import gov.nasa.jpl.mbee.util.NameTranslator;
import gov.nasa.jpl.mbee.util.TimeUtils;
import gov.nasa.jpl.mbee.util.Timer;
import gov.nasa.jpl.mbee.util.Utils;
import demandResponse.*;

import gov.nasa.jpl.mbee.util.Random;

/*
 * Translates XML to executable Java classes for Analysis Engine behavior (based
 * on events such as those of the Timeline Ontology).
 */
public class EventXmlToJava {
  
  // Used for generating unique variable names where unspecified.
  protected static int counter = 0;

  // The source XML document.
  protected Document xmlDocDOM = null;

  // The top level XML DOM node
  Node scenarioNode = null;
  
//  // Map class name (long?) -> set of javaparser.ConstructorDeclarations
//  protected Map< String, Set< ConstructorDeclaration> > constructorDeclarations =
//      new TreeMap< String, Set< ConstructorDeclaration > >();
  
  protected Map< String, Boolean > isEventMap = new TreeMap< String, Boolean >();
  
  String xmlFileName = "exampleDRScenario.xml";
  
  /**
   * The package for generated Java files. This is where Java files will be
   * written in {PROJECT}/src.
   */
  public String packageName = "generated";

  // This is for handling class names outside Java syntax.
  protected NameTranslator nameTranslator = new NameTranslator();

  // These are for removing default dependencies that are overridden.
  private boolean gotStartTimeDependency;
  private boolean gotEndTimeDependency;
  private boolean gotDurationDependency;

  demandResponse.Customer c = new Customer( "stupid class loader" );
  ObjectFlow<Object> o = new ObjectFlow< Object >( "stupid class loader" );

  protected JavaCompiler compiler = null;
  protected StandardJavaFileManager fileManager = null;

  public JavaCompiler getCompiler() {
    if ( compiler == null ) {
      compiler = ToolProvider.getSystemJavaCompiler();
    }
    return compiler;
  }

  public StandardJavaFileManager getFileManager() {
    if ( fileManager == null ) {
      fileManager = ( getCompiler() == null ? null : getCompiler().getStandardFileManager(null, null, null) );
    }
    return fileManager;
  }

  protected ClassLoader loader = null;
  protected Class<?> mainClass = null;

  protected DurativeEvent mainInstance = null;

  protected JavaToConstraintExpression expressionTranslator = null;

  //protected ClassData classData = new ClassData();

  public EventXmlToJava( String xmlFileName, String pkgName, boolean translate )
    throws ParserConfigurationException, SAXException, IOException {
    System.out.println( "\nEventXmlToJava(xmlFileName=" + xmlFileName
                        + ", packageName=" + pkgName + ", translate="
                        + translate + "): starting stats timer\n" );
    Timer timer = new Timer();
    
    this.xmlFileName = xmlFileName;
    if ( pkgName != null && !pkgName.equals( "" ) ) {
      this.packageName = pkgName;
    }
    //Debug.turnOn();
    init();
    if ( translate ) {
      translate();
    }
    System.out.println( "\nEventXmlToJava(xmlFileName=" + xmlFileName
                        + ", packageName=" + pkgName + ", translate="
                        + translate + "): finished\n" + timer + "\n" );
    
  }
  public EventXmlToJava( String xmlFileName, String pkgName )
      throws ParserConfigurationException, SAXException, IOException {
    this.xmlFileName = xmlFileName;
    if ( pkgName != null && !pkgName.equals( "" ) ) {
      this.packageName = pkgName;
    }
    init();
    translate();
  }
  
  public static String xmlFileToJsonFile(String xmlFileName, String jsonFileName) {
    String xml = null;
    try {
      xml = FileUtils.fileToString( xmlFileName );
    } catch ( FileNotFoundException e ) {
      e.printStackTrace();
      return null;
    }
    JSONObject json = XML.toJSONObject(xml);
    String jsonString = null;
    if ( json == null ) {
      return null;
    } else {
      jsonString = json.toString( 4 );
      FileUtils.stringToFile( jsonString, jsonFileName );
    }
    return jsonString;
  }
  
  public void init() throws ParserConfigurationException, SAXException, IOException {

    if ( Debug.isOn() ) Debug.outln( "random double to test repeatability = "
                 + Random.global.nextDouble() );
    
    System.out.println("xml file name = " + this.xmlFileName );
    System.out.println("package name = " + this.packageName );
    if ( Debug.isOn() ) Debug.outln("xml file name = " + this.xmlFileName );
    if ( Debug.isOn() ) Debug.outln("package name = " + this.packageName );

    expressionTranslator = new JavaToConstraintExpression( //this.expressionTranslator, 
                                                           packageName );
    
    // Translate XML to a DOM Document.
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware( true );
    DocumentBuilder builder;
    // XPathExpression expr = null;
    builder = factory.newDocumentBuilder();
    xmlDocDOM = builder.parse( xmlFileName );

    if ( !XmlUtils.validateXML( xmlFileName, xmlDocDOM ) ) {
      if ( Debug.isOn() ) Debug.outln( "Warning! XML file "
                          + xmlFileName
                          + " does not validate against its schema definition.  "
                          + "Continuing anyway." );
    }

    // output json
    System.out.println("XML TO JSON");
    String jsonFileName = xmlFileName.replaceAll( "[.][Xx][Mm][Ll]", ".json" );
    if ( xmlFileName.equals( jsonFileName ) ) {
      jsonFileName = jsonFileName + ".json";
    }
    xmlFileToJsonFile( xmlFileName, jsonFileName );
    
    scenarioNode = XmlUtils.findNode( xmlDocDOM, "scenario" );
    Assert.assertNotNull( scenarioNode );
    
    // get units
    String timeUnits = XmlUtils.getChildElementText( scenarioNode, "timeUnits" );
    if ( timeUnits == null || timeUnits.isEmpty() ) {
      if ( Debug.isOn() ) Debug.outln( "no units specified; using default" );
    } else {
      Timepoint.setUnits( timeUnits );
    }
    System.out.println( "units = " + Timepoint.getUnits() );
    
    // get epoch
    String epochString = XmlUtils.getChildElementText( scenarioNode, "epoch" );
    if ( epochString == null || epochString.isEmpty() ) {
      if ( Debug.isOn() ) Debug.outln( "no epoch specified; using default" );
    } else {
      Timepoint.setEpoch( epochString );
    }
    System.out.println( "epoch = " + Timepoint.getEpoch() );

    // get horizon duration
    String durationString = XmlUtils.getChildElementText( scenarioNode, "horizon" );
    if ( durationString == null || durationString.isEmpty() ) {
      if ( Debug.isOn() ) Debug.errln( "no duration specified; using default" );
    } else {
      int secs = Math.max( 0, 1 );  // stupid class loader
      secs = XmlUtils.getDurationInSeconds( durationString ) ;
      Timepoint.setHorizonDuration( (int)(secs / Timepoint.conversionFactor( TimeUtils.Units.seconds )) );
    }
    System.out.println( "horizon duration = " + Timepoint.getHorizonDuration()
                        + Timepoint.getUnits() );
    
    // build tables
    buildParamTable( xmlDocDOM, getClassData().getParamTable() );
    buildMethodTable( xmlDocDOM, getClassData().getMethodTable() );  
  }
  
  public void translate()
      throws ParserConfigurationException, SAXException, IOException {

    // pre-process classes
    processClassDeclarations( scenarioNode, null, "classes", false, true );
    // pre-process events
    processClassDeclarations( scenarioNode, null, "events", false, true );

    // Add constructors for invocations.
    addConstructors();
    
    // process classes first
    processClassDeclarations( scenarioNode, null, "classes", false, false );

    // process events
    processClassDeclarations( scenarioNode, null, "events", false, false );

    // process event to be executed
    NodeList nodeList = xmlDocDOM.getElementsByTagName( "eventToBeExecuted" );
    Assert.assertTrue( nodeList.getLength() < 2 );
    if ( nodeList.getLength() == 1 ) {
      Node node = nodeList.item( 0 );
      processExecutionEvent( node );
    }

  }

  public boolean isEvent( String className ) {
    if ( Utils.isNullOrEmpty( className ) ) return false;
    Boolean s = isEventMap.get( className );
    if ( s != null && s ) return true;
    String scopedName = getClassData().getClassNameWithScope( className );
    if ( scopedName == null ) return false;
    s = isEventMap.get( scopedName );
    return ( s != null && s );
  }
  
  public ClassData.Param makeParam( Node n ) {
    String name = fixName( XmlUtils.getChildElementText( n, "name" ) );
    String type = JavaToConstraintExpression.typeToClass( fixName( XmlUtils.getChildElementText( n, "type" ) ) );
    String value = expressionTranslator.fixValue( XmlUtils.getChildElementText( n, "value" ) );
    return new ClassData.Param( name, type, value );
  }

  
  // For each class definition, gather all locally defined and inherited
  // parameters and store them in a table indexed by the class name.
  protected void
      buildParamTable( Document doc,
                       Map< String, Map< String, ClassData.Param > > paramTable ) {
    
    // Start out by adding default parameters for DurativeEvent
    // Make an entry in the table for this class.
    Map< String, ClassData.Param > params = new TreeMap< String, ClassData.Param >();
    paramTable.put( "DurativeEvent", params );
    DurativeEvent de = new DurativeEvent();
    for ( gov.nasa.jpl.ae.event.Parameter< ? > p : de.getParameters() ) {
      if ( !params.containsKey( p.getName() ) ) {
        String pType =
            ( p.getValueNoPropagate() == null 
              ? "Integer" // TODO -- big assumption! Use p.getClass().
              : p.getValueNoPropagate().getClass().getSimpleName() );
        params.put( p.getName(),
                    new ClassData.Param( p.getName(), pType,
                               ( p.getValueNoPropagate() == null 
                                 ? null
                                 : p.getValueNoPropagate().toString() ) ) );
      }
    }

    
    List< Node > nList = XmlUtils.findNodes( doc, "class" );
    
    // Process classes except those with a super class that has not yet been
    // processed. Repeat looping through the unprocessed classes until they are
    // all processed or no more can be processed.
    int lastSize = nList.size() + 1;
    while ( !nList.isEmpty() && nList.size() < lastSize ) {
      lastSize = nList.size();
      ListIterator< Node > i = nList.listIterator();
      // Loop through unprocessed classes.
      while ( i.hasNext() ) {
        // for ( int i = 0; i < nList.size(); i++ ) {
        Node classNode = i.next(); // nList.get( i );
        String className = getClassName( classNode );
        boolean classIsStatic = isNodeOfDeclarationStatic( classNode );
        if ( Debug.isOn() ) Debug.outln( className + " is " + ( classIsStatic ? "" : "not" )
                     + " static" );
        getClassData().getIsStaticMap().put( className, classIsStatic );
        String superClass =
            fixName( XmlUtils.getChildElementText( classNode, "inheritsFrom" ) );
        Node parentNode = classNode.getParentNode();
        boolean isAnEvent = parentNode.getLocalName().equals( "event" );
        isEventMap.put( className, isAnEvent );
        Map< String, ClassData.Param > superParams = null;

        // If super class has not been processed, try again on the next pass.
        if ( superClass != null && !superClass.isEmpty() ) {
          superParams = paramTable.get( superClass );
          if ( superParams == null ) {
            continue;
          }
        }
        // Make an entry in the table for this class.
        //Map< String, ClassData.Param > 
        params = paramTable.get( className );
        if ( params == null ) {
          params = new TreeMap< String, ClassData.Param >();
          paramTable.put( className, params );
        }
        // Get the default DurativeEvent params if not available from superClass
        if ( isAnEvent && ( superParams == null || superParams.isEmpty() ) ) {
          assert false;
          DurativeEvent de1 = new DurativeEvent();
          for ( gov.nasa.jpl.ae.event.Parameter< ? > p : de1.getParameters() ) {
            if ( !params.containsKey( p.getName() ) ) {
              String pType =
                  ( p.getValueNoPropagate() == null 
                    ? "Integer" // TODO -- big assumption! Use p.getClass().
                    : p.getValueNoPropagate().getClass().getSimpleName() );
              params.put( p.getName(),
                          new ClassData.Param( p.getName(), pType,
                                     ( p.getValueNoPropagate() == null 
                                       ? null
                                       : p.getValueNoPropagate().toString() ) ) );
            }
          }
        } else {
          // Get inherited params.
          if ( superParams != null && params != null ) {
            params.putAll( superParams );
          }
        }
        // Now add params defined locally
        Node membersNode = XmlUtils.getChildNode( classNode, "members" );
        List< Node > pNodes = XmlUtils.getChildNodes( membersNode, "parameter" );
        // NodeList nodeList = node.XmlUtils.getChildNodes();
        for ( int j = 0; j < pNodes.size(); j++ ) {
          Node pNode = pNodes.get( j );
          ClassData.Param p = makeParam( pNode );
          if ( isNodeOfDeclarationStatic( pNode ) ) {
            getClassData().getIsStaticMap().put( className + "." + p.name, true );
          }
          ClassData.Param ep = params.get( p.name );
          if ( ep == null ) {
            params.put( p.name, p );
          } else {
            // TODO -- BAD!
          }
        }
        // Remove the processed class from the list of unprocessed classes.
        i.remove();
      }
    }
  }

  public boolean isNodeOfDeclarationStatic( Node classNode ) {
    return XmlUtils.isAttributeTrue( classNode, "static" );
  }

  // Add constructors for invocations.
  private void addConstructors() {
    Collection< ConstructorDeclaration > constructors =
        getConstructorDeclarations( this.xmlDocDOM );
    constructors.addAll( createConstructors( this.xmlDocDOM, constructors ) );
    for ( ConstructorDeclaration c : constructors ) {
      TypeDeclaration type = getTypeDeclaration( c.getName() );
      boolean alreadyAdded = false;
      if ( type == null ) {
        Debug.error( "No type found for constructor! " + c );
      } else if ( c != null ) {
        ConstructorDeclaration ctorToReplace = null;
        for ( BodyDeclaration bd : type.getMembers() ) {
          if ( bd instanceof ConstructorDeclaration ) {
            if ( equals(c, (ConstructorDeclaration)bd ) ) {
              if ( Utils.isNullOrEmpty( c.getParameters() ) ) {
                Debug.outln( "found constructor to replace:\n" + bd );
                ctorToReplace = (ConstructorDeclaration)bd;
              }
              alreadyAdded = true;
              break;
            }
            Debug.outln( "not replacing constructor:\n" + bd );
          }
        }
        if ( !alreadyAdded || ctorToReplace != null ) {
          if ( ctorToReplace != null ) {
            type.getMembers().remove( ctorToReplace );
            Debug.outln( "replacing constructor with:\n" + c );
          } else {
            Debug.outln( "adding constructor:\n" + c );
          }
          ASTHelper.addMember( type, c );
        }
      }
    }
  }

  // REVIEW -- TODO -- Wouldn't it be better to just have a Map<name, TypeDeclaration>?
  // Recursively look for a type declaration with the given name.
  protected TypeDeclaration getTypeDeclarationFrom( String name,
                                                    TypeDeclaration typeDecl ) {
    String simpleName = ClassUtils.simpleName( name );
    if ( typeDecl.getName().equals( simpleName ) ) {
      return typeDecl;
    }
    for ( BodyDeclaration bd : typeDecl.getMembers() ) {
      if ( bd instanceof TypeDeclaration ) {
        TypeDeclaration td = getTypeDeclarationFrom( simpleName, (TypeDeclaration)bd );
        if ( td != null ) return td;
      }
    }
    return null;
  }
  
  
  // Look in the compilation units in the map, classes, to find the class
  // declaration of name.
  protected TypeDeclaration getTypeDeclaration( String name ) {
    String simpleName = ClassUtils.simpleName( name );
    if ( name == null ) return null;
    CompilationUnit cu = getClassData().getClasses().get( simpleName );
    if ( cu != null ) {
      for ( TypeDeclaration type : cu.getTypes() ) {
        if ( type.getName().equals( simpleName ) ) {
          return type;
        }
      }
    } else {
      for ( CompilationUnit c : getClassData().getClasses().values() ) {
        for ( TypeDeclaration t : c.getTypes() ) {
          TypeDeclaration td = getTypeDeclarationFrom( simpleName, t );
          if ( td != null ) {
            return td;
          }
        }
      }
    }
    return null;
  }

  protected void setPackage() {
    // set the package based on the xmlFileName
    // String packageName =
    // "generated."
    // + xmlFileName.substring( 0, xmlFileName.lastIndexOf( '.' ) )
    // .replaceAll( "[^A-Za-z0-9_]+", "_" );
    if ( Debug.isOn() ) Debug.outln("setting package for current compilation unit to " + packageName );
    getClassData().getCurrentCompilationUnit().setPackage( new PackageDeclaration( ASTHelper.createNameExpr( packageName ) ) );
  }

  protected ClassOrInterfaceDeclaration processEvent( Node eventNode,
                                                      boolean innerClass,
                                                      boolean justClassDeclarations ) {
    Node classNode = XmlUtils.getChildNode( eventNode, "class" );
    ClassOrInterfaceDeclaration newClassDecl =
        processClassDeclaration( classNode, eventNode, innerClass,
                                 justClassDeclarations );
    return newClassDecl;
  }

  protected void processExecutionEvent( Node invocationNode ) {
    assert( invocationNode != null );

    getClassData().setCurrentClass( "Main" );
    initClassCompilationUnit( getClassData().getCurrentClass() );

    ClassOrInterfaceDeclaration newClassDecl =
        new ClassOrInterfaceDeclaration( ModifierSet.PUBLIC, false,
                                         getClassData().getCurrentClass() );
    ASTHelper.addTypeDeclaration( getClassData().getCurrentCompilationUnit(), newClassDecl );

    // Create public static main( String args[] ) { }
    // First, create main() { }
    int mods = ModifierSet.PUBLIC | ModifierSet.STATIC;
    
    MethodDeclaration mainMethodDecl =
        new MethodDeclaration( mods, new VoidType(), "main" );
    BlockStmt mainBody = new BlockStmt();
    mainMethodDecl.setBody( mainBody );

    ConstructorDeclaration ctor =
        new ConstructorDeclaration( ModifierSet.PUBLIC, newClassDecl.getName() );
    ASTHelper.addMember( newClassDecl, ctor );
    BlockStmt ctorBody = new BlockStmt();
    ctor.setBlock( ctorBody );
    
    // Need to set the epoch and units first thing.
    // REVIEW -- We need a scenario event that requires these arguments in the
    // constructor to ensure they are set up front.
    //String epochString = Timepoint.toTimestamp( Timepoint.getEpoch().getTime() );
    addStatements( mainBody,
                   "Timepoint.setUnits(\"" + Timepoint.getUnits() + "\");\n" );
    addStatements( mainBody,
                   "Timepoint.setEpoch(\"" + Timepoint.getEpoch() + "\");\n" );
    addStatements( mainBody,
                   "Timepoint.setHorizonDuration("
                   + Timepoint.getHorizonDuration() + ");\n" );

    // Create String args[].
    Type type = ASTHelper.createReferenceType( "String", 1 );
    VariableDeclaratorId id = new VariableDeclaratorId( "args" );
    japa.parser.ast.body.Parameter parameter =
        new japa.parser.ast.body.Parameter( type, id );
    // Wire everything together. 
    ASTHelper.addParameter( mainMethodDecl, parameter  );
    ASTHelper.addMember( newClassDecl, mainMethodDecl );
    
    // Now add statements to main()
    
    // Get the name/class of the event to execute
    String className = fixName( XmlUtils.getChildElementText( invocationNode, "eventType" ) );
    String instanceName = fixName( XmlUtils.getChildElementText( invocationNode, "eventName" ) );
    if ( instanceName == null || instanceName.isEmpty() ) {
      instanceName = className + (counter++);
    }

    // The Main class will extend the event to execute.
    addExtends( newClassDecl, className );
    
    // Use a StringBuffer to collect the statements. 
    StringBuffer stmtsMain = new StringBuffer();
    //StringBuffer stmtsCtor = new StringBuffer();

    // Get constructor arguments and create a statement constructing the instance.
    stmtsMain.append( "Main scenario = new Main();");
    //stmtsSB.append( className + " " + instanceName + " = new " + className + "(");
    //stmtsCtor.append( "super(");
    Node argumentsNode = XmlUtils.getChildNode( invocationNode, "arguments" );
    //List< ClassData.Param > arguments = new ArrayList< ClassData.Param >();
    List< Expression > args = new ArrayList< Expression >();
    if ( argumentsNode != null ) {
      List< Node > argNodeList = XmlUtils.getChildNodes( argumentsNode, "parameter" );
      boolean first = true;
      for ( int j = 0; j < argNodeList.size(); j++ ) {
        if ( first ) {
          first = false;
        } else {
          //stmtsCtor.append( ", " );
        }
        Node argNode = argNodeList.get( j );
        ClassData.Param p = makeParam( argNode );
        String exprStr = expressionTranslator.javaToAeExpr( p.value, p.type, true );
        japa.parser.ast.expr.Expression expr = new NameExpr( exprStr );
        args.add( expr );
        //stmtsCtor.append( exprStr );
      }
    }
    //stmtsCtor.append(");\n");
    ASTHelper.addStmt( ctorBody, new ExplicitConstructorInvocationStmt( false, null, args ) );
    
    // Need to import event.Expression etc. for constructor arguments
    addImport( "gov.nasa.jpl.ae.event.TimeVarying" );
    addImport( "gov.nasa.jpl.ae.event.TimeVaryingMap" );
    addImport( "gov.nasa.jpl.ae.event.TimeVaryingPlottableMap" );
    addImport( "gov.nasa.jpl.ae.event.TimeVaryingPlottableMaps" );
    addImport( "gov.nasa.jpl.ae.event.TimeVaryingProjection" );
    addImport( "gov.nasa.jpl.ae.event.Expression" );
    addImport( "gov.nasa.jpl.ae.event.Timepoint" );
//    addImport( "event.FunctionCall" );
//    addImport( "event.Functions" );
    
    // Create statements for executing & simulating the scenario event.
    //stmtsSB.append( instanceName + ".executeAndSimulate();\n" );
    stmtsMain.append( "double animationDuration = Timepoint.seconds(30.0);\n" );
    stmtsMain.append( "scenario.executeAndSimulate( Timepoint.getHorizonDuration() / animationDuration );\n" );
    
    // Put the statements in the constructor.
    //addStatements( ctorBody, stmtsCtor.toString() );
    
    // Put the statements in main().
    addStatements( mainBody, stmtsMain.toString() );
  }

  protected static void addExtends( ClassOrInterfaceDeclaration newClassDecl,
                                    String superClass ) {
    if ( newClassDecl.getExtends() == null ) {
      newClassDecl.setExtends( new ArrayList< ClassOrInterfaceType >() );
    }
    newClassDecl.getExtends().add( new ClassOrInterfaceType( superClass ) );
  }

//  protected BlockStmt addTryCatch( BlockStmt blockStmt,
//                                   String... exceptionNames ) {
//    
//    return null;
//  }

  private void addTryCatchToInitMembers( MethodDeclaration initMembers ) {
    TryStmt tryStmt = null;
    
    // Need to add a statement that will certainly need all of these exceptions;
    // otherwise, we'll get a compile error for trying to catch something that
    // can't be thrown.  Test code commented out below.
    String pkg = packageName + ".";
    if ( pkg.length() == 1 ) {
      pkg = "";
    }
    
    String tryCatchString =
        "try{\n" + ";\n" + "} catch ( Exception e ) {\n"
            + "  // TODO Auto-generated catch block\n"
            + "  e.printStackTrace();\n"
            + "}\n";

    List< Statement > stmts = new ArrayList< Statement >();
    if ( Debug.isOn() ) Debug.outln( "trying to parse \"" + stmts + "\"" );

    ASTParser parser = new ASTParser( new StringReader( tryCatchString ) );
    try {
      tryStmt = parser.TryStatement();
    } catch ( ParseException e ) {
      e.printStackTrace();
      return;
    }
    tryStmt.setTryBlock( initMembers.getBody() );
    stmts.add( tryStmt );
    BlockStmt newBody = new BlockStmt( stmts );
    initMembers.setBody( newBody );
  }

  protected Collection< ConstructorDeclaration > getConstructorDeclarations( Node top ) {
    Collection< ConstructorDeclaration > ctors =
        new ArrayList< ConstructorDeclaration >();
    List< Node > constructorsNodes = XmlUtils.findNodes( top, "constructors" );
    for ( Node constructorsNode : constructorsNodes ) {
      List< Node > mNodeList = XmlUtils.getChildNodes( constructorsNode, "function" );
      for ( Node mNode : mNodeList ) {
        String constructorString = expressionTranslator.fixValue( mNode.getTextContent() );
        ConstructorDeclaration constructorDecl = parseConstructorDeclaration( constructorString );
        if ( constructorDecl != null ) {
          if ( !ModifierSet.isPrivate( constructorDecl.getModifiers() )
               && !ModifierSet.isProtected( constructorDecl.getModifiers() ) ) {
            // TODO -- Let mods be specified in XML through attributes!
            constructorDecl.setModifiers( ModifierSet.addModifier( constructorDecl.getModifiers(),
                                                                   ModifierSet.PUBLIC ) );
          }
          addStatementsToConstructor( constructorDecl,
                                      Utils.getEmptyList(ClassData.Param.class) );
          Debug.outln( "found constructor:\n" + constructorDecl.getName() + constructorDecl.getParameters() );
          ctors.add( constructorDecl );
        }
      }
    }
    return ctors;
  }
  
  // Create constructors for event invocations.
  protected Collection< ConstructorDeclaration > 
   createConstructors( Node top, Collection< ConstructorDeclaration > ctors ) {
    //Collection< ConstructorDeclaration > ctors =
    //    new ArrayList< ConstructorDeclaration >();

    //Debug.turnOn();
    if ( Debug.isOn() ) Debug.outln( "existing constructors: " + ctors );
    //Debug.turnOff();
    List< Node > invocations = 
        XmlUtils.findNodes( top, "eventToBeExecuted" );
    invocations.addAll( XmlUtils.findNodes( top, "eventInvocation" ) );
    for ( Node invocationNode : invocations ) {
      //String name = XmlUtils.getChildElementText( invocationNode, "eventName" );
      if ( invocationNode != null ) {
        String eventType = fixName( XmlUtils.getChildElementText( invocationNode,
                                                                  "eventType" ) );
        ConstructorDeclaration ctor =
            new ConstructorDeclaration( ModifierSet.PUBLIC,
                                        ClassUtils.simpleName(eventType) );
        if ( Debug.isOn() ) Debug.outln("ctor ctord as " + ctor.getName() );
        Node argumentsNode = XmlUtils.getChildNode( invocationNode, "arguments" );
        List< ClassData.Param > arguments = new ArrayList< ClassData.Param >();
        if ( argumentsNode != null ) {
          List< Node > argNodeList = XmlUtils.getChildNodes( argumentsNode,
                                                             "parameter" );
          for ( int j = 0; j < argNodeList.size(); j++ ) {
            Node argNode = argNodeList.get( j );
            arguments.add( makeParam( argNode ) );
          }
          List< japa.parser.ast.body.Parameter > parameters =
              new ArrayList< japa.parser.ast.body.Parameter >();
          for ( ClassData.Param p : arguments ) {
            if ( p.type == null ) {
              ClassData.Param memberDecl = 
                  getClassData().lookupMemberByName( eventType,
                                                                     p.name,
                                                                     true, false );
              if ( !Debug.errorOnNull( "Error! Can't find member " + p.name
                                           + " for event class " + eventType
                                           + "!",
                                       memberDecl ) ) {
               p.type = memberDecl.type;
              } else {
                // delete 1 line below -- just for debug
                memberDecl = 
                    getClassData().lookupMemberByName( eventType,
                                                                       p.name,
                                                                       true, false );
              }
            }
            japa.parser.ast.body.Parameter param =
                ASTHelper.createParameter( new ClassOrInterfaceType( "Expression<"
                                                                     + ClassUtils.getNonPrimitiveClassName( p.type )
                                                                     + ">" ),
                                           p.name );
            parameters.add( param );
          }
          ctor.setParameters( parameters );
          addStatementsToConstructor( ctor, arguments );
        }

        // Check and see if we've already added this one.
        boolean alreadyCreated = false;
        //Debug.turnOn();
        for ( ConstructorDeclaration c : ctors ) {
          if ( equals( c, ctor ) ) {
            if ( Debug.isOn() ) Debug.outln( "constructor already created: " + ctor );
            alreadyCreated = true;
            break;
          }
        }
        //Debug.turnOff();
        // Don't add if already created. Default constructor is added
        // elsewhere, so filter that one out by checking if arguments is empty.
        if ( !alreadyCreated && !arguments.isEmpty() ) {
          if ( Debug.isOn() ) Debug.outln( "keeping constructor: " + ctor );
          ctors.add( ctor );
        }
      }
    }
    return ctors;
  }

  public static boolean equals( ConstructorDeclaration c1,
                                ConstructorDeclaration c2 ) {
    boolean localDebug = false;
    if ( localDebug ) {
      if ( Debug.isOn() ) Debug.outln( "equals(c1 = " + c1.getName() + ", c2 = " + c2.getName() );
      if ( Debug.isOn() ) Debug.outln( "equals() for c1 = \n" + c1 );
      if ( Debug.isOn() ) Debug.outln( "and c2 = \n" + c2 );
    }
    boolean equals = false;
    List< japa.parser.ast.body.Parameter > params1 = c1.getParameters();
    List< japa.parser.ast.body.Parameter > params2 = c2.getParameters();
    int paramsSize1 = (params1 == null ? 0 : params1.size() );
    int paramsSize2 = (params2 == null ? 0 : params2.size() );
    if ( c1.getName().equals( c2.getName() ) ) {
      if ( paramsSize1 == paramsSize2 ) {
        equals = true;
        for ( int i = 0; i < paramsSize1; ++i ) {
          japa.parser.ast.body.Parameter p1 = params1.get( i );
          japa.parser.ast.body.Parameter p2 = params2.get( i );
          if ( !p1.getType().toString().equals( p2.getType().toString()) ) {
            if ( localDebug ) {
              if ( Debug.isOn() ) Debug.outln( "constructors not equal; number " + i
                           + " param types do not match: "
                           + p1.getType().toString() + " != "
                           + p2.getType().toString() );
            }
            equals = false;
            break;
          }
        }
      } else {
        if ( localDebug ) {
          if ( Debug.isOn() ) Debug.outln( "constructors not equal; different numbers of params: "
                       + paramsSize1 + " != " + paramsSize2 );
        }
      }
    } else {
      if ( localDebug ) {
        if ( Debug.isOn() ) Debug.outln( "constructors not equal; different names: "
                     + c1.getName() + " != " + c2.getName() );
      }
    }
    return equals;
  }

  protected boolean hasStatementOfType( ConstructorDeclaration ctorDecl,
                                        Class<?> statementType ) {
    BlockStmt blockStmt = ctorDecl.getBlock();
    if ( blockStmt == null || blockStmt.getStmts() == null ) return false;
    for ( Statement stmt : blockStmt.getStmts() ) {
      if ( statementType.isAssignableFrom( stmt.getClass() ) ) return true;
    }
    return false;
  }
  
  protected void
      addStatementsToConstructor( ConstructorDeclaration ctor,
                                  List< ClassData.Param > arguments ) {
    StringBuffer stmtList = new StringBuffer();
    BlockStmt block = ctor.getBlock();
    if ( block == null ) {
      block = new BlockStmt();
      ctor.setBlock( block );
    }
    if ( block.getStmts() == null ) {
      block.setStmts( new ArrayList<Statement>() );
    }
    boolean looksAlreadyFixed = false;
    int pos = 0; // keep track of the insertion point before the existing statements.
    if ( !hasStatementOfType( ctor, ExplicitConstructorInvocationStmt.class ) ) {
      block.getStmts().add( pos++, new ExplicitConstructorInvocationStmt() );
    } else {
      looksAlreadyFixed = true;
    }
    // stmtList.append( "super();\n" );
    String blockString = block.toString();
    String initMembersString = "init" + ctor.getName() + "Members()";
    if ( !blockString.contains( initMembersString ) ) {
      stmtList.append( initMembersString + ";\n" );
    } else {
      looksAlreadyFixed = true;
    }
    String initCollectionsString = "init" + ctor.getName() + "Collections()";
    if ( !blockString.contains( initCollectionsString ) ) {    
      stmtList.append( initCollectionsString + ";\n" );
    } else {
      looksAlreadyFixed = true;
    }
    for ( ClassData.Param p : arguments ) {
      stmtList.append( "addDependency( this." + p.name + ", " + p.name
                       + " );\n" );
    }
    if ( isEvent( ctor.getName() ) ) {
      String initElaborationsString = "init" + ctor.getName() + "Elaborations()";
      if ( !blockString.contains( initElaborationsString ) ) {
        stmtList.append( initElaborationsString + ";\n" );
      } else {
        looksAlreadyFixed = true;
      }
      if ( !blockString.contains( "fixTimeDependencies()") ) {
        stmtList.append( "fixTimeDependencies();\n" );
      }
    }
    if ( !looksAlreadyFixed ) expressionTranslator.fixStatement( block );

    if ( Debug.isOn() ) Debug.outln( "adding statements to block: " + stmtList.toString() );
    addStatements( block, pos, stmtList.toString() );
    // ctor.setBlock( block );
    // ctor.setBlock( createBlock( stmtList.toString() ) );
  }

  protected MethodDeclaration createPublicVoidMethod( String methodName ) {
    MethodDeclaration initMembers =
        new MethodDeclaration( ModifierSet.PUBLIC, new VoidType(),
                               methodName );
    initMembers.setBody( new BlockStmt() );
    return initMembers;
  }

  private static void addStmts( BlockStmt block, List< Statement > list ) {
    addStmts( block, -1, list );
  }
  private static void addStmts( BlockStmt block, int pos, List< Statement > list ) {
    if ( list != null ) {
      if ( pos == -1 ) {
        if ( block == null || Utils.isNullOrEmpty( block.getStmts() ) ) {
          pos = 0;
        } else {
          pos = block.getStmts().size();
        }
      }
      for ( Statement stmt : list ) {
        addStmt( block, pos++, stmt );
      }
    }
  }

  /**
   * Adds the given statement to the specified block at the specified position.
   * The list of statements will be initialized if it is <code>null</code>. If
   * <code>pos</code> is -1, the statement is added to the end.
   * 
   * @param block
   * @param pos
   * @param stmt
   */
  public static void addStmt(BlockStmt block, int pos, Statement stmt) {
      List<Statement> stmts = block.getStmts();
      if (stmts == null) {
          stmts = new ArrayList<Statement>();
          block.setStmts(stmts);
      }
      if ( pos == -1 ) {
        stmts.add( stmt );
      } else {
        stmts.add( pos, stmt );
      }
  }


  public static void addStatements( BlockStmt block, String stmts ) {
    addStatements( block, -1, stmts );
  }
  public static void addStatements( BlockStmt block, int pos, String stmts ) {
    if ( Debug.isOn() ) Debug.outln( "trying to parse \"" + stmts + "\"" );
    List< Statement > list = stringToStatementList( stmts );
    addStmts( block, pos, list );
  }

  public static BlockStmt createBlock( String stmts ) {
    BlockStmt block = new BlockStmt();
    addStatements( block, stmts );
    return block;
  }

  private MethodDeclaration
      createInitCollectionsMethod( String methodName,
                                   Collection< FieldDeclaration > parameters,
                                   // Collection< FieldDeclaration > methods,
                                   Collection< FieldDeclaration > constraints,
                                   //Collection< FieldDeclaration > dependencies,
                                   Collection< Pair< String, FieldDeclaration >> effects ) {
    // Collection< FieldDeclaration > elaborations ) {
    MethodDeclaration initCollections =
        new MethodDeclaration( ModifierSet.PROTECTED, new VoidType(),
                               methodName );
    // TODO -- Add initCollections()'s body.
    BlockStmt block = new BlockStmt();
    List< Statement > stmtList = null;
    stmtList =
        createStmtsFromFieldCollection( "parameters.add( ", parameters, " );\n" );
    addStmts( block, stmtList );
    stmtList =
        createStmtsFromFieldCollection( "constraintExpressions.add( ",
                                        constraints, " );\n" );
    addStmts( block, stmtList );
//    stmtList = removeConflictingDependencies();
//    addStmts( block, stmtList );
//    stmtList =
//        createStmtsFromFieldCollection( "dependencies.add( ", dependencies,
//                                        " );\n" );
//    addStmts( block, stmtList );
    // TODO -- correct for effects?
    if ( effects != null ) {
      stmtList =  
          createEffectStmtsFromFieldCollection( "addEffects( (Parameter< ? >)", effects, " );\n" );
    }
    addStmts( block, stmtList );

    initCollections.setBody( block );
    return initCollections;
  }

  public static List< Statement > stringToStatementList( String s ) {
    if ( Debug.isOn() ) Debug.outln( "trying to parse Java statements \"" + s + "\"" );
    ASTParser parser = new ASTParser( new StringReader( s ) );
    List< Statement > stmtList = null;
    try {
      stmtList = parser.Statements();
    } catch ( ParseException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return stmtList;
  }
  
//  protected List< Statement > removeConflictingDependencies() {
//    StringBuilder sb = new StringBuilder();
//    if ( gotStartTimeDependency ) {
//      sb.append( "removeDependenciesForParameter( startTime );\n" );
//    }
//    if ( gotEndTimeDependency ) {
//      sb.append( "removeDependenciesForParameter( endTime );\n" );
//    }
//    if ( gotDurationDependency ) {
//      sb.append( "removeDependenciesForParameter( duration );\n" );
//    }
//    return stringToStatementList( sb.toString() );
//  }

  private
      List< Statement >
      createStmtsFromFieldCollection( String prefix,
                                      Collection< FieldDeclaration > fieldCollection,
                                      String suffix ) {
    if ( fieldCollection == null || fieldCollection.isEmpty() ) return null;
    // private Statement createInitCollectionStmt( String collectionName,
    // Collection< FieldDeclaration > fieldCollection ) {
    StringBuilder sb = new StringBuilder();
    for ( FieldDeclaration f : fieldCollection ) {
      // sb.append("    " + collectionName + ".add( " + f.getVariables().get( 0
      // ) + " );\n" );
      sb.append( prefix + f.getVariables().get( 0 ).getId() + suffix );
    }
    return stringToStatementList( sb.toString() );
  }

  private List< Statement >
      createEffectStmtsFromFieldCollection( String prefix,
                                            Collection< Pair< String, FieldDeclaration > > fieldCollection,
                                            String suffix ) {
    Map< String, Set< FieldDeclaration > > map = new TreeMap< String, Set< FieldDeclaration > >();
    for ( Pair< String, FieldDeclaration > p : fieldCollection ) {
      if ( !p.second.getType().toString().equals( "Effect" ) ) continue;
      Set< FieldDeclaration > set = map.get( p.first );
      if ( set == null ) {
        set = new TreeSet< FieldDeclaration >(new CompareUtils.GenericComparator< FieldDeclaration >());
        map.put( p.first, set );
      }
      set.add( p.second );
    }
    boolean madeSet = false;
    if ( fieldCollection == null || fieldCollection.isEmpty() ) return null;
    StringBuilder sb = new StringBuilder();
    for ( Map.Entry< String, Set< FieldDeclaration > > e : map.entrySet() ) {
      String effectSetName = "effectsFor" + e.getKey();
      sb.append( "Set<Effect> " + effectSetName + " = new TreeSet<Effect>();\n" );
      madeSet = true;
      for ( FieldDeclaration f : e.getValue() ) {
        sb.append( effectSetName + ".add( " + f.getVariables().get( 0 ).getId() + " );\n" );
      }
      sb.append( prefix + e.getKey() + ", " + effectSetName + suffix );
    }
    if ( madeSet ) {
      addImport("java.util.Set");
      addImport("java.util.TreeSet");
      addImport("gov.nasa.jpl.ae.event.EffectFunction");
    }
    return stringToStatementList( sb.toString() );
  }

//  public ClassOrInterfaceDeclaration processClass( Node cls ) {
//    ClassOrInterfaceDeclaration newClassDecl =
//        processClassDeclaration( cls, false, true );
//    return newClassDecl;
//  }

  public static void addModifier( TypeDeclaration typeDecl, int modifier ) {
    typeDecl.setModifiers( ModifierSet.addModifier( typeDecl.getModifiers(),
                                                    modifier) );
  }
  public static void addModifier( MethodDeclaration methodDecl, int modifier ) {
    methodDecl.setModifiers( ModifierSet.addModifier( methodDecl.getModifiers(),
                                                      modifier) );
  }
  public static void addModifier( FieldDeclaration fieldDecl, int modifier ) {
    fieldDecl.setModifiers( ModifierSet.addModifier( fieldDecl.getModifiers(),
                                                     modifier) );
  }
  public static void removeModifier( TypeDeclaration typeDecl, int modifier ) {
    typeDecl.setModifiers( ModifierSet.removeModifier( typeDecl.getModifiers(),
                                                       modifier) );
  }
  public static void removeModifier( MethodDeclaration methodDecl, int modifier ) {
    methodDecl.setModifiers( ModifierSet.removeModifier( methodDecl.getModifiers(),
                                                         modifier) );
  }
  public static void removeModifier( FieldDeclaration fieldDecl, int modifier ) {
    fieldDecl.setModifiers( ModifierSet.removeModifier( fieldDecl.getModifiers(),
                                                         modifier) );
  }
  public static void makeStatic( FieldDeclaration fieldDecl ) {
    addModifier( fieldDecl, ModifierSet.STATIC );
  }
  public static void makeStatic( TypeDeclaration typeDecl ) {
    addModifier( typeDecl, ModifierSet.STATIC );
  }
  public static void makeStatic( MethodDeclaration methodDecl ) {
    methodDecl.setModifiers( ModifierSet.addModifier( methodDecl.getModifiers(),
                                                      ModifierSet.STATIC ) );
  }
  public static void makePublic( TypeDeclaration typeDecl ) {
    removeModifier( typeDecl, ModifierSet.PRIVATE );
    removeModifier( typeDecl, ModifierSet.PROTECTED );
    addModifier( typeDecl, ModifierSet.PUBLIC );
  }
  public static void makePublic( MethodDeclaration methodDecl ) {
    removeModifier( methodDecl, ModifierSet.PRIVATE );
    removeModifier( methodDecl, ModifierSet.PROTECTED );
    addModifier( methodDecl, ModifierSet.PUBLIC );
  }
  public static void makePublic( FieldDeclaration fieldDecl ) {
    removeModifier( fieldDecl, ModifierSet.PRIVATE );
    removeModifier( fieldDecl, ModifierSet.PROTECTED );
    addModifier( fieldDecl, ModifierSet.PUBLIC );
  }
  
  public ClassOrInterfaceDeclaration processClassDeclaration( Node clsNode,
                                                              Node eventNode,
                                                              boolean isNested,
                                                              boolean justClassDeclarations ) {
    // Get class name.
    getClassData().setCurrentClass( getClassName( clsNode ) );
    
    if ( justClassDeclarations ) if ( Debug.isOn() ) Debug.out( "pre-" );
    if ( Debug.isOn() ) Debug.outln( "processing class " + getClassData().getCurrentClass() );
    if ( !isNested ) { 
      if ( justClassDeclarations ) {
        getClassData().setCurrentCompilationUnit( initClassCompilationUnit( getClassData().getCurrentClass() ) );
      } else {
        getClassData().setCurrentCompilationUnit( getClassData().getClasses().get( getClassData().getCurrentClass() ) );
      }
      assert getClassData().getCurrentCompilationUnit() != null;
    }
    ClassOrInterfaceDeclaration newClassDecl = null;
    if ( justClassDeclarations ) {
      newClassDecl =
          new ClassOrInterfaceDeclaration( ModifierSet.PUBLIC, false, 
                                           ClassUtils.simpleName( getClassData().getCurrentClass() ) );
      if ( getClassData().isClassStatic( getClassData().getCurrentClass() ) ) {
        makeStatic( newClassDecl );
      }
    } else {
      newClassDecl = getClassData().getClassDeclaration( ClassUtils.simpleName( getClassData().getCurrentClass() ) );
    }
    
    if ( justClassDeclarations ) {
      getSuperClasses( clsNode, newClassDecl, eventNode != null );

      getImports( clsNode );
    
      if ( !isNested ) {
        ASTHelper.addTypeDeclaration( getClassData().getCurrentCompilationUnit(), newClassDecl );
      }
      if ( newClassDecl != null && newClassDecl.getName().startsWith( "Power_System" )) {
        Debug.out( "" );
      }
      createDefaultConstructor( newClassDecl );
    } else {
      gotStartTimeDependency = false;
      gotEndTimeDependency = false;
      gotDurationDependency = false;
      createMembers( newClassDecl, clsNode, eventNode );
    }
    
    // inner classes
    processClassDeclarations( clsNode, newClassDecl, "events", true,
                              justClassDeclarations );
    processClassDeclarations( clsNode, newClassDecl, "classes", true,
                              justClassDeclarations );
    
    return newClassDecl;
  }

  protected void createMembers( TypeDeclaration newClassDecl, Node clsNode,
                                Node eventNode ) {
    // Get methods/functions.  
    // TODO -- If asEvent, then don't we need to convert argument types to Expressions?
    //  [Maybe no: can make into a Function expression and only pass evaluated values.]
    // TODO -- Can some args be Expressions and not others?
    // TODO -- Maybe we need to check and see whether the function is invoked with Expressions from events?  This might even determine whether class members should be Parameters.
    Collection< MethodDeclaration > methods = getMethodsForClass( getClassData().getCurrentClass() );
    for ( MethodDeclaration methodDecl : methods ) {
//      if ( asEvent ) {
//        // TODO
//      } else {
        ASTHelper.addMember( newClassDecl, methodDecl );
//      }
    }

    // Need a method for initializing members and populating Event collections
    // (parameters, effects, etc.).
    MethodDeclaration initMembers =
        createPublicVoidMethod( "init" + newClassDecl.getName() + "Members" );
    MethodDeclaration initElaborations =
        createPublicVoidMethod( "init" + newClassDecl.getName() + "Elaborations" );
    MethodDeclaration initDependencies =
        createPublicVoidMethod( "init" + newClassDecl.getName() + "Dependencies" );
    addStatements( initElaborations.getBody(), "init" + newClassDecl.getName()
                                               + "Dependencies();" );

    // Get fields (parameters, constraints, & dependencies).
    // ArrayList< Parameter< ? > > members =
    List< FieldDeclaration > members = new ArrayList< FieldDeclaration >();
    Collection< FieldDeclaration > parameters =
        getParameters( XmlUtils.getChildNode( clsNode, "members" ),
                       initMembers );
    Collection< FieldDeclaration > constraints =
        getConstraints( XmlUtils.getChildNode( clsNode, "constraints" ),
                        initMembers );
    Collection< FieldDeclaration > dependencies =
        getDependencies( XmlUtils.getChildNode( clsNode, "dependencies" ),
                         initDependencies );
    Collection< Pair< String, FieldDeclaration > > effects = null;
    if ( eventNode != null ) {
      effects = getEffects( XmlUtils.getChildNode( eventNode, "effects" ),
                            initMembers );
    }
    Collection< FieldDeclaration > elaborations = null;
    if ( eventNode != null ) {
      elaborations =
          getElaborations( XmlUtils.getChildNode( eventNode, "elaborations" ),
                           initElaborations );
    }
    
    members.addAll( parameters );
    members.addAll( constraints );
    members.addAll( dependencies );
    if ( eventNode != null ) {
      for ( Pair< String, FieldDeclaration > p : effects ) {
        members.add( p.second );
      }
      members.addAll( elaborations );
    }

    addTryCatchToInitMembers(initMembers);

    // Need a method for populating Event collections (parameters, effects,
    // etc.).
    MethodDeclaration initCollections =
        createInitCollectionsMethod( "init" + newClassDecl.getName()
                                     + "Collections", parameters, // methods,
                                     constraints, //dependencies, 
                                     effects );
    // elaborations );

    // Add fields and methods to class declaration.
    for ( FieldDeclaration f : members ) {
      ASTHelper.addMember( newClassDecl, f );
    }
    ASTHelper.addMember( newClassDecl, initMembers );
    ASTHelper.addMember( newClassDecl, initCollections );
    ASTHelper.addMember( newClassDecl, initDependencies );
    ASTHelper.addMember( newClassDecl, initElaborations );

  }

  protected void createDefaultConstructor( TypeDeclaration newClassDecl ) {
    // create a default constructor that takes no arguments
    ConstructorDeclaration ctor =
        new ConstructorDeclaration( ModifierSet.PUBLIC, newClassDecl.getName() );
    ASTHelper.addMember( newClassDecl, ctor );
    BlockStmt block = new BlockStmt();
    ASTHelper.addStmt( block, new ExplicitConstructorInvocationStmt() );
    ctor.setBlock( block );
    ASTHelper.addStmt( block,
                       new MethodCallExpr( null, "init"
                                                 + newClassDecl.getName()
                                                 + "Members" ) );
    ASTHelper.addStmt( block,
                       new MethodCallExpr( null, "init"
                                                 + newClassDecl.getName()
                                                 + "Collections" ) );

    ASTHelper.addStmt( block,
                       new MethodCallExpr( null, "init"
                                                 + newClassDecl.getName()
                                                 + "Elaborations" ) );

  }
  
  protected void getSuperClasses( Node clsNode,
                                  ClassOrInterfaceDeclaration newClassDecl,
                                  boolean isEvent ) {
    List< ClassOrInterfaceType > extendsList = getInheritsFrom( clsNode );
    if ( !Utils.isNullOrEmpty( extendsList ) ) {
      newClassDecl.setExtends( extendsList );
    }
    if ( Utils.isNullOrEmpty( newClassDecl.getExtends() ) ) {
      addExtends( newClassDecl, isEvent ? "DurativeEvent"
                                       : "ParameterListenerImpl" );
    }
    List< ClassOrInterfaceType > implementsList = getImplementsFrom( clsNode );
    if ( !Utils.isNullOrEmpty( implementsList ) ) {
      newClassDecl.setImplements( implementsList );
    }
  }

  protected void getImports( Node clsNode ) {
    List< String > imports = XmlUtils.getChildrenElementText( clsNode,
                                                              "import" );
    for ( String imp : imports ) {
      addImport( imp );
    }
  }

  private void
      processClassDeclarations( Node parentNode,
                                ClassOrInterfaceDeclaration newClassDecl,
                                String classesOrEvents, boolean isNested,
                                boolean justClassDeclarations ) {
    ClassOrInterfaceDeclaration classDecl = null;
    if ( parentNode != null ) {
      String parentClassName = getClassName( parentNode );
      boolean isEvent = classesOrEvents.equals( "events" );
      String classOrEvent = ( isEvent ? "event" : "class" );
      //String classOrEvent = classesOrEvents.replaceFirst( "[e]?s$", "" );
      List< Node > nodeList = XmlUtils.getChildNodes( parentNode,
                                                      classesOrEvents );
      // NodeList nodeList = node.XmlUtils.getChildNodes();
      for ( Node childNode : nodeList ) {
        if ( parentNode != null ) {
          List< Node > classNodes = 
              XmlUtils.getChildNodes( childNode, classOrEvent );
          for ( Node classNode : classNodes ) {
            String childClassName = getClassName( classNode );
            if ( isNested ) {
              if ( justClassDeclarations ) {
                getClassData().getNestedToEnclosingClassNames().put( childClassName, parentClassName );
                if ( Debug.isOn() ) Debug.outln( "nestedToEnclosingClassNames.put( "
                             + childClassName + ", " + parentClassName + " )" );
              }
            } else {
              if ( Debug.isOn() ) Debug.outln( childClassName + " is not a nested class of "
                           + parentClassName );
            }
            if ( isEvent ) {
              classDecl = processEvent( classNode, isNested, justClassDeclarations );
            } else {
              classDecl = processClassDeclaration( classNode, null, isNested, justClassDeclarations );
            }
            if ( justClassDeclarations && isNested ) {
              ASTHelper.addMember( newClassDecl, classDecl );
            }
          }
        }
      }
    }
  }

  private CompilationUnit initCompilationUnit( String name ) {
    getClassData().setCurrentCompilationUnit( new CompilationUnit() );
    getClassData().getClasses().put( ClassUtils.simpleName(name), getClassData().getCurrentCompilationUnit() );
    setPackage();
    return getClassData().getCurrentCompilationUnit();
  }
  
  private CompilationUnit initClassCompilationUnit( String name ) {
    getClassData().setCurrentCompilationUnit( initCompilationUnit( ClassUtils.simpleName(name) ) );
    // REVIEW -- How can we access eclipse's ability to auto-remove unused
    // imports?
    //addImport( "gov.nasa.jpl.ae.event.*" );
    addImport( "gov.nasa.jpl.ae.event.Parameter" );
    addImport( "gov.nasa.jpl.ae.event.IntegerParameter" );
    addImport( "gov.nasa.jpl.ae.event.DoubleParameter" );
    addImport( "gov.nasa.jpl.ae.event.StringParameter" );
    addImport( "gov.nasa.jpl.ae.event.BooleanParameter" );
    addImport( "gov.nasa.jpl.ae.event.StateVariable" );
    addImport( "gov.nasa.jpl.ae.event.Timepoint" );
    addImport( "gov.nasa.jpl.ae.event.Expression" );
    addImport( "gov.nasa.jpl.ae.event.ConstraintExpression" );
    addImport( "gov.nasa.jpl.ae.event.Functions" );
    addImport( "gov.nasa.jpl.ae.event.FunctionCall" );
    addImport( "gov.nasa.jpl.ae.event.ConstructorCall" );
    addImport( "gov.nasa.jpl.ae.event.Call" );
    addImport( "gov.nasa.jpl.ae.event.Effect" );
    addImport( "gov.nasa.jpl.ae.event.EffectFunction" );
    addImport( "gov.nasa.jpl.ae.event.TimeDependentConstraintExpression" );
    addImport( "gov.nasa.jpl.ae.event.Dependency" );
    addImport( "gov.nasa.jpl.ae.event.ElaborationRule" );
    addImport( "gov.nasa.jpl.ae.event.EventInvocation" );
    addImport( "gov.nasa.jpl.ae.event.ParameterListenerImpl" );
    addImport( "gov.nasa.jpl.ae.event.Event" );
    addImport( "gov.nasa.jpl.ae.event.DurativeEvent" );
    addImport( "gov.nasa.jpl.ae.event.TimeVarying" );
    addImport( "gov.nasa.jpl.ae.event.TimeVaryingMap" );
    addImport( "gov.nasa.jpl.ae.event.TimeVaryingPlottableMap" );
    addImport( "gov.nasa.jpl.ae.event.TimeVaryingPlottableMaps" );
    addImport( "gov.nasa.jpl.ae.event.TimeVaryingProjection" );
    addImport( "gov.nasa.jpl.mbee.util.Utils" );
    addImport( "gov.nasa.jpl.mbee.util.Debug" );
    addImport( "gov.nasa.jpl.mbee.util.ClassUtils" );
    addImport( "java.util.Vector" );
    addImport( "java.util.Map" );
    return getClassData().getCurrentCompilationUnit();
  }

  private void addImport( String impName ) {
    NameExpr ne = new NameExpr( impName );
    ImportDeclaration d = new ImportDeclaration( ne, false, false );
    if ( getClassData().getCurrentCompilationUnit().getImports() == null ) {
      getClassData().getCurrentCompilationUnit().setImports( new ArrayList< ImportDeclaration >() );
    }
    // check for duplicates -- REVIEW - inefficient linear search
    // TODO -- never finds duplicates!
    for ( ImportDeclaration i : getClassData().getCurrentCompilationUnit().getImports() ) {
      if ( i.getName().getName().equals( impName ) ) return;
    }
    getClassData().getCurrentCompilationUnit().getImports().add( d );
  }

  public List< ClassOrInterfaceType > getInheritsFrom( Node cls ) {
    List< String > extendsStringList =
        XmlUtils.getChildrenElementText( cls, "extends" );
    extendsStringList.addAll( 
        XmlUtils.getChildrenElementText( cls, "inheritsFrom" ) );
    List< ClassOrInterfaceType > extendsList =
        new ArrayList< ClassOrInterfaceType >();
    for ( String e : extendsStringList ) {
      ClassOrInterfaceType c = new ClassOrInterfaceType( fixName( e ) );
      extendsList.add( c );
    }
    return extendsList;
  }
  
  public List< ClassOrInterfaceType > getImplementsFrom( Node cls ) {
    List< String > implementsStringList =
        XmlUtils.getChildrenElementText( cls, "implements" );
    List< ClassOrInterfaceType > implementsList =
        new ArrayList< ClassOrInterfaceType >();
    for ( String e : implementsStringList ) {
      ClassOrInterfaceType c = new ClassOrInterfaceType( fixName( e ) );
      implementsList.add( c );
    }
    return implementsList;
  }
  
  // TODO -- Do we need to add the "owner" event as currently required for the
  // Parameter constructors?
  public static
      FieldDeclaration
      createFieldOfGenericType( String name, String typeName,
                                String parameterTypeName, String constructorArgs ) {
    String fieldTypeName = typeName;
    if ( !Utils.isNullOrEmpty( parameterTypeName ) ) {
      fieldTypeName += "< " + ClassUtils.getNonPrimitiveClassName( parameterTypeName ) + " >";
    }
    ClassOrInterfaceType fieldType = new ClassOrInterfaceType( fieldTypeName );
    FieldDeclaration f = null;
    VariableDeclaratorId id = new VariableDeclaratorId( name );
    Expression init = null;
    String initValue = null;
    if ( constructorArgs == null ) {
      initValue = "null";
    } else {
      initValue = "new " + typeName;
      if ( !Utils.isNullOrEmpty( parameterTypeName ) ) {
        initValue += "< " + ClassUtils.getNonPrimitiveClassName( parameterTypeName ) + " >";
      }
      initValue += "( " + constructorArgs + " )";
    }
    init = new NameExpr( initValue );
    VariableDeclarator variable = new VariableDeclarator( id, init );
    f =
        ASTHelper.createFieldDeclaration( ModifierSet.PUBLIC, fieldType,
                                          variable );
    return f;
  }

  public static Statement
      createAssignmentOfGenericType( String name, String typeName,
                                     String parameterTypeName,
                                     String constructorArgs ) {
    StringBuffer stmtsString = new StringBuffer();
    stmtsString.append( "if ( " + name + " == null ) " );
    stmtsString.append( name + " = " );
    if ( constructorArgs == null ) {
      stmtsString.append( "null;" );
    } else {
      stmtsString.append( "new " + typeName );
      if ( !Utils.isNullOrEmpty( parameterTypeName ) ) {
        stmtsString.append( "< " + ClassUtils.getNonPrimitiveClassName( parameterTypeName ) + " >" );
      }
      stmtsString.append( "( " + constructorArgs + " );" );
    }

    if ( Debug.isOn() ) Debug.outln( "Trying to parse assignment with ASTParser.BlockStatement(): \""
                        + stmtsString.toString() + "\"" );
    ASTParser parser =
        new ASTParser( new StringReader( stmtsString.toString() ) );
    Statement stmt = null;
    try {
      stmt = parser.BlockStatement();
    } catch ( ParseException e ) {
      e.printStackTrace();
    }

    return stmt;
  }

  public static FieldDeclaration
      createFieldOfGenericType( String name, String typeName,
                                String constructorArgs ) {
    return createFieldOfGenericType( name, typeName, null, constructorArgs );
  }

  public FieldDeclaration createParameterField( ClassData.Param p ) {
    String args[] = expressionTranslator.convertToEventParameterTypeAndConstructorArgs( p );
    // return createFieldOfGenericType( p.name, type, p.type, args );
    return createFieldOfGenericType( p.name, args[ 0 ],
                                     null,//args[ 1 ],
                                     args[ 2 ] );
  }

  public FieldDeclaration createParameterField( ClassData.Param p,
                                                MethodDeclaration initMembers ) {
    if ( initMembers == null ) {
      return createParameterField( p );
    }
    String args[] = expressionTranslator.convertToEventParameterTypeAndConstructorArgs( p );
    Statement s =
        createAssignmentOfGenericType( p.name, args[ 0 ],
                                       args[ 1 ],
                                       args[ 2 ] );
    ASTHelper.addStmt( initMembers.getBody(), s );
    FieldDeclaration f =
        createFieldOfGenericType( p.name, args[ 0 ],
                                  args[ 1 ],
                                  null );
    if ( getClassData().isMemberStatic( p.name ) ) {
      makeStatic( f );
    }
    return f;
  }

  public void addParameterField( TypeDeclaration typeDecl, ClassData.Param p ) {
    FieldDeclaration f = createParameterField( p );
    ASTHelper.addMember( typeDecl, f );
  }

//  /**
//   * @deprecated Use {@link JavaToConstraintExpression#operatorResultType(BinaryExpr.Operator,String,String)} instead
//   */
//  private static String operatorResultType( BinaryExpr.Operator o, String argType1,
//                                     String argType2 ) {
//                                      return JavaToConstraintExpression.operatorResultType( o,
//                                                                                            argType1,
//                                                                                            argType2 );
//                                    }
//
//  /**
//   * @deprecated Use {@link JavaToConstraintExpression#dominantType(String,String)} instead
//   */
//  private static String dominantType( String argType1, String argType2 ) {
//    return JavaToConstraintExpression.dominantType( argType1, argType2 );
//  }
//
//  /**
//   * @deprecated Use {@link JavaToConstraintExpression#operatorResultType(UnaryExpr.Operator,String)} instead
//   */
//  private static String
//      operatorResultType( UnaryExpr.Operator operator, String argType ) {
//        return JavaToConstraintExpression.operatorResultType( operator, argType );
//      }
//  /**
//   * @deprecated Use {@link JavaToConstraintExpression#assignOpToBinaryOp(AssignExpr.Operator)} instead
//   */
//  public static BinaryExpr.Operator assignOpToBinaryOp( AssignExpr.Operator ao ) {
//    return JavaToConstraintExpression.assignOpToBinaryOp( ao );
//  }
//
//  /**
//   * @deprecated Use {@link JavaToConstraintExpression#astUnaryOpToEventFunctionName(UnaryExpr.Operator)} instead
//   */
//  private static String
//      astUnaryOpToEventFunctionName( UnaryExpr.Operator operator ) {
//        return JavaToConstraintExpression.astUnaryOpToEventFunctionName( operator );
//      }
//
//  // \([A-Za-z]*\), //\(.*\)
//  // case \1: // \2
//  /**
//   * @deprecated Use {@link JavaToConstraintExpression#javaBinaryOpToEventFunctionName(BinaryExpr.Operator)} instead
//   */
//  private static String
//      javaBinaryOpToEventFunctionName( BinaryExpr.Operator operator ) {
//        return JavaToConstraintExpression.javaBinaryOpToEventFunctionName( operator );
//      }
  
  /**
   * Translate the input type/class name to the corresponding non-primitive
   * Class name. The case of the letters is largely ignored, and some
   * non-standard type names are considered: for example, typeToClass("real")
   * returns "Float" even though "real" is not a Java type.
   * 
   * @param type
   * @return the name of the non-primitive Class that corresponds to the input
   *         type name.
   * @deprecated Use {@link JavaToConstraintExpression#typeToClass(String)} instead
   * 
   */
  public static String typeToClass( String type ) {
    return JavaToConstraintExpression.typeToClass( type );
  }

  /**
   * @deprecated Use {@link ClassData#typeToParameterType(String)} instead
   */
  public static String typeToParameterType( String type ) {
    return ClassData.typeToParameterType( type );
  }

  /**
   * @deprecated Use {@link JavaToConstraintExpression#classToPrimitive(String)} instead
   */
  public static String classToPrimitive( String type ) {
    return JavaToConstraintExpression.classToPrimitive( type );
  }

  public FieldDeclaration createConstraintField( String name,
                                                 String expression,
                                                 String applicableStartTime,
                                                 String applicableEndTime ) {
    // ClassOrInterfaceType fieldType =
    // new ClassOrInterfaceType( "ConstraintExpression" );
    // FieldDeclaration f = null;
    if ( name == null ) {
      name = new String( "constraint" + counter++ );
    }
    String constructorArgs = null;
    String constraintType = null;
    if ( applicableStartTime == null || applicableEndTime == null
         || applicableStartTime.isEmpty() || applicableEndTime.isEmpty() ) {
      // constructorArgs = "new Expression<Boolean>( \"" + expression +
      // "\", \"Java\" )";
      constructorArgs = expressionTranslator.javaToAeExpr( expression, "Boolean", false );
      constraintType = "ConstraintExpression";
    } else {
      constructorArgs = expressionTranslator.javaToAeExpr( expression, "Boolean", false )
      // "new Expression<Boolean>( \""
      // + expression + "\", \"Java\" ), "
                        + applicableStartTime + ", " + applicableEndTime;
      constraintType = "TimeDependentConstraintExpression";
    }

    return createFieldOfGenericType( name, constraintType, constructorArgs );
    /*
     * 
     * 
     * VariableDeclaratorId id = new VariableDeclaratorId( name );
     * 
     * Expression init = null; if ( applicableStartTime == null ||
     * applicableStartTime == null ) { init = new NameExpr(
     * "new ConstraintExpression( new Expression<Boolean>( \"" + expression +
     * "\", \"Java\" ) )" ); } else { init = new NameExpr(
     * "new TimeDependentConstraintExpression( new Expression<Boolean>( \"" +
     * expression + "\", \"Java\" ), " + applicableStartTime + ", " +
     * applicableEndTime + " )" ); } VariableDeclarator variable = new
     * VariableDeclarator( id, init ); f = ASTHelper.createFieldDeclaration(
     * ModifierSet.PUBLIC, fieldType, variable ); return f;
     */
  }

  public FieldDeclaration createConstraintField( String name,
                                                 String expression,
                                                 String applicableStartTime,
                                                 String applicableEndTime,
                                                 MethodDeclaration initMembers ) {
    if ( initMembers == null ) {
      return createConstraintField( name, expression, applicableStartTime,
                                    applicableEndTime );
    }
    if ( name == null ) {
      name = new String( "constraint" + counter++ );
    }
    String constructorArgs = null;
    String constraintType = null;
    if ( applicableStartTime == null || applicableEndTime == null
         || applicableStartTime.isEmpty() || applicableEndTime.isEmpty() ) {
      constructorArgs = expressionTranslator.javaToAeExpr( expression, "Boolean", true );
      // constructorArgs = "new Expression<Boolean>( \"" + expression +
      // "\", \"Java\" )";
      constraintType = "ConstraintExpression";
    } else {
      constructorArgs = expressionTranslator.javaToAeExpr( expression, "Boolean", true )
      // constructorArgs = "new Expression<Boolean>( \""
      // + expression + "\", \"Java\" ), "
                        + applicableStartTime + ", " + applicableEndTime;
      constraintType = "TimeDependentConstraintExpression";
    }

    Statement s =
        createAssignmentOfGenericType( name, constraintType, null,
                                       constructorArgs );
    ASTHelper.addStmt( initMembers.getBody(), s );

    return createFieldOfGenericType( name, constraintType, null, null );
  }

  public FieldDeclaration createDependencyField( ClassData.Param p,
                                                 MethodDeclaration initDependencies ) {
    assert( initDependencies != null );

    String suffix = "Dependency";
    String depName = p.name.replace( '.', '_' ) + suffix;
    Expression sinkExpr = expressionTranslator.parseExpression( p.name );
    String sink = null;
    if ( sinkExpr instanceof FieldAccessExpr ) {
      sink = expressionTranslator.fieldExprToAe( (FieldAccessExpr)sinkExpr,
                                                 true, true, false, false,
                                                 false, true );
    } else {
      sink = p.name;
    }
    String source = expressionTranslator.javaToAeExpr( p.value, null,//p.type,
                                                       true, false );
    
    // Check and see if this dependency is on a parameter of another class.
    // If so, remove any default dependency on the same parameter.
    String scope = null; //, member = null;
    int pos = sink.lastIndexOf( "." );
    if ( pos >= 0 ) {
      scope = sink.substring( 0, sink.lastIndexOf( '.' ) );
////      String scopeParamString = (scope + ";").replaceAll("[.]getValue\\([^)]*\\);", ";");
////      scopeParamString = scopeParamString.substring( 0, scopeParamString.length()-1 );
//      member = sink.substring( sink.lastIndexOf( '.' ) + 1 );
//      final String[] defaults = new String[] {"startTime", "endTime", "duration"};
//      if ( Arrays.asList( defaults ).contains( member ) ) {
//        String removeDepStmt = scope + ".removeDependenciesForParameter(" + sink + ");";
//        addStatements( initDependencies.getBody(), removeDepStmt );
//      }
////      String scopeDepName = "scope_" + depName;
////      String applyDepStmts = "Dependency<?> " + scopeDepName + "= getDependency("
////                             + scopeParamString + ");\n  if (" + scopeDepName + " != null) " + scopeDepName + ".apply();"; 
////      addStatements( initDependencies.getBody(), applyDepStmts );
    }

    String addDepStmt = "addDependency( " + sink + ", " + source + " );";
    if ( scope != null ) {
      addDepStmt = "if ( ((Object)" + scope
                   + ") instanceof ParameterListenerImpl) {\n((ParameterListenerImpl)((Object)"
                   + scope + "))." + addDepStmt + "\n} else {\n" + addDepStmt + "\n}";
    }
//    String constructorArgs = sink + ", " + source;
//    Statement s =
//        createAssignmentOfGenericType( depName, "Dependency", p.type,
//                                       constructorArgs );
//    ASTHelper.addStmt( initDependencies.getBody(), s );
    addStatements( initDependencies.getBody(), addDepStmt );
    return createFieldOfGenericType( depName, "Dependency", p.type, null );
  }

  public List< Pair< String, FieldDeclaration > > createEffectField( Node effectNode,
                                                                     MethodDeclaration initMembers ) {
    if ( effectNode == null ) return null;
    ClassOrInterfaceType fieldType =
        new ClassOrInterfaceType( "Effect" );
    ClassOrInterfaceType varFieldType =
            new ClassOrInterfaceType( "Parameter" ); 
    FieldDeclaration f = null;
    String effectText = expressionTranslator.fixValue( effectNode.getTextContent() );

    // parse the effect text as a MethodCallExpr
    if ( Debug.isOn() ) Debug.outln( "trying to parse effect as Java expression\"" + effectText
                        + "\"" );
    Expression expr = expressionTranslator.parseExpression( effectText );

    // If a method call, break it down into its parts.
    // String exprClassName = expr.getClass().getSimpleName();
    if ( expr instanceof MethodCallExpr ||
         expr instanceof ObjectCreationExpr ) {
//      MethodCallExpr mcExpr = (MethodCallExpr)expr;

      JavaForFunctionCall jffc = new JavaForFunctionCall( expressionTranslator, expr, true,
                                                          packageName, (Class<?>)null );

      int myNum = counter++;
      String effectName = "effect" + myNum;
      String timeVaryingName = effectName + "Var";

      StringBuffer stmtString = new StringBuffer();
      // REVIEW -- might need a dependency instead of an assignment,
      //   timeVarying <-- new Expresion(timeVaryingV) 
      
      stmtString.append( "Object " + timeVaryingName + "V = " + jffc.getObject()
                         + ";\n" );
      stmtString.append( timeVaryingName + " = new Parameter(\""
                         + timeVaryingName + "\", null, null, this);\n" );
      stmtString.append( "addDependency(" + timeVaryingName
                         + ", new Expression(" + timeVaryingName + "V));\n" );
      stmtString.append( effectName
                         + " = new EffectFunction( "
                         + jffc.toNewFunctionCallString()
                               .replaceAll( "([^A-Za-z0-9_])" + jffc.getObject().replaceAll( "[\\]\\[{}()*+?^$#-]", "." )
                                                + "([^A-Za-z0-9_])",
                                            "$1" + timeVaryingName + "$2" )
                         + " );" );
      
      addStatements( initMembers.getBody(), stmtString.toString() );

      VariableDeclaratorId id = new VariableDeclaratorId( effectName );
      Expression init = new NameExpr( "null" );
      VariableDeclarator variable = new VariableDeclarator( id, init );
      f =
          ASTHelper.createFieldDeclaration( ModifierSet.PUBLIC, fieldType,
                                            variable );
      List< Pair< String, FieldDeclaration > > pairs = new ArrayList< Pair< String, FieldDeclaration > >();
      pairs.add( new Pair< String, FieldDeclaration >( timeVaryingName, f ) );
      id = new VariableDeclaratorId( timeVaryingName );
      //init = new NameExpr( "null" );
      variable = new VariableDeclarator( id, init );
      f =
          ASTHelper.createFieldDeclaration( ModifierSet.PUBLIC, varFieldType,
                                            variable );
      pairs.add( new Pair< String, FieldDeclaration >( timeVaryingName, f ) );
      return pairs; //new Pair< String, FieldDeclaration >( timeVaryingName, f1 );
    } else {
      assert false; // TODO -- REVIEW -- Can it be something else? an
                    // assignment? signal = flow.receive(t)
    }
    return null; //new Pair( scopeExpr.toString(), f );
  }

  public FieldDeclaration
      createElaborationField( String name, String enclosingInstance,
                              String eventType,
                              String eventName, List< ClassData.Param > arguments,
                              String conditionExpression,
                              String applicableStartTime,
                              String applicableEndTime,
                              MethodDeclaration initMembers ) {
    ClassOrInterfaceType fieldType =
        new ClassOrInterfaceType( "ElaborationRule" );
    FieldDeclaration f = null;
    int myNum = counter++;
    if ( name == null ) {
      name = new String( "elaborationRule" + myNum );
    }

    String conditionName = "condition" + myNum;
    String argumentsName = "arguments" + myNum;

    StringBuffer stmtsString = new StringBuffer();
    stmtsString.append( "Expression<?>[] " + argumentsName
                        + " = new Expression<?>[" + arguments.size() + "];\n" );
    // TODO -- Need to make sure this constructor exists!
    // TODO -- Should we have a cleanup after reading XML to tie/check
    // references or wait for javac to fail?
    for ( int i = 0; i < arguments.size(); ++i ) {
      ClassData.Param p = arguments.get( i );

//      String parameterTypeAndArgs[] =
//          convertToEventParameterTypeAndConstructorArgs( p, eventType );
      String type = JavaToConstraintExpression.typeToClass( p.type );
      if ( Utils.isNullOrEmpty( type ) ) {
        ClassData.Param param = 
            getClassData().lookupMemberByName( eventType, p.name,
                                                               true, false );
        if ( param != null ) {
          type = param.type;
        }
      }
      if ( Utils.isNullOrEmpty( type ) ) {
        System.err.println( "Error! cannot create elaboration invocation "
                            + "argument for " + p );
        type = null;
      } else {
        stmtsString.append( argumentsName + "[" + i + "] = "
                            + expressionTranslator.javaToAeExpr( p.value, type, 
                                                                 true ) + ";\n" );
      }
    }

    stmtsString.append( "Expression< Boolean > "
                        + conditionName
                        + " = "
                        + expressionTranslator.javaToAeExpr( conditionExpression,
                                                             "Boolean", true )
                        + ";\n" );

    String scopeName = getClassData().getClassNameWithScope( eventType );
    stmtsString.append( name + " = addElaborationRule( " + conditionName + ", "
                        + enclosingInstance + ", "
                        + ClassUtils.noParameterName( scopeName  )
                        + ".class, "
                        + ( Utils.isNullOrEmpty( eventName ) ? "null" : "\"" 
                            + eventName + "\"" )
                        + ", " + argumentsName + " );\n" );

    addStatements( initMembers.getBody(), stmtsString.toString() );

    VariableDeclaratorId id = new VariableDeclaratorId( name );
    Expression init = new NameExpr( "null" );
    VariableDeclarator variable = new VariableDeclarator( id, init );
    f =
        ASTHelper.createFieldDeclaration( ModifierSet.PUBLIC, fieldType,
                                          variable );
    return f;
  }

  
  
  
  // REVIEW -- Do we really need to create fields for these? Just need
  // to load parameters from constructor; and not redefine loadPrameters().
  public ArrayList< FieldDeclaration >
      getParameters( Node node, MethodDeclaration initMembers ) {
    ArrayList< FieldDeclaration > parameters =
        new ArrayList< FieldDeclaration >();
    if ( node != null ) {
      List< Node > nodeList = XmlUtils.getChildNodes( node, "parameter" );
      // NodeList nodeList = node.XmlUtils.getChildNodes();
      for ( int i = 0; i < nodeList.size(); i++ ) {
        Node childNode = nodeList.get( i );
        ClassData.Param p = makeParam( childNode );
        FieldDeclaration f = createParameterField( p, initMembers );
        if ( f != null ) {
          parameters.add( f );
        }
      }
    }
    return parameters;
  }

  // REVIEW -- Do we really need to create a field for these?
  public Collection< FieldDeclaration >
      getConstraints( Node node, MethodDeclaration initMembers ) {
    ArrayList< FieldDeclaration > constraints =
        new ArrayList< FieldDeclaration >();
    List< Node > nodeList = XmlUtils.getChildNodes( node, "constraint" );
    for ( int i = 0; i < nodeList.size(); i++ ) {
      Node childNode = nodeList.get( i );
      String name = fixName( XmlUtils.getChildElementText( childNode, "name" ) );
      String expression =
          expressionTranslator.fixValue( XmlUtils.getChildElementText( childNode,
                                                                       "expression" ) );
      Node timePeriodNode = XmlUtils.getChildNode( childNode, "timePeriod" );
      String applicableStartTime =
          XmlUtils.getChildElementText( timePeriodNode, "start" );
      String applicableEndTime = XmlUtils.getChildElementText( timePeriodNode, "end" );
      FieldDeclaration f =
          createConstraintField( name, expression, applicableStartTime,
                                 applicableEndTime, initMembers );
      if ( f != null ) {
        constraints.add( f );
      }
    }
    return constraints;
  }

  public Collection< FieldDeclaration >
      getDependencies( Node node, MethodDeclaration initDependencies ) {
    ArrayList< FieldDeclaration > dependencies =
        new ArrayList< FieldDeclaration >();
    List< Node > nodeList = XmlUtils.getChildNodes( node, "dependency" );
    for ( int i = 0; i < nodeList.size(); i++ ) {
      Node childNode = nodeList.get( i );
      if ( childNode == null ) continue;
      ClassData.Param depParam = makeParam( childNode );
      if ( Utils.isNullOrEmpty( depParam.name ) ) continue;
      if ( Utils.isNullOrEmpty( depParam.value ) ) continue;
      if ( depParam.name.equals( "startTime" ) ) {
        gotStartTimeDependency = true;
      } else  if ( depParam.name.equals( "endTime" ) ) {
        gotEndTimeDependency = true;
      } else if ( depParam.name.equals( "duration" ) ) {
        gotDurationDependency = true;
      }
      FieldDeclaration f =
          createDependencyField( depParam, initDependencies );
      if ( f != null ) {
        dependencies.add( f );
      }
    }
    return dependencies;
  }

  private Collection< Pair< String, FieldDeclaration > >
      getEffects( Node effectsNode, MethodDeclaration initMembers ) {
    ArrayList< Pair< String, FieldDeclaration > > effects =
        new ArrayList< Pair< String, FieldDeclaration > >();
    List< Node > nodeList = XmlUtils.getChildNodes( effectsNode, "effect" );
    for ( int i = 0; i < nodeList.size(); i++ ) {
      Node childNode = nodeList.get( i );
      List< Pair< String, FieldDeclaration > > pairs =
          createEffectField( childNode, initMembers );
      if ( pairs != null ) {
        effects.addAll( pairs );
      }
    }
    return effects;
  }

  public Collection< FieldDeclaration >
      getElaborations( Node node, MethodDeclaration initMembers ) {
    ArrayList< FieldDeclaration > elaborations =
        new ArrayList< FieldDeclaration >();
    List< Node > nodeList = XmlUtils.getChildNodes( node, "elaboration" );
    for ( int i = 0; i < nodeList.size(); i++ ) {
      Node elaborationNode = nodeList.get( i );
      if ( elaborationNode == null ) continue;
      Node invocationNode = XmlUtils.getChildNode( elaborationNode, "eventInvocation" );
      Node conditionNode = XmlUtils.getChildNode( elaborationNode, "condition" );
      if ( invocationNode != null ) {
        String enclosingInstance = fixName( XmlUtils.getChildElementText( invocationNode, "enclosingInstance" ) );
        if ( enclosingInstance == null ) enclosingInstance = "null";
        String eventType = fixName( XmlUtils.getChildElementText( invocationNode, "eventType" ) );
        String eventName = fixName( XmlUtils.getChildElementText( invocationNode, "eventName" ) );
        Node argumentsNode = XmlUtils.getChildNode( invocationNode, "arguments" );
        List< ClassData.Param > arguments = new ArrayList< ClassData.Param >();
        if ( argumentsNode != null ) {
          List< Node > argNodeList = XmlUtils.getChildNodes( argumentsNode, "parameter" );
          for ( int j = 0; j < argNodeList.size(); j++ ) {
            Node argNode = argNodeList.get( j );
            arguments.add( makeParam( argNode ) );
          }
        }
        String expression = "true"; // default
        String applicableStartTime = "-1"; // default
        String applicableEndTime = "-1"; // default
        if ( conditionNode != null ) {
          expression = 
              expressionTranslator.fixValue( XmlUtils.getChildElementText( conditionNode,
                                                                           "expression" ) );
          Node timePeriodNode = XmlUtils.getChildNode( conditionNode, "timePeriod" );
          if ( timePeriodNode != null ) {
            applicableStartTime = XmlUtils.getChildElementText( timePeriodNode, "start" );
            applicableEndTime = XmlUtils.getChildElementText( timePeriodNode, "end" );
          }
        }
        FieldDeclaration f =
            createElaborationField( null, enclosingInstance, eventType,
                                    eventName, arguments,
                                    expression, applicableStartTime,
                                    applicableEndTime, initMembers );
        if ( f != null ) {
          elaborations.add( f );
        }
      }
    }
    return elaborations;
  }

  protected void
    buildMethodTable( Document doc,
                      Map< String, Map< String, Set< MethodDeclaration > > > methodTable ) {
    List< Node > classNodes = XmlUtils.findNodes( doc, "class" );
    for ( Node classNode : classNodes ) {
      String className = getClassName( classNode );
      
      Map< String, Set< MethodDeclaration > > classMethods = 
          methodTable.get( className );

      if ( classMethods == null ) {
        classMethods = new TreeMap< String, Set< MethodDeclaration > >();
        methodTable.put( className, classMethods );
      }
      Collection< MethodDeclaration > methodCollection = 
          getMethods( classNode );

      for ( MethodDeclaration methodDecl : methodCollection ) {
        Set< MethodDeclaration > methodSet =
            classMethods.get( methodDecl.getName() );
        if ( methodSet == null ) {
          methodSet = new TreeSet<MethodDeclaration>(new CompareUtils.GenericComparator< MethodDeclaration >());
          classMethods.put( methodDecl.getName(), methodSet );
        }
        methodSet.add( methodDecl );
      }
    }
  }
  
  public Set< MethodDeclaration > getMethodsForClass( String className ) {
    Map< String, Set< MethodDeclaration > > classMethods =
        getClassData().getMethodTable().get( className );
    if ( classMethods == null ) return ClassData.emptyMethodDeclarationSet;
    Set< MethodDeclaration > methodsForClass = new TreeSet<MethodDeclaration>(new CompareUtils.GenericComparator< MethodDeclaration >());
    for ( Set< MethodDeclaration > methodsByName : classMethods.values() ) {
      methodsForClass.addAll( methodsByName );
    }
    return methodsForClass;
  }

  public Set< MethodDeclaration > getMethodsWithName( String methodName ) {
    return getClassData().getClassMethodsWithName( methodName, getClassData().getCurrentClass() );
  }

  public static MethodDeclaration parseMethodDeclaration( String methodText ) {
    if ( Debug.isOn() ) Debug.outln( "About to parse \"" + methodText + "\"");
    ASTParser parser = new ASTParser( new StringReader( methodText ) );
    try {
      BodyDeclaration md = parser.ClassOrInterfaceBodyDeclaration( false );
      return (MethodDeclaration)md;
    } catch ( ClassCastException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return null;
  }
  
  public static ConstructorDeclaration parseConstructorDeclaration( String ctorText ) {
    if ( Debug.isOn() ) Debug.outln( "About to parse \"" + ctorText + "\"");
    ASTParser parser = new ASTParser( new StringReader( ctorText ) );
    try {
      BodyDeclaration cd = parser.ClassOrInterfaceBodyDeclaration( false );
      return (ConstructorDeclaration)cd;
    } catch ( ClassCastException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  // TODO -- If non-static and members are referenced inside, we will need to go
  // in and call param.get/setValue(). Need to do this inside expressions
  // elsewhere (javaToEventExpression()) , too.
  public Collection< MethodDeclaration > getMethods( Node classNode ) {
    ArrayList< MethodDeclaration > methodDeclarations =
        new ArrayList< MethodDeclaration >();
    Node methodsNode = XmlUtils.getChildNode( classNode, "methods" );
    List< Node > mNodeList = XmlUtils.getChildNodes( methodsNode, "function" );
    for ( Node mNode : mNodeList ) {
      String methodString = expressionTranslator.fixValue( mNode.getTextContent() );
      MethodDeclaration methodDecl = parseMethodDeclaration( methodString );
      if ( methodDecl != null ) {
        if ( !ModifierSet.isPrivate( methodDecl.getModifiers() )
             && !ModifierSet.isProtected( methodDecl.getModifiers() ) ) {
          methodDecl.setModifiers( ModifierSet.addModifier( methodDecl.getModifiers(),
                                                            ModifierSet.PUBLIC ) );
          if ( getClassData().isClassStatic( getClassName( classNode ) ) ) {
            makeStatic(methodDecl);
            methodDecl.setModifiers( ModifierSet.addModifier( methodDecl.getModifiers(),
                                                              ModifierSet.STATIC ) );
          }
        }
        methodDeclarations.add( methodDecl );
      }
    }
    return methodDeclarations;
  }

  /**
   * @return the loader
   */
  public ClassLoader getLoader() {
    if ( loader == null ) {
      loader = getClass().getClassLoader();//fileManager.getClassLoader(null);
    }
    return loader;
  }

  /**
   * @param loader the loader to set
   */
  public void setLoader( ClassLoader loader ) {
    this.loader = loader;
  }

  /**
   * @return the packageName
   */
  public String getPackageName() {
    return packageName;
  }

  /**
   * @param packageName the packageName to set
   */
  public void setPackageName( String packageName ) {
    if ( Debug.isOn() ) Debug.outln( "setting package name to " + packageName );
    this.packageName = packageName;
  }

  public void writeJavaFile( String fileName ) throws IOException {
    File f = new File( fileName );
    FileWriter w = new FileWriter( f );
    w.write( getClassData().getCurrentCompilationUnit().toString() );
    w.close();
  }

  public void writeJavaFiles( String javaPath ) throws IOException {
    for ( Entry< String, CompilationUnit > e : getClassData().getClasses().entrySet() ) {
      getClassData().setCurrentClass( e.getKey() );
      getClassData().setCurrentCompilationUnit( e.getValue() );
      String fileName =
          ( javaPath.trim() + File.separator + e.getKey() + ".java" );
      writeJavaFile( fileName );
      if ( Debug.isOn() ) Debug.outln( "wrote compilation unit to file " + fileName );
    }
  }

  public static List< String > getClassNamesFromJARFile( String jar,
                                                         String packageName ) {
    List< String > classNames = new ArrayList< String >();
    List< Class< ? > > classes = getClassesFromJARFile( jar, packageName );
    if ( !Utils.isNullOrEmpty( classes ) ) {
      for ( Class< ? > c : classes ) {
        classNames.add( c.getName().replace( '$', '.' ) );
      }
    }
    return classNames;
  }

  public static List< Class< ? > >
      getClassesFromJARFile( String jar, String packageName ) throws Error {
    final List<Class<?>> classes = new ArrayList<Class<?>>();
    JarInputStream jarFile = null;
    try {
      jarFile = new JarInputStream( new FileInputStream( jar ) );
      JarEntry jarEntry = null;
      do {
        try {
          jarEntry = jarFile.getNextJarEntry();
        } catch ( IOException ioe ) {
          System.err.println( "Unable to get next jar entry from jar file '"
                              + jar + "' -- " +  ioe );
        }
        if ( jarEntry != null ) {
          extractClassFromJar( jar, packageName, classes, jarEntry );
        }
      } while ( jarEntry != null );
      closeJarFile( jarFile );
    } catch ( IOException ioe ) {
      System.err.println( "Unable to get Jar input stream from '" + jar
                          + "' -- " + ioe );
    } finally {
      closeJarFile( jarFile );
    }
    return classes;
  }

  private static void
      extractClassFromJar( final String jar, final String packageName,
                           final List<Class<?>> classes, JarEntry jarEntry ) throws Error {
    String className = jarEntry.getName();
    if ( className.endsWith( ".class" ) ) {
      className =
          className.substring( 0, className.length() - ".class".length() );
      if ( className.startsWith( packageName ) ) {
        try {
          classes.add( Class.forName( className.replace( '/', '.' ) ) );
        } catch ( ClassNotFoundException cnfe ) {
          System.err.println( "unable to find class named "
                              + className.replace( '/', '.' )
                              + "' within jar '" + jar + "' -- " + cnfe );
        }
      }
    }
  }

  private static void closeJarFile( final JarInputStream jarFile ) {
    if ( jarFile != null ) {
      try {
        jarFile.close();
      } catch ( IOException ioe ) {
        System.err.println( "Failed to close Jar input stream '" + jarFile
                            + "' -- " + ioe );
      }
    }
  }

  public static boolean deleteFiles( File[] files ) {
    if ( files == null ) return false;
    boolean succ = true;
    for ( File f : files ) {
      if ( !f.delete() ) {
        succ = false;
      }
    }
    return succ;
  }
  
  public static File[] getJavaFileList( File path ) {
    File[] fileArr = null;
    assert path.exists();
    fileArr = path.listFiles();
    return fileArr;
  }
  
  public static File[] getJavaFileList( String javaPath ) {
    File[] fileArr = null;
    File path = new File(javaPath);
    return getJavaFileList( path );
  }
  
  public File[] getJavaFiles( String javaPath, boolean sourceOrClass,
                              boolean justCurrentClasses ) {
    File[] fileArr = null;
    File path = new File(javaPath);
    if ( javaPath == null ) {
      javaPath = (sourceOrClass ? "src" : "bin") + File.separator + this.packageName;
      File path2 = new File(javaPath);
      if ( !path2.exists() && !sourceOrClass ) {
        javaPath = "src" + File.separator + this.packageName;
        path2 = new File(javaPath);
      }
      if ( path2.exists() ) {
        path = path2;
      }
    }
    assert path.exists();
    if ( !justCurrentClasses ) {
      fileArr = getJavaFileList( path );
      if ( fileArr != null ) {
        List< File > files = new ArrayList< File >();
        for ( File f : fileArr ) {
          if ( f.getName().endsWith( sourceOrClass ? ".java" : ".class" ) ) {
            files.add( f );
          }
        }
        fileArr = new File[ files.size() ];
        int ctr = 0;
        for ( File f : files ) {
          fileArr[ ctr++ ] = f;
        }
      }
      return fileArr;
    }

    fileArr = new File[ getClassData().getClasses().size() ];
    if ( !getClassData().getClasses().isEmpty() ) {
      int ctr = 0;
      for ( String clsName : getClassData().getClasses().keySet() ) {
        String filePathName =
            javaPath.trim() + File.separator + clsName
                + ( sourceOrClass ? ".java" : ".class" );
        fileArr[ ctr++ ] = new File( filePathName );
      }
    }
    return fileArr;
  }
  
  public boolean compileJavaFiles( String javaPath ) {
    File[] fileArr = getJavaFiles( javaPath, true, true );//path.listFiles();
    if ( fileArr.length == 0 ) fileArr = getJavaFiles( javaPath, true, false );
    System.out.println( "java.home = " + System.getProperty( "java.home" ) );
    //System.setProperty( "java.home", "C:\\Program Files\\Java\\jdk1.6.0_35");
    //System.out.println( "java.home = " + System.getProperty( "java.home" ) );
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    System.out.println( "compileJavaFiles(" + javaPath
                        + "): about to get compilationUnits/java file objects for: "
                        + Utils.toString(fileArr) );
    if ( getFileManager() == null ) {
      System.err.println( "No StandardJavaFileManager to compile Java classes." );
      return false;
    }
    Iterable<? extends JavaFileObject> compilationUnits =
        fileManager.getJavaFileObjectsFromFiles(Arrays.asList(fileArr));
    System.out.println( "compileJavaFiles(" + javaPath
                        + "): got compilationUnits as java file objects: "
                        + compilationUnits );
    CompilationTask task = 
        compiler.getTask(null, fileManager, null, null, null, compilationUnits);
    System.out.println( "compileJavaFiles(" + javaPath + 
                        "): got CompilationTask: " + task );
    boolean succ = task.call();
    System.out.println( "compileJavaFiles(" + javaPath + 
                        "): compilation success=" + succ );
    return succ;
  }
  
  public boolean loadClasses( String javaPath, String packageName ) {
    boolean succ = true;
    File path = new File(javaPath);
    assert path.exists();
    File[] fileArr = null;
    fileArr = getJavaFiles( javaPath, false, true );//path.listFiles();
    if ( fileArr.length == 0 ) fileArr = getJavaFiles( javaPath, false, false );
    //loader = getClass().getClassLoader();//fileManager.getClassLoader(null);
    for ( File f : fileArr ) {
      int pos = f.getName().lastIndexOf( '.' );
      if ( pos == -1 ) pos = f.getName().length();
      String className = packageName + '.' + f.getName().substring( 0, pos );
      try {
        Class<?> cls = getLoader().loadClass( className );
        System.out.println( "loadClasses(" + javaPath + ", " + packageName +
                            "): loaded class: " + cls.getName() );
        try {
          final Object[] a = new Object[]{};
          if ( cls != null
               && ( cls.getName().equals( packageName + ".Main" )
                    || ( mainClass == null
                         && cls.getMethod( "main", a.getClass() ) != null ) ) ) {
            mainClass = cls;
          }
        } catch ( SecurityException e ) {
        } catch ( NoSuchMethodException e ) {
        }
      } catch ( ClassNotFoundException e ) {
        System.err.println( "Couldn't load class: " + className );
        e.printStackTrace();
        succ = false;
      }
    }
    return succ;
  }

  
  public String getPackageSourcePath( String projectPath ) {
    if ( projectPath == null ) {
      projectPath = "";
    } else {
      projectPath += File.separator;
    }
    String packagePath = getPackageName().replace( '.', File.separatorChar );
    String srcPath = projectPath + "src" + File.separator + packagePath;
    return srcPath;
  }
  
  public String getPackageBinPath( String projectPath ) {
    if ( projectPath == null ) {
      projectPath = "";
    } else {
      projectPath += File.separator;
    }
    String packagePath = getPackageName().replace( '.', File.separatorChar );
    String binPath = projectPath + "bin" + File.separator + packagePath;
    return binPath;
  }
  
  public boolean compileAndLoad( String projectPath ) {
    boolean succCompile = compile( projectPath );
    boolean succLoad = load( projectPath );
    return succCompile && succLoad;
  }

  public boolean compile( String projectPath ) {
    boolean succ = compileJavaFiles( getPackageSourcePath( projectPath ) );
    return succ;
  }
  
  public boolean load( String projectPath ) {
    boolean succLoad = loadClasses( getPackageBinPath( projectPath ),
                                    getPackageName() );
    return succLoad;
  }
  
  public boolean compileLoadAndRun( String projectPath ) {
    boolean succ = compileAndLoad( projectPath );
    if ( !succ ) return false;
    return runMain(); 
  }

  public Class<?> getMainClass() {
    if ( mainClass == null ) {
      try {
        mainClass = getLoader().loadClass( getPackageName() + ".Main" );
      } catch ( ClassNotFoundException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      if ( mainClass != null ) {
        if ( Debug.isOn() ) Debug.outln( "loaded class: " + mainClass.getName() );
      }
    }
    return mainClass;
  }
  
  public DurativeEvent getMainInstance() {
    if ( mainInstance == null) {
      try {
        mainInstance = (DurativeEvent)getMainClass().newInstance();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return mainInstance;
  }
  
  
  /**
   * Invoke Main.main()
   * @return whether the invocation was successful.
   */
  public boolean runMain() {
    boolean succ = false;
//        //Class<?> cls = Utils.getClassForName( "Main", getPackageName(), true );
//        Class< ? > cls;
//        try {
//          cls = Class.forName( "Main" );
//        } catch ( ClassNotFoundException e1 ) {
//          cls = Utils.getClassForName( "Main", getPackageName(), true );
//          if ( cls == null ) {
//            System.err.println("Couldn't find main!");
//            e1.printStackTrace();
//            return false;
//          }
//        }
    String args[] = new String[] { null };
    Utils.loader = getLoader();
    Pair< Boolean, Object > p =
        ClassUtils.runMethod( false, getMainClass(), "main", (Object[])args );
    return p.first;
  }

  public DurativeEvent generateExecution() {
    if ( Debug.isOn() ) Debug.outln( "generateExecution(): begin()" );
    DurativeEvent event = getMainInstance();
    if ( event == null ) {
      if ( Debug.isOn() ) Debug.errln( "generateExecution(): null main instance! no execution!" );
    } else {
      try {
        event.execute();
      } catch ( Exception e ) {
        if ( Debug.isOn() ) Debug.errln( e.toString() );
        e.printStackTrace();
      }
    }
    if ( Debug.isOn() ) Debug.outln( "generateExecution(): end()" );
    return event;
  }
  
  public <T extends DurativeEvent> T generateExecution( Class<T> eventToExecute ) {
    //boolean succ = true;
    T instance = null;
    try {
      instance = eventToExecute.newInstance();
      instance.execute();
      //succ = true;
    } catch ( InstantiationException e ) {
      e.printStackTrace();
    } catch ( IllegalAccessException e ) {
      e.printStackTrace();
    }
    
    return instance;
  }
  
  /**
   * Fix all words in the string, name, so that it can be used as a Java
   * identifier or type name. The assumption is that a name may have the form
   * foo<bar>.barfoo, in which case each of the following are passed to
   * fixSimpleName(): "foo", "bar", and "barfoo". If the string begins with a
   * number, it does not translate it into a new name but returns it unchanged,
   * assuming that it is a number.
   * 
   * @param name
   *          The string to be fixed.
   * @return A translation of name into a valid Java identifier or type name.
   */
  protected String fixName( String name ) {
    return name;
    /*
    if ( name == null ) return null;
    String oldName = name.trim();
    String newName = name.trim().replaceAll( "[\\s:;{}()\\[\\]-]", "_" );
    if ( Debug.isOn() ) if ( !newName.equals( oldName ) ) Debug.outln( "fixName(" + oldName + ") --> " + newName );
    return newName;
    */
/*    StringBuffer sb = new StringBuffer();
    Pattern pattern = Pattern.compile( "\\b" ); // \b = word boundary
    Matcher matcher = pattern.matcher( name );
    int pos = 0;
    boolean nextIsWord = false;
    //String lastText = "";
    while ( matcher.find() ) {
      String text = name.substring( pos, matcher.start() );
      //if ( Debug.isOn() ) Debug.outln( "text = name.substring( " + pos + ", "
      //                    + matcher.start() + " ) = \"" + text + "\"" );
      pos = matcher.start();
//boolean textNotAllWhitespace = false;
      if ( nextIsWord && Utils.toDouble( text ) == null ) {
        text = fixSimpleName( text );
      }
//      if ( !nextIsWord && Utils.toDouble( lastText ) == null && textNotAllWhitespace  ) {
//        text = fixSimpleName( text );
//      }
      sb.append( text );
      nextIsWord = !nextIsWord;
//      lastText = text;
    }
    // Get any trailing non-words
    sb.append( name.substring( pos ) );
    if ( Debug.isOn() ) Debug.outln("fixName(\"" + name + "\") = \"" + sb.toString() + "\"" );
    return sb.toString();
*/  }

/**
   * Fix the string identifier, name so that it can be used as a Java identifier
   * or type name.
   * 
   * @param name
   *          The string to be fixed.
   * @return A translation of name into a valid Java identifier or type name.
   */
  protected String fixSimpleName( String name ) {
    name = name.replaceAll( "[\\W]", "_" );
    return name;
/*    if ( name == null ) return null;
    //String paramPart = Utils.parameterPartOfName( name );
    //String noParamName = Utils.noParameterName( name );
    //assert name.trim().equals( ( noParamName + paramPart ).trim() );
    String javaName = nameTranslator.translate( name, "xml", "java" );
    // TODO -- REVIEW -- Do we need to sweep though and get names first before values?
//    for ( Map.Entry e : classes.entrySet() ) {
//    }
    //if ( Debug.isOn() ) Debug.outln("fixSimpleName(\"" + name + "\") = \"" + javaName + "\"" );
    return javaName;
*/  }

  // Get the name of the class from the DOM node. If it is an inner class,
  // prepend the names of the enclosing classes for proper scope (but leaving
  // off the package name).
  public String getClassName( Node classOrEventNode ) {
    String name = "";
    Node classNode = classOrEventNode;
    if ( classOrEventNode.getLocalName().equals( "event" ) ) {
      classNode = XmlUtils.getChildNode( classOrEventNode, "class" );
      if ( classNode == null ) classNode = classOrEventNode;  // warning?
    }
    while ( classNode != null ) {
      //name = XmlUtils.getChildElementText( classNode, "name" );
  
      String rawName = XmlUtils.getChildElementText( classNode, "name" );
      if ( rawName == null ) return null;
      if ( rawName.contains( "25431" ) ) {
        Debug.out( "" );
      }
      name = fixName( rawName ) + "." + name;
      classNode =
          XmlUtils.getEnclosingNodeWithName( classNode.getParentNode(),
                                             "class" );
    }
    // remove '.' at end of string for first pass through loop above.
    name = name.substring( 0, name.length() - 1 );
    return name;
  }

  // Returns input DOM node if it has a localName "class," the closest parent
  // "class" node, or null.
  public String getEnclosingClassName( Node node ) {
    Node classNode = XmlUtils.getEnclosingNodeWithName( node, "class" );
    return getClassName( classNode );
  }

  /**
   * @return the compilationUnit
   */
  public CompilationUnit getCurrentCompilationUnit() {
    return getClassData().getCurrentCompilationUnit();
  }

  /**
   * @return the currentClass
   */
  public String getCurrentClass() {
    return getClassData().getCurrentClass();
  }

  /**
   * @param currentClass
   *          the currentClass to set
   */
  public void setCurrentClass( String currentClass ) {
    getClassData().setCurrentClass( currentClass );
    if ( getClassData().getClasses().containsKey( currentClass ) ) {
      getClassData().setCurrentCompilationUnit( getClassData().getClasses().get( currentClass ) );
    }
  }

  /**
   * @return the expressionTranslator
   */
  public JavaToConstraintExpression getExpressionTranslator() {
    return expressionTranslator;
  }
  
  /**
   * @param expressionTranslator the expressionTranslator to set
   */
  public void
      setExpressionTranslator( JavaToConstraintExpression expressionTranslator ) {
    this.expressionTranslator = expressionTranslator;
  }
  
  public ClassData getClassData() {
    JavaToConstraintExpression t = getExpressionTranslator();
    if ( Debug.errorOnNull( "Trying to get classData from null translator!", t ) ) {
      return null;
    }
    return t.getClassData();
  }
  
}
