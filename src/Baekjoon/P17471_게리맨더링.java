package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * P17471_게리맨더링
 * 난이도 6.5/10
 * BFS, 그래프 이론
 * 136ms
 *
 * 처음에 비트마스킹을 이용해 부분 집합을 구한 뒤 한 구역은 bfs를 이용해 연결을 확인하고 나머지 하나는 유니온-파인드 + indegree 개수를 이용해 연결을
 * 확인하려고 시도하였지만 이렇게 시도할 경우 일부의 경우에서 오답 처리되었다. (이 부분을 해결하려고 했지만 잘 되지 않았다..)
 *
 * 조금 더 개선된 방법으로 두 구역 모두 bfs를 이용해 연결을 확인하는 방법을 생각하였고
 * 이렇게 두 구역 모두 bfs를 이용해 연결을 확인하니 깔끔하게 풀렸다.
 */
public class P17471_게리맨더링 {
    static int n, ans = Integer.MAX_VALUE;
    static int[] arr;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static Queue<Integer> queue = new ArrayDeque();
    static ArrayList<Integer> candidates;
    static ArrayList<Integer> others;

    static void bfs(HashSet<Integer> set) {

        while (!queue.isEmpty()) {
            int idx = queue.poll();

            for (int node : graph[idx]) {
                if (!visited[node] && set.contains(node)) {
                    visited[node] = true;
                    queue.offer(node);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                int y = Integer.parseInt(st.nextToken());

                graph[i].add(y);
            }
        }

        int total = 0; // 전체 인구 수
        for (int i = 0; i < n; i++) {
            total += arr[i];
        }

        int snum = (1 << n); // 비트마스킹
        OUT : for (int i = 1; i < snum - 1; i++) {
            int sum = 0;
            candidates = new ArrayList<>();
            others = new ArrayList<>();
            visited = new boolean[n + 1];

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) { // j를 포함하고 있음
                    candidates.add(j + 1);
                    sum += arr[j]; // 인구 수를 더함

                } else {
                    others.add(j + 1);
                }
            }

            visited[candidates.get(0)] = true;
            queue.offer(candidates.get(0));
            bfs(new HashSet<>(candidates));

            boolean isConnected = true;
            for (int node : candidates) {
                if (!visited[node]) {
                    isConnected = false;
                    break;
                }
            }

            visited = new boolean[n + 1];

            visited[others.get(0)] = true;
            queue.offer(others.get(0));
            bfs(new HashSet<>(others));

            for (int node : others) {
                if (!visited[node]) {
                    isConnected = false;
                    break;
                }
            }

            // 잘 연결되어 있음
            if (isConnected) {
                ans = Math.min(ans, Math.abs((total - sum) - sum));
            }
        }

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
}
