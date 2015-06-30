package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * 
 */

/**
 * 
 */
public class ConstructorCall extends Call {

  protected Class<?> thisClass = null;
  protected Constructor<?> constructor = null;
  protected Object newObject = null;

  protected Object getEnclosingInstance() {
    return object;
  }
  
  public boolean hasEnclosingClass() {
    if ( thisClass == null ) return false;
    return thisClass.getEnclosingClass() != null;
  }
  public boolean isNestedClass() {
    return hasEnclosingClass();
  }
  public boolean isInnerClass() {
    return hasEnclosingClass() && !isStatic();
  }
  public static boolean isStatic( Class<?> cls ) {
    if ( cls == null ) return false;
    return Modifier.isStatic( cls.getModifiers() );
  }
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#isStatic()
   */
  @Override
  public boolean isStatic() {
    return isStatic( thisClass );
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
    setConstructor( ClassUtils.getConstructorForArgTypes( cls, (Class<?>[])null ) ); 
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
    this.thisClass = cls;
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
    this.thisClass = cls;
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
    this.thisClass = constructorCall.thisClass;
    this.constructor = constructorCall.constructor;
    this.arguments = constructorCall.arguments;
    this.nestedCall = constructorCall.nestedCall;
    hasTypeErrors();
  }

  // TODO -- REVIEW -- should this call super.clone() all the way up to Object?
  @Override
  public ConstructorCall clone() {
    ConstructorCall c = new ConstructorCall(this);
    return c;
  }
  
  @Override
  public Class<?>[] getParameterTypes() {
    Class< ? >[] ctorTypes = constructor.getParameterTypes();
    if ( !isInnerClass() ) return ctorTypes;
    int newSize = Utils.isNullOrEmpty( ctorTypes ) ? 0 : ctorTypes.length - 1;
    Class< ? >[] newTypes =  new Class< ? >[ newSize ];
    for ( int i = 0; i < newSize; ++i ) {
      newTypes[ i ] = ctorTypes[i+1];
    }
    return newTypes;
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
  public Object invoke( Object evaluatedObject, Object[] evaluatedArgs ) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
    evaluationSucceeded = false;
    Object[] args = false ? new Object[]{evaluatedArgs} : evaluatedArgs; // handling this in calling method, evaluate()
    if ( isInnerClass() ) {
      Object[] newArgs = new Object[args.length+1];
      newArgs[0] = evaluatedObject;
      if ( args != null ) {
        for ( int i=0; i<args.length; ++i ) {
          newArgs[i+1] = args[i];
        }
        args = newArgs;
      }
    }
    newObject = constructor.newInstance( args );
    evaluationSucceeded = true;
    return newObject;
  }
  
  protected static boolean possiblyStale( Object obj ) {
    if ( obj == null || obj instanceof TimeVarying ) return true;
    if ( obj instanceof LazyUpdate && ((LazyUpdate)obj).isStale() ) return true;
    if ( obj instanceof Variable ) {
      Object v = ((Variable<?>)obj).getValue( false );
      if ( possiblyStale( v ) ) return true;
    }
    return false;
  }
  
  @Override
  public boolean isStale() {
    if ( super.isStale() ) return true;
    if ( possiblyStale( newObject ) ) return true;
    return false;
  }

  @Override
  public Object evaluate( boolean propagate ) { // throws IllegalArgumentException,
    // REVIEW -- if this is buggy, consider making this a dependency.
    // Nested call can also be a dependency.
    if ( newObject != null && !isStale() && isGrounded( propagate, null ) ) {
      evaluationSucceeded = true;
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
      setStale( true );
      return true;
    }
    return false;
  }

  @Override
  public boolean ground( boolean deep, Set< Groundable > seen ) {
    if ( seen != null && seen.contains( this ) ) return true;
    if ( isGrounded( deep, null ) ) return true;
    this.newObject = null;
    setStale( true );
    return super.ground( deep, seen );
  }
  
  @Override
  public Boolean hasTypeErrors() {
    if ( super.hasTypeErrors() ) return true;
    if ( thisClass != getReturnType() ) return true;
    return false;
  }
  
  @Override
  public String toString(boolean withHash, boolean deep, Set< Object > seen,
                         Map< String, Object > otherOptions) {

    StringBuffer sb = new StringBuffer();
    if ( object != null ) {
      if ( object instanceof DurativeEvent ) {
        sb.append( ((DurativeEvent)object).getName() + " new." );
      } else {
        sb.append( MoreToString.Helper.toString( object, withHash, deep, seen,
                                                 otherOptions ) );
        sb.append( " new." );
      }
    }
    if ( constructor == null ) {
      sb.append( "null" );
    } else {
      sb.append( constructor.getName() );// + "(" );
      sb.append( MoreToString.Helper.toString( arguments, withHash, deep, seen,
                                               otherOptions,
                                               MoreToString.PARENTHESES, true ) );
    }
    if ( nestedCall != null ) {
      sb.append( "." + nestedCall.toString( withHash, deep, seen, otherOptions) );
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
        ClassUtils.getConstructorForArgs( thisClass, argArr, object );
    this.constructor = p.first;
    return this.constructor;
  }
  
  /**
   * @param constructor the constructor to set
   */
  public void setConstructor( Constructor<?> constructor ) {
    this.constructor = constructor;
    setStale( true );
    if ( constructor != null ) {
      this.thisClass = constructor.getDeclaringClass();
    }
    this.newObject = null;
 }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#setObject(java.lang.Object)
   */
  @Override
  public void setObject( Object object ) {
    this.object = object;
    this.newObject = null;
    setStale( true );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#setArguments(java.util.Vector)
   */
  @Override
  public void setArguments( Vector< Object > arguments ) {
    super.setArguments( arguments );
    this.newObject = null;
    setStale( true );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#setNestedCall(gov.nasa.jpl.ae.event.Call)
   */
  @Override
  public void setNestedCall( Call nestedCall ) {
    super.setNestedCall( nestedCall );
    this.newObject = null;
    setStale( true );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#getReturnType()
   */
  @Override
  public Class< ? > getReturnType() {
    return thisClass;
  }
  
}
