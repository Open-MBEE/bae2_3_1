/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.event.Expression.Form;
import gov.nasa.jpl.ae.event.TimeVaryingMap.BoolOp;
import gov.nasa.jpl.ae.event.TimeVaryingMap.Inequality;
import gov.nasa.jpl.ae.solver.AbstractFiniteRangeDomain;
import gov.nasa.jpl.ae.solver.AbstractRangeDomain;
import gov.nasa.jpl.ae.solver.BooleanDomain;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.DoubleDomain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.MultiDomain;
import gov.nasa.jpl.ae.solver.ObjectDomain;
import gov.nasa.jpl.mbee.util.Random;
import gov.nasa.jpl.ae.solver.RangeDomain;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.DomainHelper;
import gov.nasa.jpl.mbee.util.Infinity;
import gov.nasa.jpl.mbee.util.NegativeInfinity;
import gov.nasa.jpl.mbee.util.NegativeOne;
import gov.nasa.jpl.mbee.util.One;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Zero;
import gov.nasa.jpl.mbee.util.Wraps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * 
 *         TODO -- Try to define methods with <T> arguments instead of
 *         Expression<T>. FunctionCall may now support both with evaluateArgs().
 * 
 */
public class Functions {

//  private static boolean complainAboutBadExpressions = true;
  
  private static Expression forceExpression ( Object o ) {
    if ( o instanceof Expression ) return (Expression<?>)o;
    if ( o instanceof Parameter ) return new Expression( (Parameter<?>)o );
    if ( o instanceof Call ) return new Expression( (Call)o );
    return new Expression( o );
  }

  // Abstract n-ary functions
  public static class SuggestiveFunctionCall extends FunctionCall implements Suggester {
    public FunctionCall pickFunctionCall = null;
    public FunctionCall reversePickFunctionCall = null;
    public FunctionCall isGrounded = null;
    
    public SuggestiveFunctionCall( //Method isGroundedMethod,
                                   Object object, Method method,
                                   Object[] arguments ) {
      super( object, method, arguments, (Class<?>)null );
      //if 
    }

    public SuggestiveFunctionCall( SuggestiveFunctionCall suggestiveFunctionCall ) {
      super(suggestiveFunctionCall);
      pickFunctionCall = suggestiveFunctionCall.pickFunctionCall;
      reversePickFunctionCall = suggestiveFunctionCall.reversePickFunctionCall;
    }

    public SuggestiveFunctionCall( FunctionCall functionCall ) {
      super(functionCall);
    }

    public SuggestiveFunctionCall( FunctionCall functionCall,
                                   FunctionCall pickFunctionCall,
                                   FunctionCall reversePickFunctionCall ) {
      super(functionCall);
      this.pickFunctionCall = pickFunctionCall;
      this.reversePickFunctionCall = reversePickFunctionCall;
    }

    // TODO -- Need a value argument -- the target return value! Then, this can
    // be renamed "inverse()."
    @Override
    public < T > T pickValue( Variable< T > variable ) {
      return Functions.pickValueBF2( this, variable );
    }
    
    @Override
    public SuggestiveFunctionCall clone() {
      SuggestiveFunctionCall c = new SuggestiveFunctionCall(this);
      return c;
    }
    
    @Override
    public Domain< ? > calculateDomain( boolean propagate,
                                        Set< HasDomain > seen ) {
      if ( !isMonotonic() ) {
        // Must be overridden
        Debug.error(true, true, "FunctionCall.calculateDomain() must be overridden by " + this.getClass().getName());
        return null;
      }
      SuggestiveFunctionCall fc = this.clone();
      Domain<?> d = DomainHelper.combineDomains( arguments, fc, true );
      return d;
    }
    /**
     * Invert the function with respect to a range/return value and a given 
     * argument.
     * <p>
     * If g is the inverse of f, then <br>
     * g(f(x)) = x.<br>
     * g(f(x,y)) = {(u,v) | f(u,v) = f(x,y)}<br>
     * g(f(x,y),x) = {v | f(x,v) = f(x,y)}<br>
     * g(f(x,y),x,y) = true
     * <p>
     * So, if f(x,y) = x + y, then<br>
     * g(z) = {(u,v) | u + v = z}<br>
     * g(z,x) = {v | v = z - x}<br>
     * g(z,x,y} = true if x + y = z, else false
     * <p>
     * Given a FunctionCall f with arguments (a1, a2, .. an) where n is the
     * number of arguments, g = f.inverse(r, ai) is a FunctionCall where ai must
     * be an argument to f. g.evaluate() returns a Domain representing the set
     * of values that ai may be assigned such that f.evaluate() == r.
     * 
     * @param returnValue
     * @param arg
     *          the single argument with respect to which the inverse is
     *          constructed (ex. the x in f(x))
     * @return a new FunctionCall that returns a set of possible values
     * <p>
     * <b>Subclasses should override this method if the function is not
     * a bijection</b> (one-to-one and the domain and range are the same),
     * in which case the inverse may not be a single value. For example,
     * if f(x)=x^2, then the inverse is {sqrt(x), -sqrt(x)}.
     * 
     */
    public FunctionCall inverse( Object returnValue, Object arg ) { //Variable<?> variable ) {
      FunctionCall singleValueFcn = inverseSingleValue( returnValue, arg );
      if ( singleValueFcn == null ) return null;
      return new FunctionCall(null,
                              ClassUtils.getMethodsForName( Utils.class, "newList" )[0],
                              new Object[] { singleValueFcn }, (Class<?>)null );
    }

    /**
     * Invert the function with respect to a range/return value and a given
     * argument.
     * <p>
     * This could be implemented by calling {@link #inverse(Object, Object)} and
     * selecting a value from the set of possible values returned by the inverse.
     * 
     * @param returnValue
     * @param arg
     *          the single argument with respect to which the inverse is
     *          constructed (i.e. the x in f(x))
     * @return a new FunctionCall that returns a possible value
     * <p>
     * <b>Subclasses of {@link SuggestiveFunctionCall} should override this 
     * method.</b>
     */
    public FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      return null;
    }

  }
  
  public static class Binary< T , R > extends SuggestiveFunctionCall implements Suggester {
    //public SuggestiveFunctionCall functionCall = null;
    //public SuggestiveFunctionCall pickFunctionCall = null;
    //public SuggestiveFunctionCall reversePickFunctionCall = null;

    public Binary( Variable< T > o1, Expression< T > o2,
                   String functionMethod ) {
      super( (Object)null,
                                         getFunctionMethod( functionMethod ),
                                         new Object[]{ o1, o2 } );
      //functionCall = this;//(SuggestiveFunctionCall)this.expression;
    }

    public Binary( Expression< T > o1, Expression< T > o2,
                   String functionMethod ) {
      super( //new SuggestiveFunctionCall( 
             (Object)null,
                                         getFunctionMethod( functionMethod ),
                                         new Object[]{ o1, o2 } 
             //)
             );
      //functionCall = this;//(SuggestiveFunctionCall)this.expression;
    }

    public Binary( Expression< T > o1, Expression< T > o2,
                   String functionMethod,
                   String pickFunctionMethod1,
                   String pickFunctionMethod2 ) {
      this( o1, o2, functionMethod );
      //functionCall.
      pickFunctionCall =
          new FunctionCall( (Object)null,
                            getFunctionMethod( pickFunctionMethod1 ),
                            //functionCall.
                            getArgumentArray(), (Class<?>)null );
      Vector< Object > args = new Vector<Object>( //functionCall.
          getArgumentVector() );
      Collections.reverse( args );
      //functionCall.
      reversePickFunctionCall =
          new FunctionCall( (Object)null,
                            getFunctionMethod( pickFunctionMethod2 ),
                            args.toArray(), (Class<?>)null );
    }

    public Binary( Object o1, Object o2, String functionMethod,
                   String pickFunctionMethod1, String pickFunctionMethod2 ) {
      this( forceExpression( o1 ), forceExpression( o2 ),
            functionMethod,
            pickFunctionMethod1, pickFunctionMethod2 );
      // this( ( o1 instanceof Expression ) ? )
    }

    public Binary( Object o1, Object o2, String functionMethod ) {
      this( forceExpression( o1 ), forceExpression( o2 ), functionMethod );
    }

    public Binary( Binary< T, R > b ) {
      super(b);
    }

    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.event.Expression#isGrounded(boolean, java.util.Set)
     */
    @Override
    public boolean isGrounded(boolean deep, Set< Groundable > seen) {
      if ( arguments == null || arguments.size() < 2 ) return false;
      if ( !areArgumentsGrounded( deep, seen ) ) return false;
      return true;
    }

    private static Method getFunctionMethod( String functionMethod ) {
      Method m = null;
      try {
        m = Functions.class.getMethod( functionMethod, 
                                       Expression.class,
                                       Expression.class );
      } catch ( SecurityException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return m;
    }
    
    @Override
    public < T1 > T1 pickValue( Variable< T1 > variable ) {
      return pickValueBF2( this,//functionCall, 
                           variable );
    }

    public Vector< Expression > getArgumentExpressions() {
      Vector< Expression > argExprs =
          new Vector< Expression >( (Collection< Expression >)Utils.asList( Utils.newList( super.getArgumentArray() ),
                                                                            Expression.class ) );
      return argExprs;
    }
    
    public Domain< ? > inverseDomain( Domain<?> returnValue, Object argument ) {
      if ( returnValue == null ) return null;
      //FunctionCall inverse = inverse( returnValue, argument );
      //if ( inverse == null ) return null;
      Domain<?> theCombineDomain = null;
      LinkedHashSet<Object> possibleValues = new LinkedHashSet< Object >();
      if ( returnValue instanceof AbstractRangeDomain ) {
        if ( isMonotonic() ) {
          AbstractRangeDomain ard = (AbstractRangeDomain)returnValue;
          addInverseToList( ard.getLowerBound(), argument, possibleValues );
          if ( ard.getLowerBound() != ard.getUpperBound() ) {
            addInverseToList( ard.getUpperBound(), argument, possibleValues );
          }
        } else {
          // not monotonic, such as Equals
          if ( !returnValue.isInfinite() ) {
            for( long i = 0; i < returnValue.magnitude(); ++i ) {
              Object rv = ((AbstractRangeDomain)returnValue).getNthValue( i );
              addInverseToList( rv, argument, possibleValues );
            }
          }
        }
      }
      // TODO -- Handle the million other cases
      theCombineDomain = DomainHelper.combineDomains( Utils.asList( possibleValues ),
                                                      new Identity<T>( (Expression<T>)null ),
                                                      true );
      return theCombineDomain;
    }
    
    protected void addInverseToList(Object returnValue, Object argument, Collection<Object> listOfInverseResults) {
      FunctionCall inverse = inverse(returnValue, argument );
      if ( inverse == null ) {
        return;
      }
      Object cObj = null;
      try {
        cObj = inverse.evaluate( false, true );
      } catch ( IllegalAccessException e ) {
        e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        e.printStackTrace();
      } catch ( InstantiationException e ) {
        e.printStackTrace();
      }
      if ( cObj instanceof Collection ) {
        Collection<?> c = (Collection<?>)cObj;
        listOfInverseResults.addAll( c );
//      } else if ( cObj instanceof Domain ) {
//        
      } else {
        listOfInverseResults.add( cObj );
      }
    }
    
    @Override
    public < TT > Pair<Domain< TT >,Boolean> restrictDomain( Domain< TT > domain,
                                             boolean propagate,
                                             Set< HasDomain > seen ) {
      Object o1 = this.arguments.get( 0 );
      Object o2 = this.arguments.get( 1 );
      boolean changed = false;
      if (o1 instanceof HasDomain && o2 instanceof HasDomain){
        HasDomain hd1 = (HasDomain)o1;
        HasDomain hd2 = (HasDomain)o2;
        Domain d1 = inverseDomain( domain, o1 );
        Pair< Domain<?>, Boolean > p = hd1.restrictDomain( d1, propagate, seen );
        if ( p != null && p.second == Boolean.TRUE ) changed = true;
        if ( p != null && p.first != null && p.first.isEmpty() ) {
          if ( this.domain != null ) {
            this.domain.clearValues();
          }
//          return new Pair( this.domain, changed );
        } else {
          Domain d2 = inverseDomain( domain, o2 );
          p = hd2.restrictDomain( d2, propagate, seen );
          if ( p != null && p.second == Boolean.TRUE ) {
            changed = true;
          }
        }
      }
      this.domain = (Domain< T >)getDomain(propagate, seen );
      return new Pair(this.domain, changed);
    }
  }

  public static abstract class BooleanBinary< T > extends Binary< T, Boolean > 
                                                  implements Suggester {

    public BooleanBinary( Expression< T > o1, Expression< T > o2,
                          String functionMethod ) {
      super( o1, o2, functionMethod );
    }
    public BooleanBinary( Expression< T > o1, Expression< T > o2,
                          String functionMethod,
                          String pickFunctionMethod1, String pickFunctionMethod2 ) {
      super( o1, o2, functionMethod, pickFunctionMethod1, pickFunctionMethod2 );
    }
    public BooleanBinary( Object o1, Object o2, String functionMethod ) {
      super( o1, o2, functionMethod );
    }
    public BooleanBinary( Object o1, Object o2,
                          String functionMethod,
                          String pickFunctionMethod1, String pickFunctionMethod2 ) {
      super( o1, o2, functionMethod, pickFunctionMethod1, pickFunctionMethod2 );
    }
    
    public BooleanBinary( BooleanBinary<T> bb) {
      super(bb);
    }
    
//    /* (non-Javadoc)
//     * @see gov.nasa.jpl.ae.event.Call#restrictDomain(gov.nasa.jpl.ae.solver.Domain, boolean, java.util.Set)
//     */
//    @Override
//    public < TT > Pair<Domain< TT >,Boolean> restrictDomain( Domain< TT > domain,
//                                                             boolean propagate,
//                                                             Set< HasDomain > seen ) {
//      boolean changed = false;
//      if ( domain.contains((TT)Boolean.TRUE) & domain.contains((TT)Boolean.FALSE) ) {
//      } else if ( domain.magnitude() == 1 ) {
//        Object v = domain.getValue( propagate );
//        if ( v instanceof Boolean ) {
//          changed = restrictDomains(((Boolean)v) == Boolean.TRUE);
//        }
//      }
////      Domain oldDomain = this.domain.clone();
////      Domain newDomain = (Domain< TT >)getDomain(propagate, null);
////      boolean thisChanged = Utils.valuesEqual( oldDomain, newDomain );
////      this.domain = newDomain;
//      return new Pair(this.domain, changed);// || thisChanged);
//    }
//    
//    // REVIEW -- This seems out of place.  Does something else do this?
//    public abstract boolean restrictDomains( boolean targetResult );

  }
    
  public static class Unary< T, R > extends SuggestiveFunctionCall implements Suggester {
    public Unary( Variable< T > o,
                   String functionMethod ) {
      super( (Object)null, getFunctionMethod( functionMethod ),
             new Object[]{ o } );
    }
  
    public Unary( Expression< T > o1, String functionMethod ) {
      super( //new SuggestiveFunctionCall( 
             (Object)null, getFunctionMethod( functionMethod ),
             new Object[]{ o1 } 
             //)
             );
      //functionCall = this;//(SuggestiveFunctionCall)this.expression;
    }
  
    public Unary( Expression< T > o1,
                   String functionMethod,
                   String pickFunctionMethod1
                   //String pickFunctionMethod2
                   ) {
      this( o1, functionMethod );
      //functionCall.
      pickFunctionCall =
          new FunctionCall( (Object)null,
                            getFunctionMethod( pickFunctionMethod1 ),
                            //functionCall.
                            getArgumentArray(), (Class<?>)null );
      Vector< Object > args = new Vector<Object>( //functionCall.
          getArgumentVector() );
      Collections.reverse( args );
      //functionCall.
      reversePickFunctionCall = pickFunctionCall;
//          new FunctionCall( (Object)null,
//                            getFunctionMethod( pickFunctionMethod2 ),
//                            args.toArray() );
    }
  
    public Unary( Object o1, String functionMethod,
                   String pickFunctionMethod1 ) { //, String pickFunctionMethod2 ) {
      this( forceExpression( o1 ),
            functionMethod,
            pickFunctionMethod1 );//, pickFunctionMethod2 );
      // this( ( o1 instanceof Expression ) ? )
    }
  
    public Unary( Object o1, String functionMethod ) {
      this( forceExpression( o1 ), functionMethod );
    }

    public Unary( Unary m) {
      super(m);
    }
    public Unary clone() {
      return new Unary(this);
    }

    private static Method getFunctionMethod( String functionMethod ) {
      Method m = null;
      try {
        m = Functions.class.getMethod( functionMethod, 
                                       Expression.class );
      } catch ( SecurityException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return m;
    }
    
    @Override
    public < T1 > T1 pickValue( Variable< T1 > variable ) {
      return pickValueBF2( this,//functionCall, 
                           variable );
    }
  
    public Vector< Expression > getArgumentExpressions() {
      Vector< Expression > argExprs =
          new Vector< Expression >( (Collection< Expression >)Utils.asList( super.getArgumentArray(),
                                                                            Expression.class ) );
      return argExprs;
    }

  }
  

  public static class Conditional< T > extends SuggestiveFunctionCall implements Suggester {
    public Conditional( Expression<Boolean> condition, Expression< T > thenExpr, Expression< T > elseExpr ) {
      super( null, getIfThenElseMethod(), new Object[] {condition, thenExpr, elseExpr} );
    }
    
    public Conditional(Conditional<T> c) {
      super(c);
    }
    public Conditional<T> clone() {
      return new Conditional(this);
    }
    
    @Override
    public boolean isMonotonic() {
      // TODO Auto-generated method stub
      return super.isMonotonic();
    }

    public static Method getIfThenElseMethod() {
      Method m = null;
      try {
        m = Functions.class.getMethod( "ifThenElse", 
                                       Expression.class,
                                       Expression.class,
                                       Expression.class );
      } catch ( SecurityException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return m;
    }
    
    @Override
    public Domain< ? > calculateDomain( boolean propagate,
                                        Set< HasDomain > seen ) {
      if ( arguments == null || arguments.size() != 3 ) {
        // TODO -- ERROR
        return null;
      }
      Object o1 = this.arguments.get( 0 );
      Object o2 = this.arguments.get( 1 );
      Object o3 = this.arguments.get( 2 );
      
      Domain<?> d1 = o1 == null ? null : DomainHelper.getDomain( o1 );
      Domain<?> d2 = o2 == null ? null : DomainHelper.getDomain( o2 );
      Domain<?> d3 = o3 == null ? null : DomainHelper.getDomain( o3 );
      AbstractRangeDomain<T> ard2 = d2 instanceof AbstractRangeDomain ? (AbstractRangeDomain<T>)d2 : null;
      AbstractRangeDomain<T> ard3 = d3 instanceof AbstractRangeDomain ? (AbstractRangeDomain<T>)d3 : null;
      Domain<Boolean> condo = d1 instanceof Domain ? (Domain<Boolean>)d1 : null;
      
      // Return combination of domains if the condition is not restricted to
      // exactly one of {true, false}.
      if ( condo == null || condo.isEmpty()
           || ( condo.contains( true ) && condo.contains( false ) ) ) {
        if ( ard2 == null ) return ard3;
        if ( ard3 == null ) return ard2;
        MultiDomain<T> md = new MultiDomain<T>((Class<T>)getType(), (Set<Domain<T>>)Utils.newSet( (Domain<T>)ard2, ard3 ), null );
        Set< Domain< T > > s = md.computeFlattenedSet();
        if ( s != null && s.size() == 1 ) {
          return s.iterator().next();
        }
        return md;
      }
      
      if (condo.contains( true )) {
        return ard2;
      }
      return ard3;
    }
    
    @Override
    public < TT > Pair<Domain< TT >, Boolean> restrictDomain( Domain< TT > domain,
                                             boolean propagate,
                                             Set< HasDomain > seen ) {
      if ( domain == null ) return new Pair(getDomain(propagate, null), false);

      if ( arguments == null || arguments.size() != 3 ) {
        // TODO -- ERROR
        return null;
      }
      Object o1 = this.arguments.get( 0 );
      Object o2 = this.arguments.get( 1 );
      Object o3 = this.arguments.get( 2 );
      
      Domain d1 = o1 == null ? null : DomainHelper.getDomain( o1 );
      Domain d2 = o2 == null ? null : DomainHelper.getDomain( o2 );
      Domain d3 = o3 == null ? null : DomainHelper.getDomain( o3 );
      AbstractRangeDomain ard2 = d2 instanceof AbstractRangeDomain ? (AbstractRangeDomain)d2 : null;
      AbstractRangeDomain ard3 = d3 instanceof AbstractRangeDomain ? (AbstractRangeDomain)d3 : null;
      BooleanDomain condo = d1 instanceof BooleanDomain ? (BooleanDomain)d1 : null;
      
      // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      // TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      // TODO -- DON'T CREATE A NEW DOMAIN IF this.domain IS CORRECT!!!!!!! 
      // TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      
      
//      if ( condo == null || condo.isEmpty() ) {
//        // TODO -- ERROR
//        if ( d2 != null ) {
//          Domain d2c = d2.clone();
//          if ( d2c instanceof AbstractRangeDomain ) ((AbstractRangeDomain)d2c).makeEmpty();
//          else d2c.setValue( null );
//          boolean changed = Utils.valuesEqual( this.domain, d2c );
//          return new Pair(d2c, changed);
//        }
//        return null;
//      }
      Domain< ? > oldDomain = getDomain(propagate, null);
      this.domain = oldDomain;
      
      boolean changed = false;
      if ( condo == null || condo.isEmpty()
          || ( condo.contains( true ) && condo.contains( false ) ) ) {
        // Can't tell whether to restrict the domain for true case (ard2) or the false (ard3).
        // If only one of the choices overlaps with the input domain, then the condition
        // is restricted to that one!
        if ( ard2 != null && ard3 != null && domain instanceof AbstractRangeDomain ) {
          AbstractRangeDomain<T> ard = (AbstractRangeDomain<T>)domain;
          boolean overlaps2 = ard2.overlaps( ard );
          boolean overlaps3 = ard3.overlaps( ard );
          if ( overlaps2 && !overlaps3 ) {
            condo = BooleanDomain.trueDomain;
            if (o1 instanceof HasDomain) {
              Pair< Domain< Boolean >, Boolean > p = ((HasDomain)o1).restrictDomain( condo, propagate, seen );
              changed = changed || (p != null && p.second);
            }
          } else if ( !overlaps2 && overlaps3 ) {
            condo = BooleanDomain.falseDomain;
            if (o1 instanceof HasDomain) {
              Pair< Domain< Boolean >, Boolean > p = ((HasDomain)o1).restrictDomain( condo, propagate, seen );
              changed = changed || (p != null && p.second);
            }
          } else if (!overlaps2 && !overlaps3) {
            changed = oldDomain.clearValues();
            this.domain = oldDomain;
            return new Pair(this.domain, changed);
          }

        }
      }
      if ( condo == null || condo.isEmpty()
          || ( condo.contains( true ) && condo.contains( false ) ) ) {
        return new Pair(this.domain, changed);
      } else if ( condo.size() == 1) {
        if ( condo.contains(Boolean.TRUE) ) { 
          if ( ard2 != null && !ard2.isEmpty() ) {
            if (o2 instanceof HasDomain) {
              Pair< Domain< TT >, Boolean > p = ((HasDomain)o2).restrictDomain( domain, propagate, seen );
              if ( p != null ) {
                changed = changed || (p.second != null && p.second);
                this.domain = ((HasDomain)o2).getDomain( propagate, null );
              }
            } else {
              ard2.restrictTo( domain );
            }
          }
        } else if ( condo.contains(Boolean.FALSE) ) {
          if ( ard3 != null && !ard3.isEmpty() ) {
              if (o3 instanceof HasDomain) {
                Pair< Domain< TT >, Boolean > p = ((HasDomain)o3).restrictDomain( domain, propagate, seen );
                if ( p != null ) {
                  changed = changed || (p.second != null && p.second);
                  this.domain = ((HasDomain)o3).getDomain( propagate, null );
                }
              } else {
                ard3.restrictTo( domain );
              }
          }
        } else {
          // This case is not possible since we check for condo.isEmpty() above.
          return null;
        }
        return new Pair(this.domain, changed);
      }
      
      // condo must contain both true and false at this point.
      
      // check to see if neither or only one of d2 and d3 intersect with domain.
      Domain dca = domain.clone();
      Domain dcb = domain.clone();
      boolean changedA = dca.restrictTo( d2 );
      boolean changedB = dcb.restrictTo( d3 );
      if ( dca.isEmpty() && dcb.isEmpty() ) {
        // No values in domain can be produced--no solution! return an empty domain.
        this.domain = dca;
        return new Pair(this.domain, changedA);
      }
      if ( dca.isEmpty() ) {
        // We can restrict the condition to be true.
        condo.restrictTo( new BooleanDomain( true, true ) );
        this.domain = dca;
        changed = changedA;
      } else if ( dcb.isEmpty() ) {
        // We can restrict the condition to be false.
        condo.restrictTo( new BooleanDomain( false, false ) );
        this.domain = dcb;
        changed = changedB;
      } else {
        Domain newDomain = DomainHelper.combineDomains( Utils.newList(o2, o3),
                                                        new Identity<T>( (Expression<T>)null ),
                                                        true );
        changed = Utils.valuesEqual(this.domain, newDomain);
      }

      return new Pair(this.domain, changed);
    }
  }
  
  public static class IF< T > extends Conditional< T > {
    public IF( Expression<Boolean> condition, Expression< T > thenExpr, Expression< T > elseExpr ) {
      super( condition, thenExpr, elseExpr );
    }
  }

  
  public static < T > T ifThenElse( boolean b, T thenT, T elseT ) {
    if ( b ) return thenT;
    return elseT;
  }
  
  public static < T > Object ifThenElse( Object condition, T thenT, T elseT ) {
    if ( condition == null ) return elseT;
    Pair< Boolean, TimeVaryingMap< ? > > p = booleanOrTimeline( condition );
    if ( p != null && p.first != null ) {
      if ( p.first.booleanValue() ) return thenT;
      return elseT;
    }
    TimeVaryingMap< ? > tvm = p.second;
    if ( tvm == null ) return elseT;
//    if (tvm.size() == 1) {
//      Object o = tvm.values().iterator().next();
//      Boolean b = tryToGetBooleanQuick( o );
//      if ( b != null ) {
//        return ifThenElse(b, thenT, elseT);
//      }
//    }
    Object t = tvm.ifThenElse(thenT, elseT);
    return t;
  }

  
  /**
   * Evaluate if-then-else conditional function. If the condition evaluates to
   * null, it is interpreted as "false."
   * 
   * @param conditionExpr
   * @param thenExpr
   * @param elseExpr
   * @return the evaluation of thenExpr if conditionExpr evaluates to true, else
   *         the evaluation of elseExpr
   * @throws InstantiationException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   */
  public static < T > T ifThenElse( Expression< ? > conditionExpr,
                                    Expression< T > thenExpr,
                                    Expression< T > elseExpr ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( conditionExpr == null && elseExpr == null ) return null;
    Pair< Boolean, TimeVaryingMap< ? > > p = booleanOrTimeline( conditionExpr.expression );
    if ( p != null && p.second != null ) {
      Object thenObj = thenExpr.evaluate( true );
      Object elseObject = elseExpr.evaluate( true );
      T result = (T)(new TimeVaryingPlottableMap()).ifThenElse( p.second, thenObj, elseObject );
      //T result = (T)p.second.ifThenElse( thenObj, elseObject );
      return result;
    }
    Object o = Expression.evaluate( conditionExpr, Boolean.class, true );
    if (o == null || (!(o instanceof Boolean) && o.getClass() != boolean.class)) {
      Debug.error( false, "Could not evaluate condition of if-then-else as true/false; got " + o );
      return null;
    }
    Boolean b = (Boolean)o;
    //Boolean b = (conditionExpr == null ? null : conditionExpr.evaluate( false ) );
    if ( b == null || !b.booleanValue() ) {
      T elseT = null;
      try {
        elseT = (T)(elseExpr == null ? null : elseExpr.evaluate( false ) );
      } catch (ClassCastException e) {
        e.printStackTrace();
      }
      return elseT;
    }
    T thenT = null;
    try {
      thenT = (T)(thenExpr == null ? null : thenExpr.evaluate( false ) );
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    return thenT;
    
    
  }
  
  
  public static class ArgMin< R, T > extends SuggestiveFunctionCall implements Suggester {
    public ArgMin( Expression< R > argLabel1, Expression< T > arg1,
                   Expression< R > argLabel2,  Expression< T > arg2 ) {
      super( null, getArgMinMethod(), new Object[] {argLabel1, arg1, argLabel2, arg2} );
    }
    public ArgMin( Expression< ? >...keysAndValues ) {
      super( null, getVarArgMinMethod(), keysAndValues );
    }
    
    public ArgMin( ArgMin<R, T> a) {
      super(a);
    }
    
    public ArgMin<R,T> clone() {
      return new ArgMin<R,T>(this);
    }
    
    @Override
    public boolean isMonotonic() {
      // TODO Auto-generated method stub
      return false;//super.isMonotonic();
    }

    protected static Method _argMinMethod = null;
    public static Method getArgMinMethod() {
      if ( _argMinMethod != null ) return _argMinMethod; 
      try {
        _argMinMethod = Functions.class.getMethod( "argmin", 
                                                   Object.class,Object.class,
                                                   Object.class,Object.class );
      } catch ( SecurityException e ) {
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        e.printStackTrace();
      }
      return _argMinMethod;
    }
    protected static Method _varArgMinMethod = null;
    public static Method getVarArgMinMethod() {
      if ( _varArgMinMethod != null ) return _varArgMinMethod; 
      try {
        _varArgMinMethod = Functions.class.getMethod( "argmin", Object[].class );
      } catch ( SecurityException e ) {
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        e.printStackTrace();
      }
      return _varArgMinMethod;
    }
  }
  public static class ArgMax< R, T > extends SuggestiveFunctionCall implements Suggester {
    public ArgMax( Expression< R > argLabel1, Expression< T > arg1,
                   Expression< R > argLabel2,  Expression< T > arg2 ) {
      super( null, getArgMaxMethod(), new Object[] {argLabel1, arg1, argLabel2, arg2} );
    }
    public ArgMax( Expression< ? >...keysAndValues ) {
      super( null, getVarArgMaxMethod(), keysAndValues );
    }
    
    public ArgMax( ArgMax<R, T> a) {
      super(a);
    }
    
    public ArgMax<R,T> clone() {
      return new ArgMax<R,T>(this);
    }
    
    
    @Override
    public boolean isMonotonic() {
      // TODO Auto-generated method stub
      return false;//super.isMonotonic();
    }

    protected static Method _argMaxMethod = null;
    public static Method getArgMaxMethod() {
      if ( _argMaxMethod != null ) return _argMaxMethod; 
      try {
        _argMaxMethod = Functions.class.getMethod( "argmax", 
                                                   Object.class,Object.class,
                                                   Object.class,Object.class );
      } catch ( SecurityException e ) {
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        e.printStackTrace();
      }
      return _argMaxMethod;
    }
    protected static Method _varArgMaxMethod = null;
    public static Method getVarArgMaxMethod() {
      if ( _varArgMaxMethod != null ) return _varArgMaxMethod; 
      try {
        _varArgMaxMethod = Functions.class.getMethod( "argmax", Object[].class );
      } catch ( SecurityException e ) {
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        e.printStackTrace();
      }
      return _varArgMaxMethod;
    }
  }

  
  // Simple math functions

  public static class Min< T, R > extends Binary< T, R > {
    public Min( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "min", "pickValueForward", "pickValueReverse" );
      setMonotonic( true );
    }
    public Min( Object o1, Object c ) {
      super( o1, c, "min", "pickValueForward", "pickValueReverse" );
      setMonotonic( true );
    }
    
    public Min( Functions.Min<T, R> m) {
      super(m);
    }
    
    public Min<T,R> clone() {
      return new Min<T,R>(this);
    }
    
    @Override
    public //< T1  extends Comparable< ? super T1 > >
    FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      if ( arguments == null || arguments.size() != 2 ) return null;
      Object otherArg = ( arg == arguments.get( 1 ) ? arguments.get( 0 ) : arguments.get( 1 ) );
      if ( returnValue == null || otherArg == null ) return null; // arg can be null!
      return new Max<T,T>( returnValue, otherArg );
    }

    @Override
    public Domain< ? > getDomain( boolean propagate, Set< HasDomain > seen ) {
      // avoid infinite recursion
      Pair< Boolean, Set< HasDomain > > pair = Utils.seen( this, propagate, seen );
      if ( pair.first ) return null;
      seen = pair.second;
      
      Domain<?> rd =
          DomainHelper.combineDomains( new ArrayList< Object >( getArgumentExpressions() ),
                                       new Min<T,R>( null, null ),
                                       true );
      return rd;
    }
    
    /**
     * Return a domain for the matching input argument restricted by the domain
     * of the other arguments and of an expected return value.
     * 
     * @param returnValue
     * @param argument
     * @return
     */
    public Domain< ? > inverseDomain( Domain<?> returnValue, Object argument ) {
      FunctionCall inverse = inverse( returnValue, argument );
      if ( inverse == null ) return null;
      return inverse.getDomain( false, null );
    }
}

  public static class Max< T, R > extends Binary< T, R > {
    public Max( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "max", "pickValueForward", "pickValueReverse" );
      setMonotonic( true );
    }
    public Max( Object o1, Object c ) {
      super( o1, c, "max", "pickValueForward", "pickValueReverse" );
      setMonotonic( true );
    }

    public Max( Functions.Max<T, R> m) {
      super(m);
    }
    
    public Max<T,R> clone() {
      return new Max<T,R>(this);
    }
    

    
    @Override
    public //< T1  extends Comparable< ? super T1 > > 
    FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      if ( arguments == null || arguments.size() != 2 ) return null;
      Object otherArg = ( arg == arguments.get( 1 ) ? arguments.get( 0 ) : arguments.get( 1 ) );
      if ( returnValue == null || otherArg == null ) return null; // arg can be null!
      return new Min<T,T>( returnValue, otherArg );
    }

    @Override
    public Domain< ? > getDomain( boolean propagate, Set< HasDomain > seen ) {
      // avoid infinite recursion
      Pair< Boolean, Set< HasDomain > > pair = Utils.seen( this, propagate, seen );
      if ( pair.first ) return null;
      seen = pair.second;
      
      Domain<?> rd =
          DomainHelper.combineDomains( new ArrayList< Object >( getArgumentExpressions() ),
                                       new Max<T,R>( null, null ),
                                       true);
      return rd;
    }
    
    /**
     * Return a domain for the matching input argument restricted by the domain
     * of the other arguments and of an expected return value.
     * 
     * @param returnValue
     * @param argument
     * @return
     */
    public Domain< ? > inverseDomain( Domain<?> returnValue, Object argument ) {
      FunctionCall inverse = inverse( returnValue, argument );
      if ( inverse == null ) return null;
      return inverse.getDomain( false, null );
    }
  }
    
  public static class Sum< T, R > extends Binary< T, R > {
    public Sum( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "add", "pickValueForward", "pickValueReverse" );
      setMonotonic( true );
    }
    public Sum( Object o1, Object c ) {
      super( o1, c, "add", "pickValueForward", "pickValueReverse" );
      setMonotonic( true );
    }
    
    public Sum( Functions.Sum<T, R> m) {
      super(m);
    }
    
    public Sum<T,R> clone() {
      return new Sum<T,R>(this);
    }
    

    @Override
    public //< T1  extends Comparable< ? super T1 > > 
    FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      if ( arguments == null || arguments.size() != 2 ) return null;
      Object otherArg = ( arg == arguments.get( 1 ) ? arguments.get( 0 ) : arguments.get( 1 ) );
      if ( returnValue == null || otherArg == null ) return null; // arg can be null!
      return new Minus<T,T>( returnValue, otherArg );
    }
    
  }
  public static class Add< T , R > extends Sum< T, R > {
    public Add( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
    public Add( Object o1, Object c ) {
      super( o1, c );
    }
  }

  public static class Plus< T , R > extends Sum< T, R > {
    public Plus( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
      //functionCall.
    }
    public Plus( Object o1, Object c ) {
      super( o1, c );
    }
  }

  public static class Sub<T,R>//< T extends Comparable< ? super T >,
                           //R >
  extends Binary< T, R > {
    public Sub( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "subtract", "pickValueForward", "pickValueReverse" );
      //functionCall.
      setMonotonic( true );
    }
    public Sub( Object o1, Object c ) {
      super( o1, c, "subtract", "pickValueForward", "pickValueReverse" );
      //functionCall.
      setMonotonic( true );
    }
    
    public Sub( Functions.Sub<T, R> m) {
      super(m);
    }
    
    public Sub<T,R> clone() {
      return new Sub<T,R>(this);
    }

    
    @Override
    public FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      if ( arguments == null || arguments.size() != 2 ) return null;
      Object otherArg = ( arg == arguments.get( 1 ) ? arguments.get( 0 ) : arguments.get( 1 ) );
      boolean firstArg = otherArg != arguments.get( 0 );  // thus arg is the first
      if ( returnValue == null || otherArg == null ) return null; // arg can be null!
      if ( firstArg ) {
        return new Sum<T,T>( returnValue, otherArg );
      }
      return new Sub<T,T>( otherArg, returnValue );
    }
  }
  public static class Minus<T,R> extends Sub< T, R > {
    public Minus( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
      //functionCall.
      setMonotonic( true );
    }
    public Minus( Object o1, Object c ) {
      super( o1, c );
      //functionCall.
      setMonotonic( true );
    }
  }

  public static class Times< T , R > extends Binary< T, R > {
    public Times( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "times", "pickValueForward", "pickValueReverse" );
      //functionCall.
      setMonotonic( true );
    }
    public Times( Object o1, Object c ) {
      super( o1, c, "times", "pickValueForward", "pickValueReverse" );
      //functionCall.
      setMonotonic( true );
    }
    
    public Times( Functions.Times<T, R> m) {
      super(m);
    }
    
    public Times<T,R> clone() {
      return new Times<T,R>(this);
    }

    @Override
    public FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      if ( arguments == null || arguments.size() != 2 ) return null;
      Object otherArg = ( arg == arguments.get( 1 ) ? arguments.get( 0 ) : arguments.get( 1 ) );
      if ( returnValue == null || otherArg == null ) return null; // arg can be null!
      return new Divide<T,T>( returnValue, otherArg );
    }

    
  }
  
  public static class Mul<T,R> extends Times< T, R > {
    public Mul( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
      setMonotonic( true );
    }
    public Mul( Object o1, Object c ) {
      super( o1, c );
      setMonotonic( true );
    }
  }


  public static class Divide< T , R > extends Binary< T, R > {
    public Divide( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "divide", "pickValueForward", "pickValueReverse" );
      //functionCall.
      setMonotonic( true );
    }
    public Divide( Object o1, Object c ) {
      super( o1, c, "divide", "pickValueForward", "pickValueReverse" );
      //functionCall.
      setMonotonic( true );
    }
    
    public Divide( Functions.Divide<T, R> m) {
      super(m);
    }
    
    public Divide<T,R> clone() {
      return new Divide<T,R>(this);
    }

    @Override
    public FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      if ( arguments == null || arguments.size() != 2 ) return null;
      Object otherArg = ( arg == arguments.get( 1 ) ? arguments.get( 0 ) : arguments.get( 1 ) );
      boolean firstArg = otherArg != arguments.get( 0 );  // thus arg is the first
      if ( returnValue == null || otherArg == null ) return null; // arg can be null!
      if ( firstArg ) {
        return new Times<T,T>( returnValue, otherArg );
      }
      return new Divide<T,T>( otherArg, returnValue );
    }

  }
  
  public static class Div<T,R> extends Divide< T, R > {
    public Div( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
      setMonotonic( true );
    }
    public Div( Object o1, Object c ) {
      super( o1, c );
      setMonotonic( true );
    }
  }

  public static class Pow<T,R> extends Binary< T, R > {
    public Pow( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "pow", "pickValueForward", "pickValueReverse" );
      setMonotonic( true );
    }
    public Pow( Object o1, Object c ) {
      super( o1, c, "pow", "log", "log" );
      setMonotonic( true );
    }
    public Pow( Functions.Pow<T, R> m) {
      super(m);
    }
    public Pow<T,R> clone() {
      return new Pow<T,R>(this);
    }
  }


  
  // TODO -- If MAX_VALUE is passed in, should treat as infinity; should also
  // print "inf"
  // add(Expr, Expr) should call this fcn.
  public static <V1, V2> V1 plus( V1 o1, V2 o2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null || o2 == null ) return null;
    Object result = null;
    if ( o1 instanceof String || o2 instanceof String ) { // TODO -- this won't work for timelines
        result = "" + o1 + o2;
        //String s = MoreToString.Helper.toString( o1 ) + MoreToString.Helper.toString( o2 ); 
    } else {
      Number n1 = null;
      Number n2 = null;
      TimeVaryingMap<?> map1 = null;
      TimeVaryingMap<?> map2 = null;
      
      Pair< Number, TimeVaryingMap< ? > > p1 = numberOrTimeline( o1 );
      n1 = p1.first;
      map1 = p1.second;

      if ( map1 != null ) {
        result = (V1)plus( map1, o2 );
      } else {
        Pair< Number, TimeVaryingMap< ? > > p2 = numberOrTimeline( o2 );
        n2 = p2.first;
        map2 = p2.second;
    
        if ( map2 != null ) {
          result = (V1)plus( o1, map2 );
        }
      }

//          Number n1 = null;
//          Number n2 = null;
//          try {
//            n1 = Expression.evaluate( o1, Number.class, false );
//            n2 = Expression.evaluate( o2, Number.class, false );
//          } catch ( Throwable e ) {
//            // ignore
//          }
          if ( n1 != null && n2 != null ) {
            if ( Infinity.isEqual( n1 ) ) {
              try {
                if ( NegativeInfinity.isEqual( n2 ) ) {
                  result = Zero.forClass( o1.getClass() );
                } else {
                  result = Infinity.forClass( o1.getClass() );
                }
              } catch ( ClassCastException e ) {
                e.printStackTrace();
              }
            } else if ( NegativeInfinity.isEqual( n1 ) ) {
              try {
                if ( Infinity.isEqual( n2 ) ) {
                  result = Zero.forClass( o1.getClass() );
                } else {
                  result = NegativeInfinity.forClass( o1.getClass() );
                }
              } catch ( ClassCastException e ) {
                e.printStackTrace();
              }
            } else if ( n1 instanceof Double || n2 instanceof Double ) {         
              result = (Double)gov.nasa.jpl.ae.util.Math.plus(n1.doubleValue(), n2.doubleValue());
            } else if ( n1 instanceof Float || n2 instanceof Float ) {
              result = (Float)gov.nasa.jpl.ae.util.Math.plus(n1.floatValue(), n2.floatValue());
            } else if ( n1 instanceof Long || n2 instanceof Long ) {
              result = (Long)gov.nasa.jpl.ae.util.Math.plus(n1.longValue(), n2.longValue());
            } else {
              result = (Integer)gov.nasa.jpl.ae.util.Math.plus( n1.intValue(), n2.intValue() );
            }
          }
//          else {
//            TimeVaryingMap<?> map = null;
//            try {
//              map = Expression.evaluate( o1, TimeVaryingMap.class, false );
//            } catch ( Throwable e ) {
//              //ignore
//            }
//            if ( map != null ) result = plus( map, o2 );
//            else {
//              try {
//                Object obj = Expression.evaluate( o2, TimeVaryingMap.class, false );
//                if ( obj instanceof TimeVaryingMap ) {
//                  map = (TimeVaryingMap< ? >)obj;
//                }
//              } catch ( Throwable e ) {
//                //ignore
//              }
//              if ( map != null ) result = plus( o1, map );
//            }
//          }
    }
    try {
      if ( o1 != null && o2 != null && result != null ) {
        Class<?> cls1 = o1.getClass();
        Class<?> cls2 = o2.getClass();
        Object x = Expression.evaluate( result,
                                        ClassUtils.dominantTypeClass(cls1,cls2),
                                        false );
        if ( x == null ) x = result;
        // TODO: type casting this w/ V1 assume that it is the dominant class
        return (V1)x;
      }
      return (V1)result;
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return null;
  }

  public static <V1, V2> V1 min( V1 o1, V2 o2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null || o2 == null ) return null;
      Object result = null;
    if ( o1 instanceof String || o2 instanceof String ) {
        String s1 = "" + o1;
        String s2 = "" + o2;
        int comp = s1.compareTo( s2 );
        result = ( comp == 1 ) ? s2 : s1;
        //String s = MoreToString.Helper.toString( o1 ) + MoreToString.Helper.toString( o2 ); 
    } else {
      Number n1 = null;
      Number n2 = null;
      TimeVaryingMap<?> map1 = null;
      TimeVaryingMap<?> map2 = null;
      
      Pair< Number, TimeVaryingMap< ? > > p1 = numberOrTimeline( o1 );
      n1 = p1.first;
      map1 = p1.second;

      if ( map1 != null ) {
        result = (V1)min( map1, o2 );
      } else {
        Pair< Number, TimeVaryingMap< ? > > p2 = numberOrTimeline( o2 );
        n2 = p2.first;
        map2 = p2.second;
    
        if ( map2 != null ) {
          result = (V1)min( o1, map2 );
        }
      }

//      TimeVaryingMap<?> map = null;
//      try {
//        map = Expression.evaluate( o1, TimeVaryingMap.class, false );
//      } catch ( Throwable e ) {
//        //ignore
//      }
//      if ( map != null ) result = min( map, o2 );
//      else {
//        try {
//          map = Expression.evaluate( o2, TimeVaryingMap.class, false );
//        } catch ( Throwable e ) {
//          //ignore
//        }
//        if ( map != null ) result = min( o1, map );
//        else {
//          Number n1 = null;
//          Number n2 = null;
//          try {
//            n1 = Expression.evaluate( o1, Number.class, false );
//            n2 = Expression.evaluate( o2, Number.class, false );
//          } catch ( Throwable e ) {
//            // ignore
//          }
          if ( n1 != null && n2 != null ) {
            if ( NegativeInfinity.isEqual( n1 ) || NegativeInfinity.isEqual( n2 ) ) {
              try {
                  result = NegativeInfinity.forClass( o1.getClass() );
              } catch ( ClassCastException e ) {
                e.printStackTrace();
              }
            } else if ( n1 instanceof Double || n2 instanceof Double ) {
              result = Math.min(n1.doubleValue(), n2.doubleValue());
            } else if ( n1 instanceof Float || n2 instanceof Float ) {
              result = Math.min(n1.floatValue(), n2.floatValue());
            } else if ( n1 instanceof Long || n2 instanceof Long ) {
              result = Math.min(n1.longValue(), n2.longValue());
            } else {
              result = Math.min( n1.intValue(), n2.intValue() );
            }
          }
        }
//      }
//    }
    try {
      if ( o1 != null && o2 != null && result != null) {
        Class<?> cls1 = o1.getClass();
        Class<?> cls2 = o2.getClass();
        Object x = Expression.evaluate( result,
                                        ClassUtils.dominantTypeClass(cls1,cls2),
                                        false );
        if ( x == null ) x = result;
        // TODO: type casting this w/ V1 assume that it is the dominant class
        return (V1)x;
      }
      return (V1)result;
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static < T, TT > T min( Expression< T > o1, Expression< TT > o2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null || o2 == null ) return null;
    T r1 = (T)o1.evaluate( false );
    TT r2 = (TT)o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    return min( r1, r2 );
  }

  public static < T, TT > T max( Expression< T > o1, Expression< TT > o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null || o2 == null ) return null;
    T r1 = (T)o1.evaluate( false );
    TT r2 = (TT)o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    return max( r1, r2 );
  }

  public static <V1, V2> V1 max( V1 o1, V2 o2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null || o2 == null ) return null;
      Object result = null;
    if ( o1 instanceof String || o2 instanceof String ) {
        String s1 = "" + o1;
        String s2 = "" + o2;
        int comp = s1.compareTo( s2 );
        result = ( comp == -1 ) ? s2 : s1;
        //String s = MoreToString.Helper.toString( o1 ) + MoreToString.Helper.toString( o2 ); 
    } else {
      Number n1 = null;
      Number n2 = null;
      TimeVaryingMap<?> map1 = null;
      TimeVaryingMap<?> map2 = null;
      
      Pair< Number, TimeVaryingMap< ? > > p1 = numberOrTimeline( o1 );
      n1 = p1.first;
      map1 = p1.second;

      if ( map1 != null ) {
        result = (V1)max( map1, o2 );
      } else {
        Pair< Number, TimeVaryingMap< ? > > p2 = numberOrTimeline( o2 );
        n2 = p2.first;
        map2 = p2.second;
    
        if ( map2 != null ) {
          result = (V1)max( o1, map2 );
        }
      }
          if ( n1 != null && n2 != null ) {
            if ( Infinity.isEqual( n1 ) || Infinity.isEqual( n2 ) ) {
              try {
                  result = Infinity.forClass( o1.getClass() );
              } catch ( ClassCastException e ) {
                e.printStackTrace();
              }
            } else if ( n1 instanceof Double || n2 instanceof Double ) {
              result = Math.max(n1.doubleValue(), n2.doubleValue());
            } else if ( n1 instanceof Float || n2 instanceof Float ) {
              result = Math.max(n1.floatValue(), n2.floatValue());
            } else if ( n1 instanceof Long || n2 instanceof Long ) {
              result = Math.max(n1.longValue(), n2.longValue());
            } else {
              result = Math.max( n1.intValue(), n2.intValue() );
            }
          }
        }
    try {
      if ( o1 != null && o2 != null && result != null ) {
        Class<?> cls1 = o1.getClass();
        Class<?> cls2 = o2.getClass();
        Object x = Expression.evaluate( result,
                                        ClassUtils.dominantTypeClass(cls1,cls2),
                                        false );
        if ( x == null ) x = result;
        // TODO: type casting this w/ V1 assume that it is the dominant class
        return (V1)x;
      }
      return (V1)result;
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static <L, V1, V2> Object argmin( L l1, V1 o1, L l2, V2 o2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return argminormax( l1, o1, l2, o2, true );
  }
  public static <L, V1, V2> Object argmax( L l1, V1 o1, L l2, V2 o2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return argminormax( l1, o1, l2, o2, false );
  }
  public static <L, V1, V2> Object argmin( Object...keysAndValues ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return argminormax( true, keysAndValues );
  }
  public static <L, V1, V2> Object argmax( Object...keysAndValues ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return argminormax( false, keysAndValues );
  }
  public static Object argminormax( boolean isMin, Object...args ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( args.length % 2 == 1 ) {
      Debug.error("argmin/max expects key-value pairs but received an odd number of arguments!");
    }
    Object bestLabel = null;
    Object bestValue = null;
    for ( int i = 0; i < args.length-1; i += 2 ) {
      Object label = args[i];
      Object value = args[i+1];
      if ( bestLabel == null ) {
        bestLabel = label;
        bestValue = value;
      } else {
        Object winningLabel = argminormax(label, value, bestLabel, bestValue, isMin);
        if ( winningLabel instanceof TimeVaryingMap ) {
          bestLabel = winningLabel;
          if (isMin) {
            if ( i < args.length-2 ) { // Don't compute if nothing left to compare against
              bestValue =
                  ( new Functions.Min( new Expression( value ),
                                       new Expression( bestValue ) ) ).evaluate( true );
            }
          } else {
            if ( i < args.length-2 ) { // Don't compute if nothing left to compare against
              bestValue =
                  ( new Functions.Max( new Expression( value ),
                                       new Expression( bestValue ) ) ).evaluate( true );
            }
          }
        } else if ( winningLabel != null && winningLabel != bestLabel && winningLabel == label ) {
          bestLabel = label;
          bestValue = value;
        }
      }
    }
    return bestLabel;
  }

  public static <L, V1, V2> Object argminormax( L l1, V1 o1, L l2, V2 o2, boolean isMin ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null || o2 == null ) return null;
    Object result = null;
    if ( o1 instanceof String || o2 instanceof String ) {
        String s1 = "" + o1;
        String s2 = "" + o2;
        int comp = s1.compareTo( s2 );
        result = (comp == (isMin ? 1 : -1)) ? l2 : l1;
        //String s = MoreToString.Helper.toString( o1 ) + MoreToString.Helper.toString( o2 ); 
    } else {
      Number n1 = null;
      Number n2 = null;
      TimeVaryingMap<?> map1 = null;
      TimeVaryingMap<?> map2 = null;
      
      Pair< Number, TimeVaryingMap< ? > > p1 = numberOrTimeline( o1 );
      n1 = p1.first;
      map1 = p1.second;

      if ( map1 != null ) {
        result = argminormax( l1, map1, l2, o2, isMin );
      } else {
        Pair< Number, TimeVaryingMap< ? > > p2 = numberOrTimeline( o2 );
        n2 = p2.first;
        map2 = p2.second;
    
        if ( map2 != null ) {
          result = argminormax( l1, o1, l2, map2, isMin );
        }
      }
      if ( result == null && map1 == null && map2 == null  ) {
        if ( n2 == null && n1 != null ) {
          result = l1;
        } else if ( n1 == null && n2 != null ) { 
          result = l2;
        } else if ( n1 != null && n2 != null ) {
          result = (Double.compare( n1.doubleValue(), n2.doubleValue() ) == ( isMin ? 1 : -1 )) ? l2 : l1;
        }
      }
    }
    try {
      if ( result instanceof TimeVaryingMap ) {
        return result;
      }
      if ( l1 != null && l2 != null && result != null ) {
        Class<?> cls1 = l1.getClass();
        Class<?> cls2 = l2.getClass();
        Object x = Expression.evaluate( result,
                                        ClassUtils.dominantTypeClass(cls1,cls2),
                                        false );
        if ( x == null ) x = result;
        // TODO: type casting this w/ V1 assume that it is the dominant class
        return (L)x;
      }
      return (L)result;
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    return null;
  }

  
  public static TimeVaryingMap<?> getTimeline(Object o) {
    TimeVaryingMap< ? > tvm = null;
    tvm = tryToGetTimelineQuick( o );
    if ( tvm != null ) {
      return tvm;
    }
    try {
      tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
    } catch (  Throwable e ) {
      // ignore
    }
    return tvm;
  }
  
  public static Pair< Object, TimeVaryingMap<?> > objectOrTimeline(Object o) {
    Object n = tryToGetObjectQuick( o );
    TimeVaryingMap< ? > tvm = null;
    if ( n != null ) {
      new Pair< Object, TimeVaryingMap<?> >( n, tvm );
    }
    tvm = tryToGetTimelineQuick( o );
    if ( tvm != null ) {
        return new Pair< Object, TimeVaryingMap<?> >( n, tvm );
    }
    try {
        n = Expression.evaluate( o, null, false );
    } catch ( Throwable e ) {
      // ignore
    }
    if ( n == null ) {
      try {
          tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
      } catch (  Throwable e ) {
        // ignore
      }
    }
    Pair< Object, TimeVaryingMap<?> > p = new Pair< Object, TimeVaryingMap<?> >( n, tvm );
    return p;
  }
  
  public static Pair< Boolean, TimeVaryingMap<?> > booleanOrTimeline(Object o) {
    Boolean n = tryToGetBooleanQuick( o );
    TimeVaryingMap< ? > tvm = null;
    if ( n != null ) {
      new Pair< Boolean, TimeVaryingMap<?> >( n, tvm );
    }
    tvm = tryToGetTimelineQuick( o );
    if ( tvm != null ) {
        return new Pair< Boolean, TimeVaryingMap<?> >( n, tvm );
    }
    try {
        n = Expression.evaluate( o, Boolean.class, false );
    } catch ( Throwable e ) {
      // ignore
    }
    if ( n == null ) {
      try {
          tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
      } catch (  Throwable e ) {
        // ignore
      }
    }
    Pair< Boolean, TimeVaryingMap<?> > p = new Pair< Boolean, TimeVaryingMap<?> >( n, tvm );
    return p;
  }
  
  public static Pair< Number, TimeVaryingMap<?> > numberOrTimeline(Object o) {
    Number n = tryToGetNumberQuick( o );
    TimeVaryingMap< ? > tvm = null;
    if ( n == null && o instanceof String ) {
      n = toNumber(o, true);
    }
    if ( n != null ) {
      new Pair< Number, TimeVaryingMap<?> >( n, tvm );
    }
    tvm = tryToGetTimelineQuick( o );
    if ( tvm != null ) {
        return new Pair< Number, TimeVaryingMap<?> >( n, tvm );
    }
    try {
        n = Expression.evaluate( o, Number.class, false );
    } catch ( Throwable e ) {
      // ignore
    }
    if ( n == null ) {
      try {
          tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
      } catch (  Throwable e ) {
        // ignore
      }
    }
    Pair< Number, TimeVaryingMap<?> > p = new Pair< Number, TimeVaryingMap<?> >( n, tvm );
    return p;
  }
  

  protected static TimeVaryingMap<?> tryToGetTimelineQuick( Object o ) {
    if ( o == null ) return null;
    if ( o instanceof TimeVaryingMap ) return (TimeVaryingMap)o;
    if ( o instanceof Expression ) o = ((Expression<?>)o).expression;
    if ( o instanceof Parameter ) o = ((Parameter<?>)o).getValueNoPropagate();
    if ( o instanceof TimeVaryingMap ) return (TimeVaryingMap)o;
    return null;
  }
  protected static Number tryToGetNumberQuick( Object o ) {
    if ( o == null ) return null;
    if ( o instanceof Number ) return (Number)o;
    if ( o instanceof Expression ) o = ((Expression<?>)o).expression;
    if ( o instanceof Parameter ) o = ((Parameter<?>)o).getValueNoPropagate();
    if ( o instanceof Number ) return (Number)o;
    return null;
  }
  protected static Boolean tryToGetBooleanQuick( Object o ) {
    if ( o == null ) return null;
    if ( o instanceof Boolean ) return (Boolean)o;
    if ( o instanceof Expression ) o = ((Expression<?>)o).expression;
    if ( o instanceof Parameter ) o = ((Parameter<?>)o).getValueNoPropagate();
    if ( o instanceof Boolean ) return (Boolean)o;
    return null;
  }
  protected static Object tryToGetObjectQuick( Object o ) {
    if ( o == null ) return null;
    if ( o instanceof Expression ) o = ((Expression<?>)o).expression;
    if ( o instanceof Parameter ) o = ((Parameter<?>)o).getValueNoPropagate();
    return o;
  }
  
  //public static <V1, V2> V1 times( V1 o1, V2 o2 ) {
  public static <V1, V2> V1 times( V1 o1, V2 o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null || o2 == null ) return null;
    Number n1 = null;
    Number n2 = null;
    TimeVaryingMap<?> map1 = null;
    TimeVaryingMap<?> map2 = null;
    
    Object result = null;
    Pair< Number, TimeVaryingMap< ? > > p1 = numberOrTimeline( o1 );
    n1 = p1.first;
    map1 = p1.second;

    if ( map1 != null ) {
      result = (V1)times( map1, o2 );
    } else {
      Pair< Number, TimeVaryingMap< ? > > p2 = numberOrTimeline( o2 );
      n2 = p2.first;
      map2 = p2.second;
  
      if ( map2 != null ) {
        result = (V1)times( o1, map2 );
      }
    }
    
        if ( n1 != null && n2 != null ) {
          if ( Infinity.isEqual( n1 ) ) {
            try {
              if ( Zero.isEqual( n2 ) ) {
                result = One.forClass( o1.getClass() );
              } else if ( Utils.isNegative( n2 ) ){
                result = NegativeInfinity.forClass( o1.getClass() );
              } else {
                result = Infinity.forClass( o1.getClass() );
              }
            } catch ( ClassCastException e ) {
              e.printStackTrace();
            }
          } else if ( NegativeInfinity.isEqual( n1 ) ) {
            try {
              if ( Zero.isEqual( n2 ) ) {
                result = NegativeOne.forClass( o1.getClass() );
              } else if ( Utils.isNegative( n2 ) ){
                result = Infinity.forClass( o1.getClass() );
              } else {
                result = NegativeInfinity.forClass( o1.getClass() );
              }
            } catch ( ClassCastException e ) {
              e.printStackTrace();
            }
          } else if ( Zero.isEqual( n1 ) ) {
            try {
              if ( Infinity.isEqual( n2 ) ) {
                result = One.forClass( o1.getClass() );
              } else if ( NegativeInfinity.isEqual( n2 ) ){
                result = NegativeOne.forClass( o1.getClass() );
              } else {
                result = Zero.forClass( o1.getClass() );
              }
            } catch ( ClassCastException e ) {
              e.printStackTrace();
            }
          // TODO -- other types, like BigDecimal
          } else if ( n1 instanceof Double || n2 instanceof Double ) {
            // result = ((Double)n1.doubleValue()) * ((Double)n2.doubleValue());
            result = (Double)gov.nasa.jpl.ae.util.Math.times( n1.doubleValue(), n2.doubleValue() );
          } else if ( n1 instanceof Float || n2 instanceof Float ) {
            // result = ((Float)n1.floatValue()) * ((Float)n2.floatValue());
            result = (Float)gov.nasa.jpl.ae.util.Math.times( n1.floatValue(), n2.floatValue() );
          } else if ( n1 instanceof Long || n2 instanceof Long ) {
            // result = ((Long)n1.longValue()) * ((Long)n2.longValue());
            result = (Long)gov.nasa.jpl.ae.util.Math.times( n1.longValue(), n2.longValue() );
            // } else if ( n1 instanceof Integer || n2 instanceof Integer ) {
            // // result = ((Integer)n1.intValue()) * ((Integer)n2.intValue());
            // result = (Integer)times(n1.intValue(), n2.intValue());
          } else {
            // result = ((Integer)n1.intValue()) * ((Integer)n2.intValue());
            result = (Integer)gov.nasa.jpl.ae.util.Math.times( n1.intValue(), n2.intValue() );
          }
          if ( result == null ) return null;
          if ( o1.getClass().equals( result.getClass() ) ) return (V1)result;
        }

    try {
      if ( o1 != null && o2 != null && result != null ) {
        Class<?> cls1 = o1.getClass();
        Class<?> cls2 = o2.getClass();
        // TOOD -- what if cls1 is not a Number?
        Object x = Expression.evaluate( result,
                                        ClassUtils.dominantTypeClass(cls1,cls2),
                                        false );
        if ( x == null ) x = result;
        // TODO: type casting this w/ V1 assume that it is the dominant class
        return (V1)x;
      }
      return (V1)result;
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static <V1, V2> V1 divide( V1 o1, V2 o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    Object result = null;
    Number n1 = null;
    Number n2 = null;
    TimeVaryingMap<?> map1 = null;
    TimeVaryingMap<?> map2 = null;
    
    Pair< Number, TimeVaryingMap< ? > > p1 = numberOrTimeline( o1 );
    n1 = p1.first;
    map1 = p1.second;

    if ( map1 != null ) {
      result = (V1)divide( map1, o2 );
    } else {
      Pair< Number, TimeVaryingMap< ? > > p2 = numberOrTimeline( o2 );
      n2 = p2.first;
      map2 = p2.second;
  
      if ( map2 != null ) {
        result = (V1)divide( o1, map2 );
      }
    }
//    TimeVaryingMap<?> map = null;
//    try {
//      map = Expression.evaluate( o1, TimeVaryingMap.class, false );
//    } catch ( ClassCastException e ) {
//      //ignore
//    }
//    if ( map != null ) result = divide( map, o2 );
//    else {
//      try {
//      map = Expression.evaluate( o2, TimeVaryingMap.class, false );
//      } catch ( ClassCastException e ) {
//        //ignore
//      }
//      if ( map != null ) result = divide( o1, map );
//      else {
//        Number n1 = null;
//        Number n2 = null;
//        try {
//          n1 = Expression.evaluate( o1, Number.class, false );
//          n2 = Expression.evaluate( o2, Number.class, false );
//        } catch ( Throwable e ) {
//          // ignore
//        }
        if ( n1 != null && n2 != null ) {
          if ( Infinity.isEqual( n1 ) ) {
            try {
              if ( Infinity.isEqual( n2 ) ) {
                result = One.forClass( o1.getClass() );
              } else if ( NegativeInfinity.isEqual( n2 ) ) {
                result = NegativeOne.forClass( o1.getClass() );
              } else if ( Utils.isNegative( n2 ) ){
                result = NegativeInfinity.forClass( o1.getClass() );
              } else {
                result = Infinity.forClass( o1.getClass() );
              }
            } catch ( ClassCastException e ) {
              e.printStackTrace();
            }
          } else if ( NegativeInfinity.isEqual( n1 ) ) {
            try {
              if ( Infinity.isEqual( n2 ) ) {
                result = NegativeOne.forClass( o1.getClass() );
              } else if ( NegativeInfinity.isEqual( n2 ) ) {
                result = One.forClass( o1.getClass() );
              } else if ( Utils.isNegative( n2 ) ){
                result = Infinity.forClass( o1.getClass() );
              } else {
                result = NegativeInfinity.forClass( o1.getClass() );
              }
            } catch ( ClassCastException e ) {
              e.printStackTrace();
            }
          } else if ( Zero.isEqual( n1 ) ) {
            try {
              if ( Zero.isEqual( n2 ) ) {
                result = One.forClass( o1.getClass() );
              } else {
                result = Zero.forClass( o1.getClass() );
              }
            } catch ( ClassCastException e ) {
              e.printStackTrace();
            }
          } else if ( Zero.isEqual( n2 ) ) {
            try {
              if ( Utils.isNegative( n1 ) ) {
                result = NegativeInfinity.forClass( o1.getClass() );
              } else {
                result = Infinity.forClass( o1.getClass() );
              }
            } catch ( ClassCastException e ) {
              e.printStackTrace();
            }
          // TODO -- other types, like BigDecimal
          } else if ( n1 instanceof Double || n2 instanceof Double ) {
    //        result = ((Double)n1.doubleValue()) / ((Double)n2.doubleValue());
            result = (Double)gov.nasa.jpl.ae.util.Math.dividedBy( n1.doubleValue(), n2.doubleValue() );
          } else if ( n1 instanceof Float || n2 instanceof Float ) {
    //        result = ((Float)n1.floatValue()) / ((Float)n2.floatValue());
            result = (Float)gov.nasa.jpl.ae.util.Math.dividedBy( n1.floatValue(), n2.floatValue() );
          } else if ( n1 instanceof Long || n2 instanceof Long ) {
    //        result = ((Long)n1.longValue()) / ((Long)n2.longValue());
            result = (Long)gov.nasa.jpl.ae.util.Math.dividedBy( n1.longValue(), n2.longValue() );
    //      } else if ( n1 instanceof Integer || n2 instanceof Integer ) {
    ////        result = ((Integer)n1.intValue()) / ((Integer)n2.intValue());
    //        result = (Integer)dividedBy( n1.intValue(), n2.intValue() );
          } else {
    //        result = ((Integer)n1.intValue()) / ((Integer)n2.intValue());
            result = (Integer)gov.nasa.jpl.ae.util.Math.dividedBy( n1.intValue(), n2.intValue() );
          }
        }
//      }
//    }
    try {
      if ( o1 != null && o2 != null && result != null ) {
        Class<?> cls1 = o1.getClass();
        Class<?> cls2 = o2.getClass();
        Object x = Expression.evaluate( result,
                                        ClassUtils.dominantTypeClass(cls1,cls2),
                                        false );
        if ( x == null ) x = result;
        // TODO: type casting this w/ V1 assume that it is the dominant class
        return (V1)x;
      }
      return (V1)result;
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  
  public static <V1, V2> V1 pow( V1 o1, V2 o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null || o2 == null ) return null;
    Object result = null;
    Number n1 = null;
    Number n2 = null;
    TimeVaryingMap<?> map1 = null;
    TimeVaryingMap<?> map2 = null;
    
    Pair< Number, TimeVaryingMap< ? > > p1 = numberOrTimeline( o1 );
    n1 = p1.first;
    map1 = p1.second;

    if ( map1 != null ) {
      result = (V1)max( map1, o2 );
    } else {
      Pair< Number, TimeVaryingMap< ? > > p2 = numberOrTimeline( o2 );
      n2 = p2.first;
      map2 = p2.second;
  
      if ( map2 != null ) {
        result = (V1)max( o1, map2 );
      }
    }
//    TimeVaryingMap<?> map = null;
//    try {
//      map = Expression.evaluate( o1, TimeVaryingMap.class, false );
//    } catch ( ClassCastException e ) {
//      //ignore
//    }
//    if ( map != null ) result = pow( map, o2 );
//    else {
//      try {
//      map = Expression.evaluate( o2, TimeVaryingMap.class, false );
//      } catch ( ClassCastException e ) {
//        //ignore
//      }
//      if ( map != null ) result = pow( o1, map );
//      else {
//        Number n1 = null;
//        Number n2 = null;
//        try {
//          n1 = Expression.evaluate( o1, Number.class, false );
//          n2 = Expression.evaluate( o2, Number.class, false );
//        } catch ( Throwable e ) {
//          // ignore
//        }
        if ( n1 != null && n2 != null ) {
          if ( Infinity.isEqual( n1 ) ) {
            try {
              if ( Zero.isEqual( n2 ) ) {
                result = One.forClass( o1.getClass() );
              } else if ( Utils.isNegative( n2 ) ){
                result = NegativeInfinity.forClass( o1.getClass() );
              } else {
                result = Infinity.forClass( o1.getClass() );
              }
            } catch ( ClassCastException e ) {
              e.printStackTrace();
            }
          } else if ( NegativeInfinity.isEqual( n1 ) ) {
            try {
              if ( Zero.isEqual( n2 ) ) {
                result = NegativeOne.forClass( o1.getClass() );
              } else if ( Utils.isNegative( n2 ) ){
                result = Infinity.forClass( o1.getClass() );
              } else {
                result = NegativeInfinity.forClass( o1.getClass() );
              }
            } catch ( ClassCastException e ) {
              e.printStackTrace();
            }
          } else if ( Zero.isEqual( n1 ) ) {
            try {
              if ( Infinity.isEqual( n2 ) ) {
                result = One.forClass( o1.getClass() );
              } else if ( NegativeInfinity.isEqual( n2 ) ){
                result = NegativeOne.forClass( o1.getClass() );
              } else {
                result = Zero.forClass( o1.getClass() );
              }
            } catch ( ClassCastException e ) {
              e.printStackTrace();
            }
          // TODO -- other types, like BigDecimal
          // TODO -- Need to add a gov.nasa.jpl.ae.util.Math.pow() to handle overflow.
          result = (Double)Math.pow( n1.doubleValue(), n2.doubleValue() );
          } else if ( n1 instanceof Double || n2 instanceof Double ) {
          } else if ( n1 instanceof Float || n2 instanceof Float ) {
            result = (Float)((Double)result).floatValue();
          } else if ( n1 instanceof Long || n2 instanceof Long ) {
            result = (Long)((Double)result).longValue();
          } else {
            result = (Integer)((Double)result).intValue();
          }
          //return (V1)result;
        }
//      }
//    }
    try {
      if ( o1 != null && o2 != null && result != null ) {
        Class<?> cls1 = o1.getClass();
        Class<?> cls2 = o2.getClass();
        // TOOD -- what if cls1 is not a Number?
        Object x = Expression.evaluate( result,
                                        ClassUtils.dominantTypeClass(cls1,cls2),
                                        false );
        if ( x == null ) x = result;
        // TODO: type casting this w/ V1 assume that it is the dominant class
        return (V1)x;
      }
      return (V1)result;
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    return null;
  }

  
  public static <V1, V2> V1 minus( V1 o1, V2 o2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return plus( o1, times( o2, -1 ) );
  }
  
  public static < T, TT > T add( Expression< T > o1,
                                  Expression< TT > o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null || o2 == null ) return null;
    T r1 = (T)o1.evaluate( false );
    TT r2 = (TT)o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    return plus(r1,r2);
  }
  
  public static < T, TT > T subtract( Expression< T > o1, 
                                      Expression< TT > o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null || o2 == null ) return null;
    T r1 = (T)o1.evaluate( false );
    TT r2 = (TT)o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    return minus( r1, r2 );
  }

  public static < T, TT > T times( Expression< T > o1,
                                                  Expression< TT > o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null || o2 == null ) return null;
    T r1 = (T)o1.evaluate( false );
    TT r2 = (TT)o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    return times(r1, r2);
  }
  
  public static < T, TT > T divide( Expression< T > o1,
                                    Expression< TT > o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null || o2 == null ) return null;
    T r1 = (T)o1.evaluate( false );
    TT r2 = (TT)o2.evaluate( false );
    if ( r1 == null || r2 == null ) return null;
    return divide(r1, r2);
  }

  public static class Identity<T> extends Unary< T, T > {
    public Identity( Expression< T > o ) {
      super( o, "identity" );
      setMonotonic( true );
    }
    public Identity( Identity<T> m) {
      super(m);
    }
    public Identity<T> clone() {
      return new Identity<T>(this);
    }

    @Override
    public < T > T pickValue( Variable< T > variable ) {
      return variable.getValue( false );
    }
  }

  public static <T> T identity(T t) {
    return t;
  }
  public static Expression<?> identity(Expression<?> e) {
    return e;
  }

  
  public static class Negative<T> extends Unary< T, T > {
    public Negative( Expression< T > o ) {
      super( o, "negative" );
      //functionCall.
      setMonotonic( true );
    }
    public Negative( Negative<T> m) {
      super(m);
    }
    public Negative<T> clone() {
      return new Negative<T>(this);
    }
    
    // TODO -- handle cast errors
    @Override
    public < T > T pickValue( Variable< T > variable ) {
      Object arg = this.getArgument( 0 );//((FunctionCall)this.expression).getArgument( 0 );
      if ( arg == variable ) {
        return (T)negative( variable );
      }
      return pickValueE( variable, arg );
//      else if ( arg instanceof Suggester ) {
//        return (T)(negative(((Number)((Suggester)arg).pickValue( variable ))));
//      } else if ( arg instanceof Expression && ((Expression)arg).expression instanceof Suggester ) {
//        return (T)(negative((Number)((Suggester)((Expression)arg).expression).pickValue( variable )));
//      }
//      return null;
    }
  }

  public static <T> java.lang.Number negative( T v ) {
    if ( v instanceof Number ) {
      return negative((Number)v);
    }
    if ( v instanceof Variable ) {
      return negative((Variable<T>)v);
    }
    if ( v instanceof String ) {
      return negative((Number)v);
    }
    return null;
  }

  public static <T> java.lang.Number negative( Variable< T > v ) {
    T r = v.getValue( false );
    return negative( r );
//    if ( r instanceof Number ) {
//      return negative( (Number)r );
//    }
//    return null;
  }

  public static java.lang.Number negative( String v ) {
    Long i = Utils.toLong( v );
    Double n = Utils.toDouble( v );
    if ( i == null && n == null ) {
      // TODO -- ERROR!
      return null;
    }
    if ( i != null && n != null && i.doubleValue() == n.doubleValue() ) {
      i = -i;
      return i;
    }
    if ( n != null ) {
      n = -n;
      return n;
    }
    i = -i;
    return i;
  }

  public static Number negative( Number n ) {
    if ( n == null ) return null;
    Number result = null;
    if ( n.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      result = ((Double)n) * -1.0;
    } else if ( n.getClass().isAssignableFrom( java.lang.Integer.class ) ) {
      result = ((Integer)n) * -1;
    }
    return result;
  }
  
  public static <T> java.lang.Number negative( Expression< T > o ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o == null ) return null;
    T r = null;
    try {
      r = (T)o.evaluate( false );
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    if ( r instanceof Number ) {
      return negative( (Number)r );
    }
    return null;
  }


  // Inequality functions

  public static class EQ< T >
                        extends BooleanBinary< T > {
    
    public EQ( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "equals", "pickEqualToForward", "pickEqualToForward");
    }
    public EQ( Object o1, Object o2 ) {
      super( o1, o2, "equals", "pickEqualToForward", "pickEqualToForward");
    }
    public EQ( EQ<T> m) {
      super(m);
    }
    public EQ<T> clone() {
      return new EQ<T>(this);
    }
    
//    @Override
//    public < T > Domain< T > restrictDomain( Domain< T > domain,
//                                             boolean propagate,
//                                             Set< HasDomain > seen ) {
//      Object o1 = this.arguments.get( 0 );
//      Object o2 = this.arguments.get( 1 );
//      if (o1 instanceof HasDomain && o2 instanceof HasDomain){
//        HasDomain hd1 = (HasDomain)o1;
//        HasDomain hd2 = (HasDomain)o2;
//        Domain d1 = hd1.getDomain( propagate, seen );
//        Domain d2 = hd2.getDomain( propagate, seen );
//        hd1.restrictDomain( d2, propagate, seen );
//        hd2.restrictDomain( d1, propagate, seen );
//      }
//      return (Domain< T >)getDomain(propagate, seen );
//    }
    
//    public FunctionCall inverse( Object returnValue, Object arg ) { //Variable<?> variable ) {
//      FunctionCall singleValueFcn = inverseSingleValue( returnValue, arg );
//      if ( singleValueFcn == null ) return null;
//      return new FunctionCall(null,
//                              ClassUtils.getMethodsForName( Utils.class, "newList" )[0],
//                              new Object[] { singleValueFcn }, (Class<?>)null );
//    }

    @Override
    public FunctionCall inverse( Object returnValue, Object arg ) {
      FunctionCall f = invert( Utils.isTrue( returnValue ) == Boolean.TRUE, arg );
      f.arguments = new Vector<Object>(Utils.newList( Boolean.FALSE ) ); // this argument is ignored
      return f;
//          new FunctionCall( this, getClass(), "invert",
//                            new Object[] { Utils.isTrue( returnValue ), arg }, (Class<?>)null );
//      return f.evaluate();
    }

    //TODO should handle returnValue = false
    public FunctionCall invert( final boolean eqReturnValue, final Object arg ) {
      LinkedHashSet< Object > otherArgs = getOtherArgs( arg );
      // should only be one
      final Object otherArg = otherArgs.iterator().next();
      
      try {
        return new FunctionCall( (Method)Functions.class.getMethod( "not", new Class<?>[]{Expression.class} ), (Class<?>)null ) {
          @Override
          public Object
                 invoke( Object evaluatedObject,
                         Object[] evaluatedArgs ) throws IllegalArgumentException,
                                                  InstantiationException,
                                                  IllegalAccessException,
                                                  InvocationTargetException {
            return otherArg;
          }
          @Override
          public Domain< ? > getDomain( boolean propagate,
                                        Set< HasDomain > seen ) {
            if( otherArg instanceof HasDomain ) {
              Domain< ? > otherDomain = ((HasDomain)otherArg).getDomain(propagate, seen);
              if ( eqReturnValue || otherDomain.magnitude() > 1 ) {
                return otherDomain;
              }
              // need to return the arg.getDomain() excluding the single value
              if ( arg instanceof HasDomain ) {
                Domain<?> domain = ( (HasDomain)arg ).getDomain( propagate, seen );
                domain = domain.subtract( otherDomain );
                return domain;
              } else {
                  // TODO??!!
              }
              return null;
            } else {
                // TODO??!!
            }
            return null;
          }
        };
      } catch ( NoSuchMethodException e ) {
        e.printStackTrace();
      } catch ( SecurityException e ) {
        e.printStackTrace();
      }
         
      return null;
//      LinkedHashSet< Object > values = new LinkedHashSet< Object >();
//      Class<?> type = arg.getClass();
//      if ( arg instanceof Wraps ) {
//        type = ((Wraps)arg).getType();
//      }
//      for ( Object o : otherArgs ) {
//        try {
//          Object r = Expression.evaluate( o, type, true );
//          values.add(r);
//        } catch ( ClassCastException e ) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//        } catch ( IllegalAccessException e ) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//        } catch ( InvocationTargetException e ) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//        } catch ( InstantiationException e ) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//        }
//      }
//      return values;
    }
    
    @Override
    public Domain< ? > calculateDomain( boolean propagate, Set< HasDomain > seen ) {
      Object o1 = this.arguments.get( 0 );
      Object o2 = this.arguments.get( 1 );
      if (o1 instanceof HasDomain && o2 instanceof HasDomain){
        HasDomain hd1 = (HasDomain)o1;
        HasDomain hd2 = (HasDomain)o2;
        Domain d1 = hd1.getDomain(propagate, seen);
        Domain d2 = hd2.getDomain( propagate, seen );
        if(d1 instanceof RangeDomain && d2 instanceof RangeDomain){
          AbstractRangeDomain rd1 = (AbstractRangeDomain)d1;
          AbstractRangeDomain rd2 = (AbstractRangeDomain)d2;
          if (rd1.size() == 1 && rd2.size() == 1 && rd1.equals( rd2 ) ) {
            System.out.println( "true" );
            return new BooleanDomain( true, true );
          }
          else if( rd1.intersects( rd1 ) ) {//greaterEquals(rd1.getUpperBound(), rd2.getLowerBound()) &&
                  //rd1.lessEquals(rd1.getLowerBound(), rd2.getUpperBound()) ){
            System.out.println( "true or false" );
            return new BooleanDomain(false, true);
          }
          else 
            System.out.println( "false" );
            return new BooleanDomain( false, false );
        }
        //TODO else case
      }
      return null;
    }
    
    /*
    @Override
    public < T1 > T1 pickValue( Variable< T1 > variable ) {
      T1 newValue = null;
      FunctionCall f = (FunctionCall)this.expression;
      Variable< T1 > otherArg = null;
      boolean found = false;
      for ( Object arg : f.arguments ) {
        if ( variable == arg ) {
          found = true;
        } else if ( arg instanceof Expression
                    && ( ( (Expression)arg ).expression == variable ) ) {
          found = true;
        } else if ( arg instanceof Expression
                    && !( ( (Expression)arg ).expression instanceof Parameter )
                    && variable instanceof Parameter ) {
          if ( ( (Expression< T1 >)arg ).hasParameter( (Parameter< T1 >)variable,
                                                       false, null ) ) {
            newValue = variable.pickRandomValue();
          }
        } else if ( arg instanceof Variable ) {
          if ( otherArg == null ) {
            otherArg = (Variable< T1 >)arg;
          }
        }
      }
      if ( otherArg != null && found ) {
        if ( Debug.isOn() ) Debug.outln( "suggesting value " + otherArg.getValue() + " to make "
                     + getClass().getSimpleName() + " true" );
        return otherArg.getValue();
      }
      if ( newValue == null ) {
        if ( Debug.isOn() ) Debug.outln( "suggesting same value " + variable.getValue() + " for "
            + getClass().getSimpleName() + " true" );
        return variable.getValue();
      }
      if ( Debug.isOn() ) Debug.outln( "suggesting value " + newValue + " for "
                   + getClass().getSimpleName() + " true" );
      return newValue;
    }
    
*/
  }
    
  public static class Equals< T > extends EQ< T > {
    public Equals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }
    public Equals( Object o1, Object o2 ) {
      super( o1, o2 );
    }
  }

  public static class NEQ< T > 
                        extends BooleanBinary< T > {
    public NEQ( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "notEquals", "pickNotEqualToForward", "pickNotEqualToForward");
    }
    public NEQ( Object o1, Object o2 ) {
      super( o1, o2, "notEquals", "pickNotEqualToForward", "pickNotEqualToForward");
    }
    public NEQ( NEQ<T> m) {
      super(m);
    }
    public NEQ<T> clone() {
      return new NEQ<T>(this);
    }

//    @Override
//    public < T1 > T1 pickValue( Variable< T1 > variable ) {
//      return pickValueBB( this, variable );
//    }
/*      T1 newValue = null;
      FunctionCall f = (FunctionCall)this.expression;
      Variable< T1 > otherArg = null;
      boolean found = false;
      for ( Object arg : f.arguments ) {
        if ( variable == arg ) {
          found = true;
        } else if ( arg instanceof Expression
                    && ( ( (Expression)arg ).expression == variable ) ) {
          found = true;
        } else if ( arg instanceof Expression
                    && !( ( (Expression)arg ).expression instanceof Parameter )
                    && variable instanceof Parameter ) {
          if ( ( (Expression< T1 >)arg ).hasParameter( (Parameter< T1 >)variable,
                                                       false ) ) {
            newValue = variable.pickRandomValue();
          }
        } else if ( arg instanceof Variable ) {
          if ( otherArg == null ) {
            otherArg = (Variable< T1 >)arg;
          }
        }
      }
      if ( otherArg != null && found ) {
        newValue = null;
        for ( int i=0; i < 5; ++i ) {
          T1 v = variable.pickRandomValue();
          if ( v != otherArg.getValue() ) {
            if ( newValue == null ) {
              newValue = v;
            } else if ( otherArg.getDomain() == null || otherArg.getDomain().contains( v ) ) {
              return v;
            }
          }
        }
      }
      if ( newValue == null ) {
        return variable.getValue();
      }
      return newValue;
    }
    */
  }

  public static class NotEquals< T > extends NEQ< T > {
    public NotEquals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }

    public NotEquals( Object o1, Object o2 ) {
      super( o1, o2 );
    }
  }

  public static void excludeUpperBound( RangeDomain r ) {
    r.excludeUpperBound();
    if ( r instanceof AbstractFiniteRangeDomain ) {
      ( (AbstractFiniteRangeDomain)r ).fixToIncludeBounds();
    }
  }

  public static void excludeLowerBound( RangeDomain r ) {
    r.excludeLowerBound();
    if ( r instanceof AbstractFiniteRangeDomain ) {
      ( (AbstractFiniteRangeDomain)r ).fixToIncludeBounds();
    }
  }
  
  public static class LT< T > extends BooleanBinary< T > {
    public LT( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "lessThan", "pickLessThan", "pickGreaterThanOrEqual" );
      // functionCall.
      setMonotonic( true );
    }

    public LT( Object o1, Object o2 ) {
      super( o1, o2, "lessThan", "pickLessThan", "pickGreaterThanOrEqual" );
      // functionCall.
      setMonotonic( true );
    }
    
    public LT( LT<T> m) {
      super(m);
    }
    public LT<T> clone() {
      return new LT<T>(this);
    }

    @Override
    public FunctionCall inverse( Object returnValue, Object arg ) {
      return super.inverse( returnValue, arg );
    }
    
    @Override
    public Domain< Boolean > calculateDomain( boolean propagate,
                                        Set< HasDomain > seen ) {
      if ( getArguments().size() != 2 ) {
        return BooleanDomain.defaultDomain;
      }
      Object a1 = getArgument( 0 );
      Object a2 = getArgument( 1 );
      Domain<?> d1 = DomainHelper.getDomain( a1 );
      Domain<?> d2 = DomainHelper.getDomain( a2 );
      if ( d1 == null || d2 == null || d1.magnitude() <= 0 || d2.magnitude() <= 0 ) {
        return BooleanDomain.defaultDomain;
      }
      if ( d1 instanceof AbstractRangeDomain && d2 instanceof AbstractRangeDomain ) {
        AbstractRangeDomain<Object> rd1 = (AbstractRangeDomain<Object>)d1;
        AbstractRangeDomain<Object> rd2 = (AbstractRangeDomain<Object>)d2;
        if (rd1.less( rd2 ) == Boolean.TRUE) return BooleanDomain.trueDomain;
        if (rd1.greaterEquals( rd2 ) == Boolean.TRUE ) return BooleanDomain.falseDomain;
//        Object lb1 = rd1.getLowerBound();
//        Object ub1 = rd1.getUpperBound();
//        Object lb2 = rd2.getLowerBound();
//        Object ub2 = rd2.getUpperBound();
//            
//        if ( rd1.greaterEquals( lb1, ub2 ) ) {
//          return BooleanDomain.falseDomain;
//        }
//        if ( rd1.less( ub1, lb2 )
//             || ( rd1.equals( ub1, lb2 )
//                  && ( !rd1.isUpperBoundIncluded()
//                       || !rd2.isLowerBoundIncluded() ) ) ) {
//          return BooleanDomain.trueDomain;
//        }
      }
      // TODO -- There are many possibilities here. Instead, define less(),
      // alwaysLess(), etc. methods for domains that take a variety of
      // arguments.
      return BooleanDomain.defaultDomain;
    }
    
    @Override
    public FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      if ( arguments == null || arguments.size() != 2 ) return null;
      Object otherArg = ( arg == arguments.get( 1 ) ? arguments.get( 0 ) : arguments.get( 1 ) );
      boolean firstArg = otherArg != arguments.get( 0 );  // thus arg is the first
      if ( returnValue == null || otherArg == null ) return null; // arg can be null!
      AbstractRangeDomain<?> subDomainBelow =
          DomainHelper.createSubDomainBelow( otherArg, false, false );
      AbstractRangeDomain<?> subDomainAbove =
          DomainHelper.createSubDomainAbove( otherArg, false, false );
      if ( firstArg ) {
        subDomainAbove.includeLowerBound();
        return new Conditional( new Expression< Boolean >( returnValue, Boolean.class ),
                                new Expression< Object >( subDomainBelow ),
                                new Expression< Object >( subDomainAbove ) );
      }
      subDomainBelow.includeUpperBound();
      return new Conditional( new Expression< Boolean >( returnValue, Boolean.class ),
                              new Expression< Object >( subDomainAbove ),
                              new Expression< Object >( subDomainBelow ) );
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.nasa.jpl.ae.event.Call#restrictDomain(gov.nasa.jpl.ae.solver.Domain,
     * boolean, java.util.Set)
     */
    @Override
    public < TT > Pair< Domain< TT >, Boolean >
           restrictDomain( Domain< TT > domain, boolean propagate,
                           Set< HasDomain > seen ) {
      boolean changed = false;
      if ( domain.contains( (TT)Boolean.TRUE )
           && domain.contains( (TT)Boolean.FALSE ) ) {
        // nothing to do
      } else if ( domain.magnitude() == 1 ) {
        Object v = domain.getValue( propagate );
        if ( v instanceof Boolean ) {
          String oldDom = this.getDomain( propagate, null ).toString();
          changed = restrictDomains( ( (Boolean)v ) == Boolean.TRUE );
          if ( changed ) {
            System.out.println("Restricted " + getName() + " from " + oldDom + " to " + getDomain(propagate, null) );
          }
        }
      }
      // Domain oldDomain = this.domain.clone();
      // Domain newDomain = (Domain< TT >)getDomain(propagate, null);
      // boolean thisChanged = Utils.valuesEqual( oldDomain, newDomain );
      // this.domain = newDomain;
      return new Pair( this.domain, changed );// || thisChanged);
    }

    
    
    // REVIEW -- This seems out of place.  Does something else do this?
    public boolean restrictDomains( boolean targetResult ) {
      if ( arguments.size() < 2 ) return false;
      Expression< T > e1 = (Expression< T >)arguments.get( 0 );
      Expression< T > e2 = (Expression< T >)arguments.get( 1 );
      Domain< T > d1 = e1.getDomain( false, null );
      Domain< T > d2 = e2.getDomain( false, null );
      boolean changed = false;
      if ( d1 instanceof AbstractRangeDomain && d2 instanceof AbstractRangeDomain ) {
        AbstractRangeDomain< T > ard1 = (AbstractRangeDomain< T >)d1;
        AbstractRangeDomain< T > ard2 = (AbstractRangeDomain< T >)d2;
        ard1 = ard1.clone();
        ard2 = ard2.clone();
        if ( targetResult == true ) {
//          boolean c1 = ard1.restrictTo( ard2.createSubDomainBelow( ard2.getUpperBound(), false ) );
//          boolean c2 = ard2.restrictTo( ard1.createSubDomainAbove( ard1.getLowerBound(), false ) );
//          changed = changed || c1 || c2;
          if ( ard1.greaterEquals( ard1.getUpperBound(), ard2.getUpperBound() ) ) {
            boolean c = ard1.setUpperBound( ard2.getUpperBound() );
            if ( ard1.isUpperBoundIncluded() ) {
              excludeUpperBound(ard1);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e1.restrictDomain( ard1, true, null );
              changed = changed || (p.second == Boolean.TRUE);
            }
          }
          if ( ard2.lessEquals( ard2.getLowerBound(), ard1.getLowerBound() ) ) {
            boolean c = ard2.setLowerBound( ard1.getLowerBound() );
            if ( ard2.isLowerBoundIncluded() ) {
              excludeLowerBound(ard2);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e2.restrictDomain( ard2, true, null );
              changed = changed || (p.second == Boolean.TRUE);
            }
          }
        } else {
//          boolean c1 = ard1.restrictTo( ard2.createSubDomainAbove( ard2.getLowerBound(), ard2.isLowerBoundIncluded() ) );
//          boolean c2 = ard2.restrictTo( ard1.createSubDomainBelow( ard1.getUpperBound(), ard1.isUpperBoundIncluded() ) );
//          changed = changed || c1 || c2;
          if ( ard1.lessEquals( ard1.getLowerBound(), ard2.getLowerBound() ) ) {
            boolean c = ard1.setLowerBound( ard2.getLowerBound() );
            if ( !ard2.isLowerBoundIncluded() && ard1.isLowerBoundIncluded() ) {
              excludeLowerBound(ard1);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e1.restrictDomain( ard1, true, null );
              changed = changed || (p.second == Boolean.TRUE);
            }
          }
          if ( ard2.greaterEquals( ard2.getUpperBound(), ard1.getUpperBound() ) ) {
            boolean c = ard2.setUpperBound( ard1.getUpperBound() );
            if ( !ard1.isUpperBoundIncluded() && ard2.isUpperBoundIncluded() ) {
              excludeUpperBound(ard2);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e2.restrictDomain( ard2, true, null );
              changed = changed || (p.second == Boolean.TRUE);
            }
          }
        }
      }
      return changed;
    }

  }

  public static class Less< T > extends LT< T > {
    public Less( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
      // functionCall.
      setMonotonic( true );
    }

    public Less( Object o1, Object o2 ) {
      super( o1, o2 );
      // functionCall.
      setMonotonic( true );
    }
  }

  public static class LTE< T > extends BooleanBinary< T > {
    public LTE( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2,
             "lessThanOrEqual", "pickLessThanOrEqual", "pickGreaterThan" );
      // functionCall.
      setMonotonic( true );
    }

    public LTE( Object o1, Object o2 ) {
      super( o1, o2,
             "lessThanOrEqual", "pickLessThanOrEqual", "pickGreaterThan" );
      // functionCall.
      setMonotonic( true );
    }
    
    public LTE( LTE<T> m) {
      super(m);
    }
    public LTE<T> clone() {
      return new LTE<T>(this);
    }

    @Override
    public FunctionCall inverse( Object returnValue, Object arg ) {
      return super.inverse( returnValue, arg );
    }
    
    @Override
    public Domain< Boolean > calculateDomain( boolean propagate,
                                        Set< HasDomain > seen ) {
      if ( getArguments().size() != 2 ) {
        return BooleanDomain.defaultDomain;
      }
      Object a1 = getArgument( 0 );
      Object a2 = getArgument( 1 );
      Domain<?> d1 = DomainHelper.getDomain( a1 );
      Domain<?> d2 = DomainHelper.getDomain( a2 );
      if ( d1 == null || d2 == null || d1.magnitude() <= 0 || d2.magnitude() <= 0 ) {
        return BooleanDomain.defaultDomain;
      }
      if ( d1 instanceof AbstractRangeDomain && d2 instanceof AbstractRangeDomain ) {
        AbstractRangeDomain<Object> rd1 = (AbstractRangeDomain<Object>)d1;
        AbstractRangeDomain<Object> rd2 = (AbstractRangeDomain<Object>)d2;
        if (rd1.lessEquals( rd2 ) == Boolean.TRUE) return BooleanDomain.trueDomain;
        if (rd1.greater( rd2 ) == Boolean.TRUE ) return BooleanDomain.falseDomain;
      }
      // TODO -- There are many possibilities here. Instead, define less(),
      // alwaysLess(), etc. methods for domains that take a variety of
      // arguments.
      return BooleanDomain.defaultDomain;
    }
    
    @Override
    public FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      if ( arguments == null || arguments.size() != 2 ) return null;
      Object otherArg = ( arg == arguments.get( 1 ) ? arguments.get( 0 ) : arguments.get( 1 ) );
      boolean firstArg = otherArg != arguments.get( 0 );  // thus arg is the first
      if ( returnValue == null || otherArg == null ) return null; // arg can be null!
      AbstractRangeDomain<?> subDomainBelow =
          DomainHelper.createSubDomainBelow( otherArg, true, false );
      AbstractRangeDomain<?> subDomainAbove =
          DomainHelper.createSubDomainAbove( otherArg, true, false );
      if ( firstArg ) {
        excludeLowerBound(subDomainAbove);
        return new Conditional( new Expression< Boolean >( returnValue, Boolean.class ),
                                new Expression< Object >( subDomainBelow ),
                                new Expression< Object >( subDomainAbove ) );
      }
      excludeUpperBound(subDomainBelow);
      return new Conditional( new Expression< Boolean >( returnValue, Boolean.class ),
                              new Expression< Object >( subDomainAbove ),
                              new Expression< Object >( subDomainBelow ) );
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.nasa.jpl.ae.event.Call#restrictDomain(gov.nasa.jpl.ae.solver.Domain,
     * boolean, java.util.Set)
     */
    @Override
    public < TT > Pair< Domain< TT >, Boolean >
           restrictDomain( Domain< TT > domain, boolean propagate,
                           Set< HasDomain > seen ) {
      boolean changed = false;
      if ( domain.contains( (TT)Boolean.TRUE )
           && domain.contains( (TT)Boolean.FALSE ) ) {
        // nothing to do
      } else if ( domain.magnitude() == 1 ) {
        Object v = domain.getValue( propagate );
        if ( v instanceof Boolean ) {
          String oldDom = this.getDomain( propagate, null ).toString();
          changed = restrictDomains( ( (Boolean)v ) == Boolean.TRUE );
          if ( changed ) {
            System.out.println("Restricted " + getName() + " from " + oldDom + " to " + getDomain(propagate, null) );
          }
        }
      }
      // Domain oldDomain = this.domain.clone();
      // Domain newDomain = (Domain< TT >)getDomain(propagate, null);
      // boolean thisChanged = Utils.valuesEqual( oldDomain, newDomain );
      // this.domain = newDomain;
      return new Pair( this.domain, changed );// || thisChanged);
    }

    // REVIEW -- This seems out of place.  Does something else do this?
    public boolean restrictDomains( boolean targetResult ) {
      if ( arguments.size() < 2 ) return false;
      Expression< T > e1 = (Expression< T >)arguments.get( 0 );
      Expression< T > e2 = (Expression< T >)arguments.get( 1 );
      Domain< T > d1 = e1.getDomain( false, null );
      Domain< T > d2 = e2.getDomain( false, null );
      boolean changed = false;
      if ( d1 instanceof AbstractRangeDomain && d2 instanceof AbstractRangeDomain ) {
        AbstractRangeDomain< T > ard1 = (AbstractRangeDomain< T >)d1;
        AbstractRangeDomain< T > ard2 = (AbstractRangeDomain< T >)d2;
        if ( targetResult == true ) {
//          boolean c1 = ard1.restrictTo( ard2.createSubDomainBelow( ard2.getUpperBound(), ard2.isUpperBoundIncluded() ) );
//          boolean c2 = ard2.restrictTo( ard1.createSubDomainAbove( ard1.getLowerBound(), ard2.isUpperBoundIncluded() ) );
//          changed = changed || c1 || c2;
          if ( ard1.greaterEquals( ard1.getUpperBound(), ard2.getUpperBound() ) ) {
            boolean c = ard1.setUpperBound( ard2.getUpperBound() );
            if (!ard2.isUpperBoundIncluded() && ard1.isUpperBoundIncluded() ) {
              excludeUpperBound(ard1);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e1.restrictDomain( ard1, true, null );
              changed = changed || p.second;
            }
          }
          if ( ard2.lessEquals( ard2.getLowerBound(), ard1.getLowerBound() ) ) {
            boolean c = ard2.setLowerBound( ard1.getLowerBound() );
            if (!ard1.isLowerBoundIncluded() && ard2.isLowerBoundIncluded() ) {
              excludeLowerBound(ard2);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e2.restrictDomain( ard2, true, null );
              changed = changed || p.second;
            }
          }
        } else {
//        boolean c1 = ard1.restrictTo( ard2.createSubDomainAbove( ard2.getLowerBound(), false ) );
//        boolean c2 = ard2.restrictTo( ard1.createSubDomainBelow( ard1.getUpperBound(), false ) );
//        changed = changed || c1 || c2;
          if ( ard1.lessEquals( ard1.getLowerBound(), ard2.getLowerBound() ) ) {
            boolean c = ard1.setLowerBound( ard2.getLowerBound() );
            if ( ard1.isLowerBoundIncluded() ) {
              excludeLowerBound(ard1);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e1.restrictDomain( ard1, true, null );
              changed = changed || p.second;
            }
          }
          if ( ard2.greaterEquals( ard2.getUpperBound(), ard1.getUpperBound() ) ) {
            boolean c = ard2.setUpperBound( ard1.getUpperBound() );
            if ( ard2.isUpperBoundIncluded() ) {
              excludeUpperBound(ard2);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e2.restrictDomain( ard2, true, null );
              changed = changed || p.second;
            }
          }
        }
      }
      return changed;
    }

    
  }

  public static class LessEquals< T > extends LTE< T > {
    public LessEquals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
      // functionCall.
      setMonotonic( true );
    }

    public LessEquals( Object o1, Object o2 ) {
      super( o1, o2 );
      // functionCall.
      setMonotonic( true );
    }
  }

  public static class GT< T extends Comparable< ? super T > > extends
                                                              BooleanBinary< T > {
    public GT( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "greaterThan", "pickGreaterThan", "pickLessThanOrEqual" );
      // functionCall.
      setMonotonic( true );
    }

    public GT( Object o1, Object o2 ) {
      super( o1, o2, "greaterThan", "pickGreaterThan", "pickLessThanOrEqual" );
      // functionCall.
      setMonotonic( true );
    }
    
    public GT( GT<T> m) {
      super(m);
    }
    public GT<T> clone() {
      return new GT<T>(this);
    }

    @Override
    public FunctionCall inverse( Object returnValue, Object arg ) {
      return super.inverse( returnValue, arg );
    }
    
    @Override
    public FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      if ( arguments == null || arguments.size() != 2 ) return null;
      Object otherArg = ( arg == arguments.get( 1 ) ? arguments.get( 0 ) : arguments.get( 1 ) );
      boolean firstArg = otherArg != arguments.get( 0 );  // thus arg is the first
      if ( returnValue == null || otherArg == null ) return null; // arg can be null!
      AbstractRangeDomain<?> subDomainBelow =
          DomainHelper.createSubDomainBelow( otherArg, false, false );
      AbstractRangeDomain<?> subDomainAbove =
          DomainHelper.createSubDomainAbove( otherArg, false, false );

      if ( firstArg ) {
        subDomainBelow.includeUpperBound();
        return new Conditional( new Expression< Boolean >( returnValue, Boolean.class ),
                                new Expression< Object >( subDomainAbove ),
                                new Expression< Object >( subDomainBelow ) );
      }
      subDomainAbove.includeLowerBound();
      return new Conditional( new Expression< Boolean >( returnValue, Boolean.class ),
                              new Expression< Object >( subDomainBelow ),
                              new Expression< Object >( subDomainAbove ) );
    }
    
    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.event.FunctionCall#calculateDomain(boolean, java.util.Set)
     */
    @Override
    public Domain< Boolean > calculateDomain( boolean propagate,
                                              Set< HasDomain > seen ) {
      if ( getArguments().size() != 2 ) {
        return BooleanDomain.defaultDomain;
      }
      Object a1 = getArgument( 0 );
      Object a2 = getArgument( 1 );
      Domain<?> d1 = DomainHelper.getDomain( a1 );
      Domain<?> d2 = DomainHelper.getDomain( a2 );
      if ( d1 == null || d2 == null || d1.magnitude() <= 0 || d2.magnitude() <= 0 ) {
        return BooleanDomain.defaultDomain;
      }
      if ( d1 instanceof AbstractRangeDomain && d2 instanceof AbstractRangeDomain ) {
        AbstractRangeDomain<Object> rd1 = (AbstractRangeDomain<Object>)d1;
        AbstractRangeDomain<Object> rd2 = (AbstractRangeDomain<Object>)d2;
        if (rd1.greater( rd2 ) == Boolean.TRUE) return BooleanDomain.trueDomain;
        if (rd1.lessEquals( rd2 ) == Boolean.TRUE ) return BooleanDomain.falseDomain;
      }
      // TODO -- There are many possibilities here. Instead, define less(),
      // alwaysLess(), etc. methods for domains that take a variety of
      // arguments.
      return BooleanDomain.defaultDomain;
    }

    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.event.Call#restrictDomain(gov.nasa.jpl.ae.solver.Domain, boolean, java.util.Set)
     */
    @Override
    public < TT > Pair<Domain< TT >,Boolean> restrictDomain( Domain< TT > domain,
                                                             boolean propagate,
                                                             Set< HasDomain > seen ) {
      boolean changed = false;
      if ( domain.contains((TT)Boolean.TRUE) && domain.contains((TT)Boolean.FALSE) ) {
        // nothing to do
      } else if ( domain.magnitude() == 1 ) {
        Object v = domain.getValue( propagate );
        if ( v instanceof Boolean ) {
          String oldDom = this.getDomain( propagate, null ).toString();
          changed = restrictDomains( ( (Boolean)v ) == Boolean.TRUE );
          if ( changed ) {
            System.out.println("Restricted " + getName() + " from " + oldDom + " to " + getDomain(propagate, null) );
          }
        }
      }
      return new Pair(this.domain, changed);// || thisChanged);
    }
    
    // REVIEW -- This seems out of place.  Does something else do this?
    public boolean restrictDomains( boolean targetResult ) {
      if ( arguments.size() < 2 ) return false;
      Expression< T > e1 = (Expression< T >)arguments.get( 0 );
      Expression< T > e2 = (Expression< T >)arguments.get( 1 );
      Domain< T > d1 = e1.getDomain( false, null );
      Domain< T > d2 = e2.getDomain( false, null );
      boolean changed = false;
      if ( d1 instanceof AbstractRangeDomain && d2 instanceof AbstractRangeDomain ) {
        AbstractRangeDomain< T > ard1 = (AbstractRangeDomain< T >)d1;
        AbstractRangeDomain< T > ard2 = (AbstractRangeDomain< T >)d2;
        if ( targetResult == true ) {
//          boolean c1 = ard1.restrictTo( ard2.createSubDomainAbove( ard2.getLowerBound(), false ) );
//          boolean c2 = ard2.restrictTo( ard1.createSubDomainBelow( ard1.getUpperBound(), false ) );
//          changed = changed || c1 || c2;
          if ( ard1.lessEquals( ard1.getLowerBound(), ard2.getLowerBound() ) ) {
            boolean c = ard1.setLowerBound( ard2.getLowerBound() );
            if ( ard1.isLowerBoundIncluded() ) {
              excludeLowerBound(ard1);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e1.restrictDomain( ard1, true, null );
              changed = changed || p.second;
            }
          }
          if ( ard2.greaterEquals( ard2.getUpperBound(), ard1.getUpperBound() ) ) {
            boolean c = ard2.setUpperBound( ard1.getUpperBound() );
            if ( ard2.isUpperBoundIncluded() ) {
              excludeUpperBound(ard2);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e2.restrictDomain( ard2, true, null );
              changed = changed || p.second;
            }
          }
        } else {
//        boolean c1 = ard1.restrictTo( ard2.createSubDomainBelow( ard2.getUpperBound(), ard2.isUpperBoundIncluded() ) );
//        boolean c2 = ard2.restrictTo( ard1.createSubDomainAbove( ard1.getLowerBound(), ard2.isUpperBoundIncluded() ) );
//        changed = changed || c1 || c2;
          if ( ard1.greaterEquals( ard1.getUpperBound(), ard2.getUpperBound() ) ) {
            boolean c = ard1.setUpperBound( ard2.getUpperBound() );
            if (!ard2.isUpperBoundIncluded() && ard1.isUpperBoundIncluded() ) {
              excludeUpperBound(ard1);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e1.restrictDomain( ard1, true, null );
              changed = changed || p.second;
            }
          }
          if ( ard2.lessEquals( ard2.getLowerBound(), ard1.getLowerBound() ) ) {
            boolean c = ard2.setLowerBound( ard1.getLowerBound() );
            if (!ard1.isLowerBoundIncluded() && ard2.isLowerBoundIncluded() ) {
              excludeLowerBound(ard2);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e2.restrictDomain( ard2, true, null );
              changed = changed || p.second;
            }
          }
        }
      }
      return changed;
    }

  }

  public static class Greater< T extends Comparable< ? super T > > extends
                                                                   GT< T > {
    public Greater( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
      setMonotonic( true );
    }

    public Greater( Object o1, Object o2 ) {
      super( o1, o2 );
      setMonotonic( true );
    }

  }

  public static class GTE< T extends Comparable< ? super T > > extends BooleanBinary< T > {
    public GTE( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2, "greaterThanOrEqual", "pickGreaterThanOrEqual",
             "pickLessThan" );
      setMonotonic( true );
    }

    public GTE( Object o1, Object o2 ) {
      super( o1, o2, "greaterThanOrEqual", "pickGreaterThanOrEqual",
             "pickLessThan" );
      setMonotonic( true );
    }

    public GTE( GTE<T> m) {
      super(m);
    }
    public GTE<T> clone() {
      return new GTE<T>(this);
    }

    @Override
    public FunctionCall inverse( Object returnValue, Object arg ) {
      return super.inverse( returnValue, arg );
    }
    
    @Override
    public FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      if ( arguments == null || arguments.size() != 2 ) return null;
      Object otherArg = ( arg == arguments.get( 1 ) ? arguments.get( 0 ) : arguments.get( 1 ) );
      boolean firstArg = otherArg != arguments.get( 0 );  // thus arg is the first
      if ( returnValue == null || otherArg == null ) return null; // arg can be null!
      AbstractRangeDomain<?> subDomainBelow =
          DomainHelper.createSubDomainBelow( otherArg, true, false );
      AbstractRangeDomain<?> subDomainAbove =
          DomainHelper.createSubDomainAbove( otherArg, true, false );
      if ( firstArg ) {
        excludeUpperBound(subDomainBelow);
        return new Conditional( new Expression< Boolean >( returnValue, Boolean.class ),
                                new Expression< Object >( subDomainAbove ),
                                new Expression< Object >( subDomainBelow ) );
      }
      excludeLowerBound(subDomainAbove);
      return new Conditional( new Expression< Boolean >( returnValue, Boolean.class ),
                              new Expression< Object >( subDomainBelow ),
                              new Expression< Object >( subDomainAbove ) );
    }
    
    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.event.FunctionCall#calculateDomain(boolean, java.util.Set)
     */
    @Override
    public Domain< Boolean > calculateDomain( boolean propagate,
                                              Set< HasDomain > seen ) {
      if ( getArguments().size() != 2 ) {
        return BooleanDomain.defaultDomain;
      }
      Object a1 = getArgument( 0 );
      Object a2 = getArgument( 1 );
      Domain<?> d1 = DomainHelper.getDomain( a1 );
      Domain<?> d2 = DomainHelper.getDomain( a2 );
      if ( d1 == null || d2 == null || d1.magnitude() <= 0 || d2.magnitude() <= 0 ) {
        return BooleanDomain.defaultDomain;
      }
      if ( d1 instanceof AbstractRangeDomain && d2 instanceof AbstractRangeDomain ) {
        AbstractRangeDomain<Object> rd1 = (AbstractRangeDomain<Object>)d1;
        AbstractRangeDomain<Object> rd2 = (AbstractRangeDomain<Object>)d2;
        if (rd1.greaterEquals( rd2 ) == Boolean.TRUE) return BooleanDomain.trueDomain;
        if (rd1.less( rd2 ) == Boolean.TRUE ) return BooleanDomain.falseDomain;
      }
      // TODO -- There are many possibilities here. Instead, define less(),
      // alwaysLess(), etc. methods for domains that take a variety of
      // arguments.
      return BooleanDomain.defaultDomain;
    }

    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.event.Call#restrictDomain(gov.nasa.jpl.ae.solver.Domain, boolean, java.util.Set)
     */
    @Override
    public < TT > Pair<Domain< TT >,Boolean> restrictDomain( Domain< TT > domain,
                                                             boolean propagate,
                                                             Set< HasDomain > seen ) {
      boolean changed = false;
      if ( domain.contains((TT)Boolean.TRUE) && domain.contains((TT)Boolean.FALSE) ) {
        // nothing to do
      } else if ( domain.magnitude() == 1 ) {
        Object v = domain.getValue( propagate );
        if ( v instanceof Boolean ) {
          String oldDom = this.getDomain( propagate, null ).toString();
          changed = restrictDomains( ( (Boolean)v ) == Boolean.TRUE );
          if ( changed ) {
            System.out.println("Restricted " + getName() + " from " + oldDom + " to " + getDomain(propagate, null) );
          }
        }
      }
      return new Pair(this.domain, changed);// || thisChanged);
    }
    
    // REVIEW -- This seems out of place.  Does something else do this?
    public boolean restrictDomains( boolean targetResult ) {
      if ( arguments.size() < 2 ) return false;
      Expression< T > e1 = (Expression< T >)arguments.get( 0 );
      Expression< T > e2 = (Expression< T >)arguments.get( 1 );
      Domain< T > d1 = e1.getDomain( false, null );
      Domain< T > d2 = e2.getDomain( false, null );
      boolean changed = false;
      if ( d1 instanceof AbstractRangeDomain && d2 instanceof AbstractRangeDomain ) {
        AbstractRangeDomain< T > ard1 = (AbstractRangeDomain< T >)d1;
        AbstractRangeDomain< T > ard2 = (AbstractRangeDomain< T >)d2;
        if ( targetResult == true ) {
//          boolean c1 = ard1.restrictTo( ard2.createSubDomainAbove( ard2.getLowerBound(), ard2.isLowerBoundIncluded() ) );
//          boolean c2 = ard2.restrictTo( ard1.createSubDomainBelow( ard1.getUpperBound(), ard1.isUpperBoundIncluded() ) );
//          changed = changed || c1 || c2;
          if ( ard1.lessEquals( ard1.getLowerBound(), ard2.getLowerBound() ) ) {
            boolean c = ard1.setLowerBound( ard2.getLowerBound() );
            if ( !ard2.isLowerBoundIncluded() && ard1.isLowerBoundIncluded() ) {
              excludeLowerBound(ard1);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e1.restrictDomain( ard1, true, null );
              changed = changed || (p.second == Boolean.TRUE);
            }
          }
          if ( ard2.greaterEquals( ard2.getUpperBound(), ard1.getUpperBound() ) ) {
            boolean c = ard2.setUpperBound( ard1.getUpperBound() );
            if ( !ard1.isUpperBoundIncluded() && ard2.isUpperBoundIncluded() ) {
              excludeUpperBound(ard2);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e2.restrictDomain( ard2, true, null );
              changed = changed || (p.second == Boolean.TRUE);
            }
          }
        } else {
//        boolean c1 = ard1.restrictTo( ard2.createSubDomainBelow( ard2.getUpperBound(), false ) );
//        boolean c2 = ard2.restrictTo( ard1.createSubDomainAbove( ard1.getLowerBound(), false ) );
//        changed = changed || c1 || c2;
          if ( ard1.greaterEquals( ard1.getUpperBound(), ard2.getUpperBound() ) ) {
            boolean c = ard1.setUpperBound( ard2.getUpperBound() );
            if ( ard1.isUpperBoundIncluded() ) {
              excludeUpperBound(ard1);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e1.restrictDomain( ard1, true, null );
              changed = changed || (p.second == Boolean.TRUE);
            }
          }
          if ( ard2.lessEquals( ard2.getLowerBound(), ard1.getLowerBound() ) ) {
            boolean c = ard2.setLowerBound( ard1.getLowerBound() );
            if ( ard2.isLowerBoundIncluded() ) {
              excludeLowerBound(ard2);
              c = true;
            }
            if ( c ) {
              Pair< Domain< T >, Boolean > p = e2.restrictDomain( ard2, true, null );
              changed = changed || (p.second == Boolean.TRUE);
            }
          }
        }
      }
      return changed;
    }

  }

  public static class GreaterEquals< T extends Comparable< ? super T > > extends
                                                                         GTE< T > {

    public GreaterEquals( Expression< T > o1, Expression< T > o2 ) {
      super( o1, o2 );
    }

    public GreaterEquals( Object o1, Object o2 ) {
      super( o1, o2 );
    }
  }

  public static class DoesThereExist< T > extends BooleanBinary< T > {
    // REVIEW -- This could extend ForAll or vice versa.

    public DoesThereExist( Variable< T > variable,
                           // Domain<T> d,
                           Expression< Boolean > o ) {
      super( variable, o, "thereExists" ); // TODO -- pickFunctions?
      setMonotonic( Functions.isMonotonic( o ) );
    }

    public DoesThereExist( Variable< T > variable, Collection< T > valueSet,
                           Expression< Boolean > o ) {
      this( variable, o ); // TODO -- pickFunctions?
      variable.setDomain( new ObjectDomain< T >( valueSet ) );
    }

    public DoesThereExist( Collection< T > valueSet, Expression< Boolean > o ) {
      super( new Parameter< T >( "", new ObjectDomain< T >( valueSet ), null ),
             o, "forAll" ); // TODO -- pickFunctions?
      setMonotonic( Functions.isMonotonic( o ) );
    }
    public DoesThereExist( DoesThereExist<T> m) {
      super(m);
    }
    public DoesThereExist<T> clone() {
      return new DoesThereExist<T>(this);
    }
  }  

  public static class ThereExists< T > extends DoesThereExist< T > {

    public ThereExists( Variable< T > variable,
                        // Domain<T> d,
                        Expression< Boolean > o ) {
      super( variable, o );
    }
    public ThereExists( Variable< T > variable,
                   Collection< T > valueSet,
                   Expression< Boolean > o ) {
      super(variable, valueSet, o);
    }
    public ThereExists( Collection< T > valueSet,
                        Expression< Boolean > o ) {
      super(valueSet, o);
    }
  }  

  public static class Exists< T > extends DoesThereExist< T > {

    public Exists( Variable< T > variable,
                   Expression< Boolean > o ) {
      super( variable, o );
    }
    public Exists( Variable< T > variable,
                   Collection< T > valueSet,
                   Expression< Boolean > o ) {
      super( variable, valueSet, o ); // TODO -- pickFunctions?
    }
    public Exists( Collection< T > valueSet,
                   Expression< Boolean > o ) {
      super( valueSet, o ); // TODO -- pickFunctions?
    }

  }  

  public static class ForAll< T > extends BooleanBinary< T > {
    // Collection<?> quantifiedVariables = null;
    // public ForAll( Collection< Variable<?> > variables,
    // Expression< Boolean > o ) {
    // super( variables, o, "forAll" );
    // }
    public ForAll( Variable< T > variable,
                   Expression< Boolean > o ) {
      super( variable, o, "forAll" ); // TODO -- pickFunctions?
      setMonotonic( Functions.isMonotonic( o ) );
    }
    public ForAll( Variable< T > variable,
                   Collection< T > valueSet,
                   Expression< Boolean > o ) {
      this( variable, o ); // TODO -- pickFunctions?
      variable.setDomain( new ObjectDomain<T>(valueSet) );
    }
    public ForAll( Collection< T > valueSet,
                   Expression< Boolean > o ) {
      super( new Parameter<T>("", new ObjectDomain<T>(valueSet), null), o, "forAll" ); // TODO -- pickFunctions?
      setMonotonic( Functions.isMonotonic( o ) );
    }
    public ForAll( ForAll<T> m) {
      super(m);
    }
    public ForAll<T> clone() {
      return new ForAll<T>(this);
    }

  }

  public static <T extends Comparable<T>> Boolean thereExists( Variable<T> variable,
                                                               Expression< Boolean > o ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return !forAll(variable, new Expression<Boolean>( new Not( o ) ) );
  }

  public static <T extends Comparable<T>> Boolean forAll( Variable<T> variable,
                                                          Expression< Boolean > o ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( variable == null ) return null; // TODO -- fix this.  If o is True, doesnt matter if variable is null
    if ( o == null ) return true;  // TODO REVIEW
    
    // If the variable is not in expression, then it doesnt matter what
    // the variable is.  Just evaluate the expression:
    if ( variable instanceof Parameter &&
         !o.hasParameter( (Parameter< T >)variable, true, null ) ) {
      return (Boolean)o.evaluate( false );
    }
    Domain<T> d = variable.getDomain();
    Boolean b = null;
    
    // The variable doesnt have a domain, then just need to evaluate the
    // expression:
    // REVIEW
    if ( d == null || d.magnitude() == 0 ) {
      b = (Boolean)o.evaluate( false );
    }
    
    RangeDomain< T > rd = null;
    if ( b == null && d instanceof RangeDomain ) {
      rd = (RangeDomain<T>)d;
    }
    
    // If the function is monotonic then evaluate expression with values
    // at the range endpoints:
    if ( b == null && isMonotonic( o ) && rd != null) {
      variable.setValue( rd.getLowerBound() );
      if ( !(Boolean)o.evaluate( true ) ) {
        b = false; 
      }
      else {
        variable.setValue( rd.getUpperBound() );
        if ( !(Boolean)o.evaluate( true ) ) {
          b = false;
        }
        else {
          b = true;
        }
      }
    }
    
    // If the range is finite then try every value in the domain:
    if ( b == null && d.magnitude() > 0 && d instanceof AbstractFiniteRangeDomain ) {//!d.isInfinite() ) {
      AbstractFiniteRangeDomain<T> afrd = (AbstractFiniteRangeDomain<T>)d;
      b = true;
      for ( long i=0; i<d.magnitude(); ++i ) {
        variable.setValue( afrd.getNthValue( i ) );
        if ( !(Boolean)o.evaluate( true ) ) {
          b = false; 
          break;
        }
      }
    }
    if ( Debug.isOn() ) Debug.outln( "forAll(" + variable + " in " + d + ", " + o + " = " + b );
    return b;
  }


  public static boolean isMonotonic( Expression< ? > o ) {
    if ( o.form == Form.Function ) {
      if ( ((FunctionCall)o.expression).isMonotonic() ) {
        return true;
      }
    }
    return false;
  }
  
  
  private static boolean expressionsAreOkay( boolean complain,
                                             Expression< ? >... exprs ) {
    if ( exprs == null ) return true;
    for ( Expression<?> expr : exprs ) {
      if ( expr == null || !expr.isGrounded( false, null ) ) {
        if ( complain ) {
          System.err.println( "Expression is not grounded! " + expr );
          
        }
        return false;
      }
    }
    return true;
  }

  /*
  // TODO -- make this work for TimeVarying
  public static < T extends Comparable< ? super T > > Boolean
      lessThan( Expression< T > o1, Expression< T > o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
//    if ( !expressionsAreOkay( complainAboutBadExpressions, o1, o2 ) ) {
////    if ( !o1.isGrounded() || !o2.isGrounded() ) {      
//        return false;
////      }
////      return greaterThan( o2, o1 );
//    }
    if ( o1 == o2 ) return false;
    if ( o1 == null || o2 == null ) return (o2 != null);
    T r1 = o1.evaluate( false );
    T r2 = o2.evaluate( false );
    if ( r1 == r2 ) return false;
    if ( r1 == null || r2 == null ) return (r2 != null);
    boolean b;
    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ||
         r2.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      Number n1 = Expression.evaluate( o1, Number.class, false );
      Number n2 = Expression.evaluate( o2, Number.class, false );      
      b = n1.doubleValue() < n2.doubleValue();
    } else {
      b = r1.compareTo( r2 ) < 0;
    }
    if ( Debug.isOn() ) Debug.outln( o1 + " < " + o2 + " = " + b );
    return b;
  }
  */
  
  public static < T extends Comparable< ? super T > > Object
         lessThan( Expression< T > o1,
                   Expression< T > o2 ) throws IllegalAccessException,
                                        InvocationTargetException,
                                        InstantiationException {
    return compare(o1, o2, Inequality.LT);
  }

  public static < V1, V2 > Object lessThan( V1 o1,
                                            V2 o2 ) throws IllegalAccessException,
                                                    InvocationTargetException,
                                                    InstantiationException {
    return compare( o1, o2, Inequality.LT );
  }
  
  public static < T extends Comparable< ? super T > > Object
         lessThanOrEqual( Expression< T > o1,
                          Expression< T > o2 ) throws IllegalAccessException,
                                               InvocationTargetException,
                                               InstantiationException {
    return compare( o1, o2, Inequality.LTE );
  }

  public static < V1, V2 > Object
         greaterThanOrEqual( V1 o1, V2 o2 ) throws IllegalAccessException,
                                            InvocationTargetException,
                                            InstantiationException {
    return compare( o1, o2, Inequality.GTE );
  }

  public static < T extends Comparable< ? super T > > Object
         greaterThanOrEqual( Expression< T > o1,
                             Expression< T > o2 ) throws IllegalAccessException,
                                                  InvocationTargetException,
                                                  InstantiationException {
    return compare( o1, o2, Inequality.GTE );
  }

  public static < V1, V2 > Object greaterThan( V1 o1,
                                               V2 o2 ) throws IllegalAccessException,
                                                       InvocationTargetException,
                                                       InstantiationException {
    return compare( o1, o2, Inequality.GT );
  }

  public static < T extends Comparable< ? super T > > Object
         greaterThan( Expression< T > o1,
                      Expression< T > o2 ) throws IllegalAccessException,
                                           InvocationTargetException,
                                           InstantiationException {
    return compare( o1, o2, Inequality.GT );
  }

  public static < V1, V2 > Object
         lessThanOrEqual( V1 o1, V2 o2 ) throws IllegalAccessException,
                                         InvocationTargetException,
                                         InstantiationException {
    return compare( o1, o2, Inequality.LTE );
  }
  
  public static Number toNumber( Object o, boolean simple ) {
    if ( o == null ) return null;
    if ( o instanceof Number ) return (Number)o;
    if ( o instanceof String ) return toNumber( (String)o );
    if ( simple ) return null;
    Number n = null;
    try {
      n = Expression.evaluate( o, Number.class, true );
    } catch ( ClassCastException e ) {}
      catch ( IllegalAccessException e ) {}
      catch ( InvocationTargetException e ) {}
      catch ( InstantiationException e ) {}
    return n;
  }
  
  // TODO -- need to be able to return BigDecimal if others fail.
  public static Double toNumber( String s ) {
    if ( Utils.isNullOrEmpty( s ) ) return null;
    try {
      return Double.parseDouble( s );
    } catch ( NumberFormatException e ) {}
      catch ( NullPointerException e ) {}
    return null;
  }

  public static < T extends Comparable< ? super T > > Object
         compare( Expression< T > o1, Expression< T > o2,
                  Inequality i ) throws IllegalAccessException,
                                 InvocationTargetException,
                                 InstantiationException {
    if ( o1 == null && o2 == null ) return null;
    Object r1 = ( o1 == null ? null : o1.evaluate( false ) );
    Object r2 = ( o2 == null ? null : o2.evaluate( false ) );
    return compare( r1, r2, i );
  }

  public static < V1, V2 > Object
         compare( V1 o1, V2 o2, Inequality i ) throws IllegalAccessException,
                                               InvocationTargetException,
                                               InstantiationException {
    Object result = null;
    if ( o1 == null || o2 == null ) result = null;
    else if ( o1 instanceof String || o2 instanceof String ) {
      Number n1 = toNumber( o1, true );
      Number n2 = toNumber( o2, true );
      if ( !( o1 instanceof String ) || n1 != null && !( o2 instanceof String )
           || n2 != null ) {
        if ( o1 instanceof String && n1 != null ) {
          o1 = (V1)n1;
        }
        if ( o2 instanceof String && n2 != null ) {
          o2 = (V2)n2;
        }
        result = TimeVaryingMap.doesInequalityHold( o1, o2, i );
      } else {
        result = TimeVaryingMap.doesInequalityHold( o1.toString(), o2.toString(), i );
        //o1.toString().compareTo( o2.toString() ) <= 0;
      }
    } else {
      Number r1 = null;
      Number r2 = null;
      TimeVaryingMap< ? > map1 = null;
      TimeVaryingMap< ? > map2 = null;

      Pair< Number, TimeVaryingMap< ? > > p1 = numberOrTimeline( o1 );
      r1 = p1.first;
      map1 = p1.second;

      if ( map1 != null ) {
        result = (V1)compare( map1, o2, i );
      } else {
        Pair< Number, TimeVaryingMap< ? > > p2 = numberOrTimeline( o2 );
        r2 = p2.first;
        map2 = p2.second;

        if ( map2 != null ) {
          result = (V1)compare( o1, map2, i );
        }
      }
      if ( result == null ) {
        result = TimeVaryingMap.doesInequalityHold( r1, r2, i );
      }
    }
    if ( Debug.isOn() ) Debug.outln( o1 + " i " + o2 + " = " + result );
    return result;
  }

  
  public static Object and( Expression< ? > o1,
                            Expression< ? > o2 ) throws IllegalAccessException,
                                                 InvocationTargetException,
                                                 InstantiationException {
    return applyBool( o1, o2, BoolOp.AND );
  }
  public static < V1, V2 > Object
         and( V1 o1, V2 o2 ) throws IllegalAccessException,
                             InvocationTargetException,
                             InstantiationException {
    return applyBool( o1, o2, BoolOp.AND );
  }
  
  public static Object or( Expression< ? > o1,
                           Expression< ? > o2 ) throws IllegalAccessException,
                                                InvocationTargetException,
                                                InstantiationException {
    return applyBool( o1, o2, BoolOp.OR );
  }
  public static < V1, V2 > Object
         or( V1 o1, V2 o2 ) throws IllegalAccessException,
                            InvocationTargetException,
                            InstantiationException {
    return applyBool( o1, o2, BoolOp.OR );
  }

  public static Object xor( Expression< ? > o1,
                            Expression< ? > o2 ) throws IllegalAccessException,
                                                InvocationTargetException,
                                                InstantiationException {
    return applyBool( o1, o2, BoolOp.XOR );
  }
  public static < V1, V2 > Object
         xor( V1 o1, V2 o2 ) throws IllegalAccessException,
                             InvocationTargetException,
                             InstantiationException {
    return applyBool( o1, o2, BoolOp.XOR );
  }
  
  public static Object not( Expression< ? > o ) throws IllegalAccessException,
                                                InvocationTargetException,
                                                InstantiationException {
    return applyBool( o, (Expression<?>)null, BoolOp.NOT );
  }
  public static < V > Object
         not( V o ) throws IllegalAccessException,
                             InvocationTargetException,
                             InstantiationException {
    return applyBool( o, null, BoolOp.NOT );
  }

  public static Object
         applyBool( Expression< ? > o1, Expression< ? > o2,
                    BoolOp i ) throws IllegalAccessException,
                               InvocationTargetException,
                               InstantiationException {
    if ( o1 == null && o2 == null ) return null;
    Object r1 = ( o1 == null ? null : o1.evaluate( false ) );
    Object r2 = ( o2 == null ? null : o2.evaluate( false ) );
    return applyBool( r1, r2, i );
  }

  
  public static < V1, V2 > Object applyBool( V1 o1, V2 o2, BoolOp i ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    Object result = null;
    if ( o1 == null || o2 == null ) result = null;
    Boolean r1 = null;
    Boolean r2 = null;
    TimeVaryingMap< ? > map1 = null;
    TimeVaryingMap< ? > map2 = null;

    Pair< Boolean, TimeVaryingMap< ? > > p1 = booleanOrTimeline( o1 );
    r1 = p1.first;
    map1 = p1.second;
    
    if ( map1 != null ) {
      result = (V1)applyBool( map1, o2, i );
    } else {
      Pair< Boolean, TimeVaryingMap< ? > > p2 = booleanOrTimeline( o2 );
      r2 = p2.first;
      map2 = p2.second;

      if ( map2 != null ) {
        result = (V1)applyBool( o1, map2, i );
      }
    }
    if ( result == null ) {
      if ( r1 == null && r2 == null ) return null;
      else result = TimeVaryingMap.applyOp( r1, r2, i );
    }
    if(Debug.isOn())Debug.outln(o1+" i "+o2+" = "+result);
    return result;
  }

  public static TimeVaryingMap< Boolean >
         applyBool( Object o, TimeVaryingMap< ? > tv,
                    BoolOp i ) throws ClassCastException,
                               IllegalAccessException,
                               InvocationTargetException,
                               InstantiationException {
    return applyBool( tv, o, i );
  }

  public static TimeVaryingMap< Boolean >
         applyBool( TimeVaryingMap< ? > tv, Object o,
                    BoolOp i ) throws ClassCastException,
                               IllegalAccessException,
                               InvocationTargetException,
                               InstantiationException {
    if ( tv == null || o == null ) return null;
    Boolean n = null;
    try {
      if ( !(o instanceof TimeVaryingMap ) ) {
        n = Utils.isTrue( o, true );// Expression.evaluate( o, Number.class, false
                                  // );
      }
    } catch ( Throwable t ) {}
    if ( n != null ) return TimeVaryingMap.applyBool( tv, n, false, i );
    TimeVaryingMap< ? > tvm = null;
    try {
      tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
    } catch ( Throwable t ) {}
    if ( tvm != null ) return applyBool( tv, tvm, i );
    return null;
  }

  public static TimeVaryingMap< Boolean >
         applyBool( TimeVaryingMap< ? > tv1, TimeVaryingMap< ? > tv2,
                    BoolOp i ) throws ClassCastException,
                               IllegalAccessException,
                               InvocationTargetException,
                               InstantiationException {
    return TimeVaryingMap.applyBool( tv1, tv2, i );
  }
  /*
  // TODO -- make this work for TimeVarying
  public static < T extends Comparable< ? super T > > Boolean
      lessThanOrEqual( Expression< T > o1, Expression< T > o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
//    if ( !expressionsAreOkay( complainAboutBadExpressions, o1, o2 ) ) {
////    if ( !o1.isGrounded() || !o2.isGrounded() ) {
//        return false;
////      }
////      return greaterThanOrEqual( o2, o1 );
//    }
    if ( o1 == o2 ) return true;
    if ( o1 == null || o2 == null ) return (o1 == null);
    T r1 = o1.evaluate( false );
    T r2 = o2.evaluate( false );
    if ( r1 == r2 ) return true;
    if ( r1 == null || r2 == null ) return (r1 == null);
    boolean b;
    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ||
         r2.getClass().isAssignableFrom( java.lang.Double.class ) ) {
      Number n1 = Expression.evaluate( o1, Number.class, false );
      Number n2 = Expression.evaluate( o2, Number.class, false );      
      b = n1.doubleValue() <= n2.doubleValue();
    } else {
      b = r1.compareTo( r2 ) <= 0;
    }
    if ( Debug.isOn() ) Debug.outln( o1 + " <= " + o2 + " = " + b );
    return b;
  }
*/
//  // TODO -- make this work for TimeVarying
//  public static < T extends Comparable< ? super T > > Boolean
//      greaterThan( Expression< T > o1, Expression< T > o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
////    if ( !expressionsAreOkay( complainAboutBadExpressions, o1, o2 ) ) {
//////    if ( !o1.isGrounded() || !o2.isGrounded() ) {
////        return false;  // TODO -- REVIEW -- throw exception?
//////      }
//////      return lessThan( o2, o1 );
////    }
//    if ( o1 == o2 ) return false;
//    if ( o1 == null || o2 == null ) return (o1 != null);
//    T r1 = o1.evaluate( false );
//    T r2 = o2.evaluate( false );
//    if ( r1 == r2 ) return false;
//    if ( r1 == null || r2 == null ) return (r1 != null);
//    boolean b;
//    if ( r1.getClass().isAssignableFrom( java.lang.Double.class ) ||
//         r2.getClass().isAssignableFrom( java.lang.Double.class ) ) {
//      Number n1 = Expression.evaluate( o1, Number.class, false );
//      Number n2 = Expression.evaluate( o2, Number.class, false );      
//      b = n1.doubleValue() > n2.doubleValue();
//    } else {
//      b = r1.compareTo( r2 ) > 0;
//    }
//    if ( Debug.isOn() ) Debug.outln( o1 + " > " + o2 + " = " + b );
//    return b;
//  }
  
  // HERE!!! TODO
  public static < T > T
      pickGreaterThan( Expression< T > o1, Expression< T > o2 ) {
    return pickGreater( o1, o2, false );
  }

  public static < T > T pickGreaterThanOrEqual( Expression< T > o1,
                                                Expression< T > o2 ) {
    return pickGreater( o1, o2, true );
  }

  public static < T > T pickGreaterThan( Expression< T > o ) {
    return pickGreater( o, false );
  }

  public static < T > T pickGreaterThanOrEqual( Expression< T > o ) {
    return pickGreater( o, true );
  }

  public static < T > T pickGreaterThan( Expression< T > o,
                                         AbstractRangeDomain< T > domain ) {
    return pickGreater( o, domain, false );
  }

  public static
      < T >
      T
      pickGreaterThanOrEqual( Expression< T > o, AbstractRangeDomain< T > domain ) {
    return pickGreater( o, domain, true );
  }

  public static < T > T pickGreater( Expression< T > o,
                                     AbstractRangeDomain< T > domain,
                                     boolean orEquals ) {
    T t = null;
    if ( o == null ) return null;
    T r = null;
    try {
      r = (T)o.evaluate( false );
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
    if ( r == null ) return null;
    t = domain.pickRandomValueGreater( r, orEquals );
    return t;
  }

  public static < T > T pickGreater( Expression< T > o, boolean orEquals ) {
    if ( o == null ) return null;
    Domain< T > d = o.getDomain( false, null );
    if ( d != null && d instanceof AbstractRangeDomain ) {
      Domain< T > dfd = d.getDefaultDomain();
      if ( dfd instanceof AbstractRangeDomain ) {
        return pickGreater( o, (AbstractRangeDomain< T >)dfd, orEquals );
      }
    }
    return null;
  }

  public static < T > T pickGreater( Expression< T > o1, Expression< T > o2,
                                     boolean orEquals ) {
    Domain< T > d = o1.getDomain( false, null );
    if ( d instanceof AbstractRangeDomain ) {
      AbstractRangeDomain< T > ard = (AbstractRangeDomain< T >)d;
      return pickGreater( o2, ard, orEquals );
    }
    return pickGreater( o2, orEquals );
  }

  public static < T > T pickLessThan( Expression< T > o1, Expression< T > o2 ) {
    return pickLess( o1, o2, false );
  }

  public static < T > T pickLessThanOrEqual( Expression< T > o1,
                                             Expression< T > o2 ) {
    return pickLess( o1, o2, true );
  }

  public static < T > T pickLessThan( Expression< T > o ) {
    return pickLess( o, false );
  }

  public static < T > T pickLessThanOrEqual( Expression< T > o ) {
    return pickLess( o, true );
  }

  public static < T > T pickLessThan( Expression< T > o,
                                      AbstractRangeDomain< T > domain ) {
    return pickLess( o, domain, false );
  }

  public static < T > T pickLessThanOrEqual( Expression< T > o,
                                             AbstractRangeDomain< T > domain ) {
    return pickLess( o, domain, true );
  }

  public static < T > T pickLess( Expression< T > o,
                                  AbstractRangeDomain< T > domain,
                                  boolean orEquals ) {
    T t = null;
    if ( o == null ) return null;
    T r = null;
    try {
      r = (T)o.evaluate( false );
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
    if ( r == null ) return null;
    t = domain.pickRandomValueLess( r, orEquals );
    return t;
  }

  public static < T > T pickLess( Expression< T > o, boolean orEquals ) {
    if ( o == null ) return null;
    Domain< T > d = o.getDomain( false, null );
    if ( d != null && d instanceof AbstractRangeDomain ) {
      Domain< T > dfd = d.getDefaultDomain();
      if ( dfd instanceof AbstractRangeDomain ) {
        return pickLess( o, (AbstractRangeDomain< T >)dfd, orEquals );
      }
    }
    return null;
  }

  public static < T > T pickLess( Expression< T > o1, Expression< T > o2,
                                  boolean orEquals ) {
    Domain< T > d = o1.getDomain( false, null );
    if ( d instanceof AbstractRangeDomain ) {
      AbstractRangeDomain< T > ard = (AbstractRangeDomain< T >)d;
      return pickLess( o2, ard, orEquals );
    }
    return pickLess( o2, orEquals );
  }

  // Picking Equals ///////////////////////////////////////////////////////////////////
  
  public static < T > T pickEqualToForward( Expression< T > o1,
                                          Expression< T > o2 ) {
    
    return pickEquals(o1, o2, true);
  }
  
  public static < T > T pickEqualToReverse( Expression< T > o1,
                                           Expression< T > o2 ) {
     
     return pickEquals(o1, o2, false);
   }
  
  public static < T > T pickEquals( Expression< T > o1, Expression< T > o2, boolean forward) {
    
    T t = null;
    
    if ((o1 != null) && (o2 != null)) {
      
      // If we are selection a value for the first arg then evaluate the second arg expression,
      // otherwise due the reverse:
      try {
        t = (T)(forward ? o2.evaluate(false) : o1.evaluate(false));
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
   
    }
    
    return t;
  }
  
  // Picking Not Equals ///////////////////////////////////////////////////////////////////

  public static < T > T pickNotEqualToForward( Expression< T > o1,
                                               Expression< T > o2 ) {
     
    Domain<T> domain = o1.getDomain( false, null );
    return pickNotEquals(o2, domain);
  }
 
  public static < T > T pickNotEqualToReverse( Expression< T > o1,
                                               Expression< T > o2 ) {
    
    Domain<T> domain = o2.getDomain( false, null );
    return pickNotEquals(o1, domain);
  }
   
  public static < T > T pickNotEquals( Expression< T > o, Domain< T > domain) {
    
    if ( o == null ) return null;
    T r = null;
    try {
      r = (T)o.evaluate( false );
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
    if ( r == null ) return null;
    T t = null;
    Object obj = domain.pickRandomValueNotEqual(r);
    try {
      t = (T)obj;
    } catch (ClassCastException e) {
      // ignore
    }
    return t;
  }
  
  // Picking for logical operators

  public static < T > T pickTrue( Object o,
                                  Variable< T > variableForPick  ) {
    Expression<?> e = null;
    if ( o instanceof Expression ) {
      e = (Expression< ? >)o;
    } else {
      e = new Expression(o);
    }
    if ( e.getType() != null && Boolean.class.isAssignableFrom( e.getType() ) ) {
      return pickTrue( (Expression< Boolean >)o, variableForPick );
    }
    return null;
  }
  
  public static < T > T pickTrue( Expression< Boolean > expr,
                                  Variable< T > variableForPick  ) {
    if ( expr == null || expr.expression == null ) return null;
    switch ( expr.getForm() ) {
      case Constructor:
      case Function:
        Call c = (Call)expr.expression;
        // If the expression embeds a SuggestiveFunctionCall, call it.
        if ( c instanceof SuggestiveFunctionCall ) {
          ((SuggestiveFunctionCall)c).pickValue( variableForPick );
        } else {
          // Evaluate the function and see if we can pickFrom the result.
          try {
            Object o = c.evaluate( false );
            return pickTrue( o, variableForPick );
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
      case Parameter:
        // This assumes that the Parameter and the Variable are the same.
        Parameter< Boolean > p = (Parameter< Boolean >)expr.expression;
        if ( variableForPick != null && variableForPick.equals( p )) {
          return (T)(Boolean)true;
        }
        return pickTrue( p.getValueNoPropagate(), variableForPick );
      case Value:
        return pickTrue( expr.expression, variableForPick );
      case None:
        return pickTrue( expr.expression, variableForPick );
      default:
        // TODO -- ERROR -- Bad Form
    };
    return null;
  }
  
  public static boolean pickTrue( Expression< Boolean > o1,
                                  Expression< Boolean > o2 ) {
    return true;
  }
  public static < T > T pickTrue( Expression< Boolean > o1,
                                  Expression< Boolean > o2,
                                  Variable< T > variableForPick ) {
    T t1 = pickTrue( o1, variableForPick );
    T t2 = pickTrue( o1, variableForPick );
    if ( t1 == null ) return t2;
    if ( t2 == null ) return t1;
    return Random.global.nextBoolean() ? t1 : t2;
  }
  
  // Picking Sum (Add/Plus are subtypes of Sum) /////////////////////////////////////////
  // Picking Sub (Minus is subtype of Sub) ////////////////////////////////////////////
  // Picking Times ///////////////////////////////////////////////////////////////////
  // Picking Divide ///////////////////////////////////////////////////////////////////
  // TODO is it okay to pick zero for Divide?
  
  public static < T > T pickValueForward( Expression< T > o1,
                                       Expression< T > o2 ) {
      
    Domain<T> domain = o1.getDomain( false, null );
    return pickRandomValueInDomain(domain);
  }
  
  public static < T > T pickValueReverse( Expression< T > o1,
                                        Expression< T > o2 ) {
     
    Domain<T> domain = o2.getDomain( false, null );
    return pickRandomValueInDomain(domain);
  }
    
  public static < T > T pickRandomValueInDomain(Domain< T > domain) {
     
    T t = null;
    try {
      t = (T)domain.pickRandomValue();
    } catch ( ClassCastException e ) {
      // TODO???
    }
    return t;       
  }

  public static < T, V1, V2 > Object
      equals( Expression< T > o1, Expression< T > o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
//    if ( o1 == o2 ) return true;
//    if ( o1 == null || o2 == null ) return false;
    T r1 = (T)(o1 == null ? null : o1.evaluate( false ));
    T r2 = (T)(o2 == null ? null : o2.evaluate( false ));
    if ( r1 == r2 ) return true;
    if ( r1 == null || r2 == null ) return false;
    Pair< Object, TimeVaryingMap< ? > > p1 = objectOrTimeline( r1 );
    Pair< Object, TimeVaryingMap< ? > > p2 = objectOrTimeline( r2 );
    TimeVaryingMap<?> tvm1 = p1 == null ? null : p1.second;
    TimeVaryingMap<?> tvm2 = p2 == null ? null : p2.second;
    if ( tvm1 != null ) {
      if ( tvm2 != null ) {
        return TimeVaryingMap.compare(tvm1, tvm2, Inequality.EQ);
      }
      return compare(tvm1, r2, Inequality.EQ);
    } else if ( tvm2 != null ) {
      return compare(r1, tvm2, Inequality.EQ);
    }
    return eq(r1, r2);
  }
  
  protected static <T> Boolean eq( T r1, T r2 ) {    
    if ( Expression.valuesEqual( r1, r2, null, true, true )) return true;
    if ( Utils.valuesLooselyEqual( r1, r2, true ) ) return true;
    if ( r1 == null || r2 == null ) return false;
    boolean b = false;
    b = CompareUtils.compare( r1, r2, false ) == 0;
    if ( !b ) {
      boolean isNum1 = r1 instanceof Number;
      boolean isNum2 = r2 instanceof Number;
      if (isNum1 && isNum2) {
        b = DoubleDomain.defaultDomain.equals( ((Number)r1).doubleValue(), ((Number)r2).doubleValue());
      }
    }
    if ( !b && r1 instanceof Comparable ) {
      if ( r1 instanceof Parameter && !( r2 instanceof Parameter ) ) {
        b = ((Parameter<?>)r1).valueEquals( r2 );
      } else
      if ( r2 instanceof Parameter && !( r1 instanceof Parameter ) ) {
        b = ((Parameter<?>)r2).valueEquals( r1 );
      } else {
        if ( r2 == null ) b = false;
        else {
          try {
            b = ( (Comparable<T>)r1 ).compareTo( r2 ) == 0;
          } catch ( Throwable t ) { 
            b = false;
          }
        }
      }
    } else {
      b = b || r1.equals( r2 );
    }
    if ( !b ) {
      Object r11 = null;
      Object r22 = null;
      boolean changed1 = false;
      boolean changed2 = false;
      if ( r1 instanceof Wraps ) {
        r11 = ((Wraps<?>)r1).getValue( false );
        changed1 = !r1.equals( r11 );
        if ( changed1 ) b = eq( r11, r2 );
      }
      if ( !b && r2 instanceof Wraps ) {
        r22 = ((Wraps<?>)r2).getValue( false );
        changed2 = !r2.equals( r22 );
        if ( changed2 ) b = eq( r22, r1 );
        if ( !b && r11 != null ) {
          if ( changed1 && changed2 ) {
            b = eq( r11, r22 );
          }
        }
      }
    }
    if ( Debug.isOn() ) Debug.outln( "eq(): " + r1 + " == " + r2 + " = " + b );
    return b;
  }
  
  public static < T > Object
      notEquals( Expression< T > o1, Expression< T > o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    T r1 = (T)(o1 == null ? null : o1.evaluate( false ));
    T r2 = (T)(o2 == null ? null : o2.evaluate( false ));
    Pair< Object, TimeVaryingMap< ? > > p1 = objectOrTimeline( r1 );
    Pair< Object, TimeVaryingMap< ? > > p2 = objectOrTimeline( r2 );
    TimeVaryingMap<?> tvm1 = p1 == null ? null : p1.second;
    TimeVaryingMap<?> tvm2 = p2 == null ? null : p2.second;
    if ( tvm1 != null ) {
      if ( tvm2 != null ) {
        return TimeVaryingMap.compare(tvm1, tvm2, Inequality.NEQ);
      }
      return compare(tvm1, r2, Inequality.NEQ);
    } else if ( tvm2 != null ) {
      return compare(r1, tvm2, Inequality.NEQ);
    }
    Boolean b = !eq( o1, o2 );
    if ( Debug.isOn() ) Debug.outln( o1 + " != " + o2 + " = " + b );
    return b;
  }

  // Logic functions
  
  public static class And extends BooleanBinary< Boolean > {
    public And( Expression< Boolean > o1, Expression< Boolean > o2 ) {
      super( o1, o2, "and", "pickTrue", "pickTrue" );
      setMonotonic( true );
    }
    public And( Expression< Boolean > o1, FunctionCall o2 ) {
      super( o1, o2, "and", "pickTrue", "pickTrue" );
      setMonotonic( true );
    }
    public And(  FunctionCall o2, Expression< Boolean > o1 ) {
      super( o1, o2, "and", "pickTrue", "pickTrue" );
      setMonotonic( true );
    }
    public And( And a ) {
      super(a);
    }
    public And clone() {
      return new And(this);
    }
    /**
     * The inverse for And must evaluate the argument to the specified
     * returnValue, so we want the argument to be the same as the returnValue.
     * Thus, Equals is the inverse.
     * 
     * @see gov.nasa.jpl.ae.event.Functions.SuggestiveFunctionCall#inverseSingleValue(java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public //< T1  extends Comparable< ? super T1 > > 
    FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      
      if ( arguments == null || arguments.size() != 2 ) return null;
      //Object otherArg = ( arg == arguments.get( 1 ) ? arguments.get( 0 ) : arguments.get( 1 ) );
      if ( returnValue == null // || otherArg == null 
          ) return null; // arg can be null!
      return new Equals< Boolean >( forceExpression( returnValue ),
                                    forceExpression( arg ) );
    }

  }
  // REVIEW -- Should a propagate flag be added?  Currently false.
  /*
  public static Object and(Expression<Boolean> o1, Expression<Boolean> o2) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null && o2 == null ) return null;
    Object r1 = (o1 == null ? null : o1.evaluate( false ));
    Object r2 = (o2 == null ? null : o2.evaluate( false ));
    return and( r1, r2 );
  }

  public static < V1, V2 > Object
         and( V1 o1, V2 o2 ) throws IllegalAccessException,
                             InvocationTargetException, InstantiationException {
    Object result = null;
    if ( o1 == null || o2 == null ) result = null;
    else if ( o1 instanceof String || o2 instanceof String ) {
      Boolean b1 = Utils.isTrue( o1, true );
      Boolean b2 = Utils.isTrue( o2, true );
      result = and( b1, b2 );
    } else {
      Boolean r1 = null;
      Boolean r2 = null;
      TimeVaryingMap< ? > map1 = null;
      TimeVaryingMap< ? > map2 = null;

      Pair< Boolean, TimeVaryingMap< ? > > p1 = booleanOrTimeline( o1 );
      r1 = p1.first;
      map1 = p1.second;

      if ( map1 != null ) {
        result = (V1)and( map1, o2 );
      } else {
        Pair< Boolean, TimeVaryingMap< ? > > p2 = booleanOrTimeline( o2 );
        r2 = p2.first;
        map2 = p2.second;

        if ( map2 != null ) {
          result = (V1)and( o1, map2 );
        }
      }
      if ( result == null ) {
        if ( ( r1 != null && !r1 ) || ( r2 != null && !r2 ) ) result = false;
        else if ( r1 == null || r2 == null ) result = null;
        else result = r1 && r2;
      }
    }
    if ( Debug.isOn() ) Debug.outln( o1 + " && " + o2 + " = " + result );
    return result;
  }
*/
  
  public static class Or extends BooleanBinary< Boolean > {
    public Or( Expression< Boolean > o1, Expression< Boolean > o2 ) {
      super( o1, o2, "or", "pickTrue", "pickTrue" );
      setMonotonic( true );
    }
    public Or( Expression< Boolean > o1, FunctionCall o2 ) {
      super( o1, o2, "or", "pickTrue", "pickTrue" );
      setMonotonic( true );
    }
    public Or( Or a ) {
      super(a);
    }
    public Or clone() {
      return new Or(this);
    }
    /* (non-Javadoc)
     * @see gov.nasa.jpl.ae.event.Expression#isGrounded(boolean, java.util.Set)
     */
    @Override
    public boolean isGrounded(boolean deep, Set< Groundable > seen) {
      try {
        return evaluate( false ) != null;
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return false;
    }
    
    /**
     * The inverse for Or must evaluate the argument to the specified
     * returnValue, so we want the argument to be the same as the returnValue.
     * Thus, Equals is the inverse.
     * 
     * @see gov.nasa.jpl.ae.event.Functions.SuggestiveFunctionCall#inverseSingleValue(java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public //< T1  extends Comparable< ? super T1 > > 
    FunctionCall inverseSingleValue( Object returnValue, Object arg ) {
      
      if ( arguments == null || arguments.size() != 2 ) return null;
      //Object otherArg = ( arg == arguments.get( 1 ) ? arguments.get( 0 ) : arguments.get( 1 ) );
      if ( returnValue == null // || otherArg == null 
          ) return null; // arg can be null!
      return new Equals< Boolean >( forceExpression( returnValue ),
                                    forceExpression( arg ) );
    }

  }
  
  /*
  public static Object or(Expression<Boolean> o1, Expression<Boolean> o2) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null && o2 == null ) return null;
    Object r1 = (o1 == null ? null : o1.evaluate( false ));
    Object r2 = (o2 == null ? null : o2.evaluate( false ));
    return or( r1, r2 );
  }

  
  public static < V1, V2 > Object
         or( V1 o1, V2 o2 ) throws IllegalAccessException,
                            InvocationTargetException, InstantiationException {
    Object result = null;
    if ( o1 == null || o2 == null ) result = null;
    else if ( o1 instanceof String || o2 instanceof String ) {
      Boolean b1 = Utils.isTrue( o1, true );
      Boolean b2 = Utils.isTrue( o2, true );
      result = or( b1, b2 );
    } else {
      Boolean r1 = null;
      Boolean r2 = null;
      TimeVaryingMap< ? > map1 = null;
      TimeVaryingMap< ? > map2 = null;

      Pair< Boolean, TimeVaryingMap< ? > > p1 = booleanOrTimeline( o1 );
      r1 = p1.first;
      map1 = p1.second;

      if ( map1 != null ) {
        result = (V1)or( map1, o2 );
      } else {
        Pair< Boolean, TimeVaryingMap< ? > > p2 = booleanOrTimeline( o2 );
        r2 = p2.first;
        map2 = p2.second;

        if ( map2 != null ) {
          result = (V1)or( o1, map2 );
        }
      }
      if ( result == null ) {
        if ( ( r1 != null && r1 ) || ( r2 != null && r2 ) ) result = true;
        else if ( r1 == null || r2 == null ) result = null;
        else result = r1 || r2;
      }
    }
    if ( Debug.isOn() ) Debug.outln( o1 + " || " + o2 + " = " + result );
    return result;
  }
  */

  public static class Xor extends BooleanBinary< Boolean > {
    public Xor( Expression< Boolean > o1, Expression< Boolean > o2 ) {
      super( o1, o2, "xor" );
    }
    public Xor( Expression< Boolean > o1, FunctionCall o2 ) {
      super( o1, o2, "xor" );
    }
    public Xor( Xor a ) {
      super(a);
    }
    public Xor clone() {
      return new Xor(this);
    }

  }
//  public static Boolean
//      xor( Expression< Boolean > o1, Expression< Boolean > o2 ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
//    if ( o1 == null || o2 == null ) return null;
//    Boolean r1 = (Boolean)o1.evaluate( false );
//    Boolean r2 = (Boolean)o2.evaluate( false );
//    if ( r1 == null || r2 == null ) return null;
//    boolean b = ( r1 ^ r2 );
//    if ( Debug.isOn() ) Debug.outln( o1 + " ^ " + o2 + " = " + b );
//    return b;
//  }
  /*
  public static Object xor(Expression<Boolean> o1, Expression<Boolean> o2) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o1 == null && o2 == null ) return null;
    Object r1 = (o1 == null ? null : o1.evaluate( false ));
    Object r2 = (o2 == null ? null : o2.evaluate( false ));
    return xor( r1, r2 );
  }

  
  public static < V1, V2 > Object
         xor( V1 o1, V2 o2 ) throws IllegalAccessException,
                            InvocationTargetException, InstantiationException {
    Object result = null;
    if ( o1 == null || o2 == null ) result = null;
    else if ( o1 instanceof String || o2 instanceof String ) {
      Boolean b1 = Utils.isTrue( o1, true );
      Boolean b2 = Utils.isTrue( o2, true );
      result = xor( b1, b2 );
    } else {
      Boolean r1 = null;
      Boolean r2 = null;
      TimeVaryingMap< ? > map1 = null;
      TimeVaryingMap< ? > map2 = null;

      Pair< Boolean, TimeVaryingMap< ? > > p1 = booleanOrTimeline( o1 );
      r1 = p1.first;
      map1 = p1.second;

      if ( map1 != null ) {
        result = (V1)xor( map1, o2 );
      } else {
        Pair< Boolean, TimeVaryingMap< ? > > p2 = booleanOrTimeline( o2 );
        r2 = p2.first;
        map2 = p2.second;

        if ( map2 != null ) {
          result = (V1)xor( o1, map2 );
        }
      }
      if ( result == null ) {
        if ( r1 != null && r2 != null ) {
          result = r1 ^ r2;
        } else result = null;
      }
    }
    if ( Debug.isOn() ) Debug.outln( o1 + " ^ " + o2 + " = " + result );
    return result;
  }
*/


  public static class Not extends Unary< Boolean, Boolean > implements Suggester {
    public Not( Expression< Boolean > o ) {
      super( o, "not" );
      setMonotonic( true );
    }
    public Not( Not a ) {
      super(a);
    }
    public Not clone() {
      return new Not(this);
    }

    @Override
    public < T > T pickValue( Variable< T > variable ) {
      Object arg = //((FunctionCall)this.expression).
          getArgument( 0 );
      if ( arg == variable ) {
        return (T)(Boolean)false;
      }
      return pickValueE( variable, arg );
//      else if ( arg instanceof Suggester ) {
//        return (T)(Boolean)(!((Boolean)((Suggester)arg).pickValue( variable )));
//      }
//      return null;
    }
  }

//  public static Boolean not( Expression< Boolean > o ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
//    if ( o == null ) return null;
//    Boolean r = (Boolean)o.evaluate( false );
//    if ( r == null ) return null;
//    boolean b = !r;
//    if ( Debug.isOn() ) Debug.outln( "!" + o + " = " + b );
//    return b;
//  }

  /*
  public static Object not(Expression<Boolean> o) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( o == null ) return null;
    Object r = (o == null ? null : o.evaluate( false ));
    return not( r );
  }

  
  public static < V > Object
         not( V o ) throws IllegalAccessException,
                            InvocationTargetException, InstantiationException {
    Object result = null;
    if ( o == null ) result = null;
    else if ( o instanceof String ) {
      Boolean b = Utils.isTrue( o, true );
      result = not( b );
    } else {
      Boolean r = null;
      TimeVaryingMap< ? > map = null;

      Pair< Boolean, TimeVaryingMap< ? > > p = booleanOrTimeline( o );
      r = p.first;
      map = p.second;

      if ( map != null ) {
        result = (V)not( map );
      }
      if ( result == null ) {
        if ( r != null ) result = !r;
      }
    }
    if ( Debug.isOn() ) Debug.outln( "!" + o + " = " + result );
    return result;
  }

*/
  
  // TimeVaryingMap functions

  public static < T > TimeVaryingMap< Boolean >
         compare( Object o, TimeVaryingMap< T > tv,
                  Inequality i ) throws ClassCastException,
                                 IllegalAccessException,
                                 InvocationTargetException,
                                 InstantiationException {
    return compare( tv, o, i );
  }

  public static < T > TimeVaryingMap< Boolean >
         compare( TimeVaryingMap< T > tv, Object o,
                  Inequality i ) throws ClassCastException,
                                 IllegalAccessException,
                                 InvocationTargetException,
                                 InstantiationException {
    if ( tv == null || o == null ) return null;
    Number n = null;
    try {
      n = toNumber( o, false );// Expression.evaluate( o, Number.class, false );
    } catch ( Throwable t ) {}
    if ( n != null ) return TimeVaryingMap.compare( tv, n, false, i );
    TimeVaryingMap< ? extends Number > tvm = null;
    try {
      tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
    } catch ( Throwable t ) {}
    if ( tvm != null ) return compare( tv, tvm, i );
    return TimeVaryingMap.compare( tv, o, false, i );
  }

  public static < T, TT extends Number > TimeVaryingMap< Boolean >
         compare( TimeVaryingMap< T > tv1, TimeVaryingMap< TT > tv2,
                  Inequality i ) throws ClassCastException,
                                 IllegalAccessException,
                                 InvocationTargetException,
                                 InstantiationException {
    return TimeVaryingMap.compare( tv1, tv2, i );
  }

  public static < T > TimeVaryingMap< Boolean >
         lessThan( Object o, TimeVaryingMap< T > tv ) throws ClassCastException,
                                                      IllegalAccessException,
                                                      InvocationTargetException,
                                                      InstantiationException {
    return compare(o, tv, Inequality.LT);
  }

  public static < T > TimeVaryingMap< Boolean >
         lessThan( TimeVaryingMap< T > tv, Object o ) throws ClassCastException,
                                                      IllegalAccessException,
                                                      InvocationTargetException,
                                                      InstantiationException {
    return compare(tv, o, Inequality.LT);
//    if ( tv == null || o == null ) return null;
//    Pair< Number, TimeVaryingMap< ? > > p = numberOrTimeline( o );
//    if ( tvm != null ) return lessThan( tv, tvm );
//    
//    Number n = p.first;
//    TimeVaryingMap<?> tvm = p.second;
//    try {
//      n = toNumber( o, false );// Expression.evaluate( o, Number.class, false );
//    } catch ( Throwable t ) {}
//    if ( n != null ) return tv.lessThanClone( n );
//    TimeVaryingMap< ? extends Number > tvm = null;
//    try {
//      tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
//    } catch ( Throwable t ) {}
//    return null;
  }

  public static < T, TT extends Number > TimeVaryingMap< Boolean >
         lessThan( TimeVaryingMap< T > tv1,
                   TimeVaryingMap< TT > tv2 ) throws ClassCastException,
                                              IllegalAccessException,
                                              InvocationTargetException,
                                              InstantiationException {
    return compare( tv1, tv2, Inequality.LT );
  }

  public static < T > TimeVaryingMap< Boolean >
         lessThanOrEqual( Object o,
                          TimeVaryingMap< T > tv ) throws ClassCastException,
                                                   IllegalAccessException,
                                                   InvocationTargetException,
                                                   InstantiationException {
    return compare(o, tv, Inequality.LTE);
  }

  public static < T > TimeVaryingMap< Boolean >
         lessThanOrEqual( TimeVaryingMap< T > tv,
                          Object o ) throws ClassCastException,
                                     IllegalAccessException,
                                     InvocationTargetException,
                                     InstantiationException {
    return compare(tv, o, Inequality.LT);
//    if ( tv == null || o == null ) return null;
//    Number n = null;
//    try {
//      n = toNumber( o, false );// Expression.evaluate( o, Number.class, false );
//    } catch ( Throwable t ) {}
//    if ( n != null ) return tv.lessThanOrEqualClone( n );
//    TimeVaryingMap< ? extends Number > tvm = null;
//    try {
//      tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
//    } catch ( Throwable t ) {}
//    if ( tvm != null ) return lessThanOrEqual( tv, tvm );
//    return null;
  }

  public static < T, TT extends Number > TimeVaryingMap< Boolean >
         lessThanOrEqual( TimeVaryingMap< T > tv1,
                          TimeVaryingMap< TT > tv2 ) throws ClassCastException,
                                                     IllegalAccessException,
                                                     InvocationTargetException,
                                                     InstantiationException {
    return compare( tv1, tv2, Inequality.LTE );
  }

  public static < T > TimeVaryingMap< T > min( Object o,
                                               TimeVaryingMap< T > tv ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return min( tv, o );
  }
  public static < T > TimeVaryingMap< T > min( TimeVaryingMap< T > tv,
                                               Object o ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tv == null || o == null ) return null;
    Number n = null;
    try {
      n = Expression.evaluate( o, Number.class, false );
    } catch( Throwable t ) {}
    if ( n != null ) return tv.minClone( n );
    TimeVaryingMap< ? extends Number > tvm = null;
    try {
      tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
    } catch (Throwable t) {}
    if ( tvm != null ) return min( tv, tvm );
    return null;
  }   
  public static < T, TT extends Number > TimeVaryingMap< T > min( TimeVaryingMap< T > tv1,
                                                                  TimeVaryingMap< TT > tv2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return TimeVaryingMap.min( tv1, tv2 );
  }
  
  public static < T > TimeVaryingMap< T > max( Object o,
                                               TimeVaryingMap< T > tv ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return max( tv, o );
  }
  public static < T > TimeVaryingMap< T > max( TimeVaryingMap< T > tv,
                                               Object o ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tv == null || o == null ) return null;
    Number n = null;
    try {
      n = Expression.evaluate( o, Number.class, false );
    } catch( Throwable t ) {}
    if ( n != null ) return tv.maxClone( n );
    TimeVaryingMap< ? extends Number > tvm = null;
    try {
      tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
    } catch (Throwable t) {}
    if ( tvm != null ) return max( tv, tvm );
    return null;
  }   
  public static < T, TT extends Number > TimeVaryingMap< T > max( TimeVaryingMap< T > tv1,
                                                                  TimeVaryingMap< TT > tv2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return TimeVaryingMap.max( tv1, tv2 );
  }
  

  public static < L, T > TimeVaryingMap< L > argminormax( L l1, Object o,
                                                          L l2, TimeVaryingMap< T > tv,
                                                          boolean isMin) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return argminormax(l2, tv, l1, o, isMin);
  }
  
  public static < L, T > TimeVaryingMap< L > argminormax( L l1, TimeVaryingMap< T > tv,
                                                          L l2, Object o,
                                                          boolean isMin ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tv == null || o == null ) return null;
    Number n = null;
    try {
      n = Expression.evaluate( o, Number.class, false );
    } catch( Throwable t ) {}
    if ( n != null ) return TimeVaryingMap.argminormax( l1, tv, l2, n, isMin );
    TimeVaryingMap< ? extends Number > tvm = null;
    try {
      tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
    } catch (Throwable t) {}
    if ( tvm != null ) return TimeVaryingMap.argminormax( l1, tv, l2, tvm, isMin );
    return null;
  }
  
  public static < L, T, TT extends Number > TimeVaryingMap< L > argmax( L l1, TimeVaryingMap< T > tv1,
                                                                        L l2, TimeVaryingMap< TT > tv2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return TimeVaryingMap.argmax( l1, tv1, l2, tv2 );
  }

  
//  public static < T > TimeVaryingMap< T > times( Object o1,
//                                                 Object o2 ) {
//    if ( o1 == null || o2 == null ) return null;
//    Number n = Expression.evaluate( o1, Number.class, false );
//    if ( n != null ) return times( n, o2 );
//    TimeVaryingMap< ? extends Number > tvm =
//        Expression.evaluate( o, TimeVaryingMap.class, false );
//    if ( tvm != null ) return times( tv, tvm );
//    return null;
//  }
  public static < T > TimeVaryingMap< T > times( Object o,
                                                 TimeVaryingMap< T > tv ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return times(tv, o);
  }
  
  public static < T > TimeVaryingMap< T > times( TimeVaryingMap< T > tv,
                                                 Object o ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Pair< Number, TimeVaryingMap< ? > > p = numberOrTimeline( o );
    Number n = p.first;
    TimeVaryingMap< ? extends Number > tvm = (TimeVaryingMap< ? extends Number >)p.second;
    if ( n != null ) return tv.times( n );
    if ( tvm != null ) return times( tv, tvm );
    return null;
  }
  
  public static < T, TT extends Number > TimeVaryingMap< T > times( TimeVaryingMap< T > tv1,
                                                                    TimeVaryingMap< TT > tv2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return TimeVaryingMap.times( tv1, tv2 );
  }


  
  
  public static < T extends Number > TimeVaryingMap< T > pow( Object o,
                                                              TimeVaryingMap< T > tv ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tv == null || o == null ) return null;
    Number n = null;
    try {
      n = Expression.evaluate( o, Number.class, false );
    } catch ( Throwable e ) {
      // ignore
    }
    if ( n != null ) return tv.npow( n );
    TimeVaryingMap< ? extends Number > tvm = null;
    try {
        tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
    } catch (  Throwable e ) {
      // ignore
    }
    if ( tvm != null ) return (TimeVaryingMap< T >)pow( tvm, tv );
    return null;
  }
  public static < T extends Number > TimeVaryingMap< T > pow( TimeVaryingMap< T > tv,
                                                              Object o ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tv == null || o == null ) return null;
    Number n = null;
    try {
      n = Expression.evaluate( o, Number.class, false );
    } catch ( Throwable e ) {
      // ignore
    }
    if ( n != null ) return tv.pow( n );
    TimeVaryingMap< ? extends Number > tvm = null;
    try {
        tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
    } catch (  Throwable e ) {
      // ignore
    }
    if ( tvm != null ) return pow( tv, tvm );
    return null;
  }
  
  public static < T extends Number, TT extends Number > TimeVaryingMap< T > pow( TimeVaryingMap< T > tv1,
                                                                                 TimeVaryingMap< TT > tv2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return TimeVaryingMap.pow( tv1, tv2 );
  }


  
  public static < T > TimeVaryingMap< T > divide( Object o, TimeVaryingMap< T > tv ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tv == null || o == null ) return null;
    Number n = null;
    try {
      n = Expression.evaluate( o, Number.class, false );
    } catch ( Throwable e ) {
      // ignore
    }
    if ( n != null ) return TimeVaryingMap.dividedBy( n, tv );
    TimeVaryingMap< T > tvm = null;
    try {
        tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
    } catch ( Throwable e ) {
      // ignore
    }
    if ( tvm != null ) return divideMap( tvm, tv );
    return null;
  }
  public static < T > TimeVaryingMap< T > divide( TimeVaryingMap< T > tv,
                                                  Object o ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tv == null || o == null ) return null;
    Number n = null;
    try {
      n = Expression.evaluate( o, Number.class, false );
    } catch ( Throwable e ) {
      // ignore
    }
    if ( n != null ) return tv.dividedBy( n );
    TimeVaryingMap< ? extends Number > tvm = null;
    try {
        tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
    } catch ( Throwable e ) {
      // ignore
    }
    if ( tvm != null ) return divideMap( tv, tvm );
    return null;
  }
  public static < T, TT > TimeVaryingMap< T > divideMap( TimeVaryingMap< T > tv1,
                                                         TimeVaryingMap< TT > tv2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return TimeVaryingMap.dividedBy( tv1, tv2 );
  }

  
  public static < T > TimeVaryingMap< T > plus( Object o,
                                                TimeVaryingMap< T > tv ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return plus( tv, o );
  }
  public static < T > TimeVaryingMap< T > plus( TimeVaryingMap< T > tv,
                                                Object o ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tv == null || o == null ) return null;
    Number n = null;
    try {
      n = Expression.evaluate( o, Number.class, false );
    } catch( Throwable t ) {}
    if ( n != null ) return tv.plus( n );
    TimeVaryingMap< ? extends Number > tvm = null;
    try {
      tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
    } catch (Throwable t) {}
    if ( tvm != null ) return plus( tv, tvm );
    return null;
  }   
  public static < T, TT extends Number > TimeVaryingMap< T > plus( TimeVaryingMap< T > tv1,
                                                                   TimeVaryingMap< TT > tv2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
     return TimeVaryingMap.plus( tv1, tv2 );
   }
  
  public static < T > TimeVaryingMap< T > minus( Object o,
                                                 TimeVaryingMap< T > tv ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return minus( tv, o );
  }
  public static < T > TimeVaryingMap< T > minus( TimeVaryingMap< T > tv,
                                                 Object o ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if ( tv == null || o == null ) return null;
    Number n = null;
    try {
      n = Expression.evaluate( o, Number.class, false );
    } catch( Throwable t ) {}
    if ( n != null ) return tv.minus( n );
    TimeVaryingMap< ? extends Number > tvm = null;
    try {
        tvm = Expression.evaluate( o, TimeVaryingMap.class, false );
    } catch ( Throwable t ) {}
    if ( tvm != null ) return minus( tv, tvm );
    return null;
  }   
  public static < T, TT extends Number > TimeVaryingMap< T > minus( TimeVaryingMap< T > tv1,
                                                                    TimeVaryingMap< TT > tv2 ) throws ClassCastException, IllegalAccessException, InvocationTargetException, InstantiationException {
     return TimeVaryingMap.minus( tv1, tv2 );
   }
  
  public static <T1> T1 pickValueE( Variable< T1 > variable,
                                        Object object ) {
    if ( object instanceof Suggester ) {
      return (T1)((Suggester)object).pickValue( variable );
    } else if ( object instanceof Expression && 
                ((Expression<?>)object).expression instanceof Suggester ) {
      return (T1)((Suggester)((Expression<?>)object).expression).pickValue( variable );
    }
    return null;
  }

  protected static <T, T1> T1 pickValueBB2( BooleanBinary< T > booleanBinary,
                                            Variable< T1 > variable ) {
    T1 newValue = pickValueBF2( variable, booleanBinary.//functionCall.
                                pickFunctionCall,
                                booleanBinary.//functionCall.
                                reversePickFunctionCall );
    return newValue;
  }
  protected static <T1> T1 pickValueBF2( Variable< T1 > variable,
                                         FunctionCall pickFunctionCall ) {
    try {
      return (T1)pickFunctionCall.evaluate( false );
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
  protected static <T1> T1 pickValueBF2( SuggestiveFunctionCall functionCall,
                                         Variable< T1 > variable ) {
    T1 newValue = pickValueBF2( variable, functionCall.pickFunctionCall,
                                functionCall.reversePickFunctionCall );
    return newValue;
  }
  
  public static Object getArgumentWithVariable( FunctionCall fCall,
                                                Variable<?> variable,
                                                boolean mustBeOnlyOne ) {
    ArrayList< Object > list = getArgumentsWithVariable( fCall, variable, true,
                                                         mustBeOnlyOne );
    if ( Utils.isNullOrEmpty( list ) ) return null;
    return list.get( 0 );
  }
  public static ArrayList< Object > getArgumentsWithVariable( FunctionCall fCall,
                                                              Variable<?> variable ) {
    ArrayList< Object > list = getArgumentsWithVariable( fCall, variable, false, false );
    return list;
  }
  public static ArrayList< Object > getArgumentsWithVariable( FunctionCall fCall,
                                                              Variable<?> variable,
                                                              boolean returnOnlyOne,
                                                              boolean mustBeOnlyOne ) {
    if ( fCall == null || variable == null ) return null;
    Vector< Object > arguments = fCall.getArgumentVector();
    ArrayList< Object > argsWithVariable = new ArrayList<Object>();
    if ( variable instanceof Parameter ) {
      for ( Object arg : arguments ) {
        if ( arg == null ) continue;
        if ( Expression.valuesEqual( variable, arg, Parameter.class ) ||
             ( arg instanceof HasParameters && 
               ((HasParameters)arg).hasParameter( (Parameter< ? >)variable,
                                                  true, null ) ) ) {
          argsWithVariable.add( arg );
          if ( returnOnlyOne && !mustBeOnlyOne ) {
            return argsWithVariable;
          }
          if ( mustBeOnlyOne && !argsWithVariable.isEmpty() ) {
            return null;
          }
        }
      }
    }
    return argsWithVariable;
  }
  
  protected static SuggestiveFunctionCall getSuggestiveFunctionCall( Object o ) {
    SuggestiveFunctionCall call = null;
    
    try {
      call = Expression.evaluate( o, SuggestiveFunctionCall.class, true );
    } catch ( ClassCastException e ) {
    } catch ( IllegalAccessException e ) {
    } catch ( InvocationTargetException e ) {
    } catch ( InstantiationException e ) {
    }
    
    return call;
  }
  
  /**
   * Pick a value for the variable in the context of a binary function using pickFunctionCall if variable is in the expression of the first argument to the binary function or reversePickFunctionCall if in the expression of the second argument.
   * @param variable
   * @param pickFunctionCall
   * @param reversePickFunctionCall
   * @return
   */
  protected static <T1> T1 pickValueBF2( Variable< T1 > variable,
                                         FunctionCall pickFunctionCall,
                                         FunctionCall reversePickFunctionCall ) {
    // check for valid input
    if ( variable == null ) return null;
    if ( pickFunctionCall == null && reversePickFunctionCall == null ) return null;
    if (!( variable instanceof Parameter )  ) {
      Debug.error( false,
                   "Unfortunately, pickValueBF2() depends on variable being a Parameter! "
                       + variable );
      return null;
    }
    Parameter< T1 > variableParam = (Parameter< T1 >)variable;

    // get the arguments of the binary function, assumed to be the same as the
    // arguments of the pick functions
    if ( pickFunctionCall == null && reversePickFunctionCall == null ) return null;
    Vector< Object > args =
        ( pickFunctionCall == null ) ? reversePickFunctionCall.getArgumentVector()
                                     : pickFunctionCall.getArgumentVector();
    assert( args.size() == 2 );
    if ( args.size() != 2 ) return null;
    Object arg1 = args.get( 0 );
    Object arg2 = args.get( 1 );
    Expression< T1 > o1 = null;
    Expression< T1 > o2 = null;
    if ( arg1 instanceof Expression ) {
      o1 = (Expression< T1 >)arg1;
    }
    if ( arg2 instanceof Expression ) {
      o2 = (Expression< T1 >)arg2;
    }

    // choose the argument as the context for which a value is picked for the variable 
    // This was changed to use Utils.valuesEqual(), but we dont know why...
//    boolean isFirst = o1 != null && Utils.valuesEqual( variable, o1 );
//    boolean isSecond = o2 != null && Utils.valuesEqual( variable, o2 );
    boolean isFirst = o1 != null && Expression.valuesEqual( variableParam, o1, Parameter.class );
    boolean isSecond = o2 != null && Expression.valuesEqual( variableParam, o2, Parameter.class );
    boolean inFirst = isFirst || ( o1 != null && o1.hasParameter( variableParam, true, null ) );
    boolean inSecond = isSecond || ( o2 != null && o2.hasParameter( variableParam, true, null ) );

    if ( !inFirst && !inSecond ) {
      Debug.error( true, false, "Error! pickValueBF2(variable=" + variable
                          + ", pickFunction=" + pickFunctionCall
                          + ", reversePickFunctionCall="
                          + reversePickFunctionCall
                          + "): variable not in function arguments! args=" + args );
      return null;
    }
    FunctionCall chosenPickCall = null;
    Expression< T1 > arg = null;
    Expression< T1 > otherArg = null;
    boolean equal;
    boolean first;
    if ( inFirst && ( reversePickFunctionCall == null || !inSecond ) ) {
      first = true;
    } else if ( inSecond && ( pickFunctionCall == null || !inFirst ) ) {
      first = false;
    } else {
      // in both arguments; pick randomly
      first = Random.global.nextBoolean();
    }
    chosenPickCall = first ? pickFunctionCall : reversePickFunctionCall;
    arg = first ? o1 : o2;
    otherArg = first ? o2 : o1;
    equal = first ? isFirst : isSecond;
    
    // pick a value for the chosen argument    
    T1 t1 = null;
    try {
      chosenPickCall.setStale( true );
      t1 = (T1)chosenPickCall.evaluate( false );
    } catch ( IllegalAccessException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( InstantiationException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    // If the argument is the variable, then picking a value for the function is
    // the same as picking a value for the variable.
    if ( equal ) return t1;
    
    // else, the variable is a part of the argument.
    // Example: pick value for x for z <= x + y
    // arg1 = z, arg2 = x+y, chosen arg = arg2
    // If z = 1, then t1 will be chosen >= 1, let's say t1 = 10 and y = 3
    // Need to choose value for x where x + y = 10; x = 10 - y = 10 - 3 = 7

    // If the argument is a FunctionCall, try to invert the call with the target
    // value, t1, to solve for the variable.
    SuggestiveFunctionCall fCall = getSuggestiveFunctionCall( arg.expression );
    if ( fCall == null ) { //arg.expression instanceof SuggestiveFunctionCall ) {
      return t1;
    }
    // fCall=Plus(x,y)
    //SuggestiveFunctionCall fCall = (SuggestiveFunctionCall)arg.expression;
    ArrayList< Object > argsWithVar = getArgumentsWithVariable( fCall, variable );
    if ( Utils.isNullOrEmpty( argsWithVar ) || argsWithVar.size() > 1 ) {
      // TODO -- solve for variable! or simplify expression!
      return null;
    }
    Object subExprArg = argsWithVar.get( 0 );
    if ( !( subExprArg instanceof Expression ) ) {
      return null;
    }
    // inverseCall = inverse of Plus(x,y)
    // inverseCall(x) = t1 - y
    // inverseCall(y) = t1 - x
    FunctionCall inverseCall = fCall.inverse(new Expression<T1>(t1), (Expression< ? >)subExprArg);
    if ( inverseCall instanceof SuggestiveFunctionCall ) {
      T1 t11 = ((SuggestiveFunctionCall)inverseCall).pickValue( variable );
      return t11;
    } else if ( inverseCall != null ) {
      Object result = null;
      try {
        result = inverseCall.evaluate( true );
      } catch ( IllegalAccessException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( InstantiationException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      if ( result instanceof Collection ) {
        Collection<T1> coll = (Collection<T1>)result;
        T1 t11 = get( coll, Random.global.nextInt( coll.size() ) );
        return t11;
      } else {
        Class<T1> cls = (Class< T1 >)variable.getClass();
        try {
          T1 t11 = Expression.evaluate( result, cls, true );
          return t11;
        } catch ( ClassCastException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch ( IllegalAccessException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch ( InvocationTargetException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch ( InstantiationException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }

    return null;
  }

  public static < T > T get( Collection<T> coll, int index ) {
    if ( coll instanceof List ) {
      return ((List<T>)coll).get( index );
    } else {
      T t = null;
      Iterator<T> iter = coll.iterator();
      for ( int i = 0; i != index+1 && iter.hasNext(); ++i ) {
        t = iter.next();
      }
      return t;
    }
  }
  
  // delete this function
  private static <T, T1> T1 pickValueBB( BooleanBinary< T > booleanBinary,
                                         Variable< T1 > variable ) {
    T1 newValue = pickValueBF( booleanBinary,//.functionCall, 
                               variable );
    return newValue;
  }
  // delete this function
  private static <T1> T1 pickValueBF( FunctionCall f, Variable< T1 > variable ) {
    boolean propagate = true;
    T1 newValue = null;
//    FunctionCall f = (FunctionCall)booleanBinary.expression;
    //Variable< T1 > otherArg = null;
    Object otherArg = null;
    Variable<T1> otherVariable = null;
    boolean found = false;
    for ( Object arg : f.arguments ) {
      if ( variable == arg ) {
        found = true;
      } else if ( arg instanceof Expression
                  && ( ( (Expression< ? >)arg ).expression == variable ) ) {
        found = true;
      } else if ( arg instanceof Expression
                  && !( ( (Expression< ? >)arg ).expression instanceof Parameter )
                  && variable instanceof Parameter ) {
        if ( ( (Expression< ? >)arg ).hasParameter( (Parameter< T1 >)variable,
                                                     false, null ) ) {
         try {
           newValue = (T1)variable.pickRandomValue();
         } catch ( ClassCastException e ) {
           // TODO??
         }
        }
      } else {
//        if ( arg instanceof Variable ) {
        if ( otherArg == null ) {
          otherArg = arg;// (Variable< T1 >)arg;
          if ( otherArg instanceof Variable ) {
            otherVariable = (Variable< T1 >)otherArg;
          } else if ( otherArg instanceof Expression ) {
            if ( ( (Expression< T1 >)otherArg ).form == Expression.Form.Parameter ) {
              otherVariable =
                  (Variable< T1 >)( (Expression< T1 >)otherArg ).expression;
            }
          }
        }
      }
    }
    Object value = null;
    Object otherValue = null;
//    if ( found ) {
//      if ( otherArg == null ) {
//        otherValue = 
//      }
//    }
    if ( otherArg != null && found ) {
      if ( f.method.getName().equalsIgnoreCase( "equals" ) ) {
        value = variable.getValue( propagate );
        if ( otherArg instanceof Variable ) {
          otherValue = ( (Variable<?>)otherArg ).getValue( propagate );
        } else if ( otherArg instanceof Expression ) {
          try {
            otherValue = ( (Expression<?>)otherArg ).evaluate( propagate );
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
        }
        if ( otherValue instanceof Variable ) {
          if ( value != null
               && !value.getClass().isInstance( otherValue )
               && value.getClass()
                       .isInstance( ( (Variable< ? >)otherValue ).getValue( propagate ) ) ) {
            otherValue = ( (Variable< ? >)otherValue ).getValue( propagate );
          }
        }
        if ( Debug.isOn() ) Debug.outln( "suggesting other arg value " + otherValue + " to make "
                     + f.getClass().getSimpleName() + " true" );
        return (T1)otherValue;
      }
      newValue = null;
      Boolean r = false;
      for ( int i=0; i < 5; ++i ) {
        T1 v = null;
        try {
        v = (T1)variable.pickRandomValue();
        } catch ( ClassCastException e ) {
          // TODO??
        }
        Pair< Boolean, Object > p = null;
        try {
          p = ClassUtils.runMethod( false, (Object)null, f.method,
                                    new Expression< T1 >( v ), otherArg );
          r = (Boolean)p.second;
        } catch ( IllegalArgumentException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        if ( p != null && p.first && r ) {
          if ( newValue == null ) {
            newValue = v;
          } else if ( otherVariable != null && otherVariable.getDomain() == null || otherVariable.getDomain().contains( v ) ) {
            if ( Debug.isOn() ) Debug.outln( "suggesting value " + v + " to try make "
                         + f.getClass().getSimpleName() + " true" );
            return v;
          }
        }
      }
    }
    if ( value == null ) {
      value = variable.getValue( propagate );
    }
    if ( newValue == null ) {
      if ( Debug.isOn() ) Debug.outln( "suggesting same value " + value + " for "
          + f.getClass().getSimpleName() + " true" );
      return (T1)value;
    }
    if ( Debug.isOn() ) Debug.outln( "suggesting value " + newValue + " for "
        + f.getClass().getSimpleName() + " true" );
    return newValue;
  }
  
  public static void main( String[] args ) {
    Parameter<Double> z = new Parameter<Double>( "z", (Domain<Double>)(new DoubleDomain( 0.0, 10.0 )), 10.0, null );
    Parameter<Integer> y = new Parameter<Integer>( "y", new IntegerDomain( 1, 5 ), 1, null );
    Parameter<Integer> x = new Parameter<Integer>( "x", new IntegerDomain( 4, 10 ), 1, null );
    Parameter<Double> w = new Parameter<Double>( "w", new DoubleDomain( 0.0, 10.0 ), 2.0, null );
    Sum< Integer, Integer > xPlusY = new Sum< Integer, Integer >( x, y );
    Sum< Integer, Integer > xPlusYPlusW =
        new Sum< Integer, Integer >( xPlusY, w );
    Less< Integer > expr = new Less< Integer >( z, xPlusYPlusW );
    System.out.println("expr = " + expr ); 
////    Integer xVal = expr.pickValue( x );
////    System.out.println("Picked " + xVal + " for x = " + x + " in expr = " + expr ); 
//    Integer zVal = expr.pickValue( z );
//    System.out.println("Picked " + zVal + " for z = " + z + " in expr = " + expr ); 
    Double wVal = expr.pickValue( w );
    System.out.println("Picked " + wVal + " for w = " + w + " in expr = " + expr );
    
    Less< Double > exprLess = new Less< Double >( z, w );
    Double zVal = exprLess.pickValue( z );
    System.out.println("Picked " + zVal + " for z = " + z + " in exprLess = " + exprLess );
    
    
    // test for inverse functions
    System.out.println( "x = " + x + "; domain = " + x.getDomain() );
    System.out.println( "y = " + y + "; domain = " + y.getDomain() );
    EQ< Integer > eq = new EQ< Integer >( x, y );
    FunctionCall i = eq.inverse( Boolean.TRUE, x );
    System.out.println( "eq: " + eq );
    System.out.println( "inverse of eq: " + i );
    try {
      Object r = i.evaluate( true );
      System.out.println( "evaluation of i: " + r );
    } catch ( IllegalAccessException e ) {
      e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      e.printStackTrace();
    } catch ( InstantiationException e ) {
      e.printStackTrace();
    }
    
    
    // TODO -- Add tests for overflow!!
    
  }

  
}
