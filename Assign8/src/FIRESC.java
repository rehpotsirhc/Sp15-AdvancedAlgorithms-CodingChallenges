import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris Johnson on 3/2/2015.
 */
class FIRESC {


    private static long mod = 1000000007;

    private static int[] rank;
    private static int[] parents;
    private static int[] set_Sizes;
    private static int set_Count;

    public static void main(String[] args) throws IOException
    {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            String[] line = br.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int M = Integer.parseInt(line[1]);

            //initially each person is its own group
            set_Count = N;
            rank = new int[N + 1];
            set_Sizes = new int[N + 1];
            parents = new int[N + 1];

            for (int i = 1; i < N + 1; i++) {
                parents[i] = i;
                set_Sizes[i] = 1;
                rank[i] = 1;
            }
            int j;
            int i;
            while (M-- > 0) {
                line = br.readLine().split(" ");
                i = Integer.parseInt(line[0]);
                j = Integer.parseInt(line[1]);
                union(i, j);

            }

            System.out.println(set_Count + " " + calc_assignCombos());
        }
    }

    private static long calc_assignCombos()
    {
        long product = 1;
        int set_size;
        for(int i = 1; i < set_Sizes.length; i++)
        {
            set_size = set_Sizes[i];
            if(set_size > 0) product =  (product * set_size) % mod;
        }
        return product;
    }

    private static int find(int x)
    {
        if(x != parents[x]) parents[x] = find(parents[x]);
        return parents[x];
    }

    private static void union(int x, int y)
    {
        int parentX = find(x);
        int parentY = find(y);

        //if their parents aren't equal, they belong to different groups
        //unioning them will combine them into one group, reducing the total number of groups by 1
        if(parentX !=  parentY) {
            set_Count--;

            int diff = rank[parentX] - rank[parentY];

            if (diff > 0) reassign(parentY, parentX);
            else
            {
                reassign(parentX, parentY);
                if(diff == 0) rank[parentY]++;
            }
        }
    }

    private static void reassign(int child, int parent)
    {
            parents[child] = parent;
            set_Sizes[parent] += set_Sizes[child];
            set_Sizes[child] = 0;
    }

}
