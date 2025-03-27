package SWEA;

import java.util.*;
import java.io.*;

/**
 * Solved_3124_최소_스패닝_트리
 * 난이도 2/10
 * MST
 * 1840ms
 *
 * 크루스칼 알고리즘을 이용해서 최소 스패닝 트리를 만든다.
 *
 */
public class Solved_3124_최소_스패닝_트리 {
    static int[] parent;

    static void union(int x, int y) { // union 함수
        int px = find(x);
        int py = find(y);

        if (px < py) {
            parent[py] = px;
        } else {
            parent[px] = py;
        }
    }

    static int find(int x) { // find 함수
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            ArrayList<Pair> list = new ArrayList<>();
            parent = new int[v + 1];

            for (int i = 1; i <= v; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                list.add(new Pair(a, b, c));
            }

            Collections.sort(list);

            long cost = 0; // 가중치
            for (Pair p : list) {
                if (find(p.x) != find(p.y)) { // 사이클이 없으면
                    cost += p.dist;
                    union(p.x, p.y);
                }
            }

            sb.append("#").append(t).append(" ").append(cost).append("\n");

        }

        System.out.println(sb);
    }

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int dist;

        public Pair(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair other) {
            return this.dist - other.dist;
        }
    }

}
