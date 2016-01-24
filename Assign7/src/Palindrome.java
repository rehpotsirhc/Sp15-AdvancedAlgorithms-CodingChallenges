import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by Chris on 2/25/2015.
 */
public class Palindrome {


    //the string plus concatenated with it's reversal
    private static char[] pr;
    private static int[] z;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String palindrome = br.readLine();
        int length = palindrome.length();
        concatenateReversal(palindrome, length);
        computeZValues();

        System.out.println(calculateDegree(length));

    }

    private static void concatenateReversal(String p, int length) {
        pr = new char[length * 2];
        for (int i = 0; i < length; i++) {
            pr[i] = p.charAt(i);
            pr[i + length] = p.charAt(length - i - 1);
        }
    }

    private static void computeZValues() {
        z = new int[pr.length];
        int l = 0, r = 0, p, i;

        for (int k = 1; k < z.length; k++) {

            //k it outside of the current z-box
            //compute z-box by comparing to prefix
            if (r < k) {
                i = 0;
                while ((k + i) < z.length && pr[i] == pr[k + i]) i++;

                z[k] = i;

                if (i > 0) {
                    l = k;

                    r = l + i - 1;
                }

            }
            //k is inside the current z-box
            //compute z[k] using z-box to avoid re-computations
            else {
                p = k - l;
                //get z[p]
                //pr[0...p] == pr[l...k] (identical strings)
                //pr[p] == pr[k] (therefore identical characters at these indices)
                //z[p] == z[k] (the z values will be the same extending to r...and maybe after)
                // so if z[p] < r - k +1(the z box after k), just copy the z value from p to k
                if (z[p] < r - k + 1) z[k] = z[p];
                else {
                    //the z[k] might extend beyond r, so check for it

                    i = r + 1;
                    while (i < pr.length && pr[i] == pr[i - k]) i++;
                    z[k] = i - k;
                    r = i - 1;
                    l = k;

                }
            }
        }
    }

    private static long calculateDegree(int length)
    {
        //loop through the reversed(second half) of the concatenated string
        long[] ks = new long[length + 1];
        long totalK = 0;
//        for(int i = length; i < pr.length; i++)
//        {
//            //checks the z-values
//            //we've found a palindrome
//            if(z[i] == pr.length - i)
//            {
//                int k = ks[pr.length - i] + 1;
//                ks[(pr.length - i) /2]  = k;
//                totalK += k;
//
//            }
//        }
        for (int i = 1; i <= length; i++)
            if (z[pr.length - i] == i) {
                long k = ks[i / 2] + 1;
                ks[i] = k;
                totalK += k;
            }

        return totalK;
    }
}
