/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.event.Timepoint.Units;
import gov.nasa.jpl.ae.util.CompareUtils;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.MoreToString;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.SimulatedTime;
import gov.nasa.jpl.ae.util.SocketClient;
import gov.nasa.jpl.ae.util.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import junit.framework.Assert;

/**
 * @author bclement
 *
 */
public class EventSimulation extends java.util.TreeMap< Integer, Set< Pair< Object, Object > > > {

  // Constants & Types

  private static final long serialVersionUID = 7629618647715394322L;
  //private static final String enthoughtPythonPath = "/Applications/OpsRevMD1702-20120818/plugins/com.nomagic.magicdraw.jpython/scripts/magicdrawPlugin:/Library/Frameworks/Python.framework/Versions/7.3/lib";
  //private static final String enthoughtPython = "/Library/Frameworks/Python.framework/Versions/7.3/bin/Python";
  private static final String enthoughtPythonPath = "c:\\Users\\bclement\\workspace\\CS\\src\\gov\\nasa\\jpl\\ae\\magicdrawPlugin;c:\\Python27\\Lib";
  private static final String enthoughtPython = "c:\\Python27\\python.exe";
  //private static final String enthoughtTempDir = "c:\\temp";
  
  // Members
  
  // Members: properties for plotting
  
  /**
   * Whether or not the external plotter should be launched and connected to by
   * socket.
   */
  boolean tryToPlot = true;
  Timepoint.Units plotAxisTimeUnits = Timepoint.Units.hours;
  public boolean usingSamplePeriod = true;
  public double plotSamplePeriod = 900.0 / Units.conversionFactor( Units.seconds ); // 15 min
  protected String hostOfPlotter = "127.0.0.1";
  // Trying to pick a port that would not have been used by another running instance. 
  protected int port = 
      (int)( 6000 + ( ( System.currentTimeMillis() / 1000 ) % 2000 ) );
  //  protected static java.util.Random portRandomNumberGenerator =
//      new java.util.Random( System.currentTimeMillis() );
//  protected Pair<Integer, Integer > portRange = new Pair( 65000, 66000 );

  public enum EventType {
    start,
    end
    }

  public static class ObjectComparator implements Comparator< Object > {

    @Override
    public int compare( Object o1, Object o2 ) {
      if ( o1 == o2 ) return 0;
      if ( o1 == null ) return -1;
      if ( o2 == null ) return 1;
      return CompareUtils.compare( o1, o2, true, true );
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
      if ( Debug.isOn() ) Debug.outln( realResult );
    }

    @Override
    public void run() {
      initiatePlotUsingInterpreter();
    }
  }
*/

  // Members: other

  double timeScale;
  
  Map< Object, Object > currentPlottableValues =
      new TreeMap< Object, Object >( new CompareUtils.GenericComparator< Object >() );

  SocketClient plotSocket = null;
  Process plotProcess = null;
  List<Executor> executors = new ArrayList<Executor>();
  public Collection<Plottable> plottables = new ArrayList<Plottable>();
  protected Set<Plottable> projections = new HashSet< Plottable >();
    
  // New Constructors
  
  public EventSimulation( Collection<Event> events, double timeScale ) {
    super();
    for ( Event e : events ) {
      add( e );
    }
    this.timeScale = timeScale;
  }

  protected boolean put( Integer time, Object variable, Object value ) {
    Set< Pair< Object, Object > > m = get( time );
    if ( m == null ) {
      //m = new TreeMap< Object, Object >( new ObjectComparator() );
      m = new TreeSet< Pair< Object, Object > >( new ObjectComparator() );
      put( time, m );
    }
    Pair< Object, Object > p = new Pair< Object, Object >( variable, value );
    //Object o = m.put( variable, value );
    boolean existingEntry = m.contains( p );//( o != null );
    m.add( p );
    if ( existingEntry ) {
      if ( Debug.isOn() ) Debug.outln( "replaced existing entry: " + p );
    }
    return !existingEntry;
  }
  
  // Methods
  
  // Returns whether the event was added properly. If the event was not added or
  // its start or stop were already added, the function returns false.
  public boolean add( Event event ) {
    
    if ( event != null ){
      if ( Debug.isOn() ) Debug.outln( "Adding event to simulation: " + event.getName() );
    } else {
      Assert.fail("Tried to add null event to simulation.");
      return false;
    }
    
    boolean ungroundedTiming =
        ( event.getStartTime() == null
          || event.getStartTime().getValueNoPropagate() == null
          || event.getEndTime() == null || event.getEndTime().getValueNoPropagate() == null );
//    if ( ungroundedTiming ) {
//      if ( Debug.isOn() ) Debug.errln( "Warning: trying to add ungrounded event to simulation: " + e.getName() );
//    }

    // Get start and end times if they exist.
    Integer startTime = event.getStartTime().getValueOrMin();
    Integer endTime = event.getEndTime().getValueOrMax();
    if ( startTime == null || endTime == null ) {
      Debug.outln( "Warning: can't add event with no time information: "
                   + event.getName() );
      return false;
    } else if ( ungroundedTiming ) {
      if ( Debug.isOn() ) {
        Debug.outln( "Warning: not adding event " + event.getName()
                     + " to simulation with ungrounded time interval ["
                     + startTime + ", " + endTime + "]" );
      }
      return false;
    }
    
    // Put entries for the startTime and endTime in the map.
    boolean existingEntry = !put( startTime, event, EventType.start );
    if ( !put( endTime, event, EventType.end ) ) {
      existingEntry = true;
    }
    
    if ( existingEntry ) {
      if ( Debug.isOn() ) Debug.errln( "Entry already exists! " + event );
    }
//    if ( Debug.isOn() ) Debug.outln( "Simulation after addition:\n" + this );
    return !existingEntry;
  }
  
  public < V > boolean add( TimeVaryingMap< V > tv ) {
    if ( tv == null ) {
      Assert.fail("Trying to add null event to simulation.");
      return false;
    }
    if ( Debug.isOn() ) Debug.outln( "Adding TimeVaryingMap to simulation: " + tv.getName() );
    
    if ( tv instanceof Plottable && ((Plottable)tv).isProjection() ) {
      return projections.add((Plottable)tv);
    }
    
    boolean existingEntry = false;
    Object lastValue = null;
    boolean first = true;
    for ( Map.Entry< Parameter<Integer>, V > e : tv.entrySet() ) {
      if ( e.getKey() == null || e.getKey().getValueNoPropagate() == null ) {
        System.err.println( "Warning: adding time varying map entry with null time key "
                            + " to simulation " + e );
        continue;
      }
//      if ( first ) first = false;
//      else if ( lastValue == e.getValue() ||
//                ( e.getValue() != null && e.getValue().equals( lastValue ) ) ) {
//        continue;
//      }
      if ( !put( e.getKey().getValueNoPropagate(), tv, e.getValue() ) ) {
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
    try {
      simulate( timeScale, System.out );
    } catch ( Exception e ) {
      e.printStackTrace();
    }
  }
  
  public void simulate( java.io.OutputStream os ) {
    simulate( this.timeScale, os );
  }
  
  public void simulate( double scale, java.io.OutputStream os ) {
    this.timeScale = scale;
    PrintWriter w = new PrintWriter( os, true );
    //long startClock = -1;
    int lastT = -1;
    SimulatedTime simTimer = new SimulatedTime( timeScale );
    double nextPlotSimTime = (tryToPlot && usingSamplePeriod)  ? simTimer.simStart : Integer.MAX_VALUE;
    
    if ( tryToPlot ) {
      //Debug.turnOn();
      initiatePlot();
      //Debug.turnOff();
    }
    boolean firstLoop = true;
    w.println("--- simulation start, timeScale = " + timeScale + " ---");
    for ( Map.Entry< Integer, Set< Pair< Object, Object > > > e1 : entrySet() ) {
      for ( Pair< Object, Object > p : e1.getValue() ) {//.entrySet() ) {
        
        // Delay between events
        int nextEventSimTime = e1.getKey();
        if (firstLoop) {
          firstLoop = false;
          simTimer.reset();
        } else {
          // Update the plot based on the sample period.
          try {
            while ( true ) {
              int simTimeToSleepUntil =
                  (int)Math.min( nextEventSimTime, nextPlotSimTime );
              simTimer.sleepUntilSimTime( simTimeToSleepUntil );
              int simTime = simTimer.getSimTimePassed();
              while ( tryToPlot && usingSamplePeriod
                      && nextPlotSimTime <= simTime
                      && nextPlotSimTime <= Timepoint.getHorizonDuration() 
                      //&& nextPlotSimTime <= 500.0
                      ) {
                plotValues( nextPlotSimTime );
                // Recompute this in case the time scale changes during
                // simulation.
                assert this.plotSamplePeriod > 0.0;
                nextPlotSimTime += this.plotSamplePeriod;
              }
              if ( nextEventSimTime <= simTime) break;
            }
          } catch ( InterruptedException e ) {
            System.err.println("Simulation sleep interrupted unexpectedly.");
          }
        }
        
        // the event & value(s)
        int t = e1.getKey().intValue();
        Object variable = p.first; //e2.getKey();
        Object value = p.second; //e2.getValue();
        Object originalValue = value; //e2.getValue();
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
          exec.execute( nextEventSimTime, name, shortClassName, longClassName,
                        ( value == null ? "null" : value.toString() ) );
        }
        
        value = Expression.evaluate( value, null, false );
        if ( value instanceof MoreToString ) {
          Map<String,Object> options = new TreeMap< String, Object >();
          options.put( "withOwner", false );
          value = ( (MoreToString)value ).toString( false, false, null, options );
        }
        // todo -- printing should be an Executor
        String formatString = null;
        if ( t == lastT ) {
          String padding = Utils.spaces( 47 );
          formatString = "%s%-60s   %s\n";
          w.printf( formatString, padding, name + " -> " +
                    ( value == null ? "null" : value.toString() ), classNames );
        } else {
          if ( tryToPlot && !usingSamplePeriod ) {
            if ( currentPlottableValues != null && currentPlottableValues.containsKey( variable ) ) {
              currentPlottableValues.put( variable, originalValue );
            }
            plotValues( t );
          }
          formatString = "%14s : %28s  %-60s   %s\n";
          w.printf( formatString,
                    ( new Duration( t, null ) ).toStringWithUnits( false, false ),
                    Timepoint.toTimestamp( t ),
                    name + " -> " + ( value == null ? "null" : value.toString() ),
                    classNames );
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
      if ( Debug.isOn() ) Debug.outln( "Plot process stdout" );
      InputStreamReader reader =
          new InputStreamReader( plotProcess.getInputStream() );
      char buf[] = new char[10000];
      try {
        reader.read( buf, 0, 10000 );
        if ( Debug.isOn() ) Debug.outln( new String( buf ) );
      } catch ( IOException e ) {
        e.printStackTrace();
      }
      if ( Debug.isOn() ) Debug.outln( "Plot process stderr" );
      reader = new InputStreamReader( plotProcess.getInputStream() );
      try {
        reader.read( buf, 0, 10000 );
        if ( Debug.isOn() ) Debug.outln( new String( buf ) );
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
      if ( Debug.isOn() ) Debug.outln( "No plottable values." );
      tryToPlot = false;
      return;
    }
    if ( Debug.isOn() ) Debug.outln( "initiatePlot()" );
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
      if ( Debug.isOn() ) Debug.outln("System.getProperty(\"user.dir\") = " + curDir );
//      //if ( Debug.isOn() ) Debug.outln("System.getenv()[PYTHONPATH] = " + pythonPath );
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
      //plotProcess = rt.exec( pythonExe + " test.py ");
      plotProcess = rt.exec( pythonExe + " animatePlot.py " + port, newEnv, f );
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

      plotSocket = null;
      int numTries = 0;
      while ( ( plotSocket == null || !plotSocket.isConnected() ) && numTries++ < 5 ) {
      try {
        Thread.sleep( 500 );
      } catch ( InterruptedException e ) {
        e.printStackTrace();
      }

      // You can or maybe should wait for the process to complete
      //p.waitFor();
      //System.out.println("Process exited with code = " + p.exitValue());

      // Try to connect to the python program's socket.
      try {
      plotSocket = new SocketClient( hostOfPlotter , port );
      } catch ( Exception e ) {
        // ignore
      }
      }
      // Need to send a 1 so that the python socket server knows the correct
      // endianness.
      if ( plotSocket.isConnected() && currentPlottableValues.size() > 0 ) {
        plotSocket.getDataOutputStream().writeInt(1);
        //plotSocket.getDataOutputStream().writeInt(currentPlottableValues.size());
        
        //test - try sending integers to name things...
        if ( Debug.isOn() ) Debug.outln( "sending number of lines: " + currentPlottableValues.size() );
        plotSocket.getDataOutputStream().writeInt(currentPlottableValues.size());
        
        if ( Debug.isOn() ) Debug.outln( "sending line names... ");
        
        for ( java.util.Map.Entry< Object, Object > e : currentPlottableValues.entrySet() ) {
          Object o = e.getKey();
          String nn = "?";
          if (o instanceof TimeVaryingMap){
            nn = ((TimeVaryingMap)o).getName();
          } else {
            nn = "..." + o.toString().split( "@" )[0];
          }
          if ( Debug.isOn() ) Debug.outln( "  sending line " + nn);
          
          plotSocket.getDataOutputStream().writeInt(nn.length());
          plotSocket.getDataOutputStream().writeChars( nn );
          
          
        }
        
        /*int c = 0;
        while (c < currentPlottableValues.size()){
          if ( Debug.isOn() ) Debug.outln( "    " + c );
          plotSocket.getDataOutputStream().writeInt(c);
          c+=1;
        }*/
        
        
      } else {
        tryToPlot = false;
      }
    } catch ( IOException e ) {
      tryToPlot = false;
      e.printStackTrace();
    }
    if ( tryToPlot ) {
      plotProjections();
    }
  }

  public void plotProjections() {
    for ( Plottable plottable : projections ) {
      if ( plottable instanceof TimeVaryingPlottableMap ) {
        plotProjection( (TimeVaryingPlottableMap< ? >)plottable );
      }
    }
  }

  public void plotProjection( TimeVaryingPlottableMap< ? > map ) {
    if ( map == null || plotSocket == null || !plotSocket.isConnected() ) {
      return;
    }
    // The array will contain the map's hash code followed by key-value pairs.
    Vector<Double> doubleVector = new Vector< Double >();
    doubleVector.add( new Double(map.hashCode()) );
    int lastTime = Integer.MIN_VALUE;
    for ( Map.Entry< Parameter< Integer >, ? > e : map.entrySet() ) {
      Integer timeInteger = e.getKey().getValue();
      if ( timeInteger <= lastTime ) continue;
      lastTime = timeInteger.intValue();
      Double time =
          Timepoint.Units.conversionFactor( this.plotAxisTimeUnits )
              * timeInteger.doubleValue();
      Object v = Expression.evaluate( map.getValue( timeInteger ), null, false );
      assert v instanceof Double || v instanceof Integer
             || v instanceof Parameter;
      while ( v instanceof Parameter ) {
        v = ( (Parameter< ? >)v ).getValue( false );
      }
      if ( v instanceof Integer ) {
        v = ( (Integer)v ).doubleValue();
      }
      if ( Double.class.isInstance( v ) ) {
        doubleVector.add( time );
        doubleVector.add( (Double)v );
      }
    }
    try {
      plotSocket.send( doubleVector );
    } catch ( IOException e ) {
      plotSocket.close();
      tryToPlot = false;
      e.printStackTrace();
    }
  }

  private void closePlotSocket() {
    if ( plotSocket != null && plotSocket.isConnected() ) {
      plotSocket.close();
    }
  }

  /**
   * Send the time and the values of all plottable variables at that time.
   * @param time the time, typically used as the x-axis of the plot.
   */
  protected void plotValues( double time ) {
    System.out.println("called plotvalues @ " + time);
    if ( currentPlottableValues == null || 
         plotSocket == null || !plotSocket.isConnected() ) {
      return;
    }
    double doubleArray[] = new double[currentPlottableValues.size()+1];
    doubleArray[0] = Timepoint.Units.conversionFactor( this.plotAxisTimeUnits ) * time;
    int cnt = 1;
    //for ( Object v : currentPlottableValues.values() ) {
    for ( java.util.Map.Entry< Object, Object > e : currentPlottableValues.entrySet() ) {
      Object o = e.getKey();
      Object v = e.getValue();
      // TODO -- Support different sampling periods for different Plottables.
      if ( this.usingSamplePeriod && o instanceof TimeVarying && o instanceof Plottable ) {
        if( ((Plottable)o).okToSample() ) {
          v = Expression.evaluate( ((TimeVarying<?>)o).getValue( (int)time ), null, false );
          System.out.println("plotting " + o.toString() + " = "+ v);
        }
      }
      assert v instanceof Double || v instanceof Integer || v instanceof Parameter;
      while ( v instanceof Parameter ) {
        v = ( (Parameter<?>)v ).getValue(false);
      }
      if ( v instanceof Integer ) {
        v = ( (Integer)v ).doubleValue();
      }
      if ( Double.class.isInstance( v ) ) {
        doubleArray[ cnt++ ] = ((Double)v).doubleValue();
      }
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

  public int numEvents() {
    int sum = 0;
    for ( Map.Entry< Integer, Set< Pair< Object, Object > > > e : entrySet() ) {
      if ( e.getValue() != null ) sum += e.getValue().size();
    }
    return sum;
  }

//  /**
//   * @param m
//   */
//  public EventSimulation( Map< ? extends Integer, ? extends Map< Object, Object > > m ) {
//    super( m );
//    // TODO Auto-generated constructor stub
//  }
//
//  /**
//   * @param m
//   */
//  public EventSimulation( SortedMap< Integer, ? extends Map< Object, Object > > m ) {
//    super( m );
//    // TODO Auto-generated constructor stub
//  }

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
