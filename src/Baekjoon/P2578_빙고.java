package Baekjoon;
import java.util.*;
import java.io.*;

public class P2578_빙고 {
    static int n = 5;
    static int[][] arr;
    static int[][] bingo;
    static boolean[][] visited;

    static void print() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(visited[i]));
        }
        System.out.println();
    }

    static boolean bingoCount() {
        int bingoCnt = 3;

        // 가로 확인
        for (int i = 0; i < n; i++) {
            boolean isRowBingo = true;
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    isRowBingo = false;
                    break;
                }
            }

            if (isRowBingo) {
                bingoCnt--;
            }
        }

        // 세로 확인
        for (int i = 0; i < n; i++) {
            boolean isColumnBingo = true;
            for (int j = 0; j < n; j++) {
                if (!visited[j][i]) {
                    isColumnBingo = false;
                    break;
                }
            }

            if (isColumnBingo) {
                bingoCnt--;
            }
        }

        // 대각선 확인 /
        boolean isDiagonalBingo1 = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i][i]) {
                isDiagonalBingo1 = false;
                break;
            }
        }

        if (isDiagonalBingo1) {
            bingoCnt--;
        }

        boolean isDiagonalBingo2 = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i][n - i - 1]) {
                isDiagonalBingo2 = false;
                break;
            }
        }

        if (isDiagonalBingo2) {
            bingoCnt--;
        }

        if (bingoCnt <= 0) {
            return true;
        } else {
            return false;
        }
    }

    static void check(int target) {

        OUT : for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == target) {
                    visited[i][j] = true;

                    break OUT;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bingo = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                bingo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int target = bingo[i][j];

                check(target);
                ans += 1;
                if (bingoCount()) {
                    System.out.println(ans);
                    System.exit(0);
                }
            }
        }

    }
}
