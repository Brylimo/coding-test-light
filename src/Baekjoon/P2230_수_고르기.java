package Baekjoon;
import java.util.*;
import java.io.*;

public class P2230_수_고르기 {
    static final int INT_MAX = Integer.MAX_VALUE;
    static int ans = INT_MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 정렬
        Arrays.sort(arr);

        int j = 0;
        for (int i = 0; i < n; i++) { // 투 포인터 알고리즘
            while (j + 1 < n && Math.abs(arr[i] - arr[j + 1]) < m) {
                j++;
            }

            if (j + 1 < n) {
                ans = Math.min(ans, Math.abs(arr[i] - arr[j + 1]));
            }
        }

        System.out.println(ans);
    }
}
