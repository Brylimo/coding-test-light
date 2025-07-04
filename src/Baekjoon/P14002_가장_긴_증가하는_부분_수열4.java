package Baekjoon;
import java.util.*;
import java.io.*;

public class P14002_가장_긴_증가하는_부분_수열4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int[] track = new int[n];

        // 초기화
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            track[i] = i; // 자기 자신을 가리킴
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) { // 더 크면
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        track[i] = j; // 전에 방문한 인덱스를 기록
                    }
                }
            }
        }

        int length = 0;
        int maxIdx = -1;
        for (int i = 0; i < n; i++) {
            if (length < dp[i]) {
                length = dp[i];
                maxIdx = i;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (maxIdx != track[maxIdx]) {
               list.add(maxIdx);
               maxIdx = track[maxIdx];
        }

        list.add(maxIdx);

        System.out.println(length);
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(arr[list.get(i)] + " ");
        }
    }
}
