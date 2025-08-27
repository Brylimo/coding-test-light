package Baekjoon;
import java.util.*;
import java.io.*;

public class P14428_수열과_쿼리16 {
    static int n, base, size;
    static long[] arr;
    static int[] tree;

    static void update(int idx, long value) {
        arr[idx] = value;

        int curIdx = (base + idx) / 2;
        while (curIdx >= 1) {
            if (arr[tree[curIdx * 2]] < arr[tree[curIdx * 2 + 1]]) {
                tree[curIdx] = tree[curIdx * 2];
            } else if (arr[tree[curIdx * 2]] > arr[tree[curIdx * 2 + 1]]) {
                tree[curIdx] = tree[curIdx * 2 + 1];
            } else {
                tree[curIdx] = tree[curIdx * 2];
            }

            curIdx /= 2;
        }
    }

    static int query(int idx, int s, int e, int ts, int te) {
        if (te < s || e < ts) return base;
        if (ts <= s && e <= te) return tree[idx];

        int mid = (s + e) / 2;
        int a = query(idx * 2, s, mid, ts, te);
        int b = query(idx * 2 + 1, mid + 1, e, ts, te);

        if (arr[a] < arr[b]) {
            return a;
        } else if (arr[a] > arr[b]) {
            return b;
        } else {
            return Math.min(a, b);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        base = 1;
        while (base < n) base <<= 1;
        size = base << 1;

        st = new StringTokenizer(br.readLine());

        arr = new long[base + 1];
        tree = new int[size];

        Arrays.fill(arr, Long.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        // make tree
        for (int i = 0; i < n; i++) {
            tree[base + i] = i;
        }

        for (int i = base - 1; i >= 1; i--) {
            if (arr[tree[i * 2]] < arr[tree[i * 2 + 1]]) {
                tree[i] = tree[i * 2];
            } else if (arr[tree[i * 2]] > arr[tree[i * 2 + 1]]) {
                tree[i] = tree[i * 2 + 1];
            } else {
                tree[i] = tree[i * 2];
            }
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int a = Integer.parseInt(st.nextToken());
                long value = Long.parseLong(st.nextToken());

                update(a - 1, value);
            } else {
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                int idx = query(1, 0, base - 1, s - 1, e - 1);

                sb.append(idx + 1).append("\n");
            }
        }

        System.out.println(sb);
    }
}
