package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * P10799_쇠막대기
 * 난이도 1/10
 * 스택
 * 152ms
 *
 * '('인 경우 스택에 포함해주고 ()인 경우 레이저이므로 스택에 들어있는 전체 개수를 더해준다.
 * ')'인 경우 스택에 들어있는 값을 pop해주고 cnt를 1 증가시켜주면 문제에서 원하는데로 동작한다.
 *
 */
public class P10799_쇠막대기 {
    static ArrayDeque<Character> stack = new ArrayDeque<>();
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int i = 0;
        while (i < input.length()) { // 종료 조건
            if (input.charAt(i) == '(') {
                if (input.charAt(i + 1) == ')') { // 레이저
                    cnt += stack.size();
                    i += 1;
                } else {
                    stack.push('(');
                }
            } else {
                stack.pop();
                cnt += 1;
            }

            i += 1;
        }

        System.out.println(cnt);
    }
}
