package Baekjoon;
import java.util.*;
import java.io.*;

public class P11729_하노이_탑_이동_순서 {
    static int n, cnt = 0;
    static StringBuilder sb = new StringBuilder();

    static void hanoi(int n, int from, int temp, int to) {
        if (n == 1) {
            cnt += 1;
            sb.append(from).append(" ").append(to).append("\n");
            return;
        }

        hanoi(n - 1, from, to, temp);
        cnt += 1;
        sb.append(from).append(" ").append(to).append("\n");
        hanoi(n - 1, temp, from, to);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        hanoi(n, 1, 2, 3);
        System.out.println(cnt);
        System.out.println(sb);
    }
}
