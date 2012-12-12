package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.ae.util.CompareUtils;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.MoreToString;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import junit.framework.Assert;

public abstract class Call extends HasIdImpl implements HasParameters,
                                                        HasDomain,
                                                        Groundable,
                                                        Comparable< Call >,
                                                        MoreToString {

  /**
   * A function call on the result of this function call.
   */
  protected Parameter<Call> nestedCall = null;
  protected Object object = null; // object from which constructor is invoked
  protected Vector< Object > arguments = null; // arguments to constructor
  protected Vector< Object > evaluatedArguments = null; // arguments to constructor

  abstract public Class< ? > getReturnType();
  abstract public Class<?>[] getParameterTypes();
  abstract public Member getMember();
  abstract public Object invoke( Object[] evaluatedArgs ) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException;
  abstract public boolean isVarArgs();
  
  @Override
  public void deconstruct() {
    if ( nestedCall != null ) {
      if ( nestedCall.getValue( false ) != null ) {
        nestedCall.getValue( false ).deconstruct();
        nestedCall.setValue( null );
      }
      nestedCall.deconstruct();
      //nestedCall = null;
    }
    if ( this.arguments != null ) {
      for ( Object a : arguments ) {
        if ( a instanceof Expression ) {
          ((Expression<?>)a).deconstruct();
        } else if ( a instanceof Parameter ) {
          if ( ( (Parameter<?>)a ).getOwner() == null ) {
            ( (Parameter<?>)a ).deconstruct();
          }
        }
      }
      this.arguments.clear();
      //arguments = null;
    }
    this.object = null; // Can't deconstruct since Call does not own it.
    if ( evaluatedArguments != null ) {
      this.evaluatedArguments.clear(); // Can't deconstruct since Call does not own them.
    }
  }

  
  public Boolean hasTypeErrors() {
    if ( getMember() == null ) return true;
    Class< ? >[] paramTypes = getParameterTypes();
    if ( !isVarArgs() ) {
      //Assert.assertEquals( arguments.size(), paramTypes.length );
      if ( arguments.size() != paramTypes.length ) {
        return true;
      }
    } else if ( arguments.size() < paramTypes.length - 1 ) {
      this.compareTo( this );
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

  @Override
  public int compareTo( Call o ) {
    return compareTo( o, true );
  }
  public int compareTo( Call o, boolean checkId ) {
    if ( this == o ) return 0;
    if ( o == null ) return -1;
    if ( checkId ) return CompareUtils.compare( getId(), o.getId() );
    int compare = 0;//super.compareTo( o );
    compare = CompareUtils.compare( getMember(), o.getMember(), true );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( getParameterTypes(), o.getParameterTypes(), true );
    if ( compare != 0 ) return compare;
//    compare = Utils.compareTo( getReturnType(), o.getReturnType(), true );
//    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( getClass().getName(), o.getClass().getName() );
    if ( compare != 0 ) return compare;
    // TODO -- would like to skip this since it changes.
    Debug.errln( "Call.compareTo comparing value information." );
    compare = CompareUtils.compare( arguments, o.arguments, true );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( object, o.object, true );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( this, o );
    if ( compare != 0 ) return compare;
    return compare;    
  }
  
  // TODO -- consider an abstract Call class
  public Object evaluate( boolean propagate ) { // throws IllegalArgumentException,
    // IllegalAccessException, InvocationTargetException {
    if ( propagate ) {
      if ( !ground( propagate, null ) ) {
        //return null;
      }
    } 
    //else {
    //  if ( !isGrounded( false, null ) ) {
      //  return null;
      //}
    //}
    Object result = null;
    
    // evaluate the arguments before invoking the method on them
    Object evaluatedArgs[] = evaluateArgs( propagate );
    
    // evaluate the object, whose method will be invoked from a nested call
    if ( nestedCall != null && nestedCall.getValue( propagate ) != null ) {
      // REVIEW -- if this is buggy, consider wrapping object in a Parameter and
      // making this a dependency.  Cached newObject of constructor is similar.
//    if ( propagate || object == null ) {
      object = Expression.evaluate( nestedCall.getValue( propagate ), null,
                                    propagate, false );
      //      }
    }
    try {
      if ( Debug.isOn() ) Debug.outln( "About to invoke a "
                                       + getClass().getSimpleName() + ": "
                                       + this );
      // Make sure we have the right object from which to invoke the member.  
      object = Expression.evaluate( object, getMember().getDeclaringClass(),
                                    propagate, true );
//      if ( object != null ) {
//        boolean io = object instanceof Parameter;
//        boolean ii1 = getMember().getDeclaringClass().isAssignableFrom( object.getClass() );
//        if ( Debug.isOn() ) Debug.outln( object + " instanceof Parameter = " + io );
//        if ( Debug.isOn() ) Debug.outln( "getDeclaringClass()=" + getMember().getDeclaringClass()
//                     + ".isAssignableFrom( " + object.getClass().getName()
//                     + " ) = " + ii1 );
//        if ( io ) {
//          Object v = null;
//          if ( propagate ) {
//            v = ( (Parameter< ? >)object ).getValue();
//          } else {
//            v = ( (Parameter< ? >)object ).getValueNoPropagate();
//          }
//          boolean ii2 = true;
//          if ( v != null ) {
//            ii2 = getMember().getDeclaringClass().isAssignableFrom( v.getClass() );
//            if ( Debug.isOn() ) Debug.outln( "getDeclaringClass()=" + getMember().getDeclaringClass()
//                         + ".isAssignableFrom( " + v.getClass() + " ) = " + ii2 );
//          }
//          if ( !ii1 && ii2 ) {
//            object = v;
//          }
//        }
//      }
      if ( this instanceof ConstructorCall) {
        ConstructorCall cc = (ConstructorCall) this;
        if ( cc.thisClass.getEnclosingClass() != null && !Modifier.isStatic( cc.thisClass.getModifiers() )) {
          Object[] arr = new Object[evaluatedArgs.length + 1];
          arr[0] = object;
          for ( int i = 1; i<=evaluatedArgs.length; ++i) {
            arr[i] = evaluatedArgs[i-1];
          }
          evaluatedArgs = arr;
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
//    if ( result != null && nestedCall != null && nestedCall.getValue() != null ) {
//      nestedCall.getValue().object = result;
//      result = nestedCall.getValue().evaluate( propagate );
//    }
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
      if ( paramTypes.length == 0 ) {
        System.err.println("evaluateArgs() " + args + " don't match parameters " + Utils.toString(paramTypes, false) );
        break;
      }
      Class< ? > c = paramTypes[ Math.min(i,paramTypes.length-1) ];
      //Class< ? > c = paramTypes[ i ];
      argObjects[i] = Expression.evaluate( unevaluatedArg, c, propagate, true );
//      if ( Debug.isOn() ) Debug.outln("Call.evaluateArgs(): parameter type = " + c.getName() );
//      if ( c.isInstance( unevaluatedArg ) ) {
//        if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): " + c.getName() + ".isInstance("
//            + unevaluatedArg + ") = true" );
//      } else {
//        if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): " + c.getName() + ".isInstance("
//                     + unevaluatedArg + ") = false" );
//        if ( unevaluatedArg instanceof Expression ) {
//          if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): " + unevaluatedArg
//                       + " is instance of Expression" );
//          Expression< ? > expr = (Expression<?>)unevaluatedArg;
//          if ( c.isInstance( expr.expression ) ) {
//            argObjects[i] = expr.expression;
//            if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): evaluated arg = Expression.expression = "
//                         + argObjects[ i ] );
//          } else {
//            argObjects[i] = expr.evaluate( propagate );
//            if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): evaluated arg = Expression.evaluate("
//                         + propagate + ") = " + argObjects[ i ] );
//          }
//        } else if ( unevaluatedArg instanceof Parameter ) {
//          if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): " + unevaluatedArg
//                       + " is instance of Parameter" );
//          Parameter<?> p = (Parameter<?>)unevaluatedArg;
//          while ( true ) {
//            Object v = p.getValue( propagate );
//            if ( v != null
//                 && ( c.isAssignableFrom( v.getClass() ) ||
//                      ( c.isPrimitive() &&
//                        Utils.classForPrimitive( c ).isAssignableFrom( v.getClass() ) ) ) ) {
//              argObjects[i] = v;
//              if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): evaluated arg = Parameter.getValue("
//                           + propagate + ") = " + argObjects[ i ] );
//              break;
//            }
//            if ( v instanceof Parameter ) {
//              p = (Parameter< ? >)v;
//            } else {
//              break;
//            }
//          }
//        }
//        if ( argObjects[i] != null && 
//             !c.isAssignableFrom( argObjects[i].getClass() ) &&
//             Utils.isSubclassOf( c, Expression.class ) ) {
//          argObjects[i] = new Expression< Object >( argObjects[i] );
//          if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(): evaluated arg wrapped in expression: "
//                       + argObjects[ i ] );
//        }
//      }
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

    // TODO -- use HasParameters.Helper!!
    boolean subbed = false;
    if ( p1 == object ) {
      object = p2;
      subbed = true;
    }
    if ( HasParameters.Helper.substitute( object, p1, p2, deep, seen, true )) {
      subbed = true;
    }
    if ( HasParameters.Helper.substitute( arguments, p1, p2, deep, seen, true )) {
      subbed = true;
    }
    if ( p1 == nestedCall ) {
      nestedCall = (Parameter< Call >)p2;
      subbed = true;
    }
    if ( HasParameters.Helper.substitute( nestedCall, p1, p2, deep, seen, true ) ) {
      subbed = true;
    }
    return subbed;
  }

  @Override
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set<HasParameters> seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    Set< Parameter< ? > > set = new HashSet< Parameter< ? >>();
    set = Utils.addAll( set, HasParameters.Helper.getParameters( object, deep, seen, true ) );
    set = Utils.addAll( set, HasParameters.Helper.getParameters( arguments, deep, seen, true ) );
    if ( nestedCall != null ) {//&& nestedCall.getValue() != null ) {
      // REVIEW -- bother with adding nestedCall as a parameter?
      set = Utils.addAll( set, HasParameters.Helper.getParameters( nestedCall, deep, seen, true ) );
//      set = Utils.addAll( set, nestedCall.getValue().getParameters( deep, seen ) );
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
    int ecLength = 0;
    if ( this instanceof ConstructorCall) {
      ConstructorCall cc = (ConstructorCall) this;
      if ( cc.thisClass.getEnclosingClass() != null && !Modifier.isStatic( cc.thisClass.getModifiers() )) {
        ecLength = 1;
      }
    }
    if ( paramTypes.length > ecLength 
         && ( arguments == null || arguments.size() - ecLength != paramTypes.length ) ) {
      
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
    if ( nestedCall == null ) {
//      if ( !Modifier.isStatic( getMember().getModifiers() ) &&
//           object == null ) return false;
    } else {
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
      } else if ( object == null ) {
        if ( nestedCall.getValue(true) == null ) {
          grounded = false;
        } else {
          object = nestedCall.getValue(true).evaluate( deep );
          if ( object == null ) grounded = false;
        }
      }
    }
    return grounded;
  }
  
  @Override
  public String toString() {
    return MoreToString.Helper.toString( this );
  }
  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen ) {
    return toString( withHash, deep, seen, null );
  }
  @Override
  public String toString(boolean withHash, boolean deep, Set< Object > seen,
                         Map< String, Object > otherOptions) {
    Pair< Boolean, Set< Object > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) deep = false;
    seen = pair.second;
    StringBuffer sb = new StringBuffer();
    if ( nestedCall != null ) {
      sb.append( //"nested::" + 
                 nestedCall.toString(withHash, deep, seen,
                                     otherOptions) );
      if ( object != null ) {
        sb.append( "-->");
      } else {
        sb.append( "." );
      }
    }
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
      sb.append( getMember().getName() );
      sb.append( MoreToString.Helper.toString( arguments, withHash, deep, seen,
                                               otherOptions,
                                               MoreToString.PARENTHESES, true ) );
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
  public Vector< Object > getEvaluatedArguments() {
    return evaluatedArguments;
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
    return (nestedCall == null ? null : nestedCall.getValue(false) );
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
