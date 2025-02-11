package SWEA;
import java.io.*;
import java.util.StringTokenizer;

public class Solved_14510_나무_높이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];
            int maxVal = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                maxVal = Math.max(maxVal, arr[i]);
            }

            for (int i = 0; i < n; i++) {
                arr[i] = maxVal - arr[i];
            }

            int oneCnt = 0;
            int twoCnt = 0;
            for (int i = 0; i < n; i++) {
                oneCnt += arr[i] % 2;
                twoCnt += arr[i] / 2;
            }

            int balance = Math.abs(twoCnt - oneCnt);
            while (true) { // twoCnt와 oneCnt가 균형을 이룸
                twoCnt -= 1;
                oneCnt += 2;

                if (balance >= Math.abs(twoCnt - oneCnt)) {
                    balance = Math.abs(twoCnt - oneCnt);
                } else {
                    // 원상 복구
                    twoCnt += 1;
                    oneCnt -= 2;

                    break;
                }
            }

            int ans = 0;
            if (oneCnt < twoCnt) {
                ans = oneCnt * 2;
                ans += (twoCnt - oneCnt) * 2;
            } else if (oneCnt > twoCnt) {
                ans = twoCnt * 2;
                if (oneCnt - twoCnt == 1) {
                    ans += 1;
                } else {
                    ans += (oneCnt - 1 - twoCnt) * 2 + 1;
                }
            } else {
                ans = twoCnt * 2;
            }

            System.out.printf("#%d %d\n", t, ans);
        }
    }
}
