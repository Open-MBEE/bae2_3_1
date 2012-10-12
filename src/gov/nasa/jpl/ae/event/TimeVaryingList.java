/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

/**
 *
 */
public class TimeVaryingList< T > extends TimeVaryingMap< List< T > > {

  private static final long serialVersionUID = 5136649915412969932L;
  
  public long maxSize = Long.MAX_VALUE;
  
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
    this( "" );
    this.maxSize = maxSize;
    // TODO Auto-generated constructor stub
  }

  /**
   * @param name
   * @param defaultValue
   */
  public TimeVaryingList( String name, List< T > defaultValue ) {
    super( name, defaultValue );
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

  public boolean add( Timepoint t, T value ) {
    List< T > list = get( t );
    boolean changed = false;
    if ( maxSize == 0 || ( list != null && list.size() >= maxSize ) ) {
      return false;
    }
    if ( list == null ) {
      list = getValue( t.getValue() );
      if ( list == null ) {
        list = new ArrayList<T>();
      } else {
        list = new ArrayList<T>( list );
      }
      setValue( t, list );
      changed = true;
    }
    if ( list.add( value ) ) changed = true;
    return changed;
  }
  
  public boolean addIfNotContained( Timepoint t, T value ) {
    if ( !contains( t, value ) ) {
      return add( t, value );
    }
    return false;
  }
  
  public boolean contains( Timepoint t, T value ) {
    List< T > list = getValue( t );
    if ( list != null ) {
      if ( list.contains( value ) ) {
        return true;
      }
    }
    return false;
  }

  public T get( Timepoint t, int index ) {
    List< T > list = getValue( t );
    if ( list == null || list.size() <= index ) return null;
    return list.get( index );
  }
  
  public T lastElement( Timepoint t ) {
    return get( t, size() - 1 );
  }

  public int size( Timepoint t ) {
    List< T > list = getValue( t );
    if ( list != null ) {
      return list.size();
    }
    return 0;
  }
  
  /**
   * @param args
   */
  public static void main( String[] args ) {
    TimeVaryingList< Integer > tvl = new TimeVaryingList< Integer >( 3 );
    Timepoint zero = new Timepoint(0);
    Timepoint ten = new Timepoint(10);
    Timepoint twelve1 = new Timepoint(12);
    Timepoint twelve2 = new Timepoint(12);
    Timepoint fourteen = new Timepoint(14);
    int size = 0;

    System.out.println( tvl );

    tvl.add( ten, 10 );
    System.out.println( "add( ten, 10 ) = " + tvl );

    size = tvl.size(ten);
    Assert.assertTrue( size == 1 );
    size = tvl.size(zero);
    Assert.assertTrue( size == 0 );
    Assert.assertTrue( tvl.contains( ten, 10 ) );
    Assert.assertTrue( tvl.contains( new Timepoint(10), 10 ) );
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
    
    tvl.addIfNotContained( new Timepoint(8), 12 );
    System.out.println( "addIfNotContained( new Timepoint(8), 12 ) = " + tvl );
    
    tvl.addIfNotContained( twelve1, 12 );
    System.out.println( "addIfNotContained( twelve1, 12 ) = " + tvl );
    
    tvl.addIfNotContained( twelve2, 12 );
    System.out.println( "addIfNotContained( twelve2, 12 ) = " + tvl );
    
    tvl.addIfNotContained( new Timepoint(12), 12 );
    System.out.println( "addIfNotContained( new Timepoint(12), 12 ) = " + tvl );
  }

}
