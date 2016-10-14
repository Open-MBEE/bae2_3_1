/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.ae.util.SimulatedTime;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.FileUtils;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.SocketClient;
import gov.nasa.jpl.mbee.util.TimeUtils;
import gov.nasa.jpl.mbee.util.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import junit.framework.Assert;

/**
 *
 */
public class EventSimulation extends java.util.TreeMap< Integer, Set< Pair< Object, Object > > > {

  // Constants & Types

  private static final long serialVersionUID = 7629618647715394322L;

  public static final String homeDir = System.getProperty("user.home");
  public static final String gitDir = homeDir + File.separator + "git";
  public static final String enthoughtDir = homeDir + File.separator + "canopy";
  public static final String enthoughtPythonPath = gitDir + "/bae/src/gov/nasa/jpl/ae/magicdrawPlugin".replaceAll( "/", File.separator ) + File.pathSeparator + enthoughtDir + File.separator + "lib";
  public static final String enthoughtPython = enthoughtDir + "/bin/python".replaceAll( "/", File.separator );
  public static final String enthoughtTempDir = File.separator + "tmp";

  private static boolean writingTimelinesToFile = true;
  public static String csvDir = "data" + File.separator + "output";

  public static double maxSecondsToNextEvent = 43200;
  
  // Members
  
  // Members: properties for plotting
  
  /**
   * Whether or not the external plotter should be launched and connected to by
   * socket.
   */
  public boolean tryToPlot = true; // see DurativeEvent.doPlot that overrides this variable.
  
  /**
   * Whether to limit the simulation to the horizon bounds and include an event
   * for the horizon.
   */
  public boolean simulatingHorizon = true;

  TimeUtils.Units plotAxisTimeUnits = TimeUtils.Units.seconds;
  public boolean usingSamplePeriod = true;

  public double plotSamplePeriod = 
      60.0 / Timepoint.conversionFactor( TimeUtils.Units.minutes ); // convert to minutes
  protected String hostOfPlotter = "127.0.0.1";
  // Trying to pick a port that would not have been used by another running instance. 
  protected int port = 
      (int)( 6000 + ( ( System.currentTimeMillis() / 1000 ) % 2000 ) );
  protected Thread readStdoutPlotThread = null;
  protected Thread readStderrPlotThread = null;
  
  protected DurativeEvent topEvent = null;
  
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
  
  Set<Event> events = new LinkedHashSet<Event>();
  Map< Object, Object > currentPlottableValues =
      new TreeMap< Object, Object >( new CompareUtils.GenericComparator< Object >() );
  Map< Object, String > categories = new HashMap< Object, String >();
  
  SocketClient plotSocket = null;
  Process plotProcess = null;
  List<Executor> executors = new ArrayList<Executor>();
  //public Collection<Plottable> plottables = new ArrayList<Plottable>();
  protected Set<Plottable> projections = new HashSet< Plottable >();
    
  // New Constructors
  
  public EventSimulation( Collection<Event> events, double timeScale ) {
    this();
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
  
  /**
   * Write the Events out to CSV files.
   */
  public void writeEvents() {
    Map< String, Set< Event > > eventCategories =
        new LinkedHashMap< String, Set< Event > >();
    for ( Event event : events ) {
      if ( event == null ) continue;
      String name = event.getClass().getSimpleName();
      Utils.add( eventCategories, name, event );
    }
    
    // get the path where the output will be stored
    String path = Utils.isNullOrEmpty( csvDir ) ? "." : csvDir ;
    File p = new File(path);
    if ( !p.exists() ) {
      boolean succ = p.mkdirs();
      if (!succ) path = ".";
    }

    // write to file
    String dateFormat = "yyyy-DDD'T'HH:mm:ss.SSS";//TimeUtils.aspenTeeFormat;
    Calendar cal = TimeUtils.gmtCal;
    for ( String name : eventCategories.keySet() ) {
      String fileName = name + ".activities.csv";
      String pathAndFile = path + File.separator + fileName;
      StringBuffer sb = new StringBuffer();
      sb.append("Tstart Assigned,Activity Name,Tend Assigned,,,,,,,,,,");
      for ( Event event : eventCategories.get( name ) ) {
        String eventName = event.getName();
        if ( Utils.isNullOrEmpty( eventName ) ) {
          eventName = event.getClass().getSimpleName();
        }
        Timepoint startTime = event.getStartTime();
        if ( startTime == null || startTime.getValueNoPropagate() == null ) continue;
        String startDate = startTime.toTimestamp( dateFormat, cal );
        if ( startDate == null ) continue;
        Timepoint endTime = event.getEndTime();
        if ( endTime == null || endTime.getValueNoPropagate() == null ) continue;
        String endDate = endTime.toTimestamp( dateFormat, cal );
        if ( endDate == null ) continue;
        // Don't want to end the file with a newline because of a bug in TMS/MPSWeb.
        sb.append("\n" + startDate + "," + name + "," + endDate + ",,,,,,,,,,");
      }
      FileUtils.stringToFile( sb.toString(), pathAndFile );
    }
    
  }
  
  /**
   * Write the plottable timelines out to CSV files.
   */
  public void writeTimelines() {
    Set<String> fileNames = new HashSet<String>();
    Set<String> cats = new HashSet<String>();

    // Use category names for file names if they are unique.
    boolean areCategoriesUnique = true;
    for ( java.util.Map.Entry< Object, Object > e : currentPlottableValues.entrySet() ) {
      Object o = e.getKey();
      String cat = categories.get( o );
      if ( cat != null ) {
        if ( cats.contains( cat ) ) {
          areCategoriesUnique = false;
          break;
        }
        cats.add( cat );
      }
    }
    
    Map<String, Object> paramsAndTvms = topEvent.getTimeVaryingObjectMap( true, null );

    
    // Find a unique file name for each plottable timeline and write out to file.
    int ct = 0;
//    for ( java.util.Map.Entry< Object, Object > e : currentPlottableValues.entrySet() ) {
    //Map.Entry<String, TimeVarying<?>> obj;
    for ( Map.Entry<String, Object> entry : paramsAndTvms.entrySet() ) {
      Object o = entry.getValue();//e.getKey();
      // unwrap the timeline if stuffed in a parameter
      String name = entry.getKey(); 
      if ( o instanceof Parameter ) {
        //name = ((Parameter<?>)o).getName(); 
        o = ((Parameter<?>)o).getValueNoPropagate();
      }
//      if ( name != null && name.contains( "telecomPower" ) ) {
//        System.out.println("here3");
//      }
      if ( o instanceof TimeVaryingMap ) {
        TimeVaryingMap<?> tv = (TimeVaryingMap< ? >)o;
        String fileName = null;
        
        // get the name if not already gotten from the parameter
        boolean gotName = !Utils.isNullOrEmpty( name );
        if ( !gotName ) { name = tv.getName();
        gotName = !Utils.isNullOrEmpty( name ); }

        // use the category if unique
        String cat = categories.get(o);
        boolean gotCat = !Utils.isNullOrEmpty( cat );
        if ( areCategoriesUnique && gotCat ) {
          fileName = cat + ".csv";
        } else {
          // if the categories are not unique, use the timeline name appended to the category.
          if ( gotName ) fileName = name + ".csv";
          if ( !gotName || fileNames.contains( fileName ) ) {
            fileName = (gotCat ? cat + "_" : "")+ (gotName ? name : "") + ".csv";
          }
          if ( !gotCat && !gotName ) {
            fileName = "TL_" + o.hashCode() + ".csv";
          }
        }
        // If the file name is somehow not unique, number it.
        if ( fileNames.contains( fileName ) ) {
          fileName = fileName.replaceFirst( ".csv$", String.format( "%03d", ct++ ) + ".csv" );
          // If the file name is still somehow not unique, throw an error, give up, and move on to the next.
          if ( fileNames.contains( fileName ) ) {
            Debug.error( true, false, "Duplicate output timeline csv file name! " + fileName );
            continue;
          }
        }
        // remember the name 
        fileNames.add( fileName );
        
        // get the path where the output will be stored
        String path = Utils.isNullOrEmpty( csvDir ) ? "." : csvDir ;
        File p = new File(path);
        if ( !p.exists() ) {
          boolean succ = p.mkdirs();
          if (!succ) path = ".";
        }
        String pathAndFile = path + File.separator + fileName;
        
        // write to file
        String dateFormat = "yyyy-DDD'T'HH:mm:ss.SSSZ";//TimeUtils.aspenTeeFormat;
        Calendar cal = Calendar.getInstance( TimeZone.getTimeZone( "GMT" ) );
//        if ( pathAndFile.contains( "telecomPower" ) ) {
//          System.out.println("here");
//        }
        tv.toCsvFile( pathAndFile, "Data Timestamp,Data Value", dateFormat, cal );
      }
    }
  }
  
  // Returns whether the event was added properly. If the event was not added or
  // its start or stop were already added, the function returns false.
  public boolean add( Event event ) {
    
    if ( event != null ){
      if ( Debug.isOn() ) Debug.outln( "Adding event to simulation: " + event.getName() );
    } else {
      Assert.fail("Tried to add null event to simulation.");
      return false;
    }
    
    events.add( event );
    
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
      if ( Debug.isOn() ) {
        Debug.outln( "Warning: can't add event with no time information: "
                     + event.getName() );
      }
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
  
  public < V > boolean add( TimeVaryingMap< V > tv, String category ) {
    if ( tv == null ) {
      Assert.fail("Trying to add null event to simulation.");
      return false;
    }
    if ( Debug.isOn() ) Debug.outln( "Adding TimeVaryingMap to simulation category " + category + ": " + tv.getName() );
    
    categories.put( tv, category );
    
    if ( isProjectedPlottable( tv ) ) {
      boolean alreadyAdded = projections.add((Plottable)tv);
      return alreadyAdded;
    }
    
    boolean existingEntry = false;
//    Object lastValue = null;
//    boolean first = true;
    for ( Map.Entry< Parameter<Integer>, V > e : tv.entrySet() ) {
      if ( e.getKey() == null || e.getKey().getValueNoPropagate() == null ) {
        System.err.println( "Warning: adding entry, " + e
                            + ", of time varying map, " + tv.getName()
                            + ", with null time key to simulation!" );
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
  
  public < V > boolean add( Parameter< TimeVaryingMap< V > > tv, String category ) {
    if ( tv == null ) {
      Assert.fail("Trying to add null event to simulation.");
      return false;
    }
    TimeVaryingMap<V> tvm = tv.getValueNoPropagate();
    if ( tvm != null ) { 
      Assert.fail("Trying to add null event to simulation.");
      return false;
    }
    if ( Debug.isOn() ) Debug.outln( "Adding TimeVaryingMap to simulation category " + category + ": " + tv.getName() );
    
    categories.put( tv, category );
    
    if ( isProjectedPlottable( tvm ) ) {
      boolean alreadyAdded = projections.add((Plottable)tvm);
      return alreadyAdded;
    }
    
    boolean existingEntry = false;
    for ( Map.Entry< Parameter<Integer>, V > e : tvm.entrySet() ) {
      if ( e.getKey() == null || e.getKey().getValueNoPropagate() == null ) {
        System.err.println( "Warning: adding time varying map entry with null time key "
                            + " to simulation " + e );
        continue;
      }
      if ( !put( e.getKey().getValueNoPropagate(), tv, e.getValue() ) ) {
        existingEntry = true;
      }
    }
    if ( currentPlottableValues != null && !tvm.isEmpty() && 
         tvm instanceof Plottable ) {
      currentPlottableValues.put( tv,  tvm.firstEntry().getValue() );
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
    if ( writingTimelinesToFile) {
      writeEvents();
      writeTimelines();
    }
    
    
    this.timeScale = scale;
    PrintWriter w = new PrintWriter( os, true );
    //long startClock = -1;
    int lastT = -1;
    SimulatedTime simTimer = new SimulatedTime( timeScale );
    double lastSampleSimTime = Timepoint.getEpochTimepoint().getValue(false) - 1.0;
    double nextSampleSimTime = (tryToPlot && usingSamplePeriod)  ? simTimer.simStart : Integer.MAX_VALUE;
    
    if ( tryToPlot ) {
      //Debug.turnOn();
      initiatePlot();
      //Debug.turnOff();
    }
    boolean firstLoop = true;
    w.println("--- simulation start, timeScale = " + timeScale + " ---");
    boolean doneOnce = false;
    for ( Map.Entry< Integer, Set< Pair< Object, Object > > > e1 : entrySet() ) {
      for ( Pair< Object, Object > p : e1.getValue() ) {//.entrySet() ) {
        
        // Delay between events
        int nextEventSimTime = e1.getKey();
        if (firstLoop) {
          firstLoop = false;
          simTimer.reset();
        } else {
          try {
            while ( true ) {
              int simTimeToSleepUntil =
                  (int)Math.min( nextEventSimTime, nextSampleSimTime );
              if (Debug.isOn()) Debug.out("nextEventSimTime="+ nextEventSimTime);
              if (Debug.isOn()) Debug.out("nextSampleSimTime="+ nextSampleSimTime);
              if (Debug.isOn()) Debug.out("simTimeToSleepUntil="+ simTimeToSleepUntil);
              simTimer.sleepUntilSimTime( simTimeToSleepUntil );
              long simTime = simTimer.getSimTimePassed();
              if (Debug.isOn()) Debug.out("simTime="+ simTime);
              
              // Update the plot based on the sample period.
              while ( tryToPlot && (!doneOnce || ( usingSamplePeriod
                      && nextSampleSimTime <= simTime
                      && nextSampleSimTime <= nextEventSimTime
                      && (!simulatingHorizon || nextSampleSimTime <= Timepoint.getHorizonDuration()))) 
                      //&& nextPlotSimTime <= 500.0
                    ) {
                if (Debug.isOn()) Debug.out("nextSampleSimTime="+ nextSampleSimTime);
                doneOnce = true;
                plotValues( lastSampleSimTime, nextSampleSimTime );
                lastSampleSimTime = nextSampleSimTime;
                // Recompute this in case the time scale changes during
                // simulation.
                assert this.plotSamplePeriod > 0.0;
                nextSampleSimTime += this.plotSamplePeriod;
              }
              
              if ( nextSampleSimTime > nextEventSimTime ) break;
//              if ( nextEventSimTime <= simTime) break;
//              if ( simulatingHorizon && simTimer.passedHorizon() ) {
//                break;
//              }
            }
          } catch ( InterruptedException e ) {
            System.err.println("Simulation sleep interrupted unexpectedly.");
          }
        }
        
        // the event & value(s)
        if ( simulatingHorizon && nextEventSimTime >= Timepoint.getHorizonDuration() ) break;
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
        
        try {
          value = Expression.evaluate( value, null, false );
        } catch ( ClassCastException e ) {
          // TODO Auto-generated catch block
          //e.printStackTrace();
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
        if ( value instanceof MoreToString ) {
          Map<String,Object> options = new TreeMap< String, Object >();
          options.put( "withOwner", false );
          value = ( (MoreToString)value ).toString( false, false, null, options );
        }
        // TODO -- printing should be an Executor
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
        //if ( simulatingHorizon && simTimer.passedHorizon() ) break;
      }
      if ( simulatingHorizon && e1.getKey() >= Timepoint.getHorizonDuration() ) break;
      //if ( simulatingHorizon && simTimer.passedHorizon() ) break;
    }
    if ( simTimer.passedHorizon() ) {
      Debug.outln( "Passed horizon: getSimTimePassed()="
                   + simTimer.getSimTimePassed()
                   + " > Timepoint.getHorizonDuration()="
                   + Timepoint.getHorizonDuration() );
    }
    w.println("--- simulation end ---");
    closePlotSocket();
    joinIoThreads();
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
    //Debug.turnOn();
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
      try {
        plotProcess = rt.exec( pythonExe + " animatePlot.py " + port, newEnv, f );
                             //new String[] { pythonPath, mplPath }, f );
      } catch (Exception e) {
        e.printStackTrace();
      }
      if ( plotProcess == null ) {
        tryToPlot = false;
        return;
      }
      // Allow a half second for the process to start.
      readStdoutPlotThread = new Thread( new Runnable() {
        // save the debug state since it could be changed by another thread
        public boolean debugOn = Debug.isOn();
        public BufferedReader reader = null;
       
        @Override
        public void run() {
          if (debugOn)
            System.out.println( "[plot]: process output:" );
          InputStream is = plotProcess.getInputStream();
          reader = new java.io.BufferedReader(new InputStreamReader(is));
          // And print each line
          String s = null;
          try {
            while ((s = reader.readLine()) != null) {
              if (debugOn)
                System.out.println("[plot]: " + s);
            }
            reader.close();
          } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      });
      readStderrPlotThread = new Thread( new Runnable() {
        public boolean debugOn = Debug.isOn();
        public BufferedReader reader = null;
        @Override
        public void run() {
          if (debugOn)
            System.err.println( "[plot]: process error output:" );
          InputStream is = plotProcess.getErrorStream();
          reader = new BufferedReader(new InputStreamReader(is));
          // And print each line
          String s = null;
          try {
            while ((s = reader.readLine()) != null) {
              if (debugOn)
                System.err.println("[plot]: " + s);
            }
            reader.close();
          } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      });
      readStdoutPlotThread.start();
      readStderrPlotThread.start();

      try {
        Thread.sleep( 3000 );
      } catch ( InterruptedException e1 ) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      plotSocket = null;
      int numTries = 0;
      while ( ( plotSocket == null || !plotSocket.isConnected() )
              && numTries++ < 10 ) {
        try {
          Thread.sleep( 1000 );
        } catch ( InterruptedException e ) {
          e.printStackTrace();
        }

        // You can or maybe should wait for the process to complete
        // p.waitFor();
        // System.out.println("Process exited with code = " + p.exitValue());

        // Try to connect to the python program's socket.
        try {
          plotSocket = new SocketClient( hostOfPlotter, port );
        } catch ( Exception e ) {
          // ignore
          Debug.outln("failed to create socket");
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
          if (o instanceof Parameter) {
            nn = ((Parameter<?>)o).getName();
            o = ((Parameter<?>)o).getValueNoPropagate();
          } else if (o instanceof TimeVaryingMap) {
            nn = ((TimeVaryingMap<?>)o).getName();
          } else {
            nn = "..." + o.toString().split( "@" )[0];
          }
          if ( Debug.isOn() ) Debug.outln( "  sending line name " + nn);
          
          plotSocket.send( nn );
//          plotSocket.getDataOutputStream().writeInt(nn.length());
//          plotSocket.getDataOutputStream().writeChars( nn );
          
          if ( Debug.isOn() ) Debug.outln( "  sending subplot category " + nn);
          plotSocket.send( getCategory( o ) );          
        }
        
        /*int c = 0;
        while (c < currentPlottableValues.size()){
          if ( Debug.isOn() ) Debug.outln( "    " + c );
          plotSocket.getDataOutputStream().writeInt(c);
          c+=1;
        }*/
        
        
      } else {
        System.out.println("Giving up on plotting after " + numTries + " tries." );
        tryToPlot = false;
      }
    } catch ( IOException e ) {
      tryToPlot = false;
      if ( plotSocket != null && plotSocket.isConnected() ) {
        plotSocket.close();
      }
      System.out.println("Giving up on plotting." );
      e.printStackTrace();
    }
    if ( tryToPlot ) {
      plotProjections();
    } else {
      System.out.println("not plotting");
    }
  }

  /**
   * @param o
   * @return whether {@code o} is a projection of data (for visualization purposes)
   */
  protected boolean isProjectedPlottable( Object o ) {
    if ( (!(o instanceof gov.nasa.jpl.ae.fuml.ObjectFlow)) && 
             ( o instanceof Plottable && ( (Plottable)o ).isProjection() ) ) {//||
           //( o instanceof TimeVaryingMap && !( (TimeVaryingMap<?>)o ).isEmpty() &&
           // ( (TimeVaryingMap<?>)o ).firstEntry().getValue() instanceof TimeVarying ));
      return true;
    }
    if ( o instanceof Parameter ) {
      return isProjectedPlottable( ( (Parameter<?>)o ).getValueNoPropagate() );
    }
    return false;
  }

  public void plotProjectionsThatChangeAtTime( double time ) {
    plotProjectionsThatChangeAtTime( time, time );
  }
  public void plotProjectionsThatChangeAtTime( double lastTime, double time ) {
    for ( Plottable plottable : projections ) {
      if ( plottable instanceof TimeVaryingMap ) {
        TimeVaryingMap< ? > map = (TimeVaryingMap< ? >)plottable;
        NavigableMap< Parameter< Integer >, ? > m = map.subMap( (int)lastTime, false, (int)time, true );
        //if ( map.contains( (int)time ) ) {
        if ( !m.isEmpty() ) {
          plotProjection( map, (int)time );
        }
      }
    }
  }

  public void plotProjections() {
    for ( Plottable plottable : projections ) {
      if ( plottable instanceof TimeVaryingPlottableMap ) {
        plotProjection( (TimeVaryingMap< ? >)plottable );
      }
    }
  }

  protected void plotProjection( TimeVaryingMap< ? > plottable ) {
    // TODO Auto-generated method stub
    plotProjection( plottable, 0 );
  }

  public void plotProjection( TimeVaryingMap< ? > map, Integer t) {
    if ( map == null || plotSocket == null || !plotSocket.isConnected() ) {
      return;
    }
    if ( Debug.isOn() ) Debug.outln( "Attempting to plot projection at time t=" + t + " from " + map );
    // The array will contain the map's hash code followed by key-value pairs.
    Vector<Double> doubleVector = new Vector< Double >();
    try {
      plotSocket.send( "seriesData" );
      plotSocket.send( map.getName() );
      plotSocket.send( getCategory( map ) );
      //doubleVector.add( new Double(map.hashCode()) );
      int lastTime = Integer.MIN_VALUE;
      if ( !map.isEmpty() &&
           TimeVarying.class.isAssignableFrom( map.getType() ) ) {
        TimeVarying<?> tv = (TimeVarying< ? >)map.getValue( t );
        if ( tv != null && tv instanceof TimeVaryingMap ) {
          map = (TimeVaryingMap< ? >)tv;
        } else {
          Debug.error( true, "could not extract projection from nested TimeVaryingMap at time "
                             + t + " from " + map );
        }
      }
      if ( map == null || map.isEmpty() ) {
        throw new IllegalArgumentException( "Projection to plot is null or empty " + map );
      }
      if ( Debug.isOn() ) Debug.outln( "plotting projection: " + map );
      for ( Map.Entry< Parameter< Integer >, ? > e : map.entrySet() ) {
        Integer timeInteger = e.getKey().getValue();
        if ( timeInteger <= lastTime ) continue;
        lastTime = timeInteger.intValue();
        Double time =
            Timepoint.conversionFactor( this.plotAxisTimeUnits )
                * timeInteger.doubleValue();
        Object v = null;
        try {
          v = Expression.evaluate( map.getValue( timeInteger ), null, false );
        } catch ( ClassCastException e1 ) {
          // TODO Auto-generated catch block
          //e1.printStackTrace();
        } catch ( IllegalAccessException e1 ) {
          // TODO Auto-generated catch block
          //e1.printStackTrace();
        } catch ( InvocationTargetException e1 ) {
          // TODO Auto-generated catch block
          //e1.printStackTrace();
        } catch ( InstantiationException e1 ) {
          // TODO Auto-generated catch block
          //e1.printStackTrace();
        }
        assert v instanceof Double || v instanceof Integer || v instanceof Float
               || v instanceof Parameter;
        while ( v instanceof Parameter ) {
          v = ( (Parameter< ? >)v ).getValue( false );
        }
        if ( v instanceof Number ) {
          v = ( (Number)v ).doubleValue();
        } else if ( v instanceof Boolean ) {
          v = (((Boolean)v) ? 1.0: 0.0);
        }
        if ( Double.class.isInstance( v ) ) {
          doubleVector.add( time );
          doubleVector.add( (Double)v );
        }
      }
      plotSocket.send( doubleVector );
    } catch ( IOException e ) {
      plotSocket.close();
      tryToPlot = false;
      e.printStackTrace();
    }
  }

  private String getCategory( Object o ) {
    if ( categories.containsKey( o ) ) return categories.get( o );
    return "";
  }

  private void closePlotSocket() {
    if ( plotSocket != null && plotSocket.isConnected() ) {
      try {
        plotSocket.send( "quit" );
      } catch ( IOException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      plotSocket.close();
    }
  }
  
  protected void joinIoThreads() {
    if ( readStderrPlotThread != null ) {
      try {
        readStderrPlotThread.join( 0 ); // millis (0=forever)
        readStdoutPlotThread.join( 0 ); // millis (0=forever)
//        readStderrPlotThread.reader.close();
//        readStdoutPlotThread.reader.close();
      } catch ( InterruptedException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * Send the time and the values of all plottable variables at that time.
   * @param time the time, typically used as the x-axis of the plot.
   */
  protected void plotValues( double time ) {
    plotValues( time, time );
  }

  protected void plotValues( double lastTime, double time ) {
    if ( Debug.isOn() ) Debug.outln("called plotvalues @ " + time);
    plotProjectionsThatChangeAtTime( time );
    if ( currentPlottableValues == null || 
         plotSocket == null || !plotSocket.isConnected() ) {
      return;
    }
    double doubleArray[] = new double[currentPlottableValues.size()+1];
    doubleArray[0] = Timepoint.conversionFactor( this.plotAxisTimeUnits ) * time;
    int cnt = 1;
    //for ( Object v : currentPlottableValues.values() ) {
    for ( java.util.Map.Entry< Object, Object > e : currentPlottableValues.entrySet() ) {
      Object o = e.getKey();
      Object v = e.getValue();
      // TODO -- Support different sampling periods for different Plottables.
      if ( o instanceof Parameter ) {
        o = ((Parameter<?>)o).getValueNoPropagate();
      }
      if ( this.usingSamplePeriod && o instanceof TimeVarying && o instanceof Plottable ) {
        if( ((Plottable)o).okToSample() ) {
          try {
            v = Expression.evaluate( ((TimeVarying<?>)o).getValue( (int)time ), null, false );
          } catch ( ClassCastException e1 ) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
          } catch ( IllegalAccessException e1 ) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
          } catch ( InvocationTargetException e1 ) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
          } catch ( InstantiationException e1 ) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
          }
          if ( Debug.isOn() ) Debug.outln("plotting " + o.toString() + " = "+ v);
        }
      }
      assert v == null || v instanceof Double || v instanceof Integer|| v instanceof Float
              || v instanceof Boolean || v instanceof Parameter;
      while ( v instanceof Parameter ) {
        v = ( (Parameter<?>)v ).getValue(false);
      }
      if ( v instanceof Integer ) {
        v = ( (Integer)v ).doubleValue();
      }
      if ( v instanceof Float ) {
          v = ( (Float)v ).doubleValue();
      }
      if ( v instanceof Boolean ) {
        v = ( (Boolean)v ) ? 1.0 : 0.0;
      }
      if ( v == null ) v = 0.0;
      
      if ( Double.class.isInstance( v ) ) {
        if ( Debug.isOn() ) Debug.outln( "appending " + o.toString() + " at index " + cnt + " = "
                     + v );
        doubleArray[ cnt++ ] = ( (Double)v ).doubleValue();
      } else {
        Debug.error( "should have a double value for " + o.toString() + ": " + v );
        if ( Debug.isOn() ) {
          Debug.outln( "no value found! appending 0.0 for " + o.toString()
                       + " at index " + cnt + ", bad value: " + v );
        }
        doubleArray[ cnt++ ] = 0.0;
      }
    }
    try {
      plotSocket.send( "timepointData" );
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
    if ( simulatingHorizon  ) addHorizonEvent();
  }

  /**
   * @param comparator
   */
  public EventSimulation( Comparator< ? super Integer > comparator ) {
    super( comparator );
    if ( simulatingHorizon ) addHorizonEvent();
  }

  private void addHorizonEvent() {
    DurativeEvent event = new DurativeEvent( "horizon" );
    event.startTime.setValue( 0 );
    event.duration.setValue( Timepoint.getHorizonDuration() );
    add( event );
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
