package Baekjoon;
import java.util.*;
import java.io.*;

public class P19539_사과나무 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int oneCnt = 0;
        int twoCnt = 0;
        for (int i = 0; i < n; i++) {
            int temp = arr[i];

            oneCnt += temp % 2;
            twoCnt += temp / 2;
        }

        while (twoCnt > oneCnt) {
            twoCnt -= 1;
            oneCnt += 2;
        }

        if (oneCnt == twoCnt) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
