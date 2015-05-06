package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Variable;

/**
 * Query is a class that poses a kind of analysis question by manipulating the
 * open variables and constraints.
 * 
 */
class Query {

  /**
   * the parameters to the query template
   */
  Object param[];
  
  public Query( Object[] objects ) {
    // TODO Auto-generated constructor stub
  }
  public void makeFreeVariable( Variable< Number > numVarParam1 ) {
    // TODO Auto-generated method stub      
  }
  protected void addConstraint( Expression<Boolean> doesThereExist ) {
    // TODO Auto-generated method stub
    
  }
  
}
