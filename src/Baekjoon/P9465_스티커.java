package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * Main_9465_스티커
 * 난이도 1/10
 * dp
 * 620ms
 *
 * dp의 규칙성을 찾아서 dp 규칙성에 맞게 dp 테이블을 채우고 마지막에 최댓값을 구한다.
 *
 * dp[0][i] = Math.max(Math.max(dp[1][i - 1], dp[0][i - 2]), dp[1][i - 2]) + arr[0][i - 1];
 * dp[1][i] = Math.max(Math.max(dp[0][i - 1], dp[1][i - 2]), dp[0][i - 2]) + arr[1][i - 1];
 *
 */
public class P9465_스티커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[2][n];
            int[][] dp = new int[2][n + 1];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][1] = arr[0][0];
            dp[1][1] = arr[1][0];

            for (int i = 2; i <= n; i++) {
                dp[0][i] = Math.max(Math.max(dp[1][i - 1], dp[0][i - 2]), dp[1][i - 2]) + arr[0][i - 1];
                dp[1][i] = Math.max(Math.max(dp[0][i - 1], dp[1][i - 2]), dp[0][i - 2]) + arr[1][i - 1];
            }

            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}
