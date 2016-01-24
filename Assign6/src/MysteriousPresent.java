import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Chris Johnson on 2/17/2015.
 */
public class MysteriousPresent {


    //Conceptually:
    //W x H array - one position for every possible package WxH
    //index w = width of envelope and index h = height of envelope
    //vast most array positions will be empty, but the envelopes will be sorted!!, i.e.
    //w > w-1 and h > h-1
    //array will hold true/false value indicating if it has an envelope or not
    //the maximum sequence that lead up to it

    //loop through each w, h of array
    //and assign to element w,h max(w-1 & h-1 : "1" || w-1 & h-2 :"2" || w-2 & h-1 : "3")
    //use  the 1,2,3 labels to retrace

    //Since W and H can each be up to 10^6, this way will take up way too much space, ~40^12 bytes ??

    //Instead, do the same thing except have the envelopes in a sorted list.
    //Each element will have a pointer to the biggest envelope that is smaller than it (w-1, h-1 in the above scenario)
    //w-2,h-1 and w-1,h-2 will be next two smallest envelopes in the sorted list


    //keep track of the envelope with the highest sequence preceding it


    private static class Envelope {

        Envelope prevInList;
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
            int result = w;
            result = 31 * result + h;
            return result;
        }
    }


    private static List<Envelope> envelopes;
    private static Envelope max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nwh = br.readLine().split("[\\s]+");
        int N = Integer.parseInt(nwh[0]);
        int mW = Integer.parseInt(nwh[1]);
        int mH = Integer.parseInt(nwh[2]);

        envelopes = new LinkedList<Envelope>();

        for (int i = 0; i < N; i++) {

            String[] wh = br.readLine().split("[\\s]+");
            int w = Integer.parseInt(wh[0]);
            int h = Integer.parseInt(wh[1]);
            if (w > mW && h > mH) {
                justInsert(new Envelope(w, h, i + 1));
                // Envelope e = new Envelope(w, h, i + 1); if (!envelopes.contains(e)) envelopes.add(e);

            }
        }
        mark();

        if (envelopes.size() == 0) System.out.println("0");
        else {

            Envelope one, two = null, three = null;

            max = envelopes.get(0);

            for (Envelope e : envelopes) {
                one = e.justSmaller;
                if (one != null) two = one.prevInList;
                if (two != null) three = two.prevInList;

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
                    } else if (two != null && three != null) {
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


    }


    private static void mark() {
        Envelope toMark;
        Envelope toCheck;
        for (int i = 0; i < envelopes.size(); i++) {
            toMark = envelopes.get(i);
            for (int j = 0; j < envelopes.size(); j++) {
                toCheck = envelopes.get(j);
                if (toCheck.w < toMark.w && toCheck.h < toMark.h) {
                    if (toMark.justSmaller == null || (toMark.justSmaller.w < toCheck.w || toMark.justSmaller.h < toCheck.h)) {
                      //  toMark.prevInList = toMark.justSmaller;
                        toMark.justSmaller = toCheck;
                    }
                }


            }

        }

//        toMark = null;
//        toCheck = null;
//
//        for (int i = 0; i < envelopes.size(); i++) {
//            toMark = envelopes.get(i);
//            if(toMark.justSmaller != null) {
//                toMark = toMark.justSmaller;
//
//                toMark.prevInList = toMark.justSmaller;
//                for (int j = 0; j < envelopes.size(); j++) {
//                    toCheck = envelopes.get(j);
//                    if ((toCheck.w == toMark.w && toCheck.h > toMark.h && (toMark.prevInList == null || toCheck.h > toMark.prevInList.h)) || (toCheck.h == toMark.h && toCheck.w > toMark.w && (toMark.prevInList == null || toCheck.w > toMark.prevInList.w))) {
//                        toMark.prevInList = toCheck;
//                    }
//                }
//            }
//        }

    }

    private static void justInsert(Envelope e) {
        if (envelopes.isEmpty()) envelopes.add(e);
        else {
            Envelope prev = null;
            for (int i = 0; i < envelopes.size(); i++) {
                Envelope eo = envelopes.get(i);
                if (e.w == eo.w && e.h == eo.h) return;
                if ((e.w > eo.w && e.h > eo.h) || (e.w > eo.w && e.h == eo.h) || (e.w == eo.w && e.h > eo.h)) prev = eo;
                else if (e.w + e.h < eo.w + eo.h || (e.w > eo.w || e.h > eo.h)) {
                    e.prevInList = prev;
                    envelopes.add(i, e);
                    while (envelopes.size() - 1 >= ++i) {
                        Envelope next = envelopes.get(i);
                        next.prevInList = e;
                    }
                    return;
                }
            }
            envelopes.add(e);


        }

    }

    private static void insert(Envelope e) {
        if (envelopes.isEmpty()) envelopes.add(e);
        else {
            Envelope justSmaller = null;
            Envelope prev = null;
            int posInsI = envelopes.size() - 1;
            for (int i = 0; i < envelopes.size(); i++) {

                Envelope eo = envelopes.get(i);
                //both equal, ignore
                if (e.w == eo.w && e.h == eo.h) return;

                //both bigger
                //set both and continue
                if (e.w > eo.w && e.h > eo.h) {
                    prev = eo;
                    justSmaller = eo;
                }
                //one bigger, one equal
                //set prevInList and continue
                else if ((e.w > eo.w && e.h == eo.h) || (e.w == eo.w && e.h > eo.h)) {
                    prev = eo;
                }
                //both smaller
                else if (e.w + e.h < eo.w + eo.h || (e.w > eo.w || e.h > eo.h)) {
                    e.justSmaller = justSmaller;
                    e.prevInList = prev;
                    envelopes.add(i, e);
                    //get the next ones and point them to me
                    while (envelopes.size() - 1 >= ++i) {
                        Envelope next = envelopes.get(i);
                        if (next.w > e.w && next.h > e.h) {
                            if (next.justSmaller == null) next.justSmaller = e;
                            else if (next.justSmaller.w < e.w && next.justSmaller.h < e.h) next.justSmaller = e;

                        }
                        next.prevInList = e;
                    }
                    return;
                }
                //one smaller
                else if (e.w < eo.w || e.h < eo.h) {
                    posInsI = i;
                }


            }

            if (justSmaller == null) {
                e.justSmaller = justSmaller;
                e.prevInList = prev;
                envelopes.add(posInsI, e);
            } else {
                e.justSmaller = justSmaller;
                e.prevInList = prev;
                envelopes.add(e);
            }

            return;

        }
    }


}
