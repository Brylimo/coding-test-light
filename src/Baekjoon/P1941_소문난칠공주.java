package Baekjoon;
import java.util.*;
import java.io.*;

public class P1941_소문난칠공주 {
    static char[][] grid;
    static boolean[][] visited;
    static boolean[][] cmap;
    static ArrayList<Pair> list = new ArrayList<>();
    static ArrayList<Pair> candidates = new ArrayList<>();
    static Queue<Pair> queue = new LinkedList<>();

    // static int[] candidates = new int[2]; // S 카운트, Y 카운트
    static int ans;

    // dfs로 푸는건줄 알았는데 이렇게 풀면 안된다. -> 안되는 케이스가 존재한다.
    // static int[] dx = new int[] { 1, 0 };
    // static int[] dy = new int[] { 0, 1 };

    /*static void dfs(int i, int j) {
        // 종료 조건
        if (i == 5 && j == 5) return;

        if (candidates[1] == 4) { // 임도연파가 4명 이상이면 안된다
            return;
        }

        if (candidates[0] + candidates[1] == 7) { // 소문난 칠공주 결성 완료
            ans += 1;
            return;
        }

        for (int k = 0; k < 2; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny]) continue;

            if (grid[nx][ny] == 'Y') { // 임도연파
                candidates[1] += 1;
            } else { // 이다솜파
                candidates[0] += 1;
            }

            visited[nx][ny] = true;

            dfs(nx, ny);

            visited[nx][ny] = false;
            // 다시 원상태 복구
            if (grid[nx][ny] == 'Y') { // 임도연파
                candidates[1] -= 1;
            } else { // 이다솜파
                candidates[0] -= 1;
            }
        }
    }*/

    public static void caluclate(int idx) {
        if (candidates.size() == 7) { // 7 공주 결성
            for (int i = 0; i < 5; i++) { // 초기화
                for (int j = 0; j < 5; j++) {
                    cmap[i][j] = false;
                    visited[i][j] = false;
                }
            }

            int yCnt = 0;
            int sCnt = 0;
            for (Pair p : candidates) {
                cmap[p.x][p.y] = true;

                if (grid[p.x][p.y] == 'Y') {
                    yCnt += 1;
                } else if (grid[p.x][p.y] == 'S') {
                    sCnt += 1;
                }
            }

            if (sCnt < 4) { // 4명 이하이면 종료
                return;
            }

            // bfs로 검증
            visited[candidates.get(0).x][candidates.get(0).y] = true;
            queue.offer(new Pair(candidates.get(0).x, candidates.get(0).y));

            bfs();

            int cnt = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (visited[i][j]) {
                        cnt++;
                    }
                }
            }

            if (cnt == 7) { // 7 공주 모두 연결되어 있음
                ans += 1;
            }

            return;
        }

        if (idx == 25) {
            if (candidates.size() == 7) {
                for (int i = 0; i < 5; i++) { // 초기화
                    for (int j = 0; j < 5; j++) {
                        cmap[i][j] = false;
                        visited[i][j] = false;
                    }
                }

                int yCnt = 0;
                int sCnt = 0;
                for (Pair p : candidates) {
                    cmap[p.x][p.y] = true;

                    if (grid[p.x][p.y] == 'Y') {
                        yCnt += 1;
                    } else if (grid[p.x][p.y] == 'S') {
                        sCnt += 1;
                    }
                }

                if (sCnt < 4) { // 4명 이하이면 종료
                    return;
                }

                // bfs로 검증
                visited[candidates.get(0).x][candidates.get(0).y] = true;
                queue.offer(new Pair(candidates.get(0).x, candidates.get(0).y));

                bfs();

                int cnt = 0;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (visited[i][j]) {
                            cnt++;
                        }
                    }
                }

                if (cnt == 7) { // 7 공주 모두 연결되어 있음
                    ans += 1;
                }
            }

            return;
        }

        Pair current = list.get(idx);

        candidates.add(new Pair(current.x, current.y));
        caluclate(idx + 1);
        candidates.remove(candidates.size() - 1);

        caluclate(idx + 1);
    }

    static void bfs() {
        int[] dx = new int[] {-1, 0, 1, 0};
        int[] dy = new int[] {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

                if (!visited[nx][ny] && cmap[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Pair(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // grid 생성 및 초기화
        grid = new char[5][5];
        cmap = new boolean[5][5];
        visited = new boolean[5][5];

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = line.charAt(j);

                list.add(new Pair(i, j));
            }
        }

        // 조합을 구함
        caluclate(0);

        /*for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (grid[i][j] == 'Y') { // 임도연파
                    candidates[1] += 1;
                } else { // 이다솜파
                    candidates[0] += 1;
                }

                visited[i][j] = true;
                dfs(i, j);
                visited[i][j] = false;

                if (grid[i][j] == 'Y') { // 임도연파
                    candidates[1] -= 1;
                } else { // 이다솜파
                    candidates[0] -= 1;
                }
            }
        }*/

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