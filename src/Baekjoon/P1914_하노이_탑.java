package Baekjoon;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class P1914_하노이_탑 {
    static StringBuilder sb = new StringBuilder();
    static int cnt = 0;

    static void hanoi(int n, int start, int temp, int end) {
        if (n == 1) {
            cnt++;
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }

        // 유도 파트
        hanoi(n - 1, start, end, temp);
        cnt++;
        sb.append(start).append(" ").append(end).append("\n");
        hanoi(n - 1, temp, start, end);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n <= 20) {
            hanoi(n, 1, 2, 3);
            System.out.println(cnt);
            System.out.println(sb);
        } else {
            BigInteger moves = BigInteger.TWO.pow(n).subtract(BigInteger.ONE);
            System.out.println(moves);
        }
    }
}
