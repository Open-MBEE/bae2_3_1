package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasDomain;
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
  //protected Object newObject = null;

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
  public ConstructorCall( Constructor<?> constructor,
                          Class<?> returnType ) {
    this.constructor = constructor; // the constructor must be static
    this.returnType = returnType;
  }

  /**
   * Construct a call to a static constructor.
   * @param cls
   * @param constructorName
   */
  public ConstructorCall( Class<?> cls,
                          Class<?> returnType ) {
    thisClass = cls;
    this.returnType = returnType;
    setConstructor( ClassUtils.getConstructorForArgTypes( cls, (Class<?>[])null ) ); 
  }

  /**
   * @param object
   * @param constructor
   */
  public ConstructorCall( Object object, Constructor<?> constructor,
                          Class<?> returnType ) {
    this.object = object;
    this.returnType = returnType;
    setConstructor( constructor );
  }

  public ConstructorCall( Object object, Class<?> cls,
                          Class<?> returnType ) {
    this( cls, returnType );
    this.object = object;
  }

  /**
   * @param object
   * @param constructor
   * @param arguments
   */
  public ConstructorCall( Object object, Constructor<?> constructor,
                          Vector< Object > arguments,
                          Class<?> returnType ) {
    this.object = object;
    setConstructor( constructor );
    this.arguments = arguments;
    this.returnType = returnType;
    hasTypeErrors();
  }

  /**
   * @param object
   * @param cls
   * @param constructorName
   * @param arguments
   */
  public ConstructorCall( Object object, Class<?> cls,
                          Vector< Object > arguments,
                          Class<?> returnType ) {
    this.object = object;
    this.thisClass = cls;
    this.arguments = arguments;
    this.constructor = getConstructor();
    this.returnType = returnType;
    hasTypeErrors();
  }

  /**
   * @param object
   * @param constructor
   * @param arguments
   * @param nestedCall
   */
  public ConstructorCall( Object object, Constructor<?> constructor, Vector< Object > arguments,
                          Call nestedCall,
                          Class<?> returnType ) {
    this(object, constructor, arguments, returnType);
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
  }

  /**
   * @param object
   * @param constructor
   * @param arguments
   * @param nestedCall
   */
  public ConstructorCall( Object object, Constructor<?> constructor, Vector< Object > arguments,
                       Parameter<Call> nestedCall,
                       Class<?> returnType ) {
    this(object, constructor, arguments, returnType);
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
                       Call nestedCall,
                       Class<?> returnType ) {
    this(object, cls, arguments, returnType);
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
  }

  public ConstructorCall( Object object, Class<?> cls,
                       Vector< Object > arguments,
                       Parameter<Call> nestedCall,
                       Class<?> returnType ) {
    this(object, cls, arguments, returnType);
    this.nestedCall = nestedCall;
  }

  /**
   * @param object
   * @param constructor
   * @param arguments
   */
  public ConstructorCall( Object object, Constructor<?> constructor,
                          Object argumentsA[],
                          Class<?> returnType ) {
    this.object = object;
    setConstructor( constructor );
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    this.returnType = returnType;
    hasTypeErrors();
  }

  /**
   * @param object
   * @param cls
   * @param argumentsA
   */
  public ConstructorCall( Object object, Class<?> cls,
                          Object argumentsA[],
                          Class<?> returnType ) {
    this.object = object;
    this.thisClass = cls;
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    this.constructor = getConstructor();
    this.returnType = returnType;
    hasTypeErrors();
  }

  public ConstructorCall( Object object, Constructor<?> constructor,
                          Object argumentsA[],
                          ConstructorCall nestedCall,
                          Class<?> returnType ) {
    this( object, constructor, argumentsA, returnType );
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
    hasTypeErrors();
  }

  public ConstructorCall( Object object, Constructor<?> constructor,
                          Object argumentsA[],
                          Parameter<Call> nestedCall,
                          Class<?> returnType ) {
    this( object, constructor, argumentsA, returnType );
    this.nestedCall = nestedCall;
    hasTypeErrors();
  }

  public ConstructorCall( Object object, Class<?> cls,
                          Object argumentsA[], Call nestedCall,
                          Class<?> returnType ) {
    this( object, cls, argumentsA, returnType );
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
  }
  public ConstructorCall( Object object, Class<?> cls,
                          Object argumentsA[],
                          Parameter<Call> nestedCall,
                          Class<?> returnType ) {
    this( object, cls, argumentsA, returnType );
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
    this.returnType = constructorCall.returnType;
    this.argHelper = constructorCall.argHelper;
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
    if ( constructor == null ) return new Class<?>[]{};
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
    try {
      returnValue = constructor.newInstance( args );
      //returnValue = newObject;
      if ( Debug.isOn() ) {
          System.out.println("ConstructorCall constructor = " + constructor.toGenericString());
          System.out.println("ConstructorCall args = " + args);
          System.out.println("ConstructorCall.invoke " + constructor.getName() + "("
                  + Utils.toString( evaluatedArgs, false )
                  + "): ConstructorCall{" + this + "} = " + returnValue );
      }
      evaluationSucceeded = true;
    } catch (Exception e ) {
      evaluationSucceeded = false;
      if ( Debug.isOn() ) {
        Debug.error(true, false, "ConstructorCall constructor = " + constructor.toGenericString());
        Debug.error(true, false, "ConstructorCall.invoke " + constructor.getName() + "("
                            + Utils.toString( evaluatedArgs, false )
                            + "): ConstructorCall{" + this + "} " + e.getMessage() );
        e.printStackTrace();
      }
      if ( Debug.isOn() ) {
        throw e;
      }
    }
    return returnValue; //newObject;
  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#calculateDomain(boolean, java.util.Set)
   */
  @Override
  public Domain< ? > calculateDomain( boolean propagate, Set< HasDomain > seen ) {
    assert(false); // Must be overridden!
    return null;
  }

  
//  @Override
//  public boolean isStale() {
//    if ( super.isStale() ) return true;
//    if ( possiblyStale( newObject ) ) return true;
//    return false;
//  }

//  @Override
//  public Object evaluate( boolean propagate ) throws IllegalAccessException, InvocationTargetException, InstantiationException { // throws IllegalArgumentException,
//    // REVIEW -- if this is buggy, consider making this a dependency.
//    // Nested call can also be a dependency.
//    if ( newObject != null && !isStale() && isGrounded( propagate, null ) ) {
//      evaluationSucceeded = true;
//      return newObject;
//    }
//    newObject = null;
//    return super.evaluate( propagate );
//  }
  
//  @Override
//  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep,
//                             Set<HasParameters> seen ) {
//    if ( super.substitute( p1, p2, deep, seen ) ) {
//      this.newObject = null;
//      setStale( true );
//      return true;
//    }
//    return false;
//  }

//  @Override
//  public boolean ground( boolean deep, Set< Groundable > seen ) {
//    if ( seen != null && seen.contains( this ) ) return true;
//    if ( isGrounded( deep, null ) ) return true;
//    this.returnValue = null;
//    setStale( true );
//    return super.ground( deep, seen );
//  }
  
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
    if ( this.constructor != null ) return this.constructor; 
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
    this.returnValue = null;
 }

//  /* (non-Javadoc)
//   * @see gov.nasa.jpl.ae.event.Call#setObject(java.lang.Object)
//   */
//  @Override
//  public void setObject( Object object ) {
//    this.object = object;
//    this.returnValue = null;
//    setStale( true );
//  }

//  /* (non-Javadoc)
//   * @see gov.nasa.jpl.ae.event.Call#setArguments(java.util.Vector)
//   */
//  @Override
//  public void setArguments( Vector< Object > arguments ) {
//    super.setArguments( arguments );
//    this.returnValue = null;
//    setStale( true );
//  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#setNestedCall(gov.nasa.jpl.ae.event.Call)
   */
  @Override
  public void setNestedCall( Call nestedCall ) {
    super.setNestedCall( nestedCall );
//    this.newObject = null;
    setStale( true );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#getReturnType()
   */
  @Override
  public Class< ? > getReturnType() {
    if ( returnType != null ) return returnType;
    return thisClass;
  }
  
}
