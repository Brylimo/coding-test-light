package Baekjoon;
import java.util.*;
import java.io.*;

public class P2491_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 1;
        // 증가하는지 check
        int cnt = 1;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                cnt = 1;
            } else {
                cnt += 1;
            }

            ans = Math.max(ans, cnt);
        }

        // 감소하는지 check
        cnt = 1;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                cnt = 1;
            } else {
                cnt += 1;
            }

            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }
}
