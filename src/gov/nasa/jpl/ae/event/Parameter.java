package gov.nasa.jpl.ae.event;

import java.util.Set;
import java.util.TreeSet;

import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.Debug;

/**
 * 
 */

/**
 * @author bclement
 * 
 */
public class Parameter< T > implements Cloneable,
                                                   Groundable,
                                                   Comparable< Parameter< ? > >,
                                                   Satisfiable,
                                                   Node,
                                                   Variable<T>,
                                                   LazyUpdate {
  public static final Set< Parameter< ? > > emptySet =
      new TreeSet< Parameter< ? > >();

  protected String name = null;
  private Domain< T > domain = null;
  private T value = null;
  protected ParameterListener owner = null; // REVIEW -- Only one listener!
  protected boolean stale;

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

  public Parameter( Parameter< T > parameter ) {
    name = parameter.name;
    value = parameter.value;
    domain = parameter.domain;
    owner = parameter.owner;
    stale = !isGrounded();
  }

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

  @Override
  public void setValue( T value ) {
    Debug.outln( "Parameter.setValue(" + value + ") start: " + this );
    setValue( value, false ); // TODO -- REVIEW -- use a global usingLazyUpdate?
    Debug.outln( "Parameter.setValue(" + value + ") finish: " + this );
  }
  // setValue( value, false ) is lazy/passive updating
  // setValue( value, true ) is proactive updating
  public void setValue( T value, boolean propagateChange ) {
    this.value = value;
    if ( owner != null ) {
      if ( propagateChange ) {
        owner.handleValueChangeEvent( this );
      } else {
        // lazy/passive updating
        owner.setStaleAnyReferencesTo( this );
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
    return ( value != null );
  }

//  // Override this!
//  // REVIEW -- consider making this an interface &/or abstract class.
//  public T getDefaultValue() {
//    return null;
//  }
  
  @Override
  public boolean ground() {
    if ( isGrounded() ) return true;
    if ( owner != null ) {
      if ( owner.refresh( this ) ) {
        return true;
      }
    }
    if ( domain == null ) {
      //T t = getDefaultValue();
      return false;
    }
    T newValue = domain.pickRandomValue();
    String ownerStr = (owner == null) ? "?" : owner.getName(); 
    Debug.outln( "Picking random value for " + ownerStr + "."
                        + this.name + " from " + this.domain + " --> "
                        + newValue );
    setValue( newValue );
    return true;
  }

  @Override
  public int compareTo( Parameter< ? > o ) {
    if ( this == o ) return 0;
    if ( o == null ) return 1; // REVIEW -- okay for o to be null? complain?
    int compare = 0;
    if ( value == null && o.value != null ) return -1;
    if ( o.value == null && value != null ) return 1;
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
    if ( owner.hashCode() < o.owner.hashCode() ) return -1;
    if ( owner.hashCode() > o.owner.hashCode() ) return 1;
    return 0;
    // Integer.compare() not supported for Java 1.6?? 
    //return Integer.compare( owner.hashCode(), o.owner.hashCode() );
  }

  @Override
  public boolean isSatisfied() {
    return isGrounded() && !isStale() && domain.contains( value );
  }

  @Override
  public boolean satisfy() {
    if ( !isSatisfied() ) {
      value = null;
      return ground();
    }
    return true;
  }
  
  @Override
  public String toString() {
    return toString( true );
  }
  
  public String toString( boolean withOwner ) {
    StringBuffer sb  = new StringBuffer();
    if ( withOwner && getOwner() != null ) {
      sb.append( getOwner().getName() + ":");
    }
    if ( isGrounded() ) {
      sb.append( getName() + "=" + getValueNoPropagate() );
    } else {
      sb.append( getName() + "=" + getDomain() );
    }
    return sb.toString();
  }

  @Override
  public boolean isStale() {
    return stale;
  }

  @Override
  public void setStale( boolean staleness ) {
    stale = staleness;
  }
}
