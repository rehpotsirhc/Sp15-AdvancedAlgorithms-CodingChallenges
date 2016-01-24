import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 3/28/2015.
 */
public class Watermelon {



    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int w =Integer.parseInt(br.readLine());
        System.out.println(w != 2 && w % 2 == 0 ? "YES" : "NO");
    }

}
