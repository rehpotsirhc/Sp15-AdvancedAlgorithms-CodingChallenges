import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Chris on 3/19/2015.
 */
class ATM {


    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");

        double fee = .5;
        double amount = Integer.parseInt(line[0]);
        double balance = Double.parseDouble(line[1]);

        String toReturn;

        if(amount % 5 == 0 && balance > (amount + fee))
        {
            balance -= (amount + fee);
            toReturn = NumberFormat.getCurrencyInstance(Locale.US).format(balance).replace("$", "").replace(",", "");
        }
        else toReturn = String.valueOf(balance);

        if(toReturn.endsWith(".0")) toReturn += 0;
        System.out.println(toReturn);
    }
}
