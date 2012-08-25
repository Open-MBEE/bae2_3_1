package gov.nasa.jpl.ae.event;

public class Pair<A,B> {
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
}