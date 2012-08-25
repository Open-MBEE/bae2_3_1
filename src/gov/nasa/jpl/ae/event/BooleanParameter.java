/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.BooleanDomain;
import gov.nasa.jpl.ae.solver.Domain;

/**
 * @author bclement
 *
 */
public class BooleanParameter extends Parameter<Boolean> {

	/**
	 * @param n
	 * @param o 
	 */
	public BooleanParameter(String n, ParameterListener o) {
		super(n, BooleanDomain.defaultDomain, o);
	}

	/**
	 * @param n
	 * @param d
	 * @param o 
	 */
	public BooleanParameter(String n, Domain<Boolean> d, ParameterListener o) {
		super(n, d, o);
	}

	/**
	 * @param n
	 * @param v
	 * @param o 
	 */
	public BooleanParameter(String n, Boolean v, ParameterListener o) {
		super(n, BooleanDomain.defaultDomain, v, o);
	}

	/**
	 * @param n
	 * @param d
	 * @param v
	 * @param o 
	 */
	public BooleanParameter(String n, Domain<Boolean> d, Boolean v, ParameterListener o) {
		super(n, d, v, o);
	}

	/**
	 * @param parameter
	 */
	public BooleanParameter(Parameter<Boolean> parameter) {
		super(parameter);
	}

}
