/**
 * 
 */
package gov.nasa.jpl.ae.event;

import java.util.Set;

import gov.nasa.jpl.mbee.util.HasId;
import gov.nasa.jpl.mbee.util.HasName;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;

public interface HasOwner {
  Object getOwner();
  void setOwner( Object owner );

  /**
   * Get the name preceded by parent names, separated by '.'
   * 
   * @param seen
   *          a list of objects that have already been visited and that are to
   *          be used to avoid infinite recursion
   * @return
   */
  public String getQualifiedName( Set< Object > seen );

  /**
   * Get the ID preceded by parent IDs, separated by '.'
   * 
   * @param seen
   *          a list of objects that have already been visited and that are to
   *          be used to avoid infinite recursion
   * @return
   */
  String getQualifiedId( Set< Object > seen );
  
  public static class Helper {
    
    public static String getQualifiedName( Object o, Set<Object> seen ) {
      return getQualifiedNameOrId( o, seen, true );
    }
    
    public static String getQualifiedId( Object o, Set<Object> seen ) {
      return getQualifiedNameOrId( o, seen, false );
    }
    
    private static String getQualifiedNameOrId( Object o, Set<Object> seen, boolean getNameOrId ) {
      Pair< Boolean, Set< Object > > pair = Utils.seen( o, true, seen );
      if ( pair.first ) return "";
      seen = pair.second;

      StringBuffer sb = new StringBuffer();
      
      Object ownr = null;
      String nameOrId = null;

      if ( o instanceof HasOwner ) {
        ownr = ( (HasOwner)o ).getOwner();
      }

      if ( ownr != null ) {
        String ownerName = getQualifiedNameOrId( ownr, seen, getNameOrId );
        if ( !Utils.isNullOrEmpty( ownerName ) ) {
          sb.append( ownerName );
          sb.append(".");
        }
      }

      boolean hasNameOrId = ( getNameOrId && o instanceof HasName ) || ( !getNameOrId && o instanceof HasId );
      if ( hasNameOrId ) {
        Object n = getNameOrId ? ( (HasName<?>)o ).getName() : ( (HasId<?>)o ).getId();
        // Use an empty name. This will result in multiple periods between
        // names and names ending with a period. Not sure whether "null" or "<no
        // name>" would be better.  The caller can post-process to add these.
        nameOrId = n == null ? "" : "" + n;
      }
      
      sb.append( nameOrId );
      
      return sb.toString();
    }
//    public static void main( String[] args ) {
//      
//    }
  }
}
