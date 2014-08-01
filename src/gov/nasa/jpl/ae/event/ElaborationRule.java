/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import junit.framework.Assert;

/**
 * @author bclement
 * 
 */
public class ElaborationRule extends HasIdImpl implements Comparable<ElaborationRule>, HasParameters { //, ParameterListener {
  protected Expression< Boolean > condition = null;
  protected Vector< EventInvocation > eventInvocations = null;
//  protected Vector< ConstraintInvocation > constraintsToAdd = null;
  private boolean tryToSatisfyOnElaboration = false;
  protected boolean satisfyDeepOnElaboration = false;

  public ElaborationRule() {}

  public ElaborationRule( Expression< Boolean > condition,
                          Vector< EventInvocation > eventInvocations ) {
    this.condition = condition;
    this.eventInvocations = eventInvocations;
  }

//  public ElaborationRule( Expression< Boolean > condition,
//                          Vector< EventInvocation > eventInvocations,
//                          Vector< ConstraintInvocation > constraintsToAdd ) {
//    this.condition = condition;
//    this.eventInvocations = eventInvocations;
//    this.constraintsToAdd = constraintsToAdd;
//  }

  @Override
  public void deconstruct() {
    if ( condition != null ) {
      condition.deconstruct();
      //condition = null;
    }
    if ( eventInvocations != null ) {
      for ( EventInvocation i : eventInvocations ) {
        if ( i != null ) {
          i.deconstruct();
        }
      }
      eventInvocations.clear();
      //eventInvocations = null;
    }
  }
  
  public boolean isConditionSatisfied() {
    Expression< Boolean > c = condition;
    boolean isNull = ( c == null );
    if ( isNull ) return true;
    Boolean r = c.evaluate( true );
    if (r == null) return false;
    if ( Debug.isOn() ) Debug.outln( "isConditionSatisfied() = " + r  + " for " + this.getEventInvocations() );
    return r;//( condition == null || condition.evaluate(true) );
  }
  
  public boolean attemptElaboration( Vector< Event > elaboratedEvents,
                                     boolean elaborateIfCan ) {
    return attemptElaboration( elaboratedEvents, elaborateIfCan,
                               tryToSatisfyOnElaboration,
                               satisfyDeepOnElaboration );
  }
  // Fix elaboration and return whether it is elaborated.
  public boolean attemptElaboration( Vector< Event > elaboratedEvents,
                                     boolean elaborateIfCan,
                                     boolean satisfyOnElaboration,
                                     boolean satisfyDeep ) {
    
    if ( Debug.isOn() ) Debug.outln( "attemptElaboration(): " + this );
    // Find out if the rule is satisfied and elaborated.
    boolean conditionSatisfied = isConditionSatisfied();
    boolean elaborated = !elaboratedEvents.isEmpty();
    
    // Deal with change in the elaboration.
    if ( elaborated && !conditionSatisfied ||
         eventInvocations == null || eventInvocations.isEmpty() ) {
      // Need to un-elaborate!
      // TODO -- REVIEW -- Does this leak memory?
      // TODO -- REVIEW -- Is this called by anyone keeping constraints and
      // parameters of the lost sub-events?  Do we need ElaborationListeners?
      for ( Event event : elaboratedEvents ) {
        event.deconstruct();
        //System.err.println("detatched " + event);
      }
      elaboratedEvents.clear();
    } else if ( !elaborated && conditionSatisfied && elaborateIfCan ) {
      // Need to elaborate!
      for ( EventInvocation ei : eventInvocations ) {
        Event event = ei.invoke();
        if ( event != null ) {
          elaboratedEvents.add( event );
          System.err.println( "elaborated "
                              + MoreToString.Helper.toString( event, true,
                                                              false, null ) );
          if ( satisfyOnElaboration ) {
            if ( event instanceof Satisfiable ) {
              ( (Satisfiable)event ).satisfy( satisfyDeep , null );
            }
          }
        }
      }
    }  // else no change
    
    return !elaboratedEvents.isEmpty();
  }

  // REVIEW -- dead code? -- remove?
  /*
  private Vector< Event > attemptElaboration() {
    if ( eventInvocations == null ) return null;

    Vector< Event > v = new Vector< Event >();
    // TODO -- Grounding shouldn't be necessary here, right?
//    if ( !condition.isGrounded() ) {
//      condition.ground();
//    }
    if ( condition == null || condition.evaluate() ) {
      for ( EventInvocation ei : eventInvocations ) {
        Event event = ei.invoke();
        if ( event != null ) {
          v.add( event );
        }
      }
    }
    return v;
  }
  */

  @Override
  public int compareTo( ElaborationRule o ) {
    return compareTo( o, true );
  }
  public int compareTo( ElaborationRule o, boolean checkId ) {
    if ( this == o ) return 0;
    if ( o == null ) return -1;
    if ( checkId ) return CompareUtils.compare( getId(), o.getId() );
    int compare = CompareUtils.compare( condition, o.condition, true );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compareCollections( eventInvocations,
                                               o.eventInvocations,
                                               true, checkId );
    if ( compare != 0 ) return compare;
//    compare = CompareUtils.compareCollections( constraintsToAdd,
//                                               o.constraintsToAdd,
//                                               true, checkId );
//    if ( compare != 0 ) return compare;
    return 0;
  }

  @Override
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< Parameter< ? > > s = new HashSet< Parameter< ? > >();
    if ( condition != null ) {
      s = Utils.addAll( s, condition.getParameters( deep, seen ) );
    }
    for ( EventInvocation inv : eventInvocations ) {
      s = Utils.addAll( s, inv.getParameters( deep, seen ) );
    }
    return s;
  }

  @Override
  public Set< Parameter< ? > > getFreeParameters( boolean deep,
                                                  Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
    Set< Parameter< ? > > s = new HashSet< Parameter< ? > >();
    if ( condition != null ) {
      s = Utils.addAll( s, condition.getFreeParameters( deep, seen ) );
    }
    for ( EventInvocation inv : eventInvocations ) {
      s = Utils.addAll( s, inv.getFreeParameters( deep, seen ) );
    }
    return s;
  }

  @Override
  public void setFreeParameters( Set< Parameter< ? > > freeParams,
                                 boolean deep,
                                 Set< HasParameters > seen ) {
    Assert.assertTrue( "This method is not supported!", false );
  }
  
  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep,
                             Set< HasParameters > seen) {
    return substitute( p1, (Object)p2, deep, seen );
  }
  @Override
  public boolean substitute( Parameter< ? > p1, Object p2, boolean deep,
                             Set< HasParameters > seen) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return false;
    seen = pair.second;
    //if ( Utils.seen( this, deep, seen ) ) return false;
    boolean subbed = false;
    if ( condition != null ) {
      if ( condition.substitute( p1, p2, deep, seen ) ) {
        subbed = true;
      }
    }
    for ( EventInvocation inv : eventInvocations ) {
      if ( inv.substitute( p1, p2, deep, seen ) ) {
        subbed = true;
      }
    }
    return subbed;
  }

  /**
   * @return the condition
   */
  public Expression< Boolean > getCondition() {
    return condition;
  }

  /**
   * @param condition the condition to set
   */
  public void setCondition( Expression< Boolean > condition ) {
    this.condition = condition;
  }

  /**
   * @return the eventInvocations
   */
  public Vector< EventInvocation > getEventInvocations() {
    return eventInvocations;
  }

  /**
   * @param eventInvocations the eventInvocations to set
   */
  public void setEventInvocations( Vector< EventInvocation > eventInvocations ) {
    this.eventInvocations = eventInvocations;
  }

//  /**
//   * @return the constraintsToAdd
//   */
//  public Vector< ConstraintInvocation > getConstraintsToAdd() {
//    return constraintsToAdd;
//  }
//
//  /**
//   * @param constraintsToAdd the constraintsToAdd to set
//   */
//  public void
//      setConstraintsToAdd( Vector< ConstraintInvocation > constraintsToAdd ) {
//    this.constraintsToAdd = constraintsToAdd;
//  }

  @Override
  public boolean isStale() {
    for ( Parameter< ? > p : getParameters( false, null ) ) {
      if ( p.isStale() ) return true;
    }
    return false;
  }

  @Override
  public void setStale( boolean staleness ) {
    // TODO -- REVIEW -- Need anything here?
    assert false;
  }

  @Override
  public boolean hasParameter( Parameter< ? > parameter, boolean deep,
                               Set<HasParameters> seen ) {
    return getParameters( deep, seen ).contains( parameter );
  }

  @Override
  public boolean isFreeParameter( Parameter< ? > p, boolean deep,
                                  Set<HasParameters> seen ) {
    // REVIEW -- Is this just done by Events? Maybe just return false or throw
    // assertion that this method id not supported for ElaborationRule.
    for ( EventInvocation inv : eventInvocations ) {
      if ( inv.isFreeParameter( p, deep, seen ) ) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toShortString() {
    return MoreToString.Helper.toShortString( getEventInvocations() ) + " if "
    + MoreToString.Helper.toShortString( getCondition() );
  }

  @Override
  public String toString() {
    return "ElaborationRule: " + this.getEventInvocations() + " if "
           + getCondition();
  }

  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen ) {
    return toString( withHash, deep, seen, null );
  }

  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen,
                          Map< String, Object > otherOptions ) {
    return "ElaborationRule: " + MoreToString.Helper.toString( getEventInvocations(), withHash, deep, seen, otherOptions, true ) + " if "
        + MoreToString.Helper.toString( getCondition(), withHash, deep, seen, otherOptions );
  }
  
}
