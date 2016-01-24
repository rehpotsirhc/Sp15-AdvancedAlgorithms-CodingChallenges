import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by Chris on 1/25/2015.
 */
class Minister {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int T = Integer.parseInt(br.readLine());
        BigInteger letterCount = new BigInteger("26");
        BigInteger mod = new BigInteger("1000000007");

        int N;
        int cycles;
        int count;
        while (T-- > 0) {


            N = Integer.parseInt(br.readLine());

            cycles = 0;


                for (int i = 0; i < N; i++) {
                    int temp = i;
                    boolean firstTime = true;
                    count = 0;
                    while (temp != i || firstTime) {
                        count++;
                        firstTime = false;
                        if (temp < N / 2)
                            temp = temp * 2 + 1;
                        else
                            temp = 2 * (temp - N / 2);
                    }
                    if (count > cycles) cycles = count;
               }





            BigInteger cycleNo = BigInteger.valueOf(cycles);
            System.out.println(cycles);
            System.out.println(letterCount.modPow(cycleNo, mod));


        }


    }
}

