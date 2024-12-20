package Baekjoon;
import java.util.*;
import java.io.*;

public class P2573_빙산 {
    static int n, m;
    static int[][] grid;
    static int[][] gridNext;
    static boolean[][] visited;

    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    static Queue<Pair> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        gridNext = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        int iceBergCnt = 0;

        // 녹은 후 몇 개의 덩어리가 있나 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] > 0) {
                    visited[i][j] = true;
                    queue.offer(new Pair(i, j));

                    iceBergCnt += 1;
                    bfs();
                }
            }
        }

        if (iceBergCnt != 1) {
            System.out.println(time);
            System.exit(1);
        }

        while (true) {
            initialize();

            time += 1;

            // 빙산 녹는 부분 연산
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] > 0) {

                        int oceanCnt = 0;
                        // 각 칸마다 4 방향 순회하며 check
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                            if (grid[nx][ny] == 0) { // 바다
                                oceanCnt += 1;
                            }
                        }

                        // grid에 영향을 주지 않기 위해 gridNext에 저장
                        gridNext[i][j] = grid[i][j] - oceanCnt;

                        if (gridNext[i][j] < 0) gridNext[i][j] = 0;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = gridNext[i][j];
                }
            }

            iceBergCnt = 0;

            // 녹은 후 몇 개의 덩어리가 있나 확인
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && grid[i][j] > 0) {
                        visited[i][j] = true;
                        queue.offer(new Pair(i, j));

                        iceBergCnt += 1;
                        bfs();
                    }
                }
            }

            if (iceBergCnt != 1) {
                break;
            }
        }

        if (iceBergCnt == 0) {
            System.out.println(0);
        } else {
            System.out.println(time);
        }
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (!visited[nx][ny] && grid[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    queue.offer(new Pair(nx, ny));
                }
            }
        }
    }

    static void initialize() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = false;
            }
        }
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
