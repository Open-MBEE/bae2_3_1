/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.event.Expression.Form;
import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;

/**
 * 
 *         REVIEW -- Consider using ConstructorCall as a replacement for
 *         EventInvocation.
 */
public class EventInvocation extends HasIdImpl implements HasParameters, Comparable< EventInvocation >{
  protected Class< ? extends Event > eventClass = null;
  protected String eventName = null;
  protected Object[] arguments = null;
  protected Map< String, Object > memberAssignments = null; // TODO -- REVIEW --
                                                            // not using; remove? 
  protected Constructor< ? extends Event > constructor = null;
  //protected Class< ? >[] constructorParameterTypes = null;
  protected Parameter< ? > enclosingInstance = null;
  protected Expression< TimeVaryingMap< ? > > fromTimeVarying = null;
  protected boolean stale = false;

  public EventInvocation( Class< ? extends Event > eventClass,
                          String eventName,
                          Object[] arguments ) {
    this.eventClass = eventClass;
    this.eventName = eventName;
    this.arguments = arguments;
  }

  public <T extends Event> EventInvocation( Class< T > eventClass,
                                            String eventName,
                                            //Class<?>[] constructorParameterTypes,
                                            //Constructor< T > constructor,
                                            Parameter< ? > enclosingInstance,
                                            Object[] arguments,
                                            Expression< TimeVaryingMap<?> > fromTimeVarying,
                                            Map< String, Object > memberAssignments ) {
    this.eventClass = eventClass;
    this.eventName = eventName;
    this.enclosingInstance  = enclosingInstance;
    this.arguments = arguments;
    this.fromTimeVarying  = fromTimeVarying;
    this.memberAssignments = memberAssignments;
    this.constructor = null;
    //this.constructorParameterTypes  = constructorParameterTypes;
    //this.constructor = constructor;
  }

  boolean simpleDeconstruct = true;
  protected Event event = null;
  
  @Override
  public void deconstruct() {
    this.eventClass = null;
    this.eventName = null;
    this.enclosingInstance  = null;
    this.fromTimeVarying = null;
    
    if (!simpleDeconstruct) {
    if ( this.arguments != null ) {
      for ( Object a : arguments ) {
        if ( a instanceof Expression ) {
          ((Expression<?>)a).deconstruct();
        } else if ( a instanceof Parameter ) {
          if ( ( (Parameter<?>)a ).getOwner() == null ) {
            ( (Parameter<?>)a ).deconstruct();
          }
        }
      }
      //arguments = null;
    }
    }
    if ( memberAssignments != null ) {
      this.memberAssignments.clear();
      //memberAssignments = null;
    }
  }
  
  public Event invoke() {
    if ( Debug.isOn() ) Debug.outln( "invoke(): " + this );
    if ( fromTimeVarying == null ) {
      this.event = invoke(null, null);
      return event;
    }
    TimeVaryingMap< ? > tvm = null;
    //try {
      tvm = Functions.getTimeline( fromTimeVarying );
//    } catch ( IllegalAccessException e ) {
//      e.printStackTrace();
//    } catch ( InvocationTargetException e ) {
//      e.printStackTrace();
//    } catch ( InstantiationException e ) {
//      e.printStackTrace();
//    }
    if ( tvm == null ) return null;
    Expression<?>[] exprArguments = Utils.toArrayOfType( arguments, Expression.class );
    DurativeEvent parent = new DurativeEvent(eventName, tvm, enclosingInstance, eventClass, exprArguments );
    parent.addDependency( parent.startTime,  new Expression< Long>(0) );
    parent.addDependency( parent.duration,  new Expression< Long>(1) );
    parent.elaborate( false );
    setStale( false );
    this.event = parent;
    return event;
  }
  
  public boolean repairFromTimeVarying(DurativeEvent parent, Expression<Boolean> condition) {
    if ( fromTimeVarying == null ) return false;
    if ( parent == null ) return false;
    if ( getDurativeEvent() == null ) {
      Debug.error(false, "repairFromTimeVarying(): getDurativeEvent() is null!" );
      return false;
    }
    TimeVaryingMap< ? > tvm = Functions.getTimeline( fromTimeVarying );
    Expression<?>[] exprArguments = Utils.toArrayOfType( arguments, Expression.class );
    boolean changed = getDurativeEvent().repairElaborationFromTimeVarying( tvm, enclosingInstance, eventClass, exprArguments, condition );//( tvm, eventClass, exprArguments );
    return changed;
  }

  
  public Event invoke(Parameter< Long> start, Parameter< Long> end) {
    Event event = constructEvent(start, end);
    
    if ( event != null ) {
      event.setName( eventName );
      assignMembers( event );
    }

    setStale( false );
    return event;
  }

  private Event constructEvent() {
    return constructEvent(null, null);
    
  }
  private Event constructEvent(Parameter< Long> start, Parameter< Long> end) {
    Event event = null;
    Pair< Constructor< ? >, Object[] > ctorAndArgs =
        // makeConstructor();
        ClassUtils.getConstructorForArgs( eventClass, arguments,
                                     ( enclosingInstance == null ) ? null
                                     : enclosingInstance.getValue(true) );
    constructor = (Constructor< ? extends Event >)ctorAndArgs.first;
    if ( ctorAndArgs == null || constructor == null ) {
        try {
          event = eventClass.newInstance();
          if ( start != null ) {
            // REVIEW -- Should this be a dependency instead?
            event.getStartTime().setValue(start.getValue());
          }
          if ( end != null ) {
            event.getEndTime().setValue(end.getValue());
          }
        } catch ( IllegalAccessException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch ( InstantiationException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        return event;
    }
    if ( Debug.isOn() ) Debug.outln("About to call newInstance on constructor=" + constructor + " with arguments=" + Utils.toString(arguments) );
    try {
      return constructor.newInstance( (Object[])ctorAndArgs.second );
    } catch ( InstantiationException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( IllegalAccessException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( IllegalArgumentException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( InvocationTargetException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  protected Constructor< ? extends Event > makeConstructor() {
/*
    boolean nonStaticInnerClass = Utils.isInnerClass( eventClass );
    Object newArgs[] = arguments;
    if ( nonStaticInnerClass ) {
      newArgs = new Object[arguments.length + 1];
      // Warning!  Assuming that this is the parent object for the inner class.
      assert eventClass.getEnclosingClass().isAssignableFrom( this.getClass() );
      assert enclosingInstance != null;
      newArgs[0] = enclosingInstance;
      for ( int i = 0; i < arguments.length; ++i ) {
        newArgs[ i + 1 ] = arguments[ i ];
      }
    }
*/    
/*    
    // Collect argument types to search for the constructor.
    int length = newArgs.length;
    constructorParameterTypes = new Class< ? >[ length ];
    for ( int i = 0; i < length; ++i ) {
      if ( newArgs[i] == null ) {
        // TODO -- This is an assumption.  We need a better way to find the constructor.
        constructorParameterTypes[i] = Expression.class;
        continue;
      }
      Class< ? > c = newArgs[ i ].getClass();
      if ( newArgs[ i ] instanceof Expression ) {
        c = Expression.class;
      }
      constructorParameterTypes[i] = c;
    }
*/    
    /*
    if ( nonStaticInnerClass && Utils.isNullOrEmpty( arguments ) ) {
      Class<?> enclosingClass = eventClass.getEnclosingClass();
      if ( arguments[0] instanceof Parameter
           && enclosingClass.isAssignableFrom( arguments[0].getClass() ) ) {
        newArgs = new Object[arguments.length];
        newArgs[0] = ((Parameter<?>)arguments[0]).getValue();
        for ( int i = 1; i < arguments.length; ++i ) {
          newArgs[ i ] = arguments[ i ];
        }
      }
    }
    */
    constructor =
        (Constructor< ? extends Event >)(ClassUtils.getConstructorForArgs( eventClass,
                                                                     arguments,
                                                                     enclosingInstance.getValue(true) )).first;
    return constructor;
  }

  private void assignMembers( Event event ) {
    if ( memberAssignments == null ) return;
    for ( Entry< String, Object > entry : memberAssignments.entrySet() ) {
        try {
          eventClass.getField( entry.getKey() ).set( event, entry.getValue() );
        } catch ( NoSuchFieldException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch ( IllegalAccessException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch ( IllegalArgumentException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch ( SecurityException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }
  }

  @Override
  public String toShortString() {
    return eventName + "()"; // + memberAssignments;
  }
  
  @Override
  public String toString() {
    return "EventInvocation:" + eventName + Utils.toString(arguments); // + memberAssignments;
  }

  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen ) {
    return toString( withHash, deep, seen, null );
  }

  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen,
                          Map< String, Object > otherOptions ) {
    return "EventInvocation:" + eventName
           + MoreToString.Helper.toString( arguments, withHash, deep, seen,
                                           otherOptions,
                                           MoreToString.SQUARE_BRACES ); // +
                                                                         // memberAssignments;
  }
  
  // This is done by the event's constructor.
//  // create dependencies from arguments
//  public void createDependencies( DurativeEvent event ) {
//    for ( Entry< String, Expression< ? > > e : arguments.entrySet() ) {
//      try {
//        Object o = eventClass.getField( e.getKey() ).get( event );
//      } catch ( IllegalArgumentException e1 ) {
//        // TODO Auto-generated catch block
//        e1.printStackTrace();
//      } catch ( SecurityException e1 ) {
//        // TODO Auto-generated catch block
//        e1.printStackTrace();
//      } catch ( IllegalAccessException e1 ) {
//        // TODO Auto-generated catch block
//        e1.printStackTrace();
//      } catch ( NoSuchFieldException e1 ) {
//        // TODO Auto-generated catch block
//        e1.printStackTrace();
//      }
//    }
//    // TODO -- REVIEW -- should satisfy() be called on dependencies?
//  }

  /**
   * @return the eventClass
   */
  public Class< ? extends Event > getEventClass() {
    return eventClass;
  }

  /**
   * @param eventClass
   *          the eventClass to set
   */
  public void setEventClass( Class< ? extends Event > eventClass ) {
    this.eventClass = eventClass;
  }

  /**
   * @return the eventName
   */
  public String getEventName() {
    return eventName;
  }

  /**
   * @param eventName
   *          the eventName to set
   */
  public void setEventName( String eventName ) {
    this.eventName = eventName;
  }

  /**
   * @return the arguments
   */
  public Object[] getArguments() {
    return arguments;
  }

  /**
   * @param arguments
   *          the arguments to set
   */
  public void setArguments( Object[] arguments ) {
    this.arguments = arguments;
  }

  /**
   * @return the memberAssignments
   */
  public Map< String, Object > getMemberAssignments() {
    return memberAssignments;
  }

  /**
   * @param memberAssignments
   *          the memberAssignments to set
   */
  public void setMemberAssignments( Map< String, Object > memberAssignments ) {
    this.memberAssignments = memberAssignments;
  }

  /**
   * @return the constructor
   */
  public Constructor< ? extends Event > getConstructor() {
    return constructor;
  }

  /**
   * @param constructor
   *          the constructor to set
   */
  public void
      setConstructor( Constructor< ? extends DurativeEvent > constructor ) {
    this.constructor = constructor;
  }  

  public DurativeEvent getDurativeEvent() {
    if ( event instanceof DurativeEvent ) return (DurativeEvent)event;
    return null;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent( Event event ) {
    this.event  = event;
  }

  
  @Override
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< Parameter< ? > > params = 
        HasParameters.Helper.getParameters( getArguments(), deep, seen );
    if ( fromTimeVarying != null ) {
      if ( fromTimeVarying.form == Form.Parameter ) {
        params.add( (Parameter< ? >)fromTimeVarying.expression );
      } else {
        Object v = null;
        try {
          v = Expression.evaluate( fromTimeVarying, Parameter.class, false );
        } catch ( ClassCastException e ) {
        } catch ( IllegalAccessException e ) {
        } catch ( InvocationTargetException e ) {
        } catch ( InstantiationException e ) {
        }
        if ( v instanceof Parameter ) {
          params.add( (Parameter< ? >)v );
        }
      } 
    }
    return params;
  }

  @Override
  public Set< Parameter< ? > > getFreeParameters( boolean deep,
                                                  Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< Parameter< ? > > s = new TreeSet< Parameter< ? > >();
    for ( Object a : getArguments() ) {
      if ( a instanceof HasParameters )
      s = Utils.addAll( s, ((HasParameters)a).getFreeParameters( deep, seen ) );
    }
    return s;
  }

  @Override
  public void setFreeParameters( Set< Parameter< ? >> freeParams,
                                 boolean deep,
                                 Set< HasParameters > seen ) {
    Assert.assertTrue( "This method is not supported!", false );
  }
  
  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep,
                             Set< HasParameters > seen ) {
    return substitute( p1, (Object)p2, deep, seen );
  }
  @Override
  public boolean substitute( Parameter< ? > p1, Object p2, boolean deep,
                             Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    return HasParameters.Helper.substitute( getArguments(), p1, p2, deep, seen );
  }

  public boolean isStaleNoPropagate() {
    return stale;
  }

  @Override
  public boolean isStale() {
    if ( stale ) return true;
//    if ( HasParameters.Helper.isStale( getArguments(), false, null ) ) {
//      setStale( true );
//    }
    if ( !stale && isTimeVaryingStale() ) {
      setStale( true );
    }
    return stale;
  }
  
  public boolean isTimeVaryingStale() {
    boolean s = fromTimeVarying != null && fromTimeVarying.isStale();
    return s;
  }



  @Override
  public void setStale( boolean staleness ) {
    if ( !stale && staleness ) {
      System.out.println( "event invocation stale: " + this );
    }
    stale = staleness;
  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                               Set< HasParameters > seen ) {
    boolean has = HasParameters.Helper.hasParameter( this, parameter, deep, seen );
    return has;
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep,
                                  Set< HasParameters > seen ) {
    return getFreeParameters( deep, seen ).contains( p );
//    // REVIEW -- Is this just done by Events? Maybe throw
//    // assertion that this method id not supported for ElaborationRule.
//    return false;
  }

  @Override
  public int compareTo( EventInvocation o ) {
    return compareTo( o, false );
  }
  public int compareTo( EventInvocation o, boolean checkId ) {
    if ( this == o ) return 0;
    if ( o == null ) return -1;
    if ( checkId ) return CompareUtils.compare( getId(), o.getId() );
    int compare = CompareUtils.compare( eventName, o.eventName );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( arguments, o.arguments, true );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( this, o );
    if ( compare != 0 ) return compare;
    return 0;
  }

  public boolean setStaleAnyReferenceTo( Parameter< ? > p, Set< HasParameters > seen ) {
    if ( p == null ) return false;
    //if ( p != null ) return false; // short circuiting
    if ( fromTimeVarying == null ) return false;

    Pair< Boolean, Set< HasParameters > > sp = Utils.seen( this, true, seen );
    if (sp.first) return false;
    seen = sp.second;
//    Set<Parameter<?> > params = getParameters( false, null );
//    if ( params.contains( p ) ) {
//      setStale( true );
//      return true;
//    }
    // Sometimes the effect?Var has a timeline that matches the input parameter.
    TimeVaryingMap< ? > tvm1 = Functions.tryToGetTimelineQuick( fromTimeVarying );
    if ( tvm1 == null ) {
      try {
        tvm1 = Expression.evaluate( fromTimeVarying, TimeVaryingMap.class, true );
      } catch ( ClassCastException e ) {
        e.printStackTrace();
      } catch ( IllegalAccessException e ) {
        e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        e.printStackTrace();
      } catch ( InstantiationException e ) {
        e.printStackTrace();
      }
    }
    TimeVaryingMap< ? > tvm2 = Functions.tryToGetTimelineQuick( p );
    if ( tvm1 == tvm2 ) {
      setStale(true);
      return true;
    }
//    if ( p.getValueNoPropagate() instanceof TimeVaryingMap ) {
//      TimeVaryingMap<?> tvm = (TimeVaryingMap< ? >)p.getValueNoPropagate();
//      TimeVaryingMap<?> tvm2 = Functions.tryToGetTimelineQuick( o );
//      for ( Parameter<?> pp : params ) {
//        Object o = pp.getValueNoPropagate();
//        TimeVaryingMap<?> tvm2 = Functions.tryToGetTimelineQuick( o ); 
//        if ( tvm.equals( tvm2 ) ) {
//          setStale(true);
//          return true;
//        }
//      }
//    }
    return false;
  }

}
