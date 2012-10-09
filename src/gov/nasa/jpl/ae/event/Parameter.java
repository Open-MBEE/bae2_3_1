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
import gov.nasa.jpl.ae.solver.Random;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

/**
 * 
 */

/**
 * @author bclement
 * 
 */
public class Parameter< T > implements Cloneable, Groundable,
                            Comparable< Parameter< ? > >, Satisfiable, Node,
                            Variable< T >, LazyUpdate, HasConstraints {
  public static final Set< Parameter< ? > > emptySet =
      new TreeSet< Parameter< ? > >();

  protected String name = null;
  private Domain< T > domain = null;
  private T value = null;
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
    stale = !isGrounded();
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
    stale = !isGrounded();
  }

//  public Parameter( String n, Domain< T > d, Parameter<T> v, ParameterListener o ) {
//    this(n, d, (v == null) ? null : v.getValue(), o);
//  }

  public Parameter( Parameter< T > parameter ) {
    name = parameter.name;
    value = parameter.value;
    domain = parameter.domain;
    owner = parameter.owner;
    stale = !isGrounded();
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
    if ( value == null ) return val == null;
    return value.equals( val );
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
  public Domain< T > getDomain() {
    return domain;
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

  public Object getValue( boolean propagate ) {
    if ( propagate ) return getValue();
    return getValueNoPropagate();
  }

  @Override
  public T getValue() {
    Debug.outln( "Parameter.getValue() start: " + this );
    if ( isStale() ) {
      if ( owner != null ) { 
        owner.refresh( this );
        Debug.outln( "Parameter.getValue() refreshed: " + this );
      } else {
        setStale( false );
        Debug.outln( "Parameter.getValue() no owner for " + this );        
      }
    }
    Debug.outln( "Parameter.getValue() finish: " + this );
    return value;
  }

  public Object getMember( String fieldName ) {
    T v = getValueNoPropagate();
    if ( v == null ) return null;
    Object f = Utils.getFieldValue( v, fieldName );
    return null;
  }
  
  public boolean valueEquals( T otherValue ) {
    return value == otherValue || ( value != null && value.equals( otherValue ) );
  }
  
  public static <T1> boolean valuesEqual( T1 v1, T1 v2 ) {
    return v1 == v2 || ( v1 != null && v1.equals( v2 ) );
  }
  
  @Override
  public void setValue( T value ) {
    Debug.outln( "Parameter.setValue(" + value + ") start: " + this );
    setValue( value, false ); // TODO -- REVIEW -- use a global usingLazyUpdate?
    Debug.outln( "Parameter.setValue(" + value + ") finish: " + this );
  }
  // setValue( value, false ) is lazy/passive updating
  // setValue( value, true ) is proactive updating
  public void setValue( T value, boolean propagateChange ) {
    boolean changing = !valueEquals( value );
    if ( changing ) {
      this.value = value;
      constraintList.clear();
      if ( owner != null ) {
        if ( propagateChange ) {
          owner.handleValueChangeEvent( this );
        } else {
          // lazy/passive updating
          owner.setStaleAnyReferencesTo( this );
        }
      }
    }
    setStale( false );
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
  public boolean isGrounded() {
    if ( value instanceof Groundable ) {
      return ( (Groundable)value ).isGrounded();
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
    Debug.outln( "Picking random value for " + ownerStr + "."
                        + this.name + " from " + this.domain + " --> "
                        + newValue );
    return newValue;
  }
  
  @Override
  public boolean ground() {
    if ( isGrounded() ) return true;
    if ( refresh() ) return true;
    T newValue = pickRandomValue();
    if ( newValue != null ) {
      setValue( newValue );
      return true;
    }
    if ( value instanceof Groundable ) {
      ((Groundable)value).ground();
    }
    return isGrounded();
  }

  @Override
  public int compareTo( Parameter< ? > o ) {
    if ( this == o ) return 0;
    if ( o == null ) return 1; // REVIEW -- okay for o to be null? complain?
    int compare = 0;
    if ( value == null && o.value != null ) return -1;
    if ( o.value == null && value != null ) return 1;
    // REVIEW -- TODO -- doing weird stuff here!!!
    if ( value instanceof Parameter && !( o.value instanceof Parameter ) ) {
      Parameter<?> p = (Parameter)value;
      if ( !p.isGrounded() ) return -1;
      return p.compareTo(o);
    }
    if ( !(value instanceof Parameter) && o.value instanceof Parameter ) {
      Parameter<?> p = (Parameter)o.value;
      if ( !p.isGrounded() ) return 1;
      return compareTo(p);
    }
    if ( value != null && value.getClass().isAssignableFrom( o.value.getClass() ) ) {
      if ( value instanceof Comparable ) {
        T oValue = (T)o.value;
        compare = ((Comparable<T>)value).compareTo( oValue );
      } else {
        compare = value.toString().compareTo( o.value.toString() );
      }
      if ( compare != 0 ) return compare;
    }
    if ( name != o.name ) {
      if ( name == null ) return -1;
      if ( o.name == null ) return 1;
      compare = this.name.compareToIgnoreCase( o.name );
      if ( compare != 0 ) return compare;
      compare = this.name.compareTo( o.name );
      if ( compare != 0 ) return compare;
    }
    if ( compare != 0 ) return compare;
    if ( value != null ) {
      compare = value.getClass().getName().compareTo( o.value.getClass().getName() );
      if ( compare != 0 ) return compare;
    }
    // TODO -- HACK -- Doing this so that timelines keyed with Timepoint can
    // keep reservations separate for different Timepoints that occur at the
    // same time. Correct thing to do would be to have unique names (maybe using
    // scope).
    if ( owner == null && o.owner != null ) return -1;
    if ( owner != null && o.owner == null ) return 1;
    if ( owner == null && o.owner == null ) {
      return Utils.intCompare( hashCode(), o.hashCode() );
    }
    return Utils.intCompare( owner.hashCode(), o.owner.hashCode() );
  }

  public boolean inDomain() {
    return domain == null || domain.size() == 0
           || ( value != null && domain.contains( value ) );
  }
  
  @Override
  public boolean isSatisfied() {
    boolean nullDomain = domain == null;
    if ( nullDomain ) return true;
    boolean emptyDomain = domain.size() == 0;
    if ( emptyDomain ) return true;
    boolean grounded = isGrounded();
    boolean stale = isStale();
    boolean inDom = inDomain();
    if (!(grounded && !stale && inDom)) return false;
    T v = getValueNoPropagate();
    if ( v != null && v instanceof Satisfiable ) {
      if ( !((Satisfiable)v).isSatisfied() ) return false;
    }
    return true;
  }

  @Override
  public boolean satisfy() {
    if ( isSatisfied() ) return true;
    ground();
    getValue();
    if ( isSatisfied() ) return true;
    refresh();
    if ( isSatisfied() ) return true;
    T newValue = pickRandomValue();
    if ( newValue != null ) {
      setValue( newValue, true );
    }
    if ( isSatisfied() ) return true;
    ownerPickValue();
    if ( isSatisfied() ) return true;
    if ( value instanceof Satisfiable ) {
      ((Satisfiable)value).satisfy();
    }
    return isSatisfied();
  }
  
  protected boolean ownerPickValue() {
    if ( owner != null ) {//&& owner instanceof ParameterListenerImpl ) {
      if ( ((ParameterListener)owner).pickValue( this ) ) return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return toString( true, false );
  }
  
  public String toString( boolean withOwner, boolean withHash ) {
    StringBuffer sb  = new StringBuffer();
    if ( withOwner && getOwner() != null ) {
      sb.append( getOwner().getName() + ":");
    }
    if ( isGrounded() ) {
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
    Debug.outln( "setStale(" + staleness + ") to " + this );
    stale = staleness;
  }

  public Collection< Constraint > getConstraints( boolean deep,
                                                  Set<HasConstraints> seen ) {
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    if ( !Utils.isNullOrEmpty( constraintList ) ) return constraintList;
    if ( constraintList == null ) {
      constraintList = new ArrayList< Constraint >();
    }
    Method method;
    if ( domain != null && domain instanceof AbstractRangeDomain
         && value instanceof Comparable ) {
      constraintList.addAll( ( (AbstractRangeDomain)domain ).getConstraints( (Comparable)value ) );
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
