package Baekjoon;
import java.util.*;
import java.io.*;

public class P2583_영역_구하기 {
    static int m, n;
    static int[][] arr;
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();

    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    static int bfs() {
        int tcnt = 0;
        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            tcnt += 1;
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (!visited[nx][ny] && arr[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.offer(new int[] { nx, ny });
                }
            }

        }

        return tcnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = x1; j < x2; j++) {
                for (int l = y1; l < y2; l++) {
                    arr[j][l] = 1;
                }
            }
        }

        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && arr[i][j] == 0) {
                    visited[i][j] = true;

                    cnt += 1;
                    queue.offer(new int[] {i, j});
                    int aa = bfs();

                    list.add(aa);
                }
            }
        }

        Collections.sort(list);
        sb.append(cnt).append("\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(" ");
        }

        System.out.println(sb);
    }
}
