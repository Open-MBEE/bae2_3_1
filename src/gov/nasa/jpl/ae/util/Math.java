package gov.nasa.jpl.ae.util;

import java.math.BigDecimal;

import gov.nasa.jpl.mbee.util.ClassUtils;

public class Math {
  
  static float epsilon = Float.MIN_NORMAL;
  
    Math() {}

    public static double dividedBy( double rd1, double rd2 ) {
      double result;
      // check for overflow
      if ( rd2 >= 0.0 && rd2 < 1.0 && Double.MAX_VALUE * rd2 <= rd1 ) {
        result = Double.MAX_VALUE;
      } else if ( rd2 < 0.0 && rd2 > -1.0 && -Double.MAX_VALUE * rd2 >= rd1 ) {
        result = -Double.MAX_VALUE;
      } else {
        result = rd1 / rd2;
      }
      return result;
    }

    public static float dividedBy( float rd1, float rd2 ) {
      float result;
      // check for overflow
      if ( rd2 >= 0.0 && rd2 < 1.0 && Float.MAX_VALUE * rd2 <= rd1 ) {
        result = Float.MAX_VALUE;
      } else if ( rd2 < 0.0 && rd2 > -1.0 && -Float.MAX_VALUE * rd2 >= rd1 ) {
        result = -Float.MAX_VALUE;
      } else {
        result = rd1 / rd2;
      }
      return result;
    }

    public static long dividedBy( long rd1, long rd2 ) {
      long result;
      // check for divide by 0
      int posNeg = ((rd2 < 0) ^ (rd1 < 0)) ? -1 : 1;
      if ( rd2 == 0 ) {
        result = posNeg * Long.MAX_VALUE;
      } else {
        result = rd1 / rd2;
      }
      return result;
    }

    public static int dividedBy( int rd1, int rd2 ) {
      int result;
      // check for divide by 0
      int posNeg = ((rd2 < 0) ^ (rd1 < 0)) ? -1 : 1;
      if ( rd2 == 0 ) {
        result = posNeg * Integer.MAX_VALUE;
      } else {
        result = rd1 / rd2;
      }
      return result;
    }

    public static int minus( int rd1, int rd2 ) {
      int result;
      // use plus, but don't risk trying to negate +/-inf
      if ( rd2 == Integer.MAX_VALUE ) result = Math.plus( rd1, Integer.MIN_VALUE );
      else if ( rd2 == Integer.MIN_VALUE ) result = Math.plus( rd1, Integer.MAX_VALUE );
      else result = Math.plus(rd1, -rd2);
      return result;
    }

    public static long minus( long rd1, long rd2 ) {
      long result;
      // use plus, but don't risk trying to negate +/-inf
      if ( rd2 == Long.MAX_VALUE ) result = Math.plus( rd1, Long.MIN_VALUE );
      else if ( rd2 == Long.MIN_VALUE ) result = Math.plus( rd1, Long.MAX_VALUE );
      else result = Math.plus(rd1, -rd2);
      return result;
    }

    public static double minus( double rd1, double rd2 ) {
      double result = Math.plus( rd1, -rd2 );
      return result;
    }

    public static float minus( float rd1, float rd2 ) {
      float result = Math.plus( rd1, -rd2 );
      return result;
    }

    public static double plus( double rd1, double rd2 ) {
      double result;
      // check for overflow
      if ( rd1 >= 0 && Double.MAX_VALUE - rd1 <= rd2 ) {
        result = Double.MAX_VALUE;
      } else if ( rd1 < 0 && -Double.MAX_VALUE - rd1 >= rd2 ) {
        result = -Double.MAX_VALUE;
      } else {
        result = rd1 + rd2;
      }
      return result;
    }

    public static float plus( float rd1, float rd2 ) {
      float result;
      // check for overflow
      if ( rd1 >= 0 && Float.MAX_VALUE - rd1 <= rd2 ) {
        result = Float.MAX_VALUE;
      } else if ( rd1 < 0 && -Float.MAX_VALUE - rd1 >= rd2 ) {
        result = -Float.MAX_VALUE;
      } else {
        result = rd1 + rd2;
      }
      return result;
    }

    public static long plus( long rd1, long rd2 ) {
      long result;
      // check for overflow
      if ( rd1 >= 0 && Long.MAX_VALUE - rd1 <= rd2 ) {
        result = Long.MAX_VALUE;
      } else if ( rd1 < 0 && Long.MIN_VALUE - rd1 >= rd2 ) {
        result = Long.MIN_VALUE;
      } else {
        result = rd1 + rd2;
      }
      return result;
    }

    public static int plus( int rd1, int rd2 ) {
      int result;
      // check for overflow
      if ( rd1 >= 0 && Integer.MAX_VALUE - rd1 <= rd2 ) {
        result = Integer.MAX_VALUE;
      } else if ( rd1 < 0 && Integer.MIN_VALUE - rd1 >= rd2 ) {
        result = Integer.MIN_VALUE;
      } else {
        result = rd1 + rd2;
      }
      return result;
    }

    public static double times( double rd1, double rd2 ) {
      double result;
      // check for overflow
      boolean signsEqual = (rd1 > 0) == (rd2 > 0);
      double ad1 = java.lang.Math.abs( rd1 );
      double ad2 = java.lang.Math.abs( rd2 ); //if they're the same sign, take abs...
      if ( rd1 != 0 && rd2 != 0){ //REVIEW - zeroes?
        if ( Double.MAX_VALUE / ad1 <= ad2 ) result = Double.MAX_VALUE * ( signsEqual? 1 : -1);
        else result = rd1 * rd2;
      }
      else  result = rd1 * rd2;
      return result;
    }

    public static float times( float rd1, float rd2 ) {
      float result;
      // check for overflow
      boolean signsEqual = (rd1 > 0) == (rd2 > 0); // REVIEW -- why isn't this >= instead of > ????!!
      float ad1 = java.lang.Math.abs( rd1 );
      float ad2 = java.lang.Math.abs( rd2 ); //if they're the same sign, take abs...
      if ( rd1 != 0 && rd2 != 0){ //REVIEW - zeroes?
        if ( Float.MAX_VALUE / ad1 <= ad2 ) result = Float.MAX_VALUE * ( signsEqual? 1 : -1);
        else result = rd1 * rd2;
      }
      else  result = rd1 * rd2;
      return result;
    }

    public static long times( long rd1, long rd2 ) {
      long result;
      // check for overflow
      boolean signsEqual = (rd1 > 0) == (rd2 > 0);
      long ad1 = java.lang.Math.abs( rd1 );
      long ad2 = java.lang.Math.abs( rd2 ); //if they're the same sign, take abs...
      if ( rd1 != 0 && rd2 != 0){ //REVIEW - zeroes?
        if ( Long.MAX_VALUE / ad1 <= ad2 && Long.MAX_VALUE / ad2 <= ad1 ) result = Long.MAX_VALUE * ( signsEqual? 1 : -1);
        else result = rd1 * rd2;
      }
      else  result = rd1 * rd2;
      return result;
    }

    public static int times( int rd1, int rd2 ) {
      int result;
      // check for overflow
      boolean signsEqual = (rd1 > 0) == (rd2 > 0);
      int ad1 = java.lang.Math.abs( rd1 );
      int ad2 = java.lang.Math.abs( rd2 ); //if they're the same sign, take abs...
      if ( rd1 != 0 && rd2 != 0){ //REVIEW - zeroes?
        if ( ((double)Integer.MAX_VALUE) / ad1 <= ad2 ) result = Integer.MAX_VALUE * ( signsEqual? 1 : -1);
        else result = rd1 * rd2;
      }
      else  result = rd1 * rd2;
      return result;
    }
    
    public static int compare( double rd1, double rd2 ) {
      double diff = minus( rd2, rd1 );
      // See if they are close enough.
      if ( java.lang.Math.abs(diff) < java.lang.Math.abs(rd1) * 1.0e-14 ) {
        return 0;
      }
      return ((Double)rd1).compareTo(rd2);
    }
    public static int compare( float rd1, float rd2 ) {
      double diff = minus( rd2, rd1 );
      // See if they are close enough.
      if ( java.lang.Math.abs(diff) < java.lang.Math.abs(rd1) * 1.0e-5 ) {
        return 0;
      }
      return ((Float)rd1).compareTo(rd2);
    }
    
    public static boolean isInfinity(int i) {
      return i >= Integer.MAX_VALUE;
    }
    public static boolean isNegInfinity(int i) {
      return i <= Integer.MIN_VALUE;
    }
    public static boolean isInfinity(byte i) {
      return i >= Byte.MAX_VALUE;
    }
    public static boolean isNegInfinity(byte i) {
      return i <= Byte.MIN_VALUE;
    }
    public static boolean isInfinity(short i) {
      return i >= Short.MAX_VALUE;
    }
    public static boolean isNegInfinity(short i) {
      return i <= Short.MIN_VALUE;
    }
    public static boolean isInfinity(long i) {
      return i >= Long.MAX_VALUE;
    }
    public static boolean isNegInfinity(long i) {
      return i <= Long.MIN_VALUE;
    }
    public static boolean isInfinity(float i) {
      return i >= Float.MAX_VALUE;
    }
    public static boolean isNegInfinity(float i) {
      return i <= -Float.MAX_VALUE;
    }
    public static boolean isInfinity(double i) {
      return i >= Double.MAX_VALUE;
    }
    public static boolean isNegInfinity(double i) {
      return i <= -Double.MAX_VALUE;
    }
    public static boolean isInfinity(Byte i) {
      return i >= Byte.MAX_VALUE;
    }
    public static boolean isNegInfinity(Byte i) {
      return i <= Byte.MIN_VALUE;
    }
    public static boolean isInfinity(Short i) {
      return i >= Short.MAX_VALUE;
    }
    public static boolean isNegInfinity(Short i) {
      return i <= Short.MIN_VALUE;
    }
    public static boolean isInfinity(Integer i) {
      return i >= Integer.MAX_VALUE;
    }
    public static boolean isNegInfinity(Integer i) {
      return i <= Integer.MIN_VALUE;
    }
    public static boolean isInfinity(Long i) {
      return i >= Long.MAX_VALUE;
    }
    public static boolean isNegInfinity(Long i) {
      return i <= Long.MIN_VALUE;
    }
    public static boolean isInfinity(Float i) {
      return i >= Float.MAX_VALUE;
    }
    public static boolean isNegInfinity(Float i) {
      return i <= -Float.MAX_VALUE;
    }
    public static boolean isInfinity(Double i) {
      return i >= Double.MAX_VALUE;
    }
    public static boolean isNegInfinity(Double i) {
      return i <= -Double.MAX_VALUE;
    }
    public static boolean isInfinity(BigDecimal i) {
      return i.compareTo( BigDecimal.valueOf(Double.MAX_VALUE ) ) >= 0;
    }
    public static boolean isNegInfinity(BigDecimal i) {
      return i.compareTo( BigDecimal.valueOf(-Double.MAX_VALUE ) ) <= 0;
    }
    
    public static boolean isInfinity(Number i) {
      if ( i == null ) return false;
      if ( i instanceof Integer ) {
        return isInfinity((Integer)i);
      }
      if ( i instanceof Long ) {
        return isInfinity((Long)i);
      }
      if ( i instanceof Float ) {
        return isInfinity((Float)i);
      }
      if ( i instanceof Double ) {
        return isInfinity((Double)i);
      }
      if ( i instanceof BigDecimal ) {
        return isInfinity((BigDecimal)i);
      }
      if ( i instanceof Byte ) {
        return isInfinity((Byte)i);
      }
      if ( i instanceof Short ) {
        return isInfinity((Short)i);
      }
      return false;
    }
    
    public static boolean isInfinity(Object i) {
      if ( i == null ) return false;
      if ( i instanceof Number ) {
        return isInfinity( (Number)i );
      }
      Number n = ClassUtils.evaluate( i, Number.class, false );
      return isInfinity( n );
    }

    public static boolean isNegInfinity(Number i) {
      if ( i == null ) return false;
      if ( i instanceof Integer ) {
        return isNegInfinity((Integer)i);
      }
      if ( i instanceof Long ) {
        return isNegInfinity((Long)i);
      }
      if ( i instanceof Float ) {
        return isNegInfinity((Float)i);
      }
      if ( i instanceof Double ) {
        return isNegInfinity((Double)i);
      }
      if ( i instanceof BigDecimal ) {
        return isNegInfinity((BigDecimal)i);
      }
      if ( i instanceof Byte ) {
        return isNegInfinity((Byte)i);
      }
      if ( i instanceof Short ) {
        return isNegInfinity((Short)i);
      }
      return false;
    }
    
    public static boolean isNegInfinity(Object i) {
      if ( i == null ) return false;
      if ( i instanceof Number ) {
        return isNegInfinity( (Number)i );
      }
      Number n = ClassUtils.evaluate( i, Number.class, false );
      return isNegInfinity( n );
    }
    
    public static void main( String[] args ) {
      Long long0 = Long.MAX_VALUE;
      Long long1 = -Long.MAX_VALUE;
      Long long2 = Long.MIN_VALUE;
      Float float1 = Float.MAX_VALUE;
      Float float2 = Float.MIN_VALUE;
      Float float3 = -Float.MAX_VALUE;
      Float float4 = Float.POSITIVE_INFINITY;
      Float float5 = Float.NEGATIVE_INFINITY;
      System.out.println("long0 = Long.MAX_VALUE = " + long0);
      System.out.println("long1 = -Long.MAX_VALUE = " + long1);
      System.out.println("long2 = Long.MIN_VALUE = " + long2);
      System.out.println("float1 = Float.MAX_VALUE = " + float1);
      System.out.println("float2 = Float.MIN_VALUE = " + float2);
      System.out.println("float3 = -Float.MAX_VALUE = " + float3);
      System.out.println("float4 = Float.POSITIVE_INFINITY = " + float4);
      System.out.println("float5 = Float.NEGATIVE_INFINITY = " + float5);
      System.out.println("Float.compare(-Float.MAX_VALUE, Float.NEGATIVE_INFINITY) = " + Float.compare( float3, float5 ));
      System.out.println("Float.compare(Float.MAX_VALUE, Float.POSITIVE_INFINITY) = " + Float.compare( float1, float4 ));
    }
    
}
