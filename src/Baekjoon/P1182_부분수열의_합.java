package Baekjoon;
import java.util.*;
import java.io.*;

public class P1182_부분수열의_합 {
    static int n;
    static int s;
    static int[] arr;
    static int ans;
    static ArrayList<Integer> candidates = new ArrayList<>();

    static void calculate(int current, boolean flag) {
        if (flag) {
            int sum = 0;
            for (int i = 0; i < candidates.size(); i++) {
                sum += arr[candidates.get(i)];
            }

            if (candidates.size() > 0 && sum == s) {
                ans += 1;
            }
        }

        if (current == n) {
            return;
        }

        candidates.add(current);
        calculate(current + 1, true);
        candidates.remove(candidates.size() - 1);

        calculate(current + 1, false);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        calculate(0, true);

        System.out.println(ans);
    }
}
