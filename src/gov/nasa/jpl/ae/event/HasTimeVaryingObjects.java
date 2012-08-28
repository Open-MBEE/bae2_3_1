package gov.nasa.jpl.ae.event;

import java.util.Set;

public interface HasTimeVaryingObjects {
  public Set< TimeVarying< ? > > getTimeVaryingObjects( boolean deep );
}
