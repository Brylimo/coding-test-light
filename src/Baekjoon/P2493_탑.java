package Baekjoon;
import java.util.*;
import java.io.*;

public class P2493_탑 {
    static Stack<Pair> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[] res = new int[n]; // 신호 수신 결과가 담김
        int[] arr = new int[n]; // 탑의 높이가 담김

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n - 1; i >= 0; i--) {
            int current = arr[i];

            if (stack.isEmpty()) {
                stack.add(new Pair(i, current));
            } else {
                while (!stack.isEmpty() && stack.peek().value < current) {
                    Pair pair = stack.pop();

                    res[pair.idx] = i + 1;
                }

                stack.add(new Pair(i, current));
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(res[i] + " ");
        }
    }

    static class Pair {
        int idx;
        int value;

        public Pair(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
}
