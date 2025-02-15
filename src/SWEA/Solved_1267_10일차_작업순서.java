package SWEA;
import java.util.*;
import java.io.*;

public class Solved_1267_10일차_작업순서 {
    static int v, e;
    static int[] indegree;
    static ArrayList<Integer>[] graph;
    static Queue<Integer> queue = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            st = new StringTokenizer(br.readLine());

            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            graph = new ArrayList[v + 1];
            indegree = new int[v + 1];
            for (int i = 1; i <= v; i++) {
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 2 * e; i +=2) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                // 방향성을 가짐 x -> y
                graph[x].add(y);
                indegree[y] += 1;
            }

            for (int i = 1; i <= v; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }

            ArrayList<Integer> path = new ArrayList<>();
            while (!queue.isEmpty()) {
                int current = queue.poll();

                path.add(current);

                for (int node : graph[current]) {
                    indegree[node] -= 1;

                    if (indegree[node] == 0) {
                        queue.offer(node);
                    }
                }
            }

            sb.append("#" + t + " ");
            for (int node : path) {
                sb.append(node + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
