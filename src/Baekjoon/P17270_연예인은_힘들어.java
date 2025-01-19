package Baekjoon;
import java.util.*;
import java.io.*;

public class P17270_연예인은_힘들어 {
    static final int INF = (int)1e9;
    static int v, m;
    static ArrayList<Pair>[] graph;
    static int[] dist;

    static void dijkstra(int start) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.add(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (dist[current.end] > current.dist) continue;

            for (Pair node : graph[current.end]) {
                if (dist[node.end] > current.dist + node.dist) {
                    dist[node.end] = current.dist + node.dist;
                    pq.add(new Pair(node.end, dist[node.end]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[v + 1];
        Arrays.fill(dist, INF);

        graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        // graph 정의
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Pair(b, c));
            graph[b].add(new Pair(a, c));
        }

        // 지헌이의 위치, 성하의 위치
        st = new StringTokenizer(br.readLine());
        int j = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        dijkstra(j); // 지헌이 계산
        int[] jDist = dist;

        // 초기화
        dist = new int[v + 1];
        Arrays.fill(dist, INF);

        dijkstra(s); // 성하 계산
        int[] sDist = dist;

        //System.out.println(Arrays.toString(jDist));
        //System.out.println(Arrays.toString(sDist));

        PriorityQueue<Element> epq = new PriorityQueue<>();
        for (int i = 1; i <= v; i++) {
            if (i != j && i != s) { // 출발 위치가 아님
                epq.add(new Element(i, jDist[i], sDist[i],jDist[i] + sDist[i]));
            }
        }

        boolean flag = false;
        int minDist = INF;
        if (!epq.isEmpty()) {
            minDist = epq.peek().sum;
        }

        while (!epq.isEmpty()) {
            Element current = epq.poll();

            if (current.sum > minDist) break;
            if (current.jj > current.ss) continue;

            flag = true;
            System.out.println(current.x);
            break;
        }

        if (!flag) System.out.println(-1);
    }

    static class Pair implements Comparable<Pair> {
        int end;
        int dist;

        public Pair (int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair other) {
            return this.dist - other.dist;
        }
    }

    static class Element implements Comparable<Element> {
        int x;
        int jj;
        int ss;
        int sum;

        public Element(int x, int jj, int ss, int sum) {
            this.x = x;
            this.jj = jj;
            this.ss = ss;
            this.sum = sum;
        }

        @Override
        public int compareTo(Element other) {
            if (this.sum == other.sum) {
                if (this.jj == other.jj) {
                    return this.x - other.x;
                }

                return this.jj - other.jj;
            }

            return this.sum - other.sum;
        }
    }

}
