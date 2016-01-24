import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class KPrimes {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int countKP;

        int[][] counts = sieveAndCountKPrimes(100001, 6);

        while (T-- > 0) {
            countKP = 0;
            String[] gameLine = br.readLine().split(" ");
            int A = Integer.parseInt(gameLine[0]);
            int B = Integer.parseInt(gameLine[1]);
            int K = Integer.parseInt(gameLine[2]);


           int diff = counts[B][K] - counts[A][K];

            if(counts[B][0] == K && counts[A][0] == K) countKP++;
            if(counts[B][0] != K && counts[A][0] == K) countKP++;

            countKP += diff;

            System.out.println(countKP);

        }

    }


    private static int[][] sieveAndCountKPrimes(int max, int kMax) {


        int[][] counts = new int[max + 1][kMax + 1];

        int p = 2;
        counts[2][0] = 1;
        counts[2][1] = 1;
        boolean sifting = true;
        while (sifting) {
            for (int i = p + p; i < counts.length; i += p) {

                ++counts[i][0];

            }
            if (p + 1 >= counts.length) break;
            for (int i = p + 1; i < counts.length; i++) {

                // a prime was found
                if (counts[i][0] == 0) {
                   ++counts[i][0];
                    p = i;
                    break;
                }
                if (i + 1 == counts.length) sifting = false;
            }


        }
        for(int i = 3; i < max; i++)
        {
            for(int n = 1; n <= 6; n++) {
                if (n <= kMax)
                {
                    counts[i][n] = counts[i - 1][n];

                    if(counts[i][0] == n) counts[i][n]++;
                }
            }
        }

        return counts;

    }

}
