package Baekjoon;
import java.util.*;
import java.io.*;

public class P10999_구간_합_구하기2 {
    static int n, m, k, base, size;
    static long[] arr, tree, lazy;

    static void propagate(int start, int end, int node) {
        if (lazy[node] != 0) {
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            tree[node] += lazy[node] * (end - start + 1);
            lazy[node] = 0;
        }
    }

    static void update(int idx, int s, int e, int ts, int te, long diff) {
        propagate(s, e, idx);
        if (e < ts || s > te) return;
        if (ts <= s && e <= te) {
            lazy[idx] = diff;
            propagate(s, e, idx);
            return;
        }

        int mid = (s + e) / 2;
        update(idx * 2, s, mid, ts, te, diff);
        update(idx * 2 + 1, mid + 1, e, ts, te, diff);

        tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
    }

    static long get(int idx, int s, int e, int ts, int te) {
        propagate(s, e, idx);
        if (e < ts || s > te) return 0;
        if (ts <= s && e <= te) return tree[idx];

        int mid = (s + e) / 2;
        long a = get(idx * 2, s, mid, ts, te);
        long b = get(idx * 2 + 1, mid + 1, e, ts, te);

        return a + b;
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
        size = base << 1;

        arr = new long[base];
        tree = new long[size];
        lazy = new long[size];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // make tree
        for (int i = 0; i < n; i++) {
            tree[base + i] = arr[i];
        }

        for (int i = base - 1; i >= 1; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());

            if (op == 1) { // b ~ c에 d를 더함
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long d = Long.parseLong(st.nextToken());

                update(1, 0, base - 1, b - 1, c - 1, d);
            } else if (op == 2) { // b ~ c 수의 합
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                long res = get(1, 0, base - 1, b - 1, c - 1);
                sb.append(res).append("\n");
            }
        }

        System.out.println(sb);
    }
}
