/**
 * 
 */
package demandResponse;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import gov.nasa.jpl.ae.event.ConstraintExpression;
import gov.nasa.jpl.ae.event.DoubleParameter;
import gov.nasa.jpl.ae.event.DurativeEvent;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Functions;
import gov.nasa.jpl.ae.event.Functions.BooleanBinary;
import gov.nasa.jpl.ae.event.Functions.LessEquals;
import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.ParameterListenerImpl;
import gov.nasa.jpl.ae.solver.DoubleDomain;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Utils;

/**
 *
 */
public class OperatingLimits extends DurativeEvent {

  public static class Customer extends ParameterListenerImpl {
    public DoubleParameter load = null;
    public DoubleParameter delay = null;

    public Customer(String name) {
      super( name );
    }
    
    public Customer(String name,
                    Double minLoadValue, Double maxLoadValue,
                    Double minDelayValue, Double maxDelayValue) {
      this( name );
      initLoad(minLoadValue, maxLoadValue);
      initDelay(minDelayValue, maxDelayValue);
    }
    
    public void initDelay( Double minDelayValue, Double maxDelayValue ) {
      if ( delay != null ) parameters.remove( delay );
      DoubleDomain delayDomain = new DoubleDomain( minDelayValue, maxDelayValue );
      delay = new DoubleParameter( "delay", delayDomain, null, this );
      parameters.add( delay );
    }

    public void initLoad( Double minLoadValue, Double maxLoadValue ) {
      if ( load != null ) parameters.remove( load );
      DoubleDomain loadDomain = new DoubleDomain( minLoadValue, maxLoadValue );
      load = new DoubleParameter( "load", loadDomain, null, this );
      parameters.add( load );
    }

    public Double getMinLoad( boolean deep, int startTime, int endTime ) {
      if ( load.isGrounded( deep, null ) ) {
        return load.getValue();
      }
      DoubleDomain dd = (DoubleDomain)load.getDomain( deep, null );
      return dd.getLowerBound();
    }

    public Double getMaxLoad( boolean deep, int startTime, int endTime ) {
      if ( load.isGrounded( deep, null ) ) {
        return load.getValue();
      }
      DoubleDomain dd = (DoubleDomain)load.getDomain( deep, null );
      return dd.getUpperBound();
    }
  }


  public static boolean mustViolateLowerLimit = false;
  public static boolean mustViolateUpperLimit = false;
  public static boolean forAll = false;

  public Collection< Customer > customers =
      new ArrayList< OperatingLimits.Customer >();
  public Expression< Boolean > lowerStableLimit = null;
  public Expression< Boolean > upperUnstableLimit = null;
  Expression< Double > ramp = null;
  Expression< Double > rampDuration = null;

  /**
   * This is just for a single customer.
   * @param load
   */
  public OperatingLimits( DoubleParameter load, DoubleParameter duration  ) {
    super( "sendDisconnectSignals" );
    parameters.add( load );
    parameters.add( duration );
    init( load, duration );
  }

  public OperatingLimits( Customer c ) {
    super( "sendDisconnectSignals" );
    parameters.add( new Parameter<Customer>(c.getName(), null, c, this ) );
    DoubleParameter duration = new DoubleParameter( "dur", null, 0.0, this );//c.delay;
    init( c.load, duration );
  }

  private void init( DoubleParameter rampMagnitude, DoubleParameter rampDur  ) {
    ramp = new Expression< Double >( rampMagnitude );
    rampDuration = new Expression< Double >( rampDur );
    initLimits();
    addDependency( startTime, new Expression< Long >( 0 ), true );
    addDependency( duration, new Expression< Long >( 10 ), true );
  }

  private void initLimits() {
    initLimit( "lowerStableLimit", "lowerLimit", mustViolateLowerLimit );
    initLimit( "upperUnstableLimit", "upperLimit", mustViolateLowerLimit );
//    initLowerStableLimit( ramp, rampDuration );
//    initUpperUnstableLimit( ramp, rampDuration );
  }

  private void initLimit( //Expression< Double > ramp,
                          //Expression< Double > rampDuration,
                          String limitName,
                          String limitFuncName,
                          boolean negateLimit ) {
    Exception ex = null;
    try {
      Field limitField = this.getClass().getField( limitName );
      Expression< Boolean > limit = addLimit(ramp, rampDuration, limitFuncName, negateLimit );
      try {
        limitField.set( this, limit );
      } catch ( IllegalArgumentException e ) { ex = e; }
        catch ( IllegalAccessException e )  { ex = e; }
    } catch ( SecurityException e )  { ex = e; }
      catch ( NoSuchFieldException e )  { ex = e; }
    if ( ex != null ) {
      ex.printStackTrace();
    }

  }

  public Expression< Boolean > addLimit( Expression< Double > ramp,
                                         Expression< Double > rampDuration,
                                         String functionName, boolean negateLimit ) {
    Object[] args = new Object[] { rampDuration };
    Expression< Double > limit =
        new Expression< Double >( new FunctionCall( null, getClass(),
                                                    functionName, args, (Class<?>)null ) );
    BooleanBinary< Double > limitFunc = null;
//    Expression<Double> forAllRamps = new Functions.ForAll( ramp.expression, o )
    if ( this.forAll ) {
      limitFunc =
          ( negateLimit ? new Functions.Greater< Double >( ramp, limit )
                        : new Functions.LessEquals< Double >( ramp, limit ) );
    } else {
      limitFunc =
        ( negateLimit ? new Functions.Greater< Double >( ramp, limit )
                      : new Functions.LessEquals< Double >( ramp, limit ) );
    }
    Expression< Boolean > limitExpr = new Expression< Boolean >( limitFunc );

    constraintExpressions.add( new ConstraintExpression( limitExpr ) );
    return limitExpr;
  }

  public static Double lowerLimit( Double d ) {
    return 4.0 + d;
  }

  public static Double upperLimit( Integer d ) {
    return 10.0 + 4.0 * d;
  }
  
  /**
   * @param args
   */
  public static void main( String[] args ) {
    Debug.turnOff();
    
    // get command line args and replace defaults
    Double[] darr = new Double[] { 0.5, 3.0, 100.0, 120.0 };
    if ( args.length > 0 ) {
      for ( int i=0; i<darr.length; ++i ) {
        Double d = Utils.toDouble( args[i] );
        if ( d == null ) break;
        darr[i] = d;
      }
    }
    Double minLoadValue = darr[0];
    Double maxLoadValue = darr[1];
    Double minDelayValue = darr[2];
    Double maxDelayValue = darr[3];

    OperatingLimits.mustViolateLowerLimit = true;
    OperatingLimits.mustViolateUpperLimit = true;
//    OperatingLimits.mustViolateLowerLimit = false;
//    OperatingLimits.mustViolateUpperLimit = false;

    OperatingLimits.forAll  = true;

    OperatingLimits.Customer c = new Customer( "customer", 
                                               minLoadValue, maxLoadValue,
                                               minDelayValue, maxDelayValue );
    OperatingLimits ol = new OperatingLimits( c );
    
    ol.maxPassesAtConstraints = 200;
    ol.writeConstraintsOut = true;

    ol.executeAndSimulate();
    
//    try {
//      Thread.sleep( 600 );
//    } catch ( InterruptedException e ) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
    
    System.out.println("");
  }

}
