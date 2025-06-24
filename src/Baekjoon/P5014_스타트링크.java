package Baekjoon;
import java.util.*;
import java.io.*;

public class P5014_스타트링크 {
    static int[] arr;
    static int[] step;
    static boolean[] visited;
    static Queue<Integer> queue = new LinkedList<>();
    static int u, d, f, s, g;

    static void bfs() {
        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == g) {
                return;
            }

            int current = node + u;

            if (current <= f && !visited[current]) {
                visited[current] = true;
                step[current] = step[node] + 1;
                queue.offer(current);
            }

            current = node - d;
            if (current >= 1 && !visited[current]) {
                visited[current] = true;
                step[current] = step[node] + 1;
                queue.offer(current);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        arr = new int[f + 1];
        step = new int[f + 1];
        visited = new boolean[f + 1];

        visited[s] = true;
        queue.offer(s);

        bfs();

        if (visited[g]) {
            System.out.println(step[g]);
        } else {
            System.out.println("use the stairs");
        }
    }
}
