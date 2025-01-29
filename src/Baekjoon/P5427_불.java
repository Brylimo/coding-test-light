package Baekjoon;
import java.util.*;
import java.io.*;

public class P5427_불 {
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());

            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            int sx = 0, sy = 0;
            ArrayList<int[]> fireList = new ArrayList<>();
            char[][] grid = new char[h][];
            for (int i = 0; i < h; i++) {
                grid[i] = br.readLine().toCharArray();
            }

            // 위치 표시하기
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (grid[i][j] == '@') {
                        sx = i;
                        sy = j;
                    } else if (grid[i][j] == '*') {
                        fireList.add(new int[]{i, j});
                    }
                }
            }

            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[h][w];
            int[][] fireMap = new int[h][w];

            for (int i = 0; i < h; i++) {
                Arrays.fill(fireMap[i], Integer.MAX_VALUE);
            }

            for (int[] fireLoc : fireList) {
                fireMap[fireLoc[0]][fireLoc[1]] = 0;
                visited[fireLoc[0]][fireLoc[1]] = true;
                queue.offer(new int[]{fireLoc[0], fireLoc[1]});
            }

            while (!queue.isEmpty()) {
                int[] current = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = current[0] + dx[i];
                    int ny = current[1] + dy[i];

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;

                    if (!visited[nx][ny] && (grid[nx][ny] == '.' || grid[nx][ny] == '@')) {
                        visited[nx][ny] = true;

                        fireMap[nx][ny] = fireMap[current[0]][current[1]] + 1;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }

            /*for (int i = 0; i < h; i++) {
                System.out.println(Arrays.toString(fireMap[i]));
            }*/

            int[][] dist = new int[h][w];
            visited = new boolean[h][w];

            visited[sx][sy] = true;
            queue.offer(new int[]{sx, sy});

            int ans = -1;
            OUT : while (!queue.isEmpty()) {
                int[] current = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = current[0] + dx[i];
                    int ny = current[1] + dy[i];

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                        ans = dist[current[0]][current[1]] + 1;
                        break OUT;
                    }

                    if (!visited[nx][ny] && grid[nx][ny] != '#' && fireMap[nx][ny] > dist[current[0]][current[1]] + 1) {
                        visited[nx][ny] = true;
                        dist[nx][ny] = dist[current[0]][current[1]] + 1;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }

            if (ans > 0) {
                System.out.println(ans);
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}
