import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris Johnson on 2/6/2015.
 */
public class Theleastroundway {


    private static class rNumber {

        public int twoCount;
        public int fiveCount;
        public char dirTwo;
        public char dirFive;
        boolean zero;

        public rNumber(int n) {

            zero = n == 0;
            twoCount = 0;
            fiveCount = 0;
            mul(n);


        }

        public rNumber(int n, rNumber rnTwo, char dirTwo, rNumber rnFive, char dirFive) {

            twoCount = rnTwo.twoCount;
            zero = n == 0 || rnTwo.zero || rnFive.zero;
            fiveCount = rnFive.fiveCount;
            this.dirTwo = dirTwo;
            this.dirFive = dirFive;
            mul(n);

        }

        private void mul(int n) {

            if (n != 0) {
                while (n % 5 == 0) {
                    n /= 5;
                    fiveCount++;
                }
                while (n % 2 == 0) {
                    n /= 2;
                    twoCount++;
                }
            }
            if (zero) {
                if (twoCount > 0) twoCount = 1;
                if (fiveCount > 0) fiveCount = 1;
            }

        }

    }

    private static int N;
    private static rNumber[][] board;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        board = new rNumber[N][N];
        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");

            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(row[j]);
                bestPath(i, j, n);
            }
        }

        rNumber last = board[N - 1][N - 1];
        boolean useTwo = last.twoCount < last.fiveCount;


        StringBuilder path = new StringBuilder();
        int cost = last.fiveCount;
        if (useTwo) cost = last.twoCount;

        int i = N - 1;
        int j = N - 1;
        while (i > 0 || j > 0) {
            char dir = board[i][j].dirFive;
            if (useTwo) dir = board[i][j].dirTwo;
            path.append(dir);
            if (dir == 'D') i--;
            else j--;

        }


        System.out.println(cost);
        System.out.println(path.reverse());
    }


    private static void bestPath(int i, int j, int n) {

        rNumber left = null;
        rNumber up = null;

        if (j - 1 >= 0) left = board[i][j - 1];
        if (i - 1 >= 0) up = board[i - 1][j];

        rNumber r1;
        rNumber r2;
        char c1;
        char c2;

        if (left != null && up != null) {
            if (left.twoCount < up.twoCount) {
                r1 = left;
                c1 = 'R';
            } else {
                r1 = up;
                c1 = 'D';
            }

            if (left.fiveCount < up.fiveCount) {
                r2 = left;
                c2 = 'R';
            } else {
                r2 = up;
                c2 = 'D';
            }
            board[i][j] = new rNumber(n, r1, c1, r2, c2);

        } else if (left != null && up == null) {

            board[i][j] = new rNumber(n, left, 'R', left, 'R');

        } else if (left == null && up != null) {
            board[i][j] = new rNumber(n, up, 'D', up, 'D');
        } else {
            board[i][j] = new rNumber(n);
        }


    }
}





