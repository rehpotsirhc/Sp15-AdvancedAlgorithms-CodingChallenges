import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Chris Johnson on 1/26/2015.
 */
public class ShortestKingPath3A {


    private static String[][] board = new String[8][8];
    private static Map<Character, Integer> lettersIndex = new HashMap<Character, Integer>();
    private static int[] start;
    private static int[] goal;
    private static int[] curPos;
    private static String[] moveTypes = new String[8];
    private static ArrayList<String[]> paths;
    private static Random r = new Random();


    public static void main(String[] args) throws IOException {

        init();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        start = getColRow(br.readLine());
        goal = getColRow(br.readLine());
        curPos = new int[2];

        goToStart();

        if(atGoal()) System.out.print(0);
        else {
            greedy("");

            String[] shorestPath = paths.get(0);

            for (int i = 1; i < paths.size(); i++) {
                String[] nextPath = paths.get(i);
                if (nextPath.length < shorestPath.length) shorestPath = nextPath;
            }

            System.out.println(shorestPath.length);
            for (String pos : shorestPath) {
                System.out.println(pos);
            }
        }

    }

    private static void goToStart() {
        curPos[0] = start[0];
        curPos[1] = start[1];
    }

    private static void greedy(String breadCrumbs) {

        if (atGoal()) {
            paths.add(breadCrumbs.trim().split(" "));
            return;
        }
            if(breadCrumbs.trim().split(" ").length > 7) {

                goToStart();
                breadCrumbs = "";
            }
            int[][] rankedMoves = rankedNextPositions();
            if(rankedMoves[0][0] != -1)  recurse(rankedMoves[0], breadCrumbs + " " + moveTypes[rankedMoves[0][2]]);

    }

    private static int[][] rankedNextPositions() {
        int[] change;
        int[][] temp = new int[8][3];



        int count = 0;
        int i = 0;
        for (String moveType : moveTypes) {

            change = moveTypeToIndiceChange(moveType);
            if (okayMove(change)) {
                temp[count] = generateNextPos(change, i);
                count++;
            }
            i++;
        }

        int[][] ranked = new int[count][3];

        for(int r = 0; r < count; r++)
        {
            for(int c = 0; c < ranked[0].length;c++)
            {
                ranked[r][c] = temp[r][c];
            }

        }
        Arrays.sort(ranked, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {

                        int colDiff1 = Math.abs(o1[0] - goal[0]);
                        int rowDiff1 = Math.abs(o1[1] - goal[1]);
                        int diff1 = colDiff1 + rowDiff1;

                        int colDiff2 = Math.abs(o2[0] - goal[0]);
                        int rowDiff2 = Math.abs(o2[1] - goal[1]);
                        int diff2 = colDiff2 + rowDiff2;
                        return diff1 - diff2;

                    }
                }
        );
    return ranked;




    }



    private static void recurse(int[] nextPosition, String breadCrumbs) {
        curPos[1] = nextPosition[1];
        curPos[0] = nextPosition[0];
        greedy(breadCrumbs);
    }


    private static int[] getColRow(String pos) {
        return new int[]{lettersIndex.get(pos.charAt(0)), Integer.parseInt(pos.substring(1, 2)) - 1};
    }

    private static void init() {

        paths = new ArrayList<String[]>();

        Character[] letters = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        initLetters(letters);
        initBoard(letters);
        initMoveTypes();


    }

    private static void initLetters(Character[] letters) {
        for (int i = 0; i < letters.length; i++) {
            lettersIndex.put(letters[i], i);
        }
    }

    private static void initBoard(Character[] letters) {
        for (int r = board.length - 1; r <= 0; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = letters[c] + "" + (r + 1);
            }
        }
    }

    //L, R, U, D, LU, LD, RU or RD.
    private static void initMoveTypes() {
        moveTypes[0] = "L";
        moveTypes[1] = "R";
        moveTypes[2] = "U";
        moveTypes[3] = "D";
        moveTypes[4] = "LU";
        moveTypes[5] = "LD";
        moveTypes[6] = "RU";
        moveTypes[7] = "RD";

    }

    private static int[] moveTypeToIndiceChange(String moveType) {
        if (moveType.equals(moveTypes[0])) return new int[]{-1, 0};
        if (moveType.equals(moveTypes[1])) return new int[]{1, 0};
        if (moveType.equals(moveTypes[2])) return new int[]{0, 1};
        if (moveType.equals(moveTypes[3])) return new int[]{0, -1};
        if (moveType.equals(moveTypes[4])) return new int[]{-1, 1}; //L U 0 2
        if (moveType.equals(moveTypes[5])) return new int[]{-1, -1}; //L D 0 3
        if (moveType.equals(moveTypes[6])) return new int[]{1, 1}; //R U 1 2
        if (moveType.equals(moveTypes[7])) return new int[]{1, -1}; //R D 1 3
        else return null;

    }

    private static boolean okayMove(int[] change) {

        int newCol = curPos[0] + change[0];
        int newRow = curPos[1] + change[1];

        return (newRow < board.length && newRow >= 0 && newCol < board[0].length && newCol >= 0);
    }


    private static boolean atGoal() {
        return curPos[0] == goal[0] && curPos[1] == goal[1];
    }



    private static int[] generateNextPos(int[] change, int moveTypeNumber) {
        return new int[]{curPos[0] + change[0], curPos[1] + change[1], moveTypeNumber};
    }



}
