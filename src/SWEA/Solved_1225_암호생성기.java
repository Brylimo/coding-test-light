package SWEA;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

/**
 * Solved_1225_암호생성기
 * 난이도 5/10
 * 구현
 * 137ms
 *
 * 주기를 찾아서 주기의 반복 개수만큼 수학적으로 연산 횟수를 줄이고
 * 최소 필요 연산 횟수부터 일일히 값을 이동시켜 준다.
 *
 */
public class Solved_1225_암호생성기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            int tt = Integer.parseInt(br.readLine());

            int[] arr = new int[8];
            st = new StringTokenizer(br.readLine());

            // 8자리 암호에서 최소값을 찾음
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < 8; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                min = Math.min(min, arr[i]);
            }

            // 최솟값을 기준으로 주기의 횟수를 구함
            int cnt = min / 15; // 총 cnt번

            cnt -= 1; // 0이 여러번 올 수 있기 때문에 cnt를 하나 줄여 계산
            int move = (cnt * 40) % 8;

            if (cnt > 0) {
                for (int i = 0; i < 8; i++) {
                    arr[i] %= (15 * cnt);
                }
            }

            ArrayDeque<Integer> deque = new ArrayDeque<>(Arrays.stream(arr).boxed().collect(Collectors.toList()));

            // move 만큼 이동
            for (int i = 0; i < move; i++) { // 이동
                int temp = deque.pollFirst();
                deque.offer(temp);
            }

            // 이후부터 한칸씩 이동하여 0이 될 때까지 반복
            int num = 1;
            OUT : while (true) {
                for (int i = 0; i < 8; i++) {
                    int temp = deque.pollFirst();
                    temp -= num++;

                    if (temp < 0) temp = 0; // 음수이면 0으로 처리
                    deque.offerLast(temp);

                    if (temp == 0) break OUT; // 0이면 while문 out

                    if (num == 6) { // num이 6이면 num을 1로 변환
                        num = 1;
                    }
                }
            }

            sb.append("#").append(t);
            while (!deque.isEmpty()) {
                int current = deque.pollFirst();
                sb.append(" ").append(current);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}