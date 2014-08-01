/**
 * 
 */
package gov.nasa.jpl.ae.tests;

import gov.nasa.jpl.ae.xml.EventXmlToJava;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


import japa.parser.ASTHelper;
import japa.parser.ASTParser;
import japa.parser.ParseException;
import japa.parser.ast.BlockComment;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.body.VariableDeclaratorId;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.AssignExpr.Operator;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ExplicitConstructorInvocationStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.VoidType;

/**
 * @author bclement
 * 
 */
public class TestJavaparser {

  protected static CompilationUnit cu = new CompilationUnit();

  protected static void setPackage( String name ) {
    // set the package
    String packageName = "generated." + name;
    cu.setPackage( new PackageDeclaration( ASTHelper.createNameExpr( packageName ) ) );
  }

  
  /**
   * @param args
   */
  public static void main( String[] args ) {
    // Does "super();" work?
    StringBuffer sb = new StringBuffer();
    sb.append( "public class Foo extends Integer {\n" );
    sb.append( "  public Foo() {\n" );
    sb.append( "    super();\n");
    sb.append( "  }\n" );
    sb.append( "}\n" );
    ASTParser aParser = new ASTParser( new StringReader( sb.toString() ) );
    try {
      BodyDeclaration bd = aParser.ClassOrInterfaceBodyDeclaration( false );
    } catch ( ParseException e ) {
      e.printStackTrace();
    }

    
    // TestJavaparser t = new TestJavaparser();
    setPackage( "javaparserTestPackage" );

    // add an import
    NameExpr importName = new NameExpr( "event.Parameter" );
    ImportDeclaration importDecl =
        new ImportDeclaration( importName, false, false );
    cu.setImports( new ArrayList< ImportDeclaration >() );
    cu.getImports().add( importDecl );
    
    // declare a class
    ClassOrInterfaceDeclaration classDecl =
        new ClassOrInterfaceDeclaration( ModifierSet.PUBLIC, false, "MyClass" );
    ASTHelper.addTypeDeclaration( cu, classDecl );

    // create fields
//    EventXmlToJava.addParameterField( classDecl, new EventXmlToJava.Param( "field1", "String", "\"hello\"" ) );
//    EventXmlToJava.addParameterField( classDecl, new EventXmlToJava.Param( "field2", "Double", null ) );
    
    ASTParser parser = new ASTParser( new StringReader( "public static int sq(int n) {return n*n;}") );
    BodyDeclaration decl = null;
    try {
      decl = parser.ClassOrInterfaceBodyDeclaration( false );
    } catch ( ParseException e ) {
      e.printStackTrace();
    }
    if ( decl != null ) {
      ASTHelper.addMember( classDecl, decl );
    }

    parser = new ASTParser( new StringReader( "void makeStuff() {System.out.println(sParam);}") );
    BodyDeclaration mDecl = null;
    try {
      mDecl = parser.ClassOrInterfaceBodyDeclaration( false );
    } catch ( ParseException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //parser = new ASTParser( new StringReader( "String s = \"hello\";") );
    parser = new ASTParser( new StringReader( "Parameter< String > sParam = new Parameter< String >( \"hello\" ); ") );
    Statement stmt = null;
    try {
      stmt = parser.BlockStatement();
    } catch ( ParseException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if ( mDecl instanceof MethodDeclaration ) {
      MethodDeclaration methodDecl = (MethodDeclaration)mDecl;
      methodDecl.getBody().getStmts().add( 0, stmt );
    }
    if ( mDecl != null ) {
      ASTHelper.addMember( classDecl, mDecl );
    }
    
    /* 
    MethodDeclaration mDecl = new MethodDeclaration( ModifierSet.PUBLIC, new VoidType(),
                                  "makeStuff" );
    BlockStmt body = new BlockStmt();
    Expression target;
    Expression value;
    Operator op;
    AssignExpr ae = new AssignExpr( target, value, op );
    boolean isThis;
    Expression expr;
    List< Expression > iArgs
    ExplicitConstructorInvocationStmt s = 
        new ExplicitConstructorInvocationStmt( false, expr, iArgs ); 
    
    mDecl.setBody( body  );
*/
    
    // prints the changed compilation unit
    System.out.println(cu.toString());
    System.out.println();
  }

}
