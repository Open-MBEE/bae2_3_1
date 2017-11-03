/**
 * 
 */
package demandResponse;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;

import gov.nasa.jpl.ae.event.Consumable;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.ParameterListenerImpl;
import gov.nasa.jpl.ae.event.TimeVaryingPlottableMap;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.mbee.util.TimeUtils;

/**
 * 
 */
public class Customer extends ParameterListenerImpl {

  public enum CustomerType { summer, summerProfile };
  
  public boolean additive = false;
  public long offsetSecondsFromMidnight = 0;
  public long loadHorizon = Timepoint.getHorizonDuration(); 
  public double maxLoad = 3.5;  // kW
  public double minLoadFraction = 0.4;
  public double varianceFactor = 0.7;  // this is not Gaussian variance
  public double chanceOfChange = 0.8;
  public double correctionFactor = 0.1;
  public long samplePeriod = 900; // may only support seconds!
  public long seed = 3;//System.currentTimeMillis();
  public Random numGen = gov.nasa.jpl.mbee.util.Random.global;
  
  public boolean DREventExists = false;
  public DRObject drEvent = null;
  protected double DRParticipation = numGen.nextDouble();
  
  /**
   * The customer load behavior over time.  Here's a gnuplot command for its basic shape over a 24 hour period:
   * <p>gnuplot
   * <p>plot [0:24][0:1] f(x)=(1-m)*(sin(2*pi*(x/24-0.4))/2+0.5)+m, m=0.2, p=0.3, pi=3.14159, f(x)
   */
  public TimeVaryingPlottableMap<Double> load = null;
  public Consumable additiveLoad = null;

  public Parameter< TimeVaryingPlottableMap< Double > > loadParameter = null;
  public Parameter< Consumable > additiveLoadParameter = null;
  public Method summerLoadMethod = null;
  public Method summerLoadDeltaMethod = null;

  /**
   * 
   */
  public Customer() {
    super();
    init();
  }

  /**
   * @param name
   */
  public Customer( String name ) {
    super( name );
    if ( name.equals( "summerCustomer" ) ) {
      
    }
    init();
  }
  
  void init() {
    //System.out.println("called init()");
    if ( DREventExists ) {
      drEvent = new DRObject();
    }
    //this.numGen = gov.nasa.jpl.ae.solver.Random.global;
    double defaultValue = summerLoad(0);
    load = new TimeVaryingPlottableMap< Double >( "load", getSummerLoadMethod(), this,
                                                  samplePeriod, loadHorizon );
    additiveLoad = new Consumable( "additiveLoad", defaultValue,
                                   getSummerLoadDeltaMethod(), this,
                                   samplePeriod, loadHorizon,
                                   minLoadFraction*maxLoad/3.0,
                                   Double.POSITIVE_INFINITY);
    loadParameter =
        new Parameter< TimeVaryingPlottableMap< Double > >( "load", null, load, this );
    additiveLoadParameter =
        new Parameter< Consumable >( "load", null, additiveLoad, this );
    parameters.add( additiveLoadParameter );
  }

  /**
   * @param offsetSecondsFromMidnight
   * @param loadHorizon
   * @param maxLoad
   * @param minLoadFraction
   * @param seed
   */
  public Customer( long offsetSecondsFromMidnight, long loadHorizon,
                   double maxLoad, double minLoadFraction, 
                   double varianceFactor, long seed ) {
    super();
    this.offsetSecondsFromMidnight = offsetSecondsFromMidnight;
    this.loadHorizon = loadHorizon;
    this.maxLoad = maxLoad;
    this.minLoadFraction = minLoadFraction;
    this.varianceFactor = varianceFactor;
    this.seed = seed;
    init();
  }

  public Customer( Date start, long loadHorizon,
                   double maxLoad, double minLoadFraction, 
                   double varianceFactor, long seed ) {
    super();
    this.offsetSecondsFromMidnight = (long)(Timepoint.timeSinceMidnight(start) * Timepoint.conversionFactor( TimeUtils.Units.seconds ));
    this.loadHorizon = loadHorizon;
    this.maxLoad = maxLoad;
    this.minLoadFraction = minLoadFraction;
    this.varianceFactor = varianceFactor;
    this.seed = seed;
    init();
  }

  public Customer( CustomerType type ) {
    this( Timepoint.getEpoch(), 24 * 3600, 3.5, 0.2,
          ( type==Customer.CustomerType.summer ? 0.15 : 0.0 ), 3 ); 
  }
  
  public Customer( CustomerType type, boolean projected ) {
    this( Timepoint.getEpoch(), 24 * 3600, 3.5, 0.2,
          ( type==Customer.CustomerType.summer ? 0.15 : 0.0 ), 3 );
   load.setProjection( projected ); 
   additiveLoad.setProjection( projected ); 
  }
  
  public double getMaxLoad( double t, boolean includeAnyDrEvent ) {
    if ( !includeAnyDrEvent || drEvent == null ) return maxLoad;
    double reduction = drEvent.predictedLoadReduction( t / Timepoint.conversionFactor( TimeUtils.Units.seconds ) );
    reduction = reduction * DRParticipation / 1000.0; // covert from W to kW -- FIXME
    // System.out.println(maxLoad + " - drEvent.predictedLoadReduction(" + t + ")=" + reduction + " = " + (maxLoad - reduction) );
    return maxLoad - reduction;
  }
  
  public double summerLoadProfile( long timeSecs, boolean includeAnyDrEvent ) {
    long t = offsetSecondsFromMidnight + timeSecs;
    double kWatts = (  minLoadFraction * maxLoad
                       + getMaxLoad(timeSecs,includeAnyDrEvent) * ( 1 - minLoadFraction )
                         * ( 0.5 + 0.5 * Math.sin( 2 * Math.PI 
                                                   * ( t / ( 24 * 3600.0 ) - 0.4 ) ) ) );
    //if ( Debug.isOn() ) Debug.outln("summerLoadProfile(" + timeSecs + ") = " + kWatts );
    return kWatts;
  }
  
  public double summerLoad( long timeSecs ) {
    double g = numGen.nextGaussian();
    double d = numGen.nextDouble();
    double kWatts =
        summerLoadProfile( timeSecs, true )
            * ( 1 + g * varianceFactor );
//          * ( 1 + Math.pow( d, 1 / varianceFactor ) );
    //if ( Debug.isOn() ) Debug.outln("summerLoad(" + timeSecs + ") = " + kWatts );
    return kWatts;
  }
  
  public double summerLoadDelta( long timeSecsBefore, Double kWattsBefore,
                                 long timeSecsNext ) {
    double kWattsDelta = 0;
    double kWattsPredictedBefore = summerLoadProfile( timeSecsBefore, true );
    double kWattsPredictedNext = summerLoadProfile( timeSecsNext, true );
    double kWattsVariedNext = summerLoad( timeSecsNext );
    double kWattsOffNominal = kWattsBefore - kWattsPredictedBefore;
    boolean change = ( numGen.nextDouble() < chanceOfChange );
    //double kWattsNext = ;
    kWattsDelta = change ? ( kWattsVariedNext - kWattsPredictedBefore 
                             - kWattsOffNominal * correctionFactor )
                         : 0;
////    if ( Debug.isOn() ) Debug.outln( "summerLoadDelta(timeSecsBefore=" + timeSecsBefore
////                        + ", kWattsBefore=" + kWattsBefore + ", timeSecsAfter="
////                        + timeSecsNext + "): kWattsPredictedBefore="
////                        + kWattsPredictedBefore + "; kWattsPredictedNext="
////                        + kWattsPredictedNext + "; kWattsVariedNext="
////                        + kWattsVariedNext + "; kWattsOffNominal="
////                        + kWattsOffNominal + "; change=" + change
////                        + "; kWattsDelta=" + kWattsDelta );
//    if ( Debug.isOn() ) Debug.out( String.format( "summerLoadDelta(timeSecsBefore=%6d, kWattsBefore=%.2f"
//                           + ", timeSecsAfter=%6d): kWattsPredictedBefore=%.2f"
//                           + "; kWattsPredictedNext=%.2f; kWattsVariedNext=%.2f"
//                           + "; kWattsOffNominal=%.2f; change=%b"
//                           + "; kWattsDelta=%.2f%n", timeSecsBefore,
//                       kWattsBefore, timeSecsNext, kWattsPredictedBefore,
//                       kWattsPredictedNext, kWattsVariedNext, kWattsOffNominal,
//                       change, kWattsDelta ) );
    return kWattsDelta;
  }
  
  public Method getSummerLoadMethod() {
    if ( summerLoadMethod == null ) {
      summerLoadMethod = null;
      try {
        summerLoadMethod = Customer.class.getMethod( "summerLoad", long.class );
      } catch ( NoSuchMethodException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( SecurityException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return summerLoadMethod;
  }
 
  public Method getSummerLoadDeltaMethod() {
    if ( summerLoadDeltaMethod == null ) {
      summerLoadDeltaMethod = null;
      try {
        summerLoadDeltaMethod =
            Customer.class.getMethod( "summerLoadDelta", long.class,
                                      Double.class, long.class );
      } catch ( NoSuchMethodException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( SecurityException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return summerLoadDeltaMethod;
  }
 
}
