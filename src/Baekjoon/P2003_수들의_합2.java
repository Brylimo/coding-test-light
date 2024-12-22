package Baekjoon;
import java.util.*;
import java.io.*;

public class P2003_수들의_합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int j = 0;
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            while (j < n && sum + arr[j] <= m) {
                sum += arr[j];
                j += 1;

                if (sum == m) cnt += 1;
            }

            sum -= arr[i];
        }

        System.out.println(cnt);
    }
}
