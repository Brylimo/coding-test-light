package Baekjoon;
import java.util.*;
import java.io.*;

public class P17404_RGB거리_2 {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        long[][] dp = new long[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long ans = INT_MAX;
        for (int i = 0; i < 3; i++) { // 1번 집의 색 경우의 수
            for (int k = 0; k < n; k++) { // DP 테이블 초기화
                Arrays.fill(dp[k], INT_MAX);
            }

            for (int j = 0; j < 3; j++) { // 1번 집을 dp 테이블에 저장
                if (i == j) {
                    dp[0][i] = arr[0][i];
                }
            }

            for (int j = 1; j < n; j++) { // i번 집 dp 테이블 저장
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + arr[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + arr[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + arr[j][2];

                if (j == n - 1) { // n번 집
                    if (i == 0) {
                        ans = Math.min(Math.min(ans, dp[j][1]), dp[j][2]);
                    } else if (i == 1) {
                        ans = Math.min(Math.min(ans, dp[j][0]), dp[j][2]);
                    } else {
                        ans = Math.min(Math.min(ans, dp[j][0]), dp[j][1]);
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
