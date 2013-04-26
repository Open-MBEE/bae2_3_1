package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Variable;

class HowManyQuery extends Query {
  // construct query
  public HowManyQuery( Variable< Number > numVarParam1,
                       Expression< Boolean > boolExprParam2 ) {
    super( new Object[] { numVarParam1, boolExprParam2 } );

    // EDIT HERE: make calls to problem translation here
    makeFreeVariable( numVarParam1 );
    addConstraint( new Expression< Boolean >( new Functions.DoesThereExist< Number >( numVarParam1,
                                                                                      boolExprParam2 ) ) );
  }

  public String toString() {
    String statement =
        "How many " + param[ 0 ] + " would cause " + param[ 1 ] + "?";
    return statement;
  }
}
