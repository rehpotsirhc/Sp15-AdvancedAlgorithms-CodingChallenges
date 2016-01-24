import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 4/5/2015.
 */
public class BindianSignalizing {


    static class Hill {

        boolean rightPast = false;
        boolean leftPast = false;
        int farRight = -1;
        int farLeft = -1;
        int h;

        public Hill(int height) {
            this.h = height;
        }


//        public Hill copy()
//        {
//            Hill hl = new Hill(h);
//            hl.farRight = farRight;
//            hl.farLeft = farLeft;
//            return hl;
//        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());

        String[] line = br.readLine().split(" ");


        List<Hill> hills = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            hills.add(new Hill(Integer.parseInt(line[i])));
        }

        Hill current;
        Hill next;
        int j;
        for (int i = 0; i < n; i++) {
            current = hills.get(i);

            j = i + 1;
            while (i != j) {
                if(j >=n)
                {
                    current.rightPast = true;
                    j = 0;
                }


                if(i == j)break;
                next = hills.get(j);
                if (next.h > current.h)
                {
                    current.farRight = j;
                    break;
                }
                j++;
            }
            if(current.farRight == -1)
            {
                current.farRight = j-1 <= 0 ? n-1: j - 1;
            }

            j = i - 1;
            while (i != j) {
                if (j < 0 || j >= n)
                {
                    j = n - 1;
                    current.leftPast = true;
                }

                if(j < 0)
                {
                    j = n-1;
                    current.leftPast = true;
                }
                else if(j >= n)
                {
                    j = 0;
                    current.leftPast = true;
                }

                if(i == j)break;
                next = hills.get(j);
                if (next.h > current.h)
                {
                    current.farLeft = j;
                    break;
                }
                j--;
            }
            if(current.farLeft == -1)
            {



                current.farLeft = j+1 >= n ? 0:j+1;

            }

        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            //go right
            j = i + 1;
            current = hills.get(i);

            while(i != j)
            {
                if(j >= n) j = 0;
                if(i == j)break;
                next = hills.get(j);
                if((current.farRight  == j || (current.farRight >= next.farRight && ((current.rightPast && next.rightPast) || (!current.rightPast && !next.rightPast))) || (current.farRight < next.farRight && current.rightPast && !next.rightPast))
                && (next.farLeft == i ||      (current.farLeft <= next.farLeft &&    ((current.leftPast && next.leftPast)  || (!current.leftPast && !next.leftPast))) ||   (current.farLeft > next.farLeft && current.leftPast && !next.leftPast))) count++;
               // if((current.farRight >= j || current.rightPast) && (next.farLeft <= i || next.leftPast))  count++;
                j++;
            }
            //go left
            j = i - 1;
            while(i != j)
            {
                if(j < 0) j = n-1;
                if(i == j)break;
                next = hills.get(j);
                if((current.farLeft  == j || (current.farLeft <= next.farLeft && ((current.leftPast && next.leftPast) || (!current.leftPast && !next.leftPast))) || (current.farLeft< next.farLeft && current.leftPast && !next.leftPast))
                        && (next.farRight == i ||      (current.farRight >= next.farRight &&    ((current.rightPast && next.rightPast)  || (!current.rightPast && !next.rightPast))) ||   (current.farRight < next.farRight && current.rightPast && !next.rightPast)))  count++;

               // if((current.farLeft <= j || current.leftPast) && (next.farRight >= i || next.rightPast))count++;
                j--;
            }


        }

        System.out.println(count);


    }
}
