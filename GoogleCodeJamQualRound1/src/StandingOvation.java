import java.io.*;

/**
 * Created by Chris on 4/10/2015.
 */
public class StandingOvation {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



        int T  = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= T;t++) {
            String[] line = br.readLine().split(" ");


            int sMax = Integer.parseInt(line[0]);
            int countInvited = 0;
            char[] pp = line[1].toCharArray();
            // int[] people = new int[sMax];

            int p = Character.getNumericValue(pp[0]);
            int countStanding = p;
            for (int s = 1; s <= sMax; s++) {
                if (countStanding >= sMax) break;
                p = Character.getNumericValue(pp[s]);
                if (s > countStanding)
                {
                    countStanding++;
                    countInvited++;
                }
                countStanding += p;

            }
            sb.append("Case #").append(t).append(": ").append(countInvited).append("\n");


        }
        write(sb.toString(), "Y:\\Chris Docs\\code\\projects\\Sp15\\AdvAlg(5050)\\GoogleCodeJamQualRound1\\src\\StandingOvationLargeOutput.txt");

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
