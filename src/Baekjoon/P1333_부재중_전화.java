package Baekjoon;
import java.util.*;
import java.io.*;

public class P1333_부재중_전화 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int m = 2 * (20 * 180 + 5 * 19);
        int[] arr = new int[m];

        int i = 0;
        int cnt = 0;
        while (cnt < n) { // 1차적으로 기록
            for (int j = 0; j < l; j++) {
                arr[i++] = 1;
            }
            cnt += 1;

            i += 5;
        }

        for (int j = 0; j < m; j+=d) {
            if (arr[j] == 0) {
                System.out.println(j);
                System.exit(0);
            }
        }

    }
}
