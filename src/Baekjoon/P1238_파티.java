package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * Main_1238_파티
 * 난이도 3/10
 * 다익스트라
 * 172ms
 *
 * X번 마을에서 파티를 벌일 때 각각의 마을에서 X번 마을까지 가고 다시 마을로 돌아가는 시간을 모두 고려해야 한다.
 * 그래프의 방향을 바꾸는 방법으로 X번 마을에서 가는 경우와 X번 마을에서 오는 경우를 각각 나누어서 dist를 구하고
 * dist를 더해서 답을 구했다.
 *
 * 문제에 나와있는데로 다익스트라를 돌리면 파티가 끝나고 X번 마을에서 각각의 마을로 돌아가는 경우를 구할 수 있고
 * 그래프의 방향을 모두 전환시켜서 다익스트라를 돌리면 각각의 마을에서 X번 마을로 가는 경우를 구할 수 있다.
 *
 */
public class P1238_파티 {
    static int n, m, x;
    static int INT_MAX = Integer.MAX_VALUE;
    static int[] dist1, dist2;
    static ArrayList<Pair>[] graph1;
    static ArrayList<Pair>[] graph2;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    static void dijkstra(int[] dist, ArrayList<Pair>[] graph) {
        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (dist[current.y] < current.dist) continue;

            for (Pair node : graph[current.y]) {
                if (dist[node.y] > current.dist + node.dist) {
                    dist[node.y] = current.dist + node.dist;
                    pq.offer(new Pair(node.y, dist[node.y]));
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
        x = Integer.parseInt(st.nextToken());

        dist1 = new int[n + 1];
        dist2 = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist1[i] = INT_MAX;
        }

        for (int i = 1; i <= n; i++) {
            dist2[i] = INT_MAX;
        }

        graph1 = new ArrayList[n + 1];
        graph2 = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph1[i] = new ArrayList<>();
            graph2[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph1[s].add(new Pair(e, d));
            graph2[e].add(new Pair(s, d));
        }

        dist1[x] = 0;
        pq.offer(new Pair(x, 0));
        dijkstra(dist1, graph1);

        dist2[x] = 0;
        pq.offer(new Pair(x, 0));
        dijkstra(dist2, graph2);

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist1[i] + dist2[i]);
        }

        System.out.println(ans);
    }

    static class Pair implements Comparable<Pair> {
        int y;
        int dist;

        public Pair(int y, int dist) {
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair other) {
            return this.dist - other.dist;
        }
    }

}
