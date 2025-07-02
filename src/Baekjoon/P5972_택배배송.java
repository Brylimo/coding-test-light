package Baekjoon;
import java.util.*;
import java.io.*;

public class P5972_택배배송 {
    static int n, m;
    static ArrayList<Pair>[] graph;
    static int[] dist;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList[n + 1];
        dist = new int[n + 1];

        // 값 초기화
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        Arrays.fill(dist, (int)1e9);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 양방향 길
            graph[a].add(new Pair(b, c));
            graph[b].add(new Pair(a, c));
        }

        // 농부 현서는 헛간 1에 있다..
        pq.offer(new Pair(1, 0));
        dist[1] = 0;

        // 다익스트라 알고리즘
        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (dist[current.x] < current.cost) continue;

            for (Pair p : graph[current.x]) {
                if (dist[current.x] + p.cost < dist[p.x]) {
                    dist[p.x] = dist[current.x] + p.cost;
                    pq.offer(new Pair(p.x, dist[p.x]));
                }
            }
        }

        System.out.println(dist[n]);
    }

    static class Pair implements Comparable<Pair> {
        int x;
        int cost;

        public Pair(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair other) {
            return this.cost - other.cost;
        }
    }

}
