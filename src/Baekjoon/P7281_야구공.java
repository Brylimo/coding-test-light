package Baekjoon;
import java.util.*;
import java.io.*;

public class P7281_야구공 {
    static int n, m = 9, ans = 0;
    static int[][] result;

    static int[] lineUp;
    static boolean[] visited;

    static void calculate(int current) { // 순열 처리 함수
        if (current == m) {
            // 야구 게임 진행
            ans = Math.max(ans, play());
        } else if (current == 3) { // 4번 타자이면 넘어감
            calculate(current + 1);
            return;
        }

        for (int i = 0; i < m; i++) {
            if (!visited[i]) {
                visited[i] = true;
                lineUp[current] = i;
                calculate(current + 1);
                visited[i] = false;
            }
        }
    }

    static int play() { // 야구 게임 진행
        int inning = 0; // 이닝
        int outCnt = 0; // 아웃 카운트
        int score = 0; // 최종 점수
        int[] ground = new int[3]; // 1루, 2루, 3루 상태

        int idx = 0; // 타순 인덱스
        while (true) {
            int pIdx = lineUp[idx];
            int res = result[inning][pIdx]; // 현재 타석의 결과

            if (res == 0) { // 아웃
                outCnt += 1;
            } else if (res == 1) { // 안타
                for (int i = 2; i >= 0; i--) {
                    if (i == 2 && ground[i] == 1) { // 3루수에 사람이 있으면
                        score += 1;
                        ground[i] = 0;
                    } else if (i != 2 && ground[i] == 1) {
                        ground[i + 1] = 1;
                        ground[i] = 0;
                    }
                }
                ground[0] = 1;
            } else if (res == 2) { // 2루타
                for (int i = 2; i >= 0; i--) {
                    if ((i == 2 || i == 1) && ground[i] == 1) {
                        score += 1;
                        ground[i] = 0;
                    } else if (i == 0 && ground[i] == 1) {
                        ground[2] = 1;
                        ground[i] = 0;
                    }
                }
                ground[1] = 1;
            } else if (res == 3) { // 3루타
                for (int i = 2; i >= 0; i--) {
                    if (ground[i] == 1) {
                        score += 1;
                        ground[i] = 0;
                    }
                }
                ground[2] = 1;
            } else { // 홈런
                for (int i = 2; i >= 0; i--) {
                    if (ground[i] == 1) {
                        score += 1;
                        ground[i] = 0;
                    }
                }
                score += 1;
            }

            if (outCnt == 3) {
                outCnt = 0;
                inning += 1;

                // 이닝이 변경될 때마다 그라운드 초기화
                for (int i = 0; i < 3; i++) {
                    ground[i] = 0;
                }

                if (inning == n) return score;
            }
            idx = (idx + 1) % m; // 타순 하나 증가
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        result = new int[n][m]; // 결과표
        lineUp = new int[m]; // 타순 저장 배열
        visited = new boolean[m];

        // 1번 선수는 4번 타자로 미리 결정
        visited[0] = true;
        lineUp[3] = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // backtracking
        calculate(0);

        System.out.println(ans);
    }
}
