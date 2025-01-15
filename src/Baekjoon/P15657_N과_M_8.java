package Baekjoon;
import java.util.*;
import java.io.*;

public class P15657_N과_M_8 {
    static int n, m;
    static ArrayList<Integer> candidates = new ArrayList<>();
    static int[] arr;

    static void calculate(int current, int cnt) {
        if (cnt == candidates.size()) {
            for (int i = 0; i < candidates.size(); i++) {
                System.out.print(candidates.get(i) + " ");
            }
            System.out.println();
            return;
        }

        if (current == n) {
            if (cnt == candidates.size()) {
                for (int i = 0; i < candidates.size(); i++) {
                    System.out.print(candidates.get(i) + " ");
                }
                System.out.println();
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((candidates.size() > 0 && candidates.get(candidates.size() - 1) <= arr[i]) || candidates.size() == 0) {
                candidates.add(arr[i]);
                calculate(current + 1, cnt);
                candidates.remove(candidates.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        calculate(0, m);
    }
}
