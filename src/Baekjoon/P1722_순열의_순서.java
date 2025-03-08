package Baekjoon;
import java.util.*;
import java.io.*;

public class P1722_순열의_순서 {
    static int n;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int op = Integer.parseInt(st.nextToken());

        int k = 0;

        int[] tArray = new int[n];
        if (op == 1) {
            k = Integer.parseInt(st.nextToken());
        }

        arr = new int[n];

        if (op == 1) {
            for (int i = 1; i <= n; i++) {
                arr[i - 1] = i;
            }

            Arrays.sort(arr);
        } else {
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        do {
            if (op == 1 && cnt == k - 1) {
                for (int i = 0; i < n; i++) {
                    sb.append(arr[i]).append(" ");
                }
            }

            cnt++;
        } while (np());

        if (op == 2) {
            int temp = 1;

            for (int i = 0; i < n; i++) {
                temp *= (n - i);
            }

            sb.append(temp - cnt + 1);
        }

        System.out.println(sb);
    }

    static boolean np() {
        int i = n - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) i--;

        if (i == 0) return false;

        int j = n - 1;
        while (arr[i - 1] >= arr[j]) j--;

        swap(i - 1, j);

        int k = n - 1;
        while (i < k) swap(i++, k--);

        return true;
    }

    static void swap(int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

}
