package Baekjoon;
import java.util.*;
import java.io.*;

public class P2623_음악프로그램 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] indegree = new int[n + 1];
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int[] arr = new int[k];
            for (int j = 0; j < k; j++) {
                int node = Integer.parseInt(st.nextToken());
                arr[j] = node;
            }

            for (int j = 0; j < k - 1; j++) {
                graph[arr[j]].add(arr[j + 1]);
                indegree[arr[j + 1]]++;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int cnt = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current).append("\n");
            cnt++;

            for (int node : graph[current]) {
                indegree[node]--;

                if (indegree[node] == 0) {
                    queue.offer(node);
                }
            }
        }

        if (cnt != n) {
            System.out.println(0);
        } else {
            System.out.println(sb);
        }

    }
}
