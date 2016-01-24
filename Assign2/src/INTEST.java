import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Chris on 1/17/2015.
 */
class INTEST {

    public static void main(String[] args) throws IOException
    {

        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));

        String[] startLine = bw.readLine().split(" ");

        int lineCount = Integer.parseInt(startLine[0]);
        int denominator = Integer.parseInt(startLine[1]);
        int count = 0;

        while (lineCount > 0)
        {
            lineCount--;
            int numerator = Integer.parseInt(bw.readLine());

            if(numerator % denominator == 0) count++;

        }
        System.out.print(count);
    }
}
