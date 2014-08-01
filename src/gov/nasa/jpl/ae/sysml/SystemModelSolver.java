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

import sysml.SystemModel;

import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Solver;

/**
 *
 */
public class SystemModelSolver< E, C, T, P, N, I, U, R, V, W, CT > implements Solver {
  
  protected SystemModel< E, C, T, P, N, I, U, R, V, W, CT > model;
  protected Solver solver;  // REVIEW -- something feels weird about this
  
  public SystemModelSolver(SystemModel< E, C, T, P, N, I, U, R, V, W, CT > model,
                           Solver solver) {
    this.model = model;
    this.solver = solver;
  }

  public boolean solve() {
    // 
    return solve( getConstraints() );
  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Solver#solve(java.util.Collection)
   */
  @Override
  public boolean solve( Collection< Constraint > constraints ) {
    return solver.solve( constraints );
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

}
