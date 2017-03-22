package gov.nasa.jpl.ae.util;

public class Math {
    
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
      } else if ( rd1 < 0 && -Double.MAX_VALUE - rd1 >= rd2 ) {
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
}
