import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris Johnson on 2/13/2015.
 */
class GERALD03 {



    //greedy, consider in lexicographical order, i.e., L+, L-, R+, R-
    //make sure L != R for any transformation


    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while(T-- >0) {

            int N = Integer.parseInt(br.readLine());

            String[] line = br.readLine().split(" ");
            int L =  Integer.parseInt(line[0]);
            int R =  Integer.parseInt(line[1]);
            int nextL;
            int nextR;
            StringBuilder operations = new StringBuilder();
            int opC = 0;
            for(int i = 1; i < N;i++)
            {
                line = br.readLine().split(" ");
                nextL=  Integer.parseInt(line[0]);
                nextR =  Integer.parseInt(line[1]);
                //L+
                while(nextL > L)
                {
                    //make sure always L < R
                    if(L + 1 == R)
                    {
                        R++;
                        opC++;
                        operations.append("R+");
                    }
                    L++;
                    opC++;
                    operations.append("L+");
                }
                //L-
                //L < R by definition of problem, therefore decreasing L should never cause L==R
                while(nextL < L)
                {
                    L--;
                    opC++;
                    operations.append("L-");
                }

                //R+
                //L < R by definition of problem, therefore increasing R should never cause L==R
                while(nextR > R)
                {
                    R++;
                    opC++;
                    operations.append("R+");

                }
                //R--
                //we've already decreased L to a point lower than R can ever get because always L < R
                // OR
                //with the check in the L+ operation, ensuring that always L < R by increasing R when needed,
                //L is already at its ending point and R is greater than it, we will never need to decrease R so that it's below L, because always L < R
                while(nextR < R)
                {
                    R--;
                    opC++;
                    operations.append("R-");
                }

            }

            System.out.println(opC);
            System.out.println(operations);

        }
    }

}
