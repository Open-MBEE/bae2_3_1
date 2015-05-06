/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class TimeVaryingPlottableMaps< V > extends TimeVaryingMaps< V > implements Plottable {

  private static final long serialVersionUID = 8741383930429073761L;

  protected boolean dataProjected = false;

  /**
   * @param name
   * @param cls
   */
  public TimeVaryingPlottableMaps( String name, Class< V > cls ) {
    super( name, cls );
    // TODO Auto-generated constructor stub
  }

  /**
   * @param name
   * @param fileNames
   * @param cls
   */
  public TimeVaryingPlottableMaps( String name, Map<String, Number> fileNames,
                                   Class< V > cls ) {
    super( name, fileNames, cls );
  }

  /**
   * @param name
   */
  public TimeVaryingPlottableMaps( String name, Class< V > cls, boolean projected ) {
    this( name, cls );
    dataProjected = projected;
  }

  /**
   * @param name
   */
  public TimeVaryingPlottableMaps( String name, Map<String, Number> fileNames,
                                   Class< V > cls, boolean projected ) {
    this( name, fileNames, cls );
    dataProjected = projected;
  }

  @Override
  public boolean okToSample() {
    return true;
  }

  @Override
  public boolean isProjection() {
    return dataProjected;
  }

  public void setProjection( boolean b ) {
    dataProjected = b;
  }
  
  @Override
  public void fromString( String s, Class< V > cls ) {
    // skip over "plottable" and owner name
    Pattern p = Pattern.compile( "\\s*plottable [^{]*\\s*" );
    Matcher matcher = p.matcher( s );
    int end = 0;
    if ( matcher.find() ) {
      end = matcher.end();
    }
    super.fromString( s.substring( end ), cls );
  }

  @Override
  public String toString( boolean withHash, boolean deep, Set< Object > seen,
                          Map< String, Object > otherOptions ) {
    StringBuffer sb = new StringBuffer();
    sb.append( "plottable " );
    if ( getOwner() != null && getOwner() instanceof ParameterListener ) {
      sb.append( ( (ParameterListener)getOwner() ).getName() + " " );
    }
    sb.append( super.toString( withHash, deep, seen, otherOptions ) );
    return sb.toString();
  }

}
