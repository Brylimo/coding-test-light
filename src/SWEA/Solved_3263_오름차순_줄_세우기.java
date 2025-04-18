package SWEA;
import java.util.*;
import java.io.*;

/**
 * Solved_3263_오름차순_줄_세우기
 * 난이도 6/10
 * 다이나믹 프로그래밍
 * 220ms
 *
 * 줄에 대한 정보가 주어지면 오름차순으로 있는 가장 긴 길이를 구한다. 이 때 그냥 오름차순이 아니고 서로 1씩 차이가 나는 가장 긴 길이를 구한다.
 * 이렇게 오름차순 길이를 구하면 이 외의 수들은 모두 가장 앞이나 가장 뒤로 이동시키면 오름차순 줄을 세울 수 있기 때문에 전체 길이에서 이 길이를 빼주면 답이 된다.
 *
 */
public class Solved_3263_오름차순_줄_세우기 {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            int res = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[] LIS = new int[n + 1];

            for (int i = 0; i < n; i++) {
                LIS[arr[i]] = LIS[arr[i] - 1] + 1;
                res = Math.max(res, LIS[arr[i]]);
            }

            sb.append("#").append(t).append(" ").append(n - res).append("\n");
        }

        System.out.println(sb);
    }

}
