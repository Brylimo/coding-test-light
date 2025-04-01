package Baekjoon;
import java.util.*;
import java.io.*;

public class P11779_최소비용_구하기2 {
    static ArrayList<Pair>[] graph;
    static ArrayList<Pair>[] graph2;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        graph2 = new ArrayList[n + 1];
        dist = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            graph2[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[a].add(new Pair(b, cost));
            graph2[b].add(new Pair(a, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dist[start] = 0;
        pq.offer(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (dist[current.x] < current.cost) continue;

            for (Pair node : graph[current.x]) {
                if (dist[node.x] > dist[current.x] + node.cost) {
                    dist[node.x] = dist[current.x] + node.cost;
                    pq.offer(new Pair(node.x, dist[node.x]));
                }
            }
        }

        System.out.println(dist[end]);

        int x = end;

        ArrayList<Integer> path = new ArrayList<>();
        path.add(x);

        while (x != start) {
            for (Pair node : graph2[x]) {
                if (dist[node.x] + node.cost == dist[x]) {
                    x = node.x;
                    path.add(x);
                    break;
                }
            }
        }

        System.out.println(path.size());

        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
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
