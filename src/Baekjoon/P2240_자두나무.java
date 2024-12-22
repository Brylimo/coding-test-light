package Baekjoon;
import java.util.*;
import java.io.*;

public class P2240_자두나무 {
    static final int INT_MIN = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] result = new int[t + 1];
        for (int i = 1; i <= t; i++) {
            result[i] = Integer.parseInt(br.readLine());
        }
        result[0] = result[1];

        int[][][] dp = new int[t + 1][w][2];

        for (int i = 1; i <= t; i++) {
            for (int j = 0; j < w; j++) {
                Arrays.fill(dp[i][j], INT_MIN);
            }
        }

        for (int i = 1; i < t; i++) {
            for (int j = 0; j < w; j++) {
                if (result[i - 1] != result[i]) {
                    if (j > 0 && dp[i - 1][j - 1][result[i - 1] - 1] >= 0) {
                        dp[i][j][result[i] - 1] = dp[i - 1][j - 1][result[i - 1] - 1] + 1; // 이동
                    }
                    if (dp[i - 1][j][result[i - 1] - 1] >= 0)
                        dp[i][j][result[i - 1] - 1] = dp[i - 1][j][result[i - 1] - 1];
                } else {
                    if (dp[i - 1][j][result[i] - 1] >= 0)
                        dp[i][j][result[i] - 1] = dp[i - 1][j][result[i] - 1] + 1;
                    if (dp[i - 1][j][result[i] - 1] >= 0)
                        dp[i][j][1-(result[i] - 1)] = dp[i - 1][j][result[i] - 1];
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < w; i++) {
            ans = Math.max(Math.max(ans, dp[t][i][0]), dp[t][i][1]);
        }

        System.out.println(ans);
    }
}
