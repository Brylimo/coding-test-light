package Baekjoon;
import java.util.*;
import java.io.*;

public class P10868_최솟값 {
    static int n, m, base, size;
    static long[] arr, tree;

    static long get(int idx, int s, int e, int ts, int te) {
        if (te < s || e < ts) return 1_000_000_001;
        if (ts <= s && e <= te) return tree[idx];

        int mid = (s + e) / 2;
        long a = get(idx * 2, s, mid, ts, te);
        long b = get(idx * 2 + 1, mid + 1, e, ts, te);

        return Math.min(a, b);
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

        Arrays.fill(tree, 1_000_000_001);
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // make tree
        for (int i = 0; i < n; i++) {
            tree[i + base] = arr[i];
        }

        for (int i = base - 1; i >= 0; i--) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long res = get(1, 0, base - 1, a - 1, b - 1);
            sb.append(res).append("\n");
        }

        System.out.println(sb);
    }

}
