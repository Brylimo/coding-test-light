package SWEA;
import java.util.*;
import java.io.*;

public class Solved_1953_탈주범_검거 {
    static int n, m, l;
    static int[][] arr;
    static int[][] step;
    static boolean[][] visited;
    static Queue<int[]> queue = new ArrayDeque<>();
    static int[][][] ways = new int[][][] {
            {},
            { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }, // 1
            { { -1, 0 }, { 1, 0 } }, // 2
            { { 0, -1 }, { 0, 1 } }, // 3
            { { -1, 0 }, { 0, 1 } }, // 4
            { { 1, 0 }, { 0, 1 } }, // 5
            { { 1, 0 }, { 0, -1 } }, // 6
            { { -1, 0 }, { 0, -1 } } // 7
    };

    static void bfs() {
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int[][] delta = ways[arr[current[0]][current[1]]];

            for (int i = 0; i < delta.length; i++) {
                int nx = current[0] + delta[i][0];
                int ny = current[1] + delta[i][1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (!visited[nx][ny] && arr[nx][ny] > 0) {

                    if (step[current[0]][current[1]] < l) {
                        int posX = delta[i][0] * (-1);
                        int posY = delta[i][1] * (-1);

                        boolean canGo = false;
                        if (posX == 0 && posY == 0) {
                            canGo = true;
                        } else {
                            for (int j = 0; j < ways[arr[nx][ny]].length; j++) {
                                if (ways[arr[nx][ny]][j][0] == posX && ways[arr[nx][ny]][j][1] == posY) {
                                    canGo = true;
                                    break;
                                }
                            }
                        }

                        if (canGo) {
                            visited[nx][ny] = true;
                            step[nx][ny] = step[current[0]][current[1]] + 1;
                            queue.offer(new int[] {nx, ny});
                        }

                        //print();
                    }
                }
            }

        }
    }

    static void print() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(step[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            arr = new int[n][m];
            step = new int[n][m];
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited[r][c] = true;
            step[r][c] = 1; // 처음에 1로 시작
            queue.offer(new int[] {r, c});
            bfs();

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j]) cnt++;
                }
            }

            System.out.printf("#%d %d\n", t, cnt);
        }
    }
}
