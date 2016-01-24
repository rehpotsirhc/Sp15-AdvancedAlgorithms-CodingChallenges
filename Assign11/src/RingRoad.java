import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Chris on 4/1/2015.
 */
public class RingRoad {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] line;
        int a;
        int b;
        int c;
        int sum = 0;
        int total = 0;
        int[][] costs = new int[n][n];
        boolean[][] graph = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            a = Integer.parseInt(line[0]) - 1;
            b = Integer.parseInt(line[1]) - 1;
            c = Integer.parseInt(line[2]);
            sum += c;
            costs[a][b] = c;
            graph[a][b] = true;
            graph[b][a] = true;

        }
        boolean[] visited = new boolean[n];
        int cur = 0;
        int next;
        int cc;
        visited[cur] = true;
        while (true) {
            next = -1;
            cc = 0;
            for (int i = 1; i < n; i++) {
                if (!visited[i] && graph[cur][i]) {
                    next = i;
                    cc = costs[cur][i];
                }
            }
            if (next == -1) {
                total += costs[cur][0];
                break;
            }
            cur = next;
            visited[cur] = true;
            total += cc;

        }
        total = Math.min(total, sum - total);
        System.out.println(total);
    }
}