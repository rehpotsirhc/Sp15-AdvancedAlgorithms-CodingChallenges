import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Chris on 3/13/2015.
 */
class DIGJUMP {


    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        char[] line = br.readLine().toCharArray();

        int end = line.length - 1;
        ArrayList<ArrayList<Integer>> sameNumbers = new ArrayList<ArrayList<Integer>>(10);

        boolean[] visited = new boolean[line.length];
        boolean[] sameVisited = new boolean[10];
        ArrayList<Integer> same;
        Queue Q = new Queue();

        int n;

        for (int i = 0; i <= 9; i++) {
            sameNumbers.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < line.length;i++) {

            n = line[i] - '0';
            sameNumbers.get(n).add(i);
        }


        Q.enQueue(0, 0);
        visited[0] = true;
        Node node;
        int value;
        int location;
        int prev;
        int next;
        while (!Q.isEmpty()) {

            node = Q.deQueue();
            location = node.value;
            if (location == end) {
                pw.println(node.depth);
                break;
            } else {



                prev = location - 1;
                next = location + 1;

                if (prev >= 0 && !visited[prev])
                {
                    visited[prev] = true;
                    Q.enQueue(prev, node.depth + 1);
                }
                if (next <= end && !visited[next])
                {
                    visited[next] = true;
                    Q.enQueue(next, node.depth + 1);
                }


                value = line[location]  - '0';
                if (!sameVisited[value]) {
                    sameVisited[value] = true;
                    same = sameNumbers.get(value);
                    for (int i = 0; i < same.size(); i++) {
                        int tmp = same.get(i);
                        if (tmp != location && !visited[tmp]) {
                            visited[tmp] = true;
                            Q.enQueue(tmp, node.depth + 1);
                        }
                    }
                }
            }


        }

      pw.close();


    }
}


class Node {
    public int value;
    public int depth;

    public Node(int value, int depth) {
        this.value = value;
        this.depth = depth;
    }
}

class Queue {
    public boolean isEmpty() {
        return Q.size() <= 0;
    }

    public List<Node> Q = new LinkedList<Node>();

    public void enQueue(int value, int depth) {
        Q.add(new Node(value, depth));
    }

    public Node deQueue() {
        return Q.remove(0);
    }
}
