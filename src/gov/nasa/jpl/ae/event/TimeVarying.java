package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.HasId;
import gov.nasa.jpl.ae.solver.Wraps;

import java.util.Iterator;
import java.util.Map;

/**
 * 
 */

/**
 * @author bclement
 *
 * TODO -- REVIEW -- Should this implement Map<Timepoint, T>?
 * TODO -- REVIEW -- Look at seqr tms to see if can leverage Timeline infrastructure.
 */
public interface TimeVarying< V > extends Comparable< TimeVarying< V > >, HasId, Wraps< V > { //extends Map< Timepoint, T > {
  public V getValue( Parameter< Integer > t );
  public V getValue( Integer t );
  public V setValue( Parameter< Integer > t, V value );
  public V unsetValue( Parameter< Integer > t, V value );
//  public Iterator< ? > iterator();
////	public T getValueAtTime( Timepoint t );
////	public T setValueAtTime( Timepoint t, T value );
////	// allows iteration through values
////	public Timepoint timeOfNextValue( Timepoint t );
  boolean isApplied( Effect effect );
  public boolean canBeApplied( Effect effect );
  public Object getOwner();
  public void setOwner( Object owner );
}
