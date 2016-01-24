import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Chris Johnson on 3/16/2015.
 */
class KINGCON {


    private static ArrayList<ArrayList<node>> adjacentNodes;
    private static ArrayList<node> allNodes;
    private static int singleCost;
    private static int depthCounter = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        String[] line;
        int N, M, A, B;


        while (T-- > 0) {
            line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            M = Integer.parseInt(line[1]);
            singleCost = Integer.parseInt(line[2]);
            adjacentNodes = new ArrayList<ArrayList<node>>();
            allNodes = new ArrayList<node>();

            node tmp;
            for (int i = 0; i < N; i++) {
                adjacentNodes.add(new ArrayList<node>());
                tmp = new node(i);
                allNodes.add(tmp);
            }


            while (M-- > 0) {
                line = br.readLine().split(" ");
                A = Integer.parseInt(line[0]);
                B = Integer.parseInt(line[1]);
                adjacentNodes.get(A).add(allNodes.get(B));
                adjacentNodes.get(B).add(allNodes.get(A));

            }
            DFS(allNodes.get(0), true);
            int artPointCount = 0;
            for (int i = 0; i < N; i++)
                if (allNodes.get(i).artPoint)   artPointCount ++;

            System.out.println(artPointCount * singleCost + "\n");

        }
    }
    public static void DFS(node v, boolean start) {

        v.depth = v.lowest = depthCounter++;
        v.visited = true;
        for (node adj : adjacentNodes.get(v.loc)) {
            if (!adj.visited) {
                v.children++;
                DFS(adj, false);
                if (start && v.children > 1 || !start && adj.depth >= v.lowest) {
                    v.artPoint = true;
                }
                if (adj.depth < v.depth) v.depth = adj.depth;

            } else if (adj != v && adj.lowest < v.depth) v.depth = adj.lowest;


        }

    }

}

class node {

    int loc;
    int children = 0;
    int depth = 0;
    int lowest = 0;
    boolean visited = false;
    boolean artPoint;

    public node(int loc) {
        this.loc = loc;
    }

}
