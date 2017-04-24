package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.RangeDomain;
import gov.nasa.jpl.ae.solver.SingleValueDomain;
import gov.nasa.jpl.ae.util.DomainHelper;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.MethodCall;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.CompareUtils.MappedValueComparator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * 
 */

/**
 * 
 */
public class FunctionCall extends Call {

  protected Method method = null;
  protected boolean monotonic = false;
  //protected Object object = null; // object from which method is invoked
  //protected Vector< Object > arguments = null; // arguments to method

  /**
   * A function call on the result of this function call.
   */
//  protected Parameter<FunctionCall> nestedCall = null;
  
  /**
   * Construct a call to a method.
   * @param method
   * @param returnType
   */
  public FunctionCall( Method method , Class< ? > returnType  ) {
    this.method = method;
    this.returnType = returnType;
  }

  /**
   * Construct a call to a method.
   * @param cls
   * @param methodName
   * @param returnType
   */
  public FunctionCall( Class<?> cls , String methodName , Class< ? > returnType  ) {
    this(null, cls, methodName, (Vector<Object>)null, returnType);
  }

  /**
   * @param object
   * @param method
   * @param returnType
   */
  public FunctionCall( Object object , Method method , Class< ? > returnType  ) {
    this( method, returnType );
    this.object = object;
  }

  public FunctionCall( Object object , Class<?> cls , String methodName , Class< ? > returnType  ) {
    this( cls, methodName, returnType );
    this.object = object;
  }

  /**
   * @param object
   * @param method
   * @param arguments
   */
  public FunctionCall( Object object, Method method, Vector< Object > arguments, Class<?> returnType ) {
    this(object, method, returnType);
    this.arguments = arguments;
    hasTypeErrors();
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   * @param returnType
   */
  public FunctionCall( Object object , Class<?> cls , String methodName ,
                       Vector< Object > arguments , Class< ? > returnType  ) {
    this.object = object;
    Object argArr[] = null;
    if ( !Utils.isNullOrEmpty( arguments ) ) {
      argArr = arguments.toArray();
    }
    this.method = ClassUtils.getMethodForArgs( cls, methodName, argArr );
    this.arguments = arguments;
    this.returnType = returnType;
    hasTypeErrors();
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   * @param returnType
   */
  public FunctionCall( Object object , Method method , Vector< Object > arguments ,
                       Call nestedCall , Class< ? > returnType  ) {
    this(object, method, arguments, returnType);
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );  // REVIEW -- Shouldn't this be the ParameterListener of the nestedCall (last argument here)
  }

  /**
   * @param object
   * @param method
   * @param arguments
   * @param nestedCall
   * @param returnType
   */
  public FunctionCall( Object object , Method method , Vector< Object > arguments ,
                       Parameter<Call> nestedCall , Class< ? > returnType  ) {
    this(object, method, arguments, returnType);
    
    this.nestedCall = nestedCall;
  }

  /**
   * @param object
   * @param cls
   * @param methodName
   * @param arguments
   * @param nestedCall
   * @param returnType TODO
   */
  public FunctionCall( Object object , Class<?> cls , String methodName ,
                       Vector< Object > arguments ,
                       Call nestedCall , Class< ? > returnType  ) {
    this(object, cls, methodName, arguments, returnType);
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
  }

  public FunctionCall( Object object , Class<?> cls , String methodName ,
                       Vector< Object > arguments ,
                       Parameter<Call> nestedCall , Class< ? > returnType  ) {
    this(object, cls, methodName, arguments, returnType);
    this.nestedCall = nestedCall;
  }

  /**
   * @param object
   * @param method
   * @param returnType
   * @param arguments
   */
  public FunctionCall( Object object , Method method , Object argumentsA[] , Class< ? > returnType  ) {
    this(object, method, returnType);
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    hasTypeErrors();
  }

  public FunctionCall( Object object , Class<?> cls , String methodName ,
                       Object argumentsA[]) {
      this(object,cls, methodName, argumentsA, (Class<?>)null);
  }
  
  /**
   * @param object
   * @param cls
   * @param methodName
   * @param argumentsA
   * @param returnType
   */
  public FunctionCall( Object object , Class<?> cls , String methodName ,
                       Object argumentsA[] , Class< ? > returnType  ) {
    this.object = object;
    this.method = ClassUtils.getMethodForArgs( cls, methodName, argumentsA );
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    this.returnType = returnType;
    hasTypeErrors();
  }

  public FunctionCall( Object object , Method method , Object argumentsA[] ,
                       Call nestedCall , Class< ? > returnType  ) {
    // REVIEW -- Can this(...) be called here?
    this.object = object;
    this.method = method;
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
    this.returnType = returnType;
    hasTypeErrors();
  }

  public FunctionCall( Object object , Method method , Object argumentsA[] ,
                       Parameter<Call> nestedCall , Class< ? > returnType  ) {
    // REVIEW -- Can this(...) be called here?
    this.object = object;
    this.method = method;
    this.arguments = new Vector<Object>();
    if ( argumentsA != null ) {
      for ( Object o : argumentsA ) {
        this.arguments.add( o );
      }
    }
    this.nestedCall = nestedCall;
    this.returnType = returnType;
    hasTypeErrors();
  }

  public FunctionCall( Object object , Class<?> cls , String methodName ,
                       Object argumentsA[] , Call nestedCall , Class< ? > returnType  ) {
    this( object, cls, methodName, argumentsA, returnType );
    this.nestedCall = new Parameter<Call>("", null, nestedCall, null );
  }
  public FunctionCall( Object object , Class<?> cls , String methodName ,
                       Object argumentsA[] , Parameter<Call> nestedCall , Class< ? > returnType  ) {
    this( object, cls, methodName, argumentsA, (Class<?>)null );
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
    this.returnType = e.returnType;
    this.argHelper = e.argHelper;
    hasTypeErrors();
  }

  @Override
  public FunctionCall clone() {
    FunctionCall f = new FunctionCall(this);
    return f;
  }
  
  /**
   * A monotonic function is always increasing or always decreasing.
   */
  public boolean isMonotonic() {
    // TODO -- redefine for functions in Functions.java
    return monotonic ;
  }
  
  /**
   * @param monotonic the monotonic to set
   */
  public void setMonotonic( boolean monotonic ) {
    this.monotonic = monotonic;
  }

  /**
   * A monotonic function is always increasing or always decreasing.
   */
  public boolean isContinuous() {
    // TODO -- redefine for functions in Functions.java
    return false;
  }
  
  @Override
  public Class<?>[] getParameterTypes() {
    if ( method == null ) return null;
    return method.getParameterTypes();
  }
  
  @Override
  public Member getMember() {
    return method;
  }

  @Override
  public boolean isVarArgs() {
    if ( method == null ) return false;
    return method.isVarArgs();
  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#isStatic()
   */
  @Override
  public boolean isStatic() {
    if ( method == null ) return false;
    return Modifier.isStatic( method.getModifiers() );
  }
  
  @Override
  public Object invoke( Object evaluatedObject, Object[] evaluatedArgs ) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
    if ( method == null ) {
      evaluationSucceeded = false;
      if ( Debug.isOn() ) Debug.errln( "Warning! Tried to invoke a null method! " + this );
      return null;
    }
    if ( !isStatic() && evaluatedObject == null ) {
      if ( Debug.isOn() ) Debug.errln( "Warning! Tried to invoke a non-static method without an instance! " + this );
      evaluationSucceeded = false;
      return null;
    }
    if ( hasTypeErrors( evaluatedArgs ) ) {
      if ( Debug.isOn() ) Debug.errln( "Warning! Tried calling " + this
                                       + " with bad argument types! "
                                       + MoreToString.Helper.toString( evaluatedArgs ) );
      evaluationSucceeded = false;
      return null;
    }
    //Object result = null;
    returnValue = null;
// TODO -- Try this shorter form?
//    Pair< Boolean, Object > p =
//        ClassUtils.runMethod( false, evaluatedObject, method, evaluatedArgs );
//    result = p.second;
//    evaluationSucceeded = p.first;
    // FIXME -- cannot pass an empty array to a function with a single variable
    // number argument parameter. For example, Utils.newList(new Object[]{}).
    // FIXME -- cannot pass a null as a single argument to to a function with a
    // single variable number argument parameter. For example,
    // Utils.newList(new Object[]{(Object)null}).
//    Debug.turnOn();
    try {
      returnValue = method.invoke( evaluatedObject, evaluatedArgs );
      if ( Debug.isOn() ) {
          System.out.println("FunctionCall method = " + method.toGenericString());
          System.out.println("FunctionCall args = " + arguments);
          System.out.println("FunctionCall.invoke " + method.getName() + "("
                  + Utils.toString( evaluatedArgs, false )
                  + "): FunctionCall{" + this + "} = " + returnValue );
      }
      evaluationSucceeded = true;
    } catch (IllegalArgumentException e) {
      evaluationSucceeded = false;
      if ( Debug.isOn() ) {
        Debug.error(true, false, "FunctionCall method = " + method.toGenericString());
        Debug.error(true, false, "FunctionCall.invoke " + method.getName() + "("
                            + Utils.toString( evaluatedArgs, false )
                            + "): FunctionCall{" + this + "} " + e.getMessage() );
      }
      if ( Debug.isOn() ) {
        if ( method.getParameterTypes().length != evaluatedArgs.length ||
             !Arrays.asList( evaluatedArgs ).contains( null ) ) {
          e.printStackTrace();
        }
      }
      //Debug.turnOff();
      throw e;
    } catch ( Exception e ) {
      evaluationSucceeded = false;
      if ( Debug.isOn() ) {
        Debug.error(true, false, "\nfailed FunctionCall method = " + method.toGenericString());
        System.err.println( "FunctionCall.invoke " + method.getName() + "("
                            + Utils.toString( evaluatedArgs, false )
                            + "): FunctionCall{" + this + "} " + e.getMessage() + "\n");
      }
//      Debug.turnOff();
//      if ( Debug.isOn() ) {
        throw e;
//      }
    }
    //Debug.turnOff();

    return returnValue;
  }
  
//  // TODO -- delete this when version is stable -- the same implementation is in Call
//  // Try to match arguments to parameters by evaluating or creating expressions.
//  public Object[] evaluateArgs( boolean propagate ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
//    if ( method == null ) return null;
//    return super.evaluateArgs( propagate );
//  }

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
    setStale( true );
  }

  @Override
  public Class< ? > getReturnType() {
    if ( returnType == null ) {
      if ( getMethod() != null ) {
        returnType = getMethod().getReturnType();
      }
    }
    return returnType;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#calculateDomain(boolean, java.util.Set)
   */
  @Override
  public Domain< ? > calculateDomain( boolean propagate, Set< HasDomain > seen ) {
    // Should be overridden

    //Debug.error(true, false, "FunctionCall.calculateDomain() must be overridden by " + this.getClass().getName());
    // Try to do something anyway.
    // TODO
    // See if arguments have a single-value domain.
    boolean areAllArgsSingleValueDomains = true;
    for ( Object arg : arguments ) {
        Domain<?> d = DomainHelper.getDomain( arg );
      if ( d != null
           && ( d.magnitude() > 1
                || ( d.magnitude() < 0 && !d.isEmpty() && d.isInfinite() )
                || ( d instanceof RangeDomain
                     && ( (RangeDomain< ? >)d ).size() > 1 ) ) ) {
          areAllArgsSingleValueDomains = false;
          break;
        }
    }
    if ( areAllArgsSingleValueDomains ) {
        Object v = null;
        try {
          v = evaluate( propagate );
        } catch ( IllegalAccessException e ) {
        } catch ( InvocationTargetException e ) {
        } catch ( InstantiationException e ) {
        }
        if ( evaluationSucceeded ) {
            Domain<?> d = new SingleValueDomain< Object >( v );
            return d;
        }
    }
    Domain<?> d = DomainHelper.combineDomains( arguments, this );
    return d;
    // TODO
    // Add an interface where the object of the function could calculate the
    // domain or give some methods that would enable the calculation. Maybe, like 
    // TimeVaryingMap.calculateDomain(Method method, Object[] arguments)
    // TimeVaryingMap.calculateDomain(Effect effect)
    // TimeVaryingMap.restrictedDomainOfArguments(Method method, Object[] arguments)
    // TimeVaryingMap.validIntervals(Effect effect)
    // TimeVaryingMap.getValueDomain()
    // TimeVaryingMap.getValueDomain(Effect effect)
    //return null;
  }
  
  /**
   * A deep search looking for FunctionCalls
   */
  public List< FunctionCall > getFunctionCallsRecursively() {
    List< FunctionCall > calls = new ArrayList< FunctionCall >();
    calls.add( this );
    if ( arguments != null ) {
      for ( Object arg : arguments ) {
        FunctionCall argCall = null;
        try {
          if ( arg instanceof FunctionCall ) {
            argCall = (FunctionCall)arg;
          } else if ( arg instanceof Expression && ((Expression<?>)arg).expression instanceof FunctionCall ) {
            argCall = (FunctionCall)((Expression<?>)arg).expression;
          } else {
            argCall = Expression.evaluate( arg, FunctionCall.class, false, false );
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
        } catch ( ClassCastException e ) {
          ; // ignore -- this is expected
        }
        if ( argCall != null ) {
          calls.addAll( argCall.getFunctionCallsRecursively() );
        }
      }
    }
    return calls;
  }

  /**
   * @param objects
   * @param methodCall
   * @param indexOfObjectArgument
   *            where in the list of arguments an object from the collection
   *            is substituted (1 to total number of args or 0 to indicate
   *            that the objects are each substituted for
   *            methodCall.objectOfCall).
   * @return the subset of objects for which the method call returns true
   */
  public static < XX > Collection<XX> filter( Collection< XX > objects,
                                              FunctionCall methodCall,
                                              int indexOfObjectArgument ) {
      return methodCall.filter( objects, indexOfObjectArgument );
  }
  
  /**
   * @param objects
   * @param indexOfObjectArgument
   *            where in the list of arguments an object from the collection
   *            is substituted (1 to total number of args or 0 to indicate
   *            that the objects are each substituted for
   *            methodCall.objectOfCall).
   * @return the subset of objects for which the method call returns true
   */
  public < XX > Collection<XX> filter( Collection< XX > objects,
                                       int indexOfObjectArgument ) {
      Collection< XX > coll = new ArrayList< XX >();
      try {
      int numArgsMethod = method.getParameterTypes().length;
     // if ( indexOfObjectArgument > )
      for ( XX o : objects ) {
          sub( indexOfObjectArgument, o );
          Object result = null;
            result = evaluate( true, false );
          if ( this.evaluationSucceeded && Utils.isTrue( result, false ) ) {
              coll.add( o );
          }
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
      } // TODO -- flogs to evaluate() correct?
      return coll;
  }
  
  /**
   * Inductively combine the results of applying the method to each of the
   * elements and the return results for the prior element.
   * <p>
   * For example, fold() is used below to sum an array of numbers.<br>
   * {@code int plus(int a, int b) ( return a+b; )} <br>
   * {@code MethodCall plusCall = new MethodCall( null, ClassUtils.getMethodForName(this.getClass(), "plus"), 0, 0} <br>
   * {@code int[] array = new int[] ( 2, 5, 6, 5 );}<br>
   * {@code int result = fold(Arrays.asList(array), 0, 1, 2); // result = 18}
   * 
   * @param objects collection of Objects
   * @param methodCall the MethodCall to invoke on each Object in the Collection 
   * @param initialValue
   *            an initial value to act as the initial argument to the first
   *            invocation of this MethodCall.
   * @param indexOfObjectArgument
   *            where in the list of arguments an Object from the Collection
   *            is substituted (1 to total number of args) or 0 to indicate
   *            that the elements are each substituted for
   *            methodCall.objectOfCall.
   * @param indexOfPriorResultArgument
   *            where in the list of arguments the prior result value is
   *            substituted (1 to total number of args or 0 to indicate that
   *            the prior results are each substituted for methodCall.objectOfCall).
   * @return the result of calling the method on the last Object after calling
   *         the method on each prior Object (in order), passing the prior
   *         return value into the call on each element.
   */
   public static < XX > XX fold( Collection< ? > objects, FunctionCall methodCall, XX initialValue,
                           int indexOfObjectArgument, int indexOfPriorResultArgument ) {
       return methodCall.fold( objects, initialValue, indexOfObjectArgument, indexOfPriorResultArgument );
   }
   
  /**
   * Inductively combine the results of applying the method to each of the
   * elements and the return results for the prior element.
   * <p>
   * For example, fold() is used below to sum an array of numbers.<br>
   * {@code int plus(int a, int b) ( return a+b; )} <br>
   * {@code MethodCall plusCall = new MethodCall( null, ClassUtils.getMethodForName(this.getClass(), "plus"), 0, 0} <br>
   * {@code int[] array = new int[] ( 2, 5, 6, 5 );}<br>
   * {@code int result = fold(Arrays.asList(array), 0, 1, 2); // result = 18}
   * 
   * @param objects collection of Objects
   * @param initialValue
   *            an initial value to act as the initial argument to the first
   *            invocation of this MethodCall.
   * @param indexOfObjectArgument
   *            where in the list of arguments an Object from the collection
   *            is substituted (1 to total number of args) or 0 to indicate
   *            that the elements are each substituted for
   *            methodCall.objectOfCall.
   * @param indexOfPriorResultArgument
   *            where in the list of arguments the prior result value is
   *            substituted (1 to total number of args or 0 to indicate that
   *            the prior results are each substituted for methodCall.objectOfCall).
   * @return the result of calling the method on the last Object after calling
   *         the method on each prior Object (in order), passing the prior
   *         return value into the call on each element.
   */
   public  < XX > XX fold( Collection< ? > objects, XX initialValue,
                           int indexOfObjectArgument, int indexOfPriorResultArgument ) {
      XX priorResult = initialValue;
      try {
      for ( Object o : objects ) {
          sub( indexOfPriorResultArgument, priorResult );
          sub( indexOfObjectArgument, o );
          Object result = null;
            result = evaluate( true, false);
          if ( evaluationSucceeded ) {
              priorResult = (XX)result;
          } 
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
      } // TODO -- args right?
      return priorResult;
  }
  
  /**
   * Sort and return a copy of the input Collection of Objects according to
   * the results of invoking the MethodCall on each Object.
   * 
   * @param objects
   *            to be sorted
   * @param comparator
   *            specifies precedence relation on a pair of methodCall return
   *            values; null defaults to {@link CompareUtils.GenericComparator}.
   * @param methodCall
   *            a MethodCall to invoke on each Object
   * @param indexOfElementArgument
   *            where in the list of arguments an Object from the collection
   *            is substituted (1 to total number of args or 0 to indicate
   *            that the Objects are each substituted for
   *            methodCall.objectOfCall).
   * @return the input Objects in a new Collection sorted according to the
   *         method and comparator
   */
  public static < XX > Collection< XX > sort( Collection< XX > objects,
                                              Comparator< ? > comparator,
                                              FunctionCall methodCall,
                                              int indexOfElementArgument ) {
      return methodCall.sort( objects, comparator, indexOfElementArgument );
  }

  /**
   * Sort and return a copy of the input Collection of Objects according to
   * the results of invoking this MethodCall on each Object.
   * 
   * @param objects
   *            to be sorted
   * @param comparator
   *            specifies precedence relation on a pair of MethodCall return
   *            values; null defaults to {@link CompareUtils.GenericComparator}.
   * @param indexOfElementArgument
   *            where in the list of arguments an Object from the collection
   *            is substituted (1 to total number of args or 0 to indicate
   *            that the Objects are each substituted for
   *            methodCall.objectOfCall).
   * @return the input Objects in a new Collection sorted according to the
   *         method and comparator
   */
  public < K, V > Collection< K > sort( Collection< K > objects,
                                        Comparator< V > comparator,
                                        int indexOfObjectArgument ) {
      List< K > result = new ArrayList< K >( objects );
      Map< K, V > map = new HashMap< K, V >();
      try {
      for ( K o : objects ) {
          sub( indexOfObjectArgument, o );
          Object r;
            r = evaluate(true, true);
          map.put( o, (V)r );
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
      }  // TODO -- args right?
      MappedValueComparator< K, V > mapComparator =
              new CompareUtils.MappedValueComparator< K, V >( map, comparator );
      Collections.sort( result, mapComparator );
      return result;
  }

  
  
}
