import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 2/8/2015.
 */
class PPTestKnapSackAlg {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String[] nextLine = br.readLine().split(" ");
            int N = Integer.parseInt(nextLine[0]);
            int W = Integer.parseInt(nextLine[1]);


            //cpt[0] will hold the value of each question(value of each item in knapsack)
            //cpt[1] will hold the time to study (weight in knapsack)
            int[][] cpt = new int[N][2];


            for (int i = 0; i < N; i++) {
                nextLine = br.readLine().split(" ");
                int Ci = Integer.parseInt(nextLine[0]);
                int Pi = Integer.parseInt(nextLine[1]);
                int Ti = Integer.parseInt(nextLine[2]);

                if(Ti <= W) {
                    cpt[i][0] = Ci * Pi;
                    cpt[i][1] = Ti;
                }

            }

            //each test takes an integer number of minutes from 1- W minutes
            //need a column for each of these
            //need a row for each of the N
            //plus an extra row and column (filled with 0s) to make the knapsack algorithm easier to implement
            int[][] costTable = new int[N + 1][W + 1];

            System.out.println(knapsack(costTable, cpt));
        }
    }


    //see http://java.dzone.com/articles/knapsack-problem for an explanation
    //rows are values
    //columns are weight
    public static int knapsack(int[][] maxValues, int[][] valueWeight)
    {

        for(int i = 1; i < maxValues.length;i++)
        {
            for(int w = 1; w < maxValues[0].length; w++)
            {

                int weightOfItemI = valueWeight[i - 1][1];

                if(weightOfItemI <= w)
                {
                    int valueOfItemI = valueWeight[i - 1][0];

                    //the maximum of
                    // 1) carrying over the previous item(not adding any items)
                    //and
                    // 2) the current item + whatever we can fit with the leftover weight
                                                //this is the previous item at the leftover weight
                    maxValues[i][w] =  Math.max(maxValues[i-1][w], valueOfItemI + maxValues[i-1][w - weightOfItemI]);
                }
                else
                {
                    maxValues[i][w] = maxValues[i-1][w];
                }
            }
        }


        return maxValues[maxValues.length - 1][maxValues[0].length -1];
    }

}
