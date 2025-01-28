package Baekjoon;
import java.util.*;
import java.io.*;

public class P13300_방_배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[7][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[y][s]++;
        }

        int ans = 0;
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 2; j++) {
                ans += Math.ceil((double)arr[i][j] / k);
            }
        }

        System.out.println(ans);
    }
}
