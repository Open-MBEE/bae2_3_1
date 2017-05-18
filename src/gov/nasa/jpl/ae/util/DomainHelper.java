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
import java.util.Set;
import java.util.Vector;

import gov.nasa.jpl.ae.event.Call;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Functions;
import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.TimeVaryingMap.MathOperation;
import gov.nasa.jpl.ae.solver.AbstractRangeDomain;
import gov.nasa.jpl.ae.solver.BooleanDomain;
import gov.nasa.jpl.ae.solver.ComparableDomain;
import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.DoubleDomain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.IntegerDomain;
import gov.nasa.jpl.ae.solver.LongDomain;
import gov.nasa.jpl.ae.solver.MultiDomain;
import gov.nasa.jpl.ae.solver.ObjectDomain;
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

  public static <T> ComparableDomain<T> getComparableDomain( Object o ) {
    if ( o == null ) return null;
    Domain<T> d = null;
    if ( o instanceof Domain ) d = (Domain< T >)o;
    else if ( o instanceof HasDomain ) {
      d = (Domain<T>)((HasDomain)o).getDomain( false, null );
    } 
    else if ( o instanceof Wraps ) {
      Object oo = ((Wraps)o).getValue( false );
      ComparableDomain<T> cd = getComparableDomain(oo);
      return cd;
    }
    if ( o instanceof Collection ) {
      return getComparableDomain((Collection<?>)o);
    }
    Object oo = null;
    if ( d != null ) {
      if ( d instanceof ComparableDomain ) {
        return (ComparableDomain< T >)d;
      }
      if ( d instanceof SingleValueDomain ) {
        oo = d.getValue( true );
      } else if ( d instanceof MultiDomain ) {
        MultiDomain< T > md = (MultiDomain< T >)d;
        Set< Domain< T > > fds = md.getFlattenedSet();
        if ( Utils.isNullOrEmpty( fds ) ) {
          ComparableDomain<T> ard = getEmptyComparableDomain(md);
          return ard;
        }
        if ( fds.size() == 1 ) {
          d = fds.iterator().next();
          ComparableDomain< T > cd = getComparableDomain( d );
          return cd;
        }
        return null;
      }
      if ( d instanceof ObjectDomain ) {
        ObjectDomain<T> od = (ObjectDomain<T>)d;
        if ( od.isEmpty() ) { 
          ComparableDomain<T> ard = getEmptyComparableDomain(od);
          return ard;
        }
        if ( od.size() == 1) {
          for ( T t : od ) {
            ComparableDomain<T> cd = getComparableDomain(t);
            return cd;
          }
        }
        return null;
      }
    }
    AbstractRangeDomain<T> ard = (AbstractRangeDomain<T>)getDomainForClass( o.getClass() );
    if ( ard != null ) {
      ard.setBounds( (T)o, (T)o );
      return ard;
    }
    if ( oo != null ) ard = (AbstractRangeDomain<T>)getDomainForClass( oo.getClass() );
    if ( ard != null ) {
      ard.setBounds( (T)oo, (T)oo );
      return ard;
    }
    return null;
  }
  
  public static <T> ComparableDomain<T> getEmptyComparableDomain(Domain<T> d) {
    if ( d instanceof ComparableDomain ) {
      ComparableDomain<T> c = (ComparableDomain< T >)d.clone();
      c.clearValues();
      return c;
    }
    Class<T> cls = (Class< T >)d.getType();
    if ( cls == null ) {
      T v = d.getValue( false );
      if ( v != null ) {
        cls = (Class< T >)v.getClass();
      }
      return null;
    }
    AbstractRangeDomain<T> ard = (AbstractRangeDomain<T>)getDomainForClass( cls );
    return ard;
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
                                               FunctionCall fCall,
                                               boolean makeASingleRange ) {
    
    if ( fCall != null && fCall.getMethod() == null ) return null;
    List<Domain<?>> domains = new ArrayList< Domain<?> >();
    for ( Object obj : objects ) {
      Domain<?> objDomain = null;
      if ( obj instanceof HasDomain ) {
        objDomain = ((HasDomain)obj).getDomain( false, null );
      } else if ( obj instanceof Domain ) {
        objDomain = (Domain<?>)obj;
      }
      if ( objDomain != null ) {
        domains.add( objDomain );
      }
    }
    return combineTheDomains( domains, fCall, makeASingleRange );
  }

  public static < T > ComparableDomain< T > combineHybridDomains( ComparableDomain< ? > cd1,
                                                                  ComparableDomain< ? > cd2,
                                                                  FunctionCall fCall ) {
    ComparableDomain< T > d = combineTheDomains( (ComparableDomain< T >)cd1,
                                                 (ComparableDomain< T >)cd2, fCall );
    return d;
  }

  public static <T> ComparableDomain<T> combineTheDomains( ComparableDomain<T> cd1,
                                                           ComparableDomain<T> cd2,
                                                           FunctionCall fCall ) {
    LinkedHashSet< Object > results = new LinkedHashSet< Object >(); 
    Object lbPrev = cd1 == null ? null : cd1.getLowerBound();
    Object ubPrev = cd1 == null ? null : cd1.getUpperBound();
    Object odlb = cd2.getLowerBound();
    Object odub = cd2.getUpperBound();
    
    if ( fCall.getParameterTypes().length == 1 ) {
      runOnArgs( results, fCall, odlb );
      if ( odlb != odub ) {
        runOnArgs( results, fCall, odub );
      }
    } else { // assumes there are only two args
      runOnArgs( results, fCall, lbPrev, odlb );
      if ( lbPrev != ubPrev ) runOnArgs( results, fCall, ubPrev, odlb );
      if ( odlb != odub ) {
        runOnArgs( results, fCall, lbPrev, odub );
        if ( lbPrev != ubPrev ) runOnArgs( results, fCall, ubPrev, odub );
      }
    }
    ComparableDomain<T> d = getComparableDomain(results);
    return d;
  }
  
  public static <T> boolean less( ComparableDomain<T> cd, Object o1, Object o2 ) {
    if ( cd != null && cd.getType() != null ) {
      T t1 = (T)ClassUtils.evaluate( o1, cd.getType(), false );
      T t2 = (T)ClassUtils.evaluate( o2, cd.getType(), false );
      boolean b = cd.less( t1, t2 );
      return b;
    } else {
      int comp = CompareUtils.compare( o1, o2, true, false );
      return comp < 0;
    }
  }
  
  public static <T> ComparableDomain<T> getComparableDomain( Collection<?> results ) {
    RangeDomain<T> domain = null;
    Class<T> domainType = null;
    if ( domain == null && !Utils.isNullOrEmpty( results )) {
      Class< T > dominantType =
          (Class< T >)ClassUtils.dominantObjectType( new ArrayList< Object >( results ) );
      if ( dominantType != null ) {
        domain = DomainHelper.getDomainForClass( dominantType );
        if ( domain != null ) domainType = dominantType;
      }
    }
    
    // If these are domains, get their representative values.
    if ( !results.isEmpty() ) {
      LinkedHashSet<Object> newResults = new LinkedHashSet< Object >();
      for ( Object r : results ) {
        if ( r instanceof Domain<?> ) {
          List< Object > vals = getRepresentativeValues( (Domain< ? >)r, null );
          if ( vals != null ) {
            newResults.addAll( vals );
          }
        } else {
          newResults.add( r );
        }
      }
      if ( !newResults.isEmpty() ) {
        results = newResults;
      }
    }    

    // get lb and ub out of results
    Object lb = null;
    Object ub = null;
    if ( !results.isEmpty() ) {
      for ( Object r : results ) {
        if ( r == null ) continue;
        Object rr = null;
        if ( domainType == null || domain == null ) {
          domainType = (Class< T >)r.getClass();
          domain = DomainHelper.getDomainForClass( domainType );
          rr = r;
        } else {
          try {
            rr = Expression.evaluate( r, domainType, true, true );
          } catch ( ClassCastException e1 ) {
          } catch ( IllegalAccessException e1 ) {
          } catch ( InvocationTargetException e1 ) {
          } catch ( InstantiationException e1 ) {
          }
        }
        if ( rr == null ) {
          Debug.error( true, false,
                       "combineDomains intermediate value " + r
                                    + " could not be comverted to type "
                                    + domainType );
          continue;
        }
        try {
          if ( lb == null || less( domain, rr, lb ) ) {
            lb = rr;
          }
          if ( ub == null || less( domain, ub, rr ) ) {
            ub = rr;
          }
        } catch ( ClassCastException e ) {
          Debug.error( true, false,
                       "combineDomains unexpected ClassCastException " + r
                                    + " could not be comverted to type "
                                    + domainType );
        }
      }
    }
    if ( domain == null ) {
      Class< ? > dominantType =
          getDominantDomainType( new ArrayList< Object >( results ) );
      if ( dominantType != null ) {
        domain = (RangeDomain< T >)DomainHelper.getDomainForClass( dominantType );
        if ( domain != null ) domainType = (Class< T >)dominantType;
      }
    }

    if ( lb != null && ub != null && domain != null) {
      T lbt = (T)ClassUtils.evaluate( lb, domain.getType(), true );
      T ubt = (T)ClassUtils.evaluate( ub, domain.getType(), true );
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

  public static Domain<?> combineTheDomains( List< Domain<?> > domains,
                                             FunctionCall fCall,
                                             boolean makeASingleRange ) {
    if ( Utils.isNullOrEmpty( domains ) ) return null;
    if ( fCall != null && fCall.getMethod() == null ) return null;
    if ( fCall == null ) {
      fCall = new Functions.Identity<>( new Expression(domains.get( 0 ).getValue( false ) ) );
    }
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

    LinkedHashSet< Domain<?> > results = new LinkedHashSet< Domain<?> >(); 
    
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
          + domains + ", " + fCall + ") for a function of more than two arguments" );
      return null;
    }
    
    Object lb = null;
    Object ub = null;          
    Object lbPrev = null;
    Object ubPrev = null;
    Object odlb = null;
    Object odub = null;
    Set< ComparableDomain< ? > > priorRepresentativeValues = null;
        //new LinkedHashSet< ComparableDomain< ? > >();
    for ( Domain<?> objDomain : domains ) {
      if ( objDomain != null && objDomain.isEmpty() ) {
        if ( domain != null ) {
          domain.clearValues();
          return domain;
        }
        return DomainHelper.emptyDomain();
      }

      MathOperation op = TimeVaryingMap.getOperationForMethod( fCall.getMethod() );
      LinkedHashSet< ComparableDomain< ? > > representativeDomains = 
          getRepresentativeComparableDomains( objDomain, op );
      if ( representativeDomains == null ) {
        Debug.error( false, "BADNESS!!!" );
        return DomainHelper.emptyDomain();
      }
      
      if ( fCall.getParameterTypes().length > 1 && priorRepresentativeValues == null ) {
        priorRepresentativeValues = representativeDomains;
      } else {
        LinkedHashSet<ComparableDomain<?>> ranges = new LinkedHashSet<ComparableDomain<?>>();
        if ( priorRepresentativeValues == null ) {
          for ( ComparableDomain<?> cd2 : representativeDomains ) {
            ComparableDomain< ? > d = combineHybridDomains( null, cd2, fCall );
            if ( d != null && !d.isEmpty() ) {
              ranges.add( d );
            }
          }
        } else {
        for ( ComparableDomain< ? > cd1 : priorRepresentativeValues ) {
          for ( ComparableDomain<?> cd2 : representativeDomains ) {
            ComparableDomain< ? > d = combineHybridDomains( cd1, cd2, fCall );
            if ( d != null && !d.isEmpty() ) {
              ranges.add( d );
            }
          }
        }
        }
        priorRepresentativeValues = ranges;
        results.addAll( ranges );
      }
    }
    if ( results.size() == 1 ) {
      return results.iterator().next();
    }
    if ( results.size() == 0 ) {
      return DomainHelper.emptyDomain();
    }
    if ( results.size() > 1 ) {
      if ( makeASingleRange ) {
        ComparableDomain< Object > cd = getComparableDomain( results );
        if ( cd != null && ( !cd.isEmpty() || allEmpty(results) ) ) {
          return cd;
        }
      }
      MultiDomain<?> md = new MultiDomain(results, null);
      return md;
    }
    return null;
      /*
      if ( objDomain instanceof MultiDomain ) {
        MultiDomain<?> md = (MultiDomain<?>)objDomain;
        for (Domain<?> dom : md.getFlattenedSet() ) {
          
        }
      } else if ( objDomain instanceof ComparableDomain ) {
          odlb = ( (ComparableDomain< ? >)objDomain ).getLowerBound();
          odub = ( (ComparableDomain< ? >)objDomain ).getUpperBound();
      } else if ( objDomain instanceof SingleValueDomain ) {
        odlb = objDomain.getValue( false );
        odub = odlb;
      } else if ( objDomain instanceof ObjectDomain ) {
        ObjectDomain<?> objectDomain = (ObjectDomain<?>)objDomain;
        if ( objectDomain.size() == 1 ) {
          odlb = objectDomain.iterator().next();
          odub = odlb;
        } else if ( objectDomain.size() == 2 ) {
          Iterator< ? > it = objectDomain.iterator();
          odlb = it.next();
          odub = it.next();
        }
      }
      */
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
      /*
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
              if ( r == null ) continue;
              Object rr = null;
              if ( domainType == null || domain == null ) {
                domainType = r.getClass();
                domain = DomainHelper.getDomainForClass( domainType );
                rr = r;
              } else {
                try {
                  rr = Expression.evaluate( r, domainType, true, true );
                } catch ( ClassCastException e1 ) {
                } catch ( IllegalAccessException e1 ) {
                } catch ( InvocationTargetException e1 ) {
                } catch ( InstantiationException e1 ) {
                }
              }
              if ( rr == null ) {
                Debug.error( true, false,
                             "combineDomains intermediate value " + r
                                          + " could not be comverted to type "
                                          + domainType );
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
                                          + domainType );
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
    */
  }

  
  public static boolean allEmpty( Collection<Domain<?>> domains ) {
    if ( domains == null ) return false;
    for ( Domain<?> d : domains ) {
      if ( d == null ) continue;
      if ( !d.isEmpty() ) {
        return false;
      }
    }
    return true;
  }

  public static LinkedHashSet< ComparableDomain< ? > >
      getRepresentativeComparableDomains(Domain<?> domain, MathOperation op ) {
    
    if ( domain == null ) return null;
    LinkedHashSet< ComparableDomain< ? > > representativeValues =
        new LinkedHashSet< ComparableDomain< ? > >();
    if ( domain instanceof ComparableDomain ) {
      representativeValues.add( (ComparableDomain< ? >)domain );
      return representativeValues;
    }
    if ( domain instanceof MultiDomain ) {
      MultiDomain<?> md = (MultiDomain<?>)domain;
      Set<?> set = md.includeSet;
      if ( !Utils.isNullOrEmpty( md.excludeSet ) ) {
        set = md.getFlattenedSet();
      }
      if ( set != null ) {
        for ( Object ddo : set ) {
          if ( ddo instanceof Domain ) {
            Domain< ? > dd = (Domain< ? >)ddo;
            representativeValues = getRepresentativeComparableDomains( dd, op );
          }
        }
      }
    } else if ( domain instanceof SingleValueDomain ) {
      ComparableDomain< ? > dd = getComparableDomain( (SingleValueDomain< ? >)domain );
      if ( dd != null ) {
        representativeValues.add( dd );
      } else {
        Debug.error( "Could not convert SingleValueDomain(" + domain + ") to ComparableDomain" );
      }
    } else if ( domain instanceof ObjectDomain ) {
      ObjectDomain<?> objectDomain = (ObjectDomain <?>)domain;
      for ( Object o : objectDomain ) {
        if ( o == null ) continue;
        ComparableDomain< ? > cd = getComparableDomain( o );
        if ( cd != null ) {
          representativeValues.add( cd );
        } else {
          Debug.error( "Could not convert ObjectDomain(" + domain + ") to ComparableDomain" );
          return null;
        }
      }
    }
    // Handle non-monotonic stuff
    if ( op == MathOperation.DIVIDE || op == MathOperation.LOG ) {
      if ( contains( domain, 0 ) ) {
        Number lb = null;
        Number ub = null;
        for ( ComparableDomain< ? > cd : (ComparableDomain[])representativeValues.toArray() ) {
          if (!contains(cd, 0)) continue; 
          Object odlb = cd.getLowerBound();
          Object odub = cd.getUpperBound();
          lb = ClassUtils.evaluate( odlb, Number.class, false );
          ub = ClassUtils.evaluate( odub, Number.class, false );
          Object zero = ClassUtils.evaluate( 0, cd.getType(), false ); 
          if ( lb instanceof Float || lb instanceof Double ) {
            if ( lb.doubleValue() < 0.0 ) {
              if ( cd instanceof RangeDomain ) {
                RangeDomain<?> cd1 = (RangeDomain< ? >)cd.clone();
                DoubleDomain dd = new DoubleDomain(0.0, ub.doubleValue() );
                dd.excludeLowerBound();
                cd1 = (RangeDomain< ? >)cd1.subtract( dd );
                representativeValues.add(cd1 );
                representativeValues.remove( cd );
              }
            }
            if ( ub.doubleValue() < 0.0 ) {
              if ( cd instanceof RangeDomain ) {
                RangeDomain<?> cd1 = (RangeDomain< ? >)cd.clone();
                DoubleDomain dd = new DoubleDomain(lb.doubleValue(), 0.0);
                dd.excludeUpperBound();
                cd1 = (RangeDomain< ? >)cd1.subtract( dd );
                representativeValues.add(cd1 );
                representativeValues.remove( cd );
              }
            }
          }
        }
      if ( domain.getType() != null && ( lb == null || !lb.equals( 0 ) )
           && ( ub == null || !ub.equals( 0 ) ) ) {
          Object zero = ClassUtils.evaluate( 0, domain.getType(), false );
          if ( zero == null ) {
            zero = 0;
          }
          ComparableDomain< ? > cd = getComparableDomain( zero );
          representativeValues.add( cd );
        }
      }
    }

    return representativeValues;
  }

  
  public static List<Object> getRepresentativeValues(Domain<?> domain, MathOperation op ) {
    LinkedHashSet<Object> representativeValues = new LinkedHashSet<Object>();
    Object odlb = null;
    Object odub = null;
    if ( domain instanceof MultiDomain ) {
      MultiDomain<?> md = (MultiDomain<?>)domain;
      Set<?> set = md.includeSet;
      if ( !Utils.isNullOrEmpty( md.excludeSet ) ) {
        set = md.getFlattenedSet();
      }
      if ( set != null ) {
        for ( Object ddo : set ) {
          if ( ddo instanceof Domain ) {
            Domain< ? > dd = (Domain< ? >)ddo;
            List< Object > vals = getRepresentativeValues( dd, op );
            representativeValues.addAll( vals );
          }
        }
      }
    } else if ( domain instanceof ComparableDomain ) {
      odlb = ( (ComparableDomain< ? >)domain ).getLowerBound();
      odub = ( (ComparableDomain< ? >)domain ).getUpperBound();
    } else if ( domain instanceof SingleValueDomain ) {
      odlb = domain.getValue( false );
      odub = odlb;
    } else if ( domain instanceof ObjectDomain ) {
      ObjectDomain<?> objectDomain = (ObjectDomain<?>)domain;
      representativeValues.addAll( objectDomain );
//      if ( objectDomain.size() == 1 ) {
//        odlb = objectDomain.iterator().next();
//        odub = odlb;
//      } else if ( objectDomain.size() == 2 ) {
//        Iterator< ? > it = objectDomain.iterator();
//        odlb = it.next();
//        odub = it.next();
//      }
    }
    if ( odlb != null || odub != null ) {
      if ( odlb != null ) representativeValues.add( odlb );
      if ( odub != null && !odub.equals(odlb) ) representativeValues.add( odub );
      if ( op == MathOperation.DIVIDE || op == MathOperation.LOG ) {
        if ( contains( domain, 0 ) ) {
          Number lb = ClassUtils.evaluate( odlb, Number.class, false );
          Number ub = ClassUtils.evaluate( odub, Number.class, false );
          if ( lb instanceof Float || lb instanceof Double ) {
            if ( lb.doubleValue() < 0.0 || ub.doubleValue() < 0.0) {
              representativeValues.add( -Math.epsilon );
            } else {
              representativeValues.add( Math.epsilon );
            }
          }
          if ( domain.getType() != null &&!lb.equals( 0 ) && !ub.equals( 0 ) ) {
            Object zero = ClassUtils.evaluate( 0, domain.getType(), false ); 
            if ( zero == null ) {
              zero = 0;
            }
            representativeValues.add( zero );
          }
        }
      }
    }

    return Utils.asList( representativeValues );
  }
  
  public static <T> boolean contains( Domain<T> d, Object o ) {
    if ( d == null || o == null ) return false;
    if ( d.getType() != null ) {
      T t = null;
      try {
        if ( d.getType().isAssignableFrom( o.getClass() ) ) {
          t = (T)d.getType().cast( o );
        } else {
          t = (T)ClassUtils.evaluate( o, d.getType(), false );
        }
        if ( t != null && d.contains( t ) ) {
          return true;
        }
      } catch ( Throwable e ) {
        // ignore
      }
    }
    return false;
  }
  
  public static <T> Domain< T > emptyDomain() {
    SingleValueDomain< T > d = new SingleValueDomain<T>();
    return d;
  }
}
