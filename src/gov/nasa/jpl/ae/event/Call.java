package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import junit.framework.Assert;

public abstract class Call implements HasParameters, HasDomain, Groundable {

  /**
   * A function call on the result of this function call.
   */
  protected Parameter<Call> nestedCall = null;
  protected Object object = null; // object from which constructor is invoked
  protected Vector< Object > arguments = null; // arguments to constructor

  abstract public Class<?>[] getParameterTypes();
  abstract public Member getMember();
  abstract public Object invoke( Object[] evaluatedArgs ) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException;
  abstract public boolean isVarArgs();
  
  public Boolean hasTypeErrors() {
    if ( getMember() == null ) return true;
    Class< ? >[] paramTypes = getParameterTypes();
    if ( !isVarArgs() ) {
      //Assert.assertEquals( arguments.size(), paramTypes.length );
      if ( arguments.size() != paramTypes.length ) {
        return true;
      }
    } else if ( arguments.size() < paramTypes.length - 1 ) {
      return true;
    }
    // Code below is not right! The arguments may be expressions, the results of
    // whose evaluations may match, but they cannot be checked without evaluating.
//    for ( int i = 0; i < arguments.size(); i++ ) {
//      Class< ? > c = paramTypes[ i ];
//      Assert.assertTrue( c.isAssignableFrom( arguments.get( i ).getClass() ) );
//    }
    return false;
  }

  // TODO -- this overlaps a lot with FunctionCall.evaluate()
  // TODO -- consider an abstract Call class
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
    Object result = null;
    Object evaluatedArgs[] = evaluateArgs( propagate );
    try {
      if ( Debug.isOn() ) Debug.outln( "About to invoke a " + getClass().getSimpleName() + ": " + this );
      if ( object != null ) {
        boolean io = object instanceof Parameter;
        boolean ii1 = getMember().getDeclaringClass().isAssignableFrom( object.getClass() );
        if ( Debug.isOn() ) Debug.outln( object + " instanceof Parameter = " + io );
        if ( Debug.isOn() ) Debug.outln( "getDeclaringClass()=" + getMember().getDeclaringClass()
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
            ii2 = getMember().getDeclaringClass().isAssignableFrom( v.getClass() );
            if ( Debug.isOn() ) Debug.outln( "getDeclaringClass()=" + getMember().getDeclaringClass()
                         + ".isAssignableFrom( " + v.getClass() + " ) = " + ii2 );
          }
          if ( !ii1 && ii2 ) {
            object = v;
          }
        }
      }
      result = invoke( evaluatedArgs );// arguments.toArray() );
      //newObject = constructor.newInstance( evaluatedArgs );// arguments.toArray() );
    } catch ( IllegalAccessException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( IllegalArgumentException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( InstantiationException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if ( result != null && nestedCall != null && nestedCall.getValue() != null ) {
      nestedCall.getValue().object = result;
      result = nestedCall.getValue().evaluate( propagate );
    }
    if ( Debug.isOn() ) Debug.outln( "evaluate() returning " + result );
    return result;
  }

  // Try to match arguments to parameters by evaluating or creating expressions.
  // TODO -- is this necessary????
  protected Object[] evaluateArgs( boolean propagate ) {
    Class< ? >[] paramTypes = getParameterTypes();
    return FunctionCall.evaluateArgs( propagate, paramTypes, arguments,
                                      isVarArgs() );
  }

  // Try to match arguments to parameters by evaluating or creating expressions.
  public static Object[] evaluateArgs( boolean propagate,
                                       Class< ? >[] paramTypes,
                                       Vector< Object > args,
                                       boolean isVarArgs ) {
    boolean wasDebugOn = Debug.isOn();
    Debug.turnOff();
    // Class< ? >[] paramTypes = constructor.getParameterTypes();
    assert ( args.size() == paramTypes.length
             || ( isVarArgs && ( args.size() > paramTypes.length
                                 || paramTypes.length == 1 ) ) );
    Object argObjects[] = new Object[args.size()];
    for ( int i = 0; i < args.size(); ++i ) {
      Object unevaluatedArg = args.get( i );
      if ( Debug.isOn() ) Debug.outln("Call.evaluateArgs(): unevaluated arg = " + unevaluatedArg );
      Class< ? > c = paramTypes[ i ];
      argObjects[i] = unevaluatedArg;
      if ( Debug.isOn() ) Debug.outln("Call.evaluateArgs(): parameter type = " + c.getName() );
      if ( c.isInstance( unevaluatedArg ) ) {
        if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): " + c.getName() + ".isInstance("
            + unevaluatedArg + ") = true" );
      } else {
        if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): " + c.getName() + ".isInstance("
                     + unevaluatedArg + ") = false" );
        if ( unevaluatedArg instanceof Expression ) {
          if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): " + unevaluatedArg
                       + " is instance of Expression" );
          Expression< ? > expr = (Expression<?>)unevaluatedArg;
          if ( c.isInstance( expr.expression ) ) {
            argObjects[i] = expr.expression;
            if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): evaluated arg = Expression.expression = "
                         + argObjects[ i ] );
          } else {
            argObjects[i] = expr.evaluate( propagate );
            if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): evaluated arg = Expression.evaluate("
                         + propagate + ") = " + argObjects[ i ] );
          }
        } else if ( unevaluatedArg instanceof Parameter ) {
          if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): " + unevaluatedArg
                       + " is instance of Parameter" );
          Parameter<?> p = (Parameter<?>)unevaluatedArg;
          while ( true ) {
            Object v = p.getValue( propagate );
            if ( v != null
                 && ( c.isAssignableFrom( v.getClass() ) ||
                      ( c.isPrimitive() &&
                        Utils.classForPrimitive( c ).isAssignableFrom( v.getClass() ) ) ) ) {
              argObjects[i] = v;
              if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): evaluated arg = Parameter.getValue("
                           + propagate + ") = " + argObjects[ i ] );
              break;
            }
            if ( v instanceof Parameter ) {
              p = (Parameter< ? >)v;
            } else {
              break;
            }
          }
        }
        if ( argObjects[i] != null && 
             !c.isAssignableFrom( argObjects[i].getClass() ) &&
             Utils.isSubclassOf( c, Expression.class ) ) {
          argObjects[i] = new Expression< Object >( argObjects[i] );
          if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): evaluated arg wrapped in expression: "
                       + argObjects[ i ] );
        }
      }
      assert( argObjects[i] == null || c.isInstance( argObjects[i] ) );
    }
    if ( wasDebugOn ) Debug.turnOn();
    if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(" + args + ") = "
                 + Utils.toString( argObjects ) );
    return argObjects;
  }
  
  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep,
                             Set<HasParameters> seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    
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

  @Override
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set<HasParameters> seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    Set< Parameter< ? >> set = new HashSet< Parameter< ? >>();
    if ( object instanceof Parameter< ? > ) {
      set.add( (Parameter< ? >)object );
    }
    if ( deep && object instanceof HasParameters ) {
      HasParameters gotParameters = (HasParameters)object;
      set.addAll( gotParameters.getParameters( deep, seen ) );
    }
    if ( arguments != null ) {
      for ( int i = 0; i < arguments.size(); ++i  ) {
        Object a = arguments.get( i );
        if ( a instanceof Parameter< ? > ) {
          set.add( (Parameter< ? >)a );
        } else if ( !deep && a instanceof Expression ) {
          Expression<?> e = (Expression<?>)a;
          if ( e.type == Expression.Type.Parameter ) {
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
                                                  Set<HasParameters> seen ) {
    Assert.assertFalse( "This method should not be called since a Function"
                        + " does not differentiate between free and dependent"
                        + " parameters.", true );
    return null;
  }
  @Override
  public void setFreeParameters( Set< Parameter< ? >> freeParams,
                                 boolean deep,
                                 Set<HasParameters> seen ) {
    Assert.assertTrue( "This method is not supported!", false );
  }
  

  @Override
  public boolean isGrounded( boolean deep, Set< Groundable > seen ) {
    Pair< Boolean, Set< Groundable > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return true;
    seen = pair.second;
    if ( getMember() == null ) return false;
    // Check types without throwing exception (like checkForTypeErrors().
    Class< ? >[] paramTypes = getParameterTypes();
    if ( paramTypes.length > 0
         && ( arguments == null || arguments.size() != paramTypes.length ) ) {
      return false;
    }
    // Check if arguments are grounded if groundable.  Ok for arguments to be null.
    if ( arguments != null ) {
      for ( Object o : arguments ) {
        if ( o != null && o instanceof Groundable
             && !( (Groundable)o ).isGrounded( deep, seen ) ) {
          return false;
        }
      }
    }
    if ( nestedCall != null ) {
      if ( !nestedCall.isGrounded( deep, seen ) ) return false;
    }
    return true;
  }

  @Override
  public boolean ground( boolean deep, Set< Groundable > seen ) {
    Pair< Boolean, Set< Groundable > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return true;
    seen = pair.second;
    boolean grounded = true;
    if ( getMember() == null ) return false;
    // Check types without throwing exception (like checkForTypeErrors().
    Class< ? >[] paramTypes = getParameterTypes();
    if ( paramTypes.length > 0
         && ( arguments == null || arguments.size() != paramTypes.length ) )   {
      return false;
    }
    // Ground groundable arguments.  OK for arguments to be null.
    if ( arguments != null ) {
      for ( Object o : arguments ) {
        if ( o != null && o instanceof Groundable
             && !( (Groundable)o ).ground( deep, seen ) ) {
          grounded = false;
        }
      }
    }
    if ( nestedCall != null ) {
      if ( !nestedCall.ground( deep, seen ) ) {
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
    if ( getMember() == null ) {
      sb.append( "null" );
    } else {
      sb.append( getMember().getName() + "(" );
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
    for ( Parameter< ? > p : getParameters( false, null ) ) {
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
                               Set<HasParameters> seen ) {
    return getParameters( deep, seen ).contains( parameter );
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep,
                                  Set<HasParameters> seen ) {
    // REVIEW -- Is this just done by Events? Maybe throw
    // assertion that this method id not supported for ElaborationRule.
    return false;
  }

  // Getters and setters 
  
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
  public Call getNestedCall() {
    return (nestedCall == null ? null : nestedCall.getValue() );
  }

  /**
   * @param nestedCall the nestedCall to set
   */
  public void setNestedCall( Call nestedCall ) {
    if ( this.nestedCall == null ) {
      this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
    } else {
      this.nestedCall.setValue( nestedCall );
    }
  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.HasDomain#getDomain(boolean, java.util.Set)
   */
  @Override
  public Domain< ? > getDomain( boolean propagate, Set< HasDomain > seen ) {
    // TODO Auto-generated method stub
    return null;
  }

}
