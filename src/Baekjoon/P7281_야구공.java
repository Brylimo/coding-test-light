package Baekjoon;
import java.util.*;
import java.io.*;

public class P7281_야구공 {
    static final int m = 9;
    static boolean[] visited = new boolean[m];
    static int[][] players;
    static ArrayList<Integer> candidates = new ArrayList<>();

    static void evaluate() {
        int inning = 0;
        int outCnt = 0;

        for (int i = 0; i < m; i++) {
            if (players[inning][i] == 0) {
                outCnt += 1;
            } else {

            }
        }
    }

    static void calculate(int current) {
        if (current == m) {
            candidates.add(3, 0);

            evaluate();
            return;
        }

        for (int i = 1; i < m; i++) {
            if (!visited[i]) {
                visited[i] = true;
                candidates.add(i);
                calculate(current + 1);
                candidates.remove(candidates.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        players = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        calculate(0);
    }
}
