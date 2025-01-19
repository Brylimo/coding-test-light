package Baekjoon;
import java.util.*;
import java.io.*;

public class P1987_알파벳 {
    static int r, c, ans = 1;
    static char[][] grid;
    //static boolean[][][] visited;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    static void dfs(int x, int y, int dist, int snum) {

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

            if (((snum >> (grid[nx][ny] - 'A')) & 1) == 0) { // 해당 숫자가 없음
                int tnum = snum | (1 << (grid[nx][ny] - 'A'));

                //visited[nx][ny][tnum] = true;
                ans = Math.max(ans, dist + 1);
                dfs(nx, ny, dist + 1, tnum);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        grid = new char[r][];
        //visited = new boolean[r][c][1 << 26];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            grid[i] = line.toCharArray();
        }

        //visited[0][0][1 << (grid[0][0] - 'A')] = true;
        dfs(0, 0, 1, (1 << (grid[0][0] - 'A')));

        System.out.println(ans);
    }
}
