/**
 * 
 */
package gov.nasa.jpl.ae.magicdrawPlugin.modelQuery;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.KeyStroke;

import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.Utils;

import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.plugins.Plugin;

/**
 * @author bclement
 *
 */
public class MDPlugin extends Plugin {

  Configurator configForAll = new Configurator();
  ActionsConfiguratorsManager acm = null;

  public MDAction addConfiguration( String context, String subcontext, String actionName,
                                    String category, Method actionMethod ) {
    return configForAll.addConfiguration( context, subcontext, actionName, category,
                                          actionMethod );
  }
  public MDAction addConfiguration( Configurator.Context context, String subcontext, String actionName,
                                    String category, Method actionMethod ) {
    return configForAll.addConfiguration( context.toString(), subcontext, actionName, category, actionMethod );
  }

  public MDAction addConfiguration( String context, String subcontext, String actionName,
                                    String category, Method actionMethod,
                                    Object objectInvokingMethod ) {
    return configForAll.addConfiguration( context, subcontext, actionName, category, actionMethod,
                             objectInvokingMethod );
  }

  public MDAction addConfiguration( Configurator.Context context, String subcontext, String actionName,
                                    String category, Method actionMethod,
                                    Object objectInvokingMethod ) {
    return configForAll.addConfiguration( context.toString(), subcontext, actionName, category, actionMethod,
                             objectInvokingMethod );
  }

  public MDAction addConfiguration( String context, String subcontext, String actionName,
                                    String category, Method actionMethod,
                                    Object objectInvokingMethod, String id,
                                    KeyStroke k, String group ) {
    return configForAll.addConfiguration( context, subcontext, actionName, category, actionMethod,
                             objectInvokingMethod, id, k, group );
  }
  public MDAction addConfiguration( Configurator.Context context, String subcontext, String actionName,
                                    String category, Method actionMethod,
                                    Object objectInvokingMethod, String id,
                                    KeyStroke k, String group ) {
    return configForAll.addConfiguration( context.toString(), subcontext, actionName, category, actionMethod,
                             objectInvokingMethod, id, k, group );
  }

  public MDAction addConfiguration( String context, String subcontext, String actionName,
                                    String category, Method actionMethod,
                                    Object objectInvokingMethod, String id,
                                    Integer mnemonic, String group ) {
    return configForAll.addConfiguration( context, subcontext, actionName, category, actionMethod,
                             objectInvokingMethod, id, mnemonic, group );
  }
  public MDAction addConfiguration( Configurator.Context context, String subcontext, String actionName,
                                    String category, Method actionMethod,
                                    Object objectInvokingMethod, String id,
                                    Integer mnemonic, String group ) {
    return configForAll.addConfiguration( context.toString(), subcontext, actionName, category, actionMethod,
                             objectInvokingMethod, id, mnemonic, group );
  }


  public MDAction addConfiguration( String context, String subcontext, String actionName,
                                    String category, Method actionMethod,
                                    Object objectInvokingMethod, String id,
                                    Integer mnemonic, KeyStroke k, String group ) {
    return configForAll.addConfiguration( context, subcontext, actionName, category, actionMethod,
                                          objectInvokingMethod, id, mnemonic, k, group );
  }
  public MDAction addConfiguration( Configurator.Context context, String subcontext, String actionName,
                                    String category, Method actionMethod,
                                    Object objectInvokingMethod, String id,
                                    Integer mnemonic, KeyStroke k, String group ) {
    return configForAll.addConfiguration( context.toString(), subcontext, actionName, category, actionMethod,
                                          objectInvokingMethod, id, mnemonic, k, group );
  }
  
  /**
   * 
   */
  public MDPlugin() {
    super();
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see com.nomagic.magicdraw.plugins.Plugin#close()
   */
  @Override
  public boolean close() {
    if ( Debug.isOn() ) Debug.errln("closing " + getClass().getSimpleName() );
    if ( Debug.isOn() ) Debug.outln("closing " + getClass().getSimpleName() );
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.nomagic.magicdraw.plugins.Plugin#init()
   */
  @Override
  public void init() {
//    Debug.turnOn();
    if ( Debug.isOn() ) Debug.errln( "initializing " + getClass().getSimpleName() );
    if ( Debug.isOn() ) Debug.outln( "initializing " + getClass().getSimpleName() );

    acm = ActionsConfiguratorsManager.getInstance();

    Map< String, Map< String, MDAction > > actionCategories = null;
    Map< String, Map< String, Map< String, MDAction > > > subcontexts = null;

    for ( Configurator.Context context : configForAll.contexts ) {
      subcontexts = configForAll.menus.get( context );

      for ( Entry< String, Map< String, Map< String, MDAction > > > e : subcontexts.entrySet() ) {
        actionCategories = e.getValue();

        if ( !Utils.isNullOrEmpty( actionCategories ) ) {
          Configurator c = new Configurator();
          Map< String, Map< String, Map< String, MDAction > > > newSubcontext =
              new TreeMap< String, Map< String, Map< String, MDAction >> >();
          newSubcontext.put( e.getKey(), e.getValue() );

          c.menus.put( context, newSubcontext );
          addConfigurator( c, context, e.getKey() );
        }
      }
    }
  }

  protected void addConfigurator( Configurator c, Configurator.Context context ) {
    addConfigurator( c, context, null );
  }
 
  protected void addConfigurator( Configurator c, Configurator.Context context, String subcontext ) {
 
    // manager to whom configurator is added
    ActionsConfiguratorsManager acm =
        ActionsConfiguratorsManager.getInstance();
 
    // Use reflection to find and invoke the acm.add*Configurator() method
    // corresponding to this context.
    Method addMethod = context.getAddConfiguratorMethod( subcontext );

    // create argument array for the add method 
    List< Object > arguments = new ArrayList< Object >();
    for ( int j=0; j < addMethod.getParameterTypes().length; ++j ) {
      if ( addMethod.getParameterTypes()[j].equals( String.class ) ) {
        arguments.add( subcontext );
      } else if ( Configurator.class.isAssignableFrom( addMethod.getParameterTypes()[j] ) ) {
        arguments.add( c );
      } else {
        Debug.error( true,
                     "Error! addConfigurator(" + c + ", " + context + ", "
                         + subcontext + "): Unexpected "
                         + addMethod.getParameterTypes()[ j ].getSimpleName()
                         + " parameter type in add method, " + addMethod.getName()
                         + "()=" + addMethod + ", for context " + context );
        arguments.add( null );
      }
    }
    ClassUtils.runMethod( false, acm, addMethod, arguments.toArray() );
  }

  /* (non-Javadoc)
   * @see com.nomagic.magicdraw.plugins.Plugin#isSupported()
   */
  @Override
  public boolean isSupported() {
    // TODO Auto-generated method stub
    return false;
  }

}
