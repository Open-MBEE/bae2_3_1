package gov.nasa.jpl.ae.event;

/**
 * @author bclement
 *
 */
public interface Effect {
//public abstract class Effect {
//	// Can't have abstract constructor, so throwing an exception to force
//	// subclasses to override this constructor.
//	public Effect( Effect e ) {
//		throw new UnsupportedOperationException();
//	}
//	public abstract TimeVarying<?> applyTo(TimeVarying<?> tv, Timepoint t, Duration d);
	public < T, V > TimeVarying< T, V > applyTo( TimeVarying< T, V > tv, boolean propagate );//, Timepoint t, Duration d );
  public < T, V > TimeVarying< T, V > unApplyTo( TimeVarying< T, V > tv );//, Timepoint t, Duration d );
	
	public Effect clone() throws CloneNotSupportedException;
  public boolean isApplied( Parameter< ? > variable );
}
