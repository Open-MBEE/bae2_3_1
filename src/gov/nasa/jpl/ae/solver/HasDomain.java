package gov.nasa.jpl.ae.solver;

import java.util.Set;

import gov.nasa.jpl.mbee.util.Pair;

public interface HasDomain {
  /**
   * Return the possible values that the object may have.
   * @param propagate
   * @param seen
   * @return
   */
  public Domain< ? > getDomain( boolean propagate, Set< HasDomain > seen );
  
  /**
   * Make the the domain of this object the largest subset of its current domain
   * that is contained by the input domain, even if that is an empty domain. For
   * example, if this is a function f(x,y) = x+y and the sum is restricted to
   * [0,3], then the domains of x and y should be restricted such that their sum
   * is within [0,3]. In this case, we are technically restricting the range of
   * f even though we say "domain." The domain of a variable is not the same as
   * the domain of a function. Think of the function as a variable that has a
   * domain, so the range of the function is the domain of the variable
   * representing it.
   * 
   * @param domain
   * @param propagate
   * @param seen
   * @return the resulting domain and whether any restriction was made to any
   *         variable or expression as a result of this call.
   */
  public <T> Pair< Domain< T >, Boolean > restrictDomain( Domain< T > domain,
                                                          boolean propagate,
                                                          Set< HasDomain > seen );
}
