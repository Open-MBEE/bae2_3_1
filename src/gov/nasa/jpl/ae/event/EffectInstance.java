/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.HasConstraints;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;

/**
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
    return HasParameters.Helper.isStale( effect, false, null );
  }
  @Override
  public void setStale( boolean staleness ) {
    Assert.assertTrue( "This method is not supported!", false );
  }
  @Override
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< Parameter< ? > > set = new TreeSet< Parameter< ? > >();
    set.add( startTime );
    set.add( duration );
    if ( deep ) {
      set.addAll( HasParameters.Helper.getParameters( effect, deep, seen ) );
    }
    return set;
  }
  @Override
  public Set< Parameter< ? > > getFreeParameters( boolean deep,
                                                  Set< HasParameters > seen ) {
    Assert.assertTrue( "This method is not supported!", false );
    return null;
  }
  @Override
  public void setFreeParameters( Set< Parameter< ? >> freeParams,
                                 boolean deep,
                                 Set< HasParameters > seen ) {
    Assert.assertTrue( "This method is not supported!", false );
  }
  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep,
                                  Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    if ( HasParameters.Helper.isFreeParameter( startTime, p, deep, null ) ) return true;
    if ( HasParameters.Helper.isFreeParameter( duration, p, deep, null ) ) return true;
    if ( HasParameters.Helper.isFreeParameter( effect, p, deep, null ) ) return true;
    return false;
  }
  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                               Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    if ( startTime == parameter ) return true;
    if ( duration == parameter ) return true;
    return HasParameters.Helper.hasParameter( effect, parameter, deep, null );
  }
  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2,
                             boolean deep, Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    boolean subbed = false;
    if ( startTime == p1 ) {
      startTime = (Timepoint)p2;
      subbed = true;
    }
    if ( duration == p1 ) {
      duration = (Duration)p2;
      subbed = true;
    }
    if ( HasParameters.Helper.substitute( effect, p1, p2, deep, seen ) ) {
      subbed = true;
    }
    return subbed;
  }

}
