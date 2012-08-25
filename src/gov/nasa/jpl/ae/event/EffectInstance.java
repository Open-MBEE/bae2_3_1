/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;

/**
 * @author bclement
 *
 */
public class EffectInstance implements HasParameters {
  public Timepoint startTime;
  public Duration duration;
  public Effect effect;
 
  EffectInstance( Timepoint startTime, Duration duration, Effect effect ) {
    this.startTime = startTime;
    this.duration = duration;
    this.effect = effect;
  }
  
  EffectInstance( Timepoint startTime, Integer duration, Effect effect ) {
    this.startTime = startTime;
    this.duration = new Duration( duration, null );
    this.effect = effect;
  }
  
  public EffectInstance() {
    startTime = null;
    duration = null;
    effect = null;
  }
  
  public void applyTo( TimeVarying< ? > tv, boolean propagate ) {
    if ( effect != null ) {
      effect.applyTo( tv, propagate );//, startTime, duration );
    }
  }
  
  @Override
  public boolean isStale() {
    if ( startTime != null && startTime.isStale() ) return true;
    if ( duration != null && duration.isStale() ) return true;
    return HasParameters.Helper.isStale( effect, false );
  }
  @Override
  public void setStale( boolean staleness ) {
    Assert.assertTrue( "This method is not supported!", false );
  }
  @Override
  public Set< Parameter< ? > > getParameters( boolean deep ) {
    Set< Parameter< ? > > set = new TreeSet< Parameter< ? > >();
    set.add( startTime );
    set.add( duration );
    if ( deep ) {
      set.addAll( HasParameters.Helper.getParameters( effect, deep ) );
    }
    return set;
  }
  @Override
  public Set< Parameter< ? > > getFreeParameters( boolean deep ) {
    Assert.assertTrue( "This method is not supported!", false );
    return null;
  }
  @Override
  public void setFreeParameters( Set< Parameter< ? >> freeParams ) {
    Assert.assertTrue( "This method is not supported!", false );
  }
  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep ) {
    if ( HasParameters.Helper.isFreeParameter( startTime, p, deep ) ) return true;
    if ( HasParameters.Helper.isFreeParameter( duration, p, deep ) ) return true;
    if ( HasParameters.Helper.isFreeParameter( effect, p, deep ) ) return true;
    return false;
  }
  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep ) {
    if ( startTime == parameter ) return true;
    if ( duration == parameter ) return true;
    return HasParameters.Helper.hasParameter( effect, parameter, deep );
  }
  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2,
                             boolean deep ) {
    boolean subbed = false;
    if ( startTime == p1 ) {
      startTime = (Timepoint)p2;
      subbed = true;
    }
    if ( duration == p1 ) {
      duration = (Duration)p2;
      subbed = true;
    }
    if ( HasParameters.Helper.substitute( effect, p1, p2, deep ) ) {
      subbed = true;
    }
    return subbed;
  }

}
