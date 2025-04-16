package Baekjoon;
import java.util.*;
import java.io.*;

public class P20947_습격받은_도시 {
    static int n;
    static char[][] grid;

    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        grid = new char[n][n];
        char[][] nextGrid = new char[n][n];
        int[][] trace = new int[n][n];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = input.charAt(j);
                nextGrid[i][j] = grid[i][j];
            }
        }

        //while (true) {
            ArrayList<int[]> wList = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (nextGrid[i][j] == 'O') {
                        wList.add(new int[] { i, j });
                    }
                }
            }

            //if (wList.size() == 0) break;

            int maxVal = 0;
            int tx = 0;
            int ty = 0;

            // 흔적 표시
            for (int[] w : wList) {

                // trace 완성
                for (int k = 0; k < 4; k++) {
                    for (int i = 1; i <= n; i++) {
                        int nx = w[0] + dx[k] * i;
                        int ny = w[1] + dy[k] * i;

                        if (nx < 0 || nx >= n || ny < 0 || ny >= n || nextGrid[nx][ny] == 'X' || nextGrid[nx][ny] == 'O') {
                            break;
                        }

                        if (nextGrid[nx][ny] == '.') {
                            trace[nx][ny] += 1;
                            if (maxVal < trace[nx][ny]) {
                                maxVal = trace[nx][ny];

                                tx = nx;
                                ty = ny;
                            }
                        }
                    }
                }

            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (trace[i][j] == 0 && grid[i][j] == '.') {
                        grid[i][j] = 'B';
                    }
                }
            }

            // 폭탄 설치
            /*grid[tx][ty] = 'B';
            for (int k = 0; k < 4; k++) {
                for (int i = 1; i <= n; i++) {
                    int nx = tx + dx[k] * i;
                    int ny = ty + dy[k] * i;

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        break;
                    }

                    if (nextGrid[nx][ny] == 'X') {
                        nextGrid[nx][ny] = '.';
                    }

                }
            }*/

            /*for (int i = 0; i < n; i++) {
                System.out.println(Arrays.toString(grid[i]));
            }
            System.out.println();*/
        //}

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
