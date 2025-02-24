package Baekjoon;
import java.util.*;
import java.io.*;

public class P11724_연결_요소의_개수 {
    static int n, m, cnt;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static Queue<Integer> queue = new LinkedList<>();

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

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                cnt += 1;
                visited[i] = true;
                queue.offer(i);
                bfs();
            }
        }

        System.out.println(cnt);
    }
}
