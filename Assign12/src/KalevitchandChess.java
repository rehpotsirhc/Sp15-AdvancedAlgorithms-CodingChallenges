import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 4/7/2015.
 */
public class KalevitchandChess {


    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = 8;
        int w = 'W';
        char[] row;
        boolean all = true;
        boolean[] col= new boolean[8];
        int count = 0;
        for(int i = 0; i < 8; i++)
        {
            row= br.readLine().toCharArray();
            for(int j = 0; j < 8;j++)
            {
                if(row[j] == w)
                {
                    all = false;
                    if(!col[j]) col[j] = true;
                }
            }
            if(all) count++;
            all = true;
        }

        if(count < 8) {
            for (int i = 0; i < 8; i++) {
                if (!col[i]) count++;
            }
        }
        System.out.println(count);


    }
}
