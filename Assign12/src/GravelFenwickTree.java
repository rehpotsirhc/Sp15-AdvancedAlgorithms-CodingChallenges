import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Chris on 4/8/2015.
 */
class GravelFenwickTree {


    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        FenwickTree ft = new FenwickTree(n);
        ft.update(0, c); //update all (0 to n-1)
        int up, v, k;

        while(m-- > 0)
        {
            st = new StringTokenizer(br.readLine());
            String S = st.nextToken();
            up = Integer.parseInt(st.nextToken()) - 1; //0 based, why are the programming problems always 1 based??
            if(S.equals("S"))
            {
                v = Integer.parseInt(st.nextToken()) - 1;
                k = Integer.parseInt(st.nextToken());
                ft.update(up, k); //updates from up to n-1
                //we only want to update from up to v, so....
                if(v+1 < n) ft.update(v + 1, -k); //undo from v+1 to n-1 by subtracting k

            }
            else
            {
                //update was interpreted as updating a range
                //so...query will return only the valued queryied....
                //this data structure seems like magic...
                sb.append(ft.query(up)).append("\n");


            }

        }
        System.out.println(sb);
    }


    static class FenwickTree
    {
        long[] tree;

        public FenwickTree(int size)
        {
            tree = new long[size];
        }

        public void update(int index, int value)
        {
            while(index < tree.length)
            {
                tree[index] += value;
                index |= index+ 1;
            }
        }


        public long query(int index)
        {
           long sum = 0;
            while(index >=0)
            {
                sum += tree[index];
                index &= index + 1;
                index--;
            }
            return sum;
        }
    }
}
