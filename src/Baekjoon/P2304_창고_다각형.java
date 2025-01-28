package Baekjoon;
import java.util.*;
import java.io.*;

public class P2304_창고_다각형 {
    static Stack<Pair> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int maxHeight = 0;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            pairs[i] = new Pair(l, h);
            maxHeight = Math.max(maxHeight, h);
        }

        Arrays.sort(pairs);

        int sum1 = 0;
        int index = 0;
        for (int i = 0; i < pairs.length; i++) { // 앞에서부터
            if (stack.isEmpty()) {
                index = pairs[i].l;
                stack.add(pairs[i]);
            } else {
                if (stack.peek().h < pairs[i].h) {
                    sum1 += stack.peek().h * (pairs[i].l - index);
                    index = pairs[i].l;
                    stack.add(pairs[i]);
                }
            }
        }
        sum1 += stack.peek().h * (pairs[pairs.length - 1].l - index + 1);

        int sum2 = 0;
        index = 0;
        stack = new Stack<>();
        for (int i = pairs.length - 1; i >= 0; i--) { // 뒤에부터
            if (stack.isEmpty()) {
                index = pairs[i].l;
                stack.add(pairs[i]);
            } else {
                if (stack.peek().h < pairs[i].h) {
                    sum2 += stack.peek().h * (index - pairs[i].l);
                    index = pairs[i].l;
                    stack.add(pairs[i]);
                }
            }

        }
        sum2 += stack.peek().h * (index - pairs[0].l + 1);

        System.out.println(sum1 + sum2 - ((pairs[pairs.length - 1].l - pairs[0].l + 1) * maxHeight));
    }

    static class Pair implements Comparable<Pair> {
        int l;
        int h;

        public Pair(int l, int h) {
            this.l = l;
            this.h = h;
        }

        @Override
        public int compareTo(Pair other) {
            return this.l - other.l;
        }
    }
}
