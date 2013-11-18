package gov.nasa.jpl.ae.event;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.solver.SingleValueDomain;
import gov.nasa.jpl.ae.solver.Wraps;
import gov.nasa.jpl.ae.util.ClassUtils;
import gov.nasa.jpl.ae.util.CompareUtils;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.MoreToString;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.lang.reflect.TypeVariable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import junit.framework.Assert;

/**
 * 
 */

/**
 * @author bclement
 * 
 *         TODO -- REVIEW -- This is a little awkward. What about an Expression
 *         interface with implementations of Value, Parameter, MethodExpression,
 *         and Function? Would it make sense to put constructors in an
 *         expression factory?
 * 
 *         TODO -- Look at javaparser at http://code.google.com/p/javaparser/
 *         and JavaCC.
 * 
 *         TODO -- Consider using Ptolemy's expression parser.
 * 
 */
public class Expression< ResultType > extends HasIdImpl
                                    implements Cloneable, HasParameters, Groundable,
                                               LazyUpdate, Satisfiable,
                                               HasDomain, HasTimeVaryingObjects,
                                               MoreToString, Wraps< ResultType > {//, Comparable< Expression< ? > > {
  public Object expression = null;
  public Form form = Form.None;
	public Class<? extends ResultType> resultType = null;//Type.None;
  // freeParameters if not null specifies which parameters can be reassigned
  // values for satisfy().
  protected Set< Parameter< ? > > freeParameters = null;
  protected boolean evaluationSucceeded = false;


	public enum Form {
		None(null), Value(Object.class),
		Parameter(Parameter.class),
		//Method(Method.class),
		Function(FunctionCall.class),
    Constructor(ConstructorCall.class);
		
		private Class<?> myClass;
		Form( Class<?> c ) {
			myClass = c;
		}
		public Class<?> getFormClass() {
			return myClass;
		}
	}


  /**
   * Create an Expression by parsing a String in a specified language.
   * 
   * @param expressionString the String to parse
   * @param expressionLanguage the language to parse
   */
  public Expression( String expressionString, String expressionLanguage,
                     Class<ResultType> cls ) {
    resultType = cls;
    // TODO -- javaparser?
    System.err.println("Error! Expression( String ) constructor not yet supported! See EventXmlToJava.javaToEventExpression()");
    // REVIEW -- Should Expression be sub-classed for different languages?
  }

	/**
	 * @param object
	 */
	public Expression( Object object ) {
	  this( object, null );
	  if ( object != null && resultType == null ) {
		resultType = (Class< ? extends ResultType >)object.getClass();
	  }
	}

  /**
   * @param value
   * @param cls
   */
  public <T> Expression( T value, Class< ResultType > cls ) {
    resultType = cls;
    if ( cls == null ) {
        Expression<ResultType> e = null;
        if ( value instanceof Expression ) {
            e = new Expression<ResultType>( (Expression<ResultType>)value );
        } else if ( value instanceof Parameter ) {
            e = new Expression<ResultType>( (Parameter<ResultType>)value );
        } else if ( value instanceof Call ) {
            e = new Expression<ResultType>( (Call)value );
        }
        if ( e != null ) {
            copyMembers( e );
            return;            
        }
    }
    this.expression = value;
    if ( cls != null && value != null && !cls.isInstance( value ) ) {
      Debug.error( true,
                   "Expression initialized with incompatible value arg in Expression("
                       + value.getClass().getCanonicalName() + " "
                       + MoreToString.Helper.toShortString( value ) + ", "
                       + cls.getCanonicalName() );
    }
    form = Form.Value;
  }

	/**
	 * @param parameter
	 */
	public Expression( Parameter< ResultType > parameter, Class< ResultType > cls ) {
		this.expression = parameter;
    ResultType value = ( parameter == null ? null : parameter.getValue() );
    if ( value != null && cls == null ) {
      try {
        resultType = (Class< ? extends ResultType >)value.getClass();
      } catch ( ClassCastException e ) {}
    }
    if ( cls != null ) { 
      resultType = cls;
      if ( value != null && !cls.isInstance( value ) ) {
        Debug.error( true,
                     "Expression initialized with incompatible parameter arg in Expression(Parameter<"
                         + value.getClass().getCanonicalName() + "> "
                         + parameter + ", " + cls.getCanonicalName() );
      }
    }
		form = Form.Parameter;
	}

  /**
   * @param parameter
   */
  public Expression( Parameter< ResultType > parameter ) {
    this( parameter, null );
    if ( parameter != null && parameter.getValueNoPropagate() != null ) {
      resultType = (Class< ? extends ResultType >)parameter.getValueNoPropagate().getClass();
    }
  }

	/**
	 * @param function
	 */
	public Expression( FunctionCall function ) {
    init(function);
  }
  public Expression( FunctionCall function, Class<ResultType> resultType ) {
    init(function);
    if ( getResultType() == null || resultType != null ) {
      if ( getResultType() != null && resultType != null ) {
        if ( resultType.isAssignableFrom( this.resultType ) ) {
          
        }
      }
      setResultType( resultType );
    }
  }
  public void init( FunctionCall function ) {
		this.expression = function;
		if ( function != null && function.method != null ) {
		  resultType = (Class< ? extends ResultType >)function.getReturnType();
		}
		form = Form.Function;
	}
	
  /**
   * @param constructor
   */
  public Expression( ConstructorCall constructor ) {
    init(constructor);
  }
  public void init( ConstructorCall constructor ) {
    this.expression = constructor;
    if ( constructor != null ) {
      resultType = (Class< ? extends ResultType >)constructor.thisClass;
    }
    form = Form.Constructor;
  }
  
  /**
   * @param call
   */
  public Expression( Call call ) {
    if ( call instanceof ConstructorCall ) {
      init((ConstructorCall)call);
    } else if (call == null || call instanceof FunctionCall ) {
      init((FunctionCall)call);
    } else {
      Debug.error( true, "Error! Unknown Call type passed to Expression(Call call) constructor!" );
      this.expression = call;
    }
  }

  //	public Expression( Expression<ResultType> e ) {
//		this.expression = e.expression;
//		this.type = e.type;
//	}
	public Expression( Expression<ResultType> e, boolean deep ) {
		this.form = e.form;
		this.resultType = e.resultType;
		if ( !deep ) {
		  expression = e.expression;
		} else {
			switch (form) {
			case None:
//			case Method:
			case Value:  // TODO -- is this right for Value?
			case Parameter:
				expression = e.expression;
				break;
			case Function:
				expression = (Object) ((FunctionCall)e.expression).clone();
				break;
      case Constructor:
        expression = (Object) ((ConstructorCall)e.expression).clone();
        break;
//			case Parameter:
//				expression = (Object) new Parameter<ResultType>((Parameter<ResultType>)e.expression);
//				break;
			default:
				Assert.assertTrue( "Error! Bad expression type!", false );
			}
		}
	}
	
	// default shallow
  public Expression( Expression<ResultType> e ) {
    this(e, false);
  }

  
  @Override
  public Expression<ResultType> clone() throws CloneNotSupportedException {
    Expression<ResultType> e = new Expression< ResultType >( this );
    return e;
  }

  @Override
  public void deconstruct() {
    if ( expression instanceof Deconstructable ) {
      if ( form != Form.None && form != Form.Value &&
          ( form != Form.Parameter || ((Parameter<?>)expression).getOwner() == null ) ) {
        ( (Deconstructable)expression ).deconstruct();
      }
    }
    //expression = null;
  }

  public void copyMembers( Expression< ResultType > expr ) {
    this.expression = expr.expression;
    this.form = expr.form;
    this.resultType = expr.resultType;
    this.freeParameters = expr.freeParameters;
    this.evaluationSucceeded = expr.evaluationSucceeded;
  }
  
  public Boolean isResultType( Object o ) {
    try {
      ResultType t = (ResultType)o;
    } catch ( ClassCastException e ) {
      return false;
    }
    return true;
  }
  
  // REVIEW -- What if resultType == Expression.class?
  /**
   * Evaluate the expression and return the resulting value. For example, if the
   * expression is Math.min(4,6), evaluate() returns 4. If the expression is of
   * Type Value and is just the String "foo", then "foo" is returned.
   * 
   * @param propagate
   *          whether to try and update potentially stale values before
   *          evaluating.
   * @return the resulting value
   */
	public ResultType evaluate( boolean propagate ) {//throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	  evaluationSucceeded = false;
	  if ( form == null || ( form != Form.None && expression == null ) ) {
	    return null;
	  }
		try {
		switch (form) {
		case None:
			try {
				throw new IllegalAccessException();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return null; // TODO -- REVIEW -- exit?
		case Value:
			return (ResultType)expression;
		case Parameter:
          Parameter< ? > p = (Parameter< ? >)expression;
          while ( p != null ) {
            Object o = null;
            ResultType r = null;
            o = p.getValue( propagate );
            if ( o == null ) return null;
            try {
              if ( resultType != null ) {
                if ( resultType.isInstance( o ) ) {
                  evaluationSucceeded = true;
                  return (ResultType)o;
                } else {
                  if ( resultType == Integer.class && Double.class.isAssignableFrom(o.getClass()) ) {
                    Double d = (Double)o;
                    evaluationSucceeded = true;
                    return (ResultType)(Integer)d.intValue();
                  }
                  evaluationSucceeded = false;
                  throw new ClassCastException();
                }
              }
              if ( o instanceof Parameter ) {
                p = (Parameter< ? >)o;
              } else if ( o instanceof Expression ) {
                ResultType rt = ( (Expression<ResultType>)o ).evaluate( propagate );
                evaluationSucceeded = ( (Expression<ResultType>)o ).didEvaluationSucceed();
                return rt;
              } else {
                r = (ResultType)o;
                evaluationSucceeded = true;
                return r;
              }
            } catch ( ClassCastException cce ) {
              evaluationSucceeded = false;
              try {
                if ( Double.class.isAssignableFrom(o.getClass()) ) {
                  Double d = (Double)o;
                  evaluationSucceeded = true;
                  return (ResultType)(Integer)d.intValue();
                }
              } catch ( Exception e ) {
                evaluationSucceeded = false;
                // ignore
              }
              if ( o instanceof Parameter ) {
                p = (Parameter< ? >)o;
              } else if ( o instanceof Expression ) {
                ResultType rt = ( (Expression<ResultType>)o ).evaluate( propagate );
                evaluationSucceeded = ( (Expression<ResultType>)o ).didEvaluationSucceed();
                return rt;
              } else {
                Debug.error( false,
                             "Could not cast result of expression evaluation to ResultType: "
                                 + ( resultType != null ? "" : "(" + resultType
                                                               + ") " ) + this );
                //cce.printStackTrace();
                evaluationSucceeded = false;
                return (ResultType)o;
              }
            }
          }
		case Constructor:
    case Function:
      //HERE!!!;
			ResultType r = (ResultType)((Call)expression).evaluate( propagate );
			evaluationSucceeded = ((Call)expression).didEvaluationSucceed();
			return r;
		default:
		  evaluationSucceeded = false;
			return null;
		}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		evaluationSucceeded = false;
		return null;  // TODO -- REVIEW -- shouldn't get here -- die?
	}
	
	@Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen,
                          Map< String, Object > otherOptions ) {
    Pair< Boolean, Set< Object > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) deep = false;
    seen = pair.second;
		switch (form) {
		case None:
			try {
				throw new IllegalAccessException();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return null; // TODO -- REVIEW -- exit?
		case Value:
//			return ((ResultType)expression).toString();
		case Parameter:
//		case Method:
		case Function:
    case Constructor:
      if ( expression == null ) return "null";
      return MoreToString.Helper.toString( expression, withHash, deep, seen,
                                           otherOptions );
		default:
			return null;
		}
	}

  @Override
  public String toShortString() {
    return MoreToString.Helper.toShortString( expression );
  }

  @Override
  public String toString() {
    return MoreToString.Helper.toString( expression );
  }

  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen ) {
    return MoreToString.Helper.toString( expression, withHash, deep, seen );
  }

  @Override
	public boolean substitute( Parameter<?> p1, Parameter<?> p2, boolean deep,
	                           Set<HasParameters> seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
		boolean subbed = false;
		if ( form == Form.Parameter ) {
			if ( p1 == expression ) {
				expression = p2;
				subbed = true;
			}
		}
		if ( !subbed && ( expression instanceof HasParameters ) ) {
			HasParameters gotParameters = (HasParameters) expression;
			assert( gotParameters != null );
			subbed = gotParameters.substitute( p1, p2, deep, seen );
		}
		return subbed;
	}
	
	@Override
	public Set< Parameter<?> > getParameters( boolean deep,
	                                          Set<HasParameters> seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
		Set< Parameter<?> > set = new HashSet< Parameter<?> >();
		if ( form == Form.Parameter ) {
		  if ( expression != null ) {
		    Parameter<?> p = (Parameter<?>) this.expression; 
		    set.add( p );
		    if ( deep ) {
		      Object v = p.getValueNoPropagate(); 
		      if ( v != null && v instanceof HasParameters ) {
		        set = Utils.addAll( set, ((HasParameters)v).getParameters( deep, seen ) );
		      }
		    }
		  }
		} else if ( expression instanceof HasParameters ) {
			HasParameters gotParameters = (HasParameters) expression;
			if ( gotParameters != null ) {
				set = Utils.addAll( set, gotParameters.getParameters( deep, seen ) );
			}
		}
		return set;
	}
	
  /**
   * @return the freeParameters
   */
  public Set< Parameter< ? > > getFreeParameters() {
    // REVIEW -- this assumes that the parameters of the constraint and their
    // freedom never change.
    if ( freeParameters == null ) {
      freeParameters = new HashSet< Parameter< ? > >();
      for ( Parameter< ? > p : getParameters( false, null ) ) {
        if ( p.getOwner() != null && 
             p.getOwner().isFreeParameter( p, false, null ) ) {
          freeParameters.add( p );
        }
      }
    }
    return freeParameters;
  }

  /**
   * @param freeParameters
   *          the freeParameters to set
   */
  public void setFreeParameters( Set< Parameter< ? > > freeParameters ) {
    this.freeParameters = freeParameters;
  }

	public Set<Parameter<?>> getFreeParameters( boolean deep,
	                                            Set<HasParameters> seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
	  return getFreeParameters();
	}

  @Override
  public void setFreeParameters( Set< Parameter< ? > > freeParams, boolean deep,
                                 Set<HasParameters> seen ) {
    setFreeParameters( freeParams );
  }
  
	/**
   * @return the expression
   */
  public Object getExpression() {
    return expression;
  }

  /**
   * @param expression the expression to set
   */
  public void setExpression( Object expression ) {
    this.expression = expression;
  }

  /**
   * @return the form
   */
  public Form getForm() {
    return form;
  }

  /**
   * @param form the form to set
   */
  public void setForm( Form form ) {
    this.form = form;
  }

  /**
   * @param resultType the resultType to set
   */
  public void setResultType( Class< ? extends ResultType > resultType ) {
    this.resultType = resultType;
  }

  @Override
	public boolean isGrounded(boolean deep, Set< Groundable > seen) {
		if (expression instanceof Groundable) {
			return ((Groundable)expression).isGrounded(deep, seen);
		}
//		if ( expression == null ) {
//			return false;
//		}
		switch (form) {
		case Value:
//		case Method:
			return true; // null should be ok, right?
		case Parameter: // Groundable -- should not get here
		case Function: // Groundable -- should not get here
    case Constructor: // Groundable -- should not get here
		case None:
		default:
			try {
				throw new IllegalAccessException();
			} catch (IllegalAccessException e) {
			  System.err.println( "Error! Expression has invalid type: " + form );
				e.printStackTrace();
			}
			return false; // TODO -- REVIEW -- exit?
		}
	}

	/* (non-Javadoc)
	 * @see gov.nasa.jpl.ae.event.Groundable#ground(boolean, java.util.Set)
	 */
	@Override
	public boolean ground(boolean deep, Set< Groundable > seen) {
		if (expression instanceof Groundable) {
			return ((Groundable)expression).ground(deep, seen);
		}
//		if ( expression == null ) {
//			return false;
//		}
		switch (form) {
		case Value:
			return true; // null should be ok, right?
		case Parameter: // Groundable -- should not get here
		case Function: // Groundable -- should not get here
    case Constructor: // Groundable -- should not get here
		case None:
		default:
			try {
				throw new IllegalAccessException();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return false; // TODO -- REVIEW -- exit?
		}
		//return grounded;
	}

	// NOTE: Don't use hashCode() unless overridden -- default may vary between runs!
  //@Override
  public int compareTo( Expression< ? > o ) {
    if ( this == o ) return 0;
    if ( o == null ) return 1;
    int compare = CompareUtils.compare( form.ordinal(), o.form.ordinal() );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( expression, o.expression, true );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( this, o, false );
    if ( compare != 0 ) return compare;
    return compare;
  }

  @Override
  public boolean isStale() {
    for ( Parameter< ? > p : getParameters( false, null ) ) {
      if ( p.isStale() ) return true;
    }
    return false;
  }

  @Override
  public void setStale( boolean staleness ) {
    if ( Debug.isOn() ) Debug.outln( "setStale(" + staleness + ") to " + this );
    // TODO -- REVIEW -- Do nothing?
  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                               Set<HasParameters> seen ) {
    return getParameters( deep, seen ).contains( parameter );
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep,
                                  Set<HasParameters> seen ) {
    // REVIEW -- Should Expressions know which are free? Should it just be the
    // owner's job, in which case the Parameter can determine it itself?
    return false;
  }

  @Override
  public boolean isSatisfied(boolean deep, Set< Satisfiable > seen) {
    return HasParameters.Helper.isSatisfied( this, true, null );
  }

  @Override
  public boolean satisfy(boolean deep, Set< Satisfiable > seen) {
    return HasParameters.Helper.satisfy( this, true, null );
  }

  @Override
  public Domain< ResultType > getDomain( boolean propagate, Set< HasDomain > seen ) {
    if ( expression == null ) {
      if ( form == Form.Value ) {
        return SingleValueDomain.getNullDomain();
      }
      return null;
    }
    switch (form) {
    case Value:
//    case Method:
      return new SingleValueDomain<ResultType>( (ResultType)expression ); // since expression is not null
    case Parameter:
      return ((Parameter<ResultType>)expression).getDomain( propagate, seen );
    case Function:
    case Constructor:
      return (Domain< ResultType >)((FunctionCall)expression).getDomain( propagate, seen );
    case None:
    default:
      try {
        throw new IllegalAccessException();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
      return null;
    }
  }

  @Override
  public Set< TimeVarying< ? >>
      getTimeVaryingObjects( boolean deep, Set< HasTimeVaryingObjects > seen ) {
    // TODO -- use HasParameters.Helper!!
    Pair< Boolean, Set< HasTimeVaryingObjects > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< TimeVarying<?> > set = new HashSet< TimeVarying<?> >();
    set = Utils.addAll( set, HasTimeVaryingObjects.Helper.getTimeVaryingObjects( expression, deep, seen ) );
    // REVIEW -- We could make Call extend HasTimeVaryingObject, but it seems
    // like everybody has to know about everybody!
    // What about a general Has that has a get( Class<?> c, deep, seen )?
    // Consider again using reflection to go through members?
    if ( deep && ( form == Form.Function || form == Form.Constructor ) ) {
      Call call = (Call)expression;
      set = Utils.addAll( set, HasTimeVaryingObjects.Helper.getTimeVaryingObjects( call.getObject(), deep, seen ) );
      set = Utils.addAll( set, HasTimeVaryingObjects.Helper.getTimeVaryingObjects( call.getArguments(), deep, seen ) );
    }
    return set;
  }

  /**
   * Evaluate/dig or wrap the object of the given type cls from the object o,
   * which may be a Parameter or an Expression.
   * 
   * @param object
   *          the object to evaluate
   * @param cls
   *          the type of the object to find
   * @return o if o is of type cls, an object of type cls that is an evaluation
   *         of o, or null otherwise.
   */
  public static <TT> TT evaluate( Object object, Class< TT > cls,
                                  boolean propagate ) throws ClassCastException {
    return evaluate( object, cls, propagate, false );
  }
  
  public static <TT> TT evaluate( Object object, Class< TT > cls,
                                  boolean propagate,
                                  boolean allowWrapping ) throws ClassCastException {
    if ( object == null ) return null;
    // Check if object is already what we want.
    if ( cls != null && cls.isInstance( object ) || cls == object.getClass() ) {
      return (TT)object;
    }
    
    // Try to evaluate object or dig inside to get the object of the right type. 
    Object value = null;
    if ( object instanceof Parameter ) {
      value = ( (Parameter)object ).getValue( propagate );
      return evaluate( value, cls, propagate, allowWrapping );  
    } 
    else if ( object instanceof Expression ) {
      Expression< ? > expr = (Expression<?>)object;
      if ( cls != null && cls.isInstance( expr.expression ) &&
           expr.form != Form.Function) {
        return (TT)expr.expression;
      }
      value = expr.evaluate( propagate );
      return evaluate( value, cls, propagate, allowWrapping );  
    }
    else if ( object instanceof Call) {
      value = ( (Call)object ).evaluate( propagate );
      return evaluate( value, cls, propagate );  
    }
    else if ( cls != null && ClassUtils.isNumber( cls ) && 
              ClassUtils.isNumber( object.getClass() ) ) {
      try {
        Number n = (Number)object;
        TT r = (TT)ClassUtils.castNumber( n, (Class< ? extends Number >)cls );
        return r;
      } catch ( Exception e ) {
        // ignore
      }
    }
    else if ( allowWrapping && cls != null ){
      // If evaluating doesn't work, maybe we need to wrap the value in a parameter.
      if ( cls.isAssignableFrom( Parameter.class ) ) {
        if ( Debug.isOn() ) Debug.error( false, "Warning: wrapping value with a parameter with null owner!" );
        return (TT)( new Parameter( null, null, object, null ) );
      } else if ( cls.isAssignableFrom( Expression.class ) ) {
        return (TT)( new Expression( object ) );
      }
    }
    TT r = null;
    try {
      r = (TT)object;
    } catch ( ClassCastException cce ) {
      Debug.errln( "Warning! No evaluation of " + object + " with type " + cls.getName() + "!" );
      throw cce;
    }
    return r;
  }
  
  /**
   * Determine whether the values of two objects are equal by evaluating them. 
   * @param o1
   * @param o2
   * @return whether the evaluations of o1 and o2 are equal.
   * @throws ClassCastException
   */
  public static boolean valuesEqual( Object o1, Object o2 ) throws ClassCastException {
    return valuesEqual( o1, o2, null, false, false );
  }
  public static boolean valuesEqual( Object o1, Object o2, Class<?> cls ) throws ClassCastException {
    return valuesEqual( o1, o2, cls, false, false );
  }
  public static boolean valuesEqual( Object o1, Object o2, Class<?> cls,
                                     boolean propagate,
                                     boolean allowWrapping ) throws ClassCastException {
    if ( o1 == o2 ) return true;
    if ( o1 == null || o2 == null ) return false;
    if ( (o1 instanceof Float && o2 instanceof Double ) || (o2 instanceof Float && o1 instanceof Double ) ) {
      Debug.out( "" );
    }
    Object v1 = evaluate( o1, cls, propagate, false );
    Object v2 = evaluate( o2, cls, propagate, false );
    if ( Utils.valuesEqual( v1, v2 ) ) return true;
    Class< ? > cls1 = null;
    if ( v1 != null ) {
      cls1 = v1.getClass();
    }
    if ( cls1 != null && cls1 != cls && valuesEqual( v2, v1, cls1 ) ) return true;
    if ( v2 != null ) {
      Class< ? > cls2 = v2.getClass();
      if ( cls2 != cls && cls2 != cls1 && valuesEqual( v1, v2, cls2 ) ) return true;
    }
    return false;
    /*
    Class< ? > cls1 =
        ( cls != null ) ? cls : ( ( v1 == null ) ? null : v1.getClass() );
    Class< ? > cls2 =
        ( cls != null ) ? cls : ( ( v2 == null ) ? null : v2.getClass() );
    v1 = evaluate( v1, cls1, propagate, allowWrapping );
    v2 = evaluate( v2, cls1, propagate, allowWrapping );
    if ( Utils.valuesEqual( v1, v2 ) ) return true;
    Class< ? > cls1 =
        ( cls != null ) ? cls : ( ( v1 == null ) ? null : v1.getClass() );
    Class< ? > cls2 =
        ( cls != null ) ? cls : ( ( v2 == null ) ? null : v2.getClass() );
    Object v1 = evaluate( o1, cls1, propagate, allowWrapping );
    Object v2 = evaluate( o2, cls1, propagate, allowWrapping );
    if ( Utils.valuesEqual( v1, v2 ) ) return true;
    if ( cls1 != cls2 ) {
      v1 = evaluate( o1, cls2, propagate, allowWrapping );
      v2 = evaluate( o2, cls2, propagate, allowWrapping );      
    }
    return Utils.valuesEqual( v1, v2 );
    */
  }

  /**
   * A deep search looking for FunctionCalls
   */
  public List<FunctionCall> getFunctionCalls() {
    if ( form == Form.Function ) {
      return ((FunctionCall)expression).getFunctionCallsRecursively();
    }
    return Utils.getEmptyList();
  }

  public Class< ? extends ResultType > getResultType() {
    if ( this.resultType != null ) return this.resultType;
    ResultType r = evaluate( false );
    if ( r != null ) {
      resultType = (Class< ? extends ResultType >)r.getClass();
    }
    return resultType;
  }
  
  @Override
  public Class< ? > getType() {
    return getResultType();
  }

  @Override
  public Class< ? > getPrimitiveType() {
    Class< ? > c = null;
    if ( getType() != null ) {
      c = ClassUtils.primitiveForClass( getType() );
      ResultType r = evaluate( false );
      if ( c == null && r != null
           && Wraps.class.isInstance( r ) ) {// isAssignableFrom( getType() ) ) {
        c = ( (Wraps< ? >)r ).getPrimitiveType();
      }
      if ( c == null && expression != null && Wraps.class.isInstance( expression ) ) {
        c = ( (Wraps< ? >)expression).getPrimitiveType();
      }
    }
    return c;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.solver.Wraps#getTypeNameForClassName(java.lang.String)
   */
  @Override
  public String getTypeNameForClassName( String className ) {
    return ClassUtils.parameterPartOfName( className, false );
  }

  @Override
  public ResultType getValue( boolean propagate ) {
    return evaluate( propagate );
  }

  @Override
  public void setValue( ResultType value ) {
    // TODO Auto-generated method stub
    
  }

  /**
   * @return the evaluationSucceeded
   */
  public boolean didEvaluationSucceed() {
    return evaluationSucceeded;
  }

}
