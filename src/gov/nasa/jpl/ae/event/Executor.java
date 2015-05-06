package gov.nasa.jpl.ae.event;

public interface Executor {
  public void execute( double time, String name,
                       String shortClassName, String longClassName,
                       String value );
}
