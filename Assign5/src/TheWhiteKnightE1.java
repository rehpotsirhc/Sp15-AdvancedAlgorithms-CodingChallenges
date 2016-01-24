import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris Johnson on 2/6/2015.
 */
class TheWhiteKnightE1 {


    private static int N;
    private static int[][] board;
    private static final char knight = 'K';
    private static final char pawn = 'P';


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            int[] knightPos = new int[2];


            //i enumerates rows
            //j enumerates columns
            for (int i = 0; i < N; i++) {
                String row = br.readLine();

                for (int j = 0; j < N; j++) {
                    if (row.charAt(j) == pawn) board[i][j] = 1;
                    else if (row.charAt(j) == knight) {
                        knightPos[0] = i;
                        knightPos[1] = j;
                    }
                }
            }
            for (int j = N - 1; j >= knightPos[1]; j--) {
                for (int i = N - 1; i >= 0; i--) {

                    board[i][j] += max(i, j);
                }
            }

//            int max = 0;
//            for (int i = 0; i < N; i++) {
//
//                int next = board[N-1][i];
//                if (next > max) max = next;
//            }
            // System.out.println(max);
            System.out.println(board[knightPos[0]][knightPos[1]]);


        }

    }

    //possible knight moves
    //i+2, j+1
    //i-2, j+1
    //i+1, j+2
    //i-1, j+2
    private static int max(int i, int j) {

        int m1 = 0;
        int m2 = 0;
        int m3 = 0;
        int m4 = 0;
        if (inBounds(i + 2) && inBounds(j + 1)) m1 = board[i + 2][j + 1];
        if (inBounds(i - 2) && inBounds(j + 1)) m2 = board[i - 2][j + 1];
        if (inBounds(i + 1) && inBounds(j + 2)) m3 = board[i + 1][j + 2];
        if (inBounds(i - 1) && inBounds(j + 2)) m4 = board[i - 1][j + 2];

        return Math.max(Math.max(m1, m2), Math.max(m3, m4));


    }

    private static boolean inBounds(int x) {
        return (x >= 0 && x < N);
    }


}
