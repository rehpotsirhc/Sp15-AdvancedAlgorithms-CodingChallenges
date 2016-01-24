import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris Johnson on 3/18/2015.
 */
public class RegistrationSystem {


    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        Map<String, Integer> dbNames = new HashMap<String, Integer>();

        String request;
        StringBuilder sb = new StringBuilder();
        int i;
        String prompt;
        while(n-- >0)
        {
            request = br.readLine();
            if(!dbNames.containsKey(request))
            {
                sb.append("OK\n");
                dbNames.put(request, 1);
            }
            else
            {
                i = dbNames.get(request);
                while(dbNames.containsKey(request + i)) i++;

                prompt = request + i;
                dbNames.put(request, i + 1);

                sb.append(prompt +"\n");
            }


        }
        pw.print(sb);
        pw.close();
    }
}
