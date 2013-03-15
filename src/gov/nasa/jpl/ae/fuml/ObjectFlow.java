/**
 * 
 */
package gov.nasa.jpl.ae.fuml;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.SortedMap;

import gov.nasa.jpl.ae.event.Effect;
import gov.nasa.jpl.ae.event.EffectFunction;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;

/**
 *
 */
public class ObjectFlow< Obj > extends TimeVaryingMap< Obj > {

  private static final long serialVersionUID = 5278616069238729814L;
  private static Collection< Method > effectMethods = ObjectFlow.initEffectMethods();

  /**
   * For the convenience of referring to the effect methods.
   */
  protected static Method sendWithParameterMethod = getSendMethod();
  //protected static Method sendWithIntegerMethod = getSendWithIntegerMethod();
  protected static Method sendIfMethod = getSendIfMethod();
  private static Method receiveWithParameterMethod = getReceiveWithParameterMethod();

  protected static final boolean receiveSetsEvenIfNull = false;

  protected List< ObjectFlow< Obj > > listeners =
      new ArrayList< ObjectFlow< Obj > >();

  // protected Class< ? > type = null;
  
  /**
   * @param name
   * @param defaultValue
   */
  public ObjectFlow( String name ) {
    super( name );
  }

  /**
   * @param name
   * @param defaultValue
   */
  public ObjectFlow( String name, Class< Obj > type ) {
    super( name, type );
  }

  protected void breakpoint() {
    if ( getName() != null && getName().contains( "14272" ) ) {
      return;
    }
  }
  
  /**
   * Appends the specified ObjectFlow to the end of the list of listeners. 
   * @param objectFlow is the ObjectFlow to add.
   */
  public void addListener( ObjectFlow< Obj > objectFlow ) {
    getListeners().add( objectFlow );
  }
  
  public void sendIf( Obj o, Parameter<Integer> t, Boolean doSend ) {
    if ( doSend == null ) return;
    if ( doSend.booleanValue() ) {
      send( o, t );
    } else if ( isSetValueApplied(o, t) ) {
      unsetValue(t, o);
    }
  }
  
  public void send( Obj o, Parameter<Integer> t ) {
    breakpoint();
    Obj thing = Expression.evaluate( o, type, true ); //REVIEW -- This shouldn't be necessary.  Change prototype to send(Object o, IntegerParameter t)? 
    if ( type == null || type.isInstance( thing ) ) {  
      this.setValue( t, thing );
      for ( ObjectFlow< Obj > f : listeners ) {
        f.send( thing, t );
      }
    }
  }
  
//  public void send( Obj o, Integer t ) {
//    breakpoint();
//    if ( type == null || type.isInstance( o ) ) {
//      this.setValue( t, o );
//      for ( ObjectFlow< Obj > f : listeners ) {
//        f.send( o, t );
//      }
//    }
//  }

  @Override
  public void unapply( Effect effect ) {
    boolean needToUnapply = true;
    if ( effect instanceof EffectFunction ) {
      EffectFunction effunc = (EffectFunction)effect;
      if ( isASendEffect(effunc) ) {
        for ( ObjectFlow<Obj> of : getListeners() ) {
          of.unapply( effect );
        }
        if ( effunc.getMethod().equals( getSendIfMethod() ) ) {
          
        }
      }
      if ( needToUnapply  ) {
        Pair< Parameter<Integer>, Obj > p = 
          getTimeAndValueOfEffect( effect, !isASendEffect(effunc) );
  //                                      getSetValueMethod(),
  //                                      getSetValueMethod() );
        if ( p != null ) {
          unsetValue( p.first, p.second );
          return;
        }
      }
    }
    super.unapply( effect );  
  }
  

  
  public Obj receive( Parameter<Integer> t ) {
    return receive( t, false, !receiveSetsEvenIfNull );
    
  }
  protected Obj receive( Parameter<Integer> t, boolean noSetValue,
                         boolean noSetIfNull ) {
    breakpoint();
    if (name.contains( "q" )){
      breakpoint();
    }
    if ( t == null ) return null;
    Obj o = getValueEarlier( t );
    boolean noSet = noSetValue || ( noSetIfNull && o == null ); 
    if ( !noSet ) {
      if ( !isReceiveApplied( t ) ) {
        this.setValue( t, null );
      }
    }
    
    return o;
  }
 
  public boolean isReceiveApplied( Parameter<Integer> t ) {
    if ( receive( t, true, true ) == null ) {
      if ( !receiveSetsEvenIfNull ) return true;
    }
    if ( getValue( t ) == null ) return true;
    return false;
  }
  
//  public Obj receive( Integer t ) {
//    breakpoint();
//    Obj o = this.getValue( t );
//    this.setValue( t, null );
//    return o;
//  }
  
  public boolean hasStuff( Parameter<Integer> t ) {
    breakpoint();
    if ( t == null ) return false;
    return getValue( t ) != null;
  }

  public boolean hasStuff( Integer t ) {
    breakpoint();
    if ( t == null ) return false;
    return getValue( t ) != null;
  }

  public int nextTimeHasStuff( Integer t ) {
    breakpoint();
    if (name.contains("q_LADWP_receiveGenReading")) {
      breakpoint();
    }
    if ( t == null ) {
      return Timepoint.getHorizonDuration();
    }
    Parameter<Integer> tp = makeTempTimepoint( t, false );
    return nextTimeHasStuff( tp );
  }

  public int nextTimeHasStuff( Parameter<Integer> t ) {
    breakpoint();
    if ( t == null || t.getValueNoPropagate() == null ) {
      return Timepoint.getHorizonDuration();
    }
    Obj v = getValue( t );
    if ( v != null ) return t.getValue( false );
    SortedMap< Parameter<Integer>, Obj > map = tailMap( t, true );
    for ( java.util.Map.Entry< Parameter<Integer>, Obj > e : map.entrySet() ) {
      if ( e.getValue() != null ) {
        t = e.getKey();
        if ( t == null || t.getValueNoPropagate() == null ) {
          break;
        }
        return t.getValueNoPropagate();
      }
    }
    return Timepoint.getHorizonDuration();
  }

  @Override
  public boolean isApplied( Effect effect ) {
    breakpoint();
    if ( effect == null ) return false;
//    EffectFunction effectFunction = null;
//    if ( effect instanceof EffectFunction ) {
//      effectFunction = (EffectFunction)effect;
//    }
    //HERE!!! TODO!!
//    if ( effectFunction.getMethod() != null && effectFunction.getMethod().getName() != null
//        && effectFunction.getMethod().getName().equals("sendIf") ) {
//      Expression<Boolean> = new Expression<Boolean>(effectFunction.getArguments().get( 2 ));
//      if ( effectFunction.getArguments().size() == 3 ) && .  
//    }
    if ( isApplied(effect, getSendMethod(), getSendMethod()//getSendMethod2()
                   ) ) {
      return true;
    }
	  return super.isApplied( effect );
  }

  // TODO -- Most of this looks like it would work generically in TimeVaryingMap.
  // TODO -- Move it there.
  @Override
  public boolean isApplied( Effect effect, Method method1, Method method2 ) {
    breakpoint();
    if ( !( effect instanceof EffectFunction ) ) {
      return false;
    }
    EffectFunction effectFunction = (EffectFunction)effect;
    if ( effectFunction == null || effectFunction.getMethod() == null ) {
      if ( Debug.isOn() )
        Debug.errln( this.getClass().getSimpleName() + ".isApplied(Effect="
                   + effect + ", Method=" + method1 + ", Method=" + method2
                   + ") called with no effect method! " + this );
      return false;
    }
    if ( effectFunction.hasTypeErrors() ) {
      if ( Debug.isOn() )
        Debug.errln( this.getClass().getSimpleName() + ".isApplied(Effect="
          + effect + ", Method=" + method1 + ", Method=" + method2
          + "): inconsistent EffectFunction arguments! " + this );
      return false;
    }

    // Is sendIf() applied
    if ( effectFunction.getMethod().equals( getSendIfMethod() ) ) {
      return isSendIfApplied( effectFunction );
    }

    // Is receive() applied
    if ( effectFunction.getMethod().getName().equals("receive") ) {
      return isReceiveApplied( (Parameter<Integer>)effectFunction.getArguments().get( 0 ) );
    }

    // Is method (send()?) applied?
    boolean isMethod1 = effectFunction.getMethod().equals(method1);
    boolean isMethod2 =  effectFunction.getMethod().equals( method2);
    if ( isMethod1  || isMethod2 ) {
      return isSetValueApplied( effectFunction );
    }
    
    return false;
  }

  public boolean isSendIfApplicable( EffectFunction effectFunction ) {
    boolean doSend = true;
    if ( effectFunction.getArguments() == null
         || effectFunction.getArguments().size() < 3 ) {
      doSend = false;
    } else {
      Object bo = effectFunction.getArguments().get( 2 );
      Boolean b = Expression.evaluate( bo, Boolean.class, false, false );
      if ( b == null ) doSend = false;
      else doSend = b.booleanValue();
    }
    return doSend;
  }
  
  // TODO -- This looks like it would work generically in TimeVaryingMap.
  // TODO -- Move it there.
  public boolean isSendIfApplied( EffectFunction effectFunction ) {
    boolean doSend = isSendIfApplicable( effectFunction );
    if ( !doSend ) return true;
    return isSetValueApplied( effectFunction );
  }
  
  public boolean isSetValueApplied( Object v, Object timepoint ) {
    Obj value = null;
    try {
      value = (Obj)v;
    } catch( Exception e ) {
      //e.printStackTrace();
    }
    if ( value != null ) {
      if ( timepoint instanceof Parameter ) {
        return hasValueAt( value, (Parameter)timepoint );
      } if ( timepoint instanceof Integer ) {
        return hasValueAt( value, (Integer)timepoint );
      }
    }
    return false;
  }
  // TODO -- This looks like it would work generically in TimeVaryingMap.
  // TODO -- Move it out of here.
  public boolean isSetValueApplied( EffectFunction effectFunction ) {
    if ( effectFunction.getMethod() == null ) return false;
    if ( effectFunction.getMethod().getName().startsWith( "receive" ) &&
         effectFunction.getArguments().size() < 2 ) {
      Debug.error(true, "Error!  isSetValueApplied() is not applicable for this receive effects with one argument! This call is a bug.");
      return true;
    }
    if ( effectFunction.getArguments() != null
         && effectFunction.getArguments().size() >= 2 ) {
      Pair< Parameter<Integer>, Obj > p =
          getTimeAndValueOfEffect( effectFunction, !isASendEffect( effectFunction ) );
      if ( p == null ) return false;
      Object o = p.second;
      Object a1 = p.first;
      Parameter< Integer > param = null;
      param = tryCastTimepoint( a1 );
      if ( param == null ) {
        param = tryCastTimepoint( o );
      }
      if ( param != null
           && param.getValue( false ) != null
           && Integer.class.isAssignableFrom( param.getValue( false )
                                                   .getClass() ) ) {
        Object t = (Parameter< Integer >)param;
        return isSetValueApplied( o, t );
      }
    }
    return false;
  }
  
  public boolean isASendEffect( EffectFunction effectFunction ) {
    if ( effectFunction.getMethod().equals( getSendMethod() ) ) return true;
    if ( effectFunction.getMethod().equals( getSendIfMethod() ) ) return true;
    assert( !effectFunction.getMethod().getName().toLowerCase().contains("send") );
    return false;
  }

  // static functions to get Methods for this class
  
  
	public static Method getSendMethod() {
	  return getSendWithParameterMethod();
	}
	public static Method getSendWithParameterMethod() {
		if (sendWithParameterMethod == null) {
			for (Method m : ObjectFlow.class.getMethods()) {
				if (m.getName().equals("send")
						&& m.getParameterTypes() != null
						&& m.getParameterTypes().length == 2
						&& m.getParameterTypes()[1] == Parameter.class) {
					sendWithParameterMethod = m;
					break;
				}
			}
		}
		assert sendWithParameterMethod != null;
		return sendWithParameterMethod;
	}

  public static Method getSendIfMethod() {
    if (sendIfMethod == null) {
      for (Method m : ObjectFlow.class.getMethods()) {
        if (m.getName().equals("sendIf")
            && m.getParameterTypes() != null
            && m.getParameterTypes().length == 3
            && m.getParameterTypes()[1] == Parameter.class) {
          sendIfMethod = m;
          break;
        }
      }
    }
    assert sendIfMethod != null;
    return sendIfMethod;
  }

  public static Method getReceiveMethod() {
    return getReceiveWithParameterMethod();
  }
  public static Method getReceiveWithParameterMethod() {
    if ( receiveWithParameterMethod != null ) return receiveWithParameterMethod;
    try {
      receiveWithParameterMethod =
          ObjectFlow.class.getMethod( "receive", new Class<?>[]{Parameter.class} );
    } catch ( SecurityException e ) {
      e.printStackTrace();
    } catch ( NoSuchMethodException e ) {
      e.printStackTrace();
    }
    assert receiveWithParameterMethod != null;
    return receiveWithParameterMethod;
  }
  
	protected static Collection< Method > initEffectMethods() {
	  // copy to avoid polluting the superclass's list
    effectMethods = new HashSet<Method>(TimeVaryingMap.initEffectMethods());
    Method m = getSendMethod();
    if ( m != null ) effectMethods.add( m );
//    m = getSendMethod2();
//    if ( m != null ) effectMethods.add( m );
    m = getSendIfMethod();
    if ( m != null ) effectMethods.add( m );
    m = getReceiveMethod();
    if ( m != null ) effectMethods.add( m );
//    m = getReceiveWithIntegerMethod();
//    if ( m != null ) effectMethods.add( m );
    return effectMethods;
  }

  // This looks the same as parent's getEffectMethods(), but it uses its own
  // effectMethods and initEffectMethods(). So, DO NOT DELETE.
  @Override
  public Collection< Method > getEffectMethods() {
    if ( effectMethods == null ) initEffectMethods();
    return effectMethods;
  }

  
  // accessors

  
  /**
   * @return the listeners
   */
  public List< ObjectFlow< Obj > > getListeners() {
    return listeners;
  }

  /**
   * @param listeners the listeners to set
   */
  public void setListeners( List< ObjectFlow< Obj > > listeners ) {
    this.listeners = listeners;
  }
  
}
