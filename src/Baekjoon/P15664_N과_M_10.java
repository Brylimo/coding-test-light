package Baekjoon;
import java.util.*;
import java.io.*;

public class P15664_N과_M_10 {
    static int n, m;
    static int[] arr;
    static LinkedHashSet<String> set = new LinkedHashSet<>();
    static ArrayList<Integer> candidates = new ArrayList<>();
    static StringBuilder sb;
    static void calculate(int idx, int cnt) {
        if (cnt == m) {
            sb = new StringBuilder();
            for (int i = 0; i < candidates.size(); i++) {
                sb.append(candidates.get(i)).append(" ");
            }

            if (!set.contains(sb.toString())) {
                set.add(sb.toString());
            }

            return;
        }

        if (idx == n) {
            if (cnt == m) {
                sb = new StringBuilder();
                for (int i = 0; i < candidates.size(); i++) {
                    sb.append(candidates.get(i)).append(" ");
                }

                if (!set.contains(sb.toString())) {
                    set.add(sb.toString());
                }
            }

            return;
        }

        candidates.add(arr[idx]);
        calculate(idx + 1, cnt + 1);
        candidates.remove(candidates.size() - 1);

        calculate(idx + 1, cnt);
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
        calculate(0, 0);

        for (String ans : set) {
            System.out.println(ans);
        }
    }
}
