/**
 * 
 */
package gov.nasa.jpl.ae.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.solver.BooleanDomain;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.DoubleDomain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.RangeDomain;
import gov.nasa.jpl.ae.solver.StringDomain;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Wraps;

/**
 *
 */
public class DomainHelper {

  public static <T> Domain<T> getDomainForClass( Class<T> cls ) {
    if ( cls == null ) return null;
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
    
    Domain< ? > domain = dominantType == null ? null : DomainHelper.getDomainForClass( dominantType );
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
          if ( lb == null && ub == null ) {
            lb = odlb;
            ub = odub;
          } else {
            List< Object > list = Arrays.asList( new Object[] { odlb, lb } );
            fCall.setArguments( new Vector< Object >( list ) );
            try {
              lb = fCall.evaluate( true );
              list = Arrays.asList( new Object[] {odub, ub} );
              fCall.setArguments( new Vector< Object >( list ) );
              ub = fCall.evaluate( true );

            } catch ( IllegalAccessException e ) {
              // TODO Auto-generated catch block
              //e.printStackTrace();
            } catch ( InvocationTargetException e ) {
              // TODO Auto-generated catch block
              //e.printStackTrace();
            } catch ( InstantiationException e ) {
              // TODO Auto-generated catch block
              //e.printStackTrace();
            }
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
