package Programmers;

import java.util.*;

public class P1844_게임_맵_최단거리 {
    Queue<int[]> queue = new LinkedList<>();

    int[] dx = new int[] {-1, 0, 1, 0};
    int[] dy = new int[] {0, 1, 0, -1};

    public int solution(int[][] maps) {
        int answer = 0;

        int n = maps.length;
        int m = maps[0].length;

        boolean[][] visited = new boolean[n][m];
        int[][] steps = new int[n][m];

        queue.offer(new int[] {0, 0});
        visited[0][0] = true;
        steps[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[0] == n - 1 && current[1] == m - 1) break;

            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (!visited[nx][ny] && maps[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    steps[nx][ny] = steps[current[0]][current[1]] + 1;

                    queue.offer(new int[] {nx, ny});
                }
            }
        }

        /*for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(steps[i]));
        }*/

        answer = steps[n - 1][m - 1];

        if (answer == 0) answer = -1;

        return answer;
    }
}
