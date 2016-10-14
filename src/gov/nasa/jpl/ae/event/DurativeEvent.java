package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.CollectionTree;
import gov.nasa.jpl.ae.solver.Constraint;
import gov.nasa.jpl.ae.solver.ConstraintLoopSolver;
import gov.nasa.jpl.ae.solver.HasConstraints;
import gov.nasa.jpl.ae.solver.HasIdImpl;
import gov.nasa.jpl.ae.solver.Satisfiable;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.FileUtils;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.NameTranslator;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.TimeUtils;
import gov.nasa.jpl.mbee.util.Timer;
import gov.nasa.jpl.mbee.util.Utils;
import gov.nasa.jpl.mbee.util.TimeUtils.Units;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.Vector;

import junit.framework.Assert;



/**
 *
 */

public class DurativeEvent extends ParameterListenerImpl implements Event, Cloneable,
                           HasEvents, Groundable, Satisfiable,
                           //Comparable< Event >,
                           ParameterListener,
                           HasTimeVaryingObjects {

  // Static members

  public static boolean doPlot = false;

  protected static int counter = 0;

  // Other Members

  public static boolean writeConstraintsOut = false;

  public Timepoint startTime = new Timepoint( "startTime", this );
  public Duration duration = new Duration( this );
  public Timepoint endTime = new Timepoint( "endTime", this );
  // TODO -- REVIEW -- create TimeVariableParameter and EffectMap classes for
  // effects?
  //protected Set< Effect > effects = new HashSet< Effect >();
  protected List< Pair< Parameter< ? >, Set< Effect > > > effects =
      new ArrayList< Pair< Parameter< ? >, Set< Effect > > >();
  protected Map< ElaborationRule, Vector< Event > > elaborations =
      new HashMap< ElaborationRule, Vector< Event > >();

  protected Dependency startTimeDependency = null;

  protected Dependency endTimeDependency = null;

  protected Dependency durationDependency = null;

  // TODO -- consider breaking elaborations up into separate constraints
  protected AbstractParameterConstraint elaborationsConstraint =
      new AbstractParameterConstraint() {

    protected final int id = HasIdImpl.getNext();
    private boolean tryToSatisfyOnElaboration = false;
    protected boolean deepSatisfyOnElaboration = false;

    @Override
    public boolean satisfy(boolean deep, Set< Satisfiable > seen) {
      Pair< Boolean, Set< Satisfiable > > pair = Utils.seen( this, deep, seen );
      if ( pair.first ) return true;
      seen = pair.second;
      boolean satisfied = true;
      if ( !DurativeEvent.this.startTime.isGrounded(deep, null) ) return false;

      // Don't elaborate outside the horizon.  Need startTime grounded to know.
      Integer value = startTime.getValue(true);
      if ( !startTime.isGrounded(deep, null) ) return false;
      if ( value >= Timepoint.getHorizonDuration() ) {
        if ( Debug.isOn() ) Debug.outln( "satisfyElaborations(): No need to elaborate event outside the horizon: "
                     + getName() );
        return true;
      }
      List< Pair< ElaborationRule, Vector< Event >>> list =
          new ArrayList< Pair< ElaborationRule, Vector< Event >>>();
      for ( Entry< ElaborationRule, Vector< Event > > er : elaborations.entrySet() ) {
            list.add( new Pair< ElaborationRule, Vector< Event >>( er.getKey(),
                                                                   er.getValue() ) );
      }
      elaborations.clear();
      for ( Pair< ElaborationRule, Vector< Event > > p : list ) {
        ElaborationRule r = p.first;
        Vector< Event > events = p.second;
        if ( isElaborated( events ) != r.isConditionSatisfied() ) {
          if ( r.attemptElaboration( events, true,
                                     tryToSatisfyOnElaboration,
                                     deepSatisfyOnElaboration ) ) {
            if ( !r.isConditionSatisfied() ) satisfied = false;
          } else {
            if ( r.isConditionSatisfied() ) satisfied = false;
          }
        }
        elaborations.put( r, events );
      }
      return satisfied;
    }

    @Override
    public boolean isSatisfied(boolean deep, Set< Satisfiable > seen) {
      Pair< Boolean, Set< Satisfiable > > pair = Utils.seen( this, deep, seen );
      if ( pair.first ) return true;
      seen = pair.second;
      for ( Entry< ElaborationRule, Vector< Event > > er : elaborations.entrySet() ) {
        if ( !startTime.isGrounded(deep, null) ) return false;
        if ( startTime.getValueNoPropagate() < Timepoint.getHorizonDuration() ) {
          if ( isElaborated( er ) != er.getKey().isConditionSatisfied() ) {
            return false;
          }
        }
      }
      return true;
    }

    @Override
    public Set< Parameter< ? > > getParameters( boolean deep,
                                                Set<HasParameters> seen ) {
      Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
      if ( pair.first ) return Utils.getEmptySet();
      seen = pair.second;
      //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
      Set< Parameter< ? > > s = new HashSet< Parameter< ? > >();
      for ( Entry< ElaborationRule, Vector< Event > > er : elaborations.entrySet() ) {
        s = Utils.addAll( s, er.getKey().getCondition().getParameters( deep, seen ) );
      }
      return s;
    }

    @Override
    public String toShortString() {
      return getName() + ".elaborationsConstraint";
    }

    @Override
    public String toString() {
      // TODO -- should make this look evaluable, ex: condition -> eventExists
      return getName() + ".elaborationsConstraint";
    }

    @Override
    public void setFreeParameters( Set< Parameter< ? > > freeParams,
                                   boolean deep, Set< HasParameters > seen ) {
      Assert.assertFalse( "setFreeParameters() not supported!", true );
      //if ( Utils.seen( this, deep, seen ) ) return;
    }

    @Override
    public Integer getId() {
      return id;
    }
    @Override
    public int hashCode() {
      return id;
    }

    @Override
    public String toString( boolean withHash, boolean deep, Set< Object > seen ) {
      return toString();
    }

    @Override
    public String toString( boolean withHash, boolean deep, Set< Object > seen,
                            Map< String, Object > otherOptions ) {
      return toString();
    }

    @Override
    public void deconstruct() {
      // nothing to deconstruct
    }

    @Override
    public CollectionTree getConstraintCollection(boolean deep, Set< HasConstraints > seen) {
      // TODO Auto-generated method stub
      return null;
    }

  };  // end of elaborationsConstraint

  // TODO -- consider breaking effects into separate constraints
  protected AbstractParameterConstraint effectsConstraint =
      new AbstractParameterConstraint() {

    protected final int id = HasIdImpl.getNext();

    protected boolean
      areEffectsOnTimeVaryingSatisfied( Parameter< ? > variable,
                                        Set< Effect > effects,
                                        boolean deep,
                                        Set< Satisfiable > seen ) {
      boolean deepGroundable = deep;
      Set< Groundable > seenGroundable = null;
      if ( !variable.isGrounded(deepGroundable, seenGroundable) ) return false;
      if ( !variable.isSatisfied(deep, seen) ) return false;
      for ( Effect e : effects ) {
        if ( e == null ) {
          Debug.error( true, "Error! null effect in event " );
          continue;
        }
        if ( !checkIfEffectVariableMatches( variable, e ) ) return false;
        if ( !e.isApplied( variable )
             || !variable.isGrounded( deepGroundable, seenGroundable ) ) {
          return false;
        }
      }
      return true;
    }

    protected boolean
        satisfyEffectsOnTimeVarying( Parameter< ? > variable,
                                     Set< Effect > effects,
                                     boolean deep,
                                     Set< Satisfiable > seen ) {
      boolean deepGroundable = deep;
      Set< Groundable > seenGroundable = null;
      boolean satisfied = true;
      if ( !variable.isGrounded(deepGroundable, seenGroundable) ) {
        variable.ground(deepGroundable, seenGroundable);
      }
      if ( !variable.isSatisfied(deep, null) ) {
        variable.satisfy(deep, seen);
      }
      for ( Effect e : effects ) {
        if ( !checkIfEffectVariableMatches( variable, e ) ) return false;
        if ( !e.isApplied( variable ) ) {
          if ( !variable.isGrounded(deepGroundable, seenGroundable) ) {
            satisfied = false;
          } else {
        	  Object value = variable.getValue(true);
        	  if ( (!(value instanceof TimeVarying )) && value instanceof Parameter) {
        		 return satisfyEffectsOnTimeVarying((Parameter<?>) value, effects,
        		                                    deep, seen);
        	  } else if (value instanceof TimeVarying) {
        	    TimeVarying<?> tv = (TimeVarying<?>) value;
        	    if ( tv.canBeApplied( e ) ) {
        	      e.applyTo( tv, true );
        	    } else {
        	      satisfied = false;
        	    }
        	  }
          }
        }
      }
      return satisfied;
    }

    @Override
    public boolean satisfy(boolean deep, Set< Satisfiable > seen) {
      Pair< Boolean, Set< Satisfiable > > pair = Utils.seen( this, deep, seen );
      if ( pair.first ) return true;
      seen = pair.second;
      boolean satisfied = true;
      for ( Pair< Parameter< ? >, Set< Effect > > p : effects ) {
        Parameter< ? > variable = p.first;
        Set< Effect > set = p.second;
        if ( !satisfyEffectsOnTimeVarying( variable, set, deep, seen ) ) {
          satisfied = false;
        }
      }
      return satisfied;
    }

    @Override
    public boolean isSatisfied(boolean deep, Set< Satisfiable > seen) {
      Pair< Boolean, Set< Satisfiable > > pair = Utils.seen( this, deep, seen );
      if ( pair.first ) return true;
      seen = pair.second;
      for ( Pair< Parameter< ? >, Set< Effect > > p : effects ) {
        Parameter< ? > variable = p.first;
        Set< Effect > set = p.second;
        if ( !areEffectsOnTimeVaryingSatisfied( variable, set, deep, seen ) ) {
          return false;
        }
      }
      return true;
    }

    @Override
    public Set< Parameter< ? > > getParameters( boolean deep,
                                               Set<HasParameters> seen ) {
      Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
      if ( pair.first ) return Utils.getEmptySet();
      seen = pair.second;
      //if ( Utils.seen( this, deep, seen ) ) return Utils.getEmptySet();
      Set< Parameter< ? > > s = new HashSet< Parameter< ? > >();
      for ( Set< Effect > set : Pair.getSeconds( getEffects() ) ) {//.values() ) {
        for ( Effect e : set ) {
          if ( e instanceof HasParameters ) {
            s = Utils.addAll( s, ( (HasParameters)e ).getParameters( deep, seen ) );
          }
        }
      }
      return s;
    }

    @Override
    public String toShortString() {
      // TODO -- maybe make this look evaluable, ex: v.getValue(t) == fCall.arg0
      return getName() + ".effectsConstraint";
    }

    @Override
    public String toString() {
      // TODO -- maybe make this look evaluable, ex: v.getValue(t) == fCall.arg0
      return getName() + ".effectsConstraint";
    }

    @Override
    public void setFreeParameters( Set< Parameter< ? >> freeParams,
                                   boolean deep, Set< HasParameters > seen ) {
      Assert.assertFalse( "setFreeParameters() is not supported!", true );
    }

    @Override
    public Integer getId() {
      return id;
    }
    @Override
    public int hashCode() {
      return id;
    }

    @Override
    public String toString( boolean withHash, boolean deep, Set< Object > seen ) {
      return toString();
    }

    @Override
    public String toString( boolean withHash, boolean deep, Set< Object > seen,
                            Map< String, Object > otherOptions ) {
      return toString();
    }

    @Override
    public void deconstruct() {
      // nothing to deconstruct
    }

    @Override
    public CollectionTree getConstraintCollection(boolean deep, Set< HasConstraints > seen) {
      // TODO Auto-generated method stub
      return null;
    }

  };  // end of effectsConstraint



  // Constructors

  public DurativeEvent() {
    this((String)null);
  }
  public DurativeEvent( String name ) {
    setName( name );
    loadParameters();

    // create the dependency, endTime = startTime + duration
    // TODO -- REVIEW -- should this dependency just be a constraint instead?
    Functions.Sum< Integer, Integer > sum =
        new Functions.Sum< Integer, Integer >(
            new Expression< Integer >( startTime ),
            new Expression< Integer >( duration ) );
    endTimeDependency  = addDependency( endTime, sum, false );
    Functions.Sub< Integer, Integer > sub1 =
        new Functions.Sub< Integer, Integer >(
            new Expression< Integer >( endTime ),
            new Expression< Integer >( duration ) );
    startTimeDependency = addDependency( startTime, sub1, false );
    Functions.Sub< Integer, Integer > sub2 =
        new Functions.Sub< Integer, Integer >(
            new Expression< Integer >( endTime ),
            new Expression< Integer >( startTime ) );
    durationDependency = addDependency( duration, sub2, false );
  }

  public DurativeEvent(ParameterListenerImpl listener) {
    super(listener);
  }

  public DurativeEvent(String name, ParameterListenerImpl listener) {
    super(name, listener);
  }
  
  public DurativeEvent( String name, Date start, Long duration ) {
    this( name );
    if ( start != null ) {
      this.startTime.setValue( start );
    }
    if ( duration == null ) duration = new Long(1);
    this.duration.setValue( duration.intValue(), true );
  }
  
  public DurativeEvent( String name, String activitiesFileName ) {
    this(name);
    try {
      fromCsvFile( activitiesFileName );
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public DurativeEvent( DurativeEvent durativeEvent ) {
    this( null, durativeEvent );
  }
  public DurativeEvent( String name, DurativeEvent durativeEvent ) {
    setName( name );
    copyParameters( durativeEvent );
    loadParameters();

    // copy containers after clearing
    constraintExpressions.clear();
    effects.clear();
    dependencies.clear();
    for ( ConstraintExpression c : durativeEvent.constraintExpressions ) {
      ConstraintExpression nc = new ConstraintExpression( c );
      constraintExpressions.add( nc );
    }
//    for ( Map.Entry< Parameter< ? >, Set< Effect > > e :
//          durativeEvent.effects.entrySet() ) {
    for ( Pair< Parameter< ? >, Set< Effect > > p : durativeEvent.effects ) {
      Set< Effect > newSet = new HashSet< Effect >();
      try {
        for ( Effect eff : p.second ) {
          checkIfEffectVariableMatches( p.first, eff );
          Effect ne = eff.clone();
          newSet.add( ne );
        }
      } catch ( CloneNotSupportedException e1 ) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      effects.add( new Pair< Parameter< ? >, Set< Effect >>( (Parameter< ? >)p.first.clone(),
                                                             newSet ) );
    }
    for ( Dependency< ? > d : durativeEvent.dependencies ) {
      Dependency< ? > nd = new Dependency( d );
      dependencies.add( nd );
    }

    // We need to make sure the copied constraints are on this event's
    // parameters and not that of the copied constraints.
    List< Pair< Parameter< ? >, Parameter< ? > > > subList =
        buildSubstitutionList( durativeEvent );
    for ( Pair< Parameter< ? >, Parameter< ? > > p : subList ) {
      substitute( p.first, p.second, false, null );
    }
    // }
  }


  /**
   * Read a CSV file and create an event for each row that elaborates from this
   * event. Assume that a row has the following fields:
   * <ul>
   * <li>start time as a date (in supported formats for {@link TimeUtils.dateFromTimestamp()}) or integer offset
   * <li>duration as an integer offset (optional)
   * <li>end time as a date or integer offset (optional and only if no duration)
   * <li>name as a string (optional)
   * </ul>
   * Any lines that do not have at least a start time are ignored.
   * <p>
   * The first line is checked to see if it is a column header to specify which
   * field is which. The following regular expression patterns (ignoring letter
   * case) are matched to field names to disambiguate the fields:
   * <ol>
   * <li>name --&gt; name
   * <li>duration --&gt; duration
   * <li>end --&gt; end time
   * <li>start|time --&gt; start time
   * <li>activity  --&gt;  name
   * <li>'[^a-zA-Z]id[^a-zA-Z]' --&gt;  name
   * </ol>
   * 
   * @param fileName
   * @throws IOException 
   */
  public void fromCsvFile( String fileName ) throws IOException {
    if ( fileName == null ) return;
    ArrayList< ArrayList< String > > lines = FileUtils.fromCsvFile( fileName );
    
    if ( Utils.isNullOrEmpty( lines ) ) {
      // TODO -- error?
      return;
    }

    // A map to remember which field is which
    HashMap<String, Integer> fieldMap = new HashMap<String, Integer>();
    
    // TODO -- Get the header if there is one to help determine fields by name.
       
    // process lines
    boolean fieldMapInitialized = false;
    //HashMap<Object, Integer> fieldMapI = new HashMap<Object, Integer>();
    for ( ArrayList< String > fields : lines ) {
      Date start = null;
      Date end = null;
      String name = null;
      Double duration = null;
      String type = null;
      
      fieldMapInitialized = fieldMapInitialized || !fieldMap.isEmpty();
      
      TimeZone gmtZone = TimeZone.getTimeZone( "GMT" );
      
      //for ( String field : fields ) {
      for ( int i = 0; i < fields.size(); ++i ) {
        String field = fields.get( i );
        // Try to match start or end time
        Date d = TimeUtils.dateFromTimestamp( field, gmtZone );
        if ( d != null ) {
          if ( ( fieldMapInitialized && fieldMap.get( "start" ) == i ) || 
               ( !fieldMapInitialized && start == null ) ) {
            start = d;
            if ( !fieldMapInitialized ) {
              fieldMap.put("start", i);
            }
          } else if ( ( fieldMapInitialized && fieldMap.get( "end" ) == i ) || 
                      ( !fieldMapInitialized && end == null ) ) {
            end = d;
            if ( !fieldMapInitialized ) {
              fieldMap.put("end", i);
            }
          }
          continue;
        }
        
        // Try to match duration
        if ( ( fieldMapInitialized && fieldMap.get( "duration" ) == i ) || 
             ( !fieldMapInitialized && duration == null ) ) {
          duration = TimeUtils.toDurationInSeconds( field );
          if ( !fieldMapInitialized ) {
            fieldMap.put("duration", i);
          }
          if ( duration != null ) continue;
        }
        
        // Try to match name
        if ( ( fieldMapInitialized && fieldMap.get( "name" ) == i ) || 
            ( !fieldMapInitialized &&
              ( name == null || ( !name.matches( ".*[A-Za-z].*" ) &&
                                  field.matches( ".*[A-Za-z].*" ) ) ) ) ) {
          if ( !fieldMapInitialized ) {
            fieldMap.put("name", i);
          }
          name = field;
          if ( name != null ) continue;
        }
        
        // Try to match type
        if ( ( fieldMapInitialized && fieldMap.get( "type" ) == i ) || 
            ( !fieldMapInitialized &&
              ( type == null || ( !type.matches( ".*[A-Za-z].*" ) &&
                                  field.matches( ".*[A-Za-z].*" ) ) ) &&
              ClassUtils.getClassForName( field, (String)null, (String)null, false ) != null ) ) {
          if ( !fieldMapInitialized ) {
            fieldMap.put("type", i);
          }
          type = field;
        }
        
        // Make sure start is before end.
        if ( !fieldMapInitialized && end != null && end.before( start ) ) {
          // swap start and end values
          Date tmp = start;
          start = end;
          end = tmp;
          // swap indices in field map
          int tmpi = fieldMap.get("start");
          fieldMap.put( "start", fieldMap.get( "end" ) );
          fieldMap.put( "end", tmpi );
        }

        // Add elaboration
        if ( start != null && (end == null || end.after( Timepoint.epoch ) ) &&
             start.before( Timepoint.getHorizon() )) {
          addElaborationRule( start, end, duration, type );
        }
        
      } // end for field in fields
    } // for line in lines
    
    if ( Debug.isOn() ) Debug.outln( "read map from file, " + fileName + ":\n"
                                     + this.toString() );
  }
  
  public boolean addElaborationRule(Date start, Date end, Double duration, String typeName ) {
    if ( start == null ) return false;
    // Compute duration if not given.
    if ( duration == null && end != null ) {
      duration = (end.getTime() - start.getTime()) / 1000.0;
    }
    Expression<String> nameExpr = new Expression<String>(name);
    Expression<Integer> startExpr = 
        new Expression<Integer>( new Timepoint( start ), Integer.class );
    //Duration dd = new Duration( name, durVal, durUnits, o );
    Expression<Integer> durationExpr =
        new Expression<Integer>( (new Double( duration == null ? 1 :
                                   duration / Timepoint.conversionFactor( Units.seconds ) ) ).intValue(),
                                 Integer.class );
    Class<?> cls = 
        typeName == null ? DurativeEvent.class :
        ClassUtils.getClassForName( typeName, (String)null, (String)null, false );
    Class<? extends Event> eventClass = (Class<? extends Event>)cls;

    if ( !Event.class.isAssignableFrom( eventClass ) ) {
      eventClass = DurativeEvent.class;
    }
    this.addElaborationRule( new Expression< Boolean >(true), null,
                             DurativeEvent.class, "",
                             new Expression<?>[] { nameExpr, startExpr,
                                                   durationExpr } );
    return true;
  }
  
  public static boolean checkIfEffectVariableMatches( Parameter<?> variable,
                                                      Effect e ) {
    if ( e instanceof EffectFunction ) {
      EffectFunction ef = (EffectFunction)e;
      if ( ef.getObject() != null
           && !Expression.valuesEqual( ef.getObject(), variable ) ) {
        Debug.error( true, "Error! Variable (" + variable
                           + ") and effect variable (" + ef.getObject()
                           + ") do not match! " + ef );
        return false;
      }
    }
    return true;
  }

  public void fixTimeDependencies() {
  }
  public void fixTimeDependencies1() {
    boolean gotStart = false, gotEnd = false, gotDur = false;
    boolean stillHaveStart = false, stillHaveEnd = false, stillHaveDur = false;
    int numGot = 0;
    int numHave = 0;
    // see what time dependencies we have
    for ( Dependency< ? > d : getDependencies() ) {
      if ( d.parameter == startTime ) {
        if ( d == startTimeDependency ) {
          if ( !stillHaveStart ) {
            ++numHave;
            stillHaveStart = true;
          }
        } else {
          if ( !gotStart ) {
            gotStart = true;
            ++numGot;
          }
        }
      } else if ( d.parameter == endTime ) {
        if ( d == endTimeDependency ) {
          if ( !stillHaveEnd ) {
            stillHaveEnd = true;
            ++numHave;
          }
        } else {
          if ( !gotEnd ) {
            gotEnd = true;
            ++numGot;
          }
        }
      } else if ( d.parameter == duration ) {
        if ( d == durationDependency ) {
          if ( !stillHaveDur ) {
            stillHaveDur = true;
            ++numHave;
          }
        } else {
          if ( !gotDur ) {
            gotDur = true;
            ++numGot;
          }
        }
      }
    }
    // get rid of already replaced dependencies
    if ( gotStart && stillHaveStart ) {
      getDependencies().remove( startTimeDependency );
      stillHaveStart = false;
      --numHave;
    }
    if ( gotEnd && stillHaveEnd ) {
      getDependencies().remove( endTimeDependency );
      stillHaveEnd = false;
      --numHave;
    }
    if ( gotDur && stillHaveDur ) {
      getDependencies().remove( durationDependency );
      stillHaveDur = false;
      --numHave;
    }
    // only want one dependency among the three
    if ( numHave > 1 ) {
      if ( stillHaveEnd ) {
        if ( stillHaveStart ) {
          getDependencies().remove( startTimeDependency );
        }
        if ( stillHaveDur ) {
          getDependencies().remove( durationDependency );
        }
      } else if ( stillHaveDur ) {
        if ( stillHaveStart ) {
          getDependencies().remove( startTimeDependency );
        }
      } else {
        assert false;
      }
    }
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#substitute(gov.nasa.jpl.ae.event.Parameter, gov.nasa.jpl.ae.event.Parameter, boolean)
   */
  @Override
  public boolean substitute( Parameter< ? > p1, Parameter< ? > p2, boolean deep,
                             Set<HasParameters> seen ) {
    // Parent class adds 'this' to seen, so just checking here.
    if ( !Utils.isNullOrEmpty( seen ) && seen.contains( this ) ) return false;
    // call parent class's method
    boolean subbed = super.substitute( p1, p2, deep, seen );
    boolean s = HasParameters.Helper.substitute( effects, p1, p2, deep, null );
    subbed = subbed || s;
//    for ( Entry< Parameter< ?>, Set< Effect >> e : effects.entrySet() ) {
//      if ( e.getValue() instanceof HasParameters ) {
//        boolean s = ( (HasParameters)e.getValue() ).substitute( p1, p2, deep, seen );
//        subbed = subbed || s;
//      }
//    }
    return subbed;
  }

  // Methods

  // Subclasses should override this to add their own parameters.
  protected void loadParameters() {
    parameters.clear();
    parameters.add( startTime );
    parameters.add( duration );
    parameters.add( endTime );
  }

  // Subclasses should override this to add their own parameters.
  // TODO -- Can this just iterate through parameters container to avoid
  // overriding in subclasses?
  protected void copyParameters( DurativeEvent event ) {
    // Inefficiently reallocating these parameters--maybe create
    // Parameter.copy(Parameter)
    startTime = new Timepoint( event.startTime );
    duration = new Duration( event.duration );
    endTime = new Timepoint( event.endTime );
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#clone()
   */
  @Override
  public Object clone() {
    return new DurativeEvent( this );
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return toString( Debug.isOn(), false, null );
  }
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#toString(boolean, boolean, java.util.Set, java.util.Map)
   */
  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen,
                          Map< String, Object > otherOptions ) {
    Pair< Boolean, Set< Object > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) deep = false;
    seen = pair.second;
    StringBuffer sb = new StringBuffer();
    sb.append( getClass().getName() + "::");
    sb.append( getName() );
    if ( withHash ) sb.append( "@" + hashCode() );
    sb.append( "(" );
    Parameter<?> firstParams[] = { startTime, duration, endTime };  // Could use Arrays.sort() .search()
    List< Parameter< ? > > allParams =
        new ArrayList< Parameter< ? > >(Arrays.asList( firstParams ));
    Set< Parameter< ? > > restParams = getParameters( false, null );
    restParams.removeAll( allParams );
    allParams.addAll( restParams );
    boolean first = true;
    for ( Object p : allParams ) {
      if ( first ) first = false;
      else sb.append( ", " );
      if ( p instanceof Parameter ) {
        sb.append( ((Parameter<?>)p).toString( false, withHash, deep, seen, otherOptions ) );
      } else {
        sb.append( p.toString() );
      }
    }
    sb.append( ")" );
    return sb.toString();
  }

  public void executeAndSimulate() {
    executeAndSimulate( 1.0e12 );
  }

  public void executeAndSimulate( double timeScale ) {
    Timer timer = new Timer();
    amTopEventToSimulate = true;
    execute();
    System.out.println("execution:\n" + executionToString());
    simulate(timeScale);
    amTopEventToSimulate = false;
    System.out.println("Finished executing and simulating:");
    System.out.println( timer.toString() );
  }

  /* (non-Javadoc)
   * @see event.Event#execute()
   */
  @Override
  public void execute() {
    // REVIEW -- differentiate between execute for simulation and execute in
    // external environment?
    if ( Debug.isOn() ) Debug.outln( getName() + ".execute()" );
    Timer timer = new Timer();
    System.out.println( getName() + ".execute(): starting stop watch" );
    boolean satisfied = satisfy( true, null );
    if ( Debug.isOn() ) Debug.outln( getName() + ".execute() called satisfy() --> " + satisfied );
//    if ( !satisfied ) {
//      satisfied = solver.solve( getConstraints( true, null ) );
//      if ( Debug.isOn() ) Debug.outln( getName() + ".execute() called solve() --> " + satisfied );
//    }
    timer.stop();
    System.out.println( "\n" + getName() + ".execute(): Time to elaborate and resolve constraints:" );
    System.out.println( timer );
    timer.start();
    Collection<Constraint> constraints = getConstraints( true, null );
    if ( writeConstraintsOut ) {
      System.out.println("All " + constraints.size() + " constraints: ");
      for (Constraint c : constraints) {
      	System.out.println("Constraint: " + c);
      }
    }
    if ( satisfied ) {
      System.out.println( "\nAll constraints were satisfied!" );
    } else {
      Collection< Constraint > unsatisfiedConstraints =
          ConstraintLoopSolver.getUnsatisfiedConstraints( constraints );
      if ( unsatisfiedConstraints.isEmpty() ) {
        System.out.println( (constraints.size() - unsatisfiedConstraints.size())
                            + " out of " + constraints.size()
                            + " constraints were satisfied!" );
        System.err.println( getName() + "'s constraints were not satisfied!" );
      } else {
        System.err.println( "Could not resolve the following "
                            + unsatisfiedConstraints.size()
                            + " constraints for " + getName() + ":" );
        for ( Constraint c : unsatisfiedConstraints ) {
          System.err.println( c.toString() );
          c.isSatisfied( true, null ); // REVIEW -- can look shallow since constraints
                                       // were gathered deep?!
        }
      }
    }

    System.out.println( "\n" + getName() + ".execute(): Time to gather and write out constraints:" );
    System.out.println( timer );

    // HACK -- Sleeping to separate system.err from system.out.
    try {
      Thread.sleep( 100 );
    } catch ( InterruptedException e1 ) {
      e1.printStackTrace();
    }

  }

  public EventSimulation createEventSimulation() {
    Set< Event > events = getEvents( true, null );
    System.out.println("Simulating " + events.size() + " events.");
    EventSimulation sim = new EventSimulation( events, 1.0e12 );
    sim.topEvent = this;
    sim.add( this );
    settingTimeVaryingMapOwners = true;

    Map<String, Object> paramsAndTvms = getTimeVaryingObjectMap( true, null );

    settingTimeVaryingMapOwners = false;
    System.out.println("Simulating " + paramsAndTvms.size() + " state variables.");
    for ( Map.Entry<String, Object> entry : paramsAndTvms.entrySet() ) {
      Object tvo = entry.getValue();//e.getKey();
      Parameter<?> p = null;
      TimeVarying<?> tv = null;
      String name = entry.getKey();
      if ( tvo instanceof Parameter ) {
        p = (Parameter<?>)tvo;
        //name = p.getName();
        if ( p.getValueNoPropagate() instanceof TimeVarying ) {
          tv = (TimeVarying<?>)p.getValueNoPropagate();
        }
      } else if ( tvo instanceof TimeVarying ) {
        tv = (TimeVarying<?>)tvo;
      }
      if ( tv instanceof TimeVaryingMap ) {
        String category = "";
        if ( !Utils.isNullOrEmpty( name ) ) {
          category = name;
        } else {
          if ( tv instanceof TimeVaryingPlottableMap ){
            category = ((TimeVaryingPlottableMap<?>)tv).getName();
  //          category = ((TimeVaryingPlottableMap<?>)tv).category.getValue();
          }
        }
        if ( Utils.isNullOrEmpty( category ) && tv.getOwner() instanceof ParameterListener ) {
           category = ( (ParameterListener)tv.getOwner() ).getName();
        }
//        Debug.turnOn();
        sim.add( (TimeVaryingMap< ? >)tv, category );
      }
    }
    return sim;
  }

  public void simulate( double timeScale ) {
    simulate( timeScale, System.out );
  }

  public void simulate( double timeScale, java.io.OutputStream os ) {
    simulate( timeScale, os, doPlot );
  }

  public void simulate( double timeScale, java.io.OutputStream os, boolean runPlotter ) {
    if ( Debug.isOn() ) {
      Debug.outln( "\nsimulate( timeScale=" + timeScale + ", runPlotter="
                   + runPlotter + " ): starting stop watch\n" );
    }
    Timer timer = new Timer();
    try {
      EventSimulation sim = createEventSimulation();
      sim.tryToPlot = runPlotter;
      System.out.println( sim.numEvents() + " event/state transitions.");
      sim.simulate( timeScale, os );
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    if ( Debug.isOn() ) {
      Debug.outln( "\nsimulate( timeScale=" + timeScale + ", runPlotter="
                   + runPlotter + " ): completed\n" + timer + "\n" );
    }
  }

  @Override
  public void doSnapshotSimulation( boolean improved ) {
    boolean didntWriteFile = true;
    // augment file name
    String fileName = this.baseSnapshotFileName;
    if ( fileName != null ) {
      int pos = fileName.lastIndexOf( '.' );
      String pkg = getClass().getPackage().getName();
      if ( pkg != null ) {
        if ( pos == -1 ) pos = fileName.length();
        fileName =
            fileName.substring( 0, pos ) + "." + pkg + fileName.substring( pos );
      }
      String bestFileName = "best_" + fileName;
      if ( !snapshotToSameFile ) {
        fileName = Utils.addTimestampToFilename( fileName );
      }
      File file = new File( fileName );

      // write out simulation to file
      System.out.println( "writing simulation snapshot to "
                          + file.getAbsolutePath() );
      try {
        if ( Debug.isOn() ) Debug.outln( "  which is the same as " + file.getCanonicalPath() );
      } catch ( IOException e1 ) {
        // ignore
      }
      System.out.println( TimeUtils.gmtCal.getTime().toString() );
      if ( writeSimulation( file ) ) {
        String fn = FileUtils.removeFileExtension( fileName );
        writeAspen( fn + ".mdl", fn + ".ini" );
        didntWriteFile = false;
      }
      if ( improved ) {
        writeSimulation( bestFileName );
      }
    }
    if ( didntWriteFile ) {
      writeSimulation( System.out );
    }
  }

  public boolean writeSimulation( String fileName ) {
    File file = new File(fileName);
    return writeSimulation( file );
  }
  public boolean writeSimulation( File file ) {
    boolean succ = false;
    FileOutputStream os = null;
    try {
      os = new FileOutputStream( file );
      succ = writeSimulation( os );
    } catch ( FileNotFoundException e ) {
      System.err.println( "Writing simulation output to file "
                          + file.getAbsolutePath() + " failed" );
      e.printStackTrace();
    }
    try {
      os.close();
    } catch ( IOException e ) {
      System.err.println( "Trouble closing output file "
                          + file.getAbsolutePath() );
      e.printStackTrace();
    }
    return succ;
  }
  public boolean writeSimulation( OutputStream os ) {
    PrintWriter w = new PrintWriter( os, true );
    w.println( "remaining "
               + solver.getUnsatisfiedConstraints().size()
               + " unsatisfied constraints: "
               + Utils.join( solver.getUnsatisfiedConstraints(),
                             "\nConstraint: " ) );
    w.println("execution:\n" + executionToString() + "\n");
    simulate( 1e15, os, false );
    return true;
  }
  // Create an ElaborationRule for constructing an eventClass with
  // constructorParamTypes.
  // This method assumes that there is a constructor whose parameters match the
  // types of the arguments.
  public < T extends Event > ElaborationRule
      addElaborationRule( Expression< Boolean > condition,
                          Parameter< ? > enclosingInstance,
                          Class< T > eventClass,
                          String eventName,
                          Expression<?>[] arguments ) {

    // Now create the EventInvocation from the constructor and arguments.
    Vector<EventInvocation> invocation = new Vector<EventInvocation>();
    EventInvocation newInvocation = null;
    newInvocation =
        new EventInvocation( eventClass, eventName,
                             enclosingInstance, arguments,
                             (Map< String, Object >)null );
    invocation.add(newInvocation);
    Vector<Event> eventVector = new Vector<Event>();
    ElaborationRule elaborationRule = new ElaborationRule(condition, invocation);
    elaborations.put(elaborationRule, eventVector);
    return elaborationRule;
  }

  public < T extends Event > ElaborationRule
  addElaborationRule( Expression< Boolean > condition,
                      Object enclosingInstance,
                      Class< T > eventClass,
                      String eventName,
                      Expression<?>[] arguments ) {
    return addElaborationRule( condition,
                               new Parameter< Object >( "", null,
                                                        enclosingInstance, this ),
                               eventClass, eventName, arguments );
  }

  /* (non-Javadoc)
   * @see event.Event#addEffect(event.TimeVarying, java.lang.reflect.Method, java.util.Vector)
   */
  @Override
  public void addEffect( Parameter< ? > sv,
                         Object obj, Method effectFunction,
                         Vector< Object > arguments ) {
    assert sv != null;
    Effect e = new EffectFunction( obj, effectFunction, arguments, (Class<?>)null );  // TODO?  last arg?
    addEffect( sv, e );
  }

  public void addEffect( Parameter< ? > sv, Effect e ) {
    checkIfEffectVariableMatches( sv, e );
    Set< Effect > effectSet = null;
    //Pair< Parameter< ? >, Set< Effect >> p = null;
    for ( Pair< Parameter< ? >, Set< Effect > > pp : effects ) {
      if ( Utils.valuesEqual( pp.first.getValue(true), sv.getValue(true) ) ) {
        //p = pp;
        effectSet = pp.second;
        break;
      }
    }
//    if ( p != null ) {
////    if ( effects.containsKey( sv ) ) {
//      effectSet = p.second; //effects.get( sv );
//    }
    if ( effectSet == null ) {
      effectSet = new HashSet< Effect >();
      effects.add( new Pair< Parameter< ? >, Set< Effect > >( sv, effectSet ) );
    }
    if ( Debug.isOn() ) Debug.outln(getName() + "'s effect (" + e + ") in being added to set (" + effectSet + ") for variable (" + sv + ").");
    effectSet.add( e );
  }

  public void addEffects( Parameter< ? > sv, Set<Effect> set ) {
    if ( set == null || sv == null ){//||  sv.getValue( false ) == null ) {
      Debug.error( false, "Error! null arguments to " + name + ".addEffects(" + sv + ", " + set + ")" );
      return;
    }
    Set< Effect > effectSet = null;
    for ( Pair< Parameter< ? >, Set< Effect > > pp : effects ) {
      if ( pp.first.getValue(true) != null && Utils.valuesEqual( pp.first.getValue(true), sv.getValue(true) ) ) {
        if ( Debug.isOn() ) Debug.outln( getName() + "'s addEffect() says " + pp.first.getValue(true) + " == " + sv.getValue(true) );
        effectSet = pp.second;
        break;
      }
    }
    if ( effectSet == null ) {
      effectSet = new HashSet< Effect >();//set;
      effects.add( new Pair< Parameter< ? >, Set< Effect > >( sv, effectSet ) );
    }
    if ( set != null ) {
      effectSet.addAll( set );
    }
    if ( Debug.isOn() ) {
      for ( Pair< Parameter< ? >, Set< Effect > > pp : effects ) {
        if ( Utils.valuesEqual( pp.first.getValue(false), sv.getValue(false) ) ) {
          effectSet = pp.second;
          for ( Effect effect : effectSet ) {
            if ( effect instanceof EffectFunction ) {
              EffectFunction ef = (EffectFunction)effect;
              if ( ef.object != null && !pp.first.equals( ef.object ) ) {
                Debug.error( true, "Error! effect variable ("
                                    + pp.first
                                    + ") does not match that of EffectFunction! ("
                                    + ef + ")" );
              }
            }
          }
        }
      }
    }
  }

  /* (non-Javadoc)
   * @see event.Event#addEffect(event.TimeVarying, java.lang.Object, java.lang.reflect.Method, java.lang.Object)
   */
  @Override
  public void addEffect( Parameter< ? > sv,
                         Object obj, Method method, Object arg ) {
    Vector< Object > v = new Vector< Object >();
    v.add( arg );
    addEffect( sv, obj, method, v );
  }

  // These effects are dynamic, so we don't want to add them to the static set.
//  public void addEffects( Dependency<?> dependency ) {
//    for ( Effect e : getEffectsFromDependency( dependency ) ) {
//      addEffect()
//    }
//  }

  // Gather effect functions from a Dependency.
  public static Collection<Effect> getEffectsFromDependency( Dependency<?> d ) {
    if ( d == null || d.getExpression() == null ) return Utils.getEmptyList();
    List<Effect> effects = new ArrayList<Effect>();
    List<FunctionCall> calls = d.getExpression().getFunctionCalls();
    Affectable affectable = null;
    for ( FunctionCall call : calls ) {
      try {
        affectable = Expression.evaluate( call.getObject(), Affectable.class, false, false );
      }
      catch (ClassCastException e) {
        //ignore
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
      if ( affectable != null ) {
        if ( affectable.doesAffect( call.getMethod() ) ) {
          Effect effect = new EffectFunction( call );
          effects.add( effect );
        }
      }
    }
    return effects;
  }



  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#getTimeVaryingObjects(boolean, java.util.Set)
   */
  @Override
  public Set< TimeVarying< ? > > getTimeVaryingObjects( boolean deep,
                                                        Set<HasTimeVaryingObjects> seen ) {
    return getTimeVaryingObjects( deep, true, seen );
  }
  public Set< TimeVarying< ? > > getTimeVaryingObjects( boolean deep,
                                                        boolean includeDependencies,
                                                          Set<HasTimeVaryingObjects> seen ) {
    Pair< Boolean, Set< HasTimeVaryingObjects > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    if ( seen != null ) seen.remove( this );
    Set< TimeVarying< ? > > set = super.getTimeVaryingObjects( deep, includeDependencies, seen );
    //Set< TimeVarying< ? > > set = new TreeSet< TimeVarying< ? > >();
    set = Utils.addAll( set, HasTimeVaryingObjects.Helper.getTimeVaryingObjects( effects, deep, seen ) );
    if ( deep ) {
      set = Utils.addAll( set, HasTimeVaryingObjects.Helper.getTimeVaryingObjects( elaborationsConstraint, deep, seen ) );
      set = Utils.addAll( set, HasTimeVaryingObjects.Helper.getTimeVaryingObjects( effectsConstraint, deep, seen ) );
      set = Utils.addAll( set, HasTimeVaryingObjects.Helper.getTimeVaryingObjects( elaborations, deep, seen ) );
      for ( Event e : getEvents(false, null) ) {
        if ( e instanceof HasTimeVaryingObjects ) {
          set = Utils.addAll( set, ((HasTimeVaryingObjects)e).getTimeVaryingObjects( deep, seen ) );
        }
      }
    }
    return set;
  }

  @Override
  public Collection<ParameterListenerImpl> getNonEventObjects( boolean deep,
                                                               Set< ParameterListenerImpl > seen ) {
    Pair< Boolean, Set< ParameterListenerImpl > > pair =
        Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    if ( seen != null ) seen.remove( this );
    Collection< ParameterListenerImpl > set = super.getNonEventObjects( deep, seen );
    for ( Pair< Parameter< ? >, Set< Effect > > e : getEffects() ) {
      set.addAll( getNonEventObjects(e, deep, seen ) );
    }
    if ( deep ) {
      set.addAll( getNonEventObjects(elaborationsConstraint, deep, seen ) );
      set.addAll( getNonEventObjects(effectsConstraint, deep, seen ) );
      for ( Entry< ElaborationRule, Vector< Event > > e : getElaborations().entrySet() ) {
        set.addAll( getNonEventObjects(e, deep, seen ) );
      }
      for ( Event e : getEvents(false, null) ) {
        set.addAll( getNonEventObjects(e, deep, seen ) );
      }
    }
    return set;
  }

  /*
   * Gather any parameter instances contained by this event.
   * (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#getParameters(boolean, java.util.Set)
   */
  @Override
  public Set< Parameter< ? > > getParameters( boolean deep,
                                              Set<HasParameters> seen ) {
    Pair< Boolean, Set< HasParameters > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    if ( seen != null ) seen.remove( this );
    Set< Parameter< ? > > set = super.getParameters( deep, seen );
    if ( deep ) {
      set = Utils.addAll( set, HasParameters.Helper.getParameters( elaborationsConstraint, deep, seen, true ) );
      set = Utils.addAll( set, HasParameters.Helper.getParameters( effectsConstraint, deep, seen, true ) );
      set = Utils.addAll( set, HasParameters.Helper.getParameters( elaborations, deep, seen, true ) );
      set = Utils.addAll( set, HasParameters.Helper.getParameters( effects, deep, seen, true ) );
      for ( Event e : getEvents(deep, null) ) {
        if ( e instanceof HasParameters ) {
          set = Utils.addAll( set, ((HasParameters)e).getParameters( deep, seen ) );
        }
      }
    }
    return set;
  }

  public static boolean newMode = false;
  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#tryToSatisfy(boolean, java.util.Set)
   */
  @Override
  protected boolean tryToSatisfy( boolean deep, Set< Satisfiable > seen ) {
//    if ( //amTopEventToSimulate &&
//          mode % 2 == 1 ) {
//      return tryToSatisfy( deep, seen, false );
//    }
    return tryToSatisfy( deep, seen, newMode );
  }
  protected boolean tryToSatisfy( boolean deep, Set< Satisfiable > seen,
                                  boolean newTrySat ) {
    boolean satisfied = true;
    if ( newTrySat ) {
      satisfied = super.tryToSatisfy2( deep, seen );
    } else {
      satisfied = super.tryToSatisfy( deep, seen );
    }
    if ( satisfied ) {
      // already handled through getConstraints()
//      if ( !effectsConstraint.satisfy( deep, seen ) ) {
//        satisfied = false;
//      }
      if ( !Satisfiable.Helper.satisfy( effects, deep, seen ) ) {
        satisfied = false;
      }
      // already handled through getConstraints()
  //    if ( !elaborationsConstraint.satisfy( false, seen ) ) {
  //      satisfied = false;
  //    }
      // REVIEW -- Is this necessary if elaborationsConstraint is called? Well,
      // they are different in that satisfyElaborations tries to satisfy the
      // events after they are elaborated. Maybe the constraint should do this
      // so that we can get rid of satisfyElaborations(), or maybe
      // elaborationsConstraint.satisfy() should simply call
      // satisfyElaborations.
      if ( !satisfyElaborations( deep, seen ) ) {
        satisfied = false;
      }
    }
    if ( Debug.isOn() ) Debug.outln( this.getClass().getName()
                                     + " satisfy loop called satisfyElaborations() " );
    return satisfied;
  }

  @Override
  public long getNumberOfResolvedConstraints( boolean deep,
                                              Set< HasConstraints > seen ) {
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) {
      return 0;
    }
    seen = pair.second;
    if ( seen != null ) seen.remove( this );
    long num = 0;
    num += super.getNumberOfResolvedConstraints( deep, seen );
    num += elaborationsConstraint.getNumberOfResolvedConstraints( false, seen );
    num += effectsConstraint.getNumberOfResolvedConstraints( deep, seen );
    if ( deep ) {
      num += HasConstraints.Helper.getNumberOfResolvedConstraints( elaborations.keySet(), deep, seen );
      num += HasConstraints.Helper.getNumberOfResolvedConstraints( effects, deep, seen );
      Set< Event > events = getEvents( false, null );
      num += HasConstraints.Helper.getNumberOfResolvedConstraints( events, deep, seen );
    }
    return num;
  }

  @Override
  public long getNumberOfUnresolvedConstraints( boolean deep,
                                                Set< HasConstraints > seen ) {
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) {
      return 0;
    }
    seen = pair.second;
    if ( seen != null ) seen.remove( this );
    long num = 0;
    num += super.getNumberOfUnresolvedConstraints( deep, seen );
    num += elaborationsConstraint.getNumberOfUnresolvedConstraints( false, seen );
    num += effectsConstraint.getNumberOfUnresolvedConstraints( deep, seen );
    if ( deep ) {
      num += HasConstraints.Helper.getNumberOfUnresolvedConstraints( elaborations.keySet(), deep, seen );
      num += HasConstraints.Helper.getNumberOfUnresolvedConstraints( effects, deep, seen );
      Set< Event > events = getEvents( false, null );
      num += HasConstraints.Helper.getNumberOfUnresolvedConstraints( events, deep, seen );
    }
    return num;
  }

  @Override
  public long getNumberOfConstraints( boolean deep, Set< HasConstraints > seen ) {
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) {
      return 0;
    }
    seen = pair.second;
    if ( seen != null ) seen.remove( this );
    long num = 0;
    num += super.getNumberOfConstraints( deep, seen );
    num += elaborationsConstraint.getNumberOfConstraints( false, seen );
    num += effectsConstraint.getNumberOfConstraints( deep, seen );
    if ( deep ) {
      num += HasConstraints.Helper.getNumberOfConstraints( elaborations.keySet(), deep, seen );
      num += HasConstraints.Helper.getNumberOfConstraints( effects, deep, seen );
      Set< Event > events = getEvents( false, null );
      num += HasConstraints.Helper.getNumberOfConstraints( events, deep, seen );
    }
    return num;
  }

  @Override
  public Collection< Constraint > getConstraints( boolean deep,
                                                  Set<HasConstraints> seen ) {
    boolean mayHaveBeenPropagating = Parameter.mayPropagate;
    Parameter.mayPropagate = false;
    boolean mayHaveBeenChanging = Parameter.mayChange;
    Parameter.mayChange = false;
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) {
      Parameter.mayPropagate = mayHaveBeenPropagating;
      Parameter.mayChange = mayHaveBeenChanging;
      return Utils.getEmptySet();
    }
    seen = pair.second;
    if ( seen != null ) seen.remove( this );
    Collection< Constraint > set = new HashSet<Constraint>();
    set = Utils.addAll( set, super.getConstraints( deep, seen ) );
    //if ( set.equals( Utils.getEmptySet() ) ) return set;
    set.add( elaborationsConstraint );
    set.add( effectsConstraint );
    if ( deep ) {
      set = Utils.addAll( set, HasConstraints.Helper.getConstraints( elaborationsConstraint, false, seen ) );
      set = Utils.addAll( set, HasConstraints.Helper.getConstraints( effectsConstraint, deep, seen ) );
      set = Utils.addAll( set, HasConstraints.Helper.getConstraints( elaborations.keySet(), false, seen ) );
      set = Utils.addAll( set, HasConstraints.Helper.getConstraints( effects, deep, seen ) );
      Set< Event > events = getEvents( false, null );
      set = Utils.addAll( set, HasConstraints.Helper.getConstraints( events, deep, seen ) );
    }
    Parameter.mayPropagate = mayHaveBeenPropagating;
    Parameter.mayChange = mayHaveBeenChanging;
    return set;
  }

  @Override
  public CollectionTree getConstraintCollection( boolean deep,
                                                 Set< HasConstraints > seen ) {
    Pair< Boolean, Set< HasConstraints > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) {
      return null;
    }
    seen = pair.second;

    if ( constraintCollection == null ) {
      if ( seen != null ) seen.remove( this );
      constraintCollection = super.getConstraintCollection( deep, seen );
      constraintCollection.add( elaborationsConstraint );
      constraintCollection.add( effectsConstraint );
      if ( deep ) {
        constraintCollection.add( elaborationsConstraint );
        constraintCollection.add( effectsConstraint );
        constraintCollection.add( elaborations );
        constraintCollection.add( effects );
      }
    }
    return constraintCollection;
  }

  public static void testConstraintCollection() {
    // TODO -- HERE!
  }

  /*
   * Gather any event instances contained by this event.
   *
   * NOTE: Uses reflection to dig events out of members, but does not look in
   * arrays or collections other than elaborations, so this may need
   * redefinition in subclasses. This enforces the idea that events do not occur
   * unless elaborated.
   *
   * (non-Javadoc)
   *
   * @see gov.nasa.jpl.ae.event.HasEvents#getEvents(boolean)
   */
  @Override
  public Set< Event > getEvents( boolean deep, Set< HasEvents > seen ) {
    if ( elaborations == null ) return Utils.getEmptySet();
    Pair< Boolean, Set< HasEvents > > pair = Utils.seen( this, deep, seen );
    if ( pair.first ) return Utils.getEmptySet();
    seen = pair.second;
    Set< Event > set = new HashSet< Event >();
    for ( Entry< ElaborationRule, Vector< Event > > e :
          elaborations.entrySet() ) {
      if ( e.getValue() == null ) continue;
      for ( Event event : e.getValue() ) {
        set.add( event );
        if ( deep ) {
          if ( event instanceof HasEvents )
            set = Utils.addAll( set, ((HasEvents)event).getEvents( deep, seen ) );
        }
      }
    }
    return set;
  }

  // Conditionally create child event instances from this event instance.
  /* (non-Javadoc)
   * @see event.Event#elaborate(boolean)
   */
  @Override
  public void elaborate( boolean force ) {
    if ( elaborations == null ) {
      elaborations = new HashMap< ElaborationRule, Vector< Event > >();
    }
    for ( Entry< ElaborationRule, Vector< Event > > er : elaborations.entrySet() ) {
      if ( Debug.isOn() ) Debug.outln( getName() + " trying to elaborate " + er );
      elaborate( er, force );
    }
  }

  /**
   * @param entry
   * @return
   */
  protected boolean isElaborated( Entry< ElaborationRule, Vector< Event > > entry ) {
    return isElaborated( entry.getValue() );
  }

  /**
   * @param events
   * @return
   */
  protected boolean isElaborated( Vector< Event > events ) {
    boolean r = !Utils.isNullOrEmpty( events );
    if ( Debug.isOn() ) Debug.outln( "isElaborated(" + events + ") = " + r  + " for " + this.getName() );
    return r;
  }

  /**
   * @param entry
   * @param force
   * @return
   */
  public boolean elaborate( Entry< ElaborationRule, Vector< Event > > entry,
                            boolean force ) {
    boolean elaborated = true;
    if ( !isElaborated( entry ) || force ) {

      // Don't elaborate outside the horizon.  Need startTime grounded to know.
      if ( !startTime.isGrounded(false, null) ) return false;
      // REVIEW -- is force a good argument to getValue()?
      if ( startTime.getValue(force) >= Timepoint.getHorizonDuration() ) {
        if ( Debug.isOn() ) Debug.outln( "satisfyElaborations(): No need to elaborate event outside the horizon: "
                     + getName() );
        return true;
      }

      Vector< Event > oldEvents = entry.getValue();
      elaborated = entry.getKey().attemptElaboration( oldEvents, true );
    }
    return elaborated;
  }

  @Override
  public boolean isDeconstructed() {
    if ( Utils.isNullOrEmpty( effects )
         && Utils.isNullOrEmpty( elaborations )
         && super.isDeconstructed() ) {
      return true;
    }
    return false;
  }

  /*
   * Try to remove others' references to this, possibly because it is being
   * deleted.
   * (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#detach()
   */
  @Override
  public void deconstruct() {
    if ( isDeconstructed() ) {
      if ( Debug.isOn() ) {
        Debug.outln( "Attempted to deconstruct a deconstructed event: " + this );
//        try {
//          Thread.sleep(100);
//        } catch ( InterruptedException e ) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//        }
      }
      return;
    }
    String msg = "Deconstructing event: " + this.toString( true, true, null );
    if ( Debug.isOn() ) Debug.outln( msg );
    System.err.println( msg );

    // Get time varying objects to use later before potentially disconnecting
    // them.
    Set< TimeVarying< ? > > timeVaryingObjs = getTimeVaryingObjects( true, null );

    // Detach elaborations.
    if ( elaborations != null ) {
      for ( Entry< ElaborationRule, Vector< Event > > e : elaborations.entrySet() ) {
        for ( Event evt : e.getValue() ) {
          if ( evt != null ) evt.deconstruct();
        }
        e.getKey().deconstruct();
      }
      elaborations.clear();
      //elaborations = null;
    }

    // Detach effects.
    assert effects != null;
    for ( Pair< Parameter< ? >, Set< Effect > > p : effects ) {
      Parameter< ? > tvp = p.first;
      TimeVarying< ? > tv = null;
      if ( tvp != null ) {
        try {
          tv = Expression.evaluate( tvp, TimeVarying.class, false );
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
        tvp.deconstruct();
      }
      Set< Effect > set = p.second;
      if ( set != null ) {
        for ( Effect e : set ) {
          if ( tv != null ) {
            e.unApplyTo( tv ); // should this happen in EffectFunction?
          }
          if ( e instanceof EffectFunction ) {
            ((EffectFunction)e).deconstruct();
          }
        }
      }
    }
    effects.clear();

    // Detach effects embedded in dependency expressions.
    for ( Dependency<?> dependency : getDependencies() ) {
      for ( Effect e : getEffectsFromDependency( dependency ) ) {
        EffectFunction effectFunction;
        if ( e instanceof EffectFunction )  {
          effectFunction = (EffectFunction)e;
        } else {
          continue;
        }
        if ( effectFunction.getObject() != null ) {
          TimeVarying< ? > tv = null;
          try {
            tv = Expression.evaluate( effectFunction.getObject(),
                                                     TimeVarying.class,
                                                     false, false );
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
          if ( tv != null ) {
            effectFunction.unApplyTo( tv );
          }
        }
      }
    }

    // Remove references to time parameters from TimeVaryingMaps.
    // TODO -- REVIEW -- Is this already being done by ParameterListenerImpl.detach()
    // and TimeVaryingMap.detach( parameter )?
    Set<Timepoint> timepoints = getTimepoints( false, null );
    for ( TimeVarying< ? > tv : timeVaryingObjs  ) {
      if ( tv instanceof TimeVaryingMap ) {
        for ( Parameter<?> p : timepoints ) {
          if ( Debug.isOn() ) Debug.out( "i" );
          ( (TimeVaryingMap<?>)tv ).detach( p );
        }
//        ( (TimeVaryingMap<?>)tv ).keySet().removeAll( timepoints );
//        ( (TimeVaryingMap<?>)tv ).isConsistent();
      }
    }

    super.deconstruct();

    if ( Debug.isOn() ) Debug.outln( "Done deconstructing event: " + this.toString( true, true, null ) );
  }

  @Override
  public void detach( Parameter< ? > parameter ) {
    super.detach( parameter );
    // TODO - REVIEW - remove effects referencing parameter (does this make sense?)
    for ( Pair< Parameter< ? >, Set< Effect > > p : effects ) {
      Parameter< ? > tvp = p.first;
      ArrayList< Effect > set = new ArrayList< Effect >( p.second );
      for ( Effect e : set ) {
        if ( e instanceof EffectFunction ) {
          EffectFunction ef = (EffectFunction)e;
          if ( ef.hasParameter( parameter, false, null ) ) {
            effects.remove( e );
          }
        }
      }
    }

    // TODO - REVIEW - remove elaborations referencing parameter (does this make sense?)

    // See if other events need to detach the parameter
    for ( Event e : getEvents( false, null ) ) {
      if ( e instanceof ParameterListener ) {
        ((ParameterListener)e).detach( parameter );
      }
    }
  }


  /* (non-Javadoc)
   * @see event.Event#executionToString()
   */
  @Override
  public String executionToString() {
    StringBuffer sb = new StringBuffer();
    Set< Event > events = new HashSet< Event >();
    events.add( this );
    events = Utils.addAll( events, getEvents( true, null ) );
    for ( Event e : events ) {
      sb.append( MoreToString.Helper.toString( e, true, false, null ) + "\n" );
    }
    for ( ParameterListenerImpl pl : getNonEventObjects( true, null ) ) {
      sb.append( MoreToString.Helper.toString( pl, true, false, null ) + "\n" );
    }
//    for ( TimeVarying<?> tv : getTimeVaryingObjects( true, null ) ) {
//      sb.append( MoreToString.Helper.toString( tv, true, true, null ) + "\n" );
//    }
    for ( Object o : getTimeVaryingObjectMap(true, null).values() ) {
//      TimeVarying<?> tv = null;
//      if ( o instanceof TimeVarying ) {
//        tv = (TimeVarying<?>)o;
//      } else if (o instanceof Parameter && ) {
//        tv =
//      }
      sb.append( MoreToString.Helper.toString( o, true, true, null ) + "\n" );
    }
    return sb.toString();
  }

  public void writeAspen(String mdlFileName, String iniFileName ) {
    FileOutputStream mdlOs = null;
    FileOutputStream iniOs = null;
    try {
      mdlOs = new FileOutputStream( mdlFileName );
      iniOs = new FileOutputStream( iniFileName );
      writeAspen( mdlOs, iniOs );
    } catch ( FileNotFoundException e ) {
      e.printStackTrace();
    }
    try {
      if ( mdlOs != null ) {
        mdlOs.flush();
        mdlOs.close();
      }
    } catch ( IOException e ) {
      e.printStackTrace();
    }
    try {
      if ( iniOs != null ) {
        iniOs.flush();
        iniOs.close();
      }
    } catch ( IOException e ) {
      e.printStackTrace();
    }
  }

  public void writeAspen(OutputStream mdlOs, OutputStream iniOs) {
    // Load keywords into name translator so we can translate names to avoid them.
    final String aspenKeyWords[] =
        { "activity", "parameter", "start_time", "duration", "end_time",
         "permissions", "priority", "model", "fulfills", "resource",
         "state_variable", "depletable", "nondepletable", "default",
         "satisfies", "decomposition", "decompositions", "reservations",
         "dependencies", "alternatives", "expansion", "expansions",
         "uncertainty", "uncertain", "of", "start", "end", "starts_before",
         "starts_after", "ends_before", "ends_after", "starts_at", "ends_at",
         "where", "with", "or", "string", "real", "int", "bool", "constraint",
         "constraints", "ordered", "ordered_backtoback", "usage" };
    PrintWriter mdl = new PrintWriter( mdlOs );
    PrintWriter ini = new PrintWriter( iniOs );
    NameTranslator nt = new NameTranslator();
    for ( String n : aspenKeyWords ) {
      nt.translate( n, "AE", "ASPEN" );
    }

    // write model.mdl
    String modelName = nt.translate( getName(), "AE", "ASPEN" );
    mdl.println("model " + modelName + " {");
    String unitStr = Timepoint.getUnits().toString();
    unitStr = unitStr.substring( 0, unitStr.length()-1 ); // removing last char, 's'
    mdl.println("  time_scale = " + unitStr  + ";");
    mdl.println("  time_zone = gmt;");
    mdl.println("  time_format = tee;");
    mdl.println("  horizon_start = " + TimeUtils.toAspenTimeString( Timepoint.getEpoch() ) + ";");
    mdl.println("  horizon_duration = " + Timepoint.getHorizonDuration() + ";");
    mdl.println("};\n");

    // write events
    Set< Event > events = new HashSet< Event >();
    events.add( this );
    events = Utils.addAll( events, getEvents( true, null ) );
    //final int typeDepth = 2; // the nested class depth, for which types will be created.
    for ( Event e : events ) {
      // Create activity type based on enclosing classes.
      List< Class< ? > > enclosingClasses = new ArrayList< Class<?> >();
      Class<?> enclosingClass = e.getClass().getEnclosingClass();
      //System.out.println( e.getClass().getName() );
      while ( enclosingClass != null ) {
        //System.out.println("is enclosed by " + enclosingClass.getSimpleName() );
        enclosingClasses.add( enclosingClass );
        enclosingClass = enclosingClass.getEnclosingClass();
      }
      String typeName = null;
      if ( enclosingClasses.size() > 0 ) {
        typeName = enclosingClasses.get( enclosingClasses.size() - 1 ).getSimpleName();
      } else {
        typeName = e.getClass().getSimpleName();
      }
      // Get the corresponding ASPEN activity schema name.
      Long id = nt.getIdForNameAndDomain( typeName, "AE" );
      boolean newSchema = ( id == null );
      typeName = nt.translate( typeName, "AE", "ASPEN" );
      // Create a unique ASPEN instance name.
      String instanceName = e.getName();
      if ( Utils.isNullOrEmpty( instanceName ) ) {
        instanceName = typeName;
      }
      instanceName = nt.makeNameUnique( instanceName, "AE" );
      instanceName = nt.translate( instanceName, "AE", "ASPEN" );
      if ( newSchema ) {
        mdl.println( "activity " + typeName + " {}" );
      }
      ini.println( typeName + " " + instanceName + " {" );
      ini.println( "  start_time = " + Math.max( 0, Math.min( 1073741823, e.getStartTime().getValueOrMin())) + ";" );
      ini.println( "  duration = " + Math.max( 1, Math.min( 1073741823, e.getDuration().getValueOrMin() ) ) + ";\n}" );
    }

    // write timelines
    Set< TimeVarying< ? > > states = getTimeVaryingObjects( true, null );
    for ( TimeVarying< ? > state : states ) {
      String tlName = "";
      if ( state instanceof TimeVaryingMap ) {
        tlName = ( (TimeVaryingMap< ? >)state ).getName();
      }
      if ( state instanceof AspenTimelineWritable ) {
        tlName = nt.makeNameUnique( tlName, "AE" );
        tlName = nt.translate( tlName, "AE", "ASPEN" );
        AspenTimelineWritable tl = (AspenTimelineWritable)state;
        mdl.print( tl.toAspenMdl( tlName ) );
        mdl.flush();
        ini.print( tl.toAspenIni( tlName ) );
        ini.flush();
      }
    }
  }

 // getters and setters

  /* (non-Javadoc)
   * @see event.Event#getStartTime()
   */
  @Override
  public Timepoint getStartTime() {
    return startTime;
  }

  /* (non-Javadoc)
   * @see event.Event#setStartTime(event.Timepoint)
   */
  @Override
  public void setStartTime( Timepoint startTime ) {
    this.startTime = startTime;
  }

  /* (non-Javadoc)
   * @see event.Event#getDuration()
   */
  @Override
  public Duration getDuration() {
    return duration;
  }

  /* (non-Javadoc)
   * @see event.Event#setDuration(event.Duration)
   */
  @Override
  public void setDuration( Duration duration ) {
    this.duration = duration;
  }

  /* (non-Javadoc)
   * @see event.Event#getEndTime()
   */
  @Override
  public Timepoint getEndTime() {
    return endTime;
  }

  /* (non-Javadoc)
   * @see event.Event#setEndTime(event.Timepoint)
   */
  @Override
  public void setEndTime( Timepoint endTime ) {
    this.endTime = endTime;
  }

  /* (non-Javadoc)
   * @see event.Event#getEffects()
   */
  @Override
  public List< Pair< Parameter< ? >, Set< Effect > > > getEffects() {
    return effects;
  }

  /* (non-Javadoc)
   * @see event.Event#setEffects(java.util.SortedMap)
   */
  @Override
  public void setEffects( List< Pair< Parameter< ? >, Set< Effect > > > effects ) {
    this.effects = effects;
  }

  /* (non-Javadoc)
   * @see event.Event#getElaborations()
   */
  @Override
  public Map< ElaborationRule, Vector< Event >> getElaborations() {
    return elaborations;
  }

  /* (non-Javadoc)
   * @see event.Event#setElaborations(java.util.Map)
   */
  @Override
  public void setElaborations( Map< ElaborationRule,
                               Vector< Event > > elaborations ) {
    this.elaborations = elaborations;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#compareTo(gov.nasa.jpl.ae.event.ParameterListenerImpl)
   */
  @Override
  public int compareTo( ParameterListenerImpl o ) {
    return compareTo( o, true );
  }
  @Override
  public int compareTo( ParameterListenerImpl o, boolean checkId ) {
    if ( this == o ) return 0;
    if ( o == null ) return -1;
    if ( checkId ) return CompareUtils.compare( getId(), o.getId() );
    int compare = super.compareTo( o, checkId );
    if ( compare != 0 ) return compare;
//    compare = Utils.compareTo( getClass().getName(), o.getClass().getName() );
//    if ( compare != 0 ) return compare;
//    compare = Utils.compareTo( getName(), o.getName() );
//    if ( compare != 0 ) return compare;
    if ( o instanceof DurativeEvent ) {
      DurativeEvent oe = (DurativeEvent)o;
      compare = startTime.compareTo( oe.getStartTime() );
      if ( compare != 0 ) return compare;
      compare = endTime.compareTo( oe.getEndTime() );
      if ( compare != 0 ) return compare;
      compare = CompareUtils.compareCollections( effects, oe.effects, true,
                                                 checkId );
      if ( compare != 0 ) return compare;
      compare = CompareUtils.compareCollections( elaborations, oe.elaborations,
                                                 true, checkId );
      if ( compare != 0 ) return compare;
    }
    compare = CompareUtils.compareCollections( parameters, o.getParameters(),
                                               true, checkId );
    if ( compare != 0 ) return compare;
    compare = CompareUtils.compare( this, o, false, checkId );
    if ( compare != 0 ) return compare;
    return compare;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#handleValueChangeEvent(gov.nasa.jpl.ae.event.Parameter)
   *
   * An event owns all of its parameters because it is required to contain any
   * dependency that sets one of its parameters and has some connection through
   * its other members to any reference or constraint on it. Thus, an event must
   * know about the parent event from which it is elaborated if the parent
   * references the parameter.
   * handleValueChangeEvent( parameter, newValue ) updates dependencies,
   * effects, and elaborations for the changed parameter.
   */
  @Override
  public void handleValueChangeEvent( Parameter< ? > parameter ) {
    // REVIEW -- Should we be passing in a set of parameters? Find review/todo
    // note on staleness table.

    // The super class updates the dependencies.
    super.handleValueChangeEvent( parameter );

    // Update other events
    for ( Event e : getEvents( false, null ) ) {
      if ( e instanceof ParameterListener ) {
        ((ParameterListener)e).handleValueChangeEvent( parameter );
      }
    }
  }

  /**
   * @return whether or not the constraints of the elaborations have been
   *         satisfied.
   */
  public boolean satisfyElaborations() {
    boolean deep = true;
    return satisfyElaborations( deep, null );
  }

  /**
   * @param deep
   * @param seenSatisfiable
   * @return whether or not the constraints of the elaborations have been
   *         satisfied.
   */
  public boolean satisfyElaborations( boolean deep,
                                      Set<Satisfiable> seenSatisfiable ) {
    if ( elaborations == null ) return false;
    Pair< Boolean, Set< Satisfiable > > pair = Utils.seen( this, deep,
                                                           seenSatisfiable );
    if ( pair.first ) return true;
    seenSatisfiable = pair.second;

    // REVIEW -- code below is replicated in elaborate() and
    // elaborationsConstraint.
    // Don't elaborate outside the horizon.  Need startTime grounded to know.
    Set< Groundable > seenGroundable = null;
//    Set< Satisfiable > seenSatisfiable = null;
    if ( !startTime.isGrounded(deep, seenGroundable) ) startTime.ground(deep, seenGroundable);
    if ( !startTime.isGrounded(deep, seenGroundable) ) return false;
    if ( startTime.getValue(true) >= Timepoint.getHorizonDuration() ) {
      if ( Debug.isOn() ) Debug.outln( "satisfyElaborations(): No need to elaborate event outside the horizon: "
                   + getName() );
      return true;
    }

    boolean satisfied = true;
    elaborate( true );
    // REVIEW -- This vector doesn't necessarily avoid concurrent modification
    // errors. Consider changing elaborations from a map into a list of pairs,
    // like parameters and effects.
    Vector< Vector< Event > > elaboratedEvents = new Vector< Vector<Event> >();
    for ( Vector< Event > v : elaborations.values() ) {
      elaboratedEvents.add( new Vector< Event >( v ) );
    }
    for ( Vector< Event > v : elaboratedEvents ) {
      for ( Event e : v ) {
        if ( e instanceof Satisfiable ) {
          if ( !( (Satisfiable)e ).isSatisfied(deep, null) ) {
            if ( e instanceof ParameterListenerImpl ) {
              ParameterListenerImpl pl = (ParameterListenerImpl)e;
              pl.amTopEventToSimulate = false;
              pl.maxLoopsWithNoProgress = 2;
              pl.maxPassesAtConstraints = 1;
              pl.timeoutSeconds = Math.max(0.5,timeoutSeconds / 2.0);
              pl.usingLoopLimit = true;
              pl.usingTimeLimit = true;
            }
            if ( !( (Satisfiable)e ).satisfy(deep, seenSatisfiable) ) {
              satisfied = false;
            }
          }
        }
      }
    }
    return satisfied;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#refresh(gov.nasa.jpl.ae.event.Parameter)
   */
  @Override
  public boolean refresh( Parameter< ? > parameter ) {
    boolean didRefresh = super.refresh( parameter );

    if ( !didRefresh ) {
      for ( Event e : getEvents( false, null ) ) {
        if ( e instanceof ParameterListener ) {
          if ( ((ParameterListener)e).refresh( parameter ) ) return true;
        }
      }
    }
    return didRefresh;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.ParameterListenerImpl#setStaleAnyReferencesTo(gov.nasa.jpl.ae.event.Parameter)
   */
  @Override
  public void setStaleAnyReferencesTo( Parameter< ? > changedParameter, Set< HasParameters > seen ) {
    Pair< Boolean, Set< HasParameters > > p = Utils.seen( this, true, seen );
    if (p.first) return;
    seen = p.second;
    seen.remove(this);  // removing this so that call to super succeeds 

    // Alert affected dependencies.
    super.setStaleAnyReferencesTo( changedParameter, seen );
    
    for ( Event e : getEvents( false, null ) ) {
      if ( e instanceof ParameterListener ) {
        ((ParameterListener)e).setStaleAnyReferencesTo( changedParameter, seen );
      }
    }
  }
  public static void doEditCommandFile(DurativeEvent d) throws IOException {
    Runtime.getRuntime().exec( "xterm -e 'vi commandFile'" );
    d.addObservation( Timepoint.now(), d.endTime, Timepoint.now().getValue() );
  }
//  {
//
//    DurativeEvent commandEditing = this;
//    addObservation(Timepoint.now(), commandEditing .endTime, Timepoint.now().getValue());
//    Command.
//commandSequence.add(Timepoint.fromTimestamp("2012-08-05T11:00:00-07:00"),
//                    new Command(Runtime.getRuntime(), Runtime.class, "exec",
//                                new Object[] { "xterm -e 'vi commandFile'" }));
//
//  }
////    Timepoint startTime = Timepoint.fromTimestamp("2012-08-05T11:00:00-07:00");
////    commandSequence.add( startTime,
////                         new Command( Runtime.getRuntime(),
////                                      Runtime.class,
////                                      "exec",
////                                      new Object[] { "xterm -e 'vi commandFile'" },
////                                      (Call)null ) );
////
////  }

  public <V> Constraint addObservation( Parameter<Integer> timeSampled,
                                        Parameter< V > systemVariable,
                                        V value ) {
//    String exprString =
//        systemVariable.getOwner().getName() + "." + systemVariable.getName()
//            + ".getValue( timeSampled ) == value";
//    String xmlFileName = "";
//    String pkgName = "";
//    EventXmlToJava xmlToJava = new EventXmlToJava( xmlFileName, pkgName );
//    Expression<V> eqExpr =  xmlToJava.javaToAeExpr( exprString, null, true );
    FunctionCall evalFunc =
        new FunctionCall( null, Expression.class, "evaluate",
                          new Object[] { systemVariable, TimeVarying.class,
                                        true, true }, (Class<?>)null );
    Expression< V > getValExpr =
        new Expression< V >( new FunctionCall( null,
                                               TimeVarying.class, "getValue",
                                               new Object[] { timeSampled },
                                               evalFunc, (Class<?>)null ) );
    Functions.Equals< V > eqExpr = new Functions.Equals< V >( value, getValExpr );
    ConstraintExpression c = new ConstraintExpression( eqExpr );
    constraintExpressions.add( c );
    return c;
  }
//  public <V> Constraint addObservation( Parameter<Integer> timeSampled,
//                                        Parameter<V> systemVariable,
//                                        V value ) {
//    return null;
//  }

}
