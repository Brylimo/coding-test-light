package Baekjoon;
import java.util.*;
import java.io.*;

public class P22862_가장_긴_짝수_연속한_부분_수열_라지 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int kCnt = 0, cnt = 0, ans = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j < n && kCnt < k) {
                if (arr[j] % 2 != 0) {
                    kCnt += 1;
                } else {
                    cnt += 1;
                }

                j++;
            }

            while (j < n && kCnt == k && arr[j] % 2 == 0) {
                cnt += 1;
                j++;
            }

            if (arr[i] % 2 == 0) {
                ans = Math.max(ans, cnt);
                cnt -= 1;
            } else {
                kCnt -= 1;
            }
        }

        System.out.println(ans);
    }
}
