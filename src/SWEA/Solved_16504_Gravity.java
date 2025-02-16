package SWEA;
import java.util.*;
import java.io.*;

public class Solved_16504_Gravity {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];

            int[][] grid = new int[100][100];
            int[][] vGrid = new int[100][100];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < arr[i]; j++) {
                    grid[i][j] = i + 1;
                }
            }

            for (int j = 0; j < 100; j++) {
                int idx = n - 1;
                for (int i = 99; i >= 0; i--) {
                    if (grid[i][j] > 0) {
                        vGrid[idx--][j] = grid[i][j];
                    }
                }
            }

            int ans = 0;
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (vGrid[i][j] > 0) {
                        ans = Math.max(ans, Math.abs(vGrid[i][j] - (i + 1)));
                    }
                }
            }

            System.out.printf("#%d %d\n", t, ans);
        }
    }
}
