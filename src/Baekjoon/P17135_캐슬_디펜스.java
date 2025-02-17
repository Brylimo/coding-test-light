package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * P17135_캐슬_디펜스
 * 난이도 6/10
 * 구현
 * 376ms
 *
 * 문제의 조건을 잘못 체크하고 가장 왼쪽에 있는 적을 지우는 부분에서 시간을 많이 허비했다.
 * 문제의 조건대로 궁수가 공격하고 아래로 내려오는 부분을 구현해 문제를 해결했다.
 * 같은 적을 여러명이 선택할 수 있기 때문에 따로 ArrayList를 선택해 나중에 적을 지워주도록 해주었다.
 */
public class P17135_캐슬_디펜스 {
    static int n, m, d, cnt, ans;
    static int[][] grid;
    static int[][] vGrid;
    static int[][] rGrid;
    static ArrayList<Integer> candidates = new ArrayList<>();

    static void attack() {
        ArrayList<int[]> dead = new ArrayList<>();
        for (int k = 0; k < 3; k++) {
            int minDist = Integer.MAX_VALUE;
            int nx = -1, ny = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (Math.abs(i - n) + Math.abs(j - candidates.get(k)) <= d && rGrid[i][j] == 1) {
                        if (minDist >= Math.abs(i - n) + Math.abs(j - candidates.get(k))) {

                            if (minDist == Math.abs(i - n) + Math.abs(j - candidates.get(k)) && ny > j) {
                                nx = i;
                                ny = j;

                                minDist = Math.abs(i - n) + Math.abs(j - candidates.get(k));
                            } else if (minDist > Math.abs(i - n) + Math.abs(j - candidates.get(k))) {
                                nx = i;
                                ny = j;

                                minDist = Math.abs(i - n) + Math.abs(j - candidates.get(k));
                            }
                        }
                    }
                }
            }

            if (!(nx == -1 && ny == -1)) {
                dead.add(new int[] {nx, ny});
            }
        }

        for (int i = 0; i < dead.size(); i++) {
            if (rGrid[dead.get(i)[0]][dead.get(i)[1]] == 1) {
                cnt++;
                rGrid[dead.get(i)[0]][dead.get(i)[1]] = 0;
            }
        }
    }

    static void move() {
        int[][] tGrid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rGrid[i][j] > 0) {
                    int nx = i + 1;

                    if (nx < n) {
                        tGrid[nx][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rGrid[i][j] = tGrid[i][j];
            }
        }
    }

    static void simulate() {
        while (true) {
            // 궁수 공격
            attack();

            // 한칸 아래로 이동
            move();

            boolean pass = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (rGrid[i][j] == 1) {
                        pass = false;
                    }
                }
            }

            if (pass) {
                break;
            }
        }
    }

    static void dispatch() {
        // rGrid에 grid 옮기기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rGrid[i][j] = grid[i][j];
                vGrid[i][j] = 0;
            }
        }

        // 영역 표시
        /*for (int i = 0; i < 3; i++) {
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m; b++) {
                    if (Math.abs(a - n) + Math.abs(b - candidates.get(i)) <= d) { // d 거리 내에 있음
                        vGrid[a][b] = 2;
                    }
                }
            }
        }*/

        simulate();

        ans = Math.max(ans, cnt);
        cnt = 0;
    }

    public static void calculate(int idx, int cnt) {
        if (idx == m) {
            if (cnt == 3) {
                dispatch();
            }
            return;
        }

        if (cnt == 3) {
            dispatch();
            return;
        }

        candidates.add(idx);
        calculate(idx + 1, cnt + 1);
        candidates.remove(candidates.size() - 1);

        calculate(idx + 1, cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        grid = new int[n + 1][m];
        vGrid = new int[n + 1][m];
        rGrid = new int[n + 1][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        calculate(0, 0);

        System.out.println(ans);
    }
}
