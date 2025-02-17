package Baekjoon;
import java.util.*;
import java.io.*;

public class P17952_과제는_끝나지_않아 {
    public static ArrayDeque<Pair> stack = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int ans = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int a = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                if (t - 1 == 0) {
                    ans += a;
                } else {
                    stack.push(new Pair(a, t - 1));
                }
            } else {
                if (!stack.isEmpty()) {
                    Pair current = stack.pop();

                    current.time -= 1;

                    if (current.time == 0) {
                        ans += current.score;
                    } else {
                        stack.push(current);
                    }
                }
            }
        }

        System.out.println(ans);

    }

    static class Pair {
        int time;
        int score;

        public Pair(int score, int time) {
            this.score = score;
            this.time = time;
        }
    }
}
