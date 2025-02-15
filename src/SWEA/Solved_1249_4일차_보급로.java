package SWEA;
import java.util.*;
import java.io.*;

public class Solved_1249_4일차_보급로 {
    static int n;
    static int[][] grid;
    static int[][] dist;

    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());

            // grid에 값 집어넣기
            grid = new int[n][n];
            dist = new int[n][n];

            // dist 초기화
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                for (int j = 0; j < n; j++) {
                    grid[i][j] = input.charAt(j) - '0';
                }
            }

            pq.offer(new Pair(0, 0, grid[0][0]));

            while (!pq.isEmpty()) {
                Pair current = pq.poll();

                if (dist[current.x][current.y] < current.dist) continue;

                for (int i = 0; i < 4; i++) {
                    int nx = current.x + dx[i];
                    int ny = current.y + dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    if (current.dist + grid[nx][ny] < dist[nx][ny]) {
                        dist[nx][ny] = current.dist + grid[nx][ny];
                        pq.offer(new Pair(nx, ny, dist[nx][ny]));
                    }
                }
            }

            System.out.printf("#%d %d\n", t, dist[n - 1][n - 1]);
        }
    }

    static class Pair implements Comparable<Pair>{
        int x;
        int y;
        int dist;

        public Pair(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair other) {
            return this.dist - other.dist;
        }
    }
}
