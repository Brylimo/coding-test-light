package Baekjoon;
import java.util.*;
import java.io.*;

public class P5427_불_틀림 {
    public static Queue<Pair> queue = new LinkedList<>();

    public static int w, h;
    public static int[] dx = new int[]{-1, 0, 1, 0};
    public static int[] dy = new int[]{0, 1, 0, -1};

    public static char[][] grid;
    public static boolean[][] visited;
    public static int[][] step;

    public static void print() {
        for (int i = 0; i < h; i++) {
            System.out.println(Arrays.toString(step[i]));
        }
        System.out.println();
    }

    public static boolean bfs() {
        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (0 <= nx && nx < h && 0 <= ny && ny < w) {
                    if (current.type == 0) {
                        if (grid[nx][ny] == '.') {
                            grid[nx][ny] = '*';
                        }

                        queue.offer(new Pair(nx, ny, 1));
                    } else {
                        if (!visited[nx][ny] && grid[nx][ny] == '.') {
                            visited[nx][ny] = true;

                            step[nx][ny] = step[current.x][current.y] + 1;
                            queue.offer(new Pair(nx, ny, 0));
                        }
                    }
                } else if (current.type == 1) {
                    System.out.println(step[current.x][current.y] + 1);
                    return true;
                }

            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            grid = new char[h][w];
            visited = new boolean[h][w];
            step = new int[h][w];

            int startX = 0;
            int startY = 0;
            for (int i = 0; i < h; i++) {
                String input = br.readLine();
                for (int j = 0; j < w; j++) {
                    grid[i][j] = input.charAt(j);

                    if (grid[i][j] == '@') {
                        startX = i;
                        startY = j;

                        visited[i][j] = true;
                    } else if (grid[i][j] == '*') {
                        queue.add(new Pair(i, j, 0));
                    }
                }
            }

            queue.add(new Pair(startX, startY, 1));
            if (!bfs()) {
                System.out.println("IMPOSSIBLE");
            }
        }
    }

    static class Pair {
        int x;
        int y;
        int type;

        public Pair(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
