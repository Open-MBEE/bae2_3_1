/**
 * 
 */
package gov.nasa.jpl.ae.solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import gov.nasa.jpl.ae.util.Math;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Evaluatable;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Random;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;
import org.junit.Test;

/**
 * A {@link Domain} that is a union of an "include" set of Domains and a
 * subtraction of an "excludeSet." An empty include set allows for any value in
 * the domain.
 *
 */
public class MultiDomain< T >  extends HasIdImpl implements Domain< T > {
  public Class< T > type = null;
  public Set< Domain< T > > includeSet =
      new LinkedHashSet< Domain< T > >();
  public Set< Domain< T > > excludeSet =
      new LinkedHashSet< Domain< T > >();
  public MultiDomain<T> defaultDomain = null;

  /**
   * A single set computed from subtracting/restricting the excluded set from
   * the included set and eliminating overlap among the domains in the included
   * set. There is no guarantee that the flattened set will be the same. It may
   * err on the side of including values that are in the exclude set. Warning!
   * This class does not update the flattened set when the included and excluded
   * sets change, but it is computed by {@link #getFlattenedSet()}
   * when it is null by calling {@link #computeFlattenedSet()}.
   */
  protected Set<Domain<T>> flattenedSet = null;
  
  /**
   * Instantiate an empty domain .
   */
  public MultiDomain() {
    super();
  }
  
  public MultiDomain( Set< Domain< T > > includeSet,
                      Set< Domain< T > > excludeSet ) {
    super();
    this.includeSet = includeSet;
    this.excludeSet = excludeSet;
  }

  public MultiDomain( Class<T> type, Set< Domain< T > > includeSet,
                      Set< Domain< T > > excludeSet ) {
    super();
    this.type = type;
    this.includeSet = includeSet;
    this.excludeSet = excludeSet;
  }
  
  public MultiDomain( MultiDomain< T > multiDomain ) {
    super();
    this.type = multiDomain.type;
    
    this.includeSet = clone(multiDomain.includeSet);
    this.excludeSet = clone(multiDomain.excludeSet);
    this.flattenedSet = clone(multiDomain.flattenedSet);
  }

  protected LinkedHashSet< Domain< T > > clone( Set< Domain< T > > set ) {
    LinkedHashSet< Domain< T > > copy = new LinkedHashSet< Domain< T > >();
    if ( set != null ) {
      for ( Domain< T > d : set ) {
        copy.add( d.clone() );
      }
    }
    return copy;
  }
  
  public boolean include(Domain<T> d) {
    if ( includeSet == null ) {
      includeSet = new LinkedHashSet< Domain< T > >();
    }
    boolean changed = includeSet.add( d );
    return changed;
  }
  
  public boolean exclude(Domain<T> d) {
    if ( excludeSet == null ) {
      excludeSet = new LinkedHashSet< Domain< T > >();
    }
    boolean changed = excludeSet.add( d );
    return changed;
  }
  
  /**
   * Returns a single set computed from subtracting the excluded set
   * from the included set and trying to eliminate overlap among the domains in
   * the included set. Warning! This class does not update the flattened set
   * when the included and excluded sets change, but it is computed by
   * {@link MultiDomain#getFlattenedSet()} when it is null by calling
   * {@link MultiDomain#computeFlattenedSet()}.
   */
  public Set< Domain< T > > getFlattenedSet() {
    if ( flattenedSet == null ) {
      computeFlattenedSet();
    }
    return flattenedSet;
  }

  public Set< Domain< T > > computeFlattenedSet() {
    computeFlattenedSetWithMagnitude(false);
    return flattenedSet;
  }

  /**
   * Modify the input list of Domains to merge overlaps,
   * @param domainList
   * @param <T>
   */
  protected static <T> void mergeOverlaps( List< Domain< T > > domainList ) {
    boolean removed[] = new boolean[ domainList.size() ];
    for ( int i = 0; i < domainList.size(); ++i ) {
      removed[ i ] = false;
    }
    for ( int i = 0; i < domainList.size(); ++i ) {
      if ( removed[ i ] ) continue;
      Domain< T > d1 = domainList.get( i );
      if ( !( d1 instanceof AbstractRangeDomain ) ) continue;
      AbstractRangeDomain< T > rd1 = (AbstractRangeDomain< T >)d1;

      for ( int j = 0; j < domainList.size(); ++j ) {
        if ( i == j ) continue;
        if ( removed[ j ] ) continue;
        Domain< T > d2 = domainList.get( j );
        if ( !( d2 instanceof AbstractRangeDomain ) ) continue;
        AbstractRangeDomain< T > rd2 = (AbstractRangeDomain< T >)d2;
        if ( rd1.union( rd2 ) ) {
          removed[ j ] = true;
        }
      }
    }
    for ( int i = removed.length - 1; i >= 0; --i ) {
      if ( removed[ i ] ) {
        domainList.remove( i );
      }
    }

    // Subtract overlap with preceding (or following?) domains in the list.
    // There may still be overlap for domains that are not AbstractRangeDomains.
    removed = new boolean[ domainList.size() ];
    for ( int i = 0; i < domainList.size(); ++i ) {
      removed[ i ] = false;
    }
    for ( int i = 0; i < domainList.size(); ++i ) {
      if ( removed[ i ] ) continue;
      Domain< T > d1 = domainList.get( i );
      for ( int j = i + 1; j < domainList.size(); ++j ) {
        Domain< T > d2 = domainList.get( j );
        d2 = d2.subtract( d1 );
        if ( d2.isEmpty() ) {
          removed[j] = true;
        } else {
          domainList.set( j, d2 );
        }
      }
    }

    for ( int i = removed.length - 1; i >= 0; --i ) {
      if ( removed[ i ] ) {
        domainList.remove( i );
      }
    }

  }

  public long computeFlattenedSetWithMagnitude(boolean bailIfNotEmpty) {

    long mag = 0;
    
    // TODO -- implement subtract methods in the Domain classes so this works!
    
    // Make a deep copy of the included domains to modify.
    List< Domain< T > > flattenedList = new ArrayList< Domain<T> >(clone(includeSet));//.asList( includeSet );
    //flattenedSet = new LinkedHashSet< Domain< T > >();
//    for ( Domain< T > d : includeSet ) {
//      Domain< T > c = d.clone();
//      flattenedSet.add( c );
//    }
    
    // Subtract excluded domains
    for ( int i = 0; i < flattenedList.size(); ++i ) {
    //for ( Domain< T > d : flattenedList ) {
      Domain<T> d = flattenedList.get( i );
      if ( excludeSet != null ) {
        for ( Domain< T > ed : excludeSet ) {
          d = d.subtract( ed );
          //flattenedList.add( c );
          if ( d instanceof MultiDomain ) {
            ((MultiDomain) d).fixToIncludeBounds();
          }
        }
        flattenedList.set( i, d );
      }
      if ( bailIfNotEmpty && d.magnitude() > 0 ) {
        return 1;
      }
    }

    // merge domains that overlap.
    mergeOverlaps( flattenedList );

    for ( int i = 0; i < flattenedList.size(); ++i ) {
      //for ( Domain< T > d : flattenedList ) {
      Domain<T> d = flattenedList.get(i);
      long d1mag = d.magnitude();
      if ( d1mag > 0 ) {
        mag = Math.plus(mag, d1mag);
      }
    }

    flattenedSet = new LinkedHashSet< Domain< T > >( flattenedList );

    return mag;
  }

  /**
   * Clean up finite ranges to include the bounds in the include/exclude sets.
   * This just calls {@link AbstractFiniteRangeDomain#fixToIncludeBounds()} where it can.
   * @return whether any domain was fixed
   */
  public boolean fixToIncludeBounds() {
    boolean changed = false;
    List< Set< Domain< T > > > sets = Utils.newList(includeSet, excludeSet);
    for ( Set< Domain< T > > set : sets ) {
      if ( set == null ) continue;
      for ( Domain< T > d : set ) {
        if ( d == null ) continue;
        if (d instanceof AbstractFiniteRangeDomain) {
          boolean didFix = ((AbstractFiniteRangeDomain) d).fixToIncludeBounds();
          changed = changed || didFix;
        } else if ( d instanceof MultiDomain ) {
          boolean didFix = ((MultiDomain) d).fixToIncludeBounds();
          changed = changed || didFix;
        }
      }
    }
    return changed;
  }

  @Override
  public Class< ? > getType() {
    if ( type != null ) return type;
    ArrayList< Class< ? extends Object > > classes = new ArrayList< Class<? extends Object> >();
    if ( includeSet != null ) {
      classes.add( ClassUtils.mostSpecificCommonSuperclass( includeSet ) );
    }
    if ( excludeSet != null ) {
      classes.add( ClassUtils.mostSpecificCommonSuperclass( excludeSet ) );
    }
    Class< ? > cls = ClassUtils.mostSpecificCommonSuperclass( classes );
    return cls;
  }

  @Override
  public String getTypeNameForClassName( String className ) {
    return ClassUtils.parameterPartOfName( className, false );
  }

  @Override
  public Class< ? > getPrimitiveType() {
    Class< ? > c = null;
    if ( getType() != null ) {
      c = ClassUtils.primitiveForClass( getType() );
      if ( c == null && this.getValue(false) != null
           && Wraps.class.isInstance( getValue(false) ) ) {
        c = ( (Wraps< ? >)getValue(false) ).getPrimitiveType();
      }
    }
    return c;
  }

  @Override
  public T getValue( boolean propagate ) {
    if ( includeSet != null ) {
      for ( Domain<T> d : includeSet ) {
        T t = d.getValue( propagate );
        if ( t != null && contains( t ) ) return t;
      }
    }
    return null;
  }

  @Override
  public void setValue( T value ) {
    includeSet = new LinkedHashSet< Domain< T > >();
    excludeSet = new LinkedHashSet< Domain< T > >();
    includeSet.add( new SingleValueDomain< T >( value ) );
  }

  @Override
  public < V > V evaluate( Class< V > cls, boolean propagate ) {
    V v = Evaluatable.Helper.evaluate( this, cls, true, propagate, false, null );
    if ( v != null ) return v;
    
    if ( magnitude() == 1 ) {
      T t = null;
      if ( cls == null || getType() == null || cls.isAssignableFrom( getType() ) ) {
        t = getValue( propagate );
        if ( cls == null || cls.isInstance( t ) ) {
          return (V)t;
        }
      }
      v = Evaluatable.Helper.evaluate( t, cls, true, propagate, true, null );
      if (v != null) {
        return v;
      }
    }
    if ( cls == null || cls.equals( Object.class ) ) {
      return (V)this;
    }
    return null;
  }

  @Override
  public Domain< T > clone() {
    return new MultiDomain<T>(this);
  }

  @Override
  public long magnitude() {
    computeFlattenedSet();
    long m = 0;
    for ( Domain<T> d : flattenedSet ) {
      long mm = d.magnitude();
      if ( mm >= Long.MAX_VALUE ) return mm;
      if ( mm > 0 ) {
        m += mm;
      }
    }
    return m;
  }

  @Override
  public boolean isEmpty() {
    return magnitude() == 0;
  }

  @Override
  public boolean clearValues() {
    boolean changed = !isEmpty();
    includeSet.clear();
    excludeSet.clear();
    flattenedSet.clear();
    return changed;
  }

  @Override
  public boolean contains( T t ) {
    // initialize to true if there are no include constraints.
    boolean isIncluded = Utils.isNullOrEmpty( includeSet );
    
    if ( !isIncluded ) {
      for ( Domain< T > d : includeSet ) {
        if ( d.contains( t ) ) {
          isIncluded = true;
          break;
        }
      }
    }
    
    if ( isIncluded && excludeSet != null ) {
      for ( Domain< T > d : excludeSet ) {
        if ( d.contains( t ) ) {
          isIncluded = false;
          break;
        }
      }
    }
    
    return isIncluded;
  }

  @Override
  public T pickRandomValue() {
    long mag = magnitude();
    if ( mag <= 0 ) return null;
    double r = Random.global.nextDouble();
    double rMag = r * mag;
    if ( Math.isInfinity( mag ) ) {
       // TODO -- use size()
    }

    long magSoFar = 0;
    
    for ( Domain<T> d : flattenedSet ) {
      long thisMag = d.magnitude();
      if ( Math.isInfinity( thisMag ) ) {
        magSoFar = Long.MAX_VALUE;
      } else if ( thisMag > 0 ) {
        magSoFar += thisMag;
      }
      if ( magSoFar >= rMag ) {
        return d.pickRandomValue();
      }
    }
    if (!flattenedSet.isEmpty()) {
      // We should never get here, but just in case . . .
      Domain< T > d = (Domain<T>)Utils.asList( flattenedSet ).get( flattenedSet.size() - 1);
      return d.pickRandomValue();
    }
    
    return null;
  }

  @Override
  public T pickRandomValueNotEqual( T t ) {
    long z = magnitude();
    if ( z == 0 ) return null;
    if ( z == 1 ) {
      if ( this.contains( t ) ) {
        return null;
      } else {
        return this.getValue(false);
      }
    }
    T tt = null;
    for ( int i=0; i < 5; ++i ) {
      tt = pickRandomValue();
      if ( !tt.equals( t ) ) {
          return tt;
      }
    }
    tt = this.getValue(false);
    if ( tt != null && !tt.equals( t ) ) {
      return tt;
    }
    return null;
  }

  @Override
  public boolean isInfinite() {
    return Math.isInfinity( magnitude() );
  }

  @Override
  public boolean isNullInDomain() {
    boolean isIncluded = Utils.isNullOrEmpty( includeSet );
    
    if ( !isIncluded ) {
      for ( Domain< T > d : includeSet ) {
        if ( d.isNullInDomain() ) {
          isIncluded = true;
          break;
        }
      }
    }
    
    if ( isIncluded && excludeSet != null ) {
      for ( Domain< T > d : excludeSet ) {
        if ( d.isNullInDomain() ) {
          isIncluded = false;
          break;
        }
      }
    }
    
    return isIncluded;
  }

  @Override
  public MultiDomain< T > getDefaultDomain() {
    if ( defaultDomain == null ) {
      defaultDomain = new MultiDomain<T>();
    }
    return defaultDomain;
  }

//  @Override
//  public void setDefaultDomain( Domain< T > domain ) {
//    defaultDomain = domain;
//  }

  @Override
  public boolean restrictToValue( T v ) {
    if ( !contains( v ) ) return false;
    if ( magnitude() == 1 ) return false;
    
    Domain< T > found = null;
    if ( includeSet != null ) {
      for ( Domain< T > d : includeSet ) {
        if ( d.contains( v ) ) {
          found = d;
          break;
        }
      }
    }
    if ( found == null ) return false;
    includeSet.clear();
    found.restrictToValue( v );
    includeSet.add( found );

    if ( excludeSet != null ) {
      for ( Domain< T > d : (List<Domain>)Utils.asList( excludeSet, Domain.class ) ) {
        if ( d.contains( v ) ) {
          Set< Domain< T > > s = new TreeSet< Domain< T > >();
          s.add( new SingleValueDomain< T >( v ) );
          MultiDomain< T > md =
              new MultiDomain< T >( type, Utils.newSet( d ), s );
          excludeSet.remove( d );
          excludeSet.add( md );
        }
      }
    }
    return true;
  }

  @Override
  public < TT > boolean restrictTo( Domain< TT > domain ) {
    boolean changed = Utils.isNullOrEmpty( includeSet );

    if ( !changed ) {
      List<Domain<T>> dList = Utils.asList( includeSet );
      includeSet.clear();
      for ( Domain< T > d : dList ) {
        if ( d.restrictTo(d) ) {
          changed = true;
          if ( !d.isEmpty() ) {
            includeSet.add( d );
          }
        } else {
          includeSet.add( d );
        }
      }
    }
    
    return changed;
  }

  @Override
  public < TT > Domain<TT> subtract( Domain< TT > domain ) {
    MultiDomain<T> clone = (MultiDomain< T >)this.clone();
    if ( getType() != null && domain.getType() != null && getType().isAssignableFrom( domain.getType() ) ) {
      @SuppressWarnings( "unchecked" )
      Domain<T> copy = (Domain<T>)domain.clone();
      clone.excludeSet.add( copy );
      return (Domain< TT >)clone;
    }
    //MultiDomain<TT> md = new MultiDomain<TT>( (Class<TT>)domain.getType(), (Set<Domain<TT>>)null, (LinkedHashSet<Domain<TT>>)clone((Set<Domain<TT>>)excludeSet) )
    LinkedHashSet<Domain<T>> newInclude = new LinkedHashSet<Domain<T>>();
    for ( Domain< T > d : clone.includeSet ) {
        Domain<TT> dd = d.subtract( domain );
        if ( dd != null && !dd.isEmpty() ) {
          newInclude.add((Domain<T>) dd);
        }
    }
    clone.includeSet = newInclude;
    return (Domain< TT >)clone;
  }

  protected static String toString(Collection<?> coll) {
    String s = MoreToString.Helper.toString( coll, false, false, null, Utils.newMap( new Pair<String,Object>("prefix",MoreToString.CURLY_BRACES) ), true );
    return s;
  }
  
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append( "include " );
    
    // We don't want to print "null" for the sets.
    if ( includeSet == null ) {
      includeSet = new LinkedHashSet< Domain<T> >();
    }
    if ( excludeSet == null ) {
      excludeSet = new LinkedHashSet< Domain<T> >();
    }
    
    if ( includeSet != null ) {
      String s = toString(includeSet);
      sb.append( s );
    }
    sb.append( " exclude " );
    if ( excludeSet != null ) {
      String s = toString(excludeSet);
      sb.append( s );
    }

    return sb.toString();
  }
  
  /**
   * @param args
   */
  @Test
  public static void main( String[] args ) {
    Domain<Integer> id1 = new IntegerDomain( 3, 8 );
    Domain<Integer> id2 = new IntegerDomain( 6, 9 );
    Domain<Integer> ed1 = new IntegerDomain( 1, 3 );
    Domain<Integer> ed2 = new IntegerDomain( 7, 8 );
    Set<Domain<Integer> > s1 = new LinkedHashSet< Domain<Integer> >();
    s1.add( id1 );
    s1.add( id2 );
    Set<Domain<Integer> > s2 = new LinkedHashSet< Domain<Integer> >();
    s2.add( ed1 );
    s2.add( ed2 );
    MultiDomain< Integer > md = new MultiDomain<Integer>( Integer.class, s1, s2 );
    System.out.println( "MultiDomain = " + md );
    System.out.println( "flattened set = " + toString( md.getFlattenedSet() ) );
  }


}
