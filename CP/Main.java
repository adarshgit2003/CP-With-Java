package CP;

import java.io.*;
import java.util.*;

public class Main{
    public static final long MOD = 1000000007;
    public static void solve(){
        long n= sc.nextLong();
        long ans=1;
        for (long i=1; i<=n; i++){
            ans = (ans*2)%MOD;
        }
        out.println(ans);
    }
    public static void main(String[] args) {

        try {
            sc = new MyScanner();
            out = new PrintWriter(System.out);
//            boolean isLocal = System.getProperty("ONLINE_JUDGE") == null;
//            if (isLocal) {
//                sc = new MyScanner(new File("input.txt"));
//                out = new PrintWriter(new File("output.txt"));
//            }
            long t = 1;
//            t = sc.nextLong();
            while (t-- > 0) {
                solve();
            }
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static PrintWriter out;
    public static MyScanner sc;
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;
        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public MyScanner(File file) {
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
