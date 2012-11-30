/**
 * 
 */
package gov.nasa.jpl.ae.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

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
      if ( Debug.isOn() ) Debug.outln( getClass().getName()
                                       + " creating socket on host " + hostName
                                       + " and port " + port );
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

  public void send( Vector<Double> doubleVector) throws IOException {
    double[] doubleArray = new double[doubleVector.size()];
    int cnt = 0;
    for ( Double d : doubleVector ) {
      doubleArray[cnt++] = d;
    }
    send( doubleArray );
  }
  
  public void send( double... doubleArray ) throws IOException {
    String formatString = doubleArray.length + "d";
    if ( Debug.isOn() ) Debug.outln("sending int size of format string: " + formatString.length() );
    dataOutputStream.writeInt( formatString.length() );
    dataOutputStream.flush();
    if ( Debug.isOn() ) Debug.outln("sending formatString: " + formatString );
    dataOutputStream.writeBytes( formatString );
    dataOutputStream.flush();
    int size = doubleArray.length * 8;
    if ( Debug.isOn() ) Debug.outln("sending int size of double array: " + size );    
    dataOutputStream.writeInt( size );
    dataOutputStream.flush();
    for ( double d : doubleArray ) {
      if ( Debug.isOn() ) Debug.outln("sending double " + d );
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

    Debug.turnOn();
    
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
    // next int is the number of lines to plot
    socketClient.dataOutputStream.writeInt(4);
    // send array with x value followed by a y value to add to each line
    double doubleArray[] = {0.0, 1.0, 3.14, 2.71, -1.1};
    double lineId = 3.14;
    int numPoints = 60;
    double fixedLine[] = new double[2*numPoints+1];//{lineId, 0, 0.0, 1, 1.0, 2, 2.0, 3, 3.0, 4, 4.0, 5, 5.0, 6, 6.0, 7, 7.0, 8, 8.0, 9, 9.0};
    fixedLine[0] = lineId;
    for ( int j=1; j<fixedLine.length; j+=2 ) {
      fixedLine[j] = 0.5*(j-1);
      fixedLine[j+1] = numPoints * 0.5 * (Math.sin(j*6.2832/fixedLine.length)+1);
    }
    for ( int i=0; i<numPoints; ++i ) {
      socketClient.send( doubleArray );
      for ( int j=0; j<doubleArray.length; ++j ) {
        doubleArray[j] += 0.5 + Math.random();
      }
      if ( (i+1) % 10 == 0 ) {
        socketClient.send( fixedLine );
        for ( int j=2; j<fixedLine.length; j+=2 ) {
          fixedLine[j] += numPoints * 0.25 * ( 0.5 - Math.random() );
        }
      }
      try {
        Thread.sleep( 400 ); // millis
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
