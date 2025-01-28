package Baekjoon;
import java.util.*;
import java.io.*;

public class P10163_색종이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[1001][1001];
        int n = Integer.parseInt(br.readLine());

        int[] check = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for (int j = b; j < b + h; j++) {
                for (int k = a; k < a + w; k++) {
                    if (arr[j][k] != 0) {
                        check[arr[j][k]]--;
                    }

                    check[i]++;
                    arr[j][k] = i;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(check[i]);
        }
    }
}
