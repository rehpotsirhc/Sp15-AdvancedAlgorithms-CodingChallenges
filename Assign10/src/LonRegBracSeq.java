import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by Chris on 3/20/2015.
 */
public class LonRegBracSeq {


    private static int max;
    private static int count;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] seq = br.readLine().toCharArray();

        Stack<Character> s = new Stack<Character>();

        char open = '(';
        char close = ')';
        max = 0;
        count = 0;
        int i = 0;
        char c;
        int openC = 0;
        int closeC = 0;

        int j = skipClose(s, seq, 0, close);

        for (; j < seq.length; j++) {

            c = seq[j];

            if (c == open) {
                s.push(c);
                openC++;
            } else {
                while (!s.empty() && c == close) {
                    closeC++;
                    s.pop();
                    c = seq[++j];

                }
              //  s.removeAllElements();
                i = Math.min(openC, closeC) * 2;
                openC = closeC = 0;
                determineMax(i);
                i = 0;
               // skipClose(s, seq, j, close);
            }

        }
        if (s.isEmpty()) {
            determineMax(i);

        } else {
            if (i % 2 != 0 && max <= 1) {
                max = 0;
                count = 1;
            }
        }


        System.out.println(max + " " + count);


    }

    public static int skipClose(Stack s, char[] seq, int j, char close) {
        char c = seq[j];
        while (s.isEmpty() && c == close) {
            c = seq[++j];
        }
        return j;
    }

    public static void determineMax(int i) {
        if (i > max) {
            max = i;
            count = 1;
        } else if (i == max) count++;

    }


}
