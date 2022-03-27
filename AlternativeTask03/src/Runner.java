import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Runner {
    public static Map createPath( Map map, Map mapWithGradient ) {
        int n = map.n;
        int curI = 0;
        int curJ = 0;
        for ( int i = 0; i < n; i++ )
            for ( int j = 0; j < n; j++ ) {
                if ( map.map.get( i ).get( j ) == -2 ) {
                    curI = i;
                    curJ = j;
                    break;
                }
            }
        if ( mapWithGradient.map.get( curI ).get( curJ ) == 0 )
            return null;
        Map mapWithPath = new Map( n );
        for ( int i = 0; i < n; i++ )
            for ( int j = 0; j < n; j++ ) {
                int cell = map.map.get( i ).get( j );
                mapWithPath.map.get( i ).set( j, cell );
            }
        while ( true ) {
            int cell = mapWithGradient.map.get( curI ).get( curJ );
            if ( cell == 1 )
                break;
            if ( ( curI > 0 ) && ( mapWithGradient.map.get( curI-1 ).get( curJ ) == cell-1 ) ) {
                mapWithPath.map.get( curI ).set( curJ, -4 );
                curI--;
                continue;
            }
            if ( ( curI < n-1 ) && ( mapWithGradient.map.get( curI+1 ).get( curJ ) == cell-1 ) ) {
                mapWithPath.map.get( curI ).set( curJ, -4 );
                curI++;
                continue;
            }
            if ( ( curJ > 0 ) && ( mapWithGradient.map.get( curI ).get( curJ-1 ) == cell-1 ) ) {
                mapWithPath.map.get( curI ).set( curJ, -4 );
                curJ--;
                continue;
            }
            if ( ( curJ < n-1 ) && ( mapWithGradient.map.get( curI ).get( curJ+1 ) == cell-1 ) ) {
                mapWithPath.map.get( curI ).set( curJ, -4 );
                curJ++;
                continue;
            }
        }
        for ( int i = 0; i < n; i++ )
            for ( int j = 0; j < n; j++ ) {
                if ( map.map.get( i ).get( j ) == -2 ) {
                    mapWithPath.map.get( i ).set( j, -2 );
                }
            }
        return mapWithPath;
    }
    public static Map createGradient( Map map ) {
        int n = map.n;
        Map mapWithGradient = new Map( n );
        for ( int i = 0; i < n; i++ )
            for ( int j = 0; j < n; j++ ) {
                int cell = map.map.get( i ).get( j );
                if ( cell == -1 )
                    mapWithGradient.map.get( i ).set( j, 1 );
                else if ( cell == -2 )
                    mapWithGradient.map.get( i ).set( j, 0 );
                else if ( ( cell == 0 ) || ( cell == -3 ) )
                    mapWithGradient.map.get( i ).set( j, cell );
            }
        boolean finish;
        while ( true ) {
            finish = true;
            for ( int i = 0; i < n; i++ )
                for ( int j = 0; j < n; j++ ) {
                    int cell = mapWithGradient.map.get( i ).get( j );
                    if ( cell == 0 ) {
                        int minPositiveNeighbors = 0;
                        if ( ( i > 0 ) && ( mapWithGradient.map.get( i-1 ).get( j ) > minPositiveNeighbors ) ) {
                            minPositiveNeighbors = mapWithGradient.map.get( i-1 ).get( j );
                        }
                        if ( ( i < n-1 ) && ( mapWithGradient.map.get( i+1 ).get( j ) > minPositiveNeighbors ) ) {
                            minPositiveNeighbors = mapWithGradient.map.get( i+1 ).get( j );
                        }
                        if ( ( j > 0 ) && ( mapWithGradient.map.get( i ).get( j-1 ) > minPositiveNeighbors ) ) {
                            minPositiveNeighbors = mapWithGradient.map.get( i ).get( j-1 );
                        }
                        if ( ( j < n-1 ) && ( mapWithGradient.map.get( i ).get( j+1 ) > minPositiveNeighbors ) ) {
                            minPositiveNeighbors = mapWithGradient.map.get( i ).get( j+1 );
                        }
                        if ( minPositiveNeighbors > 0 ) {
                            finish = false;
                            mapWithGradient.map.get( i ).set( j, minPositiveNeighbors + 1 );
                        }
                    }
                }
            if ( finish )
                break;
        }
        return mapWithGradient;
    }
    public static Map readMap( String filename ) {
        Map map = null;
        try {
            BufferedReader br = new BufferedReader( new FileReader( filename ) );
            String nStr = br.readLine();
            if ( nStr == null ) {
                System.out.println( "Некорректный формат входного файла. " +
                        "Не могу прочитать строку с размерностью карты" );
                System.exit( 1 );
            }
            int n = 0;
            try {
                n = Integer.parseInt( nStr );
            } catch ( NumberFormatException e ) {
                System.out.println( "Некорректный формат входного файла. " +
                        "Не могу прочитать натуральную размерность карты" );
                System.exit( 1 );
            }
            if ( n <= 2 ) {
                System.out.println( "Некорректный формат входного файла. " +
                        "Резмерность карты '" + n + "'" );
                System.exit( 1 );
            }
            map = new Map( n );
            for ( int i = 0; i < n; i++ ) {
                String s = br.readLine();
                if ( s == null ) {
                    System.out.println( "Некорректный формат входного файла. Преждевременный конец" +
                            "файла" );
                            System.exit( 1 );
                }
                if ( s.length() != n ) {
                    System.out.println( "Некорректный формат входного файла. Строка некорректного" +
                            "размера" );
                            System.exit( 1 );
                }
                for ( int j = 0; j < n; j++ ) {
                    switch ( s.charAt( j ) ) {
                        case '.':
                            map.map.get( i ).set( j, 0 );
                            break;
                        case 's':
                            map.map.get( i ).set( j, -1 );
                            break;
                        case 'f':
                            map.map.get( i ).set( j, -2 );
                            break;
                        case '#':
                            map.map.get( i ).set( j, -3 );
                            break;
                        default:
                            System.out.println( "Некорректный формат входного файла. На карте недопустимый" +
                                    "знак '" +
                            s.charAt( j ) + "'" );
                            System.exit( 1 );
                    }
                }
            }
            int startCounter = 0;
            for ( int i = 0; i < n; i++ )
                for ( int j = 0; j < n; j++ )
                    if ( map.map.get( i ).get( j ) == -1 )
                        startCounter++;
            if ( startCounter != 1 ) {
                System.out.println( "Некорректный формат входного файла. " +
                        "На карте должно быть ровно один вход" );
                System.exit( 1 );
            }
            int finishCounter = 0;
            for ( int i = 0; i < n; i++ )
                for ( int j = 0; j < n; j++ )
                    if ( map.map.get( i ).get( j ) == -2 )
                        finishCounter++;
            if ( finishCounter != 1 ) {
                System.out.println( "Некорректный формат входного файла. " +
                        "На карте должно быть ровно один ввыход" );
                System.exit( 1 );
            }
        } catch ( FileNotFoundException e ) {
            System.out.println( "Не могу открыть файл '" + filename + "': " + e.getMessage() );
            System.exit( 1 );
        } catch ( IOException e ) {
            System.out.println( "Ошибка чтения из файла '" + filename + "': " + e.getMessage() );
            System.exit( 1 );
        }
        return map;
    }

}
