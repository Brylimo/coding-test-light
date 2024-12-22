package Baekjoon;
import java.util.*;
import java.io.*;

public class P11780_플로이드_2 {
    static final int INT_MAX = Integer.MAX_VALUE;
    static int n, m;
    static long[][] arr;
    static ArrayList<Integer>[][] path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // 비용의 최솟값을 담을 배열
        arr = new long[n][n];
        path = new ArrayList[n][n];

        // arr 초기화 작업
        for (int i = 0; i < n; i++) {
            Arrays.fill(arr[i], INT_MAX);
        }

        for (int i = 0; i < n; i++) {
            arr[i][i] = 0;
        }

        // path 초기화 작업
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                path[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a - 1][b - 1] = Math.min(arr[a - 1][b - 1], c);
            path[a - 1][b - 1] = new ArrayList<>(Arrays.asList(a - 1, b - 1));
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                        path[i][j].clear();

                        path[i][j].addAll(path[i][k]);
                        path[i][j].remove(path[i][j].size() - 1);
                        path[i][j].addAll(path[k][j]);
                    }
                }
            }
        }

        // 전체 최소 비용 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] >= INT_MAX) {
                    sb.append(0).append(" ");
                } else {
                    sb.append(arr[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (path[i][j].size() == 0) {
                    sb.append(0).append('\n');
                } else {
                    sb.append(path[i][j].size()).append(" ");

                    for (int item : path[i][j]) {
                        sb.append(item + 1).append(" ");
                    }

                    sb.append('\n');
                }
            }
        }

        System.out.println(sb);
    }
}
