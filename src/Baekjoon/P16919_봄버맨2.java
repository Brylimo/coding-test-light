package Baekjoon;
import java.util.*;
import java.io.*;

public class P16919_봄버맨2 {
    static int r, c, n;
    static char[][] grid;
    static StringBuilder sb = new StringBuilder();

    static void print() {
        for (int i = 0; i < r; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        grid = new char[r][c];
        for (int i = 0; i < r; i++) {
            String input = br.readLine();
            for (int j = 0; j < c; j++) {
                grid[i][j] = input.charAt(j);
            }
        }

        if (n == 1) { // 초기 격자판 상태
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    sb.append(grid[i][j]);
                }
                sb.append('\n');
            }
        } else if (n % 2 == 0) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    sb.append('O');
                }
                sb.append('\n');
            }
        } else {
            int[] dx = new int[] {-1, 0, 1, 0};
            int[] dy = new int[] {0, 1, 0, -1};

            char[][] vGrid = new char[r][c];

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    vGrid[i][j] = grid[i][j];
                }
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (grid[i][j] == 'O') {
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                            vGrid[nx][ny] = 'O';
                        }
                    }
                }
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (vGrid[i][j] == '.') {
                        grid[i][j] = 'O';
                    } else {
                        grid[i][j] = '.';
                    }
                }
            }

            if (((n - 1) / 2) % 2 == 1) {
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        sb.append(grid[i][j]);
                    }
                    sb.append('\n');
                }
            } else {
                vGrid = new char[r][c];

                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        vGrid[i][j] = grid[i][j];
                    }
                }

                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (grid[i][j] == 'O') {
                            for (int k = 0; k < 4; k++) {
                                int nx = i + dx[k];
                                int ny = j + dy[k];

                                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                                vGrid[nx][ny] = 'O';
                            }
                        }
                    }
                }

                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (vGrid[i][j] == '.') {
                            sb.append('O');
                        } else {
                            sb.append('.');
                        }
                    }
                    sb.append('\n');
                }
            }
        }

        System.out.println(sb);
    }
}
