package SWEA;

import java.util.*;
import java.io.*;

/**
 * Solved_4014_활주로_건설
 * 난이도 7/10
 * 구현
 * 114ms
 *
 * 처음에 슬라이딩 윈도우를 이용해 풀이를 접근하였지만 맨 처음 가장 높은 지형이 올 경우 등 고려해야할 조건이 많아져 구현 풀이로 변경하였다.
 * 가로와 세로로 한줄씩 체크하면서 가능하다면 카운트해준다.
 * before라는 변수를 활용해 전에 있는 값을 저장해주면서 현재 값과 비교해주도록 하였고 값이 같을 경우와 다를 경우별로 나누어 생각해서 로직을 구성하였다.
 * 각 경우의 수별로 상황에 맞게 로직을 변경해주면 성공이다.
 */
public class Solved_4014_활주로_건설 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int ans = 0;

            int[][] grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 가로
            for (int i = 0; i < n; i++) {
                int before = -1;
                int cnt = 0;

                int j = 0;
                boolean pass = true;
                OUT : while (j < n) {
                    if (before == -1 || before == grid[i][j]) { // 맨처음일 경우(-1)와 전의 높이와 현재 높이가 같을 때
                        cnt += 1;

                        before = grid[i][j];
                    } else if (before < grid[i][j] && Math.abs(before - grid[i][j]) == 1 && cnt >= x) { // 전에 높이보다 현재 높이가 더 클 경우, 성공 조건
                        cnt = 1;
                        before = grid[i][j];
                    } else if (before < grid[i][j] && cnt < x || before < grid[i][j] && Math.abs(before - grid[i][j]) != 1) { // 전에 높이보다 현재 높이가 더 클 경우, 실패 조건
                        pass = false;
                        break;
                    } else if (before > grid[i][j]) { // 전에 높이보다 현재 높이가 더 작을 경우
                        if (Math.abs(before - grid[i][j]) != 1) {
                            pass = false;
                        }

                        int kCnt = 1;
                        cnt = 0;
                        for (int k = 0; k < x - 1; k++) {
                            if (j + k + 1 < n && grid[i][j + k] != grid[i][j + k + 1]) {
                                pass = false;
                                break OUT;
                            }

                            if (j + k + 1 < n) {
                                kCnt++;
                            }
                        }

                        if (kCnt != x) {
                            pass = false;
                        }

                        before = grid[i][j];
                        j += kCnt;
                        continue;
                    }

                    j++;
                }

                if (pass) {
                    ans += 1;
                }
            }

            // 세로 -> 가로의 경우와 동일하게 접근
            for (int i = 0; i < n; i++) {
                int before = -1;
                int cnt = 0;

                int j = 0;
                boolean pass = true;
                OUT : while (j < n) {
                    if (before == -1 || before == grid[j][i]) {
                        cnt += 1;

                        before = grid[j][i];
                    } else if (before < grid[j][i] && Math.abs(before - grid[j][i]) == 1 && cnt >= x) {
                        cnt = 1;
                        before = grid[j][i];
                    } else if (before < grid[j][i] && cnt < x || before < grid[j][i] && Math.abs(before - grid[j][i]) != 1) {
                        pass = false;
                        break;
                    } else if (before > grid[j][i]) {
                        if (Math.abs(before - grid[j][i]) != 1) {
                            pass = false;
                        }

                        int kCnt = 1;
                        cnt = 0;
                        for (int k = 0; k < x - 1; k++) {
                            if (j + k + 1 < n && grid[j + k][i] != grid[j + k + 1][i]) {
                                pass = false;
                                break OUT;
                            }

                            if (j + k + 1 < n) {
                                kCnt++;
                            }
                        }

                        if (kCnt != x) {
                            pass = false;
                        }

                        before = grid[j][i];
                        j += kCnt;
                        continue;
                    }

                    j++;
                }

                if (pass) {
                    ans += 1;
                }
            }

            System.out.printf("#%d %d\n", t, ans);

        }
    }
}