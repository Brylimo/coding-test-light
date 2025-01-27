package Baekjoon;
import java.util.*;
import java.io.*;

public class P10157_자리배정 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int sx = r - 1;
        int sy = 0;
        int dir = 0;
        int cnt = 1;

        int[][] arr = new int[r][c];
        boolean[][] visited = new boolean[r][c];

        arr[sx][sy] = cnt++;
        visited[sx][sy] = true;
        while (true) {
            int nx = sx + dx[dir];
            int ny = sy + dy[dir];

            if (nx < 0 || nx >= r || ny < 0 || ny >= c || visited[nx][ny]) {
                dir = (dir + 1) % 4; // 시계방향으로 회전

                nx = sx + dx[dir];
                ny = sy + dy[dir];
            }

            visited[nx][ny] = true;
            arr[nx][ny] = cnt++;
            sx = nx;
            sy = ny;

            if (cnt == r * c + 1) break;
        }


        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i][j] == k) {
                    System.out.println((j + 1) + " " + (r - i));
                    System.exit(0);
                }
            }
        }

        System.out.println(0);
    }
}
