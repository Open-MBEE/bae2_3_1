/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.ae.util.Debug;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * @author bclement
 * 
 */
public class ConstraintLoopSolver implements Solver {

  private double timeOutMilliseconds = 5000.0;
  
  protected ArrayList< Constraint > unsatisfiedConstraints =
      new ArrayList< Constraint >();

  public ConstraintLoopSolver( double timeOutMilliseconds ) {
    this.timeOutMilliseconds = timeOutMilliseconds;
  }

  @Override
  public boolean solve( Collection< Constraint > constraints ) {
    Debug.outln( "ConstraintLoopSolver.solve(" + constraints + ")" );
    double startTime = System.currentTimeMillis();
    
    unsatisfiedConstraints.clear();
    
    unsatisfiedConstraints.addAll( constraints );
    Debug.outln( "ConstraintLoopSolver.solve(" + constraints
                 + ") unsatisfiedConstraints=" + unsatisfiedConstraints );
    while ( System.currentTimeMillis() - startTime > timeOutMilliseconds
            && !unsatisfiedConstraints.isEmpty() ) {
      Debug.outln( "remaining constraints to satisfy: " + unsatisfiedConstraints );
      for ( int i = 0; i < unsatisfiedConstraints.size(); ++i ) {
        Constraint c = unsatisfiedConstraints.get( i );
        Debug.outln( "checking constraint " + c );
        boolean thisSatisfied = c.isSatisfied();
        if ( !thisSatisfied ) {
          thisSatisfied = c.satisfy();
        }
        thisSatisfied = c.isSatisfied();
        if ( !thisSatisfied ) {
          thisSatisfied = satisfy( c );
        }
        if ( thisSatisfied ) {
          unsatisfiedConstraints.remove( i );
          --i;
        }
      }
    }
    return unsatisfiedConstraints.isEmpty();
  }

  public static boolean satisfy( Constraint constraint ) {
    Set<Variable<?>> vars = constraint.getVariables();
    Debug.outln( "satisfy(" + constraint + "): variables " + vars );
    boolean satisfied = false;
    for ( Variable<?> v : vars ) {
      if ( change( v ) ) {
        if ( constraint.isSatisfied() ) {
          satisfied = true;
          break;
        }
      }
    }
    return satisfied;
  }

  public static <T> boolean change( Variable< T > v ) {
    Debug.outln( "begin change(" + v + ")" );
    T value = v.getValue();
    Domain<T> d = v.getDomain();
    boolean gotNewValue = false;
    if ( d != null && d.size() > 1 ) {
      T newValue = null;
      for ( int i=0; i < Math.max( d.size(), 10 ); ++i ) {
        newValue = d.pickRandomValue();
        Debug.outln("Picked new value for " + v + ": " + newValue );
        if ( !newValue.equals( value ) ) {
          gotNewValue = true;
          break;
        }
      }
      if ( gotNewValue ) {
        v.setValue( newValue );
      }
      Debug.outln( "end change(" + v + ") "
                   + ( gotNewValue ? "got new value " + newValue : "" ) );
    } else {
      Debug.outln( "end change(" + v + ") restricted domain" );
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
      if ( !c.isSatisfied() ) {
        unsatisfiedConstraints.add( c );
      }
    }
    return unsatisfiedConstraints;
  }
  
}
