import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Chris on 3/23/2015.
 */
public class Hierarchy {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int minCost = 0;
        int n = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");

        int[] qual = new int[n + 1];
        ArrayList<TreeMap<Integer, Integer>> hierarchy = new ArrayList<TreeMap<Integer, Integer>>();
        int maxQual = -1;
        int tmp;
        for (int i = 1; i <= n; i++) {
            tmp = Integer.parseInt(line[i - 1]);
            qual[i] = tmp;
            hierarchy.add(i-1, new TreeMap<Integer, Integer>());
            if (tmp > maxQual) {
                maxQual = tmp;
            } else if (tmp == maxQual) {
                System.out.println(-1);
                return;
            }
        }
        int m = Integer.parseInt(br.readLine());
        // hierarchy[i] - A map from cost to index, ordered by cost, the index can supervise i
        int a, b, c;
        for (int i = 0; i < m; i++) {
            line = br.readLine().split(" ");
            a = Integer.parseInt(line[0]); //supervisor
            b = Integer.parseInt(line[1]); //subordinate
            c = Integer.parseInt(line[2]); //cost
            hierarchy.get(b - 1).put(c, a - 1);
        }
        int countWithouthSupervisor = 0;
        for(TreeMap<Integer, Integer> map : hierarchy)
        {
            if(map.isEmpty())
            {
                countWithouthSupervisor++;
                if(countWithouthSupervisor > 1)
                {
                    System.out.println(-1);
                    return;
                }
            }
            else
            {
                minCost += map.firstKey();
            }
        }
        System.out.println(minCost);
    }
}
