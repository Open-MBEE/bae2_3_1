/**
 * 
 */
package gov.nasa.jpl.ae.magicdrawPlugin.modelQuery;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * This is a dummy activator for a dummy plugin just to be able to specify
 * Plug-in Dependencies, which seems to handle adding libraries with their
 * source code and javadoc better; need to use eclipse-installed libraries
 * instead of MagicDraw's, which do not have source code.
 */
public class MQActivator implements BundleActivator {

  @Override
  public void start( BundleContext arg0 ) throws Exception {
    // TODO Auto-generated method stub
  }

  @Override
  public void stop( BundleContext arg0 ) throws Exception {
    // TODO Auto-generated method stub
  }

}
