package Baekjoon;
import java.util.*;
import java.io.*;

public class P2559_수열 {
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

        int sum = 0;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum += arr[i - 1];
            prefixSum[i] = sum;
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n - k + 1; i++) {
            ans = Math.max(ans, prefixSum[i + k] - prefixSum[i]);
        }

        System.out.println(ans);
    }
}
