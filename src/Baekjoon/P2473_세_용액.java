package Baekjoon;

import java.util.*;
import java.io.*;

/**
 * Solved_2473_세_용액
 * 난이도 4/10
 * 투 포인터
 * 236ms
 *
 */
public class P2473_세_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long closestSum = Long.MAX_VALUE;
        int[] result = new int[3];

        // 투 포인터 탐색
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            while (left < right) {
                long sum = (long) arr[i] + arr[left] + arr[right];

                if (Math.abs(sum) < Math.abs(closestSum)) {
                    closestSum = sum;
                    result[0] = arr[i];
                    result[1] = arr[left];
                    result[2] = arr[right];
                }

                if (sum < 0) left++;  // 합이 작으면 더 큰 값을 찾아야 함
                else right--;  // 합이 크면 더 작은 값을 찾아야 함
            }
        }

        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
}