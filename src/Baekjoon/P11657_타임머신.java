package Baekjoon;
import java.util.*;
import java.io.*;

public class P11657_타임머신 {
    static int[] dist;
    static ArrayList<Pair>[] graph;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
    static final int INT_MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dist = new int[n + 1];
        graph = new ArrayList[n + 1];
        Arrays.fill(dist, INT_MAX);

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Pair(c, b));
        }

        dist[1] = 0;
        pq.add(new Pair(0, 1));

        while (!pq.isEmpty()) {
            int minDist = pq.peek().cost;
            int minIndex = pq.peek().y;
            pq.poll();

            if (minDist != dist[minIndex]) {
                continue;
            }

            for(int j = 0; j < graph[minIndex].size(); j++) {
                int targetIndex = graph[minIndex].get(j).y;
                int targetDist = graph[minIndex].get(j).cost;

                int newDist = dist[minIndex] + targetDist;
                if(dist[targetIndex] > newDist) {
                    dist[targetIndex] = newDist;
                    pq.add(new Pair(newDist, targetIndex));
                }
            }
            System.out.println("hi");
        }

        for (int i = 2; i <= n; i++) {
            System.out.println(dist[i]);
        }
    }

    static class Pair implements Comparable<Pair> {
        int y;
        int cost;

        public Pair(int cost, int y) {
            this.cost = cost;
            this.y = y;
        }

        @Override
        public int compareTo(Pair p) {
            return this.cost - p.cost;
        }
    }
}
