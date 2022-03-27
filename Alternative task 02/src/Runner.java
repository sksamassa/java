import java.util.Scanner;
import java.util.ArrayList;

public class Runner {

    public static boolean isEqual( double a, double b ) {
        double diff = Math.abs( a-b );
        a = Math.abs( a );
        b = Math.abs( b );
        if ( a < b )
            a = b;
        return diff < 1e-11 * a;
    }

    public static void printOperation( int operation ) {
        switch ( operation ) {
            // No operation
            case 0:
                break;
            // '+'
            case 1:
                System.out.print( "+" );
                break;
            // '-'
            case 2:
                System.out.print( "-" );
                break;
            // '*'
            case 3:
                System.out.print( "*" );
                break;
            // '/'
            case 4:
                System.out.print( "/" );
                break;
            default:
                break;
        }
    }

    public static Double calcValue( ArrayList<Integer> digits, ArrayList<Integer> operations ) {
        ArrayList<Double> values = new ArrayList<Double>();
        for ( int i = 0; i < digits.size(); i++ )
            values.add( ( double ) digits.get( i ) );
        ArrayList<Integer> ops = new ArrayList<Integer>();
        for ( int i = 0; i < operations.size(); i++ )
            ops.add( operations.get( i ) );
        boolean continueFlag;
        while ( ops.size() > 0 ) {
            continueFlag = false;
            for ( int i = 0; i < ops.size(); i++ )
                // No operation
                if ( ops.get( i ) == 0 ) {
                    double val1 = values.get( i );
                    double val2 = values.get( i+1 );
                    values.set( i, val1 * 10.0 + val2 );
                    values.remove( i+1 );
                    ops.remove( i );
                    continueFlag = true;
                    break;
                }
            if ( continueFlag )
                continue;
            for ( int i = 0; i < ops.size(); i++ )
                // '*'
                if ( ops.get( i ) == 3 ) {
                    double val1 = values.get( i );
                    double val2 = values.get( i+1 );
                    values.set( i, val1 * val2 );
                    values.remove( i+1 );
                    ops.remove( i );
                    continueFlag = true;
                    break;
                }
            if ( continueFlag )
                continue;
            for ( int i = 0; i < ops.size(); i++ )
                // '/'
                if ( ops.get( i ) == 4 ) {
                    double val1 = values.get( i );
                    double val2 = values.get( i+1 );
                    if ( val2 == 0.0 )
                        return Double.NaN;
                    values.set( i, val1 / val2 );
                    values.remove( i+1 );
                    ops.remove( i );
                    continueFlag = true;
                    break;
                }
            if ( continueFlag )
                continue;
            double val1 = values.get( 0 );
            double val2 = values.get( 1 );
            // '+'
            if ( ops.get( 0 ) == 1 ) {
                values.set( 0, val1 + val2 );
                // '-'
            } else {
                values.set( 0, val1 - val2 );
            }
            values.remove( 1 );
            ops.remove( 0 );
        }

        return values.get( 0 );
    }

    public static void printExpressions( ArrayList<Integer> digits, Double finalVal ) {
        int n = digits.size();
        long m = 1;
        for ( int i = 0; i < n; i++ )
            m *= 5;
        for ( int index = 0; index < m; index++ ) {
            ArrayList<Integer> operators = new ArrayList<Integer>();
            int val = index;
            for ( int i = 0; i < n-1; i++ ) {
                operators.add( val % 5 );
                val /= 5;
            }
            if ( isEqual( calcValue( digits, operators ),  finalVal ) ) {
                for ( int i = 0; i < n-1; i++ ) {
                    System.out.print( digits.get( i ) );
                    printOperation( operators.get( i ) );
                }
                System.out.print( digits.get( n-1 ) );
                System.out.println( "=" + finalVal );
            }
        }
    }

}
