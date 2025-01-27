package Baekjoon;

import java.util.*;
import java.io.*;
public class P10158_개미 {
    static int[] dx = new int[]{-1, 1, 1, -1};
    static int[] dy = new int[]{1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int t = Integer.parseInt(br.readLine());

        int x = 0;
        int y = 0;

        int wt = t % (2 * w);
        if (wt <= w - p) {
            y = p + wt;
        } else {
            wt -= w - p;
            if (wt < w) {
                y = w - wt;
            } else {
                y = wt - w;
            }
        }

        int ht = t % (2 * h);
        if (ht <= h - q) {
            x = q + ht;
        } else {
            ht -= h - q;
            if (ht < h) {
                x = h - ht;
            } else {
                x = ht - h;
            }
        }

        System.out.println(y + " " + x);
    }

}
