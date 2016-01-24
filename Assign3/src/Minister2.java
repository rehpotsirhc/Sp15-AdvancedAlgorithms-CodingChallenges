import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Created by Chris Johnson on 1/26/2015.
 */
class Minister2 {


    public static int[] bigArray = new int[100000];

    public static void main(String[] args) throws IOException
    {


        int mod =1000000007;
        int alphabetCount = 26;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long result;


        while (T-- >0){
            int N = Integer.parseInt(br.readLine());
            permute(N);
            int cycles = 0;
            result = 1;
            for(int t = 0; t<N;){
                int i = t;
                while (bigArray[i]!=cycles){
                    int temp = bigArray[i];
                    bigArray[i] = cycles;
                    i = temp;
                }
                while (t<N && bigArray[t]<=cycles)
                    t++;
                cycles++;
            }


            while (cycles-- > 0){
                result = (result * alphabetCount) % mod;
            }

            System.out.println(result);
        }


    }



    public static void permute(int N) {

        for (int i = 0; i < N; i++) {
            if (i < N / 2)
                bigArray[i] = i * 2 + 1;
            else
                bigArray[i] = 2 * (i - N / 2);
        }
    }
}
