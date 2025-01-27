package Baekjoon;
import java.util.*;
import java.io.*;

public class P17387_선분_교차2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x3 = Integer.parseInt(st.nextToken());
        int y3 = Integer.parseInt(st.nextToken());
        int x4 = Integer.parseInt(st.nextToken());
        int y4 = Integer.parseInt(st.nextToken());

        int firstRes = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);
        int secondRes = (x1 * y2 + x2 * y4 + x4 * y1) - (x2 * y1 + x4 * y2 + x1 * y4);
        int thirdRes = (x3 * y4 + x4 * x1 + x1 * x3) - (x4 * y3 + x1 * y4 + x3 * y1);
        int fourthRes = (x3 * y4 + x4 * x2 + x2 * x3) - (x4 * y3 + x2 * y4 + x3 * y2);
        if ((firstRes > 0 && secondRes > 0) || (firstRes < 0 && secondRes < 0) || (thirdRes > 0 && fourthRes > 0) || (thirdRes < 0 && fourthRes < 0)) {
            System.out.println(0);
        } else {
            System.out.println(1);
        }
    }

}
