/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.SocketClient;
import gov.nasa.jpl.ae.util.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.python.core.util.StringUtil;

//import org.python.core.PyObject;
//import org.python.util.PythonInterpreter;

import junit.framework.Assert;

/**
 * @author bclement
 *
 */
public class EventSimulation extends java.util.TreeMap< Integer, Map< Object, Object > > {

  // Constants & Types

  private static final long serialVersionUID = 7629618647715394322L;
  private static final String enthoughtPythonPath = "c:\\Users\\bclement\\workspace\\CS\\src\\gov\\nasa\\jpl\\ae\\magicdrawPlugin;c:\\Python27\\Lib";
  private static final String enthoughtPython = "c:\\Python27\\python.exe";
  private static final String enthoughtTempDir = "c:\\temp";

  public enum EventType {
    start,
    end
    }
  
  public static class ObjectComparator implements Comparator< Object > {

    @Override
    public int compare( Object o1, Object o2 ) {
      int compare = o1.toString().compareTo( o2.toString() );
      if ( compare != 0 ) return compare;
      return Utils.intCompare( o1.hashCode(), o2.hashCode() );
    }
    
  }

/* Running python from Java using PythonInterpreter, available from jython. 
  private static class PlotPythonInterpreter implements Runnable {
    protected void initiatePlotUsingInterpreter() {
      PythonInterpreter interpreter = new PythonInterpreter();
      // interpreter.execFile("animatePlot.py");
      interpreter.exec( "import animatePlot" );
      // execute a function that takes a string and returns a string
      PyObject someFunc = interpreter.get( "animatePlot.main" );
      PyObject result = someFunc.__call__();
      String realResult = (String)result.__tojava__( String.class );
      Debug.outln( realResult );
    }

    @Override
    public void run() {
      initiatePlotUsingInterpreter();
    }
  }
*/
  // Members
  
  /**
   * Whether or not the external plotter should be launched and connected to by
   * socket.
   */
  boolean tryToPlot = true;
  Timepoint.Units timeUnits = Timepoint.Units.hours;
  double timeScale;
  
  Map< Object, Object > currentPlottableValues = new HashMap< Object, Object >();

  SocketClient plotSocket = null;
  Process plotProcess = null;
  List<Executor> executors = new ArrayList<Executor>();
  
  
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
  
  public EventSimulation( Collection<Event> events, double timeScale ) {
    super();
    for ( Event e : events ) {
      add( e );
    }
    this.timeScale = timeScale;
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
      Debug.outln( "Adding event to simulation: " + e.getName() );
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
    Debug.outln( "Adding TimeVaryingMap to simulation: " + tv.getName() );
    boolean existingEntry = false;
    Object lastValue = null;
    boolean first = true;
    for ( Map.Entry< Timepoint, V > e : tv.entrySet() ) {
      if ( first ) first = false;
      else if ( lastValue == e.getValue() ||
                ( e.getValue() != null && e.getValue().equals( lastValue ) ) ) {
        continue;
      }
      if ( !put( e.getKey().getValue(), tv, e.getValue() ) ) {
        existingEntry = true;
      }
    }
    if ( currentPlottableValues != null && !tv.isEmpty() && 
         tv instanceof Plottable ) {
//        ( tv.firstEntry().getValue() instanceof Double ||
//            tv.firstEntry().getValue() instanceof Integer ) ) {
      currentPlottableValues.put( tv,  tv.firstEntry().getValue() );
    }
    return !existingEntry;
  }
  
  public void add( Executor exec ) {
    executors.add( exec );
  }
  
  public void simulate( double timeScale ) {
    simulate( timeScale, System.out );
  }
  
  public void simulate( java.io.OutputStream os ) {
    simulate( this.timeScale, os );
  }
  
  public void simulate( double timeScale, java.io.OutputStream os ) {
    PrintWriter w = new PrintWriter( os, true );
    long startClock = -1;
    int lastT = -1;
    
    if ( tryToPlot ) {
      //Debug.turnOn();
      initiatePlot();
      //Debug.turnOff();
    }
    w.println("--- simulation start, timeScale = " + timeScale + " ---");
    for ( Map.Entry< Integer, Map< Object, Object > > e1 : entrySet() ) {
      for ( Map.Entry< Object, Object > e2 : e1.getValue().entrySet() ) {
        
        // Delay between events
        //Debug.outln("startClock = " + startClock );
        double nextEventTime = 0.0;
        if (startClock == -1) {
          startClock = System.currentTimeMillis();
        } else {
          nextEventTime = Duration.durationToMillis( e1.getKey() );
          double nextEventTimeScaled = nextEventTime / timeScale;
          double timePassed = ( (double)System.currentTimeMillis() ) - startClock;
          long waitMillis =
              (long)Math.min( (double)Long.MAX_VALUE / 2,
                              ( nextEventTimeScaled - timePassed  ) );
//          Debug.outln("timePassed = " + timePassed );
//          Debug.outln("nextEventTime = " + nextEventTime );
//          Debug.outln("nextEventTimeScaled = " + nextEventTimeScaled );
//          Debug.outln("waitMillis = " + waitMillis );
          if ( waitMillis > 0 ) {
            try {
              Thread.sleep( waitMillis );
            } catch ( InterruptedException e ) {
              // Something unexpected interrupted our sleep.
              e.printStackTrace();
            }
          }
        }
//        Debug.outln("current millis = " + System.currentTimeMillis() );
        
        // the event & value(s)
        int t = e1.getKey().intValue();
        Object variable = e2.getKey();
        Object value = e2.getValue();
        if ( currentPlottableValues != null && currentPlottableValues.containsKey( variable ) ) {
          currentPlottableValues.put( variable, value );
        }
        // the names of the event
        String name;
        String longClassName = variable.getClass().getName();
        String shortClassName = variable.getClass().getSimpleName();
        String classNames = shortClassName + " ==> " + longClassName;
        if ( variable instanceof ParameterListener ) {
          name = ((ParameterListener)variable).getName();
        } else {
          name = variable.getClass().getSimpleName();
        }
        
        // get String for Double
        if ( value instanceof Double ) {
          value = String.format( "%.2f", value );
        }
        
        // unleash the executors!
        for ( Executor exec : executors ) {
          exec.execute( nextEventTime, name, shortClassName, longClassName,
                        value.toString() );
        }
        
        // todo -- printing should be an Executor
        String formatString = null;
        if ( t == lastT ) {
          String padding = Utils.spaces( 47 );
          formatString = "%s%s -> %-40s     %s\n";
          w.printf( formatString, padding, name,
                    value == null ? "null" : value.toString(), classNames );
        } else {
          if ( tryToPlot ) {
            plotValues( t );
          }
          formatString = "%14s : %28s  %s -> %-40s     %s\n";
          w.printf( formatString,
                    ( new Duration( t, null ) ).toStringWithUnits( false, false ),
                    Timepoint.toTimestamp( t ),
                    name, value == null ? "null" : value.toString(), classNames );
        }
        lastT = t;
      }
    }
    w.println("--- simulation end ---");
    closePlotSocket();
//    if ( tryToPlot ) {
//      getPlotProcessOutput();
//    }
  }

  protected void getPlotProcessOutput() {
    if ( plotProcess != null ) {
      Debug.outln( "Plot process stdout" );
      InputStreamReader reader =
          new InputStreamReader( plotProcess.getInputStream() );
      char buf[] = new char[10000];
      try {
        reader.read( buf, 0, 10000 );
        Debug.outln( new String( buf ) );
      } catch ( IOException e ) {
        e.printStackTrace();
      }
      Debug.outln( "Plot process stderr" );
      reader = new InputStreamReader( plotProcess.getInputStream() );
      try {
        reader.read( buf, 0, 10000 );
        Debug.outln( new String( buf ) );
      } catch ( IOException e ) {
        e.printStackTrace();
      }
    }
  }

/* An alternative way to run the python plotter.
  protected void initiatePlot2() {
    PlotPythonInterpreter i = new PlotPythonInterpreter();
    Thread t = new Thread( i );
    t.start();
  }
*/

  protected void initiatePlot() {
    //Map< Object, Integer > varIndices = null;
    if ( Utils.isNullOrEmpty( currentPlottableValues ) ) {
      Debug.outln( "No plottable values." );
      tryToPlot = false;
      return;
    }
    Debug.outln( "initiatePlot()" );
    try {
      // Run the python plot program as a system command.
      java.lang.Runtime rt = java.lang.Runtime.getRuntime();
      // Note that this requires that animatePlot.py be in the
      // src/gov/nasa/jpl/ae/magicdrawPlugin directory.
      // This python program requires special libraries that
      // are included with the Enthought distribution.
      String curDir = System.getProperty("user.dir");
      Map< String, String > env = System.getenv();
      String pythonPath = env.get( "PYTHONPATH" );
      Debug.outln("System.getProperty(\"user.dir\") = " + curDir );
//      //Debug.outln("System.getenv()[PYTHONPATH] = " + pythonPath );
//      //File curPath = new File(curDir);
//      //Arrays.asList( curPath.listFiles() );
//      String[] pythonPaths = pythonPath.split( File.pathSeparator );
//      List<String> pathList = Arrays.asList( pythonPaths );
//      pathList.add( 0, enthoughtPythonPath );
      pythonPath = //"PYTHONPATH=" + 
          enthoughtPythonPath;// + File.pathSeparator + pythonPath;//Utils.join(pathList, File.pathSeparator); //pathList.toArray()
      //String mplPath = //"MPLCONFIGDIR="  
      //     enthoughtTempDir;
      //env.put( "PYTHONPATH", pythonPath );
      //env.put( "MPLCONFIGDIR", enthoughtTempDir );
      String[] newEnv = new String[env.size()];
      int ct = 0;
      for ( Map.Entry< String, String > envar : env.entrySet() ) {
        if ( envar.getKey().equals( "PYTHONPATH" ) ) {
          newEnv[ct++] = envar.getKey() + "=" + pythonPath;
        } else {
          newEnv[ct++] = envar.getKey() + "=" + envar.getValue();
        }
      }
      if ( !curDir.contains( "src" ) && !curDir.contains( "magicDrawPlugin" ) ) {
        curDir += File.separator + "src" + File.separator + "gov"
            + File.separator + "nasa" + File.separator + "jpl"
            + File.separator + "ae" + File.separator
            + "magicdrawPlugin";
      }
      File f = new File( curDir  );
      String pythonExe = enthoughtPython;
      if ( Utils.isNullOrEmpty( pythonExe ) ) pythonExe = "python";
      plotProcess = rt.exec( pythonExe + " animatePlot.py", newEnv, f );
                             //new String[] { pythonPath, mplPath }, f );
      // Allow a half second for the process to start.
      Thread t = new Thread( new Runnable() {
        // save the debug state since it could be changed by another thread
        boolean debugOn = Debug.isOn();
       
        @Override
        public void run() {
          if (debugOn)
            System.out.println( "[plot]: process output:" );
          java.io.InputStream is = plotProcess.getInputStream();
          java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
          // And print each line
          String s = null;
          try {
            while ((s = reader.readLine()) != null) {
              if (debugOn)
                System.out.println("[plot]: " + s);
            }
            is.close();
          } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      });
      Thread t2 = new Thread( new Runnable() {
        boolean debugOn = Debug.isOn();
       
        @Override
        public void run() {
          if (debugOn)
            System.err.println( "[plot]: process error output:" );
          java.io.InputStream is = plotProcess.getErrorStream();
          java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
          // And print each line
          String s = null;
          try {
            while ((s = reader.readLine()) != null) {
              if (debugOn)
                System.err.println("[plot]: " + s);
            }
            is.close();
          } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      });
      t.start();
      t2.start();
      
      try {
        Thread.sleep( 3000 );
      } catch ( InterruptedException e ) {
        e.printStackTrace();
      }

      // You can or maybe should wait for the process to complete
      //p.waitFor();
      //System.out.println("Process exited with code = " + p.exitValue());

      // Try to connect to the python program's socket.
      plotSocket = new SocketClient( "127.0.0.1", 60002 );
      // Need to send a 1 so that the python socket server knows the correct
      // endianness.
      if ( plotSocket.isConnected() && currentPlottableValues.size() > 0 ) {
        plotSocket.getDataOutputStream().writeInt(1);
        plotSocket.getDataOutputStream().writeInt(currentPlottableValues.size());
      } else {
        tryToPlot = false;
      }
    } catch ( IOException e ) {
      tryToPlot = false;
      e.printStackTrace();
    }
  }

  private void closePlotSocket() {
    if ( plotSocket != null && plotSocket.isConnected() ) {
      plotSocket.close();
    }
  }

  protected void plotValues( double time ) {
    if ( currentPlottableValues == null || 
         plotSocket == null || !plotSocket.isConnected() ) {
      return;
    }
    double doubleArray[] = new double[currentPlottableValues.size()+1];
    doubleArray[0] = Timepoint.Units.conversionFactor( this.timeUnits ) * time;
    int cnt = 1;
    for ( Object v : currentPlottableValues.values() ) {
      assert v instanceof Double || v instanceof Integer;
      doubleArray[ cnt++ ] = ((Double)v).doubleValue();
    }
    try {
      plotSocket.send( doubleArray );
    } catch ( IOException e ) {
      plotSocket.close();
      tryToPlot = false;
      e.printStackTrace();
    }
  }

  public String toString() {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    simulate( 1.0e300, os );
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
