import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Chris on 4/7/2015.
 */
public class Dijkstra {


    static class Vertex implements Comparable<Vertex> {
        int id;
        Vertex parent;
        List<Edge> edges = new ArrayList<>();
        long distance = Long.MAX_VALUE;

        public Vertex(int id) {
            this.id = id;
        }

        @Override
        public int compareTo(Vertex o) {
            if (distance < o.distance) return -1;
            else if (distance > o.distance) return 1;
            else return id - o.id;

        }
    }

    static class Edge {
        int weight;
        Vertex v1;

        public Edge(int w, Vertex v1) {
            this.weight = w;
            this.v1 = v1;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        SortedSet<Vertex> pq = new TreeSet<>();
        int a, b, w;
        Vertex[] graph = new Vertex[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new Vertex(i + 1);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            //add an edge from vertex a to b with weight w
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            w = Integer.parseInt(st.nextToken());
            graph[a].edges.add(new Edge(w, graph[b]));
            graph[b].edges.add(new Edge(w, graph[a]));

        }
        graph[0].distance = 0;
        pq.add(graph[0]);
        Vertex smallest;

        while (!pq.isEmpty()) {
            smallest = pq.first();
            pq.remove(smallest);
            if (smallest == graph[n - 1]) {
                StringBuilder sb = new StringBuilder();
                LinkedList<Integer> path = new LinkedList<>();
                while (smallest != null) {
                    path.addFirst(smallest.id);
                    smallest = smallest.parent;
                }
                for (int id : path) {
                    sb.append(id).append(' ');
                }
                System.out.println(sb.toString().trim());
                return;
            }
            for (Edge e : smallest.edges) {
                if (smallest.distance + e.weight < e.v1.distance) {
                    pq.remove(e.v1);
                    e.v1.distance = smallest.distance + e.weight;
                    e.v1.parent = smallest;
                    pq.add(e.v1);
                }
            }

        }
        System.out.println(-1);
    }
}
