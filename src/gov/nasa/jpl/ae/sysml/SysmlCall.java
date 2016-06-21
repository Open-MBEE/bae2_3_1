/**
 * 
 */
package gov.nasa.jpl.ae.sysml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;

import gov.nasa.jpl.ae.event.Call;

/**
 * @author bclement
 *
 */
public class SysmlCall extends Call {

  /**
   * 
   */
  public SysmlCall() {
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#getReturnType()
   */
  @Override
  public Class< ? > getReturnType() {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#getParameterTypes()
   */
  @Override
  public Class< ? >[] getParameterTypes() {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#getMember()
   */
  @Override
  public Member getMember() {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#invoke(java.lang.Object, java.lang.Object[])
   */
  @Override
  public
      Object
      invoke( Object obj, Object[] evaluatedArgs )
                                                  throws IllegalArgumentException,
                                                  InstantiationException,
                                                  IllegalAccessException,
                                                  InvocationTargetException {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#isVarArgs()
   */
  @Override
  public boolean isVarArgs() {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#isStatic()
   */
  @Override
  public boolean isStatic() {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see gov.nasa.jpl.ae.event.Call#clone()
   */
  @Override
  public Call clone() {
    // TODO Auto-generated method stub
    return null;
  }

}
