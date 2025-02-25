package Baekjoon;
import java.util.*;
import java.io.*;

public class P15565_귀여운_라이언 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                list.add(i);
            }
        }

        if (list.size() < k) {
            System.out.println(-1);
            System.exit(0);
        }

        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i <= list.size() - k; i++) {
            int start = list.get(i);
            int end = list.get(i + k - 1);
            minValue = Math.min(minValue, end - start+1);
        }

        System.out.println(minValue);
    }
}
