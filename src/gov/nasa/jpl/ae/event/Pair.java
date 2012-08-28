package gov.nasa.jpl.ae.event;

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
}