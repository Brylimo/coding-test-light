package Baekjoon;
import java.util.*;
import java.io.*;

public class P3015_오아시스_재결합 {
    static Stack<Pair> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(new Pair(arr[i], 1));
            } else {

                Pair samePair = null;
                while (!stack.isEmpty() && stack.peek().num <= arr[i]) {
                    ans += stack.peek().count;
                    if (stack.peek().num == arr[i]) {
                        stack.peek().count += 1;
                        samePair = stack.pop();
                    } else {
                        stack.pop();
                    }
                }

                if (!stack.isEmpty()) {
                    ans += 1;
                }

                if (samePair != null) {
                    stack.push(samePair);
                } else {
                    stack.push(new Pair(arr[i], 1));
                }
            }
        }

        System.out.println(ans);
    }

    static class Pair {
        long num;
        int count;

        public Pair(long num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}
