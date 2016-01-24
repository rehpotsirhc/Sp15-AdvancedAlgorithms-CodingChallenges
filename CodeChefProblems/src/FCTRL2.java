import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Chris on 3/19/2015.
 */
class FCTRL2 {


    private static ArrayList<Integer> nums;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        nums = new ArrayList<Integer>();
        while (T-- > 0) {

            System.out.println(factorial(Integer.parseInt(br.readLine())));
        }

    }

    public static int factorial(int n)
    {
        if(n ==1)return 1;

        int tmp = factorial(n-1) * n;
        nums.add();

        return ;
    }
}
