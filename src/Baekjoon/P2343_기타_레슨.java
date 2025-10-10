package Baekjoon;
import java.util.*;
import java.io.*;

public class P2343_기타_레슨 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        int start = 0;
        int end = sum;

        int ans = 0;
        OUT : while (start <= end) {
            int mid = (start + end) / 2;

            int cnt = 1;
            int sub = 0;
            for (int i = 0; i < n; i++) {
                if (sub + arr[i] <= mid) {
                    sub += arr[i];
                } else {
                    cnt += 1;
                    sub = arr[i];

                    if (sub > mid) {
                        start = 2 * sub - end;
                        continue OUT;
                    }
                }
            }

            if (sub > 0 && sub > mid) {
                start = mid + 1;
                continue;
            }

            if (cnt <= m) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(ans);
    }
}
