package SWEA;
import java.util.*;
import java.io.*;

public class Solved_2117_홈_방범_서비스 {
    static int n, m, ans;
    static int[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ArrayList<int[]> homes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) { // 집 위치 모두 저장
                        homes.add(new int[] {i, j});
                    }
                }
            }

            for (int size = 0; size <= 20; size++) { // 택시 거리가 20이 되야 모두 덮을 수 있다.
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {

                        int cnt = 0; // 집의 개수
                        for (int k = 0; k < homes.size(); k++) {
                            int[] current = homes.get(k);

                            int dist = Math.abs(current[0] - i) + Math.abs(current[1] - j); // 택시 거리
                            if (dist <= size) {
                                cnt += 1;
                            }
                        }

                        int value = cnt * m - ((size + 1) * (size + 1) + size * size);
                        if (value >= 0) {
                            ans = Math.max(ans, cnt);
                        }
                    }
                }
            }

            System.out.printf("#%d %d\n", t, ans);
            ans = 0;
        }
    }
}
