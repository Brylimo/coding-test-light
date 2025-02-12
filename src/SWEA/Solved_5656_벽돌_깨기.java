package SWEA;
import java.util.*;
import java.io.*;

public class Solved_5656_벽돌_깨기 {
    static int n, w, h, ans, subCnt, total;
    static int[][] grid;
    static int[][] vGrid;
    static int[][] sGrid;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static ArrayList<Integer> candidates = new ArrayList<>();

    static void vibrate(int x, int y, int power) {

        // 구슬이 진동한다.
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < power; j++) {
                int nx = x + dx[i] * j;
                int ny = y + dy[i] * j;

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;

                if (vGrid[nx][ny] > 0) {
                    int temp = vGrid[nx][ny];
                    vGrid[nx][ny] = 0;

                    subCnt++;
                    vibrate(nx, ny, temp);
                }
            }
        }
    }

    static void shoot() {
        // 실제 구슬을 쏨
        for (int i = 0; i < candidates.size(); i++) {
            int picked = candidates.get(i);

            // sGrid 초기화
            for (int a = 0; a < h; a++) {
                for (int b = 0; b < w; b++) {
                    sGrid[a][b] = 0;
                }
            }

            for (int j = 0; j < h; j++) { // 높이만큼 내려감
                if (vGrid[j][picked] > 0) {
                    int power = vGrid[j][picked];

                    // 가장 위에 있는 벽돌을 진동시킴
                    vibrate(j, picked, power);
                    break;
                }
            }

            for (int a = 0; a < w; a++) {
                int idx = h - 1;
                for (int b = h - 1; b >= 0; b--) {
                    if (vGrid[b][a] > 0) {
                        sGrid[idx--][a] = vGrid[b][a];
                    }
                }
            }

            //vGrid에 sGrid 옮기기
            for (int a = 0; a < h; a++) {
                for (int b = 0; b < w; b++) {
                    vGrid[a][b] = sGrid[a][b];
                }
            }

        }

        ans = Math.min(ans, total - subCnt);
        subCnt = 0;
    }

    static void initialize() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                vGrid[i][j] = grid[i][j];
            }
        }
    }

    static void calculate(int idx, int cnt) {
        // 쏠 구슬의 위치를 고르기 -> 이거때매 틀림
        /*if (idx == w) {
            if (cnt == n) {
                initialize();
                shoot();
            }

            return;
        }*/

        if (cnt == n) {
            initialize();
            shoot();
            return;
        }

        for (int i = 0; i < w; i++) {
            candidates.add(i);
            calculate(idx + 1, cnt + 1);
            candidates.remove(candidates.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            grid = new int[h][w];
            vGrid = new int[h][w];
            sGrid = new int[h][w];

            total = 0;
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    if (grid[i][j] > 0) total++;
                }
            }

            ans = total;
            calculate(0, 0);

            System.out.printf("#%d %d\n", t, ans);
        }
    }
}
