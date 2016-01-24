import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Chris on 2/3/2015.
 */
class PPTest {

    private static int N;
    private static int W;
    private static int[][] cpt;
    private static ArrayList<Integer> history;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String[] nextLine = br.readLine().split(" ");
            N = Integer.parseInt(nextLine[0]);
            W = Integer.parseInt(nextLine[1]);


            cpt = new int[N][2];
            history = new ArrayList<Integer>();

            for (int i = 0; i < N; i++) {
                nextLine = br.readLine().split(" ");
                int Ci = Integer.parseInt(nextLine[0]);
                int Pi = Integer.parseInt(nextLine[1]);
                int Ti = Integer.parseInt(nextLine[2]);

                cpt[i][0] = Ci * Pi;
                cpt[i][1] = Ti;

            }
            sortTests();
            findMax();
            Collections.sort(history);
            System.out.println(history.get(history.size() - 1));

        }
    }

    private static void findMax() {

        int cost = 0;
        int max = 0;
        ArrayList<Integer> fulcrums = new ArrayList<Integer>();
        int endIndex = -1;

        for (int i = 0; i < cpt.length; i++) {
            int nextCost = cpt[i][1];
            if (cost + nextCost <= W) {
                max += cpt[i][0];
                cost += nextCost;

                if (cost == W) {
                    endIndex = i;
                    break;
                }
            } else //cost will bring us over the limit
            {
                fulcrums.add(i);
                continue;
            }
            endIndex = i;
        }
        history.add(max);
        //cost <= W at this point

        for (Integer fulcrum : fulcrums) {

            for (int i = fulcrum + 1; i <= endIndex; i++) {
                if (!fulcrums.contains(i)) {
                    max -= cpt[i][0];
                    cost -= cpt[i][1];
                }
            }
            max += cpt[fulcrum][0];
            cost += cpt[fulcrum][1];
            int tempfulcrum = fulcrum;
            while (tempfulcrum-- >= 0) {
                int tempMax = max;
                int tempCost = cost;
                for (int i = tempfulcrum; i >= 0; i--) {
                    if (!fulcrums.contains(i)) {
                        int posNextCost = tempCost - cpt[i][1];
                        if (posNextCost > W) {
                            tempMax -= cpt[i][0];
                            tempCost -= cpt[i][1];
                        } else {
                            history.add(tempMax - cpt[i][0]);
                            break;
                        }
                    }

                }

            }

            max -= cpt[fulcrum][0];
            cost -= cpt[fulcrum][1];
        }


    }


    private static void sortTests() {
        Arrays.sort(cpt, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                double gain1 = (double) o1[0];
                double cost1 = (double) o1[1];
                double gain2 = (double) o2[0];
                double cost2 = (double) o2[1];
                double costRatio1 = gain1 / cost1;
                double costRatio2 = gain2 / cost2;
                if (costRatio1 > costRatio2) return -1;
                else if (costRatio1 < costRatio2) return 1;
                else {
                    if (gain1 > gain2) return -1;
                    else if (gain1 < gain2) return 1;
                    else return 0;
                }
            }
        });
    }
}
