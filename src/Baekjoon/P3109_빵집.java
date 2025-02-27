package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * Main_3109_빵집
 * 난이도 6/10
 * DFS
 * 552ms
 *
 * 그리디하게 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선 순서로 이동하고 마지막 열에 도착하면
 * 경로를 저장하는 방식으로 하여 모든 행에 대해 진행하였다.
 *
 * 재귀를 이용한 dfs 알고리즘을 사용할 경우 시간 초과가 나기 때문에
 * stack을 이용한 dfs 알고리즘을 설계한 후 마지막 열에 도착하면 dfs 알고리즘을 아예 빠져나올 수 있도록 하여
 * 경로를 저장해주었다.
 *
 */
public class P3109_빵집 {
    static int r, c, cnt;
    static char[][] grid;
    static boolean[][] visited;
    static boolean flag = false;

    static int[] dx = new int[] {1, 0, -1};
    static int[] dy = new int[] {1, 1, 1};

    static Stack<Pair> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        grid = new char[r][c];
        for (int i = 0; i < r; i++) {
            String input = br.readLine();
            for (int j = 0; j < c; j++) {
                grid[i][j] = input.charAt(j);
            }
        }

        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            stack.push(new Pair(i, 0));
            visited[i][0] = true;

            // dfs 알고리즘
            OUT : while (!stack.isEmpty()) {
                Pair current = stack.pop();
                visited[current.x][current.y] = true;

                if (current.y == c - 1) { // 종료 조건
                    cnt++; // 개수를 증가시키고 빠져나온다
                    break OUT;
                }

                for (int j = 0; j < 3; j++) {
                    int nx = current.x + dx[j];
                    int ny = current.y + dy[j];

                    if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                    if (!visited[nx][ny] && grid[nx][ny] == '.') {
                        stack.push(new Pair(nx, ny));
                    }
                }
            }

            stack.clear();

        }

        System.out.println(cnt);
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
