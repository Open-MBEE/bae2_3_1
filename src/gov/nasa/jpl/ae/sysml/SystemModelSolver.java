/*******************************************************************************
 * Copyright (c) <2014>, California Institute of Technology ("Caltech").  
 * U.S. Government sponsorship acknowledged.
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are 
 * permitted provided that the following conditions are met:
 * 
 *  - Redistributions of source code must retain the above copyright notice, this list of 
 *    conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice, this list 
 *    of conditions and the following disclaimer in the documentation and/or other materials 
 *    provided with the distribution.
 *  - Neither the name of Caltech nor its operating division, the Jet Propulsion Laboratory, 
 *    nor the names of its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS 
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER  
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/

package gov.nasa.jpl.ae.sysml;

import java.util.Collection;
import java.util.Set;

//import sysml.SystemModel;
import gov.nasa.jpl.ae.event.Duration;
import gov.nasa.jpl.ae.event.DurativeEvent;
import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.solver.Solver;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;

/**
 * The class copies a lot of generic solver infrastructure from
 * ParameterListenerImpl. It wraps another solver and invokes it repeatedly
 * until it reaches repeat limit or a time limit.
 */
public class SystemModelSolver extends HasIdImpl implements Solver, Satisfiable, Wraps<Solver> {
  
  //protected SystemModel< E, C, T, P, N, I, U, R, V, W, CT > model;
  protected Solver solver;  // REVIEW -- something feels weird about this

  protected double timeoutSeconds = 12*3600.0;
  protected int maxLoopsWithNoProgress = 100;
  protected long maxPassesAtConstraints = 10000;
  protected boolean usingTimeLimit = false;
  protected boolean usingLoopLimit = true;
  protected boolean amTopEventToSimulate = false;

  // Things not taken from ae.ParameterListenerImpl for snapshotting progress.
  // TODO -- This is a generic concept to work back in. A persistent (Serializable?)
  // solver could serialize the state to save away and load snapshots.
//  protected boolean snapshotSimulationDuringSolve = true;
//  protected boolean snapshotToSameFile = true;
//  protected int loopsPerSnapshot = 20;  // set to 1 to take snapshot every time
//  protected String baseSnapshotFileName = "simulationSnapshot.txt";
  
  public SystemModelSolver(Solver solver) {
    this.solver = solver;
  }

  public boolean solve() {
    return satisfy( true, null );
  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Satisfiable#satisfy(boolean, java.util.Set)
   */
  public boolean satisfy(boolean deep, Set< Satisfiable > seen) {
    Pair< Boolean, Set< Satisfiable > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return true;
    seen = pair.second;
    
    if ( isSatisfied(deep, null) ) return true;
    double clockStart = System.currentTimeMillis();
    long numLoops = 0;
    long mostResolvedConstraints = 0;
    int numLoopsWithNoProgress = 0;
    
    boolean satisfied = false;
    long millisPassed = (long)( System.currentTimeMillis() - clockStart );
    double curTimeLeft =
        ( timeoutSeconds * 1000.0 - ( millisPassed ) );
    
    while ( !satisfied
            && numLoopsWithNoProgress < maxLoopsWithNoProgress
            && ( !usingTimeLimit || curTimeLeft > 0.0 )
            && ( !usingLoopLimit || numLoops < maxPassesAtConstraints ) ) {
      if ( Debug.isOn() || this.amTopEventToSimulate ) {
        if ( usingTimeLimit ) {
          System.out.println( this.getClass().getName() + " satisfy loop with "
                              + Duration.toFormattedString( (long)curTimeLeft )
                              + " time left" );
        } else {
          System.out.println( this.getClass().getName()
                              + " satisfy loop after "
                              + Duration.toShortFormattedStringForIdentifier( millisPassed ) );
        }
        if ( Debug.isOn() || this.amTopEventToSimulate ) {
          System.out.println( this.getClass().getName()
                              + " satisfy loop round " + ( numLoops + 1 )
                              + " out of " + maxPassesAtConstraints );
        } else {
          System.out.println( this.getClass().getName()
                              + " satisfy loop round " + ( numLoops + 1 ) );
        }
        Debug.out( "" );
      }
      if ( amTopEventToSimulate ) {
        DurativeEvent.newMode = false; //numLoops % 2 == 0;
      }
      satisfied = tryToSatisfy(deep, null);

      long numResolvedConstraints = this.getNumberOfResolvedConstraints();//solver.getNumberOfResolvedConstraints();
      
      boolean improved = numResolvedConstraints > mostResolvedConstraints; 
      // TODO -- Move call to doSnapshotSimulation() into tryToSatisfy() in order to
      // move it out of this class and into DurativeEvent since Events simulate.
//      if ( snapshotSimulationDuringSolve && this.amTopEventToSimulate
//           && ( numLoops % loopsPerSnapshot == 0 ) ) {
//        doSnapshotSimulation( improved );
//      }
      
      if ( !satisfied && !improved ) {
        ++numLoopsWithNoProgress;
        if ( numLoopsWithNoProgress >= maxLoopsWithNoProgress
             && ( Debug.isOn() || amTopEventToSimulate ) ) {
          System.out.println( "\nPlateaued at " + mostResolvedConstraints + " constraints satisfied." );
          System.out.println( solver.getUnsatisfiedConstraints().size() + " unresolved constraints = " + solver.getUnsatisfiedConstraints() );
        }
      } else {
        mostResolvedConstraints = numResolvedConstraints;
        numLoopsWithNoProgress = 0;
      }
      
      millisPassed = (long)( System.currentTimeMillis() - clockStart );
      curTimeLeft = ( timeoutSeconds * 1000.0 - millisPassed );
      ++numLoops;
    }
    return satisfied;
  }

  public boolean isSatisfied(boolean deep, Set< Satisfiable > seen) {
    for ( Constraint c : getConstraints() ) { // REVIEW -- why is true passed in?
      if ( Debug.isOn() ) Debug.outln( "ParameterListenerImpl.isSatisfied(): checking " + c );
      if ( !c.isSatisfied(deep, seen) ) {
        return false;
      }
    }
    return true;
  }

  protected boolean tryToSatisfy(boolean deep, Set< Satisfiable > seen) {
    if ( Debug.isOn() ) Debug.outln( this.getClass().getName() + " satisfy loop called ground() " );
    
    Collection< Constraint > allConstraints = getConstraints();
    if ( Debug.isOn() || amTopEventToSimulate ) {
      System.out.println( this.getClass().getName()
                          + ".tryToSatisfy() calling solve() with "
                          + allConstraints.size() + " constraints" );
    }
    // REVIEW -- why not call satisfy() here and solve elsewhere??
    boolean satisfied = solver.solve( allConstraints );
    if ( Debug.isOn() || amTopEventToSimulate ) {
      System.out.println( this.getClass().getName()
                          + ".tryToSatisfy() called solve(): satisfied "
                          + ( allConstraints.size() -
                              solver.getUnsatisfiedConstraints().size() )
                          + " constraints; failed to resolve "
                          + solver.getUnsatisfiedConstraints().size()
                          + " constraints" );
    }

    satisfied = isSatisfied(deep, null);
    if ( Debug.isOn() || amTopEventToSimulate ) {
      System.out.println( this.getClass().getName()
                          + ".tryToSatisfy() called solve(): satisfied = "
                          + satisfied );
    }
    return satisfied;
  }

  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Solver#solve(java.util.Collection)
   */
  @Override
  public boolean solve( Collection< Constraint > constraints ) {
    setConstraints(constraints);
    return solve();
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Solver#getUnsatisfiedConstraints()
   */
  @Override
  public Collection< Constraint > getUnsatisfiedConstraints() {
    return solver.getUnsatisfiedConstraints();
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Solver#getConstraints()
   */
  @Override
  public Collection< Constraint > getConstraints() {
    return solver.getConstraints();
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Solver#getNumberOfResolvedConstraints()
   */
  @Override
  public int getNumberOfResolvedConstraints() {
    return solver.getNumberOfResolvedConstraints();
  }

  @Override
  public void setConstraints( Collection< Constraint > constraints ) {
    solver.setConstraints( constraints );
  }

  @Override
  public Class< ? > getType() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getTypeNameForClassName( String className ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Class< ? > getPrimitiveType() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Solver getValue( boolean propagate ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setValue( Solver value ) {
    // TODO Auto-generated method stub
    
  }  
  
  
}
