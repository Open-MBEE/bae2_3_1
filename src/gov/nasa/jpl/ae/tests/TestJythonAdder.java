package gov.nasa.jpl.ae.tests;

import java.io.File;

import org.python.core.PyObject;

public class TestJythonAdder {
  public TestJythonAdder( Adder a ) {
    a.add( 9 );
    a.add( 37 );
  }
  public TestJythonAdder( int x ) {
    printInt(x);
  }
  public TestJythonAdder( PyObject o, int x ) {
    printInt(x);
    Adder a = (Adder)o.__tojava__( Adder.class );
    a.add( 2 * x );
  }

  public TestJythonAdder( File f ) {
    System.out.println( f.getName() );
  }
  
  public static void doAdd( Adder a ) {
    a.add( 3 );
  }
  public static void doAddP( PyObject pythonAdder ) {
    Adder a = (Adder)pythonAdder.__tojava__( Adder.class );
    a.add( 3 );
  }
  public static void printInt( int i ) {
    System.out.println( i );
  }
}
