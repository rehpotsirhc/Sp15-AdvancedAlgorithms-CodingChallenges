import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 3/24/2015.
 */
public class HowManyTrees {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int h= Integer.parseInt(line[1]);
        //the first index, n represents the number of nodes
        //the second index, h represents the height
        //tree[i][j] will hold the number of ways to build a binary tree with exactly i nodes and height at least j
        long[][] trees = new long[n+1][h+1];


        //initial condition
        //only one way to build a binary tree with no nodes (technically there is no tree like this)
        trees[0][0] = 1;
        for(int i=1; i<=n; i++) {

            for(int j=0; j<=h; j++) {

                for(int k=1; k<=i; k++) {

                    if(j==0)   trees[i][0] += trees[k-1][0] * trees[i-k][0];
                    else {
                        trees[i][j] += trees[k-1][j-1] * trees[i-k][0];
                        trees[i][j] += trees[k-1][0] * trees[i-k][j-1];
                        trees[i][j] -= trees[k-1][j-1] * trees[i-k][j-1];
                    }
                }
            }
        }
        System.out.println(trees[n][h]);
    }
}
