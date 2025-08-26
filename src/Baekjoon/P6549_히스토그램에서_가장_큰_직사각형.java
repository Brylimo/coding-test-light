package Baekjoon;
import java.util.*;
import java.io.*;

public class P6549_히스토그램에서_가장_큰_직사각형 {
    static int base, size;
    static long res;
    static long[] arr;
    static int[] tree;

    static int get(int idx, int s, int e, int ts, int te) {
        if (te < s || ts > e) return 0;
        if (ts <= s && e <= te) return tree[idx];

        int mid = (s + e) / 2;
        int idx1 = get(idx * 2, s, mid, ts, te);
        int idx2 = get(idx * 2 + 1, mid + 1, e, ts, te);

        if (arr[idx1] < arr[idx2]) {
            return idx1;
        } else {
            return idx2;
        }
    }

    static long getMaxSquare(int s, int e) {
        int idx = get(1, 0, base - 1, s, e);

        long area = (e - s + 1) * arr[idx];

        if (s <= idx - 1) {
            area = Math.max(area, getMaxSquare(s, idx - 1));
        }
        if (idx + 1 <= e) {
            area = Math.max(area, getMaxSquare(idx + 1, e));
        }

        return area;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());

            if (n == 0) break;

            base = 1;
            while (base < n) base <<= 1;
            size = base << 1;

            arr = new long[base];
            tree = new int[size];

            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }

            // make tree
            for (int i = 0; i < n; i++) {
                tree[base + i] = i;
            }

            for (int i = base - 1; i >= 1; i--) {
                int left = tree[i * 2];
                int right = tree[i * 2 + 1];

                if (arr[left] <= arr[right]) {
                    tree[i] = left;
                } else {
                    tree[i] = right;
                }
            }

            long res = getMaxSquare(0, n - 1);
            sb.append(res).append("\n");

        }

        System.out.println(sb);
    }
}
