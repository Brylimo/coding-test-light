package Baekjoon;
import java.util.*;
import java.io.*;

public class P2605_줄_세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            order.add(i);
        }

        for (int i = 1; i <= n; i++) {
            if (arr[i - 1] == 0) continue;

            int current = i;
            int target = current - arr[i - 1];

            int temp = order.get(current - 1);
            order.remove(i - 1);
            order.add(target - 1, temp);
        }

        for (int i = 0; i < order.size(); i++) {
            System.out.print(order.get(i) + " ");
        }
    }
}
