import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        if ( args.length != 1 ) {
            System.out.println( "В командной строке должно быть имя файла с описанием карты" );
            System.exit( 1 );
        }
        Map map = Runner.readMap( args[ 0 ] );
        map.printMap();
        Map mapWithGradient = Runner.createGradient( map );
        Map mapWithPath = Runner.createPath( map, mapWithGradient );
        if ( mapWithPath == null ) {
            System.out.println( "Путь от входа до выхода невозможно построить" );
        } else
            mapWithPath.printMap();
    }
}
