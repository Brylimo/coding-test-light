package SsafyHomework;
import java.util.*;
import java.io.*;

public class Main_2008_빌딩건설 {
    static int[] dx = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= t; tt++) {
            int n = Integer.parseInt(br.readLine());

            int ans = 0;
            char[][] graph = new char[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    graph[i][j] = st.nextToken().charAt(0);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] == 'B') { // 빌딩 부지일 경우
                        // 8 방향 검사
                        boolean isPass = true; // 주변에 공원 부지가 없음
                        for (int k = 0; k < 8; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                            if (graph[nx][ny] == 'G') { // 공원 부지가 있음
                                isPass = false;
                                break;
                            }
                        }

                        if (isPass) { // 주변에 공원부지가 없음
                            int cnt = 0;

                            // 가로 방향 탐색
                            for (int k = 0; k < n; k++) {
                                if (graph[i][k] == 'B') {
                                    cnt += 1;
                                }
                            }

                            // 세로 방향 탐색
                            for (int k = 0; k < n; k++) {
                                if (graph[k][j] == 'B') {
                                    cnt += 1;
                                }
                            }

                            // 현재 칸이 두번 카운트되므로 중복되는 1을 빼줌
                            cnt -= 1;

                            ans = Math.max(ans, cnt);
                        } else { // 주변에 공원부지가 있음
                            ans = Math.max(ans, 2);
                        }

                    }
                }
            }

            System.out.printf("#%d %d\n", tt, ans);
        }
    }
}
