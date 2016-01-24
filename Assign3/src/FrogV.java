import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 1/25/2015.
 */
class FrogV {



    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0].trim());
        long K = Long.parseLong(firstLine[1].trim());
        long P = Long.parseLong(firstLine[2].trim());

        //coordinates of frogs
        String[] secondLine = br.readLine().split(" ");

        Frog[] frogs = new Frog[N];
        Map<Integer, Frog> frogMap = new HashMap<Integer, Frog>();

        for(int i = 0; i < N; i++)
        {
            Frog f= new Frog(Long.parseLong(secondLine[i]));
            frogs[i] = f;
            frogMap.put(i, f);

        }
        Arrays.sort(frogs, new Comparator<Frog>()
        {
                    @Override
                    public int compare(Frog f1, Frog f2) {


                        if (f1.x < f2.x) return -1;
                        else if (f1.x > f2.x) return 1;
                        else return 0;
                    }
        });


        frogs[frogs.length - 1].maxX = frogs[frogs.length - 1].x + K;
        for(int i = frogs.length - 2; i >=0; i--)
        {
            Frog pastI = frogs[i + 1];
            Frog atI = frogs[i];
            if(atI.x + K >= pastI.x) atI.maxX = pastI.maxX;
            else  atI.maxX +=atI.x + K;
        }
        while(P-- > 0)
        {
            String[] frogPair = br.readLine().split(" ");

            if(frogMap.get(Integer.parseInt(frogPair[0]) - 1).maxX == frogMap.get(Integer.parseInt(frogPair[1]) - 1).maxX) System.out.println("Yes");
            else System.out.println("No");
        }

    }

    private static class Frog
    {
        long x;
        long maxX;

        public Frog(long x)
        {
            this.x = x;
        }

    }
}
