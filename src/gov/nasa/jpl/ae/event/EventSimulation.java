/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.util.Debug;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import junit.framework.Assert;

/**
 * @author bclement
 *
 */
public class EventSimulation extends java.util.TreeMap< Integer, Map< Object, Object > > {

  // Constants & Types

  private static final long serialVersionUID = 7629618647715394322L;

  public enum EventType {
    start,
    end
    }
  
  public static class ObjectComparator implements Comparator< Object > {

    @Override
    public int compare( Object o1, Object o2 ) {
      int compare = o1.toString().compareTo( o2.toString() );
      if ( compare != 0 ) return compare;
      return Integer.compare( o1.hashCode(), o2.hashCode() );
    }
    
  }

  // Members
  
//  // epochMillis is a timestamp corresponding to TimePoint = 0 as the date/time that the simulation starts.
//  // It is an offset of the number of milliseconds since Jan 1, 1970.
//  // For example, if epochMillis == 1341614935000, then a TimePoint or int value
//  // of 0 corresponds to Fri, Jul 06, 2012 3:48:55 PM.
//  // This number comes from using the 'date' unix command:
//  //    $ date; date '+%s'
//  //    Fri, Jul 06, 2012  3:48:55 PM
//  //    1341614935
//  long epochMillis; 
  
  // New Constructors
  
  public EventSimulation( Collection<Event> events) {
    super();
    for ( Event e : events ) {
      add( e );
    }
  }

  protected boolean put( Integer time, Object variable, Object value ) {
    Map< Object, Object > m = get( time );
    if ( m == null ) {
      m = new TreeMap< Object, Object >( new ObjectComparator() );
      put( time, m );
    }
    boolean existingEntry = ( m.put( variable, value ) != null );
    return !existingEntry;
  }
  
  // Methods
  
  // Returns whether the event was added properly. If the event was not added or
  // its start or stop were already added, the function returns false.
  public boolean add( Event e ) {
    
    if ( e != null ){
      //Debug.outln( "Adding event to simulation: " + e.getName() );
    } else {
      Assert.fail("Trying to add null event to simulation.");
      return false;
    }
    
    boolean existingEntry = !put( e.getStartTime().getValue(), e, EventType.start );
    if ( !put( e.getEndTime().getValue(), e, EventType.end ) ) {
      existingEntry = true;
    }

//    Map< Event, StartOrEnd > m = get( e.getStartTime().getValue() );
//    if ( m == null ) {
//      m = new TreeMap< Event, EventSimulation.StartOrEnd >();
//      put( e.getStartTime().getValue(), m );
//    }
//    boolean existingEntry = ( m.put( e, StartOrEnd.start ) != null );
//    
//    m = get( e.getEndTime().getValue() );
//    if ( m == null ) {
//      m = new TreeMap< Event, EventSimulation.StartOrEnd >();
//      put( e.getEndTime().getValue(), m );
//    }
//    existingEntry |= ( m.put( e, StartOrEnd.end ) != null );
    if ( existingEntry ) {
      Debug.outln( "Entry already exists!" );
    }
//    Debug.outln( "Simulation after addition:\n" + this );
    return !existingEntry;
  }
  
  public < V > boolean add( TimeVaryingMap< V > tv ) {
    if ( tv == null ) {
      Assert.fail("Trying to add null event to simulation.");
      return false;
    }
    boolean existingEntry = false;
    for ( Map.Entry< Timepoint, V > e : tv.entrySet() ) {
      if ( !put( e.getKey().getValue(), tv, e.getValue() ) ) {
        existingEntry = true;
      }
    }
    return !existingEntry;
  }
  
  public void simulate( long tickInMilliseconds, java.io.OutputStream os ) {
    PrintWriter w = new PrintWriter( os, true );
    long startClock = -1;
    w.println("--- simulation start ---");
    for ( Map.Entry< Integer, Map< Object, Object > > e1 : entrySet() ) {
      for ( Map.Entry< Object, Object > e2 : e1.getValue().entrySet() ) {
        if (startClock == -1) {
          startClock = System.currentTimeMillis();
        } else {
          long waitMillis = System.currentTimeMillis() - startClock - e1.getKey(); 
          if ( waitMillis > 0 ) {
            try {
              Thread.sleep( waitMillis );
            } catch ( InterruptedException e ) {
              // Something unexpected interrupted our sleep.
              e.printStackTrace();
            }
          }
        }
        int t = e1.getKey().intValue();
        Object variable = e2.getKey();
        Event event = null;
        Object value = null;
        String name;
        if ( variable instanceof ParameterListener ) {
          name = ((ParameterListener)variable).getName();
        } else {
          name = variable.getClass().getSimpleName();
        }
        if ( variable instanceof Event ) {
          event = (Event)variable;
        } else if ( variable instanceof TimeVarying ) {
          value = ((TimeVarying<?>)variable).getValue( t );
        }
        w.printf( "%14s : %7s %s %s\n",
                  ( new Duration( t, null ) ).toStringWithUnits( false, false ),
                  //t, Timepoint.getUnits().toShortString(),
                  Timepoint.toTimestamp( t ),
                  name, e2.getValue() );//, ( (value==null) ? "" : ( " " + value ) ) );
      }
    }
    w.println("--- simulation end ---");
  }

  public String toString() {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    simulate( 0, os );
    return os.toString();
  }
  
  // Inherited Constructors
  
  /**
   * 
   */
  public EventSimulation() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @param comparator
   */
  public EventSimulation( Comparator< ? super Integer > comparator ) {
    super( comparator );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param m
   */
  public EventSimulation( Map< ? extends Integer, ? extends Map< Object, Object > > m ) {
    super( m );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param m
   */
  public EventSimulation( SortedMap< Integer, ? extends Map< Object, Object > > m ) {
    super( m );
    // TODO Auto-generated constructor stub
  }

//  /**
//   * @return the epochMillis
//   */
//  public long getEpochMillis() {
//    return epochMillis;
//  }
//
//  /**
//   * @param epochMillis the epochMillis to set
//   */
//  public void setEpochMillis( long epochMillis ) {
//    this.epochMillis = epochMillis;
//  }


}
