package Baekjoon;
import java.util.*;
import java.io.*;

public class P14938_서강그라운드 {
    static final int INT_MAX = Integer.MAX_VALUE;
    static int n, m, r;
    static int[] items;
    static long[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n];
        dist = new long[n][n];

        // dist 배열 초기화
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INT_MAX);
        }

        for (int i = 0; i < n; i++) {
            dist[i][i] = 0; // (i, i)는 0으로 초기화
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) { // item 초기화
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < r; i++) { // 길이 초기화
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            dist[a - 1][b - 1] = Math.min(dist[a - 1][b - 1], l);
            dist[b - 1][a - 1] = Math.min(dist[b - 1][a - 1], l);
        }

        for (int k = 0; k < n; k++) { // 플로이드-워셜 알고리즘
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < INT_MAX) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int totalItem = 0; // i 지역에 내렸을 때 얻을 수 있는 총 아이템 개수
            for (int j = 0; j < n; j++) {
                if (dist[i][j] <= m) { // i 지역에 내렸고 j 지역에 갈 수 있으면
                    totalItem += items[j];
                }
            }

            ans = Math.max(ans, totalItem);
        }

        System.out.println(ans);
    }
}
