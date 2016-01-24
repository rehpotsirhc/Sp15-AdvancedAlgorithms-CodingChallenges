import java.util.Scanner;

/**
 * Created by Chris on 1/17/2015.
 */
class TEST {


    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int num = in.nextInt();
            if(num != 42) System.out.println(num);
            else break;
        }

    }

}
