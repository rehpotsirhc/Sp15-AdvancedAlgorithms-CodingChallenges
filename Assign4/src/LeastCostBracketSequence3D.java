import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris Johnson on 1/30/2015.
 */
public class LeastCostBracketSequence3D {


    private static bracket[] pattern;
    private static final char open = '(';
    private static final char close = ')';
    private static final char unknown = '?';

    private static class bracket {
        public char c;
        public int openCost = 0;
        public int closeCost = 0;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        char[] patternString = br.readLine().toCharArray();

        int n = patternString.length;

        if (n % 2 != 0) {
            System.out.print("-1");
        } else {

            pattern = new bracket[n + 1];

            for (int i = 1; i <= n; i++) {
                char c = patternString[i - 1];
                pattern[i] = new bracket();
                pattern[i].c = c;
                if (c == unknown) {
                    String[] costsString = br.readLine().split(" ");
                    pattern[i].openCost = Integer.parseInt(costsString[0]);
                    pattern[i].closeCost = Integer.parseInt(costsString[1]);
                }
            }
        }

        int cost = computeCheapeast(n);


        String outputPattern = "";
        for (int i = 1; i <= n; i++) {

            outputPattern += pattern[i].c;
        }
        System.out.println(cost);
        System.out.println(outputPattern);


    }

    private static int computeCheapeast(int n) {

        int total = 0;
        bracket current = pattern[1];
        if (current.c == unknown) {
            current.c = open;
            total += current.openCost;
        }

        current = pattern[n];
        if (current.c == unknown) {
            current.c = close;
            total += current.closeCost;
        }

        if(n > 2) {
            bracket partner;
            for (int i = 2; i <= n / 2; i++) {
                int partnerIndex = n - i + 1;

                current = pattern[i];
                partner = pattern[partnerIndex];

                if(current.c != unknown && partner.c == unknown)
                {
                    total += costCalcOne(current, partner);
                }
                else if(current.c == unknown && partner.c != unknown)
                {
                    total += costCalcOne(partner, current);
                }
                else if(current.c == unknown && partner.c == unknown)
                {
                    total += costCalcTwo(current, partner);
                }



            }
        }

        return total;


    }


    private static int costCalcOne(bracket known, bracket b2)
    {
        if(known.c == open)
        {
            b2.c = close;
            return b2.closeCost;
        }
        else
        {
            b2.c = open;
            return b2.openCost;
        }
    }

    private static int costCalcTwo(bracket b1, bracket b2)
    {
        int cost1 = b1.openCost + b2.closeCost;
        int cost2 = b1.closeCost + b2.openCost;
        int total = 0;

        if (cost1 <= cost2) {
            total += cost1;
            b1.c = open;
            b2.c = close;
        }
        else
        {
            total += cost2;
            b1.c = close;
            b2.c = open;
        }

        return total;
    }


}
