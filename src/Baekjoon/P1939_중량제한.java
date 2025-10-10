package Baekjoon;
import java.util.*;
import java.io.*;

public class P1939_중량제한 {
    static int n, m, start, end;
    static ArrayList<Pair>[] graph;
    static PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o2.dist - o1.dist);
    static int[] dist;

    static class Pair implements Comparable<Pair> {
        int x;
        int dist;

        public Pair(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair other) {
            return this.dist - other.dist;
        }
    }

    static void dijkstra() {
        dist[start] = (int)1e9;
        pq.offer(new Pair(start, (int)1e9));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (dist[current.x] > current.dist) continue;

            for (Pair p : graph[current.x]) {
                if (Math.min(dist[current.x], p.dist) > dist[p.x]) {
                    dist[p.x] = Math.min(dist[current.x], p.dist);
                    pq.offer(new Pair(p.x, dist[p.x]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Pair(b, c));
            graph[b].add(new Pair(a, c));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra();

        System.out.println(dist[end]);
    }
}
