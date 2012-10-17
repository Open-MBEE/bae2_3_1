/**
 * 
 */
package gov.nasa.jpl.ae.fuml;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import gov.nasa.jpl.ae.event.Effect;
import gov.nasa.jpl.ae.event.EffectFunction;
import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.util.Debug;

/**
 * @author bclement
 *
 */
public class ObjectFlow< Obj > extends TimeVaryingMap< Obj > {

	  /**
	   * For the convenience of referring to the effect method.
	   */
	  protected static Method sendMethod1 = getSendMethod1();
	  protected static Method sendMethod2 = getSendMethod2();

	  protected List< ObjectFlow< Obj > > listeners =
      new ArrayList< ObjectFlow< Obj > >();
	  protected Class< ? > type = null;
  
  /**
   * @param name
   * @param defaultValue
   */
  public ObjectFlow( String name ) {
    super( name, null );
  }

  /**
   * @param name
   * @param defaultValue
   */
  public ObjectFlow( String name, Class< ? > type ) {
    super( name, null );
    this.type = type;
  }

  protected void breakpoint() {
    if ( getName() != null && getName().contains( "16831" ) ) {
      return;
    }
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
  
  public void send( Obj o, Timepoint t ) {
    breakpoint();
    if ( type == null || type.isInstance( o ) ) {
      this.setValue( t, o );
      for ( ObjectFlow< Obj > f : listeners ) {
        f.send( o, t );
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

  public Obj receive( Timepoint t ) {
    breakpoint();
    if ( t == null ) return null;
    Obj o = this.getValue( t );
    this.setValue( t, null );
    return o;
  }
  
//  public Obj receive( Integer t ) {
//    breakpoint();
//    Obj o = this.getValue( t );
//    this.setValue( t, null );
//    return o;
//  }
  
  public boolean hasStuff( Timepoint t ) {
    breakpoint();
    if ( t == null ) return false;
    return getValue( t ) != null;
  }

  public boolean hasStuff( Integer t ) {
    breakpoint();
    if ( t == null ) return false;
    return getValue( t ) != null;
  }

  @Override
  public boolean isApplied( Effect effect ) {
    breakpoint();
    if ( effect == null ) return false;
    if ( isApplied(effect, getSendMethod1(), getSendMethod1()//getSendMethod2()
                   ) ) {
      return true;
    }
	  return super.isApplied( effect );
  }

  @Override
  public boolean isApplied( Effect effect, Method method1, Method method2 ) {
    breakpoint();
    if ( !( effect instanceof EffectFunction ) ) {
      return false;
    }
    EffectFunction effectFunction = (EffectFunction)effect;
    if ( effectFunction == null || effectFunction.getMethod() == null ) {
      Debug.errln( this.getClass().getSimpleName() + ".isApplied(Effect="
                   + effect + ", Method=" + method1 + ", Method=" + method2
                   + ") called with no effect method! " + this );
      return false;
    }
    boolean isMethod1 = effectFunction.getMethod().equals(method1);
    boolean isMethod2 =  effectFunction.getMethod().equals( method2);
    if ( isMethod1  || isMethod2 ) {
      if ( effectFunction.getArguments() != null
           && effectFunction.getArguments().size() >= 2 ) {
        Object o = effectFunction.getArguments().get( 0 );
        Object t = (Timepoint)effectFunction.getArguments().get( 1 );
        Obj value = null;
        try {
          value = (Obj)o;
        } catch( Exception e ) {
          //e.printStackTrace();
        }
        if ( value != null ) {
          if ( t instanceof Timepoint ) {
            return hasValueAt( value, (Timepoint)t );
          } if ( t instanceof Integer ) {
            return hasValueAt( value, (Integer)t );
          }
        }
//          if ( isMethod1 || t instanceof Timepoint ) {
//            return hasValueAt( value, t );
//            return value.equals( getValue( (Timepoint) t ) );
//          }
//          if ( t instanceof Integer ) {
//            return value.equals(getValue((Integer) t));
//          } else if ( t instanceof Parameter ) {
//            Object v = ((Parameter<?>)t).getValueNoPropagate();
//            if ( v instanceof Integer ) {
//              return value.equals(getValue((Integer)v));
//            }
//          }
//        }
      }
    }
    return false;
  }

	public static Method getSendMethod1() {
		if (sendMethod1 == null) {
			for (Method m : ObjectFlow.class.getMethods()) {
				if (m.getName().equals("send")
						&& m.getParameterTypes() != null
						&& m.getParameterTypes().length == 2
						&& m.getParameterTypes()[1] == Timepoint.class) {
					sendMethod1 = m;
				}
			}
		}
		return sendMethod1;
	}

	public static Method getSendMethod2() {
		if (sendMethod2 == null) {
			for (Method m : ObjectFlow.class.getMethods()) {
				if (m.getName().equals("send")
						&& m.getParameterTypes() != null
						&& m.getParameterTypes().length == 2
						&& m.getParameterTypes()[1] == Integer.class) {
					sendMethod2 = m;
				}
			}
		}
		return sendMethod2;
	}

/**
   * @return the listeners
   */
  public List< ObjectFlow< Obj >> getListeners() {
    return listeners;
  }

  /**
   * @param listeners the listeners to set
   */
  public void setListeners( List< ObjectFlow< Obj >> listeners ) {
    this.listeners = listeners;
  }

  /**
   * @return the type
   */
  public Class< ? > getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType( Class< ? > type ) {
    this.type = type;
  }
  
}
