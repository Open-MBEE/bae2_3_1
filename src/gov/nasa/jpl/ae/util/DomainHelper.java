/**
 * 
 */
package gov.nasa.jpl.ae.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.solver.BooleanDomain;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.DoubleDomain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.RangeDomain;
import gov.nasa.jpl.ae.solver.StringDomain;
import gov.nasa.jpl.ae.solver.Wraps;
import gov.nasa.jpl.mbee.util.ClassUtils;

/**
 *
 */
public class DomainHelper {

  public static <T> Domain<T> getDomainForClass( Class<T> cls ) {
    if ( cls.equals( Boolean.class ) ) {
      return (Domain< T >)new BooleanDomain();
    }
    if ( cls.equals( Integer.class ) ) {
      return (Domain< T >)new IntegerDomain();
    }
//    if ( cls.equals( Long.class ) ) {
//      return (Domain< T >)new LongDomain();
//    }
    if ( cls.equals( Double.class ) ) {
      return (Domain< T >)new DoubleDomain();
    }
    if ( cls.equals( String.class ) ) {
      return (Domain< T >)new StringDomain();
    }
    return null;
  }

  /**
   * @param objects
   * @param fCall
   * @return
   */
  public static RangeDomain<?> combineDomains( List< Object > objects,
                                               FunctionCall fCall ) {
    // get the dominant Class of the arguments; e.g., Double dominates Integer
    Class<?> dominantType = null;
    ArrayList< Domain< ? > > domains = new ArrayList< Domain< ? > >();
    for ( Object obj : objects ) {
      Domain<?> objDomain = null;
      Class<?> objDomainType = null;
      if ( obj instanceof HasDomain ) {
        objDomain = ((HasDomain)obj).getDomain( false, null );
        domains.add( objDomain );
        if ( objDomain != null ) {
          objDomainType = objDomain.getType();
        }
      } else if (obj instanceof Wraps) {
        objDomainType = ((Wraps<?>)obj).getType();
      }
      if ( dominantType == null ) dominantType = objDomainType;
      else dominantType = ClassUtils.dominantTypeClass( dominantType, objDomainType );
    }
    
    Domain< ? > domain = DomainHelper.getDomainForClass( dominantType );
    if ( domain == null ) return null;

    Object lb = null;
    Object ub = null;          
    for ( Object obj : objects ) {
      Domain<?> objDomain = null;
      if ( obj instanceof HasDomain ) {
        objDomain = ((HasDomain)obj).getDomain( false, null );
        if ( objDomain instanceof RangeDomain ) {
          Object odlb = ( (RangeDomain< ? >)objDomain ).getLowerBound();
          Object odub = ( (RangeDomain< ? >)objDomain ).getUpperBound();
          if ( lb == null ) {
            lb = odlb;
          } else {
            List< Object > list = Arrays.asList( new Object[] { odlb, lb } );
            fCall.setArguments( new Vector< Object >( list ) );
            lb = fCall.evaluate( true );
          }
          if ( ub == null ) {
            ub = ( (RangeDomain< ? >)objDomain ).getUpperBound();
          } else {
            List< Object > list = Arrays.asList( new Object[] { odub, ub } );
            fCall.setArguments( new Vector< Object >( list ) );
            ub = fCall.evaluate( true );
          }
        }
      }
    }
    if ( domain instanceof RangeDomain ) {
      ( (RangeDomain)domain ).setBounds( lb, ub );
      return (RangeDomain)domain;
    }
    return null;
  }
}
