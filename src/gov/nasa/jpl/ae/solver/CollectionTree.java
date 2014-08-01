package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.MoreToString;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

/**
 * The CollectionTree is meant to act as a collection for gathering objects of
 * certain types without adding them to a new container--just accessing them
 * from the collections they're already in.
 */
  public class CollectionTree<C> extends java.util.AbstractCollection<C> implements MoreToString {

    /**
     * Combines {@code Iterator} for an Iterable with indexing for an array. 
     *
     */
    public static class IterationState<T> implements java.util.Iterator<T>  {
      
      public T[] array;
      public java.util.Iterator<T> iter = null;
      public Integer index = null;

      /**
       * @param container
       * @param iter
       */
      public IterationState( java.util.Iterator< T > iter ) {
        super();
        this.iter = iter;
      }
      /**
       * @param iter
       * @param index
       */
      public IterationState( T[] arr,
                             Integer index ) {
        super();
        this.array = arr;
        this.index = index;
      }

      /**
       * @param iter
       * @param index
       */
      public IterationState( T[] arr ) {
        this(arr, 0);
      }

      /**
       * This is a convenience method to avoid cast warnings.
       * @param iterable
       * @return
       */
      public static <TT> CollectionTree.IterationState<TT> getIterationState( Iterable<TT> iterable ) {
        return new CollectionTree.IterationState<TT>( iterable.iterator() );
      }

      public static <TT> IterationState< TT > getIterationState( java.util.Iterator< TT > result ) {
        return new CollectionTree.IterationState<TT>( result );
      }

      /* (non-Javadoc)
       * @see java.util.Iterator#hasNext()
       */
      @Override
      public boolean hasNext() {
        if ( iter != null ) return iter.hasNext();
        if ( array != null ) return ( index < array.length );
        // Throw exception?
        return false;
      }
      /* (non-Javadoc)
       * @see java.util.Iterator#next()
       */
      @Override
      public T next() {
        if ( iter != null ) return iter.next();
        if ( array != null ) return array[index++];
        // Throw exception?
        return null;
      }
      /* (non-Javadoc)
       * @see java.util.Iterator#remove()
       */
      @Override
      public void remove() {
        if ( iter != null ) iter.remove();
        else if ( array != null ) {
          if ( index >= 0 && index < array.length ) {
            for ( int i = index; i < array.length - 1; ++i ) {
              array[i] = array[i+1];
            }
          } else {
            // Throw exception?
          }
        }
      }
    }

  /**
   * The {@code CollectionTree Iterator} walks through the member {@sources} as
   * a tree checking each element to see if it is an instance of one of the
   * target {@code types} and if it branches into an array or {@code Iterable},
   * for which it will recursively check sub-elements.
   */
    public class Iterator implements java.util.Iterator<C> {

      public Set<?> seen = null;//new HashSet<Object>();
      
      public Stack< CollectionTree.IterationState< ? > > stack =
          new Stack< CollectionTree.IterationState< ? > >();
      Object lastObj = null;
      
      public Iterator() {
        stack.push( new CollectionTree.IterationState< Object >( sources.iterator() ) );
      }
      public Iterator( Set<?> seen ) {
        this.seen = seen;
        stack.push( new CollectionTree.IterationState< Object >( sources.iterator() ) );
      }
      
      /* (non-Javadoc)
       * @see java.util.Iterator#hasNext()
       */
      @Override
      public boolean hasNext() {
        // Remove iterator from stack if iterator is done
        while ( !stack.isEmpty() && !stack.peek().hasNext() ) {
          stack.pop();
        }
        return !stack.isEmpty();
      }

      public boolean isTypeToCollect( Object o ) {
        for ( Class< ? > cls : types ) {
          if ( cls.isInstance( o ) ) {
            return true;
          }
        }
        return false;
      }
      
      public boolean addToStack( Object o ) {
        boolean added = false;
        for ( Entry< Class< ? >, Method > branch : branchMethods.entrySet() ) {
          if ( branch.getKey().isInstance( o ) ) {
            Method m = branch.getValue();
            Object result = runBranchMethod( m, o );
            if ( result instanceof Iterable ) {
              stack.push( IterationState.getIterationState( (Iterable<?>)result ) );
              added = true;
            } else if ( result instanceof java.util.Iterator ) {
              stack.push( IterationState.getIterationState( (java.util.Iterator<?>)result ) );
              added = true;
            }
          }
        }
        return added;
      }
      
    /*
     * (non-Javadoc)
     * 
     * @see java.util.Iterator#next()
     */
    @Override
    public C next() {
      Object nextObj = null;
      while ( hasNext() && nextObj == null ) {
        Object o = stack.peek().next();

        // Don't revisit the same object -- could result in infinite recursion
        Pair< Boolean, Set< Object > > p = Utils.seen( o, true, (Set<Object>)seen );
        boolean alreadyVisited = p.first;
        if ( alreadyVisited && !allowDuplicates ) continue;

        // See if object is the right type
        if ( isTypeToCollect( o ) ) nextObj = o;

        // Add children to the stack
        if ( !alreadyVisited ) addToStack( o );

        // Remove iterator from stack if iterator is done
        if ( !stack.peek().hasNext() ) {
          stack.pop();
        }
      }
      return (C)nextObj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Iterator#remove()
     */
    @Override
    public void remove() {
      if ( !stack.isEmpty() ) stack.peek().remove();
      while ( !stack.peek().hasNext() ) {
        stack.pop();
      }
    }
  }

  protected static Method asListMethod = null;

  protected static Method entrySetMethod = null;

  protected static Method entryToListMethod = null;

  protected static Method pairToListMethod = null;

  
  /**
   * The sources are the top-level constructs that may be elements of the
   * collection and may be containers of other elements.
   */
  protected Collection< Object > sources = new LinkedHashSet< Object >();

  /**
   * Objects whose types are in {@code types} are considered part of the
   * collection.
   */
  protected Collection< Class< ? > > types = new HashSet< Class< ? >>();

  /**
   * {@code branchMethods} specify how to get sub-collections, collections
   * within elements of a collection. For example, if a source is a
   * {@code HasConstraints}, it's elements will be accessed by the branch method
   * entry,
   * {@code (HasConstraints.class=HasConstraints.getConstraintCollection())}.
   */
  protected Map< Class< ? >, Method > branchMethods = 
      new HashMap< Class< ? >, Method >();    
  

  protected boolean allowDuplicates = false;

  private Set< ? > seen = null;

  public CollectionTree( Object... objects ) {
    sources.addAll( Arrays.asList( objects ) );
    init();
  }

//  public CollectionTree( Collection< ? > c ) {
//    sources.add( c );
//    init();
//  }

  public class IterableCollection< E > extends AbstractCollection< E > {
    Iterable< E > iterable = null;
    java.util.Iterator< E > iterator = null;

    public IterableCollection( Iterable< E > iterable ) {
      this.iterable = iterable;
    }
    public IterableCollection( java.util.Iterator< E > iterator ) {
      this.iterator = iterator;
    }

    @Override
    public java.util.Iterator< E > iterator() {
      if ( iterable != null )
        return iterable.iterator();
      return iterator;
    }

    @Override
    public int size() {
      int count = 0;
      java.util.Iterator< E > i = iterator();
      while ( i.hasNext() ) {
        i.next();
        count++;
      }
      return count;
    }
  }

  public CollectionTree( Iterable< ? > c ) {
    sources.add( c );
    init();
  }

  protected static Method getAsListMethod() {
    if ( asListMethod == null ) {
      Class< ? > c = Arrays.class;
      try {
        asListMethod = c.getMethod( "asList", new Class< ? >[] { Object[].class } );
      } catch ( SecurityException e ) {
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        e.printStackTrace();
      }
    }
    return asListMethod;
  }
  
  protected static Method getEntrySetMethod() {
    if ( entrySetMethod  == null ) {
      Class< ? > c = Map.class;
      try {
        entrySetMethod = c.getMethod( "entrySet" );
      } catch ( SecurityException e ) {
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        e.printStackTrace();
      }
    }
    return entrySetMethod;
  }
  
  public static List< Object > entryToList( Entry< ?, ? > e ) {
    List< Object > l = new ArrayList< Object >( 2 );
    l.add( e.getKey() );
    l.add( e.getValue() );
    return l;
  }
  
  protected static Method getEntryToListMethod() {
    if ( entryToListMethod   == null ) {
      Class< ? > c = CollectionTree.class;
      try {
        entryToListMethod =
            c.getMethod( "entryToList", new Class< ? >[] { Entry.class } );
      } catch ( SecurityException e ) {
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        e.printStackTrace();
      }
    }
    return entryToListMethod;
  }

  public static List< Object > pairToList( Pair< ?, ? > e ) {
    List< Object > l = new ArrayList< Object >( 2 );
    l.add( e.first );
    l.add( e.second );
    return l;
  }
  
  protected static Method getPairToListMethod() {
    if ( pairToListMethod == null ) {
      Class< ? > c = CollectionTree.class;
      try {
        pairToListMethod =
            c.getMethod( "pairToList", new Class< ? >[] { Pair.class } );
      } catch ( SecurityException e ) {
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        e.printStackTrace();
      }
    }
    return pairToListMethod;
  }

  /**
   * @return the sources
   */
  public Collection< Object > getSources() {
    return sources;
  }

  /**
   * @param sources the sources to set
   */
  public void setSources( Collection< Object > sources ) {
    this.sources = sources;
  }

  /**
   * @return the types
   */
  public Collection< Class< ? >> getTypes() {
    return types;
  }

  /**
   * @param types the types to set
   */
  public void setTypes( Collection< Class< ? >> types ) {
    this.types = types;
  }

  /**
   * @return the branchMethods
   */
  public Map< Class< ? >, Method > getBranchMethods() {
    return branchMethods;
  }

  /**
   * @param branchMethods the branchMethods to set
   */
  public void setBranchMethods( Map< Class< ? >, Method > branchMethods ) {
    this.branchMethods = branchMethods;
  }

  /**
   * @return the allowDuplicates
   */
  public boolean isAllowDuplicates() {
    return allowDuplicates;
  }

  /**
   * @param allowDuplicates the allowDuplicates to set
   */
  public void setAllowDuplicates( boolean allowDuplicates ) {
    this.allowDuplicates = allowDuplicates;
  }

  private void init() {
    // Treat array as a List
    Method m = getAsListMethod();
    Class< ? > c = Object[].class;
    branchMethods.put( c, m );
    // Treat Map as an Entry set
    c = Map.class;
    m = getEntrySetMethod();
    branchMethods.put( c, m );
    // Make an Entry a List
    c = Entry.class;
    m = getEntryToListMethod();
    branchMethods.put( c, m );
    // Make a Pair a List
    c = Pair.class;
    m = getPairToListMethod();
    branchMethods.put( c, m );
//    c = Iterable.class;
//    m = null;
//    branchMethods.put( c, m );
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.AbstractCollection#size()
   */
  @Override
  public int size() {
    java.util.Iterator<C> i = iterator();
    int size = 0;
    while (i.hasNext()) {
      ++size;
      i.next();
    }
    return size;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.AbstractCollection#isEmpty()
   */
  @Override
  public boolean isEmpty() {
    return !iterator().hasNext();
  }

  protected static Object runBranchMethod( Method m, Object o ) {
    Object result = o; // default if method is null
    if ( m != null ) {
      result = null;
      Object[] args = null;
      m.setAccessible( true );
      boolean isStatic = Modifier.isStatic( m.getModifiers() );
      if ( ( m.getParameterTypes().length == 1 && ( isStatic || !m.isVarArgs() ) )
           || ( m.getParameterTypes().length == 2 && m.isVarArgs() ) ) {
        args = new Object[] { o };
      }
      ClassUtils.runMethod( true, ( isStatic ? null : o ), m, args );
    }
    return result;
  }


  /*
   * (non-Javadoc)
   * 
   * @see java.util.AbstractCollection#contains(java.lang.Object)
   */
  @Override
  public boolean contains( Object o ) {
    boolean doesContain = false;
    // set up iterator to match any type
    Collection< Class< ? > > typesBackup = types;
    types = new ArrayList<Class<?>>();
    types.add( Object.class );
    
    java.util.Iterator<?> ii = iterator();
    Iterator i = (Iterator)ii;
    while (i.hasNext()) {
      Object nextObj = i.next();
      if ( nextObj == o || nextObj.equals( o ) ) {
        doesContain = true;
        break;
      }
      Collection<?> coll = null;
      if ( nextObj instanceof CollectionTree ) {
        coll = (Collection< ? >)sources;
      } else if ( nextObj instanceof Collection ) {
        coll = (Collection< ? >)nextObj;
      } else if ( nextObj instanceof Iterable ) {
        coll = new IterableCollection<Object>( (Iterable<Object>)nextObj );
      } else if ( nextObj instanceof java.util.Iterator ) {
        coll = new IterableCollection<Object>( (java.util.Iterator<Object>)nextObj );
      }
      if ( coll.contains( o ) ) {
        doesContain = true;
        break;
      }
    }
    // restore types
    types = typesBackup;
    return doesContain;
  }

    /* (non-Javadoc)
     * @see java.util.AbstractCollection#iterator()
     */
    @Override
    public java.util.Iterator< C > iterator() {
      return (java.util.Iterator< C >)this.new Iterator( getSeen()  );
    }

    /* (non-Javadoc)
     * @see java.util.AbstractCollection#add(java.lang.Object)
     */
    @Override
    public boolean add( Object e ) {
      return sources.add( e );
    }

  /*
   * TODO -- this could look more like contains. Instead of removing these from
   * the contained collections, a removedList could be kept. (non-Javadoc)
   * 
   * @see java.util.AbstractCollection#remove(java.lang.Object)
   */
    @Override
    public boolean remove( Object o ) {
      if ( o == sources || o.equals( sources ) ) {
        sources = new LinkedHashSet<Object>();
        return true;
      }
      if ( sources.contains( o ) ) {
        return sources.remove( o );
      }
      for ( Object container : sources ) {
        if ( container instanceof HasConstraints ) {
          if ( ( (HasConstraints)container ).getConstraintCollection(false, null)
                                            .remove( o ) ) {
            return true;
          }
        }
        CollectionTree.IterationState< ? > i = null;
        if ( container.getClass().isArray() ) {
          i = new CollectionTree.IterationState< Object >( (Object[])container, 0 );
        } else if ( container instanceof Iterable ) {
          i = IterationState.getIterationState( ( (Iterable< ? >)container ) );
        } else if ( container instanceof java.util.Iterator ) {
          i = IterationState.getIterationState( ( (java.util.Iterator< ? >)container ) );
        }
        if ( i != null ) {
          while ( i.hasNext() ) {
            Object n = i.next();
            if ( n == o || ( n != null && n.equals( o ) ) ) {
              i.remove();
              return true;
            }
          }
        }
      }
      return false;
    }

    /* (non-Javadoc)
     * @see java.util.AbstractCollection#containsAll(java.util.Collection)
     */
    @Override
    public boolean containsAll( Collection< ? > c ) {
      return super.containsAll( c );
      // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see java.util.AbstractCollection#addAll(java.util.Collection)
     */
    @Override
    public boolean addAll( Collection< ? extends C > c ) {
      return super.addAll( c );
    }

    /* (non-Javadoc)
     * @see java.util.AbstractCollection#removeAll(java.util.Collection)
     */
    @Override
    public boolean removeAll( Collection< ? > c ) {
      return super.removeAll( c );
    }

  /*
   * TODO -- This probably shouldn't remove items from internal collections.  See {@link remove()}.
   * 
   * (non-Javadoc)
   * 
   * @see java.util.AbstractCollection#retainAll(java.util.Collection)
   */
    @Override
    public boolean retainAll( Collection< ? > collection ) {
      boolean modified = false;
      java.util.Iterator<Object> e = sources.iterator();
      while (e.hasNext()) {
        Object nextObj = e.next();
        boolean containsOne = false;
        for ( Object c : collection ) {
          CollectionTree ct = new CollectionTree( nextObj );
          if ( ct.contains( e.next() ) ) {
            containsOne = true;
            break;
          }
        }
        if ( !containsOne ) {
          e.remove();
          modified = true;
        } else if ( nextObj instanceof HasConstraints ) {
          if ( ((HasConstraints)nextObj).getConstraintCollection(false, null).retainAll( collection ) ) {
            modified = true;
          }
        }
      }
      return modified;
    }

    /* (non-Javadoc)
     * @see java.util.AbstractCollection#clear()
     */
    @Override
    public void clear() {
      sources.clear();
    }
    
    @Override
    public String toString( boolean withHash, boolean deep, Set< Object > seen ) {
      return super.toString();
    }

    @Override
    public String toString( boolean withHash, boolean deep, Set< Object > seen,
                            Map< String, Object > otherOptions ) {
      return super.toString();
    }

    @Override
    public String toShortString() {
      return super.toString();
    }

    
    public static void main(String[] args) {
      System.out.println("CollectionTree test");
      CollectionTree ct = new CollectionTree();
      ct.types.add( Double.class );
      ct.add( (Double)3.14 );
      ct.add( "pi" );
      HashSet<Object> h = new HashSet<Object>();
      ct.add(h);
      h.add( "e" );
      h.add( 2.71 );
      h.add( (double)9.8 );
      TreeMap<String,Object> tm = new TreeMap< String, Object >();
      h.add( tm );
      tm.put( "foo", "bar" );
      tm.put( "gravity", new Float(9.8) );
      java.util.Iterator i = ct.iterator();
      System.out.println("ct = " + ct );
    }

    /**
     * @return the seen
     */
    public Set< ? > getSeen() {
      return seen;
    }

    /**
     * @param seen the seen to set
     */
    public void setSeen( Set< ? > seen ) {
      this.seen = seen;
    }

  }