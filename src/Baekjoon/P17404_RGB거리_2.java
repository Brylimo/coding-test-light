package Baekjoon;
import java.util.*;
import java.io.*;

public class P17404_RGB거리_2 {
    static final int INT_MAX = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] table = new int[n][3];
        long[][][] dp = new long[n][3][3];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(dp[i][j], INT_MAX);
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int red = Integer.parseInt(st.nextToken());
            int green = Integer.parseInt(st.nextToken());
            int blue = Integer.parseInt(st.nextToken());

            table[i][0] = red;
            table[i][1] = green;
            table[i][2] = blue;
        }

        // 1번 집 초기화
        for (int i = 0; i < 3; i++) {
            dp[0][i][i] = table[0][i];
        }

        // 2번 집 초기화
        dp[1][0][1] = dp[0][0][0] + table[1][1];
        dp[1][0][2] = dp[0][0][0] + table[1][2];

        dp[1][1][0] = dp[0][1][1] + table[1][0];
        dp[1][1][2] = dp[0][1][1] + table[1][2];

        dp[1][2][0] = dp[0][2][2] + table[1][0];
        dp[1][2][1] = dp[0][2][2] + table[1][1];

        for (int i = 2; i < n - 1; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][0][0] = Math.min(dp[i][0][0], Math.min(dp[i - 1][j][1], dp[i - 1][j][2]) + table[i][0]);
                dp[i][1][0] = Math.min(dp[i][1][0], Math.min(dp[i - 1][j][1], dp[i - 1][j][2]) + table[i][0]);
                dp[i][2][0] = Math.min(dp[i][2][0], Math.min(dp[i - 1][j][1], dp[i - 1][j][2]) + table[i][0]);

                dp[i][0][1] = Math.min(dp[i][0][1], Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + table[i][1]);
                dp[i][1][1] = Math.min(dp[i][1][1], Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + table[i][1]);
                dp[i][2][1] = Math.min(dp[i][2][1], Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + table[i][1]);

                dp[i][0][2] = Math.min(dp[i][0][2], Math.min(dp[i - 1][j][0], dp[i - 1][j][1]) + table[i][2]);
                dp[i][1][2] = Math.min(dp[i][1][2], Math.min(dp[i - 1][j][0], dp[i - 1][j][1]) + table[i][2]);
                dp[i][2][2] = Math.min(dp[i][2][2], Math.min(dp[i - 1][j][0], dp[i - 1][j][1]) + table[i][2]);
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(Arrays.toString(dp[i][j]) + " ");
            }
            System.out.println();
        }

        // n번 집 초기화
        dp[n - 1][1][0] = Math.min(dp[n - 2][1][1], dp[n - 2][1][2]) + table[n - 2][0];
        dp[n - 1][2][0] = Math.min(dp[n - 2][2][1], dp[n - 2][2][2]) + table[n - 2][0];

        dp[n - 1][0][1] = Math.min(dp[n - 2][0][0], dp[n - 2][0][2]) + table[n - 2][0];
        dp[n - 1][2][1] = Math.min(dp[n - 2][2][0], dp[n - 2][2][2]) + table[n - 2][0];

        dp[n - 1][0][2] = Math.min(dp[n - 2][0][0], dp[n - 2][0][1]) + table[n - 2][0];
        dp[n - 1][1][2] = Math.min(dp[n - 2][1][0], dp[n - 2][1][1]) + table[n - 2][0];

        long ans = INT_MAX;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ans = Math.min(ans, dp[n - 1][i][j]);
            }
        }

        System.out.println(ans);
    }
}
