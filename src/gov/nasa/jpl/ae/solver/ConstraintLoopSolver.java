/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author bclement
 * 
 */
public class ConstraintLoopSolver implements Solver {

  public int maxTriesWithNoProgress = 1;
  
  protected ArrayList< Constraint > unsatisfiedConstraints =
      new ArrayList< Constraint >();

  protected Collection< Constraint > constraints = null;
  
  public ConstraintLoopSolver() {
  }

  @Override
  public boolean solve( Collection< Constraint > newConstraints ) {
    setConstraints( newConstraints );
    if ( Debug.isOn() ) Debug.outln( "ConstraintLoopSolver.solve(" + constraints + ")" );
    boolean deep = true;
    //double startTime = System.currentTimeMillis();
    unsatisfiedConstraints.clear();
    
    unsatisfiedConstraints.addAll( Utils.scramble( constraints ) );
    if ( Debug.isOn() ) Debug.outln( "ConstraintLoopSolver.solve(" + constraints
                 + ") unsatisfiedConstraints=" + unsatisfiedConstraints );
    int numAllConstrs = constraints.size();
    int numConstrs = unsatisfiedConstraints.size();
    System.out.println(numAllConstrs + " constraints; " + numAllConstrs + " to satisfy");
    int lastSize = -1;
    int numTimesWithNoProgress = 0;
    while ( //System.currentTimeMillis() - startTime > timeOutMilliseconds
            numTimesWithNoProgress < maxTriesWithNoProgress
            && !unsatisfiedConstraints.isEmpty() ) {
      lastSize = numConstrs;
      if ( Debug.isOn() ) Debug.outln( numConstrs + " remaining constraints to satisfy: " + unsatisfiedConstraints );
      if ( Debug.isOn() ) Debug.outln(""); 
      //Debug.turnOff();
      //unsatisfiedConstraints = Utils.scramble( unsatisfiedConstraints );
      List<Integer> intList = new ArrayList<Integer>();
      for ( int i = 0; i < unsatisfiedConstraints.size(); ++i ) {
        intList.add( i );
      }
      
      // Need to keep track of what we've satisfied and can remove
      TreeSet<Integer> deleteList = new TreeSet<Integer>(new Comparator< Integer >() {
        @Override
        public int compare( Integer o1, Integer o2 ) {
          return Integer.compare( o2,  o1 );
        }
      });
      
      // Scramble the list to avoid getting stuck in search space.
      intList = Utils.scramble( intList );
      for ( int j = 0; j < intList.size(); ++j ) {
        int i = intList.get( j );
        Constraint c = unsatisfiedConstraints.get( i );
        if ( Debug.isOn() ) Debug.outln( "checking constraint " + i + ": " + c );
        //Debug.turnOn();
        boolean thisSatisfied = c.isSatisfied( deep, null );
        if ( !thisSatisfied ) {
          if ( Debug.isOn() ) Debug.outln("try to satisfy constraint " + i + ": " + c);
          thisSatisfied = c.satisfy( deep, null );
          if ( thisSatisfied ) {
            thisSatisfied = c.isSatisfied( deep, null );
          }
        }
        if ( thisSatisfied ) {
          deleteList.add( i );
//          unsatisfiedConstraints.remove( i );
//          --i;
        }
      }
      for ( Integer j : deleteList ) {
        unsatisfiedConstraints.remove( j.intValue() );
      }
      numConstrs = unsatisfiedConstraints.size();
      boolean progress = numConstrs < lastSize;
      if ( progress ) {
        numTimesWithNoProgress = 0;
      } else {
        ++numTimesWithNoProgress;
      }
    }
    if ( Debug.isOn() ) Debug.outln( "ConstraintLoopSolver.solve() returning with " + unsatisfiedConstraints.size() + " unsatisfied constraints: " + unsatisfiedConstraints );
    return unsatisfiedConstraints.isEmpty();
  }

  public static boolean satisfy( Constraint constraint,
                                 boolean deep, Set< Satisfiable > seen ) {
    Set< Variable< ? > > vars = constraint.getFreeVariables();// ( deep, seen );
    if ( Debug.isOn() ) Debug.outln( "satisfy(" + constraint + "): variables " + vars );
    boolean satisfied = false;
    if ( Utils.isNullOrEmpty( vars ) ) return constraint.isSatisfied( deep, null );
    Variable<?>[] a = new Variable<?>[vars.size()];
    vars.toArray( a );
    for ( Variable< ? > v : Utils.scramble(a) ) {
      if ( Debug.isOn() ) Debug.outln( "try to change variable " + v );
      if ( change( v ) ) {
        if ( constraint.isSatisfied( deep, null ) ) {
          satisfied = true;
          break;
        }
      }
    }
    return satisfied;
  }

  public static <T> boolean change( Variable< T > v ) {
    if ( Debug.isOn() ) Debug.outln( "begin change(" + v + ")" );
    T value = v.getValue(true);
    Domain<T> d = v.getDomain();
    boolean gotNewValue = false;
    if ( d != null && d.magnitude() > 1 ) {
      T newValue = null;
      for ( long i=0; i < Math.max( d.magnitude(), 10 ); ++i ) {
        newValue = d.pickRandomValue();
        if ( Debug.isOn() ) Debug.outln("Picked new value for " + v + ": " + newValue );
        if ( !newValue.equals( value ) ) {
          gotNewValue = true;
          break;
        }
      }
      if ( gotNewValue ) {
        v.setValue( newValue );
      }
      if ( Debug.isOn() ) Debug.outln( "end change(" + v + ") "
                   + ( gotNewValue ? "got new value " + newValue : "" ) );
    } else {
      if ( Debug.isOn() ) Debug.outln( "end change(" + v + ") restricted domain" );
    }
    return gotNewValue;
  }

  @Override
  public Collection< Constraint > getUnsatisfiedConstraints() {
    return unsatisfiedConstraints;
  }

  public static Collection< Constraint >
      getUnsatisfiedConstraints( Collection< Constraint > constraints ) {
    Collection< Constraint > unsatisfiedConstraints =
        new ArrayList< Constraint >();

    // unsatisfiedConstraints.addAll( constraints );
    for ( Constraint c : constraints ) {
      if ( !c.isSatisfied( true, null ) ) {
        unsatisfiedConstraints.add( c );
      }
    }
    return unsatisfiedConstraints;
  }

  @Override
  public Collection< Constraint > getConstraints() {
    return constraints;
  }

  @Override
  public void setConstraints( Collection< Constraint > constraints ) {
    this.constraints = constraints;
  }

  @Override
  public int getNumberOfResolvedConstraints() {
    return getConstraints().size() - getUnsatisfiedConstraints().size();
  }
  
}
