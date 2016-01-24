import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 4/7/2015.
 */
public class TwoButtons {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        System.out.println(pressButtons(n, m, 0));
    }


    public static int pressButtons(int n, int m, int c)
    {
        while(m != n) {
            if (m == n) return c;
            while (m > n && m % 2 == 0) {
                m /= 2;
                c++;
            }
            if (m == n) return c;
            while (m < n || (m % 2 != 0 && m != n)) {
                m++;
                c++;

            }

        }
        return c;

    }
}
