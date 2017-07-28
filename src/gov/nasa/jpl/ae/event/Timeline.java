package gov.nasa.jpl.ae.event;

import java.lang.reflect.InvocationTargetException;
import java.util.TreeMap;

public class Timeline<V> extends TimeVaryingMap<V> {
  
  
  protected static TreeMap<Object, Parameter<Long>> objectToParam = new TreeMap<Object, Parameter<Long>>();

  public Timeline() {
    super();
  }
  
  
  public Timeline(String name) {
    super(name);
  }
  
  public Timeline(String name, String fileName) {
    super(name, fileName);
  }
  
  
  public Timeline(String name, String fileName, V defaultValue) {
    super(name, fileName, defaultValue);
  }
  
  public Timeline(V initialValue) {
    this();
    this.setValue( initialValue );
  }
  
  public boolean setVal(Parameter<Long> t, V value) {
    super.setValue( t, value );
    return true;
  }
  
  
  public boolean setVal(Integer t, V value) {
    if (objectToParam.containsKey( t )) {
      super.setValue( objectToParam.get( t ), value );
    } else {
      Long l = null;
      try {
        l = Expression.evaluate( t, Long.class, false );
      } catch ( ClassCastException e ) {
      } catch ( IllegalAccessException e ) {
      } catch ( InvocationTargetException e ) {
      } catch ( InstantiationException e ) {
      }
      if (l == null) {
        return false;
      }
      Parameter<Long> param = new SimpleTimepoint( l);
      objectToParam.put( t, param );
      super.setValue( param, value );
    }
    
    return true;
  }
  
  
  public boolean setVal( V value) {
    super.setValue( value );
    return true;
  }
  
  public V getVal(Parameter<Long> t) {
    return this.getValue(t );
  }
  
  public V getVal(Integer t) {
    if (objectToParam.containsKey( t )) {
      Parameter<Long> param = objectToParam.get( t );
      return this.getVal( param );
    } 
    return null;
  }
  
  public boolean addVal(Number n) {
    this.add( n );
    return true;
  }
  
  public boolean addVal(Number n , Parameter<Long> fromKey) {
    this.add( n, fromKey );
    return true;
  }
  
  public boolean addVal(Number n, Parameter<Long> fromKey, Parameter<Long> toKey) {
   this.add( n, fromKey, toKey );
   return true;
  }
  
  public boolean addVal(Number n, Integer fromKey) {
    if (objectToParam.containsKey( fromKey )) {
      Parameter<Long> param = objectToParam.get( fromKey );
      return this.addVal(n,  param );
    } 
    return false;
  }
  
  public boolean addVal(Number n, Integer fromKey, Integer toKey) {
    if (objectToParam.containsKey( fromKey )) {
      Parameter<Long> paramFrom = objectToParam.get( fromKey );
      if (objectToParam.containsKey( toKey )) {
        Parameter<Long> paramTo = objectToParam.get( toKey );
        return this.addVal(n, paramFrom, paramTo );
      }
     
    } 
    return false;
  }
  
 
  
  

}
