package Baekjoon;
import java.util.*;
import java.io.*;

public class P17135_캐슬_디펜스 {
    static int n, m, d, ans;
    static int[][] grid;
    static ArrayList<Pair> foes = new ArrayList<>();
    static boolean[] visited;
    static ArrayList<Integer> candidates = new ArrayList<>();

    static void killFoe(int x, int y) {
        int minDist = Integer.MAX_VALUE;
        int selectedIdx = 0;
        for (int i = 0; i < foes.size(); i++) {
            if (visited[i]) continue;

            int px = foes.get(i).x;
            int py = foes.get(i).y;

            if (minDist > Math.abs(x - px) + Math.abs(y - py)) {
                minDist = Math.abs(x - px) + Math.abs(y - py);
                selectedIdx = i;
            }
        }

        if (minDist <= d) {
            visited[selectedIdx] = true; // kill
        }
    }

    static void calculate(int idx, int cnt) {
        if (candidates.size() == cnt) {
            int y1 = candidates.get(0); // (n, y1)
            int y2 = candidates.get(1); // (n, y2)
            int y3 = candidates.get(2); // (n, y3)

            killFoe(n, y1);
            killFoe(n, y2);
            killFoe(n, y3);

            int temp = 0;
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    temp += 1;
                }
            }

            ans = Math.min(ans, temp);
            return;
        }

        if (idx == m) {
            if (candidates.size() == cnt) {
                int y1 = candidates.get(0); // (n, y1)
                int y2 = candidates.get(1); // (n, y2)
                int y3 = candidates.get(2); // (n, y3)

                killFoe(n, y1);
                killFoe(n, y2);
                killFoe(n, y3);

                int temp = 0;
                for (int i = 0; i < visited.length; i++) {
                    if (visited[i]) {
                        temp += 1;
                    }
                }

                ans = Math.min(ans, temp);
            }

            return;
        }

        candidates.add(idx);
        calculate(idx + 1, cnt);
        candidates.remove(candidates.remove(candidates.size() - 1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        ans = m; // 가장 많을 때 m개
        grid = new int[n + 1][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    foes.add(new Pair(i, j));
                }
            }
        }
        visited = new boolean[foes.size()];

        calculate(0, 3);

        System.out.println(ans);
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
