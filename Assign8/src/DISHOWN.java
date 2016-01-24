import java.io.*;


/**
 * Created by Chris on 2/28/2015.
 */

<<<<<<< HEAD

//implement using disjoint sets with path compression
//sets will be unioned using a value associated with it instead of the rank
//the max of this value will be at the root of the set it belongs to
=======
>>>>>>> 04cb9f5df688c7a0c854fa29e4d56f4f377bb0c6
class DISHOWN {


    private static int[][] sets;

    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer reader = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        reader.nextToken();
        return (int) reader.nval;
    }

    public static void main(String[] args) throws IOException {


        int T = nextInt();
        while (T-- > 0) {

            int N = nextInt();

            //sets[i][0] are the scores for the dishes
            //sets[i][1] are the pointers to parents
            //sets[i][1] == i, means it's a root
            sets = new int[N + 1][2];
            for (int i = 1; i <= N; i++) {
                sets[i][0] = nextInt();
                sets[i][1] = i;
            }

            int Q = nextInt();
            while (Q-- > 0) {

<<<<<<< HEAD
                int query = nextInt();

                if (query == 1) {
                    int index = nextInt();
                    //do find
                    pw.println(find(index));
                } else {
                    int index1 = nextInt();
                    int index2 = nextInt();
                    //do union
                    if (union(index1, index2) == -1) pw.println("Invalid query!");

                }
=======
                    int query = nextInt();

                    if (query == 1) {
                        int index = nextInt();
                        //do find
                        pw.println(findNonRec(index));
                    } else {
                        int index1 = nextInt();
                        int index2 = nextInt();
                        //do union
                        if (union(index1, index2) == -1) pw.println("Invalid query!");

                    }
>>>>>>> 04cb9f5df688c7a0c854fa29e4d56f4f377bb0c6
            }
        }
        pw.flush();
    }


    private static int find(int x) {
<<<<<<< HEAD
        int result;
        if (sets[x][1] == x) return x;
        else result = find(sets[x][1]);

        sets[x][1] = result;
        return result;

=======
        if (sets[x][1] != x) sets[x][1] = find(sets[x][1]);
        return sets[x][1];

    }

    private static int findNonRec(int x)
    {
        while(sets[x][1] != x)
        {
            sets[x][1] = sets[sets[x][1]][1];
            x = sets[x][1];
        }

        return x;
>>>>>>> 04cb9f5df688c7a0c854fa29e4d56f4f377bb0c6
    }

    //returns -1 if the elements belong to the same set
    //otherwise performs the union and returns 1
    private static int union(int x1, int x2) {

        if (x1 == x2) return -1;
<<<<<<< HEAD
        int parent1 = find(x1);
        int parent2 = find(x2);
=======
        int parent1 = findNonRec(x1);
        int parent2 = findNonRec(x2);
>>>>>>> 04cb9f5df688c7a0c854fa29e4d56f4f377bb0c6
        if (parent1 == parent2) return -1;
        int diff = sets[parent1][0] - sets[parent2][0];

        if (diff > 0) {
            sets[parent2][1] = sets[parent1][1];
        } else if (diff < 0) {
            sets[parent1][1] = sets[parent2][1];
        }
        return 1;
    }
<<<<<<< HEAD
}
    /*
    to make input faster because of TLE
     */

//    class IReader {
//
//        private InputStream stream;
//        private int current;
//        private int count;
//        private byte[] buffer = new byte[1024];
//
//
//        public IReader(InputStream stream) {
//            this.stream = stream;
//        }
//
//        public int nextInteger() {
//            int character = read();
//            while (isSpace(character)) {
//                character = read();
//            }
//            int result = 0;
//            while (!isSpace(character))
//            {
//                if (character < '0' || character > '9')
//                    throw new InputMismatchException();
//                result *= 10;
//                result += character & 15;
//                character = read();
//            }
//            return result;
//        }
//
//        public int read() {
//            if (current >= count) {
//                current = 0;
//                try {
//                    count = stream.read(buffer);
//                } catch (IOException e) {
//                    throw new InputMismatchException();
//                }
//                if (count <= 0)
//                    return -1;
//            }
//            return buffer[current++];
//        }
//
//
//
//
//        public static boolean isSpace(int c) {
//            return c == ' ' || c=='\r' || c =='\t' || c== -1 || c == '\n';
//        }
//    }

=======
} 
>>>>>>> 04cb9f5df688c7a0c854fa29e4d56f4f377bb0c6
