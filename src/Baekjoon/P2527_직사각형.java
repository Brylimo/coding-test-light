package Baekjoon;
import java.util.*;
import java.io.*;

public class P2527_직사각형 {
    static final int MAX_N = 50000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken());
            int q1 = Integer.parseInt(st.nextToken());

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());

            // 떨어져 있음
            if (p1 < x2 || x1 > p2 || y1 > q2 || q1 < y2) {
                System.out.println("d");
            } else if ((x1 == p2 && y1 == q2) || (p1 == x2 && q1 == y2) || (x1 == p2 && q1 == y2) || (p1 == x2 && y1 == q2)) {
                System.out.println("c");
            } else if (x1 == p2 || p1 == x2) { // 수직선이 겹침
                if (!(q1 < y2 || y1 > q2)) { // y축 범위 겹침
                    System.out.println("b");
                }
            } else if (y1 == q2 || q1 == y2) { // 수평선이 겹침
                if (!(p1 < x2 || x1 > p2)) { // x축 범위 겹침
                    System.out.println("b");
                }
            } else {
                System.out.println("a");
            }

        }
    }
}
