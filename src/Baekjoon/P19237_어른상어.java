package Baekjoon;
import java.util.*;
import java.io.*;

public class P19237_어른상어 {
    static int[] dx = new int[] {0, -1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Pair[][] grid = new Pair[n][n];
        int[] directions = new int[m + 1];
        int[][][] prio = new int[m + 1][5][4];

        int[] curx = new int[m + 1];
        int[] cury = new int[m + 1];
        boolean[] dead = new boolean[m + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (num > 0) {
                    curx[num] = i;
                    cury[num] = j;

                    grid[i][j] = new Pair(num, 0, 0, num);
                } else {
                    grid[i][j] = new Pair(0, 0, 0, 0);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            directions[i] = Integer.parseInt(st.nextToken());
        }

        // 우선순위 값 집어넣기
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= 4; j++) {
                st = new StringTokenizer(br.readLine());

                for (int l = 0; l < 4; l++) {
                    prio[i][j][l] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int time = 0;
        while (time <= 1000) {
            time += 1;
            for (int i = 1; i <= m; i++) { // 이제 아기 상어를 이동할거임
                if (dead[i]) continue;

                int dir = directions[i];

                int[] dirs = prio[i][dir];

                boolean canGo = false;
                for (int j = 0; j < 4; j++) {
                    int nx = curx[i] + dx[dirs[j]];
                    int ny = cury[i] + dy[dirs[j]];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    if (grid[nx][ny].idx == 0 || grid[nx][ny].num + k - time < 0) { // 냄새 없음
                        grid[nx][ny].semi = grid[nx][ny].num;
                        grid[nx][ny].sidx = grid[nx][ny].idx;
                        grid[nx][ny].num = time;
                        grid[nx][ny].idx = i;

                        curx[i] = nx;
                        cury[i] = ny;

                        directions[i] = dirs[j];

                        canGo = true;
                        break;
                    } else { // 냄새가 있음

                        if ((grid[nx][ny].sidx == 0 && grid[nx][ny].num == time) || (grid[nx][ny].semi + k - time <= 0 && grid[nx][ny].num == time)) { // 시간이 같으면 잡아먹힘
                            dead[i] = true;

                            canGo = true;
                            break;
                        }
                    }
                }

                if (!canGo) { // 이동할 수가 없음
                    for (int j = 0; j < 4; j++) {
                        int nx = curx[i] + dx[dirs[j]];
                        int ny = cury[i] + dy[dirs[j]];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                        if (grid[nx][ny].idx == i) { // 내 냄새가 있으면 거기로 이동
                            grid[nx][ny].semi = grid[nx][ny].num;
                            grid[nx][ny].sidx = grid[nx][ny].idx;
                            grid[nx][ny].num = time;

                            curx[i] = nx;
                            cury[i] = ny;

                            directions[i] = dirs[j];

                            break;
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                System.out.println(Arrays.toString(grid[i]));
            }
            System.out.println();

            // 탈출 조건
            boolean exit = true;

            if (dead[1]) { // 1번이 죽음
                exit = false;
            }

            if (exit) {
                for (int i = 2; i <= m; i++) {
                    if (!dead[i]) {
                        exit = false;
                        break;
                    }
                }

                if (exit) { // 탈출
                    break;
                }
            }
        }

        if (time > 1000) {
            System.out.println(-1);
        } else {
            System.out.println(time);
        }
    }

    static class Pair {
        int idx;
        int num;
        int semi;
        int sidx;

        public Pair(int idx, int num, int semi, int sidx) {
            this.idx = idx;
            this.num = num;
            this.semi = semi;
            this.sidx = sidx;
        }

        @Override
        public String toString() {
            return this.idx + " " + this.num;
        }
    }
}
