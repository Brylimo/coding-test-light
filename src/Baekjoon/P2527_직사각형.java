package Baekjoon;
import java.util.*;
import java.io.*;

public class P2527_직사각형 {
    static final int MAX_N = 50000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[MAX_N][MAX_N];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            for (int j = x; j < p; j++) {
                for (int k = y; k < q; k++) {
                    arr[j][k] = 1;
                }
            }
        }

    }
}
