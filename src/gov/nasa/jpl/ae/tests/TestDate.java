package gov.nasa.jpl.ae.tests;

import java.text.SimpleDateFormat;
import java.util.Date;

import gov.nasa.jpl.mbee.util.TimeUtils;

public class TestDate {

  public static void main( String[] args ) {
    String tString = "2012-07-17T18:25:00.000-0700";
    String format = TimeUtils.timestampFormat;
    SimpleDateFormat df = new SimpleDateFormat(format);
    try {
      Date d = df.parse( tString );
      assert( d != null );
      System.out.println("worked! " + format + " " + tString + " = " + d);
    } catch ( java.text.ParseException e1 ) {
      e1.printStackTrace();
    }

    tString = "2012-07-17 18:25:00.000-0700";
    format = TimeUtils.timestampFormat.replace( "'T'", "" );
    df = new SimpleDateFormat( format );
    try {
      Date d = df.parse( tString );
      assert( d != null );
      System.out.println("worked! " + format + " " + tString + " = " + d);
    } catch ( java.text.ParseException e1 ) {
      e1.printStackTrace();
    }

    tString = "2012-07-17T18:25:00-0700";
    format = TimeUtils.timestampFormat.replace( ".SSS", "" );
    df = new SimpleDateFormat( format );
    //df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    try {
      Date d = df.parse( tString );
      assert( d != null );
      System.out.println("worked! " + format + " " + tString + " = " + d);
    } catch ( java.text.ParseException e1 ) {
      e1.printStackTrace();
    }

    tString = "2012-07-17T18:25:00-0700";
    format = TimeUtils.timestampFormat;
    df = new SimpleDateFormat( format );
    try {
      Date d = df.parse( tString );
      assert( d != null );
      System.err.println("should not have worked! " + format + " " + tString + " = " + d);
      (new Exception()).printStackTrace();
    } catch ( java.text.ParseException e1 ) {
      System.out.println("Correctly did not work! " + format + " " + tString);
      //e1.printStackTrace();
    }

    tString = "2012-07-17 18:25:00.000";
    format = TimeUtils.timestampFormat.replace( "'T'", "" ).replace( "Z", "" );
    df = new SimpleDateFormat( format );
    try {
      Date d = df.parse( tString );
      assert( d != null );
      System.out.println("worked! " + format + " " + tString + " = " + d);
    } catch ( java.text.ParseException e1 ) {
      e1.printStackTrace();
    }

    tString = "2012-07-17 18:25:00";
    format = TimeUtils.timestampFormat.replace( "'T'", "" ).replace( ".SSSZ", "" );
    df = new SimpleDateFormat(format);
    try {
      Date d = df.parse( tString );
      assert( d != null );
      System.out.println("worked! " + format + " " + tString + " = " + d);
    } catch ( java.text.ParseException e1 ) {
      e1.printStackTrace();
    }
  
  }
}
