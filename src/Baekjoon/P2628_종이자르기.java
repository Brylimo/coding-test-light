package Baekjoon;
import java.util.*;
import java.io.*;

public class P2628_종이자르기 {
    static int n, m;
    static int[][] arr;
    static int ans = 0;
    static ArrayList<Integer> columns = new ArrayList<>();
    static ArrayList<Integer> rows = new ArrayList<>();
    static ArrayList<Integer> columns2 = new ArrayList<>();
    static ArrayList<Integer> rows2 = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        int cnt = Integer.parseInt(br.readLine());

        columns.add(0);
        rows.add(0);
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());

            int kind = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (kind == 0) { // 가로 방향
                columns.add(num);
            } else if (kind == 1) { // 세로 방향
                rows.add(num);
            }
        }
        columns.add(n);
        rows.add(m);

        Collections.sort(columns);
        Collections.sort(rows);

        columns2.add(columns.get(0));
        for (int i = 0; i < columns.size() - 1; i++) {
            columns2.add(columns.get(i + 1) - columns.get(i));
        }

        for (int i = 0; i < rows.size() - 1; i++) {
            rows2.add(rows.get(i + 1) - rows.get(i));
        }

        for (int i = 0; i < columns2.size(); i++) {
            for (int j = 0; j < rows2.size(); j++) {
                ans = Math.max(ans, columns2.get(i) * rows2.get(j));
            }
        }

        System.out.println(ans);
    }
}
