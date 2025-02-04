package Baekjoon;
import java.util.*;
import java.io.*;

public class P2170_선_긋기_못품 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        ArrayList<Pair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Pair(start, 1, i));
            list.add(new Pair(end, -1, i));
        }

        Collections.sort(list);
        HashSet<Integer> segs = new HashSet<>();

        int ans = 0;
        Integer startPoint = null;
        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i).x;
            int v = list.get(i).v;
            int index = list.get(i).index;

            if (v == 1) {
                if (segs.size() == 0) {
                    startPoint = x;
                }

                segs.add(index);
            } else {
                ans += x - startPoint;
                segs.remove(index);
            }

        }

        System.out.println(ans);
    }

    public static class Pair implements Comparable<Pair> {
        int x;
        int v;
        int index;

        public Pair(int x, int v, int index) {
            this.x = x;
            this.v = v;
            this.index = index;
        }

        @Override
        public int compareTo(Pair other) {
            return this.x - other.x;
        }
    }
}
