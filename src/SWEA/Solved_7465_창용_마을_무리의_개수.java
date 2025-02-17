package SWEA;
import java.util.*;
import java.io.*;

public class Solved_7465_창용_마을_무리의_개수 {
    static int n, m;
    static ArrayList<Integer>[] graph;
    static Queue<Integer> queue = new ArrayDeque<>();
    static boolean[] visited;

    static void bfs() {
        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int node : graph[current]) {
                if (!visited[node]) {
                    visited[node] = true;
                    queue.offer(node);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            visited = new boolean[n + 1];
            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph[x].add(y);
                graph[y].add(x);
            }

            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                    bfs();

                    cnt++;
                }
            }

            System.out.printf("#%d %d\n", t, cnt);
        }
    }
}
