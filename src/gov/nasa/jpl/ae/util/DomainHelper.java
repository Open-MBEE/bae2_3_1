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
import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.solver.AbstractRangeDomain;
import gov.nasa.jpl.ae.solver.BooleanDomain;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.DoubleDomain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.LongDomain;
import gov.nasa.jpl.ae.solver.RangeDomain;
import gov.nasa.jpl.ae.solver.SingleValueDomain;
import gov.nasa.jpl.ae.solver.StringDomain;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.Wraps;


/**
 *
 */
public class DomainHelper {

  public static <T> AbstractRangeDomain<T> getDomainForClass( Class<T> cls ) {
    if ( cls == null ) return null;
    if ( cls.equals( Boolean.class ) || cls.equals( boolean.class )) {
      return (AbstractRangeDomain< T >)new BooleanDomain();
    }
    if ( cls.equals( Integer.class ) || cls.equals(int.class)) {
      return (AbstractRangeDomain< T >)new IntegerDomain();
    }
    if ( cls.equals( Long.class ) || cls.equals( long.class ) ) {
      return (AbstractRangeDomain< T >)new LongDomain();
    }
    if ( cls.equals( Double.class ) || cls.equals( double.class ) ) {
      return (AbstractRangeDomain< T >)new DoubleDomain();
    }
//    if ( cls.equals( Float.class ) || cls.equals( float.class ) ) {
//      return (AbstractRangeDomain< T >)new FloatDomain();
//    }
    if ( cls.equals( String.class ) ) {
      return (AbstractRangeDomain< T >)new StringDomain();
    }
    return null;
  }

  public static <T> AbstractRangeDomain<T> makeRangeDomainFromValue( T t ) {
    if ( t instanceof AbstractRangeDomain) return (AbstractRangeDomain<T>)t;
    AbstractRangeDomain<T> d = (AbstractRangeDomain< T >)getDomainForClass( t.getClass() );
    if ( d != null ) {
      d.setLowerBound( t );
      d.setUpperBound( t );
    }
    return d;
  }

  public static < T > AbstractRangeDomain< T >
         createSubDomainBelow( Object o, boolean include, boolean propagate ) {
    Domain< ? > d = DomainHelper.getDomain( o );
    if ( d instanceof AbstractRangeDomain ) {
      AbstractRangeDomain< T > rd = ( (AbstractRangeDomain< T >)d ).clone();
      try {
        T t = (T)ClassUtils.evaluate( o, rd.getType(), propagate );
        AbstractRangeDomain< T > subDomain =
            rd.createSubDomainBelow( t, include );
        return subDomain;
      } catch ( Throwable e ) {}
    }
    return null;
  }  

  public static < T > AbstractRangeDomain< T >
         createSubDomainAbove( Object o, boolean include, boolean propagate ) {
    Domain< ? > d = DomainHelper.getDomain( o );
    if ( d instanceof AbstractRangeDomain ) {
      AbstractRangeDomain< T > rd = ( (AbstractRangeDomain< T >)d ).clone();
      try {
        T t = (T)ClassUtils.evaluate( o, rd.getType(), propagate );
        AbstractRangeDomain< T > subDomain =
            rd.createSubDomainAbove( t, include );
        return subDomain;
      } catch ( Throwable e ) {}
    }
    return null;
  }

  protected static void runOnArgs(Collection<Object> results, Call fCall, Object...args) {
    List<Object> list = Arrays.asList( args );
    Vector< Object > originalArgs = fCall.getArguments();
    Object r = null;
    try {
      fCall.setArguments( new Vector< Object >( list ) );
      r = fCall.evaluate( true );
    } catch ( IllegalAccessException e ) {
    } catch ( InvocationTargetException e ) {
    } catch ( InstantiationException e ) {
    } finally {
      fCall.setArguments( originalArgs );
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
      if ( objDomainType == null && obj instanceof Wraps ) {
        objDomainType = ((Wraps<?>)obj).getType();
      }
      if ( objDomainType == null && obj instanceof HasDomain ) {
        objDomain = ((HasDomain)obj).getDomain( false, null );
        //domains.add( objDomain );
        if ( objDomain != null ) {
          objDomainType = objDomain.getType();
        }
      }
      if ( objDomainType != null ) {
        if ( dominantType == null ) dominantType = objDomainType;
        else dominantType = ClassUtils.dominantTypeClass( dominantType, objDomainType );
      }
    }
    return dominantType;
  }
  
  public static <T> Domain<T> getDomain( T o ) {
    if ( o == null ) return null;
    if ( o instanceof Domain ) return (Domain< T >)o;
    if ( o instanceof HasDomain ) {
      return (Domain<T>)((HasDomain)o).getDomain( false, null );
    }
    if ( o instanceof Wraps ) {
      Object oo = ((Wraps)o).getValue( false );
      Domain<?> d = getDomain(oo);
      if ( d != null ) return (Domain< T >)d;
    }
    AbstractRangeDomain<T> ard = (AbstractRangeDomain<T>)getDomainForClass( o.getClass() );
    if ( ard != null ) {
      ard.setBounds( o, o );
      return ard;
    }
    return new SingleValueDomain<T>( o );
  }
  
  /**
   * Compute the range of values for the function call based on the domains of
   * the objects as arguments. If there are more objects than arguments, then
   * the outputs are folded back as inputs, such as for adding a list of
   * numbers. The method can fail if it cannot determine the type of the
   * arguments and result.
   * 
   * @param objects
   * @param fCall
   * @return
   */
  public static Domain<?> combineDomains( List< Object > objects,
                                               FunctionCall fCall ) {
    if ( fCall == null || fCall.getMethod() == null ) return null;
    // TODO! -- See if MultiDomain can help here.
//    // get the dominant Class of the arguments; e.g., Double dominates Integer
//    Class<?> dominantType = getDominantDomainType( objects );
//    //ArrayList< Domain< ? > > domains = new ArrayList< Domain< ? > >();
    
    Class<?> domainType = fCall.getType();
    if ( domainType == null ) {
      domainType = fCall.getMethod().getReturnType();
    }
    if ( domainType == null ) {
      return null;
    }
    
    AbstractRangeDomain domain = 
        domainType == null ? null : DomainHelper.getDomainForClass( domainType );
//    if ( domain == null ) {
//      Debug.error( "Could not create a RangeDomain in DomainHelper.combineDomains("
//                   + objects + ", " + fCall + ")" );
//      return null;
//    }

    LinkedHashSet< Object > results = new LinkedHashSet< Object >(); 
    
    if ( fCall.getParameterTypes().length == 0 ) {
      Object o = null;
      try {
        o = fCall.evaluate( true );
        if ( o == null ) {
          return null;
        }
        if ( domain == null ) {
          SingleValueDomain d = new SingleValueDomain( o );
          return d;
        }
        domain.setValue( o );
        return domain;
      } catch ( IllegalAccessException e ) {
      } catch ( InvocationTargetException e ) {
      } catch ( InstantiationException e ) {
      }
    } else if ( fCall.getParameterTypes().length > 2 ) {
      Debug.error( "Could not create a Domain in DomainHelper.combineDomains("
          + objects + ", " + fCall + ") for a function of more than two arguments" );
      return null;
    }
    
    Object lb = null;
    Object ub = null;          
    Object lbPrev = null;
    Object ubPrev = null;
    Object odlb = null;
    Object odub = null;
    for ( Object obj : objects ) {
      Domain<?> objDomain = null;
      if ( obj instanceof HasDomain ) {
        objDomain = ((HasDomain)obj).getDomain( false, null );
      }
      if ( objDomain instanceof RangeDomain ) {
          odlb = ( (RangeDomain< ? >)objDomain ).getLowerBound();
          odub = ( (RangeDomain< ? >)objDomain ).getUpperBound();
      } else {
        odlb = obj;
        odub = obj;
      }
//      if (!( obj instanceof HasDomain )) {
//        odlb = obj;
//        odub = obj;
//      } else {
//        objDomain = ((HasDomain)obj).getDomain( false, null );
//        if (!( objDomain instanceof RangeDomain )) {
//          odlb = obj;
//          odub = obj;
//        } else {
//          odlb = ( (RangeDomain< ? >)objDomain ).getLowerBound();
//          odub = ( (RangeDomain< ? >)objDomain ).getUpperBound();
//        }
//      }
          results = new LinkedHashSet< Object >(); 
          if ( fCall.getParameterTypes().length == 1 ) {
            runOnArgs( results, fCall, odlb );
            if ( odlb != odub ) {
              runOnArgs( results, fCall, odub );
            }
          } else { // assumes there are only two args
            if ( lbPrev == null && ubPrev == null ) {
              lbPrev = odlb;
              ubPrev = odub;
              continue;
            }
            if ( lb != null || ub != null ) {
              lbPrev = lb;
              ubPrev = ub;
            }
            runOnArgs( results, fCall, lbPrev, odlb );
            runOnArgs( results, fCall, ubPrev, odlb );
            if ( odlb != odub ) {
              runOnArgs( results, fCall, lbPrev, odub );
              runOnArgs( results, fCall, ubPrev, odub );
            }
          }
          
          if ( domain == null && !Utils.isNullOrEmpty( results )) {
            Class< ? > dominantType =
                ClassUtils.dominantObjectType( new ArrayList< Object >( results ) );
            if ( dominantType != null ) {
              domain = DomainHelper.getDomainForClass( dominantType );
              if ( domain != null ) domainType = dominantType;
            }
          }
          // get new lb and ub out of results
          if ( !results.isEmpty() ) {
            //lb = null; ub = null;
            for ( Object r : results ) {
              Object rr = null;
              try {
                rr = Expression.evaluate( r, domainType, true, true );
              } catch ( ClassCastException e1 ) {
              } catch ( IllegalAccessException e1 ) {
              } catch ( InvocationTargetException e1 ) {
              } catch ( InstantiationException e1 ) {
              }
              if ( rr == null ) {
                Debug.error( true, false,
                             "combineDomains intermediate value " + r
                                          + " could not be comverted to type "
                                          + domainType.getSimpleName() );
                continue;
              }
              try {
                if ( lb == null || ( domain != null && domain.less( rr, lb ) )
                     || ( domain == null
                          && CompareUtils.compare( rr, lb ) < 0 ) ) {
                  lb = rr;
                }
                if ( ub == null || ( domain != null && domain.less( ub, rr ) )
                     || ( domain == null
                          && CompareUtils.compare( ub, rr ) < 0 ) ) {
                  ub = rr;
                }
              } catch ( ClassCastException e ) {
                Debug.error( true, false,
                             "combineDomains unexpected ClassCastException " + r
                                          + " could not be comverted to type "
                                          + domainType.getSimpleName() );
              }
            }
          }
//        } else {
//          // TODO !(objDomain instanceof RangeDomain)
//          Debug.error( true, false,
//                       "combineDomains unexpected ClassCastException " + r
//                                    + " could not be comverted to type "
//                                    + domainType.getSimpleName() );
//        }
//      } else {
//        // TODO !(obj instanceof HasDomain)
//      }
    }
    if ( domain == null ) {
      Class< ? > dominantType =
          getDominantDomainType( new ArrayList< Object >( results ) );
      if ( dominantType != null ) {
        domain = DomainHelper.getDomainForClass( dominantType );
        if ( domain != null ) domainType = dominantType;
      }
    }

    if ( lb != null && ub != null && domain != null) {
      Object lbt = ClassUtils.evaluate( lb, domain.getType(), true );
      Object ubt = ClassUtils.evaluate( ub, domain.getType(), true );
      if ( lbt == null || ubt == null ) {
        Debug.error( true, true,
                     "Error!  Trying to set upper and lower bound to null in combineDomains()." );
      } else {
        domain.setBounds( lbt, ubt );
      }
    } else {
      domain = null;
    }
    return domain;
  }
}
