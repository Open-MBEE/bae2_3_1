/**
 * 
 */
package gov.nasa.jpl.ae.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

// import java.io.*;
// import java.net.*;

/**
 * @author bclement
 * 
 */
public class SocketClient {

  protected Socket sock = null;
  protected DataOutputStream dataOutputStream = null;
  protected DataInputStream dataInputStream = null;
  protected boolean connected = false;

  // default host & port
  String hostName = "127.0.0.1";
  int port = 5432;
  
  public SocketClient( String hostName, int port ) {
    init( hostName, port );
  }
  
  protected boolean init( String hostName, int port ) {
    this.hostName = hostName;
    this.port = port;
    try {
      sock = new Socket( hostName, port );
      dataOutputStream = new DataOutputStream( sock.getOutputStream() );
      dataInputStream =  new DataInputStream( sock.getInputStream() );
      connected = true;
    } catch ( UnknownHostException e ) {
      e.printStackTrace();
      return false;
    } catch ( IOException e ) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public void close() {
    try {
      dataOutputStream.close();
      dataInputStream.close();
      sock.close();
    } catch ( IOException e ) {
      e.printStackTrace();
    }
    connected = false;
  }

  public void send( double... doubleArray ) throws IOException {
    String formatString = doubleArray.length + "d";
    Debug.outln("sending int size of format string: " + formatString.length() );
    dataOutputStream.writeInt( formatString.length() );
    dataOutputStream.flush();
    Debug.outln("sending formatString: " + formatString );
    dataOutputStream.writeBytes( formatString );
    dataOutputStream.flush();
    int size = doubleArray.length * 8;
    Debug.outln("sending int size of double array: " + size );    
    dataOutputStream.writeInt( size );
    dataOutputStream.flush();
    for ( double d : doubleArray ) {
      Debug.outln("sending double " + d );
      dataOutputStream.writeDouble( d );
    }
    dataOutputStream.flush();
  }

  /**
   * @return the sock
   */
  public Socket getSock() {
    return sock;
  }

  /**
   * @param sock the sock to set
   */
  public void setSock( Socket sock ) {
    this.sock = sock;
  }

  /**
   * @return the dataOutputStream
   */
  public DataOutputStream getDataOutputStream() {
    return dataOutputStream;
  }

  /**
   * @param dataOutputStream the dataOutputStream to set
   */
  public void setDataOutputStream( DataOutputStream dataOutputStream ) {
    this.dataOutputStream = dataOutputStream;
  }

  /**
   * @return the dataInputStream
   */
  public DataInputStream getDataInputStream() {
    return dataInputStream;
  }

  /**
   * @param dataInputStream the dataInputStream to set
   */
  public void setDataInputStream( DataInputStream dataInputStream ) {
    this.dataInputStream = dataInputStream;
  }

  /**
   * @return the connected
   */
  public boolean isConnected() {
    return connected;
  }

  /**
   * @param connected the connected to set
   */
  public void setConnected( boolean connected ) {
    this.connected = connected;
  }

  /**
   * @return the hostName
   */
  public String getHostName() {
    return hostName;
  }

  /**
   * @param hostName the hostName to set
   */
  public void setHostName( String hostName ) {
    this.hostName = hostName;
  }

  /**
   * @return the port
   */
  public int getPort() {
    return port;
  }

  /**
   * @param port the port to set
   */
  public void setPort( int port ) {
    this.port = port;
  }

  public static void main( String[] args ) throws IOException {

    // default host & port
    String hostName = "127.0.0.1";
    //int port = 5432;
    int port = 60002;
    
    // host & port from command line arguments
    if ( args != null && args.length > 0 ) {
      hostName = args[0];
      if ( args.length > 1 ) {
        port = Integer.parseInt( args[1] );
      }
    }

    SocketClient socketClient = new SocketClient( hostName, port );

    // first int is for determining endianness
    socketClient.dataOutputStream.writeInt(1);
    double doubleArray[] = {0.0, 1.0, 3.14, 2.71, -1.1};
    for ( int i=0; i<10; ++i ) {
      socketClient.send( doubleArray );
      for ( int j=0; j<doubleArray.length; ++j ) {
        doubleArray[j] += Math.random();
      }
      try {
        Thread.sleep( 2000 );
      } catch ( InterruptedException e ) {
        e.printStackTrace();
      }
    }
    socketClient.dataOutputStream.writeInt(-1);
    socketClient.dataOutputStream.flush();

    socketClient.close();
  }
  /**
   * @param args
   */
  // public static void main( String[] args ) {
  // // TODO Auto-generated method stub
  //
  // }


}
