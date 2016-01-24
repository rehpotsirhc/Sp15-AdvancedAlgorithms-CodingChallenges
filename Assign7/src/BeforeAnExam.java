import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris Johnson on 2/25/2015.
 */
/*
Just take the minimum of each, then take up to the max of each, depending on how many hours are still needed
If sum of min is greater or sum of max is less than the needed sum, then its impossible
 */
public class BeforeAnExam {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");

        int d = Integer.parseInt(line[0]);
        int sumTime = Integer.parseInt(line[1]);
        int minSum = 0, maxSum = 0;
        int[][] ranges = new int[d][2];
        for (int j = 0; j < d; j++) {
            line = br.readLine().split(" ");
            ranges[j][0] = Integer.parseInt(line[0]);
            ranges[j][1] = Integer.parseInt(line[1]);
            minSum += ranges[j][0];
            maxSum += ranges[j][1];
        }
        if (minSum > sumTime || maxSum < sumTime) System.out.println("NO");
        else {
            System.out.println("YES");
            int stillNeed = sumTime - minSum;
            int takingInAddition;
            int taking;
            for (int i = 0; i < d; i++) {
                //how much can I take from each range?
                //greedily take as much as possible starting from the first(i = 0) range
                takingInAddition = Math.min(stillNeed, ranges[i][1] - ranges[i][0]);
                //add it to the minimum because I'm taking at least that much
                taking = ranges[i][0] + takingInAddition;
                //keep track of how much I still need
                stillNeed -= takingInAddition;

                System.out.print(taking);
                if(i < (d-1)) System.out.print(" ");
            }
        }
    }
}
