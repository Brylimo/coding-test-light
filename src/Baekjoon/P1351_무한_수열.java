package Baekjoon;
import java.util.*;
import java.io.*;

public class P1351_무한_수열 {
    static long n, p, q;
    static HashMap<Long, Long> map = new HashMap<>();

    static long calculate(long value) {
        if (value == 0) { // 종료 조건
            return 1L;
        }

        long left = (long)Math.floor(value / p);
        long right = (long)Math.floor(value / q);

        if (!map.containsKey(value)) map.put(value, calculate(left) + calculate(right));

        return map.get(value);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        p = Long.parseLong(st.nextToken());
        q = Long.parseLong(st.nextToken());

        long ans = calculate(n);

        System.out.println(ans);
    }
}
