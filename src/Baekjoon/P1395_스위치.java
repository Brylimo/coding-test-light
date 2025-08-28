package Baekjoon;
import java.util.*;
import java.io.*;

public class P1395_스위치 {
    static int n, m, base, size;
    static int[] tree, lazy;
    static boolean[] status;

    static void propagate(int s, int e, int node) {
        if (lazy[node] != 0) {
            if (s != e) { // leaf 노드가 아님
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            if (lazy[node] % 2 != 0) {
                tree[node] = (e - s + 1) - tree[node]; // 반전
            }
            lazy[node] = 0;
        }
    }

    static void update(int idx, int s, int e, int ts, int te, int diff) {
        propagate(s, e, idx);
        if (ts > e || te < s) return;
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

    static int get(int idx, int s, int e, int ts, int te) {
        propagate(s, e, idx);
        if (ts > e || te < s) return 0;
        if (ts <= s && e <= te) return tree[idx];

        int mid = (s + e) / 2;
        int a = get(idx * 2, s, mid, ts, te);
        int b = get(idx * 2 + 1, mid + 1, e, ts, te);

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

        status = new boolean[base];
        tree = new int[size];
        lazy = new int[size];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (op == 0) {
                update(1, 0, base - 1, s -  1, e - 1, 1);
            } else if (op == 1) {
                int res = get(1, 0, base - 1, s - 1, e - 1);
                sb.append(res).append("\n");
            }
        }

        System.out.println(sb);

    }

}
