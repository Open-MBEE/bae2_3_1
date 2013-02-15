package gov.nasa.jpl.ae.solver;

public interface Domain< T > extends Cloneable, HasId {

  public Domain< T > clone();

  public long size();

  public boolean isEmpty();
  
  public boolean contains( T t );

  public T pickRandomValue();
  public T pickRandomValueNotEqual( T t );
  
  public boolean isInfinite();
  
  public Domain< T > getDefaultDomain();
  public void setDefaultDomain( Domain< T > domain );
  
  public Class< ? > getType();

  public Class< ? > getPrimitiveType();
  
  public void restrictToValue( T v );
}
