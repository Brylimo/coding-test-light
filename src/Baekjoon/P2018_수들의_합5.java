package Baekjoon;

import java.util.*;

public class P2018_수들의_합5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int j = 0;
        int sum = 0;
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            while (j + 1 <= n && sum + j + 1 <= n) {
                sum += j + 1;
                j++;
            }

            if (sum == n) {
                cnt += 1;
            }

            sum -= i;
        }

        System.out.println(cnt);
    }
}