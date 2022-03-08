import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        String[][] arr = gameConstructor();
        printAr(arr);
        getPath(arr);
        printAr(arr);
    }
    static String[][] gameConstructor()
    {
        String[][] ar = new String[4][4];
        ar[0][0] = "#";
        ar[0][1] = "s";
        ar[0][2] = "#";
        ar[0][3] = "#";
        ar[1][0] = "#";
        ar[1][1] = ".";
        ar[1][2] = ".";
        ar[1][3] = ".";
        ar[2][0] = ".";
        ar[2][1] = ".";
        ar[2][2] = "#";
        ar[2][3] = ".";
        ar[3][0] = "#";
        ar[3][1] = "#";
        ar[3][2] = "f";
        ar[3][3] = ".";

        return ar;
    }
    static void printAr(String[][] ar)
    {
        for(int i = 0; i < ar.length; ++i)
        {
            for(int j = 0; j < ar[i].length; ++j)
            {
                System.out.print(ar[i][j] + " ");
            }
            System.out.println();
        }System.out.println();
    }
    static void getPath(String[][] ar)
    {
        for(int i = 0; i < ar.length; ++i)
        {
            for(int j = 0; j < ar[i].length; ++j)
            {
                while(ar[i][j] == "." )
                {
                    ar[i][j] = "*";
                }
            }
            ar[2][0] = ar[2][1] = ".";
        }
    }
}
