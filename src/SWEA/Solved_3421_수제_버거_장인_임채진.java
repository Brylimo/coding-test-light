package SWEA;
import java.util.*;
import java.io.*;

/**
 * Solved_3421_수제_버거_장인
 * 난이도 7/10
 * 백트래킹
 * 94ms
 *
 * 서로 어울리지 않는 재료들을 먼저 table에 표시해 두고 백트래킹을 통해 모든 경우의 수를 찾아본다.
 * 이 때 naive하게 모든 경우의 수를 찾아보면 시간초과가 걸리기 때문에 isPossible 함수를 둬서 가지치기를 통해
 * 서로 어울리지 않는 재료들이 있을 경우 재귀의 진행을 막는다.
 *
 */
public class Solved_3421_수제_버거_장인_임채진 {
    static int n, m, ans;
    static int[] selected;
    static boolean[] visited;
    static boolean[][] table;

    static boolean isPossible(int target, int depth) {
        // 현재까지 뽑은 값들이 selected에 저장되어 있으므로 현재 추가하려는 target과 모두 비교하고
        // target이 서로 어울리지 않는 재료라면 false를 리턴한다.
        for (int i = 0; i < depth; i++) {
            if (table[target][selected[i]])
                return false;
        }
        return true;
    }

    static void calculate(int depth, int startIdx) { // 백트래킹
        if (depth <= n) {
            ans++;

            for (int i = startIdx; i <= n; i++) {
                if (!visited[i] && isPossible(i, depth)) { // 만약 i가 뽑힐 수 있다면
                    visited[i] = true;
                    selected[depth] = i; // selected 배열에 i 값을 저장한다.

                    calculate(depth + 1, i + 1); // 다음으로 넘어감

                    visited[i] = false;
                }
            }

            return;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            table = new boolean[n + 1][n + 1];
            visited = new boolean[n + 1];

            // 서로 어울리지 않는 재료들을 table에 표시한다.
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                table[a][b] = true;
                table[b][a] = true;
            }

            selected = new int[n + 1];
            calculate(0, 1);

            sb.append("#").append(t).append(" ").append(ans).append("\n");
            ans = 0;
        }
        System.out.println(sb);
    }

}
