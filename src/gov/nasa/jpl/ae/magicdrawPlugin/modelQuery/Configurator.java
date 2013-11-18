/**
 * 
 */
package gov.nasa.jpl.ae.magicdrawPlugin.modelQuery;

import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.magicdrawPlugin.modelQuery.Configurator.Context;
import gov.nasa.jpl.ae.util.ClassUtils;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.KeyStroke;

import org.junit.Assert;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.actions.BrowserContextAMConfigurator;
import com.nomagic.magicdraw.actions.BrowserToolbarAMConfigurator;
import com.nomagic.magicdraw.actions.DiagramContextAMConfigurator;
import com.nomagic.magicdraw.actions.DiagramContextToolbarAMConfigurator;
import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.actions.MDActionsCategory;
import com.nomagic.magicdraw.ui.browser.Node;
import com.nomagic.magicdraw.ui.browser.Tree;
import com.nomagic.magicdraw.uml.DiagramType;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;

/**
 * A general configurator for MagicDraw. Here's how to use it:
 * 
 *   Configurator c = new Configurator();
 *   Method copyElementMethod = ElementUtils.class.getDeclaredMethods()[ 0 ];
 *   c.addConfiguration( "General", "copyElement", "Element Utils",
 *                       copyElementMethod );
 *   Method copyPackageMethod = PackageUtils.class.getDeclaredMethods()[ 0 ];
 *   c.addConfiguration( "BrowserToolbar", "copyPackage", "Package Utils",
 *                       copyPackageMethod );
 *   ActionsConfiguratorsManager acm = ActionsConfiguratorsManager.getInstance();
 *   acm.addContainmentBrowserContextConfigurator(c);
 *   acm.addBaseDiagramContextConfigurator("Class Diagram", c);
 *   acm.addBaseDiagramContextConfigurator("Activity Diagram", c);
 */
public class Configurator implements BrowserContextAMConfigurator,
                         DiagramContextAMConfigurator, AMConfigurator,
                         BrowserToolbarAMConfigurator,
                         DiagramContextToolbarAMConfigurator {
/*
  public static class DCTAC implements DiagramContextToolbarAMConfigurator {

    @Override
    public int getPriority() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public void configure( ActionsManager paramActionsManager,
                           PresentationElement paramPresentationElement ) {
      // TODO Auto-generated method stub
      
    }
    
  }
  public static class BTAC implements BrowserToolbarAMConfigurator {

    @Override
    public int getPriority() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public void configure( ActionsManager paramActionsManager, Tree paramTree ) {
      // TODO Auto-generated method stub
      
    }
    
  }
  public static class DCAC implements DiagramContextAMConfigurator {

    @Override
    public int getPriority() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public void
        configure( ActionsManager paramActionsManager,
                   DiagramPresentationElement paramDiagramPresentationElement,
                   PresentationElement[] paramArrayOfPresentationElement,
                   PresentationElement paramPresentationElement ) {
      // TODO Auto-generated method stub
      
    }
    
  }

  public static class AC implements AMConfigurator {

    @Override
    public int getPriority() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public void configure( ActionsManager paramActionsManager ) {
      // TODO Auto-generated method stub
      
    }
    
  }
  public static class BCAC implements BrowserContextAMConfigurator {

    @Override
    public int getPriority() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public void configure( ActionsManager paramActionsManager, Tree paramTree ) {
      // TODO Auto-generated method stub
      
    }
    
  }
  */
  /**
   * A Context is a place from which the user accesses menus that are populated
   * by configurators.
   * 
   */
//  public static enum Context {
//    Browser, BrowserToolbar, Diagram, DiagramToolbar, General;
  public static enum Context {
    //ContainmentBrowser, ContainmentBrowserToolbar, StructureBrowser, StructureBrowserToolbar, InheritanceBrowser, InheritanceBrowserToolbar, DiagramsBrowser, DiagramsBrowserToolbar, Browser, BrowserToolbar, Diagram, DiagramToolbar, General;
    BaseDiagramContext,
    BaseDiagramContextToolbar,
    ContainmentBrowserContext,
    ContainmentBrowserShortcuts,
    ContainmentBrowserToolbar,
    CustomizableShortcuts,
    DiagramCommandBar,
    DiagramContext,
    DiagramContextToolbar,
    DiagramsBrowserContext,
    DiagramsBrowserShortcuts,
    DiagramsBrowserToolbar,
    DiagramShortcuts,
    DiagramToolbarActionsProvider,
    DiagramToolbar,
    ExtensionsBrowserContext,
    ExtensionsBrowserShortcuts,
    ExtensionsBrowserToolbar,
    InheritanceBrowserContext,
    InheritanceBrowserShortcuts,
    InheritanceBrowserToolbar,
    LockViewBrowserContext,
    LockViewBrowserShortcuts,
    LockViewBrowserToolbar,
    MainMenu,
    MainShortcuts,
    MainToolbar,
    SearchBrowserContext,
    SearchBrowserShortcuts,
    SearchBrowserToolbar,
    TargetElementAM;

    public Method getAddConfiguratorMethod( String subcontext ) {
      // find the acm.add*Configurator() method corresponding to this context 
      String addMethodString = "add" + this + "Configurator";
      Method[] addMethods = ClassUtils.getMethodsForName( ActionsConfiguratorsManager.class, addMethodString );
      if ( addMethods == null || addMethods.length == 0 ) {
        Debug.error( true, "Error! " + this + ".getAddConfiguratorMethod("
                           + subcontext + "): add method, " + addMethodString
                           + ", for context " + this + " not found" );
        return null;
      }
      Method addMethod = addMethods[ 0 ];
      if ( addMethods.length > 1 && addMethod.getParameterTypes().length > 1
           && subcontext == null ) {
        // find that doesn't take an extra String argument (subcontext)
        for ( int i = 1; i < addMethods.length; ++i ) {
          if ( addMethods[ i ].getParameterTypes() != null
               && addMethods[ i ].getParameterTypes().length == 1 ) {
            Assert.assertTrue( addMethods[ i ].getParameterTypes()[ 0 ].isAssignableFrom( Configurator.class ) );
            addMethod = addMethods[ i ];
            break;
          }
        }
      }
      return addMethod;
    }

    public static Context fromString( String contextString ) {
      for ( Context c : contexts ) {
        if ( contextString.equals( c.toString() ) ) return c;
      }
      return null;
    }
  }

  public static class GenericMDAction extends MDAction {

    private static final long serialVersionUID = 6943254131859224755L;

    Method actionMethod = null;
    Object objectInvokingAction = null;
      
    public GenericMDAction( String id, String name, Integer mnemonic,
                            String group, Method actionMethod,
                            Object objectInvokingMethod ) {
      super(id, name, mnemonic, group);
      Debug.outln( "GenericMDAction( id=" + id + ", name=" + name
                   + ", mnemonic=" + mnemonic 
                   + ", group=" + group
                   + ", actionMethod="
                   + ( ( actionMethod == null ) ? "null" : actionMethod.getName() )
                   + ", objectInvokingMethod=" + objectInvokingMethod + " )" );
      this.actionMethod = actionMethod;
      this.objectInvokingAction = objectInvokingMethod;
    }
  
    public GenericMDAction( String id, String name, KeyStroke keyStroke, String group,
                            Method actionMethod, Object objectInvokingMethod ) {
      super( id, name, keyStroke, group );
      Debug.outln( "GenericMDAction( id=" + id + ", name=" + name
                   + ", keyStroke=" + keyStroke + ", group=" + group
                   + ", actionMethod="
                   + ( ( actionMethod == null ) ? "null" : actionMethod.getName() )
                   + ", objectInvokingMethod=" + objectInvokingMethod + " )" );
      this.actionMethod = actionMethod;
      this.objectInvokingAction = objectInvokingMethod;
    }

    @Override
    public void actionPerformed( ActionEvent actionEvent ) {
      // super.actionPerformed( actionEvent ); // don't call super -- it tries
      // to execute
      // com.nomagic.magicdraw.actions.ActionsExecuter.execute(NMAction,
      // ActionEvent)
      // actionPerformed()
      // }
      //
      // public static void actionPerformed( ActionEvent e, Method actionMethod,
      // Object objectInvokingAction ) {
      if ( actionMethod == null ) return;
      ArrayList< Object > args = new ArrayList< Object >();
      if ( actionMethod.getParameterTypes() != null
           && actionMethod.getParameterTypes().length > 0 ) {
        for ( Class< ? > c : actionMethod.getParameterTypes() ) {
          if ( c.equals( ActionEvent.class ) || c.equals( AWTEvent.class )
               || c.equals( EventObject.class ) ) {
            args.add( actionEvent );
          } else {
            Debug.error( false, "Warning! Action " + actionMethod
                                + " passing null for " + c + "!" );
            args.add( null );
          }
        }
      }
      Pair< Boolean, Object > p =
          ClassUtils.runMethod( false, objectInvokingAction, actionMethod,
                                args.toArray() );
    }

  }

  /**
   * An array of the Context constants
   */
  public static Context[] contexts = new Context[] { //Context.Browser,
                                                     //Context.BrowserToolbar,
                                                     //Context.Diagram,
                                                     //Context.DiagramToolbar,
                                                     //Context.General
                                                     Context.BaseDiagramContext,
                                                     Context.BaseDiagramContextToolbar,
                                                     Context.ContainmentBrowserContext,
                                                     Context.ContainmentBrowserShortcuts,
                                                     Context.ContainmentBrowserToolbar,
                                                     Context.CustomizableShortcuts,
                                                     Context.DiagramCommandBar,
                                                     Context.DiagramCommandBar,
                                                     Context.DiagramContext,
                                                     Context.DiagramContextToolbar,
                                                     Context.DiagramsBrowserContext,
                                                     Context.DiagramsBrowserShortcuts,
                                                     Context.DiagramsBrowserToolbar,
                                                     Context.DiagramShortcuts,
                                                     Context.DiagramToolbarActionsProvider,
                                                     Context.DiagramToolbar,
                                                     Context.ExtensionsBrowserContext,
                                                     Context.ExtensionsBrowserShortcuts,
                                                     Context.ExtensionsBrowserToolbar,
                                                     Context.InheritanceBrowserContext,
                                                     Context.InheritanceBrowserShortcuts,
                                                     Context.InheritanceBrowserToolbar,
                                                     Context.LockViewBrowserContext,
                                                     Context.LockViewBrowserShortcuts,
                                                     Context.LockViewBrowserToolbar,
                                                     Context.MainMenu,
                                                     Context.MainShortcuts,
                                                     Context.MainToolbar,
                                                     Context.SearchBrowserContext,
                                                     Context.SearchBrowserShortcuts,
                                                     Context.SearchBrowserToolbar,
                                                     Context.TargetElementAM

  };
  
  public static Map< Configurator.Context, Class<?> > typeForContext = initTypeForContext();
  public static Map< Configurator.Context, Class<?> > initTypeForContext() {
    typeForContext = new HashMap< Configurator.Context, Class<?> >();
    for ( Context c : contexts ) {
      Method addMethod = c.getAddConfiguratorMethod( "asdfghjkl" );
      Class<?> cls = Configurator.class;
      int j;
      if ( addMethod != null && addMethod.getParameterTypes() != null ) {
        for ( j = 0; j < addMethod.getParameterTypes().length; ++j ) {
          if ( Configurator.class.isAssignableFrom( addMethod.getParameterTypes()[j] ) ) {
            cls = addMethod.getParameterTypes()[j];
            break;
          }
        }
        assert( j < addMethod.getParameterTypes().length );
      }
      typeForContext.put( c, cls );
    }
    return typeForContext;
  }
  public static Map<Class<?>, Set<Configurator.Context> > contextsForType = initContextsForType();
  public static Map<Class<?>, Set<Configurator.Context> > initContextsForType() {
    contextsForType = new HashMap< Class<?>, Set<Context> >();
    for ( Entry< Context, Class< ? > > e : typeForContext.entrySet() ) {
      Set<Configurator.Context> contexts = contextsForType.get( e.getValue() );
      if ( contexts == null ) {
        contexts = new HashSet< Configurator.Context >();
        contextsForType.put( e.getValue(), contexts );
      }
      contexts.add( e.getKey() );
    }
    for ( Context c : contexts ) {
      Method addMethod = c.getAddConfiguratorMethod( "asdfghjkl" );
      Class<?> cls = Configurator.class;
      int j;
      for ( j = 0; j < addMethod.getParameterTypes().length; ++j ) {
        if ( Configurator.class.isAssignableFrom( addMethod.getParameterTypes()[j] ) ) {
          cls = addMethod.getParameterTypes()[j];
          break;
        }
      }
      assert( j < addMethod.getParameterTypes().length );
      typeForContext.put( c, cls );
    }
    return contextsForType;
  }

  /**
   * A map from contexts (e.g., DiagramToolbar) to sub-contexts (e.g.,
   * "Class Diagram") to categories to menu item names to actions.
   */
  Map< Context, Map< String, Map< String, Map< String, MDAction > > > > menus =
      null;

  /**
   * @param menus
   *          a map from contexts (General, Browser, Diagram, BrowserToolbar,
   *          DiagramToolbar) to categories to menu item names to actions.
   */
  public Configurator( Map< Context, Map< String, Map< String, Map< String, MDAction > > > > menus ) {
    //this();
    this.menus = menus;
  }

  public Configurator() {
    initMenus();
  }

  public void initMenus() {
    menus = new TreeMap< Context, Map< String, Map< String, Map< String, MDAction > > > >();
    for ( Context c : contexts ) {
      menus.put( c, new TreeMap< String, Map< String, Map< String, MDAction > > >() );
    }
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see com.nomagic.magicdraw.actions.ConfiguratorWithPriority#getPriority()
   */
  @Override
  public int getPriority() {
    return MEDIUM_PRIORITY;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.nomagic.magicdraw.actions.DiagramContextToolbarAMConfigurator#configure
   * (com.nomagic.actions.ActionsManager,
   * com.nomagic.magicdraw.uml.symbols.PresentationElement)
   */
  @Override
  public void configure(ActionsManager manager, PresentationElement diagram) {
    Debug.outln( "configure(manager=" + manager + ", diagram=" + diagram
                 + ") for DiagramContextToolbarAMConfigurator" );
    Debug.errln( "configure(manager=" + manager + ", diagram=" + diagram
                 + ") for DiagramContextToolbarAMConfigurator" );
    if ( diagram instanceof DiagramPresentationElement ) {
//    Configurator.Context context =
//        getContextForType( DiagramContextToolbarAMConfigurator.class );
      Pair< Context, String > p =
          getContextForType( DiagramContextAMConfigurator.class,
                             ( (DiagramPresentationElement)diagram ).getDiagramType().getType() );
      addDiagramActions( manager, diagram, menus.get( p.first ).get( p.second ) );
    } else {
      Assert.assertTrue( false );
    }
  }

  /**
   * More than one context may may be associated with a configurator type. Just
   * pick the first one found in the menus map.
   * 
   * @param cls
   * @return
   */
  protected Context getContextForType( Class< ? > cls ) {
    return getContextForType( cls, null ).first;
  }

  /**
   * More than one context may may be associated with a configurator type. Just
   * pick the first one found in the menus map that has the closest subcontext.
   * 
   * @param cls
   * @param subcontext
   * @return
   */
  protected Pair< Context, String > getContextForType( Class< ? > cls,
                                                       String subcontext ) {
    Set< Context > ctxts = contextsForType.get( cls );
    if ( Utils.isNullOrEmpty( ctxts ) ) {
      return new Pair< Configurator.Context, String >( contexts[ 0 ], null );
    }
    Context contextKey[] = { null, null };
    int numMatch[] = { 0, 0, 0, 0 };
    int numDontMatch[] =
        { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
         Integer.MAX_VALUE };
    String subcontextKey[] = { null, null };
    // get best matching subcontext
    for ( Context c : ctxts ) {
      Pair< Integer, Integer > p =
          prefixOverlapScore( c.toString(), subcontext );
      boolean betterContext =
          p.first > numMatch[ 0 ]
              || ( p.first == numMatch[ 0 ] && p.second < numDontMatch[ 0 ] );
      if ( betterContext ) {
        contextKey[ 0 ] = c;
        numMatch[ 0 ] = p.first;
        numDontMatch[ 0 ] = p.second;
      }
      int idx = ( betterContext ? 1 : 3 );
      int idxKey = ( betterContext ? 0 : 1 );
      // get best matching subcontext
      Map< String, Map< String, Map< String, MDAction > > > subcontexts =
          menus.get( c );
      if ( !Utils.isNullOrEmpty( subcontexts ) ) {
        if ( subcontext != null ) {
          boolean foundBetterSub = false;
          for ( String subc : subcontexts.keySet() ) {
            Map< String, Map< String, MDAction > > configs =
                subcontexts.get( subc );
            if ( configs != null && !configs.isEmpty() ) {
              p = prefixOverlapScore( subc, subcontext );
              boolean betterSubcontext = ( p.first > numMatch[ idx ] || 
                                           ( p.first == numMatch[ idx ] &&
                                             p.second < numDontMatch[ idx ] ) );
              if ( betterSubcontext ) {
                foundBetterSub = true;
                subcontextKey[ idxKey ] = subc;
                if ( !betterContext ) {
                  contextKey[ 1 ] = c;
                }
                numMatch[ idx ] = p.first;
                numDontMatch[ idx ] = p.second;
              }
            }
          }
          // If match to subcontext is also better than any seen, record it.
          if ( betterContext ) {
            if ( numMatch[ 1 ] > numMatch[ 3 ] ||
                 ( numMatch[ 1 ] == numMatch[ 3 ] &&
                   numDontMatch[ 1 ] < numDontMatch[ 3 ] ) ) {
              subcontextKey[ 1 ] = subcontextKey[ 0 ];
              numMatch[ 3 ] = numMatch[ 1 ];
              numDontMatch[ 3 ] = numDontMatch[ 1 ];
              contextKey[ 1 ] = c;
            }
          }
        }
        // Map< String, Map< String, MDAction > > configs = subcontexts.get(
        // subcontextKey );
        // if ( configs != null && !configs.isEmpty() ) {
        // return c;//new Pair< Configurator.Context, String >( c,
        // subcontextKey[0] );
        // }
      }
    }
    if ( numMatch[ 0 ] > numMatch[ 3 ] || 
         ( numMatch[ 0 ] == numMatch[ 3 ] && 
           numDontMatch[ 0 ] <= numDontMatch[ 3 ] ) ) {
      return new Pair< Configurator.Context, String >( contextKey[ 0 ],
                                                       subcontextKey[ 0 ] );
    }
    return new Pair< Configurator.Context, String >( contextKey[ 1 ],
                                                     subcontextKey[ 1 ] );
  }

  public static Pair< Integer, Integer > prefixOverlapScore( String s1,
                                                             String s2 ) {
    if ( Utils.isNullOrEmpty( s1 ) || Utils.isNullOrEmpty( s2 ) ) {
      return new Pair< Integer, Integer >( 0, 0 );
    }
    // String subcontextKey = null;
    int numMatch = 0;
    int numDontMatch = Integer.MAX_VALUE;
    if ( s1.contains( s2 ) ) {
//      if ( numMatch < s2.length() ) {
//        // subcontextKey = s2;
        numMatch = s2.length();
        numDontMatch = s1.length() - s2.length();
//      } else if ( numMatch == s2.length() ) {
//        if ( s1.length() - s2.length() < numDontMatch ) {
//          // subcontextKey = s2;
//          numDontMatch = s1.length() - s2.length();
//        }
//      }
    } else if ( s2.contains( s1 ) ) {
//      if ( numMatch < s1.length() ) {
//        // subcontextKey = s1;
        numMatch = s1.length();
        numDontMatch = s2.length() - s1.length();
//      } else if ( numMatch == s1.length() ) {
//        if ( s2.length() - s1.length() < numDontMatch ) {
//          // subcontextKey = s1;
//          numDontMatch = s2.length() - s1.length();
//        }
//      }
    }
    return new Pair< Integer, Integer >( numMatch, numDontMatch );
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see
   * com.nomagic.actions.AMConfigurator#configure(com.nomagic.actions.ActionsManager
   * )
   */
  @Override
  public void configure( ActionsManager manager ) {
    Debug.outln( "configure(manager=" + manager + ") for AMConfigurator" );
    Debug.errln( "configure(manager=" + manager + ") for AMConfigurator" );
    Pair< Context, String > p =
        getContextForType( AMConfigurator.class, manager.getClass().getSimpleName() );
    addElementActions( manager, null, menus.get( p.first ).get( p.second ) );//Context.General ) );
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.nomagic.magicdraw.actions.DiagramContextAMConfigurator#configure(com
   * .nomagic.actions.ActionsManager,
   * com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement,
   * com.nomagic.magicdraw.uml.symbols.PresentationElement[],
   * com.nomagic.magicdraw.uml.symbols.PresentationElement)
   */
  @Override
  public void configure( ActionsManager manager,
                         DiagramPresentationElement diagram,
                         PresentationElement[] selected,
                         PresentationElement requestor ) {
    Debug.outln( "configure(manager=" + manager + ", diagram=" + diagram
                 + ", selected=" + Utils.toString( selected ) + ", requestor=" + requestor + ") for DiagramContextAMConfigurator" );
    Debug.errln( "configure(manager=" + manager + ", diagram=" + diagram
                 + ", selected=" + Utils.toString( selected ) + ", requestor=" + requestor + ") for DiagramContextAMConfigurator" );
    
    DiagramType dType = diagram.getDiagramType();
    Pair< Context, String > p =
    //Configurator.Context context = 
        getContextForType( DiagramContextAMConfigurator.class, diagram.getDiagramType().getType() );
    if ( requestor != null ) {
      Element e = requestor.getElement();
      addElementActions( manager, e, menus.get( p.first ).get( p.second ) );
    } else {
      addDiagramActions( manager, diagram, menus.get( p.first ).get( p.second ) );
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.nomagic.magicdraw.actions.BrowserContextAMConfigurator#configure(com
   * .nomagic.actions.ActionsManager, com.nomagic.magicdraw.ui.browser.Tree)
   */
  @Override
  public void configure( ActionsManager manager, Tree browser ) {
    Debug.outln( "configure(manager=" + manager + ", browser=" + browser
                 + ") for BrowserContextAMConfigurator" );
    Debug.errln( "configure(manager=" + manager + ", browser=" + browser
                 + ") for BrowserContextAMConfigurator" );
    Node no = browser.getSelectedNode();
    if ( no == null ) return;
    Object o = no.getUserObject();
    if ( !( o instanceof Element ) ) return;
    Pair< Context, String > p =
    //Configurator.Context context = 
        getContextForType( BrowserContextAMConfigurator.class, browser.getName() );
    addElementActions( manager, (Element)o, menus.get( p.first ).get( p.second ) );
  }

  protected void addDiagramActions( ActionsManager manager,
                                    PresentationElement diagram,
                                    Map< String, Map< String, MDAction > > actionCategories ) {
    if (diagram == null) return;
    Element element = diagram.getActualElement();
    if (element == null)
      return;
    Element owner = element.getOwner();
    if (owner == null || !(owner instanceof NamedElement)) return;
    //Map< String, Map< String, MDAction > > > actionCategories = subcontexts.get(element.)
    addElementActions(manager, (NamedElement) owner, actionCategories);
  }

  protected void
      addElementActions( ActionsManager manager, Element e,
                         Map< String, Map< String, MDAction > > actionCategories ) {
    Debug.outln( "addElementActions( manager=" + manager + ", element=" + e
                 + ", actionCategories=" + actionCategories + ")" );
    Debug.errln( "addElementActions( manager=" + manager + ", element=" + e
                 + ", actionCategories=" + actionCategories + ")" );
//    ActionsCategory c = new MDActionsCategory("categoryId", "categoryName");
//    try {
//      c.addAction( makeMDAction( "CinYoung",
//                                 "isAwesome",
//                                 null,
//                                 null,
//                                 null,
//                                 TestPlugin.ElementUtils.class.getDeclaredMethods()[ 0 ],
//                                 null ) );
//      c.setNested(true);
//      manager.addCategory(0, c);
//    } catch ( Exception ex ) {
//      ex.printStackTrace();
//    }
//    Debug.errln( "CinYoungIsAwesomeBlawesomeness" );
//    Debug.outln( "CinYoungIsAwesomeBlawesomeness" );
    for ( Entry< String, Map< String, MDAction > > category :
          actionCategories.entrySet() ) {
      ActionsCategory c =
          myCategory( manager, category.getKey(), category.getKey() );
      //new ActionsCategory( category.getKey(), category.getKey() );
      for ( Entry< String, MDAction > action : category.getValue().entrySet() ) {
        c.addAction( action.getValue() );
      }
    }
  }

  /**
   * Gets the specified category, creates it if necessary.
   * @param manager
   * @param id
   * @param name
   * @return category with given id/name
   */
  private ActionsCategory myCategory(ActionsManager manager, String id, String name) {
    Debug.outln( "myCategory( manager=" + manager + ", id=" + id
                 + ", name=" + name + ")" );
    ActionsCategory category = (ActionsCategory) manager.getActionFor(id);
    if (category == null) {
      category = new MDActionsCategory(id, name);
      category.setNested(true);
      manager.addCategory(0, category);
    }
    return category;
  }
  
  public MDAction addConfiguration( String context, String subcontext, String actionName,
                                    String category, Method actionMethod ) {
    return addConfiguration( context, subcontext, actionName, category, actionMethod, null );
  }

  public MDAction addConfiguration( String context, String subcontext, String actionName,
                                    String category, Method actionMethod,
                                    Object objectInvokingMethod ) {
    return addConfiguration( context, subcontext, actionName, category, actionMethod,
                             objectInvokingMethod, "", (KeyStroke)null, null );
  }

  public MDAction addConfiguration( String context, String subcontext, String actionName,
                                    String category, Method actionMethod,
                                    Object objectInvokingMethod, String id,
                                    KeyStroke k, String group ) {
    return addConfiguration( context, subcontext, actionName, category, actionMethod,
                             objectInvokingMethod, id, null, k, group );
  }

  public MDAction addConfiguration( String context, String subcontext, String actionName,
                                    String category, Method actionMethod,
                                    Object objectInvokingMethod, String id,
                                    Integer mnemonic, String group ) {
    return addConfiguration( context, subcontext, actionName, category, actionMethod,
                             objectInvokingMethod, id, mnemonic, null, group );
  }

  public MDAction addConfiguration( String context, String subcontext, String actionName,
                                    String category, Method actionMethod,
                                    Object objectInvokingMethod, String id,
                                    Integer mnemonic, KeyStroke k, String group ) {
    MDAction mdAction = makeMDAction( id, actionName, mnemonic, k, group,
                                      actionMethod, objectInvokingMethod );
    if ( mdAction == null ) return null;
    Context c = Context.fromString( context );
    if ( c == null ) {
      Debug.error( true, "Error! addConfiguration( context=" + context
                         + ", actionName=" + actionName + ", category="
                         + category + ", actionMethod=" + actionMethod
                         + ", objectInvokingMethod=" + objectInvokingMethod
                         + ", id=" + id + ", mnemonic=" + mnemonic + ", k=" + k
                         + ", group=" + category + " ): Unrecognized context, "
                         + context );
      return null;
    }
    Debug.outln( "addConfiguration( context=" + context
                         + ", actionName=" + actionName + ", category="
                         + category + ", actionMethod=" + actionMethod
                         + ", objectInvokingMethod=" + objectInvokingMethod
                         + ", id=" + id + ", mnemonic=" + mnemonic + ", k=" + k
                         + ", group=" + category + " )" );
    Debug.errln( "addConfiguration( context=" + context
                 + ", actionName=" + actionName + ", category="
                 + category + ", actionMethod=" + actionMethod
                 + ", objectInvokingMethod=" + objectInvokingMethod
                 + ", id=" + id + ", mnemonic=" + mnemonic + ", k=" + k
                 + ", group=" + category + " )" );

    Map< String, Map< String, MDAction > > categories = menus.get( c ).get( subcontext );
    Map< String, MDAction > actions = categories.get( category ); 
    if ( actions == null ) {
      actions = new TreeMap< String, MDAction >();
      categories.put( category, actions );
    }
    if ( id == null || id.isEmpty() ) {
      actions.put( actionName, mdAction );
    } else {
      actions.put( id, mdAction );
    }
    return mdAction;
  }

  public static MDAction makeMDAction( String id, String name,
                                       Integer mnemonic, KeyStroke k,
                                       String group, Method actionMethod,
                                       Object objectInvokingMethod ) {
    if ( actionMethod == null ) return null;
    MDAction mda = null;
    if ( mnemonic != null ) {
      mda = new GenericMDAction( id, name, mnemonic, group, actionMethod,
                                 objectInvokingMethod );
    } else {
      mda = new GenericMDAction( id, name, k, group, actionMethod,
                                 objectInvokingMethod );
    }
    return mda;
  }  

  
}