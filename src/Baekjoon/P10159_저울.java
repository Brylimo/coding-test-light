package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * Main_10159_저울
 * 난이도 2/10
 * 플로이드 워셜
 * 148ms
 *
 * 플로이드 워셜 알고리즘을 이용해서 물건의 비교 관계를 테이블에 저장하고 이 테이블을 이용해서
 * 비교 결과를 알 수 없는 물건의 개수를 구한다.
 *
 */
public class P10159_저울 {
    static StringBuilder sb = new StringBuilder();
    static int[][] table;
    static int INT_MAX = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        table = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(table[i], INT_MAX);
        }

        for (int i = 0; i <= n; i++) {
            table[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            table[a][b] = 1;
        }

        // 플로이드 워셜 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (table[i][j] > table[i][k] + table[k][j]) {
                        table[i][j] = table[i][k] + table[k][j];
                    }
                }
            }
        }

        /*for (int i = 1; i <= n; i++) {
            System.out.println(Arrays.toString(table[i]));
        }*/

        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (table[i][j] == INT_MAX && table[j][i] == INT_MAX) { // 비교 결과를 알 수 없으면 개수 증가
                    cnt++;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}
