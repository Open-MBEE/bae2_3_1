/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author bclement
 * 
 *         Not used -- trying to use existing Event, Parameter, Constraint,
 *         Dependency data structs as the network to avoid overhead/complexity.
 */
public class ConstraintNetwork {
  
  public static class Node { //implements Comparable< Node >{
    Parameter< ? > parameter = null;
    //Dependency< ? > dependency = null;
    protected Set< Edge > edges = new TreeSet< Edge >();
  }
  
  /*
   * An Edge is an Expression and a Direction. The expression is a node in the
   * network. The other node is unspecified parameter or expression. It is
   * unspecified so that a network can be Map<Parameter<?>, Expression<?>>.
   * 
   * A direction of IN means that the expression is dependent on the unspecified
   * node. OUT means that the unspecified node is dependent on this expression.
   * NONE means that the expression is a Constraint. Edges IN to the expression
   * are assumed for the parameters that are part of the expression.
   * 
   */
  public static class Edge { //< T extends Comparable< T > > extends Expression< T > {
    //
    public static enum Direction { NONE, IN, OUT };

    Direction direction = Direction.IN;
    Expression< ? > expression = null;
    
    public Edge( Expression< ? > expression, Direction direction ) {
      this.expression = expression;
      this.direction = direction;
    }
    //Expression< ? > getExpression();
  }
  
//  public class DependencyEdge implements Edge {
//    protected Dependency< ? > dependency;
//
//    @Override
//    public int compareTo( Edge o ) {
//      // TODO Auto-generated method stub
//      return 0;
//    }
//
//    @Override
//    public Expression< ? > getExpression() {
//      return dependency.expression;
//    }
//    
//  }
  
}
