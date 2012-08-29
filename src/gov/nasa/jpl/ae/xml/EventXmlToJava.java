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
import japa.parser.ast.expr.ConditionalExpr;
import japa.parser.ast.expr.EnclosedExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.UnaryExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ExplicitConstructorInvocationStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.Type;
import japa.parser.ast.type.VoidType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;
// import javax.xml.xpath.XPathExpression;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import gov.nasa.jpl.ae.event.DurativeEvent;
import gov.nasa.jpl.ae.event.Pair;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Utils;

/*
 * Translates XML to executable Java classes for Analysis Engine behavior (based
 * on events such as those of the Timeline Ontology).
 */
public class EventXmlToJava {
  
  // not using this yet
  public static final String charactersAssumedNotInIdentifiers = "<>+*/.";
  
  // A struct for packaging name, types, and values, which are used for many
  // purposes in the XML: parameters, args, dependencies, . . .
  public class Param {
    public String name;
    public String type;
    public String value;

    public Param( String name, String type, String value ) {
      this.name = name;
      this.type = type;
      this.value = value;
    }

    public Param( Node n ) {
      name = fixName( XmlUtils.getChildElementText( n, "name" ) );
      type = fixName( XmlUtils.getChildElementText( n, "type" ) );
      value = fixValue( XmlUtils.getChildElementText( n, "value" ) );
    }

    public String toString() {
      return "(" + name + ", " + type + ", " + value + ")";
    }
  }

  // Used for generating unique variable names where unspecified.
  protected static int counter = 0;

  // The source XML document.
  protected Document xmlDocDOM = null;

  // The long name of the class currently being processed.
  protected String currentClass = null;

  // The javaparser.CompilationUnit for the class currently being processed.
  protected CompilationUnit currentCompilationUnit = null;
  
  // Map: simpleName -> javaparser.CompilationUnit
  protected Map< String, CompilationUnit > classes =
      new TreeMap< String, CompilationUnit >();

  // Map: longName -> parameter name -> Param
  protected Map< String, Map< String, Param > > paramTable =
      new TreeMap< String, Map< String, Param > >();

  // Map: longName -> method name -> set of javaparser.MethodDeclarations
  protected Map< String, Map< String, Set< MethodDeclaration > > > methodTable =
      new TreeMap< String, Map< String, Set< MethodDeclaration > > >();
  
  // The package for generated Java files. This is where Java files will be
  // written in {PROJECT}/src.
  protected String packageName = "generated";
  
  // This is for handling class names outside Java syntax.
  protected NameTranslator nameTranslator = new NameTranslator();

  // These are for removing default dependencies that are overridden.
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
      Debug.outln( "Warning! XML file "
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
    processClassDeclarations( scenarioNode, null, "classes", false );//, false );

    // process events
    processClassDeclarations( scenarioNode, null, "events", false );//, false );
//    nodeList = xmlDocDOM.getElementsByTagName( "events" );
//    Assert.assertTrue( nodeList.getLength() < 2 );
//    if ( nodeList.getLength() == 1 ) {
//      // nodeList = nodeList.item( 0 ).XmlUtils.getChildNodes();
//      List< Node > nList = XmlUtils.getChildNodes( nodeList.item( 0 ), "event" );
//      for ( int i = 0; i < nList.size(); i++ ) {
//        Node node = nList.get( i );
//        // for ( int i = 0; i < nodeList.getLength(); i++ ) {
//        // Node node = nodeList.item( i );
//        processEvent( node, false );
//      }
//    }

    // process event to be executed
    NodeList nodeList = xmlDocDOM.getElementsByTagName( "eventToBeExecuted" );
    Assert.assertTrue( nodeList.getLength() < 2 );
    if ( nodeList.getLength() == 1 ) {
      Node node = nodeList.item( 0 );
      processExecutionEvent( node );
    }

    // Add constructors for invocations.
    addConstructors();

  }

//  public void processClasses( Node scenarioNode, boolean asEvent, boolean isInner ) {
//    String classesOrEvents = ( asEvent ? "events" : "classes" );
//    List<Node> nodeList = XmlUtils.getChildNodes( scenarioNode, classesOrEvents );//xmlDocDOM.getElementsByTagName( "classes" );
//    Assert.assertTrue( nodeList.size() < 2 );
//    if ( nodeList.size() == 1 ) {
//      List< Node > nList = XmlUtils.getChildNodes( nodeList.get( 0 ), "class" );
//      for ( int i = 0; i < nList.size(); i++ ) {
//        Node node = nList.get( i );
//        processClass( node, isInner );
//      }
//    }
//  }

/*
*/
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
    if ( name == null ) return null;
    StringBuffer sb = new StringBuffer();
    Pattern pattern = Pattern.compile( "\\b" ); // \b = word boundary
    Matcher matcher = pattern.matcher( name );
    int pos = 0;
    boolean nextIsWord = false;
    //String lastText = "";
    while ( matcher.find() ) {
      String text = name.substring( pos, matcher.start() );
      //Debug.outln( "text = name.substring( " + pos + ", "
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
    Debug.outln("fixName(\"" + name + "\") = \"" + sb.toString() + "\"" );
    return sb.toString();
  }
  
  /**
   * Fix the string identifier, name so that it can be used as a Java identifier
   * or type name.
   * 
   * @param name
   *          The string to be fixed.
   * @return A translation of name into a valid Java identifier or type name.
   */
  protected String fixSimpleName( String name ) {
    //return name;
    if ( name == null ) return null;
    //String paramPart = Utils.parameterPartOfName( name );
    //String noParamName = Utils.noParameterName( name );
    //assert name.trim().equals( ( noParamName + paramPart ).trim() );
    String javaName = nameTranslator.translate( name, "xml", "java" );
    // TODO -- REVIEW -- Do we need to sweep though and get names first before values?
//    for ( Map.Entry e : classes.entrySet() ) {
//    }
    //Debug.outln("fixSimpleName(\"" + name + "\") = \"" + javaName + "\"" );
    return javaName;
  }

  /**
   * Fix all names in the string so that they can be used as Java identifiers
   * or type names.  This currently just calls fixName( value ) since fixName()
   * does not change number tokens in the string.
   * 
   * @param value
   *          The string to be fixed.
   * @return A translation of name into a valid Java identifier or type name.
   */
  protected String fixValue( String value ) {
    //return value;
    if ( value == null ) return null;
    //String javaValue = nameTranslator.substitute( value, "xml", "java" );
    String javaValue = fixName( value );
    //Debug.outln("fixName(\"" + value + "\") = \"" + javaValue + "\"" );
    return javaValue;
  }
  
  // Get the name of the class from the DOM node. If it is an inner class,
  // prepend the names of the enclosing classes for proper scope (but leaving
  // off the package name).
  public String getClassName( Node classNode ) {
    String name = "";
    while ( classNode != null ) {
      name =
          fixName( XmlUtils.getChildElementText( classNode, "name" ) ) + "."
                   + name;
      classNode =
          XmlUtils.getEnclosingNodeWithName( classNode.getParentNode(),
                                             "class" );
    }
    // remove '.' at end of string for first pass through loop above.
    name = name.substring( 0, name.length() - 1 );
    return name;
  }

  // Try to figure out the scope of the class name if an inner class, and return
  // the scoped class name.
  public String getClassNameWithScope( String className ) {
    if ( Utils.isNullOrEmpty( className ) ) return null;
    // Return input class name if in table.
    if ( paramTable.keySet().contains( className ) ) {
      // Note: this.paramTable is used because it is populated at the beginning.
      // this.methodTable could also be used.  this.classes cannot be used since
      // it only contains classes processed so far.
      return className;
    }
    // See if the class is an inner class of the current class
    String classNameWithScope = this.currentClass + "." + className;
    if ( paramTable.keySet().contains( classNameWithScope ) ) {
      return classNameWithScope;
    }
    // Loop through existing class names and find those that end with the input
    // name. Pick the one that seems to be "best" and print a warning if not
    // sure.
    String otherClassName = null;
    for ( String n : paramTable.keySet() ) {
      if ( n.endsWith( className ) ) { 
        if ( otherClassName != null && otherClassName.endsWith( className ) ) {
          if ( n.endsWith( classNameWithScope ) ) {
            if ( otherClassName.endsWith( classNameWithScope ) ) {
              if ( n.contains( classNameWithScope ) ) {
                if ( otherClassName.contains( classNameWithScope ) ) {
                  System.err.println( "Warning! Got more than one candidate class for "
                                      + className + ": " + otherClassName + ", "
                                      + n );
                  if ( n.length() < otherClassName.length() ) {
                    otherClassName = n;
                  }
                } else {
                  otherClassName = n;
                }
              }
            } else {
              otherClassName = n;
            }
          }
        } else {
          otherClassName = n;
        }
      }
    }
    return otherClassName;
  }

  protected Param lookupMemberByName( String className, String paramName ) {
    if ( className == null ) return null;
    if ( paramName == null ) return null;
    Map< String, Param > params = paramTable.get( className );
    if ( params == null ) {
      String classNameWithScope = getClassNameWithScope( className );
      if ( !Utils.errorOnNull( "Error! Could not find a class definition for " 
                               + className
                               + " when looking for memeber " + paramName + ".",
                               classNameWithScope ) ) {
        params = paramTable.get( classNameWithScope );
      }
    }
    if ( params == null ) return null;
    return params.get( paramName );
  }

  // For each class definition, gather all locally defined and inherited
  // parameters and store them in a table indexed by the class name.
  protected void
      buildParamTable( Document doc,
                       Map< String, Map< String, Param > > paramTable ) {
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
        String superClass = fixName( XmlUtils.getChildElementText( classNode, "inheritsFrom" ) );
        Node parentNode = classNode.getParentNode();
        boolean isEvent = parentNode.getLocalName().equals( "event" );
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
        if ( isEvent && ( superParams == null || superParams.isEmpty() ) ) {
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
          Param p = new Param( pNode );
          Param ep = params.get( p.name );
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

  // Add constructors for invocations.
  private void addConstructors() {
    Collection< ConstructorDeclaration > constructors =
        createConstructors( this.xmlDocDOM );
    constructors.addAll( getConstructorDeclarations( this.xmlDocDOM ) );
    for ( ConstructorDeclaration c : constructors ) {
      TypeDeclaration type = getTypeDeclaration( c.getName() );
      if ( type != null && c != null ) {
        ASTHelper.addMember( type, c );
      }
    }
  }

  // REVIEW -- TODO -- Wouldn't it be better to just have a Map<name, TypeDeclaration>?
  // Recursively look for a type declaration with the given name.
  protected TypeDeclaration getTypeDeclarationFrom( String name,
                                                    TypeDeclaration typeDecl ) {
    String simpleName = Utils.simpleName( name );
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
    String simpleName = Utils.simpleName( name );
    if ( name == null ) return null;
    CompilationUnit cu = this.classes.get( simpleName );
    if ( cu != null ) {
      for ( TypeDeclaration type : cu.getTypes() ) {
        if ( type.getName().equals( simpleName ) ) {
          return type;
        }
      }
    } else {
      for ( CompilationUnit c : this.classes.values() ) {
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
    currentCompilationUnit.setPackage( new PackageDeclaration( ASTHelper.createNameExpr( packageName ) ) );
  }

  protected ClassOrInterfaceDeclaration processEvent( Node eventNode,
                                                      boolean innerClass ) {
    Node classNode = XmlUtils.getChildNode( eventNode, "class" );
    ClassOrInterfaceDeclaration newClassDecl =
        processClassDeclaration( classNode, eventNode, innerClass );
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
    String className = fixName( XmlUtils.getChildElementText( invocationNode, "eventType" ) );
    String instanceName = fixName( XmlUtils.getChildElementText( invocationNode, "eventName" ) );
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

  protected Collection< ConstructorDeclaration > getConstructorDeclarations( Node top ) {
    Collection< ConstructorDeclaration > ctors =
        new ArrayList< ConstructorDeclaration >();
    List< Node > constructorsNodes = XmlUtils.findNodes( top, "constructors" );
    for ( Node constructorsNode : constructorsNodes ) {
      List< Node > mNodeList = XmlUtils.getChildNodes( constructorsNode, "function" );
      for ( Node mNode : mNodeList ) {
        String constructorString = fixValue( mNode.getTextContent() );
        ConstructorDeclaration constructorDecl = parseConstructorDeclaration( constructorString );
        if ( constructorDecl != null ) {
          if ( !ModifierSet.isPrivate( constructorDecl.getModifiers() )
               && !ModifierSet.isProtected( constructorDecl.getModifiers() ) ) {
            // TODO -- Let mods be specified in XML through attributes!
            constructorDecl.setModifiers( ModifierSet.addModifier( constructorDecl.getModifiers(),
                                                              ModifierSet.PUBLIC ) );
            constructorDecl.setModifiers( ModifierSet.addModifier( constructorDecl.getModifiers(),
                                                              ModifierSet.STATIC ) );
          }
          ctors.add( constructorDecl );
        }
      }
    }
    return ctors;
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
        String eventType = fixName( XmlUtils.getChildElementText( invocationNode,
                                                                  "eventType" ) );
        ConstructorDeclaration ctor =
            new ConstructorDeclaration( ModifierSet.PUBLIC,
                                        Utils.simpleName(eventType) );
        Debug.outln("ctor ctord as " + ctor.getName() );
        Node argumentsNode = XmlUtils.getChildNode( invocationNode, "arguments" );
        List< Param > arguments = new ArrayList< Param >();
        if ( argumentsNode != null ) {
          List< Node > argNodeList = XmlUtils.getChildNodes( argumentsNode,
                                                             "parameter" );
          for ( int j = 0; j < argNodeList.size(); j++ ) {
            Node argNode = argNodeList.get( j );
            arguments.add( new Param( argNode ) );
          }
          List< japa.parser.ast.body.Parameter > parameters =
              new ArrayList< japa.parser.ast.body.Parameter >();
          for ( Param p : arguments ) {
            if ( p.type == null ) {
              Param memberDecl = lookupMemberByName( eventType, p.name );
              if ( !Utils.errorOnNull( "Error! Can't find member " + p.name
                                           + " for event class " + eventType
                                           + "!",
                                       memberDecl ) ) {
                p.type = memberDecl.type;
              }
            }
            japa.parser.ast.body.Parameter param =
                ASTHelper.createParameter( new ClassOrInterfaceType( "Expression<"
                                                                         + p.type
                                                                         + ">" ),
                                           p.name );
            parameters.add( param );
          }
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
    stmtList.append( "init" + ctor.getName() + "Elaborations();\n" );
    Debug.outln( "adding statements to block: " + stmtList.toString() );
    addStatements( block, stmtList.toString() );
    ctor.setBlock( block );
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
    if ( effects != null ) {
      stmtList =  
          createEffectStmtsFromFieldCollection( "effects.put( ", effects, " );\n" );
    }
    addStmts( block, stmtList );

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

//  public ClassOrInterfaceDeclaration processClass( Node cls ) {
//    ClassOrInterfaceDeclaration newClassDecl =
//        processClassDeclaration( cls, false, true );
//    return newClassDecl;
//  }

  public ClassOrInterfaceDeclaration processClassDeclaration( Node clsNode,
                                                              Node eventNode,
                                                              boolean isInnerClass ) {
    gotStartTimeDependency = false;
    gotEndTimeDependency = false;
    gotDurationDependency = false;

    // Get class name.
    String name = getClassName( clsNode );
    if ( !isInnerClass ) { 
      currentCompilationUnit = initClassCompilationUnit( name );
    }
    ClassOrInterfaceDeclaration newClassDecl =
        new ClassOrInterfaceDeclaration( ModifierSet.PUBLIC, false, 
                                         Utils.simpleName(name) );

    // Get class inheritances as Java extends list.
    // TODO -- REVIEW -- Do we need to deal with (allow for) Java interfaces?
    // Can only extend one class!
    List< ClassOrInterfaceType > extendsList = getInheritsFrom( clsNode );
    if ( extendsList != null && !extendsList.isEmpty() ) {
      newClassDecl.setExtends( extendsList );
    }

    getImports( clsNode );
    
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

    if ( newClassDecl.getExtends() == null
         || newClassDecl.getExtends().isEmpty() ) {
      if ( newClassDecl.getExtends() == null ) {
        newClassDecl.setExtends( new ArrayList< ClassOrInterfaceType >() );
      }
      String superClass = ( eventNode != null ?
                            "DurativeEvent" : "ParameterListenerImpl" ); 
      newClassDecl.getExtends().add( new ClassOrInterfaceType( superClass ) );
    }

    // Need a method for initializing members and populating Event collections
    // (parameters, effects, etc.).
    MethodDeclaration initMembers =
        createPublicVoidMethod( "init" + newClassDecl.getName() + "Members" );
    MethodDeclaration initElaborations =
        createPublicVoidMethod( "init" + newClassDecl.getName() + "Elaborations" );

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
                         initMembers );
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
                                     constraints, dependencies, effects );
    // elaborations );

    // Add fields and methods to class declaration.
    for ( FieldDeclaration f : members ) {
      ASTHelper.addMember( newClassDecl, f );
    }
    ASTHelper.addMember( newClassDecl, initMembers );
    ASTHelper.addMember( newClassDecl, initCollections );
    ASTHelper.addMember( newClassDecl, initElaborations );

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
    // inner classes
    processClassDeclarations( clsNode, newClassDecl, "events", true );
    processClassDeclarations( clsNode, newClassDecl, "classes", true );
    
    if ( !isInnerClass ) {
      ASTHelper.addTypeDeclaration( currentCompilationUnit, newClassDecl );
    }
    return newClassDecl;
  }

  protected void getImports( Node clsNode ) {
    List< String > imports = XmlUtils.getChildrenElementText( clsNode,
                                                              "import" );
    for ( String imp : imports ) {
      addImport( imp );
    }
  }

  private void
      processClassDeclarations( Node node,
                                ClassOrInterfaceDeclaration newClassDecl,
                                String classesOrEvents, boolean isInner ) {
    ClassOrInterfaceDeclaration classDecl = null;
    if ( node != null ) {
      boolean isEvent = classesOrEvents.equals( "events" );
      String classOrEvent = ( isEvent ? "event" : "class" );
      //String classOrEvent = classesOrEvents.replaceFirst( "[e]?s$", "" );
      List< Node > nodeList = XmlUtils.getChildNodes( node, classesOrEvents );
      // NodeList nodeList = node.XmlUtils.getChildNodes();
      for ( Node childNode : nodeList ) {
        if ( node != null ) {
          List< Node > classNodes = XmlUtils.getChildNodes( childNode, classOrEvent );
          for ( Node classNode : classNodes ) {
            if ( isEvent ) {
              classDecl = processEvent( classNode, isInner );
            } else {
              classDecl = processClassDeclaration( classNode, null, isInner );
            }
            if ( isInner ) {
              ASTHelper.addMember( newClassDecl, classDecl );
            }
          }
        }
      }
    }
  }

  private CompilationUnit initCompilationUnit( String name ) {
    currentClass = name;
    currentCompilationUnit = new CompilationUnit();
    classes.put( Utils.simpleName(name), currentCompilationUnit );
    setPackage();
    return currentCompilationUnit;
  }
  
  private CompilationUnit initClassCompilationUnit( String name ) {
    currentCompilationUnit = initCompilationUnit( Utils.simpleName(name) );
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
    addImport( "gov.nasa.jpl.ae.event.ParameterListenerImpl" );
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

  public List< ClassOrInterfaceType > getInheritsFrom( Node cls ) {
    List< String > extendsStringList =
        XmlUtils.getChildrenElementText( cls, "inheritsFrom" );
    List< ClassOrInterfaceType > extendsList =
        new ArrayList< ClassOrInterfaceType >();
    for ( String e : extendsStringList ) {
      ClassOrInterfaceType c = new ClassOrInterfaceType( fixName( e ) );
      extendsList.add( c );
    }
    return extendsList;
  }
  
  // Returns input DOM node if it has a localName "class," the closest parent
  // "class" node, or null.
  public String getEnclosingClassName( Node node ) {
    Node classNode = XmlUtils.getEnclosingNodeWithName( node, "class" );
    return getClassName( classNode );
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
    stmtsString.append( name + " = " );
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
    String args = "\"" + p.name + "\", null, " + p.value + ", this";
    if ( Utils.isNullOrEmpty( p.type ) ) {
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
    if ( argType1 == null ) return argType2;
    if ( argType2 == null ) return argType1;
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
    String effectText = fixValue( effectNode.getTextContent() );

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
      createElaborationField( String name, String enclosingInstance,
                              String eventType,
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

      String type = p.type;
      if ( Utils.isNullOrEmpty( type ) ) {
        Param param = lookupMemberByName( eventType, p.name );
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
                            + javaToEventExpression( p.value, type, 
                                                     true ) + ";\n" );
      }
    }

    stmtsString.append( "Expression< Boolean > "
                        + conditionName
                        + " = "
                        + javaToEventExpression( conditionExpression, "Boolean", 
                                                 true )
                        + ";\n" );

    boolean needToMakeAParameter = false;
//    if ( !enclosingInstance.equals( "this" ) ) {
//      Param p = lookupMemberByName( currentClass, enclosingInstance );
//      if ( p == null ) {
//        System.err.println( "Couldn't find member " + enclosingInstance + " in "
//                            + currentClass );
//      } else {
//        enclosingInstance += ".getValue()";
//      }
//    }
    if ( enclosingInstance.equals( "this" ) ) {
      needToMakeAParameter = true;
    } else {
      Param p = lookupCurrentClassMember( enclosingInstance );
      if ( p == null ) {
        needToMakeAParameter = true;
      }
    }
    if ( needToMakeAParameter ) {
      enclosingInstance =
          "new Parameter<" + currentClass + ">( \"" + currentClass
              + "\", null, " + enclosingInstance + ", null )";
    }
    stmtsString.append( name + " = addElaborationRule( " + conditionName + ", "
                        + enclosingInstance + ", "
                        + getClassNameWithScope( eventType ) + ".class, "
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
      String name = fixName( XmlUtils.getChildElementText( childNode, "name" ) );
      String expression = fixValue( XmlUtils.getChildElementText( childNode, "expression" ) );
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
      if ( childNode == null ) continue;
      Param depParam = new Param( childNode );
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
        String enclosingInstance = fixName( XmlUtils.getChildElementText( invocationNode, "enclosingInstance" ) );
        if ( enclosingInstance == null ) enclosingInstance = "null";
        String eventType = fixName( XmlUtils.getChildElementText( invocationNode, "eventType" ) );
        String eventName = fixName( XmlUtils.getChildElementText( invocationNode, "eventName" ) );
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
          expression = fixValue( XmlUtils.getChildElementText( conditionNode, "expression" ) );
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
    Debug.outln( "About to parse \"" + ctorText + "\"");
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
  public Collection< MethodDeclaration > getMethods( Node node ) {
    ArrayList< MethodDeclaration > methodDeclarations =
        new ArrayList< MethodDeclaration >();
    Node methodsNode = XmlUtils.getChildNode( node, "methods" );
    List< Node > mNodeList = XmlUtils.getChildNodes( methodsNode, "function" );
    for ( Node mNode : mNodeList ) {
      String methodString = fixValue( mNode.getTextContent() );
      MethodDeclaration methodDecl = parseMethodDeclaration( methodString );
      if ( methodDecl != null ) {
        if ( !ModifierSet.isPrivate( methodDecl.getModifiers() )
             && !ModifierSet.isProtected( methodDecl.getModifiers() ) ) {
          // TODO -- Let mods be specified in XML through attributes!
          methodDecl.setModifiers( ModifierSet.addModifier( methodDecl.getModifiers(),
                                                            ModifierSet.PUBLIC ) );
          methodDecl.setModifiers( ModifierSet.addModifier( methodDecl.getModifiers(),
                                                            ModifierSet.STATIC ) );
        }
        methodDeclarations.add( methodDecl );
      }
    }
    return methodDeclarations;
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
