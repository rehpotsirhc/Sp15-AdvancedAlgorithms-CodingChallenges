import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 1/29/2015.
 */
public class TicTacToe3C {


    private static int[][] board = new int[3][3];
    private static int x = 1;
    private static int o = 2;


    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String firstRow = br.readLine();
        String secondRow = br.readLine();
        String thirdRow = br.readLine();



        board[0] = translateRow(firstRow);
        board[1] = translateRow(secondRow);
        board[2] = translateRow(thirdRow);

        System.out.print(checkBoard());

    }
    private static int[] translateRow(String s)
    {
        int[] row = new int[3];

        row[0] = translateSquare(s.charAt(0));
        row[1] = translateSquare(s.charAt(1));
        row[2] = translateSquare(s.charAt(2));

        return row;
    }
    private static int translateSquare(char c)
    {
        if(c == 'X') return x;
        if(c == '0') return o;
        if(c == '.') return 0;
        return -1;
    }

    private static String checkBoard()
    {

        int xCount = 0;
        int oCount = 0;


        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board.length; c++)
            {
                if(board[r][c] == x) xCount++;
                if(board[r][c] == o) oCount++;
            }
        }

        if(oCount > xCount || xCount > oCount + 1) return "illegal";

        boolean xWon = checkWin(x);
        boolean oWon = checkWin(o);

        if(xWon && oWon) return "illegal";

        if(xWon)
        {
            if(xCount == oCount) return "illegal";
            return "the first player won";
        }
        if(oWon)
        {
            if(xCount > oCount) return "illegal";
            return "the second player won";
        }

        if(xCount + oCount == 9) return "draw";

        if(xCount == oCount) return "first";
        if(xCount == oCount + 1) return "second";






        return "-1";
    }

    private static boolean checkWin(int p)
    {
        //check rows
        for(int r = 0; r < board[0].length; r++) {
            if (board[r][0] == p && board[r][1] == p && board[r][2] == p) return true;
        }

        //check columns
        for(int c = 0; c < board.length; c++) {
            if (board[0][c] == p && board[1][c] == p && board[2][c] == p) return true;
        }

        //check diagonals
        if(board[0][0] == p && board[1][1] == p && board[2][2] == p) return true;
        if(board[0][2] == p && board[1][1] == p && board[2][0] == p) return true;

        return false;
    }
}
