/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.Vector;

/**
 * @author bclement
 *
 */
public class TimeVaryingConstructorCall extends ConstructorCall {

  
  public boolean returnsTimeVaryingMap(boolean recursive, Set<Object> seen) {
    return TimeVaryingFunctionCall.returnsTimeVaryingMap( this, recursive, seen );
  }
  
  @Override
  public Object invoke( Object evaluatedObject,
                        Object[] evaluatedArgs ) {//throws IllegalArgumentException,
    return TimeVaryingFunctionCall.invoke( this, evaluatedObject, evaluatedArgs );
  }

  @Override
  public Object evaluateObject( boolean propagate ) throws ClassCastException,
                                                    IllegalAccessException,
                                                    InvocationTargetException,
                                                    InstantiationException {
    Throwable t = null;
    ClassCastException e1 = null;
    IllegalAccessException e2 = null;
    InvocationTargetException e3 = null;
    InstantiationException e4 = null;     
    Object o = null;
    
    try {
      o = super.evaluateObject( propagate );
    } catch ( ClassCastException e ) {
      e1 = e;
      t = e;
    } catch ( IllegalAccessException e ) {
      e2 = e;
      t = e;
    } catch ( InvocationTargetException e ) {
      e3 = e;
      t = e;
    } catch ( InstantiationException e ) {
      e4 = e;
      t = e;
    }
    
    if ( o == null ) o = object;
    TimeVaryingMap<?> map = TimeVaryingFunctionCall.evaluateAsTimeVaryingMap( o, true, null );
    if ( map != null ) o = map;
    
    if ( o == null && t != null ) {
      if ( e1 != null ) throw e1;
      if ( e2 != null ) throw e2;
      if ( e3 != null ) throw e3;
      if ( e4 != null ) throw e4;
    }
    return o;
  }
  
  @Override
  public Boolean hasTypeErrors( Object[] evaluatedArgs ) {
    return false;
    // TODO Auto-generated method stub
    //return super.hasTypeErrors( evaluatedArgs );
  }

  @Override
  public synchronized Boolean hasTypeErrors() {
    return false;
    // TODO Auto-generated method stub
    //return super.hasTypeErrors();
  }
  
  @Override
  public Object evaluateArg( Object unevaluatedArg, Class< ? > c,
                             boolean propagate ) throws ClassCastException,
                                                 IllegalAccessException,
                                                 InvocationTargetException,
                                                 InstantiationException {
    Throwable t = null;
    ClassCastException e1 = null;
    IllegalAccessException e2 = null;
    InvocationTargetException e3 = null;
    InstantiationException e4 = null;     
    Object o = null;
    
    try {
      o = super.evaluateArg( unevaluatedArg, c, propagate );
    } catch ( ClassCastException e ) {
      e1 = e;
      t = e;
    } catch ( IllegalAccessException e ) {
      e2 = e;
      t = e;
    } catch ( InvocationTargetException e ) {
      e3 = e;
      t = e;
    } catch ( InstantiationException e ) {
      e4 = e;
      t = e;
    }
    
    if ( o == null ) o = object;
    TimeVaryingMap<?> map = TimeVaryingFunctionCall.evaluateAsTimeVaryingMap( o, true, null );
    if ( map != null ) o = map;
    
    if ( o == null && t != null ) {
      if ( e1 != null ) throw e1;
      if ( e2 != null ) throw e2;
      if ( e3 != null ) throw e3;
      if ( e4 != null ) throw e4;
    }
    return o;
  }

  /**
   * @param constructor
   * @param returnType
   */
  public TimeVaryingConstructorCall( Constructor< ? > constructor,
                                     Class< ? > returnType ) {
    super( constructor, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param cls
   * @param returnType
   */
  public TimeVaryingConstructorCall( Class< ? > cls, Class< ? > returnType ) {
    super( cls, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param constructor
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object,
                                     Constructor< ? > constructor,
                                     Class< ? > returnType ) {
    super( object, constructor, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object, Class< ? > cls,
                                     Class< ? > returnType ) {
    super( object, cls, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param constructor
   * @param arguments
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object,
                                     Constructor< ? > constructor,
                                     Vector< Object > arguments,
                                     Class< ? > returnType ) {
    super( object, constructor, arguments, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param arguments
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object, Class< ? > cls,
                                     Vector< Object > arguments,
                                     Class< ? > returnType ) {
    super( object, cls, arguments, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param constructor
   * @param arguments
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object,
                                     Constructor< ? > constructor,
                                     Vector< Object > arguments,
                                     Call nestedCall, Class< ? > returnType ) {
    super( object, constructor, arguments, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param constructor
   * @param arguments
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object,
                                     Constructor< ? > constructor,
                                     Vector< Object > arguments,
                                     Parameter< Call > nestedCall,
                                     Class< ? > returnType ) {
    super( object, constructor, arguments, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param arguments
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object, Class< ? > cls,
                                     Vector< Object > arguments,
                                     Call nestedCall, Class< ? > returnType ) {
    super( object, cls, arguments, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param arguments
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object, Class< ? > cls,
                                     Vector< Object > arguments,
                                     Parameter< Call > nestedCall,
                                     Class< ? > returnType ) {
    super( object, cls, arguments, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param constructor
   * @param argumentsA
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object,
                                     Constructor< ? > constructor,
                                     Object[] argumentsA,
                                     Class< ? > returnType ) {
    super( object, constructor, argumentsA, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param argumentsA
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object, Class< ? > cls,
                                     Object[] argumentsA,
                                     Class< ? > returnType ) {
    super( object, cls, argumentsA, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param constructor
   * @param argumentsA
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object,
                                     Constructor< ? > constructor,
                                     Object[] argumentsA,
                                     ConstructorCall nestedCall,
                                     Class< ? > returnType ) {
    super( object, constructor, argumentsA, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param constructor
   * @param argumentsA
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object,
                                     Constructor< ? > constructor,
                                     Object[] argumentsA,
                                     Parameter< Call > nestedCall,
                                     Class< ? > returnType ) {
    super( object, constructor, argumentsA, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param argumentsA
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object, Class< ? > cls,
                                     Object[] argumentsA, Call nestedCall,
                                     Class< ? > returnType ) {
    super( object, cls, argumentsA, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param object
   * @param cls
   * @param argumentsA
   * @param nestedCall
   * @param returnType
   */
  public TimeVaryingConstructorCall( Object object, Class< ? > cls,
                                     Object[] argumentsA,
                                     Parameter< Call > nestedCall,
                                     Class< ? > returnType ) {
    super( object, cls, argumentsA, nestedCall, returnType );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param constructorCall
   */
  public TimeVaryingConstructorCall( ConstructorCall constructorCall ) {
    super( constructorCall );
  }

//  /**
//   * @param args
//   */
//  public static void main( String[] args ) {
//  }

}
