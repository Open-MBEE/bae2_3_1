package gov.nasa.jpl.ae.magicdrawPlugin.modelQuery;

import gov.nasa.jpl.ae.util.ClassUtils;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.Vector;

//import javax.measure.quantity.Duration;
//import javax.measure.unit.SI;

//import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.impl.EClassifierImpl;
import org.eclipse.emf.ecore.impl.ENamedElementImpl;
//import org.jscience.physics.amount.Amount;
import org.osgi.framework.Bundle;

public final class EmfUtils {

  public static String spewIndentCharacters = "-> ";
  public static String spewObjectPrefix = "* * * * *";
  public static String spewObjectSuffix = spewObjectPrefix;

  public static Integer parseInt(String intStr) {
    try {
      int i = Integer.parseInt(intStr);
      return i;
    } catch (Exception e) {
      return null;
    }
  }

  public static boolean isInt(String intStr) {
    try {
      int i = Integer.parseInt(intStr);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // DateUtils.contains() is wrong. Defining own.
  public static boolean contains(Date sb, Date eb, Date s, Date e) {
    return contains(sb, eb, s, e, true, true);
  }

  /**
   * Returns true if the start (s) and end (e) dates are contained within the
   * start (sb) and end (eb) bounds. A null value of the start or end bounds
   * indicates the container is boundless.
   * 
   * @param sb
   * @param eb
   * @param s
   * @param e
   * @return whether [sb,eb] contains [s,e].
   */
  public static boolean contains(Date sb, Date eb, Date s, Date e,
      boolean includeStart, boolean includeEnd) {
    // LogUtil.logger().setLevel( Level.ALL );
    if (sb == null && eb == null)
      return true;
    if (sb == null) {
      if (e == null)
        return false;
      int compareEnds = e.compareTo(eb);
      return (compareEnds < 0 || (includeEnd && compareEnds == 0));
    }
    if (eb == null) {
      if (s == null)
        return false;
      int compareStarts = s.compareTo(sb);
      return (compareStarts > 0 || (includeStart && compareStarts == 0));
    }
    if (s == null || e == null)
      return false;
    int compareEnds = e.compareTo(eb);
    int compareStarts = s.compareTo(sb);
    boolean c = // s.after(sb) && e.before(eb);
    (compareEnds < 0 || (includeEnd && compareEnds == 0))
        && (compareStarts > 0 || (includeStart && compareStarts == 0));
    // LogUtil.debug( "contains(" + sb + ", " + eb + ", " + s + ", " + e +
    // ") = " + c );
    // if ( sb != null && eb != null && s != null && e != null ) {
    // LogUtil.debug( "contains(" + ( sb.getTime() / 1000 ) + "s, "
    // + ( eb.getTime() / 1000 ) + "s, " + ( s.getTime() / 1000 )
    // + "s, " + ( e.getTime() / 1000 ) + "s) = " + c );
    // }
    return c;
  }

  public static String toIntString(long timeOrDuration) {
    if (timeOrDuration <= -1073741823)
      return "-infinity";
    if (timeOrDuration >= 1073741823)
      return "infinity";
    return String.valueOf(timeOrDuration);
  }

//  public static long toSeconds(Amount<Duration> duration) {
//    if (duration == null) {
//      LogUtil.error("Cannot convert null duration!");
//      return 0;
//    }
//    return duration.longValue(SI.SECOND);
//  }

  public static String toTimeString(Date d, String format) {
    if (d != null) {
      return toTimeString(d.getTime(), format);
    } else {
      Debug.error("Cannot convert null Date");
      return null;
    }
  }

  public static String toTimeString(long millis, String format) {
    if (format == null)
      return null;
    Calendar cal = Calendar.getInstance();
    cal.setTimeZone(TimeZone.getTimeZone("GMT"));
    cal.setTimeInMillis(millis);
    String timeString = new SimpleDateFormat(format).format(cal.getTime());
    return timeString;
  }

  public static class QuoteMaster {
    Vector<Boolean> inSingleQuotes = null;
    Vector<Boolean> inDoubleQuotes = null;
    String string;

    public QuoteMaster(String string) {
      this.string = string;
      if (Utils.isNullOrEmpty(string))
        return;
      inSingleQuotes = new Vector<Boolean>(string.length());
      inDoubleQuotes = new Vector<Boolean>(string.length());
      boolean insideSingleQuotes = false;
      boolean insideDoubleQuotes = false;
      boolean outerSingle = false;
      boolean outerDouble = false;
      for (int i = 0; i < string.length(); ++i) {
        if (string.charAt(i) == '"') {
          insideDoubleQuotes = !insideDoubleQuotes;
          if (!insideDoubleQuotes) {
            if (outerDouble) {
              outerDouble = false;
              insideSingleQuotes = false;
              assert !outerSingle;
            }
          } else {
            assert !outerDouble;
            if (!outerSingle) {
              outerDouble = true;
            }
          }
        } else if (string.charAt(i) == '\'') {
          insideSingleQuotes = !insideSingleQuotes;
          if (!insideSingleQuotes) {
            if (outerSingle) {
              outerSingle = false;
              insideDoubleQuotes = false;
              assert !outerDouble;
            }
          } else {
            assert !outerSingle;
            if (!outerDouble) {
              outerSingle = true;
            }
          }
        }
        inSingleQuotes.add(insideSingleQuotes);
        inDoubleQuotes.add(insideDoubleQuotes);
      }
    }

    public String makeSingleQuotesDouble() {
      StringBuilder newString = new StringBuilder(string);
      for (int i = 0; i < string.length(); ++i) {
        if (string.charAt(i) == '\'') {
          if (!inDoubleQuotes.get(i)) {
            newString.setCharAt(i, '"');
          }
        }
      }
      return newString.toString();
    }

    public boolean isInQuotes(int pos) {
      if (inSingleQuotes == null)
        return false; // not initialized properly
      return inSingleQuotes.get(pos) || inDoubleQuotes.get(pos);
    }
  }

  public static String writeNameAndTypeOfEObject(EObject o, String indent) {
    StringBuffer sb = new StringBuffer();
    sb.append(indent + spewObjectPrefix + "\n");
    sb.append(indent + "EClass: " + o.eClass() + "\n");
    if (o instanceof ENamedElement) {
      sb.append(indent + "name: " + ((ENamedElement) o).getName() + "\n");
    }
    if (o instanceof ETypedElement) {
      sb.append(indent + "type: " + ((ETypedElement) o).getEType() + "\n");
    }
    sb.append(indent + spewObjectSuffix + "\n");
    return sb.toString();
  }

  public static String spewObject(Object o, String indent) {
    StringBuffer sb = new StringBuffer();
    sb.append(indent + spewObjectPrefix + "\n");
    Class<?> c = o.getClass();
    Method[] methods = c.getMethods();
    for (Method m : methods) {
      if (m.getReturnType() == void.class || m.getReturnType() == null
          || m.getName().startsWith("wait")
          || m.getName().startsWith("notify")
          || m.getName().startsWith("remove")
          || m.getName().startsWith("delete")) {
        continue;
      }
      if (m.getParameterTypes().length == 0) {
          sb.append(indent + m.getDeclaringClass() + ", "
              + m.toGenericString() + " --> "
              + ClassUtils.runMethod(true, o, m).second + "\n");
      }
    }
    sb.append(indent + spewObjectSuffix + "\n");
    return sb.toString();
    // System.out.println( "EObject.eAllContents()=" + o.eAllContents() +
    // "\n"
    // );
  }

  public static String spewContents(EObject def, int thisLevel, int maxDepth,
      boolean justNameType) {
    StringBuffer sb = new StringBuffer();
    StringBuffer indent = new StringBuffer();
    for (int i = 0; i < thisLevel; ++i) {
      indent.append(spewIndentCharacters);
    }
    if (justNameType) {
      sb.append(writeNameAndTypeOfEObject(def, indent.toString()));
    } else {
      sb.append(spewObject(def, indent.toString()));
    }
    if (thisLevel < maxDepth) {
      Iterator<EObject> iter = def.eContents().iterator();
      while (iter.hasNext()) {
        sb.append(spewContents(iter.next(), thisLevel + 1, maxDepth,
            justNameType));
      }
    }
    return sb.toString();
  }

  public static String spewContents(EObject def, int maxDepth,
      boolean justNameType) {
    StringBuffer sb = new StringBuffer();

    if (def instanceof EClassifierImpl) {
      sb.append(spewObjectPrefix + " "
          + ((EClassifierImpl) def).getInstanceClassName() + " "
          + spewObjectSuffix);
    }
    if (def instanceof ENamedElementImpl) {
      sb.append(spewObjectPrefix + " "
          + ((ENamedElementImpl) def).getName() + " "
          + spewObjectSuffix);
    }
    sb.append(spewContents(def, 0, maxDepth, justNameType));
    return sb.toString();
  }

  public static String spewContents(Collection<? extends EObject> defs,
      int maxDepth, boolean justNameType) {
    StringBuffer sb = new StringBuffer();
    for (EObject def : defs) {
      sb.append(spewContents(def, maxDepth, justNameType));
    }
    return sb.toString();
  }

  public static boolean isNumber(String s) {
    if (Utils.isNullOrEmpty(s)) return false;
    try {
      Double.parseDouble(s);
    } catch (NumberFormatException e) {
      return false;
    } catch (NullPointerException e) {
      return false;
    }
    return true;
  }

  // @see EMFUtils.getDisplayName()
  public static String getName(EObject o) {
    EStructuralFeature nameFeature = o.eClass().getEStructuralFeature(
        "name");
    if (nameFeature == null) {
      return null;
    }
    return (String) o.eGet(nameFeature);
  }

  public static String getId(EObject o) {
    EStructuralFeature nameFeature = o.eClass().getEStructuralFeature("id");
    if (nameFeature == null) {
      return null;
    }
    return (String) o.eGet(nameFeature);
  }

  public static void getEObjectsOfType(EObject o, Class<?> type,
      Set<EObject> set) {
    assert set != null;
    if (type.isAssignableFrom(o.getClass())) {
      if (set.contains(o)) {
        return;
      } else {
        set.add(o);
      }
    }
    Iterator<EObject> iter = o.eContents().iterator();
    while (iter.hasNext()) {
      EObject subO = iter.next();
      getEObjectsOfType(subO, type, set);
    }
  }

  public static Set<EObject> getEObjectsOfType(EObject o, Class<?> type) {
    Set<EObject> set = new HashSet<EObject>();
    getEObjectsOfType(o, type, set);
    return set;
  }

  public static EObject getFirstContaining(Collection<? extends EObject> c,
      EObject contained) {
    for (EObject o : c) {
      if (contains(o, contained)) {
        return o;
      }
    }
    return null;
  }

  public static boolean contains(EObject outer, EObject inner) {
    for (EObject o : getEObjectsOfType(outer, inner.getClass())) {
      if (o == inner) {
        return true;
      }
    }
    return false;
  }

  public static <T extends EObject> T getContainerOfEType(EObject eObj,
      Class<T> cls) {
    return getContainerOfEType(eObj, cls, false);
  }

  public static <T extends EObject> T getContainerOfEType(EObject eObj,
      Class<T> cls, boolean includeSelf) {
    if (eObj == null)
      return null;
    if (includeSelf) {
      if (cls.isInstance(eObj))
        return (T) eObj;
    }
    return getContainerOfEType(eObj.eContainer(), cls, true);
  }

  /**
   * @param objects
   *            a collection of Objects
   * @return a comma separated, parenthesized list of the names of the
   *         elements in the collection. If they do not have a getName()
   *         method, then toString() is used.
   */
  public static String toStringNames(Collection<Object> objects) {
    StringBuffer sb = new StringBuffer();
    sb.append("(");
    boolean first = true;
    for (Object obj : objects) {
      if (first)
        first = false;
      else {
        sb.append(", ");
      }
//      if (obj instanceof INamedDefinition) {
//        sb.append(((INamedDefinition) obj).getName());
//      } else 
      if (obj instanceof ENamedElement) {
        sb.append(((ENamedElement) obj).getName());
      } else {
        try {
          Method m = obj.getClass().getMethod("getName",
              (Class<?>[]) null);
          if (m != null) {
            sb.append(ClassUtils.runMethod(true, obj, m).second);
          } else {
            sb.append(obj.toString());
          }
        } catch (Exception e) {
          sb.append(obj.toString());
        }
      }
    }
    sb.append(")");
    return sb.toString();
  }

  public static String toNamesString(Collection<EObject> objects) {
    List<Object> l = new ArrayList< Object >();
    l.addAll(objects);
    return toStringNames(l);
  }

  public static String getBundleFilename(Bundle bundle, String filename) {
    assert (bundle != null);
    assert (filename != null && filename.length() > 0);
    URL fileURL = FileLocator.find(bundle, new Path(filename), null);
    try {
      String fn = FileLocator.toFileURL(fileURL).getFile();
      if (Platform.getOS().equals(Platform.OS_WIN32)) {
        if (fn.startsWith("/")) { // huh... why does this start with a /
                      // on windows?
          fn = fn.substring(1);
        }
      }
      return fn;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }

//  public static void copyFile(String srcFilename, String destFilename) {
//    try {
//      FileUtils.copyFile(new File(srcFilename), new File(destFilename));
//    } catch (IOException e) {
//      LogUtil.error(e);
//    }
//  }

  public static int compare(int i1, int i2) {
    if (i1 < i2)
      return -1;
    if (i1 > i2)
      return 1;
    return 0;
  }

  
  /**
   * @param args
   */
  public static void main( String[] args ) {
    // TODO Auto-generated method stub

  }

}
