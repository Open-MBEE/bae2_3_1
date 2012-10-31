package gov.nasa.jpl.ae.util;

import gov.nasa.jpl.ae.event.EventInvocation;
import gov.nasa.jpl.ae.solver.HasId;

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
    return compareTo( o, true );
  }
  public int compareTo( Pair< A, B > o, boolean checkId ) {
    if ( this == o ) return 0;
    if ( o == null ) return 1;
    boolean bothHaveId = (this instanceof HasId) && (o instanceof HasId);
    if ( checkId && bothHaveId ) {
      return CompareUtils.compare( ( (HasId)this ).getId(), ( (HasId)o ).getId() );
    }
    int compare = CompareUtils.compare( first, o.first, true, true );
    if ( compare != 0 ) return compare;
    return CompareUtils.compare( second, o.second, true, true );
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