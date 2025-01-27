package Baekjoon;
import java.util.*;
import java.io.*;

public class P2669_직사각형_네개의_합집의_면적_구하기 {
    static final int MAX_N = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[MAX_N + 1][MAX_N + 1];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = x1; j < x2; j++) {
                for (int k = y1; k < y2; k++) {
                    arr[j][k] = 1;
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < MAX_N; i++) {
            for (int j = 0; j < MAX_N; j++) {
                if (arr[i][j] == 1) {
                    cnt += 1;
                }
            }
        }

        System.out.println(cnt);
    }
}
