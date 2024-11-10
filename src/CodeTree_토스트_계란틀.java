import java.util.*;
import java.io.*;

public class CodeTree_토스트_계란틀 {
    public static final int MAX_N = 50;

    public static int n, l, r;
    public static int[][] grid = new int[MAX_N][MAX_N];
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];

    public static Queue<Pair> queue = new LinkedList<>();

    public static boolean inRange(int x, int y) {
        return (0 <= x && x < n && 0 <= y && y < n);
    }

    public static void bfs() {
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        int sum = 0;
        ArrayList<Pair> tempList = new ArrayList<>();
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int amount = grid[current.x][current.y];

            sum += amount;
            tempList.add(new Pair(current.x, current.y));
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (!inRange(nx, ny)) {
                    continue;
                }

                int opponentAmount = grid[nx][ny];

                if (!visited[nx][ny] && l <= Math.abs(amount - opponentAmount) && Math.abs(amount - opponentAmount) <= r) {
                    visited[nx][ny] = true;
                    queue.add(new Pair(nx, ny));
                }
            }
        }

        int size = tempList.size();

        int num = sum / size;
        for (Pair pair : tempList) {
            grid[pair.x][pair.y] = num;
        }
    }

    public static void print() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while (true) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;
                        queue.add(new Pair(i, j));

                        bfs();
                        cnt++;
                    }
                }
            }

            if (cnt == n * n) {
                break;
            } else {
                ans += 1;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    visited[i][j] = false;
                }
            }
        }

        System.out.println(ans);
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}