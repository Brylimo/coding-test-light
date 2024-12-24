package Baekjoon;
import java.util.*;
import java.io.*;

public class P2230_수_고르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // array 정렬
        Arrays.sort(arr);


        for (int i = 0; i < n; i++) {

        }

    }
}
