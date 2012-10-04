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

  /**
   * Appends the specified ObjectFlow to the end of the list of listeners. 
   * @param objectFlow is the ObjectFlow to add.
   */
  public void addListener( ObjectFlow< Obj > objectFlow ) {
    getListeners().add( objectFlow );
  }
  
  public void send( Obj o, Timepoint t ) {
    if ( type == null || type.isInstance( o ) ) {
      this.setValue( t, o );
      for ( ObjectFlow< Obj > f : listeners ) {
        f.send( o, t );
      }
    }
  }
  
  public void send( Obj o, Integer t ) {
    if ( type == null || type.isInstance( o ) ) {
      this.setValue( t, o );
      for ( ObjectFlow< Obj > f : listeners ) {
        f.send( o, t );
      }
    }
  }

  public Obj receive( Timepoint t ) {
    Obj o = this.getValue( t );
    this.setValue( t, null );
    return o;
  }
  
  public Obj receive( Integer t ) {
    Obj o = this.getValue( t );
    this.setValue( t, null );
    return o;
  }
  
  public boolean hasStuff( Timepoint t ) {
    return getValue( t ) != null;
  }

  public boolean hasStuff( Integer t ) {
    return getValue( t ) != null;
  }

  @Override
  public boolean isApplied( Effect effect ) {
	  if (super.isApplied(effect)) {
		  return true;
	  }
	return isApplied(effect, getSendMethod1(), getSendMethod2());
  }
  
	public static Method getSendMethod1() {
		if (setValueMethod1 == null) {
			for (Method m : TimeVaryingMap.class.getMethods()) {
				if (m.getName().equals("setValue")
						&& m.getParameterTypes() != null
						&& m.getParameterTypes().length == 2
						&& m.getParameterTypes()[1] == Timepoint.class) {
					setValueMethod1 = m;
				}
			}
		}
		return setValueMethod1;
	}

	public static Method getSendMethod2() {
		if (setValueMethod2 == null) {
			for (Method m : TimeVaryingMap.class.getMethods()) {
				if (m.getName().equals("setValue")
						&& m.getParameterTypes() != null
						&& m.getParameterTypes().length == 2
						&& m.getParameterTypes()[1] == Integer.class) {
					setValueMethod2 = m;
				}
			}
		}
		return setValueMethod2;
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
