package Baekjoon;
import java.util.*;
import java.io.*;

public class P14267_회사_문화1 {
    static int n, m;
    static int[] parents;
    static HashMap<Integer, Integer> map = new HashMap<>();
    static ArrayList<Integer>[] graph;

    static void dfs(int idx, int weight) {

        int org =  map.get(idx);
        map.put(idx, org + weight); // update

        for (int node : graph[idx]) {
            dfs(node, weight + org);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        parents = new int[n + 1];
        for (int i = 0; i < n; i++) {
            map.put(i + 1, 0);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            parents[i] = Integer.parseInt(st.nextToken());

            if (parents[i] != -1) {
                graph[parents[i]].add(i);
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map.put(idx, map.get(idx) + w);
        }

        // tree를 순회하며 lazy하게 update, 1번이 사장
        dfs(1, 0);

        for (int i = 1; i <= n; i++) {
            sb.append(map.get(i)).append(" ");
        }

        System.out.println(sb);
    }
}
