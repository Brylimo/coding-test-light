package Baekjoon;
import java.util.*;
import java.io.*;

public class P14719_빗물 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[][] grid = new int[h][w];

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[w];
        for (int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 벽 채우기
        for (int i = 0; i < w; i++) {
            for (int j = h - 1; j >= h - arr[i]; j--) {
                grid[j][i] = 1;
            }
        }

        int ans = 0;
        for (int i = h - 1; i >= 0; i--) {
            boolean onWall = false;

            int cnt = 0;
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == 1 && !onWall) {
                    onWall = true;
                } else if (grid[i][j] == 0 && onWall) {
                    cnt += 1;
                } else if (grid[i][j] == 1 && onWall) { // flush
                    ans += cnt;
                    cnt = 0;
                }
            }

        }

        System.out.println(ans);
    }
}
