import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by Chris on 3/23/2015.
 */
public class LonRegBracSeq2 {


    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] seq = br.readLine().toCharArray();

        Stack<Integer> s = new Stack<Integer>();


        //if j is the index of a closing bracket,
        //ob[j] is the position of the corresponding opening bracket
        //-1 means there is no corresponding opening bracket
        int[] ob = new int[seq.length + 1];

        //eob[j] is the position of the corresponding earliest opening bracket (begins the regular bracket sequence to which j belongs)
        //-1 means there is no corresponding opening bracket
        int[] eob = new int[seq.length + 1];

        int phi = 0;


        int max = 0;
        int count = 0;



        char open = '(';
        int prev;
        int candidate;
        for(int i = 1; i <= seq.length; i++)
        {
            if(seq[i-1] == open) s.push(i);
            else
            {
                if(!s.isEmpty())
                {
                    //record position of corresponding opening bracket
                    //it may be used later
                    ob[i] = s.pop();
                    //is the position before the corresponding opening bracket a closing bracket?
                    //if it is AND it has it's own corresponding opening bracket
                    //then we add the two regular bracket sequences together!
                    prev = ob[i] - 1;
                    if(eob[prev] != phi) eob[i] = eob[prev];

                    //the earliest  open bracket is just the correspnding one
                    else eob[i] = ob[i];


                    candidate = i - eob[i] + 1;

                    if( candidate> max)
                    {
                        max = candidate;
                        count = 1;
                    }
                    else if(candidate == max) count++;
                }

            }
        }


        if(count == 0) count++;
        System.out.println(max + " " + count);

    }
}
