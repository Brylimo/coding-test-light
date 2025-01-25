package Baekjoon;
import java.util.*;
import java.io.*;

public class P15656_N과_M_7 {
    static int n, m;
    static int[] arr;
    static boolean[] isSelected;
    static ArrayList<Integer> candidates = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static void calculate(int idx) {
        if (candidates.size() == m) {
            for (int i = 0; i < m; i++) {
                sb.append(candidates.get(i)).append(" ");
            }
            sb.append("\n");
            return;
        }

        if (idx == n) {
            if (candidates.size() == m) {
                for (int i = 0; i < m; i++) {
                    sb.append(candidates.get(i)).append(" ");
                }
                sb.append("\n");
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            candidates.add(arr[i]);
            calculate(idx + 1);
            candidates.remove(candidates.size() - 1);
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
        calculate(0);

        System.out.println(sb);
    }
}