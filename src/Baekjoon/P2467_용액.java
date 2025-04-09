package Baekjoon;

import java.util.*;
import java.io.*;

public class P2467_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;
        int[] list = new int[2];
        for (int i = 0; i < n - 1; i++) {
            int temp = arr[i];

            int idx = Arrays.binarySearch(arr, i + 1, n - 1, temp * (-1));

            if (idx < 0) {
                idx = (idx + 1) * (-1);

                if (ans > Math.abs(arr[i] + arr[idx])) {
                    ans = Math.abs(arr[i] + arr[idx]);

                    list[0] = arr[i];
                    list[1] = arr[idx];
                }
                if (idx - 1 >= 0 && i != idx - 1 && ans > Math.abs(arr[i] + arr[idx - 1])) {
                    ans = Math.abs(arr[i] + arr[idx - 1]);

                    list[0] = arr[i];
                    list[1] = arr[idx - 1];
                }

            } else {
                ans = 0;
                list[0] = arr[i];
                list[1] = arr[idx];
                break;
            }

        }

        System.out.println(list[0] + " " + list[1]);

    }
}
