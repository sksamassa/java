import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main( String[] args ) {
        Scanner sc = new Scanner( System.in );
        ArrayList<Integer> digits = new ArrayList<Integer>();

        System.out.print( "Type 8 digits =>" );

        if ( ! sc.hasNextLine() ) {
            System.out.println( "It was necessary to enter a string of numbers" );
            System.exit( 1 );
        }
        String line = sc.nextLine();
        if ( line.length() < 8 ) {
            System.out.println( "String too short" );
            System.exit( 1 );
        }
        if ( line.length() > 8 ) {
            System.out.println( "Long string" );
            System.exit( 1 );
        }
        for ( int i = 0; i < 8; i++ ) {
            char ch = line.charAt( i );
            if ( ( ch < '0' ) || ( ch > '9' ) ) {
                System.out.println( "Incorrect digit '" + ch + "'" );
                System.exit( 1 );
            }
            digits.add( ch - '0' );
        }

        Runner.printExpressions( digits, 100.0 );

    }
}

