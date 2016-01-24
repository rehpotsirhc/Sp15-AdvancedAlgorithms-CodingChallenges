import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Chris on 2/15/2015.
 */
class DELISH {


    //max & min subarray on same array without overlap
    //compute max and min from left to right and right to left (4 sub arrays)
    //use Kadane algorithm
    //store the max/min at every index
    //the max/min subarray up to position A[i] will be stored at A[i]
    //loop through to find max difference (without overlap) between (minimum left-right and max right-left) and (max left-right and min right-left)
    //then take the maximum of these two as the answer


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            int N = Integer.parseInt(br.readLine());
            long[] delishes = new long[N];

            String[] delishesLine = br.readLine().split("[\\s]+");

            for (int i = 0; i < N; i++) {
                delishes[i] = Integer.parseInt(delishesLine[i]);
            }
            long[] maxLeftRight = kadane(delishes, true, true);
            long[] minLeftRight = kadane(delishes, false, true);
            long[] maxRightLeft = kadane(delishes, true, false);
            long[] minRightLeft = kadane(delishes, false, false);


            long maxDiff = delishes[0] - delishes[1];
            long diff;


            for (int i = 0; i < delishes.length - 1; i++) {
                diff = maxLeftRight[i] - minRightLeft[i + 1];

                if (diff >= maxDiff) maxDiff = diff;

                diff = maxRightLeft[i + 1] - minLeftRight[i];

                if (diff >= maxDiff) maxDiff = diff;

            }
            System.out.println(maxDiff);

        }
    }

    public static long[] kadane(long[] b, boolean maxMin, boolean leftRight) {

        if (b == null || b.length == 0) return null;
        long[] a = new long[b.length];


        int x = init(a.length, leftRight);
        a[x] = b[x];
        long maxHere = b[x];
        for (int i = initI(a.length, leftRight); eqTest(a.length, i, leftRight); i = incDec(i, leftRight)) {
            //take b[i] || b[i] + previous
            maxHere = takeTest(maxMin, b[i], b[i] + maxHere);

            //see if we've found a new maximum/minimum
            a[i] = assign(maxMin, leftRight, maxHere, i, a);
        }
        return a;

    }


    private static int incDec(int i, boolean leftRight) {

        if (leftRight) return ++i;
        else return --i;
    }

    private static int initI(int length, boolean leftRight) {
        if (leftRight) return 1;
        else return length - 2;
    }

    private static int init(int length, boolean leftRight) {
        if (leftRight) return 0;
        else return length - 1;
    }

    private static boolean eqTest(int length, int i, boolean leftRight) {
        if (leftRight) return i < length;
        else return i >= 0;

    }

    private static long takeTest(boolean maxMin, long a1, long a2) {
        if (maxMin) return Math.max(a1, a2);
        else return Math.min(a1, a2);
    }
    private static long assign(boolean maxMin, boolean leftRight, long here, int i, long[] a) {
        if (maxMin)
        {
            if(leftRight)  return Math.max(here, a[i-1]);
            else return Math.max(here, a[i+1]);

        }
        else
        {
            if(leftRight) return Math.min(here, a[i-1]);
            else return Math.min(here, a[i+1]);
        }
    }


}
