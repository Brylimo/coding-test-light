package SWEA;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Solved_2105_디저트_카페 {
    static int[] dx = new int[] {1, 1, -1, -1};
    static int[] dy = new int[] {1, -1, -1, 1};

    static int n, ans;
    static int[][] arr;
    static HashSet<Integer> candidates = new HashSet<>();

    static void track(int x, int y) {
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
