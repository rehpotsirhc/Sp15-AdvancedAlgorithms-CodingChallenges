import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Chris on 1/27/2015.
 */
public class Lorry3B {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String[] line1 = br.readLine().trim().split(" ");
        int n = Integer.parseInt(line1[0]);
        long v = Integer.parseInt(line1[1]);

        int[][] boats = new int[n][4];

        for (int i = 0; i < n; i++) {

            String[] line2 = br.readLine().trim().split(" ");

            boats[i][0] = Integer.parseInt(line2[0]);
            boats[i][1] = Integer.parseInt(line2[1]);
            boats[i][3] = i + 1;
        }


        sort(boats);
        int vSoFar = 0;
        long maxCap = 0;
        int continueAt = 0;
        StringBuilder boatsUsed = new StringBuilder();
        boatsUsed.append(" ");
        for (int i = 0; i < n; i++) {
            int bv = boats[i][0];
            int bc = boats[i][1];
            if (bv + vSoFar <= v + 1) {

                vSoFar += bv;
                maxCap += bc;
                boats[i][2] = 1;
                boatsUsed.append(boats[i][3] + " ");
            }
            if (vSoFar == v - 1) continueAt = i;
            if (vSoFar >= v) break;

        }
        String boatsUsedString = boatsUsed.toString();

        long toDecrease = Integer.MAX_VALUE;
        int indexToRemove = -1;

        if (vSoFar == v + 1) {
            for (int i = continueAt; i >= 0; i--) {
                if (boats[i][2] == 1) {
                    int temp = boats[i][1];
                    if (temp < toDecrease) {
                        toDecrease = temp;
                        indexToRemove = i;
                    }
                }
            }
        }
        if (indexToRemove != -1) {
            maxCap -= toDecrease;
            boatsUsedString = boatsUsedString.replace(" " + boats[indexToRemove][3] + " ", " ");
        }
        pw.println(maxCap);
        pw.println(boatsUsedString.trim());
        pw.close();
    }


    private static void sort(int[][] boats) {
        Arrays.sort(boats, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                double capacity1 = (double) o1[1];
                double size1 = (double) o1[0];
                double capacity2 = (double) o2[1];
                double size2 = (double) o2[0];
                double capacityToSizeRatio1 = capacity1 / size1;
                double capacityToSizeRatio2 = capacity2 / size2;

                if (capacityToSizeRatio1 > capacityToSizeRatio2) return -1;
                else if (capacityToSizeRatio1 < capacityToSizeRatio2) return 1;
                else {
                    if (capacity1 < capacity2) return -1;
                    else if (capacity1 > capacity2) return 1;
                    else {
                        if (size1 < size2) return -1;
                        else if (size1 > size2) return 1;
                        else return 0;
                    }

                }

            }
        });
    }
}
