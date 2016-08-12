/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasConstraints;
import gov.nasa.jpl.ae.solver.SingleValueDomain;
import gov.nasa.jpl.mbee.util.Utils;

public class SimpleTimepoint extends Timepoint {

  /**
   * @param name
   * @param o
   */
  public SimpleTimepoint( String name, ParameterListener o ) {
    super( name, o );
    init();
  }

  /**
   * @param name
   * @param value
   * @param o
   */
  public SimpleTimepoint( String name, Integer value, ParameterListener o ) {
    super( name, null, value, o );//value == null ? null : new SingleValueDomain<Integer>(value), value, o );
    init();
  }

  /**
   * @param value
   */
  public SimpleTimepoint( int value ) {
    super( "", null, value, null );//new SingleValueDomain<Integer>(value), value, null );
    init();
  }

  /**
   * @param timepoint
   */
  public SimpleTimepoint( Timepoint timepoint ) {
    this( timepoint.name, timepoint.value, timepoint.owner );
  }

  /**
   * @param date
   */
  public SimpleTimepoint( Date date ) {
    super( date );
    init();
  }
  

//  /**
//   * @param name
//   * @param domain
//   * @param value
//   * @param o
//   */
//  public SimpleTimepoint( String name, Domain< Integer > domain, Integer value,
//                          ParameterListener o ) {
//    super( name, value, o );
//    init();
//  }

  public void init() {
    setDomain(null);
//    if ( value != null && !(this.getDomain() instanceof SingleValueDomain)) {
//      this.setDomain( new SingleValueDomain< Integer >( value ) );
//    }
//    this.constraintList.clear();
  }
  
  public SimpleTimepoint clone() {
    return new SimpleTimepoint( this );
  }
  
  @Override
  public Collection< Constraint > getConstraints( boolean deep,
                                                  Set<HasConstraints> seen ) {
    return Utils.getEmptyList();
  }
  
}
