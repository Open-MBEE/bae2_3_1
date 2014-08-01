/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.StringDomain;

/**
 *
 */
public class StringParameter extends Parameter<String> {

	/**
	 * @param n
	 * @param o 
	 */
	public StringParameter(String n, ParameterListener o) {
		super(n, StringDomain.defaultDomain, o);
	}
	/**
	 * @param n
	 * @param d
	 * @param o 
	 */
	public StringParameter(String n, Domain<String> d, ParameterListener o) {
		super(n, d, o);
	}

	/**
	 * @param n
	 * @param v
	 * @param o 
	 */
	public StringParameter(String n, String v, ParameterListener o) {
		super(n, StringDomain.defaultDomain, v, o);
	}

	/**
	 * @param n
	 * @param d
	 * @param v
	 * @param o 
	 */
	public StringParameter(String n, Domain<String> d, String v, ParameterListener o) {
		super(n, d, v, o);
	}

	/**
	 * @param parameter
	 */
	public StringParameter(Parameter<String> parameter) {
		super(parameter);
	}

  @Override
  public String toString() {
    String s;
    if ( isGrounded( false, null ) ) {
      // TODO -- escapes? substitute \" and \n  for '"' and newline, etc.?
      s = getName() + "=\"" + getValueNoPropagate() + "\"";
    } else {
      s = getName() + "=" + getDomain();
    }
    return s;
  }

}
