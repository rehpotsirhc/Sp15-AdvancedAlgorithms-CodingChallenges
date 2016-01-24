import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris Johnson on 2/13/2015.
 */
class MGCRNK {


    //basic 2-direction(right or down) path optimization problem
    //dynamic programming
    // 2D array, 1 based
    // m[i][j] = max(m[i-1][j], m[i][j-1]), i.e., max(coming from above (going down), coming from the left (going right)
    //set bread crumbs along the way
    //base cases are m[2][1] = m[1][1] + val(m[2][1]) and m[1][2] = m[1][1] + val(m[1][2])


    public static class room
    {
        public double s;
        public room(double s)
        {
            this.s = s;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- >0)
        {
            int N = Integer.parseInt(br.readLine());

            room[][] rooms = new room[N][N];

            rooms[0][0] = new room( 0);
            double sp;
            double sp2;
            double sn;
            double k;
            for(int i = 0; i  < N; i++) {
                String[] line = br.readLine().split("[\\s]+");

                for(int j = 0; j < line.length; j++)
                {
                    sn = Double.parseDouble(line[j]);


                    if(j == 0 && i ==0) continue;
                    else if(j== 0) //can only come from above
                    {
                        sp = rooms[i-1][0].s;
                        rooms[i][0] = new room(sn + sp);
                    }
                    else if(i == 0) //can only come from left
                    {
                        sp = rooms[0][j - 1].s;
                        rooms[0][j] = new room(sn + sp);
                    }
                    else
                    {
                        sp = rooms[i-1][j].s; //from above
                        sp2 = rooms[i][j-1].s; //from left

                        k = (double)i + (double)j + 1.0;
                        if(sp / k > sp2 / k)
                        {
                            rooms[i][j] = new room(sn + sp);
                        }
                        else rooms[i][j] = new room(sn + sp2);
                    }

                }
            }

            double st = rooms[N-1][N-1].s;

            if(st < 0) System.out.println("Bad Judges");
            else System.out.println(st / ((double)((2 * (N - 2)) + 1)));


        }
    }

}
