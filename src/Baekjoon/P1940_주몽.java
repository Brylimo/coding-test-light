package Baekjoon;

import java.util.*;
import java.io.*;

public class P1940_주몽 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int ans = 0;
        int j = n - 1;
        for (int i = 0; i < n; i++) {
            while (i < j) {
                if (arr[i] + arr[j] < m) {
                    i++;
                } else if (arr[i] + arr[j] > m) {
                    j--;
                } else if (arr[i] + arr[j] == m) {
                    ans += 1;
                    j--;
                }
            }
        }

        System.out.println(ans);
    }
}
