/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.LongDomain;

/**
 * @author bclement
 *
 */
public class LongParameter extends Parameter<Long> {

	/**
	 * @param n
	 * @param o 
	 */
	public LongParameter(String n, ParameterListener o) {
		super(n, LongDomain.domain, o);
	}

	/**
	 * @param n
	 * @param d
	 * @param o 
	 */
	public LongParameter(String n, Domain<Long> d, ParameterListener o) {
		super(n, d, o);
	}

	/**
	 * @param n
	 * @param v
	 * @param o 
	 */
	public LongParameter(String n, Long v, ParameterListener o) {
		super(n, LongDomain.domain, v, o);
	}

	/**
	 * @param n
	 * @param d
	 * @param v
	 * @param o 
	 */
	public LongParameter(String n, Domain<Long> d, Long v, ParameterListener o) {
		super(n, d, v, o);
	}

	/**
	 * @param parameter
	 */
	public LongParameter(Parameter<Long> parameter) {
		super(parameter);
	}

}
