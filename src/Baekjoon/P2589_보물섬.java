package Baekjoon;
import java.util.*;
import java.io.*;

public class P2589_보물섬 {
    static int l, w;
    static char[][] grid;
    static boolean[][] visited;
    static int[][] step;
    static Queue<int[]> queue = new LinkedList<>();

    static void bfs() {
        int[] dx = new int[] {-1, 0, 1, 0};
        int[] dy = new int[] {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= l || ny >= w) continue;

                if (!visited[nx][ny] && grid[nx][ny] == 'L') { // 육지이고 방문하지 않았으면
                    visited[nx][ny] = true;
                    step[nx][ny] = step[current[0]][current[1]] + 1;

                    queue.offer(new int[] { nx, ny });
                }
            }
        }
    }

    static void init() { // 초기화 함수
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < w; j++) {
                visited[i][j] = false;
                step[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        grid = new char[l][w];
        visited = new boolean[l][w];
        step = new int[l][w];

        for (int i = 0; i < l; i++) {
            String line = br.readLine();

            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        int ans = 0;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == 'L') { // 육지이면
                    init(); // 초기화

                    visited[i][j] = true;
                    queue.offer(new int[] {i, j});

                    bfs();

                    // ans 값 업데이트
                    for (int x = 0; x < l; x++) {
                        for (int y = 0; y < w; y++) {
                            ans = Math.max(ans, step[x][y]);
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
