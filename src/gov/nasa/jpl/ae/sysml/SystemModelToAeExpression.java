package gov.nasa.jpl.ae.sysml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.ConstructorCall;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.Expression.Form;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.util.ClassData;
import gov.nasa.jpl.ae.util.JavaToConstraintExpression;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.HasPreference;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;
import sysml.SystemModel;

public class SystemModelToAeExpression< C, T, P, N, U, SM extends SystemModel< ?, C, T, P, N, ?, U, ?, ?, ?, ? > > {
    
    public static boolean debug = false;
    public static boolean doCallCaching = false;
  
    protected SM model = null;
    protected ClassData classData = new ClassData();
    
    /**
     * Maps the parsed Expression Parameters to the Parameter objects
     * we create for them.
     */
    private Map<P, Parameter<Object>> exprParamMap = new HashMap<P,Parameter<Object>>();

    public Map< P, Parameter< Object >> getExprParamMap() {
      return exprParamMap;
    }

    /**
     * Maps the parsed Expression Parameters to the Parameter objects
     * we create for them.
     */
    private Map<Parameter<Object>, P> paramExprMap = new HashMap<Parameter<Object>,P>();

    public Map< Parameter< Object >, P > getParamExprMap() {
      return paramExprMap;
    }
 
    protected Parameter<?> putExprParamMap( P propNode, Parameter< Object > param ) {
      Parameter< Object > oldVal = exprParamMap.put( propNode, param );
      paramExprMap.put( param, propNode );
      return oldVal;
    }

    public SystemModelToAeExpression(SM model) {
        setModel(model);
    }

    /**
     * Converts the passed sysml expression to an Ae expression, and
     * returns its evaluation.
     * 
     * @param expressionElement The sysml expression to convert/evaluate
     * @return The evaluated AE converted expression
     * @throws InstantiationException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public <X> X evaluateExpression( Object expressionElement ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
      Expression<X> expression = toAeExpression( expressionElement );
      return expression.evaluate( true );
    }

    public enum CallCase {UNKNOWN, FAIL, EmsSystemModel, sysml, ae, common};
    public enum ArgsUsed {UNKNOWN, ae, raw};
    // Cache doesn't work because the clone isn't deep enough.
    public static Map< String, Map< String, Map< Object, Pair<CallCase, ArgsUsed> > > > callCache =
        Collections.synchronizedMap( new HashMap< String, Map< String, Map< Object, Pair<CallCase, ArgsUsed> > > >() );

    public static Pair< CallCase, ArgsUsed > callCacheGet( Object object,
                                                           String operationName,
                                                           Object args ) {
      if ( !doCallCaching ) return null;
      if ( operationName == null ) return null;
      String className =
          ( object == null ) ? "null" : object.getClass().getCanonicalName();
      if ( args == null ) args = "null";
      return Utils.get( callCache, className, operationName, args );
    }
  
    public static Pair< CallCase, ArgsUsed >
        callCachePut( Object object, String operationName, Object args,
                      Pair< CallCase, ArgsUsed > callCasePair ) {
      if ( !doCallCaching ) return null;
      if ( operationName == null ) return null;
      String className =
          ( object == null ) ? "null" : object.getClass().getCanonicalName();
      if ( args == null ) args = "null";
      return Utils.put( callCache, className, operationName, args, callCasePair );
    }
    
  /**
   * Return a Call object based on the passed operation and arguments
   * 
   * @param object
   *          The object whose method is called; if this is null, this.model is
   *          used as the object.
   * @param operationName
   *          The name of operation used to search for the equivalent java call
   * @param aeArguments
   *          The arguments for operation
   * @return Call object or null if the operationName is not a java call
   */
    private Call createCall(Object object, N operationName, Vector< Object > aeArguments,
                            Vector<Object> rawArguments) {
      boolean mayUseRawArgs = true;      
      
      Call call = null;
      Method method = null;
      //Object object = o;
      
      boolean usedRawArgs = false;

      
      
      //debug = true;
      
      
      
      
      /*
       * We will look for the corresponding Constructor or FunctionCall in
       * the following order:
       * 
       * 1. The current model class, ie EmsSystemModel (assume its a FunctionCall)
       * 2. The view_repo.syml package (assume its a ConstructorCall or FunctionCall)
       * 3. The Functions.java and ae.event package (assume its a ConstructorCall)
       * 4. Common Java classes (assume its a FunctionCall)
       * 5. The mbee.util package (assume its a FunctionCall) 
       * 
       */
      if ( operationName == null )  return null;
                  
      ArrayList<Class<?>> argTypes = new ArrayList<Class<?>>();
      
      // Finding out the argument types:
      for (Object arg : aeArguments) {
        
        if (arg instanceof Expression) {
          argTypes.add( ((Expression<?>)arg).getType());
        }
        else {
            argTypes.add( arg.getClass() );
        }
      }

      ArrayList<Class<?>> rawArgTypes = new ArrayList<Class<?>>();
      // Get the raw argument types:
      for (Object arg : rawArguments) {
        rawArgTypes.add( arg.getClass() );
      }

      // Caching place where method was found instead of the resulting call
      // because the clone of the call isn't deep enough.
      Pair<CallCase, ArgsUsed> cachedCase = null;
      if ( doCallCaching ) cachedCase =
          callCacheGet( object, operationName.toString(), argTypes );
      CallCase callCase = cachedCase == null ? CallCase.UNKNOWN : cachedCase.first;
      ArgsUsed argsUsed = cachedCase == null ? ArgsUsed.UNKNOWN : cachedCase.second;
            
      CallCase newCallCase = callCase; // for adding to the cache; only update if UNKNOWN
      ArgsUsed newArgsUsed = argsUsed; // for adding to the cache; only update if UNKNOWN
      
      if ( !mayUseRawArgs ) {
        argsUsed = ArgsUsed.ae;
        newArgsUsed = ArgsUsed.ae;
      }

      
      if ( callCase == CallCase.FAIL ) return null;
      
      boolean nullEmptyOrSameArgs = true;
      Iterator< Class< ? > > i = rawArgTypes.iterator();
      for ( Class<?> t : argTypes ) {
        Class< ? > rawT = i.hasNext() ? i.next() : null;
        if ( t != null && !Utils.valuesEqual( t, rawT ) ) {
          nullEmptyOrSameArgs = false;
        }
      }
      
      // 1.
      
      if ( debug ) System.out.println("^^^^^^^^^^^^  1  ^^^^^^^^^^^^^^^");
      
//        if ( operationName.equals( "evaluate" ) ) {
////          method = ClassUtils.getMethodForArgTypes( EvaluateOperation.class,
////                                                    operationName.toString(),
////                                                    argTypes.toArray(new Class[argTypes.size()]));
//            //////call = new ConstructorCall( this, OperationFunctionCall.class, argTypes.toArray(new Class[argTypes.size()]) );
//            //call = new ConstructorCall( this, OperationFunctionCall.class, new Object[] { object, arguments } );
//            ////call = new OperationFunctionCall( object, arguments );
//            call = new OperationFunctionCallConstructorCall( object, arguments );
//        }
    if ( call == null
         && ( callCase == CallCase.UNKNOWN || callCase == CallCase.EmsSystemModel ) ) {
        if ( argsUsed != ArgsUsed.raw ) {
          method = ClassUtils.getMethodForArgTypes( model.getClass(),
                                                    operationName.toString(),
                                                    argTypes.toArray(new Class[argTypes.size()]), false);
        }
        
        if ( method != null ) {
          call = new TranslatedFunctionCall<P>( object, method, aeArguments, this );
          if ( call != null ) call.alternativeArguments.add( rawArguments );
        }
        
//          // Check to see if there is a preference over function calls.
//          // TODO -- move this out of this already long function. It can probably
//          // be reused elsewhere in this function anyway.
//          boolean tryRawArgs =
//              (!nullEmptyOrSameArgs || argsUsed == ArgsUsed.raw ) && argsUsed != argsUsed.ae && ( call == null// || argsUsed == ArgsUsed.raw
//              || prefersRawArgs( call, argTypes, rawArgTypes ) );//, null ) );
        
        if ( (!nullEmptyOrSameArgs || argsUsed == ArgsUsed.raw ) && method == null && argsUsed != argsUsed.ae ) {
//          if ( tryRawArgs ) {
//          Method method2 = ClassUtils.getMethodForArgTypes( model.getClass(),
            method = ClassUtils.getMethodForArgTypes( model.getClass(),
                                                      operationName.toString(),
                                                      rawArgTypes.toArray(new Class[rawArgTypes.size()]), false);
//          if ( method2 != null ) {
//            Call call2 = new FunctionCall( object, method2, rawArguments );
//            if ( call2 != null ) {
//              
//              
//              usedRawArgs = true;
//              if ( debug ) System.out.println( ":-)) ******!!!!!!!!!%%%%%%%############&&&&&&&&&@@@@@@@@@" );
//              method = method2;
//              call = call2;
//              call.alternativeArguments.clear();
//              call.alternativeArguments.add( aeArguments );
//            }
//          }
        }
     }
     if ( method != null ) {
       if ( object == null ) {
         object = model;
       }
       if ( debug ) {
         if ( newCallCase == CallCase.UNKNOWN ) newCallCase = CallCase.EmsSystemModel;
         if ( newArgsUsed == ArgsUsed.UNKNOWN ) newArgsUsed = usedRawArgs ? ArgsUsed.raw : ArgsUsed.ae;         
         System.out.println("^^^^^^^^^^^^  method = " + method.getDeclaringClass().getSimpleName() + "." + method.getName() + "  ^^^^^^^^^^^^^^^");
         if ( ! usedRawArgs )
           System.out.println("^^^^^^^^^^^^  ae arg types = " + argTypes + "  ^^^^^^^^^^^^^^^");
         else
           System.out.println("^^^^^^^^^^^^  raw arg types = " + rawArgTypes + "  ^^^^^^^^^^^^^^^");
       }
     }
     

     // 2.

     if ( method == null && call == null
         && ( callCase == CallCase.UNKNOWN || callCase == CallCase.sysml ) ) {

       if ( debug ) System.out.println("^^^^^^^^^^^^  2  ^^^^^^^^^^^^^^");

       if ( argsUsed != ArgsUsed.raw ) {
           call = JavaToConstraintExpression.javaCallToEventFunction(operationName.toString(),
                                                                     aeArguments,
                                                                     argTypes.toArray(new Class[]{}));
           if ( call != null ) call.alternativeArguments.add( rawArguments );
        }

        
        // Check to see if there is a preference over constructors.
        // TODO -- move this out of this already long function. It can probably
        // be reused elsewhere in this function anyway.
        boolean tryRawArgs =
            (!nullEmptyOrSameArgs || argsUsed == ArgsUsed.raw ) && argsUsed != argsUsed.ae && ( call == null// || argsUsed == ArgsUsed.raw
                || prefersRawArgs( call, argTypes, rawArgTypes ) );
        
        if ( tryRawArgs ) {
          Call call2 = JavaToConstraintExpression.javaCallToEventFunction(operationName.toString(),
                                                                    rawArguments,
                                                                    rawArgTypes.toArray(new Class[]{}));
          if ( call2 != null ) {
            // Try it out just to be sure
            usedRawArgs = true;
            if ( debug ) System.out.println( ":-)) ******!!!!!!!!!%%%%%%%############&&&&&&&&&@@@@@@@@@" );
            call = call2;
            call.alternativeArguments.clear();
            call.alternativeArguments.add( aeArguments );
          }
        }
        if ( call != null ) {
          if ( newCallCase == CallCase.UNKNOWN ) newCallCase = CallCase.sysml;
          if ( newArgsUsed == ArgsUsed.UNKNOWN ) newArgsUsed = usedRawArgs ? ArgsUsed.raw : ArgsUsed.ae;
          if ( debug ) {
            System.out.println("^^^^^^^^^^^^  call = " + call + "  ^^^^^^^^^^^^^^^");
            System.out.println("^^^^^^^^^^^^  method = " + call.getMember() + "  ^^^^^^^^^^^^^^^");
            if ( !usedRawArgs )
              System.out.println("^^^^^^^^^^^^  ae arg types = " + argTypes + "  ^^^^^^^^^^^^^^^");
            else
              System.out.println("^^^^^^^^^^^^  raw arg types = " + rawArgTypes + "  ^^^^^^^^^^^^^^^");
          }
        }
      }

     
      //3.
     
      if ( call == null && method == null
          && ( callCase == CallCase.UNKNOWN || callCase == CallCase.ae ) ) {
  
        if ( debug ) System.out.println("^^^^^^^^^^^^  3  ^^^^^^^^^^^^^^^");
        if ( aeArguments.size() == 1 ) {
            call = JavaToConstraintExpression.unaryOpNameToEventFunction( operationName.toString() );
        } 
        else if ( aeArguments.size() == 2 ) {
            call = JavaToConstraintExpression.binaryOpNameToEventFunction( operationName.toString() );
        } 
        else if ( aeArguments.size() == 3
                    && operationName.toString().equalsIgnoreCase( "if" ) ) {
            call = JavaToConstraintExpression.getIfThenElseConstructorCall();
        }
        
        if ( call != null ) {
          // TODO -- See if raw arguments are a better fit than these.
          call.setArguments( aeArguments );
          call.alternativeArguments.add( rawArguments );
          
          boolean tryRawArgs =
              (!nullEmptyOrSameArgs || argsUsed == ArgsUsed.raw ) && argsUsed != argsUsed.ae && ( call == null// || argsUsed == ArgsUsed.raw
                  || prefersRawArgs( call, argTypes, rawArgTypes ) );
          
          if ( tryRawArgs ) {
            call.setArguments( rawArguments );
            call.alternativeArguments.clear();
            call.alternativeArguments.add( aeArguments );
            usedRawArgs = true;
            if ( debug ) System.out.println( ":-)) ******!!!!!!!!!%%%%%%%############&&&&&&&&&@@@@@@@@@" );
          }
          
          if ( newCallCase == CallCase.UNKNOWN ) newCallCase = CallCase.ae;
          if ( newArgsUsed == ArgsUsed.UNKNOWN ) newArgsUsed = usedRawArgs ? ArgsUsed.raw : ArgsUsed.ae;
          if ( debug && call != null ) {
            System.out.println("^^^^^^^^^^^^  call = " + call + "  ^^^^^^^^^^^^^^^");
            System.out.println("^^^^^^^^^^^^  method = " + call.getMember() + "  ^^^^^^^^^^^^^^^");
            if ( !usedRawArgs )
              System.out.println("^^^^^^^^^^^^  ae arg types = " + argTypes + "  ^^^^^^^^^^^^^^^");
            else
              System.out.println("^^^^^^^^^^^^  raw arg types = " + rawArgTypes + "  ^^^^^^^^^^^^^^^");
          }
        }
      }
      
        
      // 4.
        
      if ( call == null && method == null
          && ( callCase == CallCase.UNKNOWN || callCase == CallCase.common ) ) {

        if ( debug ) System.out.println("^^^^^^^^^^^^  4  ^^^^^^^^^^^^^^^");
        if ( argsUsed != ArgsUsed.raw ) {
          method = ClassUtils.getJavaMethodForCommonFunction( operationName.toString(),
                                                              aeArguments.toArray() );
        }
        if ((!nullEmptyOrSameArgs || argsUsed == ArgsUsed.raw ) && method == null && argsUsed != argsUsed.ae) {
          method = ClassUtils.getJavaMethodForCommonFunction( operationName.toString(),
                                                              rawArguments.toArray() );
          if ( method != null ) usedRawArgs = true;
        }
      
        if ( method != null ) {
          if ( debug ) System.out.println("^^^^^^^^^^^^  method = " + method.getDeclaringClass().getSimpleName() + "." + method.getName() + "  ^^^^^^^^^^^^^^^");
          if ( newCallCase == CallCase.UNKNOWN ) newCallCase = CallCase.common;
          if ( newArgsUsed == ArgsUsed.UNKNOWN ) newArgsUsed = usedRawArgs ? ArgsUsed.raw : ArgsUsed.ae;
        }
        if ( debug ) {
          if ( !usedRawArgs )
            System.out.println("^^^^^^^^^^^^  ae arg types = " + argTypes + "  ^^^^^^^^^^^^^^^");
          else
            System.out.println("^^^^^^^^^^^^  raw arg types = " + rawArgTypes + "  ^^^^^^^^^^^^^^^");
        }
      }

      
      // 5.
      // Added some of the mbee utils to getJavaMethodForCommonFunction() called
      // just before, so this is part of 4.      

      
      if ( call == null && method == null ) {
        // TODO -- if it *still* fails, maybe search through all classes of all
        // packages for a method with this name.
        if ( newCallCase == CallCase.UNKNOWN ) newCallCase = CallCase.FAIL;
      }
      
      // Make the FunctionCall if it was not a ConstructorCall:
      if ( method != null ) {//&& call == null ) {
        call = new TranslatedFunctionCall( object, method, usedRawArgs ? rawArguments : aeArguments, this );
        call.alternativeArguments.add(usedRawArgs ? aeArguments : rawArguments);
      }
      
      if ( call instanceof ConstructorCall && !( call instanceof TranslatedConstructorCall ) ) {
        call = new TranslatedConstructorCall< P >( (ConstructorCall)call, this );
      } else if ( call instanceof FunctionCall && !( call instanceof TranslatedFunctionCall ) ) {
        call = new TranslatedFunctionCall< P >( (FunctionCall)call, this );
      }

      // Put any new results in cache.
      if ( doCallCaching && cachedCase == null ) {
        callCachePut( object, operationName.toString(), argTypes,
                      new Pair< CallCase, ArgsUsed >( newCallCase, newArgsUsed ) );
      }

      if ( debug && call != null ) {
        System.out.println("^^^^^^^^^^^^  final call = " + call + "  ^^^^^^^^^^^^^^^");
        System.out.println("^^^^^^^^^^^^  method = " + call.getMember() + "  ^^^^^^^^^^^^^^^");
        if ( !usedRawArgs )
          System.out.println("^^^^^^^^^^^^  ae arg types = " + argTypes + "  ^^^^^^^^^^^^^^^");
        else
          System.out.println("^^^^^^^^^^^^  raw arg types = " + rawArgTypes + "  ^^^^^^^^^^^^^^^");
      }

      if ( debug && call == null ) System.out.println("^^^^^^^^^^^^  final call = " + call + "  ^^^^^^^^^^^^^^^");

      return call;
    }
  
    
    // This code probably isn't ready. Tried to improve the old one below this
    // one, but decided to abandon before testing.
    private boolean prefersRawArgs( Call call, ArrayList< Class< ? >> argTypes,
                                    ArrayList< Class< ? >> rawArgTypes,
                                    HasPreference< List< Class > > obj ) {
      if ( call == null ) return false;
      //if (!( call instanceof ConstructorCall )) return false;

      boolean prefersRawArgs = false;
      //Constructor< ? > ctor = ((ConstructorCall)call).getConstructor();
      Member ctor = call.getMember();
      Class<?> cls = ctor.getDeclaringClass();
      boolean hasPreference = HasPreference.Helper.classHasPreference( cls );
      //if ( !hasPreference ) return false;
      // The constructor's class has preferences over arguments to the
      // constructor. Determine which arguments are best.
      try {
        // Need an instance to access preferences.
        //HasPreference< List< Class > > obj = null;
        Object o = call.evaluate( true );
        if ( !call.didEvaluationSucceed() ||
             ( o != null && !call.getReturnType().isInstance( o ) && 
               !( o instanceof Wraps &&
                  call.getReturnType().isAssignableFrom( ((Wraps)o).getType() ) ) ) ) {  
          prefersRawArgs = true;
        }
        if ( obj == null && hasPreference && call instanceof ConstructorCall && o instanceof HasPreference ) {
          obj = (HasPreference< List< Class > >)o;
        }
//            HasPreference< List< Class > > obj =
//                (HasPreference< List< Class > >)ctor.newInstance( aeArguments.toArray() );
        try {
          if ( obj == null || ( hasPreference && obj.prefer( (List)rawArgTypes, (List)argTypes ) ) ) {
            prefersRawArgs = true;
            if ( debug ) System.out.println( ":-)) ******!!!!!!!!!%%%%%%%############&&&&&&&&&@@@@@@@@@" );
          }
        } catch (ClassCastException e) {
          // Assuming class cast exception on prefer since we don't
          // know for sure what args it takes.
        }
        if ( debug ) System.out.println( "?:-| ******!!!!!!!!!%%%%%%%############&&&&&&&&&@@@@@@@@@" );
      } catch (Throwable t) {
        if ( debug ) System.out.println( ":-( ******!!!!!!!!!%%%%%%%############&&&&&&&&&@@@@@@@@@" );
        ////t.printStackTrace();
        // Assumed exception when invoking call.evaluate( true )
        prefersRawArgs = true;
      }
    return prefersRawArgs;
  }

    
    public boolean prefersRawArgs( Call call, ArrayList< Class< ? >> argTypes,
                                  ArrayList< Class< ? >> rawArgTypes ) {
      if ( call == null ) return false;
      if (!( call instanceof ConstructorCall )) return false;

      boolean prefersRawArgs = false;
      //Constructor< ? > ctor = ((ConstructorCall)call).getConstructor();
      Member ctor = call.getMember();
      Class<?> cls = ctor.getDeclaringClass();
      boolean hasPreference = HasPreference.Helper.classHasPreference( cls );
      if ( !hasPreference ) return false;
      // The constructor's class has preferences over arguments to the
      // constructor. Determine which arguments are best.
      try {
        // Need an instance to access preferences.
        HasPreference< List< Class > > obj = (HasPreference< List< Class > >)call.evaluate( true );
//            HasPreference< List< Class > > obj =
//                (HasPreference< List< Class > >)ctor.newInstance( aeArguments.toArray() );
        try {
          if ( obj == null || obj.prefer( (List)rawArgTypes, (List)argTypes ) ) {
            prefersRawArgs = true;
            if ( debug ) System.out.println( ":-)) ******!!!!!!!!!%%%%%%%############&&&&&&&&&@@@@@@@@@" );
          }
        } catch (ClassCastException e) {
          // Assuming class cast exception on prefer since we don't
          // know for sure what args it takes.
        }
        if ( debug ) System.out.println( "?:-| ******!!!!!!!!!%%%%%%%############&&&&&&&&&@@@@@@@@@" );
      } catch (Throwable t) {
        if ( debug ) System.out.println( ":-( ******!!!!!!!!!%%%%%%%############&&&&&&&&&@@@@@@@@@" );
        ////t.printStackTrace();
        // Assumed exception when invoking call.evaluate( true )
        prefersRawArgs = true;
      }
    return prefersRawArgs;
  }

    public Expression<?> elementValueToAeExpression( Object argValueNode,
                                                     String argValName ) {
      String argType = null;
      Parameter<Object> param = null;
      Collection<U> argValPropNodes = null;
      Object argValProp = null;
      String type = model.getTypeString((C)argValueNode, (Object) null);
      if ( type.equals("Property") ) {
        Object v = model.getValue((C)argValueNode, null);
        int ct = 0;
        while ( v != null && v instanceof Collection && ct < 10) {
          v = ((Collection<?>)v).iterator().next();
          ct++;
        }
        try {
          if ( v != null ) type = model.getTypeString((C)v, (Object) null);
        } catch (Throwable t ) {
          if ( v != null ) type = "" + ClassUtils.getType( v );
        }
      }
      Boolean setValue = true;
      
      if (type == null) {
        argValProp = argValueNode;
        argType = "Object";
      }
      else {
        setValue = !type.equals("Parameter"); // Dont set the value for Parameters
        
        // Get the value of the argument based on type:
        if (type.equals("LiteralInteger") || type.equalsIgnoreCase( "int" ) || type.equalsIgnoreCase( "integer" )) {
          
          argValPropNodes = model.getValue((C)argValueNode, "integer");
          argType = "Long";
        }
        else if (type.equals("LiteralReal") || type.equalsIgnoreCase( "float" ) || type.equalsIgnoreCase( "double" ) ) {
          
          argValPropNodes = model.getValue((C)argValueNode, "double");
          argType = "Double";
        }
        else if (type.equals("LiteralBoolean") || type.equalsIgnoreCase( "bool" ) || type.equalsIgnoreCase( "boolean" ) ) {
          
          argValPropNodes = model.getValue((C)argValueNode, "boolean");
          argType = "Boolean";
        }
        else if (type.equals("LiteralUnlimitedNatural")) {
          
          argValPropNodes = model.getValue((C)argValueNode, "naturalValue");
          argType = "Long";
        }
        else if (type.equals("LiteralString") || type.equalsIgnoreCase( "string" ) || type.equalsIgnoreCase( "str" ) ) {
          
          argValPropNodes = model.getValue((C)argValueNode, "string");
          argType = "String";
        }
        else if (type.equals("OpaqueExpression")) {
          
          argValPropNodes = model.getValue((C)argValueNode, "expressionBody");
          argType = "String";
        }
        else if (type.equals("LiteralNull")) {
          argValProp = null;
          argType = null;
        }
        // Otherwise, will just set the value of the parameter to the node itself:
        else {
            argValProp = argValueNode;
            argType = "Object";
        }
      }
      
      if (!Utils.isNullOrEmpty(argValPropNodes)) {
        
        // TODO can we assume this is always size 1?  
        if ( argValPropNodes.size() == 1 ) {
          argValProp = argValPropNodes.iterator().next();
        } else {
          argValProp = argValPropNodes;
        }
        Debug.outln( "\nargValProp = " + argValProp );
      }
      
      // Wrap the argument in a Parameter:
      // Note: argType can be null
      param = (Parameter<Object>)classData.getParameter( null, argValName, argType, false, true, true, false );

      // Set value of the param to value if it has one,
      // and add to the argument list:
      if (param != null) {
        
        if ( Debug.isOn() ) Debug.outln( "\nparam = " + param );
        if (argValProp != null && setValue) {
            param.setValue(argValProp);   
        }
        
        // Add to map:
        P propNode =  model.asProperty( argValueNode );
        if (!exprParamMap.containsKey( propNode ) ) {
          putExprParamMap( propNode, param );
        }
        
        //System.out.println( "\nelementValueToAeExpression(" + argValueNode + ", " + argValName + ") = param = " +  param );
        return new Expression<Object>(param);
      }
      
      // Creating param failed, so just add the value object itself wrapped in an
      // Expression:
      else {
        //System.out.println( "\nelementValueToAeExpression(" + argValueNode + ", " + argValName + ") = argValProp = " +  argValProp );
        return new Expression<Object>(argValProp);
      }
      
    }
    
    /**
     * Returns the node for the passed operand property value.  Mainly needed
     * to process ElementValues.
     */
    private P getValueOfElement(P operandProp)
    {
      
      P valueOfElementNode = null;
      
      // We assume that it is an ElementValue, so get the id of the referenced
      // element:
      //Collection< T > foo = model.getType(model.asContext( operandProp ), null);
      //Collection< P > valueOfElemNodes = 
      //    model.getProperty(model.asContext( operandProp ), "type");
      Collection< P > valueOfElemNodes = 
              model.getProperty(model.asContext( operandProp ), "element");
      
      // If it is a elementValue, then this will be non-empty:
      if (!Utils.isNullOrEmpty(valueOfElemNodes)) {
                      
        // valueOfElemNodes should always be size 1 b/c element
        // is a single NodeRef
        valueOfElementNode = valueOfElemNodes.iterator().next();
        
      }
      
      // Otherwise just use the node itself as we are not dealing with
      // elementValue types:
      else {
        valueOfElementNode = operandProp;
      }
      
      return valueOfElementNode;
    }
         
    /**
     * Processes the passes Operation element and adds
     * to the argument list
     * 
     */
    private N processOperation(P valueOfElementNode,
                               Vector<Expression<?>> arguments,
                               boolean isOperandArg) {
        
        // running example !:
        //   Viewpoint vp exposes exposed and has an Operation vp_op(foo), which 
        //   is defined as the Expression, List(map(foo, Name)).
        // 
        //   1st call: List = processOperation(List, [], false)
        //   2nd call: map = processOperation(map, [], false)
        //   3rd call: map = processOperation(map, [], true)
      
        N operationName = null;
        N operNames = model.getName((C)valueOfElementNode);
        
        // TODO should we add to the arguments list even if its
        // the operand operation name and not a operand arg?
        // ie get_names.  No!  how should we handle this.
        // quick fix is below, not quite right b/c at times
        // we will need to add the arg list, this is when the
        // operantionName is not a java call but just a model element.
        // Perhaps do the createCall() checks on it in here to check?
        // Thats not very robust.

        if (!Utils.isNullOrEmpty((String)operNames)) {

            operationName = operNames;
            
            // Only add to argument list if its a operand arg:
            if (isOperandArg) {
                
                Collection<P> opExpProps = model.getProperty( (C)valueOfElementNode, 
                                                              "expression" );
                Collection<P> opParamProps = model.getProperty( (C)valueOfElementNode, 
                                                               "parameters" );
                
                // If the Operation has no expression then 
                // make a Call for it:
                //if (Utils.isNullOrEmpty( opExpProps )) {
                    
                    Vector<Object> opEmptyArgs = new Vector<Object>();
    
                    // If it has parameter make a empty arg
                    // for each parameter:
                    if (!Utils.isNullOrEmpty( opParamProps )) {
                        
                        for (int i = 0; i < opParamProps.size(); ++i) {
                            opEmptyArgs.add( new Expression< Object >( (Object)null ) );//new Object() ) );
                        }
                    }
                    
                    // The object from which the operation is invoked.
                    Object object = null;
                    
                    // 
                    Call argCall;
                    
                    // If an expression is defined for the operation, we wrap the invocation
                    // of the operation in a Java method call, that we later wrap as a
                    // FunctionCall.
                    if (!Utils.isNullOrEmpty( opExpProps )) {
                      EvaluateOperation evo =
                          new EvaluateOperation( valueOfElementNode );
                      object = this;
                      operationName = (N)"evaluate";
                      Vector<Object> newArgs = new Vector< Object >();
                      newArgs.add( evo );
                      newArgs.add( opEmptyArgs );//.toArray() );
                      opEmptyArgs = newArgs;
                      //////call = new ConstructorCall( this, OperationFunctionCall.class, argTypes.toArray(new Class[argTypes.size()]) );
                      //call = new ConstructorCall( this, OperationFunctionCall.class, new Object[] { object, arguments } );
                      ////call = new OperationFunctionCall( object, arguments );
                      argCall = new OperationFunctionCallConstructorCall( object, opEmptyArgs );

                    } else {
                      // Create a Call for the argument 
                      argCall = createCall(object, operationName, opEmptyArgs, opEmptyArgs);
                                            
                    }
                    //System.out.println("*******************************************");
                    //System.out.println("args for call=" + opEmptyArgs);
                    //System.out.println("*******************************************");
                    
                    if ( argCall != null ) {
                       
                      // Add to the argument list:
                      arguments.add(new Expression<Object>(argCall));
            
                    } // Ends if argCall != null
                      
//                }
//                
//                // TODO FIXME this is no longer correct below:
//                // If the Operation has a expression then
//                // use operationToAeExpression to process
//                else {
//                  
//                  // Create a function call for 
//                  //   operationToAeExpression( valueOfElementNode, opParamOps ).evaluate()
//                  //      
//                  EvaluateOperation evo = new EvaluateOperation< P >( valueOfElementNode );
//                  // Create a Call for the argument 
//                  Call argCall = createCall(null, operationName, opEmptyArgs);
//                  
//                  
//                  
//                  Debug.error("Error: dont know how to process a Operation arg with a expression!");
//
//                  System.out.println("This is weird!");
////                    List<Object> parameterValues = new ArrayList<Object>();
////                    
////                    // If it has parameters then pass in the
////                    // parameter values:
////                    if (!Utils.isNullOrEmpty( opParamProps )) {
////                        // If the argument is a Parameter (wrapped in an expression),
////                        // then get its value:
////                        for (Object arg : arguments) {
////                            if (arg instanceof Expression) {
////                                Expression<?> expr = (Expression<?>)arg;
////                                
////                                if (expr.form.equals(Expression.Form.Parameter)) {
////                                    Parameter<?> param = (Parameter<?>)expr.expression;
////                                    parameterValues.add( param.getValue() );
////                                }
////                            }
////                        }
////                    }
////                    
////                    arguments.add( operationToAeExpression(valueOfElementNode,
////                                                           parameterValues));
////                
//                }
            
            } // ends if operand arg
         
        }
        
        return operationName;
    }
    
    /**
     * A functor for wrapping a call on a SysML Operation as a Java function call.
     * <p>
     * An Operation may be defined with a SysML Expression. We want to be able to
     * evaluate the Operation's Expression with arguments passed in to its
     * Parameters. So, we need to translate the Expression to an AE Expression and
     * substitute the arguments before invocation/evaluation. The evaluate method
     * of EvaluateOperation is the Java method that, when invoked, invokes the
     * Operation's Expression with the specified arguments.
     */
    public class EvaluateOperation {
        P operation;
        public EvaluateOperation( P operation ) {
          this.operation = operation;
        }
//        public Object evaluate( Object[] sysmlParameters ) {
//            return null;
//        }
        public Object evaluate( Object...sysmlArguments ) {
//          return evaluate(sysmlParameters);
//        }
//        Object evaluate( Collection<P> sysmlParameters ) {
//          List<Object> paramList = Utils.asList( sysmlParameters, Object.class );
          Vector<Object> paramList = new Vector<Object>(Utils.arrayAsList( sysmlArguments ));
          Object result = operationToAeExpressionImpl( operation, paramList, paramList );//.evaluate(true);
          return result;
        }
    }

  /**
   * OperationFunctionCallConstructorCall wraps an OperationFunctionCall in a
   * constructor, i.e., when evaluated, this constructs an
   * OperationFunctionCall. The OperationFunctionCall is used to wrap a SysML
   * Expression whose Operation is defined as a SysML Expression so that it can
   * be evaluated via a Java method invocation.
   *
   */
    public class OperationFunctionCallConstructorCall extends ConstructorCall {
      public OperationFunctionCallConstructorCall( Object object, Vector< Object > arguments ) {
        super( object, OperationFunctionCall.class, arguments );
      }
      @Override
      protected synchronized void sub( int indexOfArg, Object obj ) {
        Vector< Object > args = arguments;
        if ( args != null && args.size() == 2 && (args.get( 1 ) instanceof Vector || args.get( 1 ) == null || args.get( 1 ).getClass().isArray() ) ) {
          Object innerArgs = args.get( 1 );
          if ( innerArgs instanceof Vector ) {
            args = new Vector<Object>((Vector<Object>)args.get( 1 ));
            Call.sub( this, args, indexOfArg, obj );
            arguments.set( 1, args );
          } else if ( innerArgs != null ) {
            Call.sub( (Object[])innerArgs, indexOfArg-1, obj );
          }
          setStale( true );
        } else {
          Debug.error("Unexpected arguments to OperationFunctionCallConstructorCall: " + arguments );
          return;
        }
          
      }

    }

    /**
     * The OperationFunctionCall is used to wrap a SysML Expression whose
     * Operation is defined as a SysML Expression so that it can be evaluated via
     * a Java method invocation. The method is the evaluate method of an
     * EvaluateOperation, which wraps the SysML Operation, an EmsScriptNode.
     */
    public class OperationFunctionCall extends FunctionCall {

//        public OperationFunctionCall( P operation,
//                                      Vector< Object > arguments ) {
//            super( new EvaluateOperation( operation ), EvaluateOperation.class, "evaluate", arguments );
//        }

        public OperationFunctionCall( Object object, Vector< Object > arguments ) {
            super( object, EvaluateOperation.class, "evaluate", arguments );
        }
    }
    
    /**
     * Get a name for the operation whether an Operation element, a String, or a
     * name of type N.
     * 
     * @param operation
     * @return
     * @throws ClassCastException
     */
    N getOperationName( Object operation ) throws ClassCastException {
      if ( operation == null ) return null;
      N operationName = null;
      if ( operation instanceof String ) {
        operationName = model.asName( operation );
        return operationName;
      }      
      N operationNames = model.getName( (C)operation );
      if ( !Utils.isNullOrEmpty( (String)operationNames  ) ) {
        operationName = operationNames;
      } else {
        try {
          operationName = (N)operation;
        } catch ( ClassCastException ignore ) {
          operationName = (N)("" + operation); // this may throw ClassCastException
        }
      }
      
      return operationName;
    }
    
    /**
     * bogus method to force jar update
     * @param operation
     * @return
     */
    String getOperationNameString( Object operation ) {
      return "" + getOperationName( operation );
    }
    
    protected String getOperationLiteralString( P operation ) {
      String typeName = model.getTypeString( (C)operation, null );
      
      if ( typeName != null && typeName.equals( "LiteralString" ) ) {
        // By default, this is a string reference to an Java function, but it
        // could also be to an Operation or some other element.
        Collection< U > values = model.getValue( (C)operation, null );
        if ( !Utils.isNullOrEmpty( values ) ) {
          if ( values.size() > 1 ) {
            // TODO -- ERROR -- only expected one
          }
          U v = values.iterator().next();
          if ( v instanceof String ) {
            return (String)v;
          }
        }
      }
      return null;
    }

    
    /**
     * Converts the passed sysml expression to an AE expression.
     *
     * @param expressionElement The sysml expression to parse
     * @return The converted to AE expression
     */
    public <X> Expression<X> toAeExpression( Object expressionElement) {
      
      Expression<X> expression = null;

      // Check for null input.
      if ( expressionElement == null ) return null;
      if ( model == null ) {
          Debug.error( "Model cannot be null!" );
          return null;
      }

      // If it is not an Expression than we cannot process it:
      String expressionType = model.getTypeString((C)expressionElement, null);
      if (!expressionType.equals("Expression")) {
        Debug.error( "Passed expression is not an Expression type, got type "+ expressionType);
        return null;
      }

      // Pull out the operation, and recursively process the arguments. 
      
      Collection< P > operands = model.getProperty( (C)expressionElement, "operand");
      
      if ( Utils.isNullOrEmpty( operands ) ) return null;

      // first operand must be the operation
      Iterator< P > it = operands.iterator();
      
      P operation = getValueOfElement(it.next());
      Object operationObj = operation;
      String opLiteralString = getOperationLiteralString( operation );
      if ( opLiteralString != null ) operationObj = opLiteralString;
      
      // The other operands are model element arguments to the operation. 
      Vector< Object > arguments = new Vector< Object >();
      Vector< Object > rawArgs = new Vector< Object >();
      while (it.hasNext() ) {
        P rawArg = it.next();
        
        // This will return the value of the element that is referenced by rawArg
        // if rawArg is an ElementValue. So, if rawArg points to a Property, the
        // property value is added to arguments.
        arguments.add( elementArgumentToAeExpression( rawArg ) );
        
        // Get the elementValueOfElement from the ElementValue if that's what this
        // is. So, if rawArg references a Property, the Property is added to
        // rawArgs.
        Object arg = getValueOfElement(rawArg);
        if ( arg == null ) arg = rawArg;

        rawArgs.add( arg );
      }

      // System.out.println( "\ntoAeExpression(" + expressionElement + ") = operationToAeExpressionImpl(" + operation + ", " + arguments + ")" );
      return operationToAeExpressionImpl( operationObj, arguments, rawArgs );
    }

    protected <X> Expression<X> operationToAeExpressionImpl( Object operation,
                                                             Vector< Object > aeArgs,
                                                             Vector< Object > rawArgs ) {
      Expression<X> expression = null;
      // If the operation is a SysML Operation, call operationToAeExpression2() to get the expression.
      String operationType = null;
      if ( !( operation instanceof String ) ) {
        operationType = model.getTypeString((C)operation, null);
      }
      if ( operationType != null && operationType.equals( "Operation" ) ) {
        Collection< P > opExpProps =
            model.getProperty( (C)operation, "expression" );
        Collection< P > opParamProps =
            model.getProperty( (C)operation, "parameters" );

        // Replace the parameters with the arguments.
        if ( !Utils.isNullOrEmpty( opExpProps ) ) {
          Expression< X > opExpression = toAeExpression( opExpProps.iterator().next() );
          if ( !Utils.isNullOrEmpty( opParamProps ) ) {
            Iterator< Object > it = aeArgs.iterator();
            // TODO -- Check to see if last Parameter is for a variable number of arguments!
            for ( P param : opParamProps ) {
              if ( !it.hasNext() ) {
                // TODO -- ERROR! Length is not the same!
                Debug.error( true, true, "ERROR! The number of arguments to "
                             + operation
                             + " is not the same as the number of parameters!" );
                break;
              }
              Object aeArg = it.next();
              if (exprParamMap.containsKey(param)) {
                Parameter< Object > aeParam = exprParamMap.get( param );
                opExpression.substitute( aeParam, aeArg, true, null );
              }
            }
            if ( it.hasNext() ) {
              // TODO -- ERROR!  Length is not the same!
              Debug.error( true, true,
                           "ERROR! The number of arguments to " + operation
                               + " is not the same as the number of parameters!"
                               + "  Parameters representing a variable number of arguments are not yet supported");
            }
          }
          expression = opExpression;
        }
        //expression = operationToAeExpression2( operation, arguments  );
      }
      
      // If operation is not an Operation model element, or if the Operation does
      // not have an Expression defining it, use its name to try and match against
      // other implementations that may be intended.
      if ( expression == null || ( expression.expression == null && expression.form != Form.Value ) ) {
        N operationName = getOperationName( operation );
        Call call = createCall(null, operationName, aeArgs, rawArgs );
        expression = new Expression( call ); // FIXME what to do if call is null?
        //expression = new Expression( call.evaluate( false ) ); // This breaks test case 22
      }
      
      //System.out.println( "\noperationToAeExpressionImpl(" + operation + ", " + aeArgs + ") = " + expression );
      return expression;
    }

    /**
     * Translate a SysML Operation, SysML Expression, or name to an AE Expression.  
     * @param arg
     * @return
     */
    public Expression<?> elementArgumentToAeExpression( P arg ) { // Why is this P and not T??
      
      String typeString = null;
      
      if ( arg == null) return new Expression<Object>( (Object)null, Object.class );
      
      // Handle special case of expose parameter value, which is a collection of EmsScriptNode
      // If it is not this special case, then get its type:
      // FIXME better way to handle this
      if (!Collection.class.isAssignableFrom(arg.getClass())) {

        // Get the valueOfElementProperty node:
        arg = getValueOfElement(arg);
  
        typeString = model.getTypeString((C)arg, null);
      
      }
      
      // If the typeString is null, then create a Parameter for it 
      if (typeString == null) {
        // The argument name needs to be unique, so we'll use the identifier.
        return elementValueToAeExpression(arg, "" + model.getIdentifier((C)arg));
      }
      
      // If it is an Operation, then it may be meant as a function pointer for
      // functional programming use, like with map.
      else if (typeString.equals("Operation")) {

        Vector<Expression<?> > v = new Vector< Expression<?> >();
        processOperation( arg, v, true);
        
        if ( !Utils.isNullOrEmpty( v ) ) {
          //System.out.println( "\nelementArgumentToAeExpression(" + arg + ") = " + v.firstElement() );
          return v.firstElement();
        }
        else {
          Debug.error( true, true,
                      "\nERROR! processOperation(" + arg + ") didn't return a call!" );
        }
        
      }
      
      else if (typeString.equals("Expression")) {
        //System.out.println( "\nelementArgumentToAeExpression(" + arg + ") = toAeExpression(" + arg +  ")" );
        return toAeExpression(arg);
      }
                  
      // All other cases failed, then just create a Parameter for
      // it, ie it is a Property, Parameter, Element, a LiteralInt, etc:
      else {
                
        // Get the argument Node:
        Collection<U > argValueNodes = model.getValue((C)arg, null);

        // Get the name of the argument Node:
//        Collection<N > argValueNames = model.getName(arg);
//        String argValName = Utils.isNullOrEmpty(argValueNames) ? null : 
//                                                                 argValueNames.iterator().next().toString();
        // This needs to be unique, so we'll use the identifier.
        String argValName = "" + model.getIdentifier( (C)arg );
        
        // TODO can we assume this will always be size one?
        Object argValueNode = Utils.isNullOrEmpty(argValueNodes) ? arg : argValueNodes.iterator().next();
        if ( Debug.isOn() ) Debug.outln( "\nargValueNode = " + argValueNode );

        String argName = Utils.isNullOrEmpty(argValName) ?  argValueNode.toString() : argValName;

        // Create a Parameter for the argument and add to arguments:
        //System.out.println( "\nelementArgumentToAeExpression(" + arg + " = elementValueToAeExpression(" + argValueNode + ", " + argName + ")" );
        if ( !model.getElementClass().isInstance( argValueNode ) ) {
          argValueNode = arg;
        }
        return elementValueToAeExpression(argValueNode, argName);
                  
      } // ends else 
      
      return null;
      
    }
    
    /**
     * Converts the passed sysml Operation to an AE Expression
     * 
     * @param operationElement the sysml operation to convert
     * @param parameterValues a List of parameter values for the operation
     *        The order of the elements of this list must match the
     *        order of elements of parameter for the passed
     *        Operation.
     * @return the AE expression
     */
    public <X> Expression<X> operationToAeExpression( Object operationElement, 
                                                      ArrayList<Object> parameterValues) {
      return operationToAeExpression( operationElement,
                                      new Vector<Object>(parameterValues) );
    }

    /**
     * Converts the passed sysml Operation to an AE Expression
     * 
     * @param operationElement the sysml operation to convert
     * @param parameterValues a List of parameter values for the operation
     *        The order of the elements of this list must match the
     *        order of elements of parameter for the passed
     *        Operation.
     * @return the AE expression
     */
    public <X> Expression<X> operationToAeExpression( Object operationElement, 
                                                      Vector<Object> parameterValues) {
      
      // running example !:
      //   Viewpoint vp exposes exposed and has an Operation vp_op(foo), which 
      //   is defined as the Expression, List(map(foo, Name)).
      // 
      //   1st call = operationToAeExpression( vp_op( foo ), exposed )
      
      // Converting parameterValues to AeExpressions:
      Iterator< Object > it = parameterValues.iterator();
      Vector< Object > args = new Vector< Object >();
      while (it.hasNext() ) {
        args.add( elementArgumentToAeExpression( (P)it.next() ) ); // FIXME dont like warning, also this is a Collection<EmsScriptNodes> which our methods are not equipped for
      }
      
      return operationToAeExpressionImpl( (P)operationElement, // FIXME dont like warning
                                          args,
                                          parameterValues );

   }
    
    public P getElementForAeParameter( Expression< ? > paramExpression ) {
      // TODO Auto-generated method stub
      if ( paramExpression == null && paramExpression.expression == null || !(paramExpression.expression instanceof Parameter) ) {
        // TODO -- ERROR
        return null;
      }
      Parameter<?> parameter = (Parameter< ? >)paramExpression.expression;
      return model.asProperty( paramExprMap.get( parameter ) );
    }
    
    // accessors
    
    public SM getModel() {
        return model;
    }
    
    public void setModel( SM model ) {
        this.model = model;
    }
    
    public ClassData getClassData() {
      return classData;
    }

    public void setClassData( ClassData classData ) {
      this.classData = classData;
    }

}
