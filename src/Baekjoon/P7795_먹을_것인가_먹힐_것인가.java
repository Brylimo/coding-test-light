package Baekjoon;
import java.util.*;
import java.io.*;

public class P7795_먹을_것인가_먹힐_것인가 {
    static int n, m;
    static int[] a, b;
    static StringBuilder sb = new StringBuilder();

    static int getUpperBound(int target) {
        int s = 0, e = a.length;

        while (s < e) {
            int mid = (s + e) / 2;

            if (a[mid] <= target) s = mid + 1;
            else e = mid;
        }

        return e;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            a = new int[n];
            b = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(a);
            int ans = 0;
            for (int i = 0; i < m; i++) {
                int idx = getUpperBound(b[i]);

                ans += n - idx;
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}
