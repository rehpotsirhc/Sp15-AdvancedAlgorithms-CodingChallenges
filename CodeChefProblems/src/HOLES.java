import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 3/19/2015.
 */
class HOLES {

    public static void main(String[] args) throws IOException

    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int count;
        char[] letters;
        while (T-- > 0) {
            letters = br.readLine().toCharArray();
            count = 0;
            for (char c : letters) {

                //"A", "D", "O", "P", "Q" , "R"
                if (c == 'A' || c == 'D' || c == 'O' || c == 'P' || c == 'R' || c == 'Q') count++;

                    //B
                else if (c == 'B') count += 2;

                //C, E, F, G, H, I, J, K, L, M, N, S, T, U, V, W, X, Y, Z
            }
            System.out.println(count);
        }
    }
}
