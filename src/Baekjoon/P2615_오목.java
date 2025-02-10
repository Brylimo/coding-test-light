package Baekjoon;
import java.util.*;
import java.io.*;

public class P2615_오목 {
    static int[][] grid;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        grid = new int[19][19];
        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 19; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int win = -1;
        int sx = -1, sy = -1;
        int fx = -1, fy = -1;
        // 가로 방향 체크
        OUT:
        for (int i = 0; i < 19; i++) {
            int cnt = 0;
            int before = 0;
            for (int j = 0; j < 19; j++) {
                if (grid[i][j] != 0 && before == grid[i][j]) {
                    cnt += 1;
                } else {
                    if (cnt == 5) { // 5개이면
                        win = before;
                        sx = fx;
                        sy = fy;

                        break OUT;
                    }

                    fx = i;
                    fy = j;

                    cnt = 1;
                }

                before = grid[i][j];

            }

            if (cnt == 5) {
                win = before;
                sx = fx;
                sy = fy;

                break OUT;
            }
        }

        if (win != -1) {
            System.out.println(win);
            System.out.println((sx + 1) + " " + (sy + 1));
            System.exit(0);
        }

        // 세로 방향 체크
    OUT: for (int j = 0; j < 19; j++) {
            int cnt = 0;
            int before = 0;
            for (int i = 0; i < 19; i++) {
                if (grid[i][j] != 0 && before == grid[i][j]) {
                    cnt += 1;
                } else {
                    if (cnt == 5) { // 5개이면
                        win = before;
                        sx = fx;
                        sy = fy;

                        break OUT;
                    }

                    fx = i;
                    fy = j;

                    cnt = 1;
                }

                before = grid[i][j];
            }

            if (cnt == 5) {
                win = before;
                sx = fx;
                sy = fy;

                break OUT;
            }
        }

        if (win != -1) {
            System.out.println(win);
            System.out.println((sx + 1) + " " + (sy + 1));
            System.exit(0);
        }

        // 대각선 방향 체크 /
        int[] dx = new int[]{-1, 1};
        int[] dy = new int[]{1, -1};

        OUT : for (int i = 0; i < 19; i++) {
            int cnt = 0;
            int before = 0;
            for (int j = 0; j < 19; j++) {
                if (grid[i][j] != 0) {
                    before = grid[i][j];
                    cnt = 1;
                    for (int k = 1; k < 3; k++) {
                        for (int p = 0; p < 2; p++) {
                            int tx = i + dx[p] * k;
                            int ty = j + dy[p] * k;

                            if (tx < 0 || tx >= 19 || ty < 0 || ty >= 19) continue;

                            if (before == grid[tx][ty]) {
                                cnt += 1;
                            }
                        }
                    }

                    boolean sFlag = false;
                    for (int p = 0; p < 2; p++) {
                        int tx = i + dx[p] * 3;
                        int ty = j + dy[p] * 3;

                        if (tx < 0 || tx >= 19 || ty < 0 || ty >= 19) continue;

                        if (before == grid[tx][ty]) {
                            sFlag = true;
                        }
                    }

                    if (cnt == 5 && !sFlag) {
                        win = before;
                        sx = i + 2;
                        sy = j - 2;

                        break OUT;
                    }
                }
            }
        }

        if (win != -1) {
            System.out.println(win);
            System.out.println((sx + 1) + " " + (sy + 1));
            System.exit(0);
        }

        // 대각선 방향 체크 \
        dx = new int[]{-1, 1};
        dy = new int[]{-1, 1};

        OUT : for (int i = 0; i < 19; i++) {
            int cnt = 0;
            int before = 0;
            for (int j = 0; j < 19; j++) {
                if (grid[i][j] != 0) {
                    before = grid[i][j];
                    cnt = 1;
                    for (int k = 1; k < 3; k++) {
                        for (int p = 0; p < 2; p++) {
                            int tx = i + dx[p] * k;
                            int ty = j + dy[p] * k;

                            if (tx < 0 || tx >= 19 || ty < 0 || ty >= 19) continue;

                            if (before == grid[tx][ty]) {
                                cnt += 1;
                            }
                        }
                    }

                    boolean sFlag = false;
                    for (int p = 0; p < 2; p++) {
                        int tx = i + dx[p] * 3;
                        int ty = j + dy[p] * 3;

                        if (tx < 0 || tx >= 19 || ty < 0 || ty >= 19) continue;

                        if (before == grid[tx][ty]) {
                            sFlag = true;
                        }
                    }

                    if (cnt == 5 && !sFlag) {
                        win = before;
                        sx = i - 2;
                        sy = j - 2;

                        break OUT;
                    }
                }
            }
        }

        if (win != -1) {
            System.out.println(win);
            System.out.println((sx + 1) + " " + (sy + 1));
            System.exit(0);
        }

        System.out.println(0);
    }
}
