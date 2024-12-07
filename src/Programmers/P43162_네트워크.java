package Programmers;
import java.util.*;

public class P43162_네트워크 {
    public final int MAX_N = 200;

    public ArrayList<Integer>[] graph = new ArrayList[MAX_N];
    public Queue<Integer> queue = new LinkedList<Integer>();
    public boolean[] visited = new boolean[MAX_N];

    public void bfs() {

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for (int x : graph[current]) {
                if (!visited[x]) {
                    visited[x] = true;
                    queue.add(x);
                }
            }
        }
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                if (computers[i][j] == 1) {
                    graph[i].add(j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer += 1;
                visited[i] = true;
                queue.add(i);

                bfs();
            }
        }

        return answer;
    }
}
