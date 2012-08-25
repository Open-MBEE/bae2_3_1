package gov.nasa.jpl.ae.xml;

import japa.parser.ASTHelper;
import japa.parser.ASTParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.TypeParameter;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.body.VariableDeclaratorId;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BinaryExpr.Operator;
import japa.parser.ast.expr.ConditionalExpr;
import japa.parser.ast.expr.EnclosedExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.expr.UnaryExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.CatchClause;
import japa.parser.ast.stmt.ExplicitConstructorInvocationStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.Type;
import japa.parser.ast.type.VoidType;

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
import java.lang.reflect.TypeVariable;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;
// import javax.xml.xpath.XPathExpression;

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
import gov.nasa.jpl.ae.event.Pair;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.TimeVarying;
import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.FileUtils;
import gov.nasa.jpl.ae.xml.EventXmlToJava.Param;

@SuppressWarnings( "unused" )
public class EventXmlToJava {
  
  public static class Param {
    public String name;
    public String type;
    public String value;

    public Param( String name, String type, String value ) {
      this.name = name;
      this.type = type;
      this.value = value;
    }

    public Param( Node n ) {
      name = XmlUtils.getChildElementText( n, "name" );
      type = XmlUtils.getChildElementText( n, "type" );
      value = XmlUtils.getChildElementText( n, "value" );
    }

    public String toString() {
      return "(" + name + ", " + type + ", " + value + ")";
    }
  }

  protected static int counter = 0; // for naming

  protected Document xmlDocDOM = null;

  protected String currentClass = null;
  protected CompilationUnit currentCompilationUnit = null;

  protected Map< String, CompilationUnit > classes =
      new TreeMap< String, CompilationUnit >();

  protected Map< String, Map< String, Param > > paramTable =
      new TreeMap< String, Map< String, Param > >();

  protected Map< String, Map< String, Set< MethodDeclaration > > > methodTable =
      new TreeMap< String, Map< String, Set< MethodDeclaration > > >();
  
  protected String packageName = "generated";

  private boolean gotStartTimeDependency;
  private boolean gotEndTimeDependency;
  private boolean gotDurationDependency;

  public EventXmlToJava( String xmlFileName, String pkgName )
      throws ParserConfigurationException, SAXException, IOException {
    if ( pkgName != null && !pkgName.equals( "" ) ) {
      this.packageName = pkgName;
    }

    // Translate XML to a DOM Document.
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware( true );
    DocumentBuilder builder;
    // XPathExpression expr = null;
    builder = factory.newDocumentBuilder();
    xmlDocDOM = builder.parse( xmlFileName );

    if ( !XmlUtils.validateXML( xmlFileName, xmlDocDOM ) ) {
      System.err.println( "Warning! XML file "
                          + xmlFileName
                          + " does not validate against its schema definition.  "
                          + "Continuing anyway." );
    }

    Node scenarioNode = XmlUtils.findNode( xmlDocDOM, "scenario" );
    Assert.assertNotNull( scenarioNode );
    
    // get epoch
    String epochString = XmlUtils.getChildElementText( scenarioNode, "epoch" );
    if ( epochString == null || epochString.isEmpty() ) {
      Debug.outln( "no epoch specified; using default" );
    } else {
      Timepoint.setEpoch( epochString );
    }
    String timeUnits = XmlUtils.getChildElementText( scenarioNode, "timeUnits" );
    if ( timeUnits == null || timeUnits.isEmpty() ) {
      Debug.outln( "no units specified; using default" );
    } else {
      Timepoint.setUnits( timeUnits );
    }
    System.out.println( "epoch = " + Timepoint.getEpoch() );
    System.out.println( "units = " + Timepoint.getUnits() );
    
    // build tables
    buildParamTable( xmlDocDOM, paramTable );
    buildMethodTable( xmlDocDOM, methodTable );
    
    // process classes first
    NodeList nodeList = xmlDocDOM.getElementsByTagName( "classes" );
    Assert.assertTrue( nodeList.getLength() < 2 );
    if ( nodeList.getLength() == 1 ) {
      List< Node > nList = XmlUtils.getChildNodes( nodeList.item( 0 ), "class" );
      for ( int i = 0; i < nList.size(); i++ ) {
        Node node = nList.get( i );
        processClass( node );
      }
    }

    // process events
    nodeList = xmlDocDOM.getElementsByTagName( "events" );
    Assert.assertTrue( nodeList.getLength() < 2 );
    if ( nodeList.getLength() == 1 ) {
      // nodeList = nodeList.item( 0 ).XmlUtils.getChildNodes();
      List< Node > nList = XmlUtils.getChildNodes( nodeList.item( 0 ), "event" );
      for ( int i = 0; i < nList.size(); i++ ) {
        Node node = nList.get( i );
        // for ( int i = 0; i < nodeList.getLength(); i++ ) {
        // Node node = nodeList.item( i );
        processEvent( node );
      }
    }

    // process event to be executed
    nodeList = xmlDocDOM.getElementsByTagName( "eventToBeExecuted" );
    Assert.assertTrue( nodeList.getLength() < 2 );
    if ( nodeList.getLength() == 1 ) {
      Node node = nodeList.item( 0 );
      processExecutionEvent( node );
    }

    // Add constructors for invocations.
    addConstructors();

    // cleanup any missing references, constructors, imports?
  }

  protected Param lookupMemberByName( String className, String paramName ) {
    Map< String, Param > params = paramTable.get( className );
    if ( params == null ) return null;
    // TODO -- if null, look in parent from which className inherits.
    return params.get( paramName );
  }

  protected static void
      buildParamTable( Document doc,
                       Map< String, Map< String, Param >> paramTable ) {
    List< Node > nList = XmlUtils.findNodes( doc, "class" );

    int lastSize = nList.size() + 1;
    while ( !nList.isEmpty() && nList.size() < lastSize ) {
      lastSize = nList.size();
      ListIterator< Node > i = nList.listIterator();
      while ( i.hasNext() ) {
        // for ( int i = 0; i < nList.size(); i++ ) {
        Node classNode = i.next(); // nList.get( i );
        String className = XmlUtils.getChildElementText( classNode, "name" );
        String superClass = XmlUtils.getChildElementText( classNode, "inheritsFrom" );
        Map< String, Param > superParams = null;
        // If super class has not been processed, try again on the next pass.
        if ( superClass != null && !superClass.isEmpty() ) {
          superParams = paramTable.get( superClass );
          if ( superParams == null ) {
            continue;
          }
        }
        // Make an entry in the table for this class.
        Map< String, Param > params = paramTable.get( className );
        if ( params == null ) {
          params = new TreeMap< String, Param >();
          paramTable.put( className, params );
        }
        // Get the default DurativeEvent params if not available from superClass
        if ( superParams == null || superParams.isEmpty() ) {
          DurativeEvent de = new DurativeEvent();
          for ( gov.nasa.jpl.ae.event.Parameter< ? > p : de.getParameters() ) {
            if ( !params.containsKey( p.getName() ) ) {
              String pType =
                  ( p.getValueNoPropagate() == null 
                    ? "Integer" // TODO -- big assumption! Use p.getClass().
                    : p.getValueNoPropagate().getClass().getSimpleName() );
              params.put( p.getName(),
                          new Param( p.getName(), pType,
                                     ( p.getValueNoPropagate() == null 
                                       ? null
                                       : p.getValueNoPropagate().toString() ) ) );
            }
          }
        } else {
          // Get inherited params.
          params.putAll( superParams );
        }
        // Now add params defined locally
        Node membersNode = XmlUtils.getChildNode( classNode, "members" );
        List< Node > pNodes = XmlUtils.getChildNodes( membersNode, "parameter" );
        // NodeList nodeList = node.XmlUtils.getChildNodes();
        for ( int j = 0; j < pNodes.size(); j++ ) {
          Node pNode = pNodes.get( j );
          Param p = new Param( pNode );
          Param ep = params.get( p.name );
          if ( ep == null ) {
            params.put( p.name, p );
          } else {
            // TODO -- BAD!
          }
        }
        i.remove();
      }
    }
  }

  // Add constructors for invocations.
  private void addConstructors() {
    Collection< ConstructorDeclaration > constructors =
        createConstructors( this.xmlDocDOM );
    for ( ConstructorDeclaration c : constructors ) {
      TypeDeclaration type = getTypeDeclaration( c.getName() );
      ASTHelper.addMember( type, c );
    }
  }

  // Look in the compilation units in the map, classes, to find the class
  // declaration of name.
  protected TypeDeclaration getTypeDeclaration( String name ) {
    CompilationUnit cu = this.classes.get( name );
    ClassOrInterfaceDeclaration classDecl = null;
    for ( TypeDeclaration type : cu.getTypes() ) {
      if ( type.getName().equals( name ) ) {
        return type;
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
    currentCompilationUnit.setPackage( new PackageDeclaration( ASTHelper.createNameExpr( packageName ) ) );
  }

  protected ClassOrInterfaceDeclaration processEvent( Node event ) {
    Node classNode = XmlUtils.getChildNode( event, "class" );
    ClassOrInterfaceDeclaration newClassDecl =
        processClassDeclaration( classNode, true );

    if ( newClassDecl.getExtends() == null ) {
      newClassDecl.setExtends( new ArrayList< ClassOrInterfaceType >() );
    }
    if ( newClassDecl.getExtends().isEmpty() ) {
      newClassDecl.getExtends()
                  .add( new ClassOrInterfaceType( "DurativeEvent" ) );
    }

    // Need a method for initializing members and populating Event collections
    // (parameters, effects, etc.).
    MethodDeclaration initMembers =
        createInitMembersMethod( "init" + newClassDecl.getName() + "Members" );

    // Get fields (parameters, constraints, & dependencies).
    // ArrayList< Parameter< ? > > members =
    List< FieldDeclaration > members = new ArrayList< FieldDeclaration >();
    Collection< FieldDeclaration > parameters =
        getParameters( XmlUtils.getChildNode( classNode, "members" ), initMembers );
    Collection< FieldDeclaration > constraints =
        getConstraints( XmlUtils.getChildNode( classNode, "constraints" ), initMembers );
    Collection< FieldDeclaration > dependencies =
        getDependencies( XmlUtils.getChildNode( classNode, "dependencies" ), initMembers );
    // TODO -- Get effects.
    Collection< Pair< String, FieldDeclaration > > effects =
        getEffects( XmlUtils.getChildNode( event, "effects" ), initMembers );
    // TODO -- Get elaborations.
    Collection< FieldDeclaration > elaborations =
        getElaborations( XmlUtils.getChildNode( event, "elaborations" ), initMembers );

    members.addAll( parameters );
    members.addAll( constraints );
    members.addAll( dependencies );
    for ( Pair< String, FieldDeclaration > p : effects ) {
      members.add( p.second );
    }
    members.addAll( elaborations );

    addTryCatchToInitMembers(initMembers);

    // Need a method for populating Event collections (parameters, effects,
    // etc.).
    MethodDeclaration initCollections =
        createInitCollectionsMethod( "init" + newClassDecl.getName()
                                     + "Collections", parameters, // methods,
                                     constraints, dependencies, effects );
    // elaborations );

    // Add fields and methods to class declaration.
    for ( FieldDeclaration f : members ) {
      ASTHelper.addMember( newClassDecl, f );
    }
    ASTHelper.addMember( newClassDecl, initMembers );
    ASTHelper.addMember( newClassDecl, initCollections );

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

    // TODO -- What else?

    return newClassDecl;
  }

  protected void processExecutionEvent( Node invocationNode ) {
    assert( invocationNode != null );

    currentClass = "Main";
    initCompilationUnit( currentClass );

    ClassOrInterfaceDeclaration newClassDecl =
        new ClassOrInterfaceDeclaration( ModifierSet.PUBLIC, false,
                                         currentClass );
    ASTHelper.addTypeDeclaration( currentCompilationUnit, newClassDecl );

    // Create public static main( String args[] ) { }
    // First, create main() { }
    int mods = ModifierSet.PUBLIC | ModifierSet.STATIC;
    
    MethodDeclaration mainMethodDecl =
        new MethodDeclaration( mods, new VoidType(), "main" );
    mainMethodDecl.setBody( new BlockStmt() );
  
    // Need to set the epoch and units first thing.
    // REVIEW -- We need a scenario event that requires these arguments as a
    // constructor to ensure they are set up front.
    //String epochString = Timepoint.toTimestamp( Timepoint.getEpoch().getTime() );
    addStatements( mainMethodDecl.getBody(),
                   "Timepoint.setUnits(\"" + Timepoint.getUnits() + "\");\n" );
    addStatements( mainMethodDecl.getBody(),
                   "Timepoint.setEpoch(\"" + Timepoint.getEpoch() + "\");\n" );

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
    //List< Expression > args = new ArrayList< Expression >();
    String className = XmlUtils.getChildElementText( invocationNode, "eventType" );
    String instanceName = XmlUtils.getChildElementText( invocationNode, "eventName" );
    if ( instanceName == null || instanceName.isEmpty() ) {
      instanceName = className + (counter++);
    }

    // Use a StringBuffer to collect the statements. 
    StringBuffer stmtsSB = new StringBuffer();

    // Get constructor arguments and create a statement constructing the instance.
    stmtsSB.append( className + " " + instanceName + " = new " + className + "(");
    Node argumentsNode = XmlUtils.getChildNode( invocationNode, "arguments" );
    //List< Param > arguments = new ArrayList< Param >();
    if ( argumentsNode != null ) {
      List< Node > argNodeList = XmlUtils.getChildNodes( argumentsNode, "parameter" );
      boolean first = true;
      for ( int j = 0; j < argNodeList.size(); j++ ) {
        if ( first ) {
          first = false;
        } else {
          stmtsSB.append( ", " );
        }
        Node argNode = argNodeList.get( j );
        Param p = new Param( argNode );
        stmtsSB.append( javaToEventExpression( p.value, p.type, true ) );
      }
    }
    stmtsSB.append(");\n");
    
    // Need to import event.Expression etc. for constructor arguments
    addImport( "gov.nasa.jpl.ae.event.TimeVarying" );
    addImport( "gov.nasa.jpl.ae.event.TimeVaryingMap" );
    addImport( "gov.nasa.jpl.ae.event.Expression" );
    addImport( "gov.nasa.jpl.ae.event.Timepoint" );
//    addImport( "event.FunctionCall" );
//    addImport( "event.Functions" );
    
    // Create statements for executing & simulating the scenario event.
    stmtsSB.append( instanceName + ".execute();\n" );
    
    // Put the statements in main().
    addStatements( mainMethodDecl.getBody(), stmtsSB.toString() );
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
//    try {
//      Method m = Class.forName( this.getClass().getName() ).getMethod( initMembers.getName(), MethodDeclaration.class );      
//    } catch ( NoSuchMethodException | SecurityException
//              | ClassNotFoundException e1 ) {
//      e1.printStackTrace();
//    }
    String pkg = packageName + ".";
    if ( pkg.length() == 1 ) {
      pkg = "";
    }
    // TODO -- REVIEW -- Why are we creating a Method referring to the method
    // it's in???  I guess this was a test.
//    addStatements( initMembers.getBody(),
//                   "java.lang.reflect.Method m = Class.forName( \""
//                       + pkg + currentClass + "\" ).getMethod( \""
//                       + initMembers.getName() + "\", (Class<?>[])null );" );
    
    String tryCatchString =
        "try{\n" + ";\n" + "} catch ( Exception e ) {\n"
            + "  // TODO Auto-generated catch block\n"
            + "  e.printStackTrace();\n"
            + "}\n";
//        "try{\n" + ";\n" + "} catch ( SecurityException e ) {\n"
//            + "  // TODO Auto-generated catch block\n"
//            + "  e.printStackTrace();\n"
//            + "} catch ( NoSuchMethodException e ) {\n"
//            + "  // TODO Auto-generated catch block\n"
//            + "  e.printStackTrace();\n"
//            + "} catch ( ClassNotFoundException e ) {\n"
//            + "  // TODO Auto-generated catch block\n"
//            + "  e.printStackTrace();\n"
//            + "}\n";

    List< Statement > stmts = new ArrayList< Statement >();
    Debug.outln( "trying to parse \"" + stmts + "\"" );

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

  // Create constructors for event invocations.
  protected Collection< ConstructorDeclaration > createConstructors( Node top ) {
    Collection< ConstructorDeclaration > ctors =
        new ArrayList< ConstructorDeclaration >();

    List< Node > invocations = XmlUtils.findNodes( top, "eventToBeExecuted" );
    invocations.addAll( XmlUtils.findNodes( top, "eventInvocation" ) );
    for ( Node invocationNode : invocations ) {
      //String name = XmlUtils.getChildElementText( invocationNode, "eventName" );
      if ( invocationNode != null ) {
        String eventType = XmlUtils.getChildElementText( invocationNode, "eventType" );
        ConstructorDeclaration ctor =
            new ConstructorDeclaration( ModifierSet.PUBLIC, eventType );
        Node argumentsNode = XmlUtils.getChildNode( invocationNode, "arguments" );
        List< Param > arguments = new ArrayList< Param >();
        if ( argumentsNode != null ) {
          List< Node > argNodeList = XmlUtils.getChildNodes( argumentsNode, "parameter" );
          for ( int j = 0; j < argNodeList.size(); j++ ) {
            Node argNode = argNodeList.get( j );
            arguments.add( new Param( argNode ) );
          }
          List< TypeParameter > typeParameters =
              new ArrayList< TypeParameter >();
          List< japa.parser.ast.body.Parameter > parameters =
              new ArrayList< japa.parser.ast.body.Parameter >();
          for ( Param p : arguments ) {
            japa.parser.ast.body.Parameter param =
                ASTHelper.createParameter( new ClassOrInterfaceType( "Expression<"
                                                                         + p.type
                                                                         + ">" ),
                                           p.name );
            parameters.add( param );
            // List< ClassOrInterfaceType > typeBound = new ArrayList<
            // ClassOrInterfaceType >();
            // typeBound.add( e );
            // typeParameters.add( new TypeParameter( p.name, typeBound ) );
          }
          // ctor.setTypeParameters( typeParameters );
          ctor.setParameters( parameters );
          addStatementsToConstructor( ctor, arguments );
        }

        // Check and see if we've already added this one.
        boolean alreadyCreated = false;
        for ( ConstructorDeclaration c : ctors ) {
          if ( equals( c, ctor ) ) {
            alreadyCreated = true;
            break;
          }
        }
        // Don't add if already created. Default constructor is added
        // elsewhere, so filter that one out by checking if arguments is empty.
        if ( !alreadyCreated && !arguments.isEmpty() ) {
          ctors.add( ctor );
        }
      }
    }
    return ctors;
  }

  public static boolean equals( ConstructorDeclaration c1,
                                ConstructorDeclaration c2 ) {
    boolean equals = false;
    List< japa.parser.ast.body.Parameter > params1 = c1.getParameters();
    List< japa.parser.ast.body.Parameter > params2 = c2.getParameters();
    if ( c1.getName() == c2.getName() && params1.size() == params2.size() ) {
      equals = true;
      for ( int i = 0; i < params1.size(); ++i ) {
        japa.parser.ast.body.Parameter p1 = params1.get( i );
        japa.parser.ast.body.Parameter p2 = params2.get( i );
        if ( p1.getType() != p2.getType() ) {
          equals = false;
          break;
        }
      }
    }
    return equals;
  }

  protected static void
      addStatementsToConstructor( ConstructorDeclaration ctor,
                                  List< Param > arguments ) {
    StringBuffer stmtList = new StringBuffer();
    BlockStmt block = new BlockStmt();
    ASTHelper.addStmt( block, new ExplicitConstructorInvocationStmt() );
    // stmtList.append( "super();\n" );
    stmtList.append( "init" + ctor.getName() + "Members();\n" );
    stmtList.append( "init" + ctor.getName() + "Collections();\n" );
    for ( Param p : arguments ) {
      if ( p.name.equals( "startTime" ) || p.name.equals( "endTime" )
           || p.name.equals( "duration" ) ) {
        stmtList.append( "removeDependency( this." + p.name
                         + " );\n" );
      }
      stmtList.append( "addDependency( this." + p.name + ", " + p.name
                       + " );\n" );
    }
    addStatements( block, stmtList.toString() );
    ctor.setBlock( block );
    // ctor.setBlock( createBlock( stmtList.toString() ) );
  }

  protected MethodDeclaration createInitMembersMethod( String methodName ) {
    MethodDeclaration initMembers =
        new MethodDeclaration( ModifierSet.PUBLIC, new VoidType(),
                               methodName );
    initMembers.setBody( new BlockStmt() );
    return initMembers;
  }

  private static void addStmts( BlockStmt block, List< Statement > list ) {
    if ( list != null ) {
      for ( Statement stmt : list ) {
        ASTHelper.addStmt( block, stmt );
      }
    }
  }

  public static void addStatements( BlockStmt block, String stmts ) {
    Debug.outln( "trying to parse \"" + stmts + "\"" );
    List< Statement > list = stringToStatementList( stmts );
    addStmts( block, list );
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
                                   Collection< FieldDeclaration > dependencies,
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
    stmtList = removeConflictingDependencies();
    addStmts( block, stmtList );
    stmtList =
        createStmtsFromFieldCollection( "dependencies.add( ", dependencies,
                                        " );\n" );
    addStmts( block, stmtList );
    // TODO -- correct for effects?
    stmtList =  
        createEffectStmtsFromFieldCollection( "effects.put( ", effects, " );\n" );
    addStmts( block, stmtList );
    // // TODO -- not correct for elaborations
    // stmtList =
    // createStmtsFromFieldCollection( "elaborations.add( ", elaborations,
    // " );\n" );
    // if ( stmtList != null ) {
    // addStmts( block, stmtList );
    // }
    // stmtList = createElaborationInitStmts( elaborations );
    // if ( stmtList != null ) {
    // addStmts( block, stmtList );
    // }

    initCollections.setBody( block );
    return initCollections;
  }

  public static List< Statement > stringToStatementList( String s ) {
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
  
  protected List< Statement > removeConflictingDependencies() {
    StringBuilder sb = new StringBuilder();
    if ( gotStartTimeDependency ) {
      sb.append( "removeDependency( startTime );\n" );
    }
    if ( gotEndTimeDependency ) {
      sb.append( "removeDependency( endTime );\n" );
    }
    if ( gotDurationDependency ) {
      sb.append( "removeDependency( duration );\n" );
    }
    return stringToStatementList( sb.toString() );
  }

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
      Set< FieldDeclaration > set = map.get( p.first );
      if ( set == null ) {
        set = new HashSet< FieldDeclaration >();
        map.put( p.first, set );
      }
      set.add( p.second );
    }
    boolean madeSet = false;
    if ( fieldCollection == null || fieldCollection.isEmpty() ) return null;
    StringBuilder sb = new StringBuilder();
    for ( Map.Entry< String, Set< FieldDeclaration > > e : map.entrySet() ) {
      String effectSetName = "effectsFor" + e.getKey();
      sb.append( "Set<Effect> " + effectSetName + " = new HashSet<Effect>();\n" );
      madeSet = true;
      for ( FieldDeclaration f : e.getValue() ) {
        sb.append( effectSetName + ".add( " + f.getVariables().get( 0 ).getId() + " );\n" );
      }
      sb.append( prefix + e.getKey() + ", " + effectSetName + suffix );
    }
    if ( madeSet ) {
      addImport("java.util.Set");
      addImport("java.util.HashSet");
      addImport("gov.nasa.jpl.ae.event.EffectFunction");
    }
    return stringToStatementList( sb.toString() );
  }

  // public String processClass( Node cls ) {
  public ClassOrInterfaceDeclaration processClass( Node cls ) {
    ClassOrInterfaceDeclaration newClassDecl =
        processClassDeclaration( cls, false );
    // TODO -- need to process parameters as simple local fields (not
    // Parameter<T>)
    return newClassDecl;
  }

  public ClassOrInterfaceDeclaration processClassDeclaration( Node clsNode,
                                                              boolean asEvent ) {
    gotStartTimeDependency = false;
    gotEndTimeDependency = false;
    gotDurationDependency = false;

    // Get class name.
    String name = XmlUtils.getChildElementText( clsNode, "name" );
    currentCompilationUnit = initClassCompilationUnit( name );
    ClassOrInterfaceDeclaration newClassDecl =
        new ClassOrInterfaceDeclaration( ModifierSet.PUBLIC, false, name );

    // Get class inheritances as Java extends list.
    // TODO -- REVIEW -- Do we need to deal with (allow for) Java interfaces?
    // Can only extend one class!
    List< ClassOrInterfaceType > extendsList = getInheritsFrom( clsNode );
    if ( extendsList != null && !extendsList.isEmpty() ) {
      newClassDecl.setExtends( extendsList );
    }

    if ( !asEvent ) {
      // TODO -- Add parameters as field declarations that are not wrapped with
      // Parameter<>.
    }

    // Get methods/functions.  
    // TODO -- If asEvent, then don't we need to convert argument types to Expressions?
    //  [Maybe no: can make into a Function expression and only pass evaluated values.]
    // TODO -- Can some args be Expressions and not others?
    // TODO -- Maybe we need to check and see whether the function is invoked with Expressions from events?  This might even determine whether class members should be Parameters.
    Collection< MethodDeclaration > methods = getMethodsForClass( currentClass );
    for ( MethodDeclaration methodDecl : methods ) {
//      if ( asEvent ) {
//        // TODO
//      } else {
        ASTHelper.addMember( newClassDecl, methodDecl );
//      }
    }

    // TODO -- REVIEW -- Need to add other things?

    ASTHelper.addTypeDeclaration( currentCompilationUnit, newClassDecl );
    return newClassDecl;
  }

  private CompilationUnit initCompilationUnit( String name ) {
    currentClass = name;
    currentCompilationUnit = new CompilationUnit();
    classes.put( name, currentCompilationUnit );
    setPackage();
    return currentCompilationUnit;
  }
  private CompilationUnit initClassCompilationUnit( String name ) {
    currentCompilationUnit = initCompilationUnit( name );
    // REVIEW -- How can we access eclipse's ability to auto-remove unused
    // imports?
    addImport( "gov.nasa.jpl.ae.event.Parameter" );
    addImport( "gov.nasa.jpl.ae.event.IntegerParameter" );
    addImport( "gov.nasa.jpl.ae.event.DoubleParameter" );
    addImport( "gov.nasa.jpl.ae.event.StringParameter" );
    addImport( "gov.nasa.jpl.ae.event.BooleanParameter" );
    addImport( "gov.nasa.jpl.ae.event.Expression" );
    addImport( "gov.nasa.jpl.ae.event.ConstraintExpression" );
    addImport( "gov.nasa.jpl.ae.event.Functions" );
    addImport( "gov.nasa.jpl.ae.event.FunctionCall" );
    addImport( "gov.nasa.jpl.ae.event.Effect" );
    addImport( "gov.nasa.jpl.ae.event.TimeDependentConstraintExpression" );
    addImport( "gov.nasa.jpl.ae.event.Dependency" );
    addImport( "gov.nasa.jpl.ae.event.ElaborationRule" );
    addImport( "gov.nasa.jpl.ae.event.EventInvocation" );
    addImport( "gov.nasa.jpl.ae.event.Event" );
    addImport( "gov.nasa.jpl.ae.event.DurativeEvent" );
    addImport( "gov.nasa.jpl.ae.event.TimeVarying" );
    addImport( "gov.nasa.jpl.ae.event.TimeVaryingMap" );
    addImport( "java.util.Vector" );
    addImport( "java.util.Map" );
    return currentCompilationUnit;
  }

  private void addImport( String impName ) {
    NameExpr ne = new NameExpr( impName );
    ImportDeclaration d = new ImportDeclaration( ne, false, false );
    if ( currentCompilationUnit.getImports() == null ) {
      currentCompilationUnit.setImports( new ArrayList< ImportDeclaration >() );
    }
    currentCompilationUnit.getImports().add( d );
  }

  public static List< ClassOrInterfaceType > getInheritsFrom( Node cls ) {
    List< String > extendsStringList =
        XmlUtils.getChildrenElementText( cls, "inheritsFrom" );
    List< ClassOrInterfaceType > extendsList =
        new ArrayList< ClassOrInterfaceType >();
    for ( String e : extendsStringList ) {
      ClassOrInterfaceType c = new ClassOrInterfaceType( e );
      extendsList.add( c );
    }
    return extendsList;
  }

  // TODO -- Do we need to add the "owner" event as currently required for the
  // Parameter constructors?
  public static
      FieldDeclaration
      createFieldOfGenericType( String name, String typeName,
                                String parameterTypeName, String constructorArgs ) {
    String fieldTypeName = typeName;
    if ( parameterTypeName != null ) {
      fieldTypeName += "< " + parameterTypeName + " >";
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
      if ( parameterTypeName != null ) {
        initValue += "< " + parameterTypeName + " >";
      }
      initValue += "( " + constructorArgs + " )";
      // if ( constructorArgs != null ) {
      // initValue += constructorArgs;
      // }
      // initValue += " )";
    }
    init = new NameExpr( initValue );
    VariableDeclarator variable = new VariableDeclarator( id, init );
    f =
        ASTHelper.createFieldDeclaration( ModifierSet.PUBLIC, fieldType,
                                          variable );
    // f = ASTHelper.createFieldDeclaration( ModifierSet.PUBLIC, fieldType, name
    // + " = null");
    return f;
  }

  public static Statement
      createAssignmentOfGenericType( String name, String typeName,
                                     String parameterTypeName,
                                     String constructorArgs ) {
    StringBuffer stmtsString = new StringBuffer();
    stmtsString.append( name + " = " );
    // stmtsString.append( "public " + typeName );
    // if ( parameterTypeName != null ) {
    // stmtsString.append( "< " + parameterTypeName + " >" );
    // }
    if ( constructorArgs == null ) {
      stmtsString.append( "null;" );
    } else {
      stmtsString.append( "new " + typeName );
      if ( parameterTypeName != null ) {
        stmtsString.append( "< " + parameterTypeName + " >" );
      }
      stmtsString.append( "( " + constructorArgs + " );" );
    }

    Debug.outln( "Trying to parse assignment with ASTParser.BlockStatement(): \""
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

  // REVIEW -- Do we need to specify a domain in the XML? look up a domain based
  // on p.type?
  public String[] convertToEventParameterTypeAndConstructorArgs( Param p ) {
    String ret[] = new String[ 3 ];
    if ( p.type == null || p.type.isEmpty() || p.type.equalsIgnoreCase( "null" ) ) {
      Param pDef = this.lookupCurrentClassMember( p.name );
      if ( pDef != null ) {
        p.type = pDef.type;
      }
    }
    String type = "Parameter";
    String parameterTypes = p.type;
    String domain = null;
    String args = "\"" + p.name + "\", null, " + p.value + ", this";
    if ( p.type == null || p.type.isEmpty() || p.type.equalsIgnoreCase( "null" ) ) {
      System.err.println( "Error! creating a field " + p + " of unknown type!" );
    } else if ( p.type.toLowerCase().startsWith( "int" )
                || p.type.toLowerCase().startsWith( "long" ) // TODO -- Need a
                                                             // LongParameter
                || p.type.trim().replaceAll( " ", "" )
                         .equals( "Parameter<Integer>" ) ) {
      type = "IntegerParameter";
      parameterTypes = null; // "Integer";
      // domain = "IntegerDomain";
      args = "\"" + p.name + "\", this";
    } else if ( p.type.toLowerCase().equals( "double" )
                || p.type.trim().replaceAll( " ", "" )
                         .equals( "Parameter<Double>" ) ) {
      type = "DoubleParameter";
      parameterTypes = null;
      args = "\"" + p.name + "\", this";
    } else if ( p.type.toLowerCase().equals( "boolean" )
                || p.type.trim().replaceAll( " ", "" )
                         .equals( "Parameter<Boolean>" ) ) {
      type = "BooleanParameter";
      parameterTypes = null;
      args = "\"" + p.name + "\", this";
    } else if ( p.type.equals( "String" )
                || p.type.trim().replaceAll( " ", "" )
                         .equals( "Parameter<String>" ) ) {
      type = "StringParameter";
      parameterTypes = null;
      args = "\"" + p.name + "\", this";
//    } else if ( p.type.startsWith( "TimeVaryingMap" ) ) {
//      args = "\"" + p.name + "\", this";
    }
    ret[ 0 ] = type;
    ret[ 1 ] = parameterTypes;
    ret[ 2 ] = args;
    return ret;
  }

  public FieldDeclaration createParameterField( Param p ) {
    String args[] = convertToEventParameterTypeAndConstructorArgs( p );
    // return createFieldOfGenericType( p.name, type, p.type, args );
    return createFieldOfGenericType( p.name, args[ 0 ], args[ 1 ], args[ 2 ] );
  }

  public FieldDeclaration createParameterField( Param p,
                                                MethodDeclaration initMembers ) {
    if ( initMembers == null ) {
      return createParameterField( p );
    }
    String args[] = convertToEventParameterTypeAndConstructorArgs( p );
    Statement s =
        createAssignmentOfGenericType( p.name, args[ 0 ], args[ 1 ], args[ 2 ] );
    ASTHelper.addStmt( initMembers.getBody(), s );
    return createFieldOfGenericType( p.name, args[ 0 ], args[ 1 ], null );
  }

  public void addParameterField( TypeDeclaration typeDecl, Param p ) {
    FieldDeclaration f = createParameterField( p );
    ASTHelper.addMember( typeDecl, f );
  }

  private static Integer toInteger( String s ) {
    Integer i = null;
    try {
      i = Integer.parseInt( s );
    } catch ( NumberFormatException e ) {
      // leave i = null
    }
    return i;
  }

  private String operatorResultType( BinaryExpr.Operator o, String argType1,
                                     String argType2 ) {
    switch ( o ) {
      case or: // ||
      case and: // &&
      case equals: // ==
      case notEquals: // !=
      case less: // <
      case greater: // >
      case lessEquals: // <=
      case greaterEquals: // >=
        return "Boolean";
      case binOr: // |
      case binAnd: // &
      case xor: // ^
      case lShift: // <<
      case rSignedShift: // >>
      case rUnsignedShift: // >>>
      case plus: // +
      case minus: // -
      case times: // *
      case divide: // /
      case remainder: // %
      default:
        return dominantType( argType1, argType2 );
    }
  }

  private static String dominantType( String argType1, String argType2 ) {
    if ( argType1.equals( "String" ) ) return argType1;
    if ( argType2.equals( "String" ) ) return argType2;
    if ( argType1.toLowerCase().equals( "double" ) ) return argType1;
    if ( argType2.toLowerCase().equals( "double" ) ) return argType2;
    if ( argType1.toLowerCase().startsWith( "long" ) ) return argType1;
    if ( argType2.toLowerCase().startsWith( "long" ) ) return argType2;
    if ( argType1.toLowerCase().startsWith( "int" ) ) return argType1;
    if ( argType2.toLowerCase().startsWith( "int" ) ) return argType2;
    return argType1;
  }

  // TODO -- should probably import and do a switch on all classes in
  // japa.parser.ast.expr.*
  public String javaparserToAeExpressionType( Expression expr ) {
    Param p;
    String name = null;
    String result = null;
    String className = expr.getClass().getSimpleName();
    // Inefficient string compare.
    switch ( className ) {
      case "ConditionalExpr":
        ConditionalExpr ce = ( (ConditionalExpr)expr );

        result =
            dominantType( javaparserToAeExpressionType( ce.getThenExpr() ),
                          javaparserToAeExpressionType( ce.getElseExpr() ) );
        break;
      case "BinaryExpr":
        BinaryExpr be = ( (BinaryExpr)expr );
        result =
            operatorResultType( be.getOperator(),
                                javaparserToAeExpressionType( be.getLeft() ),
                                javaparserToAeExpressionType( be.getRight() ) );
        break;
      case "UnaryExpr":
        UnaryExpr ue = ( (UnaryExpr)expr );
        result =
            operatorResultType( ue.getOperator(),
                                javaparserToAeExpressionType( ue.getExpr() ) );
        break;
      case "EnclosedExpr":
        result = javaparserToAeExpressionType( ( (EnclosedExpr)expr ).getInner() );
        break;
      case "NameExpr":
        name = ( (NameExpr)expr ).getName();
        break;
      default:
        if ( className.endsWith( "LiteralExpr" ) ) {
          String typeOfLiteral =
              className.substring( 0, className.length() - 11 );
          if ( typeOfLiteral.equals( "Null" ) ) {
            result = "null"; // BAD!
          } else {
            result = typeOfLiteral;
          }
          break;
        } else if ( className.contains( "Literal" ) ) {
          result = className.substring( 0, className.indexOf( "Literal" ) );
        }
        name = expr.toString();
    }
    if ( result == null ) {
      if ( name != null && name.startsWith( "\"" ) ) result = "String";
      p = lookupCurrentClassMember( name );
      result = ( p == null ) ? null : p.type;
    }
    Debug.outln( "javaToEventExpressionType(" + expr + ") = " + result );
    return result;
  }

  private Param lookupCurrentClassMember( String name ) {
    return lookupMemberByName( currentClass, name );
  }

  private String
      operatorResultType( UnaryExpr.Operator operator, String argType ) {
    return argType;
  }

  
  public String javaparserToAeExpression( Expression expr, String type,
                                          boolean convertFcnCallArgsToExprs ) {
    if ( type == null || type.isEmpty() || type.equals( "null" ) ) {
      type = javaparserToAeExpressionType( expr );
    }
    final String prefix =
        "new Expression" + ( type == null ? "" : "<" + type + ">" ) + "( ";
    final String suffix = " )";
    String middle = null;
    // Inefficient string compare.
    switch ( expr.getClass().getSimpleName() ) {
      case "BinaryExpr":
        BinaryExpr be = ( (BinaryExpr)expr );
        // middle =
        return "new Functions."
               + javaBinaryOpToEventFunctionName( be.getOperator() ) + "( "
               + javaparserToAeExpression( be.getLeft(), 
                                           convertFcnCallArgsToExprs ) + ", "
               + javaparserToAeExpression( be.getRight(), 
                                           convertFcnCallArgsToExprs ) + " )";
        // break;
      case "UnaryExpr":
        UnaryExpr ue = ( (UnaryExpr)expr );
        // middle =
        return "new Functions."
               + javaUnaryOpToEventFunctionName( ue.getOperator() ) + "( "
               + javaparserToAeExpression( ue.getExpr(), type,
                                           convertFcnCallArgsToExprs ) + " )";
        // break; 
      case "EnclosedExpr":
        middle =
            javaparserToAeExpression( ( (EnclosedExpr)expr ).getInner(), type,
                                      convertFcnCallArgsToExprs );
        break;
      case "NameExpr":
        middle = ( (NameExpr)expr ).getName();
        break;
      case "AssignExpr":
        AssignExpr ae = (AssignExpr)expr;
        String result = null;
        if ( ae.getOperator() == AssignExpr.Operator.assign ) {
          result =
              ae.getTarget().toString() + ".setValue( "
                  + javaparserToAeExpression( ae.getValue(), 
                                              convertFcnCallArgsToExprs ) + " )";
          return result;
        }
        BinaryExpr abe = new BinaryExpr();
        abe.setLeft( ae.getTarget() );
        abe.setRight( ae.getValue() );
        abe.setOperator( assignOpToBinaryOp( ae.getOperator() ) );
        Param p = lookupCurrentClassMember( ae.getTarget().toString() );
        if ( p != null ) {
          Assert.assertNotNull( abe.getOperator() );
          result =
              ae.getTarget().toString() + ".setValue( "
                  + javaparserToAeExpression( abe, 
                                              convertFcnCallArgsToExprs ) + " )";
          return result;
        }
        middle = ae.toString();
        break;
      case "MethodCallExpr":
        MethodCallExpr mce = (MethodCallExpr)expr;
        JavaForFunctionCall javaForFunctionCall =
            new JavaForFunctionCall( this, mce, convertFcnCallArgsToExprs );
//        String callName = mce.getName();
//        String className = currentClass;
//        Expression scope = mce.getScope();
//        String object = getObjectFromScope( scope );
//        String pkg = packageName + ".";
//        if ( pkg.length() == 1 ) {
//          pkg = "";
//        }
//        Set< MethodDeclaration > classMethods =
//            getClassMethodsWithName( callName, className );
//        StringBuffer methodArgs = new StringBuffer();
//        methodArgs.append( "  new Expression" + "( new FunctionCall( (Object)"
//                           + object + ", Class.forName(\"" + pkg + className
//                           + "\").getMethod(\"" + callName + "\"" );
//        if ( !classMethods.isEmpty() ) {
//          if ( classMethods.size() > 1 ) {
//            System.err.println( "Warning! " + classMethods.size()
//                                + " methods with name " + callName + " in "
//                                + className );
//          }
//          // Warning just grabs the first method of this name!
//          MethodDeclaration methodDecl = classMethods.iterator().next();
//          assert( methodDecl != null );
//          for ( japa.parser.ast.body.Parameter parameter :
//                methodDecl.getParameters() ) {
//              methodArgs.append( ", " );
////            }
//            methodArgs.append( parameter.getType().toString() + ".class" ); 
//          }
//        }
////        result =
//////            "try{\n"
////               "  new Expression" + "( new FunctionCall( (Object)" + object
////               + ", Class.forName(\"" + className + "\").getMethod(\""
////               + callName + "\", "
////               + methodArgs.toString()
//        methodArgs.append( " ), new Object[]{ " );
////                + "} catch ( NoSuchMethodException e ) {\n"
////                + "  // TODO Auto-generated catch block\n"
////                + "  e.printStackTrace();\n"
////                + "} catch ( SecurityException e ) {\n"
////                + "  // TODO Auto-generated catch block\n"
////                + "  e.printStackTrace();\n"
////                + "} catch ( ClassNotFoundException e ) {\n"
////                + "  // TODO Auto-generated catch block\n"
////                + "  e.printStackTrace();\n" 
////                + "}\n";
//
//        boolean first = true;
//        for ( Expression a : mce.getArgs() ) {
//          if ( first ) {
//            first = false;
//          } else {
//            methodArgs.append( ", " );
//          }
//          // REVIEW -- We might not want to convert args to Expressions for some
//          // function calls. This assumes that all method declarations are
//          // converted to take Expressions.  Does this depend on the scope?
//          methodArgs.append( javaToEventExpression( a, convertFcnCallArgsToExprs ) );
//        }
//        methodArgs.append( " } ) )" );
//        result = methodArgs.toString();
//        return result;
        return javaForFunctionCall.toNewExpressionString();
      case "ConditionalExpr": // TODO
      default:
        middle = expr.toString();
    }
    if ( !convertFcnCallArgsToExprs ) {
      return middle;
    }
    return prefix + middle + suffix;
  }

  public String getObjectFromScope( Expression scopeExpr ) {
    String object = null;
    if ( scopeExpr != null) {
      object = javaparserToAeExpression( scopeExpr, false );
    }
    if ( object == null ) object = "null";
    return object;
  }

  public static BinaryExpr.Operator assignOpToBinaryOp( AssignExpr.Operator ao ) {
    // REVIEW -- pull this out and make it a static array;
    BinaryExpr.Operator bo = null;
    switch ( ao ) {
      case assign:
        System.err.println( "Error! Trying to translate an assignment with the '=' operator to a binary operator." );
        break; // leave null
      case plus:
        bo = BinaryExpr.Operator.plus;
        break;
      case minus:
        bo = BinaryExpr.Operator.minus;
        break;
      case star:
        bo = BinaryExpr.Operator.times;
        break;
      case slash:
        bo = BinaryExpr.Operator.divide;
        break;
      case and:
        bo = BinaryExpr.Operator.and;
        break;
      case or:
        bo = BinaryExpr.Operator.or;
        break;
      case xor:
        bo = BinaryExpr.Operator.xor;
        break;
      case rem:
        bo = BinaryExpr.Operator.remainder;
        break;
      case lShift:
        bo = BinaryExpr.Operator.lShift;
        break;
      case rSignedShift:
        bo = BinaryExpr.Operator.rSignedShift;
        break;
      case rUnsignedShift:
        bo = BinaryExpr.Operator.rUnsignedShift;
        break;
      default:
        System.err.println( "Error! Trying to translate an unknow assignment operator "
                            + ao + " to a binary operator." );
    }
    return bo;
  }

  public String javaparserToAeExpression( Expression expr,
                                          boolean convertFcnCallArgsToExprs ) {
    return javaparserToAeExpression( expr, null, convertFcnCallArgsToExprs );
  }

  private static String
      javaUnaryOpToEventFunctionName( UnaryExpr.Operator operator ) {
    return "" + Character.toUpperCase( operator.toString().charAt( 0 ) )
           + operator.toString().substring( 1 );
  }

  // \([A-Za-z]*\), //\(.*\)
  // case \1: // \2
  private static String
      javaBinaryOpToEventFunctionName( BinaryExpr.Operator operator ) {
    return "" + Character.toUpperCase( operator.toString().charAt( 0 ) )
           + operator.toString().substring( 1 );
    /*
     * switch ( operator ) { case or: // || return "Or"; case and: // && return
     * "And"; case binOr: // | return "binOr"; case binAnd: // & return
     * "binAnd"; case xor: // ^ return "xor"; case equals: // == return
     * "equals"; case notEquals: // != return "notEquals"; case less: // <
     * return "less"; case greater: // > return "greater"; case lessEquals: //
     * <= return "lessEquals"; case greaterEquals: // >= return "greaterEquals";
     * case lShift: // << return "lShift"; case rSignedShift: // >> return
     * "rSignedShift"; case rUnsignedShift: // >>> return "rUnsignedShift"; case
     * plus: // + return "plus"; case minus: // - return "minus"; case times: //
     * * return "times"; case divide: // / return "divide"; case remainder: // %
     * return "remainder"; } return null;
     */
  }

  public String javaToEventExpression( String exprString, String type, 
                                       boolean convertFcnCallArgsToExprs ) {
    Debug.outln( "trying to parse Java expression\"" + exprString + "\"" );
    ASTParser parser = new ASTParser( new StringReader( exprString ) );
    Expression expr = null;
    try {
      expr = parser.Expression();
    } catch ( ParseException e ) {
      e.printStackTrace();
      return "new Expression<String>( \"" + exprString + "\" )";
    }
    return javaparserToAeExpression( expr, type, 
                                  convertFcnCallArgsToExprs );
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
      constructorArgs = javaToEventExpression( expression, "Boolean", true );
      constraintType = "ConstraintExpression";
    } else {
      constructorArgs = javaToEventExpression( expression, "Boolean", true )
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
      constructorArgs = javaToEventExpression( expression, "Boolean", true );
      // constructorArgs = "new Expression<Boolean>( \"" + expression +
      // "\", \"Java\" )";
      constraintType = "ConstraintExpression";
    } else {
      constructorArgs = javaToEventExpression( expression, "Boolean", true )
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

  public FieldDeclaration createDependencyField( Param p ) {
    String name = p.name + "Dependency";
    // TODO -- REVIEW -- Aren't we going to need to parse the expressions?
    String constructorArgs =
        // p.name + ", new Expression<" + p.type + ">( \"" + p.value
        // + "\", \"Java\" )";
        p.name + ", " + javaToEventExpression( p.value, p.type, true );
    return createFieldOfGenericType( name, "Dependency", p.type,
                                     constructorArgs );
  }

  public FieldDeclaration createDependencyField( Param p,
                                                 MethodDeclaration initMembers ) {
    if ( initMembers == null ) {
      return createDependencyField( p );
    }
    String name = p.name + "Dependency";
    // TODO -- REVIEW -- Aren't we going to need to parse the expressions?
    String constructorArgs =
    // p.name + ", new Expression<" + p.type + ">( \"" + p.value
    // + "\", \"Java\" )";
        p.name + ", " + javaToEventExpression( p.value, p.type, true );
    Statement s =
        createAssignmentOfGenericType( name, "Dependency", p.type,
                                       constructorArgs );
    ASTHelper.addStmt( initMembers.getBody(), s );
    return createFieldOfGenericType( name, "Dependency", p.type, null );
  }

  public Pair< String, FieldDeclaration > createEffectField( Node effectNode,
                                                             MethodDeclaration initMembers ) {
    if ( effectNode == null ) return null;
    ClassOrInterfaceType fieldType =
        new ClassOrInterfaceType( "Effect" );
    FieldDeclaration f = null;
    String effectText = effectNode.getTextContent();

    // parse the effect text as a MethodCallExpr
    Debug.outln( "trying to parse effect as Java expression\"" + effectText
                        + "\"" );
    ASTParser parser = new ASTParser( new StringReader( effectText ) );
    Expression expr = null;
    try {
      expr = parser.Expression();
    } catch ( ParseException e ) {
      e.printStackTrace();
      return null;
    }

    // If a method call, break it down into its parts.
    // String exprClassName = expr.getClass().getSimpleName();
    if ( expr instanceof MethodCallExpr ) { // exprClassName.equals(
                                            // "MethodCallExpr" ) ) {
      MethodCallExpr mcExpr = (MethodCallExpr)expr;

      JavaForFunctionCall jffc = new JavaForFunctionCall( this, mcExpr, false );
      
  /*    
      
      // Identify the object from which the method is called.
      Expression scopeExpr = mcExpr.getScope();
      // TODO -- REVIEW -- By calling getValue(), this assumes that the effect
      // is on a variable packaged in a Parameter.
      String object = (scopeExpr == null) ? "null"
                      : scopeExpr.toString() + ".getValue()"; //getObjectFromScope( scopeExpr );

      //  Get the Method from the Class of the object
      String pkg = packageName + ".";
      if ( pkg.length() == 1 ) {
        pkg = "";
      }
      String callName = mcExpr.getName();
      String className = currentClass;

//      Parameter< TimeVarying< Integer >> p = new Parameter<TimeVarying<Integer>>("foo", null, null);
//      TypeVariable< ? >[] foo = p.getClass().getTypeParameters();
//      foo[0].getClass();
      StringBuffer parameterTypes = new StringBuffer();
      //Class<?> parameterTypes[] = null;
      if ( mcExpr.getArgs() == null ) {
        //parameterTypes = new Class<?>[0];
      } else {
        //parameterTypes = new Class<?>[mcExpr.getArgs().size()];
        //for ( int i=0; i < mcExpr.getArgs().size(); ++i ) {
        for ( Expression arg  : mcExpr.getArgs() ) {
          //Expression arg = mcExpr.getArgs().get(i);
          if ( arg == null ) {
            // TODO -- We need to handle null case by looking
            // up a method with parameters corresponding to
            // args even if null!
            //parameterTypes[i] = null;
            parameterTypes.append(", null");
          } else {
            Object argDecl;
            //parameterTypes[i] = arg.getClass();
            //parameterTypes.append( ", " + argDecl.getClass().toString() );
          }
        }
      }
      String classOfMethod =  ( object.equals( "null" ) ?
                                "Class.forName(\"" + pkg + className + "\")" :
                                object + ".getClass()" );
      String effectMethod =
          classOfMethod
              + ".getMethod(\"" + callName
              + ( parameterTypes.toString().isEmpty() ? ""
                  : ", " + parameterTypes.toString() ) + "\")";

      Set< MethodDeclaration > classMethods =
          getClassMethodsWithName( callName, className );
*/
      int myNum = counter++;
      String effectName = "effect" + myNum;

      StringBuffer stmtString = new StringBuffer();
      //stmtString.append( scopeExpr + ".setValue( new )" );      
      stmtString.append( effectName + " = new EffectFunction( " + jffc.toNewFunctionCallString()
                         + " );" );
/*                         "" */
/*                         + object + ", " + effectMethod + " );" ); */
/*                         + jffc.objectName + ", " + jffc.methodJava + " );" ); */
      
      addStatements( initMembers.getBody(), stmtString.toString() );

      VariableDeclaratorId id = new VariableDeclaratorId( effectName );
      // Expression init = new NameExpr( "new ElaborationRule()" );
      Expression init = new NameExpr( "null" );
      VariableDeclarator variable = new VariableDeclarator( id, init );
      f =
          ASTHelper.createFieldDeclaration( ModifierSet.PUBLIC, fieldType,
                                            variable );
/*      return new Pair< String, FieldDeclaration >( scopeExpr.toString(), f ); */
      return new Pair< String, FieldDeclaration >( jffc.objectName, f );
    } else {
      assert false; // TODO -- REVIEW -- Can it be something else?
    }
    return null; //new Pair( scopeExpr.toString(), f );
  }

  public FieldDeclaration
      createElaborationField( String name, String eventType,
                              String eventName, List< Param > arguments,
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

    String invocationName = "invocation" + myNum;
    String conditionName = "condition" + myNum;
    String argumentsName = "arguments" + myNum;

    StringBuffer stmtsString = new StringBuffer();
    stmtsString.append( "Expression<?>[] " + argumentsName
                        + " = new Expression<?>[" + arguments.size() + "];\n" );
    // TODO -- Need to make sure this constructor exists!
    // TODO -- Should we have a cleanup after reading XML to tie/check
    // references or wait for javac to fail?
    for ( int i = 0; i < arguments.size(); ++i ) {
      Param p = arguments.get( i );
      // TODO -- Look up parameter types from class definition. Already doing
      // multi-pass parse for constructors, so make another pass to create a
      // class->fieldName->type map.
      /*
       * DO NOT ERASE THIS String params = "<" + p.type + ">"; if ( p.type ==
       * null || p.type.trim().equals( "null" ) ) { params = ""; } else { if (
       * !p.type.equals( "String" ) && p.value.startsWith( "\"" ) ) { p.value +=
       * ", \"Java\""; } } stmtsString.append( argumentsName + "[" + i +
       * "] = new Expression" + params + "( " + p.value + " );\n" );
       */

      String type = p.type;
      if ( p.type == null || p.type.isEmpty()
           || p.type.trim().equals( "null" ) ) {
        System.err.println( "Error! cannot create elaboration invocation "
                            + "argument for " + p );
        type = null;
      } else {
        stmtsString.append( argumentsName + "[" + i + "] = "
                            + javaToEventExpression( p.value, type, 
                                                     true ) + ";\n" );
      }
    }

    // TODO -- need to parse Expression from String
    // stmtsString.append( "Expression< Boolean > " + conditionName +
    // " = new Expression< Boolean >( \"" + conditionExpression +
    // "\", \"Java\" );\n" );
    stmtsString.append( "Expression< Boolean > "
                        + conditionName
                        + " = "
                        + javaToEventExpression( conditionExpression, "Boolean", 
                                                 true )
                        + ";\n" );

    stmtsString.append( name + " = addElaborationRule( " + conditionName + ", "
                        + eventType + ".class, " + eventName + ", "
                        + argumentsName + " );\n" );

    addStatements( initMembers.getBody(), stmtsString.toString() );

    // stmtsString.append( "public ElaborationRule " + name
    // + " = null;\n" );
    VariableDeclaratorId id = new VariableDeclaratorId( name );
    // Expression init = new NameExpr( "new ElaborationRule()" );
    Expression init = new NameExpr( "null" );
    VariableDeclarator variable = new VariableDeclarator( id, init );
    f =
        ASTHelper.createFieldDeclaration( ModifierSet.PUBLIC, fieldType,
                                          variable );
    return f;
  }

  // public static void addElaborationField( TypeDeclaration typeDecl,
  // String name, String eventName,
  // List< Param > arguments,
  // String conditionExpression,
  // String applicableStartTime,
  // String applicableEndTime ) {
  // FieldDeclaration f =
  // createElaborationField( name, eventName, arguments,
  // conditionExpression, applicableStartTime,
  // applicableEndTime );
  // ASTHelper.addMember( typeDecl, f );
  // }

  // REVIEW -- Do we really need to create fields for these? Just need
  // to load parameters from constructor; and not redefine loadPrameters().
  // private ArrayList< Parameter< ? > > getParameters( Node node ) {
  // ArrayList< Parameter< ? > > parameters = new ArrayList< Parameter< ? > >();
  public ArrayList< FieldDeclaration >
      getParameters( Node node, MethodDeclaration initMembers ) {
    ArrayList< FieldDeclaration > parameters =
        new ArrayList< FieldDeclaration >();
    if ( node != null ) {
      List< Node > nodeList = XmlUtils.getChildNodes( node, "parameter" );
      // NodeList nodeList = node.XmlUtils.getChildNodes();
      for ( int i = 0; i < nodeList.size(); i++ ) {
        Node childNode = nodeList.get( i );
        Param p = new Param( childNode );
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
      String name = XmlUtils.getChildElementText( childNode, "name" );
      String expression = XmlUtils.getChildElementText( childNode, "expression" );
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
      getDependencies( Node node, MethodDeclaration initMembers ) {
    ArrayList< FieldDeclaration > dependencies =
        new ArrayList< FieldDeclaration >();
    List< Node > nodeList = XmlUtils.getChildNodes( node, "dependency" );
    for ( int i = 0; i < nodeList.size(); i++ ) {
      Node childNode = nodeList.get( i );
      Param depParam = new Param( childNode );
      if ( depParam.name.equals( "startTime" ) ) {
        gotStartTimeDependency = true;
      } else  if ( depParam.name.equals( "endTime" ) ) {
        gotEndTimeDependency = true;
      } else if ( depParam.name.equals( "duration" ) ) {
        gotDurationDependency = true;
      }
      FieldDeclaration f =
          createDependencyField( depParam, initMembers );
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
      Pair< String, FieldDeclaration > p =
          createEffectField( childNode, initMembers );
      if ( p != null && p.second != null ) {
        effects.add( p );
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
        String eventType = XmlUtils.getChildElementText( invocationNode, "eventType" );
        String eventName = XmlUtils.getChildElementText( invocationNode, "eventName" );
        Node argumentsNode = XmlUtils.getChildNode( invocationNode, "arguments" );
        List< Param > arguments = new ArrayList< Param >();
        if ( argumentsNode != null ) {
          List< Node > argNodeList = XmlUtils.getChildNodes( argumentsNode, "parameter" );
          for ( int j = 0; j < argNodeList.size(); j++ ) {
            Node argNode = argNodeList.get( j );
            arguments.add( new Param( argNode ) );
          }
        }
        String expression = "true"; // default
        String applicableStartTime = "-1"; // default
        String applicableEndTime = "-1"; // default
        if ( conditionNode != null ) {
          expression = XmlUtils.getChildElementText( conditionNode, "expression" );
          Node timePeriodNode = XmlUtils.getChildNode( conditionNode, "timePeriod" );
          if ( timePeriodNode != null ) {
            applicableStartTime = XmlUtils.getChildElementText( timePeriodNode, "start" );
            applicableEndTime = XmlUtils.getChildElementText( timePeriodNode, "end" );
          }
        }
        FieldDeclaration f =
            createElaborationField( null, eventType, eventName, arguments,
                                    expression, applicableStartTime,
                                    applicableEndTime, initMembers );
        if ( f != null ) {
          elaborations.add( f );
        }
      }
    }
    return elaborations;
  }

  protected static void
  buildMethodTable( Document doc,
                    Map< String, 
                         Map< String, 
                              Set< MethodDeclaration > > > methodTable ) {
    List< Node > classNodes = XmlUtils.findNodes( doc, "class" );
    for ( Node classNode : classNodes ) {
      String className = XmlUtils.getChildElementText( classNode, "name" );
      
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
          methodSet = new HashSet<MethodDeclaration>();
          classMethods.put( methodDecl.getName(), methodSet );
        }
        methodSet.add( methodDecl );
      }
    }
  }
  
  public static final Set< MethodDeclaration > emptyMethodDeclarationSet =
      new HashSet< MethodDeclaration >();

  public Set< MethodDeclaration > getMethodsForClass( String className ) {
    Map< String, Set< MethodDeclaration > > classMethods =
        methodTable.get( className );
    if ( classMethods == null ) return emptyMethodDeclarationSet;
    Set< MethodDeclaration > methodsForClass = new HashSet< MethodDeclaration >();
    for ( Set< MethodDeclaration > methodsByName : classMethods.values() ) {
      methodsForClass.addAll( methodsByName );
    }
    return methodsForClass;
  }

  public Set< MethodDeclaration > getMethodsWithName( String methodName ) {
    return getClassMethodsWithName( methodName, currentClass );
  }

  public Set< MethodDeclaration > getClassMethodsWithName( String methodName,
                                                           String className ) {
    Map< String, Set< MethodDeclaration > > classMethods =
        methodTable.get( className );
    if ( classMethods == null ) {
      return emptyMethodDeclarationSet;
    }
    Set< MethodDeclaration > methodSet = classMethods.get( methodName );
    if ( methodSet == null ) return emptyMethodDeclarationSet;
    return methodSet;
  }

  public static MethodDeclaration parseMethodDeclaration( String methodText ) {
    Debug.outln( "About to parse \"" + methodText + "\"");
    ASTParser parser = new ASTParser( new StringReader( methodText ) );
    try {
      BodyDeclaration md = parser.ClassOrInterfaceBodyDeclaration( false );
      return (MethodDeclaration)md;
    } catch ( ParseException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  // TODO -- If non-static and members are referenced inside, we will need to go
  // in and call param.get/setValue(). Need to do this inside expressions
  // elsewhere (javaToEventExpression()) , too.
  public static Collection< MethodDeclaration > getMethods( Node node ) {
    ArrayList< MethodDeclaration > methodDeclarations =
        new ArrayList< MethodDeclaration >();
    List< Node > mNodeList = XmlUtils.findNodes( node, "function" );
    for ( Node mNode : mNodeList ) {
      String methodString = mNode.getTextContent();
      MethodDeclaration methodDecl = parseMethodDeclaration( methodString );
      if ( methodDecl != null ) {
        if ( !ModifierSet.isPrivate( methodDecl.getModifiers() )
             && !ModifierSet.isProtected( methodDecl.getModifiers() ) ) {
          methodDecl.setModifiers( ModifierSet.addModifier( methodDecl.getModifiers(),
                                                            ModifierSet.PUBLIC ) );
          methodDecl.setModifiers( ModifierSet.addModifier( methodDecl.getModifiers(),
                                                            ModifierSet.STATIC ) );
        }
        methodDeclarations.add( methodDecl );
      }
    }
    return methodDeclarations;
    // NodeList nodeList = node.XmlUtils.getChildNodes();
    // for ( int i = 0; i < nodeList.getLength(); i++ ) {
    // Node childNode = nodeList.item( i );
    // String name = XmlUtils.getChildElementText( childNode, "name" );
    // String typeName = XmlUtils.getChildElementText( childNode, "type" );
    // String value = XmlUtils.getChildElementText( childNode, "value" );
    // MethodDeclaration m =
    // createMethodDeclaration( name, typeName, value );
    // methodDeclarations.add( m );
    // }
    // return methodDeclarations;
    // String s;
    //
    // // TODO Auto-generated method stub
    // return null;
  }

  public void writeJavaFile( String fileName ) throws IOException {
    File f = new File( fileName );
    FileWriter w = new FileWriter( f );
    w.write( currentCompilationUnit.toString() );
    w.close();
  }

  public void writeJavaFiles( String javaPath ) throws IOException {
    for ( Entry< String, CompilationUnit > e : classes.entrySet() ) {
      currentClass = e.getKey();
      currentCompilationUnit = e.getValue();
      String fileName =
          ( javaPath.trim() + File.separator + e.getKey() + ".java" );// .replaceAll(
                                                                      // File.separator
                                                                      // +
                                                                      // File.separator
                                                                      // + "+",
                                                                      // File.separator
                                                                      // );
      writeJavaFile( fileName );
    }
  }

  /**
   * @return the compilationUnit
   */
  public CompilationUnit getCurrentCompilationUnit() {
    return currentCompilationUnit;
  }

  /**
   * @return the currentClass
   */
  public String getCurrentClass() {
    return currentClass;
  }

  /**
   * @param currentClass
   *          the currentClass to set
   */
  public void setCurrentClass( String currentClass ) {
    this.currentClass = currentClass;
    if ( classes.containsKey( currentClass ) ) {
      currentCompilationUnit = classes.get( currentClass );
    }
  }
}
