package Baekjoon;
import java.math.BigInteger;
import java.util.*;
import java.io.*;
public class P2407_조합 {
    static int n, m;
    static BigInteger[][] dp;

    static BigInteger combination(int x, int y) {
        if (y == 0 || y == x) return BigInteger.ONE;

        if (dp[x][y] != null) {
            return dp[x][y];
        }

        dp[x][y] = combination(x - 1, y).add(combination(x - 1, y - 1));
        return dp[x][y];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new BigInteger[n + 1][m + 1];

        BigInteger ans = combination(n, m);
        System.out.println(ans);
    }
}
