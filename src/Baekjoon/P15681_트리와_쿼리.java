package Baekjoon;
import java.util.*;
import java.io.*;

public class P15681_트리와_쿼리 {
    static int n, r, q;
    static ArrayList<Integer>[] tree;
    static int[] nums;
    static boolean[] visited;

    static int dfs(int root) {

        int sum = 0;
        for (int node : tree[root]) {
            if (!visited[node]) {
                visited[node] = true;

                sum += dfs(node);
            }
        }

        return nums[root] = sum + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[n + 1];
        nums = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        visited[r] = true;
        dfs(r);

        for (int i = 0; i < q; i++) {
            int query = Integer.parseInt(br.readLine());
            sb.append(nums[query]).append("\n");
        }

        System.out.println(sb);
    }
}
