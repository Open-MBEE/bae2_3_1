package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.mbee.util.MethodCall;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import junit.framework.Assert;

public abstract class Call extends HasIdImpl implements HasParameters,
                                                        HasDomain,
                                                        Groundable,
                                                        Comparable< Call >,
                                                        MoreToString,
                                                        Cloneable,
                                                        Wraps<Object> {

  /**
   * A function call on the result of this function call.
   */
  protected Parameter<Call> nestedCall = null;
  protected Object object = null; // object from which constructor is invoked
  protected Vector< Object > arguments = null; // arguments to constructor
  protected Vector< Object > evaluatedArguments = null; // arguments to constructor
  protected boolean evaluationSucceeded = false;
  
  abstract public Class< ? > getReturnType();
  abstract public Class<?>[] getParameterTypes();
  abstract public Member getMember();
  abstract public Object invoke( Object obj, Object[] evaluatedArgs ) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException;
  abstract public boolean isVarArgs();
  abstract public boolean isStatic();
  
  public Call() {}
  
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

  public Boolean hasTypeErrors( Object[] evaluatedArgs ) {
    boolean gotErrors = hasTypeErrors();
    int numEvalArgs = 0;
    if ( evaluatedArgs != null ) {
      numEvalArgs = evaluatedArgs.length;
    }
    if ( gotErrors || evaluatedArgs == null || numEvalArgs == 0 ) return gotErrors;
    for ( int i = 0; !gotErrors && i < evaluatedArgs.length; i++ ) {
      //Class< ? > c = getParameterTypes()[ i ];
      Class< ? > c = getParameterTypes()[ Math.min(i,getParameterTypes().length-1) ];
      if ( c != null ) {
          Class< ? > np = ClassUtils.classForPrimitive( c );
          if ( np != null ) c = np;
      }
      if ( c == null || c.equals( Object.class ) ) continue;
      if ( i >= getParameterTypes().length-1 && isVarArgs() ) {
        if ( !c.isArray() ) {
          Debug.error( true, true, "class " + c.getSimpleName() + " should be a var arg array!" );
        } else {
          c = c.getComponentType();
        }
      }
      if ( evaluatedArgs[ i ] == null ) {
        if ( c.isPrimitive() ) {
          gotErrors = true; 
        }
      } else if ( !c.isAssignableFrom( evaluatedArgs[ i ].getClass() ) ) {
        gotErrors = true;
      }
    }
    return gotErrors;
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
      //this.compareTo( this );  // why was this here? to see if any exceptions would be raised?
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
  
  public Object evaluate( boolean propagate ) { // throws IllegalArgumentException,
    return evaluate(propagate, true);
  }
  
  // TODO -- consider an abstract Call class
  public Object evaluate( boolean propagate, boolean doEvalArgs) { // throws IllegalArgumentException,
    evaluationSucceeded = false;
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
    Object evaluatedArgs[] = null;
    
    if (doEvalArgs) {
      evaluatedArgs = evaluateArgs( propagate );
    }
    else {
      evaluatedArgs = arguments.toArray();
    }
    
    // evaluate the object, whose method will be invoked from a nested call
    if ( nestedCall != null && nestedCall.getValue( propagate ) != null ) {
      // REVIEW -- if this is buggy, consider wrapping object in a Parameter and
      // making this a dependency.  Cached newObject of constructor is similar.
//    if ( propagate || object == null ) {
      object = Expression.evaluate( nestedCall.getValue( propagate ), null,
                                    propagate, false );
      //      }
    }
    Object evaluatedObj = object;
    try {
      if ( Debug.isOn() ) Debug.outln( "About to invoke a "
                                       + getClass().getSimpleName() + ": "
                                       + this );
      // Make sure we have the right object from which to invoke the member.
      Member m = getMember();
      Class<?> cls = ( m == null ? null : m.getDeclaringClass() );
      evaluatedObj = Expression.evaluate( object, cls, propagate, true );
      
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
          arr[0] = evaluatedObj;
          for ( int i = 1; i<=evaluatedArgs.length; ++i) {
            arr[i] = evaluatedArgs[i-1];
          }
          evaluatedArgs = arr;
        }
      }
      result = invoke( evaluatedObj, evaluatedArgs );// arguments.toArray() );
      //newObject = constructor.newInstance( evaluatedArgs );// arguments.toArray() );
    } catch ( IllegalAccessException e ) {
      evaluationSucceeded = false;
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( IllegalArgumentException e ) {
      evaluationSucceeded = false;
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      evaluationSucceeded = false;
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( InstantiationException e ) {
      evaluationSucceeded = false;
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
    if( args == null ) {
      Debug.error("Error! args is null!");
      return null;
    }
    boolean wasDebugOn = Debug.isOn();
    //Debug.turnOff();
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
      if ( c != null ) {
          Class< ? > np = ClassUtils.classForPrimitive( c );
          if ( np != null ) c = np;
      }
      if ( c != null && c.equals( Object.class ) ) c = null;
      if ( c != null && i >= paramTypes.length-1 && isVarArgs ) {
        if ( !c.isArray() ) {
          Debug.error( true, true, "class " + c.getSimpleName() + " should be a var arg array!" );
        } else {
          c = c.getComponentType();
        }
      }
      argObjects[i] = Expression.evaluate( unevaluatedArg, c, propagate, true );
      if (!( argObjects[i] == null || c == null || c.isInstance( argObjects[i] ) )) {
        Debug.error( true, argObjects[ i ] +
                           ( argObjects[ i ] == null ?
                             "" : " of type " + argObjects[ i ].getClass().getCanonicalName() )
                           + " is not an instance of " + c.getSimpleName() );
//      } else if ( argObjects[i] != null && c != null && !c.equals( argObjects[i].getClass() ) ) {
//          Object x = null;
//          x = ClassUtils.coerce( argObjects[ i ], c, true );
//          if ( x != null ) argObjects[ i ] = x;
      }
    }
    if ( wasDebugOn ) Debug.turnOn();
    if ( Debug.isOn() ) Debug.outln( "Call.evaluateArgs(" + args + ") = "
                 + Utils.toString( argObjects ) );
    return argObjects;
  }
  
  @Override
  public Class< ? > getType() {
    return getReturnType();
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Wraps#getTypeNameForClassName(java.lang.String)
   */
  @Override
  public String getTypeNameForClassName( String className ) {
    return getType().getSimpleName();
  }

  @Override
  public Class< ? > getPrimitiveType() {
    Class< ? > c = null;
    if ( getType() != null ) {
      c = ClassUtils.primitiveForClass( getType() );
      Object r = evaluate( false );
      if ( c == null && r != null
           && Wraps.class.isInstance( r ) ) {// isAssignableFrom( getType() ) ) {
        c = ( (Wraps< ? >)r ).getPrimitiveType();
      }
    }
    return c;
  }

  @Override
  public Object getValue( boolean propagate ) {
    return evaluate( propagate );
  }

  @Override
  public void setValue( Object value ) {
    // TODO -- this could be used to set free values when inverting the function
    Debug.error( false, "Error! Call.setValue() is not yeet supported!" );
  }
  
  /**
   * @return the evaluationSucceeded
   */
  public boolean didEvaluationSucceed() {
    return evaluationSucceeded;
  }

  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep,
                             Set<HasParameters> seen ) {
    return substitute( p1, (Object)p2, deep, seen );
  }
  @Override
  public boolean substitute( Parameter< ? > p1, Object p2, boolean deep,
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
    if ( !subbed && HasParameters.Helper.substitute( object, p1, p2, deep, seen, true )) {
      subbed = true;
    }
    if ( HasParameters.Helper.substitute( arguments, p1, p2, deep, seen, true )) {
      subbed = true;
    }
    if ( p1 == nestedCall && p2 instanceof Parameter ) {
      nestedCall = (Parameter< Call >)p2; // REVIEW -- If p2 is set to something other than a Call, then this is trouble!
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
  public String toShortString() {
    StringBuffer sb = new StringBuffer();
    if ( getMember() == null ) {
      sb.append( "null" );
    } else {
      sb.append( getMember().getName() );
      sb.append( MoreToString.Helper.toShortString( arguments, 
                                                    MoreToString.PARENTHESES,
                                                    null,
                                                    true ) );
    }
    return sb.toString();
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
      } else if ( object instanceof Class ) {
          sb.append( ClassUtils.toString( (Class<?>)object ) + "." );
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
  
  // The following code was re-factored from MethodCall:
  /**
   * @param objects
   * @param call the Call to invoke on each object in the Collection
   * @param indexOfObjectArgument
   *            where in the list of arguments an Object from the Collection
   *            is substituted (1 to total number of args or 0 to indicate
   *            that the objects are each substituted for
   *            methodCall.objectOfCall).
   * @return the results of the Call on each of the objects
   */
  public static Collection< Object > map( Collection< ? > objects,
                                             Call call,
                                             int indexOfObjectArgument ) {
      return call.map( objects, indexOfObjectArgument );
  }
  /**
   * @param objects
   * @param indexOfObjectArgument
   *            where in the list of arguments an object from the Collection
   *            is substituted (1 to total number of args or 0 to indicate
   *            that the objects are each substituted for
   *            methodCall.objectOfCall).
   * @return the results of the Call on each of the objects
   */
  public Collection< Object > map( Collection< ? > objects,
                                       int indexOfObjectArgument ) {
      Collection< Object > coll = new ArrayList<Object>();
      for ( Object o : objects ) {
          sub( indexOfObjectArgument, o );
          Object result = evaluate(true);
          coll.add( result );
      }
      return coll;
  }
  
  /**
   * Substitute an object for a specified argument in this Call.
   * 
   * @param indexOfArg
   *            the index of the argument to be replaced
   * @param obj
   *            the replacement for the argument
   */
  protected void sub( int indexOfArg, Object obj ) {
      if ( indexOfArg < 0 ) Debug.error("bad indexOfArg " + indexOfArg );
      else if ( indexOfArg == 0 ) object = obj;
      else if ( indexOfArg > arguments.size() ) Debug.error( "bad index "
                                                             + indexOfArg
                                                             + "; only "
                                                             + arguments.size()
                                                             + " arguments!" );
      else arguments.set(indexOfArg-1,obj);
  }
  ////////

}
