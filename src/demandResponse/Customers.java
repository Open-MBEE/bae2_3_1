/**
 * 
 */
package demandResponse;

import gov.nasa.jpl.ae.event.Consumable;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.Timepoint;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Vector;

/**
 * @author bclement
 *
 */
public class Customers extends Customer {

  public Vector<Customer> group = new Vector<Customer>();
  public int numberOfCustomers = 1;
  
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

  void init2() {
    //this.numGen = new Random(seed);
    for ( int i=0; i<numberOfCustomers; ++i ) {
      Customer customer =
          new Customer( offsetSecondsFromMidnight, loadHorizon, maxLoad,
                        minLoadFraction, varianceFactor, numGen.nextLong() );
      for ( Entry< Timepoint, Double > e : customer.additiveLoad.entrySet() ) {
        additiveLoad.add( e.getKey(), e.getValue() );
      }
      group.add( customer );
    }
    
    loadParameter =
        new Parameter< TimeVaryingMap< Double > >( "load", null, load, this );
    additiveLoadParameter =
        new Parameter< Consumable >( "load", null, additiveLoad, this );
    parameters.add( additiveLoadParameter );
  }

  
}


