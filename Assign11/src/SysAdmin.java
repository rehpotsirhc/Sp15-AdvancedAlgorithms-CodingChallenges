import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 3/31/2015.
 */
public class SysAdmin {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int min = n - 1;
        int max = 1 + ((n - 1) * (n - 2)) / 2;
        if (m < min || m > max) System.out.println("-1");
        else {

            StringBuilder sb = new StringBuilder();
            int v = Integer.parseInt(line[2]);

            int a = 1;
            int b = 2;

            int step = 1;

            int ap = 2;
            if(a == v)
            {
                a = ap;
            }
            else if(a == ap)
            {
                a = v;
            }

            if(b == v)
            {
                b = ap;
            }
            else if(b == ap)
            {
               b = v;
            }


            sb.append(a + " " + b + "\n");
            m--;



            while (m > 0) {

                for (int i = 2; m > 0 && i + step <= n; i++) {


                    if (i == v) {
                        a = ap;
                    } else if (i == ap) {
                        a = v;
                    } else a = i;

                    if (i + step == v) {
                        b = ap;
                    } else if (i + step == ap) {
                        b = v;
                    } else b = i + step;

                    sb.append(a + " " + b + "\n");
                    m--;

                }
                step++;
            }

            System.out.print(sb);

        }

    }


}
