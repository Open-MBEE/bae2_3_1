/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.DoubleDomain;

/**
 * @author bclement
 *
 */
public class DoubleParameter extends Parameter<Double> {

	/**
	 * @param n
	 * @param o 
	 */
	public DoubleParameter(String n, ParameterListener o) {
		super(n, DoubleDomain.defaultDomain, o);
	}

	/**
	 * @param n
	 * @param d
	 * @param o 
	 */
	public DoubleParameter(String n, Domain<Double> d, ParameterListener o) {
		super(n, d, o);
	}

	/**
	 * @param n
	 * @param v
	 * @param o 
	 */
	public DoubleParameter(String n, Double v, ParameterListener o) {
		super(n, DoubleDomain.defaultDomain, v, o);
	}

	/**
	 * @param n
	 * @param d
	 * @param v
	 * @param o 
	 */
	public DoubleParameter(String n, Domain<Double> d, Double v, ParameterListener o) {
		super(n, d, v, o);
	}

	/**
	 * @param parameter
	 */
	public DoubleParameter(Parameter<Double> parameter) {
		super(parameter);
	}

}
