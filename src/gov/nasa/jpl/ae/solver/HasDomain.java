package gov.nasa.jpl.ae.solver;

import java.util.Set;

public interface HasDomain {
  /**
   * Return the possible values that the object may have.
   * @param propagate
   * @param seen
   * @return
   */
  public Domain< ? > getDomain( boolean propagate, Set< HasDomain > seen );
  
  /**
   * Make the input domain the domain of this object. For example, if this is a
   * function f(x,y) = x+y and the sum is restricted to [0,3], then the domains
   * of x and y should be restricted such that their sum is within [0,3]. In
   * this case, we are technically restricting the range of f even though we say
   * "domain." The domain of a variable is not the same as the domain of a
   * function. Think of the function as a variable that has a domain, so the
   * range of the function is the domain of the variable representing it.
   * 
   * @param domain
   * @param propagate
   * @param seen
   * @return
   */
  public <T> Domain< ? > restrictDomain( Domain< T > domain,
                                         boolean propagate,
                                         Set< HasDomain > seen );
  //public Domain< ? > restrictDomain( Domain<?> domain, boolean propagate, Set< HasDomain > seen );
}
