import java.util.Scanner;

/**
 * Created by Chris on 1/17/2015.
 */
public class TheatreSquare {

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);

        long n = s.nextInt();
        long m = s.nextInt();
        long a = s.nextInt();

        long horizontal = n  / a;
        long vertical = m / a;

        if(n % a != 0) horizontal++;
        if(m % a != 0) vertical++;

        System.out.print(horizontal * vertical);

    }
}
