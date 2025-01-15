package Baekjoon;
import java.util.*;
import java.io.*;

public class P1722_순열의_순서 {
    static int n;
    static int[] arr;
    static ArrayList<Integer> tlist = new ArrayList<>();
    static boolean[] visited;
    static ArrayList<Integer> candidates = new ArrayList<>();
    static ArrayList<int[]> ans = new ArrayList<>();

    static void calculate(int current) {
        if (current == n) {
            ans.add(candidates.stream().mapToInt(i->i).toArray());

            if (tlist.size() != 0) {
                boolean flag = true;
                for (int i = 0; i < n; i++) {
                    if (tlist.get(i) != candidates.get(i)) {
                        flag = false;
                    }
                }

                if (flag) {
                    System.out.print(ans.size());
                    System.exit(1);
                }
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                candidates.add(i + 1);
                calculate(current + 1);
                candidates.remove(candidates.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        int op = Integer.parseInt(st.nextToken());

        if (op == 1) {
            int index = Integer.parseInt(st.nextToken());

            calculate(0);
            for (int i = 0; i < n; i++) {
                System.out.print(ans.get(index - 1)[i] + " ");
            }
        } else if (op == 2) {
            for (int i = 0; i < n; i++) {
                tlist.add(Integer.parseInt(st.nextToken()));
            }
            calculate(0);
        }
    }
}
