package SWEA;
import java.util.*;
import java.io.*;

/**
 * Solved_2105_디저트_카페
 * 난이도 3/10
 * 구현
 * 519ms
 *
 * n이 20까지로 충분히 작기 때문에 모든 좌표마다 모든 마름모를 그려 같은게 있으면 넣어주고 정답을 갱신하도록 했다.
 * 시간을 줄이기 위해 디저트를 포함할 때 HashSet을 사용해 넣어주었다.
 * 이미 HashSet에 포함된 디저트가 있거나 grid 범위를 벗어나면 카운트를 해주지 않고 바로 다음 경우의 수로 넘어갈 수 있도록 처리해주었다.
 */
public class Solved_2105_디저트_카페 {
    static int[] dx = new int[] {1, 1, -1, -1};
    static int[] dy = new int[] {1, -1, -1, 1};

    static int n, ans;
    static int[][] arr;
    static HashSet<Integer> candidates = new HashSet<>();

    static void track(int x, int y) { // 마름모 방향으로 일일히 다니면서 트래킹하는 함수
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                int sx = x;
                int sy = y;
                boolean pass = true;
                // \ 방향으로 i만큼, / 방향으로 j만큼 이동
                OUT : for (int k = 0; k < 4; k++) {
                    if (k == 0 || k == 2) { // \ 방향이면
                        for (int l = 0; l < i; l++) {
                            int nx = sx + dx[k];
                            int ny = sy + dy[k];

                            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                                pass = false;
                                break OUT;
                            }

                            if (!candidates.contains(arr[nx][ny])) {
                                sx = nx;
                                sy = ny;

                                candidates.add(arr[nx][ny]);
                            } else {
                                pass = false;
                                break OUT;
                            }
                        }
                    } else { // 그 반대이면
                        for (int l = 0; l < j; l++) {
                            int nx = sx + dx[k];
                            int ny = sy + dy[k];

                            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                                pass = false;
                                break OUT;
                            }

                            if (!candidates.contains(arr[nx][ny])) {
                                sx = nx;
                                sy = ny;

                                candidates.add(arr[nx][ny]);
                            } else {
                                pass = false;
                                break OUT;
                            }
                        }
                    }
                }

                if (pass) {
                    ans = Math.max(ans, candidates.size());
                }

                candidates.clear();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());

            arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    track(i, j);
                }
            }

            if (ans == 0) {
                System.out.printf("#%d %d\n", t, -1);
            } else {
                System.out.printf("#%d %d\n", t, ans);
            }

            ans = 0;
        }
    }
}
