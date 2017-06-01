package gov.nasa.jpl.ae.event;
import gov.nasa.jpl.ae.event.Expression.Form;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.SortedMap;
import java.util.Vector;

/**
 * An Effect on a TimeVarying object that is performed via a function call. 
 */
public class EffectFunction extends FunctionCall implements Effect, HasTimeVaryingObjects {

  
  // FIXME -- need to change constructors to take a return type!
  
  
	/**
	 * @param method
	 */
	public EffectFunction(Method method) {
		super(method, (Class<?>)null);
	}

	/**
	 * @param object
	 * @param method
	 */
	public EffectFunction(Object object, Method method) {
		super(object, method, (Class<?>)null);
	}

	/**
	 * @param object
	 * @param method
	 * @param arguments
	 * @param returnType 
	 */
	public EffectFunction(Object object, Method method, Vector<Object> arguments, Class< ? > returnType ) {
		super(object, method, arguments, returnType);
	}

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public EffectFunction(Object object, Method method, Object[] arguments,
                        Call nestedCall) {
    this(object, method, arguments, nestedCall, (Class<?>)null);
  }

  public EffectFunction(Object object, Method method, Object[] arguments,
                        Call nestedCall, Class<?> cls) {
    super(object, method, arguments, nestedCall, cls);
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public EffectFunction(Object object, Method method, Object[] arguments,
                        Parameter<Call> nestedCall) {
    super(object, method, arguments, nestedCall, (Class<?>)null);
  }

  /**
   * @param object
   * @param method
   * @param arguments
   */
  public EffectFunction(Object object, Method method, Object[] arguments) {
    this(object, method, arguments, (Class<?>)null);
  }

  public EffectFunction( Object object , Method method , Object argumentsA[] , Class< ? > returnType  ) {
    super(object, method, argumentsA, returnType);
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

	public < TT, T > String effectStatus(TimeVarying< TT, T > tv, boolean propagate) {
    if ( tv instanceof TimeVaryingMap ) {
      TimeVaryingMap<T> tvm = (TimeVaryingMap<T>)tv;
      Pair< Parameter< Long >, Object > pair = tvm.getTimeAndValueOfEffect( this );
      Parameter<Long> p = pair.first;
      if ( p != null && p.getValue(propagate) != null ) {
        Long t = p.getValue(propagate);
        Parameter<Long> pb = tvm.getTimepointEarlier( t );
        if ( pb == null ) pb = p;
        Parameter<Long> pa = tvm.getTimepointLater( t );
        if ( pa == null ) pa = p;
        if ( pa.getValue(propagate) != null && pb.getValue( propagate ) != null ) {   
          SortedMap< Parameter< Long >, ? > m = tvm.subMap( pb, true, pa, true );
          return "state of effect on " + tvm.getName() + ": " + m;
        } else {
          return "state of effect on " + tvm.getName()
                 + "): bad timepoints; before=" + pb + ", at=" + p + ", after="
                 + pa;
        }
      }
    }
    return "";
	}
	
	/* (non-Javadoc)
	 * @see Effect#applyTo(TimeVarying, Timepoint, Duration)
	 */
	@Override
	public < TT, T > TimeVarying< TT, T > applyTo( TimeVarying< TT, T > tv, boolean propagate ) {//, Timepoint t, Duration d ) {
	  //setStartTimeArgument( t );
	  //setDurationArgument( d );
	  if ( Debug.isOn() ) Debug.outln("applying effect: " + MoreToString.Helper.toString( this, true, false, null ));
	  if ( Debug.isOn() ) Debug.outln("before " + effectStatus(tv, propagate));
    if ( object != tv && tv != null
         && ( object == null ||
              ( object instanceof Parameter && 
                ((Parameter<?>)object).getValue( true ) == null ) ) ) {
      Debug.error( true, "Warning! Assigning object of EffectFunction=(" + this
                         + ") to " + tv + "!" );
      object = tv;
    }
    if ( tv != null && object instanceof Parameter
         && !( (Parameter<?>)object ).getValue( true ).equals( tv ) ) {
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
    try {
      evaluate(propagate);
      if (tv instanceof TimeVaryingMap) {
        boolean was = ((TimeVaryingMap<?>) tv).wasApplied(this);
        if ( Debug.isOn() ) Debug.outln("effect was " + (was ? "" : "not ") + "applied: " + effectStatus(tv, propagate));
      }
    } catch ( IllegalAccessException e ) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    } catch ( InstantiationException e ) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    }
		//tv.setValue( t, result );//setValueAtTime( t, result );
		return tv;
	}
	
	@Override
  public Object evaluate( boolean propagate ) throws IllegalAccessException, InvocationTargetException, InstantiationException { // throws IllegalArgumentException,
    if ( returnValue != null && !isStale() ) {// && isGrounded( propagate, null ) ) {
      evaluationSucceeded = true;
      
      return returnValue;
    }
    Object oldValue = returnValue;
    returnValue = null;
    setStaleAnyReferencesToTimeVarying();
    Object result =  evaluate(propagate, true);
    
    if ( returnValue != null && !returnValue.equals( oldValue ) ) {
      handleChangeToTimeVaryingMap();
    }
    return result;
  }

	public void handleChangeToTimeVaryingMap() {
    Pair< Parameter< ? >, ParameterListener > pair = getTimeVaryingMapOwners();
    Parameter<?> p = pair.first;
    ParameterListener pl = pair.second;
    if ( pl != null && p != null ) {
      pl.handleValueChangeEvent( p, null );
    }
  }

  public void setStaleAnyReferencesToTimeVarying() {
    Pair< Parameter< ? >, ParameterListener > pair = getTimeVaryingMapOwners();
    Parameter<?> p = pair.first;
    ParameterListener pl = pair.second;
    if ( pl != null && p != null ) {
      pl.setStaleAnyReferencesTo( p, null );
    }
	}
	
  public Pair< Parameter<?>, ParameterListener > getTimeVaryingMapOwners() {
    TimeVaryingMap<?> tvm = getTimeVaryingMap();
    ParameterListener pl = null;
    Parameter<?> p = null;
    if ( object instanceof Parameter ) {
      p = (Parameter<?>)object;
      pl = p.getOwner();
    } else if ( object instanceof ParameterListener ) {
      pl = (ParameterListener)object;
      for ( Parameter<?> pp : pl.getParameters( false, null ) ) {
        if ( Utils.valuesEqual( pp.getValue( false ), tvm ) ) {
          p = pp;
          break;
        }
      }
    }
    if ( p != null && pl != null ) {
      return new Pair< Parameter< ? >, ParameterListener >( p, pl );
    }
    
    Object tvmOwner = tvm == null ? null : tvm.getOwner();
    if ( tvmOwner != null ) {
      if ( tvmOwner instanceof Parameter ) {
        p = (Parameter<?>)tvmOwner;
        pl = p.getOwner();
      } else if (tvmOwner instanceof ParameterListener ) {
        pl = (ParameterListener)getTimeVaryingMap().owner;
        for ( Parameter<?> pp : pl.getParameters( false, null ) ) {
          if ( Utils.valuesEqual( pp.getValue( false ), tvm ) ) {
            p = pp;
            break;
          }
        }
      }
    }
    return new Pair< Parameter< ? >, ParameterListener >( p, pl );
  }
  
  public < V > TimeVaryingMap< V > getTimeVaryingMap() {
    if ( object instanceof Parameter ) {
      Parameter<?> p = (Parameter<?>)object;
      if ( p.getValue() instanceof TimeVaryingMap ) {
        return (TimeVaryingMap< V >)p.getValue();
      }
      return null;
    }
    if ( object instanceof TimeVaryingMap ) {
      return (TimeVaryingMap< V >)object;
    }
    return null;
  }

//  abstract public void setDurationArgument( Duration d );
//
//  private void setStartTimeArgument( Timepoint t ) {
//    // TODO Auto-generated method stub
//    
//  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep, Set< HasParameters > seen ) {
    boolean has = HasParameters.Helper.hasParameter( this, parameter, deep, seen );
    return has;
  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Effect#unApplyTo(gov.nasa.jpl.ae.event.TimeVarying)
   */
  @Override
  public < T, V > TimeVarying< T, V > unApplyTo( TimeVarying< T, V > tv ) {//, Timepoint t,
                                           //Duration d ) {
    if ( tv instanceof TimeVaryingMap ) { 
      ((TimeVaryingMap< T >)tv).unapply( this );
    } else {
      assert false;
    }
    return tv;
  }
  
  /**
   * Determine if the effect is applied to a timeline.
   * 
   * @return whether the effect is applied to the intended timeline or null if
   *         it cannot find the timeline.
   */
  public Boolean isApplied() {
    Pair< Parameter< ? >, ParameterListener > p = getTimeVaryingMapOwners();
    if ( p.first != null ) {
      return isApplied( p.first );
    }
    return null;
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
    return ((TimeVarying<?,?>)value).isApplied( this );
  }

  @Override
  public Set< TimeVarying< ?,? >>
      getTimeVaryingObjects( boolean deep, Set< HasTimeVaryingObjects > seen ) {
    Pair< Boolean, Set< HasTimeVaryingObjects > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    return HasTimeVaryingObjects.Helper.getTimeVaryingObjects( object, deep, seen );
  }

  @Override
  public String toString() {
    return super.toString(true, true, null);
  }
  
}
