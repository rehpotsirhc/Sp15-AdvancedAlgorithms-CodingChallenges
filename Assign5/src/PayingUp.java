import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris Johnson on 2/6/2015.
 */
class PayingUp {


    private static int[] bankNotes;
    private static int amount;
    private static boolean satisfies;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String[] nextLine = br.readLine().split(" ");
            int N = Integer.parseInt(nextLine[0]);
            amount = Integer.parseInt(nextLine[1]);

            bankNotes = new int[N];
            for (int i = 0; i < bankNotes.length; i++) {
                bankNotes[i] = Integer.parseInt(br.readLine());
            }
            payUp(0, 0);
            if (satisfies) System.out.println("Yes");
            else System.out.println("No");

            satisfies = false;
        }

    }

    public static void payUp(int index, int sum) {

        if (satisfies) return;

        for (int i = index; i < bankNotes.length; i++) {

            int nSum = sum + bankNotes[i];
            if (nSum == amount) {
                satisfies = true;
                return;
            }
            payUp(i + 1, nSum);

        }
    }
}
