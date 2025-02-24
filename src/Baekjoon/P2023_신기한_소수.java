package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * P2023_신기한_소수
 * 난이도 3/10
 * 백트래킹
 * 104ms
 *
 * 백트래킹을 통해 순열을 구하는데 각 자리수가 늘어날 때마다 소수인지를 체크해야 하므로
 * 자리수가 늘어날 때를 기준으로 소수인지 체크하여 가지치기한다.
 *
 */
public class P2023_신기한_소수 {
    static int n;
    static ArrayList<Integer> candidates = new ArrayList<>();
    static StringBuilder builder = new StringBuilder();

    static boolean isPrime(int num) { // 소수 판별
        if (num == 0 || num == 1) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
    static void calculate(int idx, int num) {
        if (idx == n) {
            builder.append(num).append("\n");
            return;
        }

        for (int i = 0; i < 10; i++) {
            int nextNum = num * 10 + i;

            if (isPrime(nextNum)) { // 각 자리수가 늘어날 때마다 소수인지 체크하여 가지치기
                candidates.add(i);
                calculate(idx + 1, nextNum);
                candidates.remove(candidates.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        calculate(0, 0);
        System.out.println(builder);
    }
}
