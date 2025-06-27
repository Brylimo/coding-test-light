package Baekjoon;
import java.util.*;
import java.io.*;

public class P18223_민준이와_마산_그리고_건우 {
    static int v, e, p;
    static ArrayList<Pair>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Pair(b, c));
            graph[b].add(new Pair(a, c));
        }

        // dijkstra
        int[] dist = new int[v + 1];
        int[] dist2 = new int[v + 1];
        Arrays.fill(dist, (int)1e9);
        Arrays.fill(dist2, (int)1e9);

        // path를 마치 그래프처럼 기록
        /*ArrayList<Integer>[] pGraph = new ArrayList[v + 1];
        for (int i = 0; i <= v; i++) {
            pGraph[i] = new ArrayList<>();
        }*/

        // 출발지는 1번
        dist[1] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(1, 0));

        while (!pq.isEmpty()) { // 다익스트라 알고리즘
            Pair current = pq.poll();

            if (dist[current.node] < current.dist) continue;

            for (Pair p : graph[current.node]) {
                if (dist[current.node] + p.dist < dist[p.node]) {
                    //if (dist[current.node] + p.dist < dist[p.node]) pGraph[p.node].clear(); // 초기화를 해줘야할거같은데

                    dist[p.node] = dist[current.node] + p.dist;
                    //pGraph[p.node].add(current.node); // 경로 기록하기

                    pq.offer(new Pair(p.node, dist[p.node]));
                }
            }
        }

        // 출발지는 p번
        dist2[p] = 0;

        pq.clear();
        pq.offer(new Pair(p, 0));

        while (!pq.isEmpty()) { // 다익스트라 알고리즘
            Pair current = pq.poll();

            if (dist2[current.node] < current.dist) continue;

            for (Pair p : graph[current.node]) {
                if (dist2[current.node] + p.dist < dist2[p.node]) {
                    //if (dist[current.node] + p.dist < dist[p.node]) pGraph[p.node].clear(); // 초기화를 해줘야할거같은데

                    dist2[p.node] = dist2[current.node] + p.dist;
                    //pGraph[p.node].add(current.node); // 경로 기록하기

                    pq.offer(new Pair(p.node, dist2[p.node]));
                }
            }
        }

        if (dist[v] >= dist[p] + dist2[v]) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }

        // 역추적이 필요할거 같은데?? -> 역추적으로 해결하려고 하니까 안풀림
        // path라는 배열을 하나 만들어, dist[i]가 dist[minindex] + graph[minIndex][i] 값으로 갱신되는 그 순간에 path[i]에 minIndex를 넣어준다.
        // 경로는 도착점을 시작으로 path 배열을 이용하여 거꾸로 이동하여 거쳐가는 점들을 적어준 뒤, 최종적으로 리스트를 거꾸로 뒤집어 주는 식으로 찾을 수 있다.

        // bfs
        /*Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[v + 1];

        visited[v] = true; // 도착지는 v
        queue.offer(v);

        boolean isPassed = false;
        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == p) { // 건우가 있는 곳을 지남
                isPassed = true;
                break;
            }

            for (int next : pGraph[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        if (isPassed) { // 건우를 구할 수 있음
            System.out.println("SAVE HIM");
        } else { // 건우를 못구함
            System.out.println("GOOD BYE");
        }*/

    }

    static class Pair implements Comparable<Pair> {
        int node;
        int dist;

        public Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair other) {
            return this.dist - other.dist;
        }
    }
}
