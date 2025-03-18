package Baekjoon;
import java.util.*;
import java.io.*;

public class P2056_작업 {
    static int n;
    static ArrayList<Integer>[] graph;
    static int[] works;
    static int[] indegrees;
    static int[] result;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        works = new int[n + 1];
        indegrees = new int[n + 1];
        result = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());

            works[i] = time;
            int m = Integer.parseInt(st.nextToken());

            indegrees[i] = m;
            for (int j = 0; j < m; j++) {
                int t = Integer.parseInt(st.nextToken());
                graph[t].add(i);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);

                result[i] = works[i];
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int node : graph[current]) {
                indegrees[node]--;

                result[node] = Math.max(result[node], result[current] + works[node]);
                if (indegrees[node] == 0) {
                    queue.offer(node);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, result[i]);
        }

        System.out.println(ans);
    }
}
