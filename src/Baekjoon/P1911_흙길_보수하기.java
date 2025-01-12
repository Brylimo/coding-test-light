package Baekjoon;
import java.util.*;
import java.io.*;

public class P1911_흙길_보수하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        ArrayList<Pair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Pair(start, end));
        }

        Collections.sort(list);

        int ans = 0;
        int lastPoint = 0;
        for (Pair p : list) {
            if (lastPoint >= p.y) {
                continue;
            }

            if (lastPoint <= p.x) {
                lastPoint = p.x;

                int length = p.y - p.x;
                int cnt = (int)Math.ceil((double) length / l);

                ans += cnt;
                lastPoint += (l * cnt);
            } else {
                int length = p.y - lastPoint;
                int cnt = (int)Math.ceil((double) length / l);

                ans += cnt;
                lastPoint += (l * cnt);
            }
        }

        System.out.println(ans);
    }

    static class Pair implements Comparable<Pair> {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair p) {
            if (y == p.y) {
                return x - p.x;
            }

            return y - p.y;
        }
    }
}
