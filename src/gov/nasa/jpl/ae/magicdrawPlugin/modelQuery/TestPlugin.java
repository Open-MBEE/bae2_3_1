package gov.nasa.jpl.ae.magicdrawPlugin.modelQuery;

import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.util.Debug;
import gov.nasa.jpl.ae.util.JavaToConstraintExpression;
import gov.nasa.jpl.ae.util.Pair;
import gov.nasa.jpl.ae.util.Utils;

import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.evaluation.EvaluationConfigurator;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class TestPlugin extends MDPlugin {
  
  static class ElementUtils {
    public static void copyElementMethod( ActionEvent event, Element element ) {
      Element newElement = null;
      try {
        newElement = (Element)element.clone();
      } catch ( CloneNotSupportedException e ) {
        e.printStackTrace();
      }
      element.refImmediatePackage();
      element.eContents().add( newElement );
    }
  }
  static class PackageUtils {
    public static void copyPackageMethod( ActionEvent event, Element element ) {
      Element newElement = null;
      try {
        newElement = (Element)element.clone();
      } catch ( CloneNotSupportedException e ) {
        e.printStackTrace();
      }
      element.refImmediatePackage();
      element.eContents().add( newElement );
    }
    
    protected JavaToConstraintExpression translator =
        new JavaToConstraintExpression( null );

    public Object eval( Element element, boolean deep, Set<Object> seen ) {
      Object result = null;
      if ( element == null ) return null;
      Pair< Boolean, Set< Object > > p = Utils.seen( element, deep, seen );
      if ( p.first ) return null;
      seen = p.second;

      Collection< Constraint > coll = element.get_constraintOfConstrainedElement();
      if ( !Utils.isNullOrEmpty( coll ) ) {
        for ( Constraint c : coll ) {
          if ( c == null ) continue;
          String expr = c.toString();
          if ( Utils.isNullOrEmpty( expr ) ) continue;
          Expression< ? > aeExpr = translator.javaToAeExpression( expr, null, false );
          if ( aeExpr == null ) continue;
          return aeExpr.evaluate( true );
        }
      }
      EList< EObject > contents = element.eContents();
      Iterator< EObject > i = contents.iterator();
      while ( i.hasNext() ) {
        EObject eo = i.next();
        if ( eo instanceof Element ) {
          if ( eo instanceof Element ) continue; // went too deep!
          Object r = eval( (Element)eo, deep, seen );
          if ( r == null ) continue; // wrong! null should be ok!
        }
      }
      return result;
    }
    
    public void evaluate( ActionEvent event, Element element ) {
      
    }
  }

  @Override
  public boolean close() {
    Debug.errln("closing TestPlugin!");
    Debug.outln("closing TestPlugin!");
    return true;
  }

  @Override
  public void init() {
    Debug.turnOn();
    Debug.errln("initializing TestPlugin!");
    Debug.outln("initializing TestPlugin!");
//    Configurator c = new Configurator();
//    Configurator c1 = new Configurator();
//    Configurator c2 = new Configurator();
//    Configurator c3 = new Configurator();
//    Configurator c4 = new Configurator();
//    Configurator c5 = new Configurator();
    Method copyElementMethod = TestPlugin.ElementUtils.class.getDeclaredMethods()[ 0 ];
    //c.
    addConfiguration( "MainMenu", "", "copyElement", "Element Utils",
                        copyElementMethod );
    Method copyPackageMethod = TestPlugin.PackageUtils.class.getDeclaredMethods()[ 0 ];
    //c1.
    addConfiguration( "BrowserToolbar", "", "copyPackage", "Package Utils",
                        copyPackageMethod );
    //c2.
    addConfiguration( "Diagram", "Class Diagram", "copyPackage", "Package Utils",
                         copyPackageMethod );
    //c3.
    addConfiguration( "Diagram", "Activity Diagram", "copyPackage", "Package Utils",
                         copyPackageMethod );
//    //c4.
//    addConfiguration( "Diagram", "", "copyPackage", "Package Utils",
//                         copyPackageMethod );
//    //c5.
//    addConfiguration( "Diagram", "", "copyPackage", "Package Utils",
//                         copyPackageMethod );
    ActionsConfiguratorsManager acm =
        ActionsConfiguratorsManager.getInstance();
    //acm.addContainmentBrowserContextConfigurator( c1 );
    //acm.addBaseDiagramContextConfigurator( "Class Diagram", c2 );
    //acm.addBaseDiagramContextConfigurator( "Activity Diagram", c3 );
    //acm.addBaseDiagramContextConfigurator("SysML Package Diagram", c4 );
    //acm.addDiagramCommandBarConfigurator( c5 );
    //acm.addMainMenuConfigurator( c );
    EvaluationConfigurator.getInstance().registerBinaryImplementers(TestPlugin.class.getClassLoader());
    //ApplicationSyncEventSubscriber.subscribe();
    Debug.errln("finished initializing TestPlugin!");
    Debug.outln("finished initializing TestPlugin!");
  }

  @Override
  public boolean isSupported() {
    return true;
  }
}