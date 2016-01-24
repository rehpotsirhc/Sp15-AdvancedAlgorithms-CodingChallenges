import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 3/19/2015.
 */
class Factorial {


    private static int zeros;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            zeros = 0;
            trailingZeros(Integer.parseInt(br.readLine()));

            sb.append(zeros + "\n");
        }

        System.out.print(sb);
    }

    public static void trailingZeros(int num) {

        int five = 5;
        int result = num / five;
        while (result >= 1) {
            zeros += result;
            five *= 5;
            result = num / five;

        }
    }

}
