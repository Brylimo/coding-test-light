package Baekjoon;
import java.util.*;
import java.io.*;

public class P1926_그림 {
    static int n, m;
    static int[][] grid;
    static boolean[][] visited;
    static Queue<Pair> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    cnt += 1;

                    visited[i][j] = true;
                    queue.offer(new Pair(i, j));

                    ans = Math.max(ans, bfs());
                }
            }
        }

        System.out.println(cnt);
        System.out.println(ans);
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int bfs() {
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        int cnt = 0;
        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            cnt += 1;
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (!visited[nx][ny] && grid[nx][ny] == 1) {
                    visited[nx][ny] = true;

                    queue.offer(new Pair(nx, ny));
                }
            }
        }

        return cnt;
    }
}
