package Baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P11909_배열탈출 {
    static int n;
    static int[][] grid;
    static int[][] dist;
    static int[] dx = new int[] {0, 1};
    static int[] dy = new int[] {1, 0};
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int value;

        public Pair(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(Pair other) {
            return this.value - other.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = (int)1e9;
            }
        }

        dist[0][0] = 0;
        pq.offer(new Pair(0, 0, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (dist[current.x][current.y] < current.value) continue;

            for (int i = 0; i < 2; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                int cost = 0;
                if (grid[current.x][current.y] < grid[nx][ny]) {
                    cost += grid[nx][ny] - grid[current.x][current.y] + 1;
                } else if (grid[current.x][current.y] == grid[nx][ny]) {
                    cost += 1;
                }

                int temp = cost + dist[current.x][current.y];
                if (dist[nx][ny] > temp) {
                    dist[nx][ny] = temp;
                    pq.offer(new Pair(nx, ny, dist[nx][ny]));
                }
            }
        }

        System.out.println(dist[n - 1][n - 1]);
    }
}
