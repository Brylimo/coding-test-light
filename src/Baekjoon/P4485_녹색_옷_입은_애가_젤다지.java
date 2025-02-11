package Baekjoon;
import java.util.*;
import java.io.*;

public class P4485_녹색_옷_입은_애가_젤다지 {
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
    static int[][] dist;
    static int[][] arr;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int n;

    static void dijkstra() {
        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (current.d > dist[current.x][current.y]) continue;

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if (dist[nx][ny] > current.d + arr[nx][ny]) {
                    dist[nx][ny] = current.d + arr[nx][ny];
                    pq.offer(new Pair(dist[nx][ny], nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int idx = 1;
        while (true) {
            n = Integer.parseInt(br.readLine());

            if (n == 0) break;

            dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dist[0][0] = arr[0][0];
            pq.offer(new Pair(arr[0][0], 0, 0));
            dijkstra();

            System.out.printf("Problem %d: %d\n", idx++, dist[n - 1][n - 1]);
        }
    }

    static class Pair implements Comparable<Pair>{
        int d;
        int x;
        int y;

        public Pair(int d, int x, int y) {
            this.d = d;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair other) {
            return this.d - other.d;
        }
    }
}
