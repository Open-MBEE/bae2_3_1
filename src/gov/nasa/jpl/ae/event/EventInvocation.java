/**
 * 
 */
package gov.nasa.jpl.ae.event;

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
 */
public class EventInvocation implements HasParameters {
  protected Class< ? extends Event > eventClass = null;
  protected String eventName = null;
  protected Expression< ? >[] arguments = null;
  protected Map< String, Object > memberAssignments = null; // TODO -- REVIEW --
                                                            // not using; remove? 
  protected Constructor< ? extends Event > constructor = null;

  public EventInvocation( Class< ? extends Event > eventClass,
                          String eventName,
                          Expression< ? >[] arguments ) {
    this.eventClass = eventClass;
    this.eventName = eventName;
    this.arguments = arguments;
  }

  public <T extends Event> EventInvocation( Class< T > eventClass,
                                            String eventName,
                                            Constructor< T > constructor,
                                            Expression< ? >[] arguments,
                                            Map< String, Object > memberAssignments ) {
    this.eventClass = eventClass;
    this.eventName = eventName;
    this.arguments = arguments;
    this.memberAssignments = memberAssignments;
    this.constructor = constructor;
  }

  public Event invoke() {
    Event event = constructEvent();
    event.setName( eventName );
    
    if ( event != null ) {
      assignMembers( event );
    }

    return event;
  }

  private Event constructEvent() {
    Event event = null;
    if ( constructor == null ) {
        try {
          event = eventClass.newInstance();
        } catch ( InstantiationException | IllegalAccessException e ) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        return event;
    }

    try {
      return constructor.newInstance( (Object[])arguments );
    } catch ( InstantiationException | IllegalAccessException
              | IllegalArgumentException | InvocationTargetException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  private void assignMembers( Event event ) {
    if ( memberAssignments == null ) return;
    for ( Entry< String, Object > e : memberAssignments.entrySet() ) {
        try {
          eventClass.getField( e.getKey() ).set( event, e.getValue() );
        } catch ( IllegalArgumentException | IllegalAccessException
                  | NoSuchFieldException | SecurityException e1 ) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
    }
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
   * @return the arguments
   */
  public Expression< ? >[] getArguments() {
    return arguments;
  }

  /**
   * @param arguments
   *          the arguments to set
   */
  public void setArguments( Expression< ? >[] arguments ) {
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
  public Set< Parameter< ? > > getParameters( boolean deep ) {
    Set< Parameter< ? > > s = new TreeSet< Parameter< ? > >();
    for ( Expression< ? > a : getArguments() ) {
      s.addAll( a.getParameters( deep ) );
    }
    return s;
  }

  @Override
  public Set< Parameter< ? > > getFreeParameters( boolean deep ) {
    Set< Parameter< ? > > s = new TreeSet< Parameter< ? > >();
    for ( Expression< ? > a : getArguments() ) {
      s.addAll( a.getFreeParameters( deep ) );
    }
    return s;
  }

  @Override
  public void setFreeParameters( Set< Parameter< ? >> freeParams ) {
    Assert.assertTrue( "This method is not supported!", false );
  }
  
  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep ) {
    boolean subbed = false;
    for ( Expression< ? > a : getArguments() ) {
      if ( a.substitute( p1, p2, deep ) ) {
        subbed = true;
      }
    }
    return subbed;
  }

  @Override
  public boolean isStale() {
    for ( Parameter< ? > p : getParameters( false ) ) {
      if ( p.isStale() ) return true;
    }
    return false;
  }

  @Override
  public void setStale( boolean staleness ) {
    // TODO -- REVIEW -- Need anything here?
    assert false;
  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep ) {
    return getParameters( deep ).contains( parameter );
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep ) {
    // REVIEW -- Is this just done by Events? Maybe throw
    // assertion that this method id not supported for ElaborationRule.
    return false;
  }
  
}
