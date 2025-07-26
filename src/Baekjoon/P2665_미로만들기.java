package Baekjoon;
import java.util.*;
import java.io.*;

public class P2665_미로만들기 {
    static int n;
    static int[][] grid, steps;
    static Queue<Pair> queue = new LinkedList<>();

    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    static void bfs() {
        Pair current = queue.poll();

        for (int i = 0; i < 4; i++) {
            int nx = current.x + dx[i];
            int ny = current.y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

            //if ()
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        steps = new int[n][n];

        // 그리드 채우기 (1 - 흰, 0 - 검)
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = (int) line.charAt(j);
            }
        }

        //queue.offer(new Pair(0, 0));
        bfs();
    }

    static class Pair {
        int x;
        int y;
        int cnt;

        public Pair(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

}
