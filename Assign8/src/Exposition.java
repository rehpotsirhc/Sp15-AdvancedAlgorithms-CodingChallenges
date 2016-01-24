import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Chris Johnson on 3/4/2015.
 */
public class Exposition {


    static class BookRange {
        int is;
        int ie;

        BookRange(int is, int ie) {
            this.is = is;
            this.ie = ie;
        }

    }


    private static List<BookRange> bestRanges = new ArrayList<BookRange>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        int[] books = new int[n];

        TreeMap<Integer, Integer> mapBooks = new TreeMap<Integer, Integer>();

        line = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            books[i] = Integer.parseInt(line[i]);
        }

        int i = 0;
        int j = 0;
        int count;
        Integer atJ;
        Integer atI;

        bestRanges.add(new BookRange(0, 0));
        while (j < n) {
            atJ = books[j++];
            mapBooks.put(atJ, mapBooks.getOrDefault(atJ, 0) + 1);
            while (mapBooks.lastKey() - mapBooks.firstKey() > k) {
                atI = books[i++];
                count = mapBooks.get(atI);
                if (count > 1) mapBooks.put(atI, count - 1);
                else mapBooks.remove(atI);

            }

            addRange(i, j);
        }


//        while (j < n) {
//            booksInRange.add(books[j]);
//            if ((booksInRange.last() - booksInRange.first()) < k) {
//                addRange(i, j);
//                j++;
//            } else {
//                while ((booksInRange.last() - booksInRange.first()) > k) {
//                    booksInRange.remove(books[i]);
//                    i++;
//                    booksInRange.add(books[i]);
//                }
//
//                addRange(i, j);
//                j++;
//            }
//        }


        int a = (bestRanges.get(0).ie - bestRanges.get(0).is);
        int b = bestRanges.size();

        System.out.println(a + " " + b);

        BookRange best;
        for (i = 0; i < b; i++) {
            best = bestRanges.get(i);
            System.out.println((best.is + 1) + " " + (best.ie));
        }


    }

    private static void addRange(int i, int j) {
        int currentBest = bestRanges.get(0).ie - bestRanges.get(0).is;
        int prospect = (j - i);
        if (prospect > currentBest) {
            bestRanges.clear();
            bestRanges.add(new BookRange(i, j));
        } else if (prospect == currentBest) {
            bestRanges.add(new BookRange(i, j));
        }
    }


}



