import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris Johnson on 3/2/2015.
 */
class HDELIVER {


    public static int[] parents;
    public static int[] rank;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String[] line = br.readLine().split(" ");

            int N = Integer.parseInt(line[0]);
            int M = Integer.parseInt(line[1]);
            int A;
            int B;
            parents = new int[N];
            rank = new int[N];

            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }

            while (M-- > 0) {
                line = br.readLine().split(" ");
                A = Integer.parseInt(line[0]);
                B = Integer.parseInt(line[1]);

                union(A, B);
            }
            int Q = Integer.parseInt(br.readLine());


            while(Q-- >0)
            {
                line = br.readLine().split(" ");
                A = Integer.parseInt(line[0]);
                B = Integer.parseInt(line[1]);

                if(find(A) == find(B)) sb.append("YO\n");
                else sb.append("NO\n");


            }

        }
        System.out.print(sb);

    }

    public static int find(int x) {
        if (parents[x] != x) parents[x] = find(parents[x]);
        return parents[x];
    }


    public static void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if(px != py) {
            int diff = rank[px] - rank[py];

            if (diff > 0) parents[py] = px;

            else if (diff < 0) parents[px] = py;

            else {
                parents[px] = py;
                if(diff == 0)rank[py]++;
            }
        }

    }

}
