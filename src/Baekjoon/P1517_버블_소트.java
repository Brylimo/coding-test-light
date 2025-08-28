package Baekjoon;
import java.util.*;
import java.util.Map.*;
import java.io.*;

public class P1517_버블_소트 {
    static int n, base, size;
    static long[] tree;
    static ArrayList<Pair> list;

    static class Pair implements Comparable<Pair> {
        int idx;
        int value;

        public Pair(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public int compareTo(Pair other) {
            return this.value - other.value;
        }
    }

    static long get(int idx, int s, int e, int ts, int te) {
        if (te < s || e < ts) return 0;
        if (ts <= s && e <= te) return tree[idx];

        int mid = (s + e) / 2;
        long a = get(idx * 2, s, mid, ts, te);
        long b = get(idx * 2 + 1, mid + 1, e, ts, te);

        return a + b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        base = 1;
        while (base < n) base <<= 1;
        size = base << 1;

        tree = new long[size];

        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());

            if (!map.containsKey(value)) {
                map.put(value, new ArrayList<>(Arrays.asList(i)));
            } else {
                map.get(value).add(i);
            }
        }

        long sum = 0;
        for (Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {

            for (int idx : entry.getValue()) {
                sum += get(1, 0, base - 1, idx + 1, base - 1);
            }

            for (int idx : entry.getValue()) {
                tree[base + idx] = 1;

                int node = (base + idx) / 2;
                while (node >= 1) {
                    tree[node] += 1;
                    node /= 2;
                }
            }
        }

        System.out.println(sum);

    }
}