package Baekjoon;
import java.util.*;
import java.io.*;

public class P11505_구간_곱_구하기 {
    static int n, m, k, base, size;
    static long[] tree, arr;
    static long MOD = 1_000_000_007;

    static void update(int idx, long value) { // idx번째를 value로 바꿈
        // arr 업데이트
        arr[idx] = value;
        tree[base + idx] = value;

        int node = (base + idx) / 2;
        while (node >= 1) {
            tree[node] = (tree[2 * node] * tree[2 * node + 1]) % MOD;
            node /= 2;
        }
    }

    static long get(int idx, int s, int e, int ts, int te) {
        if (te < s || e < ts) return 1; // 범위 밖
        if (ts <= s && e <= te) return tree[idx] % MOD; // 범위에 포함됨

        int mid = (s + e) / 2;
        long a = get(idx * 2, s, mid, ts, te) % MOD;
        long b = get(idx * 2 + 1, mid + 1, e, ts, te) % MOD;

        return (a * b) % MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        base = 1;
        while (base < n) base <<= 1;
        size = (base << 1);

        arr = new long[base];
        tree = new long[size];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // make tree
        for (int i = 0; i < n; i++) {
            tree[i + base] = arr[i] % MOD;
        }

        for (int i = base - 1; i >= 0; i--) {
            tree[i] = (tree[i * 2] * tree[i * 2 + 1]) % MOD;
        }

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) { // update
                update(b - 1, c);
            } else { // 구간 곱
                long res = get(1, 0, base - 1, b - 1,  (int) c - 1) % MOD;
                sb.append(res).append("\n");
            }
        }

        System.out.println(sb);
    }
}
