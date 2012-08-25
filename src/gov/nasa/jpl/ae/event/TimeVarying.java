package gov.nasa.jpl.ae.event;

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
public interface TimeVarying< V > extends Comparable< TimeVarying< V > > { //extends Map< Timepoint, T > {
  public V getValue( Timepoint t );
  public V getValue( int t );
  public V setValue( Timepoint t, V value );
  public V unsetValue( Timepoint t, V value );
//  public Iterator< ? > iterator();
////	public T getValueAtTime( Timepoint t );
////	public T setValueAtTime( Timepoint t, T value );
////	// allows iteration through values
////	public Timepoint timeOfNextValue( Timepoint t );
  boolean isApplied( Effect effect );
}
