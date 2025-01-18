package Baekjoon;
import java.util.*;
import java.io.*;

public class P1865_웜홀 {
    static final int INT_MAX = Integer.MAX_VALUE;
    static ArrayList<Pair>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            int[] dist = new int[n + 1];
            graph = new ArrayList[n + 1];

            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            // 양수 도로
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                graph[s].add(new Pair(s, e, t));
                graph[e].add(new Pair(e, s, t));
            }

            // 음수 도로
            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                graph[s].add(new Pair(s, e, -1 * t));
            }

            boolean updated = false;
            // 벨만-포드 알고리즘
            for (int k = 0; k < n - 1; k++) {
                for (int i = 1; i <= n; i++) {

                    for (int j = 0; j < graph[i].size(); j++) {
                        Pair current = graph[i].get(j);

                        if (dist[current.end] > dist[i] + current.value) {
                            dist[current.end] = dist[i] + current.value;
                        }
                    }
                }
            }

            for (int i = 1; i <= n; i++) {

                for (int j = 0; j < graph[i].size(); j++) {
                    Pair current = graph[i].get(j);

                    if (dist[current.end] > dist[i] + current.value) {
                        updated = true;
                        dist[current.end] = dist[i] + current.value;
                    }
                }
            }

            if (updated) {
                sb.append("YES").append('\n');
            } else {
                sb.append("NO").append('\n');
            }
        }

        System.out.println(sb);
    }

    static class Pair {
        int start;
        int end;
        int value;

        public Pair(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
}
