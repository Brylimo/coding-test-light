package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1561_놀이공원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // n 바로 출력
        if (n <= m) System.out.println(n);

        long maxTime = 2000000000 * 30 + 1;

        long start = 1;
        long end = maxTime;

        long ans = -1; // 최대 시간을 구함
        while (start <= end) {
            long mid = (start + end) / 2;

            // 시간에 맞추어 개수를 셈
            long cnt = 0;
            for (int i = 0; i < m; i++) {
                cnt += (mid / arr[i]);
            }

            if (cnt < n) { // n이 더 큼 -> 증가해야 됨
                start = mid + 1;
            } else {
                end = mid - 1;
                ans = mid;
            }
        }

        long cnt = 0;
        for (int i = 0; i < m; i++) {
            cnt += (ans - 1) / arr[i];
        }



    }
}
