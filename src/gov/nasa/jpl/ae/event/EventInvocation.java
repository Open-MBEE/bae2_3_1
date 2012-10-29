/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.util.ClassUtils;
import gov.nasa.jpl.ae.util.CompareUtils;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;

/**
 * @author bclement
 * 
 *         TODO -- This class is not done and may need to be scrapped in favor
 *         of expressions with references (pointers to members of objects that
 *         may not yet be instantiated).
 * 
 *         REVIEW -- Consider using ConstructorCall as a replacement for
 *         EventInvocation.
 */
public class EventInvocation implements HasParameters, Comparable< EventInvocation >{
  protected Class< ? extends Event > eventClass = null;
  protected String eventName = null;
  protected Object[] arguments = null;
  protected Map< String, Object > memberAssignments = null; // TODO -- REVIEW --
                                                            // not using; remove? 
  protected Constructor< ? extends Event > constructor = null;
  //protected Class< ? >[] constructorParameterTypes = null;
  protected Parameter< ? > enclosingInstance = null;

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
                                            Map< String, Object > memberAssignments ) {
    this.eventClass = eventClass;
    this.eventName = eventName;
    this.enclosingInstance  = enclosingInstance;
    this.arguments = arguments;
    this.memberAssignments = memberAssignments;
    //this.constructorParameterTypes  = constructorParameterTypes;
    //this.constructor = constructor;
  }

  public Event invoke() {
    if ( Debug.isOn() ) Debug.outln( "invoke(): " + this );
    Event event = constructEvent();
    
    if ( event != null ) {
      event.setName( eventName );
      assignMembers( event );
    }

    return event;
  }

  private Event constructEvent() {
    Event event = null;
    Pair< Constructor< ? >, Object[] > ctorAndArgs =
        // makeConstructor();
        ClassUtils.getConstructorForArgs( eventClass, arguments,
                                     ( enclosingInstance == null ) ? null
                                     : enclosingInstance.getValue() );
    constructor = (Constructor< ? extends Event >)ctorAndArgs.first;
    if ( ctorAndArgs == null || constructor == null ) {
        try {
          event = eventClass.newInstance();
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
                                                                     enclosingInstance.getValue() )).first;
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
  public String toString() {
    return "EventInvocation:" + eventName + Utils.toString(arguments); // + memberAssignments;
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

  @Override
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    return HasParameters.Helper.getParameters( getArguments(), deep, seen );
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
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    return HasParameters.Helper.substitute( getArguments(), p1, p2, deep, seen );
  }

  @Override
  public boolean isStale() {
    return HasParameters.Helper.isStale( getArguments(), false, null );
  }

  @Override
  public void setStale( boolean staleness ) {
    // TODO -- REVIEW -- Need anything here?
    assert false;
  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                               Set< HasParameters > seen ) {
    return getParameters( deep, seen ).contains( parameter );
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
    if ( this == o ) return 0;
    if ( o == null ) return -1;
    int compare = CompareUtils.compareTo( eventName, o.eventName );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compareTo( arguments, o.arguments, true );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compareTo( this, o );
    if ( compare != 0 ) return compare;
    return 0;
  }
  
}
