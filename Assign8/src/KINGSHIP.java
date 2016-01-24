import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 2/28/2015.
 */
class KINGSHIP {



    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int N;
        int[] cities;
        int smallestCity;
        String[] line;
        int tmp;
        long cost;
        while(T-- > 0)
        {
            cost = 0;
            smallestCity = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            line = br.readLine().split(" ");
            cities = new int[N];
            for(int i = 0; i < N;i++) {

                tmp = Integer.parseInt(line[i]);
                if(tmp < smallestCity)
                {
                    if(smallestCity < Integer.MAX_VALUE)   cities[i] = smallestCity;
                    smallestCity = tmp;
                }
                else
                {
                    cities[i] = tmp;
                }
            }
            for(int i = 1; i < N; i++)
            {
               cost += (long)cities[i] * (long)smallestCity;
            }

            System.out.println(cost);

        }
    }
}
