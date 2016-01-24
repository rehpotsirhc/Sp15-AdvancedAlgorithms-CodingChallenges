import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 *
 * @author Suraj Singh
 */
class DishOwner {
    static int parent[];
    static int si[];
    public static int findParent(int i)
    {
        int j;
        if(i==parent[i])
        {
//            parent[i] = parent[parent[i]];
//            //parent[i] = findParent(parent[i]); 
//            i  = parent[i];
            return i;
        }
        else
            j = findParent(parent[i]);
        parent[i] = j;
        return j;
    }
    public static boolean union(int i, int j)
    {
        int x = findParent(i);
        int y = findParent(j);
        if(x==y)
            return false;
        if(si[x]>si[y])
            parent[y] = x;
        else if(si[y]>si[x])
            parent[x] = y;
        return true;
    }
    public static void  main(String arsf[])
    {
        InputReader reader = new InputReader(System.in);
        int test = reader.nextInt();
        int n,a[],q,m,next,next2,par1,par2;
        String str;
        boolean flag;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<test;i++)
        {

            n = reader.nextInt();
            a = new int[n];
            parent = new int[n+1];
            si = new int[n+1];
            for(int j=1;j<=n;j++)
            {
                si[j] = reader.nextInt();
                parent[j] = j;
            }
            q = reader.nextInt();
            for(int j=0;j<q;j++)
            {
                flag = false;
                next = reader.nextInt();
                if(next==0) //update
                {
                    next = reader.nextInt();
                    next2 = reader.nextInt();
                    if(!union(next,next2))
                        sb.append("Invalid query!\n");
                }
                else if(next==1) //retrieve
                {
                    next = reader.nextInt();
                    sb.append(findParent(next)+"\n");
                }
            }
        }
        System.out.print(sb);
    }
}

class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c & 15;
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String next() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

} 