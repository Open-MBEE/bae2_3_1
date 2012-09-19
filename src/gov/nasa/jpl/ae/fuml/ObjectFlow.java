/**
 * 
 */
package gov.nasa.jpl.ae.fuml;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.Timepoint;

/**
 * @author bclement
 *
 */
public class ObjectFlow< Obj > extends TimeVaryingMap< Obj > {

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
