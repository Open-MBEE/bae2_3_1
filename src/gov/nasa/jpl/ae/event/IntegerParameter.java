/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.IntegerDomain;

/**
 * @author bclement
 *
 */
public class IntegerParameter extends Parameter<Integer> {

	/**
	 * @param n
	 * @param o 
	 */
	public IntegerParameter(String n, ParameterListener o) {
		super(n, IntegerDomain.defaultDomain, o);
	}

	/**
	 * @param n
	 * @param d
	 * @param o 
	 */
	public IntegerParameter(String n, Domain<Integer> d, ParameterListener o) {
		super(n, d, o);
	}

	/**
	 * @param n
	 * @param v
	 * @param o 
	 */
	public IntegerParameter(String n, Integer v, ParameterListener o) {
		super(n, IntegerDomain.defaultDomain, v, o);
	}

	/**
	 * @param n
	 * @param d
	 * @param v
	 * @param o 
	 */
	public IntegerParameter(String n, Domain<Integer> d, Integer v, ParameterListener o) {
		super(n, d, v, o);
	}

	/**
	 * @param parameter
	 */
	public IntegerParameter(Parameter<Integer> parameter) {
		super(parameter);
	}

}
