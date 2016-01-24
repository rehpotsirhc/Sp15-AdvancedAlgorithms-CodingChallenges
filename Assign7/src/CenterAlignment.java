import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Chris Johnson on 2/25/2015.
 */
public class CenterAlignment {


    private static char frameC = '*';

    private static int blockLength;
    private static StringBuilder sb;
    private static boolean bringLeft = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> lines = new ArrayList<String>();
        String line;
        int lineLength = 0;
        blockLength = 0;
        sb = new StringBuilder();

        line = br.readLine();
        while (br.ready()) {


            addLine(lines, line, lineLength);
            line = br.readLine();
        }
        addLine(lines, line, lineLength);


        System.out.println(buildWrapperLine());
        sb.setLength(0);
        for (String l : lines) {
            System.out.println(buildLine(l));
            sb.setLength(0);
        }
        System.out.println(buildWrapperLine());

    }

    private static void addLine(ArrayList<String> lines, String line, int lineLength)
    {
        lines.add(line);
        lineLength = line.length();
        if (lineLength > blockLength) blockLength = lineLength;
    }

    private static String buildWrapperLine() {
        sb.append(frameC);
        for (int i = 0; i < blockLength; i++) {
            sb.append(frameC);
        }
        sb.append(frameC);
        return sb.toString();
    }

    private static String buildLine(String line) {

        int diff = blockLength - line.length();
        sb.append(frameC);
        int before = 0, after = 0;
        if(diff % 2 == 0)
        {
            before = after = diff / 2;
        }
        else
        {
            before = diff / 2;
            after = diff / 2;

            if(bringLeft)  after +=1;

            else before +=1;

            bringLeft = !bringLeft;

        }
        buildLine(line, before, after);
        sb.append(frameC);
        return sb.toString();
    }

    private static void buildLine(String line, int before, int after)
    {
        addSpaces(before);
        sb.append(line);
        addSpaces(after);
    }

    private static void addSpaces(int count)
    {
        for(int i = 0; i < count; i++)
        {
            sb.append(" ");
        }
    }
}
