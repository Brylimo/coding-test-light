package Baekjoon;
import java.util.*;
import java.io.*;

public class P2357_최솟값과_최대값 {

    static int n, m, base, size;
    static long[] arr;

    static long[] minTree, maxTree;

    static long getMinValue(int idx, int s, int e, int ts, int te) {
        if (te < s || e < ts) return 1000000001L;
        if (ts <= s && e <= te) return minTree[idx];

        int mid = (s + e) / 2;
        long a = getMinValue(idx * 2, s, mid, ts, te);
        long b = getMinValue(idx * 2 + 1, mid + 1, e, ts, te);

        return Math.min(a, b);
    }

    static long getMaxValue(int idx, int s, int e, int ts, int te) {
        if (te < s || e < ts) return -1L;
        if (ts <= s && e <= te) return maxTree[idx];

        int mid = (s + e) / 2;
        long a = getMaxValue(idx * 2, s, mid, ts, te);
        long b = getMaxValue(idx * 2 + 1, mid + 1, e, ts, te);

        return Math.max(a, b);
    }

    public static void main(String[] args) throws Exception {
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
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        minTree = new long[size];
        maxTree = new long[size];

        // make min tree
        for (int i = 0; i < n; i++) {
            minTree[i + base] = arr[i];
            maxTree[i + base] = arr[i];
        }

        // 나머지 트리 값 채우기
        for (int i = base - 1; i >= 1; i--) {
            minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]);
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[i * 2 + 1]);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long minVal = getMinValue(1, 0, base - 1, a - 1, b - 1);
            long maxVal = getMaxValue(1, 0, base - 1, a - 1, b - 1);

            sb.append(minVal).append(" ").append(maxVal).append("\n");
        }

        System.out.println(sb);
    }
}
