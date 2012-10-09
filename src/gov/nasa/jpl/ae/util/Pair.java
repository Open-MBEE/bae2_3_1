package gov.nasa.jpl.ae.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pair<A,B> implements Comparable< Pair< A, B > > {
  public A first;
  public B second;

  public Pair(A a, B b) {
    first = a;
    second = b;
  }
  
  @Override
  public String toString() {
    return "(" + first + ", " + second + ")";
  }

  @Override
  public int compareTo( Pair< A, B > o ) {
    if ( o == null ) return 1;
    if ( first == null ) {
      if ( o.first == null ) return 0;
      return -1;
    }
    if ( o.first == null ) return 1;
    int compare = 0;
    if ( first instanceof Comparable ) {
      compare = ((Comparable<A>)first).compareTo( o.first );
    }
    if ( compare != 0 ) return compare;
    if ( second == null ) {
      if ( o.second == null ) return 0;
      return -1;
    }
    if ( o.second == null ) return 1;
    if ( second instanceof Comparable ) {
      compare = ((Comparable<B>)second).compareTo( o.second );
    }
    if ( compare != 0 ) return compare;
    return toString().compareTo( o.toString() );
  }
  
  /**
   * @param c collection of pairs
   * @return a list of pairs' second items
   */
  public static < A, B > List< B > getSeconds( Collection< Pair< A, B > > c ) {
    List< B > seconds = new ArrayList< B >();
    for ( Pair< A, B > p : c ) {
      seconds.add( p.second );
    }
    return seconds;
  }

  /**
   * @param c collection of pairs
   * @return a list of pairs' first items
   */
  public static < A, B > List< A > getFirsts( Collection< Pair< A, B > > c ) {
    List< A > firsts = new ArrayList< A >();
    for ( Pair< A, B > p : c ) {
      firsts.add( p.first );
    }
    return firsts;
  }

}