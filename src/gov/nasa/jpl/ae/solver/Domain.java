package gov.nasa.jpl.ae.solver;

import gov.nasa.jpl.mbee.util.HasId;
import gov.nasa.jpl.mbee.util.Wraps;

public interface Domain< T > extends Cloneable, HasId<Integer>, Wraps< T > {

  public Domain< T > clone();

  public int size();

  public boolean isEmpty();

  public boolean contains( T t );

  public T pickRandomValue();
  public T pickRandomValueNotEqual( T t );

  public boolean isInfinite();

  public boolean isNullInDomain();

  public Domain< T > getDefaultDomain();
  public void setDefaultDomain( Domain< T > domain );

  public void restrictToValue( T v );

  public <TT> void restrictTo( Domain< TT > domain );
}
