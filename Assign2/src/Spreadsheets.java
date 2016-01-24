import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chris on 1/17/2015.
 */
public class Spreadsheets {

    public static char[] alphabetchars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static LinkedHashMap<Character, Integer> alphabet = new LinkedHashMap<Character, Integer>();

    public static int alphabetLength = alphabetchars.length;
    public static Pattern stupidPattern = Pattern.compile("^([A-Z]+)(\\d+)$");
    public static Pattern smartPattern = Pattern.compile("^R([\\d]+)C([\\d]+)$");

    public static void main(String[] args) throws IOException {



        for(int i = 0; i < alphabetchars.length;i++)
        {
            alphabet.put(alphabetchars[i], i);
        }


        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bw.readLine());

        for (int i = 1; i <= n; i++) {
            String coord = bw.readLine();

            Matcher m = smartPattern.matcher(coord);

            if (m.matches()) {
                String row = m.group(1);
                int col = Integer.parseInt(m.group(2));
                System.out.println(smartToStupid(col, row));
            } else {
                m = stupidPattern.matcher(coord);
                m.matches();
                String col = m.group(1);
                String row = m.group(2);
                System.out.println(stupidToSmart(col, row));
            }

        }
       //  test();

        // System.out.print(smartToStupid(800000, "1"));


    }

    public static int relUniCodeCharValue(char c) {
        return c - 'A' + 1;
    }

    public static String stupidToSmart(String col, String row) {


        col = new StringBuilder(col).reverse().toString();
        char[] colChars = col.toCharArray();

        int colNum = 0;

        for (int i = colChars.length - 1; i >= 0; i--) {

            int valueAt = relUniCodeCharValue(colChars[i]);

            colNum += valueAt * Math.pow(26, i);
        }

        return "R" + row + "C" + colNum;
    }

    //smart: R23C55
    //stupid: BC23
    public static String smartToStupid(int col, String row) {

//        char[] colChars = new char[7];
//        int length = colChars.length;
//
//
//        int i = 0;
//
////NTP1
//        if (col > 10000 && col < 100000) {
//            i = 10000;
//            colChars[length - 1] = 'P';
//            colChars[length - 2] = 'T';
//            colChars[length - 3] = 'N';
//
//        }
//
//        if (col > 100000 && col < 500000) {
//            i = 100000;
//            colChars[length - 1] = 'D';
//            colChars[length - 2] = 'X';
//            colChars[length - 3] = 'Q';
//            colChars[length - 4] = 'E';
//        }
//
//        //ABKPT
//        if (col > 500000) {
//            i = 500000;
//            colChars[length - 1] = 'T';
//            colChars[length - 2] = 'P';
//            colChars[length - 3] = 'K';
//            colChars[length - 4] = 'B';
//            colChars[length - 5] = 'A';
//        }
//
//        //ASMKF
//        if (col > 800000) {
//            i = 800000;
//            colChars[length - 1] = 'F';
//            colChars[length - 2] = 'K';
//            colChars[length - 3] = 'M';
//            colChars[length - 4] = 'S';
//            colChars[length - 5] = 'A';
//        }
//
//        char lastChar = alphabetchars[0];
//
//        for (; i < col; i++) {
//
//            char currentChar = alphabetchars[i % alphabetLength];
//            //Z
//            if (lastChar == alphabetchars[alphabetLength - 1]) {
//                for (int j = length - 2; j >= 0; j--) {
//                    if (colChars[j] == 0) {
//                        colChars[j] = alphabetchars[0];
//                        break;
//                    } else {
//                        if (colChars[j] == alphabetchars[alphabetLength - 1]) {
//                            colChars[j] = alphabetchars[0];
//                        } else {
//                            int index = alphabet.get(colChars[j]) + 1;
//                            colChars[j] = alphabetchars[index];
//                            break;
//
//                        }
//                    }
//
//                }
//
//        }
//            colChars[length - 1] = currentChar;
//            lastChar = currentChar;
//        }
//
//        String colString = String.valueOf(colChars).trim();
//
//
//        return colString + row;


        String colString = "";

        if (col < 27){
            colString += (char)('A'+col-1);
        }
        else{
            while(col>0){
                colString = (char)('A'+(col-1)%26)+colString;
                col--;
                col /= 26;
            }
        }
        return colString + row;

    }

    public static void test() {
        String stupidsmart = stupidToSmart("BC", "23");
        String smartstupid = smartToStupid(55, "23");

        if (stupidsmart.equals("R23C55") && smartstupid.equals("BC23")) {
            System.out.print("success!");
        } else {
            System.out.println("nope");
            System.out.println(stupidsmart);
            System.out.println(smartstupid);
        }


        for (int r = 0; r < 1; r++) {
            for (int c = 1; c < 200000; c++) {
                String orig = "R" + r + "C" + c;

                String changed = smartToStupid(c, String.valueOf(r));

                Matcher m = stupidPattern.matcher(changed);
                m.matches();
                String col = m.group(1);
                String row = m.group(2);


                String changedAgain = stupidToSmart(col, row);

                if (!orig.equals(changedAgain)) {
                    System.out.println(orig);
                    System.out.println(changedAgain);
                }
            }
        }
    }
}

