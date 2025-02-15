package SWEA;
import java.util.*;
import java.io.*;

public class Solved_5644_무선_충전 {
    static int m, a, n = 10, ans;
    static int[] aPath;
    static int[] bPath;
    static ArrayList<Integer>[][] grid;
    static int[] performances;

    static int[] dx = new int[] {0, -1, 0, 1, 0};
    static int[] dy = new int[] {0, 0, 1, 0, -1};

    /* x, y를 기준으로 범위만큼 표시한다. */
    static void draw(int x, int y, int c, int idx) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Math.abs(i - x) + Math.abs(j - y) <= c) {
                    grid[i][j].add(idx);
                }
            }
        }
    }

    // 이동
    static void move() {
        int ax = 0, ay = 0, bx = 9, by = 9;

        int maxValue = 0;
        if (grid[ax][ay].size() > 0 && grid[bx][by].size() == 0) { // a만 영역 안에 있을 경우
            for (int a = 0; a < grid[ax][ay].size(); a++) {
                int aIdx = grid[ax][ay].get(a);

                maxValue = Math.max(maxValue, performances[aIdx]);
            }
        } else if (grid[bx][by].size() > 0 && grid[ax][ay].size() == 0) { // b만 영역 안에 있을 경우
            for (int b = 0; b < grid[bx][by].size(); b++) {
                int bIdx = grid[bx][by].get(b);

                maxValue = Math.max(maxValue, performances[bIdx]);
            }
        } else { // 둘 다 영역 안에 있을 경우
            for (int a = 0; a < grid[ax][ay].size(); a++) {
                for (int b = 0; b < grid[bx][by].size(); b++) {
                    int aIdx = grid[ax][ay].get(a);
                    int bIdx = grid[bx][by].get(b);

                    if (aIdx == bIdx) {
                        maxValue = Math.max(maxValue, performances[aIdx]);
                    } else {
                        maxValue = Math.max(maxValue, performances[aIdx] + performances[bIdx]);
                    }
                }
            }
        }

        ans += maxValue;

        for (int i = 0; i < m; i++) { // m번 이동
            int aDir = aPath[i];
            int bDir = bPath[i];

            ax = ax + dx[aDir];
            ay = ay + dy[aDir];

            bx = bx + dx[bDir];
            by = by + dy[bDir];

            maxValue = 0;
            if (grid[ax][ay].size() > 0 && grid[bx][by].size() == 0) { // a만 영역 안에 있을 경우
                for (int a = 0; a < grid[ax][ay].size(); a++) {
                    int aIdx = grid[ax][ay].get(a);

                    maxValue = Math.max(maxValue, performances[aIdx]);
                }
            } else if (grid[bx][by].size() > 0 && grid[ax][ay].size() == 0) { // b만 영역 안에 있을 경우
                for (int b = 0; b < grid[bx][by].size(); b++) {
                    int bIdx = grid[bx][by].get(b);

                    maxValue = Math.max(maxValue, performances[bIdx]);
                }
            } else { // 둘 다 영역 안에 있을 경우
                for (int a = 0; a < grid[ax][ay].size(); a++) {
                    for (int b = 0; b < grid[bx][by].size(); b++) {
                        int aIdx = grid[ax][ay].get(a);
                        int bIdx = grid[bx][by].get(b);

                        if (aIdx == bIdx) {
                            maxValue = Math.max(maxValue, performances[aIdx]);
                        } else {
                            maxValue = Math.max(maxValue, performances[aIdx] + performances[bIdx]);
                        }
                    }
                }
            }

            ans += maxValue;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());

            aPath = new int[m];
            bPath = new int[m];

            grid = new ArrayList[n][n];
            performances = new int[a + 1];

            // grid에 표시
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = new ArrayList<>();
                }
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                aPath[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                bPath[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= a; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken()); // 좌표
                int y = Integer.parseInt(st.nextToken()); // 좌표
                int c = Integer.parseInt(st.nextToken()); // 충전범위
                int p = Integer.parseInt(st.nextToken()); // 성능

                x -= 1;
                y -= 1;

                // 성능 등록
                performances[i] = p;

                // x, y를 기준으로 표시하기
                draw(y, x, c, i);
            }

            // A와 B 이동
            move();

            System.out.printf("#%d %d\n", t, ans);
            ans = 0;
        }
    }
}
