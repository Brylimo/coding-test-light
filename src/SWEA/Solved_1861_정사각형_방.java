package SWEA;
import java.util.*;
import java.io.*;

public class Solved_1861_정사각형_방 {
    static int n, ans, num;
    static int[][] grid;
    static boolean[][] visited;

    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    static void dfs(int x, int y, int cnt, int current) {
        if (cnt > ans) {
            ans = cnt;
            num = current;
        } else if (cnt == ans) {
            if (num > current) {
                num = current;
            }
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

            if (!visited[nx][ny] && grid[nx][ny] - grid[x][y] == 1) {
                visited[nx][ny] = true;
                dfs(nx, ny, cnt + 1, current);
                visited[nx][ny] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());

            grid = new int[n][n];
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    visited[i][j] = true;
                    dfs(i, j, 1, grid[i][j]);
                    visited[i][j] = false;
                }
            }

            System.out.printf("#%d %d %d\n", t, num, ans);
            ans = 0;
            num = 0;
        }
    }
}
