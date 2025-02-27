package Baekjoon;

import java.util.*;
import java.io.*;

public class P2157_여행 {
    static int n, m, k;
    static ArrayList<Pair>[] graph;
    //static boolean[] visited;
    static int[][] table;
    static int[][] dp;
    //static Queue<Pair> queue = new LinkedList<>();

    /*static void dfs(int root, int rank, int value) {
        if (rank > m) return;

        if (root == n) {
            ans = Math.max(ans, value);
        }

        for (Pair node : graph[root]) {
            if (root >= node.x) continue;

            if (!visited[node.x]) {
                visited[node.x] = true;
                dfs(node.x, rank + 1, value + node.score);
                visited[node.x] = false;
            }
        }
    }*/

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        table = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(table[i], Integer.MAX_VALUE);
        }

        //visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a < b) {
                //graph[a].add(new Pair(b, c));
                table[a][b] = Math.min(table[a][b], c);
                table[b][a] = Math.min(table[b][a], c);
            }
        }

        dp = new int[n + 1][m + 1];
        //visited[1] = true;

        //dfs(1, 1, 0);

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        // dp[1][1]은 방문
        dp[1][1] = 0;
        for (int i = 2; i <= n; i++) { // 인덱스를 증가시킴
            for (int j = 2; j < m; j++) {

                for (int k = 1; k <= n; k++) {
                    if (dp[k][j - 1] < Integer.MAX_VALUE && i > k) {
                        dp[i][j] = Math.max(dp[i][j], dp[k][j - 1] + table[i][k]);
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < m; i++) {
            if (dp[n][i] < Integer.MAX_VALUE) {
                ans = Math.max(ans, dp[n][i]);
            }
        }

        System.out.println(ans);
    }

    static class Pair {
        int x;
        int score;

        public Pair(int x, int score) {
            this.x = x;
            this.score = score;
        }
    }

}
