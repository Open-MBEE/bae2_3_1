/**
 * 
 */
package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

import junit.framework.Assert;

/**
 * 
 */
public class TimeVaryingList< T > extends TimeVaryingMap< List< T > > {

  private static final long serialVersionUID = 5136649915412969932L;

  private static Collection< Method > effectMethods = TimeVaryingList.initEffectMethods();

  private static Method addMethod = getAddMethod();
  private static Method addWithListMethod = getAddWithListMethod();
  private static Method addIfNotContainedMethod = getAddIfNotContainedMethod();
  private static Method addIfNotContainedIfMethod = getAddIfNotContainedIfMethod();
  private static Method removeMethod = getRemoveMethod();
  private static Method removeWithListMethod = getRemoveWithListMethod();
  
  /**
   * the maximum size allowed for the list
   */
  public int maxSize = Integer.MAX_VALUE;
  /**
   * whether the lists are forced to "increase" over time. Specifically, for any
   * two adjacent lists in the map, L1 and L2 (where an empty list is
   * substituted for null) with entries t1=L1 and t2=L2, and t1<=t2, the set of
   * elements in L2 must be a superset of the elements of L1.
   */
  public boolean monotonic = true;
  /**
   * whether duplicate values are added to the lists when using add().
   */
  public boolean duplicatesAllowed = false;
 
  /**
   * @param name
   */
  public TimeVaryingList( String name ) {
    super( name );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param name
   */
  public TimeVaryingList( int maxSize ) {
    this( "", maxSize );
  }

  /**
   * @param name
   */
  public TimeVaryingList( String name, int maxSize ) {
    this( name );
    this.maxSize = maxSize;
    // TODO Auto-generated constructor stub
  }

  /**
   * @param name
   * @param defaultValue
   */
  public TimeVaryingList( String name, List< T > defaultValue) {
    super( name, defaultValue, null );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param name
   * @param initialValueFunction
   * @param o
   * @param samplePeriod
   * @param horizonDuration
   */
  public TimeVaryingList( String name, Method initialValueFunction, Object o,
                          int samplePeriod, int horizonDuration ) {
    super( name, initialValueFunction, o, samplePeriod, horizonDuration );
    // TODO Auto-generated constructor stub
  }

  public int maxSize() { return maxSize; }
  
  /**
   * Append the value to the end of the list at Parameter<Integer> t unless the list has
   * reached its maximum size.
   * 
   * @param t
   * @param value
   * @return whether the map was changed as a result of this function.
   */
  public boolean add( Parameter<Integer> t, T value ) {
    return add( t, value, monotonic, !duplicatesAllowed, true );
  }
  
  /**
   * Append each value in values to the end of the list at Parameter<Integer> t unless
   * the list has reached its maximum size.
   * 
   * @param t
   * @param values
   * @return whether the map was changed as a result of this function.
   */
  public boolean add( Parameter<Integer> t, List< T > values ) {
    boolean changed = false;
    for ( T v : values ) {
      if ( add( t, v ) ) {
        changed = true;
      }
    }
    return changed;
  }

  /**
   * Append the value to the end of the list at Parameter<Integer> t, creating a new
   * entry if necessary. If list is not null, it is assumed to be the existing
   * entry.
   * 
   * @param t
   * @param value
   * @return whether the map was changed by the addition.
   */
  protected boolean addEntry( Parameter<Integer> t, List< T > list, T value, 
                              boolean onlyifNotContained,
                              boolean onlyIfBelowMaxSize ) {
    List< T > oldList = ( list != null ? list : getValue( t ) );
    if ( onlyIfBelowMaxSize ) { 
      if ( maxSize == 0 || ( oldList != null && oldList.size() >= maxSize ) ) {
        return false;
      }
    }
    if ( onlyifNotContained ) {
      if ( oldList != null && oldList.contains( value ) ) {
        return false;
      }
    }
    boolean changed = false;
    
    if ( list == null ) list = get( t );
    if ( list == null ) {
      list = nonNullEntry( t );
      changed = true;
    }
    if ( list.add( value ) ) changed = true;
    return changed;
  }
  
  protected boolean add( Parameter<Integer> t, T value,
                         boolean monotonically, boolean onlyifNotContained,
                         boolean onlyIfBelowMaxSize ) {
    if ( t == null || t.getValue(true) == null ) return false;
    boolean noEntry = !containsKey( t );
    List< T > list = null;
    if ( !noEntry ) list = get( t );
    if ( maxSize == 0 || ( list != null && list.size() >= maxSize ) ) {
      return false;
    }
    boolean changed = false;
    if ( !onlyifNotContained || list == null || !list.contains( value ) ) {
      if ( addEntry( t, list, value, onlyifNotContained, onlyIfBelowMaxSize ) ) {
        changed = true;
      }
    }
    if ( monotonically ) {
      NavigableMap< Parameter<Integer>, List< T > > tail = tailMap(t, false);
      for ( Map.Entry< Parameter<Integer>, List< T > > e : tail.entrySet() ) {
        if ( !addEntry( e.getKey(), e.getValue(), value, onlyifNotContained,
                        onlyIfBelowMaxSize ) ) {
          if ( monotonic ) break; // will not succeed for successors since it's
                                  // monotonic
        }
      }
    }
    return changed;
  }
  
  /**
   * Remove the value from the list. If justLast, then just remove the last
   * occurrence of value; otherwise, remove all occurrences.
   * 
   * @param value
   * @param list
   * @param justLast
   * @return whether list was changed as a result of this function.
   */
  protected static boolean remove( Object value, List< ? > list,
                                   boolean justLast ) {
    boolean changed = false;
    if ( list == null ) return false; 
    if ( !justLast ) {
      if ( list.remove( value ) ) changed = true;
    } else {
      int pos = list.lastIndexOf( value );
      if ( pos >= 0 ) {
        list.remove( pos );
        changed = true;
      }
    }
    return changed;
  }
  
  /**
   * Remove the value from the list at Parameter<Integer> t. If duplicatesAreAllowed,
   * then all entries of value will be removed; otherwise, just the last entry
   * of value. If monotonic, then value will be removed from the list also for
   * all timepoints after t.
   * 
   * @param t
   * @param value
   * @return whether any changes were made to the map or its contents as a
   *         result of this function.
   */
  public boolean remove( Parameter<Integer> t, T value ) {
    return remove( t, value, monotonic, !duplicatesAllowed );
  }

  /**
   * Remove each value in values from the list at Parameter<Integer> t. If
   * duplicatesAreAllowed, then all entries of value will be removed; otherwise,
   * just the last entry of value. If monotonic, then value will be removed from
   * the list also for all timepoints after t.
   * 
   * @param t
   * @param values
   * @return whether any changes were made to the map or its contents as a
   *         result of this function.
   */
  public boolean remove( Parameter<Integer> t, List< T > values ) {
    boolean changed = false;
    for ( T v : values ) {
      if ( remove( t, v ) ) {
        changed = true;
      }
    }
    return changed;
  }

  protected boolean remove( Parameter<Integer> t, T value,
                            boolean monotonically, boolean justLast ) {
    //Assert.assertTrue( floatingEffects.isEmpty() );
    if ( floatingEffects != null){
      List< TimeVaryingMap.TimeValue > deleteList = new ArrayList();
      for ( TimeVaryingMap.TimeValue tv : floatingEffects ){
        if (tv.first == t) {
          deleteList.add(tv);
        }
        else if (tv.second == t) {
          deleteList.add(tv);
        }
      }
      for (TimeVaryingMap.TimeValue tv : deleteList){
        floatingEffects.remove(tv);
      }
    }
    boolean changed = false;
    // Create an entry at t if one does not exist.
    List< T > list = getValue( t );
    if ( !containsKey( t ) && list != null && list.contains( value ) ) {
      list = nonNullEntry( t );
      changed = true;
    }
    // Remove value from the list entry and, if monotonically, from the lists of
    // all following entries, while removing entries where the list does not
    // change.
    ArrayList< Parameter<Integer> > deleteList = new ArrayList< Parameter<Integer> >();
    List< T > lastList = getValueBefore( t );
    NavigableMap< Parameter<Integer>, List< T > > tail = tailMap(t, true);
    for ( Map.Entry< Parameter<Integer>, List< T > > e : tail.entrySet() ) {
      if ( remove( value, e.getValue(), justLast ) ) changed = true;
      // Save away the entry to delete if the list is no different than that of
      // the last entry.
      if ( Utils.valuesEqual( e.getValue(), lastList )
           || ( ( monotonically || lastList == null ) && e.getValue().isEmpty() ) ) {
        deleteList.add( e.getKey() );
      } else {
        lastList = e.getValue();
      }
    }
    // Remove lists that are no different than that of the last entry.
    for ( Parameter<Integer> tp : deleteList ) {
      remove( tp );
    }
    return changed;
  }
  
  protected void breakpoint() {
    if ( getName() != null && getName().contains( "13608" ) ) {
      return;
    }
    if ( getName() != null && getName().contains( "14272" ) ) {
      return;
    }
  }
  
  /**
   * Append value to the end of the list at Parameter<Integer> t unless the list already contains the value or has reached its maximum size.
   * @param t
   * @param value
   * @return whether the map was changed as a result of this function.
   */
  public boolean addIfNotContained( Parameter<Integer> t, T value ) {
    if ( !contains( t, value ) ) {
      return add( t, value );
    }
    return false;
  }
  
  public boolean addIfNotContained(  Parameter<Integer> t, T value, Boolean doSend ) {
    if ( doSend == null || !doSend.booleanValue() ) return false;
    if ( !contains( t, value ) ) {
      return add( t, value );
    }
    return false;
  }
  
  /**
   * @param t
   * @return the list in the map with key t or the list of a newly created entry
   *         that contains the elements of the list at the timepoint before t if
   *         it exists.
   */
  protected List<T> nonNullEntry( Parameter<Integer> t ) {
    List< T > list = get( t );
    if ( list != null ) return list;
    list = getValue( t.getValue(true) );      
    if ( list == null ) {
      list = new ArrayList<T>();
    } else {
      list = new ArrayList<T>( list );
    }
    setValue( t, list );
    return list;
  }
  
  /**
   * @param t
   * @param value
   * @return whether the list at Parameter<Integer> t contains value.
   */
  public boolean contains( Parameter<Integer> t, T value ) {
    List< T > list = getValue( t );
    if ( list != null ) {
      if ( list.contains( value ) ) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * @param t
   * @param value
   * @return the number of occurrences of the value in the List at time t
   */
  public int occurrences( Parameter<Integer> t, T value ) {
    List< T > list = getValue( t );
    return Utils.occurrences( value, list );
  }
    
  /**
   * @param t
   * @param index
   * @return the value at position index in the list at Parameter<Integer> t, where
   *         position 0 corresponds to the first element in the list.
   */
  public T get( Parameter<Integer> t, int index ) {
    List< T > list = getValue( t );
    if ( list == null || list.size() <= index || index < 0 ) return null;
    return list.get( index );
  }
  
  /**
   * @param t
   * @return the last element in the list at Parameter<Integer> t
   */
  public T lastElement( Parameter<Integer> t ) {
    return get( t, size(t) - 1 );
  }

  /**
   * @param t
   * @return the last element in the list at Parameter<Integer> t
   */
  public T firstElement( Parameter<Integer> t ) {
    return get( t, 0 );
  }

  /**
   * @param t
   * @return the number of elements in the list at Parameter<Integer> t or 0 if the list is null.
   */
  public int size( Parameter<Integer> t ) {
    List< T > list = getValue( t );
    if ( list != null ) {
      return list.size();
    }
    return 0;
  }

  /**
   * @param effectFunction
   * @return whether the input add or addIfNotContained effect was applied, i.e.
   *         actually added to the pre-existing list at the time intended or, if
   *         there is no pre-existing list, then added to a new list.
   */
  public boolean isAddApplied( EffectFunction effectFunction ) {
    boolean addIfMethod = effectFunction.method.getName().contains( "addIfNotContained" );
    boolean addMethod = !addIfMethod && effectFunction.method.getName().contains( "add" );
    assert ( effectFunction.arguments != null &&
             effectFunction.arguments.size() == 2 );
    Pair< Parameter<Integer>, T > p =
        getTimepointAndValueOfEffect( effectFunction,
                                      effectFunction.method,
                                      effectFunction.method );
    if ( p == null ) {
      return false;
    }
//    Object t = p.first;//effectFunction.arguments.get( 0 );
//    Object o = effectFunction.arguments.get( 1 );
    T value = p.second;
//    try {
//      value = (T)o;
//    } catch( Exception e ) {
//      if ( o != null ) {
//        Debug.error( true, "Value (" + o + ") has wrong type to be added to "
//                           + this );
//        return false;
//      }
//      //e.printStackTrace();
//    }
    Parameter<Integer> tp = p.first;
    if ( value == null ) {
      return true;  // REVIEW -- is this right?
    }
//    if ( t instanceof Integer ) {
//      tp = makeTempParameter<Integer>( (Integer)t, true );
//    } else if ( t instanceof Parameter<Integer> ) {
//      tp = (Parameter<Integer>)t;
//    } else {
//      System.err.println( "EffectFunction("
//                          + effectFunction
//                          + ") has wrong arguments to TimeVaryingList.add(Parameter<Integer>, T)" );
//      return false;
//    }
    Parameter<Integer> tpBefore = getTimepointBefore( tp );
    int num = occurrences( tp, value );
    int numBefore = occurrences( tpBefore, value );
    if ( num > numBefore ) return true;
    if ( num < numBefore ) return false;
    //   num == numBefore
    int size = size( tp );
    //int sizeBefore = size( tpBefore );
    if ( size >= maxSize ) return true;
    if ( addMethod && !duplicatesAllowed ) return false; // num <= numBefore is implied
//    if ( size < maxSize || sizeBefore < maxSize ) {
    return num > 0;
//    }
//    return true;
  }
  
  @Override
  public boolean isApplied( Effect effect ) {
    breakpoint();
    if ( !( effect instanceof EffectFunction ) ) {
      return false;
    }
    EffectFunction effectFunction = (EffectFunction)effect;
    if ( effectFunction == null || effectFunction.method == null ) return false;
    boolean addIfMethod = effectFunction.method.getName().contains( "addIfNotContained" );
    boolean addMethod = !addIfMethod && effectFunction.method.getName().contains( "add" );
    if ( addIfMethod || addMethod ) {
      return isAddApplied( effectFunction );
    } else {
//    if ( effectFunction.method.getName().contains( "setValue" ) ) {
      return super.isApplied( effect );
    }
  }
  
  @Override
  public boolean isConsistent() {
    boolean ok = super.isConsistent();
    // if ( floatingEffects.isEmpty() ) {
    // java.util.Map.Entry< Parameter<Integer>, List< T > > lastEntry = null;
    List< T > lastList = Utils.getEmptyList();
    for ( java.util.Map.Entry< Parameter<Integer>, List< T > > entry : entrySet() ) {
      List< T > list = null;

      try {
        list = entry.getValue();
      } catch ( ClassCastException cce ) {
        // Ignoring since super.isConsistent() has already checked this;
        ok = false;
        continue;
      }
      if ( !duplicatesAllowed ) {
        int timesSeenError = 0;
        for ( T o : list ) {
          int num = Utils.occurrences( o, list );
          if ( num > 1 ) {
            if ( Debug.isOn() && timesSeenError < 1 ) {
              Debug.error( true, "Error! multiple occurrences of " + o
                           + " in list in TimeVaryingList " + getName() );
              ++timesSeenError;
            }
            ok = false;
          }
        }
        if ( timesSeenError > 1 ) {
          Debug.error( false, timesSeenError + " members have duplicates in TimeVaryingList " + getName() );
        }
      }
      if ( monotonic ) {
        if ( Utils.isNullOrEmpty( list ) && !Utils.isNullOrEmpty( lastList ) ) {
          if ( Debug.isOn() ) {
            Debug.error( true,
                         "Error! null or empty list following non-empty list in TimeVaryingList"
                         + getName() );
          }
          ok = false;
        }
        for ( T o : lastList ) {
          if ( !list.contains( o ) ) {
            if ( Debug.isOn() ) {
              Debug.error( true, "successor list (" + list
                                 + ") does not contain those of prior list("
                                 + lastList + ") for TimeVaryingList "
                                 + getName() );
            }
            ok = false;
          }
        }
      }
      lastList = list;
    }
    // }
    return ok;
  }

  @Override
  protected void floatEffects( Parameter<Integer> t ) {
    if ( t == null ) return;
    assert t.getValueNoPropagate() != null;
    if ( !containsKey( t ) ) return;
    //List< T > list = get( t );
    List< T > added = valuesAddedAt( t );
    if ( Debug.isOn() ) Debug.out( getName() + ": floating effect, (" + t + ", " + added + ")" );
    if ( !Utils.isNullOrEmpty( added ) ) {//list ) ) {
      floatingEffects.add(  new TimeValue( t, added ) );
    }
    remove( t );
    if ( Debug.isOn() ) isConsistent();
  }
  
  @Override
  protected void unfloatEffects( Parameter<Integer> t ) {
    breakpoint();
    if ( t == null ) return;
    if ( t.getValueNoPropagate() == null ) return;
    ArrayList<TimeValue> copy = new ArrayList<TimeValue>( floatingEffects );
    for ( TimeValue e : copy ) {
      if ( e.first.compareTo( t ) == 0 ) { // REVIEW -- Do we need to use
                                           // compareTo instead of equals
                                           // elsewhere?
        add( t, e.second );
        if ( Debug.isOn() ) Debug.out( getName() + ": unfloated effect, " + e );
      }
      floatingEffects.remove( e );
    }
    if ( Debug.isOn() ) isConsistent();
  }
  
  @Override
  public void unapply( Effect effect ) {
    breakpoint();
    if ( !( effect instanceof EffectFunction ) ) {
      return;
    }
    EffectFunction effectFunction = (EffectFunction)effect;
    if ( effectFunction == null || effectFunction.method == null ) return;
    boolean addIfMethod = effectFunction.method.getName().contains( "addIfNotContained" );
    boolean addMethod = !addIfMethod && effectFunction.method.getName().contains( "add" );
    if ( addIfMethod || addMethod ) {
      unapplyAdd( effectFunction );
    } else {
      super.unapply( effect );
    }
  }
 
  public void unapplyAdd( EffectFunction effectFunction ) {
    Pair< Parameter<Integer>, T > p =
        getTimepointAndValueOfEffect( effectFunction,
                                      effectFunction.method,
                                      effectFunction.method );
    if ( p != null && isAddApplied( effectFunction ) ) {
      remove( p.first, p.second );
    }
  }
  
  /**
   * @param t
   * @return a list of values from the list at t that are not in the list before
   *         t.
   */
  public List< T > valuesAddedAt( Parameter<Integer> t ) {
    List< T > listAtT = new ArrayList< T >( getValue( t ) );
    List< T > listBefore = getValue( getTimepointBefore( t ) );
    if ( Utils.valuesEqual( listAtT, listBefore ) || listAtT == null ) {
      return Utils.getEmptyList();
    }
    if ( !Utils.isNullOrEmpty( listBefore ) ) {
      listAtT.removeAll( listBefore );
    }
    return listAtT;
  }

  @Override
  public void detach( Parameter< ? > parameter ) {
    // remove values added at a Parameter<Integer> == parameter
    if ( parameter instanceof Parameter ) {
      if ( containsKey( parameter ) ) {
        List< T > added = valuesAddedAt( (Parameter<Integer>)parameter );
        remove( (Parameter<Integer>)parameter, added );
      }
      remove( (Parameter<Integer>)parameter ); // this should already be done
    }
    
    // remove list values that are (or have) the parameter
    if ( isEmpty() ) return;
    Parameter<Integer> t = firstEntry().getKey();
    // map contents may change, look up the next timepoint each time
    while ( t != null ) { 
      List< T > list = get( t );
      if ( list != null ) {
        List<T> detachList = new ArrayList< T >();
        for ( T v : list ) {
          if ( Utils.valuesEqual( v, parameter ) || 
               HasParameters.Helper.hasParameter( v, parameter, false, null,
                                                  true ) ) {
            detachList.add(v);
          }
        }
        remove( t, detachList );
      }
      t = getTimepointAfter( t );
    }
    //super.detach( parameter );
  }

  public static Method getAddMethod() {
    if (addMethod == null) {
      for (Method m : TimeVaryingList.class.getMethods()) {
        if (m.getName().equals("add")
            && m.getParameterTypes() != null
            && m.getParameterTypes().length == 2
            && m.getParameterTypes()[1] != List.class) {
          addMethod = m;
          break;
        }
      }
    }
    assert addMethod != null;
    return addMethod;
  }
  public static Method getAddWithListMethod() {
    if (addWithListMethod == null) {
      for (Method m : TimeVaryingList.class.getMethods()) {
        if (m.getName().equals("add")
            && m.getParameterTypes() != null
            && m.getParameterTypes().length == 2
            && m.getParameterTypes()[1] == List.class) {
          addWithListMethod = m;
          break;
        }
      }
    }
    assert addWithListMethod != null;
    return addWithListMethod;
  }

  public static Method getAddIfNotContainedMethod() {
    if (addIfNotContainedMethod == null) {
      for (Method m : TimeVaryingList.class.getMethods()) {
        if (m.getName().equals("addIfNotContained")
            && m.getParameterTypes() != null
            && m.getParameterTypes().length == 2 ) {
          addIfNotContainedMethod = m;
          break;
        }
      }
    }
    assert addIfNotContainedMethod != null;
    return addIfNotContainedMethod;
  }

  public static Method getAddIfNotContainedIfMethod() {
    if (addIfNotContainedIfMethod == null) {
      for (Method m : TimeVaryingList.class.getMethods()) {
        if (m.getName().equals("addIfNotContained")
            && m.getParameterTypes() != null
            && m.getParameterTypes().length == 3 ) {
          addIfNotContainedIfMethod = m;
          break;
        }
      }
    }
    assert addIfNotContainedIfMethod != null;
    return addIfNotContainedIfMethod;
  }
  
  public static Method getRemoveMethod() {
    if (removeMethod == null) {
      for (Method m : TimeVaryingList.class.getMethods()) {
        if (m.getName().equals("remove")
            && m.getParameterTypes() != null
            && m.getParameterTypes().length == 2
            && m.getParameterTypes()[1] != List.class) {
          removeMethod = m;
          break;
        }
      }
    }
    assert removeMethod != null;
    return removeMethod;
  }
  public static Method getRemoveWithListMethod() {
    if (removeWithListMethod == null) {
      for (Method m : TimeVaryingList.class.getMethods()) {
        if (m.getName().equals("remove")
            && m.getParameterTypes() != null
            && m.getParameterTypes().length == 2
            && m.getParameterTypes()[1] == List.class) {
          removeWithListMethod = m;
          break;
        }
      }
    }
    assert removeWithListMethod != null;
    return removeWithListMethod;
  }
  protected static Collection< Method > initEffectMethods() {
    // copy to avoid polluting the superclass's list
    effectMethods = new HashSet<Method>(TimeVaryingMap.initEffectMethods());
    Method m = getAddMethod();
    if ( m != null ) effectMethods.add( m );
    m = getAddWithListMethod();
    if ( m != null ) effectMethods.add( m );
    m = getAddIfNotContainedMethod();
    if ( m != null ) effectMethods.add( m );
    m = getAddIfNotContainedIfMethod();
    if ( m != null ) effectMethods.add( m );
    m = getRemoveMethod();
    if ( m != null ) effectMethods.add( m );
    m = getRemoveWithListMethod();
    if ( m != null ) effectMethods.add( m );
    return effectMethods;
  }

  // This looks the same as parent's getEffectMethods(), but it uses its own
  // effectMethods and initEffectMethods(). So, DO NOT DELETE.
  @Override
  public Collection< Method > getEffectMethods() {
    if ( effectMethods == null ) effectMethods = initEffectMethods();
    return effectMethods;
  }
  
  /**
   * @param args
   */
  public static void main( String[] args ) {
    TimeVaryingList< Integer > tvl = new TimeVaryingList< Integer >( 3 );
    Parameter<Integer> zero = new Parameter<Integer>(null, null, 0, tvl);
    Parameter<Integer> ten = new Parameter<Integer>(null, null, 10, tvl);
    Parameter<Integer> twelve1 = new Parameter<Integer>(null, null, 12, tvl);
    Parameter<Integer> twelve2 = new Parameter<Integer>(null, null, 12, tvl);
    Parameter<Integer> fourteen = new Parameter<Integer>(null, null, 14, tvl);
    int size = 0;

    System.out.println( tvl );

    tvl.add( ten, 10 );
    System.out.println( "add( ten, 10 ) = " + tvl );

    size = tvl.size(ten);
    Assert.assertTrue( size == 1 );
    size = tvl.size(zero);
    Assert.assertTrue( size == 0 );
    Assert.assertTrue( tvl.contains( ten, 10 ) );
    Assert.assertTrue( tvl.contains( new Parameter<Integer>(null, null, 10, tvl), 10 ) );
    Assert.assertTrue( tvl.contains( twelve1, 10 ) );
    
    tvl.add( twelve1, 12 );
    System.out.println( "tvl.add( twelve1, 12 ) = " + tvl );
    
    Assert.assertTrue( tvl.contains( twelve1, 10 ) );
    Assert.assertTrue( tvl.contains( twelve2, 12 ) );
    Assert.assertTrue( tvl.contains( fourteen, 12 ) );
    
    tvl.add( twelve1, 12 );
    System.out.println( "add( twelve1, 12 ) = " + tvl );
    
    Assert.assertTrue( tvl.contains( twelve1, 10 ) );
    
    tvl.add( twelve2, 12 );
    System.out.println( "add( twelve2, 12 ) = " + tvl );
    
    tvl.add( fourteen, 12 );
    System.out.println( "add( fourteen, 12 ) = " + tvl );
    
    tvl.addIfNotContained( new Parameter<Integer>(null, null, 8, tvl), 12 );
    System.out.println( "addIfNotContained( new Parameter<Integer>(8), 12 ) = " + tvl );
    
    tvl.addIfNotContained( twelve1, 12 );
    System.out.println( "addIfNotContained( twelve1, 12 ) = " + tvl );
    
    tvl.addIfNotContained( twelve2, 12 );
    System.out.println( "addIfNotContained( twelve2, 12 ) = " + tvl );
    
    tvl.addIfNotContained( new Parameter<Integer>(null, null, 12, tvl), 12 );
    System.out.println( "addIfNotContained( new Parameter<Integer>(12), 12 ) = " + tvl );
  }

}
