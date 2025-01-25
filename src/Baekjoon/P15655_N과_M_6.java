package Baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P15655_N과_M_6 {
    static int n, m;
    static int[] arr;
    static ArrayList<Integer> candidates = new ArrayList<>();


    static void calculate(int idx, int cnt) {
        if (cnt == m) {
            for (int i = 0; i < candidates.size(); i++) {
                System.out.print(candidates.get(i) + " ");
            }
            System.out.println();
            return;
        }

        if (idx == n) {
            if (cnt == m) {
                for (int i = 0; i < candidates.size(); i++) {
                    System.out.print(candidates.get(i) + " ");
                }
                System.out.println();
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
    }

}
