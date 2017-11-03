/**
 * 
 */
package gov.nasa.jpl.ae.tests;

import java.util.List;
import gov.nasa.jpl.ae.event.ConstraintExpression;
import gov.nasa.jpl.ae.event.DurativeEvent;
import gov.nasa.jpl.ae.event.IntegerParameter;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.Functions.*;
import gov.nasa.jpl.ae.event.Expression;

/**
 * @author bclement
 *
 */
public class TestSolver {

  /**
   * @param args
   */
  public static void main( String[] args ) {
    //ConstraintLoopSolver s = new ConstraintLoopSolver();
    
    DurativeEvent cls = new DurativeEvent( "MyClass" );
    DurativeEvent.writeConstraintsOut = true;
    cls.duration.assignValue( 10L );
    cls.addDependency( cls.startTime, new Expression< Long >( 0, Long.class ) );
    cls.addDependency( cls.duration, new Expression< Long >( 20, Long.class ) );
    IntegerParameter x = new IntegerParameter( "x", 1, cls );
    cls.addDependency( x, new Expression< Integer >( 1, Integer.class ) );
    IntegerParameter y = new IntegerParameter( "y", 2, cls );
    cls.addDependency( y, new Expression< Integer >( 2, Integer.class ) );
    IntegerParameter z = new IntegerParameter( "z", 4, cls );
    //cls.addDependency( z, new Expression< Integer >( 4, Integer.class ) );
    List< Parameter< ? > > params = cls.getParameters();
    params.add( x );
    params.add( y );
    params.add( z );
    ConstraintExpression c1 =
        new ConstraintExpression( new Or(
                       new Expression<Boolean>(new Equals< Integer >( new Expression< Integer >( z ),
                           new Expression< Integer >( 3 ) )),
                       new Expression<Boolean>(new Equals< Integer >( new Expression< Integer >( z ),
                               new Expression< Integer >( -3 ) ))));
    ConstraintExpression c2 =
        new ConstraintExpression( new Less< Integer >( new Expression< Integer >( z ),
                                                       new Expression< Integer >( 0 ) ) );
    //    ConstraintExpression c1 =
//        new ConstraintExpression( new Or(
//                       new Expression<Boolean>(new Equals< Integer >( new Expression< Integer >( z ),
//                           new Expression< Integer >( new Plus< Integer, Integer >( new Expression< Integer >( x ),
//                               new Expression< Integer >( y ) ) ) )),
//
//                       new Expression<Boolean>(new Equals< Integer >( new Expression<Integer>( new Negative<Integer>( new Expression< Integer >( z ) ) ),
//                           new Expression< Integer >( new Plus< Integer, Integer >( new Expression< Integer >( x ),
//                               new Expression< Integer >( y ) ) ) ))));
//    //ConstraintExpression c2 = new ConstraintExpression( new Equals(z, Plus(x,y)) );
    cls.getConstraintExpressions().add( c1 );
    cls.getConstraintExpressions().add( c2 );

    //Debug.turnOn();
    cls.executeAndSimulate();
    cls.satisfy(true, null);
//    s.solve( cls.getConstraints( true, null ) );
//    System.out.println( s.getNumberOfResolvedConstraints()
//                        + " resolved constraints; unsatisfied constraints: "
//                        + s.getUnsatisfiedConstraints() );
   
    
  }
  
}
