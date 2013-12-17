/**
 * 
 */
package gov.nasa.jpl.ae.magicdrawPlugin.modelQuery;

import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.util.ClassUtils;
import gov.nasa.jpl.ae.util.CompareUtils;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.CompareUtils.GenericComparator;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Utils;

import japa.parser.ast.expr.FieldAccessExpr;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import junit.framework.Assert;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.magicdraw.uml.BaseElement;

/**
 * A ModelReference is a reference to elements in a model (like a UML model, for
 * example). Like a function call, the reference is made from a the scope of an
 * element (for example, a class). A specifier selects from that scope. A
 * ModelReference may be disambiguated from the context in how it is used.
 * 
 * For example, the reference to relation3 in elementA.relation3.partProperties
 * is in the scope of elementA. relation3 might be used to refer to
 * <ol>
 * <li>an element of type relation3 that relates elementA to another element,
 * <li>the relation3 type/class,
 * <li>an element related to elementA by relation3, or
 * <li>a collection of elements matching one of the prior criteria.
 * </ol>
 * 
 * We might think of a "dot" syntax for references as follows:
 * <p>
 * {@literal reference ::= specifier | reference '.' specifier | reference '(' specifier ')'}
 * <br>
 * {@literal
 * specifier ::= property | name | type
 * }
 * <p>
 * 
 * A ModelReference can be interpreted/evaluated. We will use an EMF API to
 * access a model in order to provide this interpretation.
 * <p>
 * 
 * As an {@link Expression}, a {@code ModelReference} may be of {@code Type}
 * {@link FunctionCall} (wrapping an API call) or of {@code Type} {@code Value}
 * for an {@link EObject} or a collection of {@code EObject}s.
 * <p>
 * 
 * The inherited {@link Expression} attributes are an implementation of the
 * reference to access the objects being referenced. The {@code alternatives}
 * field may contain alternative interpretations of the reference.
 * 
 */
public class ModelReference< T > extends Expression< Collection< T > > implements Cloneable {

  // REVIEW -- Should other members be wrapped in Parameters, like model???

  // protected ModelReference< ? > scopeReference = null;
  /**
   * An expression representing a reference to a single object.
   */
  protected Expression<T> singleExpression = null;

  /**
   * The {@link Object} or {@link ModelReference} which is the scope of the
   * specifier if not null. If null, the scope is determined as documented for
   * {@link #getScope()}. If null, then the model serves as the scope instead.
   * If the model is also null, then the scope is the object of the
   * {@link FunctionCall}, this.{@link Expression#expression}. If this
   * expression is not a FunctionCall, then the scope is global and may depend
   * on imports. If specifier and model are null, then scopeReference is the
   * object referenced, alternatives should be empty or null, and the
   * scopeReference is the object of this.expression as a FunctionCall, or equal
   * in value to this.expression as a Parameter or a Value.
   */
  protected Object scopeReference = null; // TODO -- rename to just "scope"

  /**
   * A string representation, which (if not null) is parsed as the
   * scopeReference.
   */
  protected String scopeReferenceString = null;
 
  /**
   * A reference to an object (or objects) with respect to the scope. If null,
   * then scopeReference is the object referenced. This may be a field, method,
   * property, or relation.
   */
  protected String specifier = null;
 
  /**
   * A specifier (or potentially a collection of specifiers) that may (or
   * will--TODO) be applied to this reference. It is used to filter alternative
   * interpretations. This may be a String (indicating a member) or a
   * ModelReference
   */
  protected Object nextSpecifier = null;
  protected Object nextSpecifierString = null;
 
  /**
   * The model within which this reference is applicable.
   */
  protected Parameter< EObject > model = null;

  /**
   * Alternative interpretations of this reference.
   */
  protected List< ModelReference< ? > > alternatives = null;

  public Class< T > singleResultType = null;
  
  // public ModelReference() {
  // super( (Method)null );
  // }

  /**
   * @param expressionString
   * @param expressionLanguage
   */
  public ModelReference( String expressionString, String expressionLanguage,
                         Class< T > cls ) {
    super( expressionString, expressionLanguage, makeCollectionClass( cls ) );
    if ( expressionLanguage.toLowerCase().equals( "dot" )
         || expressionLanguage.toLowerCase().equals( "dotsyntax" )
         || expressionLanguage.toLowerCase().equals( "java" ) ) {
      parseDotSyntax( expressionString, null, false );
    } else {
      Debug.error( true, "Error! Expression language \"" + expressionLanguage
                         + "\" is not supported!" );
    }
  }

  /**
   * This function
   * parses the expression String before constructing the model reference since the ModelReference constructors need to know the resultType.   
   * @param t
   * @return
   */
  public static <TT, TTT> ModelReference<TT> parseModelReference( String expressionString,
                                                                  String expressionLanguage ) {
    ModelReference< TTT > mr = new ModelReference< TTT >( expressionString, expressionLanguage, null );
    
    if ( mr.singleResultType == null ) {
      
    }
    if ( mr.resultType == null ) {
      
    }
    ModelReference< TT > mrNew =
        new ModelReference< TT >( mr.scopeReference, mr.scopeReferenceString,
                                  mr.specifier, mr.nextSpecifier, mr.model,
                                  mr.alternatives,
                                  (Class< ? extends Collection< TT >>)mr.resultType,
                                  (Class< TT >)mr.getSingleResultType(),
                                  false );
    return mrNew;
  }
  
  /**
   * Create a ModelReference to an object. Just sets the scope and result types.<br>
   * 
   * <em>REVIEW -- This is a reference but not a model reference.  Should this
   * be handled differently?</em>
   * 
   * @param eObject
   */
  public ModelReference( T object, Class< T > cls ) {
    super( makeCollection( object, cls ), makeCollectionClass( object ) );
    singleResultType = cls;
    setScope( object );
  }
  
  public void setScope( Object object ) {
    if ( object instanceof EObject ) {
      setModel( (EObject)object );
    } else {
      setScopeReference( object );
    }
    if ( form == Form.Function ) {
      FunctionCall fc = (FunctionCall)expression;
      if ( fc.getMethod() != null
           && fc.getMethod().getDeclaringClass().isInstance( object ) ) {
        fc.setObject( object );
      }
    }
  }

  /**
     * Create a ModelReference to an object directly.  It may not be an EObject.
     * 
     * @param p
     */
    public ModelReference( Parameter< T > p ) {
      this( p, (Class< T >)( ( p == null || p.getValue() == null )
                             ? null
                             : p.getValue().getClass() ) );
  //    if ( p != null && p.getValue() != null && p.getValue() instanceof EObject ) {
  //      model = (Parameter< EObject >)p;
  //    }
    }

  /**
     * Create a ModelReference to an object directly.  It may not be an EObject.
     * <em>
     * TODO -- IF the Parameter changes, then so should the scopeReference! Should
     * there be no scopeReference and it be determined by model and
     * expression (which in this case is the Parameter)?
     * 
   * @param p
   * @param cls
   */
  public ModelReference( Parameter< T > p, Class<T> cls ) {
    super( makeCollection(p.getValue(), cls), makeCollectionClass(cls));
    if ( p != null && p.getValue() != null && p.getValue() instanceof EObject ) {
      model = (Parameter< EObject >)p;
    }
  }

  /**
   * Create a ModelReference with the input scopeReference, specifier, and
   * resultType. Other members are determined by resolving the specifier in the
   * scopeReference.
   * 
   * @param scopeRef
   * @param specifier
   * @param nextSpecifier
   * @param resultType
   * @param resolve
   */
  public ModelReference( Object scopeRef, String specifier,
                         Object nextSpecifier,
                         Class< Collection< T > > resultType, boolean resolve ) {
    this( (String)null, (String)null, (Object)scopeRef, (String)null,
          specifier, nextSpecifier, (Parameter< EObject >)null,
          (List< ModelReference< ? >>)null, resultType, null, resolve );
  }

  /**
   * Create a ModelReference with the input model scope, specifier, and
   * resultType. Other members are determined by resolving the specifier in the
   * scopeReference.
   * 
   * @param scope
   * @param specifier
   * @param nextSpecifier
   * @param resultType
   * @param resolve
   */
  public ModelReference( EObject scope, String specifier, Object nextSpecifier, 
                         Class< Collection< T > > resultType, boolean resolve ) {
    this( (String)null, (String)null, (Object)null, (String)null, specifier,
          nextSpecifier,
          new Parameter< EObject >( "", null, scope, null ),
          (List< ModelReference< ? >>)null,
          resultType, null, resolve );
    // this( scope, null, specifier, scope, null, resultType, resolve );
    setModel( scope );
  }

  /**
   * Create a ModelReference by specifying all fields, including the model as a
   * Parameter.
   * 
   * @param scopeReference
   * @param scopeReferenceString
   * @param specifier
   * @param nextSpecifier
   * @param model
   * @param alternatives
   * @param resultType
   * @param resolve
   */
  public ModelReference( Object scopeReference, String scopeReferenceString,
                         String specifier, Object nextSpecifier,
                         Parameter< EObject > model,
                         List< ModelReference< ? > > alternatives,
                         Class<? extends Collection< T > > resultType,
                         Class< T > singleResultType, boolean resolve ) {
    super( new FunctionCall( scopeReference, (Method)null ) );
    this.scopeReference = scopeReference;
    this.scopeReferenceString = scopeReferenceString;
    this.specifier = specifier;
    this.nextSpecifier = nextSpecifier;
    this.model = model;
    this.alternatives = alternatives;
    setResultTypes( singleResultType, resultType );
    if ( resolve ) {
      resolve();
    }
  }

  public void setResultTypes( Class< T > singleResultType,
                              Class< ? extends Collection< T > > resultType ) {
    if ( singleResultType != null ) {
      setSingleResultType( singleResultType );
    }
    if ( resultType != null ) {
      setResultType( resultType );
    }
    if ( this.singleResultType == null ) {
      setSingleResultType( singleResultType );
    }
  }

  /**
   * Create a ModelReference by specifying all fields, including the model as an
   * EObject.
   * 
   * @param expressionString
   * @param expressionLanguage
   * @param scopeReference
   * @param scopeReferenceString
   * @param specifier
   * @param nextSpecifier
   * @param model
   * @param alternatives
   * @param resultType
   * @param singleResultType
   */
  public ModelReference( String expressionString, String expressionLanguage,
                         Object scopeReference,
                         String scopeReferenceString, String specifier,
                         Object nextSpecifier,
                         Parameter< EObject > model,
                         List< ModelReference< ? > > alternatives,
                         Class< ? extends Collection< T > > resultType,
                         Class< T > singleResultType, boolean resolve ) {
    super( expressionString, expressionLanguage,
           makeCollectionClass( singleResultType ) );
    this.scopeReference = scopeReference;
    this.scopeReferenceString = scopeReferenceString;
    this.specifier = specifier;
    this.nextSpecifier = nextSpecifier instanceof String ? null : nextSpecifier;
    this.nextSpecifierString =
        nextSpecifier instanceof String ? nextSpecifier : null;
    this.model = model;
    this.alternatives = alternatives;
    setResultTypes( singleResultType, resultType );
    //this.resultType = resultType;
    //setSingleResultType( singleResultType );
    if ( resolve ) {
      resolve();
    }
  }

  /**
   * Create a ModelReference by specifying all fields, including the model as an
   * EObject.
   * 
   * @param expressionString
   * @param expressionLanguage
   * @param scopeReference
   * @param scopeReferenceString
   * @param specifier
   * @param nextSpecifier
   * @param model
   * @param alternatives
   * @param resultType
   * @param singleResultType
   */
  public ModelReference( String expressionString, String expressionLanguage,
                         Object scopeReference,
                         String scopeReferenceString, String specifier,
                         Object nextSpecifier, EObject model,
                         List< ModelReference< ? > > alternatives,
                         Class< ? extends Collection< T > > resultType,
                         Class< T > singleResultType, boolean resolve ) {
    this( expressionString, expressionLanguage, scopeReference,
          scopeReferenceString, specifier, nextSpecifier,
          new Parameter<EObject>( "model", null, model, null ),
          alternatives, resultType, singleResultType, resolve );
  }

  /**
   * Create a ModelReference as a shallow copy of another.
   * @param modelReference
   */
  public ModelReference( ModelReference< T > modelReference ) {
    super( modelReference );
    copyLocalMembers( modelReference );
  }

  /**
   * Create a ModelReference as a shallow copy of another.
   * @param modelReference
   */
  public ModelReference( ModelReference< T > modelReference, boolean deep ) {
    super( modelReference );
    copyLocalMembers( modelReference, deep );
  }

  public ModelReference( Object scope, Call call, Object nextSpecifier,
                         Class< Collection< T > > returnType, boolean resolve ) {
    this( scope, call.toShortString(), nextSpecifier, returnType, resolve );
    setScope( scope );
    setNextSpecifier( nextSpecifier );
    setResultTypes( singleResultType, resultType );
    if ( call.getObject() == null && !call.isStatic() ) {
      call.setObject( getScope() );
    }
    singleExpression = new Expression( call );
    if ( resolve ) {
      resolve();
    }
  }

  /**
   * Assign this ModelReference's members the values of another's members.
   * 
   * @param modelReference
   */
  public void copyMembers( ModelReference< T > modelReference ) {
    super.copyMembers( modelReference );
    copyLocalMembers( modelReference );
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Expression#copyMembers(gov.nasa.jpl.ae.event.Expression)
   */
  @Override
  public void copyMembers( Expression< Collection< T > > expression ) {
    if ( expression instanceof ModelReference ) {
      copyMembers( (ModelReference< T >)expression );
    } else {
      super.copyMembers( expression );
    }
  }

  /**
   * Assign this ModelReference's members the values of another's members.
   * 
   * @param modelReference
   */
  public void copyLocalMembers( ModelReference< T > modelReference ) {
    copyLocalMembers( modelReference, false );
  }
  public void copyLocalMembers( ModelReference< T > modelReference,
                                boolean deep ) {
    if ( deep && modelReference.singleExpression != null ) {
      try {
        this.singleExpression = modelReference.singleExpression.clone();
      } catch ( CloneNotSupportedException e ) {
        e.printStackTrace();
        this.singleExpression = modelReference.singleExpression;
      }
    } else {
      this.singleExpression = modelReference.singleExpression;
    }
    if ( deep && modelReference.scopeReference != null &&
         modelReference.scopeReference instanceof Expression ) {
      try {
        this.scopeReference = ((Expression< ? >)modelReference.scopeReference).clone();
      } catch ( CloneNotSupportedException e ) {
        e.printStackTrace();
        this.scopeReference = modelReference.scopeReference;
      }
    } else {
      this.scopeReference = modelReference.scopeReference;
    }
    this.scopeReferenceString = modelReference.scopeReferenceString;
    this.specifier = modelReference.specifier;
    this.nextSpecifier = modelReference.nextSpecifier;
    this.nextSpecifierString = modelReference.nextSpecifierString;
    this.model = modelReference.model;
    if ( deep && modelReference.alternatives != null ) {
      this.alternatives = new ArrayList< ModelReference<?> >();
      
      for ( ModelReference< ? > mr :  modelReference.alternatives ) {
        this.alternatives.add( new ModelReference( mr, deep ) );
      }
    } else {
      this.alternatives = modelReference.alternatives;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.jpl.ae.event.Expression#clone()
   */
  @Override
  public ModelReference< T > clone() throws CloneNotSupportedException {
    return new ModelReference< T >( this );
  }

  public static <TT> Class< Collection<TT> > makeCollectionClass( TT tt ) {
    Collection<TT> coll = new ArrayList< TT >();
    Class< Collection<TT> > ccls = (Class< Collection<TT> >)coll.getClass();
    return ccls;
  }
  
  public static <TT> Class< Collection<TT> > makeCollectionClass( Class<TT> ttcls ) {
    Collection<TT> coll = new ArrayList< TT >();
    Class< Collection<TT> > ccls = (Class< Collection<TT> >)coll.getClass();
    return ccls;
  }
  
//  public static <TT> Collection<TT> makeCollection( TT tt, Class<TT> cls ) {
//    ArrayList< TT > ttList = new ArrayList< TT >();
//    ttList.add( tt );
//    return ttList;
//  }
  public static <TT,CC> Collection<TT> makeCollection( TT tt, Class<? extends TT> cls ) {
    ArrayList< TT > ttList = new ArrayList< TT >();
    ttList.add( tt );
    return ttList;
  }

  public static <TT,CC> Collection<TT> makeCollection( Parameter<TT> tt, Class<? extends TT> cls ) {
    ArrayList< TT > ttList = new ArrayList< TT >();
    ttList.add( tt.getValue() );
    return ttList;
  }

  /**
   * The scopeReference field serves as the scope if it is not null and is not
   * the object of reference. If scopeReference is null, then the model serves
   * as the scope instead. If the model is also null, then the scope is the
   * object of the FunctionCall, this.expression. If this expression is not a
   * FunctionCall, then the scope is global and may depend on imports. If
   * specifier and model are null, then scopeReference is the object referenced,
   * alternatives should be empty or null, and the scopeReference is the object
   * of this.expression as a FunctionCall, or equal in value to this.expression
   * as a Parameter or a Value.
   * 
   * @return the object from which the reference is specified.
   */
  public Object getScope() {
    if ( expression instanceof Call ) {
      Object o = ( (Call)expression ).getObject();
      if ( o != null ) {
        if ( scopeReference != null ) {
          Assert.assertTrue( o.equals( scopeReference ) ||
                             ( o instanceof Collection && 
                               ((Collection)o).contains( scopeReference)) );
        }
        return o;
      }
    }
    if ( scopeReference != null ) {
      if ( specifier == null && model != null ) {
        return model;
      }
      return scopeReference;
    }
    if ( model != null ) return model;
    return null;
  }
  
  /**
   * Evaluate the reference and return a single object matching this reference
   * for one interpretation.
   * 
   * @param propagate
   *          whether stale parameters are updated by evaluating other
   *          expressions.
   * @return a single object that is referenced for some interpretation of this
   *         object.
   */
  public Object evaluateAndGetOne( boolean propagate ) {
    return evaluate( propagate );
  }

  /**
   * Evaluate this reference and return a list of evaluation results, one
   * {@code Collection\< T \>} for each interpretation.
   * 
   * @param propagate
   *          whether stale parameters are updated by evaluating other
   *          expressions.
   * @return the list of alternative evaluation results.
   */
  public List< Collection< ? >> evaluateAndGetAlternatives( boolean propagate ) {
    List< Collection< ? >> list = new ArrayList< Collection< ? >>();
    Collection< T > evaluation = evaluate( propagate );
    if ( didEvaluationSucceed() ) {
      list.add( evaluation );
    }
    if ( !Utils.isNullOrEmpty( alternatives ) ) {
      for ( ModelReference< ? > r : alternatives ) {
        List< Collection< ? >> rList = r.evaluateAndGetAlternatives( propagate );
        if ( !Utils.isNullOrEmpty( rList ) ) {
          list.addAll( rList );
        }
      }
    }
    return list;
  }

  Class< ? extends Collection<T> > getResultCollectionType() {
    return resultType;
  }
  Class< T > getSingleResultType() {
    return singleResultType;
  }
  
  Boolean isResultCollectionType( Object o ) {
    return super.isResultType( o );
  }

  Boolean isSingleResultType( Object o ) {
    try {
      T t = (T)o;
    } catch ( ClassCastException e ) {
      return false;
    }
    return true;
  }
  

  public Collection< T > evaluateAndGetCollection( boolean propagate ) {
    List<T> coll = new ArrayList< T >();

    // find all candidate references
    resolve();
    
    // check if just a simple direct reference
    if ( isDirectReference() ) {
      coll.add( getDirectReference() );
      evaluationSucceeded = true;
      return coll;
    }
    
    // evaluate the expression to get the object referenced 
    try {
      Collection<T> ct = super.evaluate( propagate );
      // If it succeeded, we're done--don't bother with alternatives.
      if ( didEvaluationSucceed() ) {
        coll.addAll( ct );
        return coll;
      }
    } catch ( ClassCastException e ) {
      // 
      if ( Debug.isOn() || didEvaluationSucceed() ) {
        String msg = "Reference evaluation failed to cast! " +
                     this.toString( true, true, null );
        if ( didEvaluationSucceed() ) {
          Debug.error( false, "Error! " + msg );
        } else {
          Debug.errln( "Warning! " + msg );
        }
      }
      evaluationSucceeded = false;
    }
    if ( !Utils.isNullOrEmpty( alternatives ) ) {
      for ( ModelReference< ? > r : alternatives ) {
        //T o = Expression.evaluate( r, resultType, propagate );
//        try {
          Object o = r.evaluate( propagate );
          if ( r.didEvaluationSucceed() ) {
            if ( isResultCollectionType( o ) ) {
              try {
                evaluationSucceeded = true;
                return (Collection< T >)o;
              } catch ( ClassCastException e ) {}
            }
            if ( isSingleResultType( o ) ) {
              Collection o1 =
                  makeCollection( o,
                                  o.getClass().getClass()
                                    .cast( getSingleResultType() ) );
                  try {
                    Collection< T > ct = (Collection< T >)o1;
                    evaluationSucceeded = true;
                    return ct;
                  } catch ( ClassCastException e ) {}
              }
            }
//        } catch ( ClassCastException e ) {
//          if ( Debug.isOn() || r.didEvaluationSucceed() ) {
//            String msg =
//                "Reference alternative evaluation failed to cast!\nthis="
//                    + this.toString( true, true, null ) + "\nalternative="
//                    + r.toString( true, true, null );
//            if ( r.didEvaluationSucceed() ) {
//              Debug.error( false, "Error! " + msg );
//            } else {
//              Debug.errln( "Warning! " + msg );
//            }
//          }
//        }
      }
    }
    evaluationSucceeded = false;
    
    return coll;
  }
  
  // A collection is NOT returned if the reference matches
  // multiple elements/objects.
  /*
   * Resolve the reference and return the referenced object, which may require
   * picking an alternative and/or selecting from a set of matching objects.
   * (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Expression#evaluate(boolean)
   */
  @Override
  public Collection< T > evaluate( boolean propagate ) {
    List<T> coll = new ArrayList< T >();

    // find all candidate references
    resolve();
    
    // check if just a simple direct reference
    if ( isDirectReference() ) {
      coll.add( getDirectReference() );
      evaluationSucceeded = true;
      return coll;
    }
    
    // evaluate the expression to get the object referenced 
    try {
      Collection<T> ct = super.evaluate( propagate );
      // If it succeeded, we're done--don't bother with alternatives.
      if ( didEvaluationSucceed() ) {
        return ct;
      }
    } catch ( ClassCastException e ) {
      // 
      if ( Debug.isOn() || didEvaluationSucceed() ) {
        String msg = "Reference evaluation failed to cast! " +
                     this.toString( true, true, null );
        if ( didEvaluationSucceed() ) {
          Debug.error( false, "Error! " + msg );
        } else {
          Debug.errln( "Warning! " + msg );
        }
      }
      evaluationSucceeded = false;
    }
    if ( !Utils.isNullOrEmpty( alternatives ) ) {
      for ( ModelReference< ? > r : alternatives ) {
        //T o = Expression.evaluate( r, resultType, propagate );
//        try {
          T t = null;
          Object o = r.evaluate( propagate );
          if ( r.didEvaluationSucceed() ) {
            if ( isResultCollectionType( o ) ) {
              try {
                evaluationSucceeded = true;
                return (Collection< T >)o;
              } catch ( ClassCastException e ) {}
            }
            if ( isSingleResultType( o ) ) {
              Collection o1 =
                  makeCollection( o,
                                  o.getClass().getClass()
                                    .cast( getSingleResultType() ) );
                  try {
                    Collection< T > ct = (Collection< T >)o1;
                    evaluationSucceeded = true;
                    return ct;
                  } catch ( ClassCastException e ) {}
              }
//            if ( resultType == null || resultType.isInstance( o ) ) {
//              try {
//                t = (T)o;
//                evaluationSucceeded = true;
//                return t;
//              } catch ( ClassCastException e ) {}
//            }
//            if ( o instanceof Collection && !( (Collection)o ).isEmpty() ) {
//                Object o1 = ( (Collection<?>)o ).iterator().next();
//                if ( resultType == null || resultType.isInstance( o1 ) ) {
//                  try {
//                    t = (T)o1;
//                    evaluationSucceeded = true;
//                    return t;
//                  } catch ( ClassCastException e ) {}
//                }
//              }
            }
//        } catch ( ClassCastException e ) {
//          if ( Debug.isOn() || r.didEvaluationSucceed() ) {
//            String msg =
//                "Reference alternative evaluation failed to cast!\nthis="
//                    + this.toString( true, true, null ) + "\nalternative="
//                    + r.toString( true, true, null );
//            if ( r.didEvaluationSucceed() ) {
//              Debug.error( false, "Error! " + msg );
//            } else {
//              Debug.errln( "Warning! " + msg );
//            }
//          }
//        }
      }
    }
    evaluationSucceeded = false;
    return null;
  }

  public T getDirectReference() {
    if ( Debug.isOn() && isDirectReference() ) {
      Debug.errln( "Warning! Calling getDirectReference() when isDirectReference() return false!" );
    }
    return (T)scopeReference;
  }

  /**
   * @return whether this reference contains the object being referenced.
   */
  public boolean isDirectReference() {
    return ( specifier == null &&
             scopeReference != null &&
             ( expression == null || 
               ( super.evaluate( false ) == null &&
                 !didEvaluationSucceed() ) ) );
  }

  /**
   * Generate alternative interpretations for this ModelReference and filter
   * them according the nextReference.
   */
  protected void resolve() {
    Object scope = getScope();
    alternatives = getAlternatives( scope, specifier, nextSpecifier );
    
    // If there is just one alternative, use this reference to refer to its
    // singleExpression.
    if ( !isSingleExpressionAnAlternative() && alternativesSize() == 1 ) {
      copyMembers((ModelReference< T >)alternatives.get( 0 ));
      alternatives.clear();
    }
//    if ( scopeReference == null ) {
//      if ( scopeReferenceString == null ) {
//        ModelReference<?> mr = findAlternatives( specifier, null, true );
//        
//      } else {
//        scopeReference = findAlternatives( scopeReferenceString, specifier, true );
//      }
//    } else {
//      
//    }
  }

  /**
   * Parses the expressionString in "dot" syntax to load ModelReference members.
   * The specifier is after the last '.' in the string, and everything before is
   * the scopeReferenceString. If there is no '.', expressionString is the
   * specifier, and the scopeReference is the existing scopeReference or (if the
   * scopeReference is null) the model.
   * 
   * @param expressionString
   *          expression in dot syntax
   * @param propagate 
   */
  public void parseDotSyntax( String expressionString, String nextSpecifier,
                              boolean propagate ) {
    // identify scope and specifier substrings
    int pos = expressionString.lastIndexOf( '.' );
    specifier = expressionString.substring( pos + 1 ); // works for pos==-1
    if ( pos >= 0 ) {
      scopeReferenceString = expressionString.substring( 0, pos );
    }
    // get the scope's reference
    // if ( scopeReference == null ) {
    scopeReference = findAlternatives( scopeReferenceString, nextSpecifier,
                                    propagate );
    // }
    if ( scopeReference == null ) {
      return;
    }
    // evaluate to find candidate results? no, wait results to be requested
    // through evaluate() or ??? REVIEW
    // setCandidateResults(evaluate(true));
  }

  /**
   * @param specifier
   * @param nextSpecifier
   * @param propagate
   *          whether stale parameters are updated by evaluating other
   *          expressions.
   * @return a ModelReference for the specifier in the context of the existing
   *         ModelReference.
   */
  public ModelReference< ? > findAlternatives( String specifier,
                                            Object nextSpecifier,
                                            boolean propagate) {
//    ModelReference< ? > mr =
//        new ModelReference( this, specifier, nextSpecifier, resultType,
//                            propagate );
//    List< ModelReference< ? > > newAlternatives =
//        new ArrayList< ModelReference< ? > >();
//    List< Collection< ? > > evaluations =
//        this.evaluateAndGetAlternatives( propagate );
//    for ( Collection<?> refs : evaluations ) {
//      for ( Object scopeRef : refs ) {
//        ModelReference< ? > mr =
//            new ModelReference( scopeRef, specifier, nextSpecifier, resultType,
//                                propagate );
//        Object result = ((Expression)mr).evaluate( propagate );
//        if ( mr.didEvaluationSucceed() ) {
//          newAlternatives.add( mr );
//        }
//        if (  )
//        if ( !mr.isEmpty() ) {
//          if ( !Utils.isNullOrEmpty( mr.getAlternatives() ) ) {
//            
//          }
//        }
//      }
//    }
    Object scopeRef = null;
    ModelReference< ? > specRef = null;
    if ( scopeRef == null && model != null ) {
      scopeRef = new ModelReference< EObject >( model );
    }
    if ( scopeRef == null ) {
      return null;
    }
    // Class< ? > cls = null; // need to determine this before constructing
    // specRef
    // TODO -- REVIEW -- How about wrapping these getAlternatives() calls
    // FunctionCalls?
    List< ModelReference< ? > > alternativeRefs =
        getAlternatives( scopeRef, specifier, nextSpecifier );
    if ( alternativeRefs.size() == 1 ) {
      specRef = alternativeRefs.get( 0 );
    } else if ( alternativeRefs.size() > 1 ) {
      Class< Collection< ? > > cls = null;
      //      Class< List< ModelReference< ? > > > cls =
//          (Class< List< ModelReference< ? > > >)alternativeRefs.getClass();
      specRef = new ModelReference( scopeRef, specifier, nextSpecifier, cls, false );
      specRef.alternatives = alternativeRefs;
    }
    return specRef;
  }

  /**
   * @return whether there is an object that matches this reference
   */
  public boolean isEmpty() {
    return isEmpty( true );
  }

  /**
   * @param propagate
   *          whether stale parameters are updated by evaluating other
   *          expressions.
   * @return whether there is an object that matches this reference
   */
  public boolean isEmpty( boolean propagate ) {
    return ( evaluateAndGetOne( true ) != null );
  }

  public boolean isSingleExpressionAnAlternative() {
    if ( singleExpression != null ) {
      singleExpression.evaluate( false );
      return singleExpression.didEvaluationSucceed();
    }
    return false;
  }
  
  public int numberOfAlternatives() {
    int count = 0;
    if ( isSingleExpressionAnAlternative() ) {
      ++count;
    }
    count += alternativesSize();
    return count;
  }
  
  protected int alternativesSize() {
    if ( Utils.isNullOrEmpty( alternatives ) ) return 0;
    int count = 0;
    for ( ModelReference< ? > mr : alternatives ) {
      count += mr.numberOfAlternatives();
    }
    return count;
  }
  
  /**
   * Determine whether the object is considered a model element. The
   * {@link FieldAccessExpr} is interpreted based on whether the scope or
   * specifier is a model element.
   * 
   * @param o
   * @return
   */
  public static boolean isModelElement( Object o ) {
    return ( o instanceof BaseElement ||
             o instanceof EModelElement );
  }
  
  /**
   * @param specifier
   * @param nextSpecifier
   * @return a ModelReference for the specifier in the context of the existing
   *         scopeReference or (if the scopeReference is null), the model.
   */
  public ModelReference< ? > findReferenceFromScope( String specifier,
                                                     Object nextSpecifier ) {
    Object scopeRef = getScope();
    ModelReference< ? > specRef = null;
    if ( scopeRef == null && model != null ) {
      scopeRef = new ModelReference< EObject >( model );
    }
    if ( scopeRef == null ) {
      return null;
    }
    // Class< ? > cls = null; // need to determine this before constructing
    // specRef
    // TODO -- REVIEW -- How about wrapping these getAlternatives() calls
    // FunctionCalls?
    List< ModelReference< ? > > alternativeRefs =
        getAlternatives( scopeRef, specifier, nextSpecifier );
    if ( alternativeRefs.size() == 1 ) {
      specRef = alternativeRefs.get( 0 );
    } else if ( alternativeRefs.size() > 1 ) {
      Class< Collection< ? > > cls = null;
      //      Class< List< ModelReference< ? > > > cls =
//          (Class< List< ModelReference< ? > > >)alternativeRefs.getClass();
      specRef = new ModelReference( scopeRef, specifier, nextSpecifier, cls, false );
      specRef.alternatives = alternativeRefs;
    }
    return specRef;
  }
  
  public static boolean matches( Object o, String specifier ) {
    if ( o == null || Utils.isNullOrEmpty( specifier ) ) return false;
    if ( o instanceof EObject ) return matchesEObject( (EObject)o, specifier );
    if ( matchesObject( o, specifier ) ) return true;
    return false;
  }

  /**
   * Determines whether the EObject can be identified by the specifier.
   * 
   * @param eObj
   * @param specifier
   * @return
   */
  private static boolean matchesEObject( EObject eObj, String specifier ) {
    // If the EObject is a named element, then matchesObject() suffices to check
    // getName().
    if ( matchesObject( eObj, specifier ) ) return true;
    if ( matchesEClass( eObj.eClass(), specifier ) ) return true;
    return false;
  }

  private static boolean matchesEClass( EClass eClass, String specifier ) {
    if ( matchesObject( eClass, specifier ) ) return true;
    if ( matchesEObject( eClass, specifier ) ) return true;
    if ( matches( eClass.getInstanceClass(), specifier ) ) return true;
    if ( matches( eClass.getInstanceClassName(), specifier ) ) return true;
    return false;
  }
  /**
   * @param cls
   * @param specifier
   * @return whether the string specifier can be a reference to the input class
   */
  public static boolean matchesClass( Class<?> cls, String specifier ) {
    if ( matchesString( cls.getSimpleName(), specifier ) ) return true;
    if ( matchesString( cls.getName(), specifier ) ) return true;
    if ( matchesString( cls.getCanonicalName(), specifier ) ) return true;
    return false;
  }


  public static boolean matchesString( Object o, String specifier ) {
    if ( o == null || Utils.isNullOrEmpty( specifier ) ) return false;
    // see if toString() matches ignoring case and whitespace
    if ( o.toString().replaceAll( "\\s+", "" )
          .equalsIgnoreCase( specifier.replaceAll( "\\s+", "" ) ) ) return true;
    return false;
  }

  public static boolean matchesFieldValue( Object o, String specifier,
                                      String fieldName ) {
    List< String > possibleFieldNames = getPossibleFieldNames( fieldName );
    for ( String name : possibleFieldNames ) {
      Object r = ClassUtils.getField( o, name, true );
      if ( matchesString( r, specifier ) ) return true;
    }
    return false;
  }
  
  public static boolean matchesMethodResult( Object o, String specifier,
                                             String methodSuffix ) {
    List< String > possibleMethodNames = getPossibleMethodNames( methodSuffix );
    for ( String name : possibleMethodNames ) {
      Object r = ClassUtils.getField( o, name, true );
      if ( matchesString( r, specifier ) ) return true;
    }
    return false;
  }
  
  /**
   * @param o
   * @param specifier
   * @param memberSuffixes
   * @return whether there is a member (field or getter method) that references
   *         one of the labels in memberSuffixes
   */
  public static boolean matchesMember( Object o, String specifier,
                                       String[] memberSuffixes ) {
    for ( String memberName : memberSuffixes ) {
      if ( matchesFieldValue( o, specifier, memberName ) ) return true;
      if ( matchesMethodResult( o, specifier, memberName ) ) return true;
    }
    return false;
  }  
  
  /**
   * Determine whether the string specifier can be a reference to the object
   * based on String rendering, its type name, and "name" members.
   * 
   * @param o
   * @param specifier
   * @return
   */
  public static boolean matchesObject( Object o, String specifier ) {
    if ( o == null || Utils.isNullOrEmpty( specifier ) ) return false;

    // REVIEW -- this could be a score between 0.0 and 1.0.

    // see if toString() matches ignoring case and whitespace
    if ( matchesString( o, specifier ) ) return true;
    
    // match as a reference to the object's type    
    if ( matchesClass( o.getClass(), specifier ) ) return true;

    // look for identifying members
    if ( matchesMember( o, specifier,
                        new String[] { "name", "id", "identifier" } ) ) return true;

    return false;
  }

  // TODO -- REVIEW -- How about wrapping these getAlternatives() calls
  // FunctionCalls?
  /**
   * Get all alternative interpretations.
   * @param scope
   * @param specifier
   * @param nextSpecifier
   * @return
   */
  public static List< ModelReference< ? > >
      getAlternatives( Object scope, String specifier, Object nextSpecifier ) {
    return getAlternatives( scope, specifier, nextSpecifier, 1, null ); 
  }
  
  public static List< ModelReference< ? > >
    getAlternatives( Object scope, String specifier, Object nextSpecifier,
                     int maxRecurseDepth, Set<Object> seen ) {
    if ( scope == null ) return Collections.emptyList();

    // check recursion
    Pair< Integer, Set< Object > > p = seen(scope, maxRecurseDepth, seen);
    maxRecurseDepth = p.first;
    seen = p.second;
    if ( maxRecurseDepth < 0 ) return Collections.emptyList();
    
    List< ModelReference< ? > > alternatives =
        getObjectAlternatives( scope, specifier, nextSpecifier,
                               maxRecurseDepth, seen );
    if ( scope instanceof Collection ) {
      alternatives.addAll( getAlternatives( (Collection< ? >)scope, specifier,
                                            nextSpecifier, maxRecurseDepth, seen ) );
    }
    if ( scope instanceof EObject ) {
      alternatives.addAll( getAlternatives( (EObject)scope, specifier,
                                            nextSpecifier, maxRecurseDepth, seen ) );
    }
    if ( scope instanceof Map< ?, ? > ) {
      alternatives.addAll( getAlternatives( (Map< ?, ? >)scope, specifier,
                                            nextSpecifier, maxRecurseDepth, seen ) );
    }
    if ( scope instanceof Expression ) {
      alternatives.addAll( getAlternatives( ( (Expression)scope ).expression,
                                            specifier, nextSpecifier, maxRecurseDepth,
                                            seen ) );
    }
    return alternatives;
  }

  // TODO -- REVIEW -- How about wrapping these getAlternatives() calls
  // FunctionCalls?
  /**
   * Get alternative interpretations of a specifier for a Collection. The
   * specifier applies either to the Collection as a whole or to each element of
   * the Collection.
   * 
   * @param scope
   * @param specifier
   * @param nextSpecifier
   * @return different interpretations for applying a specifier to a Collection
   */
  public static List< ModelReference< ? > >
      getAlternatives( Collection< ? > scope, String specifier,
                       String nextSpecifier, int maxRecurseDepth,
                       Set<Object> seen) {
    if ( scope == null ) return Utils.getEmptyList();
    if ( Utils.isNullOrEmpty( specifier ) ) return Utils.getEmptyList();
    // check recursion
    Pair< Integer, Set< Object > > p = seen(scope, maxRecurseDepth, seen);
    maxRecurseDepth = p.first;
    seen = p.second;
    if ( maxRecurseDepth < 0 ) return Collections.emptyList();
    
    List< ModelReference< ? > > list = new ArrayList< ModelReference<?> >();

    if ( matchesString("this", specifier) || matchesString("self", specifier) ) {
      tryAddDirectReferenceToList( null, scope, specifier, nextSpecifier, list );
//      ModelReference< ? > mr = new ModelReference( scope, scope.getClass() );
//      list.add( mr );
    }
    
    GenericComparator< Object > objectComparator = 
        CompareUtils.GenericComparator.instance();
//    GenericComparator< Class< ? > > classComparator =
//        new CompareUtils.GenericComparator< Class< ? > >();
    GenericComparator< ModelReference< ? > > modelReferenceComparator = 
        CompareUtils.GenericComparator.instance();
//    Map< Class< ? >, Set< ModelReference< ? > > > alternativesForClass =
//        new TreeMap< Class< ? >, Set< ModelReference< ? > > >( classComparator );
//    Map< ModelReference< ? >, Set< Class< ? > > > classesForAlternative =
//        new TreeMap< ModelReference< ? >, Set< Class< ? > > >( modelReferenceComparator );
//    Map< Object, Set< ModelReference< ? > > > alternativesForObject =
//        new TreeMap< Object, Set< ModelReference< ? > > >( objectComparator );
    Map< ModelReference< ? >, Set< Object > > objectsForAlternative =
        new TreeMap< ModelReference< ? >, Set< Object > >( modelReferenceComparator );
    // Should we match subalternatives?
    // For example, getAlternatives({x1, y1, y2}, "zap", null );
    // If there is a getZap() method applicable to x1 and a zap() method
    // applicable to all, then you would expect two alternatives, 
    // {(getZap(x1)),(zap(x1), zap(y1), zap(y2))}.
    // {(x1.getZap()),(x1.zap(), y1.zap(), y2.zap())}.
    for ( Object o : scope ) {
      // o = x1 
      List< ModelReference< ? > > oneList =
          getAlternatives( o, specifier, nextSpecifier, maxRecurseDepth, seen );
      // oneList = (getZap(x1), zap(x1)) 
      // oneList = (x1.getZap(), x1.zap()) 
      // oneList = (zap(y1), zap(y2)) 
      // oneList = (y1.zap(), y2.zap()) 
      if ( oneList != null ) {
//        Set<ModelReference<?>> mrSet = new TreeSet< ModelReference<?> >( modelReferenceComparator );
//        mrSet.addAll( oneList );
        //alternativesForObject.put( o, mrSet );
        for ( ModelReference< ? > mr : oneList ) {
          // mr = getZap(x1)
          // mr = x1.getZap()
          mr = new ModelReference( mr, true );
          mr.removeNonStaticScope();
          // mr = getZap(null)
          // mr = null.getZap()
//          mrSet = alternativesForClass.get( o.getClass() );
//          if ( mrSet == null ) {
//            mrSet = new TreeSet< ModelReference<?> >( modelReferenceComparator );
//            alternativesForClass.put( o.getClass(), mrSet );  // put(X, {null.getZap()})
//          }
//          mrSet.add( mr );
//          Set<Class<?>> classSet = classesForAlternative.get( mr );
//          if ( classSet == null ) {
//            classSet = new TreeSet< Class<?> >( classComparator );
//            classesForAlternative.put( mr, classSet );
//          }
//          classSet.add( o.getClass() ); // put(null.getZap(), {X})
//          
          Set<Object> objSet = objectsForAlternative.get( mr );
          if ( objSet == null ) {
            objSet = new TreeSet< Object >( objectComparator );
            objectsForAlternative.put( mr, objSet ); // put(null.getZap(), {x1}) // put(null.zap(), {x1,y1,y2})
          }
          objSet.add( o );
        }
      }
    }
    for ( Entry< ModelReference< ? >, Set< Object > > e : objectsForAlternative.entrySet() ) {
      ModelReference< ? > mr = e.getKey();
      ModelReference< ? > mrCopy = new ModelReference( mr );
      mrCopy.setScope( e.getValue() );
      list.add( mrCopy ); // add((x1.getZap()))  // add((x1.zap(), y1.zap(), y2.zap()))
    }
    return list;
  }

  
  /**
   * @return whether this reference depends on its scope
   */
  public boolean isStatic() {
    if ( isSingleExpressionAnAlternative() &&
         getSingleExpression().getForm() == Form.Function ) {
      FunctionCall fc = (FunctionCall)getSingleExpression().expression;
      return fc.isStatic();
    }
    return false;
  }

  /**
   * Make this reference a template by removing its scope. The template is
   * instantiated by giving it a scope.
   */
  public void removeNonStaticScope() {
    Object scope = getScope();
    if ( !isStatic() && scope != null ) {
      if ( Expression.valuesEqual( scope, scopeReference ) ) {
        scopeReference = null;
        scopeReferenceString = null;
      }
      if ( Expression.valuesEqual( scope, model ) ) model = null;
      // check object of function call
      if ( isSingleExpressionAnAlternative()
           && getSingleExpression().getForm() == Form.Function ) {
        FunctionCall fc = (FunctionCall)getSingleExpression().expression;
        if ( Expression.valuesEqual( scope, fc.getObject() ) ) {
          fc.setObject( null );
        }
      }
    }
    for ( ModelReference< ? > mr : alternatives ) {
      mr.removeNonStaticScope();
    }
  }

  /**
   * Add or replace the scope with the input Object.
   */
  public void addScope( Object o ) {
    Object scope = getScope();
    if ( !isStatic() && scope != null ) {
      if ( Expression.valuesEqual( scope, scopeReference ) ) {
        scopeReference = null;
        scopeReferenceString = null;
      }
      if ( Expression.valuesEqual( scope, model ) ) model = null;
      // check object of function call
      if ( isSingleExpressionAnAlternative()
           && getSingleExpression().getForm() == Form.Function ) {
        FunctionCall fc = (FunctionCall)getSingleExpression().expression;
        if ( Expression.valuesEqual( scope, fc.getObject() ) ) {
          fc.setObject( null );
        }
      }
    }
    for ( ModelReference< ? > mr : alternatives ) {
      mr.removeNonStaticScope();
    }
  }

  /**
   * @return a common suffix for different toString() methods
   */
  protected String toStringSuffix() {
    StringBuffer sb = new StringBuffer();
    if ( nextSpecifier != null ) {
      sb.append( "[." + nextSpecifier + "]" );
    } else if ( nextSpecifierString != null ) {
      sb.append( "[" + nextSpecifierString + "]" );
    }
    return sb.toString();
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Expression#toString()
   */
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    if ( isSingleExpressionAnAlternative() ) {
      sb.append( super.toString() );
    } else {
      sb.append( getScope() + "." + specifier );
    }
    sb.append( toStringSuffix() );
    return sb.toString();
  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Expression#toString(boolean, boolean, java.util.Set)
   */
  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen ) {
    StringBuffer sb = new StringBuffer();
    if ( isSingleExpressionAnAlternative() ) {
      sb.append( super.toString( withHash, deep, seen ) );
    } else {
      sb.append( getScope() + "." + specifier );
    }
    sb.append( toStringSuffix() );
    return sb.toString();
  }
  
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Expression#toString(boolean, boolean, java.util.Set, java.util.Map)
   */
  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen,
                          Map< String, Object > otherOptions ) {
    StringBuffer sb = new StringBuffer();
    if ( isSingleExpressionAnAlternative() ) {
      sb.append( super.toString( withHash, deep, seen, otherOptions ) );
    } else {
      sb.append( getScope() + "." + specifier );
    }
    sb.append( toStringSuffix() );
    return sb.toString();
  }
  
  
  /**
   * Gets alternative interpretations for a specifier applied to a map, the
   * results of which may be referenced by the nextSpecifier. This simply treats
   * the keys and values as separate alternative scopes. I.e., the specifier is
   * applicable either to the set of keys or to the set of values.
   * 
   * @param scope
   *          the map to which the specifier is applied
   * @param specifier
   *          a String reference to some function, attribute, or relation in the
   *          scope
   * @param nextSpecifier
   *          a String reference to be applied to the results of the
   *          scope/specifier reference
   * @return alternative interpretations for a specifier applied to a map
   */
  public static List< ModelReference< ? > >
      getAlternatives( Map< ?, ? > scope, String specifier, String nextSpecifier,
                       int maxRecurseDepth, Set<Object> seen ) {
    if ( scope == null ) return Collections.emptyList();
    // check recursion
    Pair< Integer, Set< Object > > p = seen(scope, maxRecurseDepth, seen);
    maxRecurseDepth = p.first;
    seen = p.second;
    if ( maxRecurseDepth < 0 ) return Collections.emptyList();
    
    // Just add for keys and values as separate alternatives
    List< ModelReference< ? > > list =
        getAlternatives( scope.keySet(), specifier, nextSpecifier,
                         maxRecurseDepth, seen );

    if ( list == null ) list = new ArrayList< ModelReference<?> >();
    
    list.addAll( getAlternatives( scope.values(), specifier, nextSpecifier,
                                  maxRecurseDepth, seen ) );

    return list;
  }

  /**
   * @param object
   * @param specifier
   * @return alternatives specific to Objects (not any of their subclasses)
   */
  public static < O > List< ModelReference< ? > >
      getObjectAlternatives( O object, String specifier, Object nextSpecifier,
                             int maxRecurseDepth, Set<Object> seen ) {
    if ( object == null ) return Collections.emptyList();

    // check recursion
    Pair< Integer, Set< Object > > p = seen( object, maxRecurseDepth, seen );
    maxRecurseDepth = p.first;
    seen = p.second;
    if ( maxRecurseDepth < 0 ) return Collections.emptyList();
    
    List< ModelReference< ? > > list =
        getFieldAlternatives( object, specifier, nextSpecifier );

    list.addAll( getMethodAlternatives( object, specifier, nextSpecifier ) );
    
    return list;
  }

  /**
   * @param specifier
   * @return
   */
  public static List< String > getPossibleFieldNames( String specifier ) {
    List< String > possibleFieldNames = new ArrayList< String >();

    // get field(s) with matching name
    possibleFieldNames.add( specifier );
    possibleFieldNames.add( specifier.toUpperCase(Locale.US) );
    possibleFieldNames.add( specifier.toLowerCase(Locale.US) );
    String capitalizedSpec = Utils.capitalize( specifier );
    if ( Character.isLowerCase( specifier.charAt( 0 ) ) ) {
      possibleFieldNames.add( capitalizedSpec );
    }
    return possibleFieldNames;
  }

  /**
   * @param specifier
   * @return
   */
  public static List< String > getPossibleMethodNames( String specifier ) {
    return getPossibleMethodNames( specifier, new String[]{ "e", "eGet", "get" } );
  }

  /**
   * @param specifier
   * @param methodPrefixes
   * @return methods with names that match the specifier or the specifier with
   *         one of the method prefixes
   */
  public static List< String > getPossibleMethodNames( String specifier,
                                                       String ... methodPrefixes ) {
    // get methods with matching names
    List< String > possibleMethodNames = getPossibleFieldNames( specifier );
    String capitalizedSpec = Utils.capitalize( specifier );
    for ( String prefix : methodPrefixes ) {
      possibleMethodNames.add( prefix + capitalizedSpec );
    }
    return possibleMethodNames;
  }

  /**
   * @param object
   * @param specifier
   * @param nextSpecifier
   * @return alternative references specified as methods
   */
  public static < O > List< ModelReference< ? > >
      getMethodAlternatives( O object, String specifier, Object nextSpecifier ) {
    if ( object == null ) return Collections.emptyList();
    // get methods with matching names
    List< String > possibleMethodNames = getPossibleMethodNames( specifier );
    return getMethodAlternatives( object, specifier, nextSpecifier, possibleMethodNames );
  }

  /**
   * @param object
   * @param specifier
   * @param nextSpecifier
   * @param possibleMethodNames
   * @return alternative references specified as methods for 
   */
  protected static < O > List< ModelReference< ? > >
      getMethodAlternatives( O object, String specifier, Object nextSpecifier,
                             List< String > possibleMethodNames ) {
    if ( object == null ) return Collections.emptyList();

    ArrayList< ModelReference< ? > > list =
        new ArrayList< ModelReference< ? > >();

    for ( String name : possibleMethodNames ) {
      Method[] methods = ClassUtils.getMethodsForName( object.getClass(), name );
      if ( methods != null ) {
        for ( Method method : methods ) {
          ModelReference< ? > mr =
              getMethodAlternative( object, method, nextSpecifier );
          if ( mr != null && mr.numberOfAlternatives() > 0 ) {
            list.add( mr );
          }
        }
      }
    }
    return list;
  }

  /**
   * @param object
   * @param specifier
   * @return alternatives specific to Objects (not any of their subclasses)
   */
  public static < O > List< ModelReference< ? > >
      getFieldAlternatives( O object, String specifier, Object nextSpecifier ) {
    if ( object == null ) return Collections.emptyList();
    ArrayList< ModelReference< ? > > list =
        new ArrayList< ModelReference< ? > >();
    List< String > possibleFieldNames = getPossibleFieldNames( specifier );

    // get field(s) with matching name
    for ( String name : possibleFieldNames ) {
      ModelReference< ? > mr = getFieldAlternative( object, name, nextSpecifier );
      if ( mr != null && mr.numberOfAlternatives() > 0 ) {
        list.add( mr );
      }
    }
    return list;
  }
  
  public static ModelReference< ? >
      getFunctionCallAlternative( Object object, FunctionCall fc,
                                  String specifier, Object nextSpecifier ) {
    ModelReference< ? > mr = null;
    if ( object instanceof EObject ) {
      mr = new ModelReference( (EObject)object, specifier, nextSpecifier,
                               makeCollectionClass(fc.getReturnType()), false );
    } else {
      mr = new ModelReference( object, object.getClass() );
      mr.setSpecifier( specifier );
      mr.setNextSpecifier( nextSpecifier );
    }
    mr.singleExpression = new Expression( fc );
    return mr;
  }

  public static ModelReference< ? > getMethodAlternative( Object object,
                                                          Method method,
                                                          Object nextSpecifier ) {
    FunctionCall fc = new FunctionCall( object, method );
    ModelReference< ? > mr =
        getFunctionCallAlternative( object, fc, method.getName(), nextSpecifier );
    return mr;
  }

  public static ModelReference< ? > getFieldAlternative( Object object,
                                                         String fieldName,
                                                         Object nextSpecifier ) {
    boolean hasField = ClassUtils.hasField( object, fieldName );
    if ( !hasField ) return null;
    Object[] args = new Object[] { object, fieldName, false };
    Method m =
        ClassUtils.getMethodForArgs( ClassUtils.class, "getFieldValue", args );
    if ( m == null ) {
      Debug.error( true,
                   "Error! Could not get ClassUtils.getFieldValue() Method for args "
                       + args );
      return null;
    }
    FunctionCall fc = new FunctionCall( object, m, args );
    ModelReference< ? > mr =
        getFunctionCallAlternative( object, fc, fieldName, nextSpecifier );
    return mr;
  }
  
  /**
   * @param eObject
   * @param specifier
   * @return alternative interpretations specific to an EObject (not any of its
   *         subclasses)
   */
  public static List< ModelReference< ? > >
      getEObjectAlternatives( EObject eObject, String specifier,
                              String nextSpecifier,
                              int maxRecurseDepth, Set<Object> seen ) {
    if ( eObject == null ) return Collections.emptyList();
    if ( Utils.isNullOrEmpty( specifier ) ) return Collections.emptyList(); // REVIEW -- should this return the scope?

    // check recursion
    Pair< Integer, Set< Object > > p = seen( eObject, maxRecurseDepth, seen );
    maxRecurseDepth = p.first;
    seen = p.second;
    if ( maxRecurseDepth < 0 ) return Collections.emptyList();
    
    List< ModelReference< ? > > list = new ArrayList< ModelReference< ? > >();

    list.addAll( getSeparateAlternatives( eObject, eObject.eContents(), specifier,
                                       nextSpecifier, maxRecurseDepth, seen ) );
    list.addAll( getSeparateAlternatives( eObject, eObject.eCrossReferences(),
                                       specifier, nextSpecifier,
                                       maxRecurseDepth, seen ) );

    if ( eObject instanceof EClass ) {
      list.addAll( getEClassAlternatives( (EClass)eObject, specifier,
                                          nextSpecifier,
                                          maxRecurseDepth, seen ) );
    }
    list.addAll( getAlternatives( eObject.eClass(), specifier, nextSpecifier,
                                  maxRecurseDepth, seen ) );

    // TODO?
    return list;
  }

  /**
   * Get alternatives for the collection, where each EObject is a separate
   * interpretation of the scope. This differs from
   * {@link #getAlternatives(Collection, String, String)} where the collection
   * is the scope, a single interpretation.
   * 
   * @param scope
   *          the scope of the EObjects in the collection
   * @param collection
   * @param specifier
   * @param nextSpecifier
   * @param maxRecurseDepth
   * @return
   */
  public static List< ModelReference< ? > >
      getSeparateAlternatives( EObject scope, Collection< EObject > collection,
                               String specifier, String nextSpecifier,
                               int maxRecurseDepth, Set<Object> seen ) {
    if ( scope == null ) return Collections.emptyList();

    // check recursion
    Pair< Integer, Set< Object > > p = seen( scope, maxRecurseDepth, seen );
    maxRecurseDepth = p.first;
    seen = p.second;
    if ( maxRecurseDepth < 0 ) return Collections.emptyList();
    
    List< ModelReference< ? > > list = new ArrayList< ModelReference< ? > >();
    for ( EObject eObj : collection ) {
      if ( eObj == null ) continue;
      if ( matches( eObj, specifier ) ) {
        tryAddDirectReferenceToList( scope, eObj, specifier, nextSpecifier, list );
      }

      // recurse through EObject if haven't reached max depth of recursion
      if ( maxRecurseDepth > 0 ) {
        list.addAll( getAlternatives( eObj, specifier, nextSpecifier,
                                      maxRecurseDepth, seen ) );
      }
    }
    return list;
  }
  
  /**
   * Get alternative interpretations of a reference with name, specifier,
   * applied in the scope of an EClass.  Examples:
   * <ul>
   * <li>{@code class1.attribute1}
   * <li>{@code class1.reference1}
   * <li>{@code class1.superType1}
   * <li>{@code class1.operation1}
   * <li>{@code class1.operation1}
   * </ul> 
   * @param eClass
   * @param specifier
   * @param nextSpecifier
   * @param maxRecurseDepth 
   * @return alternatives specific to EObjects (not any of their subclasses)
   */
  public static List< ModelReference< ? > > getEClassAlternatives( EClass eClass,
                                                                   String specifier,
                                                                   String nextSpecifier,
                                                                   int maxRecurseDepth,
                                                                   Set<Object> seen ) {
    if ( eClass == null ) return Collections.emptyList();

    // check recursion
    Pair< Integer, Set< Object > > p = seen( eClass, maxRecurseDepth, seen );
    maxRecurseDepth = p.first;
    seen = p.second;
    if ( maxRecurseDepth < 0 ) return Collections.emptyList();
    
    ArrayList< ModelReference< ? > > list = new ArrayList< ModelReference< ? > >();

// Can't do this since each attribute is a different alternative!
//    list.addAll( getAlternatives( eClass.getEAttributes(), specifier,
//                                  nextSpecifier ) );

    for ( EAttribute e : eClass.getEAttributes() ) {
      list.addAll( getAlternatives( e, specifier, nextSpecifier,
                                    maxRecurseDepth, seen ) );
    }
    for ( EReference e : eClass.getEAllContainments() ) {
      list.addAll( getAlternatives( e, specifier, nextSpecifier,
                                    maxRecurseDepth, seen ) );
    }
    for ( EGenericType e : eClass.getEGenericSuperTypes() ) {
      list.addAll( getAlternatives( e, specifier, nextSpecifier,
                                    maxRecurseDepth, seen ) );
    }
    for ( EOperation e : eClass.getEOperations() ) {
      list.addAll( getAlternatives( e, specifier, nextSpecifier,
                                    maxRecurseDepth, seen ) );
    }
    for ( EReference e : eClass.getEReferences() ) {
      list.addAll( getAlternatives( e, specifier, nextSpecifier,
                                    maxRecurseDepth, seen ) );
    }
    for ( EStructuralFeature e : eClass.getEStructuralFeatures() ) {
      list.addAll( getAlternatives( e, specifier, nextSpecifier,
                                    maxRecurseDepth, seen ) );
    }
    for ( EClass e : eClass.getESuperTypes() ) {
      list.addAll( getAlternatives( e, specifier, nextSpecifier,
                                    maxRecurseDepth, seen ) );
    }
    for ( ETypeParameter e : eClass.getETypeParameters() ) {
      list.addAll( getAlternatives( e, specifier, nextSpecifier,
                                    maxRecurseDepth, seen ) );
    }    
/*
    eClass.getEAttributes();
    eClass.getEAllContainments();
    eClass.getEAllGenericSuperTypes();
    eClass.getEGenericSuperTypes();
    eClass.getEAllOperations();
    eClass.getEOperations();
    eClass.getEAllReferences();
    eClass.getEReferences();
    eClass.getEAllStructuralFeatures();
    eClass.getEStructuralFeatures();
    eClass.getEAllSuperTypes();
    eClass.getESuperTypes();
    eClass.getETypeParameters();
    eClass.getInstanceClass();
    eClass.getInstanceClassName();
*/
    return list;
  }

  /**
   * Add a new ModelReference to the list if the referenceTarget is compatible
   * as a scope for the nextSpecifier and the ModelReference evaluation is not
   * empty.
   * 
   * @param model
   *          the EObject model context or scope
   * @param referenceTarget
   *          the object that is to be directly referenced by the new
   *          ModelReference
   * @param targetRefString
   *          a string used to indicate the scope
   * @param nextSpecifier
   *          a specifier that must be applicable to the reference; otherwise,
   *          null is returned.
   * @param list
   *          the list to which the new ModelReference is added
   * @return the new ModelReference or null if nextSpecifier is not applicable
   *         or the ModelReference evaluation is empty (no objects are being
   *         referenced)
   */
  private static ModelReference< ? >
      tryAddDirectReferenceToList( EObject model, Object referenceTarget,
                                   String targetRefString,
                                   Object nextSpecifier,
                                   List< ModelReference< ? > > list ) {
    ModelReference< ? > nsmr = createNextReference( referenceTarget,
                                                    nextSpecifier );
    if ( nsmr != null && nsmr.isEmpty( true ) ) {
      return null;
    }
    ModelReference< ? > mr =
        new ModelReference( null, null, referenceTarget, targetRefString, null,
                            null, model, null,
                            makeCollectionClass( referenceTarget.getClass() ),
                            referenceTarget.getClass(), false );
    if ( mr.isEmpty() ) return null;
    list.add( mr );
    return mr;
  }
  
  /**
   * Create or fix a ModelReference to the nextSpecifier from the scope.
   * 
   * @param scope
   *          the scope to which nextSpecifier applies; this could be a
   *          ModelReference, an {@link EObject}, or some other {@link Object}.
   * @param nextSpecifier
   *          a specifier String, a ModelReference, or a Call; if a
   *          ModelReference, its scope may be set to the input scope
   * @return the new ModelReference or the fixed nextSpecifier if a
   *         ModelReference
   */
  protected static ModelReference< ? > createNextReference( Object scope,
                                                            Object nextSpecifier ) {
    boolean complainIfScopeNotSame = true;
    ModelReference nsmr = null;
    if ( nextSpecifier instanceof ModelReference ) {
      nsmr = (ModelReference)nextSpecifier;
      if ( !valuesEqual( nsmr.getScope(), scope ) ) {
        if ( complainIfScopeNotSame ) {
          Debug.error( true, "Error! The ModelReference.nextSpecifier's scope is not the ModelReference" );
        }
        nsmr.setScope( scope );
      }
      if ( !Utils.isNullOrEmpty( nsmr.getSpecifier() ) ) {
        Debug.error( true, "Error! The ModelReference.nextSpecifier's scope is not the ModelReference" );
      }
    } else {
      if ( nextSpecifier != null ) {
        if ( nextSpecifier instanceof String ) {
          nsmr = new ModelReference( scope, (String)nextSpecifier, null,
                                     (Class< ? >)null, false );
        } else if ( nextSpecifier instanceof Call ) {
          Call call = (Call)nextSpecifier;
          nsmr = new ModelReference( scope, call, null,
                                     makeCollectionClass( call.getReturnType() ),
                                     false );
        }
      }
    }
    return nsmr;
  }

  /**
   * Get alternative references specified in the EObject scope. Examples:
   * <p>
   * Assume that block1 is selected as the scope in this UML model:
   * 
   * <pre>
   * {@code
   * block1 relation1 block2
   *        -------->
   * }
   * </pre>
   * 
   * <ul>
   * <li>block1.relation1 references relation1 or an object related to block1
   * through relation1.
   * <li>class1.relation1 references a relation defined for class1 (block1's
   * class) or a class related to class1 through relation1.
   * <li>block1.name references either a Java "name" field, Java name(),
   * getName(), or getEName() methods, or a "name" structural feature. The
   * block1 scope may reference the {@link EObject), the {@link EClass}, the
   * MagicDraw {@link com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element},
   * the {@link Element}'s type, or the {@link Class<?>} for any of these.
   * <li>block1.name() is interpreted the same as block1.name.
   * </ul>
   * 
   * @param scope
   * @param specifier
   * @param nextSpecifier
   * @param maxRecurseDepth
   * @return
   * 
   */
  public static <F> List< ModelReference< ? > >
      getAlternatives( EObject scope, String specifier, String nextSpecifier,
                       int maxRecurseDepth, Set<Object> seen ) {
    if ( scope == null ) return Collections.emptyList();
    
    // check recursion
    Pair< Integer, Set< Object > > p = seen( scope, maxRecurseDepth, seen );
    maxRecurseDepth = p.first;
    seen = p.second;
    if ( maxRecurseDepth < 0 ) return Collections.emptyList();
    
    if ( Utils.isNullOrEmpty( specifier ) ) return Collections.emptyList(); // REVIEW -- should this return the scope?

    List< ModelReference< ? > > list =
        getEObjectAlternatives( scope, specifier, nextSpecifier,
                                maxRecurseDepth, seen );

    list.addAll( getObjectAlternatives( scope, specifier, nextSpecifier,
                                        maxRecurseDepth, seen ) );

    return list;
  }

  /**
   * Determine if the object, {@code o}, has already been visited, add {@code o}
   * to the seen set, and decrement the depthLimit if {@code o} is a model
   * element.
   * 
   * @param o
   *          the Object being visited
   * @param depthLimit
   *          how much deeper can we recurse through model elements; 0 indicates
   *          the bottom/leaf of the recursion; -1 indicates that the object has
   *          been seen
   * @param seen
   *          a set of objects that have already been visited
   * @return a pair including the updated depth and seen set.
   */
  protected static Pair< Integer, Set< Object > > seen( Object o, int depthLimit,
                                                        Set< Object > seen ) {
    Pair< Boolean, Set< Object > > p = Utils.seen( o, true, seen );
    if ( !p.first ) {
      depthLimit = -1;
    } else if ( isModelElement( o ) ) {
      --depthLimit;
    }
    Pair< Integer, Set< Object > > pp =
        new Pair< Integer, Set< Object > >( depthLimit, p.second );
    return pp;
  }
  
  public List< ModelReference< ? > > getAlternatives() {
    return alternatives;
  }

  public void setAlternatives( List< ModelReference< ? > > alternatives ) {
    this.alternatives = alternatives;
  }

  public void setScopeReference( Object scopeReference ) {
    this.scopeReference = scopeReference;
  }

  public Object getScopeReference() {
    return scopeReference;
  }

  /**
   * @return the specifier String
   */
  public String getSpecifier() {
    if ( Utils.isNullOrEmpty( specifier ) && expression instanceof Call ) {
      // REVIEW
      setSpecifier( ( (Call)expression ).toShortString() );
    }
    return specifier;
  }

  /**
   * Sets the new specifier String to the input String after trimming empty
   * parenthesis arguments and whitespace.
   * 
   * @param specifier
   *          the new specifier String
   */
  public void setSpecifier( String specifier ) {
    // REVIEW
    this.specifier = specifier.replaceAll( "[(]\\s*[)]", "" ).trim();
  }

  public Parameter< EObject > getModel() {
    return model;
  }

  public void setModel( Parameter< EObject > model ) {
    this.model = model;
  }

  public void setModel( EObject eObject ) {
    Parameter< EObject > m = new Parameter< EObject >( "", null, eObject, null );
    setModel( m );
  }

  // public List< Object > getCandidateResults() {
  // return candidateResults;
  // }
  //
  // public void setCandidateResults( List< Object > candidateResults ) {
  // this.candidateResults = candidateResults;
  // }

  /**
   * @return the scopeReferenceString
   */
  public String getScopeReferenceString() {
    return scopeReferenceString;
  }

  /**
   * @param scopeReferenceString the scopeReferenceString to set
   */
  public void setScopeReferenceString( String scopeReferenceString ) {
    this.scopeReferenceString = scopeReferenceString;
  }

  /**
   * @param singleResultType the singleResultType to set
   */
  public void setSingleResultType( Class< T > singleResultType ) {
    boolean needResultType = true;
    if ( getSingleResultType() == null ) {
      setSingleResultType( singleResultType );
    }
    if ( getResultType() == null && getSingleResultType() != null ) {
      setResultType( makeCollectionClass( this.singleResultType ) );
    }
    if ( getSingleResultType() == null && getResultType() != null ) {
      Class<T> cls = null;
      try  {
        cls = (Class< T >)ClassUtils.getSingleGenericParameterType( getResultType() );
      } catch (ClassCastException e) {}
      if ( cls == null ) {
        Object o = evaluate(true);
        if ( o instanceof Collection ) {
          Collection<?> coll = (Collection< ? >)o; 
          if ( !coll.isEmpty() ) {
            try  {
              cls = (Class< T >)coll.iterator().next().getClass();
            } catch (ClassCastException e) {}
          }
        }
      }
      this.singleResultType = cls;
    }
    if ( getSingleResultType() == null ) {
              Debug.error( "Error! Types incompatible for resultType: type param = "
//                           + cls.getSimpleName()
                           + "; resultType="
                           + resultType.getSimpleName() );
    } else {
      needResultType = false;
    }
//    if ( needResultType ) {
//      this.singleResultType = ;
//    }
  }

  /**
   * Return the type of element a Collection contains based on its generic
   * TypeParameters.  PROBABLY DOESN'T WORK!
   * 
   * @param collType
   * @return
   */
  public static < TT > Class< TT >
      getElementTypeOfType( Class< ? extends Collection< TT > > collType ) {
    if ( collType == null ) return null;
    TypeVariable< ? >[] params = collType.getTypeParameters();
    if ( params != null && params.length == 1 ) {
      String name = params[ 0 ].getName();
      Class< ? > cls;
      try {
        cls = Class.forName( name );
        return (Class< TT >)cls;
      } catch ( ClassCastException e ) {
      } catch ( ClassNotFoundException e ) {
      }
    }
    return null;
  }

  /**
   * Return the type of element a Collection contains based on its generic
   * TypeParameters. NEED TO TEST!
   * 
   * @param collType
   * @return
   */
  public static <T1, T2 extends Collection< T1 > > Class< T1 > getElementType( T2 coll ) {
    if ( coll == null ) return null;
    Class< ? extends Collection > collType = coll.getClass();
    Class< T1 > elementType = null;
    try {
      elementType =
          getElementTypeOfType( (Class< ? extends Collection<T1>>)collType );
    } catch ( ClassCastException e ) {}
    if ( elementType != null ) return elementType;
    try {
      elementType =
          (Class< T1 >)ClassUtils.mostSpecificCommonSuperclass( coll );
    } catch ( ClassCastException e ) {}
    return elementType;
  }


  /**
   * @param resultType the resultType to set
   */
  @Override
  public void setResultType( Class< ? extends Collection< T > > resultType ) {
    boolean needResultType = true;
    if ( this.resultType == null ) {
      this.resultType = resultType;
    }
    if ( getResultType() != null ) {
      TypeVariable<?>[] params = getResultType().getTypeParameters(); 
      if ( params != null && params.length == 1 ) {
        Object clsObj = params[0].getGenericDeclaration();
        if ( clsObj instanceof Class ) {
          Class< ? > cls = ( (Class< ? >)clsObj );
          if ( !cls.isAssignableFrom( getSingleResultType() ) ) {
            if ( resultType != null ) {
              this.resultType = null;
              setResultType( resultType );
            } else {
              Debug.error( "Error! Types incompatible for singleResultType: type param = "
                           + cls.getSimpleName()
                           + "; singleResultType="
                           + singleResultType.getSimpleName() );
            }
          } else {
            needResultType = false;
          }
        }
      }
    }
    if ( needResultType ) {
      this.resultType = makeCollectionClass( singleResultType );
    }
  }


  
  /**
   * @return the singleExpression
   */
  public Expression< T > getSingleExpression() {
    return singleExpression;
  }

  /**
   * @param singleExpression the singleExpression to set
   */
  public void setSingleExpression( Expression< T > singleExpression ) {
    this.singleExpression = singleExpression;
  }

  /**
   * @return the nextSpecifier
   */
  public Object getNextSpecifier() {
    return nextSpecifier;
  }

  /**
   * @param nextSpecifier the nextSpecifier to set
   */
  public void setNextSpecifier( Object nextSpecifier ) {
    this.nextSpecifier = nextSpecifier;
  }

  /**
   * @return the nextSpecifierString
   */
  public Object getNextSpecifierString() {
    return nextSpecifierString;
  }

  /**
   * @param nextSpecifierString the nextSpecifierString to set
   */
  public void setNextSpecifierString( Object nextSpecifierString ) {
    this.nextSpecifierString = nextSpecifierString;
  }

  /**
   * @param args
   */
  public static void main( String[] args ) {
    // TODO Auto-generated method stub

  }

}
