package Baekjoon;
import java.util.*;
import java.io.*;

public class P2268_수들의_합7 {
    static int n, m, base, size;
    static long[] arr, tree;

    static void modify(int idx, long value) {
        arr[idx] = value;
        tree[base + idx] = value;

        int parent = (base + idx) / 2;
        while (parent >= 1) {
            tree[parent] = tree[parent * 2] + tree[parent * 2 + 1];
            parent /= 2;
        }
    }

    static long query(int idx, int s, int e, int ts, int te) {
        if (e < ts || te < s) return 0;
        if (ts <= s && e <= te) return tree[idx];

        int mid = (s + e) / 2;
        long a = query(idx * 2, s, mid, ts, te);
        long b = query(idx * 2 + 1, mid + 1, e, ts, te);

        return a + b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        base = 1;
        while (base < n) base <<= 1;
        size = base << 1;

        arr = new long[base];
        tree = new long[size];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());

            if (op == 0) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                int s = 0, e = 0;
                if (a <= b) {
                    s = a;
                    e = b;
                } else {
                    s = b;
                    e = a;
                }

                long res = query(1, 0, base - 1, s - 1, e - 1);
                sb.append(res).append("\n");
            } else if (op == 1) {
                int a = Integer.parseInt(st.nextToken());
                long k = Long.parseLong(st.nextToken());

                modify(a - 1, k);
            }

        }

        System.out.println(sb);
    }
}
