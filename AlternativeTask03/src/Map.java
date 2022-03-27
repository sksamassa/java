import java.util.ArrayList;

public class Map {
    public int n;
    public ArrayList<ArrayList<Integer>> map;
    public Map( int nN ) {
        n = nN;
        map = new ArrayList<ArrayList<Integer>>();
        for ( int i = 0; i < n; i++ ) {
            map.add( new ArrayList<Integer>() );
            for ( int j = 0; j < n; j++ )
                map.get( i ).add( 0 );
        }
    }
    public void printMap() {
        for ( int i = 0; i < n; i++ ) {
            ArrayList<Integer> line = map.get( i );
            for ( int j = 0; j < n; j++ ) {
                int cell = line.get( j );
                if ( cell == 0 ) {
                    System.out.print( "." );
                } else if ( cell == -1 ) {
                    System.out.print( "s" );
                } else if ( cell == -2 ) {
                    System.out.print( "f" );
                } else if ( cell == -3 ) {
                    System.out.print( "#" );
                } else if ( cell == -4 ) {
                    System.out.print( "*" );
                } else if ( cell >= 1 ) {
                    System.out.print( cell );
                } else {
                    System.out.println( "Программе нужна отладка. Неизвестный тип клетки на карте" );
                    System.exit( 1 );
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
