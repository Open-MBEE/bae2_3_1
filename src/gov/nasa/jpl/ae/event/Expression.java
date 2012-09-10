package gov.nasa.jpl.ae.event;
import gov.nasa.jpl.ae.solver.Satisfiable;

import java.util.Set;
import java.util.TreeSet;
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
public class Expression< ResultType >
                                    implements HasParameters, Groundable,
                                               LazyUpdate, Satisfiable {//, Comparable< Expression< ? > > {
	public Object expression = null;
	public Type type = Type.None;

	public enum Type {
		None(null), Value(Object.class),
		Parameter(Parameter.class),
		//Method(Method.class),
		Function(FunctionCall.class);
		
		private Class<?> myClass;
		Type( Class<?> c ) {
			myClass = c;
		}
		public Class<?> getTypeClass() {
			return myClass;
		}
	}


  /**
   * @param value
   */
  public Expression( String expressionString, String expressionLanguage ) {
    // TODO -- javaparser?
    System.err.println("Error! Expression( String ) constructor not yet supported! See EventXmlToJava.javaToEventExpression()");
  }

	/**
	 * @param value
	 */
	public Expression( ResultType value ) {
		this.expression = value;
		type = Type.Value;
	}

	/**
	 * @param parameter
	 */
	public Expression( Parameter< ResultType > parameter ) {
		this.expression = parameter;
		type = Type.Parameter;
	}

//	/**
//	 * @param function
//	 */
//	public Expression( Method method ) {
//		this.expression = method;
//		type = Type.Method;
//	}

	/**
	 * @param function
	 */
	public Expression( FunctionCall function ) {
		this.expression = function;
		type = Type.Function;
	}
	
//	public Expression( Expression<ResultType> e ) {
//		this.expression = e.expression;
//		this.type = e.type;
//	}
	public Expression( Expression<ResultType> e, boolean deep ) {
		this.type = e.type;
		if ( !deep ) {
		  expression = e.expression;
		} else {
			switch (type) {
			case None:
//			case Method:
			case Value:  // TODO -- is this right for Value?
			case Parameter:
				expression = e.expression;
				break;
			case Function:
				expression = (Object) new FunctionCall((FunctionCall)e.expression);
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
    this.expression = e.expression;
    this.type = e.type;
  }
  
	public ResultType evaluate( boolean propagate ) {//throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	  if ( type == null || ( type != Type.None && expression == null ) ) {
	    return null;
	  }
		try {
		switch (type) {
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
		  if ( propagate )
		    return ((Parameter<ResultType>)expression).getValue();
      return ((Parameter<ResultType>)expression).getValueNoPropagate();
//		case Method:
//			return (ResultType)((Method)expression).invoke(null, (Object[])null);
		case Function:
			return (ResultType)((FunctionCall)expression).evaluate( propagate );
		default:
			return null;
		}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
		}
		return null;  // TODO -- REVIEW -- shouldn't get here -- die?
	}
	
	@Override
	public String toString() {
		switch (type) {
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
		  if ( expression == null ) return "null";
			return expression.toString();
		default:
			return null;
		}
	}

	@Override
	public boolean substitute( Parameter<?> p1, Parameter<?> p2, boolean deep ) {
		boolean subbed = false;
		if ( type == Type.Parameter ) {
			if ( p1 == expression ) {
				expression = p2;
				subbed = true;
			}
		}
		if ( !subbed && ( expression instanceof HasParameters ) ) {
			HasParameters gotParameters = (HasParameters) expression;
			assert( gotParameters != null );
			subbed = gotParameters.substitute( p1, p2, deep );
		}
		return subbed;
	}
	
	@Override
	public Set< Parameter<?> > getParameters( boolean deep ) {
		Set< Parameter<?> > set = new TreeSet< Parameter<?> >();
		if ( type == Type.Parameter ) {
			set.add( (Parameter<?>) this.expression );
		} else if ( expression instanceof HasParameters ) {
			HasParameters gotParameters = (HasParameters) expression;
			if ( gotParameters != null ) {
				set.addAll( gotParameters.getParameters( deep ) );
			}
		}
		return set;
	}

	@Override
	public Set<Parameter<?>> getFreeParameters(boolean deep) {
		Assert.assertFalse("This method should not be called since an Expression does not differentiate between free and dependent parameters.", true );
		return null; //getParameters( deep );
	}

  @Override
  public void setFreeParameters( Set< Parameter< ? >> freeParams ) {
    Assert.assertTrue( "This method is not supported!", false );
  }
  
	@Override
	public boolean isGrounded() {
		if (expression instanceof Groundable) {
			return ((Groundable)expression).isGrounded();
		}
		if ( expression == null ) {
			return false;
		}
		switch (type) {
		case Value:
//		case Method:
			return true; // since expression is not null
		case Parameter:
		case Function:
		case None:
		default:
			try {
				throw new IllegalAccessException();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return false; // TODO -- REVIEW -- exit?
		}
	}

	@Override
	public boolean ground() {
		if (expression instanceof Groundable) {
			return ((Groundable)expression).ground();
		}
		if ( expression == null ) {
			return false;
		}
		switch (type) {
		case Value:
			return true;
//		case Method:
//			return true; // since expression is not null
		case Parameter:
		case Function:
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

  // Not overriding since a superclass needs to implement a difft comparable,
  // and Java won't allow implementation of two difft Comparables!
  //@Override
  public int compareTo( Expression< ? > o ) {
    // TODO -- REVIEW -- Is this lame?
    return toString().compareTo( o.toString() );
  }

  @Override
  public boolean isStale() {
    for ( Parameter< ? > p : getParameters( false ) ) {
      if ( p.isStale() ) return true;
    }
    return false;
  }

  @Override
  public void setStale( boolean staleness ) {
    // TODO -- REVIEW -- Do nothing?
  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep ) {
    return getParameters( deep ).contains( parameter );
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep ) {
    // REVIEW -- Should Expressions know which are free? Should it just be the
    // owner's job, in which case the Parameter can determine it itself?
    return false;
  }

  @Override
  public boolean isSatisfied() {
    return HasParameters.Helper.isSatisfied( this, true );
  }

  @Override
  public boolean satisfy() {
    return HasParameters.Helper.satisfy( this, true );
  }
  
}
