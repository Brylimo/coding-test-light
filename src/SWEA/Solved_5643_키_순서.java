package SWEA;

import java.util.*;
import java.io.*;

/**
 * SWEA5643_키_순서
 * 난이도 2/10
 * 플로이드 워셜
 * 1,757ms
 *
 * 문제 요약
 * 두 학생끼리의 키를 비교한 결과 중 일부가 주어졌을 때 자신의 키가 몇 번째인지를 알 수 있는 학생들이 모두 몇 명인지 출력하는 문제이다.
 * a, b의 순서쌍이 주어지는데 이 때 a인 학생이 번호가 b인 학생보다 키가 작은 것을 의미한다.
 *
 * 아이디어
 * 모든 학생들과 키를 비교한 결과를 알고 있을 때 자신의 키가 몇 번째인지 알 수 있는 학생인지를 알 수 있다.
 * n 뒤에 몇명 + n 앞에 몇명 = N-나 이면 나는 그 중간에 껴있다는 것이다.
 * 다른 노드를 경유해서라도 어떤 노드로 갈 수 있는지 검사 -> 플로이드 워셜
 *
 * 풀이
 * n * n 2차원 배열을 만들고 a에서 b로 가는 방법이 있다면 1을 표시하고 없다면 0을 표시한다. (준비 단계)
 * 플로이드 워셜 알고리즘을 통해 거쳐갈 정점을 통해 다른 정점으로 이동할 수 있다면 1을 표시하여 저장한다. (a에서 b로 이동할 수 있다면 1이 표시된다.)
 * 테이블이 완성되면 n까지 순회하며 둘 중 누가 큰지 알 수 있는 경우라면 cnt를 1 증가시킨다.
 * cnt가 n - 1이라면 (i == j인 경우 제외) 자신의 키가 몇 번째인지 알 수 있으므로 "자신의 키가 몇 번째인지 알 수 있는 학생 카운트"를 증가시킨다.
 * 자신의 키가 몇 번째인지 알 수 있는 학생 카운트를 답으로 프린트한다.
 *
 * 참고문서
 * https://www.acmicpc.net/problem/2458 (백준 유사 문제)
 * https://velog.io/@iamjinseo/%EB%B0%B1%EC%A4%80-%EA%B3%A8%EB%93%9C4%ED%82%A4-%EC%88%9C%EC%84%9C-Java
 *
 */
public class Solved_5643_키_순서 {
    static int n, m; // n: 학생의 수, m: 두 학생의 키를 비교한 횟수
    static int[][] arr; // n * n 2차원 배열, arr[i][j] = i에서 j로 가는 방법이 있다면 1, 없다면 0 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트케이스의 개수
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine()); // 학생의 수 입력 받음
            m = Integer.parseInt(br.readLine()); // 두 학생의 키를 비교한 횟수 입력 받음

            // 그래프 초기화
            arr = new int[n][n];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()); // a 입력
                int b = Integer.parseInt(st.nextToken()); // b 입력

                // a - 1 > b - 1 가 확실하므로 1을 적어준다.
                arr[a - 1][b - 1] = 1; // a - 1에서 b - 1로 가는 방법이 있다면 1 표시
            }

            // 플로이드-워셜
            for (int k = 0; k < n; k++) { // 확실하게 거쳐갈 정점을 0번부터 n - 1번까지 순서대로 정의한다.
                for (int i = 0; i < n; i++) { // 시작 지점
                    for (int j = 0; j < n; j++) { // 종료 지점
                        // i -> k, k -> j로 가는 길이 있다면
                        // i -> j도 가능하다.
                        if ((arr[i][k] > 0) && (arr[k][j] > 0)) {
                            arr[i][j] = 1;
                        }
                    }
                }
            }

            int ans = 0; // ans -> 자신의 키가 몇 번째인지 알 수 있는 학생 카운트
            for (int i = 0; i < n; i++) {
                int cnt = 0;
                for (int j = 0; j < n; j++) {
                    if (i == j) // i와 j가 같다면 넘어감
                        continue;

                    if ((arr[j][i] > 0) || (arr[i][j] > 0)) { // 둘 중 누가 큰지 알 수 있는 경우라면
                        cnt++; // 개수 증가
                    }
                }

                if (cnt == n - 1) { // 개수가 n - 1이라면 -> 자신의 키가 몇 번째인지 알 수 있음
                    ans++; // 자신의 키가 몇 번째인지 알 수 있는 학생 카운트를 하나 증가시킨다.
                }
            }

            System.out.printf("#%d %d\n", t, ans);
        }
    }
}
