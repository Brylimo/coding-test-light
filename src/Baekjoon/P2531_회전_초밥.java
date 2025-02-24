package Baekjoon;
import java.util.*;
import java.io.*;

public class P2531_회전_초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[2 * n];
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            arr[i] = x;
            arr[i + n] = x;
        }

        int[] radix = new int[d + 1];
        HashSet<Integer> set = new HashSet<>();

        // 쿠폰 집어넣기
        set.add(c);
        radix[c]++;
        for (int i = 0; i < k; i++) {
            set.add(arr[i]);
            radix[arr[i]]++;
        }

        int cnt = set.size();
        for (int i = 0; i < 2 * n; i++) {
            int current = arr[i];

            radix[current]--;
            if (radix[current] <= 0) {
                set.remove(current);
            }

            if (k + i < 2 * n) {
                radix[arr[k + i]]++;
                set.add(arr[k + i]);
            }

            cnt = Math.max(cnt, set.size());
        }

        System.out.println(cnt);
    }
}
