package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * P2504_괄호의_값
 * 난이도 4/10
 * 스택
 * 136ms
 *
 * 괄호값들의 순서에 따라 곱셈, 덧셈 연산이 결정나게 된다. 수들이 덧셈으로 구분되고 생각하고 각각의 구분되는 수들은 모든 곱셈 연산이 포함되어 있다고 생각했다.
 * 이럴 경우 여는 괄호가 나오게 되면 해당 괄호에 맞게 수들을 곱하는 연산만 하면 되고 닫는 괄호가 나오면 수들을 나누면 되기 때문에 수들에 대한 연산이 좀 더 간단해졌다.
 * 또한 ()나 []처럼 서로 붙어 있는 경우가 생기는데 이 때 덧셈 연산을 해주면 문제에서 원하는데로 결과가 도출됐다.
 *
 */
public class P2504_괄호의_값 {
    static ArrayDeque<Character> stack = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int temp = 1;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') { // 여는 괄호면 곱셈
                temp *= 2;
                stack.push('(');
            } else if (s.charAt(i) == '[') { // 여는 괄호면 곱셈
                temp *= 3;
                stack.push('[');
            } else if (s.charAt(i) == ')') { // 닫는 괄호면 나눗셈
                if (stack.isEmpty() || stack.peek() != '(') {
                    System.out.println(0);
                    System.exit(0);
                } else if (i - 1 >= 0 && s.charAt(i - 1) == '(') { // ()이면 덧셈
                    ans += temp;
                }

                stack.pop();
                temp /= 2;
            } else if (s.charAt(i) == ']') { // 닫는 괄호면 나눗셈
                if (stack.isEmpty() || stack.peek() != '[') {
                    System.out.println(0);
                    System.exit(0);
                } else if (i - 1 >= 0 && s.charAt(i - 1) == '[') { // []면 덧셈
                    ans += temp;
                }

                stack.pop();
                temp /= 3;
            }

        }

        if (!stack.isEmpty()) { // 스택이 비어있다면 종료
            System.out.println(0);
            System.exit(0);
        }

        System.out.println(ans);
    }
}
