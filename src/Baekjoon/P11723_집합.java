package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * Main_11723_집합
 * 난이도 2/10
 * 비트마스킹
 * 1016ms
 *
 * 비트마스킹을 이용하여 집합을 표현하여 문제를 해결했다.
 *
 */
public class P11723_집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());

        int snum = 0; // 비트 초기화
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            String op = st.nextToken();
            if (op.equals("add")) {
                int x = Integer.parseInt(st.nextToken());

                if ( ((snum >> x) & 1) != 1 ) { // 포함하지 않으면
                    snum += (1 << x); // 집합에 포함
                }
            } else if (op.equals("remove")) {
                int x = Integer.parseInt(st.nextToken());

                if ( ((snum >> x) & 1) == 1 ) { // 포함하면
                    snum -= (1 << x); // 집합에서 뺌
                }
            } else if (op.equals("check")) {
                int x = Integer.parseInt(st.nextToken());

                if ( ((snum >> x) & 1) == 1 ) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else if (op.equals("toggle")) {
                int x = Integer.parseInt(st.nextToken());

                snum ^= (1 << x); // 토글
            } else if (op.equals("all")) {
                snum = (1 << 21) - 1; // (1 << 21)에서 1 빼어 모든 경우가 포함되도록 만듦
            } else if (op.equals("empty")) {
                snum = 0; // 0으로 초기화 (집합 비움)
            }

        }

        System.out.println(sb);
    }
}
