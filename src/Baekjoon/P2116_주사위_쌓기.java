package Baekjoon;
import java.util.*;
import java.io.*;

public class P2116_주사위_쌓기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()); // a, f
            int b = Integer.parseInt(st.nextToken()); // b, d
            int c = Integer.parseInt(st.nextToken()); // c, e
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());

            pairs[i] = new Pair(a, b, c, d, e, f);
        }

        int ans = 0;
        for (int i = 1; i <= 6; i++) { // 첫번째 주사위에 적힌 수
            Pair firstDice = pairs[0]; // 가장 아래에 있는 주사위

            int other = firstDice.map.get(i);

            int sum = 0;
            for (int j = 1; j <= 6; j++) {
                if (j == i || j == other) continue;

                sum = Math.max(sum, j);
            }

            int top = i;
            for (int j = 1; j < n; j++) {
                Pair current = pairs[j];

                int another = current.map.get(top);

                int tempMax = 0;
                for (int k = 1; k <= 6; k++) {
                    if (k == top || k == another) continue;

                    tempMax = Math.max(tempMax, k);
                }

                top = another;
                sum += tempMax;
            }

            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }

    static class Pair {
        HashMap<Integer, Integer> map = new HashMap<>();

        public Pair(int a, int b, int c, int d, int e, int f) {
            map.put(a, f);
            map.put(b, d);
            map.put(c, e);
            map.put(f, a);
            map.put(d, b);
            map.put(e, c);
        }
    }
}
