package SWEA;
import java.util.*;
import java.io.*;

public class Solved_2382_미생물_격리_못품 {
    static int[] dx = new int[] {0, -1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = new int[] {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            ArrayList<Integer>[][] grid = new ArrayList[n][n];
            ArrayList<Pair> list = new ArrayList<>();

            // 초기화
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = new ArrayList<>();
                }
            }

            for (int i = 1; i <= k; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                list.add(new Pair(i, x, y, num, dir));
                grid[x][y].add(i);
            }

            for (int p = 0; p < m; p++) { //  M시간 동안 미생물 격리

                // 전체를 순회하며 군집을 이동시킴
                for (int i = 0; i < list.size(); i++) {
                    Pair current = list.get(i);

                    int nx = current.x + dx[current.dir];
                    int ny = current.y + dy[current.dir];

                    if (nx <= 0 || nx >= n - 1 || ny <= 0 || ny >= n - 1) { // 약품이 칠해진 곳들
                        current.num = (int) Math.floor((double) current.num / 2); // 미생물의 절반이 죽음

                        // 군집의 이동
                        grid[current.x][current.y].remove(current.idx);
                        grid[nx][ny].add(current.idx);

                        current.x = nx;
                        current.y = ny;

                        // 방향 반대로 바꿔줌
                        if (current.dir < 2) {
                            current.dir = 1 - current.dir;
                        } else {
                            current.dir = 5 - current.dir;
                        }

                        continue;
                    }

                    // 군집의 이동
                    grid[current.x][current.y].remove(current.idx);
                    grid[nx][ny].add(current.idx);

                    current.x = nx;
                    current.y = ny;
                }
            }

        }
    }

    static class Pair {
        int idx;
        int x;
        int y;
        int dir;
        int num;

        public Pair(int idx, int x, int y, int num, int dir) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }
    }
}