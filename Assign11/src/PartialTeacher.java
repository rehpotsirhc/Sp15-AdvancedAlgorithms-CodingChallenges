import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 3/28/2015.
 */
public class PartialTeacher {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n][2];

        char[] line = br.readLine().toCharArray();

        char c;
        for (int i = 0; i < n - 1; i++)
        {

                c = line[i];

                if (c == 'L') {

                    if (dp[i][0] <= dp[i + 1][0]) dp[i][0] += dp[i + 1][0] + 1;
                    dp[i+1][1] = 1;


                } else if (c == 'R') {
                    if (dp[i][0] >= dp[i + 1][0]) dp[i + 1][0] += dp[i][0] + 1;
                    dp[i+1][1] = 2;
                } else {
                    dp[i + 1][0] += dp[i][0];
                }




        }

        for(int j = n-1; j > 0; j--)
        {
           if(dp[j][1] == 1 && dp[j-1][0] <= dp[j][0]) dp[j-1][0] = dp[j][0] + 1;
            else if(dp[j][1] == 0 && dp[j-1][0] != dp[j][0]) dp[j-1][0] = dp[j][0];
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(dp[i][0] + 1 + " ");
        }
        System.out.println(sb);
    }


}
