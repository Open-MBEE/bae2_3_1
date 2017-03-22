/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.mbee.util.Utils;

/**
 * StateVariable is simply a {@link Parameter} of type {@link TimeVaryingMap}.
 *
 */
public class StateVariable< T > extends Parameter< TimeVaryingMap< T > > {

  /**
   * 
   */
  public StateVariable() {
  }

  /**
   * @param n
   * @param d
   * @param o
   */
  public StateVariable( String n, Domain< TimeVaryingMap< T > > d,
                        ParameterListener o ) {
    super( n, d, o );
  }

  /**
   * @param n
   * @param d
   * @param v
   * @param o
   */
  public StateVariable( String n, Domain< TimeVaryingMap< T > > d, TimeVaryingMap< T > v,
                        ParameterListener o ) {
    super( n, d, v, o );
  }

  /**
   * @param n
   * @param d
   * @param fc
   * @param o
   * @param propagate
   */
  public StateVariable( String n, Domain< TimeVaryingMap< T > > d, FunctionCall fc,
                        ParameterListener o, boolean propagate ) {
    super( n, d, fc, o, propagate );
  }

  /**
   * @param parameter
   */
  public StateVariable( Parameter< TimeVaryingMap< T > > parameter ) {
    super( parameter );
  }

  @Override
  protected void setValue( TimeVaryingMap< T > val, boolean propagateChange ) {
    if ( !Utils.isNullOrEmpty( getName() ) ) {
      val.setName( getName() );
    }
    super.setValue( val, propagateChange );
  }
}
