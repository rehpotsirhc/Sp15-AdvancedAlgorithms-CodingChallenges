import java.io.*;

public class CF7D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();
        char[] cc = new char[n + n];
        s.getChars(0, n, cc, 0);
        for (int i = 0; i < n; i++)
            cc[n + n - 1 - i] = cc[i];
        int[] zz = new int[n + n];
        int l = 0, r = 0;	// z-box: [l, r)
        for (int i = 1; i < n + n; i++) {
            if (i < r && zz[i - l] < r - i) {
                zz[i] = zz[i - l];
                continue;
            }
            if (i < r)
                l = i;
            else
                l = r = i;
            while (r < n + n && cc[r] == cc[r - l])
                r++;
            zz[i] = r - l;
        }
        int[] dd = new int[n + 1];
        long deg = 0;
        for (int m = 1; m <= n; m++)
            if (zz[n + n - m] == m) {
                dd[m] = dd[m / 2] + 1;
                deg += dd[m];
            }
        System.out.println(deg);
    }
}