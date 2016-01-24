import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 3/20/2015.
 */
public class ChatServerOutgoing {

    static int parCount = 0;
    static int messageLength;
    static int totalSize = 0;
    static String removeC = "-";
    static String addC = "+";
    static String messSep = ":";
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;

        line = br.readLine().trim();
        while(br.ready())
        {

            process(line);
            line = br.readLine().trim();
        }
        process(line);
        System.out.println(totalSize);
    }
    public static void process(String line)
    {
        messageLength = 0;
        if(line.startsWith(addC)) parCount++;
        else if(line.startsWith(removeC))parCount--;
        else
        {
            int start =  line.indexOf(messSep)+1;
            int end = line.length();
            messageLength = end - start;

            totalSize += parCount * messageLength;
        }
    }
}
