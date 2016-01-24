import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris Johnson on 2/25/2015.
 */
public class AliceBobandCocoa {


    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] ct = br.readLine().split(" ");

        int[] chocTime = new int[n];

        for(int i = 0; i < n; i++)
        {
            chocTime[i] =Integer.parseInt(ct[i]);
        }

        if(n == 1) System.out.println("1 0");
        else {
            int aliceCCount = 1;
            int bobCCount = 1;
            int aliceTime = chocTime[0];
            int bobTime = chocTime[n - 1];
            int a = 1;
            int b = n - 2;
            while (a < n - 1 && b >= 1) {
                if (aliceTime <= bobTime) {
                    aliceCCount++;
                    aliceTime += chocTime[a++];
                } else {
                    bobCCount++;
                    bobTime += chocTime[b--];
                }

                if (a > b) break;

            }
            System.out.println(aliceCCount + " " + bobCCount);
        }
    }
}
