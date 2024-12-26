package Baekjoon;
import java.util.*;
import java.io.*;

public class P15683_감시 {
    static final int INT_MAX = Integer.MAX_VALUE;
    static int n, m, ans = INT_MAX;
    static int[][] grid;
    static int[][] nextGrid;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    static int[][] dirs = new int[][]{// 각각의 cctv에 대한 방향을 담음
            {},
            {1},
            {1, 3},
            {0, 1},
            {0, 1, 3},
            {0, 1, 2, 3}
    };

    static ArrayList<Pair> locs = new ArrayList<>();


    static void paint(boolean flag, Pair loc, int dir) {
        int index = loc.index;
        int x, y;

        int[] temp = new int[dirs[index].length];
        for (int i = 0; i < dirs[index].length; i++) { // cctv 회전
            temp[i] = (dirs[index][i] + dir) % 4;
        }

        for (int i = 0; i < temp.length; i++) {
            x = loc.x;
            y = loc.y;

            while (true) {
                int nx = x + dx[temp[i]];
                int ny = y + dy[temp[i]];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (flag && grid[nx][ny] != 6) { // 벽이 아니면
                        if (grid[nx][ny] == 0) { // 빈 칸만 -1 표시
                            nextGrid[nx][ny] -= 1;
                        }

                        x = nx;
                        y = ny;
                    } else if (!flag && grid[nx][ny] != 6) {
                        if (nextGrid[nx][ny] < 0) {
                            nextGrid[nx][ny] += 1; // 원상태로 되돌림
                        }

                        x = nx;
                        y = ny;
                    } else { // 벽이면 종료
                        break;
                    }
                } else { // grid를 벗어나면 종료
                    break;
                }
            }
        }
    }

    static void calculate(int current) {
        if (current == locs.size()) {

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (nextGrid[i][j] == 0) {
                        cnt += 1;
                    }
                }
            }

            ans = Math.min(ans, cnt);

            return;
        }

        Pair loc = locs.get(current);

        for (int i = 0; i < 4; i++) { // cctv 모든 방향 회전
            // 칠하기
            paint(true, loc, i);

            calculate(current + 1);

            // 걷어내기 -> 거꾸로 칠함
            paint(false, loc, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        nextGrid = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                nextGrid[i][j] = grid[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] >= 1 && grid[i][j] <= 5) {
                    locs.add(new Pair(i, j, grid[i][j]));
                }
            }
        }

        calculate(0);

        System.out.println(ans);
    }

    static class Pair {
        int x;
        int y;
        int index;

        public Pair(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }
}
