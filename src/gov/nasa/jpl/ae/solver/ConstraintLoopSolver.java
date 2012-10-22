/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * @author bclement
 * 
 */
public class ConstraintLoopSolver implements Solver {

  public int maxTimesWithNoProgress = 1;
  
  protected ArrayList< Constraint > unsatisfiedConstraints =
      new ArrayList< Constraint >();

  public ConstraintLoopSolver() {
  }

  @Override
  public boolean solve( Collection< Constraint > constraints ) {
    if ( Debug.isOn() ) Debug.outln( "ConstraintLoopSolver.solve(" + constraints + ")" );
    boolean deep = false;
    //double startTime = System.currentTimeMillis();
    unsatisfiedConstraints.clear();
    
    unsatisfiedConstraints.addAll( constraints );
//    if ( Debug.isOn() ) Debug.outln( "ConstraintLoopSolver.solve(" + constraints
//                 + ") unsatisfiedConstraints=" + unsatisfiedConstraints );
    int numConstrs = unsatisfiedConstraints.size();
    int lastSize = -1;
    int numTimesWithNoProgress = 0;
    while ( //System.currentTimeMillis() - startTime > timeOutMilliseconds
            numTimesWithNoProgress < maxTimesWithNoProgress
            && !unsatisfiedConstraints.isEmpty() ) {
      lastSize = numConstrs;
      if ( Debug.isOn() ) Debug.outln( numConstrs + " remaining constraints to satisfy: " + unsatisfiedConstraints );
      if ( Debug.isOn() ) Debug.outln(""); 
      for ( int i = 0; i < unsatisfiedConstraints.size(); ++i ) {
        Constraint c = unsatisfiedConstraints.get( i );
        if ( Debug.isOn() ) Debug.outln( "checking constraint " + i + ": " + c );
        boolean thisSatisfied = c.isSatisfied( deep, null );
        if ( !thisSatisfied ) {
          thisSatisfied = c.satisfy( deep, null );
        }
        thisSatisfied = c.isSatisfied( deep, null );
        if ( !thisSatisfied ) {
          thisSatisfied = satisfy( c, deep, null );
        }
        if ( thisSatisfied ) {
          unsatisfiedConstraints.remove( i );
          --i;
        }
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
//    Variable<?>[] a = new Variable<?>[vars.size()];
//    boolean[] b = new boolean[vars.size()];
//    vars.toArray( a );
//    for ( int i=0; i < vars.size(); ++i ) {
//      b[i] = false;
//    }
//    for ( int i=0; i < vars.size(); ++i ) {
////    for ( Variable<?> v : vars ) {
//      int j = Random.global.nextInt( vars.size() - i );
//      int k = 0;
//      while ( j >= 0 ) {
//        if ( !b[k] ) --j;
//        ++k;
//      }
//      Variable<?> v = a[--k];
//      b[k] = true;
    Variable<?>[] a = new Variable<?>[vars.size()];
    vars.toArray( a );
    for ( Variable< ? > v : Utils.scramble(a) ) {
//    for ( Variable<?> v : Utils.scramble( vars ) ) {
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
    if ( d != null && d.size() > 1 ) {
      T newValue = null;
      for ( int i=0; i < Math.max( d.size(), 10 ); ++i ) {
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
  
}
