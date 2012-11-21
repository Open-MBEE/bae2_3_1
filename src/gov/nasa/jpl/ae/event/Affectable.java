package gov.nasa.jpl.ae.event;

import java.lang.reflect.Method;
import java.util.Collection;

public interface Affectable {
  public Collection< Method > getEffectMethods();
  public boolean doesAffect( Method method );
}
