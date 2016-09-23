/**
 * 
 */
package gov.nasa.jpl.ae.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingDeque;

import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.solver.AbstractRangeDomain;
import gov.nasa.jpl.ae.solver.BooleanDomain;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.DoubleDomain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.RangeDomain;
import gov.nasa.jpl.ae.solver.SingleValueDomain;
import gov.nasa.jpl.ae.solver.StringDomain;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Wraps;


/**
 *
 */
public class DomainHelper {

  public static <T> AbstractRangeDomain<T> getDomainForClass( Class<T> cls ) {
    if ( cls == null ) return null;
    if ( cls.equals( Boolean.class ) ) {
      return (AbstractRangeDomain< T >)new BooleanDomain();
    }
    if ( cls.equals( Integer.class ) ) {
      return (AbstractRangeDomain< T >)new IntegerDomain();
    }
//    if ( cls.equals( Long.class ) ) {
//      return (Domain< T >)new LongDomain();
//    }
    if ( cls.equals( Double.class ) ) {
      return (AbstractRangeDomain< T >)new DoubleDomain();
    }
    if ( cls.equals( String.class ) ) {
      return (AbstractRangeDomain< T >)new StringDomain();
    }
    return null;
  }

  public static <T> AbstractRangeDomain<T> makeRangeDomainFromValue( T t ) {
    AbstractRangeDomain<T> d = (AbstractRangeDomain< T >)getDomainForClass( t.getClass() );
    if ( d != null ) {
      d.setLowerBound( t );
      d.setUpperBound( t );
    }
    return d;
  }


  protected static void runOnArgs(Collection<Object> results, Call fCall, Object...args) {
    List<Object> list = Arrays.asList( args );
    fCall.setArguments( new Vector< Object >( list ) );
    Object r = null;
    try {
      r = fCall.evaluate( true );
    } catch ( IllegalAccessException e ) {
    } catch ( InvocationTargetException e ) {
    } catch ( InstantiationException e ) {
    }
    if ( r != null ) {
      results.add( r );
    }
  }
  
  public static Class<?> getDominantDomainType( List< Object > objects ) {
    Class<?> dominantType = null;

    for ( Object obj : objects ) {
      Domain<?> objDomain = null;
      Class<?> objDomainType = null;
      if ( obj instanceof HasDomain ) {
        objDomain = ((HasDomain)obj).getDomain( false, null );
        //domains.add( objDomain );
        if ( objDomain != null ) {
          objDomainType = objDomain.getType();
        }
      } else if (obj instanceof Wraps) {
        objDomainType = ((Wraps<?>)obj).getType();
      }
      if ( dominantType == null ) dominantType = objDomainType;
      else dominantType = ClassUtils.dominantTypeClass( dominantType, objDomainType );
    }
    return dominantType;
  }
  
  public static <T> Domain<T> getDomain( T o ) {
    if ( o == null ) return null;
    if ( o instanceof HasDomain ) {
      return (Domain<T>)((HasDomain)o).getDomain( false, null );
    }
    AbstractRangeDomain<T> ard = (AbstractRangeDomain<T>)getDomainForClass( o.getClass() );
    if ( ard != null ) {
      ard.setBounds( o, o );
      return ard;
    }
    return new SingleValueDomain<T>( o );
  }
  
  /**
   * @param objects
   * @param fCall
   * @return
   */
  public static RangeDomain<?> combineDomains( List< Object > objects,
                                               FunctionCall fCall ) {
    // get the dominant Class of the arguments; e.g., Double dominates Integer
    Class<?> dominantType = getDominantDomainType( objects );
    //ArrayList< Domain< ? > > domains = new ArrayList< Domain< ? > >();
    
    AbstractRangeDomain domain = 
        dominantType == null ? null : DomainHelper.getDomainForClass( dominantType );
    if ( domain == null ) {
      Debug.error( "Could not create a RangeDomain in DomainHelper.combineDomains("
                   + objects + ", " + fCall + ")" );
      return null;
    }

    Object lb = null;
    Object ub = null;          
    for ( Object obj : objects ) {
      Domain<?> objDomain = null;
      if ( obj instanceof HasDomain ) {
        objDomain = ((HasDomain)obj).getDomain( false, null );
        if ( objDomain instanceof RangeDomain ) {
          Object odlb = ( (RangeDomain< ? >)objDomain ).getLowerBound();
          Object odub = ( (RangeDomain< ? >)objDomain ).getUpperBound();
          LinkedHashSet< Object > results = new LinkedHashSet< Object >(); 
          if ( fCall.getParameterTypes().length == 1 ) {
            runOnArgs( results, fCall, odlb );
            runOnArgs( results, fCall, odub );
          } else {
            if ( lb == null && ub == null ) {
              results.add( odlb );
              results.add( odub );
            } else {
              runOnArgs( results, fCall, lb, odlb );
              runOnArgs( results, fCall, ub, odlb );
              runOnArgs( results, fCall, lb, odub );
              runOnArgs( results, fCall, ub, odub );
            }
          }
          // get new lb and ub out of results
          if ( !results.isEmpty() ) {
            //lb = null; ub = null;
            for ( Object r : results ) {
              Object rr = null;
              try {
                rr = Expression.evaluate( r, dominantType, true, true );
              } catch ( ClassCastException e1 ) {
              } catch ( IllegalAccessException e1 ) {
              } catch ( InvocationTargetException e1 ) {
              } catch ( InstantiationException e1 ) {
              }
              if ( rr == null ) {
                Debug.error( true, false,
                             "combineDomains intermediate value " + r
                                          + " could not be comverted to type "
                                          + dominantType.getSimpleName() );
                continue;
              }
              try {
                if ( lb == null || domain.less(rr, lb) ) {
                  lb = rr;
                }
                if ( ub == null || domain.less(ub, rr) ) {
                  ub = rr;
                }
              } catch ( ClassCastException e ) {
                Debug.error( true, false,
                             "combineDomains unexpected ClassCastException " + r
                                          + " could not be comverted to type "
                                          + dominantType.getSimpleName() );
              }
            }
          }
        } else {
          // TODO !(objDomain instanceof RangeDomain)
        }
      } else {
        // TODO !(obj instanceof HasDomain)
      }
    }
    if ( lb != null && ub != null ) {
      domain.setBounds( lb, ub );
    } else {
      domain = null;
    }
    return domain;
  }
}
