import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 1/22/2015.
 */
public class AWinner {


    private static int sortingCol = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int roundTotal = Integer.parseInt(br.readLine());
        Map<String, Integer> playerRows = new HashMap<String, Integer>();
        int[][] scores = new int[20][roundTotal + 1];
        int currentRound = 1;

        while (currentRound <= roundTotal) {


            String[] round = br.readLine().split(" ");
            String player = round[0];

            if (!playerRows.containsKey(player)) playerRows.put(player, playerRows.size());

            if (scores.length - 1 == playerRows.get(player))
                scores = resize(scores, scores.length * 2);

            int playerRow = playerRows.get(player);


            int pointsGained = Integer.parseInt(round[1]);
            if (currentRound > 1) {
                for (int i : playerRows.values()) {
                    int score = scores[i][currentRound - 1];
                    scores[i][currentRound] = score;
                }

            }
            scores[playerRow][currentRound] += pointsGained;


            scores[playerRow][0] = playerRow;


            currentRound++;
        }



       // System.out.print(find(resize(scores, playerRows.size()), playerRows));

        System.out.print(findCorrectMethod(resize(scores, playerRows.size()), playerRows));


    }

    private static int[][] resize(int[][] scores, int row) {

        int col = scores[0].length;
        int[][] temps = new int[row][col];

        int newR = Math.min(scores.length, temps.length);

        for (int r = 0; r < newR; r++) {
            for (int c = 0; c < col; c++) {
                temps[r][c] = scores[r][c];
            }
        }
        return temps;

    }

    private static void sort(int[][] scores, int rCutOff, int c) {

        int r = scores.length - 1;
        sortingCol = c;

        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] list1, int[] list2) {

                int t1 = list1[sortingCol];
                int t2 = list2[sortingCol];
                if (t1 < t2) return -1;
                if (t1 > t2) return 1;
                return 0;
            }
        });


        int max = scores[r][c];

        for (; r >= rCutOff; r--) {
            if (scores[r][c] != max) {
                rCutOff = r + 1;
                if (rCutOff == scores.length - 1) return;


                break;
            }

        }
        c--;


        sort(scores, rCutOff, c);

    }

    private static String find(int[][] scores,  Map<String, Integer> playerRows)
    {
        sort(scores, 0, scores[0].length - 1);

        int playerCount = scores.length;
        for (String player : playerRows.keySet()) {
            if (playerRows.get(player) == scores[playerCount - 1][0]) return player;
        }

        return "";
    }


    private static String findCorrectMethod(int[][] scores,  Map<String, Integer> playerRows) {
        int r = scores.length - 1;
        int rCutOff = 0;
        int c = scores[0].length - 1;
        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] list1, int[] list2) {

                int t1 = list1[list1.length - 1];
                int t2 = list2[list2.length - 1];
                if (t1 < t2) return -1;
                if (t1 > t2) return 1;
                return 0;
            }
        });


        int max = scores[r][c];


        for (; r >= 0; r--) {

            if (scores[r][c] != max) {
                rCutOff = r + 1;
                break;
            }

        }

        //a winner was found
        if(rCutOff == scores.length - 1)
        {
            for (String player : playerRows.keySet()) {

                if (playerRows.get(player) == scores[rCutOff][0])return player;
            }

        }


        //there was a tie at the end
        //search from round 1 (column 1), row by row and column by column until we find the first player to have a score >= max

          for(c = 1; c < scores[0].length - 1;c++)
           {
               for(r = scores.length - 1; r >= rCutOff; r--)
               {

                   if(scores[r][c] >= max)
               {
                   for (String player : playerRows.keySet()) {

                       if (playerRows.get(player) == scores[r][0])return player;
                   }
               }
           }
       }
        return "";
    }
}
