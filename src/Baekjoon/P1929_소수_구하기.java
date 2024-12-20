package Baekjoon;

import java.util.*;

public class P1929_소수_구하기 {
    public static boolean isPrimeNumber(int x) {
        if (x == 1) return false;

        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        for (int i = m; i <= n; i++) {
            if (isPrimeNumber(i)) {
                System.out.println(i);
            }
        }
    }
}
