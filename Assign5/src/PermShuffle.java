import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Chris on 2/2/2015.
 */
class PermShuffle {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String[] nextLine = br.readLine().split(" ");
            int N = Integer.parseInt(nextLine[0]);
            int M = Integer.parseInt(nextLine[1]);
            String[] perm = br.readLine().split(" ");
            int gapC = 2 * N;
            int[] gaps = new int[gapC];
            int[][] permutation = new int[N + 1][2];

            for (int i = 1; i <= N; i++) {
                permutation[i][1] = i;
                permutation[i][0] = Integer.parseInt(perm[i - 1]);
            }
            Arrays.sort(permutation, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            while (M-- > 0) {
                String[] range = br.readLine().split(" ");
                int li = Integer.parseInt(range[0]);
                int ri = Integer.parseInt(range[1]);

                for (int j = 2 *li; j <= 2 *ri - 1; j ++) {

                    gaps[j] = -1;
                }
            }

            int sum = 0;
            for (int i = 2; i < gapC; i+= 2) {

                if(gaps[i] == -1) gaps[i] = sum;
                else gaps[i] = ++sum;
            }


            boolean possible = true;

            for(int i = 1; i <=N;i++)
            {
                if(permutation[i][1] == i) continue;
                else
                {
                    int whereIWas = 2 * permutation[i][1] -2;
                    int whereIAm  = 2 * i - 2;
                    if(gaps[whereIWas] != gaps[whereIAm])
                    {
                        possible = false;
                        break;
                    }
                    else continue;
                }
            }



//            for (int i = 1; i <= N; i++) {
//
//                if (permutation[i] == i) continue;
//                else if (ladder[i] != 1) {
//                    possible = false;
//                    break;
//                } else {
//                    int j = i;
//                    boolean matched = false;
//                    while (j <= N && ladder[j] == 1) {
//                        if (permutation[j++] == i) {
//                            matched = true;
//                            break;
//                        }
//
//                    }
//                    j = i;
//                    while (!matched && j >= 1 && ladder[j] == 1) {
//                        if (permutation[j--] == i) {
//                            matched = true;
//                            break;
//                        }
//                    }
//
//                    if (!matched) {
//                        possible = false;
//                        break;
//                    }
//                }
//
//            }
            if (possible) System.out.println("Possible");
            else System.out.println("Impossible");
        }
    }
}
