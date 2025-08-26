package Baekjoon;
import java.util.*;
import java.io.*;

public class P1275_커피숍2 {
    static int n, q, base, size;
    static long[] arr, tree;

    static void update(int idx, long value) {
        arr[idx] = value;
        tree[base + idx] = value;

        int node = (base + idx) / 2;
        while (node >= 1) {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
            node /= 2;
        }
    }

    static long get(int idx, int s, int e, int ts, int te) {
        if (te < s || e < ts) return 0;
        if (ts <= s && e <= te) return tree[idx];

        int mid = (s + e) / 2;
        long a = get(idx * 2, s , mid, ts, te);
        long b = get(idx * 2 + 1, mid + 1, e, ts, te);

        return a + b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        base = 1;
        while (base < n) base <<= 1;
        size = base << 1;

        arr = new long[base];
        tree = new long[size];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        // make tree
        for (int i = 0; i < n; i++) {
            tree[base + i] = arr[i];
        }

        for (int i = base - 1; i >= 1; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            int s = 0, e = 0;
            if (x < y) {
                s = x;
                e = y;
            } else {
                s = y;
                e = x;
            }

            long sum = get(1, 0, base - 1, s - 1, e - 1);
            update(a - 1, b);

            sb.append(sum).append("\n");
        }

        System.out.println(sb);
    }

}
