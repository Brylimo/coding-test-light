package Baekjoon;
import java.util.*;
import java.io.*;

public class P2665_미로만들기 {
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    static int[] dx = new int[]{0,1,0,-1};
    static int[] dy = new int[]{1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] grid = new int[n][n];
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], (int) 1e9);
        }

        // grid에 값들을 채움 -> 0과 1의 위치를 바꿈
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                if (line.charAt(j) == '1') {
                    grid[i][j] = 0;
                } else {
                    grid[i][j] = 1;
                }
            }
        }

        // dijkstra 알고리즘
        dist[0][0] = 0;
        pq.offer(new Pair(0, 0, 0));

        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            if (dist[p.x][p.y] < p.cost) continue;

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if (dist[nx][ny] > dist[p.x][p.y] + grid[nx][ny]) {
                    dist[nx][ny] = dist[p.x][p.y] + grid[nx][ny];
                    pq.offer(new Pair(nx, ny, dist[nx][ny]));
                }
            }
        }

        System.out.println(dist[n - 1][n - 1]);
    }

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int cost;

        public Pair(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair o) {
            return this.cost - o.cost;
        }
    }
}
