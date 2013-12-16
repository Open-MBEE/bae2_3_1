package gov.nasa.jpl.ae.event;
import gov.nasa.jpl.ae.event.Expression.Form;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.Vector;

/**
 * An Effect on a TimeVarying object that is performed via a function call. 
 */
public class EffectFunction extends FunctionCall implements Effect, HasTimeVaryingObjects {

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
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public EffectFunction(Object object, Method method, Object[] arguments,
                        Call nestedCall) {
    super(object, method, arguments, nestedCall);
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public EffectFunction(Object object, Method method, Object[] arguments,
                        Parameter<Call> nestedCall) {
    super(object, method, arguments, nestedCall);
  }

  /**
   * @param object
   * @param method
   * @param arguments
   */
  public EffectFunction(Object object, Method method, Object[] arguments) {
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
  
  /**
   * @param effectFunction
   */
  public EffectFunction( Expression<?> expr ) {
    super( expr.form == Form.Function ? (FunctionCall)expr.expression : null );
  }
  
  // TODO -- REVIEW -- should this call super.clone() all the way up to Object?
//	abstract public Effect clone() throws CloneNotSupportedException;
	public EffectFunction clone() {
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
    if ( object != tv && tv != null
         && ( object == null ||
              ( object instanceof Parameter && 
                ((Parameter)object).getValue( true ) == null ) ) ) {
      Debug.error( true, "Warning! Assigning object of EffectFunction=(" + this
                         + ") to " + tv + "!" );
      object = tv;
    }
    if ( tv != null && object instanceof Parameter
         && !( (Parameter)object ).getValue( true ).equals( tv ) ) {
      Debug.error( true, "Object of EffectFunction=(" + this
                         + ") does not match " + tv + "!" );
    }
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
    if (tv instanceof TimeVaryingMap) {
      ((TimeVaryingMap) tv).wasApplied(this);
    }
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
  public boolean hasParameter( Parameter< ? > parameter, boolean deep, Set< HasParameters > seen ) {
    return getParameters( deep, seen ).contains( parameter );
  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Effect#unApplyTo(gov.nasa.jpl.ae.event.TimeVarying)
   */
  @Override
  public < T > TimeVarying< T > unApplyTo( TimeVarying< T > tv ) {//, Timepoint t,
                                           //Duration d ) {
    if ( tv instanceof TimeVaryingMap ) { 
      ((TimeVaryingMap< T >)tv).unapply( this );
    } else {
      assert false;
    }
    return tv;
  }
  
  @Override
  public boolean isApplied( Parameter< ? > variable ) {
    if ( variable == null ) return false;
    Object value = variable.getValueNoPropagate();
    if ( value == null ) return false;
    if ( !(value instanceof TimeVarying) && ( value instanceof Parameter ) ) {
      return isApplied( (Parameter< ? >)value );
    }
    if ( !(value instanceof TimeVarying) ) {
      if ( Debug.isOn() ) Debug.errln( "Effect variable is not TimeVarying! " + variable );
      return false;
    }
    return ((TimeVarying<?>)value).isApplied( this );
  }

  @Override
  public Set< TimeVarying< ? >>
      getTimeVaryingObjects( boolean deep, Set< HasTimeVaryingObjects > seen ) {
    Pair< Boolean, Set< HasTimeVaryingObjects > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    return HasTimeVaryingObjects.Helper.getTimeVaryingObjects( object, deep, seen );
  }

}
