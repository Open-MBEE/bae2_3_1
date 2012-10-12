package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.event.Expression.Type;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import junit.framework.Assert;

/**
 * 
 */

/**
 * 
 */
public class FunctionCall implements HasParameters, Groundable {

  protected Method method = null;
  protected Object object = null; // object from which method is invoked
  protected Vector< Object > arguments = null; // arguments to method

  /**
   * A function call on the result of this function call.
   */
  protected Parameter<FunctionCall> nestedCall = null;
  
  /**
   * Construct a call to a static method.
   * @param method
   */
  public FunctionCall( Method method ) {
    this.method = method; // the method must be static
  }

  /**
   * Construct a call to a static method.
   * @param cls
   * @param methodName
   */
  public FunctionCall( Class<?> cls, String methodName ) {
    this.method = Utils.getMethodForArgTypes( cls, methodName, (Class<?>[])null ); 
  }

  /**
   * @param object
   * @param method
   */
  public FunctionCall( Object object, Method method ) {
    this.object = object;
    this.method = method;
  }

  public FunctionCall( Object object, Class<?> cls, String methodName ) {
    this( cls, methodName );
    this.object = object;
  }

  /**
   * @param object
   * @param method
   * @param arguments
   */
  public FunctionCall( Object object, Method method, Vector< Object > arguments ) {
    this.object = object;
    this.method = method;
    this.arguments = arguments;
    hasTypeErrors();
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   */
  public FunctionCall( Object object, Class<?> cls, String methodName,
                       Vector< Object > arguments ) {
    this.object = object;
    Object argArr[] = null;
    if ( !Utils.isNullOrEmpty( arguments ) ) {
      argArr = arguments.toArray();
    }
    this.method = Utils.getMethodForArgs( cls, methodName, argArr );
    this.arguments = arguments;
    hasTypeErrors();
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public FunctionCall( Object object, Method method, Vector< Object > arguments,
                       FunctionCall nestedCall ) {
    this(object, method, arguments);
    this.nestedCall = new Parameter<FunctionCall>("", null, nestedCall, null );
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   */
  public FunctionCall( Object object, Method method, Vector< Object > arguments,
                       Parameter<FunctionCall> nestedCall ) {
    this(object, method, arguments);
    
    this.nestedCall = nestedCall;
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   * @param nestedCall
   */
  public FunctionCall( Object object, Class<?> cls, String methodName,
                       Vector< Object > arguments,
                       FunctionCall nestedCall ) {
    this(object, cls, methodName, arguments);
    this.nestedCall = new Parameter<FunctionCall>("", null, nestedCall, null );
  }

  public FunctionCall( Object object, Class<?> cls, String methodName,
                       Vector< Object > arguments,
                       Parameter<FunctionCall> nestedCall ) {
    this(object, cls, methodName, arguments);
    this.nestedCall = nestedCall;
  }

  /**
   * @param object
   * @param method
   * @param arguments
   */
  public FunctionCall( Object object, Method method, Object argumentsA[] ) {
    this.object = object;
    this.method = method;
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
   * @param methodName
   * @param argumentsA
   */
  public FunctionCall( Object object, Class<?> cls, String methodName, Object argumentsA[] ) {
    this.object = object;
    this.method = Utils.getMethodForArgs( cls, methodName, argumentsA );
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    hasTypeErrors();
  }

//  /**
//   * @param object
//   * @param cls
//   * @param methodName
//   * @param argumentTypesA
//   */
//  public FunctionCall( Object object, Class<?> cls, String methodName, Class<?> argumentTypesA[] ) {
//    this.object = object;
//    this.method = Utils.getMethodForArgTypes( cls, methodName, argumentTypesA );
//    this.arguments = new Vector<Object>();
//    if ( argumentTypesA != null ) {
//      for ( Object o : argumentTypesA ) {
//        this.arguments.add( o );
//      }
//    }
//    hasTypeErrors();
//  }

  public FunctionCall( Object object, Method method, Object argumentsA[],
                       FunctionCall nestedCall ) {
    this.object = object;
    this.method = method;
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    this.nestedCall = new Parameter<FunctionCall>("", null, nestedCall, null );
    hasTypeErrors();
  }

  public FunctionCall( Object object, Method method, Object argumentsA[],
                       Parameter<FunctionCall> nestedCall ) {
    this.object = object;
    this.method = method;
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    this.nestedCall = nestedCall;
    hasTypeErrors();
  }

  public FunctionCall( Object object, Class<?> cls, String methodName,
                       Object argumentsA[], FunctionCall nestedCall ) {
    this( object, cls, methodName, argumentsA );
    this.nestedCall = new Parameter<FunctionCall>("", null, nestedCall, null );
  }
  public FunctionCall( Object object, Class<?> cls, String methodName,
                       Object argumentsA[], Parameter<FunctionCall> nestedCall ) {
    this( object, cls, methodName, argumentsA );
    this.nestedCall = nestedCall;
  }

//  public FunctionCall( Object object, Class<?> cls, String methodName,
//                       Class<?> argumentTypesA[], FunctionCall nestedCall ) {
//    this( object, cls, methodName, argumentTypesA );
//    this.nestedCall = nestedCall;
//  }

  /**
   * @param e
   */
  public FunctionCall( FunctionCall e ) {
    this.object = e.object;
    this.method = e.method;
    this.arguments = e.arguments;
    this.nestedCall = e.nestedCall;
    hasTypeErrors();
  }

  private Boolean hasTypeErrors() {
    if ( method == null ) return true;
    Class< ? >[] paramTypes = method.getParameterTypes();
    Assert.assertEquals( arguments.size(), paramTypes.length );
    // Code below is not right! The arguments may be expressions, the results of
    // whose evaluations may match, but they cannot be checked without evaluating.
//    for ( int i = 0; i < arguments.size(); i++ ) {
//      Class< ? > c = paramTypes[ i ];
//      Assert.assertTrue( c.isAssignableFrom( arguments.get( i ).getClass() ) );
//    }
    return false;
  }

  // Try to match arguments to parameters by evaluating or creating expressions.
  protected Object[] evaluateArgs( boolean propagate ) {
    Class< ? >[] paramTypes = method.getParameterTypes();
    assert ( arguments.size() == paramTypes.length || 
             ( method.isVarArgs() && 
               ( arguments.size() > paramTypes.length ||
                 paramTypes.length == 1 ) ) );
    Object argObjects[] = new Object[arguments.size()];
    for ( int i = 0; i < arguments.size(); ++i ) {
      Object unevaluatedArg = arguments.get( i );
      Class< ? > c = paramTypes[ i ];
      argObjects[i] = unevaluatedArg;
      if ( !c.isInstance( unevaluatedArg ) ) {
        if ( unevaluatedArg instanceof Expression ) {
          Expression< ? > expr = (Expression<?>)unevaluatedArg;
          if ( c.isInstance( expr.expression ) ) {
            argObjects[i] = expr.expression;
          } else {
            argObjects[i] = expr.evaluate( propagate );
          }
        } else if ( unevaluatedArg instanceof Parameter ) {
          Parameter<?> p = (Parameter<?>)unevaluatedArg;
          Object v = p.getValue( propagate );
          if ( v != null
               && c.isAssignableFrom( v.getClass() ) ) {
            argObjects[i] = v;
          }
        }

        if ( argObjects[i] != null &&
             !c.isAssignableFrom( argObjects[i].getClass() ) &&
             Utils.isSubclassOf( c, Expression.class ) ) {
          argObjects[i] = new Expression( argObjects[i] );
        }
      }
      assert( argObjects[i] == null || c.isInstance( argObjects[i] ) );
    }
    return argObjects;
  }
  
  public Object evaluate( boolean propagate ) { // throws IllegalArgumentException,
    // IllegalAccessException, InvocationTargetException {
    if ( propagate ) {
      if ( !ground( propagate, null ) ) {
        return null;
      }
    } else {
      if ( !isGrounded( false, null ) ) {
        return null;
      }
    }
    Object evaluatedArgs[] = evaluateArgs( propagate );
    Object result = null;
    try {
      Debug.outln( "About to invoke method from FunctionCall: " + this );
      if ( object != null ) {
        boolean io = object instanceof Parameter;
        boolean ii1 = method.getDeclaringClass().isAssignableFrom( object.getClass() );
        Debug.outln( object + " instanceof Parameter = " + io );
        Debug.outln( "method.getDeclaringClass()=" + method.getDeclaringClass()
                     + ".isAssignableFrom( " + object.getClass().getName()
                     + " ) = " + ii1 );
        if ( io ) {
          Object v = null;
          if ( propagate ) {
            v = ( (Parameter< ? >)object ).getValue();
          } else {
            v = ( (Parameter< ? >)object ).getValueNoPropagate();
          }
          boolean ii2 = true;
          if ( v != null ) {
            ii2 = method.getDeclaringClass().isAssignableFrom( v.getClass() );
            Debug.outln( "method.getDeclaringClass()=" + method.getDeclaringClass()
                         + ".isAssignableFrom( " + v.getClass() + " ) = " + ii2 );
          }
          if ( !ii1 && ii2 ) {
            object = v;
          }
        }
      }
      result = method.invoke( object, evaluatedArgs );// arguments.toArray() );
    } catch ( IllegalAccessException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( IllegalArgumentException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if ( result != null && nestedCall != null && nestedCall.getValue() != null ) {
      nestedCall.getValue().object = result;
      result = nestedCall.getValue().evaluate( propagate );
    }
    return result;
  }

  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep,
                             Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    boolean subbed = false;
    if ( p1 == object ) {
      object = p2;
      subbed = true;
    } else if ( object instanceof HasParameters ) {
      subbed = ( (HasParameters)object ).substitute( p1, p2, deep, seen );
    }
    for ( int i = 0; i < arguments.size(); ++i ) {
      Object a = arguments.get( i );
      if ( a == p1 ) {
        arguments.setElementAt( p2, i );
        subbed = true;
      } else if ( a instanceof HasParameters ) {
        boolean s = ( (HasParameters)a ).substitute( p1, p2, deep, seen );
        subbed = subbed || s;
      }
    }
    if ( nestedCall != null && nestedCall.getValue() != null ) {
      boolean s = nestedCall.getValue().substitute( p1, p2, deep, seen ); 
      subbed = subbed || s;
    }
    return subbed;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.HasParameters#getParameters(boolean)
   */
  @Override
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< Parameter< ? >> set = new HashSet< Parameter< ? >>();
    if ( object instanceof Parameter< ? > ) {
      set.add( (Parameter< ? >)object );
    }
    if ( deep && object instanceof HasParameters ) {
      HasParameters gotParameters = (HasParameters)object;
      set.addAll( gotParameters.getParameters( deep, seen ) );
    }
    if ( arguments != null ) {
      for ( int i = 0; i < arguments.size(); ++i ) {
        Object a = arguments.get( i );
        if ( a instanceof Parameter< ? > ) {
          set.add( (Parameter< ? >)a );
        } else if ( !deep && a instanceof Expression ) {
          Expression<?> e = (Expression<?>)a;
          if ( e.type == Type.Parameter ) {
            set.add( (Parameter< ? >)e.expression );
          }
        }
        if ( deep && a instanceof HasParameters ) {
          HasParameters gotParameters = (HasParameters)a;
          set.addAll( gotParameters.getParameters( deep, seen ) );
        }
      }
    }
    if ( nestedCall != null && nestedCall.getValue() != null ) {
      // REVIEW -- bother with adding nestedCall as a parameter?
      set.addAll( nestedCall.getValue().getParameters( deep, seen ) );
    }
    return set;
  }

  @Override
  public Set< Parameter< ? > > getFreeParameters( boolean deep,
                                                  Set< HasParameters > seen ) {
    Assert.assertFalse( "This method should not be called since a Function"
                        + " does not differentiate between free and dependent"
                        + " parameters.", true );
    return null;
  }
  @Override
  public void setFreeParameters( Set< Parameter< ? >> freeParams,
                                 boolean deep,
                                 Set< HasParameters > seen) {
    Assert.assertTrue( "This method is not supported!", false );
  }
  

  @Override
  public boolean isGrounded(boolean deep, Set< Groundable > seen) {
    if ( method == null ) return false;
    // Check types without throwing exception (like checkForTypeErrors().
    Class< ? >[] paramTypes = method.getParameterTypes();
    if ( paramTypes.length > 0
         && ( arguments == null || arguments.size() != paramTypes.length ) ) {
      return false;
    }
    // Check if arguments are grounded if groundable.  Ok for arguments to be null.
    if ( arguments != null ) {
      for ( Object o : arguments ) {
        if ( o != null && o instanceof Groundable
             && !( (Groundable)o ).isGrounded(deep, seen) ) {
          return false;
        }
      }
    }
    if ( nestedCall != null ) {
      if ( !nestedCall.isGrounded(deep, seen) ) return false;
    }
    return true;
  }

  @Override
  public boolean ground(boolean deep, Set< Groundable > seen) {
    boolean grounded = true;
    if ( method == null ) return false;
    // Check types without throwing exception (like checkForTypeErrors().
    Class< ? >[] paramTypes = method.getParameterTypes();
    if ( paramTypes.length > 0
         && ( arguments == null || arguments.size() != paramTypes.length ) )   {
      return false;
    }
    // Ground groundable arguments.  OK for arguments to be null.
    if ( arguments != null ) {
      for ( Object o : arguments ) {
        if ( o != null && o instanceof Groundable
             && !( (Groundable)o ).ground(deep, seen) ) {
          grounded = false;
        }
      }
    }
    if ( nestedCall != null ) {
      if ( !nestedCall.ground(deep, seen) ) {
        grounded = false;
      }
    }
    return grounded;
  }
  
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    if ( object != null ) {
      if ( object instanceof DurativeEvent ) {
        sb.append( ((DurativeEvent)object).getName() + "." );
      } else {
        sb.append( object.toString() + "." );
      }
    }
    if ( method == null ) {
      sb.append( "null" );
    } else {
      sb.append( method.getName() + "(" );
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

  @Override
  public boolean isStale() {
    for ( Parameter< ? > p : getParameters( false, new HashSet<HasParameters>() ) ) {
      if ( p.isStale() ) return true;
    }
    if ( nestedCall != null ) {
      if ( nestedCall.isStale() ) return true;
    }
    return false;
  }

  @Override
  public void setStale( boolean staleness ) {
    // TODO -- REVIEW -- Need anything here?
    assert false;
  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                               Set< HasParameters > seen ) {
    return getParameters( deep, seen ).contains( parameter );
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep,
                                  Set< HasParameters > seen ) {
    // REVIEW -- Is this just done by Events? Maybe throw
    // assertion that this method id not supported for ElaborationRule.
    return false;
  }

  
  // Getters and setters 
  
  
  /**
   * @return the method
   */
  public Method getMethod() {
    return method;
  }

  /**
   * @param method the method to set
   */
  public void setMethod( Method method ) {
    this.method = method;
  }

  /**
   * @return the object
   */
  public Object getObject() {
    return object;
  }

  /**
   * @param object the object to set
   */
  public void setObject( Object object ) {
    this.object = object;
  }

  /**
   * @return the arguments
   */
  public Vector< Object > getArguments() {
    return arguments;
  }

  /**
   * @param arguments the arguments to set
   */
  public void setArguments( Vector< Object > arguments ) {
    this.arguments = arguments;
  }

  /**
   * @return the nestedCall
   */
  public FunctionCall getNestedCall() {
    return (nestedCall == null ? null : nestedCall.getValue() );
  }

  /**
   * @param nestedCall the nestedCall to set
   */
  public void setNestedCall( FunctionCall nestedCall ) {
    if ( this.nestedCall == null ) {
      this.nestedCall = new Parameter<FunctionCall>("", null, nestedCall, null );
    } else {
      this.nestedCall.setValue( nestedCall );
    }
  }
  
}
