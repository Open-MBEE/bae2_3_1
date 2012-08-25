package gov.nasa.jpl.ae.event;
import java.lang.reflect.Method;
import java.util.Vector;

import junit.framework.Assert;

/**
 * 
 */

/**
 * @author bclement
 *
 */
public class EffectFunction extends FunctionCall implements Effect {

	/**
	 * @param method
	 */
	public EffectFunction(Method method) {
		super(method);
	}

	/**
	 * @param object
	 * @param method
	 */
	public EffectFunction(Object object, Method method) {
		super(object, method);
	}

	/**
	 * @param object
	 * @param method
	 * @param arguments
	 */
	public EffectFunction(Object object, Method method, Vector<Object> arguments) {
		super(object, method, arguments);
	}

	/**
	 * @param effectFunction
	 */
	public EffectFunction( EffectFunction effectFunction ) {
		super( effectFunction );
	}
	
  /**
   * @param effectFunction
   */
  public EffectFunction( FunctionCall call ) {
    super( call );
  }
  
//	abstract public Effect clone() throws CloneNotSupportedException;
	public Effect clone() {
		return new EffectFunction( this );
	}
	
//	/* (non-Javadoc)
//	 * @see TimeVarying#valueAtTime(Timepoint)
//	 */
//	@Override
//	public Function valueAtTime(Timepoint t) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	/* (non-Javadoc)
	 * @see Effect#applyTo(TimeVarying, Timepoint, Duration)
	 */
	@Override
	public < T > TimeVarying< T > applyTo( TimeVarying< T > tv, boolean propagate ) {//, Timepoint t, Duration d ) {
	  //setStartTimeArgument( t );
	  //setDurationArgument( d );
	  object = tv;
    /*
		if ( arguments == null ) {
			arguments = new Vector<Object>();
		}
		if ( arguments.size() == 0 ) {
			arguments.add( tv.getValue( t ) ); //getValueAtTime( t ) );
		} else {
			for ( int i = 0; i < arguments.size(); ++i ) {
				if ( arguments.get( i ) == null ) {
					arguments.setElementAt( tv.getValue( t ), i ); //getValueAtTime( t ), i );
					break; // alternatively replace all null arguments instead of just 1?
				}
			}
		}
		*/
		//T result = (T) evaluate();
    evaluate(propagate);
		//tv.setValue( t, result );//setValueAtTime( t, result );
		return tv;
	}

//  abstract public void setDurationArgument( Duration d );
//
//  private void setStartTimeArgument( Timepoint t ) {
//    // TODO Auto-generated method stub
//    
//  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep ) {
    return getParameters( deep ).contains( parameter );
  }
  
  @Override
  public < T > TimeVarying< T > unApplyTo( TimeVarying< T > tv ) {//, Timepoint t,
                                           //Duration d ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isApplied( Parameter< ? extends TimeVarying< ? > > variable ) {
    return variable.getValue() != null && variable.getValue().isApplied( this );
  }

}
