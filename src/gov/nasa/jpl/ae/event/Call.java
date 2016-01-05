package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Random;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
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
  //protected Vector< Object > evaluatedArguments = null; // arguments to constructor
  protected boolean evaluationSucceeded = false;
  private boolean stale = true;
  
  abstract public Class< ? > getReturnType();
  abstract public Class<?>[] getParameterTypes();
  abstract public Member getMember();
  abstract public Object invoke( Object obj, Object[] evaluatedArgs ) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException;
  abstract public boolean isVarArgs();
  abstract public boolean isStatic();
  abstract public Call clone();
  
  public Call() {}
  
  public Class< ? > getObjectType() {
    if ( getMember() == null ) return null;
    if ( isStatic() ) return null;
    return getMember().getDeclaringClass();
  }
  
  @Override
  public synchronized void deconstruct() {
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
    stale = true;
//    if ( evaluatedArguments != null ) {
//      this.evaluatedArguments.clear(); // Can't deconstruct since Call does not own them.
//    }
  }

  
  public static boolean divisibleBy( int x, int y ) {
      if ( y == 0 ) return false;
      return ( x / y ) * y == x;
  }
  
  public static boolean isA( Object o, Class<?> c ) {
      return isA( o, c, true, true );
  }  
  public static boolean isA( Object o, Class<?> c, boolean nullOkay,
                             boolean tryEvaluating ) {
      if ( o == null ) return nullOkay;
      if ( c == null ) return false;
      Class<?> oc = o.getClass();
      if ( c.isAssignableFrom( oc ) ) return true;
      if ( o instanceof Wraps ) {
          Wraps<?> w = ((Wraps<?>)o);
          oc = w.getType();
          if ( oc != null && c.isAssignableFrom( oc ) ) return true;
          oc = w.getPrimitiveType();
          if ( oc != null && c.isAssignableFrom( oc ) ) return true;
      }
      if ( tryEvaluating ) {
          Object v = null;
          try {
            v = Expression.evaluate( o, c, false );
          } catch ( ClassCastException e ) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
          } catch ( IllegalAccessException e ) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
          } catch ( InvocationTargetException e ) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
          } catch ( InstantiationException e ) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
          }
          return isA( v, c, false, false );
      }
      return false;
  }
  
  public static boolean isA( Collection< ? > c, Class< ? >[] classes ) {
      return isA( c, Utils.arrayAsList( classes, Class.class ) );
  }

  public static boolean isA( Collection< ? > c, Class< ? >[] classes,
                             boolean isVarArgs ) {
      return isA( c, Utils.arrayAsList( classes, Class.class ), isVarArgs );
  }
  
  public static boolean isA( Collection< ? > c, Collection< Class > classes ) {
      return isA( c, classes, false );
  }
  
  public static boolean isA( Collection< ? > c, Collection< Class > classes,
                             boolean isVarArgs ) {
      if ( c == null || classes == null ) return false;

      // check if the list sizes match the number
      boolean argsSame = c.size() == classes.size();
      boolean numberOfArgsOkay = argsSame || ( isVarArgs && classes.size() < c.size() );
      if ( !numberOfArgsOkay ) return false;
      
      Iterator< Class > i = classes.iterator();
      Class< ? > cls = null;
      for ( Object o : c ) {
          if ( i.hasNext() ) {
              cls = i.next();
          }
          if ( !isA( o, cls ) ) return false;
      }
      return true;
  }
  
  public synchronized boolean canMapToListOfArguments() {
      int as = arguments.size();
      if ( as == 0 ) return false;

      Class<?> cls = getMember().getDeclaringClass();
      if ( cls == null ) return false;

      Class< ? >[] types = getParameterTypes();
      int ts = types.length;
      
      if ( ts == 0 ) {
          // check if arguments can substitute for the object
          boolean allMatched = true;
          for ( Object a : arguments ) {
              allMatched = isA( a, cls );
          }
          if ( allMatched ) return true;
      }
      // check each argument to see if it matches the types
      boolean allMatched = true;
      for ( Object a : arguments ) {
            allMatched = ( ( ( a instanceof Collection ) &&
                             isA( (Collection< ? >)a, types, isVarArgs() ) ) || 
                           ( ts == 1 && isA( a, types[ 0 ] ) ) );
          if ( !allMatched ) break;
      }
      if ( allMatched ) return true;
      return false;
  }

  public synchronized boolean canMapToListOfArguments( Object[] evaluatedArgs ) {
      // replace this.arguments with evaluatedArgs and call canMapToListOfArguments()
      Vector< Object > oldArguments = arguments;
      Vector< Object > newArguments = new Vector< Object >();
      newArguments.addAll( Utils.arrayAsList( evaluatedArgs ) );
      arguments = newArguments;
      boolean ans = canMapToListOfArguments();
      arguments = oldArguments;
      return ans;
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
      } else if ( !c.isAssignableFrom( evaluatedArgs[ i ].getClass() )
                  && ( !isVarArgs() || 
                       ( !Collection.class.isAssignableFrom( c ) &&
                         ( !evaluatedArgs[ i ].getClass().isArray() || 
                           ( ((Object[])evaluatedArgs[ i ]).length > 0 && 
                             !c.isAssignableFrom(((Object[])evaluatedArgs[ i ])[0].getClass())))))) {
        gotErrors = true;
      }
    }
    return gotErrors;
  }
  
  public synchronized Boolean hasTypeErrors() {
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
  
  public Object evaluate( boolean propagate ) throws IllegalAccessException, InvocationTargetException, InstantiationException { // throws IllegalArgumentException,
    return evaluate(propagate, true);
  }
  
  //public boolean 
  
  // TODO -- consider an abstract Call class
  public synchronized Object evaluate( boolean propagate, boolean doEvalArgs ) throws IllegalAccessException, InvocationTargetException, InstantiationException { // throws IllegalArgumentException,
    evaluationSucceeded = false;
    // IllegalAccessException, InvocationTargetException {
    if ( getMember() == null ) {
      Debug.error( true, false, "evaluate() failed!  No member for " + this );
      return null;
    }

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
    Object[] unevaluatedArgs = arguments.toArray();
    if ( ( doEvalArgs ) || hasTypeErrors( unevaluatedArgs ) ) {
      //System.out.println("@@@@@@@@@@@   DUDE   @@@@@@@@@@@@@");
      evaluatedArgs = evaluateArgs( propagate );
    }
    else {
      //System.out.println("@@@@@@@@@@@   SWEET   @@@@@@@@@@@@@");
      evaluatedArgs = unevaluatedArgs;
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

      // moved this inside invoke
//      if ( this instanceof ConstructorCall) {
//        ConstructorCall cc = (ConstructorCall) this;
//        if ( cc.thisClass.getEnclosingClass() != null && !Modifier.isStatic( cc.thisClass.getModifiers() )) {
//          Object[] arr = new Object[evaluatedArgs.length + 1];
//          arr[0] = evaluatedObj;
//          for ( int i = 1; i<=evaluatedArgs.length; ++i) {
//            arr[i] = evaluatedArgs[i-1];
//          }
//          evaluatedArgs = arr;
//        }
//      }
      
      evaluatedArgs = fixArgsForVarArgs( evaluatedArgs );
      
      result = invoke( evaluatedObj, evaluatedArgs );// arguments.toArray() );
      //newObject = constructor.newInstance( evaluatedArgs );// arguments.toArray() );

      // No longer stale after invoked with updated arguments and result is cached.
      stale = false;
      
    } catch ( IllegalAccessException e ) {
      evaluationSucceeded = false;
      //e.printStackTrace();
      throw e;
    } catch ( IllegalArgumentException e ) {
      evaluationSucceeded = false;
      //e.printStackTrace();
      throw e;
    } catch ( InvocationTargetException e ) {
      evaluationSucceeded = false;
      //e.printStackTrace();
      throw e;
    } catch ( InstantiationException e ) {
      evaluationSucceeded = false;
      //e.printStackTrace();
      throw e;
    }
//    if ( result != null && nestedCall != null && nestedCall.getValue() != null ) {
//      nestedCall.getValue().object = result;
//      result = nestedCall.getValue().evaluate( propagate );
//    }
    if ( Debug.isOn() ) 
      Debug.outln( "evaluate() returning " + result );
    
    return result;
  }

  /**
   * Wrap the variable args in an array as a single arg to the vararg.
   * 
   * @param evaluatedArgs
   * @return
   */
  protected Object[] fixArgsForVarArgs( Object[] evaluatedArgs ) {
    if ( !isVarArgs() || evaluatedArgs == null ) return evaluatedArgs;
    int paramSize = getParameterTypes().length;
    if ( evaluatedArgs.length < paramSize - 1 ) {
      return evaluatedArgs;
    }
    try {
      Object[] newArgs = new Object[ paramSize ];
      for ( int i = 0; i < paramSize - 1; ++i ) {
        newArgs[ i ] = evaluatedArgs[ i ];
      }
      Class< ? > lastParamType = getParameterTypes()[getParameterTypes().length - 1];
      Object varArgArray = Array.newInstance(lastParamType.getComponentType(), evaluatedArgs.length - paramSize + 1);
      for ( int i = paramSize - 1, j = 0; i < evaluatedArgs.length; ++i, ++j ) {
        Array.set(varArgArray, j, evaluatedArgs[ i ]);
      }
      newArgs[ paramSize - 1 ] = varArgArray;
      return newArgs;
    } catch ( Throwable t ) {
      t.printStackTrace();
      return evaluatedArgs;
    }
  }

  // Try to match arguments to parameters by evaluating or creating expressions.
  // TODO -- is this necessary????
  protected Object[] evaluateArgs( boolean propagate ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class< ? >[] paramTypes = getParameterTypes();
    return Call.evaluateArgs( propagate, paramTypes, arguments, isVarArgs() );
  }

  // Try to match arguments to parameters by evaluating or creating expressions.
  public static Object[] evaluateArgs( boolean propagate,
                                       Class< ? >[] paramTypes,
                                       Vector< Object > args,
                                       boolean isVarArgs ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
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
          c = c.getComponentType(); // TODO -- don't we need to pass info along
                                    // about whether the result should be an
                                    // array?
        }
      }
      if ( ( c == null || c.equals( Object.class ) ) // || Expression.class.isAssignableFrom( c ) )
           && unevaluatedArg instanceof Wraps ) {
        c = ((Wraps)unevaluatedArg).getType();
      }
      argObjects[i] = Expression.evaluate( unevaluatedArg, c, propagate, true );
      if (!( argObjects[i] == null || c == null || c.isInstance( argObjects[i] ) )) {
        Debug.error( true, "\nArgument " +argObjects[ i ] +
                           ( argObjects[ i ] == null ?
                             "" : " of type " + argObjects[ i ].getClass().getCanonicalName() )
                           + " is not an instance of " + c.getSimpleName() + " for " + i + "th argument of call" );
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
      Object r = null;
      try {
        r = evaluate( false );
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      }
      if ( c == null && r != null
           && Wraps.class.isInstance( r ) ) {// isAssignableFrom( getType() ) ) {
        c = ( (Wraps< ? >)r ).getPrimitiveType();
      }
    }
    return c;
  }

  @Override
  public Object getValue( boolean propagate ) {
    try {
      return evaluate( propagate );
    } catch ( IllegalAccessException e ) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    } catch ( InstantiationException e ) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    }
    return null;
  }

  @Override
  public void setValue( Object value ) {
    // TODO -- this could be used to set free values when inverting the function
    Debug.error( false, "Error! Call.setValue() is not yet supported!" );
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
  public synchronized boolean substitute( Parameter< ? > p1, Object p2, boolean deep,
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
    if ( subbed ) stale = true;
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
  public synchronized boolean isGrounded( boolean deep, Set< Groundable > seen ) {
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
  public synchronized boolean ground( boolean deep, Set< Groundable > seen ) {
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
          try {
            object = nestedCall.getValue(true).evaluate( deep );
          } catch ( IllegalAccessException e ) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
          } catch ( InvocationTargetException e ) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
          } catch ( InstantiationException e ) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
          }
          if ( object == null ) grounded = false;
        }
      }
    }
    if ( grounded ) stale = true;
    return grounded;
  }
  
//  public synchronized String toLongString() {
//    StringBuffer sb = new StringBuffer();
//    if ( getMember() == null ) {
//      sb.append( "null" );
//    } else {
//      sb.append( getMember().getName() );
//      sb.append( MoreToString.Helper.toShortString( arguments, 
//                                                    MoreToString.PARENTHESES,
//                                                    null,
//                                                    true ) );
//    }
//    return sb.toString();
//  }
  
  @Override
  public synchronized String toShortString() {
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
  public synchronized String toString(boolean withHash, boolean deep, Set< Object > seen,
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
    if ( stale ) return true;
    for ( Parameter< ? > p : getParameters( false, null ) ) {
      if ( p.isStale() ) {
        stale = true;
        return true;
      }
    }
    if ( nestedCall != null ) {
      if ( nestedCall.isStale() ) {
        stale = true;
        return true;
      }
    }
    if ( object instanceof LazyUpdate )  {
      if ( ( (LazyUpdate)object ).isStale() ) {
        stale = true;
        return true;
      }
    }
    return false;
  }

  @Override
  public void setStale( boolean staleness ) {
    stale = staleness;
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
    if ( this.object != object ) {
      this.object = object;
      stale = true;
    }
  }

//  /**
//   * @return the arguments
//   */
//  public Vector< Object > getEvaluatedArguments() {
//    return evaluatedArguments;
//  }

//  /**
//   * @return the arguments
//   * The caller is responsible for setting the stale flag if the arguments are modified.
//   */
//  public Vector< Object > getArgument() {
//    return arguments;
//  }
  /**
   * @param i
   * @return the nth argument
   */
  public Object getArgument(int n) {
    return arguments.get( n );
  }

  /**
   * @return a the arguments in an array
   */
  public Object[] getArgumentArray() {
    return arguments.toArray();
  }
  
  /**
   * @return a copy of the arguments as a {@link Vector}
   */
  public Vector<Object> getArgumentVector() {
    return new Vector<Object>( arguments );
  }

  /**
   * @param arguments the arguments to set
   */
  public synchronized void setArguments( Vector< Object > arguments ) {
    if ( arguments != this.arguments ) {
      this.arguments = arguments;
      stale = true;
    }
  }

  /**
   * @param i index of argument to set
   * @param argument the argument to set
   */
  public synchronized void setArgument( int i, Object argument ) {
    while ( arguments.size() < i ) {
      arguments.add( null );
      stale = true;
    }
    if ( i == arguments.size() ) {
      arguments.add( argument );
      stale = true;
    } else if ( arguments.get( i ) != argument ) {
      this.arguments.set(i, argument);
      stale = true;
    }
  }

  /**
   * @return the nestedCall
   * The caller is responsible for setting stale = true if modifying the nestedCall. 
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
    stale = true;
  }
  
  public Class< ? > getTypeForSubstitutionIndex( int index ) {
    // If the argument substitutes for the object of the call,
    // check and see if the object should be a Collection.
    if ( index == 0 ) {
        Class< ? > objTypeReqd = getObjectType();
        return objTypeReqd;
    } else {
        Class< ? >[] types = getParameterTypes();
        if ( Utils.isNullOrEmpty( types ) ) return null;
        if ( types.length < index ) {
          if ( isVarArgs() ) {
            return types[types.length-1];
          }
          return null;
        }
        return types[index-1];
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
      try {
        for ( Object o : objects ) {
            sub( indexOfObjectArgument, o );
            Object result = null;
            result = evaluate(true);
            coll.add( result );
        }
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
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
  protected synchronized void sub( int indexOfArg, Object obj ) {
    sub( this, indexOfArg, obj );
  }
  protected static void sub( Call call, int indexOfArg, Object obj ) {
    sub(call, (Vector< Object >)null, indexOfArg, obj );
  }
  protected static void sub( Call call, Vector< Object > arguments, int indexOfArg, Object obj ) {
      if ( arguments == null ) arguments = call.arguments;
      if ( indexOfArg < 0 ) Debug.error("bad indexOfArg " + indexOfArg );
      else if ( indexOfArg == 0 ) {
        if ( call.object != obj ) {
          call.object = obj;
          call.setStale( true );
        }
      }
      else {
        if ( indexOfArg > arguments.size() ) {
          Debug.err( "bad index " + indexOfArg + "; only " + arguments.size() + " arguments!  Adding null argument placeholders!" );
          if ( indexOfArg > 100 ) {
            Debug.error( "bad index " + indexOfArg + "; greater than 100" );
          } else {
            while ( indexOfArg > arguments.size() ) {
              arguments.add( null );
            }
          }
        }
        if ( arguments.get( indexOfArg - 1 ) != obj ) {
          arguments.set(indexOfArg-1,obj);
          call.setStale( true );
        }
      }
  }
  
  /**
   * Replace the entry of the array at the specified index with the specified
   * object.
   * 
   * @param arguments
   * @param indexOfArg
   * @param obj
   */
  protected static void sub( Object[] arguments, int indexOfArg, Object obj ) {
    if ( indexOfArg < 0 ) Debug.error("bad indexOfArg " + indexOfArg );
    else if ( arguments == null ) return;
    else if ( indexOfArg >= arguments.length ) {
      Debug.error( "bad index " + indexOfArg + "; only " + arguments.length
                   + " arguments!" );
    }
    else arguments[indexOfArg] = obj;
  }
  /**
   * Similar to {@link #sub(Call, Vector, int, Object)} except the arguments are
   * not the arguments of the call.
   * 
   * @param call
   * @param arguments
   * @param indexOfArg
   * @param obj
   */
  protected static void sub( Call call, Object[] arguments, int indexOfArg, Object obj ) {
    if ( indexOfArg < 0 ) Debug.error("bad indexOfArg " + indexOfArg );
    else if ( indexOfArg == 0 ) {
      call.object = obj;
      call.setStale( true );
    }
    else if ( arguments == null ) return;
    else if ( indexOfArg > arguments.length ) Debug.error( "bad index "
                                                           + indexOfArg
                                                           + "; only "
                                                           + arguments.length
                                                           + " arguments!" );
    else arguments[indexOfArg-1] = obj;
  }
  ////////
  
  
  /**
   * Compute a transitive closure of a set using this MethodCall as a relation from an argument to the return value.
   * @param initialSet the Set of initial items to be substituted for an argument or the object of this MethodCall
   * @param indexOfObjectArgument
   *            where in the list of arguments an object from the set
   *            is substituted (1 to total number of args or 0 to indicate
   *            that the objects are each substituted for
   *            methodCall.objectOfCall).
   * @param maximumSetSize the size of the resulting set will be limited to the maximum of this argument and the size of initialSet 
   * @return a new Set that includes the initialSet and the results of applying the methodCall on each item (substituting the argument for the given index) in the new Set  
   */
  public < XX > Set< XX > closure( Set< XX > initialSet,
                                   int indexOfObjectArgument, int maximumSetSize ) {
      Set< XX > closedSet = new TreeSet< XX >( CompareUtils.GenericComparator.instance() );
      closedSet.addAll( initialSet );
      ArrayList< XX > queue =
              new ArrayList< XX >( initialSet );
      Set< XX > seen = new HashSet< XX >();
      try {
      while ( !queue.isEmpty() ) {
          XX item = queue.get( 0 );
          queue.remove( 0 );
          sub( indexOfObjectArgument, item );
          if ( seen.contains( item ) ) continue;
          seen.add( item );
          Object result = null;
            result = evaluate( true, true );
          if ( !evaluationSucceeded ) continue;
          Collection< XX > newItems = null;
          try {
              if ( result instanceof Collection ) {
                  newItems = (Collection< XX >)result;
              } else {
                  newItems = (Collection< XX >)Utils.newSet( result );
              }
          } catch ( ClassCastException e ) {
              continue;
          }
          if ( !Utils.isNullOrEmpty( newItems ) ) {
              Utils.addN( closedSet, maximumSetSize - closedSet.size(), newItems );
          }
      }
      } catch ( IllegalAccessException e1 ) {
        // TODO Auto-generated catch block
        //e1.printStackTrace();
      } catch ( InvocationTargetException e1 ) {
        // TODO Auto-generated catch block
        //e1.printStackTrace();
      } catch ( InstantiationException e1 ) {
        // TODO Auto-generated catch block
        //e1.printStackTrace();
      }  // TODO -- args right?
      return closedSet;
  }
  
  /**
   * Compute a transitive closure of a map using this MethodCall to specify for each key in the map a set of items that should have a superset of related items in the map.
   * @param initialSet the Set of initial items to be substituted for an argument or the object of this MethodCall
   * @param indexOfObjectArgument
   *            where in the list of arguments an object from the set
   *            is substituted (1 to total number of args or 0 to indicate
   *            that the objects are each substituted for
   *            methodCall.objectOfCall).
   * @param maximumSetSize the size of the resulting set will be limited to the maximum of this argument and the size of initialSet 
   * @return a new Set that includes the initialSet and the results of applying the methodCall on each item (substituting the argument for the given index) in the new Set  
   */
  public < XX, C extends Map< XX, Set< XX > > > C mapClosure( C relationMapToClose,
                                                              int indexOfObjectArgument,
                                                              int maximumSetSize ) {
      ArrayList< XX > queue =
              new ArrayList< XX >( relationMapToClose.keySet() );
//      Set< XX > seen = new HashSet< XX >();
      try {
      while ( !queue.isEmpty() ) {
          XX item = queue.get( 0 );
          queue.remove( 0 );
          sub( indexOfObjectArgument, item );
//          if ( seen.contains( item ) ) continue;
//          seen.add( item );
//          Method method =
//                  ClassUtils.getMethodForArgs( AbstractSystemModel.class, "isA",
//                                               item, item );
//          MethodCall methodCall =
//                  new MethodCall( null, method,
//                                  new Object[] { null, item } );
          Object result = null;
            result = evaluate( true, true );
          if ( !evaluationSucceeded ) continue;
          Collection< XX > isItemSet = null;
          try {
              if ( result instanceof Collection ) {
                  isItemSet = (Collection< XX >)result;
              } else {
                  isItemSet = (Collection< XX >)Utils.newSet( result );
              }
          } catch ( ClassCastException e ) {
              continue;
          }
          Set< XX > relatedToItem = relationMapToClose.get( item );
          for ( XX isA : isItemSet ) {
              Set< XX > related = relationMapToClose.get( isA );
              int ct = 0;
              if ( related == null ) {
                  related = new TreeSet< XX >(CompareUtils.GenericComparator.instance());
                  relationMapToClose.put( isA, related );
              } else {
                  ct = related.size();
              }
              related.addAll( relatedToItem );
              if ( related.size() > ct ) {
                  queue.add( isA );
              }
              if ( relationMapToClose.size() >= maximumSetSize ) break;
          }
      }
      } catch ( IllegalAccessException e1 ) {
        // TODO Auto-generated catch block
        //e1.printStackTrace();
      } catch ( InvocationTargetException e1 ) {
        // TODO Auto-generated catch block
        //e1.printStackTrace();
      } catch ( InstantiationException e1 ) {
        // TODO Auto-generated catch block
        //e1.printStackTrace();
      }  // TODO -- args right?
      return relationMapToClose;
  }
  public Object getOtherArg( Object theArg ) {
    LinkedHashSet< Object > otherArgs = getOtherArgs( theArg );
    int n = Random.global.nextInt( otherArgs.size() );
    Iterator<Object> iter = otherArgs.iterator();
    Object otherArg = null;
    for (int i = 0; i != n; ++i) {
      otherArg = iter.next();
    }
    return otherArg;
  }
  public LinkedHashSet<Object> getOtherArgs( Object theArg ) {
    //FunctionCall f = (FunctionCall)this.expression;
    Object otherArg = null;
    LinkedHashSet<Object> otherArgs = new LinkedHashSet< Object >();
    boolean found = false;
    for ( Object arg : arguments ) {
      if ( theArg == arg ) {
        found = true;
      } else if ( arg instanceof Expression
                  && ( ( (Expression)arg ).expression == theArg ) ) {
        found = true;
      } else {
        otherArgs.add( arg );
      }
    }
    if ( !found ) {
      // TODO -- ERROR
    }
    return otherArgs;
  }


}
