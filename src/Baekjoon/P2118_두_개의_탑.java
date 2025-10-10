package Baekjoon;
import java.util.*;
import java.io.*;

public class P2118_두_개의_탑 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int total = 0;
        int[] arr = new int[n * 2];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            total += arr[i];
        }

        for (int i = n; i < 2 * n; i++) {
            arr[i] = arr[i - n];
        }

        int s = 0, e = 0, sum = 0, ans = 0;
        while (true) {
            if (sum > total - sum) {
                sum -= arr[s++];
            } else if (e == 2 * n) break;
            else {
                ans = Math.max(ans, Math.min(sum, total - sum));
                sum += arr[e++];
            }
        }

        System.out.println(ans);
    }
}
