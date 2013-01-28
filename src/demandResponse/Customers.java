/**
 * 
 */
package demandResponse;

import gov.nasa.jpl.ae.event.Consumable;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.TimeVaryingPlottableMap;
import gov.nasa.jpl.ae.event.Timepoint;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Vector;

/**
 *
 */
public class Customers extends Customer {

  // Since Customers is a Customer representing the group, and it's load values
  // are calculated on construction, the group of individual Customers is
  // private to try and ensure that the overall load can be updated with changes
  // to individuals.
  private Vector<Customer> group = new Vector<Customer>();

  public int numberOfCustomers = 1;
  
  // Maybe keep a separate map for the overall group's load.
  //TimeVaryingPlottableMap<Double> groupLoad = new TimeVaryingPlottableMap<Double>("totalLoad");
  
  /**
   * 
   */
  public Customers(int numberOfCustomers) {
    super();
  }

  /**
   * @param name
   */
  public Customers( String name, int numberOfCustomers ) {
    super( name );
    this.numberOfCustomers = numberOfCustomers;
    // TODO Auto-generated constructor stub
  }

  /**
   * @param offsetSecondsFromMidnight
   * @param loadHorizon
   * @param maxLoad
   * @param minLoadFraction
   * @param varianceFactor
   * @param seed
   */
  public Customers( int offsetSecondsFromMidnight, int loadHorizon,
                    double maxLoad, double minLoadFraction,
                    double varianceFactor, long seed, int numberOfCustomers ) {
    super( offsetSecondsFromMidnight, loadHorizon, maxLoad, minLoadFraction,
           varianceFactor, seed );
    this.numberOfCustomers = numberOfCustomers;
    init2();
  }

  
  
  
  /**
   * @param start
   * @param loadHorizon
   * @param maxLoad
   * @param minLoadFraction
   * @param varianceFactor
   * @param seed
   */
  public Customers( Date start, int loadHorizon, double maxLoad,
                    double minLoadFraction, double varianceFactor, long seed,
                    int numberOfCustomers ) {
    super( start, loadHorizon, maxLoad, minLoadFraction, varianceFactor, seed );
    this.numberOfCustomers = numberOfCustomers;
    init2();
  }

  /**
   * @param type
   */
  public Customers( CustomerType type, int numberOfCustomers) {
    super( type );
    this.numberOfCustomers = numberOfCustomers;
    init2();
  }

  /**
   * @param type
   */
  public Customers( CustomerType type, int numberOfCustomers, boolean projected) {
    super( type, projected );
    this.numberOfCustomers = numberOfCustomers;
    init2();
  }

  void init2() {
    //this.numGen = new Random(seed);
    TimeVaryingPlottableMap< Double > deltaMap =
        new TimeVaryingPlottableMap< Double >( "deltaMap", null, 0.0, Double.class,
            false );
    for ( int i=0; i<numberOfCustomers; ++i ) {
      Customer customer =
          new Customer( offsetSecondsFromMidnight, loadHorizon, maxLoad,
                        minLoadFraction, varianceFactor, numGen.nextLong() );
      Consumable.deltaMapPlusEquals( deltaMap,
                                     customer.additiveLoad.getDeltaMap() );
//      for ( Entry< Timepoint, Double > e : customer.additiveLoad.entrySet() ) {
//        Timepoint tp = e.getKey();
//        Integer t = tp.getValue();
//        Double cLoad = customer.additiveLoad.getValue(tp);
//        Double gLoad = groupLoad.getValue(t);
//        if ( gLoad == null ) gLoad = 0.0;
//        groupLoad.setValue( tp, gLoad + cLoad );
//      }
      group.add( customer );
    }
    
    this.additiveLoad.initializeFromDeltaMap( deltaMap );
    
    loadParameter =
        new Parameter< TimeVaryingPlottableMap< Double > >( "totalCustomerLoad", null, this.additiveLoad, this );
    additiveLoadParameter =
        new Parameter< Consumable >( "load", null, additiveLoad, this );
    parameters.add( additiveLoadParameter );
  }
  
}


