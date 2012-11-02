package gov.nasa.jpl.ae.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import gov.nasa.jpl.ae.event.Functions.NotEquals;
import gov.nasa.jpl.ae.solver.AbstractRangeDomain;
import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasConstraints;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.ae.solver.Random;
import gov.nasa.jpl.ae.solver.RangeDomain;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.ClassUtils;
import gov.nasa.jpl.ae.util.CompareUtils;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

/**
 * 
 */
public class Parameter< T > extends HasIdImpl implements Cloneable, Groundable,
                            Comparable< Parameter< ? > >, Satisfiable, Node,
                            Variable< T >, LazyUpdate, HasConstraints {
  public static final Set< Parameter< ? > > emptySet =
      new TreeSet< Parameter< ? > >();
  
  // These are for debug validation.
  public static boolean mayPropagate = true;
  public static boolean mayChange = true;

  protected String name = null;
  private Domain< T > domain = null;
  protected T value = null;
  protected ParameterListener owner = null; // REVIEW -- Only one listener!
  protected boolean stale;
  protected List< Constraint > constraintList = new ArrayList< Constraint >();
  
  public Parameter( String n, Domain< T > d, ParameterListener o ) {
    name = n;
    domain = d;
    owner = o;
    stale = true; // not grounded
  }

  // public Parameter( String n, T v ) {
  // name = n;
  // value = v;
  // }
  public Parameter( String n, Domain< T > d, T v, ParameterListener o ) {
    name = n;
//    if ( d != null ) {
      domain = d;
//    } else {
//      domain = new SingleValueDomain< T >(v);
//    }
    value = v;
    owner = o;
    stale = !isGrounded( true, null );
  }

  @SuppressWarnings( "unchecked" )
  public Parameter( String n, Domain< T > d, FunctionCall fc,
                    ParameterListener o, boolean propagate ) {
    name = n;
    domain = d;
    if ( fc != null ) {
      value = (T)fc.evaluate( propagate );
    }
    owner = o;
    stale = !isGrounded( true, null );
  }

//  public Parameter( String n, Domain< T > d, Parameter<T> v, ParameterListener o ) {
//    this(n, d, (v == null) ? null : v.getValue(), o);
//  }

  public Parameter( Parameter< T > parameter ) {
    name = parameter.name;
    value = parameter.value;
    domain = parameter.domain;
    owner = parameter.owner;
    stale = !isGrounded( true, null );
  }

  /*
  public Parameter( String n, Domain d, Expression<T> expression,
                    ParameterListener o ) {
    name = n;
    domain = d;
    owner = o;
    if ( expression != null ) {
      if ( o instanceof ParameterListenerImpl ) {
        ParameterListenerImpl pli = (ParameterListenerImpl)o;
        pli.addDependency( this, expression );
      }
    }
    stale = true; // not grounded
  }
*/
  
  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#clone()
   */
  @Override
  public Object clone() {
    return new Parameter< T >( this );
  }

  @Override
  public boolean equals( Object val ) {
    if ( this == val ) return true;
    if ( value == val ) return true;
    if ( value == null ) return false;
    if ( val == null ) return false;
    if ( val instanceof Parameter && ( (Parameter)val ).valueEquals( value ) ) {
      return true;
    }
    //if ( val instanceof Parameter ) return ( compareTo( (Parameter<?>)val ) == 0 );
    if ( value.equals( val ) ) return true;
    return false;
  }
  
  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  /**
   * @return the domain
   */
  @Override
  public Domain< T > getDomain( boolean propagate, Set< HasDomain > seen ) {
    return domain;
  }

  @Override
  public Domain< T > getDomain() {
    return getDomain( false, null );
  }

  /**
   * @param domain
   *          the domain to set
   */
  @Override
  public void setDomain( Domain< T > domain ) {
    setDomain( domain, true );
  }
  public void setDomain( Domain< T > domain, boolean propagate ) {
    this.domain = domain;
    if ( propagate && owner != null ) {
      //owner.setStaleAnyReferencesTo( this );
      owner.handleDomainChangeEvent( this );
    }
  }

  public T getValueNoPropagate() {
    return value;
  }
  
  @Override
  public T getValue( boolean propagate ) {
    if ( propagate ) return getValue();
    return getValueNoPropagate();
  }

  public T getValue() {
    if ( Debug.isOn() ) Debug.outln( "Parameter.getValue() start: " + this );
    assert mayPropagate;
    if ( isStale() ) {
      if ( owner != null ) { 
        owner.refresh( this );
        if ( Debug.isOn() ) Debug.outln( "Parameter.getValue() refreshed: " + this );
      } else {
        setStale( false );
        if ( Debug.isOn() ) Debug.outln( "Parameter.getValue() no owner for " + this );        
      }
    }
    if ( Debug.isOn() ) Debug.outln( "Parameter.getValue() finish: " + this );
    return value;
  }

  /**
   * @return the Parameter's value, the domain's lower bound, or null.
   */
  public T getValueOrMin() {
    if ( value != null ) return value;
    if ( domain instanceof RangeDomain ) {
      return (T)((RangeDomain<T>)domain).getLowerBound();
    }
    return null;
  }
  
  /**
   * @return the Parameter's value, the domain's upper bound, or null.
   */
  public T getValueOrMax() {
    if ( value != null ) return value;
    if ( domain instanceof RangeDomain ) {
      return (T)((RangeDomain<T>)domain).getUpperBound();
    }
    return null;
  }
  
  public Object getMember( String fieldName ) {
    T v = getValueNoPropagate();
    if ( v == null ) return null;
    Object f = ClassUtils.getFieldValue( v, fieldName );
    return f;
  }
  
  public <T1> boolean valueEquals( T1 otherValue ) {
    return value == otherValue || ( value != null && value.equals( otherValue ) );
  }
  
  public Class< ? > getType() {
    if ( domain != null && domain.getType() != null ) {
      return domain.getType();
    }
    if ( value != null ) {
      return value.getClass();
    }
    return null;
  }
  
  @Override
  public void setValue( T value ) {
    setValue( value, true ); // TODO -- REVIEW -- use a global usingLazyUpdate?
  }
  // setValue( value, false ) is lazy/passive updating
  // setValue( value, true ) is proactive updating
  protected void setValue( T val, boolean propagateChange ) {
    if ( Debug.isOn() ) Debug.outln( "Parameter.setValue(" + val + ") start: " + this );
    assert !propagateChange || mayPropagate;
    assert mayChange;
    T castVal = null;
    if ( val instanceof Integer && ((Integer)val).equals(112032)) {
      Debug.out( "" );
    }
    try {
      castVal = (T)Expression.evaluate( val, getType(), propagateChange, false);
      val = castVal;
    } catch ( ClassCastException cce ) {
      cce.printStackTrace();
    }
//    if ( val instanceof Parameter && getType() != null && !getType().isInstance( val ) ) {
//      Object valVal = ( (Parameter< ? >)val ).getValue( propagateChange );
//      T castVal = null;
//      try {
//        castVal = (T)valVal;
//        setValue( castVal, propagateChange );
//      } catch ( ClassCastException cce ) {
//        cce.printStackTrace();
//      }
//      return;
//    }
    boolean changing = !valueEquals( val );
    if ( changing ) {
      if ( owner != null ) {//&& propagateChange ) {
        // lazy/passive updating
        owner.setStaleAnyReferencesTo( this );
      }
     this.value = val;
      constraintList.clear();
      if ( owner != null ) {//&& propagateChange ) {
          owner.handleValueChangeEvent( this );
      }
    }
    setStale( false );
    if ( Debug.isOn() ) Debug.outln( "Parameter.setValue(" + val + ") finish: " + this );
  }

  /**
   * @return the owner
   */
  public ParameterListener getOwner() {
    return owner;
  }

  /**
   * @param owner the owner to set
   */
  public void setOwner( ParameterListener owner ) {
    this.owner = owner;
  }

  @Override
  public boolean isGrounded(boolean deep, Set< Groundable > seen) {
    Pair< Boolean, Set< Groundable > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return true;
    seen = pair.second;
    //if ( owner == null ) return false;
    if ( deep && value instanceof Groundable ) {
      return ( (Groundable)value ).isGrounded(deep, seen);
    }
    return (domain == null || value != null );
  }

//  // Override this!
//  // REVIEW -- consider making this an interface &/or abstract class.
//  public T getDefaultValue() {
//    return null;
//  }
  
  public boolean refresh() {
    if ( owner != null ) {
      if ( owner.refresh( this ) ) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public boolean pickValue() {
    if ( Random.global.nextBoolean() ) {
      return ownerPickValue();
    }
    T value = pickRandomValue();
    if ( value != null ) {
      setValue( value );
      return true;
    }
    return false;
  }
  
  @Override
  public T pickRandomValue() {
    if ( domain == null ) {
      return null;
    }
    T newValue = domain.pickRandomValue();
    String ownerStr = (owner == null) ? "?" : owner.getName(); 
    if ( Debug.isOn() ) Debug.outln( "Picking random value for " + ownerStr + "."
                        + this.name + " from " + this.domain + " --> "
                        + newValue );
    return newValue;
  }
  
  @Override
  public boolean ground( boolean deep, Set< Groundable > seen ) {
    Pair< Boolean, Set< Groundable > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return true;
    seen = pair.second;
    //if ( owner == null ) return false;
    if ( isGrounded(deep, null) ) return true;
    if ( refresh() ) return true;
    T newValue = pickRandomValue();
    if ( newValue != null ) {
      setValue( newValue );
      return true;
    }
    if ( deep && value instanceof Groundable ) {
      ((Groundable)value).ground(deep, seen);
    }
    return isGrounded(deep, null);
  }

  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo( Parameter< ? > o ) {
    return compareTo( o, true );
  }
  public int compareTo( Parameter< ? > o, boolean checkId ) {
    if ( this == o ) return 0;
    if ( o == null ) return 1; // REVIEW -- okay for o to be null? complain?
//    if ( Timepoint.isComparableToTimepoint( o ) ) {
//      return super.compareTo( o );
//    }

    if ( checkId ) {
      return CompareUtils.compare( getId(), o.getId() );
    }
    
    int compare = 0;
//    if ( value == null && o.value != null ) return -1;
//    if ( o.value == null && value != null ) return 1;
//    // REVIEW -- TODO -- doing weird stuff here!!!
//    if ( value instanceof Parameter && !( o.value instanceof Parameter ) ) {
//      Debug.errln("Parameters of parameters!");
//      Parameter<?> p = (Parameter)value;
//      if ( !p.isGrounded( false, null ) ) return -1;
//      return p.compareTo(o);
//    }
//    if ( !(value instanceof Parameter) && o.value instanceof Parameter ) {
//      Debug.errln("Parameters of parameters!");
//      Parameter<?> p = (Parameter)o.value;
//      if ( !p.isGrounded( false, null ) ) return 1;
//      return compareTo(p);
//    }
//    if ( value != null && value.getClass().isAssignableFrom( o.value.getClass() ) ) {
//      if ( value instanceof Comparable ) {
//        T oValue = (T)o.value;
//        compare = ((Comparable<T>)value).compareTo( oValue );
//      } else {
//        compare = value.toString().compareTo( o.value.toString() );
//      }
//      if ( compare != 0 ) return compare;
//    }
    compare = CompareUtils.compare( name, o.name, true );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( getClass().getName(), o.getClass().getName(), true );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( getDomain(), o.getDomain(), true );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( getType(), o.getType(), true );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( getOwner(), o.getOwner(), true );
    if ( compare != 0 ) return compare;
//    Debug.errln("Parameter.compareTo() potentially accessing value information");
    compare = CompareUtils.compare( getValue(), o.getValue(), true );
    if ( compare != 0 ) return compare;
//    compare = CompareUtils.compareTo( this, o, false );
//    if ( compare != 0 ) return compare;

//    // this assumes domains do not change
//    if ( domain != null && o.domain != null && domain.getType() != null
//         && o.domain.getType() != null ) {
//      compare = domain.getType().getName().compareTo( o.domain.getType().getName() );
//      if ( compare != 0 ) return compare;
//    }
//    // TODO -- HACK -- Doing this so that timelines keyed with Timepoint can
//    // keep reservations separate for different Timepoints that occur at the
//    // same time. Correct thing to do would be to have unique names (maybe using
//    // scope).
//    if ( owner == null && o.owner != null ) return -1;
//    if ( owner != null && o.owner == null ) return 1;
//    if ( owner == null && o.owner == null ) {
//      return Utils.intCompare( hashCode(), o.hashCode() );
//    }
//    return Utils.intCompare( owner.hashCode(), o.owner.hashCode() );
//    System.err.println("compareTo() getting two different parameters with the same names and hash codes seems very unlikely. p1=" + this + ", p2=" + o );
    return compare; 
  }

  public boolean inDomain() {
    boolean inDom = false;
    try {
      inDom = domain == null || domain.size() == 0
              || ( value != null && domain.contains( value ) );
    } catch ( ClassCastException e ) {
      if ( Debug.isOn() ) Debug.errln( "Warning! Parameter value and domain types do not match! " + this );
      if ( value instanceof Parameter ) {
        if ( Debug.isOn() ) Debug.errln( "Warning! Parameter inside Parameter! " + this );
        inDom = ( (Parameter<?>)value ).inDomain();
      }
    }
    return inDom;
  }
  
  @Override
  public boolean isSatisfied(boolean deep, Set< Satisfiable > seen) {
    Pair< Boolean, Set< Satisfiable > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return true;
    seen = pair.second;
    //if ( owner == null ) return false;
    boolean nullDomain = domain == null;
    if ( nullDomain ) return true;
    boolean emptyDomain = domain.size() == 0;
    if ( emptyDomain ) return true;
    boolean grounded = isGrounded(deep, null);
    boolean stale = isStale();
    boolean inDom = inDomain();
    if (!(grounded && !stale && inDom)) return false;
    T v = getValueNoPropagate();
    if ( deep && v != null && v instanceof Satisfiable ) {
      if ( !((Satisfiable)v).isSatisfied(deep, seen) ) return false;
    }
    return true;
  }

  @Override
  public boolean satisfy(boolean deep, Set< Satisfiable > seen) {
    Pair< Boolean, Set< Satisfiable > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return true;
    seen = pair.second;
    if ( owner == null ) return false;
    if ( isSatisfied(deep, null) ) return true;
    ground(deep, null);
    getValue();
    if ( isSatisfied(deep, null) ) return true;
    refresh();
    if ( isSatisfied(deep, null) ) return true;
    T newValue = pickRandomValue();
    if ( newValue != null ) {
      setValue( newValue, true );
    }
    if ( isSatisfied(deep, null) ) return true;
    ownerPickValue();
    if ( isSatisfied(deep, null) ) return true;
    if ( deep && value instanceof Satisfiable ) {
      ((Satisfiable)value).satisfy(deep, seen);
    }
    return isSatisfied(deep, null);
  }
  
  protected boolean ownerPickValue() {
    if ( owner != null ) {//&& owner instanceof ParameterListenerImpl ) {
      if ( ((ParameterListener)owner).pickValue( this ) ) return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return toString( true, false, true, null );
  }
  
  public String toString( boolean withOwner, boolean withHash, boolean deep, Set< Object > seen ) {
    Pair< Boolean, Set< Object > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) deep = false;
    seen = pair.second;
    StringBuffer sb  = new StringBuffer();
    if ( withOwner && getOwner() != null ) {
      if ( Utils.isNullOrEmpty( getOwner().getName() ) ) {
        sb.append( "<EVT>@" + getOwner().hashCode() + ":");
      } else {
        sb.append( getOwner().getName() + ":");
      }
    }
    if ( !deep ) {
      sb.append( getName() );
    } else if ( isGrounded(false, null) ) {
      sb.append( getName() + "=" + getValueNoPropagate() );
    } else if ( getDomain() != null ) {
      sb.append( getName() + "=" + getDomain() );
    } else {
      sb.append( getName() + "(ungrounded, null domain)" );
    }
    if ( withHash ) {
      sb.append("(" + hashCode() + ")" );
    }
    return sb.toString();
  }

  @Override
  public boolean isStale() {
    return stale;
  }

  @Override
  public void setStale( boolean staleness ) {
    if ( Debug.isOn() ) Debug.outln( "setStale(" + staleness + ") to " + this );
    if ( name.contains( "effect65Var" ) ) {
      Debug.out( "" );
    }
    stale = staleness;
  }

  public Collection< Constraint > getConstraints( boolean deep,
                                                  Set<HasConstraints> seen ) {
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    
    // check for cached constraints
    if ( !Utils.isNullOrEmpty( constraintList ) ) return constraintList;
    if ( constraintList == null ) {
      constraintList = new ArrayList< Constraint >();
    }
    
    // get domain constraints
    Method method;
    if ( domain != null && domain instanceof AbstractRangeDomain
         && ( value == null || value instanceof Comparable ) ) {
      constraintList.addAll( ( (AbstractRangeDomain<T>)domain ).getConstraints( this ) );
    } else {
      try {
        method = getClass().getMethod( "inDomain", (Class< ? >[])null );
        constraintList.add( new ConstraintExpression( new FunctionCall( this, method,
                                                               (Object[])null ) ) );
      } catch ( NoSuchMethodException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( SecurityException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
//    NotEquals< T > f =
//        new Functions.NotEquals< T >( new Expression< T >( value ),
//                                      new Expression< T >( (T)null ) );
//    cList.add( new ConstraintExpression( f ) );

    // get constraints in the value
    if ( deep ) {
      T v = getValueNoPropagate(); 
      if ( v != null && 
//           v instanceof ParameterListenerImpl ) {
//        cList.addAll( ((ParameterListenerImpl)v).getConstraints( deep, seen ) );
//      } else if ( v != null && 
                  v instanceof HasConstraints ) {
        constraintList.addAll( ((HasConstraints)v).getConstraints( deep, seen ) );
      }
    }
    return constraintList;
  }

}
