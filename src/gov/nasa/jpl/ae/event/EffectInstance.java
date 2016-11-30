/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;

/**
 *
 */
public class EffectInstance extends HasIdImpl implements HasParameters {
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
  
  @Override
  public void deconstruct() {
    // nothing owned; can only set to null
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
    return HasParameters.Helper.isStale( effect, false, null, true );
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
    Set< Parameter< ? > > set = new HashSet< Parameter< ? > >();
    set.add( startTime );
    set.add( duration );
    if ( deep ) {
      set = Utils.addAll( set, HasParameters.Helper.getParameters( effect, deep, seen, true ) );
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
    if ( HasParameters.Helper.isFreeParameter( startTime, p, deep, null, true ) ||
         HasParameters.Helper.isFreeParameter( duration, p, deep, null, true ) ||
         HasParameters.Helper.isFreeParameter( effect, p, deep, null, true ) ) {
      return true;
    }
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
    
    if ( seen != null ) seen.remove( this ); // because getParameters checks seen set, too.
    return HasParameters.Helper.hasParameter( effect, parameter, deep, null, true );
  }
  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2,
                             boolean deep, Set< HasParameters > seen ) {
    return substitute( p1, (Object)p2, deep, seen );
  }
  @Override
  public boolean substitute( Parameter< ? > p1, Object p2,
                             boolean deep, Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    boolean subbed = false;
    if ( startTime == p1 ) {
      if ( p2 instanceof Timepoint ) {
        startTime = (Timepoint)p2;
        subbed = true;
      } else {
        Debug.error( true, true, "Could not substitute " + p2 + " for " + p1
                                 + " since it is not a Timepoint!" );
      }
    }
    if ( duration == p1 ) {
      if ( p2 instanceof Duration ) {
        duration = (Duration)p2;
        subbed = true;
      } else {
        Debug.error( true, true, "Could not substitute " + p2 + " for " + p1
                                 + " since it is not a Duration!" );
      }
    }
    if ( HasParameters.Helper.substitute( effect, p1, p2, deep, seen, true ) ) {
      subbed = true;
    }
    return subbed;
  }

  @Override
  public String toString() {
    Object[] array = new Object[]{ startTime, duration, effect };
    return MoreToString.Helper.toString( array );
  }

  @Override
  public String toShortString() {
    Object[] array = new Object[]{ startTime, duration, effect };
    return MoreToString.Helper.toShortString( array, null );
  }

  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen ) {
    return toString( withHash, deep, seen, null );
  }

  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen,
                          Map< String, Object > otherOptions ) {
    Object[] array = new Object[]{ startTime, duration, effect };
    return MoreToString.Helper.toString( array, withHash, deep, seen );
  }

}
