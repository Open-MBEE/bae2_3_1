package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.event.Expression.Type;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import junit.framework.Assert;

/**
 * 
 */

/**
 * @author bclement
 * 
 */
public class FunctionCall implements HasParameters, Groundable {

  protected Method method = null;
  protected Object object = null; // object from which method is invoked
  protected Vector< Object > arguments = null; // arguments to method

  /**
   * A function call on the result of this function call.
   */
  protected FunctionCall nestedCall = null;
  
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

  public FunctionCall( Object object, Method method, Object argumentsA[], FunctionCall nestedCall ) {
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
    this.nestedCall = nestedCall;
  }

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
        if ( !c.isAssignableFrom( argObjects[i].getClass() ) &&
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
    if ( !ground() ) {
      return null;
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
          Object v = ( (Parameter< ? >)object ).getValue();
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
    if ( result != null && nestedCall != null ) {
      nestedCall.object = result;
      result = nestedCall.evaluate( propagate );
    }
    return result;
  }

  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep ) {
    boolean subbed = false;
    if ( p1 == object ) {
      object = p2;
      subbed = true;
    } else if ( object instanceof HasParameters ) {
      subbed = ( (HasParameters)object ).substitute( p1, p2, deep );
    }
    for ( int i = 0; i < arguments.size(); ++i ) {
      Object a = arguments.get( i );
      if ( a == p1 ) {
        arguments.setElementAt( p2, i );
        subbed = true;
      } else if ( a instanceof HasParameters ) {
        boolean s = ( (HasParameters)a ).substitute( p1, p2, deep );
        subbed = subbed || s;
      }
    }
    if ( nestedCall != null ) {
      boolean s = nestedCall.substitute( p1, p2, deep ); 
      subbed = subbed || s;
    }
    return subbed;
  }

  @Override
  public Set< Parameter< ? > > getParameters( boolean deep ) {
    Set< Parameter< ? >> set = new TreeSet< Parameter< ? >>();
    if ( object instanceof Parameter< ? > ) {
      set.add( (Parameter< ? >)object );
    }
    if ( deep && object instanceof HasParameters ) {
      HasParameters gotParameters = (HasParameters)object;
      set.addAll( gotParameters.getParameters( deep ) );
    }
    if ( arguments != null ) {
      for ( int i = 0; i < arguments.size(); ++i ) {
        Object a = arguments.get( i );
        if ( a instanceof Parameter< ? > ) {
          set.add( (Parameter< ? >)a );
        }
        if ( deep && a instanceof HasParameters ) {
          HasParameters gotParameters = (HasParameters)a;
          set.addAll( gotParameters.getParameters( deep ) );
        }
      }
    }
    if ( nestedCall != null ) {
      set.addAll( nestedCall.getParameters( deep ) );
    }
    return set;
  }

  @Override
  public Set< Parameter< ? > > getFreeParameters( boolean deep ) {
    Assert.assertFalse( "This method should not be called since a Function"
                        + " does not differentiate between free and dependent"
                        + " parameters.", true );
    return null;
  }
  @Override
  public void setFreeParameters( Set< Parameter< ? >> freeParams ) {
    Assert.assertTrue( "This method is not supported!", false );
  }
  

  @Override
  public boolean isGrounded() {
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
             && !( (Groundable)o ).isGrounded() ) {
          return false;
        }
      }
    }
    if ( nestedCall != null ) {
      if ( !nestedCall.isGrounded() ) return false;
    }
    return true;
  }

  @Override
  public boolean ground() {
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
             && !( (Groundable)o ).ground() ) {
          grounded = false;
        }
      }
    }
    if ( nestedCall != null ) {
      if ( !nestedCall.ground() ) {
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
    for ( Parameter< ? > p : getParameters( false ) ) {
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
  public boolean hasParameter( Parameter< ? > parameter, boolean deep ) {
    return getParameters( deep ).contains( parameter );
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep ) {
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
    return nestedCall;
  }

  /**
   * @param nestedCall the nestedCall to set
   */
  public void setNestedCall( FunctionCall nestedCall ) {
    this.nestedCall = nestedCall;
  }
  
}
