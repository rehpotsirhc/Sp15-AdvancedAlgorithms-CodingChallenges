import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Chris Johnson on 2/18/2015.
 */
public class MysteriousPresent2 {

    public static final Comparator<Envelope> H
            = new Comparator<Envelope>() {
        public int compare(Envelope p1, Envelope p2) {


            return p1.h - p2.h;

        }
    };
    public static final Comparator<Envelope> W
            = new Comparator<Envelope>() {
        public int compare(Envelope p1, Envelope p2) {

            return p1.w - p2.w;

        }
    };

    public static final Comparator<Envelope> sum
            = new Comparator<Envelope>() {
        public int compare(Envelope p1, Envelope p2) {

            return (p1.h + p1.w) - (p2.h + p2.w);

        }
    };


    private static class Envelope{

        Envelope prevInListH;
        Envelope prevInListW;
        Envelope prevInChain;
        Envelope justSmaller;
        int w;
        int h;
        int index;
        int chainLength;

        public Envelope(int w, int h, int i) {

            this.w = w;
            this.h = h;
            this.index = i;
            chainLength = 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Envelope)) return false;

            Envelope envelope = (Envelope) o;

            if (h != envelope.h) return false;
            if (w != envelope.w) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int resultx = w;
            resultx = 31 * resultx + h;
            return resultx;
        }
    }

    private static ArrayList<Envelope> envelopesByH;
    private static ArrayList<Envelope> envelopesByW;
    private static ArrayList<Envelope> envelopesBySum;
    private static Envelope max;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nwh = br.readLine().split("[\\s]+");
        int N = Integer.parseInt(nwh[0]);
        int mW = Integer.parseInt(nwh[1]);
        int mH = Integer.parseInt(nwh[2]);

        envelopesByH = new ArrayList<Envelope>();
        envelopesByW = new ArrayList<Envelope>();
        envelopesBySum = new ArrayList<Envelope>();

        for (int i = 0; i < N; i++) {

            String[] wh = br.readLine().split("[\\s]+");
            int w = Integer.parseInt(wh[0]);
            int h = Integer.parseInt(wh[1]);
            if (w > mW && h > mH) {

              Envelope e =  new Envelope(w, h, i + 1);

                envelopesByH.add(e);
                envelopesByW.add(e);
                envelopesBySum.add(e);
            }

        }
        Collections.sort(envelopesByH, H);
        Collections.sort(envelopesByW, W);
        Collections.sort(envelopesBySum, sum);

        findJustLess();

        Envelope one, two = null, three = null;

        max = envelopesBySum.get(0);

        for (Envelope e : envelopesBySum) {
            one = e.justSmaller;
            if (one != null) two = one.prevInListH;
            if (one != null) three = one.prevInListW;

            if (one != null) {
                if (two != null && three == null) {

                    if (one.chainLength > two.chainLength) {
                        e.chainLength += one.chainLength;
                        e.prevInChain = one;
                    } else {
                        e.chainLength += two.chainLength;
                        e.prevInChain = two;

                    }

                } else if (two == null && three != null) {
                    if (one.chainLength > three.chainLength) {
                        e.chainLength += one.chainLength;
                        e.prevInChain = one;
                    } else {
                        e.chainLength += three.chainLength;
                        e.prevInChain = three;

                    }
                }  else if (two != null && three != null) {
                    if (one.chainLength >= two.chainLength && one.chainLength >= three.chainLength) {
                        e.chainLength += one.chainLength;
                        e.prevInChain = one;
                    } else if (two.chainLength >= one.chainLength && two.chainLength >= three.chainLength) {
                        e.chainLength += two.chainLength;
                        e.prevInChain = two;
                    } else {
                        e.chainLength += three.chainLength;
                        e.prevInChain = three;
                    }

                } else {
                    e.chainLength += one.chainLength;
                    e.prevInChain = one;
                }
            }
            if (e.chainLength > max.chainLength) max = e;

        }

        String chain = "" + max.index;


        Envelope prev = max.prevInChain;

        while (prev != null) {
            chain = prev.index + " " + chain;
            prev = prev.prevInChain;
        }

        System.out.println(max.chainLength);
        System.out.println(chain);

    }

    public static void findJustLess()
    {
        for(int i = envelopesByH.size() -1; i >=0 ;i--)
        {
            Envelope atI = envelopesByH.get(i);

            Envelope atI1 = null;
            Envelope atI2 = null;
           if(i-1 >=0) atI1 = envelopesByH.get(i - 1);
            if(i-2 >=0) atI2 = envelopesByH.get(i - 2);

            if(atI1 != null && atI1.h < atI.h && atI1.w <atI.w)
            {
                atI.justSmaller = atI1;
                atI1.prevInListH = atI2;

                for(int j = envelopesByW.size() - 1; j>=0;j--)
                {
                    Envelope atJ = envelopesByW.get(j);
                    if(atJ.w <= atI1.w) atI1.prevInListW = atJ;

                }

            }

        }
    }



}
