package Baekjoon;
import java.util.*;
import java.io.*;

public class P11758_CCW {
    static int[] ax = new int[3];
    static int[] ay = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            ax[i] = x;
            ay[i] = y;
        }

         int res = (ax[0] * ay[1] + ax[1] * ay[2] + ax[2]* ay[0]) - (ax[1] * ay[0] + ax[2] * ay[1] + ax[0] * ay[2]);

        if (res > 0) {
            System.out.println(1);
        } else if (res < 0) {
            System.out.println(-1);
        } else {
            System.out.println(0);
        }
    }
}
