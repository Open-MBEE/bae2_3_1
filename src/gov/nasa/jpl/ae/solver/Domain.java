package gov.nasa.jpl.ae.solver;

public interface Domain< T > extends Cloneable {

  public Domain< T > clone();

  public long size();

  public boolean contains( T t );

  public T pickRandomValue();
  
  public boolean isInfinite();
  
  public Domain< T > getDefaultDomain();
  public void setDefaultDomain( Domain< T > domain );
  
  public Class< ? > getType();

  public Class< ? > getPrimitiveType();
  
  public void restrictToValue( T v );
}
