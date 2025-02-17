package SWEA;
import java.util.*;
import java.io.*;

public class Solved_5653_줄기세포배양 {
    static int n, m, k;
    static int[][] grid;
    static int[][] wGrid;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            grid = new int[n][m];
            wGrid = new int[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    wGrid[i][j] = grid[i][j];
                }
            }


        }
    }
}
