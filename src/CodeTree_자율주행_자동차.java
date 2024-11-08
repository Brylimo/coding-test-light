import java.util.*;
import java.io.*;

public class CodeTree_자율주행_자동차 {
    public static int n, m;
    public static Queue<Point> queue = new LinkedList<>();

    public static int[][] grid;
    public static boolean[][] visited;

    public static int dir;
    public static int[] dx = new int[]{-1, 0, 1, 0};
    public static int[] dy = new int[]{0, 1, 0, -1};

    public static boolean inRange(int x, int y) {
        return (0 <= x && x < n && 0 <= y && y < m);
    }

    public static void bfs(int x, int y) {
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            dir = (dir + 3) % 4;
            int nx = current.x + dx[dir];
            int ny = current.y + dy[dir];

            int rotateCnt = 1;
            while (!inRange(nx, ny) || grid[nx][ny] == 1 || visited[nx][ny]) {
                if (rotateCnt == 4) {
                    int nextDir = (dir + 2) % 4;

                    // 거꾸로 1칸 이동
                    nx = current.x + dx[nextDir];
                    ny = current.y + dy[nextDir];

                    if (!inRange(nx, ny) || grid[nx][ny] == 1) {
                        // 이동 멈춤
                        return;
                    }

                    break;
                }

                dir = (dir + 3) % 4;

                nx = current.x + dx[dir];
                ny = current.y + dy[dir];

                rotateCnt += 1;
            }

            visited[nx][ny] = true;
            queue.add(new Point(nx, ny));
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void print() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(visited[i]));
        }
    }

    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        visited = new boolean[n][m];

        st = new StringTokenizer(br.readLine());

        int currX = Integer.parseInt(st.nextToken());
        int currY = Integer.parseInt(st.nextToken());
        int currDir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dir = currDir;

        // 첫번째 위치 방문 표시
        visited[currX][currY] = true;
        queue.add(new Point(currX, currY));
        bfs(currX, currY);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    ans += 1;
                }
            }
        }

        System.out.println(ans);
    }
}
