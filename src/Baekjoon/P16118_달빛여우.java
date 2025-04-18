package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * Main_16118_달빛여우
 * 난이도 6/10
 * 다익스트라
 * 876ms
 *
 * 여우의 경우는 일반 다익스트라를 구해준다. 이때 원래 값 * 2인 값으로 계산해준다.
 * 늑대의 경우 다익스트라를 똑같이 이용해주나 visited를 써서 재방문을 막아주도록 했다.
 * 그루터기에서 홀수, 짝수로 2차원 visited 배열을 만들어 홀수, 짝수 별로 한번씩 방문할 수 있게 하여 다익스트라 dist 배열을 갱신해 주었다.
 *
 * 마지막에는 여우의 dist와 늑대의 dist를 비교하며 달빛 여우가 달빛 늑대보다 먼저 도착할 수 있는 그루터기의 개수를 구한다.
 *
 */
public class P16118_달빛여우 {

    static int n, m;
    static ArrayList<Pair>[] graph;
    static int[][] wdist;
    static int[] fdist;

    static void dijkstra1(int[] dist) { // 여우
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(1, 0, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (dist[current.x] < current.d) continue;

            for (Pair node : graph[current.x]) {
                if (dist[node.x] > dist[current.x] + node.d * 2) {
                    dist[node.x] = dist[current.x] + node.d * 2;
                    pq.offer(new Pair(node.x, dist[node.x], 0));
                }
            }
        }
    }

    static void dijkstra2(int[][] dist) { // 늑대
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n + 1][2];
        pq.offer(new Pair(1, 0, 1));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            int parity = current.turn % 2;
            if (visited[current.x][parity]) continue; // visited 체크를 통해 재방문을 막음
            visited[current.x][parity] = true;

            int speedFactor = (parity == 1) ? 1 : 4;

            for (Pair node : graph[current.x]) {
                int nextX = node.x;
                int cost = current.d + node.d * speedFactor;

                if (dist[nextX][1 - parity] > cost) {
                    dist[nextX][1 - parity] = cost;
                    pq.offer(new Pair(nextX, cost, current.turn + 1));
                }
            }

        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        fdist = new int[n + 1];
        wdist = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            Arrays.fill(wdist[i], Integer.MAX_VALUE);
            fdist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[a].add(new Pair(b, d, 0));
            graph[b].add(new Pair(a, d, 0));
        }

        fdist[1] = 0;
        dijkstra1(fdist);

        wdist[1][1] = 0;
        dijkstra2(wdist);

        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (fdist[i] < Math.min(wdist[i][0], wdist[i][1])) {
                cnt += 1;
            }
        }

        System.out.println(cnt);

    }

    static class Pair implements Comparable<Pair> {
        int x;
        int d;
        int turn;

        public Pair(int x, int d, int turn) {
            this.x = x;
            this.d = d;
            this.turn = turn;
        }

        @Override
        public int compareTo(Pair other) {
            return this.d - other.d;
        }
    }

}
