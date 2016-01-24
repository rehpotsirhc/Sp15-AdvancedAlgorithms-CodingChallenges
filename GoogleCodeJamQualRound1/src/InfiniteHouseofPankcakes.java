import java.io.*;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * Created by Chris on 4/11/2015.
 */
public class InfiniteHouseofPankcakes {


    static class Pair {
        int int1;
        int int2;

        public Pair(int one, int two) {
            int1 = one;
            int2 = two;
        }
    }

    static class Plate implements Comparable<Plate> {
        int id;
        int plates;

        public Plate(int id, int plates) {
            this.id = id;
            this.plates = plates;
        }

        @Override
        //plates with more pancakes will come first
        public int compareTo(Plate o) {

            if (this.plates < o.plates) return 1;
            else if (this.plates > o.plates) return -1;
            else return o.id - this.id;
        }

        public static Pair half(Plate p) {
            int one;
            if (p.plates % 2 == 0) {

                one = p.plates / 2;
                return new Pair(one, one);
            } else {
                one = p.plates / 2 + 1;
                return new Pair(one, one - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int D;

        String[] line;

        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            int nextPlateId;
            SortedSet<Plate> plates = new TreeSet<>();
            int totalMinutes = 0;
            int biggestPlateHalfValue;
            Plate biggest;
            nextPlateId = D = Integer.parseInt(br.readLine());
            line = br.readLine().split(" ");

            for (int d = 0; d < D; d++) {

                plates.add(new Plate(d, Integer.parseInt(line[d])));
            }

            while (true) {

                biggest = plates.first();
                if(biggest.plates ==1)   break;
                biggestPlateHalfValue = Plate.half(biggest).int1;
                SortedSet<Plate> toHalf = new TreeSet<>();
                Plate removed;
                for (Plate p : plates) {
                    if (p.plates > biggestPlateHalfValue) {
                        toHalf.add(p);
                    }
                }
                Plate newP;
                Pair pair;


                while(!(toHalf.size() + biggestPlateHalfValue < biggest.plates))
                {
                    if(toHalf.size() >0) {
                        removed = toHalf.last();
                        toHalf.remove(removed);

                        while (toHalf.size() > 0 && toHalf.last().plates == removed.plates) toHalf.remove(toHalf.last());
                    }
                }
                if(toHalf.size() > 0) {
                    totalMinutes += toHalf.size();
                    for (Plate p : toHalf) {
                        plates.remove(p);
                        pair = Plate.half(p);

                        p.plates = pair.int1;
                        newP = new Plate(++nextPlateId, pair.int2);
                        plates.add(p);
                        plates.add(newP);
                    }
                }
                else break;
            }

            totalMinutes += plates.first().plates;
            sb.append("Case #").append(t).append(": ").append(totalMinutes);
            if(t != T) sb.append("\n\r");
        }

       write(sb.toString(), "Y:\\Chris Docs\\code\\projects\\Sp15\\AdvAlg(5050)\\GoogleCodeJamQualRound1\\src\\InfiniteHouseofPankcakes2.txt");
      // System.out.print(sb);


    }


    private static void write(String s, String dest) throws IOException
    {
        BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(dest)));

        //write contents of StringBuffer to a file
        bwr.write(s);

        //flush the stream
        bwr.flush();

        //close the stream
        bwr.close();
    }



}

