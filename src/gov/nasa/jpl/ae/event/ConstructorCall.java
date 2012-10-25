package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.util.Set;
import java.util.Vector;

/**
 * 
 */

/**
 * @author bclement
 * 
 */
public class ConstructorCall extends Call {

  protected Class<?> thisClass = null;
  protected Constructor<?> constructor = null;
  protected Object newObject = null;

  protected Object getEnclosingInstance() {
    return object;
  }
  
  /**
   * Construct a call to a static constructor.
   * @param constructor
   */
  public ConstructorCall( Constructor<?> constructor ) {
    this.constructor = constructor; // the constructor must be static
  }

  /**
   * Construct a call to a static constructor.
   * @param cls
   * @param constructorName
   */
  public ConstructorCall( Class<?> cls ) {
    thisClass = cls;
    this.constructor = Utils.getConstructorForArgTypes( cls, (Class<?>[])null ); 
  }

  /**
   * @param object
   * @param constructor
   */
  public ConstructorCall( Object object, Constructor<?> constructor ) {
    this.object = object;
    setConstructor( constructor );
  }

  public ConstructorCall( Object object, Class<?> cls ) {
    this( cls );
    this.object = object;
  }

  /**
   * @param object
   * @param constructor
   * @param arguments
   */
  public ConstructorCall( Object object, Constructor<?> constructor,
                          Vector< Object > arguments ) {
    this.object = object;
    setConstructor( constructor );
    this.arguments = arguments;
    hasTypeErrors();
  }

  /**
   * @param object
   * @param cls
   * @param constructorName
   * @param arguments
   */
  public ConstructorCall( Object object, Class<?> cls,
                          Vector< Object > arguments ) {
    this.object = object;
    this.arguments = arguments;
    this.constructor = getConstructor();
    hasTypeErrors();
  }

  /**
   * @param object
   * @param constructor
   * @param arguments
   * @param nestedCall
   */
  public ConstructorCall( Object object, Constructor<?> constructor, Vector< Object > arguments,
                          Call nestedCall ) {
    this(object, constructor, arguments);
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
  }

  /**
   * @param object
   * @param constructor
   * @param arguments
   * @param nestedCall
   */
  public ConstructorCall( Object object, Constructor<?> constructor, Vector< Object > arguments,
                       Parameter<Call> nestedCall ) {
    this(object, constructor, arguments);
    this.nestedCall = nestedCall;
  }

  /**
   * @param object
   * @param cls
   * @param constructorName
   * @param arguments
   * @param nestedCall
   */
  public ConstructorCall( Object object, Class<?> cls,
                       Vector< Object > arguments,
                       Call nestedCall ) {
    this(object, cls, arguments);
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
  }

  public ConstructorCall( Object object, Class<?> cls,
                       Vector< Object > arguments,
                       Parameter<Call> nestedCall ) {
    this(object, cls, arguments);
    this.nestedCall = nestedCall;
  }

  /**
   * @param object
   * @param constructor
   * @param arguments
   */
  public ConstructorCall( Object object, Constructor<?> constructor,
                          Object argumentsA[] ) {
    this.object = object;
    setConstructor( constructor );
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    hasTypeErrors();
  }

  /**
   * @param object
   * @param cls
   * @param argumentsA
   */
  public ConstructorCall( Object object, Class<?> cls,
                          Object argumentsA[] ) {
    this.object = object;
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    this.constructor = getConstructor();
    hasTypeErrors();
  }

  public ConstructorCall( Object object, Constructor<?> constructor,
                          Object argumentsA[],
                          ConstructorCall nestedCall ) {
    this( object, constructor, argumentsA );
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
    hasTypeErrors();
  }

  public ConstructorCall( Object object, Constructor<?> constructor,
                          Object argumentsA[],
                          Parameter<Call> nestedCall ) {
    this( object, constructor, argumentsA );
    this.nestedCall = nestedCall;
    hasTypeErrors();
  }

  public ConstructorCall( Object object, Class<?> cls,
                          Object argumentsA[], Call nestedCall ) {
    this( object, cls, argumentsA );
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
  }
  public ConstructorCall( Object object, Class<?> cls,
                          Object argumentsA[],
                          Parameter<Call> nestedCall ) {
    this( object, cls, argumentsA );
    this.nestedCall = nestedCall;
  }

  /**
   * @param constructorCall
   */
  public ConstructorCall( ConstructorCall constructorCall ) {
    this.object = constructorCall.object;
    this.constructor = constructorCall.constructor;
    this.arguments = constructorCall.arguments;
    this.nestedCall = constructorCall.nestedCall;
    hasTypeErrors();
  }

  @Override
  public Class<?>[] getParameterTypes() {
    return constructor.getParameterTypes();
  }
  
  @Override
  public Member getMember() {
    return constructor;
  }

  @Override
  public boolean isVarArgs() {
    return constructor.isVarArgs();
  }
  
  @Override
  public Object invoke( Object[] evaluatedArgs ) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
    newObject = constructor.newInstance( evaluatedArgs ); 
    return newObject;
  }
  
  @Override
  public Object evaluate( boolean propagate ) { // throws IllegalArgumentException,
    // REVIEW -- if this is buggy, consider making this a dependency.
    // Nested call can also be a dependency.
    if ( newObject != null && !isStale() && isGrounded( propagate, null ) ) {
      return newObject;
    }
    newObject = null;
    return super.evaluate( propagate );
  }
  
  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep,
                             Set<HasParameters> seen ) {
    if ( super.substitute( p1, p2, deep, seen ) ) {
      this.newObject = null;
      return true;
    }
    return false;
  }

  @Override
  public boolean ground( boolean deep, Set< Groundable > seen ) {
    if ( seen != null && seen.contains( this ) ) return true;
    if ( isGrounded( deep, null ) ) return true;
    this.newObject = null;
    return super.ground( deep, seen );
  }
  
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    if ( object != null ) {
      if ( object instanceof DurativeEvent ) {
        sb.append( ((DurativeEvent)object).getName() + " new." );
      } else {
        sb.append( object.toString() + " new." );
      }
    }
    if ( constructor == null ) {
      sb.append( "null" );
    } else {
      sb.append( constructor.getName() + "(" );
      boolean first = true;
      for ( Object a : arguments ) {
        if (first) {
          first = false;
        } else {
          sb.append(", ");
        }
        if ( a == null ) {
          sb.append( "null" );
        } else {
          sb.append( a.toString() );
        }
      }
      sb.append( ")" );
    }
    if ( nestedCall != null ) {
      sb.append( "." + nestedCall.toString() );
    }
    return sb.toString();
  }

  
  // Getters and setters   
  
  /**
   * @return the constructor
   */
  public Constructor<?> getConstructor() {
    Object argArr[] = null;
    if ( !Utils.isNullOrEmpty( arguments ) ) {
      argArr = arguments.toArray();
    }
    Pair< Constructor< ? >, Object[] > p =
        Utils.getConstructorForArgs( thisClass, argArr, object );
    this.constructor = p.first;
    return this.constructor;
  }
  
  /**
   * @param constructor the constructor to set
   */
  public void setConstructor( Constructor<?> constructor ) {
    this.constructor = constructor;
    this.thisClass = constructor.getDeclaringClass();
    this.newObject = null;
 }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#setObject(java.lang.Object)
   */
  @Override
  public void setObject( Object object ) {
    this.object = object;
    this.newObject = null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#setArguments(java.util.Vector)
   */
  @Override
  public void setArguments( Vector< Object > arguments ) {
    super.setArguments( arguments );
    this.newObject = null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#setNestedCall(gov.nasa.jpl.ae.event.Call)
   */
  @Override
  public void setNestedCall( Call nestedCall ) {
    super.setNestedCall( nestedCall );
    this.newObject = null;
  }
  
}
