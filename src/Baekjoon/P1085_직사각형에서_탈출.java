package Baekjoon;
import java.util.*;
import java.io.*;

public class P1085_직사각형에서_탈출 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int ans = Integer.MAX_VALUE;
        ans = Math.min(ans, x);
        ans = Math.min(ans, y);
        ans = Math.min(ans, w - x);
        ans = Math.min(ans, h - y);

        System.out.println(ans);
    }
}
