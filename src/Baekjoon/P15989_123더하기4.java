package Baekjoon;
import java.util.*;
import java.io.*;

public class P15989_123더하기4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[][] dp = new int[10001][4]; // 1~k까지를 이용해 구성하는 방법의 수

            dp[1][1] = 1;
            dp[2][1] = 1;
            dp[2][2] = 1;
            dp[3][1] = 1;
            dp[3][2] = 1;
            dp[3][3] = 1;
            for (int i = 4; i <= n; i++) {
                dp[i][1] = dp[i - 1][1];
                if (i >= 2) {
                    dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
                }
                if (i >= 3) {
                    dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
                }
            }

            int sum = 0;
            for (int i = 1; i < 4; i++) {
                sum += dp[n][i];
            }

            System.out.println(sum);
        }
    }
}
