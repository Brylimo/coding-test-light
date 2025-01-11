package Baekjoon;
import java.util.*;
import java.io.*;

public class P17406_배열_돌리기_4 {
    static int n, m, k, ans = Integer.MAX_VALUE;
    static int[][] arr;
    static int[][] nextArr;
    static ArrayList<Integer> candidates = new ArrayList<>();
    static boolean[] visited;
    static Pair[] pairs;

    static void rotate(int r, int c, int s) {
        int startX = r - s - 1;
        int startY = c - s - 1;
        int endX = r + s - 1;
        int endY = c + s - 1;

        // left
        int temp = nextArr[startX][startY];
        for (int i = startX; i < endX; i++) {
            nextArr[i][startY] = nextArr[i + 1][startY];
        }

        // bottom
        for (int i = startY; i < endY; i++) {
            nextArr[endX][i] = nextArr[endX][i + 1];
        }

        // right
        for (int i = endX; i > startX; i--) {
            nextArr[i][endY] = nextArr[i - 1][endY];
        }

        // top
        for (int i = endY; i > startY; i--) {
            nextArr[startX][i] = nextArr[startX][i - 1];
        }

        nextArr[startX][startY + 1] = temp;
    }

    static void calculate(int current) {
        if (current == k) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    nextArr[i][j] = arr[i][j];
                }
            }

            for (int i = 0; i < k; i++) {
                int r = pairs[candidates.get(i)].r;
                int c = pairs[candidates.get(i)].c;
                int s = pairs[candidates.get(i)].s;

                for (int j = s; j > 0; j--) {
                    rotate(r, c, j);
                }
            }

            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = 0; j < m; j++) {
                    sum += nextArr[i][j];
                }
                ans = Math.min(ans, sum);
            }

            return;
        }

        for (int i = 0; i < k; i++) {
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

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        nextArr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pairs = new Pair[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            pairs[i] = new Pair(r, c, s);
        }

        visited = new boolean[k];
        calculate(0);

        System.out.println(ans);
    }

    static class Pair {
        int r;
        int c;
        int s;

        public Pair(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}
